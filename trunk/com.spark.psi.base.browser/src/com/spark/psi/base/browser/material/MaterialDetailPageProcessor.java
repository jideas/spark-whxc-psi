package com.spark.psi.base.browser.material;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.InternalLWList;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.custom.combo.LWComboTree;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.ui.wt.widgets.Widget;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.TextRegexp;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.EnumEntitySource;
import com.spark.psi.base.browser.MaterialCategorySource;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.PropertyInputType;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsItemData;
import com.spark.psi.publish.base.materials.entity.MaterialsPropertyDefine;
import com.spark.psi.publish.base.materials.task.MaterialsInfoTask;
import com.spark.psi.publish.base.materials.task.ValidationMaterialsIsExistTask;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;

public class MaterialDetailPageProcessor extends BaseFormPageProcessor {

	public static final String ID_Tree_Category = "Tree_Category";
	public static final String ID_Text_Name = "Text_Name";
	public static final String ID_Text_Number = "Text_Number";
	public static final String ID_Text_Code = "Text_Code";
	public static final String ID_Text_Spec = "Text_Spec";
	public static final String ID_List_InventoryStrategy = "List_InventoryStrategy";
	public static final String ID_Text_Unit = "Text_Unit";
	public static final String ID_Text_LossRate = "Text_LossRate";
	public static final String ID_Text_AvgBuyPrice = "Text_AvgBuyPrice";
	public static final String ID_Text_StandardPrice = "Text_StandardPrice";
	public static final String ID_Text_SalePrice = "Text_SalePrice";
	public static final String ID_Text_PlanPrice = "Text_PlanPrice";
	public static final String ID_Text_ShelfLife = "Text_ShelfLife";
	public static final String ID_Text_WarningDay = "Text_WarningDay";
	public final static String ID_Check_JointGoods = "Check_JointGoods";
	public final static String ID_Text_Supplier = "Text_Supplier";
	public final static String ID_Text_Percentage = "Text_Percentage";
	public static final String ID_Area_Property = "Area_Property";
	public static final String ID_Area_BusInfo = "Area_BusInfo";
	public static final String ID_Button_Save = "Button_Save";
	public static final String ID_Button_SaveAndNew = "Button_SaveAndNew";

	public final static Color BUSINFO_COLOR = new Color(0x8eb8dd);

	protected LWComboTree listCategory = null;
	protected Text codeText = null;
	protected Text nameText = null;
	protected Text numberText = null;
	protected Text specText = null;
	protected LWComboList strategyLsit = null;
	// protected Text unitText = null;
	protected Text lossRateText = null;
	protected Text avgPriceText = null;
	protected Text standardPriceText = null;
	protected Text salePriceText = null;
	protected Text planPriceText = null;
	protected Text shelfLifeText = null;
	protected Text warningDayText = null;
	protected Button jointCheck = null;
	protected Text supplierText = null;
	private Text percentageText = null;
	protected Text memoText = null;

	protected Composite propertyArea = null;
	// private Composite busInfoArea = null;

	private Button saveButton = null;
	private Button saveAndNewButton = null;

