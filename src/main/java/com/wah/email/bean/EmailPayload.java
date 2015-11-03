package com.wah.email.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.suj1th.rabpubsub.Payload;
import com.wah.email.constants.EventType;
/**
 *
 * @author vikram
 *
 */
public class EmailPayload extends Payload {
    private Set<String> to = new HashSet<String>();
    private Set<String> cc = new HashSet<String>();
    private Set<String> bcc = new HashSet<String>();
    private String subject;
    private String content;
    private String from;
    private List<Attachment> attachment = new ArrayList<Attachment>();
    private EventType eventType;
    private HashMap<String, String> data = new HashMap<String, String>();

    /**
     * @return the to
     */
    public Set<String> getTo() {
        return this.to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Set<String> to) {
        this.to = to;
    }

    /**
     * @return the cc
     */
    public Set<String> getCc() {
        return this.cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(Set<String> cc) {
        this.cc = cc;
    }

    /**
     * @return the bcc
     */
    public Set<String> getBcc() {
        return this.bcc;
    }

    /**
     * @param bcc the bcc to set
     */
    public void setBcc(Set<String> bcc) {
        this.bcc = bcc;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the attachment
     */
    public List<Attachment> getAttachment() {
        return this.attachment;
    }

    /**
     * @param attachment the attachment to set
     */
    public void setAttachment(List<Attachment> attachment) {
        this.attachment = attachment;
    }

    /**
     * @return the eventType
     */
    public EventType getEventType() {
        return this.eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the data
     */
    public HashMap<String, String> getData() {
        return this.data;
    }

    /**
     * @param data the data to set
     */
    public void setData(HashMap<String, String> data) {
        this.data = data;
    }



}
