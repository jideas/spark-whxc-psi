package com.spark.order.util;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.facade.BillsConstant;

/**
 * <p>ҵ�񹤾���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-10-18
 */
public class SUtil {
	/**
	 * ������
	 */
	protected Context context;
	public SUtil(Context context){
		this.context = context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	/**
	 * ����⻧GUID
	 * @return GUID
	 */
	public GUID getTenantsGuid(){
		return BillsConstant.getTenantsGuid(context);
	}
}
