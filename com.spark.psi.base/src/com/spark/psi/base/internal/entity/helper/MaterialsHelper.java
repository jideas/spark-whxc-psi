package com.spark.psi.base.internal.entity.helper;

import com.jiuqi.dna.core.Context;
import com.spark.psi.base.Materials;
import com.spark.psi.base.MaterialsCategory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Partner;
import com.spark.psi.base.publicimpl.MaterialsCategoryInfoImpl;
import com.spark.psi.base.publicimpl.MaterialsInfoImpl;
import com.spark.psi.base.publicimpl.MaterialsItemDataImpl;
import com.spark.psi.base.utils.MaterialsProperyUtil;

public class MaterialsHelper{
	
	public static MaterialsItemDataImpl materialsItemToItemData(MaterialsItem item){
		if(item==null)return null;
		MaterialsItemDataImpl entity = new MaterialsItemDataImpl();
		entity.setId(item.getId());
		entity.setAvgBuyPrice(item.getAvgBuyPrice());
		entity.setInventoryUpperLimit(item.getTotalStoreAmount());
		entity.setPropertiesWithoutUnit(MaterialsProperyUtil.splitPropertyWithoutUnit(MaterialsProperyUtil.subMaterialsPropertyToString(item.getMaterialProperties())));
		entity.setPropertiesWithUnit(MaterialsProperyUtil.subMaterialsPropertyToString(item.getMaterialProperties()));
		entity.setPropertyValues(item.getMaterialProperties());
		entity.setRecentPurchasePrice(item.getRecentPurchasePrice());
		entity.setRefFlag(item.isRefFlag());
		entity.setSalePrice(item.getSalePrice());
		entity.setStatus(item.getStatus());
		entity.setCountDecimal(item.getScale());
		entity.setMaterialSpec(item.getSpec());
		entity.setTotalStoreLowerLimit(item.getTotalStoreFlore());
		entity.setTotalStoreUpperLimit(item.getTotalStoreUpper());
		entity.setLossRate(item.getLossRate());
		entity.setPlanPrice(item.getPlanPrice());
		entity.setStandardPrice(item.getStandardPrice());
		entity.setMaterialNo(item.getMaterialNo());
		entity.setInventoryStrategy(item.getInventoryStrategy());
		return entity;
	}
	
	public static MaterialsInfoImpl materialsToMaterialsInfo(Context context, Materials materials){
		if(materials==null)return null;
		MaterialsInfoImpl entity = new MaterialsInfoImpl();
		entity.setCode(materials.getMaterialCode());
		entity.setId(materials.getId());
		entity.setMemo(materials.getRemark());
		entity.setName(materials.getMaterialName());
		entity.setRefFlag(materials.isRefFlag());
//		entity.setSalePrice(materials.getSalePrice());
		entity.setStatus(materials.getStatus());
		entity.setMaterialsWarnningType(materials.getInventoryWarningType());
		entity.setShelfLife(materials.getShelfLife());
		entity.setWarningDay(materials.getWarningDay());
		entity.setMemo(materials.getRemark());
		entity.setMaterialsWarnningType(materials.getInventoryWarningType());
		entity.setJointVenture(materials.isJointVenture());
		entity.setSupplierId(materials.getSupplierId());
		entity.setPercentage(materials.getPercentage());
		if (null != materials.getSupplierId()) {
			Partner supplierInfo = context.find(Partner.class, materials.getSupplierId());
			entity.setSupplier(supplierInfo.getShortName());
		}
		return entity;
	}
	
	public static MaterialsCategoryInfoImpl materialsCategoryTomaterialsCategoryInfoImpl(MaterialsCategory category){
		if(category==null)return null;
		MaterialsCategoryInfoImpl entity = new MaterialsCategoryInfoImpl();
		entity.setCategoryNo(category.getCategoryNo());
		entity.setScale(category.getScale());
		entity.setId(category.getId()); 
		entity.setName(category.getName());
		return entity;
	}
	
}
