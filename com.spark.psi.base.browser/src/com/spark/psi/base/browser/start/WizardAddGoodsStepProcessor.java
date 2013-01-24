package com.spark.psi.base.browser.start;

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
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SEditColumn;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SListEditColumn;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.GoodsCategorySource;
import com.spark.psi.base.browser.GoodsCommon;
import com.spark.psi.base.browser.goods.OptionListSource;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.PropertyInputType;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryInfo;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;
import com.spark.psi.publish.base.goods.entity.PropertyDefine;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree.CategoryNode;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey;
import com.spark.psi.publish.base.goods.task.DeleteGoodsTask;
import com.spark.psi.publish.base.goods.task.GoodsInfoTask;
import com.spark.psi.publish.base.goods.task.ValidationGoodsIsExistTask;
import com.spark.psi.publish.base.goods.task.GoodsInfoTask.ItemMethod;
import com.jiuqi.util.StringUtils;


/**
 * 
 * <p>批量添加商品</p>
 *


 *
 * @version 2012-7-10
 */
public class WizardAddGoodsStepProcessor extends WizardBaseStepProcessor {

	public static final String ID_List_Category = "List_Category";
	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Area_Goods = "Area_Goods";
	public static final String ID_Button_Del = "Button_Del";
	
	public static enum Column {
		code("编号/条码"), 
		name("商品名称"), 
		salePrice("销售价格");
		
		private String title;
		
		private Column(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return title;
		}
	}
	
	private Composite tableArea;
	private SEditTable table;
	// 显示商品的数量（只显示数据库中已存在的商品数量）
	private Label countLabel;
	
	private GoodsCategoryTree categoryTree;
	private Map<String, Item> itemTempStore = new HashMap<String, Item>();
	private PropertyDefine[] propertyDefines;
	private GUID selectedCategoryId;
	
