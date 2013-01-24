package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.QueryTerm;

/**
 * 客户供应商已完成交易列表处理器
 * 
 */
public abstract class PartnerProcessedOrderListProcessor extends
		PartnerOrderListProcessor {

	final static String ID_Label_Name = "Label_Name";
	final static String ID_Label_Info = "Label_Info";
	final static String ID_List_QueryTerm = "List_QueryTerm";

	protected LWComboList queryTermList;
	/**
	 * 页面提示
	 */
	protected Label infoLabel;

	@Override
	public void process(Situation situation) {
		super.process(situation);

		Label nameLabel = this.createControl(ID_Label_Name, Label.class);
		nameLabel.setText(partnerInfo.getName());
		infoLabel = this.createControl(ID_Label_Info, Label.class);
//		infoLabel.setText(getTradeInfo());

		queryTermList = this
				.createControl(ID_List_QueryTerm, LWComboList.class);
		PSIProcessorUtils.initQueryTermSource(queryTermList);
		queryTermList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});

	}

	@Override
	public final Object[] getElements(Context context, STableStatus tablestatus,
			String searchText) {
		Object[] objs = getElements(context, tablestatus,
				context.find(QueryTerm.class, queryTermList.getText()),
				searchText);
		infoLabel.setText(getTradeInfo());
		return objs;
	}

	public abstract String getTradeInfo();

	public abstract Object[] getElements(Context context,
			STableStatus tablestatus, QueryTerm queryTerm, String searchText);

}
