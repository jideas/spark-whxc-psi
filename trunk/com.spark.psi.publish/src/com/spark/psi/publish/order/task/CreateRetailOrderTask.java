/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PsiSimpleTask;

/**
 * <p>
 * �������۵�
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-3-6
 */

public abstract class CreateRetailOrderTask extends
		PsiSimpleTask<CreateRetailOrderTask.Error> {

	public enum Error {
		/**
		 * ��Ʒ��ͣ��
		 */
		GoodsStoped("��Ʒ��ͣ��"),
		/**
		 * ��Ʒ�����������0��
		 */
		GoodsCount_Zero("��Ʒ�����������0��"),
		/**
		 * ������Ʒ������������
		 */
		promotion("�ѳ�����������");
		String msg;

		/**
		 * ����쳣��ʾ
		 * 
		 * @return String
		 */
		public String getMsg() {
			return msg;
		}

		Error(String msg) {
			this.msg = msg;
		}
	}

	private GUID customerId; // �ͻ�ID

	private double amount; // �������

	private String memo;// ��ע
	
	private String orderNumber;//�������

	private double discountAmount;// �����ۿ� N

	private GUID id;

	private RetailOrderGoodsItem[] retailOrderGoodsItems; // ������Ʒ��ϸ

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(GUID customerId) {
		this.customerId = customerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public RetailOrderGoodsItem[] getRetailOrderGoodsItems() {
		return retailOrderGoodsItems;
	}

	public void setRetailOrderGoodsItems(
			RetailOrderGoodsItem[] retailOrderGoodsItems) {
		this.retailOrderGoodsItems = retailOrderGoodsItems;
	}

	public static final class RetailOrderGoodsItem {

		private GUID id;

		private GUID goodsItemId;// ��Ʒ��Ŀid

		private double count;// ���� NUM(10,2)

		private double amount;// ��� NUM(17,2

		private double discountCount;// �ۿ��� Num(5,4)

		private double discountAmount;// �ۿ۶� NUM(17,2)

		private double price; // ���۵���

		private GUID promotionId; // ������id

		public GUID getId() {
			return id;
		}

		public void setId(GUID id) {
			this.id = id;
		}

		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		public void setGoodsItemId(GUID goodsItemId) {
			this.goodsItemId = goodsItemId;
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

		public double getDiscountCount() {
			return discountCount;
		}

		public void setDiscountCount(double discountCount) {
			this.discountCount = discountCount;
		}

		public double getDiscountAmount() {
			return discountAmount;
		}

		public void setDiscountAmount(double discountAmount) {
			this.discountAmount = discountAmount;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public GUID getPromotionId() {
			return promotionId;
		}

		public void setPromotionId(GUID promotionId) {
			this.promotionId = promotionId;
		}

	}

}
