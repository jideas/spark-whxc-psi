package com.spark.psi.inventory.browser.allocate;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;
import com.spark.psi.publish.inventory.key.GetInventoryAllocateSheetListKey;
import com.spark.psi.publish.inventory.key.GetInventoryAllocateSheetListKey.SortField;
import com.spark.psi.publish.inventory.task.InventoryAllocateTask;

/**
 * ���ύ�Ŀ��������б�����
 */
public class SubmitingAllocateSheetListProcessor extends PSIListPageProcessor<InventoryAllocateSheetItem>{

	// �����ӵ�����
	public final static String ID_BUTTON_NEW_INVENTORYALLOCATESHEET = "Button_New_InventoryAllocateSheet";
	// ����������
	public final static String ID_LABEL_INVENTORYALLOCATESHEET_COUNT = "Label_InventoryAllocateSheet_Count";
	// �����ı�
	public final static String ID_TEXT_SEARCH = "Text_Search";
	// ������ť
	public final static String ID_BUTTON_SEARCH = "Button_Search";
	// �༭���鿴
	public final static String ID_ACTION_EDIT = "edit";
	public final static String ID_ACTION_VIEW = "view";

	public static enum Columns{
		SheetNumber,
		CreateDate,
		SourceStoreName,
		DestinationStoreName,
		status;
	}

	private Text searchText;
	private Label countLabel;

	@Override
	public void process(Situation situation){

		super.process(situation);
		Button btnAdd = this.createControl(ID_BUTTON_NEW_INVENTORYALLOCATESHEET, Button.class, JWT.NONE);
		btnAdd.setText(" ���������� ");
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new AllocateSheetDetailController(getContext(), null, table).showAllocateSheetDetail();
			}
		});

		countLabel = this.createControl(ID_LABEL_INVENTORYALLOCATESHEET_COUNT, Label.class, JWT.NONE);
		searchText = this.createControl(ID_TEXT_SEARCH, Text.class);
		searchText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				table.render();
			}
		});
	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 */
	@Override
	public void postProcess(Situation context){
		super.postProcess(context);
	}

	/**
	 * ��ȡ�����б�
	 */
	public Object[] getElements(Context context, STableStatus tablestatus){
		GetInventoryAllocateSheetListKey key = new GetInventoryAllocateSheetListKey(0, Integer.MAX_VALUE, true);
		key.setStatus(GetInventoryAllocateSheetListKey.Submittingstatus);
		key.setSearchText(searchText.getText());
		if(CheckIsNull.isNotEmpty(tablestatus.getSortColumn())){
			key.setSortField(getSortField(tablestatus.getSortColumn()));
			key.setSortType(getSortType(tablestatus.getSortDirection()));
		}
		List<InventoryAllocateSheetItem> itemsList = context.getList(InventoryAllocateSheetItem.class, key);
		countLabel.setText("" + itemsList.size());
		return itemsList.toArray();
	}

	private SortType getSortType(SSortDirection sortDirection){
		if(SSortDirection.ASC.equals(sortDirection)){
			return SortType.Asc;
		}
		else{
			return SortType.Desc;
		}

	}

	private SortField getSortField(String sortColumn){
		if(Columns.CreateDate.name().equals(sortColumn)){
			return SortField.CreateDate;
		}
		else if(Columns.DestinationStoreName.name().equals(sortColumn)){
			return SortField.DestinationStoreName;
		}
		else if(Columns.SheetNumber.name().equals(sortColumn)){
			return SortField.SheetNumber;
		}
		else if(Columns.SourceStoreName.name().equals(sortColumn)){
			return SortField.SourceStoreName;
		}
		else if(Columns.status.name().equals(sortColumn)){
			return SortField.status;
		}
		return null;
	}

	/**
	 * ��ȡָ������ID
	 */
	public String getElementId(Object element){
		// return "id_" + element.toString(); row row list.add(ros);
		InventoryAllocateSheetItem item = (InventoryAllocateSheetItem)element;
		return item.getSheetId().toString();
	}

	/**
	 * ��ȡ���ԶԱ�����ݽ��е����в���
	 */
	public String[] getTableActionIds(){
		return new String[] {Action.Submit.name(), Action.Delete.name()};
	}

	/**
	 * ָ����������ʱ���������¼�
	 */
	public void actionPerformed(final String rowId, String actionName, String actionValue){

		if(Action.Submit.name().equals(actionName)){
			confirm("ȷ���ύ��ǰ���ε���?", new Runnable(){

				public void run(){
					InventoryAllocateTask task = new InventoryAllocateTask(GUID
							.valueOf(rowId));
					getContext().handle(task, InventoryAllocateTask.Method.Submit);
					if(!task.isSuccess())
					{
						if(null!=task.getErrors()&&task.getErrors().length>0)
						{
							StringBuilder message = new StringBuilder();
							for(InventoryAllocateTask.Error error:task.getErrors())
							{
								if(message.length()>0)
								{
									message.append(";");
								}
								message.append(error.getMessage());
							}
							alert(message.toString());
						}
						return;
					}
					table.render();
				}
			});

		}
		else if(Action.Delete.name().equals(actionName)){
			confirm("ȷ��ɾ����ѡ��������?", new Runnable(){

				public void run(){
					InventoryAllocateTask task = new InventoryAllocateTask(GUID.valueOf(rowId));
					getContext().handle(task, InventoryAllocateTask.Method.Delete);
					table.render();
				}
			});
		}
		if(ID_ACTION_EDIT.equals(actionName)){
			new AllocateSheetDetailController(getContext(), GUID.valueOf(rowId), table).showAllocateSheetDetail();
		}
	}

	public String getValue(Object element, int columnIndex){
		// InventoryAllocateSheetItem item = (InventoryAllocateSheetItem)
		// element;
		return "";
	}

	@Override
	protected String getExportFileTitle() {
		return "���ε�";
	}
}