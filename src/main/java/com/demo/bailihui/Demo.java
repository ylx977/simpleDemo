package com.demo.bailihui;

import java.io.File;
import java.nio.file.Files;

import com.demo.hashUtils.HashUtil;

public class Demo {
	
	//	http://t.blhapi.li91.com/index/getToken    获取token信息
	

	public static void main(String[] args) {
		String format = String.format("app_id=%s:app_key=%s:tamptimes=%s", "100010011","76029ff3807c85e9bd6c42ccdee7e1da","2019-01-13 14:15:13");
		String upperCase = HashUtil.getStringMD5(format).toUpperCase();
		System.out.println(upperCase);
		
	}
	
}
