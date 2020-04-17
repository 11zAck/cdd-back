package cl.roisel.cdd.app.transbank.models;

import java.io.Serializable;

/**
 * Respuesta de transbank.
 * @author isaac
 *
 */
public class InitTransbankResponse implements Serializable{

	private String token;
	private String urlForm;
	
	public InitTransbankResponse() {
	}
	
	public InitTransbankResponse( String token, String urlForm ) {
		this.token = token;
		this.urlForm = urlForm;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUrlForm() {
		return urlForm;
	}
	public void setUrlForm(String urlForm) {
		this.urlForm = urlForm;
	}
	
	private static final long serialVersionUID = -1327204689221117648L;
		
}
