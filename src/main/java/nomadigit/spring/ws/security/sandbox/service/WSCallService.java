package nomadigit.spring.ws.security.sandbox.service;

import nomadigit.spring.ws.security.sandbox.model.CreateUserRequest;
import nomadigit.spring.ws.security.sandbox.model.User;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class WSCallService {

    private final WebServiceTemplate webServiceTemplate;

    public WSCallService(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public void call() {
        var request = new CreateUserRequest();
        var user = new User(123L, "123", "fadfs@sadfas.dd");
        request.setUser(user);
        var response = webServiceTemplate.marshalSendAndReceive("http://localhost:8080/ws/hello", request);
        System.out.println(response);
    }
}
