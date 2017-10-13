package com.zzm.test.mail;

import org.junit.Assert;

public class Config {
	private static final long serialVersionUID = 1L;
	// 发送方邮件服务器
	private String smtp;
	// 发送时是否验证
	private boolean auth;
	// 验证用户名
	private String username;
	// 验证密码
	private String password;
	// 是否使用代理
	private boolean proxy;
	// 代理地址
	private String proxyHost;
	// 代理端口
	private int proxyPort = 80;

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isProxy() {
		return proxy;
	}

	public void setProxy(boolean proxy) {
		this.proxy = proxy;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	protected void check() {
		if (auth) {
			Assert.assertNotNull(username);
			Assert.assertNotNull(password);
		}
		if (proxy) {
			Assert.assertNotNull(proxyHost);
		}
	}
}
