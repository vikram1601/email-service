package com.wah.email.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.wah.email.bean.EmailPayload;
import com.wah.email.constants.Email;
import com.wah.email.constants.EventType;

/**
 *
 * @author vikram
 *
 */
@Service("emailDataBuilder")
public class EmailDataBuilder {
    private final Logger logger = Logger.getLogger(this.getClass());

    private VelocityEngine velocityEngine;

    /**
     *
     * @return {@link VelocityEngine}
     */
    public VelocityEngine getVelocityEngine() {
        return this.velocityEngine;
    }

    /**
     *
     * @param velocityEngine
     */
    @Autowired
    @Required
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    /**
     * @author vikram
     * @param payload
     * @return {@link EmailPayload}
     */
    public EmailPayload buildEmailData(EmailPayload payload) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("data", payload.getData());
        HashMap<Email, String> data = this._fetchEmailData(payload);
        payload.setFrom(data.get(Email.FROM));
        payload.setSubject(data.get(Email.SUBJECT));
        String content =
                VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine,
                        "emailTemplates/" + data.get(Email.TEMPLATE) + ".vm", "UTF-8", model);
        payload.setContent(content);

        return payload;
    }

    /**
     * this is used to set email content, subject, and from into payload
     *
     * @author vikram
     * @param payload
     * @return {@link HashMap}
     */
    private HashMap<Email, String> _fetchEmailData(EmailPayload payload) {
        HashMap<Email, String> hashMap = new HashMap<Email, String>();
        String emailSubject = "";
        String emailTemplate = "";;
        String emailFrom = "";

        if (payload.getEventType() == EventType.USER_SIGNUP) {
            emailSubject = "Thank you for your interest. We'll get back soon.";
            emailTemplate = "signupmailer";
            emailFrom = "sales@wah.com";
        }

        hashMap.put(Email.SUBJECT, emailSubject);
        hashMap.put(Email.TEMPLATE, emailTemplate);
        hashMap.put(Email.FROM, emailFrom);
        return hashMap;
    }

}
