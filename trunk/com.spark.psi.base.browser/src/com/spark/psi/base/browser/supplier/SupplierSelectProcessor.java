package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.partner.PartnerSelectProcessor;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.base.partner.entity.SupplierShortItem;
import com.spark.psi.publish.base.partner.key.GetShortSupplierListKey;

public class SupplierSelectProcessor extends PartnerSelectProcessor {

	private boolean isJoint = false;
	public void init(final Situation context) {
		super.init(context);
		if (getArgument2() != null) {
			isJoint = (Boolean)getArgument2();
		}
	}
	
	@Override
	public Object[] getElements(Context context, String searchText,
			STableStatus tablestatus) {
		GetShortSupplierListKey key = new GetShortSupplierListKey();
		key.setOnlyJointVenture(isJoint);
		key.setSearchText(searchText);
		@SuppressWarnings("unchecked")
		ListEntity<SupplierShortItem> listEntity = (ListEntity<SupplierShortItem>) context
				.find(ListEntity.class, key);
		return listEntity.getItemList().toArray();
	}

	@Override
	protected PartnerInfo getPartnerInfo(GUID id) {
		return getContext().find(SupplierInfo.class, id);
	}
	
	@Override
	protected String getPartnerType() {
		return "π©”¶…Ã";
	}
	
	@Override
	protected PageController getNewPartnerPageController() {
		return new PageController(NewSupplier2Processor.class,
				NewSupplier2Render.class);
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}

}

