package com.spark.psi.base.browser.start;

import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIBillsPageProcessor;
import com.spark.psi.publish.Action;

/**
 * <p>快速设置仓库界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-27
 */

public class QuickSetStoreProcessor extends PSIBillsPageProcessor<TempStorageInfo>{
	/**
	 * 控件ID
	 */
	public final static String ID_Button_Add = "Button_Add";
	//编辑用户
	public final static String ID_ACTION_EDIT = "edit";

	/**字段属性名*/
	public static enum Columns{
		Name,
		KeeperNames,
		Phone,
		Consignee,
		Mobile,
		Province,
		City,
		District,
		Address,
		Postcode,
		KeeperIds,
		SaleIds,
		SaleNames,
		Enabled
	}

	//
	private SEditTable userTable;

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//新增用户
		Button addButton = this.createControl(ID_Button_Add, Button.class, JWT.NONE);
		addButton.addActionListener(new AddButtonListener());
		//注册消息
		regMessageListener();
	}
	
	/**
	 * 注册消息
	 */
	private void regMessageListener(){
		//验证用户是否被使用
		getContext().regMessageListener(ValidateUserUsedDownMessage.class, new MessageListener<ValidateUserUsedDownMessage>(){

			public void onMessage(Situation context, ValidateUserUsedDownMessage message,
                    MessageTransmitter<ValidateUserUsedDownMessage> transmitter)
            {
				List<TempStorageInfo> storageList = WizardUtil.getStorageInfoList(table);
				Map<String, TempStorageInfo> map = WizardUtil.getEmployeeIdMap(storageList);
				if(map.containsKey(message.getUserId())){ //该用户已被使用
					message.getResponseHandler().handle(true, null, null, null);
				}else{ //该用户未被使用
					message.getResponseHandler().handle(false, null, null, null);
				}
            }

		});
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		//
		userTable = (SEditTable)this.getArgument();
	}

	/**
	 * 获得数据列表
	 */
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus){
		return null;
	}

	/**
	 * 获值附加数据
	 */
	@Override
	public SNameValue[] getExtraData(Object element){
		if(element instanceof TempStorageInfo){
			TempStorageInfo item = (TempStorageInfo)element;
			return new SNameValue[] {new SNameValue(Columns.Name.name(), String.valueOf(item.getName())),
			        new SNameValue(Columns.Phone.name(), String.valueOf(item.getPhone())),
			        new SNameValue(Columns.Consignee.name(), String.valueOf(item.getConsignee())),
			        new SNameValue(Columns.Mobile.name(), String.valueOf(item.getMobile())),
			        new SNameValue(Columns.Province.name(), String.valueOf(item.getProvince())),
			        new SNameValue(Columns.City.name(), String.valueOf(item.getCity())),
			        new SNameValue(Columns.District.name(), String.valueOf(item.getDistrict())),
			        new SNameValue(Columns.Address.name(), String.valueOf(item.getAddress())),
			        new SNameValue(Columns.Postcode.name(), String.valueOf(item.getPostcode())),
			        new SNameValue(Columns.KeeperNames.name(), String.valueOf(item.getKeeperNames())),
			        new SNameValue(Columns.KeeperIds.name(), String.valueOf(item.getKeeperIds())),
			        new SNameValue(Columns.SaleNames.name(), String.valueOf(item.getSaleNames())),
			        new SNameValue(Columns.SaleIds.name(), String.valueOf(item.getSaleIds())),
			        new SNameValue(Columns.Enabled.name(), String.valueOf(item.isEnabled()))};
		}
		return null;
	}

	/**
	 * 获取指定对象的ID
	 */
	public String getElementId(Object element){
		return ((TempStorageInfo)element).getId().toString();
	}

	/**
	 * 获取可以对表格数据进行删除操作
	 */
	@Override
	public String[] getTableActionIds(){
		return new String[] {Action.Active.name(), Action.Delete.name()};
	}

	/**
	 * 获取可以对指定行对象进行删除操作
	 */
	@Override
	protected String[] getElementActionIds(Object element){
		TempStorageInfo tempStorageInfo = (TempStorageInfo)element;
		if(tempStorageInfo.isEnabled()){
			return new String[] {Action.Delete.name()};
		}
		else{
			return new String[] {Action.Active.name(), Action.Delete.name()};
		}
	}

	/**
	 * 行对像指定操作发生时，触发的事件
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(actionName.equals(Action.Delete.name())){ //删除仓库
			table.removeRow(rowId);
			table.renderUpate();
		}
		else if(actionName.equals(Action.Active.name())){ //启用仓库
			TempStorageInfo tempStorageInfo = WizardUtil.getStorageInfoByRowId(rowId, table);
			tempStorageInfo.setEnabled(Boolean.TRUE);
			table.updateRow(tempStorageInfo);
			table.renderUpate();
		}
		else if(ID_ACTION_EDIT.equals(actionName)){ //编辑仓库
			WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
			windowStyle.setSize(600, 260);
			TempStorageInfo tempStorageInfo = WizardUtil.getStorageInfoByRowId(rowId, table);
			PageControllerInstance controllerInstance = new PageControllerInstance("EditStorePage", tempStorageInfo, userTable, table);
			MsgRequest request = new MsgRequest(controllerInstance, "仓库设置", windowStyle);
			//
			request.setResponseHandler(new ResponseHandler(){

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					if(CheckIsNull.isNotEmpty(returnValue)){
						table.updateRow(returnValue);
						table.renderUpate();
					}
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	/**
	 * 新增仓库按钮侦听器
	 */
	private class AddButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
			windowStyle.setSize(600, 260);
			PageControllerInstance controllerInstance = new PageControllerInstance("EditStorePage", null, userTable, table);
			MsgRequest request = new MsgRequest(controllerInstance, "新增仓库", windowStyle);
			//
			request.setResponseHandler(new ResponseHandler(){

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					if(CheckIsNull.isNotEmpty(returnValue)){
						table.addRow(returnValue);
						table.renderUpate();
					}
				}
			});
			getContext().bubbleMessage(request);
		}

	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}

}
