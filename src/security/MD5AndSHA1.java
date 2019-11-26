//package security;
//
//import java.io.IOException;
//import java.security.MessageDigest;
//
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
///**
// * Created on 2017年10月9日 上午9:24:58
// * @author shimh
// * 功能说明：
// * 		数字摘要（消息摘要）单向哈希函数对消息进行计算而产生
// * 		MD5 SHA
// *
// */
//public class MD5AndSHA1 {
//	public static void main(String[] args) throws Exception {
//		test1();
//	}
//
//	private static void test1() throws Exception{
//		String pwd = "shimh";
//
//		byte[] pwdByte = md5(pwd);
//		String pwdString = Code0.byte2Base64(pwdByte);
//		System.out.println("md5-base64encoder:" + pwdString);
//		System.out.println("md5-16:" + Code0.bytes2Hex(pwdByte));
//	}
//	private static byte[] md5(String pwd) throws Exception{
//
//		MessageDigest md = MessageDigest.getInstance("MD5");
//
//		byte[] pwdByte =  md.digest(pwd.getBytes("utf-8"));
//		return pwdByte;
//
//	}
//
//	private static byte[] sha(String pwd) throws Exception{
//
//		MessageDigest md = MessageDigest.getInstance("SHA-1");
//
//		byte[] pwdByte =  md.digest(pwd.getBytes("utf-8"));
//
//		return pwdByte;
//	}
//
//}
