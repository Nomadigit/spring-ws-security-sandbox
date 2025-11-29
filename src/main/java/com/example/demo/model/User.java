package com.example.demo.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Java class for User complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
        "id",
        "username",
        "email",
        "age",
        "active",
        "salary",
        "createdDate",
        "userType"
})
public class User {

    @XmlElement(required = true)
    protected Long id;

    @XmlElement(required = true)
    protected String username;

    @XmlElement(required = true)
    protected String email;

    protected Integer age;
    protected Boolean active;
    protected BigDecimal salary;

    @XmlElement(name = "created_date")
    protected Date createdDate;

    @XmlElement(name = "user_type")
    protected UserType userType;

    // Конструкторы
    public User() {
    }

    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer value) {
        this.age = value;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean value) {
        this.active = value;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal value) {
        this.salary = value;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date value) {
        this.createdDate = value;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType value) {
        this.userType = value;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", active=" + active +
                ", salary=" + salary +
                ", createdDate=" + createdDate +
                ", userType=" + userType +
                '}';
    }
}
