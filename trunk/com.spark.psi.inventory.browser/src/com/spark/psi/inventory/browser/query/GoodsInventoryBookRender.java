package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIGoodsListPageRender;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageRender;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.report.service.provider.book.InventoryBook;

public class GoodsInventoryBookRender extends GoodsCategoryFramePageRender {

	public final static class GoodsBookQueryListRender extends PSIGoodsListPageRender {
		private LoginInfo loginInfo = null;
		
		

		@Override
		public void init(Situation context) {
			super.init(context);
			loginInfo = context.find(LoginInfo.class);
		}
		@Override
		protected void afterFooterRender() {
			super.afterFooterRender();
			// new LWComboList(headerLeftArea,
			// JWT.APPEARANCE3).setID(InventoryBookQueryListProcessor.ID_COMBOLIST_STORE);
			// new Label(headerLeftArea).setText("  ");
			new LWComboList(headerLeftArea, JWT.APPEARANCE3).setID(InventoryBookQueryListProcessor.ID_COMBOLIST_TERM);
			new Label(headerLeftArea).setText("  商品数量：");
			Label label = new Label(headerLeftArea);
			label.setID(InventoryBookQueryListProcessor.ID_LABEL_INVENTORYBOOK_COUNT);
			GridData gd = new GridData();
			gd.widthHint = 100;
			label.setLayoutData(gd);
			if (!hasAmountAuth()) {
				return;
			}
			Button b = new Button(this.footerLeftArea, JWT.APPEARANCE2);
			b.setText(" 调整成本 ");
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PageControllerInstance pci = new PageControllerInstance("PSI_ChangeCostPage");
					WindowStyle ws = new WindowStyle(JWT.CLOSE);
					ws.setSize(600, 190);
					MsgRequest request = new MsgRequest(pci, "调整成本", ws);
					((Situation) getContext()).bubbleMessage(request);
				}
			});
		}

		
		@Override
		public STableStyle getTableStyle() {
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(false);
			return tableStyle;
		}
		/**
		 * 获取列
		 */
		public STableColumn[] getColumns() {
			int length = 13;
			if (!hasAmountAuth()) {
				length = length - 4;
			}
			if (!hasCountAuth()) {
				length = length - 4;
			}
			STableColumn[] columns = new STableColumn[length];
			int index = 0;
			// 需要加SheetId 获取调拨单ID
			columns[index] = new STableColumn("goodsCode", 100, JWT.LEFT, "商品编码");
			columns[index++].setSortable(true);

			columns[index] = new STableColumn("goodsNo", 100, JWT.LEFT, "商品条码");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);

			columns[index] = new STableColumn("goodsName", 100, JWT.LEFT, "商品名称");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
			columns[index] = new STableColumn("goodsAttr", 100, JWT.LEFT, "商品规格");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
			columns[index] = new STableColumn("goodsUnit", 50, JWT.CENTER, "单位");
			columns[index].setSortable(true);
			columns[index++].setGrab(true);
			if (hasCountAuth()) {
				columns[index] = new STableColumn("count_begin", 90, JWT.RIGHT, "期初库存数量");
				columns[index].setSortable(true);
				columns[index++].setGrab(true);
			}
			if (hasAmountAuth()) {
				columns[index] = new STableColumn("amount_begin", 90, JWT.RIGHT, "期初库存金额");
				columns[index].setSortable(true);
				columns[index++].setGrab(true);
			}
			if (hasCountAuth()) {
				columns[index] = new STableColumn("instoCount", 70, JWT.RIGHT, "入库数量");
				columns[index].setSortable(true);
				columns[index++].setGrab(true);
			}
			if (hasAmountAuth()) {
				columns[index] = new STableColumn("instoAmount", 70, JWT.RIGHT, "入库金额");
				columns[index].setSortable(true);
				columns[index++].setGrab(true);
			}
			if (hasCountAuth()) {
				columns[index] = new STableColumn("outstoCount", 70, JWT.RIGHT, "出库数量");
				columns[index].setSortable(true);
				columns[index++].setGrab(true);
			}
			if (hasAmountAuth()) {
				columns[index] = new STableColumn("outstoAmount", 70, JWT.RIGHT, "出库金额");
				columns[index].setSortable(true);
				columns[index++].setGrab(true);
			}
			if (hasCountAuth()) {
				columns[index] = new STableColumn("count_end", 90, JWT.RIGHT, "期末库存数量");
				columns[index].setSortable(true);
				columns[index++].setGrab(true);
			}
			if (hasAmountAuth()) {
				columns[index] = new STableColumn("amount_end", 90, JWT.RIGHT, "期末库存金额");
				columns[index].setSortable(true);
				columns[index++].setGrab(true);
			}
			return columns;
		}

		/**
		 * 单元格取值
		 */
		public String getText(Object element, int columnIndex) {
			STableColumn[] columns = getColumns();
			String text = "";
			if (columnIndex >= 0 && columnIndex < columns.length) {
				String fieldName = columns[columnIndex].getName();
				InventoryBook book = (InventoryBook) element;
				if (fieldName.equals("goodsCode")) { // 编码/条码
//					if (CheckIsNull.isEmpty(book.getGoodsCode())) {
//						GoodsItem gi = this.getContext().find(GoodsItem.class, book.getGoodsId());
//						if (null == gi) {
//							return null;
//						}
//						text = gi.getGoodsCode();
//					} else {
//						text = book.getGoodsCode();
//					}
					text = book.getGoodsCode();
				} else if (fieldName.equals("goodsNo")) {
					text = book.getGoodsNo();
				} else if (fieldName.equals("goodsName")) { // 商品名称
					text = book.getGoodsName();
				} else if (fieldName.equals("goodsAttr")) { // 商品规格
					text = book.getGoodsAttr();
				} else if (fieldName.equals("goodsUnit")) { // 单位
					text = book.getGoodsUnit();
				} else if (fieldName.equals("count_begin")) { // 期初库存数量
					text = DoubleUtil.getRoundStr(book.getCount_begin());
				} else if (fieldName.equals("amount_begin")) { // 期初库存金额
					text = DoubleUtil.getRoundStr(book.getAmount_begin());
				} else if (fieldName.equals("instoCount")) { // 入库数量
					text = DoubleUtil.getRoundStr(book.getInstoCount());
				} else if (fieldName.equals("instoAmount")) { // 入库金额
					text = DoubleUtil.getRoundStr(book.getInstoAmount());
				} else if (fieldName.equals("outstoCount")) { // 出库数量
					text = DoubleUtil.getRoundStr(book.getOutstoCount());
				} else if (fieldName.equals("outstoAmount")) { // 出库金额
					text = DoubleUtil.getRoundStr(book.getOutstoAmount());
				} else if (fieldName.equals("count_end")) { // 期末库存数量
					text = DoubleUtil.getRoundStr(book.getCount_end());
				} else if (fieldName.equals("amount_end")) { // 期末库存金额
					text = DoubleUtil.getRoundStr(book.getAmount_end());
				}
			}
			return text;
		}

		/**
		 * 获得精度
		 */
		@Override
		public int getDecimal(Object element, int columnIndex) {
			STableColumn[] columns = getColumns();
			if (columnIndex >= 0 && columnIndex < columns.length) {
				String fieldName = columns[columnIndex].getName();
				if (fieldName.equals("count_begin")) { // 期初库存数量
					return 2;
				} else if (fieldName.equals("amount_begin")) { // 期初库存金额
					return 2;
				} else if (fieldName.equals("instoCount")) { // 入库数量
					return 2;
				} else if (fieldName.equals("instoAmount")) { // 入库金额
					return 2;
				} else if (fieldName.equals("outstoCount")) { // 出库数量
					return 2;
				} else if (fieldName.equals("outstoAmount")) { // 出库金额
					return 2;
				} else if (fieldName.equals("count_end")) { // 期末库存数量
					return 2;
				} else if (fieldName.equals("amount_end")) { // 期末库存金额
					return 2;
				}
			}
			return -1;
		}

		/**
		 * 判断是否有库存金额权限
		 * 
		 *@return
		 */
		private boolean hasAmountAuth() {
			return loginInfo.hasAuth(Auth.SubFunction_InventoryQuery_Price);
//			GUID id = getContext().find(Login.class).getEmployeeId();
//			return getContext().find(Boolean.class, Auth.SubFunction_InventoryQuery_Price, id);
		}

		/**
		 * 判断是否有库存数量权限
		 * 
		 *@return
		 */
		private boolean hasCountAuth() {
			return loginInfo.hasAuth(Auth.SubFunction_InventoryQuery_Count);
//			GUID id = getContext().find(Login.class).getEmployeeId();
//			return getContext().find(Boolean.class, Auth.SubFunction_InventoryQuery_Count, id);
		}
	}
}
