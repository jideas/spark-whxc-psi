package com.spark.psi.inventory.browser.checkout;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.inventory.browser.checkin.ExtendSimpleSheetPageProcessor;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageProcessor;
import com.spark.psi.inventory.browser.internal.InventoryShelfInfoPageRender;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfo;
import com.spark.psi.publish.inventory.checkout.entity.CheckOutBaseInfoItem;
import com.spark.psi.publish.inventory.checkout.task.SureCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.SureCheckOutTaskItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

/**
 * �����м����ϸ�б�����
 */
public class CheckedOutDetailProcessor extends ExtendSimpleSheetPageProcessor<CheckOutBaseInfoItem> implements
		IDataProcessPrompt {

	public final static String ID_FooterRightArea = "footerRightArea";

	public final static String ID_Label_Customer = "label_Customer";
	public final static String ID_Label_Store = "label_Store";
	public final static String ID_Label_PlanDate = "label_PlanDate";
	public final static String ID_Label_RelatedNumber = "label_RelatedNumber";

	public final static String ID_Text_DeliveryPerson = "Text_DeliveryPerson";
	public final static String ID_Text_DeliveryUnit = "Text_DeliveryUnit";
	public final static String ID_Text_VoucherNumber = "Text_VoucherNumber";

	public static enum Columns {
		GoodsCode, GoodsName, GoodsProperties, GoodsUnit, CheckingCount, CheckedCount, CheckCount, GoodsNo
	}

	CheckOutBaseInfo info;

	private Label deliveryPersonText, deliveryUnitText, voucherNumberText;

	@Override
	public void process(Situation situation) {
		super.process(situation);
		if (null == info) {
			initData();
		}
		initControls();
		registerValidator();
	}

	private void initControls() {

		this.createMemoText().setEnabled(false);
		if (null == info) {
			return;
		}
		this.createMemoText().setText(info.getRemark());
		this.lblProcessingStatusValue.setText(CheckingOutStatus.Finish.getName());

		Label label1 = this.createControl(ID_Label_Customer, Label.class);
		Label label2 = this.createControl(ID_Label_Store, Label.class);
		Label label3 = this.createControl(ID_Label_RelatedNumber, Label.class);
		if (CheckingOutType.Return.equals(info.getCheckoutType())) {
			label1.setText("��Ӧ�̣�" + info.getPartnerName());
			label2.setText("  ����ֿ⣺" + info.getStoreName());
			label3.setText("  ��ص��ݣ�" + info.getRelaBillsNo());
		} else if (CheckingOutType.getCheckingOutType(info.getCheckoutType()).isMaterialTakeOrReturn()) {
			label1.setText("����ֿ⣺" + info.getStoreName());
			label2.setText("  ��ص��ݣ�" + info.getRelaBillsNo());
		} else {
			label1.setText("�ͻ���" + info.getPartnerName());
			label2.setText("  ����ֿ⣺" + info.getStoreName());
			label3.setText("  ��ص��ݣ�" + info.getRelaBillsNo());
		}

		// �Ƶ�
		Label createDate = this.createControl(ID_Label_Label_ExtraInfo, Label.class);
		createDate.setText("ȷ�ϳ��⣺" + info.getCheckoutPersonName() + "(" + DateUtil.dateFromat(info.getCheckoutDate())
				+ ")");

		this.deliveryPersonText = this.createLabelControl(ID_Text_DeliveryPerson);
		this.deliveryUnitText = this.createLabelControl(ID_Text_DeliveryUnit);
		this.voucherNumberText = this.createLabelControl(ID_Text_VoucherNumber);

		if (!CheckingOutType.RealGoods.getCode().equals(info.getCheckoutType())) {
			this.deliveryPersonText.setText(info.getTakePerson());
			this.deliveryUnitText.setText(info.getTakeUnit());
			this.voucherNumberText.setText(info.getVouchersNo());
			if (CheckIsNull.isEmpty(info.getTakePerson())) {
				this.deliveryPersonText.setText("��");
			}
			if (CheckIsNull.isEmpty(info.getTakeUnit())) {
				this.deliveryUnitText.setText("��");
			}
			if (CheckIsNull.isEmpty(info.getVouchersNo())) {
				this.voucherNumberText.setText("��");
			}
		}

	}

	/**
	 * ȷ�ϳ���
	 */
	private boolean save() {
		if (!validateInput()) {
			return false;
		}
		if (!validateStoreStatus()) {
			return false;
		}
		if (!validateInventoryCount()) {
			return false;
		}
		final String[] rowIds = table.getAllRowId();
		if (rowIds == null) {
			return false;
		}
		Map<GUID, CheckOutBaseInfoItem> map = new HashMap<GUID, CheckOutBaseInfoItem>();
		for (CheckOutBaseInfoItem item : info.getItems()) {
			map.put(item.getRECID(), item);
		}
		final SureCheckOutTask task = new SureCheckOutTask();
		task.setId(info.getRECID());
		List<SureCheckOutTaskItem> itemList = new ArrayList<SureCheckOutTaskItem>();
		List<DistributeInventoryItem> inventorys = new ArrayList<DistributeInventoryItem>();
		for (int i = 0; i < rowIds.length; i++) {
			String rowId = rowIds[i];
			String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
			if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
				itemList.add(new SureCheckOutTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
				// ׼����λ����
				DistributeInventoryItem dii = new DistributeInventoryItem();
				CheckOutBaseInfoItem goods = map.get(GUID.valueOf(rowId));
				dii.setStockId(goods.getGoodsId());
				dii.setName(context.find(MaterialsItem.class, goods.getGoodsId()).getMaterialName());
				dii.setCount(DoubleUtil.strToDouble(values[0]));
				inventorys.add(dii);
			}
		}
		if (CheckIsNull.isEmpty(itemList)) {
			return false;
		}
		task.setItems(itemList);

		task.setDeliveryPerson(this.deliveryPersonText.getText());
		task.setDeliveryDepartment(this.deliveryUnitText.getText());
		task.setVoucherNumber(this.voucherNumberText.getText());
		// ѡ���λ
		PageController pc = new PageController(InventoryShelfInfoPageProcessor.class,
				InventoryShelfInfoPageRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, info.getStoreId(), inventorys
				.toArray(new DistributeInventoryItem[inventorys.size()]));
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(720, 480);
		MsgRequest request = new MsgRequest(pci, "ѡ���λ", windowStyle);

		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null != returnValue) {
					task.setInventoryItems((DistributeInventoryItem[]) returnValue);
					getContext().handle(task);

					if (task.isSuccess()) {
						getContext().bubbleMessage(new MsgResponse(true));
					}
				}
			}
		});
		context.bubbleMessage(request);
		return false;

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

	/**
	 * �ϲ���ͬ���۲��ϣ�������Ƿ���
	 * 
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validateInventoryCount() {
		boolean canCheckOut = true;

		String[] rowIds = table.getAllRowId();

		List<SureCheckOutTaskItem> itemList = new ArrayList<SureCheckOutTaskItem>();
		for (int i = 0; i < rowIds.length; i++) {
			String rowId = rowIds[i];
			String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
			if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
				itemList.add(new SureCheckOutTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
			}
		}
		if (CheckIsNull.isEmpty(itemList)) {
			return false;
		}
		SureCheckOutTaskItem[] items = new SureCheckOutTaskItem[itemList.size()];
		for (int i = 0; i < itemList.size(); i++) {
			items[i] = itemList.get(i);
		}

		Map<GUID, Item> map = new HashMap<GUID, Item>();
		for (SureCheckOutTaskItem item : items) {
			for (CheckOutBaseInfoItem det : info.getItems()) {
				if (det.getRECID().equals(item.getId())) {
					if (map.containsKey(det.getGoodsId())) {
						map.get(det.getGoodsId()).setCheckCount(
								item.getCount() + map.get(det.getGoodsId()).getCheckCount());
					} else {
						Item thisItem = new Item();
						thisItem.setGoodsItemId(det.getGoodsId());
						thisItem.setCheckCount(item.getCount());
						map.put(det.getGoodsId(), thisItem);
					}
					break;
				}
			}

		}
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry) iterator.next();
			Item item = (Item) entry.getValue();
			ResourceToken<Inventory> token = getContext().findResourceToken(Inventory.class, info.getStoreId(),
					item.getGoodsItemId());
			if (null == token || item.getCheckCount() > token.getFacade().getCount()) {
				MaterialsItemInfo goods = getContext().find(MaterialsItemInfo.class, item.getGoodsItemId());
				alert("���ϣ�" + goods.getBaseInfo().getName() + ",�������������");
				canCheckOut = false;
				return canCheckOut;
			}
		}
		return canCheckOut;
	}

	/**
	 * ע������֤�� void
	 */
	protected void registerValidator() {
		registerInputValidator(new TableDataValidator(table) {

			@Override
			protected String validateRowCount(int rowCount) {
				if (rowCount < 1) {
					return "������ϲ���Ϊ�գ�";
				}

				List<SureCheckOutTaskItem> itemList = new ArrayList<SureCheckOutTaskItem>();
				String[] rowIds = table.getAllRowId();
				for (int i = 0; i < rowIds.length; i++) {
					String rowId = rowIds[i];
					String[] values = table.getEditValue(rowId, Columns.CheckCount.name());
					if (CheckIsNull.isNotEmpty(values[0]) && Double.valueOf(values[0]) > 0) {
						itemList.add(new SureCheckOutTaskItem(GUID.valueOf(rowId), Double.valueOf(values[0])));
					}
				}
				if (CheckIsNull.isEmpty(itemList)) {
					return "����д�������������";
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
						return "���ϣ�" + goodsName + "�������ò���δ����������";
					}
				}
				return null;
			}
		});
	}

	private void initData() {
		if (null != this.getArgument()) {
			if (getArgument() instanceof CheckOutBaseInfo) {
				info = (CheckOutBaseInfo) getArgument();
			} else if (getArgument() instanceof String) {
				info = getContext().find(CheckOutBaseInfo.class, GUID.valueOf((String) getArgument()));
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
		if (element instanceof CheckOutBaseInfoItem) {
			CheckOutBaseInfoItem item = (CheckOutBaseInfoItem) element;
			return item.getRECID().toString();
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
		if (null != info) {
			return info.getSheetNo();
		}
		return null;
	}

	@Override
	protected String getSheetTitle() {
		if (null != info) {
			CheckingOutType type = CheckingOutType.getCheckingOutType(info.getCheckoutType());
			if (CheckingOutType.Return.equals(type)) {
				return "�ɹ��˻����ⵥ";
			} else {
				return type.getName() + "��";
			}
		}
		return "���ⵥ";
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
	protected void initSheetData() {

		initData();
	}

	@Override
	public SNameValue[] getExtraData(Object element) {
		// if (element instanceof CheckOutBaseInfoItem) {
		// CheckOutBaseInfoItem item = (CheckOutBaseInfoItem) element;
		// MaterialsItemInfo goods = getContext().find(MaterialsItemInfo.class,
		// item.getGoodsItemId());
		// return new SNameValue[] { new SNameValue(Columns.GoodsName.name(),
		// goods.getBaseInfo().getName()),
		// new SNameValue(Columns.CheckingCount.name(), item.getRealCount() +
		// ""),
		// new SNameValue(Columns.CheckedCount.name(), item.getCheckedCount() +
		// "") };
		// }
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

	protected static class Item {
		/**
		 * �˴γ��������
		 */
		private double checkCount;
		/**
		 * �ѳ��������
		 */
		private double checkedCount;
		/**
		 * �����������
		 */
		private double checkingCount;
		/**
		 * ����
		 */
		private double price;
		/**
		 * ������ĿID
		 */
		private GUID goodsItemId;
		/**
		 * RECID
		 */
		private GUID id;

		public double getCheckCount() {
			return checkCount;
		}

		public void setCheckCount(double checkCount) {
			this.checkCount = checkCount;
		}

		public double getCheckedCount() {
			return checkedCount;
		}

		public void setCheckedCount(double checkedCount) {
			this.checkedCount = checkedCount;
		}

		public double getRealCount() {
			return checkingCount;
		}

		public void setCheckingCount(double checkingCount) {
			this.checkingCount = checkingCount;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		public void setGoodsItemId(GUID goodsItemId) {
			this.goodsItemId = goodsItemId;
		}

		public void setId(GUID id) {
			this.id = id;
		}

		public GUID getId() {
			return id;
		}
	}

	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + info.getSheetNo() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("���ⵥ��", info.getSheetNo());
						bw.addLabel("����ֿ�", info.getStoreName());
						if (null != info.getPartnerName()) {
							bw.addLabel("�ͻ�����", info.getPartnerName());
						}
						bw.addLabel("ȷ�ϳ���", info.getCheckoutPersonName() + "("
								+ DateUtil.dateFromat(info.getCheckoutDate()) + ")");
						bw.addLabel("��ע", info.getRemark());
						if (CheckIsNull.isNotEmpty(info.getRelaBillsNo())) {
							bw.addLabel("��ص���", info.getRelaBillsNo());
						}
						if (CheckIsNull.isNotEmpty(info.getTakePerson())) {
							bw.addLabel("�����", info.getTakePerson());
						}
						if (CheckIsNull.isNotEmpty(info.getTakeUnit())) {
							bw.addLabel("�����λ", info.getTakeUnit());
						}
						if (CheckIsNull.isNotEmpty(info.getVouchersNo())) {
							bw.addLabel("ƾ֤��", info.getVouchersNo());
						}
						String[] head = new String[] { "���ϱ��", "��������", "��������", "���Ϲ��", "���ϵ�λ", "��������" };
						List<String[]> list = new ArrayList<String[]>();
						Object[] object = getElements(context, null);
						for (Object obj : object) {
							CheckOutBaseInfoItem item = (CheckOutBaseInfoItem) obj;
							list.add(new String[] { item.getGoodsCode(), item.getGoodsNo(), item.getGoodsName(),
									item.getGoodsSpec(), item.getUnit(), DoubleUtil.getRoundStr(item.getRealCount()) });
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
