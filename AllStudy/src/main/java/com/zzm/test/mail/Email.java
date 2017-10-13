package com.zzm.test.mail;

import java.io.Serializable;

public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 邮件内容类型
	 * 
	 * @author lvyue
	 * 
	 */
	public enum ContentType {
		TEXT, HTML
	}

	// 发件人
	private String sender;
	// 收件人
	private String[] addressees;
	// 抄送人
	private String[] cc;
	// 安送人
	private String[] bcc;
	// 邮件主题
	private String subject;
	// 邮件内容
	private String content;
	// 文本内容类型
	private ContentType contentType = ContentType.TEXT;
	// 附件
	private boolean affix;
	// 附件地址
	private String[] affixName;

	/**
	 * 获取发信人地址
	 * 
	 * @return
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * 设置发信人地址
	 * 
	 * @param sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * 获取收信人地址
	 * 
	 * @return
	 */
	public String[] getAddressees() {
		return addressees;
	}

	/**
	 * 设置收信人地址
	 * 
	 * @param addressees
	 */
	public void setAddressees(String[] addressees) {
		this.addressees = addressees;
	}

	/**
	 * 获取邮件主题
	 * 
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置邮件主题
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获取邮件内容
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置邮件内容
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取邮件类型
	 * 
	 * @return
	 */
	public ContentType getContentType() {
		return contentType;
	}

	/**
	 * 设置邮件类型
	 * 
	 * @param contentType
	 */
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	/**
	 * 是否带有附件
	 * 
	 * @return
	 */
	public boolean hasAffix() {
		return affix;
	}

	/**
	 * 获取附件地址
	 * 
	 * @return
	 */
	public String[] getAffixName() {
		return affixName;
	}

	/**
	 * 设置附件地址
	 * 
	 * @param affixName
	 */
	public void setAffixName(String[] affixName) {
		this.affixName = affixName;
		affix = (this.affixName != null && this.affixName.length > 0);
	}

	public String[] getCc() {
		return cc;
	}

	/**
	 * 抄送人
	 * 
	 * @param cc
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	/**
	 * 添加暗送
	 * 
	 * @param bcc
	 */
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
}
