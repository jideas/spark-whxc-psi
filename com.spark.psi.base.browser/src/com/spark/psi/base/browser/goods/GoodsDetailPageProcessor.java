package com.spark.psi.base.browser.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboTree;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.GoodsCategorySource;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryInfo;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;
import com.spark.psi.publish.base.goods.entity.PropertyDefine;
import com.spark.psi.publish.base.goods.task.GoodsInfoTask;
import com.spark.psi.publish.base.goods.task.ValidationGoodsIsExistTask;
import com.spark.psi.publish.base.goods.task.GoodsInfoTask.ItemMethod;

public class GoodsDetailPageProcessor extends PageProcessor implements IDataProcessPrompt{

//	public final static String ID_Area_BusInfo = "Area_BusInfo";
	public final static String ID_List_Category = "List_Category";
	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_Text_Code = "Text_Code";
	public final static String ID_Text_Price = "Text_Price";
	public final static String ID_Text_ShelfLife = "Text_ShelfLife";
	public final static String ID_Text_AlarmDays = "AlarmDays";
//	public final static String ID_Check_JointGoods = "Check_JointGoods";
//	public final static String ID_Text_Supplier = "Text_Supplier";
	public final static String ID_Area_Table = "Composite_TableArea";
	public final static String ID_Text_Memo = "Text_Memo";
	public final static String ID_Button_Save = "ID_Button_Save";
	public final static String ID_Button_SaveAndNew = "Button_SaveAndNew";

//	private final static Color DEFAULT_LINKE_COLOR = new Color(0x8eb8dd);

	private Map<Integer, String> columnIndexMap = new HashMap<Integer, String>();
	// 删除的商品条目：保存时要清
//	private final List<String> deleteItems = new ArrayList<String>();

	private GUID defaultCategoryId = null;
//	private boolean isJointOnly = false;

	private GoodsInfo goodsInfo = null;
	private GoodsCategoryTree categoryTree = null;
	private GUID selectedCategoryId = null;
	private GoodsCategoryInfo propertiedCategoryInfo = null;
	private LoginInfo loginInfo = null;
//	private GUID supplierId = null;

	private GoodsItemShowList goodsShowList = null;
	private SEditTable itemTable = null;

	private LWComboTree listCategory = null;
	private Text nameText = null;
	private Text codeText = null;
	private Text priceText = null;
	private Text shelfText = null;
	private Text alermText = null;
//	private Button checkJoint = null;
//	private Text supplierText = null;
	private Text memoText = null;
	private Composite tableArea = null;
//	private Composite busInfoArea = null;
	private Button saveButton = null;
	private Button saveAndNewButton = null;

	@Override
	public void init(Situation context) {
		loginInfo = context.find(LoginInfo.class);
		//
		categoryTree = context.find(GoodsCategoryTree.class);
		GUID goodsId = (GUID) this.getArgument();
		if (goodsId != null) {
			goodsInfo = context.find(GoodsInfo.class, goodsId);
			if (goodsInfo == null) {
				throw new IllegalArgumentException("找不到指定的商品信息");
			}
		}
		if (getArgument2() != null && getArgument2() instanceof GUID) {
			defaultCategoryId = (GUID) getArgument2();
		}

//		if (getArgument3() != null && getArgument3() instanceof Boolean) {
//			isJointOnly = (Boolean) getArgument3();
//		}
	}

	@Override
	public void process(Situation context) {
		listCategory = createControl(ID_List_Category, LWComboTree.class);
		nameText = createControl(ID_Text_Name, Text.class);
		codeText = createControl(ID_Text_Code, Text.class);
		priceText = createControl(ID_Text_Price, Text.class);
		shelfText = createControl(ID_Text_ShelfLife, Text.class);
		alermText = createControl(ID_Text_AlarmDays, Text.class);
//		checkJoint = createControl(ID_Check_JointGoods, Button.class);
//		supplierText = createControl(ID_Text_Supplier, Text.class);
		memoText = createControl(ID_Text_Memo, Text.class);

		tableArea = createControl(ID_Area_Table, Composite.class);
//		busInfoArea = createControl(ID_Area_BusInfo, Composite.class);

		saveButton = createControl(ID_Button_Save, Button.class);
		saveAndNewButton = createControl(ID_Button_SaveAndNew, Button.class);

		registerNotEmptyValidator(nameText, "商品名称");
		registerNotEmptyValidator(shelfText, "保质期");
		registerNotEmptyValidator(alermText, "预警天数");
		registerInputValidator(new TextInputValidator(nameText, "商品名称重复，请修改。") {

			@Override
			protected boolean validateText(String text) {
				ValidationGoodsIsExistTask goodsValidateTask = new ValidationGoodsIsExistTask(
						goodsInfo == null ? null : goodsInfo.getId(),
						selectedCategoryId, text, "");
				getContext().handle(goodsValidateTask);
				if (ValidationGoodsIsExistTask.ErrType.Name
						.equals(goodsValidateTask.getErrType())) {
					return false;
				} else {
					return true;
				}
			}
		});

//		busInfoArea = createControl(ID_Area_BusInfo, Composite.class);
//		setBufInfoArea();

		codeText.setEnabled(false);
		priceText.addClientEventHandler(JWT.CLIENT_EVENT_FOCUS_LOST,
				"PSIBase.GoodsDetail.priceOnChange");
		listCategory.getTree().setSource(new GoodsCategorySource());
		listCategory.getTree().setInput(categoryTree);
		listCategory.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				listOnSelection(nameText);
			}
		});
		// 联营商品
