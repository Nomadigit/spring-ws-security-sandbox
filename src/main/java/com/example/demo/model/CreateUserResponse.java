package com.example.demo.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for CreateUserResponse complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateUserResponse", propOrder = {
        "success",
        "message",
        "createdUser"
})
@XmlRootElement(name = "CreateUserResponse", namespace = "http://example.com/ws")
public class CreateUserResponse {

    protected boolean success;

    @XmlElement(required = true)
    protected String message;

    @XmlElement(name = "created_user")
    protected User createdUser;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean value) {
        this.success = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User value) {
        this.createdUser = value;
    }

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", createdUser=" + createdUser +
                '}';
    }
}
