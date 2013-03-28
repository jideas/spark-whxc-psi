package com.spark.psi.order.browser.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.BasePublicImages;
import com.spark.psi.base.browser.InventoryViewWindow;
import com.spark.psi.base.browser.SetPSICaseUtil;
import com.spark.psi.base.browser.SimpleSheetPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.order.entity.OrderGoodsItem;
import com.spark.psi.publish.order.entity.OrderInfo;

/**
 * <p>
 * 订单父类
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author modi
 * @version 2012-4-11
 */
public abstract class OrderDetailProcessor<T extends OrderGoodsItem> extends SimpleSheetPageProcessor<T> implements
		IDataProcessPrompt {

	private final static GridData gdButton;
	private final static GridData gdHidden;
	static {
		gdButton = new GridData();
		gdButton.widthHint = 80;
		gdButton.heightHint = 28;

		gdHidden = new GridData();
		gdHidden.exclude = true;
	}

	/**
	 * 添加材料
	 */
	public static final String ID_BUTTON_ADDGOODS = "button_addGoods";
	/**
	 * 按钮容器部分
	 */
	public static final String ID_COMPOSITE_BUTTON = "composite_button";
	/**
	 * 备注text
	 */
	protected Text memoText = null;

	// 查库窗口
	protected InventoryViewWindow inventoryInfoWindow;

	/**
	 * <p>
	 * 界面展示状态枚举
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author modi
	 * @version 2012-4-11
	 */
	public static enum View {
		Create, Edit, Look;
		public boolean isIn(View... views) {
			for (View v : views) {
				if (this == v) {
					return true;
				}
			}
			return false;
		}
	}

	public static enum CellTypeEnum {
		Number, Text,
		/**
		 * 仓库
		 */
		List_Store,
		/**
		 * 价格下拉
		 */
		List_Price
	}

	/**
	 * <p>
	 * 列枚举
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author modi
	 * @version 2012-4-11
	 */
	public static enum Cloumns {
		/**
		 * 
		 */
		GoodsItemCode(100, JWT.LEFT, "材料编号", null, true),
		/**
		 * 
		 */
		GoodsNo(100, JWT.LEFT, "材料条码", null, true),
		/**
		 * 
		 */
		GoodsName(100, JWT.LEFT, "材料名称", null, true),
		/**
		 * 
		 */
		GoodsProperties(130, JWT.LEFT, "材料规格", null, true),
		/**
		 * 
		 */
		GoodsUnit(36, JWT.CENTER, "单位", null, true),
		/**
		 * 
		 */
		Count(100, JWT.RIGHT, "数量", CellTypeEnum.Number, true),
		/**
		 * 
		 */
		Price(100, JWT.RIGHT, "单价", CellTypeEnum.Number, true), 
		/**
		 * 
		 */
		Buy_Avg_Price(100, JWT.RIGHT, "平均采购价",null, true),
		/**
		 * 
		 */
		DisCount(80, JWT.RIGHT, "折扣率（%）", CellTypeEnum.Number, true),
		/**
		 * 
		 */
		DisAmount(100, JWT.RIGHT, "折扣额", CellTypeEnum.Number, true),
		/**
		 * 
		 */
		Amount(100, JWT.RIGHT, "金额", null, true),
		/**
		 * 
		 */
		ReturnStore(100, JWT.CENTER, "退货仓库", CellTypeEnum.List_Store, true),
		/**
		 * 
		 */
		ReturnCount(100, JWT.RIGHT, "退货数量", CellTypeEnum.Number, true),
		/**
		 * 
		 */
		ReturnPrice(100, JWT.RIGHT, "退货单价", CellTypeEnum.Number, true),
		/**
		 * 
		 */
		ReturnAmount(100, JWT.RIGHT, "退货金额", CellTypeEnum.Number, true);
		private int width;
		private int align;
		private String title;
		private boolean isGrab;
		private CellTypeEnum cellType;

		/**
		 * @param width
		 * @param align
		 * @param title
		 */
		private Cloumns(int width, int align, String title, CellTypeEnum cellType, boolean isGrab) {
			this.width = width;
			this.align = align;
			this.title = title;
			this.isGrab = isGrab;
			this.cellType = cellType;
		}

		public CellTypeEnum getCellType() {
			return cellType;
		}

		public int getWidth() {
			return width;
		}

		public int getAlign() {
			return align;
		}

		public String getTitle() {
			return title;
		}

		public boolean isGrab() {
			return isGrab;
		}

		public boolean isIn(Cloumns... cloumns) {
			for (Cloumns c : cloumns) {
				if (this == c) {
					return true;
				}
			}
			return false;
		}
	}

	public static final String Text_TotalAmount = "TotalAmount";
	protected SNumberText Text_totalAmount;
	OrderInfo orderInfo;
	protected View viewEnum;
	// protected Situation context;
	private List<T> itemList = new ArrayList<T>();

	private Composite buttonCmp;

	@Override
	public void init(Situation context) {
		super.init(context);
		// this.context = context;
		this.orderInfo = getOrderInfo();
		if (null == viewEnum) {
			if (null == this.orderInfo || null == this.orderInfo.getOrderNumber()
					|| "".equals(this.orderInfo.getOrderNumber())) {
				this.viewEnum = View.Create;
			} else if (this.orderInfo.getOrderStatus().isIn(OrderStatus.Submit, OrderStatus.Denied)) {
				this.viewEnum = View.Edit;
			} else {
				this.viewEnum = View.Look;
			}
		}
	}

	@Override
	public void process(Situation context) {
		super.process(context);
		buttonCmp = this.createControl(ID_COMPOSITE_BUTTON, Composite.class);
		T[] ts = initItemList();
		if (null != ts) {
			itemList.addAll(Arrays.asList(ts));
		}
		memoText = this.createMemoText();
		if (null != orderInfo) {
			memoText.setText(orderInfo.getRemark());
			if (View.Look == viewEnum) {
				memoText.setEditable(false);
			}
		}
		if (OrderStatus.Submit.equals(orderInfo.getOrderStatus())
				|| OrderStatus.Denied.equals(orderInfo.getOrderStatus())) {
			memoText.setEnabled(true);
		} else {
			memoText.setEnabled(false);
		}

		// 总金额
		Text_totalAmount = this.createControl(Text_TotalAmount, SNumberText.class);
		if (View.Look != viewEnum) {
			this.registerInputValidator(new TextInputValidator(Text_totalAmount, "订单金额不能小于0！") {

				@Override
				protected boolean validateText(String str) {
					double amount = Double.parseDouble(str);
					if (amount < 0) {
						return false;
					}
					return true;
				}
			});
		}
		// 处理查库动作的客户端事件
		table.addClientEventHandler(STable.CLIENT_EVENT_ACTION, "SaaSOrder.handleActionPerformed");

		// 初始化查库窗口
		inventoryInfoWindow = new InventoryViewWindow(this.table);

		//
		this.registerInputValidator(new TableDataValidatorImpl());
	}

	@Override
	public String[] getTableActionIds() {
		if (View.Look == viewEnum) {
			return null;
		}
		return new String[] { Action.LookInventory.name(), Action.Delete.name() };
	}

	@Override
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		super.actionPerformed(rowId, actionName, actionValue);
		if (View.Look == viewEnum) {
			return;
		}
		if (Action.LookInventory.name().equals(actionName)) {
			String[] locationInfo = actionValue.split(":");
			lookInventory(rowId, new Point(Integer.parseInt(locationInfo[0]), Integer.parseInt(locationInfo[1])));
		} else if (Action.Delete.name().equals(actionName)) {
			table.removeRow(rowId);
			table.renderUpate();
		}
	}

	/**
	 * 执行查库操作
	 * 
	 * @param rowId
	 *            void
	 */
	protected abstract void lookInventory(String rowId, Point location);

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		itemMap.clear();
		return itemList.toArray();
	}

	/**
	 * 添加一条材料记录
	 * 
	 * @param item
	 * @return OrderDetailProcessor
	 */
	protected OrderDetailProcessor<T> addGoodsItem(T item) {
		itemList.add(item);
		return this;
	}

	/**
	 * 删除一条材料记录
	 * 
	 * @param rowId
	 * @return boolean
	 */
	protected void deleteGoodsItem(String rowId) {
		itemList.remove(itemMap.get(rowId));
		itemMap.remove(rowId);
		table.render();
	}

	private Map<String, T> itemMap = new HashMap<String, T>();

	@SuppressWarnings("unchecked")
	@Override
	public String getElementId(Object element) {
		OrderGoodsItem item = (OrderGoodsItem) element;
		itemMap.put(item.getId().toString(), (T) element);
		return item.getId().toString();
	}

	protected T getGoodsItem(String rowId) {
		return itemMap.get(rowId);
	}

	/**
	 * 初始化列表集合 void
	 * 
	 * @param itemList2
	 */
	protected abstract T[] initItemList();

	/**
	 * 获得订单对象
	 * 
	 * @return OrderInfo
	 */
	protected abstract OrderInfo getOrderInfo();

	/**
	 * 添加业务按钮
	 * 
	 * @param action
	 *            void
	 */
	protected void addButton(OrderAction action) {
		switch (action) {
		case Delete:
			break;
		case Approval:
			addApprove();
			addReturn();
			break;
		case consignment:
			addConsignment();
			break;
		case Deny:
			// 在审核状态中
			break;
		case Execut:
			addExecut();
			break;
		case Stop:
			addStop();
			break;
		case Submit:
			addSubmit();
			break;
		default:
			break;
		}
	}

	protected void addSubmit() {
		this.createButton(" 提交 ").addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (addSaveAction(false)) {
					addSubmitAction(e);
				}
			}
		});
	}

	/**
	 * 保存,保存成功返回true void
	 */
	protected abstract boolean addSaveAction(boolean haveTitle);

	private void addStop() {
		this.createButton("中止").addActionListener(new ActionListener() {

			public void actionPerformed(final ActionEvent e) {

				new SetPSICaseUtil(getContext(), SetPSICaseUtil.StopCause) {

					@Override
					protected void action(String cause) {
						addStopAction(e, cause);
						getContext().bubbleMessage(new MsgResponse(true));
					}
				};
			}
		});
	}

	private void addExecut() {
		this.createButton(" 重新执行 ").addActionListener(new ActionListener() {

			public void actionPerformed(final ActionEvent e) {
				String title = "确认重新执行此订单？";
				if (OrderType.Purchase_Return == orderInfo.getOrderType()) {
					title = "确认重新执行此退货申请？";
				}
				confirm(title, new Runnable() {

					public void run() {
						addExecutAction(e);
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
			}
		});

	}

	private void addConsignment() {
		this.createButton(" 确认发货 ").addActionListener(new ActionListener() {

			public void actionPerformed(final ActionEvent e) {
				String title = "确认发货此直供订单？";

				confirm(title, new Runnable() {

					public void run() {
						addConsignmentAction(e);
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
			}
		});

	}

	private void addReturn() {
		this.createButton(" 退回 ").addActionListener(new ActionListener() {

			public void actionPerformed(final ActionEvent e) {
				handleReturn(e);
			}
		});
	}

	/**
	 * 执行退回操作 void
	 * 
	 * @param e
	 */
	protected void handleReturn(final ActionEvent e) {
		new SetPSICaseUtil(getContext(), SetPSICaseUtil.ReturnCause) {

			@Override
			protected void action(String cause) {
				addReturnAction(e, cause);
				getContext().bubbleMessage(new MsgResponse(true));
			}
		};
	}

	private void addApprove() {
		this.createButton(" 批准 ").addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				addApproveAction(e);
				// getContext().bubbleMessage(new MsgResponse(true));
			}
		});

	}

	/**
	 * 在订单按钮区域加入按钮
	 * 
	 * @param title
	 * @return Button
	 */
	protected final Button createButton(String title) {
		new Label(buttonCmp).setText(" ");
		Button b = new Button(buttonCmp, JWT.APPEARANCE3);
		b.setText(title);
		b.setLayoutData(gdButton);
		return b;
	}

	/**
	 * 隐藏相关按钮
	 * 
	 * @param title
	 * @return
	 */
	protected final void setButtonVisible(Button button, boolean visible) {
		if (visible) {
			((Control) button.getPrev()).setVisible(true);
			((Control) button.getPrev()).setLayoutData(new GridData());
			button.setLayoutData(gdButton);
			button.setVisible(true);
		} else {
			((Control) button.getPrev()).setVisible(false);
			((Control) button.getPrev()).setLayoutData(gdHidden);
			button.setLayoutData(gdHidden);
			button.setVisible(false);
		}
	}

	/**
	 * 提交 void
	 * 
	 * @param e
	 */
	protected abstract void addSubmitAction(ActionEvent e);

	/**
	 * 中止 void
	 * 
	 * @param e
	 * @param cause
	 */
	protected abstract void addStopAction(ActionEvent e, String cause);

	/**
	 * 重新执行 void
	 * 
	 * @param e
	 */
	protected abstract void addExecutAction(ActionEvent e);

	/**
	 * 确认发货 void
	 * 
	 * @param e
	 */
	protected void addConsignmentAction(ActionEvent e) {

	}

	/**
	 * 退回 void
	 * 
	 * @param e
	 * @param cause
	 */
	protected abstract void addReturnAction(ActionEvent e, String cause);

	/**
	 * 批准 void
	 * 
	 * @param e
	 */
	protected abstract void addApproveAction(ActionEvent e);

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
		return orderInfo.getRemark();
	}

	@Override
	protected String getSheetApprovalInfo() {
		if (orderInfo.getApproverLabel() == null || "".equals(orderInfo.getApproverLabel())) {
			return null;
		} else {
			return "审批：" + orderInfo.getApproverLabel();
		}
	}

	@Override
	protected String getSheetCreateInfo() {
		if (orderInfo.getCreatorLabel() != null) {
			return "制单：" + orderInfo.getCreatorLabel() + "(" + DateUtil.dateFromat(orderInfo.getCreateDate()) + ")";
		} else {
			return null;
		}
	}

	@Override
	protected String[] getSheetExtraInfo() {
		if (orderInfo.getOrderType() != null) {
			return new String[] { "类型：" + orderInfo.getOrderType().getName() };
		}
		return null;
	}

	@Override
	protected String getSheetNumber() {
		return orderInfo.getOrderNumber();
	}

	@Override
	protected String[] getSheetType() {
		return null;
	}

	@Override
	protected String getStopCause() {
		if (orderInfo.getOrderStatus() == OrderStatus.Stop) {
			return "中止原因：" + orderInfo.getStopCause();
		} else if (orderInfo.getOrderStatus() == OrderStatus.Denied) {
			return "退回原因：" + orderInfo.getDenyCause();
		}
		return "";
	}

	@Override
	protected void initSheetData() {

	}

	//
	public SNameValue[] getExtraData(Object element) {
		OrderGoodsItem item = (OrderGoodsItem) element;
		return new SNameValue[] { new SNameValue(Cloumns.Amount.name(), "" + item.getAmount()),
				new SNameValue(Cloumns.ReturnAmount.name(), "" + item.getAmount()) };
	}

	protected final void fillPartnerInfoArea(Composite partnerInfoArea, String partnerTypeName, PartnerInfo partnerInfo) {
		if (null == partnerInfo) {
			return;
		}
		//
		GridLayout gl = new GridLayout();
		partnerInfoArea.setLayout(gl);
		String faxNumber = partnerInfo.getFax();
		//
		String contactPersonName = partnerInfo.getLinkmanName();
		String contactMobileNo = partnerInfo.getLinkmanMobile();
		String contactLandLineNumber = partnerInfo.getLinkmanTel();

		Label label = new Label(partnerInfoArea);
		StringBuffer partnerInfoString = new StringBuffer();
		partnerInfoString.append(partnerTypeName);
		partnerInfoString.append("名称：" + partnerInfo.getShortName());
		if (!StringUtils.isEmpty(faxNumber)) {
			partnerInfoString.append("(");
			label.setText(partnerInfoString.toString());
			partnerInfoString.setLength(0);
			partnerInfoString.append(faxNumber);
			partnerInfoString.append(")");
			new Label(partnerInfoArea).setImage(BasePublicImages.getImage("images/contact/saas_mark_fax.png"));
			label = new Label(partnerInfoArea);
		}
		partnerInfoString.append("   ");
		partnerInfoString.append(partnerTypeName);
		
		partnerInfoString.append("联系人：");
		if (!StringUtils.isEmpty(contactPersonName)) {
			partnerInfoString.append(contactPersonName);
			if (!StringUtils.isEmpty(contactMobileNo) || !StringUtils.isEmpty(contactLandLineNumber)) {
				partnerInfoString.append("(");
				if (!StringUtils.isEmpty(contactMobileNo)) {
					new Label(partnerInfoArea).setImage(BasePublicImages.getImage("images/contact/saas_mark_tel.png"));
					GridData gd = new GridData();
					gd.widthHint = 80;
					Label lb = new Label(partnerInfoArea);
					lb.setText(contactMobileNo + ")");
					lb.setLayoutData(gd);
				} else {
					new Label(partnerInfoArea) .setImage(BasePublicImages.getImage("images/contact/saas_mark_phone.png"));
					Label lb = new Label(partnerInfoArea);
					lb.setText(contactLandLineNumber + ")");
					GridData gd = new GridData();
					gd.widthHint = 80;
					lb.setLayoutData(gd);
				}
			}
		} else {
			partnerInfoString.append("无");
		}
		label.setText(partnerInfoString.toString());
		//
		gl.numColumns = partnerInfoArea.getChildrenCount();
	}

	protected final void fillDeliveryInfoArea(Composite deliveryInfoArea) {
		GridLayout gl = new GridLayout();
		deliveryInfoArea.setLayout(gl);
		//
		this.orderInfo.getPartnerInfo();
		Label label = new Label(deliveryInfoArea);
		String address = "无";
		if (null != this.orderInfo && null != this.orderInfo.getPartnerInfo()
				&& this.orderInfo.getPartnerInfo().getProvince() != null) {
			address = this.orderInfo.getPartnerInfo().getProvince().getName();
			address += this.orderInfo.getPartnerInfo().getCity().getName();
			address += this.orderInfo.getPartnerInfo().getTown().getName();
			address += this.orderInfo.getPartnerInfo().getAddress();
		}
		StringBuffer deliveryInfoString = new StringBuffer();
		deliveryInfoString.append("收货地址：");

		deliveryInfoString.append(address);
		label.setText(deliveryInfoString.toString());
		//
		gl.numColumns = deliveryInfoArea.getChildrenCount();
	}

	// ==============================校验部分===============================
	/**
	 * 获取指定单元格编辑值，校验时用。以作异常处理
	 * 
	 * @param rowId
	 * @param columnName
	 * @return String
	 */
	protected String getCellString(String rowId, String columnName) {
		String[] strs = table.getEditValue(rowId, columnName);
		if (null == strs || 0 == strs.length) {
			return null;
		}
		return strs[0];
	}

	private String valiDateCount(String str, Cloumns columnsEnum) {
		if (CheckIsNull.isEmpty(str)) {
			return validateTitle(columnsEnum, ValiDateErrorEnum.NULL);
		} else {
			try {
				double count = Double.parseDouble(str);
				if (count == 0) {
					return validateTitle(columnsEnum, ValiDateErrorEnum.Zero);
				} else if (count < 0) {
					return validateTitle(columnsEnum, ValiDateErrorEnum.Less_than_zero);
				} else if (count > Count_Max) {
					return validateTitle(columnsEnum, ValiDateErrorEnum.Greater_than_Max);
				} else {
					return null;
				}
			} catch (NumberFormatException e) {
				return validateTitle(columnsEnum, ValiDateErrorEnum.FormatException);
			}
		}
	}

	private String valiDateDisCount(String str, Cloumns columnsEnum) {
		if (CheckIsNull.isEmpty(str)) {
			return validateTitle(columnsEnum, ValiDateErrorEnum.NULL);
		} else {
			try {
				double count = Double.parseDouble(str);
				if (count == 0) {
					return validateTitle(columnsEnum, ValiDateErrorEnum.Zero);
				} else if (count < 0) {
					return validateTitle(columnsEnum, ValiDateErrorEnum.Less_than_zero);
				} else if (count > 100) {
					return validateTitle(columnsEnum, ValiDateErrorEnum.Greater_than_Max);
				} else {
					return null;
				}
			} catch (NumberFormatException e) {
				return validateTitle(columnsEnum, ValiDateErrorEnum.FormatException);
			}
		}
	}

	private String valiDateAmount(String str, Cloumns columnsEnum) {
		if (CheckIsNull.isEmpty(str)) {
			return validateTitle(columnsEnum, ValiDateErrorEnum.NULL);
		} else {
			try {
				double amount = Double.parseDouble(str);
				if (amount == 0) {
					return validateTitle(columnsEnum, ValiDateErrorEnum.Zero);
				} else if (amount < 0) {
					return validateTitle(columnsEnum, ValiDateErrorEnum.Less_than_zero);
				} else if (amount > Amount_Max) {
					return validateTitle(columnsEnum, ValiDateErrorEnum.Greater_than_Max);
				} else {
					return null;
				}
			} catch (NumberFormatException e) {
				return validateTitle(columnsEnum, ValiDateErrorEnum.FormatException);
			}
		}
	}

	/**
	 * 父类总校验，只做基础格校验，各 【加减乘除】 复合校验请各子类添加 (non-Javadoc)
	 * 
	 * @see com.spark.common.components.pages.SimpleListPageProcessor#validateCell(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	protected String validateCell(String rowId, String columnName) {
		if (View.Look == viewEnum) {
			return null;
		}
		String str = getCellString(rowId, columnName);
		if (Cloumns.Count.name().equals(columnName)) {
			return valiDateCount(str, Cloumns.Count);
		} else if (Cloumns.Price.name().equals(columnName)) {
			return valiDateAmount(str, Cloumns.Price);
		} else if (Cloumns.DisCount.name().equals(columnName)) {
			return valiDateDisCount(str, Cloumns.DisCount);
		} else if (Cloumns.DisAmount.name().equals(columnName)) {
			return valiDateAmount(str, Cloumns.DisAmount);
		} else if (Cloumns.ReturnStore.name().equals(columnName)) {
			if (CheckIsNull.isEmpty(str)) {
				return validateTitle(Cloumns.ReturnStore, ValiDateErrorEnum.NULL);
			} else {
				try {
					GUID.valueOf(str);
					return null;
				} catch (Exception e) {
					return validateTitle(Cloumns.ReturnStore, ValiDateErrorEnum.FormatException);
				}
			}
		} else if (Cloumns.ReturnCount.name().equals(columnName)) {
			return valiDateCount(str, Cloumns.ReturnCount);
		} else if (Cloumns.ReturnPrice.name().equals(columnName)) {
			return valiDateAmount(str, Cloumns.ReturnPrice);
		} else if (Cloumns.ReturnAmount.name().equals(columnName)) {
			return valiDateAmount(str, Cloumns.ReturnAmount);
		}
		return super.validateCell(rowId, columnName);
	}

	/**
	 * 数量最大值（可等于最大值）
	 */
	public final static double Count_Max = 9999999999999.9999;
	/**
	 * 金额最大值
	 */
	public final static double Amount_Max = 999999999999999.99;

	/**
	 * <p>
	 * 校验——错误枚举
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author modi
	 * @version 2012-4-25
	 */
	protected enum ValiDateErrorEnum {
		/**
		 * 小于0
		 */
		Less_than_zero,
		/**
		 * 等于0
		 */
		Zero,
		/**
		 * 大于最大值
		 */
		Greater_than_Max,
		/**
		 * 类型转换错误
		 */
		FormatException,
		/**
		 * 为空
		 */
		NULL
	}

	protected abstract String validateTitle(Cloumns columnsEnum, ValiDateErrorEnum error);

	@Override
	protected String validateRowCount(int rowCount) {
		if (View.Look == viewEnum) {
			return null;
		}
		if (0 >= rowCount) {
			return getTableNullTitle();
		}
		return super.validateRowCount(rowCount);
	}

	/**
	 * 明细table为null提示
	 * 
	 * @return String
	 */
	protected abstract String getTableNullTitle();

	// ==============================校验部分【END】===============================
	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		return addSaveAction(false);
	}
}