//		checkJoint.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				if (!checkJoint.getSelection() && null != goodsInfo) {
//					if (isContainsHasInventoryItem()) {
//						Runnable confirmRunnable = new Runnable() {
//							
//							public void run() {
//								// 确定
//								
//							}
//						};
//						
//						Runnable cancelRunnable = new Runnable() {
//							
//							public void run() {
//								// 取消
//								checkJoint.setSelection(true);
//							}
//						};
//						confirm("", confirmRunnable, cancelRunnable);
//					}
//				}
//				
////				if (hasInventory) {
////					alert("", new Runnable() {
////
////						public void run() {
////							// TODO Auto-generated method stub
////							
////						}
////						
////					});
////				}
//				if (checkJoint.getSelection()) {
//					setSupplierHide(false);
//				} else {
//					setSupplierHide(true);
//				}
//			}
//		});

//		supplierText.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				MsgRequest request = CommonSelectRequest
//						.createSelectSupplierRequest(false, false, false,
//								goodsInfo == null ? null : goodsInfo
//										.getSupplierId());
//				request.setResponseHandler(new ResponseHandler() {
//					
//					public void handle(Object returnValue, Object returnValue2,
//							Object returnValue3, Object returnValue4) {
//						supplierId = (GUID)returnValue;
//						if (supplierId != null) {
//							SupplierInfo supplierInfo = getContext().find(SupplierInfo.class, supplierId);
//							supplierText.setText(supplierInfo == null ? "" : supplierInfo.getName());
//						}
//					}
//				});
//				getContext().bubbleMessage(request);
//			}
//		});

		// 保存按钮
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		// 保存新建按钮
		saveAndNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAndNew();
			}
		});

		initData();

	}
	
//	private boolean isContainsHasInventoryItem() {
//		boolean hasInventory = false;
//		if (null != goodsInfo) {
//			GUID[] ids = new GUID[goodsInfo.getItems().length];
//			for (int itemIndex = 0; itemIndex < ids.length; itemIndex++) {
//				ids[itemIndex] = goodsInfo.getItems()[itemIndex].getId();
//			}
//			List<InventoryInfo> inventoryList = getContext().getList(
//					InventoryInfo.class,
//					new GetInventoryInfoListKey(ids, null));
//			for (InventoryInfo inventory : inventoryList) {
//				if (inventory.getCount() > 0) {
//					hasInventory = true;
//					break;
//				}
//			}
//		}
//		return hasInventory;
//	}
	
	private void initData() {
		initData(false);
	}
	private void initData(boolean isSaveNew) {
		if (goodsInfo != null) {
			// 修改商品
			listCategory.setSelection(categoryTree.getNodeById(goodsInfo
					.getCategory().getId()));
			listCategory.setEditable(false);
			listCategory.setButtonVisible(false);
			//
			nameText.setText(goodsInfo.getName());
			Label codeTitle = (Label) codeText.getPrev();
			codeText.setVisible(true);
			codeTitle.setVisible(true);
			codeText.setText(goodsInfo.getCode());
			priceText.setText(goodsInfo.getSalePrice() == 0 ? "" : DoubleUtil
					.getRoundStr(goodsInfo.getSalePrice()));
			shelfText.setText("" + goodsInfo.getShelfLife());
			alermText.setText("" + goodsInfo.getWarningDay());
//			if (goodsInfo.isJointVenture()) {
//				checkJoint.setSelection(true);
//				supplierText.setText(goodsInfo.getSupplier());
//			} else {
//				checkJoint.setSelection(false);
//			}
			memoText.setText(goodsInfo.getRemark());
			
//			supplierText.setText(goodsInfo.getSupplier());
//			supplierId = goodsInfo.getSupplierId();
		} else {
			Label codeTitle = (Label) codeText.getPrev();
			codeText.setVisible(false);
			codeTitle.setVisible(false);

			listCategory.getTree().setExpand(categoryTree, true);
			listCategory.setEditable(false);
			listCategory.setButtonVisible(true);
			listCategory.setSelection(defaultCategoryId == null ? null
					: defaultCategoryId.toString());

			nameText.setText("");
			codeText.setText("");
			priceText.setText("");
			shelfText.setText("");
			alermText.setText("");
//			checkJoint.setSelection(isJointOnly);
			memoText.setText("");

//			if (isJointOnly) {
//				checkJoint.setEnabled(false);
//			}
//			if (isSaveNew) {
//				resetItemTable();
//			}
			resetItemTable();
//			if (null != itemTable && !itemTable.isDisposed()) {
//				itemTable.render();
//			}
		}
	}

