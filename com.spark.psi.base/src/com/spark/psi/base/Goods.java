package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.InventoryWarningType;

/**
 * 商品对象<br>
 * 查询方法：<br>
 * (1)根据ID查询Goods对象
 * (2)....
 */
public interface Goods {
	
	public GUID getId();
	public String getGoodsCode();
	public String getGoodsName();
	public String getNamePY();
	public GUID getCategoryId();
	public double getSalePrice();
	public boolean isJointVenture();
	public GUID getSupplierId();
	public String getRemark();
	public int getShelfLife();
	public int getWarningDay();
	public boolean isCanDelete();
	public boolean isRefFlag();
	public long getCreateDate();
	public long getLastModifyDate();
	public String getLastModifyPerson();
	public GUID getCreatorId();
	public String getCreator();
	public InventoryWarningType getInventoryWarningType();
	public GoodsStatus getStatus();
}
