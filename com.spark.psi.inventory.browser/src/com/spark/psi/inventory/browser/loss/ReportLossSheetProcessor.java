package com.spark.psi.inventory.browser.loss;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.inventory.entity.ReportLossInfo;

public abstract class ReportLossSheetProcessor<TItem> extends SimpleSheetPageProcessor<TItem> {

	protected LoginInfo   loginInfo         = null;
	protected ReportLossInfo reportLossInfo = null;
	
	public static enum ColumnName {
		materialCode, materialNo, materialName, spec, unit, count, reason
	}
	
	@Override
	public void process(final Situation situation) {
		super.process(situation);
	}
	
	@Override
	protected void initSheetData() {
		loginInfo = getContext().find(LoginInfo.class);
		GUID infoId = (GUID)getArgument();
		if (null != infoId) {
			reportLossInfo = getContext().get(ReportLossInfo.class, infoId);
		}
	}

}
