/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-16       Ī��        
 * ============================================================*/

package com.spark.order.intf.key;

import java.util.Date;

import com.spark.order.intf.type.BillsEnum;

/**
 * <p>�˻�����ѯ�������ƷKEY</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-16
 */

public class SearchStoreKey {

	private String cusproName;
	private String goodsName;
	private Date startDate;
	private Date endDate;
	private BillsEnum billsEnum;
	
	public String getCusproName() {
		return cusproName;
	}
	public void setCusproName(String cusproName) {
		this.cusproName = cusproName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setBillsEnum(BillsEnum billsEnum) {
		this.billsEnum = billsEnum;
	}
	public BillsEnum getBillsEnum() {
		return billsEnum;
	}
	
	
}
