package com.spark.psi.account.browser.joint;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementItem;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementListEntity;
import com.spark.b2c.publish.JointVenture.key.GetJointSettlementListKey;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.JointSettlementStatus;
import com.spark.psi.publish.ListEntity;

public class SubmittedJointPayListProcessor<Item> extends
		PSIListPageProcessor<Item> {
	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "Search";
	
	public static enum ColumnName {
		sheetNo("单据编号"), 
		supplierName("供应商名称"), 
		beginDate("开始日期"), 
		endDate("结束日期"), 
		saleAmount("销售金额"), 
		percentageAmount("提成金额"),  
		adjustAmount("调整金额"), 
		status("单据状态");
		
		private String title;
		private ColumnName(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return this.title;
		}
	}
	
	private Label countLabel    = null;
	private SSearchText2 search = null;
	
	public void process(Situation context) {
		super.process(context);
		countLabel = createLabelControl(ID_Label_Count);
		search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}
	
	@Override
	public String getElementId(Object element) {
		JointSettlementItem item = (JointSettlementItem)element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetJointSettlementListKey key = new GetJointSettlementListKey(0, Integer.MAX_VALUE, true);
		key.setSearchText(search.getText());
		key.setStatus(JointSettlementStatus.Submitted);
		ListEntity<JointSettlementItem> listEntity = context.find(JointSettlementListEntity.class, key);
		countLabel.setText("" + listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new JointSettlementItem[0]);
	}
	@Override
	public void actionPerformed(final String rowId, String actionName,
			String actionValue) {
		if (Action.Approval.name().equals(actionName)
				|| Action.Deny.name().equals(actionName)
				|| Action.Detail.name().equals(actionName)) {
			PageController pc = new PageController(JointPaySheetDetailProcessor.class, JointPaySheetDetailRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "联营结算单详情");
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
	protected String[] getElementActionIds(Object element) {
		return new String[] {Action.Approval.name(), Action.Deny.name() };
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.Approval.name(), Action.Deny.name() };
	}

	@Override
	protected String getExportFileTitle() {
		return "联营结算-待审核";
	}
}
