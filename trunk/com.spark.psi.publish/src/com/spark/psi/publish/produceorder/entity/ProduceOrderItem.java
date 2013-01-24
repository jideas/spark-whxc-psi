package com.spark.psi.publish.produceorder.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ProduceOrderStatus;

/**
 * ���������б�<BR>
 * ��ѯ������<BR>
 * GetProduceOrderListKey+ProduceOrderListEntity
 * 
 */
public interface ProduceOrderItem {

	public GUID getId();
	public String getBillsNo();
	public long getPlanDate();
	public double getGoodsCount();
	public String getRemark();
	public String getCreator();
	public long getCreateDate();
	public long getFinishDate();
	public ProduceOrderStatus getStatus();
}
