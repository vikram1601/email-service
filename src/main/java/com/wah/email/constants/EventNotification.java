package com.wah.email.constants;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vikram
 *
 */
public enum EventNotification {
    /**
     *
     */
    EMAIL("context welcome mailer"),


    /**
     *
     */
    MESSAGE("message");

    String value;

    private static final Map<String, EventNotification> lookup = new HashMap<String, EventNotification>();

    static {
        for (EventNotification ls : EventNotification.values()) {
            lookup.put(ls.getValue(), ls);
        }
    }

    EventNotification(String value) {
        this.value = value;
    }

    /**
     * @return value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @param value
     * @return Enum Event
     */
    public static EventNotification get(String value) {
        return lookup.get(value);
    }
}