	private Button onSaleRadio = null;
	protected List<Control> propertyDefineControlList = new ArrayList<Control>();
	protected final static GridData gdLabel;
	protected final static GridData gdInput;
	static {

		gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.VERTICAL_ALIGN_CENTER);
		gdInput = new GridData(GridData.FILL_HORIZONTAL);
	}

	private MaterialsCategoryTree categoryTree = null;
	private LoginInfo loginInfo = null;
	private MaterialsInfo materialsInfo = null;
	private GUID supplierId = null;
	private boolean isJointOnly = false;

	// 当前选择的分类
	protected GUID selectedCategoryId = null;
	private GUID defaultCategoryId = null;
	// 设置属性的分类（可以为当前选择的分类，也可以为当前选择分类的父类）
	protected MaterialsCategoryInfo propertiedCategoryInfo;

	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
		//
		categoryTree = context.find(MaterialsCategoryTree.class);

		GUID materialId = (GUID) this.getArgument();
		if (materialId != null) {
			materialsInfo = context.find(MaterialsInfo.class, materialId);
			if (materialsInfo == null) {
				throw new IllegalArgumentException("找不到指定的材料信息");
			}
		}
		if (getArgument2() != null && getArgument2() instanceof GUID) {
			defaultCategoryId = (GUID) getArgument2();
		}
		if (getArgument3() != null) {
			isJointOnly = (Boolean) getArgument3();
		}
	}

	@Override
	public void process(Situation context) {
		listCategory = createControl(ID_Tree_Category, LWComboTree.class);
		codeText = createControl(ID_Text_Code, Text.class);
		nameText = createControl(ID_Text_Name, Text.class);
		numberText = createControl(ID_Text_Number, Text.class);
		specText = createControl(ID_Text_Spec, Text.class);
		strategyLsit = createControl(ID_List_InventoryStrategy, LWComboList.class);
		// unitText = createControl(ID_Text_Unit, Text.class);
		lossRateText = createControl(ID_Text_LossRate, Text.class);
		avgPriceText = createControl(ID_Text_AvgBuyPrice, Text.class);
		standardPriceText = createControl(ID_Text_StandardPrice, Text.class);
		salePriceText = createControl(ID_Text_SalePrice, Text.class);
		planPriceText = createControl(ID_Text_PlanPrice, Text.class);
		shelfLifeText = createControl(ID_Text_ShelfLife, Text.class);
		warningDayText = createControl(ID_Text_WarningDay, Text.class);

		propertyArea = createControl(ID_Area_Property, Composite.class);
		// busInfoArea = createControl(ID_Area_BusInfo, Composite.class);

		saveButton = this.createControl(ID_Button_Save, Button.class);
		saveAndNewButton = this.createControl(ID_Button_SaveAndNew, Button.class);

		listCategory.getTree().setSource(new MaterialCategorySource());
		listCategory.getTree().setInput(categoryTree);
		listCategory.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				resetPropertyArea();
			}
		});
		// TODO 设置默认值
		strategyLsit.getList().setSource(new EnumEntitySource(EnumType.StoreStrategy));
		strategyLsit.getList().setInput("");
		strategyLsit.setSelection("01");

		registerNotEmptyValidator(nameText, "材料名称");
		registerNotEmptyValidator(numberText, "材料条码");
		registerNotEmptyValidator(specText, "规格");
		registerNotEmptyValidator(standardPriceText, "标准价格");
		registerNotEmptyValidator(planPriceText, "计划价格");
		registerNotEmptyValidator(shelfLifeText, "保质期");
		registerNotEmptyValidator(warningDayText, "预警天数");
		registerInputValidator(new TextInputValidator(nameText, "材料名称重复，请修改。") {

			@Override
			protected boolean validateText(String text) {
				ValidationMaterialsIsExistTask goodsValidateTask = new ValidationMaterialsIsExistTask(
						materialsInfo == null ? null : materialsInfo.getId(), selectedCategoryId, text == null ? ""
								: text, "");
				getContext().handle(goodsValidateTask);
				if (ValidationMaterialsIsExistTask.ErrType.Name.equals(goodsValidateTask.getErrType())) {
					return false;
				} else {
					return true;
				}
			}
		});
		registerInputValidator(new TextInputValidator(numberText, "材料条码重复，请修改。") {

			@Override
			protected boolean validateText(String text) {
//				ValidationMaterialsIsExistTask goodsValidateTask = new ValidationMaterialsIsExistTask(
//						materialsInfo == null ? null : materialsInfo.getId(), selectedCategoryId, "", text);
//				if (null != materialsInfo) {
//					goodsValidateTask.setMaterialItemId(materialsInfo.getItems()[0].getId());
//				}
//				getContext().handle(goodsValidateTask);
//				if (ValidationMaterialsIsExistTask.ErrType.Code.equals(goodsValidateTask.getErrType())) {
//					return false;
//				} else {
//					return true;
//				}
				return true;
			}
		});
		registerInputValidator(new TextInputValidator(standardPriceText, "标准价格必须大于0") {

			@Override
			protected boolean validateText(String text) {
				if (StringUtils.isEmpty(text)) {
					return false;
				} else {
					try {
						if (DoubleUtil.strToDouble(text) <= 0) {
							return false;
						}
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
				return true;
			}
		});
		registerInputValidator(new TextInputValidator(planPriceText, "计划价格必须大于0") {

			@Override
			protected boolean validateText(String text) {
				if (StringUtils.isEmpty(text)) {
					return false;
				} else {
					try {
						if (DoubleUtil.strToDouble(text) <= 0) {
							return false;
						}
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
				return true;
			}
		});
		registerInputValidator(new TextInputValidator(salePriceText, "销售价格必须大于0") {

			@Override
			protected boolean validateText(String text) {
				if (StringUtils.isEmpty(text)) {
					return false;
				} else {
					try {
						if (DoubleUtil.strToDouble(text) <= 0) {
							return false;
						}
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
				return true;
			}
		});
		registerInputValidator(new TextInputValidator(lossRateText, "损耗率只能大于等于0且小于1") {
			
			@Override
			protected boolean validateText(String text) {
				if (StringUtils.isNotEmpty(text)) {
					double lossRate = DoubleUtil.strToDouble(text);
					if (lossRate < 0 || lossRate >= 1) {
						return false;
					}
				}
				return true;
			}
		});

		setBufInfoArea();

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

	private void save() {
		GUID id = doSave();
		if (null == id)
			return;
		super.resetDataChangedstatus();
		hint("保存成功！");
		this.materialsInfo = getContext().find(MaterialsInfo.class, id);
		initData();
	}

	private void saveAndNew() {
		GUID id = doSave();
		if (null == id)
			return;
		this.materialsInfo = null;

		setBufInfoArea();
		initData();
		resetPropertyArea();
	}

	private GUID doSave() {
		if (!validateInput()) {
			return null;
		}

		MaterialsInfoTask task = new MaterialsInfoTask();
		task.setCategoryId(selectedCategoryId);
		task.setCode(codeText.getText());
		task.setName(nameText.getText());
		if (!StringUtils.isEmpty(shelfLifeText.getText())) {
			task.setShelfLife(Integer.parseInt(shelfLifeText.getText()));
		}
		if (!StringUtils.isEmpty(warningDayText.getText())) {
			task.setWarningDay(Integer.parseInt(warningDayText.getText()));
		}
		if (task.getShelfLife() > 0 && task.getWarningDay()> 0 && 
				task.getShelfLife() <= task.getWarningDay()) {
			alert("预警天数必须小于保质期。");
			return null;
		}
		task.setRemark(memoText.getText());
		String[] values = new String[propertiedCategoryInfo.getPropertyDefines().length];
		for (int propertyIndex = 0; propertyIndex < propertiedCategoryInfo.getPropertyDefines().length; propertyIndex++) {
			String value;
			if (propertyDefineControlList.get(propertyIndex) instanceof Text) {
				Text text = (Text) propertyDefineControlList.get(propertyIndex);
				value = text.getText() == null ? "" : text.getText();
			} else {
				LWComboList lwList = (LWComboList) propertyDefineControlList.get(propertyIndex);
				value = lwList.getList().getSeleted();
			}
			values[propertyIndex] = value;
		}

		MaterialsInfoTask.Item item = new MaterialsInfoTask.Item();
		// TODO
		// item.setInventoryStrategy(strategyLsit.getList().getSeleted());
		item.setLossRate(StringUtils.isEmpty(lossRateText.getText()) ? 0.0 : DoubleUtil.strToDouble(lossRateText
				.getText(), 4));
		item.setStandardPrice(StringUtils.isEmpty(standardPriceText.getText()) ? 0.0 : DoubleUtil
				.strToDouble(standardPriceText.getText()));
		item.setSalePrice(StringUtils.isEmpty(salePriceText.getText()) ? 0.0 : DoubleUtil.strToDouble(salePriceText
				.getText()));
		item.setPlanPrice(StringUtils.isEmpty(planPriceText.getText()) ? 0.0 : DoubleUtil.strToDouble(planPriceText .getText()));
		item.setInventoryStrategy(strategyLsit.getList().getSeleted());
		
		task.setJointVenture(jointCheck.getSelection());
		if (jointCheck.getSelection()) {
			if (null == supplierId) {
				alert("请选择供应商。");
				return null;
			}
			String percentageStr = percentageText.getText();
			if (StringUtils.isEmpty(percentageStr) || DoubleUtil.strToDouble(percentageStr) <= 0
					|| DoubleUtil.strToDouble(percentageStr) > 1) {
				alert("提成比例不能为空, 且必须大于0小于1");
				return null;
			}
			task.setPercentage(DoubleUtil.strToDouble(percentageStr));
			task.setSupplierId(supplierId);
		}
		if (materialsInfo == null || materialsInfo.getItems().length < 1) {
			item.setId(GUID.randomID());
			item.setPropertyValues(values);
			item.setMaterialsSpec(specText.getText());
			item.setMaterialsNo(numberText.getText());
			item.setUnit(values[0]);
		} else if (materialsInfo.getItems().length > 0) {
			item.setId(materialsInfo.getItems()[0].getId());
			item.setMaterialsNo(numberText.getText());
			if (materialsInfo.isRefFlag()) { // 已引用 属性值 和规格值 不能修改
//				item.setPropertyValues(materialsInfo.getItems()[0].getPropertyValues());
				item.setPropertyValues(values);
				item.setUnit(values[0]);
				item.setMaterialsSpec(materialsInfo.getItems()[0].getMaterialSpec());
				item.setMaterialsNo(materialsInfo.getItems()[0].getMaterialNo());
//				item.setUnit(materialsInfo.getItems()[0].getUnit());
			} else {
				item.setPropertyValues(values);
				item.setUnit(values[0]);
				item.setMaterialsSpec(specText.getText());
				item.setMaterialsNo(numberText.getText());
			}
		}
		if (onSaleRadio != null) {
			item.setOnsale(onSaleRadio.getSelection());
		} else {
			item.setOnsale(true);
		}

		ValidationMaterialsIsExistTask goodsValidateTask = new ValidationMaterialsIsExistTask(
				materialsInfo == null ? null : materialsInfo.getId(), selectedCategoryId, "", item.getMaterialsNo());
		if (null != materialsInfo) {
			goodsValidateTask.setMaterialItemId(materialsInfo.getItems()[0].getId());
			goodsValidateTask.setSpec(item.getMaterialsSpec());
		}
		getContext().handle(goodsValidateTask);
		if (ValidationMaterialsIsExistTask.ErrType.SPECANDNUMBER.equals(goodsValidateTask.getErrType())) {
			alert("材料条码和规格不能同时重复。");
			return null;
		}
		List<MaterialsInfoTask.Item> itemList = new ArrayList<MaterialsInfoTask.Item>();
		if (materialsInfo != null) {
			// 增加删除的item
			List<GUID> deleteIds = new ArrayList<GUID>();
			for (MaterialsItemData itemData : materialsInfo.getItems()) {
				if (!item.getId().equals(itemData.getId())) {
					deleteIds.add(item.getId());
					MaterialsInfoTask.Item delItem = new MaterialsInfoTask.Item();
					delItem.setId(itemData.getId());
					delItem.setMethod(MaterialsInfoTask.ItemMethod.Delete);
					itemList.add(delItem);
				}
			}
		}
		itemList.add(item);
		task.setItems(itemList.toArray(new MaterialsInfoTask.Item[itemList.size()]));
		if (materialsInfo != null) {
			task.setId(materialsInfo.getId());
			getContext().handle(task, MaterialsInfoTask.Method.Update);
		} else {
			getContext().handle(task, MaterialsInfoTask.Method.Create);
		}
		getContext().bubbleMessage(new MsgResponse(false));
		return task.getId();
	}

	private void initData() {
		Label codeTitle = (Label) codeText.getPrev();

		// 只能查看
		codeText.setEnabled(false);
		avgPriceText.setEnabled(false);

		if (materialsInfo == null) {
			codeText.setVisible(false);
			codeTitle.setVisible(false);
			listCategory.getTree().setExpand(categoryTree, true);
			listCategory.setEditable(false);
			listCategory.setButtonVisible(true);
			listCategory.setSelection(defaultCategoryId == null ? null : defaultCategoryId.toString());

			codeText.setText("");
			nameText.setText("");
			numberText.setText("");
			specText.setText("");
			strategyLsit.setSelection("01");
			// TODO
			// strategyLsit.setText();
			// unitText.setText("");
			lossRateText.setText("0.00");
			avgPriceText.setText("");
			standardPriceText.setText("");
			salePriceText.setText("");
			planPriceText.setText("");
			shelfLifeText.setText("");
			warningDayText.setText("");
			// memoText.setText("");
		} else {
			supplierId = materialsInfo.getSupplierId();
			codeText.setVisible(true);
			codeTitle.setVisible(true);
			// 修改商品
			listCategory.setSelection(categoryTree.getNodeById(materialsInfo.getCategory().getId()));
			listCategory.setEditable(false);
			listCategory.setButtonVisible(false);
			//
			codeText.setText(materialsInfo.getCode());
			nameText.setText(materialsInfo.getName());
			numberText.setText(materialsInfo.getItems()[0].getMaterialNo());
			specText.setText(materialsInfo.getItems()[0].getMaterialSpec());
			strategyLsit.setSelection(materialsInfo.getItems()[0].getInventoryStrategy());
			// TODO
			// strategyLsit.setText();
			// unitText.setText(materialsInfo.getItems()[0].getUnit());
			lossRateText.setText(DoubleUtil.getRoundStr(materialsInfo.getItems()[0].getLossRate(), 4));
			avgPriceText.setText(DoubleUtil.getRoundStr(materialsInfo.getItems()[0].getAvgBuyPrice()));
			standardPriceText.setText(DoubleUtil.getRoundStr(materialsInfo.getItems()[0].getStandardPrice()));
			salePriceText.setText(DoubleUtil.getRoundStr(materialsInfo.getItems()[0].getSalePrice()));
			planPriceText.setText(DoubleUtil.getRoundStr(materialsInfo.getItems()[0].getPlanPrice()));
			shelfLifeText.setText(materialsInfo.getShelfLife() + "");
			warningDayText.setText(materialsInfo.getWarningDay() + "");
			// memoText.setText(materialsInfo.getRemark());
			// jointCheck.setSelection(isJointOnly);
			// if (isJointOnly) {
			// jointCheck.setEnabled(false);
			// }
		}

		if (!loginInfo.hasAuth(Auth.SubFunction_MaterialManage_UpdateMaterial)
				&& !loginInfo.hasAuth(Auth.SubFunction_MaterialManage_ChangeMaterialStatus)) {
			saveButton.setVisible(false);
			saveAndNewButton.setVisible(false);
			for (Control ctrl : propertyDefineControlList) {
				ctrl.setEnabled(false);
			}
			if (null != memoText) {
				memoText.setEnabled(false);
			}
			if (null != nameText) {
				nameText.setEnabled(false);
			}
			if (null != codeText) {
				codeText.setEnabled(false);
			}
		}
	}

	private void setBufInfoArea() {
		// if(materialsInfo == null) {
		// busInfoArea.dispose();
		// }
		// else {
		// Label lastLabel = null;
		//			
		// GridData gdSep = new GridData();
		// gdSep.widthHint = 1;
		// gdSep.heightHint = 10;
		// if (loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ShowInventoryInfo))
		// {
		// final Label label = new Label(busInfoArea);
		// label.setText("  库存信息");
		// label.setForeground(BUSINFO_COLOR);
		// PageController pcInventory = new
		// PageController(GoodsInventoryInfoProcessor.class,
		// GoodsInventoryInfoRender.class);
		// PageControllerInstance pciInventory = new
		// PageControllerInstance(pcInventory, materialsInfo.getId(), true);
		// showPopWindow(label, pciInventory);
		// lastLabel = label;
		// }
		// if(loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ShowPurchaseInfo)) {
		// if(lastLabel != null) {
		// lastLabel.setText(lastLabel.getText() + "  ");
		// Composite sep1 = new Composite(busInfoArea);
		// sep1.setLayoutData(gdSep);
		// sep1.setBackground(BUSINFO_COLOR);
		// }
		//				
		// final Label label1 = new Label(busInfoArea);
		// label1.setText("  采购情况");
		// label1.setForeground(BUSINFO_COLOR);
		// PageController pcPurchase = new
		// PageController(GoodsPurchaseSummaryProcessor.class,
		// GoodsPurchaseSummaryRender.class);
		// PageControllerInstance pciPurchase = new
		// PageControllerInstance(pcPurchase, materialsInfo.getId(), true);
		// showPopWindow(label1, pciPurchase);
		// lastLabel = label1;
		// }
		// if(loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ShowSaleInfo)) {
		// if(lastLabel != null) {
		// lastLabel.setText(lastLabel.getText() + "  ");
		// Composite sep2 = new Composite(busInfoArea);
		// sep2.setLayoutData(gdSep);
		// sep2.setBackground(BUSINFO_COLOR);
		// }
		//				
		// final Label label2 = new Label(busInfoArea);
		// label2.setForeground(BUSINFO_COLOR);
		// label2.setText("  销售情况");
		// PageController pcSale = new
		// PageController(GoodsSalesSummaryProcessor.class,
		// GoodsSalesSummaryRender.class);
		// PageControllerInstance pciSale = new PageControllerInstance(pcSale,
		// materialsInfo.getId(), true);
		// showPopWindow(label2, pciSale);
		// lastLabel = label2;
		// }
		// }
	}

	// private void showPopWindow(final Control owner, final
	// PageControllerInstance pci) {
	// SMenuWindow menuWindow = new SMenuWindow(null, owner,Direction.Down);
	// menuWindow.bindTargetControl(owner);
	// menuWindow.addClientEventHandler(JWT.CLIENT_EVENT_VISIBLE_SHOW,"PSIBase.GoodsSelect.onSelectedGoodsWindowShow");
	// Composite windowArea = menuWindow.getContentArea();
	// windowArea.setLayout(new GridLayout());
	// final ScrolledPanel area = new ScrolledPanel(windowArea);
	// GridData gd = new GridData();
	// gd.widthHint = 750;
	// gd.heightHint = 360;
	// area.setLayoutData(gd);
	// menuWindow.addClientNotifyListener(new ClientNotifyListener() {
	// public void notified(ClientNotifyEvent e) {
	// area.getComposite().showPage(ControllerPage.NAME, pci);
	// }
	// });
	// }

	protected void resetPropertyArea() {

		if (!setBaseInfo()) {
			return;
		}

		MaterialsPropertyDefine[] propertyDefines = propertiedCategoryInfo.getPropertyDefines();
		MaterialsItemData[] items = materialsInfo == null ? null : materialsInfo.getItems();

		for (int propertyIndex = 0; propertyIndex < propertyDefines.length; propertyIndex++) {
			final MaterialsPropertyDefine propertyDefine = propertyDefines[propertyIndex];
			if (0 == propertyIndex) {
				SAsteriskLabel label = new SAsteriskLabel(propertyArea, propertyDefine.getName() + "：");
				label.setLayoutData(gdLabel);
			} else {
				Label label = new Label(propertyArea);
				label.setText(propertyDefine.getName() + "：");
				label.setLayoutData(gdLabel);
			}
			if (propertyDefine.getValueInputMode() == PropertyInputType.INPUT) {
				Text text = new Text(propertyArea, JWT.APPEARANCE3);
				text.setLayoutData(gdInput);
				if (items != null && items.length > 0 && items[0].getPropertyValues().length > propertyIndex) {
					if (items[0] != null) {
						text.setText(items[0].getPropertyValues()[propertyIndex] == null ? "" : items[0]
								.getPropertyValues()[propertyIndex]);
						if (items[0].isRefFlag()) {
							text.setEnabled(false);
						}
					}
				}
				if (propertyIndex == 0) {
					registerNotEmptyValidator(text, propertyDefine.getName());
				}
				propertyDefineControlList.add(text);
			} else {
				LWComboList list = new LWComboList(propertyArea, JWT.APPEARANCE3);
				list.setLayoutData(gdInput);
				InternalLWList inList = list.getList();
				inList.setSource(new ListSourceAdapter() {

					public String getElementId(Object element) {
						return element.toString();
					}

					public Object getElementById(String id) {
						return id;
					}

					public String getText(Object element) {
						return element.toString();
					}

					public Object[] getElements(Object inputElement) {
						return propertyDefine.getOptions();
					}
				});
				inList.setInput("");
				if (items != null && items.length > 0 && items[0].getPropertyValues().length > propertyIndex) {
					list.setSelection(items[0] == null ? "" : items[0].getPropertyValues()[propertyIndex]);
					if (items[0].isRefFlag()&&propertyIndex != 0)
						list.setEnabled(false);
				}
				propertyDefineControlList.add(list);
				if (propertyIndex == 0) {
					registerNotEmptyValidator(list, propertyDefine.getName());
				}
			}
		}

		int elementCountAfterProperty = 0;
		if (loginInfo.hasAuth(Auth.SubFunction_MaterialManage_ChangeMaterialStatus)) { // 只有总经理和销售人员可以修改商品销售状态
			Label statusLabel = new Label(propertyArea);
			statusLabel.setText("销售状态：");
			statusLabel.setLayoutData(gdLabel);
			Composite radionCmp = new Composite(propertyArea);
			radionCmp.setLayoutData(gdInput);
			radionCmp.setLayout(new GridLayout(2));
			onSaleRadio = new Button(radionCmp, JWT.RADIO);
			onSaleRadio.setText("在售");
			Button offSaleRadio = new Button(radionCmp, JWT.RADIO);
			offSaleRadio.setText("停售");
			if (items != null && items.length > 0
					&& materialsInfo.getItems()[0].getStatus() == MaterialsStatus.STOP_SALE) {
				offSaleRadio.setSelection(true);
			} else {
				onSaleRadio.setSelection(true);
			}

			elementCountAfterProperty += 1;
		}

		Label jointLabel = new Label(propertyArea);
		jointLabel.setText("联营商品：");
		jointLabel.setLayoutData(gdLabel);
		jointCheck = new Button(propertyArea, JWT.CHECK);
		jointCheck.setLayoutData(gdInput);
		elementCountAfterProperty += 1;

//		Label supplierLabel = new Label(propertyArea);
		SAsteriskLabel supplierLabel = new SAsteriskLabel(propertyArea, "供 应 商：");
//		supplierLabel.setText("供 应 商：");
		supplierLabel.setLayoutData(gdLabel);
		supplierText = new Text(propertyArea, JWT.BUTTON | JWT.APPEARANCE3);
		supplierText.setLayoutData(gdInput);
		supplierText.setButtonVisible(true);
		elementCountAfterProperty += 1;

		SAsteriskLabel percentageLabel = new SAsteriskLabel(propertyArea, "提成比例：");
		percentageLabel.setLayoutData(gdLabel);
		percentageText = new Text(propertyArea, JWT.APPEARANCE3);
		percentageText.setLayoutData(gdInput);
		percentageText.setRegExp(TextRegexp.NUMBER_ONE_FOUR);
		percentageText.setText("0.00");
//		percentageText.setRegExp("(^[0]?(\\.)(\\d)?(\\d)?(\\d)?(\\d)?$)");
		elementCountAfterProperty += 1;

		int preElementCount = 13 + propertyDefines.length + elementCountAfterProperty;
		if (preElementCount % 2 > 0) {
			Label tempLabel = new Label(propertyArea);
			tempLabel.setLayoutData(gdLabel);
			tempLabel.setBorder(CBorder.BORDER_NORMAL);
			tempLabel.setVisible(false);
			Text tempText = new Text(propertyArea);
			tempText.setLayoutData(gdInput);
			tempText.setVisible(false);
		}

		Label memoLabel = new Label(propertyArea);
		memoLabel.setText("备注：");
		memoLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END));
		memoText = new Text(propertyArea, JWT.APPEARANCE3);
		GridData gdMemo = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gdMemo.heightHint = 50;
		gdMemo.horizontalSpan = 3;
		memoText.setLayoutData(gdMemo);
		if (materialsInfo != null) {
			memoText.setText(materialsInfo.getRemark());
		}
		addCheckJointAction(jointCheck);
		addSupplierSelectionListner(supplierText);
		if (isJointOnly) {
			jointCheck.setSelection(true);
		}
		if (materialsInfo != null) {
			jointCheck.setSelection(materialsInfo.isJointVenture());
			supplierLabel.setVisible(materialsInfo.isJointVenture());
			supplierText.setVisible(materialsInfo.isJointVenture());
			supplierText.setText(materialsInfo.getSupplier());

			percentageLabel.setVisible(materialsInfo.isJointVenture());
			percentageText.setVisible(materialsInfo.isJointVenture());
			percentageText.setText(DoubleUtil.getRoundStr(materialsInfo.getPercentage(), 4));
		} else {
			supplierLabel.setVisible(isJointOnly);
			supplierText.setVisible(isJointOnly);

			percentageLabel.setVisible(isJointOnly);
			percentageText.setVisible(isJointOnly);
		}

		if (!loginInfo.hasAuth(Auth.SubFunction_MaterialManage_UpdateMaterial)) {
			jointCheck.setEnabled(false);
		}
//		if (materialsInfo != null && materialsInfo.getItems().length > 0 && loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ShowPurchaseAmount)) {
//			new Label(propertyArea).setLayoutData(gdLabel);
//			Label infoLabel = new Label(propertyArea);
//			infoLabel.setText("最近采购价格："
//					+ DoubleUtil.getRoundStr(materialsInfo.getItems()[0]
//							.getRecentPurchasePrice())
//					+ "    平均库存成本："
//					+ DoubleUtil.getRoundStr(materialsInfo.getItems()[0]
//							.getAvgBuyPrice()));
//			GridData gdInfo = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING);
////			gdInfo.verticalIndent = -15;
//			gdInfo.horizontalSpan = 3;
//			infoLabel.setLayoutData(gdInfo);
//		}
		// if (materialsInfo != null && materialsInfo.getItems().length > 0 &&
		// loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ShowPurchaseAmount)) {
		// new Label(propertyArea).setLayoutData(gdLabel);
		// Label infoLabel = new Label(propertyArea);
		// infoLabel.setText("最近采购价格："
		// + DoubleUtil.getRoundStr(materialsInfo.getItems()[0]
		// .getRecentPurchasePrice())
		// + "    平均库存成本："
		// + DoubleUtil.getRoundStr(materialsInfo.getItems()[0]
		// .getAvgBuyPrice()));
		// GridData gdInfo = new GridData(GridData.FILL_HORIZONTAL |
		// GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING);
		// // gdInfo.verticalIndent = -15;
		// gdInfo.horizontalSpan = 3;
		// infoLabel.setLayoutData(gdInfo);
		// }

		saveButton.setEnabled(true);
		saveAndNewButton.setEnabled(true);
		propertyArea.layout();
	}

	private void addCheckJointAction(final Button check) {
		check.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 点击联营
				if (check.getSelection()) {
					setSupplierHide(false);
				} else {
					setSupplierHide(true);
				}
			}
		});
	}

	private void addSupplierSelectionListner(final Text text) {
		text.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 选择供应商
				MsgRequest request = CommonSelectRequest.createSelectSupplierRequest(materialsInfo == null ? null
						: materialsInfo.getSupplierId(), true);
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						supplierId = (GUID) returnValue;
						if (supplierId != null) {
							SupplierInfo supplierInfo = getContext().find(SupplierInfo.class, supplierId);
							supplierText.setText(supplierInfo == null ? "" : supplierInfo.getName());
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	private void setSupplierHide(boolean isHide) {
		SAsteriskLabel supplierTitle = (SAsteriskLabel) supplierText.getPrev();
		supplierText.setVisible(!isHide);
		supplierTitle.setVisible(!isHide);

		SAsteriskLabel percentageTitle = (SAsteriskLabel) percentageText.getPrev();
		percentageText.setVisible(!isHide);
		percentageTitle.setVisible(!isHide);
		
		if (isHide) {
			supplierText.setText(null);
			percentageText.setText("0.0000");
		}
	}

	protected boolean setBaseInfo() {
		propertiedCategoryInfo = null;
		selectedCategoryId = GUID.tryValueOf(listCategory.getText());
		if (selectedCategoryId != null) {
			MaterialsCategoryTree.CategoryNode node = categoryTree.getNodeById(selectedCategoryId);
			while (node != null && !node.isSetPropertyFlag()) {
				node = node.getParent();
			}
			if (node != null) {
				propertiedCategoryInfo = getContext().find(MaterialsCategoryInfo.class, node.getId());
			}
		}
		//
		resetArea();
		if (selectedCategoryId != null && this.categoryTree.getNodeById(selectedCategoryId).getChildren().length > 0) {
			propertiedCategoryInfo = null;
			if (selectedCategoryId != null) {
				alert("必须选择最末一级节点！");
			}
			disableButtons();
			Label noticeLabel = new Label(propertyArea);
			noticeLabel.setText("必须选择最末一级节点");
			// noticeLabel.setBackground(new Color(0xd6e8f4));
			GridData gdLabel = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_CENTER);
			gdLabel.horizontalSpan = 4;
			noticeLabel.setLayoutData(gdLabel);
			propertyArea.layout();
			return false;
		}
		if (propertiedCategoryInfo == null) {
			if (selectedCategoryId != null) {
				alert("请首先为该分类设置属性，再新建材料！");
			}
			disableButtons();
			Label noticeLabel = new Label(propertyArea);
			noticeLabel.setText("分类未设置属性");
			// noticeLabel.setBackground(new Color(0xd6e8f4));
			GridData gdLabel = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_CENTER);
			gdLabel.horizontalSpan = 4;
			noticeLabel.setLayoutData(gdLabel);
			propertyArea.layout();
			return false;
		}
		return true;
	}

	protected void disableButtons() {
		saveButton.setEnabled(false);
		saveAndNewButton.setEnabled(false);
	}

	protected void resetArea() {
		List<Widget> notDefaultFormWidgets = new ArrayList<Widget>();
		Widget nextWiget = warningDayText.getNext();
		while (nextWiget != null) {
			notDefaultFormWidgets.add(nextWiget);
			nextWiget = nextWiget.getNext();
		}
		for (Widget widget : notDefaultFormWidgets) {
			widget.dispose();
		}
		propertyArea.layout();
		propertyDefineControlList.clear();
	}
}

class StrategySource extends ListSourceAdapter {

	public Object[] getElements(Object inputElement) {
		
		return null;
	}

	public String getText(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getElementById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getElementId(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}