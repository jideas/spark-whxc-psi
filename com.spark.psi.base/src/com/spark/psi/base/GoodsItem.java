package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
//import com.spark.psi.publish.InventoryWarningType;

public interface GoodsItem {

	public GUID getId();
	public String getGoodsCode();
	public String getGoodsNo();
	public String getGoodsName();
	public String getNamePY();
	public GUID getCategoryId();
	public GoodsCategory getCategory();
	public String getSpec();
	public int getScale();
	public boolean isNeedProduce();
//	public boolean isJointVenture();
	public String getInventoryStrategy();
	public String getGoodsUnit();
	public double getAvgCost();
	public double getStandardCost();
	public double getAssessCost();
	public int getShelfLife() ;
	public int getWarningDay() ;
	public double getSalePrice();
	public double getOriginalPrice();
	public double getLossRate();
	public GoodsStatus getStatus();
	public boolean isCanDelete();
	public boolean isRefFlag() ;
	public long getCreateDate();
	public long getLastModifyDate() ;
	public String getLastModifyPerson();
	public String[] getGoodsProperties();
	public GUID getBomId();
	public GUID getCreatorId();
	public String getCreator();
	public GUID getGoodsId();
	public Goods getGoods();
	public String getPropertiesWithoutUnit();
	public String getSerialNumber();
	public double getHalfkgPrice();
	
	
	/**
	 * ���Ԥ������
	 * 
	 * @return GoodsWarnningType
	 */
//	public InventoryWarningType getGoodsWarnningType();
	/**
	 * �ܿ����������
	 * 
	 * @return double
	 */
//	public double getTotalStoreUpperLimit();
	/**
	 * �ܿ����������
	 * 
	 * @return double
	 */
//	public double getTotalStoreLowerLimit();
	
	/**
	 * @return the salePrice
	 */

	/**
	 * ����ɹ�����
	 */
//	public double getRecentPurchasePrice();
}
