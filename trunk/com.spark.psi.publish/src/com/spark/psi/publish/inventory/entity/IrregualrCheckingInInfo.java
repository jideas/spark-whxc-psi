package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.order.entity.OrderGoodsItem;

/**
 * 零采入库单
 * ok
 *
 */
public interface IrregualrCheckingInInfo {
	
	/**
	 * 获得入库单ID
	 * @return
	 */
	public GUID getId();
	
	/**
	 * 获得入库单编号
	 * @return
	 */
	public String getSheetNo(); 
	
	/**
	 * 获取入库类型
	 */
	public CheckingInType getType();
	
	/**
	 * 获取入库仓库名称
	 */
	public String getStoreName();
	
	/**
	 * 获取供应商名称
	 */
	public String getSupplierName();
	
	/**
	 * 获取采购人名称
	 * @return
	 */
	public String getBuyPerson();
	
	/**
	 * 获取采购日期
	 * @return
	 */
	public long getBuyDate();
	
	/**
	 * 获得备注
	 * @return
	 */
	public String getRemark();
	
	/**
	 * 获得入库金额
	 * @return
	 */
	public double getCheckingInAmount();
	
	/**
	 * 获取确认入库人名称
	 * @return
	 */
	public String getCheckInPersonName();
	
	/**
	 * 获得确认入库日期
	 * @return
	 */
	public long getCheckingInDate();
	
	/**
	 * 获取入库单状态
	 */
	public CheckingInStatus getStatus();
	
	/**
	 * 获得零采商品明细
	 * @return
	 */
	public IrregualrGoodsItem[] getGoodsItems();
	
	public interface IrregualrGoodsItem extends OrderGoodsItem {
		
	}
}
