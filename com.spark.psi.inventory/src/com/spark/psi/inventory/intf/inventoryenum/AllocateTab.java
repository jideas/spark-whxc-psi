/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.inventoryenum
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-2       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.inventoryenum;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */

public enum AllocateTab {
	/** 新增	 */
	TAB_CREATE,
	
	/** 审核 */
	TAB_EXAMINE,
	
	/** 跟踪 */
	TAB_FOLLOW,
	
	/** 历史 */
	TAB_HISTORY;
	
	public String getTitle() {
		switch(this) {
		case TAB_CREATE:
			return "新增调拨";
		case TAB_EXAMINE:
			return "调拨审批";
		case TAB_FOLLOW:
			return "调拨跟踪";
		case TAB_HISTORY:
			return "调拨记录";
		default:
			return "";
		}
	}
	
}
