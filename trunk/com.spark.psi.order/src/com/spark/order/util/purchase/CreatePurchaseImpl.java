package com.spark.order.util.purchase;

import com.jiuqi.dna.core.Context;

/**
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-23
 */
abstract class CreatePurchaseImpl implements ICreatePurchase{
	/**
	 * 上下文
	 */
	protected Context context;
	/**
	 * @param context
	 */
	public CreatePurchaseImpl(Context context) {
		super();
		this.context = context;
	}
}
