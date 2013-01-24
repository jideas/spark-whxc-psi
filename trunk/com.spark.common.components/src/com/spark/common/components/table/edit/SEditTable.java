package com.spark.common.components.table.edit;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.SNumberLabelProvider;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.STableStyle;
import com.spark.common.utils.character.DoubleUtil;

/**
 * 
 */
public class SEditTable extends STable {

	//
	public final static String CLIENT_EVENT_VALUE_CHANGED = "valueChanged";

	//
	private final static ListSourceAdapter defaultListSource = new ListSourceAdapter() {
		public String getElementId(Object element) {
			return null;
		}

		public Object getElementById(String id) {
			return null;
		}

		public String getText(Object element) {
			return null;
		}

		public Object[] getElements(Object inputElement) {
			return null;
		}
	};

	private final static class TextCellValueSetter implements
			SButtonEditColumn.CellValueSetter {
		private Text textControl;

		public TextCellValueSetter(Text textControl) {
			this.textControl = textControl;
		}

		public void setValue(String value, String text) {
			textControl.setText(value);
			textControl.setDescription(text);
		}

	}

	/**
	 * 
	 */
	private Composite actionMenuArea;

	/**
	 * 
	 */
	private boolean autoAddRow;

	/**
	 * 
	 * @param parent
	 * @param columns
	 * @param tableStyle
	 */
	public SEditTable(Composite parent, STableColumn[] columns,
			STableStyle tableStyle, SActionInfo[] actions) {
		super(parent, columns, tableStyle);
		//
		actionMenuArea = new Composite(bodyArea);
		actionMenuArea.setLocation(-1000, -1000);// XXX：DNA缺陷，恢复布局后，会重新设置位置
		actionMenuArea.moveBefore(contentBrowser);
		// actionMenuArea.setCssClassName(JWT.CSS_SYSTEM_STYLED_NOBACKGROUND);
		// actionMenuArea.setBorder(CBorder.BORDER_NORMAL);
		// actionMenuArea.setBackground(Color.COLOR_WHITE);
		STableColumn[] actualColumns = columns;
		if (actions != null && actions.length > 0) {
			//
			actualColumns = new STableColumn[columns.length + 1];
			for (int i = 0; i < columns.length; i++) {
				actualColumns[i] = columns[i];
			}
			int actionColumnWidth = 0;
			for (int i = 0; i < actions.length; i++) {
				final SActionInfo action = actions[i];
				Label button = new Label(actionMenuArea);
				button.setID(action.getId());
				button.setCursor(Cursor.HAND);
				button.addMouseClickListener(new MouseClickListener() {
					public void click(MouseEvent e) {
						Label target = (Label) e.getSource();
						JSONObject actionInfo = target
								.getCustomObject("actionInfo");
						try {
							String rowId = actionInfo.getString("rowId");
							String actionName = actionInfo
									.getString("actionName");
							String actionValue = actionInfo
									.getString("actionValue");
							fireActionEvent(rowId, actionName, actionValue);
						} catch (JSONException ex) {
						}
					}
				});
				button.addClientEventHandler(JWT.CLIENT_EVENT_CLICK,
						"SaasEditTable.handleActionButton");
				ImageDescriptor normalImage = action.getNormalImage();
				ImageDescriptor hoverImage = action.getHoverImage();
				if (normalImage != null && hoverImage != null) {
					actionColumnWidth += normalImage.getWidth() + 3;
					// button.setImage(normalImage);
					button.setBackimage(normalImage);
					button.setWidth(normalImage.getWidth());
					button.setHeight(normalImage.getHeight());
					button.setHoverBackimage(hoverImage);
				} else {
					button.setText(action.getTitle());
					button.setForeground(Color.COLOR_BLUE);
					button.setHoverForeground(Color.COLOR_BROWN);
					actionColumnWidth += action.getTitle().length() * 12 + 10;
				}
			}
			actualColumns[columns.length] = new STableColumn("actions",
					actionColumnWidth, JWT.LEFT, "操作");
			this.columns = actualColumns;
		}

		//
		this.addClientEventHandler("rowClick", "SaasEditTable.handleClickEvent");
		this.addClientEventHandler("tableInited",
				"SaasEditTable.handleTableInited");
		this.addClientEventHandler("tableMouseover",
				"SaasEditTable.handleMouseOver");
		this.addClientEventHandler("tableMouseout",
				"SaasEditTable.handleMouseOut");
		this.addClientEventHandler("rowAdded", "SaasEditTable.handleRowAdded");
		this.addClientEventHandler("rowUpdated",
				"SaasEditTable.handleRowUpdated");
		this.addClientEventHandler("rowRemoved",
				"SaasEditTable.handleRowRemoved");
		if (columns != null) {
			for (int i = columns.length - 1; i >= 0; i--) {
				if (columns[i] instanceof SEditColumn) {
					JSONObject columnInfo = new JSONObject();
					SEditColumn editColumn = (SEditColumn) columns[i];
					Text textControl = null;
					String editorType = "";
					if (editColumn instanceof STextEditColumn) {
						textControl = new Text(bodyArea, JWT.APPEARANCE1
								| JWT.MIDDLE);
						textControl.addClientEventHandler(
								JWT.CLIENT_EVENT_DOCUMENT,
								"SaasEditTable.handleTextChange");
						editorType = "text";
						int maxLength = ((STextEditColumn) editColumn)
								.getMaxLength();
						if (maxLength > 0) {
							textControl.setMaximumLength(maxLength);
						}
					} else if (editColumn instanceof SDateEditColumn) {
						textControl = new SDatePicker(bodyArea);
						textControl.addClientMessageHandler("selection",
								"SaasEditTable.handleTextChange");
						editorType = "date";
					} else if (editColumn instanceof SNumberEditColumn) {
						SNumberEditColumn numberEditColumn = (SNumberEditColumn) editColumn;
						textControl = new Text(bodyArea, JWT.APPEARANCE1
								| JWT.MIDDLE | JWT.RIGHT);
						String regexp = "^([+]?\\d*?(\\.\\d*)?)$";
						if (numberEditColumn.isMinusEnable()) {
							regexp = "^([-+]?\\d*?(\\.\\d*)?)$";
						}
						if (numberEditColumn.getDecimal() > 0) {
							textControl.setRegExp(regexp);
						} else {
							textControl.setRegExp(regexp); // XXX:
						}
						int maxLength = numberEditColumn.getMaxLength();
						if (maxLength > 0) {
							textControl.setMaximumLength(maxLength);
						}
						textControl.addClientEventHandler(
								JWT.CLIENT_EVENT_DOCUMENT,
								"SaasEditTable.handleTextChange");
						textControl.addClientEventHandler(
								JWT.CLIENT_EVENT_FOCUS_LOST,
								"SaasEditTable.handleTextBlur");
						editorType = "number";
					} else if (editColumn instanceof SButtonEditColumn) {
						textControl = new Text(bodyArea, JWT.BUTTON
								| JWT.APPEARANCE1 | JWT.MIDDLE);
						textControl.addClientEventHandler(
								JWT.CLIENT_EVENT_DOCUMENT,
								"SaasEditTable.handleTextChange");
						textControl.addClientEventHandler(
								JWT.CLIENT_EVENT_ACTION,
								"SaasEditTable.handleTextButtonSelect");
						textControl.setEditable(false);
						final SButtonEditColumn buttonEditColumn = (SButtonEditColumn) editColumn;
						textControl
								.addClientNotifyListener(new ClientNotifyListener() {
									public void notified(ClientNotifyEvent e) {
										Text text = (Text) e.widget;
										buttonEditColumn
												.getHandler()
												.processButontClick(
														getContext(),
														text.getText(),
														text.getDescription(),
														new TextCellValueSetter(
																(Text) e.widget));
									}
								});
						editorType = "select";
					} else if (editColumn instanceof SListEditColumn) {
						LWComboList comboList = new LWComboList(bodyArea,
								JWT.BUTTON | JWT.APPEARANCE1 | JWT.MIDDLE);
						ListSourceAdapter source = ((SListEditColumn) editColumn)
								.getListSource();
						if (source == null) {
							source = defaultListSource;
						}
						comboList.getList().setSource(source);
						comboList.getList().setInput(null);
						textControl = comboList;
						textControl.addClientMessageHandler("selection",
								"SaasEditTable.handleTextChange"); // TODO：改成event
						comboList.setVisibleItemCount(10);
						comboList.pack();
						editorType = "combo";
					} else if (editColumn instanceof SListEdit2Column) {
						ComboList comboList = new ComboList(bodyArea,
								JWT.BUTTON | JWT.APPEARANCE1 | JWT.MIDDLE
										| editColumn.getAlign());
						int maxItemCount = ((SListEdit2Column) editColumn)
								.getMaxItemCount();
						for (int item = 0; item < maxItemCount; item++) {
							comboList.addItem(String.valueOf(item));
						}
						textControl = comboList;
						textControl.addClientMessageHandler("selection",
								"SaasEditTable.handleTextChange"); // TODO：改成event
						textControl.addClientEventHandler(
								JWT.CLIENT_EVENT_PANEL_POPUP,
								"SaasEditTable.handleListEditor2Popup");
						comboList.setVisibleItemCount(10);
						comboList.pack();
						editorType = "combo2";
					}
					textControl.setID(editColumn.getName());
					textControl.moveFirst();
					try {
						//
						columnInfo.put("index", new Integer(i));
						columnInfo.put("type", editorType);
						//
						JSONArray formulaArr = new JSONArray();
						columnInfo.put("formulas", formulaArr);
						//
						SFormula[] formulas = editColumn.getFormulas();
						if (formulas != null) {
							for (SFormula formula : formulas) {
								JSONObject formulaObj = new JSONObject();
								if (formula instanceof SAsignFormula) {
									formulaObj.put("type", "asign");
									formulaObj.put("target",
											((SAsignFormula) formula)
													.getTarget());
								} else if (formula instanceof SEvalFormula) {
									formulaObj.put("type", "eval");
								}
								formulaObj.put("express",
										formula.getExpressScript());
								formulaArr.put(formulaObj);
							}
						}
					} catch (JSONException e) {
					}
					textControl.setCustomObject("columnInfo", columnInfo);
					textControl.setCssClassName(JWT.CSS_SYSTEM_NONE_BORDER);
					textControl.setBackground(Color.COLOR_LIGHTBLUE);
					textControl.setVisible(false);
					textControl.addClientEventHandler(
							JWT.CLIENT_EVENT_KEY_DOWN,
							"SaasEditTable.handleTextKeydown");
				}
			}
		}
		// 处理自动增行风格
		if (tableStyle instanceof SEditTableStyle) {
			SEditTableStyle editTableStyle = (SEditTableStyle) tableStyle;
			if (editTableStyle.isAutoAddRow()) {
				autoAddRow = true;
				JSONObject tableInfo = hideArea
						.getCustomObject("editTableInfo");
				if (tableInfo == null) {
					tableInfo = new JSONObject();
					hideArea.setCustomObject("editTableInfo", tableInfo);
				}
				try {
					tableInfo.put("autoAddRow", true);
				} catch (JSONException e) {
				}
				hideArea.addClientNotifyListener(new ClientNotifyListener() { // XXX：可以做成异步事件
					public void notified(ClientNotifyEvent e) {
						JSONObject notityActionObject = hideArea
								.getCustomObject("notityActionObject");
						if (notityActionObject != null) {
							if (notityActionObject.has("autoAddRow")) {
								try {
									Object newObject = ((SEditContentProvider) contentProvider)
											.getNewElement();
									if (newObject != null) {
										addRow(newObject);
										renderUpate();
									}
								} catch (Throwable t) {
								} finally {
									notityActionObject.remove("autoAddRow");
									hideArea.setCustomObject(
											"notityActionObject",
											notityActionObject);
								}
							}
						}
					}
				});
			}
		}

	}

