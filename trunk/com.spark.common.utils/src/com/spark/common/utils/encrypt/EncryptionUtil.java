package com.spark.common.utils.encrypt;

public abstract class EncryptionUtil {

	/**
	 * ��md5�����ַ���
	 * 
	 * @param inbuf
	 * @return
	 */
	public static String encryptMD5(String inbuf) {
		MD5 md5 = new MD5();
		return md5.getMD5ofStr(inbuf);
	}

	/**
	 * ��AES�����ַ���
	 * 
	 * @param inbuf
	 *            Ҫ���ܵ��ַ���
	 * @param secretkey
	 *            ��Կ
	 * @throws Exception
	 */
	public static String encryptAES(String inbuf, String secretkey) throws Exception {
		String s = EncryptionUtil.encryptMD5(secretkey);
		s = s.substring(8, 24);
		return AES.Encrypt(inbuf, s);
	}

	/**
	 * ��AES�����ַ���
	 * 
	 * @param inbuf
	 *            Ҫ���ܵ��ַ���
	 * @param secretkey
	 *            ��Կ
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES(String inbuf, String secretkey) throws Exception {
		String s = EncryptionUtil.encryptMD5(secretkey);
		s = s.substring(8, 24);
		return AES.Decrypt(inbuf, s);
	}
}
