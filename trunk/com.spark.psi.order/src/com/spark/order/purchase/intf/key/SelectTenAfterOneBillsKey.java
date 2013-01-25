package com.spark.order.purchase.intf.key;

import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.PageEnum;

/**
 * <p>��ѯ���10�Ŷ�����Ϣ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2012-2-13
 */
public class SelectTenAfterOneBillsKey extends SelectKey{
	private final BillsEnum billsEnum = BillsEnum.PURCHASE; // ��������
	private final PageEnum tabEnum = PageEnum.LOG; // ҳǩö��
	private final String sortType = "desc";
	private final String sortField = "createDate";
	private int row = 10;
	/**
	 * @return the billsEnum
	 */
	public BillsEnum getBillsEnum() {
		return billsEnum;
	}
	/**
	 * @return the tabEnum
	 */
	public PageEnum getTabEnum() {
		return tabEnum;
	}
	/**
	 * @return the sortType
	 */
	@Override
	public String getSortType() {
		return sortType;
	}
	/**
	 * @return the sortField
	 */
	public String getSortField() {
		return sortField;
	}
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
}
