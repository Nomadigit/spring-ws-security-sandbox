package nomadigit.spring.ws.security.sandbox.configuration;

import nomadigit.spring.ws.security.sandbox.configuration.properties.SignatureProperties;
import nomadigit.spring.ws.security.sandbox.model.ObjectFactory;
import jakarta.annotation.PostConstruct;
import nomadigit.spring.ws.security.sandbox.util.CustomCryptoFactory;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.common.crypto.WSProviderConfig;
import org.apache.xml.security.Init;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

@Configuration
public class WebServiceTemplateConfiguration {

    private final SignatureProperties signatureProperties;

    public WebServiceTemplateConfiguration(SignatureProperties signatureProperties) {
        this.signatureProperties = signatureProperties;
    }

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
        var password = String.valueOf(signatureProperties.password());
        var interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementPassword(password);
        interceptor.setSecurementUsername(signatureProperties.alias());
        interceptor.setSecurementSignatureCrypto(CustomCryptoFactory.customCryptoFromPKCS12(signatureProperties.classPath(), password));
        interceptor.setSecurementMustUnderstand(true);
        interceptor.setAllowRSA15KeyTransportAlgorithm(true);
        interceptor.setSecurementSignatureAlgorithm("http://www.w3.org/2007/05/xmldsig-more#rsa-pss");
        interceptor.setSecurementSignatureDigestAlgorithm("http://www.w3.org/2001/04/xmlenc#sha256");
        interceptor.setSecurementActions(ConfigurationConstants.SIGNATURE);
        return interceptor;
    }
}
