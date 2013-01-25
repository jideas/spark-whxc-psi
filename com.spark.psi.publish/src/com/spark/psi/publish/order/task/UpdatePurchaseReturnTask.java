/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;

/**
 * 采购退货Task
 * 
 * @author zhoulijun
 * 
 */
@StructClass
public class UpdatePurchaseReturnTask extends Task<UpdatePurchaseReturnTask.Method> {

	public enum Method {
		Create, Update
	}

	@StructField
	private GUID id;// GUID
	@StructField
	private GUID partnerId;// 客户/供应商GUID
	@StructField
	private double amount;
	@StructField
	private String consignee;// 收货人V(10)
	@StructField
	private String consigneePhoneNumber; // 收货人电话
	@StructField
	private GUID consigneeId;
	@StructField
	private String address;// 收货地址 V(200)
	@StructField
	private GUID contactPersonGuid;// 联系人GUID
	@StructField
	private String memo;// 备注
	private GUID storeId; // 退货仓库id
	@StructField
	private PurchaseReturnGoodsItem[] purchaseReturnGoodsItems;

	public static final class PurchaseReturnGoodsItem {

		@StructField
		private GUID goodsItemId;// 商品条目id
		@StructField
		private double price = -1;// 单价 NUM(17,2)
		@StructField
		private double count;// 数量 NUM(10,2)
		@StructField
		private double amount;// 金额 NUM(17,2

		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		public void setGoodsItemId(GUID goodsItemId) {
			this.goodsItemId = goodsItemId;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getCount() {
			return count;
		}

		public void setCount(double count) {
			this.count = count;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public GUID getContactPersonGuid() {
		return contactPersonGuid;
	}

	public void setContactPersonGuid(GUID contactPersonGuid) {
		this.contactPersonGuid = contactPersonGuid;
	}

	public String getRemark() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public PurchaseReturnGoodsItem[] getPurchaseReturnGoodsItems() {
		return purchaseReturnGoodsItems;
	}

	public void setPurchaseReturnGoodsItems(PurchaseReturnGoodsItem[] purchaseReturnGoodsItems) {
		this.purchaseReturnGoodsItems = purchaseReturnGoodsItems;
	}

	public String getConsigneePhoneNumber() {
		return consigneePhoneNumber;
	}

	public void setConsigneePhoneNumber(String consigneePhoneNumber) {
		this.consigneePhoneNumber = consigneePhoneNumber;
	}

	public GUID getConsigneeId() {
		return consigneeId;
	}

	public void setConsigneeId(GUID consigneeId) {
		this.consigneeId = consigneeId;
	}

}
