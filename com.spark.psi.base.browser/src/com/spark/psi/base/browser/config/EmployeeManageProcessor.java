package com.spark.psi.base.browser.config;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableTextColor;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.portal.browser.WindowStyle.Location;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.EmployeeStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.key.GetEmployeeListKey;
import com.spark.psi.publish.base.organization.task.ChangeEmployeeStatusTask;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeInfoTask;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeInfoTask.Item;

/**
 * 员工管理界面处理器
 * 
 * 
 */
public class EmployeeManageProcessor extends PSIListPageProcessor<EmployeeItem>
		implements IDataProcessPrompt {
 
	public final static String ID_List_Scope = "List_Scope";
	public final static String ID_List_DeptScope = "List_DeptScope";

	public final static String ID_Button_DepartmentConfig = "Button_DepartmentConfig";
	public final static String ID_Button_AuthConfig = "Button_AuthConfig";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Check_status = "Check_status";
	
	private static final String ALL_EMPLOYEE_ITEM = "全部员工";

	private ListSourceAdapter queryScopeSource;
	
	private LWComboList deptScopeList;

	private ComboList authScopeList;

	private Button statusCheckBox;

	static enum Columns {
		name, mobileNumber, idNumber, email, department, postion, roles
	}

	TenantInfo tenant;

	@Override
	public void process(Situation situation) {
		super.process(situation);
		//
		tenant = getContext().find(TenantInfo.class);
		LoginInfo login = getContext().find(LoginInfo.class);

		deptScopeList = this.createControl(ID_List_DeptScope,
				LWComboList.class, JWT.NONE);
//		queryScopeSource1 = PSIProcessorUtils.initQueryScopeSource(
//				deptScopeList, "公司直属",ALL_EMPLOYEE_ITEM,true);
		queryScopeSource = PSIProcessorUtils.initDeparmentQueryScopeSource(deptScopeList);
		deptScopeList.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});

		statusCheckBox = this.createControl(ID_Check_status, Button.class);
		statusCheckBox.setSelection(true);
		statusCheckBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});

		authScopeList = this.createControl(ID_List_Scope, ComboList.class,
				JWT.NONE);
		authScopeList.addItem(ALL_EMPLOYEE_ITEM);
		authScopeList.addItem("有用户角色的员工");
		authScopeList.addItem("无用户角色的员工");
		authScopeList.setSelection(0);

		authScopeList.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});

		Button buttonDepartmentConfig = this.createControl(
				ID_Button_DepartmentConfig, Button.class, JWT.NONE);
		if(buttonDepartmentConfig != null){
			if (login.hasAuth(Auth.SubFunction_ConfigMange_Employee_SetDeparemnt)) {
				buttonDepartmentConfig.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						configDepartment(table.getSelections());
					}
				});
			} else {
				buttonDepartmentConfig.setVisible(false);
				// GridData data = (GridData)buttonDepartmentConfig.getLayoutData();
				// data.exclude = true;
				// buttonDepartmentConfig.setLayoutData(data);
				// buttonDepartmentConfig.getParent().layout();
			}
		}

		//
		Button buttonAuthConfig = this.createControl(ID_Button_AuthConfig,
				Button.class, JWT.NONE);
		if(buttonAuthConfig != null){
			buttonAuthConfig.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					configAuth(table.getSelections());
				}
			});
		}
		//
		Button buttonSave = this.createControl(ID_Button_Save, Button.class,
				JWT.NONE);
		if(buttonSave != null){
			buttonSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					processData();
				}
			});
		} 
	}

	private void save() {
		String[] rowIds = table.getAllRowId();
		if (rowIds != null) {
			List<UpdateEmployeeInfoTask.Item> items = new ArrayList<UpdateEmployeeInfoTask.Item>();
			boolean isNull = true;  //非空验证
			StringBuilder nullErrMsg = new StringBuilder();
			boolean isMail = true;  //是否是正确的邮箱格式
			boolean isMobile = true;  //是否是正确的手机号码
			List<STableTextColor> sTableTextColors = new ArrayList<STableTextColor>();
			for (int i = 0; i < rowIds.length; i++) {
				String rowId = rowIds[i];
				String[] values = table.getEditValue(rowId,
						Columns.name.name(), Columns.idNumber.name(),
						Columns.mobileNumber.name(), Columns.email.name(),
						Columns.postion.name(), Columns.department.name(),
						Columns.roles.name());

				UpdateEmployeeInfoTask.Item item = new UpdateEmployeeInfoTask.Item();
				String[] extraData = table.getExtraData(rowId, "isNew");
				String mobileNumber = "";
				if ("1".equals(extraData[0])) {
					item.setCreate(true);
				}
				if ("1".equals(table.getExtraData(rowId,
						"isCanChangeMobileNo")[0])) {
					mobileNumber = values[2];
				} else {
					mobileNumber = table.getExtraData(rowId, "mobileNumber")[0];
				}
//				if (!(StringHelper.isNotEmpty(values[0])
//						&& StringHelper.isNotEmpty(values[5].trim()) && StringHelper
//							.isNotEmpty(mobileNumber))) {
//					// 如果没有填姓名、手机号码、部门，则不保存
//					continue;
//					
//				} 
				if (isSave(values)) {
					boolean isVaild = true;
					if(StringHelper.isEmpty(values[0])){
						sTableTextColors.add(new STableTextColor(rowId, 1, STable.WARNINGCOLOR));
						isVaild = false;
						if(nullErrMsg.indexOf("姓名")==-1){
							if(nullErrMsg.length()>0){
								nullErrMsg.append(",");
							}
							nullErrMsg.append("姓名");
						}
					}else {
						sTableTextColors.add(new STableTextColor(rowId, 1, STable.NORMALCOLOR));
					}
					if(StringHelper.isEmpty(values[5])){
						sTableTextColors.add(new STableTextColor(rowId, 5, STable.WARNINGCOLOR));
						isVaild = false;
						if(nullErrMsg.indexOf("部门")==-1){
							if(nullErrMsg.length()>0){
								nullErrMsg.append(",");
							}
							nullErrMsg.append("部门");
						}
					}else {
						sTableTextColors.add(new STableTextColor(rowId, 5, STable.NORMALCOLOR));
					}
					if(StringHelper.isEmpty(mobileNumber)){
						sTableTextColors.add(new STableTextColor(rowId, 2, STable.WARNINGCOLOR));
						isVaild = false;
						if(nullErrMsg.indexOf("手机号码")==-1){
							if(nullErrMsg.length()>0){
								nullErrMsg.append(",");
							}
							nullErrMsg.append("手机号码");
						}
					}else {
						sTableTextColors.add(new STableTextColor(rowId, 2, STable.NORMALCOLOR));
					}
					if(!isVaild){
						isNull = isVaild;
						continue;
					}
				}else{
					continue;
				}
				if(StringHelper.isNotEmpty(values[3])){
					if(!validationIsMail(values[3])){
						sTableTextColors.add(new STableTextColor(rowId, 4, STable.WARNINGCOLOR));
						isMail = false;
						continue;
					}else{
						sTableTextColors.add(new STableTextColor(rowId, 4, STable.NORMALCOLOR));
					}
				}
				if(!validationMobileNo(mobileNumber)){
					sTableTextColors.add(new STableTextColor(rowId, 2, STable.WARNINGCOLOR));
					isMobile = false;
					continue; 
				}else{
					sTableTextColors.add(new STableTextColor(rowId, 2, STable.NORMALCOLOR));
				}
				item.setId(GUID.valueOf(rowId));
				item.setName(values[0].trim());
				item.setIdNumber(values[1]);
				item.setMobileNo(mobileNumber);
				item.setEmail(values[3]);
				item.setPosition(values[4]);
				item.setDepartmentId(GUID.valueOf(values[5].trim()));
				item.setRoles(Integer.parseInt(StringUtils.isEmpty(values[6]) ? "-1"
						: values[6].trim()));
		  		items.add(item);
				
			}
			table.setTextBackgroundColor(sTableTextColors);
			if(!isNull){
				alert(nullErrMsg+"不能为空！");
				return ;
			}
			if(!isMail){
				alert("必须是正确的邮箱地址格式！");
				return ;
			}
			if(!isMobile){
				alert("必须是正确的手机号码！");
				return ;
			}
			if(!validationMobileIsOnly(items)){
				return ;
			}
			if(!validationUserCount(items)){
				return ;
			}
			try{
				getContext().handle(
						new UpdateEmployeeInfoTask(items
								.toArray(new UpdateEmployeeInfoTask.Item[items
								                                         .size()])));	            
            }
            catch(Exception e){
	            alert(e.getMessage());
	            return ;
            }
			EmployeeManageProcessor.this.hint("保存成功");
			table.render();
		}

	}

	private boolean validationIsMail(String string){
	    if(string.indexOf("@")==-1)return false;
	    return true;
    }
	
	private boolean validationMobileNo(String telephone){
		Pattern p = Pattern.compile( "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1} \\d{1}-?\\d{8}$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})$))");
        Matcher m = p.matcher(telephone);
        boolean b = m.matches();
        return b;
	}

	private boolean isSave(String[] values){
	    for(String string : values){
	        if(!com.jiuqi.util.StringHelper.isNull(string))return true;
        }
	    return false;
    }

	private boolean validationUserCount(List<Item> items){
	    int userCount = tenant.getUserCount();
	    int userCounted = 0;
	    for(Item item : items){
	        if(item.getRoles()>0){
	        	userCounted++;
	        }
        }
	    if(userCounted>userCount){
	    	alert("用户数量超过可设置的数量！");
	    	return false;
	    }
	    return true;
    }

	@SuppressWarnings("unchecked")
	public Object[] getElements(Context context, STableStatus tablestatus) {
		//XXX：数据修改判断机制不完善，目前切换查询范围也会导致触发数据变化，所以先每次都reset
		resetDataChangedstatus();
		//查询所有员工，确定已授权用户数量 
		GetEmployeeListKey key = new GetEmployeeListKey();
		key.setQueryAll(false);
		ListEntity<EmployeeItem> result = context.find(ListEntity.class, key);
		//按照查询条件查询用户
		key = new GetEmployeeListKey();
		key.setQueryAll(false);
		key.setStatus(statusCheckBox.getSelection() ? EmployeeStatus.Normal
				: null);
		QueryScope qs = (QueryScope) queryScopeSource.getElementById(deptScopeList.getText());
		key.setQueryScope(qs);
		key.setQueryAll(qs.getName().equals(ALL_EMPLOYEE_ITEM));
		key.setRoleScope(authScopeList.getList().getSelectionIndex());
		result = context.find(ListEntity.class, key);
		if (result != null && result.getTotalCount() > 0) {
			return	result.getItemList().toArray();
		} else {
			return new Object[] { getNewElement() }; 
		}
	}

	public String getElementId(Object element) {
		if (element instanceof String) {
			return (String) element;
		} else {
			EmployeeItem item = (EmployeeItem) element;
			return item.getId().toString();
		}
	}

	@Override
	public boolean isSelectable(Object element) {
		if (element instanceof EmployeeItem) {
			return ((EmployeeItem) element).getStatus() != EmployeeStatus.Supper;
		} else {
			return true;
		}
	}

	public String getValue(Object element, String columnName) {
		if (element instanceof String) {
			return "";
		}
		EmployeeItem item = (EmployeeItem) element;
		Columns column = null;
		try{
			column=Columns.valueOf(columnName);
		}catch (Exception e) {
		}
		if (column != null) {
			switch (column.ordinal()) {
			case 0:
				return item.getName();
			case 1:
				if (item.getRoles() != -1)
					return null; // 只有从未设置过角色的用户才能修改手机号码
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
	public SNameValue[] getExtraData(Object element) {
		SNameValue[] result = new SNameValue[2];
		result[0] = new SNameValue("isCanChangeMobileNo", "1");
		if (element instanceof String) {
			result[1] = new SNameValue("isNew", "1");
		} else {
			EmployeeInfo emp = (EmployeeInfo) element;
			if (emp.getRoles() > -1) {
				result[0] = new SNameValue("isCanChangeMobileNo", "0");
			}
			result[1] = new SNameValue("mobileNumber", emp.getMobileNo());
		}
		return result;
		// return new SNameValue[]{new SNameValue("isNew", "1")};
	}

	@Override
	public String[] getTableActionIds() {
		LoginInfo login = getContext().find(LoginInfo.class);
		List<String> actions = new ArrayList<String>();
		actions.add(Action.AuthConfig.name());
		if (login.hasAuth(Auth.SubFunction_ConfigMange_Department))
			actions.add(Action.DepartmentConfig.name());
		actions.add(Action.Resign.name());
		actions.add(Action.Reinstatus.name());
		actions.add(Action.Delete.name());
		return actions.toArray(new String[0]);
	}

	protected String[] getElementActionIds(Object element) {
		if (element instanceof String) {
			return new String[] { Action.DepartmentConfig.name(),
					Action.AuthConfig.name(), Action.Delete.name() };
		}
		EmployeeItem item = (EmployeeItem) element;
		if (item != null) {
			String[] actions = new String[item.getActions().length];
			for (int i = 0; i < actions.length; i++) {
				actions[i] = item.getActions()[i].name();
			}
			return actions;
		}
		return null;
	}

	public Object getNewElement() {
		return GUID.randomID().toString();
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		String name = table.getEditValue(rowId, Columns.name.name())[0];
		if (actionName.equals(Action.DepartmentConfig.name())) {
			configDepartment(rowId);
		} else if (actionName.equals(Action.AuthConfig.name())) {
			configAuth(rowId);
		} else if (actionName.equals(Action.Resign.name())) {
			// TODO：处理离职
			this.confirm("确定要将["+name+"]设为离职吗?", new Runnable(){
				
				public void run(){
					ChangeEmployeeStatusTask task = new ChangeEmployeeStatusTask(
							EmployeeStatus.Departure, GUID.valueOf(rowId));
					getContext().handle(task);
					table.updateRowActions(rowId, new String[]{Action.Reinstatus.name()});
					String rolesStr = table.getEditValue(rowId,
							Columns.roles.name())[0];
					int roles = Integer.parseInt(StringUtils.isEmpty(rolesStr) ? "0"
							: rolesStr);
					if(roles>0){
						table.updateCell(rowId, Columns.roles.name(), "0", ""); 
					}
					table.renderUpate();
				}
			});
			} else if (actionName.equals(Action.Reinstatus.name())) {
			// TODO：处理复职 
				this.confirm("确定要将["+name+"]复职吗?", new Runnable(){

					public void run(){
						ChangeEmployeeStatusTask task = new ChangeEmployeeStatusTask(
								EmployeeStatus.Normal, GUID.valueOf(rowId));
						getContext().handle(task);
						table.updateRowActions(rowId, new String[]{Action.DepartmentConfig.name(),
						        Action.AuthConfig.name(), Action.Resign.name()});
						table.renderUpate();	                    
                    }
				});
			} else if (actionName.equals(Action.Delete.name())) {
			table.removeRow(rowId);
			table.renderUpate();
		}
	}

	/**
	 * // TODO：处理设置权限
	 * 
	 * @param rowIds
	 */
	private void configAuth(final String... rowIds) {
		if(null==rowIds){
			alert("请选择要操作的用户！");
			return;
		}
		StringBuilder name = new StringBuilder();
		String[] depts = new String[rowIds.length];
		int roles = 0;
		if (rowIds.length == 1) {
			String rolesStr = table.getEditValue(rowIds[0],
					Columns.roles.name())[0];
			roles = Integer.parseInt(StringUtils.isEmpty(rolesStr) ? "0"
					: rolesStr);
		}
		for (int i = 0 ;i<rowIds.length;i++) {
			String string = rowIds[i];
			if (name.length() > 0)
				name.append(",");
			name.append(table.getEditValue(string, Columns.name.name())[0]);
			depts[i] = table.getEditValue(string, Columns.department.name())[0];
		}
		PageControllerInstance pci = new PageControllerInstance(
				new PageController(AuthConfigProcessor.class,
						AuthConfigRender.class), name.toString(), roles,depts);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE);
		windowStyle.setSize(640, 490);
		MsgRequest request = new MsgRequest(pci, "设置权限", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				int roles = (Integer) returnValue;
				String rolesName = (String) returnValue2;
				for (int i = 0; i < rowIds.length; i++) {
					table.updateCell(rowIds[i], Columns.roles.name(),
							String.valueOf(roles), rolesName);
				}
				table.renderUpate();
			}
		});
		getContext().bubbleMessage(request);
	} 

	private void configDepartment(final String... rowIds) {
		Integer[] roles = new Integer[rowIds.length];
		for (int i = 0 ;i<rowIds.length;i++) {
			String r = table.getEditValue(rowIds[i], Columns.roles.name())[0];
			if(!StringHelper.isEmpty(r)){
				roles[i] = Integer.parseInt(r);
			}
		}
		PageControllerInstance pci = new PageControllerInstance(
				new PageController(DepartmentConfigProcessor.class,
						DepartmentConfigRender.class),roles,JWT.CHECK);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE, Location.Context);
		windowStyle.setSize(240, 360);
		MsgRequest request = new MsgRequest(pci, "设置部门", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				if (returnValue != null) {
					DepartmentTree.DepartmentNode node = (DepartmentTree.DepartmentNode) returnValue;
					for (int i = 0; i < rowIds.length; i++) {
						table.updateCell(
								rowIds[i],
								Columns.department.name(),
								node.getId().toString(),
								node.getName().equals(tenant.getTitle()) ? "公司直属"
										: node.getName());
					}
				}
				table.renderUpate();
			}
		});
		getContext().bubbleMessage(request);
	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		save();
		resetDataChangedstatus();
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
	        	alert("手机号码重复："+item.getMobileNo());
	        	return false;
	        }
	        list.add(item.getMobileNo());
        }
		return true;
    }

	@Override
	protected String getExportFileTitle() {
		return "员工列表";
	}
	
}
