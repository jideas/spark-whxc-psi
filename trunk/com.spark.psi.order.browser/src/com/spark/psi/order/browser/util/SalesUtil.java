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
public final class SalesUtil {
	private SalesUtil() {

	}

	/**
	 * 通过商品获得销售明细对象
	 * 
	 * @param Materials
	 * @return SalesOrderGoodsItem
	 */
	public static SalesOrderGoodsItem getSalesOrderGoodsItem(Context context, MaterialsItemInfo Materials,
			double count, double price) {
		SalesOrderGoodsItemImpl item = new SalesOrderGoodsItemImpl();
		item.setGoodsCode(Materials.getBaseInfo().getCode());
		item.setScale(Materials.getBaseInfo().getCategory().getScale());
		item.setGoodsItemId(Materials.getItemData().getId());
		item.setName(Materials.getBaseInfo().getName());
		item.setSpec(Materials.getItemData().getPropertiesWithoutUnit());
		item.setUnit(Materials.getItemData().getPropertyValues()[0]);
		item.setId(GUID.randomID());
		List<PromotionItem> promotions = context.getList(PromotionItem.class, Materials.getItemData().getId());
		item.setPromotionList(promotions.toArray(new PromotionItem[promotions.size()]));
		item.setGoodsItemPrice(Materials.getItemData().getSalePrice());

		// 设置正确的价格
		if (promotions.size() > 0) {
			if (price == 0) { // 如果指定价格为0，则试图寻找第一个促销价格
				item.setPromotionId(promotions.get(0).getId());
				item.setPrice(promotions.get(0).getPromotionPrice());
			} else { // 否则，试图匹配第一个相同价格的促销价格
				for (PromotionItem promotionItem : promotions) {
					if (promotionItem.getPromotionPrice() == price) {
						item.setPromotionId(promotionItem.getId());
						item.setPrice(promotionItem.getPromotionPrice());
						break;
					}
				}
				if (item.getPrice() == 0) { // 最后匹配不上，则使用指定价格
					item.setPrice(price);
				}
			}
		} else {
			if (price == 0) {
				item.setPrice(item.getGoodsItemPrice());
			} else {
				item.setPrice(price);
			}
		}
		if (count != 0) {
			item.setCount(count);
			item.setAmount(count * item.getPrice());
		}
		return item;
	}

	public static SalesOrderGoodsItem getSalesOrderGoodsItem(Context context, MaterialsItemInfo Materials) {
		return getSalesOrderGoodsItem(context, Materials, 0, 0);
	}

	/**
	 * 通过商品获得销售退货明细对象
	 * 
	 * @param Materials
	 * @return SalesOrderGoodsItem
	 */
	public static SalesReturnGoodsItem getSalesReturnMaterialsItem(MaterialsItemInfo Materials) {
		SalesReturnGoodsItemImpl item = new SalesReturnGoodsItemImpl();
		// item.setAmount(amount);
		item.setGoodsCode(Materials.getBaseInfo().getCode());
		item.setGoodsNo(Materials.getItemData().getMaterialNo());
		// item.setCount(count);
		item.setScale(Materials.getBaseInfo().getCategory().getScale());
		item.setGoodsItemId(Materials.getItemData().getId());
		item.setName(Materials.getBaseInfo().getName());
		// item.setPrice(price);
		item.setSpec(Materials.getItemData().getPropertiesWithoutUnit());
		// item.setSalesCause(purchaseCause);
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
