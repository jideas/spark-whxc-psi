package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�������ṹʵ��</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-5-17
 */
public class LevelTree {
	
	/**
	 * ��Ҷ�ӽڵ�
	 */
	public static final String STAUTS_PARENT = "0";
	/**
	 * Ҷ�ӽڵ�
	 */
	public static final String STAUTS_CHILD = "1";
	
	
	
	private byte[] path;
		
	private GUID RECID;
	
	private String stauts;

	private GUID tenantId;
	
	public GUID getTenantId(){
		return tenantId;
	}
	
	public void setTenantId(GUID tenantId){
		this.tenantId = tenantId;
	}
	
	
	public byte[] getPath(){
    	return path;
    }

	public void setPath(byte[] path){
    	this.path = path;
    }

	public GUID getRECID(){
    	return RECID;
    }

	public void setRECID(GUID rECID){
    	RECID = rECID;
    }

	public String getStauts(){
    	return stauts;
    }

	public void setStauts(String stauts){
    	this.stauts = stauts;
    }
	
}
