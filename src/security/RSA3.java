package security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created on 2017年10月9日 上午9:24:58
 * @author shimh
 * 功能说明： 
 * 			非对称加密：两个密钥 一个公开密钥  一个私有密钥
 * 		RSA
 *
 */
public class RSA3 {
	
	public static void main(String[] args) throws Exception {
		test1();
	}
	
	public static void test1() throws Exception{
		
		String data = "123456789";
		System.out.println(data);
		KeyPair kp = getKeyPair();
		PublicKey publicKey = kp.getPublic();
		PrivateKey privateKey = kp.getPrivate();
		
		//System.out.println(getPrivateKey(kp));
		
		byte[] enBytes = publicEncrypt(data.getBytes(),publicKey);
		System.out.println("加密后-base64："+ Code0.byte2Base64(enBytes));
		byte[] deBytes = privateDecrypt(enBytes,privateKey);
		
		System.out.println(new String(deBytes));
		
	}
	
	public static KeyPair getKeyPair() throws Exception{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024); //此大小与明文的长度有关
		KeyPair kp = kpg.generateKeyPair();
		return kp;
	}
	//将公钥编码 方便保存 如：数据库
	public static String getPublicKey(KeyPair keyPair) throws Exception{
		PublicKey  publicKey =  keyPair.getPublic();
		byte[] bytes = publicKey.getEncoded();
		return Code0.byte2Base64(bytes);
	}
	//将私钥编码 方便保存 如：数据库
	public static String getPrivateKey(KeyPair keyPair) throws Exception{
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] bytes = privateKey.getEncoded();
		return Code0.byte2Base64(bytes);
	}
	//将string转换为PublicKey
	public static PublicKey string2PublicKey(String keyStr) throws Exception{
		byte[] bytes = Code0.base642Byte(keyStr);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey publicKey = kf.generatePublic(keySpec);
		return publicKey;
	}
	
	
	//将string转换为PrivateKey
	public static PrivateKey string2PrivateKey(String keyStr) throws Exception{
		byte[] bytes = Code0.base642Byte(keyStr);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = kf.generatePrivate(keySpec);
		return privateKey;
	}
	//公钥加密
	public static byte[] publicEncrypt(byte[] bytes, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        return cipher.doFinal(bytes);
	}
	//私钥解密
	public static byte[] privateDecrypt(byte[] bytes, PrivateKey privateKey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");   
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        return cipher.doFinal(bytes);  
	}
	
	//私钥加密
	public static byte[] privateEncrypt(byte[] bytes, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
        return cipher.doFinal(bytes);
	}
	//公钥解密
	public static byte[] publicDecrypt(byte[] bytes, PublicKey publicKey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");   
        cipher.init(Cipher.DECRYPT_MODE, publicKey);  
        return cipher.doFinal(bytes);  
	}
	
}
