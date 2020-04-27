package cl.roisel.cdd.app.transbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transbank.webpay.wswebpay.service.TransactionResultOutput;

import cl.roisel.cdd.app.transbank.models.InitTransbankResponse;
import cl.roisel.cdd.app.transbank.services.TransbankService;

@RestController
@RequestMapping("/tbk-service")
public class TransbankController {

	@Autowired private TransbankService service;
	
	@Value("${transbank.return.url}")
	private String returnUrl;
	
	@Value("${transbank.final.url}")
	private String finalUrl;
	
    @PostMapping
    public ResponseEntity<?> init(
    	@RequestParam("amount") Double amount,
    	@RequestParam("session-id") String sessionId,
    	@RequestParam("buy-order") String buyOrder
    ){
    	
    	InitTransbankResponse output = service.iniciarPago(amount, sessionId, buyOrder, this.returnUrl, this.finalUrl);
    	if( output != null ) {
    		return new ResponseEntity<InitTransbankResponse>(output, HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PostMapping("/result")
    public ResponseEntity<?> transactionResult( @RequestParam("token_ws") String token ){
    	TransactionResultOutput result = service.getResultado(token);
    	if ( result != null ) {
    		return new ResponseEntity<TransactionResultOutput>(result,HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PostMapping("/notification")
    public ResponseEntity<?> acknowledge( @RequestParam("token_ws") String token ) {
    	service.execAcknowledge(token);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
