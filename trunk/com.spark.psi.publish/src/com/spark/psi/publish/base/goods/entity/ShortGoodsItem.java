package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��С����Ʒ��Ŀ������
 */
public interface ShortGoodsItem {

	public String getBomStatus();
	public GUID getId();

	public String getName() ;

	public String getGoodsCode() ;

	public String getGoodsNo();

	public String getGoodsSpec() ;

	public String getGoodsUnit() ;

	public double getSalesPrice() ;

	public double getOriginalPrice();

	public GUID getBomId();
}
