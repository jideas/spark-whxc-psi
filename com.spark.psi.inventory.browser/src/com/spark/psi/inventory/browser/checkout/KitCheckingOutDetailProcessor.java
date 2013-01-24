package com.spark.psi.inventory.browser.checkout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.DocumentEvent;
import com.jiuqi.dna.ui.wt.events.DocumentListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.inventory.browser.checkout.KitsSelectProcessor.SelectItem;
import com.spark.psi.inventory.browser.count.KitSheetDetailInfo.Kit;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.inventory.checkout.task.KitCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.KitCheckOutTaskItem;
import com.spark.psi.publish.inventory.sheet.entity.CheckKitItem;

/**
 * 其他物品出库单明细列表处理器
 */
public class KitCheckingOutDetailProcessor extends PSIListPageProcessor<CheckKitItem> implements IDataProcessPrompt {

	public final static String ID_ComboList_Store = "ComboList_Store";// 出库仓库
	public final static String ID_Text_Source = "Text_Source";// 物品来源
	public final static String ID_Text_Purpose = "Text_Purpose";// 用途
	public final static String ID_Text_Remark = "Text_Remark";// 备注
	public final static String ID_Text_DeliveryPerson = "Text_DeliveryPerson";// 提货人
	public final static String ID_Text_DeliveryDepartment = "Text_DeliveryDepartment";// 提货单位
	public final static String ID_Text_VoucherNumber = "Text_VoucherNumber";// 凭证号
	public final static String ID_Button_AddDetail = "Button_AddDetail";
	public final static String ID_Button_Check = "Button_Check";// 确认出库
	private Text txtSource;
	private Text txtPurpose;
	private Text txtRemark;
	private Text txtDeliveryPerson;
	private Text txtDeliveryDepartment;
	private Text txtVoucherNumber;
	private LWComboList storeList;
	private Map<String, Kit> selectedItemList = new LinkedHashMap<String, Kit>();
	private Set<String> selectedKitsIds = new HashSet<String>();
	private Button btnAdd;
	private Button btnCheck;

