package com.spark.psi.publish.onlineorder.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ������Ʒ������Ϣ����ѯ������ϻ�����Ϣ<BR>
 * ��ѯ������<BR>
 * List<TotalMaterialsItem> + GetTotalMaterialsKey
 *
 */
public interface TotalMaterialsItem {
	
	public GoodsItem[] getGoods();

	public MaterialsItem[] getMaterials();
	
	public interface GoodsItem
	{
		public GUID getGoodsId();
		public String getGoodsCode();
		public String getGoodsNo();
		public String getGoodsName();
		public String getGoodsSpec();
		public String getUnit();
		public int getGoodsScale();
		public double getCount();
		public GUID getBomId();
		//TODO
//		public double getLockCount();
	}
	
	public interface MaterialsItem
	{
		public GUID getMaterialId();
		public String getMaterialCode();
		public String getMaterialNo();
		public String getMaterialName();
		public String getMaterialSpec();
		public String getUnit();
		public int getMaterialScale();
		public double getCount();
	}
}
