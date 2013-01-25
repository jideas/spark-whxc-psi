package com.spark.psi.order.browser.util;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.order.browser.internal.PurchaseOrderGoodsItemImpl;
import com.spark.psi.order.browser.internal.PurchaseReturnGoodsItemImpl;
import com.spark.psi.order.browser.util.IPurchaseGoodsGroup.PurchaseGroupGoods;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.order.entity.PurchaseOrderGoodsItem;
import com.spark.psi.publish.order.entity.PurchaseReturnGoodsItem;
import com.spark.psi.publish.order.key.GetPurchasingGoodsCauseKey;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderTask;

/**
 * <p>�ɹ�������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-11
 */
public final class PurchaseUtil {
	private PurchaseUtil(){
		
	}
	/**
	 * ͨ����Ʒ��òɹ���ϸ����
	 * @param goods
	 * @return PurchaseOrderGoodsItem
	 */
	public static PurchaseOrderGoodsItem getPurchaseOrderGoodsItem(GoodsItemInfo goods){
		PurchaseOrderGoodsItemImpl item = new PurchaseOrderGoodsItemImpl();
//		item.setAmount(amount);
		item.setGoodsCode(goods.getBaseInfo().getCode());
//		item.setCount(count);
		item.setScale(goods.getBaseInfo().getCategory().getScale());
		item.setGoodsItemId(goods.getItemData().getId());
		item.setName(goods.getBaseInfo().getName());
		item.setGoodsNo(goods.getItemData().getGoodsItemNo());
//		item.setPrice(price);
		item.setSpec(goods.getItemData().getPropertiesWithoutUnit());
//		item.setPurchaseCause(purchaseCause);
		item.setUnit(goods.getItemData().getPropertyValues()[0]);
		item.setId(GUID.randomID());
		return item;
	}
	/**
	 * ͨ����Ʒ��òɹ���ϸ����
	 * @param goods
	 * @return PurchaseOrderGoodsItem
	 */
	public static PurchaseReturnGoodsItem getPurchaseReturnGoodsItem(MaterialsItemInfo goods){
		PurchaseReturnGoodsItemImpl item = new PurchaseReturnGoodsItemImpl();
//		item.setAmount(amount);
		item.setGoodsCode(goods.getBaseInfo().getCode());
//		item.setCount(count);
		item.setScale(goods.getBaseInfo().getCategory().getScale());
		item.setGoodsItemId(goods.getItemData().getId());
		item.setName(goods.getBaseInfo().getName());
		item.setPrice(goods.getItemData().getRecentPurchasePrice());
		item.setSpec(goods.getItemData().getPropertiesWithoutUnit());
		item.setGoodsNo(goods.getItemData().getMaterialNo());
//		item.setPurchaseCause(purchaseCause);
		item.setUnit(goods.getItemData().getPropertyValues()[0]);
		item.setId(GUID.randomID());
		return item;
	}
	
	//======================���ɲɹ�����=======================
	/**
	 * ���ɲɹ�����ʱ���ɹ�������ϸ
	 * @param context
	 * @param goods
	 * @return UpdatePurchaseOrderTask.PurchaseOrderGoodsItem
	 */
	public static UpdatePurchaseOrderTask.PurchaseOrderGoodsItem getGoodsItem(Context context, PurchaseGroupGoods goods){
		UpdatePurchaseOrderTask.PurchaseOrderGoodsItem item = new UpdatePurchaseOrderTask.PurchaseOrderGoodsItem();
		item.setAmount(goods.getCount()*goods.getPrice());
		item.setCount(goods.getCount());
		item.setPrice(goods.getPrice());
		item.setGoodsItemId(goods.getGoodsItemId());
		item.setPurchaseGoodsItemId(goods.getPurchaseGoodsId());
		item.setPurchaseCause(context.find(String.class,
				new GetPurchasingGoodsCauseKey(goods.getPurchaseGoodsId())));
		return item;
	}
	
	//==================�༭��ϸ����=======================
}
