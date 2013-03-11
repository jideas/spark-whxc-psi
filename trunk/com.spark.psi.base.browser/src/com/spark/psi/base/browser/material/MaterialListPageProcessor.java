package com.spark.psi.base.browser.material;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.key.GetMaterialsInfoListKey;
import com.spark.psi.publish.base.materials.task.ChangeMaterialsStatusTask;
import com.spark.psi.publish.base.materials.task.DeleteMaterialsTask;

public class MaterialListPageProcessor extends PSIGoodsListPageProcessor {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Button_New = "Button_New";
	public static final String ID_Button_Delete = "Button_Delete";
	public final static String ID_Button_State = "Button_State";
	
	public static enum ColumnName {
		supplierName, percentage, name, code, number, unit, avgPrice, planPrice, standardPrice
	}
	
	private Label countLabel;
	private Button newButton;
	private Button stateButton;
	private Button deleteButton; 

	private MaterialsStatus state;
	private boolean isShowJoint = false;
	
	private LoginInfo loginInfo;


	@Override
	public void init(Situation situation) {
		super.init(situation);
		this.state = (MaterialsStatus) this.getArgument2();
		loginInfo = situation.find(LoginInfo.class);
		if (null != getArgument3()) {
			isShowJoint = (Boolean) getArgument3();
		}
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		
		countLabel = this.createControl(ID_Label_Count, Label.class);
		newButton = this.createControl(ID_Button_New, Button.class);
		stateButton = this.createControl(ID_Button_State, Button.class);
		if (state == MaterialsStatus.ON_SALE) {
			stateButton.setText(" 停售材料 ");
		} else {
			stateButton.setText(" 恢复销售 ");
		}
		deleteButton = this.createControl(ID_Button_Delete, Button.class);

		newButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				editGoodsInfoDetail(null, "新增材料");
			}
		});

		stateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (table.getSelection() == null) {
					alert("请先选择材料！");
					return;
				}
				changeGoodsState(table.getSelections());
				table.render();
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				confirm("确定要删除所选材料吗？", new Runnable() {
					
					public void run() {
						if (table.getSelection() == null) {
							alert("请先选择材料！");
							return;
						}
						deleteGoods(table.getSelections());
					}
				});
			}
		});

		table.addActionListener(new SActionListener() {

			public void actionPerformed(final String rowId, String actionName,
					String actionValue) {
				if (Action.OffSale.name().equals(actionName)) {
					changeGoodsState(new String[] { rowId });
					table.render();
				} else if (Action.OnSale.name().equals(actionName)) {
					ChangeMaterialsStatusTask onSaleTask = new ChangeMaterialsStatusTask(
							true, GUID.tryValueOf(rowId));
					getContext().handle(onSaleTask);
					table.render();
				} else if (Action.Delete.name().equals(actionName)) {
					confirm("确定要删除该材料吗？", new Runnable() {
						public void run() {
							DeleteMaterialsTask delTask = new DeleteMaterialsTask(GUID
									.tryValueOf(rowId));
							getContext().handle(delTask);
							table.removeRow(rowId);
							table.render();
						}
					});
				} else if(Action.InventoryInfo.name().equals(actionName)) {
					PageController pc = new PageController(MaterialInventoryInfoProcessor.class, MaterialInventoryInfoRender.class);
					PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId), false);
					WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
					style.setSize(850, 400);
					MsgRequest request = new MsgRequest(pci, "库存信息", style);
					getContext().bubbleMessage(request);
					table.render();
					return;
				} 
			}
		});

		table.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String[] rowIds = table.getSelections();
				if(!deleteButton.isDisposed()) {
					deleteButton.setEnabled(true);
				}
				if (rowIds == null)
					return;
				for (String rowId : rowIds) {
					MaterialsInfo goodsInfo = getContext().find(MaterialsInfo.class,
							GUID.tryValueOf(rowId));
					if (goodsInfo.isRefFlag() && !deleteButton.isDisposed()) {
						deleteButton.setEnabled(false);
						break;
					}
				}
			}
		});
		
		//销售员工不能新增商品
