package security;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * Created on 2017年10月9日 上午9:24:58
 * @author shimh
 * 功能说明： 
 * 			数字签名：对非对称加密和数字摘要的综合运用。
 * 						主要用来验证发送者的身份是否合法，正文的内容是否被修改。
 * 
 * 			场景：A： 1 通信正文 + MD5 + RSA ---> 数字签名 
 * 					 2 A将通信正文和生成的数字签名发送给B
 * 				
 * 				  B：通信正文 + MD5 ======= 数字签名 + RSA 校验
 * 			MD5WithRSA
 *
 */
public class Signature4 {
	
	public static void main(String[] args) throws Exception {
		test();
	}
	
	public static void test() throws Exception{
		String content = "i am superman";
		KeyPair kp = RSA3.getKeyPair();
		PublicKey publicKey = kp.getPublic();
		PrivateKey privateKey = kp.getPrivate();
		
		//生成数字签名 
		byte[] sign = sign(content.getBytes(),privateKey);

		//校验数字签名  
		boolean verify = verify(content.getBytes(),sign,publicKey);
		System.out.println(verify);
	}
	
	
	public static byte[] sign(byte[] content,PrivateKey privateKey) throws Exception{
		Signature signature = Signature.getInstance("MD5withRSA");  
        signature.initSign(privateKey);  
        signature.update(content); 
        return signature.sign();
	}
	
	public static boolean verify(byte[] content,byte[] sign,PublicKey publicKey) throws Exception{
		Signature signature = Signature.getInstance("MD5withRSA");  
        signature.initVerify(publicKey);  
        signature.update(content);  
        // 验证签名是否正常  
        return signature.verify(sign); 
	}
}
