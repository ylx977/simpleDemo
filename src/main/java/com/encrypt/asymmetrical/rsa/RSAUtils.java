package com.encrypt.asymmetrical.rsa;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 *
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class RSAUtils {

	/**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
//    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
//    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    public static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
//    private static final int MAX_ENCRYPT_BLOCK = 117;//当初始化大小是1024的时候用这个大小(RSA)
    private static final int MAX_ENCRYPT_BLOCK = 245;//当初始化大小是2048的时候用这个大小(RSA2)

    /**
     * RSA最大解密密文大小
     */
//    private static final int MAX_DECRYPT_BLOCK = 128;//当初始化大小是1024的时候用这个大小(RSA)
    private static final int MAX_DECRYPT_BLOCK = 256;//当初始化大小是2048的时候用这个大小(RSA2)

    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(2048);//初始化大小至少要512，默认是1024
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64Utils.encode(signature.sign());
    }

    /**
     * <p>
     * 校验数字签名(公钥验签)
     * </p>
     *
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64Utils.decode(sign));
    }

    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
//        return cipher.doFinal(encryptedData);
        
        int inputLen = encryptedData.length;
        System.out.println(inputLen);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
//        return cipher.doFinal(encryptedData);//如果明文不长直接用这个即可
        
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥加密(注：公钥加密的密文每次加密后的内容都是不一样的，但是私钥解密的内容都是一样的)
     * </p>
     *
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
//        return cipher.doFinal(data);
        
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <p>
     * 私钥加密(私钥加密的内容每次都是一样的)
     * </p>
     *
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
//        return cipher.doFinal(data);
        
        
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64Utils.encode(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64Utils.encode(key.getEncoded());
    }
    
    
    /**
     * 根据私钥(字符串)获取公钥(字符串)
     * 都是base64编码
     * @return
     */
    public static String getPublicKeyFromPrivateKey(String privateK) throws Exception {
    	byte[] keyBytes = Base64Utils.decode(privateK);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        RSAPrivateCrtKey rsaPrivateKey = (RSAPrivateCrtKey)privateKey;
        
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPublicExponent());
        PublicKey myPublicKey = keyFactory.generatePublic(publicKeySpec);
        return Base64Utils.encode(myPublicKey.getEncoded());
    }
    
    public static void main(String[] args) throws Exception {
		Map<String, Object> keyPair = genKeyPair();
//		String publicKey = getPublicKey(keyPair);
//		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrT+honH1yXKOLJsK+8p3mSg2ZeHAOvuxAQOKzzhXVNjPh8LhBlha8EJ2S1+ugKm++Ev7RE2VK8rNytWUsywrN2mBwaB406vhOt1rn+A31eF8qdNpsfgz8y9fgp8YZz6yffhY5x5+4boLu32TfaVBks+8HXR7mRSX7iUQOoY1AfQIDAQAB";
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmcHwRjreWAC1waDewgnOQI7XeMMyG4QLYQTI2TmY7uFVEXJSEviBhzMeT7s3fpud6be27vVcGT1DPlvCjPulvSuolFklu/YyGRu/G8/y8Slx1sLnG2rubyVk7QLvF7BXeLYaZbn6raXRy6FwcWVD6IecAelzu7KMq5ojt9Nnf1tP1zkMRvyRmw2/bdw/45MzfM3cQWG0uRY/xGpDWV4rmvFcE+nlyTsKbX9P5S2ZC1NgqLXtVMw3rVGZgPM7vun5SUnlbSiwEoAI5V4g+kfirMzUA4XV6zOZQwWCZ6o+0TsQgfSUA6Kp86AXc44l/VmFP1+C3oxoyt09Tocv+aJz2wIDAQAB";
//		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyJ3F6nwCHdRmBJ3W+ar4ZyDCjq9Qvt+k65KKWyJ07bFi3ZafHKWjzGqAFYrei8tqa8iS++V+NapuM7RsVU3SX++qPhor1INrIbvAzsqF0C9ZPDuICeM5WgI9be23z/EYNXP0nggezbX13ObHo9fZamwaoQZ2EWKzZIaRxzcKEPVBm1ncRsjNWXgEYc6vhW2bXhxxBa+ixtHKvCwCgmt1sg2b3AOjpyd8mVjrLSRV/JJUsQUOBC4GrdthcrwKDQu+zG9ZWkYhouJJMALGbATRHen/1Gh6U+sijCS85HodCVayLH1V9aSLcd+NvPT20j5OFD1tVbqtnzRPy0xCoiZoVwIDAQAB";
//		String privateKey = getPrivateKey(keyPair);
//		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKtP6GicfXJco4smwr7yneZKDZl4cA6+7EBA4rPOFdU2M+HwuEGWFrwQnZLX66Aqb74S/tETZUrys3K1ZSzLCs3aYHBoHjTq+E63Wuf4DfV4Xyp02mx+DPzL1+CnxhnPrJ9+FjnHn7hugu7fZN9pUGSz7wddHuZFJfuJRA6hjUB9AgMBAAECgYAlN8EIKSdf1xx4uL0Keu8u/tuhgfbmXTg6ZV5bLeS0Y4g2B/Qf25mo1ftF0b8RFucb5kiR56LT+5Jc02fdqWb5IhFw5xDmYr6PoZg7imhfIjeL0thwFcETfA/PXDD11vauXUJcBZdlmX2+OUqNjr18thXgAbSYgW3I4KAWa7uxgQJBAPGaDmtBqWhsWaFh0eTVkuwvQmiPky18lBr8ygtZ+7kF/c0LyObMMuJaT1F+atv06t1Omb3IphUr3iwFbKS1R/ECQQC1hX5He3ZoYv33FfEYuUYzUUbU8fBTYlJgB7mndNG/HVkW1LGJDt7SCn5qnACqW7zNxRsZ/Gsox58HbS6qtW1NAkAKOP+FMEkPvw3n0zDDYvoNfk5sCC8tDIljvvB5nHwXLxa3F1mP0nIQu8uBYjz5Q5qwdIPy0MueN7X13ODk8zqxAkEAjIdxsQN7MutYdF7PTXW6TGZi8N7xUIyw+1lfuK2u6EdfPlahr1sM1A19uylVNo5ZB/DNv3t0tph8nLJBS7WzjQJBAIh5BuyWTdCwqpnkrHvz9VDYX8wd6H8mjW9Gj3P9373x+JgHt8pO0dDe9hIJ3NOK8dTzsB1QLdP6ol0M526MgBA=";
		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZwfBGOt5YALXBoN7CCc5Ajtd4wzIbhAthBMjZOZju4VURclIS+IGHMx5Puzd+m53pt7bu9VwZPUM+W8KM+6W9K6iUWSW79jIZG78bz/LxKXHWwucbau5vJWTtAu8XsFd4thplufqtpdHLoXBxZUPoh5wB6XO7soyrmiO302d/W0/XOQxG/JGbDb9t3D/jkzN8zdxBYbS5Fj/EakNZXiua8VwT6eXJOwptf0/lLZkLU2Cote1UzDetUZmA8zu+6flJSeVtKLASgAjlXiD6R+KszNQDhdXrM5lDBYJnqj7ROxCB9JQDoqnzoBdzjiX9WYU/X4LejGjK3T1Ohy/5onPbAgMBAAECggEARYb4IpnOHxc7GTKoaC/M705oSvhhOSYNizKBrBCqgiKst/Xs/R0YXU2VQFKVNpAh6SSk+QtEOTmFpbYSlHPar18FnQdu077xSn9MfMQcxEaMbqO1o6VH6kSLjc1M1nZsJ26ZIzTlEQ6xfpYi5XP4MDbaKdcp+U804wgRbLeQznqFFCyo8KIWMNAElYZuWY5qfKFVwFz7Y0AXs8yI+zVE7vU/P4gcHe9MJl2ZkKXvUY/+CKwHh9A/BSgvqgrGQO2tiw7paQA0uW4Ba5NJLu+paX6NG8U55Gpz88Xjw77L1k3YKbUUopukHIu/6ICKcIQxHoGJhoKUIfpdlDnfhbWWWQKBgQDUrF3s6DtXdQAk/ZSgQSVyW2XnN2u2JQG/Ni999rqWCYqtenXtuWp4KJaJNaaO/J8qciXTX2dEyqvczHKaytbuoRqBoysZYXYclAZ/0Sxh26pYDF2INdaUJYcFQOFrDE5naGm3GVBrykM5dGL1vGvG1bJ5NXcI60jsWMdSuPcETQKBgQC5FOqlEsMjRCxuMBZitS0Yaa92fQtgcPXwlp/DzoN7jAswyorJP+1fBS+K8uCGbefLm+8zPp/P//bnyHHfKU4UzP+Jl3EYc99R1OQTRbpX/YcmvplFcc7NYMnzGF7oBocIslHWcqRaguSHpRDijOgqoQrpTO2C3xmVz9KlelSMxwKBgQDJc6jY4zZq5pJHd8jUcEFHbNo/RdkKxU09Udd0y0T9IHazUx/oGk2HGnNV73MhWJxweYXpDiLlH9HiKeuZQvCLRCG2kNgfh5l5AqKRapNO8uQN2VCz2MO0u0OFr2qXmyWdgGM7JIhwQjyhbOTcfyncQ8p2VDW4Rh9iecn9SuX5tQKBgGj7elWEHPgGFYWy9R+sURYF7KczKp7btEVxytHQdDhCKZ7VZH/fKQ+660rPlZfrA440tyvEG/Zp5G14/05sKYQW++PZ6SbLoyLRtUVRSe/bOSw69Mm1Um6gtiyT6FGIUlQWgeE4Sg2lLWfbs4YuEwsLKhtHrXmMfwDBT2TZzNfFAoGAH4iKhBP2MFrVFXDENAlETkjFEaIOtDdc3LqZ9k7OQ/X133DG++rR0dmfRaX61yVlPF4wXDPUbxUfvCr8BSicV//eLBjmPNcqK8UNEGDomt++TP4K/3XtRLQct0IGR9b1zu5GdoojRvYZbhJTa79HdEkdMAu/Av+3xRZhoFdwykU=";
//		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDIncXqfAId1GYEndb5qvhnIMKOr1C+36TrkopbInTtsWLdlp8cpaPMaoAVit6Ly2pryJL75X41qm4ztGxVTdJf76o+GivUg2shu8DOyoXQL1k8O4gJ4zlaAj1t7bfP8Rg1c/SeCB7NtfXc5sej19lqbBqhBnYRYrNkhpHHNwoQ9UGbWdxGyM1ZeARhzq+FbZteHHEFr6LG0cq8LAKCa3WyDZvcA6OnJ3yZWOstJFX8klSxBQ4ELgat22FyvAoNC77Mb1laRiGi4kkwAsZsBNEd6f/UaHpT6yKMJLzkeh0JVrIsfVX1pItx34289PbSPk4UPW1Vuq2fNE/LTEKiJmhXAgMBAAECggEBAIOY6zELs5+5qkow98nC2ztbBATw/7iVn+tfrx5lFeqtx7qviSQTi8UVv3tiIuX0w5R8d96BBi9R4wRLwByc9fk0j6o5JLM8mdQBA1MjCnAr/w9BxdUmloJyobUwkLj43/aAX+AiV4J9cBA92ouhld73lug2AtEYu7CfjZTuh83iym1C70tSU8hcfrOPbvT4h0jzCp9wVWjPOcIzsqjWThvoUN8Xi9hGwDcLN/JU9g8rz8YWOaRrjDDsuo+hzi3PXquKsnTBkbjYRKNfZ6CG0cev10r6cqIyVB3uhT2XfVhQ13uOQhLDDi5F0WBH6wCgKnkwB21MaxQltRJia5l6esECgYEA6qNP0uTG3Qy6rJ7Y6lx96teDZ4WiN16DYORfC6FHutVfNk3Iev3UMNGC8DQ67/0mwWQIA6mLnmbFZPFYcTn3cH5F3FbIiK0mbmhkdOP3IZUTISATYcgcRw87yfQrce/kuhJvPnLEQEDSIozpp/NCJmK7fh6iCUqjpixFQweEr8cCgYEA2uGFjKBw0uDrRbrZRF2Rip7Cv2ILy3bzlhpeEU3Sedt83ANxv15Qclh4oDkEN4Nhh6RGZOly8X+8LZJJa6QoWN0kcEkOJ7g1Rw2r2pnkP/YEq0Ln0Hn2JVCdIu8u9YM9BQWAGBnEEuwMH2S5pbCZ+fnI6bFvINTaKYsR0xL3ovECgYBzYVloaE2N96t4JSifnlng+JdKecE6HwTVyZcfXfCq5C/MwVUbfeQxGWWc/uIYj1DWen0iZ+YbP4hDyhZmn02ZCM8LubUk2Jh0ZGs5u7wTzH26pU5CoCefndMVzTbr8T51bYk8mSz+N6HdkZN4njtiKtzt+NxPW1+74Dsy6eiKkwKBgEhqwxfC4XEzz0OTzVyMJKIbYs2VL9fSdEceVC8fc1qz3rygOxsYpj8evFIuaxG+2x7YoB21Cz2RiH3/X2FIv7ye9fZchV1o2d+3SzuZHOZ4jBTpnu7f2yt/X232eq/qCt9FcZsHs1KbXGtqlO2X32nQVcPT27OeGMwT4gLWzK3RAoGAKMc3Z+HJh6Rxvy7GS69i+VWhtCMOS74nIdtX+wwp2GMPrqeG81NQV69f6Nvk3tWDtZS/v0PFz2lo2nBCqd+n3BH1+F3sKVM7tBnLr27U5fhhf5OpGWIExE4n2wkktcnJczPIDI8Dffyow41w2YXBo2+b1oz/XX3876K+JblAbyQ=";		
		//每次生成的公私钥都是随机的
		System.out.println("公钥(base64编码)："+publicKey);
		System.out.println("私钥(base64编码)："+privateKey);
		System.out.println("公钥长度："+ publicKey.length());
		System.out.println("私钥长度："+privateKey.length());
		
//		String content = "大家好，我叫杨凌霄";
		String content = "sign_type=RSA2sign_type=RSA2";
//		String content = "sign_type%3dRSA";
		System.out.println("原文："+content);
		String sign = sign(content.getBytes("utf-8"), privateKey);
		System.out.println("签名："+sign);
		
		boolean verify = verify(content.getBytes("utf-8"), publicKey, sign);
		System.out.println("用公钥进行验签:结果："+verify);
		
		System.out.println("-------------------------------------");
		byte[] encryptByPrivateKeyData = encryptByPrivateKey(content.getBytes("utf-8"), privateKey);
		System.out.println("用私钥对原文加密后的信息(BASE64格式)：" +Base64Utils.encode(encryptByPrivateKeyData));
		
		byte[] decryptByPublicKeyData = decryptByPublicKey(encryptByPrivateKeyData, publicKey);
		System.out.println("用公钥对(用私钥加密后的信息)解密："+ new String(decryptByPublicKeyData,"utf-8"));
		System.out.println("-------------------------------------");

		
		byte[] encryptByPublicKeyData = encryptByPublicKey(content.getBytes("utf-8"), publicKey);
		System.out.println("用公钥对原文加密后的信息(BASE64格式)：" +Base64Utils.encode(encryptByPublicKeyData));
		
		byte[] decryptByPrivateKeyData = decryptByPrivateKey(encryptByPublicKeyData, privateKey);
		System.out.println("用私钥对(用私钥加密后的信息)解密："+ new String(decryptByPrivateKeyData,"utf-8"));
		System.out.println("-------------------------------------");
		
		
		
	}

}
	
