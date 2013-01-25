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
import com.spark.psi.publish.PsiSimpleTask;

/**
 * <p>
 * 创建零售单
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-3-6
 */

public abstract class CreateRetailOrderTask extends
		PsiSimpleTask<CreateRetailOrderTask.Error> {

	public enum Error {
		/**
		 * 商品已停售
		 */
		GoodsStoped("商品已停售"),
		/**
		 * 商品数量必须大于0！
		 */
		GoodsCount_Zero("商品数量必须大于0！"),
		/**
		 * 促销商品超过促销数量
		 */
		promotion("已超促销数量！");
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

	private GUID customerId; // 客户ID

	private double amount; // 订单金额

	private String memo;// 备注
	
	private String orderNumber;//订单编号

	private double discountAmount;// 整单折扣 N

	private GUID id;

	private RetailOrderGoodsItem[] retailOrderGoodsItems; // 销售商品明细

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

		private GUID goodsItemId;// 商品条目id

		private double count;// 数量 NUM(10,2)

		private double amount;// 金额 NUM(17,2

		private double discountCount;// 折扣率 Num(5,4)

		private double discountAmount;// 折扣额 NUM(17,2)

		private double price; // 销售单价

		private GUID promotionId; // 促销单id

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
