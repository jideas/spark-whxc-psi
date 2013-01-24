package com.spark.psi.base.internal.entity;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.key.goods.GetChildrenGoodsCategoryListKey;
import com.spark.psi.base.key.goods.GetGoodsCategoryLeafNodesKey;

/**
 * 商品分类
 
 *
 */
@StructClass
public class GoodsCategoryImpl implements GoodsCategory {
	
	/**
	 * 分类ID
	 */
	protected GUID id;

	/**
	 * 分类名称
	 */
	protected String name;

	/**
	 * 数量小数位
	 */
	protected int countDecimal;

	/**
	 * 库存金额上限
	 */
	protected String categoryNo;
	
	/**
	 * 是否有效
	 */
	protected boolean valid;

	/**
	 * 是否是叶子节点
	 */
	protected boolean leafNode;	
	
	/**
	 * 是否已关联
	 */
	protected boolean reflag;
	/**
	 * 是否已设置属性
	 */
	protected boolean propertyFlag;

	
	/**
	 * 租户id
	 */
	protected GUID tenantId;
	
	/**
	 * 父节点
	 */
	protected GUID parentId;
	
	/**
	 * 级次路径
	 */
	protected String path;
	
	protected long createDate;
	
	
	
	public GoodsCategoryImpl(){
	}
	
	public GoodsCategoryImpl(GUID id,String name){
		this.id = id;
		this.name = name;
	}

	/**
	 * 获得商品条目列表<br>
	 * 包含子分类的商品条目
	 * @param context
	 * @return GoodsItem[]
	 */
	public GoodsItem[] getGoodsItems(final Context context){
		List<GoodsItem> items = context.getList(GoodsItem.class,this.getId());
		return items.toArray(new GoodsItem[items.size()]);
	}	
	
	
	
	public long getCreateDate(){
    	return createDate;
    }

	public void setCreateDate(long createDate){
    	this.createDate = createDate;
    }

	public boolean isPropertyFlag(){
    	return propertyFlag;
    }

	public void setPropertyFlag(boolean propertyFlag){
    	this.propertyFlag = propertyFlag;
    }

	public boolean isReflag(){
    	return reflag;
    }

	public void setReflag(boolean reflag){
    	this.reflag = reflag;
    }

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScale() {
		return countDecimal;
	}

	public void setCountDecimal(int countDecimal) {
		this.countDecimal = countDecimal;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public boolean isLeafNode() {
		return leafNode;
	}

	public void setLeafNode(boolean leafNode) {
		this.leafNode = leafNode;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public GoodsCategory[] getChildren(final Context context){
	   List<GoodsCategory> list = context.getList(GoodsCategory.class,new GetChildrenGoodsCategoryListKey(this.id));
	   return list.toArray(new GoodsCategory[list.size()])
	   
	   ;
	}
	
	public GoodsCategory[] getLeafNodes(final Context context){
	   List<GoodsCategory> list = context.getList(GoodsCategory.class,new GetGoodsCategoryLeafNodesKey(this.id));
	   return list.toArray(new GoodsCategory[0]);		
	}
	
	public GoodsCategory getParent(final Context context){
		return context.find(GoodsCategory.class,this.getParent());
	}

	public GUID getTenantId(){
    	return tenantId;
    }

	public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }

	public GUID getParentId(){
    	return parentId;
    }

	public void setParentId(GUID parentId){
    	this.parentId = parentId;
    }

	public String getPath(){
    	return path;
    }

	public void setPath(String path){
    	this.path = path;
    }

	@Override
	public boolean equals(Object target){
		if(!(target instanceof GoodsCategory))return false;
		GoodsCategory category = (GoodsCategory)target;
		return this.getId().equals(category.getId());
	}

	public GUID getParent(){
	    return this.parentId;
    }
	
}
