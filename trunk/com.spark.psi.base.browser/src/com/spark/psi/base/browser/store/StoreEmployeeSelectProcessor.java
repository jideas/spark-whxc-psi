package com.spark.psi.base.browser.store;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;

public abstract class StoreEmployeeSelectProcessor extends PageProcessor
		implements SEditContentProvider {

	public final static String ID_Table = "EmployeeTable";
	public final static String ID_Button_Select = "ButtonSelect";
	public final static String ID_Button_Cancel = "ButtonCancel";

	protected SEditTable table;

	protected String[] employeeIds;

	protected GUID storeId;
	
	/**
	 * 
	 */
	public final void init(Situation context) {
		Object argument = this.getArgument();
		if (argument != null && argument instanceof String) {
			employeeIds = StringUtils.split((String) argument, ";");
		}
		Object arg1 = this.getArgument2();
		if (arg1 != null && arg1 instanceof GUID){
			storeId = (GUID)arg1;
		}
	}

	public final void process(Situation context) {
		table = this.createControl(ID_Table, SEditTable.class);
		table.setContentProvider(this);
		table.render();

		//
		Button selectButton = this
				.createControl(ID_Button_Select, Button.class);
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleConfirmSelect();
			}
		});
		Button cancelButton = this
				.createControl(ID_Button_Cancel, Button.class);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgCancel());
			}
		});
	}

	protected abstract String[] getSelections();

	/**
	 * 
	 */
	private void handleConfirmSelect() {
		String[] selections = getSelections();
		StringBuffer ids = new StringBuffer();
		StringBuffer names = new StringBuffer();
		if (selections != null && selections.length > 0) {
			for (String id : selections) {
				ids.append(id);
				ids.append(';');
				names.append(getEmployeeNameById(id));
				names.append(';');
			}
			ids.deleteCharAt(ids.length() - 1);
			names.deleteCharAt(names.length() - 1);
		}
		getContext().bubbleMessage(
				new MsgResponse(true, ids.toString(), names.toString()));
	}

	/**
	 * 根据id获取员工姓名
	 * 
	 * @param id
	 * @return
	 */
	protected final String getEmployeeNameById(String id) {
		return table.getExtraData(id, "name")[0];
	}

	public final String getElementId(Object element) {
		return ((EmployeeItem) element).getId().toString();
	}

	public String getValue(Object element, String columnName) {
		return null;
	}

	public String[] getActionIds(Object element) {
		return null;
	}

	public Object getNewElement() {
		return null;
	}

	public SNameValue[] getExtraData(Object element) {
		return new SNameValue[] { new SNameValue("name",
				((EmployeeItem) element).getName()+"("+((EmployeeItem) element).getDepartmentName()+")")};
	}

}
