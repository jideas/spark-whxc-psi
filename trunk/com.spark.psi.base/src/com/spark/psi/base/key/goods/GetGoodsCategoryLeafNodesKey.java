package com.spark.psi.base.key.goods;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>����⻧����Ʒ���������Ҷ�ӽڵ�</p>
 *


 *
 
 * @version 2012-3-28
 */
public class GetGoodsCategoryLeafNodesKey{
	
	private GUID categoryId;
	
	private GUID tenantId;
	
	/**
	 * ��ѯָ����Ʒ���������Ҷ�ӽڵ�
	 * @param categoryId
	 */
	public GetGoodsCategoryLeafNodesKey(GUID categoryId){
		this.categoryId = categoryId;
	}
	
	
	/**
	 * ��ѯ����Ҷ�ӽڵ����
	 */
	public GetGoodsCategoryLeafNodesKey(GUID categoryId,GUID tenantId){
		this.categoryId = categoryId;
		this.tenantId = tenantId;
	}
	
	public GetGoodsCategoryLeafNodesKey(){
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
