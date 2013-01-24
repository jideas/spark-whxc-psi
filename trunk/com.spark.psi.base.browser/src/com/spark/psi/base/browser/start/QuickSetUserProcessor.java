package com.spark.psi.base.browser.start;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIBillsPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.key.GetEmployeeListKey;

/**
 * <p>���������û����洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-27
 */

public class QuickSetUserProcessor extends PSIBillsPageProcessor<TempUserInfo>{
	/**
	 * �ؼ�ID
	 */
	public final static String ID_Button_Add = "Button_Add";
	//�༭�û�
	public final static String ID_ACTION_EDIT = "edit";

	/**�ֶ�������*/
	public static enum Columns{
		Name,
		MobileNo,
		DepartmentName,
		RolesName,
		IdentityNumber,
		Eamil,
		Job,
		DepartmentId,
		Roles,
		IsCreate
	}

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//�����û�
		Button addButton = this.createControl(ID_Button_Add, Button.class, JWT.NONE);
		addButton.addActionListener(new AddButtonListener());
	}

	/**
	 * ��������б�
	 */
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus){
		GetEmployeeListKey key = new GetEmployeeListKey();
		key.setQueryAll(false);
		ListEntity<EmployeeItem> result = context.find(ListEntity.class, key);
		List<TempUserInfo> userInfoList = new ArrayList<TempUserInfo>();
		if(CheckIsNull.isNotEmpty(result)){
			TempUserInfo tempUserInfo = null;
			for(EmployeeItem item : result.getItemList()){
				tempUserInfo = new TempUserInfo();
				tempUserInfo.setDepartmentId(item.getDepartmentId());
				tempUserInfo.setDepartmentName(item.getDepartmentName());
				tempUserInfo.setEmail(item.getEmail());
				tempUserInfo.setId(item.getId());
				tempUserInfo.setIdentityNumber(item.getIdNumber());
				tempUserInfo.setJob(item.getPosition());
				tempUserInfo.setMobile(item.getMobileNo());
				tempUserInfo.setName(item.getName());
				tempUserInfo.setRoles(item.getRoles());
				tempUserInfo.setRolesName(item.getRolesInfo());
				tempUserInfo.setCreate(Boolean.FALSE);
				userInfoList.add(tempUserInfo);
			}
		}
		return userInfoList.toArray();
	}

	/**
	 * ��ֵ��������
	 */
	@Override
	public SNameValue[] getExtraData(Object element){
		if(element instanceof TempUserInfo){
			TempUserInfo item = (TempUserInfo)element;
			return new SNameValue[] {new SNameValue(Columns.Name.name(), String.valueOf(item.getName())),
			        new SNameValue(Columns.MobileNo.name(), String.valueOf(item.getMobile())),
			        new SNameValue(Columns.DepartmentName.name(), String.valueOf(item.getDepartmentName())),
			        new SNameValue(Columns.RolesName.name(), String.valueOf(item.getRolesName())),
			        new SNameValue(Columns.IdentityNumber.name(), String.valueOf(item.getIdentityNumber())),
			        new SNameValue(Columns.Eamil.name(), String.valueOf(item.getEmail())),
			        new SNameValue(Columns.Job.name(), String.valueOf(item.getJob())),
			        new SNameValue(Columns.DepartmentId.name(), String.valueOf(item.getDepartmentId())),
			        new SNameValue(Columns.Roles.name(), String.valueOf(item.getRoles())),
			        new SNameValue(Columns.IsCreate.name(), String.valueOf(item.isCreate()))};
		}
		return null;
	}

	/**
	 * ��ȡָ�������ID
	 */
	public String getElementId(Object element){
		return ((TempUserInfo)element).getId().toString();
	}

	/**
	 * ��ȡ���ԶԱ�����ݽ���ɾ������
	 */
	@Override
	public String[] getTableActionIds(){
		return new String[] {Action.Delete.name()};
	}

	/**
	 * ��ȡ���Զ�ָ���ж������ɾ������
	 */
	@Override
	protected String[] getElementActionIds(Object element){
		TempUserInfo tempUserInfo = (TempUserInfo)element;
		if(tempUserInfo.isCreate()){
			return new String[] {Action.Delete.name()};
		}
		else{
			return null;
		}
	}

	/**
	 * �ж���ָ����������ʱ���������¼�
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(actionName.equals(Action.Delete.name())){ //ɾ���û�
			ValidateUserUsedUpMessage message = new ValidateUserUsedUpMessage(rowId);
			message.setResponseHandler(new ResponseHandler(){

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					if((Boolean)returnValue){
						alert("���û��ѱ�ʹ�ã�����ɾ��ʹ�ø��û��Ĳֿ⣡");
					}
					else{
						table.removeRow(rowId);
						table.renderUpate();
					}
				}
			});
			getContext().bubbleMessage(message);
		}
		else if(ID_ACTION_EDIT.equals(actionName)){ //�༭�û�
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(450, 200);
			TempUserInfo tempUserInfo = WizardUtil.getUserInfoByRowId(rowId, table);
			PageControllerInstance controllerInstance = new PageControllerInstance("EditUserPage", tempUserInfo, table);
			MsgRequest request = new MsgRequest(controllerInstance, "�û�����", style);
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
	 * �����û���ť������
	 */
	private class AddButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//��ѯ����Ա����ȷ������Ȩ�û�����
			int authroizable = getContext().get(TenantInfo.class).getUserCount();
			if(table.getAllRowId().length >= authroizable){
				alert("��������û����������꣡");
				return;
			}
			//
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(450, 200);
			PageControllerInstance controllerInstance = new PageControllerInstance("EditUserPage", null, table);
			MsgRequest request = new MsgRequest(controllerInstance, "�����û�", style);
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
