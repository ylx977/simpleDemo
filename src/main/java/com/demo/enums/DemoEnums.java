package com.demo.enums;

import java.io.Serializable;

public enum DemoEnums {
	RED,BLUE,DARK;
	
	private Integer code;
	private String message;
	private DemoEnums(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	private DemoEnums() {
	}
}
