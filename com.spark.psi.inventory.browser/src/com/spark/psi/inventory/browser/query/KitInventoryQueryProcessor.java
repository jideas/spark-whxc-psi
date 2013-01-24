package com.spark.psi.inventory.browser.query;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.KitInventoryViewWindow;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.inventory.entity.KitInventoryDetail;
import com.spark.psi.publish.inventory.key.GetKitInventoryDetailListKey;

/**
 * 其他物品库存查询处理器
 */
public class KitInventoryQueryProcessor extends
		PSIListPageProcessor<KitInventoryDetail> {

	public final static String ID_COMBOLIST_STORE = "ComboList_Store";
	public final static String ID_LABEL_KITINVENTORYDETAIL_COUNT = "Label_KitInventoryDetail_Count";
	public final static String ID_TEXT_SEARCH = "Text_Search";
	public final static String ID_viewInventory = "viewInventory";
	private Label countLabel;
	private LWComboList storeList;
	private Text txtSearch;
	List<KitInventoryDetail> list = new ArrayList<KitInventoryDetail>();// 表格数据
	//查库窗口
	protected KitInventoryViewWindow inventoryInfoWindow;

	static enum Columns {
		KitName, KitDesc, Unit, Count
	}

	@Override
	public void process(Situation situation) {

		super.process(situation);

		storeList = this.createControl(ID_COMBOLIST_STORE, LWComboList.class,
				JWT.NO);
		countLabel = this.createControl(ID_LABEL_KITINVENTORYDETAIL_COUNT,
				Label.class, JWT.NONE);
		txtSearch = this.createControl(ID_TEXT_SEARCH, Text.class);

		StoreSource storeSource = new StoreSource(StoreStatus.ENABLE,StoreStatus.ONCOUNTING,StoreStatus.STOP);
		storeSource.setShowAllStoreItem(true);
		storeList.getList().setSource(storeSource);
		storeList.getList().setInput(null);
		if (CheckIsNull.isNotEmpty(storeSource.getFirstStoreId())) {
			storeList.setSelection(storeSource.getFirstStoreId().toString());
		}
		storeList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				doF5();
			}
		});

		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doF5();
			}
		});
		
		//处理查库动作的客户端事件
		table.addClientEventHandler(STable.CLIENT_EVENT_ACTION, "InventoryClient.handleActionPerformed");
		
		//初始化查库窗口
//		inventoryInfoWindow = new KitInventoryViewWindow(this.table);
	}

	private void doF5() {
		table.render();
	}

	public Object[] getElements(Context context, STableStatus tablestatus) {
		GUID storeId = GUID.valueOf(storeList.getText());
		if (storeId.equals(GUID.emptyID)) {
			storeId = null;
		}
		GetKitInventoryDetailListKey key = new GetKitInventoryDetailListKey(
				storeId, tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		key.setSearchText(txtSearch.getText());
		if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
			key.setSortField(GetKitInventoryDetailListKey.SortField
					.valueOf(tablestatus.getSortColumn()));
			if (tablestatus.getSortDirection() == SSortDirection.ASC) {
				key.setSortType(SortType.Asc);
			} else {
				key.setSortType(SortType.Desc);
			}
		}
		list = getContext().getList(KitInventoryDetail.class, key);
		int size = list.size();
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
		return list.toArray(new KitInventoryDetail[0]);
//		if (CheckIsNull.isNotEmpty(list)) {
//			showCount();
//			return list.toArray(new KitInventoryDetail[0]);
//		}
//		countLabel.setText("0");
//		return null;
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof KitInventoryDetail) {
			KitInventoryDetail item = (KitInventoryDetail) element;
			return item.getKitName() +""+ item.getKitDesc() +""+ item.getUnit();
		}
		return null;
	}

	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(ID_viewInventory)) {
			String[] locationInfo = actionValue.split(":");
			lookInventory(rowId,new Point(Integer.parseInt(locationInfo[0]), Integer.parseInt(locationInfo[1])));
		}
	}

	private void lookInventory(String rowId, Point point) {
		inventoryInfoWindow = new KitInventoryViewWindow(this.table);
		inventoryInfoWindow.refresh(rowId, null,point,false);
	}

	@Override
	protected String getExportFileTitle() {
		return "其他物品库存";
	}
}