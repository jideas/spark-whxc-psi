package com.spark.psi.order.browser.onlinereturn;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.BillsWriter;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.constant.OnlineReturnStatus;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfo;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnDet;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnInfo;
import com.spark.psi.publish.onlinereturn.task.ApproveOnlineReturnTask;
import com.spark.psi.publish.onlinereturn.task.ExecuteOnlineReturnTask;
import com.spark.psi.publish.onlinereturn.task.OnlineReturnInfoTaskItem;
import com.spark.psi.publish.onlinereturn.task.SaveOrSubmitOnlineReturnInfoTask;
import com.spark.psi.publish.onlinereturn.task.StopOnlineReturnTask;
import com.spark.psi.publish.onlinereturn.task.SureOnlineReturnFinishTask;

public class OnlineReturnSheetDetailProcessor<TItem> extends SimpleSheetPageProcessor<TItem> {

	public static final String ID_Label_CustomerName = "Label_CustomerName";
	public static final String ID_Label_MobileNo = "Label_MobileNo";
	public static final String ID_Label_BookingDate = "Label_BookingDate";
	public static final String ID_Label_TotalAmount = "Label_TotalAmount";
	public static final String ID_Label_Status = "Label_Status";
	public static final String ID_Text_TotalReturnAmount = "Text_TotalReturnAmount";
	public static final String ID_Button_Save = "Button_Save";
	public static final String ID_Button_Submit = "Button_Submit";
	public static final String ID_Button_Approval = "Button_Approval";
	public static final String ID_Button_Reject = "Button_Reject";
	public static final String ID_Button_Stop = "Button_Stop";
	public static final String ID_Button_Finish = "Button_Finish";
	public static final String ID_Button_ReExceute = "Button_ReExceute";
	public static final String ID_Text_Vantages = "Text_Vantages";

	public static enum ColumnName {
		goodsName("商品名称"), goodsNo("商品条码"), goodsSpec("规格"), goodsCode("商品编号"), unit("单位"), price("单价"), count("销售数量"), returnCount("退货数量"), returnAmount(
				"退货金额");

		private String title;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	public static enum TableExtraValueName {
		price, count, onlineBillsItemId, goodsId, goodsCode, goodsNo, goodsSpec, goodsUnit, goodsName, billsCount
	}

	public static enum Type {
		New, Detail
	}

	private OnlineOrderInfo orderInfo = null;
	private OnlineReturnInfo returnInfo = null;
	private Type viewType = null;
	private LoginInfo loginInfo = null;

	private Text remarkText = null;
	private Text vantagesText = null;
	private Text returnAmountText = null;

	@Override
	public void init(Situation context) {
		super.init(context);
		GUID id = (GUID) getArgument();
		loginInfo = context.find(LoginInfo.class);
		viewType = (Type) getArgument2();
		if (Type.New == viewType) {
			orderInfo = getContext().find(OnlineOrderInfo.class, id);
		} else {
			returnInfo = getContext().find(OnlineReturnInfo.class, id);
		}

	}

