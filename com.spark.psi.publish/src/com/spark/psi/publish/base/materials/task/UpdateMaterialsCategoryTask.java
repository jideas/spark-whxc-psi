package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.materials.entity.MaterialsPropertyDefine;
import com.spark.psi.publish.constant.GoodsScale;

/**
 * 更新材料分类
 * 
 */
public class UpdateMaterialsCategoryTask extends SimpleTask {

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
	 * 库存金额上限
	 */
	private double inventoryUpperLimit;

	/**
	 * 商品分类属性定义列表
	 */
	private MaterialsPropertyDefine[] propertyDefines;
	
	private String CategoryNo;

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
	 * @return the inventoryUpperLimit
	 */
	public double getInventoryUpperLimit() {
		return inventoryUpperLimit;
	}

	/**
	 * @param inventoryUpperLimit
	 *            the inventoryUpperLimit to set
	 */
	public void setInventoryUpperLimit(double inventoryUpperLimit) {
		this.inventoryUpperLimit = inventoryUpperLimit;
	}

	/**
	 * @return the propertyDefines
	 */
	public MaterialsPropertyDefine[] getPropertyDefines() {
		return propertyDefines;
	}

	/**
	 * @param propertyDefines
	 *            the propertyDefines to set
	 */
	public void setPropertyDefines(MaterialsPropertyDefine[] propertyDefines) {
		this.propertyDefines = propertyDefines;
	}

	public String getCategoryNo() {
		return CategoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		CategoryNo = categoryNo;
	}

}
