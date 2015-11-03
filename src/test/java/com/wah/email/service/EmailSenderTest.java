package com.wah.email.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.wah.email.bean.EmailPayload;

/**
 *
 * @author vikram
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailSenderTest {
    private Logger logger = Logger.getLogger(this.getClass());

    @Mock(name = "emailSender")
    private EmailSender emailSender;

    /**
     * this is used to test functioning of emailSender
     */
    @Test
    public void testSendEmail() {
        EmailPayload payload = new EmailPayload();
        try {
            Set<String> to = new HashSet<String>();
            to.add("test@yopmail.com");
            payload.setTo(to);
            payload.setFrom("test@yopmail.com");
            payload.setSubject("Test mail sended at:" + new Date());
            payload.setContent("-- test email content --");
            boolean flag = this.emailSender.sendEmail(payload);
            if (flag && this.logger.isInfoEnabled()) {

                this.logger.info("-- email send successfully --");
            }
        } catch (Exception e) {
            this.logger.error("Exception while sending email", e);
        }
    }

}
