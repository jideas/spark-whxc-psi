/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.demo.store.common
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-10-22       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.inventoryenum;

/**
 * <p>仓库明项</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author zhongxin
 * @version 2011-10-22
 */

public enum StoreDetailItem {
	/**  管理员 */
	STOREMANAGER("0"),
	/**  销售 */
	SALER("1"),
	/**  采购 */
	PURCHASE("2");
	
	private String value;
	private StoreDetailItem(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
