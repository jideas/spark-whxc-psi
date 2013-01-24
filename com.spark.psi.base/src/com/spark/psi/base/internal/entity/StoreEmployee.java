/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.store.entity
 * �޸ļ�¼��
 * ����                ����           ����
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
		/**  ����Ա */
		STOREMANAGER("0"),
		/**  ���� */
		SALER("1"),
		/**  �ɹ� */
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
