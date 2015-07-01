package com.jcell.persistense.api.response;

import org.springframework.http.HttpStatus;

public class BasicResponse {

	private HttpStatus statusDescription;
	private Integer status;
	private String caused;
	public HttpStatus getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(HttpStatus statusDescription) {
		this.statusDescription = statusDescription;
		this.status=statusDescription.value();
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCaused() {
		return caused;
	}
	public void setCaused(String caused) {
		this.caused = caused;
	}
	
	
}
