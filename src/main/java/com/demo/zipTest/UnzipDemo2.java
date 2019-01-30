package com.demo.zipTest;

import java.io.File;

public class UnzipDemo2 {
	public static void main(String[] args) {
		File file = new File("C:\\Users\\fuzamei\\Desktop\\hoho\\zip");
//		String parent = file.getParent();
		File parentFile = file.getParentFile();
		if(!parentFile.exists()){
			parentFile.mkdirs();
		}
		System.out.println(parentFile.exists());
	}
}
