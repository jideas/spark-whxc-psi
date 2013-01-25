package com.spark.order.sales2;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.OrderSimpleTaskFather;

/**
 * 设置销售预计出库日期
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-7
 */
public class SetSalesPlanOutDateTask extends OrderSimpleTaskFather{
	private final GUID id;
	private final long date;
	
	/**
	 * @param id
	 * @param date
	 */
	public SetSalesPlanOutDateTask(GUID id, long date) {
		super();
		this.id = id;
		this.date = date;
	}
	public GUID getId() {
		return id;
	}

	public long getDate() {
		return date;
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
