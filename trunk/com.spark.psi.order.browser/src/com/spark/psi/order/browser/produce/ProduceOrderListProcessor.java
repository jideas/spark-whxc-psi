package com.spark.psi.order.browser.produce;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.psi.base.browser.PSIListPageProcessor;

public abstract class ProduceOrderListProcessor<Item> extends PSIListPageProcessor<Item> {

	public static final String ID_Search = "Search";
	
	protected SSearchText2 search;
	@Override
	public void process(Situation context) {
		super.process(context);
		search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}



	@Override
	protected String getExportFileTitle() {
		return "Éú²ú¶©µ¥";
	}
}
