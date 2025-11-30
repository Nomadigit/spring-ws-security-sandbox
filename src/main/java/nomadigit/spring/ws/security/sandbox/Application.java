package nomadigit.spring.ws.security.sandbox;

import nomadigit.spring.ws.security.sandbox.configuration.properties.SignatureProperties;
import nomadigit.spring.ws.security.sandbox.service.WSCallService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@EnableConfigurationProperties(SignatureProperties.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        WSCallService wsCallService = context.getBean(WSCallService.class);
        while (true) {
            try {
                wsCallService.call();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            } finally {
                System.exit(0);
            }
        }
    }
}
