package com.example.demo.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for CreateUserRequest complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateUserRequest", propOrder = {
        "user"
})
@XmlRootElement(name = "CreateUserRequest", namespace = "http://example.com/ws")
public class CreateUserRequest {

    @XmlElement(required = true)
    protected User user;

    public User getUser() {
        return user;
    }

    public void setUser(User value) {
        this.user = value;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "user=" + user +
                '}';
    }
}
