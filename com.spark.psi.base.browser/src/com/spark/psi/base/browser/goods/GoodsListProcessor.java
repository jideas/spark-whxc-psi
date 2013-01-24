package com.spark.psi.base.browser.goods;

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
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIGoodsListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey;
import com.spark.psi.publish.base.goods.task.ChangeGoodsStatusTask;
import com.spark.psi.publish.base.goods.task.DeleteGoodsTask;

public abstract class GoodsListProcessor extends PSIGoodsListPageProcessor {

	public final static String ID_Label_Count = "Label_Count";
	public final static String ID_Button_NewGoods = "Button_NewGoods";
	public final static String ID_Button_status = "Button_status";
	public final static String ID_Button_Delete = "Button_Delete";

	private Label countLabel;
	private Button newButton;
	private Button statusButton;
	private Button deleteButton; 

	private GoodsStatus status;
	private boolean isNoPriceOnly = false;
	
	private LoginInfo loginInfo;

	
	@Override
	public void init(Situation situation) {
		super.init(situation);
		loginInfo = situation.find(LoginInfo.class);
		this.status = isViewOnSale() ? GoodsStatus.ON_SALE : GoodsStatus.STOP_SALE;
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);

		countLabel = this.createControl(ID_Label_Count, Label.class);
		newButton = this.createControl(ID_Button_NewGoods, Button.class);
		statusButton = this.createControl(ID_Button_status, Button.class);
		deleteButton = this.createControl(ID_Button_Delete, Button.class);
		
		newButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				editGoodsInfoDetail(null);
			}
		});

		statusButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (table.getSelection() == null) {
					alert("请先选择商品！");
					return;
				}
				changeGoodsStatus(table.getSelections());
				table.render();
			}
		});

		deleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (table.getSelection() == null) {
					alert("请先选择商品！");
					return;
				}
				deleteGoods(table.getSelections());
			}
		});

