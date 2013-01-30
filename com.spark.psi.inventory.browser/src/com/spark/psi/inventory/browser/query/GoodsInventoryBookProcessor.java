package com.spark.psi.inventory.browser.query;

import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.TimeTest;
import com.spark.psi.base.Store;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategorySelectionMsg;
import com.spark.psi.inventory.browser.query.GoodsInventoryBookRender.GoodsBookQueryListRender;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.inventory.key.ReportInventoryBookKey;
import com.spark.psi.report.service.provider.book.InventoryBook;

public class GoodsInventoryBookProcessor extends
		GoodsCategoryFramePageProcessor {

	private GoodsBookQueryListProcessor queryListProcessor;

	@Override
	public void process(final Situation context) {
		getContext().regMessageListener(GoodsCategorySelectionMsg.class,
				new MessageListener<GoodsCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							GoodsCategorySelectionMsg message,
							MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
						String storeId = null;
						Date begin = null, end = null;
						if (queryListProcessor != null) {
							// storeId = queryListProcessor.getStoreList()
							// .getText();
							begin = queryListProcessor.getTermList1().getDate();
							end = queryListProcessor.getTermList2().getDate();
						}
						ControllerPage page = (ControllerPage) rightArea
								.showPage(
										ControllerPage.NAME,
										new PageControllerInstance(
												new PageController(
														GoodsBookQueryListProcessor.class,
														GoodsBookQueryListRender.class),
												message.getCategoryId(),
												storeId, begin, end));
						if (page != null) {
							queryListProcessor = (GoodsBookQueryListProcessor) page
									.getProcessor();
						}
					}
				});
		super.process(context);
	}

	public final static class GoodsBookQueryListProcessor extends
			PSIGoodsListPageProcessor {

		// 过滤仓库下拉列表
		public final static String ID_COMBOLIST_STORE = "ComboList_Store";
		// 过滤日期下拉列表
		public final static String ID_COMBOLIST_TERM1 = "ComboList_Term1";
		public final static String ID_COMBOLIST_TERM2 = "ComboList_Term2";
		public final static String ID_COMBOLIST_TERM3 = "ComboList_Term_button";
		// 商品数量
		public final static String ID_LABEL_INVENTORYBOOK_COUNT = "Label_InventoryBook_Count";

		//
		private Label countLabel;
		// private LWComboList storeList;
		private DatePicker termList1, termList2;
		private Button dateoverBtn;

		@Override
		public void process(Situation situation) {

			super.process(situation);

			//
			// storeList = this.createControl(ID_COMBOLIST_STORE,
			// LWComboList.class);
			// StoreSource storeSource = new
			// StoreSource(StoreStatus.ENABLE,StoreStatus.ONCOUNTING,StoreStatus.STOP);
			// storeSource.setShowAllStoreItem(true);
			// storeList.getList().setSource(storeSource);
			// storeList.getList().setInput(null);
			// if(this.getArgument2() != null){
			// storeList.setSelection((String)this.getArgument2());
			// }
			// else if(storeSource.getFirstStoreId() != null){
			// storeList.setSelection(storeSource.getFirstStoreId().toString());
			// }
			// storeList.addSelectionListener(new SelectionListener(){
			// public void widgetSelected(SelectionEvent e){
			// table.render();
			// }
			// });
			termList1 = this.createSDatePickerControl(ID_COMBOLIST_TERM1);
			termList2 = this.createSDatePickerControl(ID_COMBOLIST_TERM2);

			if (this.getArgument3() != null) {
				termList1.setDate((Date) this.getArgument3());
			} else {
				termList1
						.setDate(new Date(new TimeTest().getFirstDayOfMonth()));
			}
			if (this.getArgument4() != null) {
				termList2.setDate((Date) this.getArgument4());
			} else {
				termList2.setDate(new Date());
			}
			dateoverBtn = this.createButtonControl(ID_COMBOLIST_TERM3);
			dateoverBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					table.render();
				}
			});
			countLabel = this.createControl(ID_LABEL_INVENTORYBOOK_COUNT,
					Label.class);
		}

		@Override
		protected Object[] getElements(Context context, String searchText,
				GUID categoryId, STableStatus tablestatus) {
			// ReportInventoryBookKey key = new
			// ReportInventoryBookKey(InventoryType.Goods,
			// tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
			ReportInventoryBookKey key = new ReportInventoryBookKey(
					InventoryType.Goods, 0, Integer.MAX_VALUE, true);
			key.setSearchKey(searchText);
			key.setGoodsTypeId(categoryId);
			key.setBeginTime(this.termList1.getDate().getTime());
			key.setEndTime(this.termList2.getDate().getTime());
			key.setStoreId(Store.GoodsStoreId);
			// if (CheckIsNull.isNotEmpty(storeList.getText())) {
			// GUID storeId = GUID.valueOf(storeList.getText());
			// if(!storeId .equals(GUID.emptyID)) {
			// key.setStoreId(storeId);
			// }
			// }
			List<InventoryBook> list = context
					.getList(InventoryBook.class, key);
			int size = list.size();
			if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
				String preSize = countLabel.getText();
				if (StringHelper.isNotEmpty(preSize)) {
					size += Integer.parseInt(preSize);
				}
			}
			countLabel.setText(String.valueOf(size));
			return list.toArray(new InventoryBook[0]);
		}

		/*
		 * 获取指定对象ID
		 */
		public String getElementId(Object element) {
			InventoryBook book = (InventoryBook) element;
			return book.getGoodsId() + "";
		}

		@Override
		protected String getExportFileTitle() {
			return "商品库存台账";
		}

		public DatePicker getTermList1() {
			return termList1;
		}

		public void setTermList1(DatePicker termList1) {
			this.termList1 = termList1;
		}

		public DatePicker getTermList2() {
			return termList2;
		}

		public void setTermList2(DatePicker termList2) {
			this.termList2 = termList2;
		}

	}
}