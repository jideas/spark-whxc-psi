package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.InventoryWarningType;

/**
 * 商品详细信息数据（包括所属分类信息和其所有明细条目的信息） <BR>
 * 查询说明：<br>
 * (1)根据商品ID查询，返回GoodsInfo<br>
 * (2)根据GetGoodsInfoListKey查询，返回GoodsInfo列表
 */
public interface GoodsInfo {

	/**
	 * 获取商品ID
	 * 
	 * @return
	 */
	public GUID getId();

	/**
	 * @return 是否联营商品
	 */
//	public boolean isJointVenture();

	/**
	 * @return 联营供应商
	 */
//	public GUID getSupplierId();
	
	/**
	 * 
	 * @return 联营供应商名称
	 */
//	public String getSupplier();

	/**
	 * @return 保质期
	 */
	public int getShelfLife();

	/**
	 * @return 保质期预警日期
	 */
	public int getWarningDay();

	/**
	 * 获取商品代码
	 * 
	 * @return
	 */
	public String getCode();

	/**
	 * 获取商品名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 获取统一销售价格
	 * 
	 * @return
	 */
	public double getSalePrice();

	/**
	 * 获取商品状态
	 * 
	 * @return
	 */
	public GoodsStatus getStatus();

	/**
	 * 获取备注信息
	 * 
	 * @return
	 */
	public String getRemark();

	/**
	 * 是否已经被引用的标志
	 * 
	 * @return
	 */
	public boolean isRefFlag();

	/**
	 * 获取商品分类
	 * 
	 * @return
	 */
	public GoodsCategoryInfo getCategory();

	/**
	 * 获取所有条目
	 * 
	 * @return
	 */
	public GoodsItemData[] getItems();

	/**
	 * 商品库存预警类型 按金额预警 按数量预警
	 * 
	 * @return GoodsWarnningType
	 */
	public InventoryWarningType getGoodsWarnningType();

}
