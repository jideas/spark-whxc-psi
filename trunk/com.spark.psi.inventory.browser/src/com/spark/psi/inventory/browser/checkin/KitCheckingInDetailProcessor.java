package com.spark.psi.inventory.browser.checkin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.inventory.sheet.entity.CheckKitItem;
import com.spark.psi.publish.inventory.sheet.task.CheckInKitTask;
import com.spark.psi.publish.inventory.sheet.task.CheckInKitTaskItem;

/**
 * ������Ʒ��ⵥ��ϸ�б�����
 */
public class KitCheckingInDetailProcessor extends PSIListPageProcessor<CheckKitItem> implements IDataProcessPrompt {

	public final static String ID_Button_Check = "Button_Check";
	public final static String ID_ComboList_StoreList = "ComboList_StoreList";
	public final static String ID_Text_KitSource = "Text_KitSource";
	public final static String ID_Text_Remark = "Text_Remark";
	private Text txtKitSource;
	private Text txtRemark;
	private LWComboList storeList;
	private Button btnCheck;

	static enum Columns {
		kitName, kitDescription, unit, count
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);
		txtKitSource = this.createControl(ID_Text_KitSource, Text.class);
		txtRemark = this.createControl(ID_Text_Remark, Text.class);
		storeList = this.createControl(ID_ComboList_StoreList, LWComboList.class);
		btnCheck = this.createControl(ID_Button_Check, Button.class);
		registerNotEmptyValidator(storeList, "���ֿ�");
		initData(false);
		initEvent();
		registerValidator();
	}

	/**
	 * ������Ʒ��KeyId
	 * 
	 * @param kitName
	 * @param kitDesc
	 * @param kitUnit
	 * @return
	 */
	private String key(String kitName, String kitDesc, String kitUnit) {
		return GUID.MD5Of(kitName + "~" + kitDesc + "~" + kitUnit).toString();
	}

	/**
	 * ע������֤��
	 */
	protected void registerValidator() {

		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateCell(String rowId, String columnName) {
				String[] values = table.getEditValue(rowId, Columns.count.name(), Columns.kitName.name(), Columns.kitDescription
						.name(), Columns.unit.name());

				// ��������Ʒ���ơ����ԡ���λ��ֻҪ�ĸ��ֶ�����һ����Ϊ�գ����ʾ¼�������ݣ�����֤����ȷ��
				if (CheckIsNull.isNotEmpty(values[0]) || CheckIsNull.isNotEmpty(values[1]) || CheckIsNull.isNotEmpty(values[2])
						|| CheckIsNull.isNotEmpty(values[3])) {
					if (CheckIsNull.isEmpty(values[0])) {
						return "������Ʒ��" + values[1] + "�������������Ϊ�գ�";
					} else {
						if (Double.parseDouble(values[0]) == 0) {
							return "������Ʒ��" + values[1] + "�������������Ϊ0��";
						}
					}

					if (StringHelper.toStr(values[1]).length() > 100) {
						return "������Ʒ��" + values[1] + "����Ʒ�������ֻ��¼��100���ַ���";
					}

					if (StringHelper.toStr(values[2]).length() > 1000) {
						return "������Ʒ��" + values[2] + "����Ʒ�������ֻ��¼��1000���ַ���";
					}

					if (StringHelper.toStr(values[3]).length() > 10) {
						return "������Ʒ��" + values[3] + "����Ʒ��λ���ֻ��¼��10���ַ���";
					}
				}
				return null;
			}

			@Override
			protected String validateRowCount(int rowCount) {

				if (rowCount < 1)
					return "���������Ʒ��Ϣ����Ϊ�գ�";

				Set<String> kits = new HashSet<String>();
				String[] rowIds = table.getAllRowId();
				if (rowIds != null) {
					for (int i = 0; i < rowIds.length; i++) {
						String rowId = rowIds[i];
						String[] values = table.getEditValue(rowId, Columns.kitName.name(), Columns.kitDescription.name(),
								Columns.unit.name(), Columns.count.name());
						if (CheckIsNull.isNotEmpty(values[0]) && CheckIsNull.isNotEmpty(values[1])
								&& CheckIsNull.isNotEmpty(values[2]) && CheckIsNull.isNotEmpty(values[3])) {
							String key = key(values[0], values[1], values[2]);
							if (kits.contains(key)) {
								return "��" + (i + 1) + "�е���Ʒ��Ϣ�����ظ���";
							} else
								kits.add(key);
						} else {
							if (CheckIsNull.isNotEmpty(values[0]) || CheckIsNull.isNotEmpty(values[1])
									|| CheckIsNull.isNotEmpty(values[2]))
								return "��" + (i + 1) + "�е���Ʒ��Ϣ��Ҫ¼��������";
						}
					}
				}

				return null;
			}
		});
	}

	private void initData(boolean tag) {
		if (tag) {// ������ˢ��
			storeList.setSelection(null);
			txtKitSource.setText("");
			txtRemark.setText("");
			storeList.setText("");
			table.render();
			resetDataChangedstatus();
		} else {// ��һ�δ�ҳ��
			storeList.getList().setSource(new StoreSource(StoreStatus.ENABLE));// ���òֿ�
			storeList.getList().setInput(null);
		}
	}

	private void initEvent() {
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processData();
			}
		});
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return new Object[] { getNewElement() };
	}

	@Override
	public Object getNewElement() {
		return GUID.randomID().toString();
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof String) {
			return (String) element;
		} else {
			CheckKitItem item = (CheckKitItem) element;
			return item.getKitName().concat(item.getKitDescription()).concat(item.getUnit());
		}
	}

	public String getValue(Object element, String columnName) {
		if (element instanceof String) {
			return "";
		}
		CheckKitItem item = (CheckKitItem) element;
		Columns column = Columns.valueOf(columnName);
		if (column != null) {
			switch (column.ordinal()) {
			case 0:
				return item.getKitName();
			case 1:
				return item.getKitDescription();
			case 2:
				return item.getUnit();
			case 3:
				return String.valueOf(item.getCount());
			default:
				return null;
			}
		}
		return null;
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}

	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Delete.name())) {
			if (table.getAllRowId().length == 1) {
				alert("�����Ʒ�б���뱣��һ�У�");
			} else {
				table.removeRow(rowId);
				table.renderUpate();
			}
		}
	}

	public String getPromptMessage() {
		return null;
	}
 
	public boolean processData() {

		if (!validateInput()) {
			return false;
		}

		String[] rowIds = table.getAllRowId();
		 
		CheckInKitTask task = new CheckInKitTask();
		List<CheckInKitTaskItem> itemList = new ArrayList<CheckInKitTaskItem>();
		for (int i = 0; i < rowIds.length; i++) {
			String rowId = rowIds[i];
			String[] values = table.getEditValue(rowId, Columns.kitName.name(), Columns.kitDescription.name(), Columns.unit
					.name(), Columns.count.name());
			if (CheckIsNull.isNotEmpty(values[0]) && CheckIsNull.isNotEmpty(values[1]) && CheckIsNull.isNotEmpty(values[2])
					&& CheckIsNull.isNotEmpty(values[3])) {
				itemList.add(new CheckInKitTaskItem(values[0], values[1], values[2], DoubleUtil.round(Double
						.parseDouble(values[3]))));
			}
		}
		task.setItems(itemList);
		task.setGoodsFrom(txtKitSource.getText());
		task.setRemark(txtRemark.getText());
		task.setStoreId(GUID.valueOf(this.storeList.getText()));
 
		if (itemList.size() == 0) {
			alert("�����Ʒ��ϸ�б���Ϊ�գ�");
			return false;
		}

		getContext().handle(task);

		hint("�����ɹ���", new Runnable() {
			public void run() {
				initData(true);
			}
		});

		return false;
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}