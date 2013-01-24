package com.spark.oms.publish.product.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ProductItemIntf {
	/**
	 * 产品标识
	 */
	public GUID getId();
	/**
	 * 产品类别
	 */
	public String getCategory();
	/**
	 * 产品系列
	 */
	public String getSerial();
	/**
	 * 产品名称
	 */
	public String getName();
	/**
	 * 产品代码
	 */
	public String getCode();
	/**
	 * 短信提醒下线
	 */
	public double getRemindLine();
	/**
	 * 产品描述
	 */
	public String getRemark();
	/**
	 * 产品图片
	 */
	public byte[] getPicture();
	
	/**
	 * 产品状态
	 */
	public String getSaledstatus();
}
