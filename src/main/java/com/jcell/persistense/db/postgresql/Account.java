package com.jcell.persistense.db.postgresql;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="account_id")
	private Long id;
	
	private String username;
	private String password;
	
	@Column(name="device_id")
	private String deviceId;
	
	@Column(name="token_number")
	private String tokenNumber;
	
	@Column(name="last_update")
	private Date lastUpdate;
	
	@Column(name="activation_status")
	private Boolean activationStatus;
	
	///////// relation
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
	private List<DebitTransaction> debitTransactions;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
	private List<CreditTransaction> creditTransactions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTokenNumber() {
		return tokenNumber;
	}

	public void setTokenNumber(String tokenNumber) {
		this.tokenNumber = tokenNumber;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Boolean getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(Boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public List<DebitTransaction> getDebitTransactions() {
		return debitTransactions;
	}

	public void setDebitTransactions(List<DebitTransaction> debitTransactions) {
		this.debitTransactions = debitTransactions;
	}

	public List<CreditTransaction> getCreditTransactions() {
		return creditTransactions;
	}

	public void setCreditTransactions(List<CreditTransaction> creditTransactions) {
		this.creditTransactions = creditTransactions;
	}
	
}
