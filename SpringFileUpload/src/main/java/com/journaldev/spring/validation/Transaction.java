package com.journaldev.spring.validation;

public class Transaction {
	private String User_id;
	private String transaction_id;
	private  String tx_date;
	private  String payer_name;
	private  String payer_acc;
	private  String payee_name;
	private  String payee_acc;
	private  String amount;
			
    public String getUser_id() {
		return User_id;
	}
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTx_date() {
		return tx_date;
	}
	public void setTx_date(String tx_date) {
		this.tx_date = tx_date;
	}
	public String getPayer_name() {
		return payer_name;
	}
	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}
	public String getPayer_acc() {
		return payer_acc;
	}
	public void setPayer_acc(String payer_acc) {
		this.payer_acc = payer_acc;
	}
	public String getPayee_name() {
		return payee_name;
	}
	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}
	public String getPayee_acc() {
		return payee_acc;
	}
	public void setPayee_acc(String payee_acc) {
		this.payee_acc = payee_acc;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	

}
