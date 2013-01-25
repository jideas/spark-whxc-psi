/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.report.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-12-13       汤成国        
 * ============================================================*/

package com.spark.psi.report.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>根据租户和日期得到最新的一条滚动屏数据</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 汤成国
 * @version 2011-12-13
 */

public class GetScreenDataByDateAndTenantsGuidKey{

	private GUID tenantId; // 租户GUID
	private long currentDate; // 当期日期
	/**
	 * 
	 * @param tenantId 租户GUID
	 * @param currentDate 当期日期
	 */
	public GetScreenDataByDateAndTenantsGuidKey(GUID tenantId,
	        long currentDate) {
		this.tenantId = tenantId;
		this.currentDate = currentDate;
	}
	/**
     * @return 租户GUID
     */
    public GUID getTenantsGuid(){
    	return tenantId;
    }
	/**
     * @return 当期日期
     */
    public long getCurrentDate(){
    	return currentDate;
    }
}
