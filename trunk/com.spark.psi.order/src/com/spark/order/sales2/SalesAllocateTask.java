package com.spark.order.sales2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.OrderSimpleTaskFather;

/**
 * 销售配货完成
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-7
 */
public class SalesAllocateTask extends OrderSimpleTaskFather{
	private final GUID id;
	
	/**
	 * @param id
	 */
	public SalesAllocateTask(GUID id) {
		super();
		this.id = id;
	}
	
	public GUID getId() {
		return id;
	}

	@Override
	protected void setLenght(int lenght) {
		this.lenght = lenght;
	}
	@Override
	protected void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
}
