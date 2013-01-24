package com.spark.psi.base.browser.bom;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SContentProvider;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.base.bom.entity.BomHistoryItem;
import com.spark.psi.publish.base.bom.key.GetBomItemListByGoodsIdKey;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;

public class GoodsBomHistorylistProcessor extends BaseFormPageProcessor implements IDataProcessPrompt {
	public final static String ID_Label_Name = "Label_Name";
	public final static String ID_Label_Spec = "Label_Spec";
	public final static String ID_Label_Code = "Label_Code";

	public final static String ID_Label_ItemNumber = "Label_ItemNumber";
	public final static String ID_Label_Unit = "Label_Unit";
	public final static String ID_Label_LossRate = "Label_LossRate";

	public final static String ID_Table_Bom = "Table";
	public final static String ID_Button_Add = "Button";
	public static final int DEFAULT_Table_COUNT = 10;

	public enum Column {
		BomNo, Creator, Approvor, Outeffector, OuteffectDate, IneffectDate,
	}

	private GoodsItemInfo info;
	private STable table;

	private Label code, name, number, spec, unit, lossRate;

	@Override
	public void process(Situation context) {
		initControls();
		addActionToControls();
	}

	private void addActionToControls() {
		table.addActionListener(new SActionListener() {
			public void actionPerformed(String rowId, String actionName, String actionValue) {
				if (Action.Delete.name().equals(actionName)) {
					table.removeRow(rowId);
					table.renderUpate();
				}
			}
		});
		table.setContentProvider(new SContentProvider() {
			public String getElementId(Object element) {
				if (element instanceof String) {
					return (String) element;
				}
				if (element instanceof BomHistoryItem) {
					return ((BomHistoryItem) element).getId().toString();
				}
				return null;
			}

			public Object[] getElements(Context context, STableStatus tableState) {
				List<BomHistoryItem> list = context.getList(BomHistoryItem.class, new GetBomItemListByGoodsIdKey(info
						.getItemData().getId()));
				return list.toArray();
			}

			public boolean isSelectable(Object element) {
				return false;
			}

			public boolean isSelected(Object element) {
				return false;
			}

		});
		table.setLabelProvider(new SLabelProvider() {

			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}

			public String getText(Object element, int columnIndex) {
				BomHistoryItem item = (BomHistoryItem) element;
				switch (columnIndex) {
				case 0:

					return item.getBomNo();
				case 1:

					return item.getCreator();
				case 2:

					return item.getApprovor();
				case 3:

					return item.getIneffectDate();
				case 4:

					return item.getIneffector();
				case 5:

					return item.getOuteffectDate();
				case 6:

					return item.getOuteffector();

				default:
					break;
				}

				return null;
			}

			public Color getForeground(Object element, int columnIndex) {
				return null;
			}

			public Color getBackground(Object element, int columnIndex) {
				return null;
			}
		});
		table.render();
	}

	private void initControls() {
		info = getContext().find(GoodsItemInfo.class, this.getArgument());
		code = this.createControl(ID_Label_Code, Label.class);
		code.setText(info.getBaseInfo().getCode());
		name = this.createControl(ID_Label_Name, Label.class);
		name.setText(info.getBaseInfo().getName());
		number = this.createControl(ID_Label_ItemNumber, Label.class);
		spec = this.createControl(ID_Label_Spec, Label.class);
		unit = this.createControl(ID_Label_Unit, Label.class);
		lossRate = this.createControl(ID_Label_LossRate, Label.class);
		table = createControl(ID_Table_Bom, STable.class);
		number.setText(info.getItemData().getGoodsItemNo());
		spec.setText(info.getItemData().getSpec());
		unit.setText(info.getItemData().getUnit());
		lossRate.setText(DoubleUtil.getRoundStr(DoubleUtil.mul(100, info.getItemData().getLossRate())) + "%");

	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		return false;
	}

}
