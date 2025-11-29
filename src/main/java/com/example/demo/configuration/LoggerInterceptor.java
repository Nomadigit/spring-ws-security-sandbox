package com.example.demo.configuration;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class LoggerInterceptor extends ClientInterceptorAdapter {

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        var forLog = new ByteArrayOutputStream();
        try {
            messageContext.getRequest().writeTo(forLog);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info(forLog);
        return super.handleRequest(messageContext);
    }
}