package com.spark.psi.inventory.browser.checkout;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.popup.PopupWindow.Direction;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.inventory.browser.count.KitSheetDetailInfo.Kit;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.inventory.entity.KitInventoryDetail;
import com.spark.psi.publish.inventory.key.GetKitInventoryDetailListKey;

/**
 * 物品选择 1
 */
public class KitsSelectProcessor extends PSIListPageProcessor<KitInventoryDetail> {

	public final static String ID_TEXT_SEARCH = "Text_Search";
	public final static String ID_Label_SelectedCount = "Label_SelectedCount";
	public final static String ID_Button_Finish = "Button_Finish";
	public final static String ID_Button_Cancel = "Button_Cancel";
	private GUID storeId;
	// private Composite selectedArea;
	private Composite selectedKitsPage;
	private Label selectedCountLabel;
	private Text searchText;
	private Map<String, Kit> selectedItemList = new LinkedHashMap<String, Kit>();
	private List<KitInventoryDetail> listKit;
	private Button btnFinish;
	private Button btnCancel;

	static enum Columns {
		kitName, kitDesc, unit, count
	}

	@Override
	public void process(final Situation context) {
		storeId = (GUID) this.getArgument();
		super.process(context);
		// selectedArea = this.createControl(ID_Area_Selected, Composite.class);
		searchText = this.createControl(ID_TEXT_SEARCH, Text.class);
		btnFinish = this.createControl(ID_Button_Finish, Button.class);
		btnCancel = this.createControl(ID_Button_Cancel, Button.class);
		selectedCountLabel = this.createControl(ID_Label_SelectedCount, Label.class);
		initData();
		initEvent();
	}

	private void initData() {
		GetKitInventoryDetailListKey key = new GetKitInventoryDetailListKey(storeId, 0, Integer.MAX_VALUE, true);
		key.setSearchText(searchText.getText());
		listKit = getContext().getList(KitInventoryDetail.class, key);
		updateCountLabel();
	}

	private void initEvent() {
		// TODO:查询
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initData();
				table.render();
			}
		});
		// TODO:完成选择
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedItemList.isEmpty()) {
					alert("请选择物品！");
					return;
				}
				getContext().bubbleMessage(new MsgResponse(true, new SelectItem(selectedItemList)));
			}
		});
		// TODO:取消选择
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true, null));
			}
		});
		// TODO：鼠标移动上去显示层，暂时放在这里实现，应该抽象到MsgRequest体系中
		SMenuWindow menuWindow = new SMenuWindow(null, selectedCountLabel, Direction.Down);
		menuWindow.bindTargetControl(selectedCountLabel);
		menuWindow
				.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW, "PSIBase.GoodsSelect.onSelectedGoodsWindowShow");
		Composite windowArea = menuWindow.getContentArea();
		windowArea.setLayout(new GridLayout());
		final Composite area = new Composite(windowArea);
		GridData gd = new GridData();
		gd.widthHint = 400;
		gd.heightHint = 200;
		area.setLayoutData(gd);
		// XXX：由于表格控件缺陷，目前需要每次都刷新整个表格
		menuWindow.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				selectedKitsPage = area.showPage(ControllerPage.NAME, new PageControllerInstance(new PageController(
						SelectedKitsItemListProcessor.class, SelectedKitsItemListRender.class), selectedItemList));
				// 消息类，消息监听
				selectedKitsPage.getContext().regMessageListener(SelectItem.class, new MessageListener<SelectItem>() {
					public void onMessage(Situation context, SelectItem message,
							MessageTransmitter<SelectItem> transmitter) {
						selectedItemList = null;
						selectedItemList = message.getSelectedItemList();
						updateCountLabel();
					}
				});
			}
		});
	}

	/**
	 * 更新已选择材料数量的最新显示
	 */
	private void updateCountLabel() {
		selectedCountLabel.setText(String.valueOf(selectedItemList.size()));
		selectedCountLabel.getParent().getParent().layout();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (CheckIsNull.isNotEmpty(listKit)) {
			return listKit.toArray();
		} else {
			return null;
		}
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof String) {
			return (String) element;
		} else {
			KitInventoryDetail item = (KitInventoryDetail) element;
			return GUID.MD5Of(item.getKitName() + "~" + item.getKitDesc() + "~" + item.getUnit()).toString();
			// GUID.MD5Of(this.kitName + "~" +this.kitDesc + "~"
			// +this.kitUnit).toString();
		}
	}

	public String getValue(Object element, String columnName) {
		if (element instanceof String) {
			return "";
		} else if (element instanceof KitInventoryDetail) {
			KitInventoryDetail item = (KitInventoryDetail) element;
			Columns column = null;
			try {
				column = Columns.valueOf(columnName);
			} catch (Exception e) {
			}
			if (column != null) {
				switch (column.ordinal()) {
				case 0:
					return item.getKitName();
				case 1:
					return item.getKitDesc();
				case 2:
					return item.getUnit();
				default:
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public SNameValue[] getExtraData(Object element) {
		if (element instanceof KitInventoryDetail) {
			KitInventoryDetail item = (KitInventoryDetail) element;
			return new SNameValue[] { new SNameValue(KitCheckingOutDetailProcessor.Columns.count.name(), String
					.valueOf(item.getCount())) };
		}
		return null;
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Add.name() };
	}

	@Override
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Add.name())) {
			if (selectedItemList.containsKey(rowId)) {
				return;
			}
			String[] values = table.getEditValue(rowId, Columns.kitName.name(), Columns.kitDesc.name(), Columns.unit
					.name());
			// 添加到集合里面，返回到请求打开的窗口
			String[] extraData = table.getExtraData(rowId, KitCheckingOutDetailProcessor.Columns.count.name());
			Kit itemInfo = new Kit(values[0], values[1], values[2], Double.parseDouble(extraData[0]));
			selectedItemList.put(rowId, itemInfo);
			updateCountLabel();
		}
	}

	public final static class SelectItem {
		private Map<String, Kit> selectedItemList;

		public SelectItem(Map<String, Kit> selectedItemList) {
			this.selectedItemList = selectedItemList;
		}

		public Map<String, Kit> getSelectedItemList() {
			return selectedItemList;
		}
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}