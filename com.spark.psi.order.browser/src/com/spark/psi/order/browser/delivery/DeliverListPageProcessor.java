package com.spark.psi.order.browser.delivery;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.deliver.entity.DeliverItem;

public abstract class DeliverListPageProcessor<Item> extends PSIListPageProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "Search";
	public static final String ID_Button_AdvanceSearch = "Button_AdvanceSearch";
	
	private Label        label  = null;
	protected SSearchText2 search;
	@Override
	public void process(Situation context) {
		super.process(context);
		label = createControl(ID_Label_Count, Label.class);
		search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		
//		final Button button = createButtonControl(ID_Button_AdvanceSearch);
//		button.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				advanceSearchAction();
//			}
//		});
	}

	@Override
	public final String getElementId(Object element) {
		DeliverItem item = (DeliverItem)element;
		return item.getId().toString();
	}
	
	protected final void setListCount(boolean isFirstPage, int count) {
		int size = count;
		if (!isFirstPage) {
			String preSize = label.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		label.setText(String.valueOf(size));
	}
	
	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)) {
			PageController pc = new PageController(DeliverDetailPageProcessor.class, DeliverDetailPageRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "配送单详情");
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
		return "配送单记录";
	}

	protected abstract void advanceSearchAction();
}
