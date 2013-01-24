package com.spark.psi.inventory.browser.checkout;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.entity.CheckingOutInfo;
import com.spark.psi.publish.inventory.entity.CheckingOutItem;
import com.spark.psi.publish.inventory.key.GetCheckingOutListKey;
import com.spark.psi.publish.inventory.key.GetCheckingOutListKey.SortField;

/**
 * 销售出库单列表处理器
 */
public class MaterialsCheckingOutListProcessor extends PSIListPageProcessor<CheckingOutItem> {

	//出库单数量
	public final static String ID_LABEL_CHECKOUTGINSHEET_COUNT = "Label_CheckingOut_Count";
	//搜索文本
	public final static String ID_TEXT_SEARCH = "Text_Search";
	//搜索按钮
	public final static String ID_BUTTON_SEARCH = "Button_Search";

	//编辑、查看
	public final static String ID_ACTION_EDIT = "edit";
	public final static String ID_ACTION_VIEW = "view";
	
	public static enum Columns
	{
		CreateDate,CheckoutType,RelatedNumber,StoreName,status,PlanCheckoutDate
	}
	
	private Label countLabel;
	private Text searchText;

	@Override
	public void process(Situation situation) {
		
		super.process(situation);	
		
		countLabel = this.createControl(ID_LABEL_CHECKOUTGINSHEET_COUNT,
				Label.class, JWT.NONE);

		searchText = this.createControl(ID_TEXT_SEARCH, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}
	
	/*
	 * 获取对象列表
	 */
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetCheckingOutListKey key = new GetCheckingOutListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setType(CheckingOutType.Mateiral_Take);
		key.setSearchText(searchText.getText());
		if(CheckIsNull.isNotEmpty(tablestatus.getSortColumn()))
		{
			key.setSortField(getSortField(tablestatus.getSortColumn()));
			key.setSortType(getSortType(tablestatus.getSortDirection()));
		}
		List<CheckingOutItem> itemList = context.getList(CheckingOutItem.class, key);
//		if(CheckIsNull.isEmpty(itemList))
//		{
//			countLabel.setText("0");
//			return null;
//		}
		this.createControl(ID_LABEL_CHECKOUTGINSHEET_COUNT, Label.class).setText(String.valueOf(itemList.size()));
		CheckingOutItem[] items = new CheckingOutItem[itemList.size()];
		for(int i=0;i<itemList.size();i++)
		{
			items[i] = itemList.get(i);
		}
		int size = items.length;
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
//		countLabel.setText(""+items.length);
		return items;
	}

	private SortType getSortType(SSortDirection sortDirection) {
		if(SSortDirection.ASC.equals(sortDirection))
		{
			return SortType.Asc;
		}
		else
		{
			return SortType.Desc;
		}
	}

	private SortField getSortField(String sortColumn) {
		if(Columns.StoreName.name().equals(sortColumn))
		{
			return SortField.StoreName;
		}
		else if(Columns.CreateDate.name().equals(sortColumn))
		{
			return SortField.CreateDate;
		}
		else if(Columns.RelatedNumber.name().equals(sortColumn))
		{
			return SortField.RelatedNumber;
		}
		else if(Columns.PlanCheckoutDate.name().equals(sortColumn))
		{
			return SortField.PlanCheckoutDate;
		}
		else if(Columns.status.name().equals(sortColumn))
		{
			return SortField.status;
		}
		return null;
	}

	/*
	 * 获取指定对象ID
	 */
	public String getElementId(Object element) {
		return ((CheckingOutItem)element).getSheetId().toString();
	}
	
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.CheckOut.name()};
	}

	/**
	 * 指定操作发生时，触发的事件
	 */
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		CheckingOutInfo info = getContext().find(CheckingOutInfo.class, GUID.valueOf(rowId));
		if (actionName.equals(Action.CheckOut.name())) {
			PageController pc = new PageController(
					CheckingOutDetailProcessor.class,
					CheckingOutDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc,info,rowId);
			MsgRequest request = new MsgRequest(pci,"出库单详情");
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					createControl(ID_LABEL_CHECKOUTGINSHEET_COUNT, Label.class).setText("0");
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
		else if(ID_ACTION_EDIT.equals(actionName))
		{
			PageController pc = new PageController(
					CheckingOutDetailProcessor.class,
					CheckingOutDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc,info,rowId);
			MsgRequest request = new MsgRequest(pci,"出库单详情");
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2,
						Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}