//		if (loginInfo.hasAuth(Auth.Sales) && !loginInfo.hasAuth(Auth.SalesManager)) {
//			newButton.dispose();
//		}
		if (!loginInfo.hasAuth(Auth.SubFunction_MaterialManage_UpdateMaterial)) {
			newButton.dispose();
		}
		
		if(!(loginInfo.hasAuth(Auth.SubFunction_MaterialManage_ChangeMaterialStatus))) {
			stateButton.dispose();
			deleteButton.dispose();
		}
	}

	private void deleteGoods(final String[] ids) { 
		confirm("确定要删除所选材料吗?", new Runnable(){
			
			public void run(){
				GUID[] goodsIds = new GUID[ids.length];
				for (int idIndex = 0; idIndex < ids.length; idIndex++) {
					goodsIds[idIndex] = GUID.tryValueOf(ids[idIndex]);
				}
				DeleteMaterialsTask delTask = new DeleteMaterialsTask(goodsIds);
				getContext().handle(delTask);
				table.render();
			}
		});
	}

	private void changeGoodsState(String[] ids) {
		GUID[] goodsIds = new GUID[ids.length];
		for (int idIndex = 0; idIndex < ids.length; idIndex++) {
			goodsIds[idIndex] = GUID.tryValueOf(ids[idIndex]);
		}
		ChangeMaterialsStatusTask stateTask;
		if (state == MaterialsStatus.ON_SALE) {
			stateTask = new ChangeMaterialsStatusTask(false, goodsIds);
		} else {
			stateTask = new ChangeMaterialsStatusTask(true, goodsIds);
		}
		getContext().handle(stateTask);
	}

	private void editGoodsInfoDetail(final GUID goodsId, String title) {
		final PageController pc = new PageController(
				MaterialDetailPageProcessor.class, MaterialDetailPageRender.class);
		PageControllerInstance newPci = new PageControllerInstance(pc, goodsId,
				getCategoryId(), isShowJoint);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(620, 350);
		MsgRequest newGoodsWinRequest = new MsgRequest(newPci, title,
				windowStyle);
		newGoodsWinRequest.setResponseHandler(new ResponseHandler() {

			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
					table.render();
			}
		});
		getContext().bubbleMessage(newGoodsWinRequest);
	}


	@Override
	public String getElementId(Object element) {
		return ((MaterialsInfo) element).getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		List<String> actionList = new ArrayList<String>();
		if(loginInfo.hasAuth(Auth.SubFunction_MaterialManage_ChangeMaterialStatus)) {
			if (state == MaterialsStatus.ON_SALE) {
				actionList.add(Action.OffSale.name());
			} else {
				actionList.add(Action.OnSale.name());
			}
		}
		if(loginInfo.hasAuth(Auth.StoreKeeperManager)||loginInfo.hasAuth(Auth.StoreKeeper))
		actionList.add(Action.InventoryInfo.name());
		actionList.add(Action.Delete.name());
		return actionList.toArray(new String[actionList.size()]);
//		if(!(loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ChangeGoodsState))) {
//			return null;
//		}
//		if (state == GoodsState.ON_SALE) {
////			return new String[] { Action.OffSale.name(), Action.Delete.name(),
////					Action.InventoryInfo.name(), Action.PurchaseInfo.name(), Action.SalesInfo.name() };
//			return new String[] { Action.OffSale.name(), Action.Delete.name(), Action.InventoryInfo.name()};
//		} else {
////			return new String[] { Action.OnSale.name(), Action.Delete.name(),
////					Action.InventoryInfo.name(), Action.PurchaseInfo.name(), Action.SalesInfo.name() };
//			return new String[] { Action.OnSale.name(), Action.Delete.name(), Action.InventoryInfo.name()};
//		}
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		MaterialsInfo MaterialsInfo = (MaterialsInfo) element;
		List<String> actionList = new ArrayList<String>();
		if (loginInfo.hasAuth(Auth.SubFunction_MaterialManage_ChangeMaterialStatus)) {
			if (state == MaterialsStatus.ON_SALE) {
				actionList.add(Action.OffSale.name());
			} else {
				actionList.add(Action.OnSale.name());
			}
		}
//		if (loginInfo.hasAuth(Auth.SubFunction_GoodsMange_EditThreshold)) {
//			actionList.add(Action.InventoryInfo.name());
//		}
		if (!isShowJoint && state == MaterialsStatus.ON_SALE) {
			actionList.add(Action.InventoryInfo.name());
		}
		if (loginInfo.hasAuth(Auth.SubFunction_MaterialManage_UpdateMaterial) && !MaterialsInfo.isRefFlag()) {
			actionList.add(Action.Delete.name());
		}
		return actionList.toArray(new String[actionList.size()]);
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)) {
			editGoodsInfoDetail(GUID.valueOf(rowId), "材料详情");
		}
	}

	@Override
	protected Object[] getElements(Context context, String searchText,
			GUID categoryId, STableStatus tablestatus) {
		GetMaterialsInfoListKey key;
		if (categoryId == null) {
			key = new GetMaterialsInfoListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		} else {
			key = new GetMaterialsInfoListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
			key.setCateoryId(categoryId);
		}
		if (null != tablestatus.getSortDirection() && SSortDirection.ASC.equals(tablestatus.getSortDirection())) {
			key.setSortType(SortType.Asc);
		} else {
			key.setSortType(SortType.Desc);
		}
		
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(GetMaterialsInfoListKey.SortField.valueOf(tablestatus.getSortColumn()));
		} else {
			key.setSortField(GetMaterialsInfoListKey.SortField.None);
		}
		key.setSearchText(searchText);
		key.setStatus(state);
		key.setJointVenture(isShowJoint);
		@SuppressWarnings("unchecked")
		ListEntity<MaterialsInfo> listEntity = (ListEntity<MaterialsInfo>) context
				.find(ListEntity.class, key);
		if (listEntity != null) {
			int size = listEntity.getItemList().size();
			if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
				String preSize = countLabel.getText();
				if (StringHelper.isNotEmpty(preSize)) {
					size += Integer.parseInt(preSize);
				}
			}
			countLabel.setText(String.valueOf(size));
//			countLabel.setText(String.valueOf(listEntity.getTotalCount()));
			return listEntity.getItemList().toArray();
		} else {
			return null;
		}
	}

	@Override
	protected String getExportFileTitle() {
		return "材料列表";
	}



}
