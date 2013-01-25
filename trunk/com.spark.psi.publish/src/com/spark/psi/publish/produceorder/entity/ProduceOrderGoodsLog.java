package com.spark.psi.publish.produceorder.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * �������������Ʒ��¼<BR>
 * ��ѯ������<BR>
 * GetProduceOrderGoodsLogListKey+ProduceOrderGoodsLogListEntity;
 */
public interface ProduceOrderGoodsLog {

	public GUID getId();
	public GUID getBillsId();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsName();
	public String getGoodsSpec();
	public String getUnit();
	public int getGoodsScale();
	public double getCount();
	public GUID getBomId();
	public double getThistimeCount();
	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();
}
