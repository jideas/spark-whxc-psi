package com.spark.order.util.purchase;

import com.jiuqi.dna.core.Context;

/**
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-23
 */
abstract class CreatePurchaseImpl implements ICreatePurchase{
	/**
	 * ������
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
