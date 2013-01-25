package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PropertyInputType;

/**
 * �������Զ������
 * 
 */
public interface PropertyDefine {

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
