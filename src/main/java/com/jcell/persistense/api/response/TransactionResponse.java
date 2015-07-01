package com.jcell.persistense.api.response;

import java.util.Date;

public class TransactionResponse extends BasicResponse{

	private Double amount;
	private Date dateTransaction;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getDateTransaction() {
		return dateTransaction;
	}
	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}
	
}
