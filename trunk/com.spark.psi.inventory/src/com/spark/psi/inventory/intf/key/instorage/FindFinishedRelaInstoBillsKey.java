/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-22       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO ������</p>
 *


 *
 * @author ��־��
 * @version 2011-11-22
 */

public class FindFinishedRelaInstoBillsKey{

	private  GUID  cusProGuid;
	
	private String type;
	
	public FindFinishedRelaInstoBillsKey(String type,GUID  cusProGuid){
		this.type = type;
		this.cusProGuid = cusProGuid;
	}

	/**
     * @return the type
     */
    public String getType(){
    	return type;
    }

	/**
     * @param type the type to set
     */
    public void setType(String type){
    	this.type = type;
    }

	/**
     * @return the cusProGuid
     */
    public GUID getCusProGuid(){
    	return cusProGuid;
    }

	/**
     * @param cusProGuid the cusProGuid to set
     */
    public void setCusProGuid(GUID cusProGuid){
    	this.cusProGuid = cusProGuid;
    } 
}
