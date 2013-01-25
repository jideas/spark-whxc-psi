package com.spark.psi.publish.inventory.entity;

/**
 * 订单（销售/销售退货/采购/采购退货）对应的出入库单数据<br>
 * 查询方法：getList(OrderCheckSheetItem.class,GetOrderCheckSheetItemKey);
 * ok
 */
public interface OrderCheckSheetItem {

	/**
	 * 获取出入库仓库名称
	 * @return
	 */
	public String getStoreName();

	/**
	 * 获取已出入库出入库单号
	 * 
	 * @return
	 */
	public String getCheckedSerialNos();

	/**
	 * 获取预计出库日期
	 * 
	 * @return
	 */
	public long getPlayCheckDate();

	/**
	 * 得到明细商品条目信息
	 * 
	 * @return
	 */
	public Item[] getItems();

	/**
	 * 明细条目信息
	 */
	public static class Item {

		/**
		 * 商品说明（格式：商品名称+属性）
		 */
		private String goodsInfo;

		/**
		 * 商品价格
		 */
		private double price;

		/**
		 * 采购/销售/退货数量
		 */
		private double orderCount;

		/**
		 * 已经出入库数量
		 */
		private double checkedCount;

		/**
		 * 数量小数位
		 */
		private int countDecimal;

		/**
		 * @return the goodsInfo
		 */
		public String getGoodsInfo() {
			return goodsInfo;
		}

		/**
		 * @param goodsInfo
		 *            the goodsInfo to set
		 */
		public void setGoodsInfo(String goodsInfo) {
			this.goodsInfo = goodsInfo;
		}

		/**
		 * @return the price
		 */
		public double getPrice() {
			return price;
		}

		/**
		 * @param price
		 *            the price to set
		 */
		public void setPrice(double price) {
			this.price = price;
		}

		/**
		 * @return the orderCount
		 */
		public double getOrderCount() {
			return orderCount;
		}

		/**
		 * @param orderCount
		 *            the orderCount to set
		 */
		public void setOrderCount(double orderCount) {
			this.orderCount = orderCount;
		}

		/**
		 * @return the checkedCount
		 */
		public double getCheckedCount() {
			return checkedCount;
		}

		/**
		 * @param checkedCount
		 *            the checkedCount to set
		 */
		public void setCheckedCount(double checkedCount) {
			this.checkedCount = checkedCount;
		}

		/**
		 * @return the countDecimal
		 */
		public int getScale() {
			return countDecimal;
		}

		/**
		 * @param countDecimal
		 *            the countDecimal to set
		 */
		public void setCountDecimal(int countDecimal) {
			this.countDecimal = countDecimal;
		}

	}

}
