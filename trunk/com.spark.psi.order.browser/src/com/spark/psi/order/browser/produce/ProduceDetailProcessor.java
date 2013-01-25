package com.spark.psi.order.browser.produce;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

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
import com.jiuqi.dna.ui.wt.graphics.Font;
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
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.SMenuWindow;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoGoodsItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoMaterialsItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo.ReceivedLog;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo.ReturnedLog;
import com.spark.psi.publish.produceorder.task.FinishTask;
import com.spark.psi.publish.produceorder.task.UpdateProduceOrderStatusTask;

public class ProduceDetailProcessor extends AbstractProduceOrderProcessor {
	public static final String ID_Label_Info = "Label_Info";
	public static final String ID_Table_Goods = "Table_Goods";
	public static final String ID_Table_Material = "Table_Material";
	public static final String ID_Button_Submit = "Button_Submit";
	public static final String ID_Button_Approval = "Button_Approval";
	public static final String ID_Button_Reject = "Button_Reject";
	public static final String ID_Button_ReturnMaterial = "Button_ReturnMaterial";
	public static final String ID_Button_ReceiveMaterial = "Button_ReceiveMaterial";
	public static final String ID_Button_Confirm = "Button_Confirm";
	
	public static enum GoodsColumnName {
		goodsName, spec, count, doneCount, currentCount
	}
	
	public static enum GoodsTableExtraValueName {
		itemId, count, doneCount, goodsId
	}
	
	public static enum MaterialColumnName {
		materialName, count, storeName, receivedCount, returnedCount, currentCount
	}
	
	public static enum MaterialTableExtraValueName {
		itemId, storeId, materialId, count, receivedCount, returnedCount
	}
	
	private SEditTable goodsTable     = null;
	private SEditTable materialtable  = null;
	
	private LoginInfo loginInfo       = null;
	
	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		goodsTable = createControl(ID_Table_Goods, SEditTable.class);
		materialtable = createControl(ID_Table_Material, SEditTable.class);
		final Label infoLabel = createControl(ID_Label_Info, Label.class);
		
		String sheetInfo = "制单：" + orderInfo.getCreator() + "(" + DateUtil.dateFromat(orderInfo.getCreateDate()) + ")";
		if (orderInfo.getApprovePerson() != null) {
			sheetInfo += "     审批：" + orderInfo.getApprovePerson() + "(" + DateUtil.dateFromat(orderInfo.getApproveDate()) + ")";
		}
		if (orderInfo.getFinishDate() > 0) {
			sheetInfo += "     确认完成日期：" + DateUtil.dateFromat(orderInfo.getFinishDate());
		}
		infoLabel.setText(sheetInfo);
		goodsTable.setContentProvider(new EditableGoodsTableContentProvider());
		materialtable.setContentProvider(new EditableMaterialTableContentProvider());
		goodsTable.render();
		materialtable.render();
		
