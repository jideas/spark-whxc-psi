package com.spark.psi.base.browser.station;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.station.entity.StationItem;
import com.spark.psi.publish.base.station.key.GetStationListKey;
import com.spark.psi.publish.base.station.key.GetStationListKey.SortField;
import com.spark.psi.publish.base.station.task.StopStationTask;

public class StationListProcessor extends PSIListPageProcessor<StationItem> {

	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	public final static String ID_BUTTON_DETAILSEARCH = "Button_DetailSearch";
	public final static String ID_BUTTON_NEW = "Button_New";

	public static enum Columns {
		Number, ShortName, Address, Manager, MobileNo;
	}

	private Text searchText;

	private Label countLabel;

	private Button newStation;

	@Override
	public void process(Situation context) {
		super.process(context);
		this.searchText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class, JWT.NONE);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		countLabel = createControl(ID_LABEL_COUNT, Label.class);
		this.newStation = createControl(ID_BUTTON_NEW, Button.class);
		this.newStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doNewButtonDown();
			}
		});

	}

	private void doNewButtonDown() {
		WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		style.setSize(650, 300);
		PageControllerInstance controllerInstance = new PageControllerInstance("EditStationPage");
		MsgRequest request = new MsgRequest(controllerInstance, "新增站点", style);
		// 回应
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);

	}

	@Override
	public String getElementId(Object element) {
		return ((StationItem) element).getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Active.name(), Action.DeActive.name() };
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		StationItem item = (StationItem) element;
		if (item.isStop()) {
			return new String[] { Action.Active.name() };
		} else {
			return new String[] { Action.DeActive.name() };
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetStationListKey key = new GetStationListKey();
		if (!StringUtils.isEmpty(tablestatus.getSortColumn())) {
			String column = tablestatus.getSortColumn();
			if (Columns.Number.name().equals(column) || Columns.ShortName.name().equals(column)) {
				key.setSortField(GetStationListKey.SortField.valueOf(column));
			} else {
				key.setSortField(SortField.None);
			}
			if (tablestatus.getSortDirection() == SSortDirection.ASC) {
				key.setSortType(SortType.Asc);
			} else {
				key.setSortType(SortType.Desc);
			}
		}
		key.setSearchText(searchText.getText());
		ListEntity<StationItem> listEntity = context.find(ListEntity.class, key);
		if (listEntity != null) {
			countLabel.setText(String.valueOf(listEntity.getItemList().size()));
			return listEntity.getItemList().toArray();
		}
		countLabel.setText("0");
		return null;
	}

	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Active.name())) {
			confirm("确定启用所选站点？", new Runnable() {
				public void run() {
					try {
						getContext().handle(new StopStationTask(GUID.valueOf(rowId), false));
					} catch (IllegalArgumentException e) {
						alert(e.getMessage());
						return;
					}
					table.render();
				}
			});
		} else if (actionName.equals(Action.DeActive.name())) {
			confirm("确定停用所选站点？", new Runnable() {
				public void run() {
					try {
						getContext().handle(new StopStationTask(GUID.valueOf(rowId), true));
					} catch (IllegalArgumentException e) {
						alert(e.getMessage());
						return;
					}
					table.render();
				}
			});
		} else if ("edit".equals(actionName)) {
			GUID stationId = GUID.valueOf(rowId);
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(650, 300);
			PageControllerInstance controllerInstance = new PageControllerInstance("EditStationPage", stationId);
			MsgRequest request = new MsgRequest(controllerInstance, "新增站点", style);
			// 回应
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}

	}

	@Override
	protected String getExportFileTitle() {
		return "站点";
	}
}