	public void render() {
		String[] rowIds = this.getAllRowId();
		if (rowIds != null) {
			for (String rowId : rowIds) {
				hideArea.setCustomObject(rowId, null);
			}
		}
		Control[] children = bodyArea.getChildren();
		for (int i = 0; i < children.length - 1; i++) {
			children[i].setVisible(false);
		}
		super.render();
	}

	/**
	 * 
	 * @param contentProvider
	 */
	public void setContentProvider(SEditContentProvider contentProvider) {
		if (autoAddRow) {
			contentProvider = new SEditContentProviderProxy(contentProvider);
		}
		super.setContentProvider(contentProvider);
		this.contentProvider = contentProvider;
	}

	/**
	 * 返回指定行和列的编辑值数据
	 * 
	 * @return
	 */
	public String[] getEditValue(String rowId, String... columnNames) {
		try {
			JSONObject row = hideArea.getCustomObject(rowId);
			JSONObject values = row.getJSONObject("values");
			String[] results = new String[columnNames.length];
			for (int i = 0; i < results.length; i++) {
				if (values.has(columnNames[i])) {
					JSONObject valueObj = values.getJSONObject(columnNames[i]);
					if (valueObj != null) {
						results[i] = valueObj.getString("value");
					}
				}
			}
			return results;
		} catch (Throwable t) {
			return null;
		}
	}

