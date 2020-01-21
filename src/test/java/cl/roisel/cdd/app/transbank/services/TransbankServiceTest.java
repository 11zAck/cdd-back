package cl.roisel.cdd.app.transbank.services;

import cl.transbank.webpay.configuration.Configuration;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class TransbankServiceTest {

    @Autowired private TransbankService service;

    @Autowired
    @Qualifier("configTransbankEnvironment")
    private Configuration configuration;

    @Ignore
    @Test
    void iniciarPago() {
        double amount = 1000.00;
        String sessionId = "1234567890";
        String buyOrder = "12340";
        String returnUrl = "http://localhost:8000/return";
        String finalUrl = "http://localhost:8000/final";

        final Map<String, Object> stringObjectMap = service.iniciarPago(amount, sessionId, buyOrder, returnUrl, finalUrl);
        assertNotNull( stringObjectMap.get("TOKEN") );

    }
}