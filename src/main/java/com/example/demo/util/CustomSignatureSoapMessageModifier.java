package com.example.demo.util;

import lombok.SneakyThrows;
import org.apache.wss4j.dom.message.WSSecHeader;
import org.apache.wss4j.dom.message.WSSecTimestamp;
import org.w3c.dom.Document;

import java.security.PrivateKey;
import java.security.cert.Certificate;

public final class CustomSignatureSoapMessageModifier {

    @SneakyThrows
    public static void sign(Document message, PrivateKey privateKey, Certificate certificate) {
        WSSecHeader secHeader = new WSSecHeader(message);
        secHeader.insertSecurityHeader();
        WSSecTimestamp timestamp = new WSSecTimestamp(secHeader);
        timestamp.setTimeToLive(300);
        timestamp.build();
    }
}
