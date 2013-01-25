/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.report.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-12-15       汤成国        
 * ============================================================*/

package com.spark.psi.report.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.report.constant.OldReportEnums.FenXiType;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 汤成国
 * @version 2011-12-15
 */

public class GetScereenDataKey{

	private GUID tenantId; // 租户GUID
	private GUID deptGuid; // 部门GUID
	private GUID empGuid; // 员工GUID
	private FenXiType fenXiType; // 分析类型 
	public GetScereenDataKey(GUID tenantId) {
		this.tenantId = tenantId;
	}
	/**
     * @return 租户GUID
     */
    public GUID getTenantsGuid(){
    	return tenantId;
    }
    /**
     * @return 分析类型 
     */
    public FenXiType getFenXiType(){
    	return fenXiType;
    }
	/**
     * @param fenXiType 分析类型 
     */
    public void setFenXiType(FenXiType fenXiType){
    	this.fenXiType = fenXiType;
    }
	/**
     * @return 部门GUID
     */
    public GUID getDeptGuid(){
    	return deptGuid;
    }
	/**
     * @param deptGuid 部门GUID
     */
    public void setDeptGuid(GUID deptGuid){
    	this.deptGuid = deptGuid;
    }
    /**
     * @return 员工GUID
     */
    public GUID getEmpGuid(){
    	return empGuid;
    }
	/**
     * @param empGuid 员工GUID
     */
    public void setEmpGuid(GUID empGuid){
    	this.empGuid = empGuid;
    }
}