		initActions();
	}
	
	private void initActions() {
		Button button = null;
		switch(orderInfo.getStatus()) {
		case Submiting:
		case Reject:
			button = createControl(ID_Button_Submit, Button.class);
			addSumbitActionListener(button);
			break;
		case Submited:
			Button approvalButton = createControl(ID_Button_Approval, Button.class);
			Button rejectButton = createControl(ID_Button_Reject, Button.class);
			if (loginInfo.hasAuth(Auth.SubFunction_ProduceOrder_Approval)) {
				addApprovalActionListener(approvalButton);
				addRejectActionListener(rejectButton);
			} else {
				approvalButton.dispose();
				rejectButton.dispose();
			}
			break;
		case Producing:
			Button receiveButton = createControl(ID_Button_ReceiveMaterial, Button.class);
			Button returnButton = createControl(ID_Button_ReturnMaterial, Button.class);
			Button confirmButton = createControl(ID_Button_Confirm, Button.class);
			if (loginInfo.hasAuth(Auth.SubFunction_ProduceOrder_Produce)) {
				addReceiveReturnActionListener(receiveReturnLabel);
				addReceiveActionListener(receiveButton);
				addReturnMaterialActionListener(returnButton);
				addConfirmActionListener(confirmButton);
			} else {
				receiveButton.dispose();
				returnButton.dispose();
				confirmButton.dispose();
				// receiveReturnLabel 全局变量暂不做dispose
			}
			break;
		case Finished:
			Button receiveButton1 = createControl(ID_Button_ReceiveMaterial, Button.class);
			Button returnButton1 = createControl(ID_Button_ReturnMaterial, Button.class);
			if (loginInfo.hasAuth(Auth.SubFunction_ProduceOrder_Produce)) {
				addReceiveReturnActionListener(receiveReturnLabel);
				addReceiveActionListener(receiveButton1);
				addReturnMaterialActionListener(returnButton1);
			} else {
				receiveButton1.dispose();
				returnButton1.dispose();
				// receiveReturnLabel 全局变量暂不做dispose
			}
		}
	}
	
	private void addSumbitActionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 提交
				UpdateProduceOrderStatusTask task = new UpdateProduceOrderStatusTask(orderInfo.getId());
				getContext().handle(task, UpdateProduceOrderStatusTask.Method.Submit);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}
	
	private void addApprovalActionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 批准
				UpdateProduceOrderStatusTask task = new UpdateProduceOrderStatusTask(orderInfo.getId());
				getContext().handle(task, UpdateProduceOrderStatusTask.Method.Approve);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}
	
	private void addRejectActionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 退回
				MsgRequest request = CommonSelectRequest.createCommonDenyRequest(false);
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						if (null != returnValue2) {
							UpdateProduceOrderStatusTask task = new UpdateProduceOrderStatusTask(orderInfo.getId());
							task.setRejectReason((String)returnValue2);
							getContext().handle(task, UpdateProduceOrderStatusTask.Method.Deny);
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
		SMenuWindow menuWindow = new SMenuWindow(null, label,Direction.Down);
		menuWindow.bindTargetControl(label);
		menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW,"Produce.onReceiveReturn");
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
//				glContent.verticalSpacing = 10;
				contentArea.setLayout(glContent);
				
				GridData gdTitle = new GridData(GridData.FILL_HORIZONTAL);
				gdTitle.heightHint = 19;
				boolean isShowContent = false;
				if (orderInfo.getReceivedLogs() != null
						&& orderInfo.getReceivedLogs().length > 0) {
					Label label = new Label(contentArea);
					label.setText("领料情况：");
					label.setFont(new Font(9, "黑体", JWT.FONT_STYLE_PLAIN));
					label.setLayoutData(gdTitle);
					for (ReceivedLog log : orderInfo.getReceivedLogs()) {
						showReceiveItem(contentArea, log);
					}
					isShowContent = true;
				}
				if (orderInfo.getReturnedLogs() != null 
						&& orderInfo.getReturnedLogs().length > 0) {
					Label label = new Label(contentArea);
					label.setText("退料情况：");
					label.setFont(new Font(9, "黑体", JWT.FONT_STYLE_PLAIN));
					label.setLayoutData(gdTitle);
					
					for (ReturnedLog log : orderInfo.getReturnedLogs()) {
						showReturnItem(contentArea, log);
					}
					isShowContent = true;
				}
				if (!isShowContent) {
					Label label = new Label(contentArea);
					label.setText("无领/退料情况。");
				}
				contentArea.layout();
			}
		});
	}
	
	private void addReceiveActionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 领料
				PageController pc = new PageController(ReturnAndReceiveMaterialPageProcessor.class, ReturnAndReceiveMaterialPageRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, orderInfo, ReturnAndReceiveMaterialPageProcessor.Type.RECEIVE);
				MsgRequest request = new MsgRequest(pci, "申请领料");
				request.setResponseHandler(new ResponseHandler() {
					
					public void handle(Object returnValue, Object returnValue2,
							Object returnValue3, Object returnValue4) {
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
				getContext().bubbleMessage(request);
//				if (!validateMaterialTableData()) return;
//				ReceiveTask task = new ReceiveTask(orderInfo.getId());
//				String[] rowIds = materialtable.getAllRowId();
//				List<ReceiveTask.Item> itemList = new ArrayList<ReceiveTask.Item>();
//				ReceiveTask.Item item = null;
//				for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
//					String rowId = rowIds[rowIndex];
//					String countStr = materialtable.getEditValue(rowId, MaterialColumnName.currentCount.name())[0];
//					if (StringUtils.isEmpty(countStr)) {
//						continue;
//					}
//					String[] baseValues = materialtable.getExtraData(rowId, MaterialTableExtraValueName.itemId.name(), 
//							MaterialTableExtraValueName.storeId.name(), MaterialTableExtraValueName.materialId.name());
//					item = task.new Item(GUID.tryValueOf(baseValues[0]), DoubleUtil.strToDouble(countStr), 
//							GUID.tryValueOf(baseValues[1]), GUID.tryValueOf(baseValues[2]));
//					itemList.add(item);
//				}
//				task.setItems(itemList.toArray(new ReceiveTask.Item[0]));
//				task.setSheetNo(orderInfo.getBillsNo());
//				getContext().handle(task);
//				hint("申请领料成功，出库单已生成。", new Runnable() {
//					
//					public void run() {
//						getContext().bubbleMessage(new MsgResponse(true));						
//					}
//				});
			}
		});
	}
	
	private void addReturnMaterialActionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 退料
				PageController pc = new PageController(ReturnAndReceiveMaterialPageProcessor.class, ReturnAndReceiveMaterialPageRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, orderInfo, ReturnAndReceiveMaterialPageProcessor.Type.RETURN);
				MsgRequest request = new MsgRequest(pci, "申请退料");
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
	
	private void addConfirmActionListener(Button button) {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确认完成
				if (!validateGoodsTableData()) return;
				FinishTask task = new FinishTask(orderInfo.getId());
				String[] rowIds = goodsTable.getAllRowId();
				List<FinishTask.Item> itemList = new ArrayList<FinishTask.Item>();
				FinishTask.Item item = null;
				for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
					String rowId = rowIds[rowIndex];
					String currrentCountStr = goodsTable.getEditValue(rowId, GoodsColumnName.currentCount.name())[0];
					if (StringUtils.isEmpty(currrentCountStr)) {
						continue;
					}
					String[] baseValues = goodsTable.getExtraData(rowId, GoodsTableExtraValueName.itemId.name(),
							GoodsTableExtraValueName.goodsId.name());
					item = task.new Item(GUID.tryValueOf(baseValues[0]), DoubleUtil.strToDouble(currrentCountStr),
							GUID.tryValueOf(baseValues[1]));
					itemList.add(item);
				}
				task.setItems(itemList.toArray(new FinishTask.Item[0]));
				task.setSheetNo(orderInfo.getBillsNo());
				getContext().handle(task);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}
	
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
			label.setText("材料：" + item.getMaterialName() + "[" + item.getMaterialSpec() + "]");
			
			new Label(materialArea).setText("  ");
			
			label = new Label(materialArea);
			label.setText("申请数量：" + DoubleUtil.getRoundStr(item.getPlanCount(), 2));
			
			new Label(materialArea).setText("  ");
			
			label = new Label(materialArea);
			label.setText("已领数量：" + DoubleUtil.getRoundStr(item.getRealCount(), 2));
			
			// 每一行中间加一个竖线
