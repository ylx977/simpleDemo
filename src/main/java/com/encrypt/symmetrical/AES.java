package com.encrypt.symmetrical;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.Arrays;

/**
 * AES是一种对称加密算法，在 DES 的基础上，使用三重数据加密算法，对数据进行加密，
 * 它相当于是对每个数据块应用三次 DES 加密算法。由于计算机运算能力的增强，
 * 原版 DES 密码的密钥长度变得容易被暴力破解；3DES 即是设计用来提供一种相对简单的方法，
 * 即通过增加 DES 的密钥长度来避免类似的攻击，而不是设计一种全新的块密码算法这样来说，破解的概率就小了很多。
 * 缺点由于使用了三重数据加密算法，可能会比较耗性能。简单的AES加密算法实现：
 * @author fuzamei
 *
 */
public class AES {

	private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key 加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.getEncoder().encodeToString(result);//通过Base64转码返回
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

            //执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(key.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

//            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
            return new SecretKeySpec(Arrays.copyOf(key.getBytes("utf-8"), 16), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return null;
    }

    public static void main(String[] args) {
        String content = "hello,您好";
//        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        String key = "ssdhjasdhas";
        System.out.println("content:" + content);
        String s1 = AES.encrypt(content, key);
        System.out.println("s1:" + s1);
        System.out.println("s2:"+AES.decrypt(s1, key));

    }
	
}
