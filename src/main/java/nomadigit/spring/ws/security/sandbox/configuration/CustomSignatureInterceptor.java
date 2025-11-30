package nomadigit.spring.ws.security.sandbox.configuration;

import nomadigit.spring.ws.security.sandbox.configuration.properties.SignatureProperties;
import nomadigit.spring.ws.security.sandbox.util.CustomSignatureSoapMessageModifier;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

@ConditionalOnBooleanProperty("application.ws.custom-security-interceptor")
@WebServiceTemplateConfiguration.CustomInterceptor
@Component
public class CustomSignatureInterceptor extends ClientInterceptorAdapter {
    private final SignatureProperties signatureProperties;

    @SneakyThrows
    public CustomSignatureInterceptor(SignatureProperties signatureProperties) {
        this.signatureProperties = signatureProperties;
    }

    @SneakyThrows
    @Override
    public boolean handleRequest(MessageContext messageContext) {
        var saaj = (SaajSoapMessage) messageContext.getRequest();
        CustomSignatureSoapMessageModifier.sign(saaj.getDocument(), signatureProperties);
        return super.handleRequest(messageContext);
    }
}
