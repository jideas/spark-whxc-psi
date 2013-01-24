package com.spark.psi.account.intf.constant.dealing;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>常量集合</p>
 *


 *
 * @author 杨霖
 * @version 2011-11-15
 */

public final class CusdealConstant {
	
	public final static Object lock = new Object();
	
	/**
	 * 客户往类型,数据字典定义名称
	 */
	public final static String CUSDEAL_TYPE_CUS = "cusdeal_cus";
	/**
	 * 供应商往类型,数据字典定义名称
	 */
	public final static String CUSDEAL_TYPE_PRO = "cusdeal_pro";
	/**
	 * 收款方式类型,数据字典定义名称
	 */
	public final static String RECEIPT_TYPE = "receiptOrpay_way";
	
	public final static Map<String,String> CUS_TYPE_MAP = new HashMap<String, String>();
	public final static Map<String,String> PRO_TYPE_MAP = new HashMap<String, String>();
	public final static Map<String,String> RECEIPT_TYPE_MAP = new HashMap<String, String>();
	
	/**
	 * 是否需要初始化数据
	 */
	public static boolean needInit = true; 
	
	public final static String[] cusColumns = {"客户名称","应收金额","操作"};
	
	/**
	 * 收付款获取往来数据类型
	 * @author yanglin
	 *
	 */
	public static enum DATA_TYPE {
		//取所有的数据
		ALL,
		//销售查看客户情况
		SALE,
		//采购查看供应商情况
		BUY
	}

	/**
	 * 收付款排序字段
	 */
	public static enum ORDER_COLUMNS {
		//名称
		CUSPRO_NAME,
		//类型
		CUSPRO_TYPE,
		//应收/应付总额
		AMOUNT,
		//信用额度
		CREDITLINE,
		//已超已超信用额度
		EXCEEDED_CREDITLINE,
		//已超账期
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
	 * 排序类型
	 */
	public static enum ORDER_TYPE {
		//降序
		DESC,
		//升序
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
