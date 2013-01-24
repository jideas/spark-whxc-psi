package com.spark.psi.account.browser;

import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.spark.psi.publish.PartnerType;

public class PartnerTypeSource extends ListSourceAdapter {
	
	private static final PartnerType[] partnerTypes = new PartnerType[]{PartnerType.Customer, PartnerType.Supplier};
	
	public Object[] getElements(Object inputElement) {
		return partnerTypes;
	}

	public String getText(Object element) {
		PartnerType partnerType = (PartnerType)element;
		return partnerType.getName();
	}

	public Object getElementById(String id) {
		return PartnerType.getPartnerTypeByValue(id);
	}

	public String getElementId(Object element) {
		PartnerType partnerType = (PartnerType)element;
		return partnerType.getCode();
	}

}