	/**
	 * 返回指定行的额外数据
	 * 
	 * @return
	 */
	public String[] getExtraData(String rowId, String... names) {
		String[] results = new String[names.length];
		try {
			JSONObject row = hideArea.getCustomObject(rowId);
			JSONObject extraObj = row.getJSONObject("extraData");
			if (extraObj != null) {
				for (int i = 0; i < names.length; i++) {
					if (extraObj.has(names[i])) {
						results[i] = extraObj.getString(names[i]);
					}
				}
			}
		} catch (Throwable t) {
		}
		return results;
	}

	/**
	 * 
	 * @param rowId
	 * @param columnName
	 * @param value
	 * @param text
	 */
	public void updateCell(String rowId, String columnName, String value,
			String text) {
		updateCell(rowId, columnName, value, text, -1);
	}

	/**
	 * 
	 * @param rowId
	 * @param columnName
	 * @param value
	 * @param text
	 */
	public void updateCell(String rowId, String columnName, String value,
			String text, int decimal) {
		try {
			//
			int columnIndex = -1;
			STableColumn column = null;
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].getName().equals(columnName)) {
					columnIndex = i;
					column = columns[i];
					break;
				}
			}
			if (columnIndex == -1) {
				throw new IllegalArgumentException();
			}
			if (decimal >= 0 && text != null) {
				updateCell(rowId, columnIndex, formatText(text, decimal));
			} else {
				updateCell(rowId, columnIndex, text);
			}

			//
			JSONObject row = hideArea.getCustomObject(rowId);
			JSONObject values = row.getJSONObject("values");
			JSONObject valueObj = null;
			if (values.has(columnName)) {
				valueObj = values.getJSONObject(columnName);
			} else {
				valueObj = new JSONObject();
				values.put(columnName, valueObj);
			}
			if (column instanceof SNumberEditColumn && decimal >= 0
					&& value != null) {
				fillFormattedValue(value, decimal, valueObj);
			} else {
				valueObj.put("value", value);
			}
			if (column instanceof SListEditColumn
					|| column instanceof SListEdit2Column
					|| column instanceof SButtonEditColumn) {
				valueObj.put("desc", text);
			}
			//
			hideArea.setCustomObject(rowId, row);
		} catch (Throwable t) {
		}
	}

	/**
	 * 
	 * @param rowId
	 * @param actionIds
	 */
	public void updateRowActions(String rowId, String[] actionIds) {
		JSONObject row = hideArea.getCustomObject(rowId);
		if (row != null) {
			JSONArray actions = new JSONArray();
			try {
				if (actionIds != null) {
					for (String actionId : actionIds) {
						actions.put(actionId);
					}
				}
			} catch (Throwable t) {
			}
			try {
				row.put("rowActions", actions);
			} catch (JSONException e) {
			}
		}
		hideArea.setCustomObject(rowId, row);
	}

	/**
	 * 
	 * @param rowId
	 * @param name
	 * @param value
	 */
	public void updateExtraData(String rowId, String name, String value) {
		try {
			JSONObject row = hideArea.getCustomObject(rowId);
			JSONObject extraObj = row.getJSONObject("extraData");
			if (extraObj == null) {
				extraObj = new JSONObject();
				row.put("extraData", extraObj);
			}
			extraObj.put(name, value);
			//
			hideArea.setCustomObject(rowId, row);
		} catch (Throwable t) {
		}
	}

	/**
	 * 
	 * @param rowData
	 */
	@Override
	protected void processAddRow(Object object) {
		processUpdateRow(object);
	}

	@Override
	protected void processUpdateRow(Object object) {
		SEditContentProvider editContentProvider = ((SEditContentProvider) this.contentProvider);
		JSONObject row = new JSONObject();
		try {
			JSONObject valuesObj = new JSONObject();
			row.put("values", valuesObj);
			JSONObject optionsObj = new JSONObject();
			row.put("options", optionsObj); //
			JSONArray decimals = new JSONArray();
			row.put("decimals", decimals);
			for (int i = 0; i < columns.length; i++) {
				int decimal = -1;
				if (this.labelProvider instanceof SNumberLabelProvider) {
					decimal = ((SNumberLabelProvider) this.labelProvider)
							.getDecimal(object, i);
					decimals.put(i, decimal);
				}
				try {
					String value = editContentProvider.getValue(object,
							columns[i].getName());
					if (value != null) {
						JSONObject valueObject = new JSONObject();
						if (columns[i] instanceof SNumberEditColumn
								&& decimal >= 0 && value != null) {
							fillFormattedValue(value, decimal, valueObject);
						} else {
							valueObject.put("value", value);
						}
						if (columns[i] instanceof SListEditColumn
								|| columns[i] instanceof SListEdit2Column
								|| columns[i] instanceof SButtonEditColumn) {
							valueObject.put("desc",
									this.labelProvider.getText(object, i));
						}
						valuesObj.put(columns[i].getName(), valueObject);
					}
				} catch (Throwable t) {
					t.printStackTrace();
				}
				try {
					if (editContentProvider instanceof SEditContentProvider2) {
						SNameValue[] options = ((SEditContentProvider2) editContentProvider)
								.getOptions(object, columns[i].getName());
						if (options != null) {
							JSONArray optionArray = new JSONArray();
							for (SNameValue option : options) {
								JSONObject optionObj = new JSONObject();
								optionObj.put("value", option.getName());
								optionObj.put("desc", option.getValue());
								optionArray.put(optionObj);
							}
							optionsObj.put(columns[i].getName(), optionArray);
						}
					}
				} catch (Throwable t) {
				}
			}//
			JSONArray actions = new JSONArray();
			try {
				String[] actionIds = editContentProvider.getActionIds(object);
				if (actionIds != null) {
					for (String actionId : actionIds) {
						actions.put(actionId);
					}
				}
			} catch (Throwable t) {
			}
			row.put("rowActions", actions);
			SNameValue[] extraDatas = editContentProvider.getExtraData(object);
			if (extraDatas != null && extraDatas.length > 0) {
				JSONObject extraObj = new JSONObject();
				for (int i = 0; i < extraDatas.length; i++) {
					extraObj.put(extraDatas[i].getName(),
							extraDatas[i].getValue());
				}
				row.put("extraData", extraObj);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		hideArea.setCustomObject(editContentProvider.getElementId(object), row);
	}

	@Override
	protected void processRemoveRow(String id) {
		hideArea.setCustomObject(id, null);
	}

	/**
	 * 标识编辑单元状态
	 * 
	 * @param rowId
	 * @param columnName
	 * @param hint
	 */
	public void markEditCellstatus(String rowId, String columnName, String hint) {
		// TODO：
	}

	public SEditColumn[] getEditColumns() {
		List<SEditColumn> columnList = new ArrayList<SEditColumn>();
		for (STableColumn column : columns) {
			if (column instanceof SEditColumn) {
				columnList.add((SEditColumn) column);
			}
		}
		return columnList.toArray(new SEditColumn[0]);
	}

	private void fillFormattedValue(String value, int decimal,
			JSONObject valueObj) {
		try {
			Double dv = DoubleUtil.strToDouble(value);
			valueObj.put("value", DoubleUtil.getRoundStrWithOutQfw(dv, decimal));
			valueObj.put("desc", DoubleUtil.getRoundStr(dv, decimal));
		} catch (Throwable t) {
			try {
				valueObj.put("value", value);
			} catch (JSONException e) {
			}
		}
	}

	private String formatText(String value, int decimal) {
		try {
			Double dv = DoubleUtil.strToDouble(value);
			return DoubleUtil.getRoundStr(dv, decimal);
		} catch (Throwable t) {
			return value;
		}
	}
}

class SEditContentProviderProxy implements SEditContentProvider {

	private SEditContentProvider prvoider;

	public SEditContentProviderProxy(SEditContentProvider prvoider) {
		this.prvoider = prvoider;
	}

	public Object[] getElements(Context context, STableStatus tablestatus) {
		Object[] elements = prvoider.getElements(context, tablestatus);
		if (elements == null || elements.length == 0) {
			return new Object[] { getNewElement() };
			// } else {
			// Object[] newArray = new Object[elements.length + 1];
			// System.arraycopy(elements, 0, newArray, 0, elements.length);
			// newArray[newArray.length - 1] = getNewElement();
			// return newArray;
		}
		return elements;
	}

	public String getElementId(Object element) {
		return prvoider.getElementId(element);
	}

	public boolean isSelected(Object element) {
		return prvoider.isSelected(element);
	}

	public boolean isSelectable(Object element) {
		return prvoider.isSelectable(element);
	}

	public String getValue(Object element, String columnName) {
		return prvoider.getValue(element, columnName);
	}

	public String[] getActionIds(Object element) {
		return prvoider.getActionIds(element);
	}

	public Object getNewElement() {
		return prvoider.getNewElement();
	}

	public SNameValue[] getExtraData(Object element) {
		return prvoider.getExtraData(element);
	}

}