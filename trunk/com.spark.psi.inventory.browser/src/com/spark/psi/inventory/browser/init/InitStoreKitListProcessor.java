package com.spark.psi.inventory.browser.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.store.entity.StoreInfo;
import com.spark.psi.publish.base.store.entity.StoreInitKitItem;
import com.spark.psi.publish.inventory.task.SaveInitKitItemsTask;

/**
 * <p>初始化仓库其他物品库存界面处理器</p>
 * @author 龚海淘
 * @version 2012-4-24
 */
public class InitStoreKitListProcessor extends PSIListPageProcessor<StoreInitKitItem> implements IDataProcessPrompt {
	
	public final static String ID_Button_AddKitItem = "Button_AddKitItem";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Label_StoreName = "Label_StoreName";
	public final static String ID_Label_KitItemCount = "Label_KitItemCount";	
	private String storeId;
	private StoreInfo storeInfo;
	private Map<String, StoreInitKitItem> kits = new HashMap<String, StoreInitKitItem>();
	private Label lblCount;
	private Label lblStore;
	private Button btnSave;
	private Button btnAdd;
	public static enum Columns {
		kitName, kitDescription, unit, count
	}
	LoginInfo login;

	@Override
	public void init(Situation context) {
		storeId = (String) this.getArgument();
		System.out.println(storeId);
	}
	
	@Override
	public void process(Situation situation) {		
		
		//当前用户登陆信息，功能权限判断
		login = getContext().get(LoginInfo.class);
//		System.out.println(login.getEmployeeInfo().getName());
//		if(login.hasAuth(Auth.MainFunction_ApprovalManage))//判断登陆用户是否有此操作权限
//			System.out.println("有《"+Auth.MainFunction_ApprovalManage.getName()+"》权限");
//		
		super.process(situation);		
		lblStore = this.createControl(ID_Label_StoreName, Label.class);	
		lblCount = this.createControl(ID_Label_KitItemCount,Label.class);		
		btnAdd = this.createControl(ID_Button_AddKitItem, Button.class);		
		btnSave = this.createControl(ID_Button_Save, Button.class);		
		initEvent();
		initData();
		initAuth();
		registerValidator();
		showCount();
	}
	
	/**
	 * 注册表格验证器
	 */
	protected void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateCell(String rowId, String columnName) 
			{
				String[] values = table.getEditValue(rowId, Columns.count.name(),Columns.kitName.name());				
				if(CheckIsNull.isEmpty(values[0])){
					return "其它物品：" + values[1] + "，库存初始数量不能为空";
				} else {
					if(Double.parseDouble(values[0])<=0){
						return "其它物品：" + values[1] + "，库存初始数量不能为0";
					}
				}				
				return null;
			}

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) 
					return "其它物品库存初始化不能为空";
				return null;
			}
		});
	}

	/**
	 * 相关功能权限控制
	 */
	private void initAuth() {
		
		if(!login.hasAuth(Auth.Boss,Auth.StoreKeeperManager,Auth.StoreKeeper)){
			btnAdd.setEnabled(false);
		}
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		storeInfo = getContext().find(StoreInfo.class, GUID.tryValueOf(storeId));
		lblStore.setText(storeInfo.getName());
	}

	/**
	 * 注册事件
	 */
	private void initEvent() {
		btnSave.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				processData();
			}
		});		
		btnAdd.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				edit();
			}
		});		
	}
	
	/**
	 * 显示数量
	 */
	private void showCount(){
		lblCount.setText(String.valueOf(kits.size()));
	}

	/**
	 * 　保存
	 */
	private boolean save() {
		
		if (!validateInput()) {
			return false;
		}
		
		String[] rowIds = table.getAllRowId();
		StoreInitKitItem[] items = new StoreInitKitItem[rowIds.length];
		for (int i = 0; i < rowIds.length; i++) {
			String[] values = table.getEditValue(rowIds[i],Columns.kitName.name(), Columns.kitDescription.name(),Columns.unit.name(),Columns.count.name());
			items[i] = new StoreInitKitItem();
			items[i].setKitName(values[0]);
			items[i].setKitDescription(values[1]);
			items[i].setUnit(values[2]);
			items[i].setCount(DoubleUtil.round(Double.parseDouble(values[3]),0));//四舍五入
		}		
			
		SaveInitKitItemsTask task = new SaveInitKitItemsTask(storeInfo.getId(), items);
		getContext().handle(task);
		table.render();
		hint("操作成功");
		return true;
	}

	private void edit() {
		PageControllerInstance pci = new PageControllerInstance(new PageController(KitEditProcessor.class,KitEditRender.class));
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(380, 140);
		MsgRequest request = new MsgRequest(pci, "快速新增物品", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2,Object returnValue3, Object returnValue4) {
				if(returnValue != null){
					StoreInitKitItem item = (StoreInitKitItem)returnValue;
					String newId = key(item.getKitName(),item.getKitDescription(),item.getUnit());
					if (kits.containsKey(newId)){
						StoreInitKitItem kit = kits.get(newId);
						table.updateCell(newId, 3, String.valueOf(kit.getCount() + item.getCount()));//修改对应ID行中的初始数量
						table.renderUpate();
					} else {
						table.addRow(item);						
						table.renderUpate();						
						kits.put(newId, item);
						showCount();
					}
				}
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		List<StoreInitKitItem> itemList = context.getList(StoreInitKitItem.class, storeInfo.getId());
		if(CheckIsNull.isNotEmpty(itemList)) {
			for (StoreInitKitItem item : itemList) {
				kits.put(key(item.getKitName(),item.getKitDescription(),item.getUnit()), item);
			}
			showCount();
			return itemList.toArray();
		} else {
			return null;
		}
	}
	
	/**
	 * 生成物品的KeyId
	 * @param kitName
	 * @param kitDesc
	 * @param kitUnit
	 * @return
	 */
	private String key(String kitName, String kitDesc, String kitUnit) {
		return GUID.MD5Of(kitName + "~" +kitDesc + "~" +kitUnit).toString();
	}

	@Override
	public String getElementId(Object element) {		
		if (element instanceof StoreInitKitItem) {
			StoreInitKitItem item = (StoreInitKitItem) element;
			return key(item.getKitName(),item.getKitDescription(),item.getUnit());
		}		
		return null;
	}
	
	@Override
	public String getValue(Object element, String columnName) {
		if (element instanceof StoreInitKitItem) {
			StoreInitKitItem item = (StoreInitKitItem) element;
			if (columnName.equals(Columns.kitName.name())) {
				return item.getKitName();
			}
			if (columnName.equals(Columns.kitDescription.name())) {
				return item.getKitDescription();
			}
			if (columnName.equals(Columns.unit.name())) {
				return item.getUnit();
			}			
			if(columnName.equals(Columns.count.name())){
				return String.valueOf(item.getCount());
			}
		}
		return null;
	}
	
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}

	@Override
	public void actionPerformed(final String rowId, String actionName,String actionValue) {		
		if (actionName.equals(Action.Delete.name())) {	
			confirm("确认删除所选物品？",
				new Runnable() {
					public void run() {
						table.removeRow(rowId);
						table.renderUpate();
						kits.remove(rowId);
						showCount();
					}
				}
			);	
		} 
	}

	/**
	 * 提示信息
	 */
	public String getPromptMessage(){
	    return null;
    }

	/**
	 * 处理数据
	 */
	public boolean processData(){
		if(save()){
			resetDataChangedstatus();
			return true;
		}else{
			return false;
		}
    }

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}