//			if ((itemIndex + 1) % 2 > 0 && itemIndex != ceceivedLog.getItems().length) {
//				new SSeparator(materialArea, JWT.VERTICAL, 0);
//			}
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
			label.setText("退料数量：" + DoubleUtil.getRoundStr(item.getPlanCount(), item.getScale()));
			
			new Label(materialArea).setText("  ");
			
			label = new Label(materialArea);
			label.setText("已退数量：" + DoubleUtil.getRoundStr(item.getRealCount(), item.getScale()));
			
//			// 每一行中间加一个竖线
//			if (itemIndex % 2 > 0 && itemIndex != returnLog.getItems().length) {
//				new SSeparator(parentArea, JWT.VERTICAL, 0);
//			}
		}
	}
	
	
	private boolean validateGoodsTableData() {
		String[] rowIds = goodsTable.getAllRowId();
		int emptyCount = 0;
		for (String rowId : rowIds) {
			String currrentCountStr = goodsTable.getEditValue(rowId, GoodsColumnName.currentCount.name())[0];
			if (StringUtils.isEmpty(currrentCountStr)) {
				emptyCount++;
				continue;
			}
			String[] baseValues = goodsTable.getExtraData(rowId, GoodsTableExtraValueName.count.name(), 
					GoodsTableExtraValueName.doneCount.name());
			double doneCount = DoubleUtil.strToDouble(baseValues[1]);
			double count = DoubleUtil.strToDouble(baseValues[0]);
			double currentCount = DoubleUtil.strToDouble(currrentCountStr);
			
			if (currentCount == 0) {
//				alert("本次完成数量不能为0");
//				return false;
				emptyCount++;
				continue;
			}
			
			if ((doneCount + currentCount) > count) {
				alert("完成数量不能大于数量。");
				return false;
			}
		}
		if (emptyCount == rowIds.length) {
			alert("本次完成数量不能为空，或都为0。");
			return false;
		}
		return true;
	}
	
