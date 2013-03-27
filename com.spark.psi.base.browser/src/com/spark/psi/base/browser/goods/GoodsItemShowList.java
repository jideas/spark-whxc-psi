package com.spark.psi.base.browser.goods;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.common.components.table.edit.SEditColumn;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SEditTableStyle;
import com.spark.common.components.table.edit.SListEditColumn;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PropertyInputType;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;
import com.spark.psi.publish.base.goods.entity.PropertyDefine;

public class GoodsItemShowList {
	
	
	public static final String COLUMN_NAME_NUMBER = "numberName";
	public static final String COLUMN_NAME_SPEC = "specName";
	public static final String COLUMN_NAME_PRICE = "price";
	public static final String COLUMN_NAME_STANDARDCOST = "standardCost";
	public static final String COLUMN_NAME_ORIGINALPRICE = "originalPrice";
	public static final String COLUMN_NAME_LOSSRATE = "lossRate";
	public static final String COLUMN_NAME_STATUS = "status";
	
	final static STableColumn COLUMN_NUMBER = new SNumberEditColumn(COLUMN_NAME_NUMBER, 120,JWT.CENTER, "条码");
	final static STableColumn COLUMN_SPEC = new STextEditColumn(COLUMN_NAME_SPEC, 120,JWT.CENTER, "规格");
	final static STableColumn COLUMN_PRICE = new SNumberEditColumn(COLUMN_NAME_PRICE, 70, JWT.RIGHT, "销售价格");
	final static STableColumn COLUMN_STANDARDCOST = new SNumberEditColumn(COLUMN_NAME_STANDARDCOST, 70, JWT.RIGHT, "标准成本");
	final static STableColumn COLUMN_ORIGINALPRICE = new SNumberEditColumn(COLUMN_NAME_ORIGINALPRICE, 70, JWT.RIGHT, "原价");
	final static STableColumn COLUMN_LOSSRATE = new SNumberEditColumn(COLUMN_NAME_LOSSRATE, 55,JWT.CENTER, "损耗率");
	final static STableColumn COLUMN_STATUS = new STableColumn(COLUMN_NAME_STATUS, 55,JWT.CENTER, "状态");
	
	protected final static List<String> editableWheneverColumnList = new ArrayList<String>();
	static  {
		editableWheneverColumnList.add(COLUMN_NAME_PRICE);
		editableWheneverColumnList.add(COLUMN_NAME_STANDARDCOST);
		editableWheneverColumnList.add(COLUMN_NAME_ORIGINALPRICE);
		editableWheneverColumnList.add(COLUMN_NAME_LOSSRATE);
	}

	private Composite parent                    = null;
	
	private LoginInfo loginInfo                 = null;
	private GoodsInfo goodsInfo                 = null;
	private PropertyDefine[] propertyDefines    = null;
	private Text salePriceText 	        = null;
	
	private SEditTable itemTable                = null;
	
	private final List<STableColumn> columnList = new ArrayList<STableColumn>();
	private final List<String> deletedRowIdList = new ArrayList<String>();
	private STableColumn unitColumn             = null;
	
	public GoodsItemShowList(Composite parent, GoodsInfo goodsInfo, PropertyDefine[] propertyDefines, Text salePriceText) {
		this.loginInfo = parent.getContext().find(LoginInfo.class);
		this.parent = parent;
		this.goodsInfo = goodsInfo;
		this.propertyDefines = propertyDefines;
		this.salePriceText = salePriceText;
		
		build();
	}
	
	public SEditTable getItemTable() {
		return this.itemTable;
	}
	
	public List<String> getDeleteRowIdList() {
		return this.deletedRowIdList;
	}
	
