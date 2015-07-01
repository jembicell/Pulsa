package com.jcell.persistense.db.postgresql;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction_type")
public class TransactionType implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	private String description;
	
	@OneToOne(mappedBy="transactionType")
	private CreditTransaction creditTransaction;
	
	@OneToOne(mappedBy="transactionType")
	private DebitTransaction debitTransaction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CreditTransaction getCreditTransaction() {
		return creditTransaction;
	}

	public void setCreditTransaction(CreditTransaction creditTransaction) {
		this.creditTransaction = creditTransaction;
	}

	public DebitTransaction getDebitTransaction() {
		return debitTransaction;
	}

	public void setDebitTransaction(DebitTransaction debitTransaction) {
		this.debitTransaction = debitTransaction;
	}
}
