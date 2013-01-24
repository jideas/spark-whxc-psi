package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;
import com.spark.psi.publish.inventory.entity.IrregualrCheckingInInfo.IrregualrGoodsItem;

public class IrregularCheckingInDetailProcessor extends PSIListPageProcessor<IrregualrGoodsItem> {

	public static final String ID_Label_SheetNumber = "Label_SheetNumber";
	public static final String ID_Label_SheetType = "Label_SheetType";
	public static final String ID_Label_StoreName = "Label_Store";
	public static final String ID_Label_Supplier = "Label_Supplier";
	public static final String ID_Label_Purchaser = "Label_Purchaser";
	public static final String ID_Label_PurchaseDate = "Label_PurchaseDate";
	public static final String ID_Text_Memo = "Text_Memo";
	public static final String ID_Label_Amount = "Label_Amount";
	public static final String ID_Label_CheckinInfo = "Label_CheckinInfo";

	private CheckInBaseInfo sheetInfo;

	@Override
	public void init(Situation context) {
		super.init(context);
		if (getArgument() != null && getArgument() instanceof GUID) {
			GUID sheetId = (GUID) getArgument();
			sheetInfo = context.find(CheckInBaseInfo.class, sheetId);
		}
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		if (sheetInfo == null) {
			alert("无法取得该入库单信息");
			return;
		}

		Label label = createControl(ID_Label_SheetNumber, Label.class);
		label.setText(sheetInfo.getSheetNo());

		label = createControl(ID_Label_SheetType, Label.class);
		label.setText(sheetInfo.getSheetType().getName());

		label = createControl(ID_Label_StoreName, Label.class);
		label.setText(sheetInfo.getStoreName());

		label = createControl(ID_Label_Supplier, Label.class);
		label.setText(sheetInfo.getPartnerName());

		label = createControl(ID_Label_Purchaser, Label.class);
		label.setText(sheetInfo.getBuyPerson());

		label = createControl(ID_Label_PurchaseDate, Label.class);
		label.setText(DateUtil.dateFromat(sheetInfo.getBuyDate()));

		Text memoText = createControl(ID_Text_Memo, Text.class);
		memoText.setText(sheetInfo.getRemark());

		label = createControl(ID_Label_Amount, Label.class);
		label.setText(DoubleUtil.getRoundStr(sheetInfo.getAmount()));

		label = createControl(ID_Label_CheckinInfo, Label.class);
		label.setText(sheetInfo.getCheckinPersonName() + "(" + DateUtil.dateFromat(sheetInfo.getCheckinDate()) + ")");

	}

	@Override
	public String getElementId(Object element) {
		CheckInBaseInfoItem item = (CheckInBaseInfoItem) element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return sheetInfo.getItems().toArray();
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
