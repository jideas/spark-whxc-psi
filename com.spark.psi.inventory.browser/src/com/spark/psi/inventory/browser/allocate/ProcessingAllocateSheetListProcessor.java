package com.spark.psi.inventory.browser.allocate;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetItem;
import com.spark.psi.publish.inventory.key.GetInventoryAllocateSheetListKey;
import com.spark.psi.publish.inventory.key.GetInventoryAllocateSheetListKey.SortField;

/**
 * ���д������Ŀ��������б�������
 */
public class ProcessingAllocateSheetListProcessor extends
		PSIListPageProcessor<InventoryAllocateSheetItem> {

	// ����������
	public final static String ID_LABEL_INVENTORYALLOCATESHEET_COUNT = "Label_InventoryAllocateSheet_Count";
	// �����ı�
	public final static String ID_TEXT_SEARCH = "Text_Search";

	public final static String ID_ACTION_EDIT = "edit";
	public final static String ID_ACTION_VIEW = "view";
	
	public static enum Columns {
		SheetNumber, CreateDate, SourceStoreName, DestinationStoreName, CreatorName,status;
	}

	private Text searchText;
	private Label countLabel;

	@Override
	public void process(Situation situation) {

		super.process(situation);

		countLabel = this.createControl(ID_LABEL_INVENTORYALLOCATESHEET_COUNT,
				Label.class, JWT.NONE);
		searchText = this.createControl(ID_TEXT_SEARCH, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}

	/*
	 * ��ȡ�����б�
	 */
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetInventoryAllocateSheetListKey key = new GetInventoryAllocateSheetListKey(
				0, Integer.MAX_VALUE, true);
		key.setStatus(GetInventoryAllocateSheetListKey.Processingstatus);
		key.setSearchText(searchText.getText());
		if (CheckIsNull.isNotEmpty(tablestatus.getSortColumn())) {
			key.setSortField(getSortField(tablestatus.getSortColumn()));
			key.setSortType(getSortType(tablestatus.getSortDirection()));
		}
		List<InventoryAllocateSheetItem> itemsList = context.getList(
				InventoryAllocateSheetItem.class, key);
		countLabel.setText("" + itemsList.size());
		return itemsList.toArray();
	}
	
	private SortType getSortType(SSortDirection sortDirection) {
		if (SSortDirection.ASC.equals(sortDirection)) {
			return SortType.Asc;
		} else {
			return SortType.Desc;
		}

	}

	private SortField getSortField(String sortColumn) {
		if(Columns.CreateDate.name().equals(sortColumn))
		{
			return SortField.CreateDate;
		}
		else if(Columns.DestinationStoreName.name().equals(sortColumn))
		{
			return SortField.DestinationStoreName;
		}
		else if(Columns.SheetNumber.name().equals(sortColumn))
		{
			return SortField.SheetNumber;
		}
		else if(Columns.SourceStoreName.name().equals(sortColumn))
		{
			return SortField.SourceStoreName;
		}
		else if(Columns.status.name().equals(sortColumn))
		{
			return SortField.status;
		}
		else if(Columns.CreatorName.name().equals(sortColumn))
		{
			return SortField.CreatorName;
		}
		return null;
	}

	/*
	 * ��ȡָ������ID
	 */
	public String getElementId(Object element) {
		InventoryAllocateSheetItem item = (InventoryAllocateSheetItem) element;
		return item.getSheetId().toString();
	}

	/**
	 * ��ȡ���ԶԱ������ݽ���ɾ������
	 */
	@Override
	public String[] getTableActionIds(){
		return new String[] {Action.CheckOut.name(), Action.CheckIn.name()};
	}

	/**
	 * ��ȡ���Զ�ָ���ж������ɾ������
	 */
	@Override
	protected String[] getElementActionIds(Object element){
		InventoryAllocateSheetItem item = (InventoryAllocateSheetItem)element;
		if(InventoryAllocateStatus.Allocating.equals(item.getStatus()) && hasAuthForStore(item.getSourceStoreId())){
			return new String[] {Action.CheckOut.name()};
		}else if(InventoryAllocateStatus.AllocateOut.equals(item.getStatus()) && hasAuthForStore(item.getDestinationStoreId())){
			return new String[] {Action.CheckIn.name()};
		}else{
			return null;
		}
	}
	
	/**
	 *���Ƿ���в���ָ���ֿ��Ȩ�� 
	 *
	 *@return true: ��Ȩ�� �� false:��Ȩ�� 
	 */
	private boolean hasAuthForStore(GUID storeGuid){
		if(getContext().find(Boolean.class, Auth.Boss)){
			return true;
		}
		GetStoreListKey key = new GetStoreListKey(StoreStatus.ENABLE);
		ListEntity<StoreItem> listEntity = getContext().find(ListEntity.class, key);
		if(null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())){
			return false;
		}
		for(StoreItem item : listEntity.getItemList()){
			if(item.getId().equals(storeGuid)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ָ����������ʱ���������¼�
	 */
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {

		if (ID_ACTION_VIEW.equals(actionName)
				|| Action.Approval.name().equals(actionName)) {
			new AllocateSheetDetailController(getContext(), GUID.valueOf(rowId), table).showAllocateSheetDetail();
		}else if(actionName.equals(Action.CheckOut.name())){ //����
			new AllocateSheetDetailController(getContext(), GUID.valueOf(rowId), table).showAllocateSheetDetail();
		}else if(actionName.equals(Action.CheckIn.name())){ //���
			new AllocateSheetDetailController(getContext(), GUID.valueOf(rowId), table).showAllocateSheetDetail();
		}
	}

	@Override
	protected String getExportFileTitle() {
		return "���ε�";
	}
}