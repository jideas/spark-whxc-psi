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
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;

/**
 * 其他物品入库 查看明细用
 */
public class KitCheckInSheetDetailProcessor extends ExtendSimpleSheetPageProcessor<CheckInBaseInfoItem> {

	public final static String ID_Label_Store = "Label_Store";
	public final static String ID_Label_Source = "Label_Source";
	String sheetId;
	CheckInBaseInfo info;
	Label lblStore;
	Label lblSource;

	static enum Columns {
		kitName, kitDescription, unit, count
	}

	@Override
	public void init(Situation context) {
		if (null != this.getArgument()) {
			sheetId = (String) this.getArgument();
		}
		info = getContext().get(CheckInBaseInfo.class, GUID.valueOf(sheetId));
	}

	@Override
	public void process(Situation situation) {

		super.process(situation);

		lblStore = this.createControl(ID_Label_Store, Label.class);
		lblSource = this.createControl(ID_Label_Source, Label.class);
		lblStore.setText("入库仓库：" + nvl(info.getStoreName()));
		lblSource.setText("  物品来源：" + nvl(info.getGoodsFrom()));
		createMemoText().setText(info.getRemark());
		createMemoText().setEnabled(false);
		if (null != info) {
			// 制单
			Label createDate = this.createControl(ID_Label_Label_ExtraInfo, Label.class);
			createDate.setText("制单：" + info.getCheckinPersonName() + "(" + DateUtil.dateFromat(info.getCheckinDate())
					+ ")");
			this.lblProcessingStatusValue.setText(CheckingInStatus.Finish.getName());
		}
	}

	private String nvl(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	/**
	 * 生成物品的KeyId
	 * 
	 * @param kitName
	 * @param kitDesc
	 * @param kitUnit
	 * @return
	 */
	private String key(String kitName, String kitDesc, String kitUnit) {
		return GUID.MD5Of(kitName + "~" + kitDesc + "~" + kitUnit).toString();
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof String) {
			return (String) element;
		} else {
			CheckInBaseInfoItem item = (CheckInBaseInfoItem) element;
			return key(item.getGoodsName(), item.getGoodsSpec(), item.getUnit());
		}
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (CheckIsNull.isNotEmpty(info.getItems())) {
			return info.getItems().toArray();
		}
		return null;
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
		if (info != null)
			return info.getRemark();
		return null;
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
		if (info != null)
			return null;// info.getSeetNumber();
		return null;
	}

	@Override
	protected String getSheetTitle() {
		return "其他入库单";
	}

	@Override
	protected String[] getSheetType() {
		return null;
	}

	@Override
	protected String getStopCause() {
		return null;
	}

	@Override
	protected void initSheetData() {}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + info.getSheetNo() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("入库单号", info.getSheetNo());
						bw.addLabel("入库仓库", info.getStoreName());
						bw.addLabel("物品来源", info.getGoodsFrom());
						bw.addLabel("确认入库", info.getCheckinPersonName() + "("
								+ DateUtil.dateFromat(info.getCheckinDate()) + ")");
						bw.addLabel("备注", info.getRemark());

						String[] head = new String[] { "物品名称", "物品描述", "单位", "入库数量" };
						List<String[]> list = new ArrayList<String[]>();
						Object[] object = getElements(context, null);
						for (Object obj : object) {
							CheckInBaseInfoItem item = (CheckInBaseInfoItem) obj;
							list.add(new String[] { item.getGoodsName(), item.getGoodsSpec(), item.getUnit(),
									DoubleUtil.getRoundStr(item.getRealCount()) });
						}
						bw.setTable(head, list);
						try {
							bw.write(info.getSheetNo());
						} catch (Exception e) {
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