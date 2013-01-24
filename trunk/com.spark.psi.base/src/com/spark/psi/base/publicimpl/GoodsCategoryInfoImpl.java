package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryInfo;
import com.spark.psi.publish.base.goods.entity.PropertyDefine;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * 商品分类详细数据，包括属性信息 <br>
 * 查询说明：<br>
 * (1)根据分类ID查询GoodsCategoryInfo对象
 */
public class GoodsCategoryInfoImpl implements GoodsCategoryInfo {

	/**
	 * 分类ID
	 */
	protected GUID id;

	/**
	 * 分类名称
	 */
	protected String name;
	
	/**
	 * 分类编号
	 */
	protected String categoryNo;

	/**
	 * 数量小数位
	 */
	protected int scale; 

	/**
	 * 商品分类属性定义列表
	 */
	protected PropertyDefine[] propertyDefines;

	/**
	 * 获取分类ID
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * 获取分类名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}  

	/**
	 * 获取属性定义列表
	 * 
	 * @return
	 */
	public PropertyDefine[] getPropertyDefines() {
		return propertyDefines;
	}

	public void setId(GUID id){
    	this.id = id;
    }

	public void setName(String name){
    	this.name = name;
    } 

	public int getScale() {
		return GoodsScale.PSI_SCALE;
	}

	public void setScale(int scale) {
		this.scale = scale;
	} 

	public void setPropertyDefines(PropertyDefine[] propertyDefines){
    	this.propertyDefines = propertyDefines;
    }

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	
	
}
