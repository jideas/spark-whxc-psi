package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * ������Ʒ���ۼ�¼<BR>
 * ��ѯ������<BR>
 * GetOnlineSalesListKey+OnlineSalesListEntity;
 *
 */
public interface OnlineSalesItem {

	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsName();
	public String getGoodsSpec();
	public String getUnit();
	public double getCount();
	public double getAmount();
}
