package com.spark.psi.base.key.materials;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>����⻧����Ʒ���������Ҷ�ӽڵ�</p>
 *


 *
 
 * @version 2012-3-28
 */
public class GetMaterialsCategoryLeafNodesKey{
	
	private GUID categoryId;
	
	private GUID tenantId;
	
	/**
	 * ��ѯָ����Ʒ���������Ҷ�ӽڵ�
	 * @param categoryId
	 */
	public GetMaterialsCategoryLeafNodesKey(GUID categoryId){
		this.categoryId = categoryId;
	}
	
	
	/**
	 * ��ѯ����Ҷ�ӽڵ����
	 */
	public GetMaterialsCategoryLeafNodesKey(GUID categoryId,GUID tenantId){
		this.categoryId = categoryId;
		this.tenantId = tenantId;
	}
	
	public GetMaterialsCategoryLeafNodesKey(){
	    // TODO Auto-generated constructor stub
    }

	public GUID getCategoryId(){
    	return categoryId;
    }

	public void setCategoryId(GUID categoryId){
    	this.categoryId = categoryId;
    }

	public GUID getTenantId(){
    	return tenantId;
    }

	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }

	
	
	
}
