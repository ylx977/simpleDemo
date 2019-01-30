package com.demo.smCipher.utils;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import com.demo.smCipher.BCECUtil;
import com.demo.smCipher.SM2Util;
import com.encrypt.asymmetrical.rsa.Base64Utils;

/**
 * 自己手写整合的SM2工具类
 * @author fuzamei
 * 
 * 需要依赖以下两个jar包
	<dependency>
        <groupId>org.bouncycastle</groupId>
        <artifactId>bcprov-jdk15on</artifactId>
        <version>1.60</version>
    </dependency>
    <dependency>
        <groupId>org.bouncycastle</groupId>
        <artifactId>bcpkix-jdk15on</artifactId>
        <version>1.60</version>
    </dependency>
 */
public final class SM2Utils {
	
	private SM2Utils(){
		throw new AssertionError("instantiation is not permitted");
	}
	
	/**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "SM2PublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "SM2PrivateKey";
	
    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
	public static Map<String, Object> genKeyPair() throws Exception {
		AsymmetricCipherKeyPair keyPair = SM2Util.generateKeyPairParameter();
        ECPrivateKeyParameters priKey = (ECPrivateKeyParameters) keyPair.getPrivate();
        ECPublicKeyParameters pubKey = (ECPublicKeyParameters) keyPair.getPublic();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, pubKey);
        keyMap.put(PRIVATE_KEY, priKey);
        return keyMap;
	}
    
    /**
     * <p>
     * 获取私钥16进制格式
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getHexPrivateKey(Map<String, Object> keyMap)
            throws Exception {
    	ECPrivateKeyParameters priKey = (ECPrivateKeyParameters) keyMap.get(PRIVATE_KEY);
    	//这里有个大坑，就是不要用new BigInteger()去转换，容易导致验签失败，因为有的私钥的hex是66的长度，前面有两个00，需要保留这样的私钥
    	//而用biginteger转换会丢失00
        return ByteUtils.toHexString(priKey.getD().toByteArray());
    }
	
    /**
     * <p>
     * 获取公钥16进制格式
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getHexPublicKey(Map<String, Object> keyMap)
            throws Exception {
    	ECPublicKeyParameters pubKey = (ECPublicKeyParameters) keyMap.get(PUBLIC_KEY);
    	//公钥x轴对应的16进制字符串
    	String pubXHex = ByteUtils.toHexString(pubKey.getQ().getAffineXCoord().getEncoded());
    	//公钥y轴对应的16进制字符串
    	String pubYHex = ByteUtils.toHexString(pubKey.getQ().getAffineYCoord().getEncoded());
        return pubXHex + pubYHex;
    }
    
    /**
     * 由16进制字符串的公钥获取公钥
     * @param pubKey(16进制 由xHex + yHex拼接而成)
     * @return
     */
    private static ECPublicKeyParameters getPublicKeyFromString(String pubKey){
    	String xHex = pubKey.substring(0,64);
    	String yHex = pubKey.substring(64);
    	ECPublicKeyParameters publicKey = BCECUtil.createECPublicKeyParameters(xHex, yHex, SM2Util.CURVE, SM2Util.DOMAIN_PARAMS);
    	return publicKey;
    }
    
    /**
     * 由16进制字符串的私钥获取私钥
     * @param priKey(16进制)
     * @return
     */
    private static ECPrivateKeyParameters getPrivateKeyFromString(String priKey){
    	ECPrivateKeyParameters privateKey = new ECPrivateKeyParameters(new BigInteger(ByteUtils.fromHexString(priKey)), SM2Util.DOMAIN_PARAMS);
    	return privateKey;
    }
    
    /**
     * <p>
     * 用私钥对信息生成数字签名（Hex格式）
     * </p>
     *
     * @param data 原始数据
     * @param privateKey 私钥(Hex编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
    	byte[] sign = SM2Util.sign(getPrivateKeyFromString(privateKey), data);
    	return ByteUtils.toHexString(sign);
    }
    
    /**
     * 公钥验签，检查是否验签成功
     * @param data
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
    	boolean flag = SM2Util.verify(getPublicKeyFromString(publicKey), data, ByteUtils.fromHexString(sign));
    	return flag;
    }
    
    /**
     * 通过公钥对原始数据进行加密，返回的加密结果用Hex字符串表示
     * @param srcData
     * @param publicKey
     * @return
     * @throws Exception 
     */
    public static String encryptByPublicKey(byte[] srcData, String publicKey) throws Exception{
    	ECPublicKeyParameters publicKeyFromString = getPublicKeyFromString(publicKey);
    	//公钥加密
        byte[] encryptedData = SM2Util.encrypt(publicKeyFromString, srcData);
        return ByteUtils.toHexString(encryptedData);
    }
    
    
    /**
     * 用私钥对加密数据进行解密，并返回字节数组
     * @param encryptedData
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String encryptedData, String privateKey) throws Exception{
    	ECPrivateKeyParameters privateKeyFromString = getPrivateKeyFromString(privateKey);
    	byte[] decrypt = SM2Util.decrypt(privateKeyFromString, ByteUtils.fromHexString(encryptedData));
    	return decrypt;
    }
    
    
    
    public static void main(String[] args) throws Exception {
//		Map<String, Object> genKeyPair = genKeyPair();
//		String hexPrivateKey = getHexPrivateKey(genKeyPair);
//		System.out.println(hexPrivateKey);
//		String hexPublicKey = getHexPublicKey(genKeyPair);
//		System.out.println(hexPublicKey);
    	
    	String privateKey = "00dadf762de86d0b4c5a026d06896ad2f476e8fbd836bc42be2dd49a1730fb227f";
    	String publicKey = "53e13ba2d55fb14c6d5c0770646b64b1733c3a3c29d59f8d3b1c391928341851f0c6fe4572e1c00a87f4eca5c7fb472d0584666b859e776cc4eb247154e785de";
//		
		String sign = sign("hello i am SM2!".getBytes(), privateKey);
		System.out.println("签名数据：" + sign);
		
		boolean verify = verify("hello i am SM2!".getBytes(), publicKey, sign);
		System.out.println("验签是否成功：" + verify);
    	
    	
    	String plainText = "大家好，我叫杨凌霄，hello SM2!";
    	String encryptByPublicKey = encryptByPublicKey(plainText.getBytes("UTF-8"), publicKey);
    	System.out.println("公钥加密后的内容:"+encryptByPublicKey);
    	
    	byte[] decryptByPrivateKey = decryptByPrivateKey(encryptByPublicKey, privateKey);
    	System.out.println("私钥解密后的内容:"+new String(decryptByPrivateKey,"utf-8"));
    	
	}
}
