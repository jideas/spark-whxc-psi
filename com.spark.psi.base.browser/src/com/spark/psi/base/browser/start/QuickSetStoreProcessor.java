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
 * <p>�������òֿ���洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-27
 */

public class QuickSetStoreProcessor extends PSIBillsPageProcessor<TempStorageInfo>{
	/**
	 * �ؼ�ID
	 */
	public final static String ID_Button_Add = "Button_Add";
	//�༭�û�
	public final static String ID_ACTION_EDIT = "edit";

	/**�ֶ�������*/
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
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//�����û�
		Button addButton = this.createControl(ID_Button_Add, Button.class, JWT.NONE);
		addButton.addActionListener(new AddButtonListener());
		//ע����Ϣ
		regMessageListener();
	}
	
	/**
	 * ע����Ϣ
	 */
	private void regMessageListener(){
		//��֤�û��Ƿ�ʹ��
		getContext().regMessageListener(ValidateUserUsedDownMessage.class, new MessageListener<ValidateUserUsedDownMessage>(){

			public void onMessage(Situation context, ValidateUserUsedDownMessage message,
                    MessageTransmitter<ValidateUserUsedDownMessage> transmitter)
            {
				List<TempStorageInfo> storageList = WizardUtil.getStorageInfoList(table);
				Map<String, TempStorageInfo> map = WizardUtil.getEmployeeIdMap(storageList);
				if(map.containsKey(message.getUserId())){ //���û��ѱ�ʹ��
					message.getResponseHandler().handle(true, null, null, null);
				}else{ //���û�δ��ʹ��
					message.getResponseHandler().handle(false, null, null, null);
				}
            }

		});
	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		//
		userTable = (SEditTable)this.getArgument();
	}

	/**
	 * ��������б�
	 */
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus){
		return null;
	}

	/**
	 * ��ֵ��������
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
	 * ��ȡָ�������ID
	 */
	public String getElementId(Object element){
		return ((TempStorageInfo)element).getId().toString();
	}

	/**
	 * ��ȡ���ԶԱ�����ݽ���ɾ������
	 */
	@Override
	public String[] getTableActionIds(){
		return new String[] {Action.Active.name(), Action.Delete.name()};
	}

	/**
	 * ��ȡ���Զ�ָ���ж������ɾ������
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
	 * �ж���ָ����������ʱ���������¼�
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(actionName.equals(Action.Delete.name())){ //ɾ���ֿ�
			table.removeRow(rowId);
			table.renderUpate();
		}
		else if(actionName.equals(Action.Active.name())){ //���òֿ�
			TempStorageInfo tempStorageInfo = WizardUtil.getStorageInfoByRowId(rowId, table);
			tempStorageInfo.setEnabled(Boolean.TRUE);
			table.updateRow(tempStorageInfo);
			table.renderUpate();
		}
		else if(ID_ACTION_EDIT.equals(actionName)){ //�༭�ֿ�
			WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
			windowStyle.setSize(600, 260);
			TempStorageInfo tempStorageInfo = WizardUtil.getStorageInfoByRowId(rowId, table);
			PageControllerInstance controllerInstance = new PageControllerInstance("EditStorePage", tempStorageInfo, userTable, table);
			MsgRequest request = new MsgRequest(controllerInstance, "�ֿ�����", windowStyle);
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
	 * �����ֿⰴť������
	 */
	private class AddButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
			windowStyle.setSize(600, 260);
			PageControllerInstance controllerInstance = new PageControllerInstance("EditStorePage", null, userTable, table);
			MsgRequest request = new MsgRequest(controllerInstance, "�����ֿ�", windowStyle);
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
