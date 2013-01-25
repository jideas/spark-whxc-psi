package com.spark.psi.order.browser.util;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>采购订单分组整理</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-10
 */
public interface IPurchaseGoodsGroup {
	SupplierGroup[] getSupplierGroupList();
	PurchaseOrderGroup[] getPurchaseOrderGroupList(GUID supplierGroupId);
	
	public class SupplierGroup{
		protected GUID id;
		protected String name;
		/**
		 * @param id
		 * @param name
		 */
		SupplierGroup(GUID id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public GUID getId() {
			return id;
		}
		public String getName() {
			return name;
		}
	}
	public class PurchaseOrderGroup{
		protected GUID sourceId;
		protected String sourceName;
		protected boolean isDirect;
		protected GUID contactId;
		protected long deliveryDate;
		protected String salesMemo;
		protected PurchaseGroupGoods[] goodsItems;
		public GUID getSourceId() {
			return sourceId;
		}
		public String getSourceName() {
			return sourceName;
		}
		public boolean isDirect() {
			return isDirect;
		}
		public GUID getContactId() {
			return contactId;
		}
		public PurchaseGroupGoods[] getGoodsItems() {
			return goodsItems;
		}
		public long getDeliveryDate() {
			return deliveryDate;
		}
		public String getSalesMemo() {
			return salesMemo;
		}
	}
	
	public class PurchaseGroupGoods{
		protected GUID purchaseGoodsId;
		protected GUID goodsItemId;
		protected String goodsName;
		protected double price;
		protected double count;
		public GUID getPurchaseGoodsId() {
			return purchaseGoodsId;
		}
		public String getGoodsName() {
			return goodsName;
		}
		public double getPrice() {
			return price;
		}
		public double getCount() {
			return count;
		}
		public GUID getGoodsItemId() {
			return goodsItemId;
		}
	}
}
