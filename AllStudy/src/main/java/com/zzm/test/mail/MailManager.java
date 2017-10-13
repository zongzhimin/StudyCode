package com.zzm.test.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import com.zzm.test.mail.Email.ContentType;

public class MailManager {
private final static Logger logger = Logger.getLogger(MailManager.class);
    
    private Session session;
    
    public MailManager(Config config)
    {
        try
        {
            reload(config);
        }
        catch (Throwable t)
        {
            logger.error("", t);
        }
    }
    
    public boolean send(Email email)
    {
        try
        {
            // 定义发送信息的对象
            Message msg = new MimeMessage(session);
            // 设置发信人地址
            if (StringUtils.isNotEmpty(email.getSender()))
            {
                msg.setFrom(new InternetAddress(email.getSender()));
            }
            else
            {
                msg.setFrom();
            }
            // 设置标题
            msg.setSubject(email.getSubject());
            // 获取收信人列表
            String[] addressees = email.getAddressees();
            // 非空验证
            Assert.assertNotNull(addressees);
            // 设置收信人
            InternetAddress[] toInternetAddressees = new InternetAddress[addressees.length];
            for (int i = 0; i < addressees.length; i++)
            {
                toInternetAddressees[i] = new InternetAddress(addressees[i]);
            }
            msg.setRecipients(RecipientType.TO, toInternetAddressees);
            // 设置抄送人
            String[] cc = email.getCc();
            if (cc != null && cc.length > 0)
            {
                // 设置收信人
                InternetAddress[] ccInternetAddressees = new InternetAddress[cc.length];
                for (int i = 0; i < cc.length; i++)
                {
                    ccInternetAddressees[i] = new InternetAddress(cc[i]);
                }
                msg.setRecipients(RecipientType.CC, ccInternetAddressees);
            }
            // 设置暗送
            String[] bcc = email.getBcc();
            if (bcc != null && bcc.length > 0)
            {
                // 设置收信人
                InternetAddress[] bccInternetAddressees = new InternetAddress[bcc.length];
                for (int i = 0; i < bcc.length; i++)
                {
                    bccInternetAddressees[i] = new InternetAddress(bcc[i]);
                }
                msg.setRecipients(RecipientType.BCC, bccInternetAddressees);
            }
            if (email.getContentType().equals(ContentType.TEXT))
            {
                msg.setText(email.getContent());
                // 添加附件
                if (email.hasAffix())
                {
                    Multipart mp = new MimeMultipart();
                    String[] affixName = email.getAffixName();
                    for (int i = 0; i < affixName.length; i++)
                    {
                        MimeBodyPart affix = new MimeBodyPart();
                        affix.attachFile(affixName[i]);
                        mp.addBodyPart(affix, i);
                    }
                    msg.setContent(mp);
                }
            }
            else if (email.getContentType().equals(ContentType.HTML))
            {
                // 添加邮件内容
                BodyPart body = new MimeBodyPart();
                body.setContent(email.getContent(), "text/html;charset=utf-8");
                Multipart mp = new MimeMultipart();
                mp.addBodyPart(body, 0);
                // 添加附件
                if (email.hasAffix())
                {
                    String[] affixName = email.getAffixName();
                    for (int i = 0; i < affixName.length; i++)
                    {
                        MimeBodyPart affix = new MimeBodyPart();
                        affix.attachFile(affixName[i]);
                        mp.addBodyPart(affix, i + 1);
                    }
                }
                msg.setContent(mp);
            }
            // 设置发送时间
            msg.setSentDate(new Date(System.currentTimeMillis()));
            // 发送邮件
            Transport.send(msg);
            return true;
        }
        catch (Throwable t)
        {
            logger.error("", t);
            return false;
        }
    }
    
    /**
     * 重新载入配置
     * 
     * @param config
     * @throws Throwable
     */
    public void reload(Config config)
        throws Throwable
    {
        try
        {
            // 不为空
            Assert.assertNotNull(config);
            // 配置验证
            config.check();
            // 定义属性对象
            Properties props = new Properties();
            // 设置属性
            // 设置smtp服务器
            props.put("mail.smtp.host", config.getSmtp());
            // 设置客户端是否向邮件服务器提交验证
            props.put("mail.smtp.auth", config.isAuth());
            // 定义验证对象
            Authenticator authenticator = null;
            if (config.isAuth())
            {
                // 设置验证信息
                authenticator = new LocalAuthenticator(config.getUsername(), config.getPassword());
            }
            // 设置代理
            if (config.isProxy())
            {
                System.setProperty("http.proxyHost", config.getProxyHost());
                System.setProperty("http.proxyPort", String.valueOf(config.getProxyPort()));
            }
            // 初始化Session
            session = Session.getDefaultInstance(props, authenticator);
        }
        catch (Throwable t)
        {
            logger.error("", t);
        }
    }
    
    /**
     * 验证地址类
     * 
     * @author lvyuely
     *         
     */
    class LocalAuthenticator extends Authenticator
    {
        private String username;
        
        private String password;
        
        public LocalAuthenticator(String username, String password)
        {
            this.username = username;
            this.password = password;
        }
        
        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            // TODO Auto-generated method stub
            return new PasswordAuthentication(username, password);
        }
    }
}
