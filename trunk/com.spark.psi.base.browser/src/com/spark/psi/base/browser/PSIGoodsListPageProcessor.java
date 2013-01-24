package com.spark.psi.base.browser;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.publish.base.goods.entity.GoodsInfoItem;

public abstract class PSIGoodsListPageProcessor extends
		PSIListPageProcessor<GoodsInfoItem> {

	public final static String ID_Text_SearchText = "Text_SearchText";
	public final static String ID_Button_SearchCurrent = "Button_SearchCurrent";
	public final static String ID_Button_SearchAll = "Button_SearchAll";

	//
	private Text searchText;

	//
	private GUID categoryId;

	// 当前查询表格的分类范围（每次查询完毕后设置为当前分类）
	private GUID searchCategoryId;

	@Override
	public void init(Situation situation) {
		this.categoryId = (GUID) this.getArgument();
		this.searchCategoryId = this.categoryId;
	}

	public void process(Situation context) {
		super.process(context);

		searchText = this.createControl(ID_Text_SearchText, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});

		// 在当前分类中查询
		this.createControl(ID_Button_SearchCurrent, Button.class)
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						table.render();
					}
				});
		// 在所有分类中查询
		this.createControl(ID_Button_SearchAll, Button.class)
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						searchCategoryId = null;
						table.render();
					}
				});
	}

	@Override
	public final Object[] getElements(Context context, STableStatus tablestatus) {
		Object[] elements = this.getElements(context, searchText.getText(),
				searchCategoryId, tablestatus);
		searchCategoryId = this.categoryId; // 重置
		return elements;
	}

	protected GUID getCategoryId() {
		return this.categoryId;
	}

	protected abstract Object[] getElements(Context context, String searchText,
			GUID categoryId, STableStatus tablestatus);
}
