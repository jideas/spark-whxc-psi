package com.spark.order.util;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.facade.BillsConstant;

/**
 * <p>业务工具类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-10-18
 */
public class SUtil {
	/**
	 * 上下文
	 */
	protected Context context;
	public SUtil(Context context){
		this.context = context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	/**
	 * 获得租户GUID
	 * @return GUID
	 */
	public GUID getTenantsGuid(){
		return BillsConstant.getTenantsGuid(context);
	}
}
