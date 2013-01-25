/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

package com.spark.order.sales.intf.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.entity.OrderDet;

/**
 * <p>销售退货明细实体</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 王天才
 * @version 2011-11-10
 */

public class SaleCancelItem extends OrderDet{

	/**
	 * 仓库
	 */
	private GUID storeId;
	private String storeName;
	/**
	 * 最近采购价格
	 */
	private double recentPrice;
	/**
	 * 库存数量
	 */
	private double storeNum;
	 
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public double getRecentPrice() {
		return recentPrice;
	}
	public void setRecentPrice(double recentPrice) {
		this.recentPrice = recentPrice;
	}
	public double getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(double storeNum) {
		this.storeNum = storeNum;
	}
}
