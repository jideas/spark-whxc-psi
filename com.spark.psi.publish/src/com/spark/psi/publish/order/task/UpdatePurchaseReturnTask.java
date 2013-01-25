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
 * �ɹ��˻�Task
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
	private GUID partnerId;// �ͻ�/��Ӧ��GUID
	@StructField
	private double amount;
	@StructField
	private String consignee;// �ջ���V(10)
	@StructField
	private String consigneePhoneNumber; // �ջ��˵绰
	@StructField
	private GUID consigneeId;
	@StructField
	private String address;// �ջ���ַ V(200)
	@StructField
	private GUID contactPersonGuid;// ��ϵ��GUID
	@StructField
	private String memo;// ��ע
	private GUID storeId; // �˻��ֿ�id
	@StructField
	private PurchaseReturnGoodsItem[] purchaseReturnGoodsItems;

	public static final class PurchaseReturnGoodsItem {

		@StructField
		private GUID goodsItemId;// ��Ʒ��Ŀid
		@StructField
		private double price = -1;// ���� NUM(17,2)
		@StructField
		private double count;// ���� NUM(10,2)
		@StructField
		private double amount;// ��� NUM(17,2

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