	@Override
	public void process(Situation situation) {
		super.process(situation);
		final Label customerLabel = createControl(ID_Label_CustomerName, Label.class);
		final Label mobileNoLabel = createControl(ID_Label_MobileNo, Label.class);
		final Label totalAmountLabel = createControl(ID_Label_TotalAmount, Label.class);
		final Label bookingDateLabel = createLabelControl(ID_Label_BookingDate);
		final Label statusLabel = createLabelControl(ID_Label_Status);
		remarkText = createMemoText();
		returnAmountText = createTextControl(ID_Text_TotalReturnAmount);
		vantagesText = this.createControl(ID_Text_Vantages, Text.class);
		if(null!=returnInfo)
		{
			vantagesText.setText(returnInfo.getVantages()+"");
			if(!(OnlineReturnStatus.Rejected==returnInfo.getStatus()||OnlineReturnStatus.Submitting==returnInfo.getStatus()))
			{
				vantagesText.setEditable(false);
			}
		}
		if (viewType == Type.New && null != orderInfo) {
			bookingDateLabel.setText(DateUtil.dateFromat(orderInfo.getCreateDate()));
			customerLabel.setText(orderInfo.getRealName());
			mobileNoLabel.setText(orderInfo.getConsigneeTel());
			totalAmountLabel.setText(DoubleUtil.getRoundStr(orderInfo.getTotalAmount()));
		} else if (viewType == Type.Detail && null != returnInfo) {
			bookingDateLabel.setText(DateUtil.dateFromat(returnInfo.getOnlineCreateDate()));
			customerLabel.setText(returnInfo.getMemberName());
			mobileNoLabel.setText(returnInfo.getMemberPhone());
			totalAmountLabel.setText(DoubleUtil.getRoundStr(returnInfo.getSalesAmount()));
			remarkText.setText(returnInfo.getReturnReason());
			returnAmountText.setText(DoubleUtil.getRoundStr(returnInfo.getAmount()));

			if (OnlineReturnStatus.Submitting.equals(returnInfo.getStatus())
					|| OnlineReturnStatus.Rejected.equals(returnInfo.getStatus())) {
				remarkText.setEnabled(true);
			} else {
				remarkText.setEnabled(false);
			}
		}

		statusLabel.setText(returnInfo == null ? OnlineReturnStatus.Submitting.getTitle() : returnInfo.getStatus()
				.getTitle());

		returnAmountText.setEnabled(false);
		table.addClientEventHandler(SEditTable.CLIENT_EVENT_VALUE_CHANGED, "OnlineReturn.handleTableDataChange");
		initSheetActions();
	}

	private boolean doSave(boolean isSubmit) {
		if (!validate())
			return false;
		SaveOrSubmitOnlineReturnInfoTask task = new SaveOrSubmitOnlineReturnInfoTask(isSubmit);
		task.setRECID(returnInfo == null ? null : returnInfo.getRECID());
		if (Type.New.equals(viewType)) {
			task.setMemberId(orderInfo.getMemberId());
			task.setMemberName(orderInfo.getRealName());
			task.setOnlineBillsId(orderInfo.getId());
			task.setOnlineBillsNo(orderInfo.getBillsNo());
			task.setStationId(orderInfo.getStationId());
			task.setStationName(orderInfo.getStationName());
			task.setMemberPhone(orderInfo.getConsigneeTel());
			task.setOnlineCreateDate(orderInfo.getCreateDate());
			task.setSalesAmount(orderInfo.getTotalAmount());
		} else {
			task.setMemberId(returnInfo.getMemberId());
			task.setMemberName(returnInfo.getMemberName());
			task.setOnlineBillsId(returnInfo.getOnlineBillsId());
			task.setOnlineBillsNo(returnInfo.getOnlineBillsNo());
			task.setStationId(returnInfo.getStationId());
			task.setStationName(returnInfo.getStationName());
			task.setMemberPhone(returnInfo.getMemberPhone());
			task.setOnlineCreateDate(returnInfo.getOnlineCreateDate());
			task.setSalesAmount(returnInfo.getSalesAmount());
		}
		task.setReturnReason(remarkText.getText());
		String vantagesStr = vantagesText.getText();
		if(CheckIsNull.isNotEmpty(vantagesStr))
		{
			task.setVantages(Integer.valueOf(vantagesStr));
		}
		// task.setAmount(StringUtils.isEmpty(returnAmountText.getText()) ? 0.0
		// : DoubleUtil.strToDouble(returnAmountText.getText()));

		double totalReturnAmount = 0.0;
		List<OnlineReturnInfoTaskItem> itemList = new ArrayList<OnlineReturnInfoTaskItem>();
		String[] rowIds = table.getAllRowId();
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			String rowId = rowIds[rowIndex];
			String[] baseValues = table.getExtraData(rowId, TableExtraValueName.price.name(), TableExtraValueName.count
					.name(), TableExtraValueName.onlineBillsItemId.name(), TableExtraValueName.goodsId.name(),
					TableExtraValueName.goodsCode.name(), TableExtraValueName.goodsNo.name(),
					TableExtraValueName.goodsSpec.name(), TableExtraValueName.goodsUnit.name(),
					TableExtraValueName.goodsName.name(), TableExtraValueName.billsCount.name());
			String returnCountStr = table.getEditValue(rowId, ColumnName.returnCount.name())[0];
			if (StringUtils.isEmpty(returnCountStr) || DoubleUtil.strToDouble(returnCountStr) <= 0) {
				continue;
			}
			double returnCount = DoubleUtil.strToDouble(returnCountStr);
			double price = DoubleUtil.strToDouble(baseValues[0]);
			OnlineReturnInfoTaskItem item = new OnlineReturnInfoTaskItem();
			item.setAmount(DoubleUtil.mul(price, returnCount));
			item.setCount(returnCount);
			item.setPrice(price);
			item.setOnlineBillsItemId(GUID.tryValueOf(baseValues[2]));
			item.setGoodsId(GUID.tryValueOf(baseValues[3]));
			item.setGoodsCode(baseValues[4]);
			item.setGoodsNo(baseValues[5]);
			item.setGoodsSpec(baseValues[6]);
			item.setGoodsUnit(baseValues[7]);
			item.setGoodsName(baseValues[8]);
			item.setBillsCount(DoubleUtil.strToDouble(baseValues[9]));

			totalReturnAmount += (price * returnCount);
			itemList.add(item);
		}
		task.setAmount(totalReturnAmount);
		task.setItems(itemList);
		try {
			getContext().handle(task);
		} catch (Throwable th) {
			th.printStackTrace();
			alert(th.getMessage());
			return false;
		}
		return true;
	}

