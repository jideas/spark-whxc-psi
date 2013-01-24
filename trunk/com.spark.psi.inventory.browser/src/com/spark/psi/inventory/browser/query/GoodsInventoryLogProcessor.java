package com.spark.psi.inventory.browser.query;

import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.Store;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageProcessor;
import com.spark.psi.base.browser.goods.GoodsCategorySelectionMsg;
import com.spark.psi.inventory.browser.query.GoodsInventoryLogRender.GoodsInventoryLogQueryListRender;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.entity.InventoryLogItem;
import com.spark.psi.publish.inventory.key.GetInventoryLogKey;
import com.spark.psi.publish.inventory.key.GetInventoryLogKey.SortField;

public class GoodsInventoryLogProcessor extends GoodsCategoryFramePageProcessor {
	private GoodsInventoryLogQueryListProcessor queryListProcessor;

	public static enum Columns {
		Date, RelatedNumber, GoodsCode, GoodsNumber, GoodsName, Properties, Unit, Type, CheckedInCount, CheckedInAmount, CheckedOutCount, CheckedOutAmount;
	}

	@Override
	public void process(final Situation context) {
		getContext().regMessageListener(GoodsCategorySelectionMsg.class,
				new MessageListener<GoodsCategorySelectionMsg>() {
					public void onMessage(Situation context, GoodsCategorySelectionMsg message,
							MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
						String storeId = null;
						Date date0 = null, date1 = null;
						if (queryListProcessor != null) {
							storeId = Store.GoodsStoreId.toString();
							date0 = queryListProcessor.dateBegin.getDate();
							date1 = queryListProcessor.dateEnd.getDate();
						}
						ControllerPage page = (ControllerPage) rightArea.showPage(ControllerPage.NAME,
								new PageControllerInstance(new PageController(
										GoodsInventoryLogQueryListProcessor.class,
										GoodsInventoryLogQueryListRender.class), message.getCategoryId(), storeId,
										date0,date1));
						if (page != null) {
							queryListProcessor = (GoodsInventoryLogQueryListProcessor) page.getProcessor();
						}
					}
				});
		super.process(context);
	}

	public final static class GoodsInventoryLogQueryListProcessor extends PSIGoodsListPageProcessor {

		public final static String ID_DATE_BEGIN = "DATE_BEGIN";
		public final static String ID_DATE_End = "DATE_End";
		public final static String ID_BUTTON_QueryButton = "BUTTON_QueryButton";
		// 过滤仓库下拉列表
		public final static String ID_COMBOLIST_STORE = "ComboList_Store";
		// 商品数量
		public final static String ID_LABEL_INVENTORYLOG_COUNT = "Label_InventoryLog_Count";

		//
		private Label countLabel;
 
		private DatePicker dateBegin, dateEnd;
		private Button queryButton;

		@Override
		public void process(Situation situation) {

			super.process(situation);

			this.dateBegin = this.createSDatePickerControl(ID_DATE_BEGIN);
			if (null != this.getArgument3()) {
				this.dateBegin.setDate((Date) this.getArgument3());
			} else {
				this.dateBegin.setDate(DateUtil.getThisMonth());
			}
			this.dateEnd = this.createSDatePickerControl(ID_DATE_End);
			if (null != this.getArgument4()) {
				this.dateEnd.setDate((Date) this.getArgument4());
			} else {
				this.dateEnd.setDate(new Date());
			}
			this.queryButton = this.createButtonControl(ID_BUTTON_QueryButton);
			this.queryButton.setText("确定");
			this.queryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					table.render();
				}
			});

			countLabel = this.createControl(ID_LABEL_INVENTORYLOG_COUNT, Label.class, JWT.NONE);
		}

		@Override
		protected Object[] getElements(Context context, String searchText, GUID categoryId, STableStatus tablestatus) {
			GetInventoryLogKey key = new GetInventoryLogKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
			key.setGoodsCategoryId(categoryId);
			key.setInventoryType(InventoryType.Goods);
			key.setSearchText(searchText);
			key.setDateBegin(this.dateBegin.getDate().getTime());
			key.setDateEnd(dateEnd.getDate().getTime());
			key.setStoreId(Store.GoodsStoreId);
			// if (CheckIsNull.isNotEmpty(storeList.getText())) {
			// GUID storeId = GUID.valueOf(storeList.getText());
			// if(!storeId .equals(GUID.emptyID)) {
			// key.setStoreId(storeId);
			// }
			// }
			if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
				key.setSortField(getSortField(tablestatus.getSortColumn()));
				key.setSortType(getSortType(tablestatus.getSortDirection()));
			}
			List<InventoryLogItem> list = context.getList(InventoryLogItem.class, key);
			int size = list.size();
			if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
				String preSize = countLabel.getText();
				if (StringHelper.isNotEmpty(preSize)) {
					size += Integer.parseInt(preSize);
				}
			}
			countLabel.setText(String.valueOf(size));
			return list.toArray(new InventoryLogItem[0]);
//			if (CheckIsNull.isNotEmpty(list)) {
//				InventoryLogItem[] items = new InventoryLogItem[list.size()];
//				for (int i = 0; i < list.size(); i++) {
//					items[i] = list.get(i);
//				}
//				countLabel.setText(String.valueOf(list.size()));
//				return items;
//			}
//			countLabel.setText("0");
//			return null;
		}

		private SortType getSortType(SSortDirection sortDirection) {
			if (SSortDirection.ASC.equals(sortDirection)) {
				return SortType.Asc;
			} else {
				return SortType.Desc;
			}
		}

		private SortField getSortField(String sortColumn) {
			if (Columns.CheckedInCount.name().equals(sortColumn)) {
				return SortField.CheckedInCount;
			} else if (Columns.Date.name().equals(sortColumn)) {
				return SortField.Date;
			} else if (Columns.GoodsCode.name().equals(sortColumn)) {
				return SortField.GoodsCode;
			} else if (Columns.GoodsName.name().equals(sortColumn)) {
				return SortField.GoodsName;
			} else if (Columns.Properties.name().equals(sortColumn)) {
				return SortField.Properties;
			} else if (Columns.Type.name().equals(sortColumn)) {
				return SortField.Type;
			} else if (Columns.Unit.name().equals(sortColumn)) {
				return SortField.Unit;
			} else if (Columns.CheckedInAmount.name().equals(sortColumn)) {
				return SortField.CheckedInAmount;
			} else if (Columns.CheckedOutAmount.name().equals(sortColumn)) {
				return SortField.CheckedOutAmount;
			} else if (Columns.CheckedOutCount.name().equals(sortColumn)) {
				return SortField.CheckedOutCount;
			} else if (Columns.RelatedNumber.name().equals(sortColumn)) {
				return SortField.RelatedNumber;
			}

			return null;
		}

		/*
		 * 获取指定对象ID
		 */
		public String getElementId(Object element) {
			return ((InventoryLogItem) element).getId().toString();
		}

		@Override
		protected String getExportFileTitle() {
			return "库存流水";
		}
	}
}
