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
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfo;
import com.spark.psi.publish.inventory.checkin.entity.CheckInBaseInfoItem;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.inventory.sheet.task.CheckInSheetTaskItem;
import com.spark.psi.publish.inventory.sheet.task.SureCheckInTask;
import com.spark.psi.publish.inventory.sheet.task.SureCheckInTask.CheckInError;

/**
 * 入库单明细列表处理器
 */
public class ShowCheckedInDetailProcessor extends ExtendSimpleSheetPageProcessor<CheckingGoodsItem> implements
		IDataProcessPrompt {

	public final static String ID_Button_CheckIn = "button_CheckIn";
	public final static String ID_Button_Inspect = "Button_Inspect";

	public final static String ID_FooterLeftArea = "footerLeftArea";
	public final static String ID_FooterRightArea = "footerRightArea";
	public final static String ID_RenderButtonArea = "renderButtonArea";
	public final static String ID_StopCauseArea = "stopCauseArea";

	public final static String ID_Label_Supplier = "label_Supplier";
	public final static String ID_Label_Store = "label_Store";
	public final static String ID_Label_PlanDate = "label_PlanDate";
	public final static String ID_Label_RelatedNumber = "label_RelatedNumber";

	public static enum Columns {
		GoodsCode, GoodsName, GoodsProperties, GoodsUnit, CheckingCount, CheckedCount, CheckCount, CheckedAmount
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
		this.showCheckedInItemPage(info.getItems().toArray(new CheckInBaseInfoItem[info.getItems().size()]));
		this.lblProcessingStatusValue.setText(CheckingInStatus.Finish.getName());
		// if (!info.getStatus().equals(CheckingInStatus.None) && null !=
		// info.getItems()
		// && info.getItems().length > 0) {
		// this.lblLink.setText("　确认入库");
		// }
		this.createMemoText().setText(info.getRemark());
		// 制单
		Label createDate = this.createControl(ID_Label_Label_ExtraInfo, Label.class);
		createDate.setText("制单：" + DateUtil.dateFromat(info.getCheckinDate()));
		if (CheckingInType.Return.equals(info.getSheetType())) {
			this.createControl(ID_Label_Supplier, Label.class).setText("客户：" + info.getPartnerName());
		} else {
			this.createControl(ID_Label_Supplier, Label.class).setText("供应商：" + info.getPartnerName());
		}

		this.createControl(ID_Label_Store, Label.class).setText("  入库仓库：" + info.getStoreName());
		if (CheckIsNull.isNotEmpty(info.getRelaBillsNo())) {
			this.createControl(ID_Label_RelatedNumber, Label.class).setText("  相关单据：" + info.getRelaBillsNo());
		}
	}

	/**
    *
    */
	private boolean save() {
		if (!validateInput()) {
			return false;
		}
		if (!validateStoreStatus()) {
			return false;
		}
		String[] rowIds = table.getAllRowId();
		if (rowIds != null) {

			List<CheckInSheetTaskItem> itemList = new ArrayList<CheckInSheetTaskItem>();
			for (int i = 0; i < rowIds.length; i++) {
				String rowId = rowIds[i];
				String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
				if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
					itemList.add(new CheckInSheetTaskItem(GUID.valueOf(rowId), DoubleUtil.strToDouble(values[0])));
				}
			}
			if (CheckIsNull.isEmpty(itemList)) {
				return false;
			}
			SureCheckInTask task = new SureCheckInTask(info.getId(), itemList);
			try {
				getContext().handle(task);
			} catch (Throwable e) {
				e.printStackTrace();
				alert(e.getMessage());
				return false;
			}
			if (!task.isSuccess()) {
				List<CheckInError> errors = task.getErrors();
				if (CheckIsNull.isNotEmpty(errors)) {
					StringBuffer message = new StringBuffer();
					for (CheckInError error : errors) {
						message.append(error.getMessage());
					}
					alert(message.toString());
					return false;
				}
			}
			getContext().bubbleMessage(new MsgResponse(true));
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	private boolean validateStoreStatus() {

		GetStoreListKey key = new GetStoreListKey(StoreStatus.ENABLE);
		ListEntity<StoreItem> listEntity = getContext().find(ListEntity.class, key);
		if (null == listEntity || CheckIsNull.isEmpty(listEntity.getItemList())) {
			alert("仓库停用或盘点中，请检查！");
			return false;
		}
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(info.getStoreId())) {
				return true;
			}
		}
		alert("仓库停用或盘点中，请检查！");
		return false;
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

	/**
	 * 注册表格验证器 void
	 */
	protected void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "入库材料不能为空";
				}
				List<CheckInSheetTaskItem> itemList = new ArrayList<CheckInSheetTaskItem>();
				for (String rowId : table.getAllRowId()) {
					String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
					if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
						itemList.add(new CheckInSheetTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
					}
				}

				if (CheckIsNull.isEmpty(itemList)) {
					return "请填写入库材料数量";
				}
				return null;
			}

			@Override
			protected String validateCell(String rowId, String columnName) {
				String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
				if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
					String[] columnValue = table.getEditValue(rowId, Columns.CheckCount.name());
					String goodsName = table.getExtraData(rowId, Columns.GoodsName.name())[0];

					String checkingCount = table.getExtraData(rowId, Columns.CheckingCount.name())[0] == "" ? "0"
							: table.getExtraData(rowId, Columns.CheckingCount.name())[0];
					String checkedCount = table.getExtraData(rowId, Columns.CheckedCount.name())[0] == "" ? "0" : table
							.getExtraData(rowId, Columns.CheckedCount.name())[0];
					if (DoubleUtil.sub(Double.valueOf(columnValue[0]), DoubleUtil.sub(Double.valueOf(checkingCount),
							Double.valueOf(checkedCount))) > 0) {

						return "材料：" + goodsName + "，超出可入库数量";
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

	/**
	 * 处理数据
	 */
	public boolean processData() {
		if (save()) {
			resetDataChangedstatus();
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected String getStopCause() {
		return null;
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + info.getSheetNo() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("入库单号", info.getSheetNo());
						bw.addLabel("入库仓库", info.getStoreName());
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
