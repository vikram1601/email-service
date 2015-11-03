package com.wah.email.handler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.suj1th.rabpubsub.AbstractSubscriber;
import com.suj1th.rabpubsub.EventType;
import com.wah.email.bean.EmailPayload;
import com.wah.email.service.EmailDataBuilder;
import com.wah.email.service.EmailSender;

/**
 *
 * @author vikram
 *
 */
@Component
public class EmailHandler extends AbstractSubscriber<EmailPayload> {
    private EmailDataBuilder emailDataBuilder;
    private EmailSender emailSender;
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * @return the emailSender
     */
    public EmailSender getEmailSender() {
        return this.emailSender;
    }


    /**
     * @param emailSender the emailSender to set
     */
    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     *
     */
    public EmailHandler() {}


    @Override
    protected void handle(EmailPayload payload) {
        this._sendEmail(payload);
    }


    private boolean _sendEmail(EmailPayload payload) {
        boolean flag = false;
        try {
            payload = this.emailDataBuilder.buildEmailData(payload);
            flag = this.emailSender.sendEmail(payload);
        } catch (Exception e) {
            this.logger.error("Exception", e);
        }
        return flag;
    }

    @Override
    protected EventType getEventType() {
        return EventType.TEST;
    }


    /**
     * @return the emailDataBuilder
     */
    public EmailDataBuilder getEmailDataBuilder() {
        return this.emailDataBuilder;
    }


    /**
     * @param emailDataBuilder the emailDataBuilder to set
     */
    public void setEmailDataBuilder(EmailDataBuilder emailDataBuilder) {
        this.emailDataBuilder = emailDataBuilder;
    }


}