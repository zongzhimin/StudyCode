package com.zzm.test.vast.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClientSocket extends Thread
{
    private String name;
    @Override
    public void run()
    {
        try
        {
            DatagramSocket socket = new DatagramSocket();
            String serverHost = "127.0.0.1";
            int serverPort = 3344;
            DatagramPacket dp = new DatagramPacket((name).getBytes(), (name).getBytes().length,
                InetAddress.getByName(serverHost), serverPort);
            socket.send(dp);
            
            DatagramPacket dp1 = new DatagramPacket(buffer, buffer.length);
            socket.receive(dp1);
            byte[] data = new byte[dp1.getLength()];
            System.arraycopy(dp1.getData(), 0, data, 0, dp1.getLength());
            
            System.out.println("服务端回应数据：" + new String(data));
            socket.close();
        }
        catch (Throwable e1)
        {
            e1.printStackTrace();
        }
        
    }
    
    private byte[] buffer = new byte[1024];
    
    private static DatagramSocket ds = null;
    
    public UdpClientSocket(String name) {
		this.name = name;
	}

	/**
     * 构造函数，创建UDP客户端
     */
    public UdpClientSocket()
        throws Exception
    {
        // ds = new DatagramSocket(8899); // 邦定本地端口作为客户端
    }
    
    /**
     * 向指定的服务端发送数据信息
     */
    public final void send(final String host, final int port, final byte[] bytes)
        throws IOException
    {
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);
        ds.send(dp);
    }
    
    /**
     * 接收从指定的服务端发回的数据
     */
    public final byte[] receive()
        throws Exception
    {
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        ds.receive(dp);
        byte[] data = new byte[dp.getLength()];
        System.arraycopy(dp.getData(), 0, data, 0, dp.getLength());
        return data;
    }
    
    public static void main(String[] args)
    {
        while (true)
        {
            
            try
            {
                new Thread(new UdpClientSocket("a")).start();
                new Thread(new UdpClientSocket("b")).start();
            }
            catch (Throwable e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}

