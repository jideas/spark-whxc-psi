/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.facade
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.constant.instorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * ������������
 * </p>
 * 
 * 
 * 
 * 
 * @author ��־��
 * @version 2011-11-10
 */

public abstract class InstoConstant {

	public enum TabTitle {
		/**
		 * �ɹ����
		 */
		TAB_BUYIN,
		/**
		 * �����˻�
		 */
		TAB_SALECANCEL,
		/**
		 * �������
		 */
		TAB_OTHERIN,
		/**
		 *����¼
		 */
		TAB_HISTORY;

		/**
		 * �б�Ĭ��������
		 * 
		 * @return String
		 */
		public String getDefaultOrderColumn() {
			switch (this) {
			case TAB_BUYIN:
				return "t.planInstoDate";
			case TAB_SALECANCEL:
				return "t.createDate";
			case TAB_HISTORY:
				return "t.allInstoDate";
			default:
				return "";
			}
		}

		/**
		 * �õ�ö�eֵ�ı���
		 * 
		 * @return String
		 */
		public String getTitle() {
			switch (this) {
			case TAB_BUYIN:
				return "�ɹ����";
			case TAB_SALECANCEL:
				return "�����˻�";
			case TAB_OTHERIN:
				return "�������";
			case TAB_HISTORY:
				return "����¼";
			default:
				return "";
			}
		}
	}

	/**
	 * �õ������ֶ�
	 * 
	 * @param title
	 * @param index
	 * @return String
	 */
	public final static String getOrderField(TabTitle title, int index) {
		initArray();
		switch (title) {
		case TAB_BUYIN:
			return buyOrder[index];
		case TAB_SALECANCEL:
			return cancelOrder[index];
		case TAB_HISTORY:
			return historyOrder[index];
		}
		return null;
	}

	private static void initArray() {
		if (null != buyOrder) {
			return;
		}
		buyOrder = new String[] { "t.checkinDate", "t.sheetNo", "t.relaBillsNo", "t.storeNamePY", "t.status" };
		cancelOrder = new String[] { "t.createDate", "t.sheetNo", "t.relaBillsNo", "t.storeNamePY",
				"t.status" };
		historyOrder = new String[] { "t.checkinDate", "t.sheetNo", "t.sheetType", "t.relaBillsNo",
				"t.storeNamePY", "collate_gbk(t.checkinString)", "t.status" };
	}

	/**
	 * 
	 * ���ݳ��� ��ȡ�ַ���
	 * 
	 * @param str
	 * @param length
	 * @return String
	 */
	public static String subStrByLength(String str, int length) {
		if (str.length() <= length) {
			return str;
		}
		int byteLength = 2 * length;
		byte[] bs = str.getBytes();
		if (bs.length <= byteLength) {
			return str;
		}
		byte[] bs2 = new byte[byteLength];
		for (int i = 0; i < byteLength; i++) {
			bs2[i] = bs[i];
		}
		String s = new String(bs2);
		String sub = s.substring(0, s.length() - 1);
		if (s.getBytes().length - 1 == sub.getBytes().length) {
			return sub;
		}
		return s;
	}

	private static String[] buyOrder;
	private static String[] cancelOrder;
	private static String[] historyOrder;

	/**
	 * ��Ӧ��ֱ��������ֿ�id������
	 */
	public final static GUID PROVIDERSOTRE = GUID.valueOf("00000000000000000000000000000000");
	public final static String PROVIDERSOTRENAME = "��Ӧ��ֱ��";

	/**
	 * �ͻ�/��Ӧ�����������Ͷ���
	 * 
	 * @author yanglin
	 * 
	 */
	// public final static class CusdealType{
	// /**
	// * ����Ӧ��
	// */
	// public static final String CUS_TZYS = "01";
	// /**
	// * ���۳���
	// */
	// public static final String CUS_XSCK = "02";
	// /**
	// * �˻����
	// */
	// public static final String CUS_THRK = "03";
	// /**
	// * �����տ�
	// */
	// public static final String CUS_XSSK = "04";
	// /**
	// * �����˿�
	// */
	// public static final String CUS_XSTK = "05";
	// /**
	// * ���۳���
	// */
	// public static final String CUS_LSCK = "06";
	// /**
	// * �����տ�
	// */
	// public static final String CUS_LSSK = "07";
	// /**
	// * �����˻�
	// */
	// public static final String CUS_LSTH = "08";
	// /**
	// * �����˿�
	// */
	// public static final String CUS_LSTK = "09";
	// /**
	// * ��ʼ��Ӧ��
	// */
	// public static final String CUS_INIT = "10";
	//
	// /**
	// * ����Ӧ��
	// */
	// public static final String PRO_TZYF = "01";
	// /**
	// * �ɹ����
	// */
	// public static final String PRO_CGRK = "02";
	// /**
	// * �˻�����
	// */
	// public static final String PRO_THCK = "03";
	// /**
	// * �ɹ�����
	// */
	// public static final String PRO_CGFK = "04";
	// /**
	// * �ɹ��˿�
	// */
	// public static final String PRO_CGTK = "05";
	// /**
	// * ������
	// */
	// public static final String PRO_LXCG = "06";
	// /**
	// * ��ʼ��Ӧ��
	// */
	// public static final String PRO_INIT = "07";
	// /**
	// * ��ɸ���
	// */
	// public static final String PRO_LCFK = "08";
	// }

	/**
	 * <p>
	 * �ͻ���Ӧ������
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author ��־��
	 * @version 2011-11-10
	 */
	public enum CusType {
		/**
		 * �ͻ�
		 */
		CUSTOMER,
		/**
		 * ��Ӧ��
		 */
		PROVIDER,
		/**
		 * ���ǲɹ�
		 */
		LXCG,
		/**
		 * ɢ��
		 */
		SK;

		public String toCode() {
			switch (this) {
			case CUSTOMER:
				return "01";
			case PROVIDER:
				return "02";
			case LXCG:
				return "03";
			case SK:
				return "04";
			}
			return "";
		}

		public String toString() {
			switch (this) {
			case CUSTOMER:
				return "�ͻ�";
			case PROVIDER:
				return "��Ӧ��";
			}
			return "";
		}
	}
}
