/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.order.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.order.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.PsiSimpleTask;

/**
 * <p>
 * 创建零售退货
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-3-6
 */

public class CreateRetailReturnTask extends
		PsiSimpleTask<CreateRetailReturnTask.Error> {
	public enum Error {
		/**
		 * 商品已停售
		 */
		GoodsStoped("商品已停售"),
		/**
		 * 商品数量必须大于0！
		 */
		GoodsCount_Zero("商品数量必须大于0！");
		String msg;

		/**
		 * 获得异常提示
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

	private GUID customerId; // 客户名称

	private String retrunProof; // 退货凭据

	private DealingsWay dealingsWay; // 支付方式

	private double amount; // 订单金额

	private String memo;// 备注
	private GUID id;

	/**
	 * 退货商品明细
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

		private GUID storeId; // 退货仓库id

		private GUID goodsItemId;// 商品条目id

		private double price = -1;// 单价 NUM(17,2)

		private double count;// 数量 NUM(10,2)

		private double amount;// 金额 NUM(17,2

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
