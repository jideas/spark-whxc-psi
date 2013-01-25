package com.spark.order.intf.type;


/**
 * <p>
 * 页签枚举
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author 莫迪
 * @version 2011-10-21
 */
public enum PageEnum {
	// ----------主列表界面---------
	/**
	 * 待提交、已退回订单
	 */
	NEW("采购订货", "销售订货"),
	/**
	 * 新增采购退货
	 */
	NEW_CANCEL("采购退货", "销售退货"),
	/**
	 * 采购审批
	 */
	EXAMINE("采购审批", "销售审批"),
	/**
	 * 采购跟踪
	 */
	FOLLOW("采购跟踪", "销售跟踪"),
	/**
	 * 采购记录
	 */
	LOG("采购记录", "销售记录"),
	// -----------独立下单客户----------
	/**
	 * 网上订货
	 */
	ONE_NEW("网上订货"),
	/**
	 * 订货记录
	 */
	ONE_LOG("订货记录"),
	// -----------详情界面-----------
	/**
	 * 订单明细
	 */
	DETAIL("采购订单明细", "销售订单明细"),
//	/**
//	 * 订单明细无操作图标列
//	 */
//	DETAIL_1("采购订单明细", "销售订单明细"),
	// ------------采购订单其他相关页面----------
	/**
	 * 采购订单新建页面（商品清单）
	 */
	BUY_CREATE("采购订货"),
	/**
	 * 采购预警库存商品
	 */
	GOODS_STORE_WARNING("选择采购商品(从预警库存中)"),
	/**
	 * 采购全部商品
	 */
	GOODS_ALL("选择采购商品(从全部商品中)"),
	/**
	 * 采购订单查库
	 */
	BUY_LOOK_STORAGE("查库"),
	//-------------客户供应商----------------
	/**
	 * 客户供应商单据(未完交易和交易记录)
	 */
	BILLS_CUSP("供应商", "客户");

	private String[] name;

	private PageEnum(String... name) {
		this.name = name;
	}


	// /**
	// * 获取页签名称(采购)
	// *
	// * @return String
	// */
	// public String getName() {
	// return name[0];
	// }

	/**
	 * 获取页签名称
	 * 
	 * @param ord
	 * @return String
	 */
	public String getName(BillsEnum ord) {
		if (BillsEnum.SALE.equals(ord) && name.length > 1) {
			return name[1];
		}
		return name[0];
	}

}
