package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;

/**
 * 员工管理界面视图
 * 
 */
public class EmployeeManageRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new Label(headerLeftArea).setText("选择部门：");
		new LWComboList(headerLeftArea, JWT.APPEARANCE3)
				.setID(EmployeeManageProcessor.ID_List_DeptScope);
		new Label(headerLeftArea).setText(" ");
		;
		Button checkstatus = new Button(headerLeftArea, JWT.CHECK);
		checkstatus.setText("仅显示在职员工");
		checkstatus.setID(EmployeeManageProcessor.ID_Check_status); 

		//
		new ComboList(headerRightArea, JWT.APPEARANCE3)
				.setID(EmployeeManageProcessor.ID_List_Scope);

		//
		//
		if(!hideFooterArea()){
			Button buttonAuthConfig = new Button(footerLeftArea, JWT.APPEARANCE2);
			buttonAuthConfig.setText(" 设置权限 ");
			buttonAuthConfig.setEnabled(false);
			buttonAuthConfig.setID(EmployeeManageProcessor.ID_Button_AuthConfig);
			
			
			new Label(footerLeftArea).setText(" ");

			Button buttonDepartmentConfig = new Button(footerLeftArea,
					JWT.APPEARANCE2);
			buttonDepartmentConfig.setEnabled(false);
			buttonDepartmentConfig.setText(" 设置部门 ");
			buttonDepartmentConfig
			.setID(EmployeeManageProcessor.ID_Button_DepartmentConfig);

			//
			Button buttonSave = new Button(footerRightArea, JWT.APPEARANCE3);
			buttonSave.setText("   保存   ");
			buttonSave.setID(EmployeeManageProcessor.ID_Button_Save);
		}

		//
		// table.addClientEventHandler("valueChanged", "SaasEditTable.test");
		table.addClientEventHandler(STable.CLIENT_EVENT_SELECTION,
				"PSIBase.EmployeeManage.handleSelection"); // 客户端界面逻辑，暂时放在视图层
	}

	public STableColumn[] getColumns() {
		STableColumn[] columns = new STableColumn[7];
		columns[0] = new STextEditColumn(
				EmployeeManageProcessor.Columns.name.name(), 80, JWT.LEFT,
				"姓名");
		((STextEditColumn)columns[0]).setMaxLength(20);
		columns[1] = new STextEditColumn(
				EmployeeManageProcessor.Columns.mobileNumber.name(), 100,
				JWT.LEFT, "手机");
		((STextEditColumn)columns[1]).setMaxLength(11);
		columns[2] = new STextEditColumn(
				EmployeeManageProcessor.Columns.idNumber.name(), 110, JWT.LEFT,
				"身份证号码");
		((STextEditColumn)columns[2]).setMaxLength(18);
		columns[3] = new STextEditColumn(
				EmployeeManageProcessor.Columns.email.name(), 110, JWT.LEFT,
				"邮箱");
		columns[4] = new STableColumn(
				EmployeeManageProcessor.Columns.department.name(), 80,
				JWT.LEFT, "部门");
		columns[5] = new STextEditColumn(
				EmployeeManageProcessor.Columns.postion.name(), 80,
				JWT.LEFT, "岗位");
//		columns[5].get
		columns[6] = new STableColumn(
				EmployeeManageProcessor.Columns.roles.name(), 80, JWT.LEFT,
				"用户权限");

//		columns[1].setGrab(true);
		columns[2].setGrab(true);
		columns[3].setGrab(true);
		columns[4].setGrab(true);
		columns[6].setGrab(true);

		return columns;
	}

	public STableStyle getTableStyle() {
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setRowHeight(25);
		tableStyle.setAutoAddRow(true);
		tableStyle.setPageAble(false);
		tableStyle.setSelectionMode(SSelectionMode.Multi);
		return tableStyle;
	}

	public String getText(Object element, int columnIndex) {
		if (element instanceof String) {
			return "";
		}
		EmployeeItem item = (EmployeeItem) element;

		switch (columnIndex) {
		case 0:
			return item.getName();
		case 1:
			return item.getMobileNo();
		case 2:
			return item.getIdNumber();
		case 3:
			return item.getEmail();
		case 4:
			return item.getDepartmentName();
		case 5:
			return item.getPosition();
		case 6:
			return item.getRolesInfo();
		default:
			return "";
		}
	}

	@Override
	public String getToolTipText(Object element, int columnIndex) {
		if (element instanceof String) {
			return null;
		}
		EmployeeItem item = (EmployeeItem) element;

		switch (columnIndex) {
		case 0:
			return item.getName();
		case 1:
			return item.getMobileNo();
		case 2:
			return item.getIdNumber();
		case 3:
			return item.getEmail();
		case 4:
			return item.getDepartmentName();
		case 5:
			return item.getPosition();
		case 6:
			return item.getRolesInfo();
		default:
			return null;
		}
	}
	
}
