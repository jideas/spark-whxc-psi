package com.spark.psi.base.browser.start;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.portal.browser.WindowStyle.Location;
import com.spark.psi.base.browser.PSIBillsPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.config.AuthConfigProcessor;
import com.spark.psi.base.browser.config.AuthConfigRender;
import com.spark.psi.base.browser.config.DepartmentConfigProcessor;
import com.spark.psi.base.browser.config.DepartmentConfigRender;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.key.GetEmployeeListKey;
import com.spark.psi.publish.base.organization.task.DeleteEmployeeTask;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeInfoTask;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeInfoTask.Item;

/**
 * <p>Ա�����û���Ϣ���洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-9
 */

public class WizardEmployeeInfoStepProcessor extends WizardBaseStepProcessor{

	/**
	 * �ؼ�ID 
	 */
	public final static String ID_Button_SetDept = "Button_SetDept";
	public final static String ID_Button_SetRoles = "Button_SetRoles";
	public final static String ID_Table_Employee = "Table_Employee";

	/**�ֶ�������*/
	public static enum Columns{
		Name,
		MobileNo,
		IdentityNumber,
		Email,
		Department,
		Postion,
		Roles
	}

	//
	private SEditTable table;

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation context){
		super.process(context);
		// �������
		createUIComponent();
	}

	/**
	 * �������
	 */
	private void createUIComponent(){
		final TenantInfo tenant = getContext().find(TenantInfo.class);
		//
		Button setDeptButton = this.createControl(ID_Button_SetDept, Button.class);
		Button setRolesButton = this.createControl(ID_Button_SetRoles, Button.class);
		//Table
		table = this.createControl(ID_Table_Employee, SEditTable.class);
		table.addClientEventHandler(STable.CLIENT_EVENT_SELECTION, "PSIWizard.EmployeeTableSelection");
		//���ò���
		setDeptButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(selectedEmployee()){
					alert("��δѡ���κ�Ա����");
					return;
				}
				InnerClass.configDepartment(getContext(), tenant, table, table.getSelections());
			}
		});
		//����Ȩ��
		setRolesButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(selectedEmployee()){
					alert("��δѡ���κ�Ա����");
					return;
				}
				//�����ֻ����Ƿ�Ϊ11λ
				for(String rowId : table.getSelections()){
					String mobileNumber = "";
					if("1".equals(table.getExtraData(rowId, "isCanChangeMobileNo")[0])){
						mobileNumber = table.getEditValue(rowId, Columns.MobileNo.name())[0];
					}
					else{
						mobileNumber = table.getExtraData(rowId, "mobileNumber")[0];
					}
					if(StringHelper.isEmpty(mobileNumber) || !mobileNumber.trim().matches("^\\d{11}$")){
						alert("��ѡԱ�����ֻ����벻��Ϊ�գ��ұ���Ϊ11λ���֣����޸ģ�");
						return;
					}
				}
				InnerClass.configAuth(getContext(), tenant, table, table.getSelections());
			}
		});
	}

	/**
	 * ��֤�Ƿ�ѡ����Ա��
	 */
	private boolean selectedEmployee(){
		return table.getSelections() == null || table.getSelections().length == 0;
	}

	/**
	 * ����ִ��
	 */
	@Override
	protected boolean operateExecute(){
		String[] rowIds = table.getAllRowId();
		LoginInfo loginInfo = getContext().get(LoginInfo.class);
		if(rowIds != null){
			List<UpdateEmployeeInfoTask.Item> items = new ArrayList<UpdateEmployeeInfoTask.Item>();
			for(int i = 0; i < rowIds.length; i++){
				String rowId = rowIds[i];
				String[] values =
				        table.getEditValue(rowId, Columns.Name.name(), Columns.IdentityNumber.name(),
				                Columns.MobileNo.name(), Columns.Email.name(), Columns.Postion.name(),
				                Columns.Department.name(), Columns.Roles.name());

				UpdateEmployeeInfoTask.Item item = new UpdateEmployeeInfoTask.Item();
				String[] extraData = table.getExtraData(rowId, "isNew");
				String mobileNumber = "";
				if("1".equals(extraData[0])){
					item.setCreate(true);
				}
				if("1".equals(table.getExtraData(rowId, "isCanChangeMobileNo")[0])){
					mobileNumber = values[2];
				}
				else{
					mobileNumber = table.getExtraData(rowId, "mobileNumber")[0];
				}
				if(StringHelper.isNotEmpty(mobileNumber) && !mobileNumber.trim().matches("^\\d{11}$")){
					alert("Ա�����ֻ����벻Ϊ��ʱ������Ϊ11λ���֣����޸ģ�");
					return false;
				}
				if(!(StringHelper.isNotEmpty(values[0]) && StringHelper.isNotEmpty(values[5].trim()) && StringHelper
				        .isNotEmpty(mobileNumber)))
				{
					// ���û�����������ֻ����롢���ţ��򲻱���
					continue;
				}
				item.setId(GUID.valueOf(rowId));
				item.setName(values[0].trim());
				item.setIdNumber(values[1]);
				item.setMobileNo(mobileNumber);
				item.setEmail(values[3]);
				item.setPosition(values[4]);
				item.setDepartmentId(GUID.valueOf(values[5].trim()));
				item.setRoles(Integer.parseInt(StringUtils.isEmpty(values[6]) ? "-1" : values[6].trim()));
				//Ϊ������ܾ���ʱΪtrue
				if(loginInfo.getEmployeeInfo().getId().equals(GUID.valueOf(rowId))
				        || item.getId().equals(loginInfo.getTenantId()))
				{
					item.setValid(Boolean.TRUE);
				}
				else{
					item.setValid(Boolean.FALSE);
				}
				items.add(item);
			}
			if(!validationMobileIsOnly(items)){
				return false;
			}
			try{
				getContext().handle(
				        new UpdateEmployeeInfoTask(items.toArray(new UpdateEmployeeInfoTask.Item[items.size()])));
			}
			catch(Exception e){
				alert(e.getMessage());
				return false;
			}
			table.render();
			hint("����ɹ���");
			return true;
		}
		return false;
	}

	/**
	 * ��һ��ִ��
	 */
	@Override
    protected boolean nextExecute(){
		return true;
    }
	
	/**
	 * ��֤�ֻ������Ƿ�Ψһ
	 * @param items 
	 * 
	 * @return boolean
	 */
	private boolean validationMobileIsOnly(List<Item> items){
		List<String> list = new ArrayList<String>();
		for(Item item : items){
			if(list.contains(item.getMobileNo())){
				alert("�ֻ������ظ���" + item.getMobileNo());
				return false;
			}
			list.add(item.getMobileNo());
		}
		return true;
	}

	/**
	 * �ڲ��࣬����б���
	 */
	public static class InnerClass extends PSIBillsPageProcessor<EmployeeItem>{

		/**
		 * �ؼ�ID 
		 */
		public final static String ID_Label_Authroized = "Label_Authroized";
		public final static String ID_Label_Authroizable = "Label_Authroizable";
		public final static String ID_List_DeptScope = "List_DeptScope";
		public final static String ID_List_Scope = "List_Scope";
		//
		private static final String ALL_EMPLOYEE_ITEM = "ȫ��Ա��";

		/**
		 * ���
		 */
		private Label authroizedLabel;
		private Label authroizableLabel;
		private LWComboList deptScopeList;
		private ListSourceAdapter queryScopeSource;
		private ComboList authScopeList;

		//�⻧��Ϣ
		private TenantInfo tenant;

		/**
		 * ҳ���ʼ��
		 */
		@Override
		public void process(Situation context){
			super.process(context);
			//
			tenant = getContext().find(TenantInfo.class);
			//
			authroizedLabel = this.createControl(ID_Label_Authroized, Label.class);
			authroizableLabel = this.createControl(ID_Label_Authroizable, Label.class);
			//���ŷ�Χ�б�
			deptScopeList = this.createControl(ID_List_DeptScope, LWComboList.class, JWT.NONE);
			queryScopeSource = PSIProcessorUtils.initDeparmentQueryScopeSource(deptScopeList);
			deptScopeList.addSelectionListener(new SelectionListener(){

				public void widgetSelected(SelectionEvent e){
					table.render();
				}
			});
			//�Ƿ�����û���ɫ�б�
			authScopeList = this.createControl(ID_List_Scope, ComboList.class, JWT.NONE);
			authScopeList.addItem(ALL_EMPLOYEE_ITEM);
			authScopeList.addItem("���û���ɫ��Ա��");
			authScopeList.addItem("���û���ɫ��Ա��");
			authScopeList.setSelection(0);
			authScopeList.addSelectionListener(new SelectionListener(){

				public void widgetSelected(SelectionEvent e){
					table.render();
				}
			});
			//Ĭ��ֵ��0
			authroizedLabel.setText("0");
			authroizableLabel.setText("0");
		}

		/**
		 * ��ID
		 */
		@Override
		public String getElementId(Object element){
			if(element instanceof String){
				return (String)element;
			}
			else{
				EmployeeItem item = (EmployeeItem)element;
				return item.getId().toString();
			}
		}

		/**
		 * ����б�����
		 */
		@Override
		public Object[] getElements(Context context, STableStatus tablestatus){
			//��ѯ����Ա����ȷ������Ȩ�û�����
			int authroizable = tenant.getUserCount();
			GetEmployeeListKey key = new GetEmployeeListKey();
			key.setQueryAll(false);
			ListEntity<EmployeeItem> result = context.find(ListEntity.class, key);
			if(result != null && result.getTotalCount() > 0){
				List<EmployeeItem> itemList = result.getItemList();
				int authroized = 0;
				for(EmployeeItem item : itemList){
					if(item.getRoles() > 0){
						authroized++;
						authroizable--;
					}
				}
				authroizableLabel.setText(String.valueOf(authroizable));
				authroizedLabel.setText(String.valueOf(authroized));
				authroizedLabel.getParent().getParent().layout();
			}
			else{
				authroizableLabel.setText(String.valueOf(authroizable));
				authroizedLabel.setText(String.valueOf(0));
			}

			//���ղ�ѯ������ѯ�û�
			key = new GetEmployeeListKey();
			key.setQueryAll(false);
			QueryScope qs = (QueryScope)queryScopeSource.getElementById(deptScopeList.getText());
			key.setQueryScope(qs);
			key.setQueryAll(qs.getName().equals(ALL_EMPLOYEE_ITEM));
			key.setRoleScope(authScopeList.getList().getSelectionIndex());
			result = context.find(ListEntity.class, key);
			if(result != null && result.getTotalCount() > 0){
				return result.getItemList().toArray();
			}
			else{
				return new Object[] {this.getNewElement()};
			}
		}

		/**
		 * �Ƿ�ѡ��
		 */
		@Override
		public boolean isSelectable(Object element){
			if(element instanceof EmployeeItem){
				LoginInfo loginInfo = getContext().get(LoginInfo.class);
				EmployeeItem item = (EmployeeItem)element;
				if(item != null && !item.getId().equals(loginInfo.getTenantId()) && !loginInfo.getEmployeeInfo().getId().equals(item.getId())){
					return true;
				}else{
					return false;
				}
//				return ((EmployeeItem)element).getStatus() != EmployeeStatus.Supper;
			}
			else{
				return true;
			}
		}

		/**
		 * �½�һ����
		 */
		public Object getNewElement(){
			return GUID.randomID().toString();
		}

		/**
		 * ��ȡ���ԶԱ�����ݽ���ָ������
		 */
		@Override
		public String[] getTableActionIds(){
			return new String[] {Action.DepartmentConfig.name(), Action.AuthConfig.name(), Action.Delete.name()};
		}

		/**
		 * ��ȡ���Զ�ָ���ж������ָ������
		 */
		protected String[] getElementActionIds(Object element){
			if(element instanceof String){
				return new String[] {Action.DepartmentConfig.name(), Action.AuthConfig.name(), Action.Delete.name()};
			}
			LoginInfo loginInfo = getContext().get(LoginInfo.class);
			EmployeeItem item = (EmployeeItem)element;
			if(item != null && !item.getId().equals(loginInfo.getTenantId()) && !loginInfo.getEmployeeInfo().getId().equals(item.getId())){
				return new String[] {Action.DepartmentConfig.name(), Action.AuthConfig.name(), Action.Delete.name()};
			}
			return null;
		}

		/**
		 * ���ֵ
		 */
		public String getValue(Object element, String columnName){
			if(element instanceof String){
				return "";
			}
			EmployeeItem item = (EmployeeItem)element;
			Columns column = Columns.valueOf(columnName);
			if(column != null){
				switch(column.ordinal()){
					case 0:
						return item.getName();
					case 1:
						if(item.getRoles() != -1) return null; // ֻ�д�δ���ù���ɫ���û������޸��ֻ�����
						return item.getMobileNo();
					case 2:
						return item.getIdNumber();
					case 3:
						return item.getEmail();
					case 4:
						return item.getDepartmentId().toString();
					case 5:
						return item.getPosition();
					case 6:
						return String.valueOf(item.getRoles());
					default:
						return null;
				}
			}
			return null;
		}

		/**
		 * �����������
		 */
		public SNameValue[] getExtraData(Object element){
			SNameValue[] result = new SNameValue[2];
			result[0] = new SNameValue("isCanChangeMobileNo", "1");
			if(element instanceof String){
				result[1] = new SNameValue("isNew", "1");
			}
			else{
				EmployeeInfo emp = (EmployeeInfo)element;
				if(emp.getRoles() > -1){
					result[0] = new SNameValue("isCanChangeMobileNo", "0");
				}
				result[1] = new SNameValue("mobileNumber", emp.getMobileNo());
			}
			return result;
		}

		/**
		 * ָ����������ʱ���������¼�
		 */
		public void actionPerformed(final String rowId, String actionName, String actionValue){
			if(actionName.equals(Action.DepartmentConfig.name())){
				//���ò���
				configDepartment(getContext(), tenant, table, rowId);
			}
			else if(actionName.equals(Action.AuthConfig.name())){
				//�����ֻ����Ƿ�Ϊ11λ
				String mobileNumber = "";
				if("1".equals(table.getExtraData(rowId, "isCanChangeMobileNo")[0])){
					mobileNumber = table.getEditValue(rowId, Columns.MobileNo.name())[0];
				}
				else{
					mobileNumber = table.getExtraData(rowId, "mobileNumber")[0];
				}
				if(StringHelper.isEmpty(mobileNumber) || !mobileNumber.trim().matches("^\\d{11}$")){
					alert("��ѡԱ�����ֻ����벻��Ϊ�գ��ұ���Ϊ11λ���֣����޸ģ�");
					return;
				}
				//����Ȩ��
				configAuth(getContext(), tenant, table, rowId);
			}
			else if(actionName.equals(Action.Delete.name())){
				//ɾ����ǰ��
				confirm("�Ƿ�ɾ����ǰԱ����", new Runnable(){

					public void run(){
						DeleteEmployeeTask deleteEmployeeTask = new DeleteEmployeeTask(GUID.valueOf(rowId));
						getContext().handle(deleteEmployeeTask);
						table.render();
						hint("ɾ���ɹ���");
					}
				});
			}
		}

		/**
		 * ���ò���
		 *
		 *@param rowIds
		 */
		public static void configDepartment(Situation context, final TenantInfo stenant, final SEditTable stable,
		        final String... rowIds)
		{
			Integer[] roles = new Integer[rowIds.length];
			for(int i = 0; i < rowIds.length; i++){
				String r = stable.getEditValue(rowIds[i], Columns.Roles.name())[0];
				if(StringHelper.isNotEmpty(r)){
					roles[i] = Integer.parseInt(r);
				}
			}
			PageControllerInstance pci =
			        new PageControllerInstance(new PageController(DepartmentConfigProcessor.class,
			                DepartmentConfigRender.class), roles, JWT.CHECK);
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE, Location.Context);
			windowStyle.setSize(240, 360);
			MsgRequest request = new MsgRequest(pci, "���ò���", windowStyle);
			request.setResponseHandler(new ResponseHandler(){
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					if(returnValue != null){
						DepartmentTree.DepartmentNode node = (DepartmentTree.DepartmentNode)returnValue;
						for(int i = 0; i < rowIds.length; i++){
							stable.updateCell(rowIds[i], Columns.Department.name(), node.getId().toString(), node
							        .getName().equals(stenant.getTitle()) ? "��˾ֱ��" : node.getName());
						}
					}
					stable.renderUpate();
				}
			});
			context.bubbleMessage(request);
		}

		/**
		 * ����Ȩ��
		 *
		 *@param rowIds
		 */
		public static void configAuth(Situation context, final TenantInfo stenant, final SEditTable stable,
		        final String... rowIds)
		{
			//
			StringBuilder name = new StringBuilder();
			String[] depts = new String[rowIds.length];
			int roles = 0;
			if(rowIds.length == 1){
				String rolesStr = stable.getEditValue(rowIds[0], Columns.Roles.name())[0];
				roles = Integer.parseInt(StringUtils.isEmpty(rolesStr) ? "0" : rolesStr);
			}
			for(int i = 0; i < rowIds.length; i++){
				if(name.length() > 0) name.append(",");
				name.append(stable.getEditValue(rowIds[i], Columns.Name.name())[0]);
				depts[i] = stable.getEditValue(rowIds[i], Columns.Department.name())[0];
			}
			PageControllerInstance pci =
			        new PageControllerInstance(new PageController(AuthConfigProcessor.class, AuthConfigRender.class),
			                name.toString(), roles, depts);
			WindowStyle windowStyle = new WindowStyle(JWT.CLOSE);
			windowStyle.setSize(640, 400);
			MsgRequest request = new MsgRequest(pci, "����Ȩ��", windowStyle);
			request.setResponseHandler(new ResponseHandler(){
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					int roles = (Integer)returnValue;
					String rolesName = (String)returnValue2;
					for(int i = 0; i < rowIds.length; i++){
						stable.updateCell(rowIds[i], Columns.Roles.name(), String.valueOf(roles), rolesName);
					}
					stable.renderUpate();
				}
			});
			context.bubbleMessage(request);
		}

		@Override
		protected String getExportFileTitle() {
			return null;
		}
	}
}
