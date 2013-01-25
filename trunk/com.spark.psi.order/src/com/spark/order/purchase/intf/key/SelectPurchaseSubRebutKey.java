package com.spark.order.purchase.intf.key;

import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.key.SelectMainKey;


/**
 * <p>��ѯ���ύ�����˻زɹ���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-18
 */
public class SelectPurchaseSubRebutKey extends SelectKey{
	private final SelectMainKey mainKey;
	public SelectPurchaseSubRebutKey(SelectMainKey mainKey) {
		this.mainKey = mainKey;
	}

	/**
	 * @return the mainKey
	 */
	public SelectMainKey getMainKey() {
		return mainKey;
	}
}
