package com.spark.order.intf;

import java.util.HashMap;
import java.util.Map;

import com.spark.psi.base.ApprovalConfig;


/**
 * <p>��������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */
public final class OrderConstant2 {
	static Map<ApprovalConfig.Mode, OrderEnum> approvalMap = new HashMap<ApprovalConfig.Mode, OrderEnum>();
	private OrderConstant2(){
		
	}
	public static String stopCode = "-1";
	public static String stopName = "����ֹ";
	
}
