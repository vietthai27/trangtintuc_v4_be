package com.thai27.trangtintuc_v4_be.Exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

}
