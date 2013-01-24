package com.spark.psi.base.browser.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.GoodsCommon;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.base.browser.start.SaveGoodsCategoryInfoMessage;
import com.spark.psi.publish.PropertyInputType;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryInfo;
import com.spark.psi.publish.base.goods.entity.PropertyDefine;
import com.spark.psi.publish.base.goods.task.UpdateGoodsCategoryTask;

public class GoodsCategoryDetailProcessor extends PageProcessor implements IDataProcessPrompt{

	public final static String ID_Label_CategoryName = "CategoryName";
	public final static String ID_ComboList_Decimal = "Decimal";
	public final static String ID_Label_CategoryCode = "Label_CategoryCode";
	public final static String ID_Table_Properties = "CategoryProperties";
	public final static String ID_Table_Options = "PropertyOptions";
	public final static String ID_Composite_Arrow = "Composite_Arrow";
	public final static String ID_Button_Save = "SaveButton";
	public final static SEditTableStyle tableStyle;

	public final static int COUNT_SCALE = 2;
	
	static {
		tableStyle = new SEditTableStyle();
		tableStyle.setSelectionMode(SSelectionMode.Row);
		tableStyle.setAutoAddRow(true);
	}

	private Label categoryNameLabel;
//	private ComboList decimalList;
	private Label categoryCodeLabel;
	private SEditTable propertiesTable;
	private SEditTable optionsTable;
	private Button saveButton;
	private Composite arrowCmp;

	final static String[] DEFAULT_UNITS = new String[] { "份", "只", "个",
			"公斤", "斤", "箱", "瓶", "包" };

	private String lastSelectedPropertyRowId;
	// 临时存储界面上的属性定义值
	private final Map<String, PropertyDefine> ui_propertyDefines = new HashMap<String, PropertyDefine>();
	private final List<String> ui_propertyIds = new ArrayList<String>();
	
	// 整个表格区域是否可以编辑
	private boolean isEditable = true;
	// 在整个表格不可编辑的状态，未保存的属性可选项id（在不可编辑时，属性可选项值任可新增）。保存后要清空该值
	private final List<String> unSavedNewOptionId_UnEditable = new ArrayList<String>();

	public static enum PropertyColumns {
		name,
		// type
	}

	public static enum OptionColumns {
		item
	}

	public static enum PropertyActions {
		// up, down, 
		remove
	}

	public static enum OptionActions {
		// up, down, 
		remove
	}
	private GoodsCategoryInfo categoryInfo;
	private GoodsCategoryInfo propertiedParentNode = null;
	
	
	
