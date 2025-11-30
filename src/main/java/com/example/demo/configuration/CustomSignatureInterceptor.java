package com.example.demo.configuration;

import com.example.demo.util.CustomSignatureSoapMessageModifier;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

@ConditionalOnBooleanProperty("application.ws.custom-security-interceptor")
@WebServiceTemplateConfiguration.CustomInterceptor
@Component
public class CustomSignatureInterceptor extends ClientInterceptorAdapter {
    private final PrivateKey privateKey;
    private final Certificate certificate;

    @SneakyThrows
    public CustomSignatureInterceptor(
            @Value("${application.keystore.classpath}") ClassPathResource keystoreClassPath,
            @Value("${application.keystore.password}") char[] password,
            @Value("${application.keystore.alias}") String alias
    ) {
        var keystore = KeyStore.getInstance(keystoreClassPath.getFile(), password);
        certificate = keystore.getCertificate(alias);
        privateKey = (PrivateKey) keystore.getKey(alias, password);
    }

    @SneakyThrows
    @Override
    public boolean handleRequest(MessageContext messageContext) {
        var saaj = (SaajSoapMessage) messageContext.getRequest();
        CustomSignatureSoapMessageModifier.sign(saaj.getDocument(), privateKey, certificate);
        return super.handleRequest(messageContext);
    }
}
