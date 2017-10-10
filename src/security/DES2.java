package security;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created on 2017年10月9日 上午9:24:58
 * @author shimh
 * 功能说明： 
 * 			对称加密
 * 		DES  AES
 *
 */
public class DES2 {
	
	public static final String DEFAULT_KEY = "12345678";
	
	public static void main(String[] args) throws Exception {
		test1();
	}
	
	public static void test1() throws Exception{
		String pwd = "shimh";
		
		//SecretKey key = getKeyDES(); 随机key
		
		SecretKey key = loadKeyDES(DEFAULT_KEY.getBytes());
		
		byte[] byteEn = encryptDES(pwd.getBytes(),key);
		byte[] byteDe = decryptDES(byteEn, key);
		
		System.out.println(new String(byteDe,"utf-8"));
		
		
	}
	
	//生成随机SecretKey
	public static SecretKey getKeyDES() throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		kg.init(56);
		SecretKey key = kg.generateKey();
		return key;
	}
	
	//key.getEncoded() 重新生成SecretKey
	public static SecretKey loadKeyDES(byte[] bytes) throws Exception{
		SecretKey key = new SecretKeySpec(bytes,"DES");
		return key;
	}
	public static SecretKey loadKeyDES2(byte[] bytes) throws Exception{
		
		DESKeySpec dks = new DESKeySpec(bytes);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		return key;
	}
	
	public static byte[] encryptDES(byte[] bytes,SecretKey key) throws Exception{
		
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(bytes);
	}
	
	public static byte[] decryptDES(byte[] bytes,SecretKey key) throws Exception{
		
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(bytes);
	}
	
	
}
