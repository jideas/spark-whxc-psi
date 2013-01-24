package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.organization.entity.EmployeeItem;
import com.spark.psi.publish.base.organization.key.GetEmployeeListByAuthKey;

/**
 * 销售人员选择界面处理器
 * 
 */
public class SalesmanSelectProcessor extends
		PSIListPageProcessor<EmployeeItem> {

	final static String ID_Text_Search = "Text_Search";
	final static String ID_Button_Confirm = "Button_Confirm";
	final static String ID_Button_Cancel = "Button_Cancel";

	static enum Columns {
		EmployeeName, Department
	}

	private Text searchText;

	private GUID employeeId;

	public void init(Situation context) {
		super.init(context);
	}

	public void process(Situation context) {
		super.process(context);
		if(this.getArgument()!=null&&!this.getArgument().equals("null")){
			
			employeeId = GUID.valueOf(this.getArgument().toString());
		}
		searchText = this.createControl(ID_Text_Search, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		//
		this.createControl(ID_Button_Confirm, Button.class).addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (StringUtils.isEmpty(table.getSelection())) {
							alert("请选择销售人员");
							return;
						}
						getContext().bubbleMessage(
								new MsgResponse(true, GUID.valueOf(table
										.getSelection())));
					}
				});
		this.createControl(ID_Button_Cancel, Button.class).addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContext().bubbleMessage(new MsgCancel());
					}
				});
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetEmployeeListByAuthKey key = new GetEmployeeListByAuthKey(getContext().find(LoginInfo.class).getEmployeeInfo().getDepartmentId(),
				Auth.Sales);
		key.setSearchText(StringUtils.isEmpty(searchText.getText())?"":searchText.getText());
		
		@SuppressWarnings("unchecked")
		ListEntity<EmployeeItem> listEntity = context.find(ListEntity.class,
				key);
		return listEntity.getItemList().toArray();
	}

	/**
	 * 
	 */
	public boolean isSelected(Object element) {
		return ((EmployeeItem) element).getId().equals(employeeId);
	}

	public final String getElementId(Object element) {
		return ((EmployeeItem) element).getId().toString();
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}

}
