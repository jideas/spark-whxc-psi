package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.goods.entity.PropertyDefine;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * 更新商品分类
 * 
 */
public class UpdateGoodsCategoryTask extends SimpleTask {

	/**
	 * 分类ID
	 */
	private GUID id;

	/**
	 * 分类名称
	 */
	private String name;

	/**
	 * 数量小数位
	 */
	private int amountDecimal;

	/**
	 * 商品分类属性定义列表
	 */
	private PropertyDefine[] propertyDefines;
	
	/**
	 * 商品分类编码
	 */
	private String categoryNo;

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the amountDecimal
	 */
	public int getScale() {
		return GoodsScale.PSI_SCALE;
	}

	/**
	 * @param amountDecimal
	 *            the amountDecimal to set
	 */
	public void setScale(int amountDecimal) {
		this.amountDecimal = amountDecimal;
	}


	/**
	 * @return the propertyDefines
	 */
	public PropertyDefine[] getPropertyDefines() {
		return propertyDefines;
	}

	/**
	 * @param propertyDefines
	 *            the propertyDefines to set
	 */
	public void setPropertyDefines(PropertyDefine[] propertyDefines) {
		this.propertyDefines = propertyDefines;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	

}
