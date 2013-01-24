package com.spark.psi.base.key.goods;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得租户的商品分类的所有叶子节点</p>
 *


 *
 
 * @version 2012-3-28
 */
public class GetGoodsCategoryLeafNodesKey{
	
	private GUID categoryId;
	
	private GUID tenantId;
	
	/**
	 * 查询指定商品分类的所有叶子节点
	 * @param categoryId
	 */
	public GetGoodsCategoryLeafNodesKey(GUID categoryId){
		this.categoryId = categoryId;
	}
	
	
	/**
	 * 查询所有叶子节点分类
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
