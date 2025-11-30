package nomadigit.spring.ws.security.sandbox.util;

import org.apache.wss4j.common.crypto.Crypto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

public final class CustomCryptoFactory {
    public static Crypto customCryptoFromPKCS12(ClassPathResource keystoreClassPath, String password) throws Exception {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStoreLocation(keystoreClassPath);
        cryptoFactoryBean.setKeyStorePassword(password);
        cryptoFactoryBean.setKeyStoreType("PKCS12");
        cryptoFactoryBean.afterPropertiesSet();
        return cryptoFactoryBean.getObject();
    }
}
