package com.spark.psi.publish.base.partner.entity;


/**
 * 
 * <p>供应商列表</p>
 * 
 * 供应商维护主列表查询供应商列表 ListEntity<SupplierItem>+com.spark.psi.publish.partner.key.GetSupplierListKey;
 * 商品采购选择供应商界面查询可用供应商列表 ListEntity<SupplierItem>+com.spark.psi.publish.partner.key.GetSupplierListKey;
 *


 *
 
 * @version 2012-3-2
 */
public interface SupplierItem extends PartnerItem{

	public String getSupplierType();
	
	public double getTaxRate();
}
