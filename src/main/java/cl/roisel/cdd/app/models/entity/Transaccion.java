package cl.roisel.cdd.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dvt__tl_trx")
public class Transaccion implements Serializable {

	private static final long serialVersionUID = -6855384075378519816L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "acc_date")
	private String accountingDate;
	
	@Column(name = "buy_order")
	private String buyOrder;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "exp_date")
	private String expirationDate;

	@Column(name = "amount")
	private String amount;
	
	@Column(name = "commerce_code")
	private String commerceCode;
	
	@Column(name = "auth_code")
	private String authorizationCode;
	
	@Column(name = "pay_type_code")
	private String paymentTypeCode;
	
	@Column(name = "res_code")
	private String responseCode;
	
	@Column(name = "session_id")
	private String sessionId;
	
	@Column(name = "trx_date")
	private String transactionDate;
	
	@Column(name = "vci")
	private String vci;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}

	public String getBuyOrder() {
		return buyOrder;
	}

	public void setBuyOrder(String buyOrder) {
		this.buyOrder = buyOrder;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCommerceCode() {
		return commerceCode;
	}

	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getVci() {
		return vci;
	}

	public void setVci(String vci) {
		this.vci = vci;
	}

}
