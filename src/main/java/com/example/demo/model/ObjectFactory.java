package com.example.demo.model;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * Объект фабрика для пакета com.example.generated
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _User_QNAME = new QName("http://example.com/ws", "User");
    private final static QName _CreateUserRequest_QNAME = new QName("http://example.com/ws", "CreateUserRequest");
    private final static QName _CreateUserResponse_QNAME = new QName("http://example.com/ws", "CreateUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.generated
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link User }
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link CreateUserRequest }
     */
    public CreateUserRequest createCreateUserRequest() {
        return new CreateUserRequest();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.com/ws", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.com/ws", name = "CreateUserRequest")
    public JAXBElement<CreateUserRequest> createCreateUserRequest(CreateUserRequest value) {
        return new JAXBElement<CreateUserRequest>(_CreateUserRequest_QNAME, CreateUserRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.com/ws", name = "CreateUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }
}