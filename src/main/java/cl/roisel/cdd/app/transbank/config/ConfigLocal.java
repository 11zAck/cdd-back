package cl.roisel.cdd.app.transbank.config;

import cl.transbank.webpay.Webpay;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class ConfigLocal {

    @Bean
    public cl.transbank.webpay.configuration.Configuration configTransbankEnvironment(){
        return cl.transbank.webpay.configuration.Configuration.forTestingWebpayPlusNormal();
    }

}