	private void build() {
		unitColumn = createColumn(propertyDefines[0], 55);
		
		columnList.add(COLUMN_NUMBER);
		columnList.add(COLUMN_SPEC);
		columnList.add(unitColumn);
		columnList.add(COLUMN_LOSSRATE);
		// 加载列
		for (int i = 1; i < propertyDefines.length; i++) { // 除单位的属性列
			columnList.add(createColumn(propertyDefines[i]));
		}
		if (loginInfo.hasAuth(Auth.SubFunction_GoodsMange_ShowSalesAmount)) {
			columnList.add(COLUMN_PRICE);
			columnList.add(COLUMN_STANDARDCOST);
			columnList.add(COLUMN_ORIGINALPRICE);
		}
		if (loginInfo
				.hasAuth(Auth.SubFunction_GoodsMange_ChangeGoodsStatus)) {
			columnList.add(COLUMN_STATUS);
		}

		for (STableColumn column : columnList) {
			column.setGrab(true);
		}
		itemTable = new SEditTable(parent, columnList.toArray(new STableColumn[0]), getTableStyle(),
				getTableActions());
		itemTable.setID("goodsItemTable");
		JSONObject operation = new JSONObject();
		try {
			operation.append("priceIndex", String.valueOf(columnList.indexOf(COLUMN_PRICE)));
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		itemTable.setCustomObject("PriceColumnIndex", operation);
		itemTable.addActionListener(new SActionListener() {
			public void actionPerformed(String rowId, String actionName,
					String actionValue) {
				// 处理在售动作
				if (actionName.equals(Action.OnSale.name())) {
					String[] extraDatas = itemTable.getExtraData(rowId,
							"isNew", "isRef");
					boolean isNew = "1".equals(extraDatas[0]);
					boolean isRef = "1".equals(extraDatas[1]);
					itemTable.updateCell(rowId, "status",
							GoodsStatus.ON_SALE.getCode(),
							GoodsStatus.ON_SALE.getName());
					if (isNew || !isRef) {
						itemTable.updateRowActions(rowId,
								new String[] { Action.OffSale.name(),
										Action.Delete.name() });
					} else {
						itemTable.updateRowActions(rowId,
								new String[] { Action.OffSale.name() });
					}

				}
				// 处理停售动作
				else if (actionName.equals(Action.OffSale.name())) {
					String[] extraDatas = itemTable.getExtraData(rowId,
							"isNew", "isRef");
					boolean isNew = "1".equals(extraDatas[0]);
					boolean isRef = "1".equals(extraDatas[1]);
					itemTable.updateCell(rowId, GoodsItemShowList.COLUMN_NAME_STATUS,
							GoodsStatus.STOP_SALE.getCode(),
							GoodsStatus.STOP_SALE.getName());
					if (isNew || !isRef) {
						itemTable.updateRowActions(rowId,
								new String[] { Action.OnSale.name(),
										Action.Delete.name() });
					} else {
						itemTable.updateRowActions(rowId,
								new String[] { Action.OnSale.name() });
					}
				}
				// 处理删除动作
				else if (actionName.equals(Action.Delete.name())) {
					itemTable.removeRow(rowId);
					deletedRowIdList.add(rowId);
				}
				itemTable.renderUpate();
				
//				if (itemTable.getAllRowId() == null || itemTable.getAllRowId().length == 0) {
//					itemTable.addRow(GUID.randomID().toString());
//				}
			}
		});
		//
		setTableContentProvider();
		setTableLabelProvider();
		itemTable.render();
	}
	
	
	private void setTableContentProvider() {
		itemTable.setContentProvider(new SEditContentProvider() {
			
			public boolean isSelected(Object element) {
				return false;
			}
			
			public boolean isSelectable(Object element) {
				return false;
			}
			
			public Object[] getElements(Context context, STableStatus tablestatus) {
				return goodsInfo == null ? null : goodsInfo.getItems();
			}
			
			public String getElementId(Object element) {
				if (element instanceof String) {
					return (String)element;
				} else if (element instanceof GoodsItemData) {
					GoodsItemData itemData = (GoodsItemData)element;
					return itemData.getId().toString();
				}
				return null;
			}
			
			public String getValue(Object element, String columnName) {
				if (element instanceof String) {
					if (columnName.equals(GoodsItemShowList.COLUMN_NAME_PRICE)) {
						try {
							return DoubleUtil.getRoundStr(Double
									.parseDouble(salePriceText.getText())); // 初始销售价格
						} catch (Throwable t) {
							return "";
						}
					} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_STATUS)) {
						return GoodsStatus.ON_SALE.getCode(); // 初始状态
					} else {
						return "";
					}
				} else if (element instanceof GoodsItemData) {
					GoodsItemData item = (GoodsItemData) element;
//					if (!item.isRefFlag()) { // 仅没有引用时可以修改属性值
						for (int i = 0; i < propertyDefines.length; i++) {
							PropertyDefine define = propertyDefines[i];
							if (define.getName().equals(columnName)) {
								return item.getPropertyValues()[i];
							}
						}
//					}
					if (item.isRefFlag() && !editableWheneverColumnList.contains(columnName)) {
						return null;
					}
					if (columnName.equals(GoodsItemShowList.COLUMN_NAME_NUMBER)) {
						return item.getGoodsItemNo();
					} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_SPEC)) {
						return item.getSpec();
					} else  if (columnName.equals(GoodsItemShowList.COLUMN_NAME_PRICE)) {
						if (item.getSalePrice() > 0) {
							return String.valueOf(item.getSalePrice());
						} else {
							return "";
						}
					} else  if (columnName.equals(GoodsItemShowList.COLUMN_NAME_STANDARDCOST)) {
						if (item.getStandardCost() > 0) {
							return String.valueOf(item.getStandardCost());
						} else {
							return "";
						}
					} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_ORIGINALPRICE)){
						if (item.getOriginalPrice() > 0) {
							return DoubleUtil.getRoundStr(item.getOriginalPrice());
						} else {
							return "";
						}
					} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_LOSSRATE)){
						if (item.getLossRate() > 0) {
							return DoubleUtil.getRoundStr(item.getLossRate());
						} else {
							return "";
						}
					} else if (columnName.equals(GoodsItemShowList.COLUMN_NAME_STATUS)) {
						return item.getStatus().getCode();
					}
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
			
			public String[] getActionIds(Object element) {
				if (element instanceof String) {
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
		});
	}
	
	private void setTableLabelProvider() {
		itemTable.setLabelProvider(new GoodsItemLabelProvider());
	}
	
	private STableStyle getTableStyle() {
		SEditTableStyle tableStyle = new SEditTableStyle();
		tableStyle.setAutoAddRow(true);
		tableStyle.setRowHeight(24);
		return tableStyle;
	}
	
	private SActionInfo[] getTableActions() {
		SActionInfo[] actionInfos = new SActionInfo[] {
				new SActionInfo(Action.OnSale.name(), Action.OnSale
						.getTitle(), null, null),
				new SActionInfo(Action.OffSale.name(), Action.OffSale
						.getTitle(), null, null),
				new SActionInfo(Action.Delete.name(), Action.Delete
						.getTitle(), null, null) };
		return actionInfos;
	}
	
	private SEditColumn createColumn(PropertyDefine propertyDefine) {
		return createColumn(propertyDefine, 80);
	}
	
	public boolean isEmptyItemRow(String rowId) {
		boolean isEmpty = true;
		if ("1".equals(itemTable.getExtraData(rowId, "isRef")[0])) {
			return false;
		}
		String[] propertyColumns = new String[propertyDefines.length];
		for (int i = 0; i < propertyDefines.length; i++) {
			propertyColumns[i] = propertyDefines[i].getName();
		}
		String[] propertyValues = itemTable
				.getEditValue(rowId, propertyColumns);
		for (String columnValue : propertyValues) {
			if (!StringHelper.isEmpty(columnValue)) {
				return false;
			}
		}
		String[] baseValues = itemTable.getEditValue(rowId, COLUMN_NAME_NUMBER, COLUMN_NAME_SPEC, 
				COLUMN_NAME_ORIGINALPRICE, COLUMN_NAME_LOSSRATE);
		for (String columnValue : baseValues) {
			if (!StringHelper.isEmpty(columnValue)) {
				return false;
			}
		}
		return isEmpty;
	}

	private SEditColumn createColumn(PropertyDefine propertyDefine, int width) {
		if (propertyDefine.getValueInputMode() == PropertyInputType.INPUT) {
			return new STextEditColumn(propertyDefine.getName(), width,
					JWT.LEFT, propertyDefine.getName());
		} else {
			SListEditColumn column = new SListEditColumn(propertyDefine
					.getName(), width, JWT.LEFT, propertyDefine.getName());
			column.setListSource(new OptionListSource(propertyDefine
					.getOptions()));
			return column;
		}
	}
	
	class GoodsItemLabelProvider implements SLabelProvider,
		SNumberLabelProvider {

		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		public String getText(Object element, int columnIndex) {
			if (element instanceof String) {
				if (columnList.indexOf(COLUMN_PRICE) == columnIndex) {
					try {
						return DoubleUtil.getRoundStr((Double
								.parseDouble(salePriceText.getText()))); // 初始销售价格
					} catch (Throwable t) {
						return "";
					}
				} else if (columnList.indexOf(COLUMN_STATUS) == columnIndex) {
					return GoodsStatus.ON_SALE.getName(); // 初始状态
				}
				return "";
			} else if (element instanceof GoodsItemData) {
				GoodsItemData item = (GoodsItemData) element;
				if (0 == columnIndex) {
					return item.getGoodsItemNo();
				} else if (1 == columnIndex) {
					return item.getSpec();
				} else if (2 == columnIndex) {
					return item.getUnit();
				} else if (columnIndex > 2) {
					if (columnList.indexOf(COLUMN_PRICE) == columnIndex) {
						if (item.getSalePrice() > 0) {
							return DoubleUtil.getRoundStr(item.getSalePrice());
						} else {
							return "";
						}
					} else if (columnList.indexOf(COLUMN_STANDARDCOST) == columnIndex){
						if (item.getStandardCost() > 0) {
							return DoubleUtil.getRoundStr(item.getStandardCost());
						} else {
							return "";
						}
					} else if (columnList.indexOf(COLUMN_ORIGINALPRICE) == columnIndex){
						if (item.getOriginalPrice() > 0) {
							return DoubleUtil.getRoundStr(item.getOriginalPrice());
						} else {
							return "";
						}
					} else if (columnList.indexOf(COLUMN_LOSSRATE) == columnIndex){
						if (item.getLossRate() > 0) {
							return DoubleUtil.getRoundStr(item.getLossRate(), 4);
						} else {
							return "";
						}
					} else if (columnList.indexOf(COLUMN_STATUS) == columnIndex) {
						return item.getStatus().getName();
					}
					if (propertyDefines.length > 1 && null != propertyDefines[0]) {
						try {
							return item.getPropertyValues()[columnIndex - 3];
						} catch (Throwable t) {
							return "";
						}
					}
				}
			}
			return null;
		}

		public String getToolTipText(Object element, int columnIndex) {
			return null;
		}

		public int getDecimal(Object element, int columnIndex) {
			if (columnList.indexOf(COLUMN_PRICE) == columnIndex) {
				return 2;
			} else if (columnList.indexOf(COLUMN_LOSSRATE) == columnIndex) {
				return 4;
			}
			return -1;
		}
	}
}
