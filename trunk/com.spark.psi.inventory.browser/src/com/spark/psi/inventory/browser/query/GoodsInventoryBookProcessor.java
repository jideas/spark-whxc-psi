package com.spark.psi.inventory.browser.query;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.Store;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.base.browser.QueryTermSource;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategorySelectionMsg;
import com.spark.psi.inventory.browser.query.GoodsInventoryBookRender.GoodsBookQueryListRender;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.inventory.key.ReportInventoryBookKey;
import com.spark.psi.report.service.provider.book.InventoryBook;

public class GoodsInventoryBookProcessor extends GoodsCategoryFramePageProcessor{

	private GoodsBookQueryListProcessor queryListProcessor;

	@Override
	public void process(final Situation context){
		getContext().regMessageListener(GoodsCategorySelectionMsg.class,
		        new MessageListener<GoodsCategorySelectionMsg>(){
			        public void onMessage(Situation context, GoodsCategorySelectionMsg message,
			                MessageTransmitter<GoodsCategorySelectionMsg> transmitter)
			        {
				        String storeId = null;
				        String queryTerm = null;
				        if(queryListProcessor != null){
//					        storeId = queryListProcessor.storeList.getText();
					        storeId = Store.GoodsStoreId.toString();
					        queryTerm = queryListProcessor.termList.getText();
				        }
				        ControllerPage page =
				                (ControllerPage)rightArea.showPage(ControllerPage.NAME, new PageControllerInstance(
				                        new PageController(GoodsBookQueryListProcessor.class,
				                        		GoodsBookQueryListRender.class), message.getCategoryId(), storeId,
				                        queryTerm));
				        if(page != null){
					        queryListProcessor = (GoodsBookQueryListProcessor)page.getProcessor();
				        }
			        }
		        });
		super.process(context);
	}

	public final static class GoodsBookQueryListProcessor extends PSIGoodsListPageProcessor{

		// 过滤仓库下拉列表
		public final static String ID_COMBOLIST_STORE = "ComboList_Store";
		// 过滤日期下拉列表
		public final static String ID_COMBOLIST_TERM = "ComboList_Term";
		// 商品数量
		public final static String ID_LABEL_INVENTORYBOOK_COUNT = "Label_InventoryBook_Count";

		//
		private Label countLabel;
//		private LWComboList storeList;
		private LWComboList termList;

		@Override
		public void process(Situation situation){

			super.process(situation);

			//
//			storeList = this.createControl(ID_COMBOLIST_STORE, LWComboList.class);
//			StoreSource storeSource = new StoreSource(StoreStatus.ENABLE,StoreStatus.ONCOUNTING,StoreStatus.STOP);
//			storeSource.setShowAllStoreItem(true);
//			storeList.getList().setSource(storeSource);
//			storeList.getList().setInput(null);
//			if(this.getArgument2() != null){
//				storeList.setSelection((String)this.getArgument2());
//			}
//			else if(storeSource.getFirstStoreId() != null){
//				storeList.setSelection(storeSource.getFirstStoreId().toString());
//			}
//			storeList.addSelectionListener(new SelectionListener(){
//				public void widgetSelected(SelectionEvent e){
//					table.render();
//				}
//			});

			//
			termList = this.createControl(ID_COMBOLIST_TERM, LWComboList.class);
			QueryTermSource termSource = new QueryTermSource();
			termList.getList().setSource(termSource);
			termList.getList().setInput(null);
			if(this.getArgument3() != null){
				termList.setSelection((String)this.getArgument3());
			}
			else{
				termList.setSelection(termSource.getFirstStoreId());
			}
			termList.addSelectionListener(new SelectionListener(){
				public void widgetSelected(SelectionEvent e){
					table.render();
				}
			});
			countLabel = this.createControl(ID_LABEL_INVENTORYBOOK_COUNT, Label.class);
		}

		@Override
		protected Object[] getElements(Context context, String searchText, GUID categoryId, STableStatus tablestatus){
			//ReportInventoryBookKey key = new ReportInventoryBookKey(InventoryType.Goods, tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
			ReportInventoryBookKey key = new ReportInventoryBookKey(InventoryType.Goods, 0, Integer.MAX_VALUE, true);
			key.setSearchKey(searchText);
			key.setGoodsTypeId(categoryId);
			QueryTerm queryTerm = context.find(QueryTerm.class, termList.getText());
			key.setBeginTime(queryTerm.getStartTime());
			key.setEndTime(queryTerm.getEndTime());
			key.setStoreId(Store.GoodsStoreId);
//			if (CheckIsNull.isNotEmpty(storeList.getText())) {
//				GUID storeId = GUID.valueOf(storeList.getText());
//				if(!storeId .equals(GUID.emptyID)) {
//					key.setStoreId(storeId);
//				}
//			}
			List<InventoryBook> list = context.getList(InventoryBook.class, key);
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
		public String getElementId(Object element){
			InventoryBook book = (InventoryBook)element;
			return book.getGoodsId() + "";
		}

		@Override
		protected String getExportFileTitle() {
			return "商品库存台账";
		}

	}
}