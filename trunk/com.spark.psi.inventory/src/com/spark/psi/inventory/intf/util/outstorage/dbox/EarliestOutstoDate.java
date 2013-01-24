/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.outstorage.dbox
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-25       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.outstorage.dbox;

/**
 * <p>最早出库时间</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-25
 */

public class EarliestOutstoDate{

	private long date;

	public EarliestOutstoDate(long date){
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public long getDate(){
		return date;
	}
}
