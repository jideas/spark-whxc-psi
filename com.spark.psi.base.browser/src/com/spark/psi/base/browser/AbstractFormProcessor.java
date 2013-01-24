package com.spark.psi.base.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.spark.common.components.pages.PageProcessor;

public abstract class AbstractFormProcessor extends PageProcessor {

	public final static String ID_SheetTitleComposite = "SheetTitleComposite";
	public final static String ID_SheetNumberComposite = "SheetNumberComposite";
	
	@Override
	public void process(Situation context) {
		final SheetTitleComposite sheetTitleComposite = this.createControl(ID_SheetTitleComposite,
				SheetTitleComposite.class);
		final SheetNumberComposite sheetNumberComposite = this.createControl(ID_SheetNumberComposite,
				SheetNumberComposite.class);
		sheetTitleComposite.setTitleValue(getSheetTitle());
		sheetNumberComposite.setSheetNumber(getSheetNumber());
	}

	/**
	 * 获得单据名称
	 * 
	 * @return
	 */
	protected abstract String getSheetTitle();
	
	/**
	 * 获取单据编号
	 * 
	 * @return
	 */
	protected abstract String getSheetNumber();
}
