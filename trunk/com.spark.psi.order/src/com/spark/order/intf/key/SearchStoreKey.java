/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-16       莫迪        
 * ============================================================*/

package com.spark.order.intf.key;

import java.util.Date;

import com.spark.order.intf.type.BillsEnum;

/**
 * <p>退货单查询出入库商品KEY</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 王天才
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
