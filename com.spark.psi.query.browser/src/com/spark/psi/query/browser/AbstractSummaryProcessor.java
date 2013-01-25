package com.spark.psi.query.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.PSIListPageProcessor;

public abstract class AbstractSummaryProcessor<Item> extends PSIListPageProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Button_Reset = "Button_Reset";
	public static final String ID_Button_AdvanceSearch = "Button_AdvanceSearch";
	public static final String ID_Button_Export = "Button_Export";
	
	private Label countLabel  = null;
	@Override
	public void process(Situation context) {
		super.process(context);
		countLabel = createLabelControl(ID_Label_Count);
		final Button advanceSearch = createButtonControl(ID_Button_AdvanceSearch);
		final Button resetButton = createButtonControl(ID_Button_Reset);
//		final Button exportButton = createButtonControl(ID_Button_Export);
		
		advanceSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doAdvanceSearch();
			}
		});
		
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doReset();
			}
		});
		
//		exportButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				doExport();
//			}
//		});
	}
	
	protected void setRecordCount(boolean isFistPage, int count) {
		int size = count;
		if (!isFistPage) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
	}
	
	protected abstract void doAdvanceSearch();
	
	protected abstract void doReset();
	
//	protected abstract void doExport();
}
