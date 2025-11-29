package com.example.demo.model;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for userType.
 */
@XmlType(name = "UserType")
@XmlEnum
public enum UserType {

    REGULAR,
    PREMIUM,
    ADMIN,
    MODERATOR;

    public String value() {
        return name();
    }

    public static UserType fromValue(String v) {
        return valueOf(v);
    }
}