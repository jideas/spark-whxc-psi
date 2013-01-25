package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;

/**
 * ��Ʒ��Ϣ�б���Ŀ
 * 
 */
public interface GoodsInfoItem {

	public GUID getId();

	public String getCode();

	public String getName();

	public boolean isRef();

	public double getPrice();

	public GoodsStatus getStatus();

}
