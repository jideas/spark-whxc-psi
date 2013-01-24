package com.spark.psi.base.browser.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.config.entity.SalesmanCreditItem;
import com.spark.psi.publish.base.config.key.GetSalesManCreditListKey;
import com.spark.psi.publish.base.config.task.SaveSalesmanCreditTask;

/**
 * 销售人员授权配置界面处理器
 * 
 * 
 */
public class SalesmanCreditConfigProcessor extends
		PSIListPageProcessor<SalesmanCreditItem> implements IDataProcessPrompt{
	public final static String ID_Label_Count = "Label_Count";
	public final static String ID_Button_Clean = "Button_Clean";
	public final static String ID_Button_Save = "Button_Save";

	public static enum Columns {
		EmployeeName, Department, //
		CustomerCreditAmountUpperLimit, TotalCreditAmount, AllocatedCount, AllocatedAmount, //
		CustomerCreditDayUpperLimit, OrderApprovalUpperLimit
	}

	private Label countLabel;

	@Override
	public void process(Situation situation) {
		super.process(situation);

		countLabel = this.createControl(ID_Label_Count, Label.class, JWT.NONE);

		Button cleanButton = this.createControl(ID_Button_Clean, Button.class,
				JWT.NONE);
		cleanButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 批量清空
//				for(iterable_type iterable_element : iterable){
//	                table.set
//                }
			}
		});

		Button saveButton = this.createControl(ID_Button_Save, Button.class,
				JWT.NONE);
		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				processData();
			}
		});
	}

	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetSalesManCreditListKey key = new GetSalesManCreditListKey();
		@SuppressWarnings("unchecked")
		ListEntity<SalesmanCreditItem> listEntity = context.find(ListEntity.class,
				key);
		countLabel.setText("" + listEntity.getTotalCount());
		return listEntity.getItemList().toArray();
	}

	public String getElementId(Object element) {
		return ((SalesmanCreditItem) element).getSalesmanId().toString();
	}

	public String getValue(Object element, String columnName){
		SalesmanCreditItem item = (SalesmanCreditItem)element;
		Columns column = Columns.valueOf(columnName);
		if(column != null){
			switch(column.ordinal()){
				case 0:
					return item.getSalesmanName();
				case 1:
					return item.getDepartmentInfo();
				case 2:
					return String.valueOf(item.getCustomerCreditLimit());
				case 3:
					return String.valueOf(item.getAvailableTotalCreditLimit());
				case 4:
					return String.valueOf(item.getCustomerCountUsed());
				case 5:
					return String.valueOf(item.getCustomerCreditUsed());
				case 6:
					return String.valueOf(item.getCustomerCreditDayLimit());
				case 7:
					return String.valueOf(item.getOrderApprovalLimit());
				default:
					return null;
			}
		}
		return "0.00";
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Clear.name() };
	}

	protected String[] getElementActionIds(Object element) {
		return new String[] { Action.Clear.name() };
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Clear.name())) {
			// TODO：清空
		}
	}

	public String getPromptMessage(){
	    return null;
    }

	public boolean processData(){
		String[] rowIds = table.getAllRowId();
		if (rowIds != null) {
			List<SaveSalesmanCreditTask.CreditItem> items = new ArrayList<SaveSalesmanCreditTask.CreditItem>();
			for (int i = 0; i < rowIds.length; i++) {
				String rowId = rowIds[i];
				String[] values = table.getEditValue(rowId,
						Columns.CustomerCreditAmountUpperLimit.name(), Columns.TotalCreditAmount.name(),
						Columns.CustomerCreditDayUpperLimit.name(),
						Columns.OrderApprovalUpperLimit.name());
				
				SaveSalesmanCreditTask.CreditItem item = new SaveSalesmanCreditTask.CreditItem();
				item.setSalesmanId(GUID.valueOf(rowId));
				item.setCustomerCreditLimit(StringUtils.isEmpty(values[0]) ? 0 : Double.parseDouble(values[0]));
				item.setAvailableTotalCreditLimit(StringUtils.isEmpty(values[1]) ? 0 : Double.parseDouble(values[1]));
				item.setCustomerCreditDayLimit(StringUtils.isEmpty(values[2]) ? 0 : Integer.parseInt(values[2]));
				item.setOrderApprovalLimit(StringUtils.isEmpty(values[3]) ? 0 : Double.parseDouble(values[3]));
				items.add(item);
				if(item.getAvailableTotalCreditLimit()<item.getCustomerCreditLimit()){
					String name = table.getEditValue(rowId, Columns.EmployeeName.name())[0];
					alert(name+" 的累计信用额度不能小于客户信用额度");
					return false;
				}
			}
			getContext().handle(new SaveSalesmanCreditTask(items.toArray(new SaveSalesmanCreditTask.CreditItem[0])));
			table.render();
			resetDataChangedstatus();
			hint("保存成功");
		}

	   return true;
    }

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}
