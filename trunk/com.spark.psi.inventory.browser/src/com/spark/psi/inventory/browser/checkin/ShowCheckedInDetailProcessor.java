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
 * ��ⵥ��ϸ�б�����
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
		// this.lblLink.setText("��ȷ�����");
		// }
		this.createMemoText().setText(info.getRemark());
		// �Ƶ�
		Label createDate = this.createControl(ID_Label_Label_ExtraInfo, Label.class);
		createDate.setText("�Ƶ���" + DateUtil.dateFromat(info.getCheckinDate()));
		if (CheckingInType.Return.equals(info.getSheetType())) {
			this.createControl(ID_Label_Supplier, Label.class).setText("�ͻ���" + info.getPartnerName());
		} else {
			this.createControl(ID_Label_Supplier, Label.class).setText("��Ӧ�̣�" + info.getPartnerName());
		}

		this.createControl(ID_Label_Store, Label.class).setText("  ���ֿ⣺" + info.getStoreName());
		if (CheckIsNull.isNotEmpty(info.getRelaBillsNo())) {
			this.createControl(ID_Label_RelatedNumber, Label.class).setText("  ��ص��ݣ�" + info.getRelaBillsNo());
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
			alert("�ֿ�ͣ�û��̵��У����飡");
			return false;
		}
		for (StoreItem item : listEntity.getItemList()) {
			if (item.getId().equals(info.getStoreId())) {
				return true;
			}
		}
		alert("�ֿ�ͣ�û��̵��У����飡");
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
				return "���ǲɹ���ⵥ";
			} else if (CheckingInType.Return.equals(info.getSheetType())) {
				return "�����˻���ⵥ";
			} else {
				return info.getSheetType().getName() + "��";
			}
		}
		return "��ⵥ";
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
	 * ע������֤�� void
	 */
	protected void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "�����ϲ���Ϊ��";
				}
				List<CheckInSheetTaskItem> itemList = new ArrayList<CheckInSheetTaskItem>();
				for (String rowId : table.getAllRowId()) {
					String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
					if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
						itemList.add(new CheckInSheetTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
					}
				}

				if (CheckIsNull.isEmpty(itemList)) {
					return "����д����������";
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

						return "���ϣ�" + goodsName + "���������������";
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
	 * ��ʾ��Ϣ
	 */
	public String getPromptMessage() {
		return null;
	}

	/**
	 * ��������
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
						bw.addLabel("��ⵥ��", info.getSheetNo());
						bw.addLabel("���ֿ�", info.getStoreName());
						bw.addLabel("�������", DateUtil.dateFromat(info.getCheckinDate()));
						if (null != info.getPartnerName()) {
							bw.addLabel("��Ӧ������", info.getPartnerName());
						}
						bw.addLabel("ȷ�����", info.getCheckinPersonName());
						bw.addLabel("��ע", info.getRemark());
						String str = null;
						if (CheckingInType.Adjustment.equals(info.getSheetType())) {
							str = "�����";
						} else {
							str = "�������";
						}
						String[] head = new String[] { "���ϱ��", "��������", "��������", "���Ϲ��", "���ϵ�λ", str };
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
