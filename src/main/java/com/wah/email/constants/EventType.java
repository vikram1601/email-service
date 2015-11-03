package com.wah.email.constants;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vikram
 *
 */
public enum EventType {

    /**
     *
     */
    USER_SIGNUP("user signup event");

    String value;

    private static final Map<String, EventType> lookup = new HashMap<String, EventType>();

    static {
        for (EventType ls : EventType.values()) {
            lookup.put(ls.getValue(), ls);
        }
    }

    EventType(String value) {
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
    public static EventType get(String value) {
        return lookup.get(value);
    }
}
