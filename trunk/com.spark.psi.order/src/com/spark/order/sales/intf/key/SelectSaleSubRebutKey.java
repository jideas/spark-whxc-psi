package com.spark.order.sales.intf.key;

import com.spark.order.intf.key.SelectMainKey;
import com.spark.order.purchase.intf.key.SelectPurchaseSubRebutKey;


/**
 * <p>��ѯ���ύ�����˻زɹ���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-18
 */
public class SelectSaleSubRebutKey extends SelectPurchaseSubRebutKey{

	public SelectSaleSubRebutKey(SelectMainKey mainKey) {
		super(mainKey);
	}
}
