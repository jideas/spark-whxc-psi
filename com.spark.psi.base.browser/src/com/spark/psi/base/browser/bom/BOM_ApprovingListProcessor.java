package com.spark.psi.base.browser.bom;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;
import com.spark.psi.publish.base.bom.entity.BomItem;
import com.spark.psi.publish.base.bom.key.GetBomItemListKey;

public class BOM_ApprovingListProcessor extends PSIListPageProcessor<BomItem> {

	public static final String ID_LABEL_COUNT = "ID_LABEL_COUNT";
	public static final String ID_TEXT_SEARCHTEXT = "ID_TEXT_SEARCHTEXT";

	public enum Columns {
		goodsCode, goodsNo, goodsName, goodsSpec, goodsUnit, bomNo, createDate
	}

	private Label countLabel;
	private Text searchText;

	@Override
	public void process(Situation context) {
		super.process(context);
		this.countLabel = this.createControl(ID_LABEL_COUNT, Label.class);
		this.searchText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class);
	}

	@Override
	public String getElementId(Object element) {
		BomItem item = (BomItem) element;
		return item.getId().toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetBomItemListKey key = new GetBomItemListKey();
		key.setStatus(BOM_STATUS.Approveing);
		if (!StringUtils.isEmpty(tablestatus.getSortColumn())) {
			key.setSortField(GetBomItemListKey.SortField.valueOf(tablestatus.getSortColumn()));
			if (tablestatus.getSortDirection() == SSortDirection.ASC) {
				key.setSortType(SortType.Asc);
			} else {
				key.setSortType(SortType.Desc);
			}
		}
		key.setSearchText(searchText.getText());
		ListEntity<BomItem> listEntity = context.find(ListEntity.class, key);
		if (listEntity != null) {
			countLabel.setText(String.valueOf(listEntity.getItemList().size()));
			return listEntity.getItemList().toArray();
		}
		countLabel.setText("0");
		return null;
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Approval.name() };
	}

	@Override
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		super.actionPerformed(rowId, actionName, actionValue);
		BaseFunction[] functions = new BaseFunction[] { new BaseFunction(new PageControllerInstance(new PageController(
				EditBomProcessor.class, ApprovingBomRender.class), null, rowId), "BOMÏêÇé") };
		MsgRequest request = new MsgRequest(functions, "BOMÏêÇé");
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}

}
