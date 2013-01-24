package com.spark.psi.account.browser.invoice;

import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.spark.psi.publish.InvoiceType;

public class InvoiceTypeSource extends ListSourceAdapter {

	public Object[] getElements(Object inputElement) {
		return new InvoiceType[] {InvoiceType.ValueAddesTax, InvoiceType.Common};
	}

	public String getText(Object element) {
		InvoiceType invoiceType = (InvoiceType)element;
		return invoiceType.getName();
	}

	public Object getElementById(String id) {
		InvoiceType invoiceType = InvoiceType.getInvoiceTypeByCode(id);
		return invoiceType;
	}

	public String getElementId(Object element) {
		InvoiceType invoiceType = (InvoiceType)element;
		return invoiceType.getCode();
	}

}
