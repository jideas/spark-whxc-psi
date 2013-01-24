package com.spark.common.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * �绰���뼰�����ʹ�����
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author Ī��
 * @version 2011-11-17
 */
public final class PhoneAndTypeUtil {
	public static String separator = "��";
	private final static Map<String, PhoneType> phoneMap = new HashMap<String, PhoneType>();
	private PhoneAndTypeUtil() {

	}
	public enum PhoneType
	{
		/**
		 * �̻�
		 */
		TELEPHONE("1", "�̻�"),
		/**
		 * �ֻ�
		 */
		MOBILEPHONE("2", "�ֻ�"),
		/**
		 * ����
		 */
		FAX("3", "����");
		private String key;
		private String name;
		private PhoneType(String str, String name){
			this.key = str;
			this.name = name;
			phoneMap.put(str, this);
		}
		/**
		 * ������ͱ�ʶ
		 * @return String
		 */
		public String getKey() {
			return key;
		}
		
		/**
		 * �����������
		 * @return String
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * ���ݱ�ʶ��ȡ�̻�����
		 * @param key
		 * @return PhoneType
		 */
		public static PhoneType getPhoneType(String key){
			return phoneMap.get(key);
		}
	}

	private PhoneType type;// �绰��������
	private String tel;// �绰����

	/**
	 * ���ƴ���ַ���
	 * @return String
	 */
	public String spell(){
		return spell(tel, type);
	}
	
	/**
	 * �绰+����ƴ��
	 * 
	 * @param tel
	 * @param type
	 * @return String
	 */
	public static String spell(String tel, PhoneType type) {
		return type.getKey() + separator + tel;
	}

	/**
	 * �绰+���Ͳ��
	 * 
	 * @param telStr
	 * @return PhoneAndTypeUtil
	 */
	public static PhoneAndTypeUtil split(String telStr) {
		PhoneAndTypeUtil util = new PhoneAndTypeUtil();
		if(null == telStr){
			return util;
		}
		String[] strs = telStr.split(separator);
		if (strs.length > 1) {
			util.setTel(strs[1]);
			util.setType(PhoneType.getPhoneType(strs[0]));
		}
		return util;
	}

	/**
	 * ������first���ȼ��Զ�ɸѡ����
	 * 
	 * @param mobile
	 *            �ֻ�
	 * @param tel
	 *            �̻�
	 * @param first
	 *            ��������
	 * @return String
	 */
	public static PhoneAndTypeUtil selectTel(String mobile, String tel,
			PhoneType first) {
		PhoneAndTypeUtil util = new PhoneAndTypeUtil();
		switch (first) {
		case MOBILEPHONE:
			if(null == mobile){
				util.setType(PhoneType.TELEPHONE);
				util.setTel(tel == null?"":tel);
			}
			else{
				util.setType(PhoneType.MOBILEPHONE);
				util.setTel(mobile == null?"":mobile);
			}
			break;
		case TELEPHONE:
			if(null == tel){
				util.setType(PhoneType.MOBILEPHONE);
				util.setTel(mobile == null?"":mobile);
			}
			else{
				util.setType(PhoneType.TELEPHONE);
				util.setTel(tel == null?"":tel);
			}
			break;

		default:
			break;
		}
		return util;
	}

	/**
	 * @return the type
	 */
	public PhoneType getType() {
		return type;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(PhoneType type) {
		this.type = type;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
}
