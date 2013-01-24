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
 * <p>员工与用户信息界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-9
 */

public class WizardEmployeeInfoStepProcessor extends WizardBaseStepProcessor{

	/**
	 * 控件ID 
	 */
	public final static String ID_Button_SetDept = "Button_SetDept";
	public final static String ID_Button_SetRoles = "Button_SetRoles";
	public final static String ID_Table_Employee = "Table_Employee";

	/**字段属性名*/
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
	 * 页面初始化
	 */
	@Override
	public void process(Situation context){
		super.process(context);
		// 创建组件
		createUIComponent();
	}

	/**
	 * 创建组件
	 */
	private void createUIComponent(){
		final TenantInfo tenant = getContext().find(TenantInfo.class);
		//
		Button setDeptButton = this.createControl(ID_Button_SetDept, Button.class);
		Button setRolesButton = this.createControl(ID_Button_SetRoles, Button.class);
		//Table
		table = this.createControl(ID_Table_Employee, SEditTable.class);
		table.addClientEventHandler(STable.CLIENT_EVENT_SELECTION, "PSIWizard.EmployeeTableSelection");
		//设置部门
		setDeptButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(selectedEmployee()){
					alert("您未选择任何员工！");
					return;
				}
				InnerClass.configDepartment(getContext(), tenant, table, table.getSelections());
			}
		});
		//设置权限
		setRolesButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(selectedEmployee()){
					alert("您未选择任何员工！");
					return;
				}
				//检验手机号是否为11位
				for(String rowId : table.getSelections()){
					String mobileNumber = "";
					if("1".equals(table.getExtraData(rowId, "isCanChangeMobileNo")[0])){
						mobileNumber = table.getEditValue(rowId, Columns.MobileNo.name())[0];
					}
					else{
						mobileNumber = table.getExtraData(rowId, "mobileNumber")[0];
					}
					if(StringHelper.isEmpty(mobileNumber) || !mobileNumber.trim().matches("^\\d{11}$")){
						alert("所选员工的手机号码不能为空，且必须为11位数字，请修改！");
						return;
					}
				}
				InnerClass.configAuth(getContext(), tenant, table, table.getSelections());
			}
		});
	}

	/**
	 * 验证是否选择了员工
	 */
	private boolean selectedEmployee(){
		return table.getSelections() == null || table.getSelections().length == 0;
	}

	/**
	 * 操作执行
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
					alert("员工的手机号码不为空时，必须为11位数字，请修改！");
					return false;
				}
				if(!(StringHelper.isNotEmpty(values[0]) && StringHelper.isNotEmpty(values[5].trim()) && StringHelper
				        .isNotEmpty(mobileNumber)))
				{
					// 如果没有填姓名、手机号码、部门，则不保存
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
				//为助理或总经理时为true
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
			hint("保存成功！");
			return true;
		}
		return false;
	}

	/**
	 * 下一步执行
	 */
	@Override
    protected boolean nextExecute(){
		return true;
    }
	
	/**
	 * 验证手机号码是否唯一
	 * @param items 
	 * 
	 * @return boolean
	 */
	private boolean validationMobileIsOnly(List<Item> items){
		List<String> list = new ArrayList<String>();
		for(Item item : items){
			if(list.contains(item.getMobileNo())){
				alert("手机号码重复：" + item.getMobileNo());
				return false;
			}
			list.add(item.getMobileNo());
		}
		return true;
	}

	/**
	 * 内部类，存放列表区
	 */
	public static class InnerClass extends PSIBillsPageProcessor<EmployeeItem>{

		/**
		 * 控件ID 
		 */
		public final static String ID_Label_Authroized = "Label_Authroized";
		public final static String ID_Label_Authroizable = "Label_Authroizable";
		public final static String ID_List_DeptScope = "List_DeptScope";
		public final static String ID_List_Scope = "List_Scope";
		//
		private static final String ALL_EMPLOYEE_ITEM = "全部员工";

		/**
		 * 组件
		 */
		private Label authroizedLabel;
		private Label authroizableLabel;
		private LWComboList deptScopeList;
		private ListSourceAdapter queryScopeSource;
		private ComboList authScopeList;

		//租户信息
		private TenantInfo tenant;

		/**
		 * 页面初始化
		 */
		@Override
		public void process(Situation context){
			super.process(context);
			//
			tenant = getContext().find(TenantInfo.class);
			//
			authroizedLabel = this.createControl(ID_Label_Authroized, Label.class);
			authroizableLabel = this.createControl(ID_Label_Authroizable, Label.class);
			//部门范围列表
			deptScopeList = this.createControl(ID_List_DeptScope, LWComboList.class, JWT.NONE);
			queryScopeSource = PSIProcessorUtils.initDeparmentQueryScopeSource(deptScopeList);
			deptScopeList.addSelectionListener(new SelectionListener(){

				public void widgetSelected(SelectionEvent e){
					table.render();
				}
			});
			//是否存在用户角色列表
			authScopeList = this.createControl(ID_List_Scope, ComboList.class, JWT.NONE);
			authScopeList.addItem(ALL_EMPLOYEE_ITEM);
			authScopeList.addItem("有用户角色的员工");
			authScopeList.addItem("无用户角色的员工");
			authScopeList.setSelection(0);
			authScopeList.addSelectionListener(new SelectionListener(){

				public void widgetSelected(SelectionEvent e){
					table.render();
				}
			});
			//默认值置0
			authroizedLabel.setText("0");
			authroizableLabel.setText("0");
		}

		/**
		 * 行ID
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
		 * 获得列表数据
		 */
		@Override
		public Object[] getElements(Context context, STableStatus tablestatus){
			//查询所有员工，确定已授权用户数量
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

			//按照查询条件查询用户
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
		 * 是否选择
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
		 * 新建一数据
		 */
		public Object getNewElement(){
			return GUID.randomID().toString();
		}

		/**
		 * 获取可以对表格数据进行指定操作
		 */
		@Override
		public String[] getTableActionIds(){
			return new String[] {Action.DepartmentConfig.name(), Action.AuthConfig.name(), Action.Delete.name()};
		}

		/**
		 * 获取可以对指定行对象进行指定操作
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
		 * 获得值
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
						if(item.getRoles() != -1) return null; // 只有从未设置过角色的用户才能修改手机号码
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
		 * 额外的行数据
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
		 * 指定操作发生时，触发的事件
		 */
		public void actionPerformed(final String rowId, String actionName, String actionValue){
			if(actionName.equals(Action.DepartmentConfig.name())){
				//设置部门
				configDepartment(getContext(), tenant, table, rowId);
			}
			else if(actionName.equals(Action.AuthConfig.name())){
				//检验手机号是否为11位
				String mobileNumber = "";
				if("1".equals(table.getExtraData(rowId, "isCanChangeMobileNo")[0])){
					mobileNumber = table.getEditValue(rowId, Columns.MobileNo.name())[0];
				}
				else{
					mobileNumber = table.getExtraData(rowId, "mobileNumber")[0];
				}
				if(StringHelper.isEmpty(mobileNumber) || !mobileNumber.trim().matches("^\\d{11}$")){
					alert("所选员工的手机号码不能为空，且必须为11位数字，请修改！");
					return;
				}
				//设置权限
				configAuth(getContext(), tenant, table, rowId);
			}
			else if(actionName.equals(Action.Delete.name())){
				//删除当前行
				confirm("是否删除当前员工？", new Runnable(){

					public void run(){
						DeleteEmployeeTask deleteEmployeeTask = new DeleteEmployeeTask(GUID.valueOf(rowId));
						getContext().handle(deleteEmployeeTask);
						table.render();
						hint("删除成功！");
					}
				});
			}
		}

		/**
		 * 设置部门
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
			MsgRequest request = new MsgRequest(pci, "设置部门", windowStyle);
			request.setResponseHandler(new ResponseHandler(){
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					if(returnValue != null){
						DepartmentTree.DepartmentNode node = (DepartmentTree.DepartmentNode)returnValue;
						for(int i = 0; i < rowIds.length; i++){
							stable.updateCell(rowIds[i], Columns.Department.name(), node.getId().toString(), node
							        .getName().equals(stenant.getTitle()) ? "公司直属" : node.getName());
						}
					}
					stable.renderUpate();
				}
			});
			context.bubbleMessage(request);
		}

		/**
		 * 配置权限
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
			MsgRequest request = new MsgRequest(pci, "设置权限", windowStyle);
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
