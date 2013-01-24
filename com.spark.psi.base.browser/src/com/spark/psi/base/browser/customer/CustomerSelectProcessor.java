package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.partner.PartnerSelectProcessor;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.CustomerItem;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.key.GetShortCustomerListKey;

/**
 * 客户选择界面处理器
 * 
 */
public class CustomerSelectProcessor extends PartnerSelectProcessor {

	@Override
	public Object[] getElements(Context context, String searchText,
			STableStatus tablestatus) {
		GetShortCustomerListKey key = new GetShortCustomerListKey(
				GetShortCustomerListKey.SearchType.Auth);
		key.setSearchText(searchText);
		@SuppressWarnings("unchecked")
		ListEntity<CustomerItem> listEntity = (ListEntity<CustomerItem>) context
				.find(ListEntity.class, key);
		return listEntity.getItemList().toArray();
	}

	@Override
	protected PartnerInfo getPartnerInfo(GUID id) {
		return getContext().find(CustomerInfo.class, id);
	}

	@Override
	protected String getPartnerType() {
		return "客户";
	}

	@Override
	protected PageController getNewPartnerPageController() {
		return new PageController(NewCustomer2Processor.class,
				NewCustomer2Render.class);
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}

}
