/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.intf.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-11       莫迪        
 * ============================================================*/

package com.spark.order.intf.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>查询已出入库商品实体类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 王天才
 * @version 2011-11-11
 */

public class SearchStoreEntity {

	/**
	 * 客户供应商GUID
	 */
	private GUID cusproGuid;
	private String cusproName;
	/**
	 * 已出入库商品实体LIST
	 */
	private List<SearchStoreGoods> goodsList;
	
	public GUID getCusproGuid() {
		return cusproGuid;
	}
	public void setCusproGuid(GUID cusproGuid) {
		this.cusproGuid = cusproGuid;
	}
	public List<SearchStoreGoods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<SearchStoreGoods> goodsList) {
		this.goodsList = goodsList;
	}
	public void setCusproName(String cusproName) {
		this.cusproName = cusproName;
	}
	public String getCusproName() {
		return cusproName;
	}
}
