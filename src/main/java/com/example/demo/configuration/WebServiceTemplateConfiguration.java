package com.example.demo.configuration;

import com.example.demo.model.ObjectFactory;
import jakarta.annotation.PostConstruct;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.crypto.WSProviderConfig;
import org.apache.xml.security.Init;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

@Configuration
public class WebServiceTemplateConfiguration {

    @Value("${application.keystore.classpath}")
    private ClassPathResource keystoreClassPath;
    @Value("${application.keystore.password}")
    private char[] password;
    @Value("${application.keystore.alias}")
    private String alias;

    @Qualifier
    @interface CustomInterceptor {
    }

    @PostConstruct
    public void init() {
        WSProviderConfig.setAddJceProviders(false);
        WSProviderConfig.init(false, true, false);
        Init.init();
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(@CustomInterceptor ClientInterceptor[] interceptors) {
        var webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri("http://localhost:8080/user");
        var jaxbMarshaller = new Jaxb2Marshaller();
        jaxbMarshaller.setPackagesToScan(ObjectFactory.class.getPackage().getName());
        webServiceTemplate.setMarshaller(jaxbMarshaller);
        webServiceTemplate.setUnmarshaller(jaxbMarshaller);
        webServiceTemplate.setInterceptors(interceptors);
        return webServiceTemplate;
    }

    @ConditionalOnMissingBean(CustomSignatureInterceptor.class)
    @Bean
    @CustomInterceptor
    public Wss4jSecurityInterceptor wss4jSecurityInterceptor() throws Exception {
        var interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementPassword(String.valueOf(password));
        interceptor.setSecurementUsername(alias);
        interceptor.setSecurementSignatureCrypto(customCrypto());
        interceptor.setSecurementMustUnderstand(true);
        interceptor.setAllowRSA15KeyTransportAlgorithm(true);
        interceptor.setSecurementSignatureAlgorithm("http://www.w3.org/2007/05/xmldsig-more#rsa-pss");
        interceptor.setSecurementSignatureDigestAlgorithm("http://www.w3.org/2001/04/xmlenc#sha256");
        interceptor.setSecurementActions(ConfigurationConstants.SIGNATURE);
        return interceptor;
    }

    public Crypto customCrypto() throws Exception {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStoreLocation(keystoreClassPath);
        cryptoFactoryBean.setKeyStorePassword(String.valueOf(password));
        cryptoFactoryBean.setKeyStoreType("PKCS12");
        cryptoFactoryBean.afterPropertiesSet();
        return cryptoFactoryBean.getObject();
    }
}
