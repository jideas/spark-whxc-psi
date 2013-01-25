package com.spark.psi.publish.base.materials.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.InventoryWarningType;

/**
 * 材料详细信息数据（包括所属分类信息和其所有明细条目的信息） <BR>
 * 查询说明：<br>
 * (1)根据材料ID查询，返回MaterialsInfo<br>
 * (2)根据GetMaterialsInfoListKey查询，返回MaterialsInfo列表
 */
public interface MaterialsInfo {

	/**
	 * 获取商品ID
	 * 
	 * @return
	 */
	public GUID getId();
	
	
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
	public MaterialsStatus getStatus();

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
	public MaterialsCategoryInfo getCategory();

	/**
	 * 获取所有条目
	 * 
	 * @return
	 */
	public MaterialsItemData[] getItems();
	
	/**
	 * 商品库存预警类型
	 *  按金额预警
	 *  按数量预警
	 * @return MaterialsWarnningType
	 */
	public InventoryWarningType getMaterialsWarnningType();
	
	public GUID getSupplierId();
	public String getSupplier();
	public double getPercentage();
	public boolean isJointVenture();

}
