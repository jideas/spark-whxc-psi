package com.spark.psi.base.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseListPageProcessor;

public abstract class PSIListPageProcessor<Item> extends
		BaseListPageProcessor<Item> {
	public static final String ID_Button_Export = "Button_Export";

	@Override
	public void process(Situation context) {
		super.process(context);
		final Button exportButton = createButtonControl(ID_Button_Export);
		String title = getExportFileTitle();
		if (null == title) {
			exportButton.dispose();
		} else {
			exportButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					doExport();
				}
			});
		}
	}
	
	protected void doExport() {
		String title = getExportFileTitle();
		title = StringUtils.isEmpty(title) ? GUID.randomID().toString() : title;
		table.exportDatas(StringUtils.isEmpty(title) ? GUID.randomID().toString() : title + ".xls", 
				"application/vnd.ms-excel", 102400000, title);
	}
	
	protected abstract String getExportFileTitle();
	
}
