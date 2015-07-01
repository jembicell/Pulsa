package com.jcell.persistense.api.retrieve;

public class ChangePassword extends Login{

	private String oldPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
