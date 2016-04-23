package com.kmutt.stcp.dto;

public class MessageResult {

	// Field//
	public String StatusCode;
	public Boolean IsError;
	public String ErrorDescription;

	public MessageResult(){
		
		this.StatusCode = "";
		this.IsError = false;
		this.ErrorDescription = "";
		
	}
	
}
