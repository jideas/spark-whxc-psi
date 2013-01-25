package com.spark.order.intf.key;

import com.spark.order.intf.type.BillsEnum;

/**
 * <p>У���ڶ���ҳǩ�����ύ�����˻�״̬���Ƿ��е���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-7
 */
public class ValiBillsByNewKey extends SelectKey{
	private final BillsEnum billsEnum;
	public ValiBillsByNewKey(BillsEnum billsEnum){
		this.billsEnum = billsEnum;
	}
	/**
	 * ��ȡ��ǰ
	 * @return BillsEnum
	 */
	public BillsEnum getBillsEnum() {
		return billsEnum;
	}
}
