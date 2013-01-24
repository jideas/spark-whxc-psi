package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.partner.PartnerProcessingOrderListProcessor;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;

/**
 * 客户未完成交易列表处理器
 * 
 */
public class CustomerProcessingOrderListProcessor extends
		PartnerProcessingOrderListProcessor {

	@Override
	public void process(Situation situation) {
		super.process(situation);
		Button button = this.createControl(ID_BUTTON_NEW, Button.class);
		button.setText(" 新增销售 ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		button = this.createControl(ID_BUTTON_NEWRETURN, Button.class);
		button.setText(" 新增销售退货 ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	public String getElementId(Object element) {
		return null;
	}

	@Override
	public String[] getTableActionIds() {
		return null;
	}

	@Override
	public String[] getElementActionIds(Object element) {
		return null;
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus,
			String searchText) {

		return null;
	}

	@Override
	protected PartnerInfo getPartnerInfo(GUID partnerId) {
		return getContext().find(CustomerInfo.class, partnerId);
	}

	@Override
	protected String getExportFileTitle() {
		return "客户未完成交易记录";
	}
}
