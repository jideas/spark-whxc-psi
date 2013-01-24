package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.base.b2b.entity.AuthorizedGoodsItemItem;

/**
 * 所有授权商品列表处理器
 */
public class B2BGoodsListProcessor extends
		PSIListPageProcessor<AuthorizedGoodsItemItem> {

	public final static String ID_LABEL_B2BCOUNT = "Label_B2BCount";
	public final static String ID_LABEL_UNB2BCOUNT = "Label_UnB2BCount";

	private int B2BCount = 0;
	private int UnB2BCount = 0;

	@Override
	public void process(Situation situation) {

		super.process(situation);

		Label countLabel = this.createControl(ID_LABEL_B2BCOUNT, Label.class,
				JWT.NONE);
		countLabel.setText(String.valueOf(B2BCount));

		Label unCountLabel = this.createControl(ID_LABEL_UNB2BCOUNT,
				Label.class, JWT.NONE);
		unCountLabel.setText(String.valueOf(UnB2BCount));

		this.table.getSelection();
	}

	public Object[] getElements(Context context, STableStatus tablestatus) {
		return new Object[] {};
	}

	//
	public String getElementId(Object element) {
		return "";// ((AuthorizedGoodsItemItem)element)
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Associate.name() };
	}

	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (actionName.equals(Action.Associate.name())) {
			// TODO：处理启用动作
		}
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}
