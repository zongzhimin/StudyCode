package com.sealine;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * Telnet操作器,基于commons-net-2.2.jar
 * 
 * @author JiangKunpeng
 *
 */
public class TelnetUtil
{
    
    public String prompt = ">"; // 结束标识字符串,Windows中是>,Linux中是#
    
    public char promptChar = '>'; // 结束标识字符
    
    public String loginPrompt = "login:";
    
    public String passwordPrompt = "Password:";
    
    private TelnetClient telnet;
    
    private InputStream in; // 输入流,接收返回信息
    
    private PrintStream out; // 向服务器写入 命令
    
    /**
     * @param termtype 协议类型：VT100、VT52、VT220、VTNT、ANSI
     * @param prompt 结果结束标识
     */
    public TelnetUtil(String termtype, String prompt, String ip, int port)
    {
        telnet = new TelnetClient(termtype);
        setPrompt(prompt);
        try
        {
            telnet.connect(ip, port);
        }
        catch (SocketException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public TelnetUtil(String termtype)
    {
        telnet = new TelnetClient(termtype);
    }
    
    public TelnetUtil()
    {
        telnet = new TelnetClient();
    }
    
    /**
     * 登录到目标主机
     * 
     * @param ip
     * @param port
     * @param username
     * @param password
     */
    public boolean login(String ip, int port, String username, String password)
    {
        try
        {
            if (null == telnet)
            {
                telnet.connect(ip, port);
            }
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
            if (!loginPrompt.equals("NONE"))
            {
                readUntil(loginPrompt);
                write(username);
            }
            readUntil(passwordPrompt);
            write(password);
            String rs = readUntil(null);
            if (rs != null && rs.contains("Login Failed"))
            {
                System.out.println("telnet login failed:" + ip);
                return false;
            }
            return true;
        }
        catch (Exception e)
        {
            System.out.println("telnet login exception:" + ip);
            return false;
        }
    }
    
    /**
     * 读取分析结果
     * 
     * @param pattern 匹配到该字符串时返回结果
     * @return
     */
    public String readUntil(String pattern)
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            char lastChar = (char)-1;
            boolean flag = pattern != null && pattern.length() > 0;
            if (flag)
                lastChar = pattern.charAt(pattern.length() - 1);
            char ch;
            int code = -1;
            while ((code = in.read()) != -1)
            {
                ch = (char)code;
                sb.append(ch);
                
                // 匹配到结束标识时返回结果
                if (flag)
                {
                    if (ch == lastChar && sb.toString().endsWith(pattern))
                    {
                        return sb.toString();
                    }
                }
                else
                {
                    // 如果没指定结束标识,匹配到默认结束标识字符时返回结果
                    if (ch == promptChar)
                        return sb.toString();
                }
                // 登录失败时返回结果
                if (sb.toString().contains("Login Failed"))
                {
                    return sb.toString();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }
    
    /** 读到pattern1就输入1,直到pattern2 */
    public String readUntil(String pattern1, String pattern2)
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            char lastChar1 = pattern1.charAt(pattern1.length() - 1);
            char lastChar2 = pattern2.charAt(pattern2.length() - 1);
            char ch;
            int code = -1;
            while ((code = in.read()) != -1)
            {
                ch = (char)code;
                sb.append(ch);
                
                if (ch == lastChar2 && sb.toString().endsWith(pattern2))
                {
                    // System.out.println("command over");
                    return sb.toString();
                }
                if (ch == lastChar1 && sb.toString().endsWith(pattern1))
                {
                    out.println("\r");
                    out.flush();
                    // System.out.println("input backspace,pattern1:"+pattern1+","+pattern2);
                    // sendCommand2(" ",pattern1);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }
    
    /**
     * 发送命令
     * 
     * @param value
     */
    public void write(String value)
    {
        try
        {
            out.println(value);
            out.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 发送命令,返回执行结果
     * 
     * @param command
     * @return
     */
    public String sendCommand(String command)
    {
        try
        {
            readUntil(prompt);
            write(command);
            return readUntil("[");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public String sendCommand2(String command, String pattern1)
    {
        try
        {
            write(command);
            return readUntil(pattern1, prompt);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("send command2 error:" + e.getMessage());
        }
        return null;
    }
    
    /**
     * 关闭连接
     */
    public void disconnect()
    {
        try
        {
            if (telnet != null && !telnet.isConnected())
                telnet.disconnect();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void setPrompt(String prompt)
    {
        if (prompt != null)
        {
            this.prompt = prompt;
            this.promptChar = prompt.charAt(prompt.length() - 1);
        }
    }
    
    // public static void main2(String[] args) {
    // TelnetUtil telnet = new TelnetUtil("VT220","$"); //Windows,用VT220,否则会乱码
    // telnet.loginPrompt = "login:";
    // telnet.passwordPrompt = "Password:";
    // telnet.login("192.168.0.224", 23, "wulian", "wulian");
    // String rs = telnet.sendCommand("cd / && ls");
    // telnet.disconnect();
    // System.out.println(rs);
    // }
    
    public static void main(String[] args)
    {
        TelnetUtil telnet = new TelnetUtil("VT220", ">", "172.28.0.33", 23); // Windows,用VT220,否则会乱码
        telnet.loginPrompt = "Username:";
        telnet.passwordPrompt = "Password:";
        telnet.login("172.28.0.33", 23, "admin", "P@55w0rd!");
        String rs = telnet.sendCommand("latlong");
        String rs1 = telnet.sendCommand("rx snr");
        StringBuffer stringBuffer = new StringBuffer();
        try
        {
            rs = new String(rs.getBytes("ISO-8859-1"), "GBK"); // 转一下编码
            stringBuffer.append(rs);
            rs1 = new String(rs1.getBytes("ISO-8859-1"), "GBK"); // 转一下编码
            stringBuffer.append(rs1);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        telnet.disconnect();
        System.out.println(stringBuffer);
    }
}