//	private boolean validateMaterialTableData() {
//		String[] rowIds = materialtable.getAllRowId();
//		for (String rowId : rowIds) {
//			String currrentCountStr = materialtable.getEditValue(rowId, MaterialColumnName.currentCount.name())[0];
//			if (!StringUtils.isEmpty(currrentCountStr)) { // 只要有一个不为空就可以
//				return true;
//			}
//		}
//		alert("本次领料数量不能为空。");
//		return false;
//	}
	
	
	
	private class EditableGoodsTableContentProvider implements SEditContentProvider {

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			ProduceOrderInfoGoodsItem item = (ProduceOrderInfoGoodsItem)element;
			return new SNameValue[] { new SNameValue(GoodsTableExtraValueName.itemId.name(), item.getId().toString()),
					new SNameValue(GoodsTableExtraValueName.count.name(), "" + item.getCount()),
					new SNameValue(GoodsTableExtraValueName.doneCount.name(), "" + item.getFinishedCount()),
					new SNameValue(GoodsTableExtraValueName.goodsId.name(), "" + item.getGoodsId()) };
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
			if (loginInfo.hasAuth(Auth.SubFunction_ProduceOrder_Produce)) {
				if (GoodsColumnName.currentCount.name().equals(columnName)) {
					return "";
				}
			}
			return null;
		}

		public String getElementId(Object element) {
			ProduceOrderInfoGoodsItem item = (ProduceOrderInfoGoodsItem)element;
			return item.getId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			return orderInfo.getGoods();
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public boolean isSelected(Object element) {
			return false;
		}
		
	}

	private class EditableMaterialTableContentProvider implements SEditContentProvider {

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			ProduceOrderInfoMaterialsItem item = (ProduceOrderInfoMaterialsItem)element;
			return new SNameValue[] { new SNameValue(MaterialTableExtraValueName.itemId.name(), item.getId().toString()),
					new SNameValue(MaterialTableExtraValueName.storeId.name(), item.getStoreId().toString()),
					new SNameValue(MaterialTableExtraValueName.count.name(), "" + item.getCount()),
					new SNameValue(MaterialTableExtraValueName.receivedCount.name(), "" + item.getReceivedCount()),
					new SNameValue(MaterialTableExtraValueName.returnedCount.name(), "" + item.getReturnedCount()),
					new SNameValue(MaterialTableExtraValueName.materialId.name(), item.getMaterialId().toString())};
		}

		public Object getNewElement() {
			return null;
		}

		public String getValue(Object element, String columnName) {
//			if (MaterialColumnName.currentCount.name().equals(columnName)) {
//				return "";
//			}
			return null;
		}

		public String getElementId(Object element) {
			ProduceOrderInfoMaterialsItem item = (ProduceOrderInfoMaterialsItem)element;
			return item.getId().toString();
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			return orderInfo.getMaterials();
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public boolean isSelected(Object element) {
			return false;
		}
		
	}
}