//	private void setSupplierHide(boolean isHide) {
//		Label supplierTitle = (Label) supplierText.getPrev();
//		supplierText.setVisible(!isHide);
//		supplierTitle.setVisible(!isHide);
//	}

	private void listOnSelection(Text nameText) {
		// nameText.setText("");
		resetItemTable();

		// 当前用户具有修改商品的权限，但商品被引用且当前用户不能修改商品价格
		// （即是当前用户进入时，商品明细列表处于完全不可编辑状态，但该用户又具有编辑商品-这里可继续添加明细的权限）
		if (null != goodsInfo
				&& goodsInfo.isRefFlag()
				&& loginInfo.hasAuth(Auth.SubFunction_GoodsMange_UpdateGoods)
				&& !loginInfo
						.hasAuth(Auth.SubFunction_GoodsMange_EditSalesAmount)) {
			if (null != itemTable && null != itemTable.getAllRowId()
					&& itemTable.getAllRowId().length > 0) {
				itemTable.addSelectionListener(new SelectionListener() {

					public void widgetSelected(SelectionEvent e) {
						String[] allRowIds = itemTable.getAllRowId();
						String selectedRowId = itemTable.getSelection();
						String lastRowId = allRowIds[allRowIds.length - 1];
						String lastItemId = null;
						try {
							lastItemId = goodsInfo.getItems()[goodsInfo
									.getItems().length - 1].getId().toString();
						} catch (Throwable t) {
							lastItemId = null;
						}
						if (lastItemId == null || !lastItemId.equals(lastRowId)) {
							return;
						}

						if (selectedRowId == null || lastRowId == null)
							return;
						if (selectedRowId.equals(lastRowId)) {
							itemTable.addRow(GUID.randomID().toString());
							itemTable.renderUpate();
						}
					}
				});
			}
		}
	}
	
	private void enableBaseInput(boolean enabled) {
		nameText.setEnabled(enabled);
		priceText.setEnabled(enabled);
		shelfText.setEnabled(enabled);
		alermText.setEnabled(enabled);
	}

	private void resetItemTable() {
		// 根据categoryInfo加载明细表格
		propertiedCategoryInfo = null;
		selectedCategoryId = GUID.tryValueOf(listCategory.getText());
		if (selectedCategoryId != null) {
			GoodsCategoryTree.CategoryNode node = categoryTree
					.getNodeById(selectedCategoryId);
			while (node != null && !node.isSetPropertyFlag()) {
				node = node.getParent();
			}
			if (node != null) {
				propertiedCategoryInfo = getContext().find(
						GoodsCategoryInfo.class, node.getId());
			}
		}
		//

		tableArea.clear();
		if (selectedCategoryId != null
				&& this.categoryTree.getNodeById(selectedCategoryId)
						.getChildren().length > 0) {
			propertiedCategoryInfo = null;
			if (selectedCategoryId != null) {
				alert("必须选择最末一级节点！");
			}
			saveButton.setEnabled(false);
			saveAndNewButton.setEnabled(false);
			STableColumn[] columns = new STableColumn[1];
			columns[0] = new STableColumn(" ", 200, JWT.RIGHT, "必须选择最末一级节点");
			columns[0].setGrab(true);
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(false);
			STable table = new STable(tableArea, columns, tableStyle);
			//
			table.setContentProvider(new GoodsItemEditContentProvider());
			table.setLabelProvider(new GoodsItemLabelProvider(0));
			table.render();
			enableBaseInput(false);
		} else if (propertiedCategoryInfo == null) {
			if (selectedCategoryId != null) {
				alert("请首先为该分类设置属性，再新建商品！");
			}
			saveButton.setEnabled(false);
			saveAndNewButton.setEnabled(false);
			STableColumn[] columns = new STableColumn[1];
			columns[0] = new STableColumn(" ", 200, JWT.RIGHT, "分类未设置属性");
			columns[0].setGrab(true);
			STableStyle tableStyle = new STableStyle();
			tableStyle.setPageAble(false);
			STable table = new STable(tableArea, columns, tableStyle);
			//
			table.setContentProvider(new GoodsItemEditContentProvider());
			table.setLabelProvider(new GoodsItemLabelProvider(0));
			table.render();
			enableBaseInput(false);
		} else {
			enableBaseInput(true);
			final PropertyDefine[] propertyDefines = propertiedCategoryInfo
					.getPropertyDefines();
			goodsShowList = new GoodsItemShowList(tableArea, goodsInfo, propertyDefines, priceText);
			itemTable = goodsShowList.getItemTable();
			registerInputValidator(new TableDataValidator(itemTable) {

				@Override
				protected String validateRowCount(int rowCount) {
					if (rowCount < 1) {
						return "商品条目不能为空";
					}
					return null;
				}

				@Override
				protected String validateCell(String rowId, String columnName) {
					if (goodsShowList.isEmptyItemRow(rowId)) {
						return null;
					}
					
					if (GoodsItemShowList.COLUMN_NAME_LOSSRATE.equals(columnName)) {
						String lossRateStr = itemTable.getEditValue(rowId, columnName)[0];
						if (StringUtils.isEmpty(lossRateStr)) {
							return null;
						}
						if (DoubleUtil.strToDouble(lossRateStr)  >= 1
								|| DoubleUtil.strToDouble(lossRateStr) < 0) {
							return "损耗率只能为大于等于0小于1的小数。";
						}
					}
					
					List<String> judgeColumns = new ArrayList<String>();
					judgeColumns.add(GoodsItemShowList.COLUMN_NAME_NUMBER);
					judgeColumns.add(GoodsItemShowList.COLUMN_NAME_SPEC);
					judgeColumns.add(GoodsItemShowList.COLUMN_NAME_PRICE);
					judgeColumns.add(GoodsItemShowList.COLUMN_NAME_STANDARDCOST);
					judgeColumns.add(propertyDefines[0].getName());
					if (!judgeColumns.contains(columnName)) return null;
					
					if (goodsInfo != null) {
						for (GoodsItemData item : goodsInfo.getItems()) {
							if (item.getId().toString().equals(rowId)
									&& item.isRefFlag() 
									&& !columnName.equals(GoodsItemShowList.COLUMN_NAME_PRICE)
									&& !columnName.equals(GoodsItemShowList.COLUMN_NAME_STANDARDCOST)) { // 如果已被引用，则只校验价格
								return null;
							}
						}
					}
					String[] columnValue = itemTable.getEditValue(rowId,
							columnName);
					if (columnValue == null || columnValue.length < 1
							|| StringHelper.isEmpty(columnValue[0])) {
						if (columnName.equals(GoodsItemShowList.COLUMN_NAME_NUMBER)) {
							return "条码不能为空";
						} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_SPEC)) {
							return "规格不能为空";
						} else  if (columnName.equals(GoodsItemShowList.COLUMN_NAME_PRICE)) {
							return "销售价格不能为空";
						} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_STANDARDCOST)) {
							return "标准成本不能为空";
						} else {
							return columnName + "不能为空";
						}
					} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_NUMBER)) {
//						ValidationGoodsIsExistTask goodsValidateTask = new ValidationGoodsIsExistTask(
//								goodsInfo == null ? null : goodsInfo.getId(),
//								selectedCategoryId, "", columnValue[0]);
//						goodsValidateTask.setItemId(GUID.tryValueOf(rowId));
//						getContext().handle(goodsValidateTask);
//						if (ValidationGoodsIsExistTask.ErrType.Code
//								.equals(goodsValidateTask.getErrType())) {
//							return "商品条码不能重复。";
//						}
					} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_PRICE)) {
						try {
							double price = DoubleUtil
									.strToDouble(columnValue[0]);
							if (price <= 0) {
								return "销售价格必须大于0";
							}
						} catch (Exception e) {
							return "销售价格必须为数字";
						}
					} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_ORIGINALPRICE)) {
						try {
							double price = DoubleUtil
									.strToDouble(columnValue[0]);
							if (price <= 0) {
								return "原价必须大于0";
							}
						} catch (Exception e) {
							return "原价必须为数字";
						}
					}
					return null;
				}
			});

			saveButton.setEnabled(true);
			saveAndNewButton.setEnabled(true);
		}
		tableArea.layout();
	}

