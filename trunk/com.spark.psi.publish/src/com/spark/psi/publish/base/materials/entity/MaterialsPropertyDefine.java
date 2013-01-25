package com.spark.psi.publish.base.materials.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PropertyInputType;

/**
 * 分类属性定义对象
 * 
 */
public interface MaterialsPropertyDefine {

	/**
	 * 
	 * @return
	 */
	public GUID getId();
	/**
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 
	 * @return
	 */
	public PropertyInputType getValueInputMode();

	/**
	 * 
	 * @return
	 */
	public String[] getOptions();

}