	@Override
	public void init(Situation context) {
		super.init(context);
		GUID[] argument = (GUID[]) this.getArgument();
		final GUID categoryId = argument[0];
		categoryInfo = context.find(
				GoodsCategoryInfo.class, categoryId);

		final GUID propertiedParentNodeId = argument[1] == null ? null
				: argument[1];
		if (null != propertiedParentNodeId) {
			propertiedParentNode = context.find(GoodsCategoryInfo.class,
					propertiedParentNodeId);
		}
	}
	@Override
	public void process(Situation context) {
		categoryNameLabel = createControl(ID_Label_CategoryName, Label.class);
//		decimalList = createControl(ID_ComboList_Decimal, ComboList.class);
		categoryCodeLabel = createControl(ID_Label_CategoryCode,
				Label.class);
		propertiesTable = createControl(ID_Table_Properties, SEditTable.class);
		optionsTable = createControl(ID_Table_Options, SEditTable.class);
		saveButton = createControl(ID_Button_Save, Button.class);
		arrowCmp = createControl(ID_Composite_Arrow, Composite.class);

		initDataShow();
		
		//
		if(saveButton != null){
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(doSave()) {
						hint("保存成功！");
						getContext().bubbleMessage(
								new GoodsCategorySelectionMsg(categoryInfo.getId()));
					}
				}
			});
		}
		
		context.regMessageListener(GoodsCategorySelectionMsg.class, new MessageListener<GoodsCategorySelectionMsg>() {

			public void onMessage(Situation context,
					GoodsCategorySelectionMsg message,
					MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
				categoryInfo = context.find(
						GoodsCategoryInfo.class, categoryInfo.getId());
				categoryNameLabel.setText(categoryInfo.getName());
			}
		});
		
		/**用于配置向导保存数据*/
		context.regMessageListener(SaveGoodsCategoryInfoMessage.class, new MessageListener<SaveGoodsCategoryInfoMessage>(){

			public void onMessage(Situation context, SaveGoodsCategoryInfoMessage message,
                    MessageTransmitter<SaveGoodsCategoryInfoMessage> transmitter)
            {
				boolean saveResult = doSave();
	            message.getResponseHandler().handle(saveResult, null, null, null);
	            getContext().bubbleMessage(
						new GoodsCategorySelectionMsg(categoryInfo.getId()));
            }
		});
	}
	
	private void initDataShow() {
		initBaseDataShow();
		initPropertyDataShow();
	}
	
	private void initBaseDataShow() {
		//
		categoryNameLabel.setText(categoryInfo.getName());
		categoryCodeLabel.setText(categoryInfo.getCategoryNo());
		
		//
//		decimalList.addItem("0");
//		decimalList.addItem("1");
//		decimalList.addItem("2");
//		decimalList.addItem("3");
//		decimalList.addItem("4");
////		decimalList.setSelection(propertiedParentNode == null ? categoryInfo.getScale() : propertiedParentNode.getScale());
//		decimalList.setSelection("2");
		
//		if (propertiedParentNode != null && !propertiedParentNode.getId().equals(categoryInfo.getId())) {
//			decimalList.setEnabled(false);
//			decimalList.setButtonVisible(false);
//		}
	}
	
	private void initPropertyDataShow() {
		//
		PropertyDefine[] propertyDefines;
		if (propertiedParentNode != null) {
			propertyDefines = propertiedParentNode.getPropertyDefines();
		} else {
			propertyDefines = categoryInfo.getPropertyDefines();
		}
		if (propertyDefines == null || propertyDefines.length == 0) {
			PropertyDefineItem unitItem = new PropertyDefineItem(GUID.randomID(),
					"单位", GoodsCategoryDetailProcessor.DEFAULT_UNITS);
//			PropertyDefineItem specItem = new PropertyDefineItem(GUID.randomID(),
//					"规格", null);
			propertyDefines = new PropertyDefine[] { unitItem }; // TOOD:单位枚举
			// 默认选择单位（第一行）
			lastSelectedPropertyRowId = unitItem.getId().toString(); 
		}
		for (PropertyDefine pd : propertyDefines) {
			if (pd.getId() == null) {
				String pdId = GUID.randomID().toString();
				ui_propertyDefines.put(pdId, pd);
				ui_propertyIds.add(pdId);
			} else {
				ui_propertyDefines.put(pd.getId().toString(), pd);
				ui_propertyIds.add(pd.getId().toString());
			}
		}

		//
		if (propertiedParentNode != null
				|| GoodsCommon.isCategoryContainGoods(getContext(), categoryInfo.getId())) {//父类设置了属性或该分类下已关联商品时，属性不可编辑
			isEditable = false;
		}
		propertiesTable.setContentProvider(new PropertyContentProvider(
				propertyDefines, isEditable));
		propertiesTable.render();
		propertiesTable.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				peroptySelectionAction();
			}
		});
		propertiesTable.addActionListener(new SActionListener() {

			public void actionPerformed(final String rowId, String actionName,
					String actionValue) {
				doPropertyTableAction(rowId, actionName, actionValue);
			}
		});
		if (propertiesTable.getAllRowId() != null && propertiesTable.getAllRowId().length > 0) {
			lastSelectedPropertyRowId = propertiesTable.getAllRowId()[0];
		} else {
			lastSelectedPropertyRowId = null;
		}
		
		optionsTable.setContentProvider(new OptionEditContentProvider(getSelectedPdOptionValues(), isEditable));
		optionsTable.render();
		optionsTable.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				optionSelectionAction();
			}
		});
		optionsTable.addActionListener(new SActionListener() {

			public void actionPerformed(String rowId, String actionName,
					String actionValue) {
				doOptionTableAction(rowId, actionName, actionValue);
			}
		});
	}
	
	private void peroptySelectionAction() {
		changeArrowPosition();
		// 保存上一个属性的可选项（保存至内存）
		if (isEditable) {
			ui_savePropertyDefine();
		}
		optionsTable.setContentProvider(new OptionEditContentProvider(getSelectedPdOptionValues(), isEditable));
		optionsTable.render();
		lastSelectedPropertyRowId = propertiesTable.getSelection() == null ? lastSelectedPropertyRowId
				: propertiesTable.getSelection();
		
		if(isEditable && propertiesTable.getSelection() != null) {
			if(propertiesTable.getSelection().equals(propertiesTable.getAllRowId()[0])
					&& propertiesTable.getAllRowId().length < 2) {
				propertiesTable.addRow(new PropertyDefineItem(GUID.randomID(), "", null));
				propertiesTable.renderUpate();
			}
		}
	}
	
	private void optionSelectionAction() {
		if (isEditable) {
			return;
		}
		String[] rowIds = optionsTable.getAllRowId();
		String selectedRowId = optionsTable.getSelection();
		String lastRowId = rowIds[rowIds.length - 1];
		// 最后一行是新增的，则不用手动新增行了，表格会自动新增
		if (unSavedNewOptionId_UnEditable.contains(lastRowId)) {
			return;
		}
		if (lastRowId.equals(selectedRowId) && rowIds.length < 12) {
			GUID newElementGuid = GUID.randomID();
			unSavedNewOptionId_UnEditable.add(newElementGuid.toString());
			optionsTable.addRow(new SNameValue(newElementGuid.toString(), ""));
			List<String> actionList = new ArrayList<String>();
//			if (rowIds.length > 2) {
//				actionList.add(PropertyActions.up.name());
//			}
//			actionList.add(PropertyActions.down.name());
			if (isEditable) {
				actionList.add(PropertyActions.remove.name());
			}
			optionsTable.updateRowActions(lastRowId, actionList.toArray(new String[actionList.size()]));
			
			optionsTable.renderUpate();
		}
	}
	
	private void doPropertyTableAction(final String rowId, String actionName,
			String actionValue) {
		if (PropertyActions.remove.name().equals(actionName)) {
			confirm("确认是否删除所选商品属性？", new Runnable() {

				public void run() {
					String[] rowIds = propertiesTable.getAllRowId();
					String lastRowId = rowIds[rowIds.length - 1];
					if (rowId.equals(lastRowId) && propertiesTable.getAllRowId().length > 1) {
						List<String> actionList = new ArrayList<String>();
						if (rowIds.length > 2) { //第一行（单位）不能加任何操作
							// actionList.add(OptionActions.up.name());
							if (isEditable) {
								actionList.add(OptionActions.remove.name());
							}
						}
						propertiesTable.updateRowActions(rowIds[rowIds.length - 2], actionList.toArray(new String[actionList.size()]));
					}
					propertiesTable.removeRow(rowId);
					ui_propertyDefines.remove(rowId);
					ui_propertyIds.remove(rowId);
					
					propertiesTable.renderUpate();
				}
			});
		} else {
			// 取得表格里每一行的数据
			String[] rowIds = propertiesTable.getAllRowId();
			List<PropertyDefine> allPropertyElmentList = new ArrayList<PropertyDefine>();
//			int actionRowIndex = 0;
			for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
//				if (rowIds[rowIndex].equals(rowId)) {
//					actionRowIndex = rowIndex;
//				}
				allPropertyElmentList.add(ui_propertyDefines.get(rowIds[rowIndex]));
			}
			// 交换数据位置
			//单位不能移动所以allPropertyElments长度要比表格行数少1
			PropertyDefine[] allPropertyElments = allPropertyElmentList.toArray(new PropertyDefine[allPropertyElmentList.size()]);
//			if (PropertyActions.up.name().equals(actionName)) {
//				if (actionRowIndex <= 1) {
//					alert("“单位”必须在一行，不能在移动了。");
//				} else {
//					PropertyDefine tempElemnt = allPropertyElments[actionRowIndex];
//					PropertyDefine preElemnt = allPropertyElments[actionRowIndex - 1];
//					allPropertyElments[actionRowIndex] = preElemnt;
//					allPropertyElments[actionRowIndex - 1] = tempElemnt;
//					
//					ui_propertyIds.set(actionRowIndex, preElemnt.getId().toString());
//					ui_propertyIds.set(actionRowIndex - 1, tempElemnt.getId().toString());
//				}
//			} else if (PropertyActions.down.name().equals(actionName)) {
//				if (actionRowIndex < rowIds.length - 1) {
//					PropertyDefine tempElemnt = allPropertyElments[actionRowIndex];
//					PropertyDefine nextElemnt = allPropertyElments[actionRowIndex + 1];
//					allPropertyElments[actionRowIndex] = nextElemnt;
//					allPropertyElments[actionRowIndex + 1] = tempElemnt;
//					
//					ui_propertyIds.set(actionRowIndex, nextElemnt.getId().toString());
//					ui_propertyIds.set(actionRowIndex + 1, tempElemnt.getId().toString());
//				} else {
//					alert("已经是最后一行了！");
//				}
//			}
			// 重新显示
			propertiesTable.setContentProvider(new PropertyContentProvider(
					allPropertyElments, isEditable));
			propertiesTable.render();
		}
	}
	
	
	private void doOptionTableAction(final String rowId, String actionName,
			String actionValue) {
		if (OptionActions.remove.name().equals(actionName)) {
			String[] rowIds = optionsTable.getAllRowId();
			String lastRowId = rowIds[rowIds.length - 1];
			if (rowId.equals(lastRowId) && rowIds.length > 1) {
				List<String> actionList = new ArrayList<String>();
//				if (rowIds.length > 2) {
//					actionList.add(OptionActions.up.name());
//				}
				if (isEditable) {
					actionList.add(OptionActions.remove.name());
				}
				optionsTable.updateRowActions(rowIds[rowIds.length - 2], actionList.toArray(new String[actionList.size()]));
			}
			
			optionsTable.removeRow(rowId);
			optionsTable.renderUpate();
			return;
		}
		// 取得表格里每一行的数据
		String[] rowIds = optionsTable.getAllRowId();
		List<SNameValue> allOptionElmentList = new ArrayList<SNameValue>();
//		int actionRowIndex = 0;
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			String value = optionsTable.getExtraData(rowIds[rowIndex], rowIds[rowIndex])[0];
			if (StringHelper.isEmpty(value)) {
				value = optionsTable.getEditValue(rowIds[rowIndex], OptionColumns.item.name())[0];
			}
			SNameValue obj = new SNameValue(rowIds[rowIndex], value);
			allOptionElmentList.add(obj);
//			if (rowIds[rowIndex].equals(rowId)) {
//				actionRowIndex = rowIndex;
//			}
		}
		// 交换数据的位置
		SNameValue[] allOptionElments = allOptionElmentList.toArray(new SNameValue[allOptionElmentList.size()]);