	@Override
	public void process(Situation context) {
		super.process(context);
		//
		final LWComboTree categoryList = createControl(ID_List_Category, LWComboTree.class);
		final Button delBtn = createControl(ID_Button_Del, Button.class);
		tableArea = createControl(ID_Area_Goods, Composite.class);
		categoryTree = context.find(GoodsCategoryTree.class);
		countLabel = createControl(ID_Label_Count, Label.class);
		
		categoryList.getTree().setSource(new GoodsCategorySCWithGoodsFlag());
		categoryList.getTree().setInput(categoryTree);
		
		categoryList.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				tableArea.clear();
				String selectedIdStr = categoryList.getTree().getSeleted();
				selectedCategoryId = selectedIdStr == null ? null : GUID.tryValueOf(selectedIdStr);
				setGoodsTable();
				
				if (selectedCategoryId != null && propertyDefines != null) {
					delBtn.setEnabled(true);
				} else {
					delBtn.setEnabled(false);
				}
			}
		});
		categoryList.setSelection(null);

		delBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {// 删除商品， 如果商品条目已存在，则直接从数据库删除
				if (table == null && table.isDisposed()) {
					return;
				} else {
					confirm("确定要删除选中商品吗？", new Runnable() {

						public void run() {
							doDelete();							
						}
					});
				}
				
			}
		});
		
		registerNotEmptyValidator(categoryList, "商品分类");
		
	}
	
	private void doDelete() {
		String[] selectedIds = table.getSelections();
		if (null == selectedIds) {
			alert("请先选择商品。");
			return;
		}
		// 已存在于数据库中的商品列表
		List<GoodsInfo> existGoodsList = new ArrayList<GoodsInfo>();
		// 已存在于数据库中商品，对应于表格中选中的rowID列表
		Map<GoodsInfo, List<String>> goodsDelIdMap = new HashMap<GoodsInfo, List<String>>();
		for (String rowId : selectedIds) {
			if (itemTempStore.containsKey(rowId)) { // 数据库中已存在的商品条目
				GoodsInfo goodsInfo = itemTempStore.get(rowId).goodsInfo;
				List<String> goodsDelIds = goodsDelIdMap.get(goodsInfo);
				if (null == goodsDelIds) {
					existGoodsList.add(goodsInfo);
					goodsDelIds = new ArrayList<String>();
					goodsDelIds.add(rowId);
					goodsDelIdMap.put(goodsInfo, goodsDelIds);
				} else {
					goodsDelIds.add(rowId);
				}
			}
			table.removeRow(rowId);
		}
		List<GUID> delFromDBGoodsIds = new ArrayList<GUID>();
		for (GoodsInfo goods : existGoodsList) {
			List<String> goodsDelItemIds = goodsDelIdMap.get(goods);
			if (goods.getItems().length == goodsDelItemIds.size()) { //商品本身的条目个数与删除选中的个数相等，就直接从数据库删除该商品
				delFromDBGoodsIds.add(goods.getId());
			} else {
				// 检查商品的所有条目部分存在于选中的条目中，则删除商品的条目关保存
				// TODO 注：这里暂时就不管没选中的条目是否编辑过直接保存,也就是之前的编辑会失效
				GoodsInfoTask task = new GoodsInfoTask();
				List<GoodsInfoTask.Item> taskItems = new ArrayList<GoodsInfoTask.Item>();
				for (GoodsItemData goodsItem : goods.getItems()) {
					if (goodsDelItemIds.contains(goodsItem.getId().toString())) {
						GoodsInfoTask.Item delItem = new GoodsInfoTask.Item();
						delItem.setId(goodsItem.getId());
						delItem.setMethod(ItemMethod.Delete);
						taskItems.add(delItem);
					} else {
						GoodsInfoTask.Item updateItem = new GoodsInfoTask.Item();
						updateItem.setId(goodsItem.getId());
						updateItem.setMethod(ItemMethod.Update);
						if (goodsItem.getStatus().equals(GoodsStatus.ON_SALE)) {
							updateItem.setOnsale(true);
						} else {
							updateItem.setOnsale(false);
						}
						updateItem.setPropertyValues(goodsItem.getPropertyValues());
						updateItem.setSalePrice(goodsItem.getSalePrice());
						taskItems.add(updateItem);
					}
				}
				
				task.setCategoryId(selectedCategoryId);
				task.setId(goods.getId());
				task.setItems(taskItems.toArray(new GoodsInfoTask.Item[taskItems.size()]));
				task.setCode(goods.getCode());
				task.setName(goods.getName());
				
				getContext().handle(task, GoodsInfoTask.Method.Update);
			}
		}
		DeleteGoodsTask delTask = new DeleteGoodsTask(delFromDBGoodsIds.toArray(new GUID[delFromDBGoodsIds.size()]));
		getContext().handle(delTask);
		
		// 更新商品数量显示
		String countStr = countLabel.getText();
		if (StringUtils.isNotEmpty(countStr)) {
			try {
				int preCount = Integer.parseInt(countStr);
				int newCount = preCount - existGoodsList.size();
				countLabel.setText(String.valueOf(newCount < 0 ? 0 : newCount));
				countLabel.getParent().layout();
			} catch (Throwable th) {
				System.out.println("删除商品时，更新商品数量出错。");
				th.printStackTrace();
			}
		}
		
		table.renderUpate();
	}
	
	
	@Override
	protected boolean operateExecute() {
		// 保存
		boolean result = false;
		if (!validateInput()) { 
			return false;
		}
		String[] rowIds = table.getAllRowId();
		List<TempGoods> tempGoodsList = getMergedTempGoodsListByRowIds(rowIds);
		if(tempGoodsList == null || tempGoodsList.size() == 0){
			return true;
		}
		if (validateData(tempGoodsList)) {
			try {
				for (TempGoods goods : tempGoodsList) {
					GoodsInfoTask.Method taskMethod = getGoodsInfoTaskByTempGoods(goods);
					GoodsInfoTask task = new GoodsInfoTask();
					fillTaskByTempGoods(task, goods);
					getContext().handle(task, taskMethod);
				}
				result = true;
				table.render();
				hint("保存成功。");
			} catch (Throwable th) {
				th.printStackTrace();
				result = false;
			}
		} 
		return result;
	}
	
	
	private boolean validateData(List<TempGoods> tempGoodsList) {
		GUID goodsId = null;
		for (TempGoods goods : tempGoodsList) {
			for (ItemRowData tempItem : goods.getItems()) {
				if (goodsId == null && itemTempStore.containsKey(tempItem.getId())) {
					goodsId = itemTempStore.get(tempItem.getId()).goodsInfo.getId();
				}
			}
			
			ValidationGoodsIsExistTask validateCodeTask = new ValidationGoodsIsExistTask(goodsId, selectedCategoryId, "", goods.getCode());
			getContext().handle(validateCodeTask);
			if (ValidationGoodsIsExistTask.ErrType.Code
					.equals(validateCodeTask.getErrType())) {
				alert("商品编号/条码重复，请修改。");
				return false;
			}
			
			ValidationGoodsIsExistTask validateNameTask = new ValidationGoodsIsExistTask(
					goodsId, selectedCategoryId, goods.getName(), "");
			getContext().handle(validateNameTask);
			if (ValidationGoodsIsExistTask.ErrType.Name
					.equals(validateNameTask.getErrType())) {
				alert("商品名称重复，请修改。");
				return false;
			}
		}
		return true;
	}
	
	private void fillTaskByTempGoods(final GoodsInfoTask task, final TempGoods goods) {
		GUID goodsId = null;
		GoodsInfoTask.Item[] items = new GoodsInfoTask.Item[goods.getItems().length];
		GoodsInfoTask.Item item;
		int itemIndex = 0;
		for (ItemRowData tempItem : goods.getItems()) {
			item = new GoodsInfoTask.Item();
			item.setId(GUID.tryValueOf(tempItem.getId()));
			Item preData = itemTempStore.get(tempItem.getId());
			if (null != preData && preData.itemData.isRefFlag()) {
				item.setPropertyValues(preData.itemData.getPropertyValues());
				item.setOnsale(preData.itemData.getStatus().equals(GoodsStatus.ON_SALE) ? true : false);
			} else {
				item.setPropertyValues(tempItem.getPropertyValues());
				item.setOnsale(true);
			}
			item.setSalePrice(DoubleUtil.strToDouble(tempItem.getSalePrice()));
			if (goodsId == null && null != preData) {
				goodsId = preData.goodsInfo.getId();
			}
			
			items[itemIndex] = item;
			itemIndex++;
		}
		goodsId = goodsId == null ? GUID.randomID() : goodsId;
		
		task.setCategoryId(selectedCategoryId);
		task.setId(goodsId);
		task.setItems(items);
		task.setCode(goods.getCode());
		task.setName(goods.getName());
	}
	
	private GoodsInfoTask.Method getGoodsInfoTaskByTempGoods(TempGoods goods) {
		GoodsInfoTask.Method taskMethod = GoodsInfoTask.Method.Create;
		for (ItemRowData tempItem : goods.getItems()) {
			if (this.itemTempStore.containsKey(tempItem.getId())) {
				taskMethod = GoodsInfoTask.Method.Update;
				break;
			}
		}
		return taskMethod;
	}
	
	private List<TempGoods> getMergedTempGoodsListByRowIds(String[] rowIds) {
		if (propertyDefines == null) return new ArrayList<TempGoods>();
		List<TempGoods> tempGoodsList = new ArrayList<TempGoods>();
		String[] propertyColumnNames = new String[propertyDefines.length];
		int pdIndex = 0;
		for (PropertyDefine pd : propertyDefines) {
			propertyColumnNames[pdIndex] = pd.getName();
			pdIndex++;
		}
		
		TempGoods goods;
		for (String rowId : rowIds) {
			String[] baseValues = table.getEditValue(rowId, Column.code.name(), Column.name.name(), Column.salePrice.name());
			String[] propertyValues = table.getEditValue(rowId, propertyColumnNames);
			if (isAllEmptyValue(baseValues) && isAllEmptyValue(propertyValues)) { // 空行忽略
				continue;
			}
//			if (isContainEmplyValue(baseValues)) { // 数据不完整，则返回空结果
//				tempGoodsList.clear();
//				break;
//			}
//			if (!this.itemTempStore.containsKey(rowId) && isContainEmplyValue(propertyValues)) { // 数据不完整，则返回空结果
//				tempGoodsList.clear();
//				break;
//			}
			
			goods = new TempGoods();
			ItemRowData item = new ItemRowData();
			
			if (itemTempStore.containsKey(rowId) && itemTempStore.get(rowId).itemData.isRefFlag()) {
				goods.setCode(itemTempStore.get(rowId).goodsInfo.getCode());
				goods.setName(itemTempStore.get(rowId).goodsInfo.getName());
				item.setId(rowId);
				item.setPropertyValues(itemTempStore.get(rowId).itemData.getPropertyValues());
			} else {
				goods.setCode(baseValues[0]);
				goods.setName(baseValues[1]);
				item.setId(rowId);
				item.setPropertyValues(propertyValues);
			}
			item.setSalePrice(baseValues[2]);
			TempGoods sameGoods = getFirstSameGoodsInList(tempGoodsList, goods.getCode(), goods.getName());
			if (sameGoods == null) {
				if (isDuplicateGoodsName(tempGoodsList, goods.getName())) {
					alert("商品名称重复，请修改。");
					return new ArrayList<TempGoods>();
				} else if (isDuplicateGoodsCode(tempGoodsList, goods.getCode())) {
					alert("商品编号/条码重复，请修改。");
					return new ArrayList<TempGoods>();
				} else {
					goods.setItems(new ItemRowData[] { item });
					tempGoodsList.add(goods);
				}
			} else {
				ItemRowData[] items = sameGoods.getItems();
				ItemRowData[] newItems = new ItemRowData[items.length + 1];
				int newItemIndex = 0;
				for (ItemRowData itemTemp : items) {
					newItems[newItemIndex] = itemTemp;
					newItemIndex++;
				}
				newItems[newItems.length - 1] = item;
				sameGoods.setItems(newItems);
			}
		}
		return tempGoodsList;
	}
	
