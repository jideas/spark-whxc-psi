package com.spark.psi.order.browser.util;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.order.browser.internal.SalesOrderGoodsItemImpl;
import com.spark.psi.order.browser.internal.SalesReturnGoodsItemImpl;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.order.entity.PromotionItem;
import com.spark.psi.publish.order.entity.SalesOrderGoodsItem;
import com.spark.psi.publish.order.entity.SalesReturnGoodsItem;

/**
 * <p>
 * 采购工具类
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author modi
 * @version 2012-4-11
 */
public final class SalesMaterialsUtil {
	private SalesMaterialsUtil() {

	}

	/**
	 * 通过商品获得销售明细对象
	 * 
	 * @param Materials
	 * @return SalesOrderMaterialsItem
	 */
	public static SalesOrderGoodsItem getSalesOrderMaterialsItem(Context context, MaterialsItemInfo Materials,
			double count, double price) {
		SalesOrderGoodsItemImpl item = new SalesOrderGoodsItemImpl();
		item.setGoodsCode(Materials.getBaseInfo().getCode());
		item.setScale(Materials.getBaseInfo().getCategory().getScale());
		item.setGoodsItemId(Materials.getItemData().getId());
		item.setName(Materials.getBaseInfo().getName());
		item.setGoodsNo(Materials.getItemData().getMaterialNo());
		item.setSpec(Materials.getItemData().getMaterialSpec());
		item.setUnit(Materials.getItemData().getUnit());
		item.setId(Materials.getItemData().getId());
		List<PromotionItem> promotions = context.getList(PromotionItem.class, Materials.getItemData().getId());
		item.setPromotionList(promotions.toArray(new PromotionItem[promotions.size()]));
		item.setBuyAvgPrice(Materials.getItemData().getAvgBuyPrice());
	 
		if (count != 0) {
			item.setCount(count);
			item.setAmount(0);
		}
		return item;
	}

	public static SalesOrderGoodsItem getSalesOrderMaterialsItem(Context context, MaterialsItemInfo Materials) {
		return getSalesOrderMaterialsItem(context, Materials, 0, 0);
	}

	/**
	 * 通过商品获得销售退货明细对象
	 * 
	 * @param Materials
	 * @return SalesOrderMaterialsItem
	 */
	public static SalesReturnGoodsItem getSalesReturnMaterialsItem(MaterialsItemInfo Materials) {
		SalesReturnGoodsItemImpl item = new SalesReturnGoodsItemImpl();
		item.setGoodsCode(Materials.getBaseInfo().getCode());
		item.setScale(Materials.getBaseInfo().getCategory().getScale());
		item.setGoodsItemId(Materials.getItemData().getId());
		item.setName(Materials.getBaseInfo().getName());
		item.setSpec(Materials.getItemData().getPropertiesWithoutUnit());
		item.setUnit(Materials.getItemData().getPropertyValues()[0]);
		item.setId(GUID.randomID());
		return item;
	}

	/**
	 * 当前用户是否是员工
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isEmployee(Context context) {
		LoginInfo login = context.find(LoginInfo.class);
		if (!login.hasAuth(Auth.Boss, Auth.SalesManager)) {
			return true;
		}
		return false;
	}
}
