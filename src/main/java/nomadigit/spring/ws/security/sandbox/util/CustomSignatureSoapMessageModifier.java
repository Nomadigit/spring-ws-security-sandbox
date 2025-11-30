package nomadigit.spring.ws.security.sandbox.util;

import lombok.SneakyThrows;
import nomadigit.spring.ws.security.sandbox.configuration.properties.SignatureProperties;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.message.WSSecHeader;
import org.apache.wss4j.dom.message.WSSecSignature;
import org.w3c.dom.Document;

public final class CustomSignatureSoapMessageModifier {

    @SneakyThrows
    public static void sign(
            Document message,
            SignatureProperties signatureProperties
    ) {
        var secHeader = new WSSecHeader(message);
        secHeader.insertSecurityHeader();
        var password = String.valueOf(signatureProperties.password());
        var crypto = CustomCryptoFactory.customCryptoFromPKCS12(signatureProperties.classPath(), password);
        var signature = new WSSecSignature(secHeader);
        signature.setUserInfo(signatureProperties.alias(), password);
        signature.setKeyIdentifierType(WSConstants.BST_DIRECT_REFERENCE);
        signature.setSignatureAlgorithm(WSConstants.RSA_SHA256);
        signature.build(crypto);
    }
}
