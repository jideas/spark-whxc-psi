package com.spark.psi.query.browser;

import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategorySelectionMsg;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.key.GetInventoryItemKey.SortField;
import com.spark.psi.query.browser.OnlineSalesSummaryRender.OnlineSalesRender;
import com.spark.psi.query.browser.util.OnlineSalesSearchCondition;
import com.spark.psi.query.browser.util.StationSource;
import com.spark.psi.query.intf.publish.entity.OnlineSalesItem;
import com.spark.psi.query.intf.publish.entity.OnlineSalesListEntity;
import com.spark.psi.query.intf.publish.key.GetOnlineSalesListKey;

public class OnlineSalesSummaryProcessor extends
		GoodsCategoryFramePageProcessor {

	private OnlineSalesListProcessor queryListProcessor;

	public static enum Cloumns {
		goodsCode, GoodNumber, GoodsName, GoodsAttribute, Unit, Count,Amount;
	}

	@Override
	public void process(final Situation context) {
		getContext().regMessageListener(GoodsCategorySelectionMsg.class,
				new MessageListener<GoodsCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							GoodsCategorySelectionMsg message,
							MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
						String stationId = null;
						if (queryListProcessor != null) {
							stationId = queryListProcessor.stationList.getText();
						}
						ControllerPage page = (ControllerPage) rightArea
								.showPage(
										ControllerPage.NAME,
										new PageControllerInstance(
												new PageController(
														OnlineSalesListProcessor.class,
														OnlineSalesRender.class),
												message.getCategoryId(),
												stationId));
						if (page != null) {
							queryListProcessor = (OnlineSalesListProcessor) page
									.getProcessor();
						}
					}
				});
		super.process(context);
	}

	/**
	 * 商品库存查询处理器
	 */
	public final static class OnlineSalesListProcessor extends
			PSIGoodsListPageProcessor {

		// 站点下拉列表
		public final static String ID_COMBOLIST_STATION = "ComboList_Station";
		// 商品数量
		public final static String ID_LABEL_TOTALAMOUNT = "Label_TotalAmount";
		
		// 商品数量
		public final static String ID_BUTTON_ADVANCE = "Button_Advance";
		
//		public final static String ID_viewInventory = "viewInventory";

		private Label amountLabel;
		private LWComboList stationList;
		private Button advance;
		//查库窗口
//		protected InventoryViewWindow inventoryInfoWindow;
		private OnlineSalesSearchCondition condtion = null;
		private String stationId = null;
		
		
		@Override
		public void process(Situation situation) {
			super.process(situation);
			stationId = (String) this.getArgument2();
			stationList = this.createControl(ID_COMBOLIST_STATION,
					LWComboList.class, JWT.NO);
			StationSource source = new StationSource();
			stationList.getList().setSource(source);
			stationList.getList().setInput(null);
			if(null==stationId)
			{
				stationId = GUID.emptyID.toString();
			}
			if(null!=stationId)
			{
				stationList.setSelection(stationId);
			}
			advance = this.createControl(ID_BUTTON_ADVANCE,Button.class);
			
			amountLabel = this.createControl(ID_LABEL_TOTALAMOUNT,
					Label.class, JWT.NONE);
			stationList.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					table.render();
				}
			});
			advance.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					PageController pc = new PageController(OnlineSalesAdvaceSearchProcessor.class,
							OnlineSalesAdvaceSearchRender.class);
					PageControllerInstance pci = new PageControllerInstance(pc);
					WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
					windowStyle.setSize(390, 200);
					MsgRequest request = new MsgRequest(pci, "高级搜索", windowStyle);
					request.setResponseHandler(new ResponseHandler() {
						@Override
						public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
							if (null == returnValue)
								return;
							condtion = (OnlineSalesSearchCondition) returnValue;
							table.render();
							condtion = null;
						}
					});
					getContext().bubbleMessage(request);
				}
			});
		}

		@Override
		protected Object[] getElements(Context context, String searchText,
				GUID categoryId, STableStatus tablestatus) {
			GetOnlineSalesListKey key = new GetOnlineSalesListKey(0,
					Integer.MAX_VALUE, true);
			key.setGoodsCategoryId(categoryId);
			key.setSearchText(searchText);
			GUID stationId = GUID.emptyID;
			if (CheckIsNull.isNotEmpty(stationList.getText())) {
				stationId = GUID.valueOf(stationList.getText());
			}
			if(stationId.equals(GUID.emptyID)) {
				stationId = null;
			}
			key.setStationId(stationId);
//			if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
//				key.setSortField(getSortField(tablestatus.getSortColumn()));
//				key.setSortType(getSortType(tablestatus.getSortDirection()));
//			}
			if(null!=condtion)
			{
				if(condtion.getCreateDateBegin()>0)
				key.setDeliverDateBegin(condtion.getCreateDateBegin());
				if(condtion.getDeliverDateEnd()>0)
				key.setCreateDateEnd(condtion.getDeliverDateEnd());
				if(CheckIsNull.isNotEmpty(condtion.getCustomerName()))
				key.setCustomerName(condtion.getCustomerName());
				if(CheckIsNull.isNotEmpty(condtion.getGoodsCode()))
					key.setGoodsCode(condtion.getGoodsCode());
				if(CheckIsNull.isNotEmpty(condtion.getGoodsName()))
					key.setGoodsName(condtion.getGoodsName());
				if(CheckIsNull.isNotEmpty(condtion.getGoodsNo()))
					key.setGoodsNo(condtion.getGoodsNo());
			}
			OnlineSalesListEntity le = getContext().find(OnlineSalesListEntity.class, key);

			List<OnlineSalesItem> list = le.getItemList();
			if (CheckIsNull.isNotEmpty(list)) {
				OnlineSalesItem[] items = new OnlineSalesItem[list.size()];
				int index = 0;
				for (OnlineSalesItem item : list) {
					items[index++] = item;
				}
				amountLabel.setText(DoubleUtil.getRoundStr(le.getTotalAmount()));
				return items;
			}
			amountLabel.setText("0.00");
			return null;
		}

		public SortField getSortField(String sortColumn) {
			if (Cloumns.Count.name().equals(sortColumn)) {
				return SortField.count;
			} else if (Cloumns.GoodNumber.name().equals(sortColumn)) {
				return SortField.number;
			} else if (Cloumns.GoodsAttribute.name().equals(sortColumn)) {
				return SortField.spec;
			} else if (Cloumns.GoodsName.name().equals(sortColumn)) {
				return SortField.name;
			} else if (Cloumns.Unit.name().equals(sortColumn)) {
				return SortField.unit;
			}
			return null;
		}

		public SortType getSortType(SSortDirection sortDirection) {
			if (SSortDirection.ASC.equals(sortDirection)) {
				return SortType.Asc;
			} else {
				return SortType.Desc;
			}

		}

		
		@Override
		protected void doExport() {
			super.doExport();
		}

		@Override
		public void actionPerformed(String rowId, String actionName,
				String actionValue) {
//			if(ID_viewInventory.equals(actionName))
//			{
//				String[] locationInfo = actionValue.split(":");
////				lookInventory(rowId,new Point(Integer.parseInt(locationInfo[0]), Integer.parseInt(locationInfo[1])));
//			}
		}

//		private void lookInventory(String rowId, Point point) {
//			inventoryInfoWindow.refresh(GUID.valueOf(rowId), null,point,false);
//		}

		/*
		 * 获取指定对象ID
		 */
		public String getElementId(Object element) {
			OnlineSalesItem item = (OnlineSalesItem) element;
			return item.getGoodsId().toString();
		}

		@Override
		protected String getExportFileTitle() {
			return "线上销售";
		}

	}

}
