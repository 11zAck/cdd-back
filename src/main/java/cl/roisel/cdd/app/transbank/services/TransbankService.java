package cl.roisel.cdd.app.transbank.services;

import cl.transbank.webpay.Webpay;
import cl.transbank.webpay.WebpayNormal;
import cl.transbank.webpay.configuration.Configuration;
import com.transbank.webpay.wswebpay.service.WsInitTransactionOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransbankService {

    private static final Logger log = LoggerFactory.getLogger( TransbankService.class );
    private static final String DIVISOR = "-----------------------------------------------------------------------";

    @Autowired
    @Qualifier("configTransbankEnvironment")
    private Configuration configuration;

    public WsInitTransactionOutput iniciarPago(double amount, String sessionId, String buyOrder, String returnUrl, String finalUrl ) {

        log.info(DIVISOR);
        log.info("Iniciando pago en transbank -->");
        log.info("\tmonto.......: {}", amount);
        log.info("\tsessionId...: {}", sessionId);
        log.info("\tbuyOrder....: {}", buyOrder);
        log.info("\treturnUrl...: {}", returnUrl);
        log.info("\tfinalUrl....: {}", finalUrl);
        log.info(DIVISOR);

        WsInitTransactionOutput wsOutput = null;
        WebpayNormal webpay = null;
        try {
            webpay = new Webpay(configuration).getNormalTransaction();
        } catch (Exception e) {
            log.error("[ ! ] : Error en el servicio de transbank. {}", e);
            log.error("<-- Servicio con errores.");
        }

        if( null != webpay ){
            log.info("WEBPAY :: Environment: {}", configuration.getEnvironment().getInternalName());
            wsOutput = webpay.initTransaction(amount, sessionId, buyOrder, returnUrl, finalUrl);
            log.info("\tTOKEN: {}", wsOutput.getToken());
            log.info("\tURL..: {}", wsOutput.getUrl());
            log.info("<-- Esperando interacción con tarjetahabiente. ");
        }else{
            log.error("WEBPAY [null].");
            log.error("<-- Servicio con errores.");
        }

        return wsOutput;
    }
}
