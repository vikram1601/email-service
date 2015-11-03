package com.wah.email.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.wah.email.bean.EmailPayload;
import com.wah.email.constants.EventType;

/**
 *
 * @author vikram
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailDataBuilderTest {
    private Logger logger = Logger.getLogger(this.getClass());

    @Mock(name = "emailDataBuilder")
    private EmailDataBuilder emailDataBuilder;

    /**
     * used to test build email data
     */
    @Test
    public void testBuildEmailData() {
        EmailPayload payload = new EmailPayload();
        Set<String> to = new HashSet<String>();
        to.add("test@yopmail.com");
        payload.setTo(to);
        payload.setEventType(EventType.USER_SIGNUP);
        payload = this.emailDataBuilder.buildEmailData(payload);
        if (this.logger.isInfoEnabled() && payload.getSubject().length() > 0) {
            this.logger.info("-- email data build successfully --");
        }
    }
}
