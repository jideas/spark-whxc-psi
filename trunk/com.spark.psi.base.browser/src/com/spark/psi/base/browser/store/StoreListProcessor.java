package com.spark.psi.base.browser.store;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.base.store.task.ChangeStoreStatusTask;
import com.spark.psi.publish.base.store.task.DeleteStoreTask;

/**
 * 仓库列表界面处理器
 * 
 */
public class StoreListProcessor extends PSIListPageProcessor<StoreItem> {

	public final static String ID_BUTTON_NEWSTORE = "Button_NewStore";
	public final static String ID_LABEL_STORE_COUNT = "Label_StoreCount";

	private Label countLabel;
	

	public static enum Columns {
		Name, Address, Keepers, status,仓库
	}

	@Override
	public void init(Situation context){
	    super.init(context);
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);
		LoginInfo login = getContext().find(LoginInfo.class);
		Button button = this.createControl(ID_BUTTON_NEWSTORE, Button.class,
				JWT.NONE);
		if(button != null){
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edit(null);
				}
			});
		}
		if(!login.hasAuth(Auth.SubFunction_StoreMange_Update)){
			button.dispose();
		}
		countLabel = this.createControl(ID_LABEL_STORE_COUNT, Label.class,
				JWT.NONE);
	}

	@SuppressWarnings("unchecked")
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetStoreListKey key = new GetStoreListKey(StoreStatus.ALL);
		if(!StringUtils.isEmpty(tablestatus.getSortColumn())){
			key.setSortField(GetStoreListKey.SortField.valueOf(tablestatus.getSortColumn()));
			if(tablestatus.getSortDirection()==SSortDirection.ASC){
				key.setSortType(SortType.Asc);
			}else{
				key.setSortType(SortType.Desc);
			}
		}
		ListEntity<StoreItem> result = context.find(ListEntity.class, key);
		if (result != null) {
			List<StoreItem> itemList = result.getItemList();
			countLabel.setText(String.valueOf(result.getTotalCount()));
			return itemList.toArray();
		} else {
			countLabel.setText("0");
		}
		return null;
	}

	public String getElementId(Object element) {
		StoreItem item = (StoreItem) element;
		return item.getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		List<String> list = new ArrayList<String>();
		list.add(Action.Active.name());
		list.add(Action.DeActive.name());
		if(getContext().find(LoginInfo.class).hasAuth(Auth.SubFunction_StoreMange_Update)){
			list.add(Action.Delete.name());
		}
		return list.toArray(new String[0]);
	}

	protected String[] getElementActionIds(Object element) {
		StoreItem item = (StoreItem) element;
		String[] result = new String[item.getAction().length];
		for(int i = 0; i < result.length; i++){
	        result[i] = item.getAction()[i].name();
        }
		return result;
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Active.name())) {
			StoreInfo storeInfo = getContext().find(StoreInfo.class,
					GUID.valueOf(rowId));
			if (storeInfo.getStatus() == StoreStatus.STOP) {
				ChangeStoreStatusTask task = new ChangeStoreStatusTask(
						GUID.valueOf(rowId), StoreStatus.ENABLE);
				getContext().handle(task);
				refresh();
			} else if (storeInfo.getStatus() == StoreStatus.DISABLED) {
				BaseFunction[] functions = new BaseFunction[] {
						new BaseFunction(new PageControllerInstance(
								"InitStoreGoodsListPage", rowId), "材料库存"),
						new BaseFunction(new PageControllerInstance(
								"InitStoreKitListPage", rowId), "其他库存") };
				MsgRequest request = new MsgRequest(functions, "仓库启用设置");
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
		} else if (actionName.equals(Action.DeActive.name())) {
			ChangeStoreStatusTask task = new ChangeStoreStatusTask(
					GUID.valueOf(rowId), StoreStatus.STOP);
			getContext().handle(task);
			refresh();
		} else if (actionName.equals(Action.Delete.name())) {
			confirm("确定要删除所选仓库吗？",
							new Runnable() {
								public void run() {
									getContext().handle(
											new DeleteStoreTask(GUID
													.valueOf(rowId)));
									refresh();
								}
							});
		} else if (actionName.equals("edit")) {
			edit(GUID.valueOf(rowId));
		}
	}

	private void refresh() {
		table.render();
	}

	private void edit(GUID storeId) {
		PageControllerInstance pci = new PageControllerInstance(
				new PageController(StoreEditProcessor.class,
						StoreEditRender.class), storeId);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(680, 400);
		MsgRequest request = new MsgRequest(pci, storeId == null ? "新增仓库"
				: "编辑仓库", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	protected String getExportFileTitle() {
		return "仓库列表";
	}
}
