package com.spark.oms.publish.product.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 获取产品附加功能
 *
 */
public interface ProductAddFunction {
	/**
	 * 获得功能ID
	 * @return
	 */
	public GUID getID();
	
	/**
	 * 获得与产品相关联的
	 * @return
	 */
	public GUID getProductItemRECID();
	/**
	 * 增值功能名称
	 * @return
	 */
	public String getName();
	/**
	 * 增值功能代码
	 * @return
	 */
	public String getCode();
	/**
	 * 增值功能单价
	 * @return
	 */
	public double getPrice();
	/**
	 * 增值功能收费单位
	 * @return
	 */
	public String getUnit();
	

}