//		table.addActionListener(new SActionListener() {
//
//			public void actionPerformed(String rowId, String actionName,
//					String actionValue) {
////				if (Action.OffSale.name().equals(actionName)) {
////					changeGoodsStatus(new String[] { rowId });
////				} else if (Action.OnSale.name().equals(actionName)) {
////					ChangeGoodsStatusTask onSaleTask = new ChangeGoodsStatusTask(
////							true, GUID.tryValueOf(rowId));
////					getContext().handle(onSaleTask);
////				} else if (Action.Delete.name().equals(actionName)) {
////					DeleteGoodsTask delTask = new DeleteGoodsTask(GUID
////							.tryValueOf(rowId));
////					getContext().handle(delTask);
////					table.removeRow(rowId);
////				} else if(Action.InventoryInfo.name().equals(actionName)) {
////					PageController pc = new PageController(GoodsInventoryInfoProcessor.class, GoodsInventoryInfoRender.class);
////					PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId), false);
////					WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
////					style.setSize(850, 400);
////					MsgRequest request = new MsgRequest(pci, "库存信息", style);
////					getContext().bubbleMessage(request);
////					return;
////				}
//				tableActionPerformed();
//				table.render();
//			}
//		});

		table.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String[] rowIds = table.getSelections();
				if(!deleteButton.isDisposed()) {
					deleteButton.setEnabled(true);
				}
				if (rowIds == null)
					return;
				for (String rowId : rowIds) {
					GoodsInfo goodsInfo = getContext().find(GoodsInfo.class,
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
		
		if (!loginInfo.hasAuth(Auth.SubFunction_GoodsMange_UpdateGoods)) {
			newButton.dispose();
		}
		if(!(loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ChangeGoodsStatus))) {
			statusButton.dispose();
			deleteButton.dispose();
		}
	}

	
	private void deleteGoods(final String[] ids) { 
		confirm("确定要删除所选商品吗?", new Runnable(){
			
			public void run(){
				GUID[] goodsIds = new GUID[ids.length];
				for (int idIndex = 0; idIndex < ids.length; idIndex++) {
					goodsIds[idIndex] = GUID.tryValueOf(ids[idIndex]);
				}
				DeleteGoodsTask delTask = new DeleteGoodsTask(goodsIds);
				getContext().handle(delTask);
				table.render();
			}
		});
	}

	private void changeGoodsStatus(String[] ids) {
		GUID[] goodsIds = new GUID[ids.length];
		for (int idIndex = 0; idIndex < ids.length; idIndex++) {
			goodsIds[idIndex] = GUID.tryValueOf(ids[idIndex]);
		}
		ChangeGoodsStatusTask statusTask;
		if (status == GoodsStatus.ON_SALE) {
			statusTask = new ChangeGoodsStatusTask(false, goodsIds);
		} else {
			statusTask = new ChangeGoodsStatusTask(true, goodsIds);
		}
		getContext().handle(statusTask);
	}

	private void editGoodsInfoDetail(final GUID goodsId) {
		final PageController pc = new PageController(
				GoodsDetailPageProcessor.class, GoodsDetailPageRender.class);
		PageControllerInstance newPci = new PageControllerInstance(pc, goodsId,
				getCategoryId(), isViewJointOnly());
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(800, 500);
		MsgRequest newGoodsWinRequest = new MsgRequest(newPci, "商品详情",
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
	public Object[] getElements(Context context, String searchText,
			GUID categoryId, STableStatus tablestatus) {
		GetGoodsInfoListKey key;
		if (categoryId == null) {
			key = new GetGoodsInfoListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		} else {
			key = new GetGoodsInfoListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
			key.setCateoryId(categoryId);
		}
		if (null != tablestatus.getSortDirection() && SSortDirection.ASC.equals(tablestatus.getSortDirection())) {
			key.setSortType(SortType.Asc);
		} else {
			key.setSortType(SortType.Desc);
		}
		
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(GetGoodsInfoListKey.SortField.valueOf(tablestatus.getSortColumn()));
		} else {
			key.setSortField(GetGoodsInfoListKey.SortField.None);
		}
		key.setSearchText(searchText);
		key.setNopriceOnly(isNoPriceOnly); 
		//  加状态和是否联营
		key.setStatus(isViewOnSale() ? GoodsStatus.ON_SALE : GoodsStatus.STOP_SALE);
		key.setJointVenture(isViewJointOnly());
		@SuppressWarnings("unchecked")
		ListEntity<GoodsInfo> listEntity = (ListEntity<GoodsInfo>) context
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
	public String getElementId(Object element) {
		return ((GoodsInfo) element).getId().toString();
	}

	protected boolean isViewJointOnly() {
		return false;
	}
	
	protected boolean isViewOnSale() {
		return true;
	}
	
	@Override
	public String[] getTableActionIds() {
		List<String> actionList = new ArrayList<String>();
		if(loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ChangeGoodsStatus)) {
			if (status == GoodsStatus.ON_SALE) {
				actionList.add(Action.OffSale.name());
			} else {
				actionList.add(Action.OnSale.name());
			}
		}
//		if(loginInfo.hasAuth(Auth.SubFunction_GoodsMange_EditThreshold)) {
//			actionList.add(Action.InventoryInfo.name());
//		}
		actionList.add(Action.Delete.name());
		return actionList.toArray(new String[actionList.size()]);
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		GoodsInfo goodsInfo = (GoodsInfo) element;
		List<String> actionList = new ArrayList<String>();
		if (loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ChangeGoodsStatus)) {
			if (status == GoodsStatus.ON_SALE) {
				actionList.add(Action.OffSale.name());
			} else {
				actionList.add(Action.OnSale.name());
			}
		}
//		if (loginInfo.hasAuth(Auth.SubFunction_GoodsMange_EditThreshold)) {
//			actionList.add(Action.InventoryInfo.name());
//		}
		if (loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ChangeGoodsStatus) && !goodsInfo.isRefFlag()) {
			actionList.add(Action.Delete.name());
		}
		return actionList.toArray(new String[actionList.size()]);
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if ("detail".equals(actionName)) {
			editGoodsInfoDetail(GUID.valueOf(rowId)); // XXX：表格控件缺陷，无法直接获取到rowId
		} else if (Action.OffSale.name().equals(actionName)) {
			changeGoodsStatus(new String[] { rowId });
			table.render();
		} else if (Action.OnSale.name().equals(actionName)) {
			ChangeGoodsStatusTask onSaleTask = new ChangeGoodsStatusTask(
					true, GUID.tryValueOf(rowId));
			getContext().handle(onSaleTask);
			table.render();
		} else if (Action.Delete.name().equals(actionName)) {
			DeleteGoodsTask delTask = new DeleteGoodsTask(GUID
					.tryValueOf(rowId));
			getContext().handle(delTask);
			table.removeRow(rowId);
			table.render();
		} 
//		else if(Action.InventoryInfo.name().equals(actionName)) {
//			PageController pc = new PageController(GoodsInventoryInfoProcessor.class, GoodsInventoryInfoRender.class);
//			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId), false);
//			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
//			style.setSize(850, 400);
//			MsgRequest request = new MsgRequest(pci, "库存信息", style);
//			getContext().bubbleMessage(request);
//			return;
//		}
	}

}
