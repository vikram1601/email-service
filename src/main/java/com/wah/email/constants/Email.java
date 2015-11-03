package com.wah.email.constants;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vikram
 *
 */
public enum Email {
    /**
     *
     */
    SUBJECT("subject"),

    /**
     *
     */
    TEMPLATE("template"),

    /**
     *
     */
    FROM("from");

    String value;

    private static final Map<String, Email> lookup = new HashMap<String, Email>();

    static {
        for (Email ls : Email.values()) {
            lookup.put(ls.getValue(), ls);
        }
    }

    Email(String value) {
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
    public static Email get(String value) {
        return lookup.get(value);
    }
}
