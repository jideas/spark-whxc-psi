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
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.PsiSimpleTask;

/**
 * <p>
 * ���������˻�
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-3-6
 */

public class CreateRetailReturnTask extends
		PsiSimpleTask<CreateRetailReturnTask.Error> {
	public enum Error {
		/**
		 * ��Ʒ��ͣ��
		 */
		GoodsStoped("��Ʒ��ͣ��"),
		/**
		 * ��Ʒ�����������0��
		 */
		GoodsCount_Zero("��Ʒ�����������0��");
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

	private GUID customerId; // �ͻ�����

	private String retrunProof; // �˻�ƾ��

	private DealingsWay dealingsWay; // ֧����ʽ

	private double amount; // �������

	private String memo;// ��ע
	private GUID id;

	/**
	 * �˻���Ʒ��ϸ
	 */
	private RetailReturnGoodsItem[] retailReturnGoodsItems; //

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

	public String getRetrunProof() {
		return retrunProof;
	}

	public void setRetrunProof(String retrunProof) {
		this.retrunProof = retrunProof;
	}

	public DealingsWay getDealingsWay() {
		return dealingsWay;
	}

	public void setDealingsWay(DealingsWay dealingsWay) {
		this.dealingsWay = dealingsWay;
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

	public RetailReturnGoodsItem[] getRetailReturnGoodsItems() {
		return retailReturnGoodsItems;
	}

	public void setRetailReturnGoodsItems(
			RetailReturnGoodsItem[] retailReturnGoodsItems) {
		this.retailReturnGoodsItems = retailReturnGoodsItems;
	}

	public static final class RetailReturnGoodsItem {

		private GUID storeId; // �˻��ֿ�id

		private GUID goodsItemId;// ��Ʒ��Ŀid

		private double price = -1;// ���� NUM(17,2)

		private double count;// ���� NUM(10,2)

		private double amount;// ��� NUM(17,2

		public GUID getStoreId() {
			return storeId;
		}

		public void setStoreId(GUID storeId) {
			this.storeId = storeId;
		}

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

}
