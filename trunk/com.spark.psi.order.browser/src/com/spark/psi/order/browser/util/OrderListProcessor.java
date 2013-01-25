package com.spark.psi.order.browser.util;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.order.key.GetOrderListKey;

/**
 * <p>订单控制类父类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-20
 */
public abstract class OrderListProcessor<Item> extends PSIListPageProcessor<Item>{
	public final static String ID_TEXT_SEARCH = "TEXT_SEARCH";
	protected Text searchText;

	public static enum Columns {
		/**
		 * 交货日期
		 */
		DeliveryDate(GetOrderListKey.SortField.DeliveryDate, 100, JWT.CENTER,
				"交货日期", true, true),
		/**
		 * 订单编号
		 */
		OrderNumber(GetOrderListKey.SortField.OrderNumber, 150, JWT.LEFT,
				"单据编号", true, true),
		/**
		 * 客户名称
		 */
		PartnerName(GetOrderListKey.SortField.PartnerShortName, 120,
				JWT.LEFT, "客户名称", true, true),
		/**
		 * 类型
		 */
		Type(GetOrderListKey.SortField.OrderType, 100, JWT.CENTER, "类型", true,
				true),
		/**
		 * 金额
		 */
		Amount(GetOrderListKey.SortField.Amount, 100, JWT.RIGHT, "金额", true,
				true),
		/**
		 * 制单人
		 */
		Creator(GetOrderListKey.SortField.Creator, 100, JWT.CENTER, "制单人",
				true, true),
		/**
		 * 制单日期
		 */
		CreateDate(GetOrderListKey.SortField.CreateDate, 100, JWT.CENTER,
				"制单日期", true, true),
		/**
		 * 处理状态
		 */
		status(GetOrderListKey.SortField.OrderStatus, 100, JWT.CENTER, "处理情况",
				true, true);
		private GetOrderListKey.SortField sortField;
		private int width;
		private int align;
		private String title;
		private boolean isGrab;
		private boolean isSort;

		/**
		 * @param width
		 * @param align
		 * @param title
		 * @param isGrab
		 * @param isSort
		 */
		private Columns(GetOrderListKey.SortField sortField, int width,
				int align, String title, boolean isGrab, boolean isSort) {
			this.sortField = sortField;
			this.width = width;
			this.align = align;
			this.title = title;
			this.isGrab = isGrab;
			this.isSort = isSort;
		}

		public int getWidth() {
			return width;
		}

		public int getAlign() {
			return align;
		}

		public String getTitle() {
			return title;
		}

		public boolean isGrab() {
			return isGrab;
		}

		public boolean isSort() {
			return isSort;
		}

		public GetOrderListKey.SortField getSortField() {
			return sortField;
		}
	}
	
	/**
	 * 设置订单排序规则
	 * @param key
	 * @param tablestatus void
	 */
	protected void setLimitKeySort(LimitKey key, STableStatus tablestatus){
		if(null != tablestatus.getSortDirection()){
			key.setSortType(SSortDirection.ASC == tablestatus
							.getSortDirection() ? SortType.Asc : SortType.Desc);
		}
	}
	
	/**
	 * 当前用户是员工
	 * @return boolean
	 */
	protected abstract boolean isEmployee();
}
