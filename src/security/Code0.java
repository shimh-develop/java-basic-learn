//package security;
//
//import java.io.IOException;
//
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
///**
// * Created on 2017年10月9日 上午9:24:58
// * @author shimh
// * 功能说明：
// * 		由于转换后的字符串，可能产生无法显示和网络传输的控制字符，需要对其编码。
// * 		十六进制编码和Base64编码
// *
// */
//public class Code0 {
//
//	public static String bytes2Hex(byte[] bytes){
//		StringBuilder hex = new StringBuilder();
//
//		for(int i = 0; i < bytes.length; i++){
//			byte b = bytes[i];
//			boolean negative = false;
//			if(b < 0) negative = true;
//			int inte = Math.abs(b);
//			if(negative) inte = inte | 0x80;
//			String temp = Integer.toHexString(inte & 0xFF);
//			if(temp.length() == 1){
//				hex.append("0");
//			}
//			hex.append(temp.toLowerCase());
//		}
//		return hex.toString();
//	}
//
//	public static byte[] hex2Bytes(String hex){
//		byte[] bytes = new byte[hex.length()/2];
//
//		for(int i = 0; i < hex.length(); i = i + 2){
//			String subStr = hex.substring(i,i + 2);
//			boolean negative = false;
//			int inte = Integer.parseInt(subStr,16);
//			if(inte > 127) negative = true;
//			if(inte == 128){
//				inte = -128;
//			}else if(negative){
//				inte = 0 - (inte & 0x7F);
//			}
//			byte b = (byte)inte;
//			bytes[i/2] = b;
//		}
//
//		return bytes;
//	}
//
//	public static String byte2Base64(byte[] bytes){
//		BASE64Encoder encoder = new BASE64Encoder();
//		return encoder.encode(bytes);
//	}
//
//	public static byte[] base642Byte(String base64) throws IOException{
//		BASE64Decoder decoder = new BASE64Decoder();
//		return decoder.decodeBuffer(base64);
//	}
//}
