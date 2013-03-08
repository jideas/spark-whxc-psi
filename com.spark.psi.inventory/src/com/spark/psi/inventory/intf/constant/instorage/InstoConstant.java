/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.facade
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.constant.instorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>
 * 入库管理常量集合
 * </p>
 * 
 * 
 * 
 * 
 * @author 王志坚
 * @version 2011-11-10
 */

public abstract class InstoConstant {

	public enum TabTitle {
		/**
		 * 采购入库
		 */
		TAB_BUYIN,
		/**
		 * 销售退货
		 */
		TAB_SALECANCEL,
		/**
		 * 其他入库
		 */
		TAB_OTHERIN,
		/**
		 *入库记录
		 */
		TAB_HISTORY;

		/**
		 * 列表默认排序列
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
		 * 得到枚舉值的标题
		 * 
		 * @return String
		 */
		public String getTitle() {
			switch (this) {
			case TAB_BUYIN:
				return "采购入库";
			case TAB_SALECANCEL:
				return "销售退货";
			case TAB_OTHERIN:
				return "其他入库";
			case TAB_HISTORY:
				return "入库记录";
			default:
				return "";
			}
		}
	}

	/**
	 * 得到排序字段
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
	 * 根据长度 截取字符串
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
	 * 供应商直供的虚拟仓库id和名称
	 */
	public final static GUID PROVIDERSOTRE = GUID.valueOf("00000000000000000000000000000000");
	public final static String PROVIDERSOTRENAME = "供应商直供";

	/**
	 * 客户/供应商往来款类型定义
	 * 
	 * @author yanglin
	 * 
	 */
	// public final static class CusdealType{
	// /**
	// * 调整应收
	// */
	// public static final String CUS_TZYS = "01";
	// /**
	// * 销售出库
	// */
	// public static final String CUS_XSCK = "02";
	// /**
	// * 退货入库
	// */
	// public static final String CUS_THRK = "03";
	// /**
	// * 销售收款
	// */
	// public static final String CUS_XSSK = "04";
	// /**
	// * 销售退款
	// */
	// public static final String CUS_XSTK = "05";
	// /**
	// * 零售出库
	// */
	// public static final String CUS_LSCK = "06";
	// /**
	// * 零售收款
	// */
	// public static final String CUS_LSSK = "07";
	// /**
	// * 零售退货
	// */
	// public static final String CUS_LSTH = "08";
	// /**
	// * 零售退款
	// */
	// public static final String CUS_LSTK = "09";
	// /**
	// * 初始化应收
	// */
	// public static final String CUS_INIT = "10";
	//
	// /**
	// * 调整应付
	// */
	// public static final String PRO_TZYF = "01";
	// /**
	// * 采购入库
	// */
	// public static final String PRO_CGRK = "02";
	// /**
	// * 退货出库
	// */
	// public static final String PRO_THCK = "03";
	// /**
	// * 采购付款
	// */
	// public static final String PRO_CGFK = "04";
	// /**
	// * 采购退款
	// */
	// public static final String PRO_CGTK = "05";
	// /**
	// * 零采入库
	// */
	// public static final String PRO_LXCG = "06";
	// /**
	// * 初始化应付
	// */
	// public static final String PRO_INIT = "07";
	// /**
	// * 零采付款
	// */
	// public static final String PRO_LCFK = "08";
	// }

	/**
	 * <p>
	 * 客户供应商类型
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * @author 王志坚
	 * @version 2011-11-10
	 */
	public enum CusType {
		/**
		 * 客户
		 */
		CUSTOMER,
		/**
		 * 供应商
		 */
		PROVIDER,
		/**
		 * 零星采购
		 */
		LXCG,
		/**
		 * 散客
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
				return "客户";
			case PROVIDER:
				return "供应商";
			}
			return "";
		}
	}
}
