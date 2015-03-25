package com.enumurations;

public enum RoleEnum {
	
	ADMIN("ADMIN"),
	ROLE_SPECIAL("ROLE_SPECIAL"),
	USER_SIMPLE("USER_SIMPLE");

	private String value;
	
	private RoleEnum(String value) {
		this.value = value;
	}

	public String getValue(){
		return this.value;
	}
}
