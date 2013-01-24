package com.spark.psi.base.internal.entity.helper;

import com.jiuqi.dna.core.Context;
import com.spark.psi.base.Goods;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Partner;
import com.spark.psi.base.publicimpl.GoodsCategoryInfoImpl;
import com.spark.psi.base.publicimpl.GoodsInfoImpl;
import com.spark.psi.base.publicimpl.GoodsItemDataImpl;

public class GoodsHelper{
	
	public static GoodsItemDataImpl goodsItemToItemData(GoodsItem item){
		if(item==null)return null;
		GoodsItemDataImpl entity = new GoodsItemDataImpl();
		entity.setId(item.getId());
//		entity.setAveragePurchasePrice(item.getAveragePurchasePrice());
//		entity.setInventoryUpperLimit(item.getInventoryAmountUpperLimit());
//		entity.setPropertiesWithoutUnit(item.getPropertiesWithoutUnit());
//		entity.setPropertiesWithUnit(item.getPropertiesWithUnit());
		entity.setPropertyValues(item.getGoodsProperties());
//		entity.setRecentPurchasePrice(item.getRecentPurchasePrice());
		entity.setRefFlag(item.isRefFlag());
		entity.setSalePrice(item.getSalePrice());
		entity.setStatus(item.getStatus());
		entity.setCountDecimal(item.getScale());
		entity.setOriginalPrice(item.getOriginalPrice());
		entity.setSpec(item.getSpec());
		entity.setGoodsItemNo(item.getGoodsNo());
		entity.setLossRate(item.getLossRate());
		entity.setBomId(item.getBomId());
		entity.setStandardCost(item.getStandardCost());
		entity.setAverageCost(item.getAvgCost());
		entity.setSerialNumber(item.getSerialNumber());
//		entity.setTotalStoreLowerLimit(item.getTotalStoreLowerLimit());
//		entity.setTotalStoreUpperLimit(item.getTotalStoreUpperLimit());
		return entity;
	}
	
	public static GoodsInfoImpl goodsToGoodsInfo(Context context, Goods goods){
		if(goods==null)return null;
		GoodsInfoImpl entity = new GoodsInfoImpl();
		entity.setCode(goods.getGoodsCode());
		entity.setId(goods.getId());
		entity.setMemo(goods.getRemark());
		entity.setName(goods.getGoodsName());
		entity.setRefFlag(goods.isRefFlag());
		entity.setSalePrice(goods.getSalePrice());
		entity.setStatus(goods.getStatus());
		entity.setGoodsWarnningType(goods.getInventoryWarningType());
		entity.setShelfLife(goods.getShelfLife());
		entity.setWarningDay(goods.getWarningDay());
//		entity.setJointVenture(goods.isJointVenture());
//		if(goods.isJointVenture()&&null!=goods.getSupplierId())
//		{
//			entity.setSupplierId(goods.getSupplierId());
//			Partner partner = context.find(Partner.class,goods.getSupplierId());
//			if(null!=partner)
//			entity.setSupplier(partner.getShortName());
//		}
		
		return entity;
	}
	
	public static GoodsCategoryInfoImpl goodsCategoryToGoodsCategoryInfoImpl(GoodsCategory category){
		if(category==null)return null;
		GoodsCategoryInfoImpl entity = new GoodsCategoryInfoImpl();
		entity.setCategoryNo(category.getCategoryNo());
		entity.setScale(category.getScale());
		entity.setId(category.getId()); 
		entity.setName(category.getName());
		return entity;
	}
	
}
