package com.spark.psi.publish.inventory.entity;

/**
 * ����������/�����˻�/�ɹ�/�ɹ��˻�����Ӧ�ĳ���ⵥ����<br>
 * ��ѯ������getList(OrderCheckSheetItem.class,GetOrderCheckSheetItemKey);
 * ok
 */
public interface OrderCheckSheetItem {

	/**
	 * ��ȡ�����ֿ�����
	 * @return
	 */
	public String getStoreName();

	/**
	 * ��ȡ�ѳ�������ⵥ��
	 * 
	 * @return
	 */
	public String getCheckedSerialNos();

	/**
	 * ��ȡԤ�Ƴ�������
	 * 
	 * @return
	 */
	public long getPlayCheckDate();

	/**
	 * �õ���ϸ��Ʒ��Ŀ��Ϣ
	 * 
	 * @return
	 */
	public Item[] getItems();

	/**
	 * ��ϸ��Ŀ��Ϣ
	 */
	public static class Item {

		/**
		 * ��Ʒ˵������ʽ����Ʒ����+���ԣ�
		 */
		private String goodsInfo;

		/**
		 * ��Ʒ�۸�
		 */
		private double price;

		/**
		 * �ɹ�/����/�˻�����
		 */
		private double orderCount;

		/**
		 * �Ѿ����������
		 */
		private double checkedCount;

		/**
		 * ����С��λ
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
