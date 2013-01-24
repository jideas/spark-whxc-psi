package com.spark.psi.inventory.browser.checkin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;

/**
 * 入库单明细列表处理器
 */
public class JointCheckedInDetailProcessor extends ExtendSimpleSheetPageProcessor<CheckingGoodsItem> implements
		IDataProcessPrompt {

	public final static String ID_FooterLeftArea = "footerLeftArea";
	public final static String ID_FooterRightArea = "footerRightArea";
	public final static String ID_RenderButtonArea = "renderButtonArea";

	public final static String ID_Label_Store = "label_Store";

	public static enum Columns {
		GoodsCode, GoodsName, GoodsProperties, GoodsUnit, CheckedCount
	}

	CheckInBaseInfo info;

	@Override
	public void process(Situation situation) {
		super.process(situation);
		initContorls();
	}

	private void initContorls() {
		this.createMemoText().setEnabled(false);
		if (null == info) {
			return;
		}
		this.showCheckedInItemPage(info.getItems().toArray(new CheckInBaseInfoItem[info.getItems().size()]));
		this.lblProcessingStatusValue.setText(CheckingInStatus.Finish.getName());
		this.createMemoText().setText(info.getRemark());
		// 制单
		Label createDate = this.createControl(ID_Label_Label_ExtraInfo, Label.class);
		createDate.setText("确认入库：" + info.getCreator() + "(" + DateUtil.dateFromat(info.getCheckinDate()) + ")");

		this.createControl(ID_Label_Store, Label.class).setText("入库仓库：" + info.getStoreName());
	}

	private void initData() {
		if (null != this.getArgument()) {
			if (getArgument() instanceof CheckInBaseInfo) {
				info = (CheckInBaseInfo) this.getArgument();
			} else if (getArgument() instanceof String) {
				info = getContext().find(CheckInBaseInfo.class, GUID.valueOf((String) getArgument()));
			} else if (getArgument() instanceof GUID) {
				info = getContext().find(CheckInBaseInfo.class, (GUID) getArgument());
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
			if (CheckingInType.Irregular.equals(info.getSheetType())) {
				return "零星采购入库单";
			} else if (CheckingInType.Return.equals(info.getSheetType())) {
				return "销售退货入库单";
			} else {
				return info.getSheetType().getName() + "单";
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

	@Override
	public SNameValue[] getExtraData(Object element) {
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

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + info.getSheetNo() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("入库单号", info.getSheetNo());
						bw.addLabel("入库日期", DateUtil.dateFromat(info.getCheckinDate()));
						if (null != info.getPartnerName()) {
							bw.addLabel("供应商名称", info.getPartnerName());
						}
						bw.addLabel("确认入库", info.getCheckinPersonName());
						bw.addLabel("备注", info.getRemark());
						String str = null;
						if (CheckingInType.Adjustment.equals(info.getSheetType())) {
							str = "入库金额";
						} else {
							str = "入库数量";
						}
						String[] head = new String[] { "材料编号", "材料条码", "材料名称", "材料规格", "材料单位", str };
						List<String[]> list = new ArrayList<String[]>();
						Object[] object = getElements(context, null);
						for (Object obj : object) {
							CheckInBaseInfoItem item = (CheckInBaseInfoItem) obj;
							String value = null;
							if (CheckingInType.Adjustment.equals(info.getSheetType())) {
								value = DoubleUtil.getRoundStr(item.getAmount());
							} else {
								value = DoubleUtil.getRoundStr(item.getRealCount());
							}
							list.add(new String[] { item.getGoodsCode(), item.getGoodsNo(), item.getGoodsName(),
									item.getGoodsSpec(), item.getUnit(), value });
						}
						bw.setTable(head, list);
						try {
							bw.write(info.getSheetNo());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
	}

	@Override
	protected boolean isNeedExport() {
		return true;
	}
}