//	private void setBufInfoArea() {
//		if (goodsInfo == null) {
//			busInfoArea.dispose();
//		} else {
//			Label lastLabel = null;
//
//			GridData gdSep = new GridData();
//			gdSep.widthHint = 1;
//			gdSep.heightHint = 10;
//
//			if (loginInfo
//					.hasAuth(Auth.SubFunction_GoodsMange_ShowInventoryInfo)) {
//				final Label label = new Label(busInfoArea);
//				label.setText("  库存信息");
//				label.setForeground(DEFAULT_LINKE_COLOR);
//				PageController pcInventory = new PageController(
//						GoodsInventoryInfoProcessor.class,
//						GoodsInventoryInfoRender.class);
//				PageControllerInstance pciInventory = new PageControllerInstance(
//						pcInventory, goodsInfo.getId(), true);
//				showPopWindow(label, pciInventory);
//				lastLabel = label;
//			}
//			if (loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ShowPurchaseInfo)) {
//				if (lastLabel != null) {
//					lastLabel.setText(lastLabel.getText() + "  ");
//
//					Composite sep1 = new Composite(busInfoArea);
//					sep1.setLayoutData(gdSep);
//					sep1.setBackground(DEFAULT_LINKE_COLOR);
//				}
//				final Label label1 = new Label(busInfoArea);
//				label1.setText("  采购情况");
//				label1.setForeground(DEFAULT_LINKE_COLOR);
//				PageController pcPurchase = new PageController(
//						GoodsPurchaseSummaryProcessor.class,
//						GoodsPurchaseSummaryRender.class);
//				PageControllerInstance pciPurchase = new PageControllerInstance(
//						pcPurchase, goodsInfo.getId(), true);
//				showPopWindow(label1, pciPurchase);
//				lastLabel = label1;
//			}
//			if (loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ShowSaleInfo)) {
//				if (lastLabel != null) {
//					lastLabel.setText(lastLabel.getText() + "  ");
//					Composite sep2 = new Composite(busInfoArea);
//					sep2.setLayoutData(gdSep);
//					sep2.setBackground(DEFAULT_LINKE_COLOR);
//				}
//				final Label label2 = new Label(busInfoArea);
//				label2.setForeground(DEFAULT_LINKE_COLOR);
//				label2.setText("  销售情况");
//				PageController pcSale = new PageController(
//						GoodsSalesSummaryProcessor.class,
//						GoodsSalesSummaryRender.class);
//				PageControllerInstance pciSale = new PageControllerInstance(
//						pcSale, goodsInfo.getId(), true);
//				showPopWindow(label2, pciSale);
//				lastLabel = label2;
//			}
//		}
//	}
//
//	private void showPopWindow(final Control owner,
//			final PageControllerInstance pci) {
//		SMenuWindow menuWindow = new SMenuWindow(null, owner, Direction.Down);
//		menuWindow.bindTargetControl(owner);
//		menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW,
//				"PSIBase.GoodsSelect.onSelectedGoodsWindowShow");
//		Composite windowArea = menuWindow.getContentArea();
//		windowArea.setLayout(new GridLayout());
//		final ScrolledPanel area = new ScrolledPanel(windowArea);
//		GridData gd = new GridData();
//		gd.widthHint = 750;
//		gd.heightHint = 360;
//		area.setLayoutData(gd);
//		menuWindow.addClientNotifyListener(new ClientNotifyListener() {
//			public void notified(ClientNotifyEvent e) {
//				area.getComposite().showPage(ControllerPage.NAME, pci);
//			}
//		});
//	}

	private void save() {
		GUID id = doSave();
		if (id == null)
			return;
		super.resetDataChangedstatus();
		hint("保存成功！");
		this.goodsInfo = getContext().find(GoodsInfo.class, id);
		initData();
	}

	private void saveAndNew() {
		GUID id = doSave();
		if (id == null)
			return;
		this.goodsInfo = null;

//		setBufInfoArea();

		initData(true);
	}

	private GUID doSave() {
		// 基本验证
		if (!validateInput()) {
			return null;
		}

		// 处理item
		if (itemTable != null && !itemTable.isDisposed()) {
			//
			PropertyDefine[] propertyDefines = propertiedCategoryInfo
					.getPropertyDefines();
			String[] propertyColumns = new String[propertyDefines.length];
			for (int i = 0; i < propertyDefines.length; i++) {
				propertyColumns[i] = propertyDefines[i].getName();
			}
			String[] rowIds = itemTable.getAllRowId();
			List<GoodsInfoTask.Item> itemList = new ArrayList<GoodsInfoTask.Item>();
			if (rowIds != null && rowIds.length > 0) {
				for (int i = 0; i < rowIds.length; i++) {
					if (goodsShowList.isEmptyItemRow(rowIds[i])) { // 忽略空行
						continue;
					}
					
					GUID id = GUID.valueOf(rowIds[i]);
					GoodsInfoTask.Item item = new GoodsInfoTask.Item();
					item.setId(id);
					GoodsItemData itemData = getGoodsItemDataById(id);
					//
					String[] otherValues = itemTable.getEditValue(rowIds[i],
							GoodsItemShowList.COLUMN_NAME_NUMBER, GoodsItemShowList.COLUMN_NAME_SPEC, 
							GoodsItemShowList.COLUMN_NAME_PRICE, GoodsItemShowList.COLUMN_NAME_ORIGINALPRICE, 
							GoodsItemShowList.COLUMN_NAME_LOSSRATE, GoodsItemShowList.COLUMN_NAME_STATUS,
							GoodsItemShowList.COLUMN_NAME_STANDARDCOST);
					
					item.setGoodsNo(otherValues[0]);
					item.setGoodsSpec(otherValues[1]);
					item.setSalePrice(DoubleUtil.strToDouble(StringUtils.isEmpty(otherValues[2]) ? "0" : otherValues[2]));
					item.setOriginalPrice(DoubleUtil.strToDouble(StringUtils.isEmpty(otherValues[3]) ? "0" : otherValues[3]));
					item.setLossRate(DoubleUtil.strToDouble(StringUtils.isEmpty(otherValues[4]) ? "0" : otherValues[4], 4));
					item.setStandardCost(DoubleUtil.strToDouble(otherValues[6]));
					if (otherValues[5] == null) {
						if (itemData == null
								|| itemData.getStatus().equals(
										GoodsStatus.ON_SALE)) {
							item.setOnsale(true);
						} else {
							item.setOnsale(false);
						}
					} else {
						item.setOnsale(GoodsStatus.ON_SALE.getCode().equals(
								otherValues[5]));
					}
					
					if (!validateGoodsItemUnique(item.getId(), item.getGoodsSpec(), item.getGoodsNo())) {
						alert("已存在条码为：" + item.getGoodsNo() + "，规格为：" + item.getGoodsSpec() + "的商品。");
						return null;
					}
					// 对被引用的条目，属性值从原数据中获取
					if ("1"
							.equals(itemTable.getExtraData(rowIds[i], "isRef")[0])) {
						String[] propertyValues = itemTable.getEditValue(
								rowIds[i], propertyColumns);
						for (GoodsInfoTask.Item preItem : itemList) {
//							if (preItem.getGoodsNo().equals(item.getGoodsNo()) ) {
//								alert("商品明细条码不能重复。");
//								return null;
//							}
							if (preItem.getGoodsSpec().equals(item.getGoodsSpec())
									&& preItem.getPropertyValues()[0].equals(item.getPropertyValues()[0])) {
								alert("商品明细的规格和单位不能同时重复。");
								return null;
							}
//							if (preItem.getGoodsNo().equals(item.getGoodsNo())
//									&& preItem.getGoodsSpec().equals(item.getGoodsSpec())) {
//								alert("商品条码和商品规格不能同时重复。");
//								return null;
//							}
						}
						item.setUnit(propertyValues[0]);
						if (null != itemData) {
							item
									.setPropertyValues(propertyValues);
//							item.setUnit(itemData.getUnit());
							item.setGoodsNo(itemData.getGoodsItemNo());
							item.setGoodsSpec(itemData.getSpec());
							itemList.add(item);
						}
					} else {
						String[] propertyValues = itemTable.getEditValue(
								rowIds[i], propertyColumns);
						item.setPropertyValues(propertyValues);
						for (GoodsInfoTask.Item preItem : itemList) {
//							if (preItem.getGoodsNo().equals(item.getGoodsNo()) ) {
//								alert("商品明细条码不能重复。");
//								return null;
//							}
							if (preItem.getGoodsSpec().equals(item.getGoodsSpec())
									&& preItem.getPropertyValues()[0].equals(item.getPropertyValues()[0])) {
								alert("商品明细的规格和单位不能同时重复。");
								return null;
							}
							if (preItem.getGoodsNo().equals(item.getGoodsNo())
									&& preItem.getGoodsSpec().equals(item.getGoodsSpec())) {
								alert("商品条码和商品规格不能同时重复。");
								return null;
							}
						}
						item.setUnit(propertyValues[0]);
						itemList.add(item);
					}
				}
			}
			if (itemList.size() < 1) {
				alert("商品条目不能为空！");
				return null;
			}
			GoodsInfoTask task = new GoodsInfoTask();
			task.setCategoryId(selectedCategoryId);
			task.seRemark(memoText.getText());
			task.setName(nameText.getText());
			task.setCode(goodsInfo == null ? null : goodsInfo.getCode());
			if (CheckIsNull.isNotEmpty(priceText.getText()))
				task.setSalePrice(DoubleUtil.strToDouble(priceText.getText()));
			if (CheckIsNull.isNotEmpty(shelfText.getText()))
				task.setShelfLife(Integer.valueOf(shelfText.getText()));
			if (CheckIsNull.isNotEmpty(alermText.getText()))
				task.setWarningDay(Integer.valueOf(alermText.getText()));
			
			if (task.getShelfLife() > 0 && task.getWarningDay()> 0 && 
					task.getShelfLife() <= task.getWarningDay()) {
				alert("预警天数必须小于保质期。");
				return null;
			}
			if (goodsInfo != null) {
				// 增加删除的item
				for (GoodsItemData itemData : goodsInfo.getItems()) {
					if (goodsShowList.getDeleteRowIdList().contains(itemData.getId().toString())) {
						GoodsInfoTask.Item item = new GoodsInfoTask.Item();
						item.setId(itemData.getId());
						item.setMethod(ItemMethod.Delete);
						itemList.add(item);
					}
				}
				task.setItems(itemList.toArray(new GoodsInfoTask.Item[0]));
				task.setId(goodsInfo.getId());
				getContext().handle(task, GoodsInfoTask.Method.Update);
				// 删除后要清空
//				deleteItems.clear();
			} else {
				task.setItems(itemList.toArray(new GoodsInfoTask.Item[0]));
				getContext().handle(task, GoodsInfoTask.Method.Create);
			}
			getContext().bubbleMessage(new MsgResponse(false));
			return task.getId();
		} else {
			throw new IllegalStateException();
		}
	}

	
	private boolean validateGoodsItemUnique(GUID itemId, String spec, String number) {
		ValidationGoodsIsExistTask goodsValidateTask = new ValidationGoodsIsExistTask(
				goodsInfo == null ? null : goodsInfo.getId(),
				selectedCategoryId, "", number);
		goodsValidateTask.setItemId(itemId);
		goodsValidateTask.setSpec(spec);
		getContext().handle(goodsValidateTask);
		if (ValidationGoodsIsExistTask.ErrType.SPECANDNUMBER
				.equals(goodsValidateTask.getErrType())) {
			return false;
		} else {
			return true;
		}
	}
	
	private GoodsItemData getGoodsItemDataById(GUID itemId) {
		GoodsItemData itemData = null;
		if (goodsInfo != null && goodsInfo.getItems() != null) {
			for (GoodsItemData item : goodsInfo.getItems()) {
				if (item.getId().equals(itemId)) {
					itemData = item;
					break;
				}
			}
		}
		return itemData;
	}

