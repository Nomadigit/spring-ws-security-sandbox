package nomadigit.spring.ws.security.sandbox.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;

@ConfigurationProperties("application.keystore")
public record SignatureProperties(
        ClassPathResource classPath,
        char[] password,
        String alias
) {
}
