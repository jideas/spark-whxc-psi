package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;

/**
 * 商品明细条目数据对象<br>
 * 查询方法：无
 */
public interface GoodsItemData {

	/**
	 * id
	 * @return
	 */
	public GUID getId();
	public GUID getBomId();

	/**
	 * 单价
	 * @return
	 */

	public double getSalePrice();
	
	/**
	 * 
	 * @return 原价
	 */
	public double getOriginalPrice();
	
	/**
	 * 
	 * @return 条码
	 */
	public String getGoodsItemNo();
	
	/**
	 * 
	 * @return 规格
	 */
	public String getSpec();
	
	/**
	 * 
	 * @return 损耗率
	 */
	public double getLossRate();

	/**
	 * 
	 * 标准成本
	 */
	public double getStandardCost();

	/**
	 * 平均库存成本
	 * @return
	 */
	public double getAverageCost();

	/**
	 * 属性
	 * @return
	 */
	public String[] getPropertyValues();

	/**
	 * 不带单位的属性值
	 * @return
	 */
	public String getPropertiesWithoutUnit();

	/**
	 * 带单位的属性值
	 * @return
	 */
	public String getPropertiesWithUnit();

	/**
	 * 库存上限
	 * @return
	 */
	public double getInventoryUpperLimit();
	
	/**
	 * 总库存上限数量
	 * 
	 * @return double
	 */
	public double getTotalStoreUpperLimit();
	/**
	 * 总库存下限数量
	 * 
	 * @return double
	 */
	public double getTotalStoreLowerLimit();

	/**
	 * 状态
	 * @return
	 */
	public GoodsStatus getStatus();
	/**
	 * 是否已关联
	 * @return
	 */
	public boolean isRefFlag();
	
	/**
	 * 商品数量小数位数
	 * 
	 * @return int
	 */
	public int getScale();
	
	/**
	 * 商品单位
	 * 
	 * @return String
	 */
	public String getUnit();
	
	public String getSerialNumber();
	
	public double getHalfkgPrice();
}
