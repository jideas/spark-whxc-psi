package com.spark.psi.inventory.browser.loss;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;
import com.spark.psi.publish.inventory.key.GetReportLossInfoListKey;
import com.spark.psi.publish.inventory.key.GetReportLossInfoListKey.ViewStatus;

public class FinishedReportLossListProcessor<Item> extends PSIListPageProcessor<Item> {
	
	public final static String ID_List_Time = "Combo_Time";
	public final static String ID_Label_Count = "List_Count";
	public final static String ID_Search = "Search";
	
	public static enum ColumnName {
		sheetCode, finishDate, storeName, reporterName
	}
	
	private LWComboList queryTermList        = null;
	private Label countLabel 				 = null;
	private SSearchText2 searchText          = null;
	
	@Override
	public void process(final Situation situation) {
		super.process(situation);
		queryTermList = createControl(ID_List_Time, LWComboList.class);
		countLabel = createControl(ID_Label_Count, Label.class);
		searchText = createControl(ID_Search, SSearchText2.class);
		
		PSIProcessorUtils.initQueryTermSource(queryTermList);
		queryTermList.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});
		
		searchText.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 搜索报损单
				table.render();
			}
		});
		
		this.table.getSelection();
	}
	
	@Override
	public String getElementId(Object element) {
		ReportLossInfo info = (ReportLossInfo)element;
		return info.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetReportLossInfoListKey key = new GetReportLossInfoListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
//		key.setStatus(ReportLossStatus.Finished);
		key.setViewStatus(ViewStatus.finished);
		key.setQueryTerm(getContext().find(QueryTerm.class,queryTermList.getText()));
		key.setSearchKey(searchText.getText());
		List<ReportLossInfo> infoList = context.getList(ReportLossInfo.class, key);
		int size = infoList.size();
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
//		countLabel.setText(String.valueOf(infoList.size()));
		return infoList.toArray(new ReportLossInfo[0]);
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)) {
			PageController pc = new PageController(ReportLossDetailProcessor.class, ReportLossDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "报损详情");
			getContext().bubbleMessage(request);
		}
	}

	@Override
	protected String getExportFileTitle() {
		return "报损单";
	}
}
