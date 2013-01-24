package com.spark.psi.inventory.browser.checkin;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.inventory.sheet.task.CheckInSheetTaskItem;

/**
 * 入库单明细列表处理器
 */
public class RealGoodsCheckedInDetailProcessor extends ExtendSimpleSheetPageProcessor<CheckingGoodsItem> implements
		IDataProcessPrompt {

	public final static String ID_Button_CheckIn = "button_CheckIn";
	public final static String ID_Button_Inspect = "Button_Inspect";

	public final static String ID_FooterLeftArea = "footerLeftArea";
	public final static String ID_FooterRightArea = "footerRightArea";
	public final static String ID_RenderButtonArea = "renderButtonArea";
	public final static String ID_StopCauseArea = "stopCauseArea";

	public final static String ID_Label_Store = "label_Store";
	public final static String ID_Label_PlanDate = "label_PlanDate";
	public final static String ID_Label_RelatedNumber = "label_RelatedNumber";

	public static enum Columns {
		GoodsCode, GoodsName, GoodsProperties, GoodsUnit, CheckingCount, CheckedCount, CheckCount
	}

	CheckInBaseInfo info;

	@Override
	public void process(Situation situation) {
		super.process(situation);
		registerValidator();
		initContorls();
	}

	private void initContorls() {
		this.createMemoText().setEnabled(false);
		if (null == info) {
			return;
		}
		this.createMemoText().setText(info.getRemark());
		this.lblProcessingStatusValue.setText(CheckingInStatus.Finish.getName());
		// 制单
		Label createDate = this.createControl(ID_Label_Label_ExtraInfo, Label.class);
		createDate.setText("确认入库：" + info.getCheckinPersonName() + "(" + DateUtil.dateFromat(info.getCheckinDate()) + ")");

		this.createControl(ID_Label_Store, Label.class).setText("入库仓库：" + info.getStoreName());
		this.createControl(ID_Label_RelatedNumber, Label.class).setText("  生产订单：" + info.getRelaBillsNo());

	}

	private void initData() {
		if (null != this.getArgument()) {
			if (getArgument() instanceof CheckInBaseInfo) {
				info = (CheckInBaseInfo) this.getArgument();
			} else if (getArgument() instanceof String) {
				info = getContext().find(CheckInBaseInfo.class, GUID.valueOf((String) getArgument()));
			}
		}
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (null == info) {
			return null;
		}
		if (CheckIsNull.isNotEmpty(info.getItems())) {
			return info.getItems().toArray();
		}
		return null;
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof CheckingGoodsItem) {
			CheckingGoodsItem item = (CheckingGoodsItem) element;
			return item.getId().toString();
		}
		return "";
	}

	public String getValue(Object element, String columnName) {
		if (element instanceof CheckingGoodsItem) {
			if (Columns.CheckCount.name().equals(columnName)) {
				return String.valueOf(((CheckingGoodsItem) element).getCheckCount());
			}
		}

		return "";
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		return null;
	}

	@Override
	protected String getRemark() {
		return info.getRemark();
	}

	@Override
	protected String getSheetApprovalInfo() {
		return null;
	}

	@Override
	protected String getSheetCreateInfo() {
		return null;
	}

	@Override
	protected String[] getSheetExtraInfo() {
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return info.getSheetNo();
	}

	@Override
	protected String getSheetTitle() {
		if (null != info) {
			if (CheckingInType.Kit.equals(info.getSheetType())) {
				return "物品人库单";
			}
			if (CheckingInType.Irregular.equals(info.getSheetType())) {
				return "零星采购入库单";
			}
			if (CheckingInType.Return.equals(info.getSheetType())) {
				return "销售退货入库单";
			}
			if (CheckingInType.Purchase.equals(info.getSheetType())) {
				return "采购入库单";
			}

		}
		return "入库单";
	}

	@Override
	protected String[] getSheetType() {
		return null;
	}

	@Override
	protected void initSheetData() {
		initData();
	}

	/**
	 * 注册表格验证器 void
	 */
	protected void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "入库商品不能为空";
				}
				List<CheckInSheetTaskItem> itemList = new ArrayList<CheckInSheetTaskItem>();
				for (String rowId : table.getAllRowId()) {
					String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
					if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
						itemList.add(new CheckInSheetTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
					}
				}

				if (CheckIsNull.isEmpty(itemList)) {
					return "请填写入库商品数量";
				}
				return null;
			}

			@Override
			protected String validateCell(String rowId, String columnName) {
				String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
				if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
					String[] columnValue = table.getEditValue(rowId, Columns.CheckCount.name());
					String goodsName = table.getExtraData(rowId, Columns.GoodsName.name())[0];

					String checkingCount = table.getExtraData(rowId, Columns.CheckingCount.name())[0] == "" ? "0" : table
							.getExtraData(rowId, Columns.CheckingCount.name())[0];
					String checkedCount = table.getExtraData(rowId, Columns.CheckedCount.name())[0] == "" ? "0" : table
							.getExtraData(rowId, Columns.CheckedCount.name())[0];
					if (DoubleUtil.sub(Double.valueOf(columnValue[0]), DoubleUtil.sub(Double.valueOf(checkingCount), Double
							.valueOf(checkedCount))) > 0) {

						return "商品：" + goodsName + "，超出可入库数量";
					}

				}
				return null;
			}
		});
	}

	@Override
	public SNameValue[] getExtraData(Object element) {
		if (element instanceof CheckingGoodsItem) {
			CheckingGoodsItem item = (CheckingGoodsItem) element;
			MaterialsItemInfo goods = getContext().find(MaterialsItemInfo.class, item.getGoodsItemId());
			return new SNameValue[] { new SNameValue(Columns.GoodsName.name(), goods.getBaseInfo().getName()),
					new SNameValue(Columns.CheckingCount.name(), item.getCheckingCount() + ""),
					new SNameValue(Columns.CheckedCount.name(), item.getCheckedCount() + "") };
		}
		return null;
	}

	/**
	 * 提示信息
	 */
	public String getPromptMessage() {
		return null;
	}

	@Override
	protected String getStopCause() {
		return null;
	}

	public boolean processData() {
		return false;
	}

}
