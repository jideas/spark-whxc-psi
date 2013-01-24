package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.partner.PartnerProcessedOrderListProcessor;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;

/**
 * 客户已完成交易列表处理器
 * 
 */
public class CustomerProcessedOrderListProcessor extends
		PartnerProcessedOrderListProcessor {

	@Override
	public String getTradeInfo() {
		return null;
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus,
			QueryTerm queryTerm, String searchText) {
		return null;
	}

	public String getElementId(Object element) {
		return null;
	}

	@Override
	public String[] getTableActionIds() {
		return null;
	}

	@Override
	protected PartnerInfo getPartnerInfo(GUID partnerId) {
		return getContext().find(CustomerInfo.class, partnerId);

	}

	@Override
	protected String getExportFileTitle() {
		return "客户已完成交易记录";
	}

}
