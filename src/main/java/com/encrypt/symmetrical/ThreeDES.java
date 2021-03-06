package com.encrypt.symmetrical;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES是一种对称加密算法，在 DES 的基础上，使用三重数据加密算法，对数据进行加密，
 * 它相当于是对每个数据块应用三次 DES 加密算法。由于计算机运算能力的增强，
 * 原版 DES 密码的密钥长度变得容易被暴力破解；3DES 即是设计用来提供一种相对简单的方法，
 * 即通过增加 DES 的密钥长度来避免类似的攻击，而不是设计一种全新的块密码算法这样来说，破解的概率就小了很多。
 * 缺点由于使用了三重数据加密算法，可能会比较耗性能。简单的3DES加密算法实现：
 * @author fuzamei
 *
 */
public class ThreeDES {

	private static final String KEY_ALGORITHM = "DESede";
    private static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";//默认的加密算法

    /**
     * DESede 加密操作
     *
     * @param content 待加密内容
     * @param key 加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");
            
            System.out.println("源字节长度："+byteContent.length);

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密
            
            System.out.println("加密后字节长度："+result.length);

            return Base64.getEncoder().encodeToString(result);//通过Base64转码返回
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * DESede 解密操作
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
     * 对文件进行加密
     * @param srcFile
     * @param destFile
     * @param key
     */
    public static void encryptFile(File srcFile, File destFile, String key){
    	if(!srcFile.exists()){
    		throw new RuntimeException("源文件不存在");
    	}
    	if(destFile.exists()){
    		throw new RuntimeException("目标文件已经存在");
    	}
    	try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

            FileInputStream fileInputStream = new FileInputStream(srcFile);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(destFile), cipher);

            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = fileInputStream.read(buffer))!=-1){
            	cipherOutputStream.write(buffer, 0, len);
            }
            
            fileInputStream.close();
            cipherOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    /**
     * 对文件进行解密
     * @param srcFile
     * @param destFile
     * @param key
     */
    public static void decryptFile(File srcFile, File destFile, String key){
    	if(!srcFile.exists()){
    		throw new RuntimeException("源文件不存在");
    	}
    	if(destFile.exists()){
    		throw new RuntimeException("目标文件已经存在");
    	}
    	try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

            CipherInputStream cipherInputStream = new CipherInputStream(new FileInputStream(srcFile),cipher);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = cipherInputStream.read(buffer))!=-1){
            	fileOutputStream.write(buffer, 0, len);
            }
            
            cipherInputStream.close();
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    /**
//     * 生成加密秘钥
//     *
//     * @return
//     */
//    private static SecretKey getSecretKey(final String key) {
//        //返回生成指定算法密钥生成器的 KeyGenerator 对象
//        KeyGenerator kg = null;
//
//        try {
//            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
//
//            //DESede
//            kg.init(new SecureRandom(key.getBytes()));
//
//            //生成一个密钥
//            SecretKey secretKey = kg.generateKey();
//
//            return secretKey;
//        } catch (NoSuchAlgorithmException ex) {
//        	 ex.printStackTrace();
//        }
//
//        return null;
//    }
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
    		
    		//DESede
    		kg.init(new SecureRandom(key.getBytes()));
    		
    		//生成一个密钥
    		SecretKey secretKey = kg.generateKey();
    		
    		return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为DESede专用密钥
//    		return new SecretKeySpec(Arrays.copyOf(key.getBytes("utf-8"), 8), KEY_ALGORITHM);// 转换为DES专用密钥（这个是网站的做法，key就是输入的字符串）
    	} catch (NoSuchAlgorithmException ex) {
    		ex.printStackTrace();
    	}
    	
    	return null;
    }

    public static void main(String[] args) {
//        String content = "hello";
////        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
//        String key = "sdee";
//        System.out.println("未3DES加密的内容::" + content);
//        String s1 = ThreeDES.encrypt(content, key);
//        System.out.println("3DES加密后的内容(用了BASE64编码):" + s1);
//        System.out.println("3DES解密后的内容:"+ ThreeDES.decrypt(s1, key));

        
//    	encryptFile(new File("C:\\Users\\fuzamei\\Desktop\\Effective.Java.3rd.Edition.2018.1.pdf")
//		, new File("C:\\Users\\fuzamei\\Desktop\\Effective.Java.3rd.Edition.2018.1.pdf-en"), "keykeykeykeykeykey");

        decryptFile(new File("C:\\Users\\fuzamei\\Desktop\\Effective.Java.3rd.Edition.2018.1.pdf-en")
		, new File("C:\\Users\\fuzamei\\Desktop\\Effective.Java.3rd.Edition.2018.1-2.pdf"), "keykeykeykeykeykey");
        
    }
	
}