//	private boolean isContainEmplyValue (String[] strArray) {
//		if (null == strArray) return true;
//		boolean result = false;
//		for (String str : strArray) {
//			if (StringUtils.isEmpty(str)) {
//				result = true;
//				break;
//			}
//		}
//		return result;
//	}
	
	private boolean isAllEmptyValue(String[] strArray) {
		if (null == strArray) return true;
		boolean result = true;
		for (String str : strArray) {
			if (StringUtils.isNotEmpty(str)) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	private boolean isDuplicateGoodsName(List<TempGoods> tempGoodsList, String name) {
		boolean isDuplicateGoodsName = false;
		for (TempGoods goods : tempGoodsList) {
			if (goods.getName().equals(name)) {
				isDuplicateGoodsName = true;
				break;
			}
		}
		return isDuplicateGoodsName;
	}
	
	private boolean isDuplicateGoodsCode(List<TempGoods> tempGoodsList, String code) {
		boolean isDuplicateGoodsCode = false;
		for (TempGoods goods : tempGoodsList) {
			if (goods.getCode().equals(code)) {
				isDuplicateGoodsCode = true;
				break;
			}
		}
		return isDuplicateGoodsCode;
	}
	
	private TempGoods getFirstSameGoodsInList(List<TempGoods> tempGoodsList, String code, String name) {
		TempGoods sameGoods = null;
		for (TempGoods goods : tempGoodsList) {
			if (goods.getCode().equals(code) && goods.getName().equals(name)) {
				sameGoods = goods;
				break;
			}
		}
		return sameGoods;
	}
	
	private class TempGoods {
		private String code;
		private String name;
		private ItemRowData[] items;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public ItemRowData[] getItems() {
			return items;
		}
		public void setItems(ItemRowData[] items) {
			this.items = items;
		}
		
		
	}
	
	private class ItemRowData {
		private String id;
		private String[] propertyValues;
		private String salePrice;
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String[] getPropertyValues() {
			return propertyValues;
		}
		public void setPropertyValues(String[] propertyValues) {
			this.propertyValues = propertyValues;
		}
		public String getSalePrice() {
			return salePrice;
		}
		public void setSalePrice(String salePrice) {
			this.salePrice = salePrice;
		}
	}
	
	private void setGoodsTable() {
		if (null == selectedCategoryId) {
			STableStyle tableStyle = new STableStyle();
			table = new SEditTable(tableArea, getColumns(null, "请选择已设置属性的商品分类"), tableStyle, null);
			table.setContentProvider(new ItemContentProvider(null));
			table.setLabelProvider(new ItemLabelProvider(new ArrayList<String>(), null));
			table.render();
			table.getParent().layout();
			return;
		}
		GoodsCategoryInfo categoryInfo = getContext().find(
				GoodsCategoryInfo.class, selectedCategoryId);
		propertyDefines = getPropertyDefinesByCategory(categoryInfo);
		if (null == categoryInfo || propertyDefines == null || propertyDefines.length < 1) {
			alert("请首先为该分类设置属性，再新建商品！");
			STableStyle tableStyle = new STableStyle();
			table = new SEditTable(tableArea, getColumns(null), tableStyle, null);
			table.setContentProvider(new ItemContentProvider(null));
			table.setLabelProvider(new ItemLabelProvider(new ArrayList<String>(), null));
		} else {
			SEditTableStyle tableStyle = new SEditTableStyle();
			tableStyle.setAutoAddRow(true);
			tableStyle.setSelectionMode(SSelectionMode.Multi);
			STableColumn[] columns = getColumns(propertyDefines);
			table = new SEditTable(tableArea, columns, tableStyle, null);
			
			table.setContentProvider(new ItemContentProvider(propertyDefines));
			List<String>  columnNames = new ArrayList<String>();
			for (STableColumn column : columns) {
				columnNames.add(column.getName());
			}
			table.setLabelProvider(new ItemLabelProvider(columnNames, propertyDefines));
			
			registerInputValidator(new TableDataValidator(table) {
				
				@Override
				protected String validateRowCount(int rowCount) {
					return null;
				}
				
				@Override
				protected String validateCell(String rowId, String columnName) {
					if (itemTempStore.containsKey(rowId) && itemTempStore.get(rowId).itemData.isRefFlag()) {
						String salePrice = table.getEditValue(rowId, Column.salePrice.name())[0];
						if (columnName.equals(Column.salePrice.name()) && StringUtils.isEmpty(salePrice)) {
							return "销售价格不能为空！";
						} else {
							return null;
						}
					}
					String[] baseValues = table.getEditValue(rowId, Column.code.name(), Column.name.name(), Column.salePrice.name());
					
					List<String> propertyColumns = new ArrayList<String>();
					for (PropertyDefine pd : propertyDefines) {
						propertyColumns.add(pd.getName());
					}
					String[] propertyValues = table.getEditValue(rowId, propertyColumns.toArray(new String[propertyColumns.size()]));
					
					if (isAllEmptyValue(baseValues) && isAllEmptyValue(propertyValues)) { // 空行忽略
						return null;
					}
					
					if (columnName.equals(Column.code.name()) && StringUtils.isEmpty(baseValues[0])) {
						return "商品编号/条码不能为空！";
					}
					if (columnName.equals(Column.name.name()) && StringUtils.isEmpty(baseValues[1])) {
						return "商品名称不能为空！";
					}
					if (propertyColumns.contains(columnName)) {
						int propertyColumnIndex = propertyColumns.indexOf(columnName);
						if (StringUtils.isEmpty(propertyValues[propertyColumnIndex])) {
							return columnName + "不能为空";
						}
					}
					if (columnName.equals(Column.salePrice.name()) && StringUtils.isEmpty(baseValues[2])) {
						return "销售价格不能为空！";
					}
					return null;
				}
			});
		}
		table.render();
		table.getParent().layout();
	}
	
	private STableColumn[] getColumns(PropertyDefine[] propertyDefines, String defaultTitle) {
		STableColumn[] columns;
		if (null == propertyDefines) {
			columns = new STableColumn[1];
			columns[0] = new STableColumn("", 150, JWT.LEFT, defaultTitle);
		} else {
			int columnsSize = propertyDefines.length + 3;
			columns = new STableColumn[columnsSize];
			columns[0] = new STextEditColumn(Column.code.name(), 150, JWT.LEFT, Column.code.getTitle());
			columns[1] = new STextEditColumn(Column.name.name(), 150, JWT.LEFT, Column.name.getTitle());
			for (int pdIndex = 1; pdIndex < propertyDefines.length; pdIndex++) { // 属性中单位放到最后显示单位的index=0
				columns[pdIndex + 1] = createColumn(propertyDefines[pdIndex], 100);
			}
			columns[columnsSize - 2] = createColumn(propertyDefines[0], 100);
			columns[columnsSize - 1] = new SNumberEditColumn(Column.salePrice.name(), 150, JWT.LEFT, Column.salePrice.getTitle());
			
			columns[2].setGrab(true);
		}
		return columns;
	}
	
	private STableColumn[] getColumns(PropertyDefine[] propertyDefines) {
		return getColumns(propertyDefines, "分类未设置属性");
	}
	
	private SEditColumn createColumn(PropertyDefine propertyDefine, int width) {
		if (propertyDefine.getValueInputMode() == PropertyInputType.INPUT) {
			return new STextEditColumn(propertyDefine.getName(), width, JWT.LEFT,
					propertyDefine.getName());
		} else {
			SListEditColumn column = new SListEditColumn(propertyDefine
					.getName(), width, JWT.LEFT, propertyDefine.getName());
			column.setListSource(new OptionListSource(propertyDefine
					.getOptions()));
			return column;
		}
	}
	
	private PropertyDefine[] getPropertyDefinesByCategory(GoodsCategoryInfo categoryInfo) {
		if (null == categoryInfo) return null;
		GoodsCategoryInfo propertiedCategoryInfo = null;
		if (categoryInfo.getId() != null) {
			GoodsCategoryTree.CategoryNode node = categoryTree
					.getNodeById(categoryInfo.getId());
			while (node != null && !node.isSetPropertyFlag()) {
				node = node.getParent();
			}
			if (node != null) {
				propertiedCategoryInfo = getContext().find(
						GoodsCategoryInfo.class, node.getId());
			}
		}
		return propertiedCategoryInfo == null ? null : propertiedCategoryInfo.getPropertyDefines();
	}
	
	private List<Item> getItemListByCategoryId(GUID categoryId) {
		GetGoodsInfoListKey key = new GetGoodsInfoListKey();
		key.setCateoryId(categoryId);
		@SuppressWarnings("unchecked")
		ListEntity<GoodsInfo> listEntity = (ListEntity<GoodsInfo>) getContext()
				.find(ListEntity.class, key);
		List<GoodsInfo> goodsList = listEntity.getItemList();
		List<Item> itemList = new ArrayList<Item>();
		for (GoodsInfo goodsInfo : goodsList) {
			for (GoodsItemData itemData : goodsInfo.getItems()) {
				Item item = new Item();
				item.goodsInfo = goodsInfo;
				item.itemData = itemData;
				itemList.add(item);
			}
		}
		return itemList;
	}
	
	private void saveItemTemp(List<Item> itemList) {
		itemTempStore.clear();
		for (Item item : itemList) {
			itemTempStore.put(item.itemData.getId().toString(), item);
		}
	}
	
	private class ItemContentProvider implements SEditContentProvider {

		private Item items[] = null;
		private PropertyDefine[] propertyDefines;
		
		public ItemContentProvider (PropertyDefine[] propertyDefines) {
			this.propertyDefines = propertyDefines;
		}

		public String[] getActionIds(Object element) {
			return null;
		}

		public SNameValue[] getExtraData(Object element) {
			return null;
		}

		public Object getNewElement() {
			return GUID.randomID().toString();
		}

		public String getValue(Object element, String columnName) {
			if (element instanceof Item) {
				Item item = (Item) element;
				if (!item.itemData.isRefFlag()) {
					if (Column.code.name().equals(columnName)) {
						return item.goodsInfo.getCode();
					}
					if (Column.name.name().equals(columnName)) {
						return item.goodsInfo.getName();
					}
					if (null != propertyDefines){
						for (int i = 0; i < propertyDefines.length; i++) {
							PropertyDefine define = propertyDefines[i];
							if (define.getName().equals(columnName)) {
								return item.itemData.getPropertyValues()[i];
							}
						}
					}
				}
				if (Column.salePrice.name().equals(columnName)) { 
					if (item.itemData.getSalePrice() > 0) {
						return String.valueOf(item.itemData.getSalePrice());
					} else {
						return "";
					}
				}
			} else if (element instanceof String) {
				return "";
			}
			return null;
		}

		public String getElementId(Object element) {
			if (element instanceof Item) {
				Item item = (Item) element;
				return item.itemData.getId().toString();
			} else if (element instanceof String) {
				return (String) element;
			} else {
				return null;
			}
		}

		public Object[] getElements(Context context, STableStatus tablestatus) {
			if (selectedCategoryId == null) return null;
			List<Item> itemList = getItemListByCategoryId(selectedCategoryId);
			saveItemTemp(itemList);
			if (itemList.size() > 0) {
				items = itemList.toArray(new Item[itemList.size()]);
				countLabel.setText(String.valueOf(itemList.size()));
			} else {
				countLabel.setText("0");
			}
			countLabel.getParent().layout();
			return items;
		}

		public boolean isSelectable(Object element) {
			boolean isSelectable = true;
			if (element instanceof Item) {
				Item item = (Item)element;
				if (item.itemData.isRefFlag()) {
					isSelectable = false;
				}
			}
			return isSelectable;
		}

		public boolean isSelected(Object element) {
			return false;
		}
		
	}
	
	private class ItemLabelProvider implements SLabelProvider {

		private List<String> sortedColumnList;
		private PropertyDefine[] propertyDefines;
		
		public ItemLabelProvider(List<String> sortedColumnList, PropertyDefine[] propertyDefines) {
			this.sortedColumnList = sortedColumnList;
			this.propertyDefines = propertyDefines;
		}
		
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			String columnName = sortedColumnList.size() < columnIndex + 1 ? null : sortedColumnList.get(columnIndex);
			if (element instanceof Item && StringUtils.isNotEmpty(columnName)) {
				Item item = (Item) element;
				if (Column.code.name().equals(columnName)) {
					return item.goodsInfo.getCode();
				}
				if (Column.name.name().equals(columnName)) {
					return item.goodsInfo.getName();
				}
				if (Column.salePrice.name().equals(columnName) && item.itemData.getSalePrice() > 0) { 
					return DoubleUtil.getRoundStr(item.itemData.getSalePrice());
				} else if (null != propertyDefines) {
					for (int i = 0; i < propertyDefines.length; i++) {
						PropertyDefine define = propertyDefines[i];
						if (define.getName().equals(columnName)) {
							return item.itemData.getPropertyValues()[i];
						}
					}
				}
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}
		
	}
	
	final static class Item {
		GoodsInfo goodsInfo;
		GoodsItemData itemData;
	}
	
	/**
	 * 商品分类source，标志出含有商品的分类
	 * 
	 *
	 */
	private final class GoodsCategorySCWithGoodsFlag extends GoodsCategorySource {
		@Override
		public ImageDescriptor getImage(Object element) {
			if(element instanceof CategoryNode && GoodsCommon.isCategoryContainGoods(getContext(), ((CategoryNode)element).getId())) {
				return BaseImages
					.getImage("images/goods/ico_properties.png");
			} else {
				return super.getImage(element);
			}
			
		}
	}
}
