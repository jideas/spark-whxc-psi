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
 * <p>Ա�����û���Ϣ������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-9
 */

public class WizardEmployeeInfoStepRender extends WizardBaseStepRender{

	/**
	 * ����
	 */
	@Override
	protected String getTitle(){
		return "����Ա�����û�";
	}

	/**
	 * ������Ϣ��
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
	 * �ײ���߰�ť��
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){
		GridLayout layout = new GridLayout(2);
		leftButtonArea.setLayout(layout);
		//
		GridData buttonGridData = new GridData();
		buttonGridData.widthHint = 80;
		buttonGridData.heightHint = 30;
		//���ò���
		Button setDeptButton = new Button(leftButtonArea, JWT.APPEARANCE2);
		setDeptButton.setID(WizardEmployeeInfoStepProcessor.ID_Button_SetDept);
		setDeptButton.setText("���ò���");
//		setDeptButton.setEnabled(Boolean.FALSE);
		setDeptButton.setLayoutData(buttonGridData);
		//����Ȩ��
		Button setRolesButton = new Button(leftButtonArea, JWT.APPEARANCE2);
		setRolesButton.setID(WizardEmployeeInfoStepProcessor.ID_Button_SetRoles);
		setRolesButton.setText("����Ȩ��");
//		setRolesButton.setEnabled(Boolean.FALSE);
		setRolesButton.setLayoutData(buttonGridData);
	}

	/**
	 * �ڲ��࣬����б���
	 */
	public static class InnerClass extends PSIBillsPageRender{

		/**
		 * ���صײ�����
		 */
		protected boolean hideFooterArea() {
			return true;
		}
		
		@Override
		protected void afterFooterRender(){
			super.afterFooterRender();
			//
			new Label(headerLeftArea).setText("ѡ���ţ�");
			new LWComboList(headerLeftArea, JWT.APPEARANCE3)
			        .setID(WizardEmployeeInfoStepProcessor.InnerClass.ID_List_DeptScope);
			//
			new Label(headerLeftArea).setText(" ");
			//
			new Label(headerLeftArea).setText("����˾���� ");
			//
			Label labelAuthroized = new Label(headerLeftArea);
			labelAuthroized.setText("0");
			labelAuthroized.setForeground(Color.COLOR_RED);
			labelAuthroized.setID(WizardEmployeeInfoStepProcessor.InnerClass.ID_Label_Authroized);
			//
			new Label(headerLeftArea).setText(" λԱ�������û�Ȩ�ޣ����������� ");
			//
			Label labelAuthroizable = new Label(headerLeftArea);
			labelAuthroizable.setText("0");
			labelAuthroizable.setForeground(Color.COLOR_RED);
			labelAuthroizable.setID(WizardEmployeeInfoStepProcessor.InnerClass.ID_Label_Authroizable);
			//
			new Label(headerLeftArea).setText(" λ��");
			//
			new ComboList(headerRightArea, JWT.APPEARANCE3)
			        .setID(WizardEmployeeInfoStepProcessor.InnerClass.ID_List_Scope);
		}

		/**
		 * ��ȡ��
		 */
		@Override
		public STableColumn[] getColumns(){
			STableColumn[] columns = new STableColumn[7];
			columns[0] = new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.Name.name(), 80, JWT.CENTER, "����");
			((STextEditColumn)columns[0]).setMaxLength(20);
			columns[1] =
			        new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.MobileNo.name(), 100, JWT.CENTER, "�ֻ�");
			((STextEditColumn)columns[1]).setMaxLength(11);
			columns[2] =
			        new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.IdentityNumber.name(), 110, JWT.LEFT,
			                "���֤����");
			((STextEditColumn)columns[2]).setMaxLength(20);
			columns[3] = new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.Email.name(), 110, JWT.LEFT, "����");
			columns[4] =
			        new STableColumn(WizardEmployeeInfoStepProcessor.Columns.Department.name(), 80, JWT.LEFT, "����");
			columns[5] =
			        new STextEditColumn(WizardEmployeeInfoStepProcessor.Columns.Postion.name(), 80, JWT.CENTER, "��λ");
			columns[6] = new STableColumn(WizardEmployeeInfoStepProcessor.Columns.Roles.name(), 80, JWT.LEFT, "�û�Ȩ��");
			//
			columns[2].setGrab(true);
			columns[3].setGrab(true);
			columns[4].setGrab(true);
			columns[6].setGrab(true);
			return columns;
		}

		/**
		 * ��Ԫ��ȡֵ
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
		 * �����ʽ
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
