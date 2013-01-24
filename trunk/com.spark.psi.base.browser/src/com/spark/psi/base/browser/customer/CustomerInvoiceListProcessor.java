package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.account.entity.InvoiceItem;

/**
 * 客户的开票记录列表处理器
 */
public class CustomerInvoiceListProcessor extends
		PSIListPageProcessor<InvoiceItem> {
	public final static String ID_COMBO_TIME = "Combo_Time";
	public final static String ID_LABEL_CUTOMERNAME = "Label_CutomerName";
	public final static String ID_LABEL_COUNT = "Label_InvoiceCount";
	public final static String ID_LABEL_AMOUNT = "Label_InvoiceAmount";
	public final static String ID_TEXT_SEARCHTEXT = "Text_SearchText";
	public final static String ID_TEXT_SEARCHBUTTON = "Text_SearchButton";
	
	@Override
	public void process(Situation situation) {
		super.process(situation);
		this.table.getSelection();
	}
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return new Object[] {
				"1",
				"2",
				"3", "4", "A" };
	}

	public String getElementId(Object element) {
		return "id_" + element.toString();
	}

	@Override
	public String[] getTableActionIds() {
		return null;
	}

	@Override
	protected String getExportFileTitle() {
		return "客户开票记录";
	}
}
