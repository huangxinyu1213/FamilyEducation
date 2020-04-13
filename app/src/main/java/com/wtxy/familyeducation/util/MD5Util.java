package com.wtxy.familyeducation.util;

import java.security.MessageDigest;

/**
 * MD5 加密编码工具类
 * 
 * @author guowei
 * @version 2010-6-21 下午04:03:17
 */
public class MD5Util {

	/**
	 * 将输入的字符串进行MD5加密（编码）
	 * 
	 * @param inputString
	 * @return
	 */
	public static String createMD5String(String inputString) {
		return encodeByMD5(inputString);
	}

	/**
	 * 验证MD5密码是否正确
	 * 
	 * @param md5
	 * @param inputString
	 * @return
	 */
	public static boolean authMD5String(String md5, String inputString) {
		if (md5.equals(encodeByMD5(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对字符串进行MD5编码
	 * 
	 * @param originStr
	 * @return
	 */
	public static String encodeByMD5(String originStr) {
		if (originStr != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后的更新，然后完成摘要计算
				char[] _charStr = originStr.toCharArray();
				byte[] _byteStr = new byte[_charStr.length];
				for (int i = 0; i < _charStr.length; i++) {
					_byteStr[i] = (byte) _charStr[i];
				}
				byte[] _results = md.digest(_byteStr);
				StringBuffer _hexValue = new StringBuffer();
				for (int i = 0; i < _results.length; i++) {
					int _val = ((int) _results[i]) & 0xff;
					if (_val < 16) {
						_hexValue.append("0");
					}
					_hexValue.append(Integer.toHexString(_val));
				}
				return _hexValue.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
//	public static String hashToMD5Hex(String string) {
//		if (string == null || string.trim().length() < 1) {
//			return null;
//		}
//		String signStr = null;
//		try {
//			byte[] bytes = string.getBytes("utf-8");
//			MessageDigest md5 = MessageDigest.getInstance("MD5");
//			md5.update(bytes);
//			byte[] md5Byte = md5.digest();
//			if(md5Byte != null){
//				signStr = HexBin.encode(md5Byte);
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		return signStr;
//	}

	public static void main(String[] args) throws Exception {
		// String _utf8Name = URLEncoder.encode("最终NO2" , "utf-8");
		// System.out.println(URLDecoder.decode(_utf8Name , "utf-8"));
		// System.out.println(_utf8Name);
		// uid + key
		// uid+rolename+gender+job+key
		// System.out.println(createMD5String("25642108" + _utf8Name + "0" + "1"
		// + SharedConstants.EXTERNAL_SYS_MD5_KEY));
	}

}