	static enum Columns {
		kitName, kitDescription, unit, count, inventory
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);
		txtSource = this.createControl(ID_Text_Source, Text.class);
		txtPurpose = this.createControl(ID_Text_Purpose, Text.class);
		txtRemark = this.createControl(ID_Text_Remark, Text.class);
		txtDeliveryPerson = this.createControl(ID_Text_DeliveryPerson, Text.class);
		txtDeliveryDepartment = this.createControl(ID_Text_DeliveryDepartment, Text.class);
		txtVoucherNumber = this.createControl(ID_Text_VoucherNumber, Text.class);
		storeList = this.createControl(ID_ComboList_Store, LWComboList.class);
		btnAdd = this.createControl(ID_Button_AddDetail, Button.class);
		btnCheck = this.createControl(ID_Button_Check, Button.class);
		registerNotEmptyValidator(storeList, "出库仓库");
		initData(true);
		initEvent();
		registerValidator();
	}

	/**
	 * 生成物品的KeyId
	 * 
	 * @param kitName
	 * @param kitDesc
	 * @param kitUnit
	 * @return
	 */
	// private String key(String kitName, String kitDesc, String kitUnit) {
	// return GUID.MD5Of(kitName + "~" +kitDesc + "~" +kitUnit).toString();
	// }

	/**
	 * 注册表格验证器
	 */
	protected void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateCell(String rowId, String columnName) {
				 
				return null;
			}

			@Override
			protected String validateRowCount(int rowCount) {

				if (rowCount < 1)
					return "其它出库物品信息不能为空！";

				String[] rowIds = table.getAllRowId();
				if (rowIds != null) {
					for (int i = 0; i < rowIds.length; i++) {
						String[] values = table.getEditValue(rowIds[i], Columns.kitName.name(), Columns.kitDescription.name(),
								Columns.unit.name(), Columns.count.name(), Columns.inventory.name());
						if (CheckIsNull.isEmpty(values[3])) {
							return "其它物品：" + values[0] + "，出库数量不能为空！";
						} else {
							if (Double.parseDouble(values[3]) <= 0) {
								return "其它物品：" + values[0] + "，出库数量不能为0！";
							} else if (Double.parseDouble(values[3]) > Double.parseDouble(values[4])) {
								return "其它物品：" + values[0] + "，出库数量超过库存本身数量！";
							}
						}
					}
				}

				return null;
			}
		});
	}

	private void initData(boolean tag) {
		if (tag) {
			// TODO 填充仓库列表
			StoreSource storeSource = new StoreSource(StoreStatus.ENABLE);
			storeList.getList().setSource(storeSource);// 启用仓库
			storeList.getList().setInput(null);
			if (storeSource.getSize() == 1) {// 如果为1个时，默认选中，多个时不需要
				storeList.setSelection(storeSource.getFirstStoreId());
			}
		} else {
			// storeList.setSelection(null);
			// 操作后清空刷新
			txtSource.setText("");
			txtPurpose.setText("");
			txtDeliveryPerson.setText("");
			txtDeliveryDepartment.setText("");
			txtRemark.setText("");
			txtVoucherNumber.setText("");
			storeList.getList().setInput(null);
			table.render();// 刷新表格
			selectedKitsIds.clear();
			resetDataChangedstatus();
		}
	}

	private void initEvent() {

		storeList.addDocumentListener(new DocumentListener() {

			public void documentUpdate(DocumentEvent e) {
				table.render();
				selectedKitsIds.clear();
				selectedItemList.clear();
			}
		});

		// TODO 添加物品明细
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (storeList.getText() != null) {
					PageController pc = new PageController(KitsSelectProcessor.class, KitsSelectRender.class);
					PageControllerInstance pci = new PageControllerInstance(pc, GUID.valueOf(storeList.getText()));
					WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
					windowStyle.setSize(640, 310);
					MsgRequest request = new MsgRequest(pci, "选择明细", windowStyle);
					request.setResponseHandler(new ResponseHandler() {
						public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
							if (returnValue != null) {
								selectedItemList.clear();
								selectedItemList = ((SelectItem) returnValue).getSelectedItemList();
								// selectedItemList.putAll(((SelectItem)returnValue).getSelectedItemList());
								Set<String> key = selectedItemList.keySet();
								for (Iterator<String> it = key.iterator(); it.hasNext();) {
									String s = it.next();
									if (selectedKitsIds.contains(s)) {// 如果包含
										continue;
									}
									Kit item = (Kit) selectedItemList.get(s);
									selectedKitsIds.add(item.getId());
									table.addRow(item);
								}
								table.renderUpate();
							}
						}
					});
					getContext().bubbleMessage(request);
				} else {
					alert("请先选择出库仓库！");
				}
			}
		});
		// TODO 确认出库
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processData();
			}
		});
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		return null;
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof Kit) {
			Kit item = (Kit) element;
			return item.getId();
		}
		return null;
	}

	public String getValue(Object element, String columnName) {
		if (element instanceof Kit) {
			Kit item = (Kit) element;
			if (columnName.equals(Columns.kitName.name())) {
				return item.getKitName();
			}
			if (columnName.equals(Columns.kitDescription.name())) {
				return item.getKitDesc();
			}
			if (columnName.equals(Columns.unit.name())) {
				return item.getKitUnit();
			}
			if (columnName.equals(Columns.inventory.name())) {
				return String.valueOf(item.getCount());// return "";//库存
			}
			if (columnName.equals(Columns.count.name())) {// 不加点击不起编辑 出库数量
				return "";
			}
		}
		return null;
	}

	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}

	@Override
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (Action.Delete.name().equals(actionName)) {
			selectedKitsIds.remove(rowId);
			table.removeRow(rowId);
			table.renderUpate();
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
		List<KitCheckOutTaskItem> lists = new ArrayList<KitCheckOutTaskItem>();
		if (rowIds != null) {
			for (int i = 0; i < rowIds.length; i++) {
				String[] values = table.getEditValue(rowIds[i], Columns.kitName.name(), Columns.kitDescription.name(),
						Columns.unit.name(), Columns.count.name(), Columns.inventory.name());
				if (values[0] != null && values[1] != null && values[2] != null && values[3] != null)
					lists.add(new KitCheckOutTaskItem(values[0], values[1], values[2], Integer.parseInt(values[3])));
			}
		}

		if (lists.size() == 0) {
			alert("必须有一条以上的物品出库明细！");
			return false;
		}

		KitCheckOutTask task = new KitCheckOutTask();
		task.setStoreId(GUID.valueOf(storeList.getText()));
		task.setItems(lists);
		task.setGoodsFrom(txtSource.getText());
		task.setGoodsUse(txtPurpose.getText());
		task.setRemark(txtRemark.getText());
		task.setTakePerson(txtDeliveryPerson.getText());
		task.setTakeUnit(txtDeliveryDepartment.getText());
		task.setVoucherNumber(txtVoucherNumber.getText());

		getContext().handle(task);

		initData(false);

		return true;
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}