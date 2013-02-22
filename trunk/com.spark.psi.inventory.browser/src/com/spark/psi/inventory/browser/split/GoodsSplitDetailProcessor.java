package com.spark.psi.inventory.browser.split;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.popup.PopupWindow.Direction;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.base.Store;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.inventory.browser.split.SplitGoodsSelectProcessor.SelectedItem;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem.MaterialsItem;
import com.spark.psi.publish.onlineorder.key.GetTotalMaterialsKey;
import com.spark.psi.publish.onlineorder.key.GetTotalMaterialsKey.GoodsItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo.ReceivedLog;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo.ReturnedLog;
import com.spark.psi.publish.produceorder.task.UpdateProduceOrderStatusTask;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Goods;
import com.spark.psi.publish.split.entity.GoodsSplitDet_Material;
import com.spark.psi.publish.split.entity.GoodsSplitInfo;
import com.spark.psi.publish.split.task.GoodsSplitTaskDet;
import com.spark.psi.publish.split.task.UpdateGoodsSplitBillTask;
import com.spark.psi.publish.split.task.UpdateGoodsSplitStatusTask;

public class GoodsSplitDetailProcessor extends
		AbstractGoodsSplitOrderProcessor {
	public static final String ID_Label_Info = "Label_Info";
	public static final String ID_Table_Goods = "Table_Goods";
	public static final String ID_Table_Material = "Table_Material";
	public static final String ID_Button_Approval = "Button_Approval";
	public static final String ID_Button_Deny = "Button_Deny";
	public static final String ID_Button_Distribut = "Button_Distribut";
	public static final String ID_Button_AddGoods = "Button_AddGoods";

	public static enum GoodsColumnName {
		goodsName, spec, count
	}

	public static enum GoodsTableExtraValueName {
		itemId, count, goodsId
	}

	public static enum MaterialColumnName {
		materialName, spec, scount, count
	}

	public static enum MaterialTableExtraValueName {
		itemId, materialId, spec, scount, count
	}

	private SEditTable goodsTable = null;
	private SEditTable materialtable = null;

	private LoginInfo loginInfo = null;

	private GoodsSplitInfo orderInfo = null;
	private List<GoodsDets> goodsDets = null;
	private List<MaterialDets> materialDets = null;;

	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
		if (null != this.getArgument()) {
			orderInfo = context.find(GoodsSplitInfo.class, (GUID) this
					.getArgument());
		}
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		initActions();
		goodsTable = createControl(ID_Table_Goods, SEditTable.class);
		materialtable = createControl(ID_Table_Material, SEditTable.class);
		goodsTable.setContentProvider(new EditableGoodsTableContentProvider());
		materialtable
				.setContentProvider(new EditableMaterialTableContentProvider());
		goodsTable.render();
		materialtable.render();
		if (null == orderInfo) {
			return;
		}

		final Label infoLabel = createControl(ID_Label_Info, Label.class);

		String sheetInfo = "制单：" + orderInfo.getCreator() + "("
				+ DateUtil.dateFromat(orderInfo.getCreateDate()) + ")";
		if (orderInfo.getApprovalPerson() != null) {
			sheetInfo += "     审批：" + orderInfo.getApprovalPerson() + "("
					+ DateUtil.dateFromat(orderInfo.getApprovalDate()) + ")";
		}
		if (orderInfo.getDistributPerson() != null) {
			sheetInfo += "     分配：" + orderInfo.getDistributPerson() + "("
					+ DateUtil.dateFromat(orderInfo.getDistributDate()) + ")";
		}
		if (orderInfo.getFinishDate() > 0) {
			sheetInfo += "     完成日期："
					+ DateUtil.dateFromat(orderInfo.getFinishDate());
		}
		infoLabel.setText(sheetInfo);

	}

	private void initActions() {
		Button button = null;
			switch (orderInfo.getStatus()) {
			case Submiting:
			case Approvaling:
				button = createControl(ID_Button_Approval, Button.class);
				addApprovalActionListener(button);
				Button buttons = createControl(ID_Button_Deny, Button.class);
				addRejectActionListener(buttons);
				break;
			case Approvaled:
				button = createControl(ID_Button_Distribut, Button.class);
				addDistributActionListener(button);
				break;
			}
		
		Button buttons = createControl(ID_Button_AddGoods, Button.class);
		buttons.setEnabled(false);
//		addGoodsActionListener(buttons);
	}

	private void addDistributActionListener(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(
						DistributeProcessor.class,
						DistributeRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc,orderInfo);
				MsgRequest request = new MsgRequest(pci, "分配仓库");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
							getContext().bubbleMessage(new MsgResponse(true));
					}

				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addGoodsActionListener(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(
						SplitGoodsSelectProcessor.class,
						SplitGoodsSelectRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				MsgRequest request = new MsgRequest(pci, "添加商品");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						if (CheckIsNull.isNotEmpty(returnValue)) {
							refreshTable(returnValue);
						}
					}

				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void refreshTable(Object returnValue) {
		SelectedItem[] items = (SelectedItem[]) returnValue;
		goodsDets = new ArrayList<GoodsDets>();
		GoodsItem[] goodsItems = new GoodsItem[items.length];
		int index = 0;
		GetTotalMaterialsKey tk = new GetTotalMaterialsKey();
		for (final SelectedItem item : items) {
			GoodsItem gi = tk.new GoodsItem();
			gi.setCount(item.getCount());
			gi.setGoodsCode(item.getCode());
			gi.setGoodsId(item.getGoodsItemId());
			gi.setGoodsName(item.getName());
			gi.setGoodsSpec(item.getSpec());
			gi.setUnit(item.getUnit());
			goodsItems[index] = gi;
			index++;
			GoodsDets goods = new GoodsDets();
			goods.setGcount(item.getCount());
			goods.setGoodsCode(item.getCode());
			goods.setGoodsId(item.getGoodsItemId());
			goods.setGoodsName(item.getName());
			goods.setGoodsSpec(item.getSpec());
			goods.setGoodsUnit(item.getUnit());
			goods.setRECID(item.getGoodsItemId());
			goodsDets.add(goods);
		}

		tk.setGoodsItems(goodsItems);
		try {
			materialDets = new ArrayList<MaterialDets>();
			TotalMaterialsItem tmi = getContext().find(
					TotalMaterialsItem.class, tk);
			if (CheckIsNull.isNotEmpty(tmi.getMaterials())) {
				for (final MaterialsItem mi : tmi.getMaterials()) {
					MaterialDets m = new MaterialDets();
					m.setMaterialId(mi.getMaterialId());
					m.setMcode(mi.getMaterialCode());
					m.setScount(mi.getCount());
					m.setMname(mi.getMaterialName());
					m.setMspec(mi.getMaterialSpec());
					m.setMunit(mi.getUnit());
					m.setRECID(mi.getMaterialId());
					materialDets.add(m);
				}

			}
		} catch (Throwable e) {
			alert(e.getMessage());
			System.out.println(e.getMessage());
		}
		// goodsTable.setContentProvider(new
		// EditableGoodsTableContentProvider());
		// materialtable.setContentProvider(new
		// EditableMaterialTableContentProvider());
		goodsTable.render();
		materialtable.render();
	}



	private void fillTaskData(UpdateGoodsSplitBillTask task) {
		task.setRemark(remarkText.getText());
		task.setStoreId(Store.GoodsStoreId);
		List<GoodsSplitTaskDet> goodsDets = new ArrayList<GoodsSplitTaskDet>();
		for (String rowId : goodsTable.getAllRowId()) {
			GoodsSplitTaskDet det = new GoodsSplitTaskDet(GUID.valueOf(rowId), DoubleUtil.strToDouble(goodsTable.getExtraData(rowId, GoodsColumnName.count.name())[0]));
			
			goodsDets.add(det);
		}
		List<GoodsSplitTaskDet> materialDets = new ArrayList<GoodsSplitTaskDet>();
		for (String rowId : materialtable.getAllRowId()) {
			GoodsSplitTaskDet det = new GoodsSplitTaskDet(GUID.valueOf(rowId), DoubleUtil.strToDouble(materialtable.getEditValue(rowId, MaterialColumnName.count.name())[0]));
			det.setsCount(DoubleUtil.strToDouble(materialtable.getExtraData(rowId, MaterialColumnName.scount.name())[0]));
			materialDets.add(det);
		}
		task.setGoodsDets(goodsDets);
		task.setMaterialDets(materialDets);
	}

	private boolean validationValue() {
		if (null == goodsTable.getAllRowId()
				|| goodsTable.getAllRowId().length < 1) {
			alert("请选择商品！");
			return false;
		}
		if (null == materialtable.getAllRowId()
				|| materialtable.getAllRowId().length < 1) {
			alert("材料信息发生错误！");
			return false;
		}
		for (String rowId : materialtable.getAllRowId()) {
			String countStr = materialtable.getEditValue(rowId,
					MaterialColumnName.count.name())[0];
			if (CheckIsNull.isEmpty(countStr)) {
				alert("请填写材料数量！");
				return false;
			}
		}
		return true;
	}

	private void addApprovalActionListener(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 批准
				UpdateGoodsSplitStatusTask task = new UpdateGoodsSplitStatusTask(
						orderInfo.getRECID(),GoodsSplitStatus.Approvaled);
				try
				{
					getContext().handle(task);
				}
				catch(Throwable t)
				{
					alert(t.getMessage());
					return;
				}
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	private void addRejectActionListener(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 退回
				MsgRequest request = CommonSelectRequest
						.createCommonDenyRequest(false);
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						if (null != returnValue2) {
							UpdateGoodsSplitStatusTask task = new UpdateGoodsSplitStatusTask(
									orderInfo.getRECID(),GoodsSplitStatus.Rejected,(String) returnValue2);
							getContext().handle(task);
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addReceiveReturnActionListener(Label label) {
		// 查看领/退料情况
		receiveReturnLabel.setText("领料/退料情况");
		receiveReturnLabel.setForeground(Color.COLOR_BLUE);
		SMenuWindow menuWindow = new SMenuWindow(null, label, Direction.Down);
		menuWindow.bindTargetControl(label);
		menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW,
				"Produce.onReceiveReturn");
		Composite windowArea = menuWindow.getContentArea();
		windowArea.setLayout(new GridLayout());
		final ScrolledPanel area = new ScrolledPanel(windowArea);
		GridData gd = new GridData();
		gd.widthHint = 500;
		gd.heightHint = 280;
		area.setLayoutData(gd);
		final Composite contentArea = area.getComposite();
		menuWindow.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent e) {
				contentArea.clear();
				GridLayout glContent = new GridLayout();
				glContent.marginTop = 10;
				// glContent.verticalSpacing = 10;
				contentArea.setLayout(glContent);

				GridData gdTitle = new GridData(GridData.FILL_HORIZONTAL);
				gdTitle.heightHint = 19;
				boolean isShowContent = false;
				// TODO
				// if (orderInfo.getReceivedLogs() != null
				// && orderInfo.getReceivedLogs().length > 0) {
				// Label label = new Label(contentArea);
				// label.setText("入库情况：");
				// label.setFont(new Font(9, "黑体", JWT.FONT_STYLE_PLAIN));
				// label.setLayoutData(gdTitle);
				// for (ReceivedLog log : orderInfo.getReceivedLogs()) {
				// showReceiveItem(contentArea, log);
				// }
				// isShowContent = true;
				// }

				if (!isShowContent) {
					Label label = new Label(contentArea);
					label.setText("无入库情况。");
				}
				contentArea.layout();
			}
		});
	}

	// private void addConfirmActionListener(Button button) {
	// button.addActionListener(new ActionListener() {
	//			
	// public void actionPerformed(ActionEvent e) {
	// // 确认完成
	// if (!validateGoodsTableData()) return;
	// FinishTask task = new FinishTask(orderInfo.getRECID());
	// String[] rowIds = goodsTable.getAllRowId();
	// List<FinishTask.Item> itemList = new ArrayList<FinishTask.Item>();
	// FinishTask.Item item = null;
	// for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
	// String rowId = rowIds[rowIndex];
	// String currrentCountStr = goodsTable.getEditValue(rowId,
	// GoodsColumnName.currentCount.name())[0];
	// if (StringUtils.isEmpty(currrentCountStr)) {
	// continue;
	// }
	// String[] baseValues = goodsTable.getExtraData(rowId,
	// GoodsTableExtraValueName.itemId.name(),
	// GoodsTableExtraValueName.goodsId.name());
	// item = task.new Item(GUID.tryValueOf(baseValues[0]),
	// DoubleUtil.strToDouble(currrentCountStr),
	// GUID.tryValueOf(baseValues[1]));
	// itemList.add(item);
	// }
	// task.setItems(itemList.toArray(new FinishTask.Item[0]));
	// task.setSheetNo(orderInfo.getBillNo());
	// getContext().handle(task);
	// getContext().bubbleMessage(new MsgResponse(true));
	// }
	// });
	// }

	private void showReceiveItem(Composite parentArea, ReceivedLog ceceivedLog) {
		Composite titleArea = new Composite(parentArea);
		GridData gdTitle = new GridData(GridData.FILL_HORIZONTAL);
		gdTitle.heightHint = 23;
		titleArea.setLayoutData(gdTitle);
		GridLayout glTitle = new GridLayout();
		glTitle.numColumns = 2;
		glTitle.marginTop = 5;
		glTitle.horizontalSpacing = 20;
		titleArea.setLayout(glTitle);
		titleArea.setBackground(new Color(0xC3DFE9));

		Label label = new Label(titleArea);
		label.setText("仓库：" + ceceivedLog.getStoreName());
		GridData gdStore = new GridData();
		gdStore.widthHint = 110;
		label.setLayoutData(gdStore);

		label = new Label(titleArea);
		label.setText("领料人：" + ceceivedLog.getCreator());
		GridData gdReceiver = new GridData();
		gdReceiver.widthHint = 110;
		label.setLayoutData(gdReceiver);

		Composite materialArea = new Composite(parentArea);
		GridLayout glMaterial = new GridLayout();
		glMaterial.numColumns = 5;
		glMaterial.marginLeft = 3;
		materialArea.setLayout(glMaterial);
		GridData gdMaterial = new GridData();
		gdMaterial.heightHint = 19 * ceceivedLog.getItems().length;
		materialArea.setLayoutData(gdMaterial);

		for (int itemIndex = 0; itemIndex < ceceivedLog.getItems().length; itemIndex++) {
			ReceivedLog.Item item = ceceivedLog.getItems()[itemIndex];
			label = new Label(materialArea);
			label.setText("材料：" + item.getMaterialName() + "["
					+ item.getMaterialSpec() + "]");

			new Label(materialArea).setText("  ");

			label = new Label(materialArea);
			label.setText("数量："
					+ DoubleUtil.getRoundStr(item.getPlanCount(), 2));

			new Label(materialArea).setText("  ");

			label = new Label(materialArea);
			label.setText("已入库数量："
					+ DoubleUtil.getRoundStr(item.getRealCount(), 2));

			// 每一行中间加一个竖线
			// if ((itemIndex + 1) % 2 > 0 && itemIndex !=
			// ceceivedLog.getItems().length) {
			// new SSeparator(materialArea, JWT.VERTICAL, 0);
			// }
		}
	}

	private void showReturnItem(Composite parentArea, ReturnedLog returnLog) {
		Composite titleArea = new Composite(parentArea);
		GridData gdTitle = new GridData(GridData.FILL_HORIZONTAL);
		gdTitle.heightHint = 23;
		titleArea.setLayoutData(gdTitle);
		GridLayout glTitle = new GridLayout();
		glTitle.numColumns = 2;
		glTitle.horizontalSpacing = 20;
		titleArea.setLayout(glTitle);
		titleArea.setBackground(new Color(0xC3DFE9));

		Label label = new Label(titleArea);
		label.setText("仓库：" + returnLog.getStoreName());
		GridData gdStore = new GridData();
		gdStore.widthHint = 110;
		label.setLayoutData(gdStore);

		label = new Label(titleArea);
		label.setText("退料人：" + returnLog.getCreator());
		GridData gdReceiver = new GridData();
		gdReceiver.widthHint = 110;
		label.setLayoutData(gdReceiver);

		Composite materialArea = new Composite(parentArea);
		GridLayout glMaterial = new GridLayout();
		glMaterial.numColumns = 5;
		materialArea.setLayout(glMaterial);

		for (int itemIndex = 0; itemIndex < returnLog.getItems().length; itemIndex++) {
			ReturnedLog.Item item = returnLog.getItems()[itemIndex];
			label = new Label(materialArea);
			label.setText("材料：" + item.getMaterialName());

			new Label(materialArea).setText("  ");

			label = new Label(materialArea);
			label.setText("退料数量："
					+ DoubleUtil.getRoundStr(item.getPlanCount(), item
							.getScale()));

			new Label(materialArea).setText("  ");

			label = new Label(materialArea);
			label.setText("已退数量："
					+ DoubleUtil.getRoundStr(item.getRealCount(), item
							.getScale()));

			// // 每一行中间加一个竖线
			// if (itemIndex % 2 > 0 && itemIndex !=
			// returnLog.getItems().length) {
			// new SSeparator(parentArea, JWT.VERTICAL, 0);
			// }
		}
	}

	private boolean validateGoodsTableData() {
		String[] rowIds = goodsTable.getAllRowId();
		int emptyCount = 0;
		// for (String rowId : rowIds) {
		// String currrentCountStr = goodsTable.getEditValue(rowId,
		// GoodsColumnName.count.name())[0];
		// if (StringUtils.isEmpty(currrentCountStr)) {
		// emptyCount++;
		// continue;
		// }
		// String[] baseValues = goodsTable.getExtraData(rowId,
		// GoodsTableExtraValueName.count.name(),
		// GoodsTableExtraValueName.doneCount.name());
		// double doneCount = DoubleUtil.strToDouble(baseValues[1]);
		// double count = DoubleUtil.strToDouble(baseValues[0]);
		// double currentCount = DoubleUtil.strToDouble(currrentCountStr);
		//			
		// if (currentCount == 0) {
		// // alert("本次完成数量不能为0");
		// // return false;
		// emptyCount++;
		// continue;
		// }
		//			
		// if ((doneCount + currentCount) > count) {
		// alert("完成数量不能大于数量。");
		// return false;
		// }
		// }
		// if (emptyCount == rowIds.length) {
		// alert("本次完成数量不能为空，或都为0。");
		// return false;
		// }
		if (rowIds.length < 1) {
			alert("请选择商品！");
			return false;
		}
		return true;
	}

	// private boolean validateMaterialTableData() {
	// String[] rowIds = materialtable.getAllRowId();
	// for (String rowId : rowIds) {
	// String currrentCountStr = materialtable.getEditValue(rowId,
	// MaterialColumnName.currentCount.name())[0];
	// if (!StringUtils.isEmpty(currrentCountStr)) { // 只要有一个不为空就可以
	// return true;
	// }
	// }
	// alert("本次领料数量不能为空。");
	// return false;
	// }

	private class EditableGoodsTableContentProvider implements
			SEditContentProvider {

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			GoodsSplitDet_Goods item = (GoodsSplitDet_Goods) element;
			return new SNameValue[] {
					new SNameValue(GoodsTableExtraValueName.itemId.name(), item
							.getRECID().toString()),
					new SNameValue(GoodsTableExtraValueName.count.name(), ""
							+ item.getGcount()),
					// new SNameValue(GoodsTableExtraValueName.doneCount.name(),
					// "" + item.getFinishedCount()),
					new SNameValue(GoodsTableExtraValueName.goodsId.name(), ""
							+ item.getGoodsId()) };
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			return null;
		}

		public String getElementId(Object element) {
			GoodsSplitDet_Goods item = (GoodsSplitDet_Goods) element;
			return item.getGoodsId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			if (CheckIsNull.isNotEmpty(goodsDets))
				return goodsDets.toArray();
			else if (null != orderInfo)
				return orderInfo.getGoodsDets().toArray();
			else
				return null;
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public boolean isSelected(Object element) {
			return false;
		}

	}

	private class EditableMaterialTableContentProvider implements
			SEditContentProvider {

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			GoodsSplitDet_Material item = (GoodsSplitDet_Material) element;
			return new SNameValue[] {
					new SNameValue(MaterialTableExtraValueName.itemId.name(),
							item.getRECID().toString()),
					// new
					// SNameValue(MaterialTableExtraValueName.storeId.name(),
					// item.getStoreId().toString()),
					new SNameValue(MaterialTableExtraValueName.scount.name(),
							"" + item.getScount()),
					new SNameValue(MaterialTableExtraValueName.materialId
							.name(), "" + item.getMaterialId()),
					new SNameValue(MaterialTableExtraValueName.spec.name(), ""
							+ item.getMspec()),
			// new SNameValue(MaterialTableExtraValueName.materialId.name(),
			// item.getMaterialId().toString())
			};
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			return null;
		}

		public String getElementId(Object element) {
			GoodsSplitDet_Material item = (GoodsSplitDet_Material) element;
			return item.getMaterialId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			if (CheckIsNull.isNotEmpty(goodsDets))
				return materialDets.toArray();
			else if (null != orderInfo)
				return orderInfo.getMaterialDets().toArray();
			else
				return null;
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public boolean isSelected(Object element) {
			return false;
		}

	}

	private class MaterialDets implements GoodsSplitDet_Material {

		private GUID RECID;
		private GUID billId;
		private GUID materialId;
		private double mcount;
		private String mname;
		private String munit;
		private String mspec;
		private String mcode;
		private String mnumber;
		private double scount;

		public String getMcode() {
			return mcode;
		}

		public String getMnumber() {
			return mnumber;
		}

		public GUID getRECID() {
			return RECID;
		}

		public GUID getBillId() {
			return billId;
		}

		public GUID getMaterialId() {
			return materialId;
		}

		public double getMcount() {
			return mcount;
		}

		public String getMname() {
			return mname;
		}

		public String getMunit() {
			return munit;
		}

		public String getMspec() {
			return mspec;
		}

		public void setRECID(GUID rECID) {
			RECID = rECID;
		}

		public void setBillId(GUID billId) {
			this.billId = billId;
		}

		public void setMaterialId(GUID materialId) {
			this.materialId = materialId;
		}

		public void setMcount(double mcount) {
			this.mcount = mcount;
		}

		public void setMname(String mname) {
			this.mname = mname;
		}

		public void setMunit(String munit) {
			this.munit = munit;
		}

		public void setMspec(String mspec) {
			this.mspec = mspec;
		}

		public void setMcode(String mcode) {
			this.mcode = mcode;
		}

		public void setMnumber(String mnumber) {
			this.mnumber = mnumber;
		}

		public void setScount(double scount) {
			this.scount = scount;
		}

		public double getScount() {
			return scount;
		}

	}

	private class GoodsDets implements GoodsSplitDet_Goods {
		private GUID RECID;
		private GUID goodsId;
		private GUID billId;
		private double gcount;
		private String reason;
		private String goodsName;
		private String goodsSpec;
		private String goodsUnit;
		private String goodsCode;
		private String goodsNo;

		public String getGoodsCode() {
			return goodsCode;
		}

		public String getGoodsNo() {
			return goodsNo;
		}

		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}

		public void setGoodsNo(String goodsNo) {
			this.goodsNo = goodsNo;
		}

		public GUID getRECID() {
			return RECID;
		}

		public GUID getGoodsId() {
			return goodsId;
		}

		public GUID getBillId() {
			return billId;
		}

		public double getGcount() {
			return gcount;
		}

		public String getReason() {
			return reason;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public String getGoodsSpec() {
			return goodsSpec;
		}

		public String getGoodsUnit() {
			return goodsUnit;
		}

		public void setRECID(GUID rECID) {
			RECID = rECID;
		}

		public void setGoodsId(GUID goodsId) {
			this.goodsId = goodsId;
		}

		public void setBillId(GUID billId) {
			this.billId = billId;
		}

		public void setGcount(double gcount) {
			this.gcount = gcount;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public void setGoodsSpec(String goodsSpec) {
			this.goodsSpec = goodsSpec;
		}

		public void setGoodsUnit(String goodsUnit) {
			this.goodsUnit = goodsUnit;
		}

	}
}
