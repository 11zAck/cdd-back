package cl.roisel.cdd.app.transbank.services;

import cl.transbank.webpay.Webpay;
import cl.transbank.webpay.WebpayNormal;
import cl.transbank.webpay.configuration.Configuration;

import com.transbank.webpay.wswebpay.service.TransactionResultOutput;
import com.transbank.webpay.wswebpay.service.WsInitTransactionOutput;
import com.transbank.webpay.wswebpay.service.WsTransactionDetailOutput;

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
    
    private static Map<Integer,String> responseCodes;
    static {
    	responseCodes = new HashMap<>();
    	responseCodes.put( 0, "TRANSACCIÓN APROBADA");
    	responseCodes.put(-1, "RECHAZO DE TRANSACCIÓN");
    	responseCodes.put(-2, "REINTENTE TRANSACCIÓN");
    	responseCodes.put(-3, "ERROR EN TRANSACCIÓN");
    	responseCodes.put(-4, "RECHAZO DE TRANSACCIÓN");
    	responseCodes.put(-5, "RECHAZO POR ERROR DE TASA");
    	responseCodes.put(-6, "EXCEDE CUPO MÁXIMO");
    	responseCodes.put(-7, "EXCEDE LÍMITE DIARIO POR TRANSACCIÓN");
    	responseCodes.put(-8, "RUBRO NO AUTORIZADO");
    }

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
 
        if( null != webpay ) {
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
    
    public TransactionResultOutput getResultado(String token) {
    	
    	log.info(DIVISOR);
        log.info("Ejecutando método resultado de pago en transbank -->");
        log.info("\ttoken.......: {}", token);
        log.info(DIVISOR);
        
        TransactionResultOutput outResult = null;
        WebpayNormal webpay = null;
        
        try {
        	webpay = new Webpay(configuration).getNormalTransaction();
        } catch ( Exception e ) {
        	log.error("[ ! ] : Error en el servicio de transbank. {}", e);
            log.error("<-- Servicio con errores.");            
        }
        
        if( null != webpay ) {
        	outResult = webpay.getTransactionResult(token);
        }else{
            log.error("WEBPAY [null].");
            log.error("<-- Servicio con errores.");
        }
        
        if( null != outResult && null != outResult.getDetailOutput() && outResult.getDetailOutput().size() > 0 ) {
        	WsTransactionDetailOutput detail = outResult.getDetailOutput().get(0);
        	int responseCode = detail.getResponseCode();
        	TransbankService.responseCodes.forEach( (code,text) -> {
        		if( code == responseCode ) {
        			log.info("\tCódigo..: {}", code);
        			log.info("\tMensaje.: {}", text);
        		}
        	});
        	
        } else {
        	log.error("WEBPAY outResult: [NULL]");
        }

        return outResult;
        
    }
    
    public void execAcknowledge(String token) {
    	log.info(DIVISOR);
        log.info("Ejecutando método acknowledge en transbank -->");
        log.info("\ttoken.......: {}", token);
        log.info(DIVISOR);

        WebpayNormal webpay = null;
        
        try {
        	webpay = new Webpay(this.configuration).getNormalTransaction();
        } catch ( Exception e ) {
        	log.error("[ ! ] : Error en el servicio de transbank. {}", e);
            log.error("<-- Servicio con errores.");      
        }
        
        if( null != webpay ) {
        	try {
				webpay.acknowledgeTransaction(token);
			} catch (Exception e) {
				log.error("[ ! ] : Error en el servicio de transbank. {}", e);
			}
        }else{
            log.error("WEBPAY [null].");
            log.error("<-- Servicio con errores.");
        }
        
        return;
        
    }
    
}