//		if (OptionActions.down.name().equals(actionName)) {
//			if (actionRowIndex < rowIds.length - 1) {
//				SNameValue tempElemnt = allOptionElments[actionRowIndex];
//				SNameValue nextElemnt = allOptionElments[actionRowIndex + 1];
//				allOptionElments[actionRowIndex] = nextElemnt;
//				allOptionElments[actionRowIndex + 1] = tempElemnt;
//			} else {
//				alert("已经是最后一行了！");
//				return;
//			}
//		} else if (OptionActions.up.name().equals(actionName)) {
//			if (actionRowIndex == 0) {
//				alert("已经是第一行了！");
//				return;
//			} else {
//				SNameValue tempElemnt = allOptionElments[actionRowIndex];
//				SNameValue preElemnt = allOptionElments[actionRowIndex - 1];
//				allOptionElments[actionRowIndex] = preElemnt;
//				allOptionElments[actionRowIndex - 1] = tempElemnt;
//			}
//		} 
		// 重新显示数据
		optionsTable.setContentProvider(new OptionEditContentProvider(allOptionElments, isEditable));
		optionsTable.render();
	}
	
	/**
	 * 取得当前选择属性的可选项值，如果当前没有选中的，则返回第一个属性的选项值
	 * @return
	 */
	protected SNameValue[] getSelectedPdOptionValues() {
		if (propertiesTable == null || propertiesTable.getAllRowId() == null || propertiesTable.getAllRowId().length < 1) {
			return null;
		}
		// 显示目前选择的属性的可选项
		PropertyDefine pd;
		if (null == propertiesTable.getSelection()) {
			pd = ui_propertyDefines.get(propertiesTable.getAllRowId()[0]);
		} else {
			pd = ui_propertyDefines.get(propertiesTable.getSelection());
		}
		SNameValue[] optoinValues = new SNameValue[0];
		if (pd != null && pd.getOptions() != null) {
			optoinValues = new SNameValue[pd.getOptions().length];
			for (int optionIndex = 0; optionIndex < pd.getOptions().length; optionIndex++) {
				optoinValues[optionIndex] = new SNameValue(GUID
						.randomID().toString(),
						pd.getOptions()[optionIndex]);
			}
		}
		return optoinValues;
	}
	private boolean doSave() {
//		if (isEditable) {
		// 保存当前编辑属性的可选项（保存至内存）
		if (!ui_savePropertyDefine()) {
			return false;
		}
//		}

		// 保存页面上所有数据
		UpdateGoodsCategoryTask task = new UpdateGoodsCategoryTask();
		task.setId(categoryInfo.getId());
		task.setCategoryNo(categoryInfo.getCategoryNo());
		task.setScale(COUNT_SCALE);
		task.setName(categoryInfo.getName()); 

		if (null == propertiedParentNode) {
			List<PropertyDefine> pdList = new ArrayList<PropertyDefine>();
			for (int index = 0; index < ui_propertyIds.size(); index++) {
				String pdId = ui_propertyIds.get(index);
				PropertyDefine pdTemp = ui_propertyDefines.get(pdId);
				if (pdTemp.getName() != null && !"".equals(pdTemp.getName())) {
					pdList.add(ui_propertyDefines.get(pdId));
				}
			}
			task.setPropertyDefines(pdList.toArray(new PropertyDefine[pdList.size()]));
		}
		try {
			getContext().handle(task);
			
			// 清空缓存
			unSavedNewOptionId_UnEditable.clear();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			alert("保存失败！\n"+exception.getMessage());
			return false;
		}
		
	}
	
	private void changeArrowPosition() {
		String[] rowIds = propertiesTable.getAllRowId();
		int selectedRowIndex = 0;
		for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
			if (rowIds[rowIndex].equals(propertiesTable.getSelection())) {
				selectedRowIndex = rowIndex;
				break;
			}
		}
		arrowCmp.clear();
		GridLayout glArrowCmp = new GridLayout();
		glArrowCmp.marginTop = tableStyle.getRowHeight()
				* (selectedRowIndex + 1) + tableStyle.getRowHeight() / 2;
		arrowCmp.setLayout(glArrowCmp);
		Label arrowLabel = new Label(arrowCmp);
		GridData gdArrowLabel = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_CENTER);
		gdArrowLabel.widthHint = 19;
		gdArrowLabel.heightHint = 19;
		arrowLabel.setLayoutData(gdArrowLabel);
		arrowLabel.setBackimage(BaseImages
				.getImage("images/goods/arrow-blue.png"));
		arrowCmp.layout();
	}

	private boolean ui_savePropertyDefine() {
		if (lastSelectedPropertyRowId != null) {
			String[] rowIds = propertiesTable.getAllRowId();
			String lastPropertyName = "";
			for (int rowIndex = 0; rowIndex < rowIds.length; rowIndex++) {
				String propertyName;
				if (isEditable) {
					propertyName = propertiesTable.getEditValue(
						rowIds[rowIndex], PropertyColumns.name.name())[0];
				} else {
					propertyName = propertiesTable.getExtraData(rowIds[rowIndex], "name")[0];
				}
				if (lastSelectedPropertyRowId.equals(rowIds[rowIndex])) {
					if(propertiesTable.getExtraData(rowIds[rowIndex], "isUnit") != null
							&& propertiesTable.getExtraData(rowIds[rowIndex], "isUnit")[0] != null
							&& propertiesTable.getExtraData(rowIds[rowIndex], "isUnit")[0].equals("1")) {
						lastPropertyName = "单位";
					} else {
						lastPropertyName = propertyName == null ? "" : propertyName;
					}
					break;
				}
			}
			lastPropertyName = lastPropertyName.trim();
//			if (!lastPropertyName.trim().equals("")) {
				for (String key : ui_propertyDefines.keySet()) {
					PropertyDefine temp = ui_propertyDefines.get(key);
					if (lastSelectedPropertyRowId.equals(temp.getId().toString()))
						continue;
					if (!lastPropertyName.trim().equals("") && lastPropertyName.trim().equals(temp.getName())) {
						alert("属性名称不能重复！");
						return false;
					}
					if (lastPropertyName.trim().length() > 6) {
						alert("属性名称长度不能超过6个字符！");
						return false;
					}
				}

				List<String> lastOptions = new ArrayList<String>();
				String[] optionRowIds = optionsTable.getAllRowId();
				for (int rowIndex = 0; rowIndex < optionRowIds.length; rowIndex++) {
					String optionValue = optionsTable.getEditValue(
							optionRowIds[rowIndex], OptionColumns.item.name())[0];
					if (optionValue == null || optionValue.equals("")) {
						optionValue = optionsTable.getExtraData(optionRowIds[rowIndex], optionRowIds[rowIndex])[0];
					}
					if (optionValue == null || optionValue.equals(""))
						continue;
					lastOptions.add(optionValue);
				}
				ui_propertyDefines.put(lastSelectedPropertyRowId,
						new PropertyDefineItem(GUID
								.tryValueOf(lastSelectedPropertyRowId),
								lastPropertyName,
								lastOptions.toArray(new String[lastOptions
										.size()])));
				if (!ui_propertyIds.contains(lastSelectedPropertyRowId)) {
					ui_propertyIds.add(lastSelectedPropertyRowId);
				}
//			}
		}
		return true;
	}
	
	class PropertyContentProvider implements SEditContentProvider {

		private PropertyDefine[] propertyDefines;
		private boolean editable = true;
		
		//新增的元素ID
//		private String newElementId = null;

		public PropertyContentProvider(PropertyDefine[] propertyDefines,
				boolean editable) {
			if (propertyDefines == null || propertyDefines.length == 0) {
				this.propertyDefines = new PropertyDefine[] { new PropertyDefineItem(
						GUID.randomID(), "单位",
						GoodsCategoryDetailProcessor.DEFAULT_UNITS) }; // TOOD:单位枚举
			} else {
				this.propertyDefines = propertyDefines;
			}
			this.editable = editable;
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			return propertyDefines;
		}

		public String getElementId(Object element) {
			return ((PropertyDefine) element).getId().toString();
		}

		public boolean isSelected(Object element) {
			return false;
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public String getValue(Object element, String columnName) {
			if(element instanceof PropertyDefineItem) {
				PropertyDefineItem item = (PropertyDefineItem)element;
				if(item.getName().equals("单位")) {
					return null;
				}
			}
			if (editable) {
				if (GoodsCategoryDetailProcessor.PropertyColumns.name.name()
						.equals(columnName)) {
					PropertyDefine item = (PropertyDefine)element;
					if(item.getName().equals("单位")) {
						return null;
					}
					return ((PropertyDefine) element).getName();
				}
			}
			return null;
		}

		public String[] getActionIds(Object element) {
			PropertyDefine value = (PropertyDefine)element;
			if(value.getName().equals("单位") ) {
				return null;
			}
			List<String> actionIds = new ArrayList<String>();
//			if (newElementId != null && newElementId.equals(value.getName())) {
//				actionIds.add(OptionActions.up.name());
//			} 
//			else {
//				if (propertyDefines.length > 1) {
//					PropertyDefine firstValue = propertyDefines[1];
//					if (!value.equals(firstValue)) {
//						actionIds.add(PropertyActions.up.name());
//					}
//				}
//				if (propertyDefines.length > 2) {
//					PropertyDefine lastValue = propertyDefines[propertyDefines.length - 1];
//					if (!value.equals(lastValue) && !value.getName().trim().equals("")) {
//						actionIds.add(PropertyActions.down.name());
//					}
//				}
//			}
			if (editable) {
				actionIds.add(PropertyActions.remove.name());
			}
			return actionIds.toArray(new String[actionIds.size()]);
		}

		public Object getNewElement() {
			if(propertiesTable.getAllRowId().length < 12) {
				if (propertiesTable.getAllRowId().length > 0) {
					String[] rowIds = propertiesTable.getAllRowId();
					String lastRowId = rowIds[rowIds.length - 1];
					List<String> actionList = new ArrayList<String>();
//					if (rowIds.length > 2) {
//						actionList.add(PropertyActions.up.name());
//					}
//					actionList.add(PropertyActions.down.name());
					if (editable) {
						actionList.add(PropertyActions.remove.name());
					}
					propertiesTable.updateRowActions(lastRowId, actionList.toArray(new String[actionList.size()]));
				}
				
				GUID pdId = GUID.randomID();
				PropertyDefine pd = new PropertyDefineItem(pdId, "", null);
//				newElementId = pdId.toString();
				ui_propertyDefines.put(pdId.toString(), pd);
				return pd;
			} else {
				return null;
			}
		}

		public SNameValue[] getExtraData(Object element) {
			if(element instanceof PropertyDefineItem || element instanceof PropertyDefine) {
				PropertyDefine item = (PropertyDefine)element;
				if(item.getName().equals("单位")) {
					return new SNameValue[]{ new SNameValue("isUnit", "1"), new SNameValue("name", item.getName())};
				} else {
					return new SNameValue[]{ new SNameValue("isUnit", "0"), new SNameValue("name", item.getName())};
				}
			}
			return null;  
		}

	}

	/**
	 * 属性可选列表编辑内容提供器
	 */
	class OptionEditContentProvider implements SEditContentProvider {

		private SNameValue[] values;
		private boolean editable = false;
		
		private String newElementId = null;

		public OptionEditContentProvider(SNameValue[] values, boolean editable) {
			this.values = values;
			this.editable = editable;
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
//			values = getSelectedPdOptionValues();
			if (values == null || values.length < 1) return null;
			return values;
		}

		public String getElementId(Object element) {
			return ((SNameValue) element).getName();
		}

		public boolean isSelected(Object element) {
			return false;
		}

		public boolean isSelectable(Object element) {
			return false;
		}

		public String getValue(Object element, String columnName) {
			SNameValue nValue = (SNameValue)element;
			if (editable) {
				if (GoodsCategoryDetailProcessor.OptionColumns.item.name().equals(
						columnName)) {
					return nValue.getValue();
				}
			} else if (unSavedNewOptionId_UnEditable.contains(nValue.getName())) {
				return nValue.getValue();
			}
			return null;
		}

		public String[] getActionIds(Object element) {
			SNameValue value = (SNameValue)element;
			List<String> actionIds = new ArrayList<String>();
//			if (newElementId != null && newElementId.equals(value.getName())) {
//				actionIds.add(OptionActions.up.name());
//			} 
//			else {
//				if (values.length > 0) {
//					SNameValue firstValue = values[0];
//					if (!value.equals(firstValue)) {
//						actionIds.add(OptionActions.up.name());
//					}
//				}
//				if (values.length > 1) {
//					SNameValue lastValue = values[values.length - 1];
//					if (!value.equals(lastValue)) {
//						actionIds.add(OptionActions.down.name());
//					}
//				}
//			}
			if (editable || unSavedNewOptionId_UnEditable.contains(value.getName())) {
				actionIds.add(OptionActions.remove.name());
			}
			return actionIds.toArray(new String[actionIds.size()]);
		}

		public Object getNewElement() {
			if(optionsTable.getAllRowId().length < 12) {
				if (optionsTable.getAllRowId().length > 0) {
					String[] rowIds = optionsTable.getAllRowId();
					String lastRowId = rowIds[rowIds.length - 1];
					List<String> actionList = new ArrayList<String>();
//					if (rowIds.length > 1) {
//						actionList.add(OptionActions.up.name());
//					}
//					actionList.add(OptionActions.down.name());
					if (editable || unSavedNewOptionId_UnEditable.contains(lastRowId)) {
						actionList.add(OptionActions.remove.name());
					}
					optionsTable.updateRowActions(lastRowId, actionList.toArray(new String[actionList.size()]));
				}
				GUID newElementGuid = GUID.randomID();
				newElementId = newElementGuid.toString();
				if (!editable) { // 不是在整个表格可编辑的状态下新增行
					unSavedNewOptionId_UnEditable.add(newElementId);
				}
				return new SNameValue(newElementId, "");
			} else {
				return null;
			}
		}

		public SNameValue[] getExtraData(Object element) {
			return new SNameValue[]{(SNameValue) element};
		}

	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		return doSave();
	}
}



class PropertyDefineItem implements PropertyDefine {

	protected GUID id;

	protected String name;

	protected String[] options;

	protected PropertyInputType valueInputMode;

	public PropertyDefineItem(GUID id, String name, String[] options) {
		this.id = id;
		this.name = name;
		this.options = options;
		// TODO:
		this.valueInputMode = options != null && options.length > 0 ? PropertyInputType.SELECT
				: PropertyInputType.INPUT;
	}

	/**
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public PropertyInputType getValueInputMode() {
		return this.valueInputMode;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getOptions() {
		return options;
	}

}

