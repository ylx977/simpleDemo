package com.encrypt;

public class Test {
	
	public static void main(String[] args) {
		String encrypt = SymmetricEncryptUtils.encrypt("hello", "keykeykeykeykeykey", SymmetricEncryptUtils.Mode.AES_ECB_PKCS5Padding);
		System.out.println(encrypt);
		String decrypt = SymmetricEncryptUtils.decrypt("D9AJJJBsHellbrChbi47mQ==", "keykeykeykeykeykey", SymmetricEncryptUtils.Mode.AES_ECB_PKCS5Padding);
		System.out.println(decrypt);
	}
	
}
