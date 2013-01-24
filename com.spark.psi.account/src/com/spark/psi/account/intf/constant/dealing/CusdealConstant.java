package com.spark.psi.account.intf.constant.dealing;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>��������</p>
 *


 *
 * @author ����
 * @version 2011-11-15
 */

public final class CusdealConstant {
	
	public final static Object lock = new Object();
	
	/**
	 * �ͻ�������,�����ֵ䶨������
	 */
	public final static String CUSDEAL_TYPE_CUS = "cusdeal_cus";
	/**
	 * ��Ӧ��������,�����ֵ䶨������
	 */
	public final static String CUSDEAL_TYPE_PRO = "cusdeal_pro";
	/**
	 * �տʽ����,�����ֵ䶨������
	 */
	public final static String RECEIPT_TYPE = "receiptOrpay_way";
	
	public final static Map<String,String> CUS_TYPE_MAP = new HashMap<String, String>();
	public final static Map<String,String> PRO_TYPE_MAP = new HashMap<String, String>();
	public final static Map<String,String> RECEIPT_TYPE_MAP = new HashMap<String, String>();
	
	/**
	 * �Ƿ���Ҫ��ʼ������
	 */
	public static boolean needInit = true; 
	
	public final static String[] cusColumns = {"�ͻ�����","Ӧ�ս��","����"};
	
	/**
	 * �ո����ȡ������������
	 * @author yanglin
	 *
	 */
	public static enum DATA_TYPE {
		//ȡ���е�����
		ALL,
		//���۲鿴�ͻ����
		SALE,
		//�ɹ��鿴��Ӧ�����
		BUY
	}

	/**
	 * �ո��������ֶ�
	 */
	public static enum ORDER_COLUMNS {
		//����
		CUSPRO_NAME,
		//����
		CUSPRO_TYPE,
		//Ӧ��/Ӧ���ܶ�
		AMOUNT,
		//���ö��
		CREDITLINE,
		//�ѳ��ѳ����ö��
		EXCEEDED_CREDITLINE,
		//�ѳ�����
		EXCEEDED_ACCOUNT_PERIOD;
		
		public String toString() {
			switch(this){
            case CUSPRO_NAME:
	            return "cusproName";
            case CUSPRO_TYPE:
	            return "cusproType";
            case AMOUNT:
	            return "takePayBalance";
            case CREDITLINE:
	            return "creditline";
            case EXCEEDED_CREDITLINE:
	            return "exceededCreditline";
            case EXCEEDED_ACCOUNT_PERIOD:
	            return "exceededAccountPeriod";
	        }
			return "";
		}
	}
	/**
	 * ��������
	 */
	public static enum ORDER_TYPE {
		//����
		DESC,
		//����
		ASC;
		public String toString() {
			switch (this) {
			case DESC:
				return "desc";
			case ASC:
				return "asc";
			default:
				break;
			}
			return "";
		}
	}
}
