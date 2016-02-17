package com.almin.mvpdemo.model;

public abstract class MemberProfile {
	private String id;
	private String legacyUserId;
	private String emailAddress;
	private String tokenValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLegacyUserId() {
		return legacyUserId;
	}
	
	public void setLegacyUserId(String legacyUserId) {
		this.legacyUserId = legacyUserId;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getTokenValue() {
		return tokenValue;
	}
	
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	
	public abstract String getTokenName();
}
