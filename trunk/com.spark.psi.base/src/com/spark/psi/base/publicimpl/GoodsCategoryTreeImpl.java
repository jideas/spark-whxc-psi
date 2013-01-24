package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;

public class GoodsCategoryTreeImpl implements GoodsCategoryTree{
	/**
	 * 设置了属性的分类数量
	 */
	private int propertiedCount;

	/**
	 * 所有商品分类数量
	 */
	private int count;

	/**
	 * 根节点
	 */
	private CategoryNode[] rootNodes;

	/**
	 * 构造函数
	 * 
	 * @param rootNodes
	 */
	public GoodsCategoryTreeImpl(CategoryNode[] rootNodes) {
		this.rootNodes = rootNodes;
	}

	/**
	 * 获取设置了属性的分类数量
	 * 
	 * @return
	 */
	public int getPropertiedCount() {
		return propertiedCount;
	}

	/**
	 * 获取所有分类数量
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public CategoryNode[] getRootNodes() {
		return this.rootNodes;
	}

	public void setPropertiedCount(int propertiedCount){
    	this.propertiedCount = propertiedCount;
    }

	public void setCount(int count){
    	this.count = count;
    }

	public void setRootNodes(CategoryNode[] rootNodes){
    	this.rootNodes = rootNodes;
    }

	public CategoryNode getNodeById(GUID id){
	    for(CategoryNode node : rootNodes){
	        if(node.getId().equals(id)){
	        	return node;
	        }
	        CategoryNode child = getNodeById(id, node.getChildren());
	        if(child!=null)return child;
        }
	    return null;
    }
	
	private CategoryNode getNodeById(GUID id,CategoryNode[] nodes){
		if(null == nodes) return null;
		for(CategoryNode categoryNode : nodes){
	        if(categoryNode.getId().equals(id)){
	        	return categoryNode;
	        }
	        CategoryNode node = getNodeById(id, categoryNode.getChildren());
	        if(node!=null){
	        	return node; 
	        }
        }
		return null;
	}
	
	

}