	private boolean validate() {
		String[] rowIds = table.getAllRowId();
		int emptyCount = 0;
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			String rowId = rowIds[rowIndex];
			String returCountStr = table.getEditValue(rowId, ColumnName.returnCount.name())[0];
			if (StringUtils.isEmpty(returCountStr)) {
				emptyCount++;
				continue;
			}
			String countStr = table.getExtraData(rowId, TableExtraValueName.count.name())[0];
			if (DoubleUtil.strToDouble(returCountStr) > DoubleUtil.strToDouble(countStr)) {
				alert("退货数量不能大于销售数量。");
				return false;
			}
		}
		if (emptyCount == rowIds.length) {
			alert("请填写退货数量。");
			return false;
		}
		return true;
	}

	private void initSheetActions() {
		Button button = null;
		if (null == returnInfo || OnlineReturnStatus.Submitting.equals(returnInfo.getStatus())
				|| OnlineReturnStatus.Rejected.equals(returnInfo.getStatus())) {
			button = createButtonControl(ID_Button_Save);
			addSaveAction(button);

			button = createButtonControl(ID_Button_Submit);
			addSubmitAction(button);
		} else {
			switch (returnInfo.getStatus()) {
			case Approving:
				if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Approval)) {
					button = createButtonControl(ID_Button_Approval);
					addApprovalAction(button);

					button = createButtonControl(ID_Button_Reject);
					addRejectAction(button);
				}
				break;
			case Processing:
				if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Confirm)) {
					button = createButtonControl(ID_Button_Finish);
					addFinishAction(button);

					button = createButtonControl(ID_Button_Stop);
					addStopAction(button);
				}
				break;
			case Stopped:
				if (loginInfo.hasAuth(Auth.SubFunction_OnlineReturn_Confirm)) {
					button = createButtonControl(ID_Button_ReExceute);
					addReExcuteAction(button);
				}
				break;
			}
		}
	}

	private void addSaveAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 保存
				if(doSave(false))
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	private void addSubmitAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 提交
				boolean isSaveSuccess = doSave(true);
				if (isSaveSuccess) {
					getContext().bubbleMessage(new MsgResponse(true));
				}
			}
		});
	}

	private void addApprovalAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 审批
				ApproveOnlineReturnTask task = new ApproveOnlineReturnTask(returnInfo.getRECID(), true, null,
						returnInfo.getOnlineBillsId()); 
				try {
					getContext().handle(task);
					getContext().bubbleMessage(new MsgResponse(true));
				} catch (Throwable th) {
					th.printStackTrace();
					alert(th.getMessage());
				}
			}
		});
	}

	private void addRejectAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 退回
				MsgRequest request = CommonSelectRequest.createCommonDenyRequest(false);
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if (null != returnValue2) {
							ApproveOnlineReturnTask task = new ApproveOnlineReturnTask(returnInfo.getRECID(), false,
									(String) returnValue2, returnInfo.getOnlineBillsId());
							getContext().handle(task);
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addFinishAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 确认完成
				SureOnlineReturnFinishTask task = new SureOnlineReturnFinishTask(returnInfo.getRECID());
				getContext().handle(task);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	private void addStopAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 中止
				MsgRequest request = CommonSelectRequest.createPopSingleTextRequest(false, "中止原因", "中止原因");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						if (null != returnValue2) {
							StopOnlineReturnTask task = new StopOnlineReturnTask(returnInfo.getRECID(),
									(String) returnValue2);
							getContext().handle(task);
							getContext().bubbleMessage(new MsgResponse(true));
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void addReExcuteAction(Button button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 重新执行
				ExecuteOnlineReturnTask task = new ExecuteOnlineReturnTask(returnInfo.getRECID());
				getContext().handle(task);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	@Override
	protected String[] getBaseInfoCellContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SNameValue[] getDataInfoContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getRemark() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetApprovalInfo() {
		if (returnInfo == null) return null;
		if (StringUtils.isEmpty(returnInfo.getApprovor())) return null;
		return "审批：" + returnInfo.getApprovor() + "(" + DateUtil.dateFromat(returnInfo.getApproveDate()) + ")";
	}

	@Override
	protected String getSheetCreateInfo() {
		return returnInfo == null ? null : "制单：" + returnInfo.getCreator() + "(" + DateUtil.dateFromat(returnInfo.getCreateDate()) + ")";
	}

	@Override
	protected String[] getSheetExtraInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return returnInfo == null ? null : returnInfo.getBillsNo();
	}

	@Override
	protected String getSheetTitle() {
		return returnInfo == null ? "新增退货单" : "网上退货单";
	}

	@Override
	protected String[] getSheetType() {
		return null;
	}

	@Override
	protected String getStopCause() {
		if (null == returnInfo || StringUtils.isEmpty(returnInfo.getRejectReason())) {
			return null;
		}
		if (OnlineReturnStatus.Rejected.equals(returnInfo.getStatus())) {
			return "退回原因：" + returnInfo.getRejectReason();
		}
		return null;
	}

	@Override
	protected void initSheetData() {
		// TODO Auto-generated method stub

	}

	@Override
	public SNameValue[] getExtraData(Object element) {
		double price = 0.0;
		double count = 0.0;
		GUID onlineBillsItemId = null;
		GUID goodsId = null;
		String goodsCode = null;
		String goodsNo = null;
		String goodsSpec = null;
		String goodsUnit = null;
		String goodsName = null;
		double billsCount = 0.0;
		if (element instanceof OnlineOrderInfoItem) {
			OnlineOrderInfoItem item = (OnlineOrderInfoItem) element;
			price = item.getPrice();
			count = item.getCount();
			onlineBillsItemId = item.getId();
			goodsId = item.getGoodsId();
			goodsCode = item.getGoodsCode();
			goodsNo = item.getGoodsNo();
			goodsSpec = item.getGoodsSpec();
			goodsUnit = item.getUnit();
			goodsName = item.getGoodsName();
			billsCount = item.getCount();
		} else if (element instanceof OnlineReturnDet) {
			OnlineReturnDet item = (OnlineReturnDet) element;
			price = item.getPrice();
			count = item.getCount();
			onlineBillsItemId = item.getOnlineBillsItemId();
			goodsId = item.getGoodsId();
			goodsCode = item.getGoodsCode();
			goodsNo = item.getGoodsNo();
			goodsSpec = item.getGoodsSpec();
			goodsUnit = item.getGoodsUnit();
			goodsName = item.getGoodsName();
			billsCount = item.getBillsCount();
		}
		return new SNameValue[] { new SNameValue(TableExtraValueName.price.name(), "" + price),
				new SNameValue(TableExtraValueName.count.name(), "" + count),
				new SNameValue(TableExtraValueName.onlineBillsItemId.name(), onlineBillsItemId.toString()),
				new SNameValue(TableExtraValueName.goodsId.name(), goodsId.toString()),
				new SNameValue(TableExtraValueName.goodsCode.name(), goodsCode),
				new SNameValue(TableExtraValueName.goodsNo.name(), goodsNo),
				new SNameValue(TableExtraValueName.goodsSpec.name(), goodsSpec),
				new SNameValue(TableExtraValueName.goodsUnit.name(), goodsUnit),
				new SNameValue(TableExtraValueName.goodsName.name(), goodsName),
				new SNameValue(TableExtraValueName.billsCount.name(), billsCount + "") };
	}

	@Override
	public String getElementId(Object element) {
		if (element instanceof OnlineOrderInfoItem) {
			OnlineOrderInfoItem item = (OnlineOrderInfoItem) element;
			return item.getId().toString();
		} else if (element instanceof OnlineReturnDet) {
			OnlineReturnDet item = (OnlineReturnDet) element;
			return item.getRECID().toString();
		}
		return GUID.randomID().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		if (Type.New == viewType && null != orderInfo) {
			return orderInfo.getItems();
		}
		if (Type.Detail == viewType && null != returnInfo) {
			return returnInfo.getItems().toArray(new OnlineReturnDet[0]);
		}
		return null;
	}

	@Override
	public String getValue(Object element, String columnName) {
		if (returnInfo == null || OnlineReturnStatus.Submitting.equals(returnInfo.getStatus())
				|| OnlineReturnStatus.Rejected.equals(returnInfo.getStatus())) {
			if (ColumnName.returnCount.name().equals(columnName)) {
				if (element instanceof OnlineOrderInfoItem) {
					return "";
				} else if (element instanceof OnlineReturnDet) {
					OnlineReturnDet item = (OnlineReturnDet) element;
					return item.getCount() + "";
				}
			}
		}
		return null;
	}
	
	@Override
	protected void exportAction() {
		Display.getCurrent().exportFile(this.getSheetTitle() + returnInfo.getBillsNo() + ".xls",
				"application/vnd.ms-excel", 1000000, new ExporterWithContext() {

					public void run(Context context, OutputStream outputStream) throws IOException {
						BillsWriter bw = new BillsWriter(outputStream);
						bw.setTitle(getSheetTitle());
						bw.addLabel("收款单号", returnInfo.getBillsNo());
						bw.addLabel("客户", returnInfo.getMemberName());
						bw.addLabel("手机号", returnInfo.getMemberPhone());
						bw.addLabel("下单日期", DateUtil.dateFromat(returnInfo.getOnlineCreateDate()));
						bw.addLabel("订单金额", DoubleUtil.getRoundStr(returnInfo.getSalesAmount()));
						bw.addLabel("备注", returnInfo.getReturnReason());
						String[] head = new String[] { "商品编号", "商品条码", "商品名称", "规格", "单位", "单价", "销售数量", "退货数量", "退货金额" };
						List<String[]> list = new ArrayList<String[]>();
						for (Object obj : getElements(context, null)) {
							OnlineReturnDet item = (OnlineReturnDet) obj;
							list.add(new String[] { item.getGoodsCode(), item.getGoodsNo(), item.getGoodsName(), item.getGoodsSpec(),
									item.getGoodsUnit(), DoubleUtil.getRoundStr(item.getPrice()), DoubleUtil.getRoundStr(item.getBillsCount()),
									DoubleUtil.getRoundStr(item.getCount()), DoubleUtil.getRoundStr(item.getAmount())});
						}
						bw.setTable(head, list);
						bw.setTotalLabel("退款总额", DoubleUtil.getRoundStr(returnInfo.getAmount()));
						try {
							bw.write(returnInfo.getBillsNo());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

	}

	@Override
	protected boolean isNeedExport() {
		if (null != returnInfo && returnInfo.getStatus() == OnlineReturnStatus.Finished) {
			return true;
		}
		return false;
	}

}
