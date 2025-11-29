package com.example.demo.configuration;

import com.example.demo.model.ObjectFactory;
import jakarta.annotation.PostConstruct;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.crypto.WSProviderConfig;
import org.apache.xml.security.Init;
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

    @PostConstruct
    public void init() {
        WSProviderConfig.setAddJceProviders(false);
        WSProviderConfig.init(false, true, false);
        Init.init();
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(
            Wss4jSecurityInterceptor wss4jSecurityInterceptor,
            LoggerInterceptor loggerInterceptor
    ) {
        var webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri("http://localhost:8080/user");
        var jaxbMarshaller = new Jaxb2Marshaller();
        jaxbMarshaller.setPackagesToScan(ObjectFactory.class.getPackage().getName());
        webServiceTemplate.setMarshaller(jaxbMarshaller);
        webServiceTemplate.setUnmarshaller(jaxbMarshaller);
        ClientInterceptor[] interceptors = new ClientInterceptor[]{wss4jSecurityInterceptor, loggerInterceptor};
        webServiceTemplate.setInterceptors(interceptors);
        return webServiceTemplate;
    }

    @Bean
    public Wss4jSecurityInterceptor wss4jSecurityInterceptor() throws Exception {
        var interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementEncryptionCrypto(customCrypto());
        interceptor.setSecurementPassword("changeit");
        interceptor.setSecurementUsername("myserver");
        interceptor.setSecurementSignatureCrypto(customCrypto());
        interceptor.setSecurementMustUnderstand(true);
        interceptor.setAllowRSA15KeyTransportAlgorithm(true);
        interceptor.setSecurementSignatureAlgorithm("http://www.w3.org/2001/04/xmlenc#sha256");
        interceptor.setSecurementSignatureDigestAlgorithm("http://www.w3.org/2001/04/xmlenc#sha256");
        interceptor.setSecurementActions(ConfigurationConstants.SIGNATURE);
        return interceptor;
    }

    public Crypto customCrypto() throws Exception {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource("keystore.p12"));
        cryptoFactoryBean.setKeyStorePassword("changeit");
        cryptoFactoryBean.setKeyStoreType("PKCS12");
        cryptoFactoryBean.afterPropertiesSet();
        return cryptoFactoryBean.getObject();
    }
}