//	private boolean isEmptyItemRow(String rowId) {
//		boolean isEmpty = true;
//		if ("1".equals(itemTable.getExtraData(rowId, "isRef")[0])) {
//			return false;
//		}
//		PropertyDefine[] propertyDefines = propertiedCategoryInfo
//				.getPropertyDefines();
//		String[] propertyColumns = new String[propertyDefines.length];
//		for (int i = 0; i < propertyDefines.length; i++) {
//			propertyColumns[i] = propertyDefines[i].getName();
//		}
//		String[] propertyValues = itemTable
//				.getEditValue(rowId, propertyColumns);
//		for (String columnValue : propertyValues) {
//			if (!StringHelper.isEmpty(columnValue)) {
//				isEmpty = false;
//				break;
//			}
//		}
//		return isEmpty;
//	}

	class GoodsItemEditContentProvider implements SEditContentProvider {

		protected class PreData {
			private GUID id;

			public PreData(GUID id) {
				this.id = id;
			}

			public GUID getId() {
				return this.id;
			}
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			if (goodsInfo != null) {
				return goodsInfo.getItems();
			}
			return null;
		}

		public String getElementId(Object element) {
			if (element instanceof String) {
				return (String) element;
			} else if (element instanceof GoodsItemData) {
				return ((GoodsItemData) element).getId().toString();
			} else if (element instanceof PreData) {
				return ((PreData) element).getId().toString();
			}
			throw new IllegalStateException();
		}

		public String getValue(Object element, String columnName) {
			if (element instanceof String) {
				if (columnName.equals("price")) {
					try {
						return DoubleUtil.getRoundStr(Double
								.parseDouble(priceText.getText())); // 初始销售价格
					} catch (Throwable t) {
						return "";
					}
				} else if (columnName.equals("status")) {
					return GoodsStatus.ON_SALE.getCode(); // 初始状态
				} else {
					return "";
				}
			} else if (element instanceof GoodsItemData) {
				GoodsItemData item = (GoodsItemData) element;
//				if (!item.isRefFlag()) { // 仅没有引用时可以修改属性值
					PropertyDefine[] propertyDefines = propertiedCategoryInfo
							.getPropertyDefines();
					for (int i = 0; i < propertyDefines.length; i++) {
						PropertyDefine define = propertyDefines[i];
						if (define.getName().equals(columnName)) {
							return item.getPropertyValues()[i];
						}
					}
//				}
				if (columnName.equals("price")
						&& loginInfo
								.hasAuth(Auth.SubFunction_GoodsMange_EditSalesAmount)) {
					if (item.getSalePrice() > 0) {
						return String.valueOf(item.getSalePrice());
					} else {
						return "";
					}
				} else if (columnName.equals("status")) {
					return item.getStatus().getCode();
				}
			}
			return null;
		}

		public String[] getActionIds(Object element) {
			if (element instanceof String || element instanceof PreData) {
				if (loginInfo
						.hasAuth(Auth.SubFunction_GoodsMange_ChangeGoodsStatus)) {
					return new String[] { Action.OffSale.name(),
							Action.Delete.name() }; // 初始操作
				} else {
					return new String[] { Action.Delete.name() };
				}
			} else if (element instanceof GoodsItemData) {
				GoodsItemData item = (GoodsItemData) element;
				List<String> actionList = new ArrayList<String>();
				if (loginInfo
						.hasAuth(Auth.SubFunction_GoodsMange_ChangeGoodsStatus)) {
					if (item.getStatus() == GoodsStatus.ON_SALE) {
						actionList.add(Action.OffSale.name());
					} else {
						actionList.add(Action.OnSale.name());
					}
				}

				if (loginInfo.hasAuth(Auth.SubFunction_GoodsMange_UpdateGoods)
						&& !item.isRefFlag()) {
					actionList.add(Action.Delete.name());
				}
				return actionList.toArray(new String[actionList.size()]);
			}
			return null;
		}

		public Object getNewElement() {
			return GUID.randomID().toString();
		}

		public SNameValue[] getExtraData(Object element) {
			if (element instanceof String) {
				return new SNameValue[] { new SNameValue("isNew", "1") };
			} else if (element instanceof GoodsItemData) {
				return new SNameValue[] { new SNameValue("isRef",
						((GoodsItemData) element).isRefFlag() ? "1" : "0") };
			}
			return null;
		}

		public boolean isSelected(Object element) {
			return false;
		}

		public boolean isSelectable(Object element) {
			return false;
		}

	}

	class GoodsItemLabelProvider implements SLabelProvider,
			SNumberLabelProvider {

		private final int propertyCount;

		public GoodsItemLabelProvider(int propertyCount) {
			this.propertyCount = propertyCount;
		}

		public String getText(Object element, int columnIndex) {
			if (element instanceof String) {
				if (columnIndexMap.get(columnIndex) == null) {
					return "";
				}
				if (columnIndexMap.get(columnIndex) == null) {
					return "";
				} else if (columnIndexMap.get(columnIndex).equals("price")) {
					try {
						return DoubleUtil.getRoundStr((Double
								.parseDouble(priceText.getText()))); // 初始销售价格
					} catch (Throwable t) {
						return "";
					}
				} else if (columnIndexMap.get(columnIndex).equals(
						"recentPurchasePrice")) {
					return "";
				} else if (columnIndexMap.get(columnIndex).equals(
						"averagePurchasePrice")) {
					return "";
				} else if (columnIndexMap.get(columnIndex).equals("status")) {
					return GoodsStatus.ON_SALE.getName(); // 初始状态
				}
				return "";
			} else if (element instanceof GoodsItemData) {
				GoodsItemData item = (GoodsItemData) element;
				if (columnIndex < propertyCount - 1) {
					try {
						return item.getPropertyValues()[columnIndex + 1];
					} catch (Throwable t) {
						return "";
					}
				} else if (columnIndex == propertyCount - 1) {
					try {
						return item.getPropertyValues()[0]; // 单位
					} catch (Throwable t) {
						return "";
					}
				} else if (columnIndexMap.get(columnIndex) == null) {
					return "";
				} else if (columnIndexMap.get(columnIndex).equals("price")) {
					if (item.getSalePrice() > 0) {
						return DoubleUtil.getRoundStr(item.getSalePrice());
					} else {
						return "";
					}
				} else if (columnIndexMap.get(columnIndex).equals("status")) {
					return item.getStatus().getName();
				}
			}
			return "";
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			if (columnIndexMap.get(columnIndex) == null) {
				return -1;
			} else if (columnIndexMap.get(columnIndex).equals("price")) {
				return 2;
			}
			return -1;
		}
	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		if (doSave() != null) {
			return true;
		} else {
			return false;
		}
	}
}
