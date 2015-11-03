package com.wah.email.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.wah.email.bean.Attachment;
import com.wah.email.bean.EmailPayload;
import com.wah.email.util.PropertyLoader;

/**
 * @author User
 */
@Service("emailSender")
public class EmailSender {

    private Properties emailProperties;
    Session session;
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     *
     */
    public EmailSender() {}

    /**
     *
     */
    @PostConstruct
    public void init() {
        this.emailProperties = this.getMailProperties();
        this.session = Session.getInstance(this.loadProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailSender.this.emailProperties.getProperty("email"),
                        EmailSender.this.emailProperties.getProperty("pass"));
            }
        });
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", this.emailProperties.getProperty("smtp"));
        properties.put("mail.smtp.starttls.enable", this.emailProperties.getProperty("starttls"));
        properties.put("mail.smtp.host", this.emailProperties.getProperty("host"));
        properties.put("mail.smtp.port", this.emailProperties.getProperty("port"));
        properties.put("ccmail", this.emailProperties.getProperty("ccmail"));
        return properties;
    }

    private Properties getMailProperties() {
        return PropertyLoader.loadProperties("/payload.properties");
    }


    /**
     * @author vikram
     * @param payload
     * @throws Exception
     * @return boolean
     */
    public boolean sendEmail(EmailPayload payload) throws Exception {
        boolean flag = false;
        MimeMessage mimeMessage = new MimeMessage(this.session);
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        // MimeMultipart should not be alternative
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        try {
            for (String ia : payload.getTo()) {
                mimeMessageHelper.addTo(new InternetAddress(ia));
            }
            for (String ia : payload.getCc()) {
                mimeMessageHelper.addCc(new InternetAddress(ia));
            }
            for (String ia : payload.getBcc()) {
                mimeMessageHelper.addBcc(new InternetAddress(ia));
            }

            mimeMessageHelper.setFrom(new InternetAddress(payload.getFrom()));
            mimeMessageHelper.setSubject(payload.getSubject());
            messageBodyPart.setContent(payload.getContent(), "text/html");
            multipart = new MimeMultipart("alternative");



            for (Attachment attachment : payload.getAttachment()) {
                MimeBodyPart messageBodyPartAttachment = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment.getFileLocation());
                messageBodyPartAttachment.setDataHandler(new DataHandler(source));
                messageBodyPartAttachment.setFileName(attachment.getFileName());
                multipart.addBodyPart(messageBodyPartAttachment);
            }

            // Add html part to multi part
            multipart.addBodyPart(messageBodyPart);
            mimeMessage.setContent(multipart);
            Transport.send(mimeMessage);
            flag = true;
        } catch (Exception e) {
            this.logger.error("Exception", e);
            flag = false;
        }
        return flag;
    }



}
