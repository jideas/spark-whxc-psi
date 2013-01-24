/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.store.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       zhongxin        
 * ============================================================*/

package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * @author zhongxin
 *
 */
public class StoreEmployee {
	
	public enum StoreEmployeeType {
		/**  管理员 */
		STOREMANAGER("0"),
		/**  销售 */
		SALER("1"),
		/**  采购 */
		PURCHASE("2");
		
		private String value;
		private StoreEmployeeType(String value) {
			this.value = value;
		}
		
		public String getCode() {
			return this.value;
		}
	}
	
	private GUID recid;
	private GUID storeGuid;
	private String employeeType;
	private GUID employeeGuid;
	private GUID tenantId;
	
	
	
	public GUID getTenantId(){
    	return tenantId;
    }
	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }
	public GUID getRecid(){
    	return recid;
    }
	public void setRecid(GUID recid){
    	this.recid = recid;
    }
	public GUID getStoreGuid(){
    	return storeGuid;
    }
	public void setStoreGuid(GUID storeGuid){
    	this.storeGuid = storeGuid;
    }
	public String getEmployeeType(){
    	return employeeType;
    }
	public void setEmployeeType(String employeeType){
    	this.employeeType = employeeType;
    }
	public GUID getEmployeeGuid(){
    	return employeeGuid;
    }
	public void setEmployeeGuid(GUID employeeGuid){
    	this.employeeGuid = employeeGuid;
    }
	
	
	
}
