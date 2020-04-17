package cl.roisel.cdd.app.transbank.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.transbank.webpay.wswebpay.service.WsInitTransactionOutput;

import cl.roisel.cdd.app.transbank.models.InitTransbankResponse;
import cl.transbank.webpay.configuration.Configuration;

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

        InitTransbankResponse out = service.iniciarPago(amount, sessionId, buyOrder, returnUrl, finalUrl);
        assertNotNull( out );
    }
}