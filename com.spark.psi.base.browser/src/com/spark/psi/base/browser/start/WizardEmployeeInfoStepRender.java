package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.SimpleListPageRender;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.psi.base.browser.PSIBillsPageRender;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;

/**
 * <p>员工与用户信息界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-9
 */

public class WizardEmployeeInfoStepRender extends WizardBaseStepRender{

	/**
	 * 标题
	 */
	@Override
	protected String getTitle(){
		return "配置员工和用户";
	}

	/**
	 * 内容信息区
	 */
	@Override
	protected void addInfoArea(Composite infoArea){
		PageController pageController =
		        new PageController(WizardEmployeeInfoStepProcessor.InnerClass.class,
		                WizardEmployeeInfoStepRender.InnerClass.class);
		ControllerPage page =
		        (ControllerPage)infoArea.showPage(ControllerPage.NAME, new PageControllerInstance(pageController));
		controls.put(WizardEmployeeInfoStepProcessor.ID_Table_Employee, ((SimpleListPageRender)page
		        .getRender()).getTable());
	}

	/**
	 * 底部左边按钮区
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){
		GridLayout layout = new GridLayout(2);
		leftButtonArea.setLayout(layout);
		//
		GridData buttonGridData = new GridData();
		buttonGridData.widthHint = 80;
		buttonGridData.heightHint = 30;
		//设置部门
		Button setDeptButton = new Button(leftButtonArea, JWT.APPEARANCE2);
		setDeptButton.setID(WizardEmployeeInfoStepProcessor.ID_Button_SetDept);
		setDeptButton.setText("设置部门");
//		setDeptButton.setEnabled(Boolean.FALSE);
		setDeptButton.setLayoutData(buttonGridData);
		//设置权限
		Button setRolesButton = new Button(leftButtonArea, JWT.APPEARANCE2);
		setRolesButton.setID(WizardEmployeeInfoStepProcessor.ID_Button_SetRoles);
		setRolesButton.setText("设置权限");
//		setRolesButton.setEnabled(Boolean.FALSE);
		setRolesButton.setLayoutData(buttonGridData);
	}

	/**
	 * 内部类，存放列表区
	 */
	public static class InnerClass extends PSIBillsPageRender{

		/**
		 * 隐藏底部区域
		 */
		protected boolean hideFooterArea() {
			return true;
		}
		
		@Override
		protected void afterFooterRender(){
			super.afterFooterRender();
			//
			new Label(headerLeftArea).setText("选择部门：");
			new LWComboList(headerLeftArea, JWT.APPEARANCE3)
			        .setID(WizardEmployeeInfoStepProcessor.InnerClass.ID_List_DeptScope);
			//
			new Label(headerLeftArea).setText(" ");
			//
			new Label(headerLeftArea).setText("本公司已有 ");
			//
			Label labelAuthroized = new Label(headerLeftArea);
			labelAuthroized.setText("0");
			labelAuthroized.setForeground(Color.COLOR_RED);
			labelAuthroized.setID(WizardEmployeeInfoStepProcessor.InnerClass.ID_Label_Authroized);
			//
			new Label(headerLeftArea).setText(" 位员工具有用户权限，还可以设置 ");
			//
			Label labelAuthroizable = new Label(headerLeftArea);
			labelAuthroizable.setText("0");
			labelAuthroizable.setForeground(Color.COLOR_RED);
			labelAuthroizable.setID(WizardEmployeeInfoStepProcessor.InnerClass.ID_Label_Authroizable);
			//
			new Label(headerLeftArea).setText(" 位。");
			//
			new ComboList(headerRightArea, JWT.APPEARANCE3)
			        .setID(WizardEmployeeInfoStepProcessor.InnerClass.ID_List_Scope);
		}

		/**
		 * 获取列
		 */
		@Override
		public STableColumn[] getColumns(){
			STableColumn[] columns = new STableColumn[7];
			columns[0] = new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.Name.name(), 80, JWT.CENTER, "姓名");
			((STextEditColumn)columns[0]).setMaxLength(20);
			columns[1] =
			        new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.MobileNo.name(), 100, JWT.CENTER, "手机");
			((STextEditColumn)columns[1]).setMaxLength(11);
			columns[2] =
			        new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.IdentityNumber.name(), 110, JWT.LEFT,
			                "身份证号码");
			((STextEditColumn)columns[2]).setMaxLength(20);
			columns[3] = new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.Email.name(), 110, JWT.LEFT, "邮箱");
			columns[4] =
			        new STableColumn(WizardEmployeeInfoStepProcessor.Columns.Department.name(), 80, JWT.LEFT, "部门");
			columns[5] =
			        new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.Postion.name(), 80, JWT.CENTER, "岗位");
			columns[6] = new STableColumn(WizardEmployeeInfoStepProcessor.Columns.Roles.name(), 80, JWT.LEFT, "用户权限");
			//
			columns[2].setGrab(true);
			columns[3].setGrab(true);
			columns[4].setGrab(true);
			columns[6].setGrab(true);
			return columns;
		}

		/**
		 * 单元格取值
		 */
		@Override
		public String getText(Object element, int columnIndex){
			if (element instanceof String) {
				return "";
			}
			EmployeeItem item = (EmployeeItem)element;
			switch(columnIndex){
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

		/**
		 * 表格样式
		 */
		public STableStyle getTableStyle(){
			SEditTableStyle tableStyle = new SEditTableStyle();
			tableStyle.setRowHeight(25);
			tableStyle.setAutoAddRow(true);
			tableStyle.setSelectionMode(SSelectionMode.Multi);
			return tableStyle;
		}

	}

}
