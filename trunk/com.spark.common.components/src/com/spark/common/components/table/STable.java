package com.spark.common.components.table;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cxf.common.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Browser;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.excel.ExcelWriteHelperPoi;

/**
 * 轻量级表格控件
 */
public class STable extends Composite {

	/* 选择事件 */
	public final static String CLIENT_EVENT_SELECTION = "tableSelection";

	/* 表格准备完毕事件 */
	public final static String CLIENT_EVENT_INITED = "tableInited";

	/* 鼠标移动进入表格事件 */
	public final static String CLIENT_EVENT_MOUSEROVER = "tableMouseover";

	/* 鼠标移动出表格事件 */
	public final static String CLIENT_EVENT_MOUSEROUT = "tableMouseout";

	/* 点击行事件 */
	public final static String CLIENT_EVENT_ROWCLICK = "rowClick";

	/* 增加行事件 */
	public final static String CLIENT_EVENT_ROWADDED = "rowAdded";

	/* 修改行事件 */
	public final static String CLIENT_EVENT_ROWUPDATEED = "rowUpdated";

	/* 删除行事件 */
	public final static String CLIENT_EVENT_ROWREMOVEED = "rowRemoved";

	/* 双击行事件 */
	public final static String CLIENT_EVENT_TABLEDBLCLICK = "tabledblClick";

	/* 动作事件 */
	public final static String CLIENT_EVENT_ACTION = "tableAction";

	/* 版本号 */
	private final static String VERSIONS = "0.3";

	/* 出错的时候提醒用户的单元格颜色常量 */
	public final static Color WARNINGCOLOR = new Color(0xffebd6);
	/* 正常的时候提醒用户的单元格颜色常量 */
	public final static Color NORMALCOLOR = new Color(0xffffff);

	/* 每一列的表头消息 */
	protected STableColumn[] columns;

	/* 表格属性 */
	protected SContentProvider contentProvider;

	/* 表格的具体文本 */
	protected SLabelProvider labelProvider;

	/* 合并单元格 */
	protected SSpanProvider spanProvider;

	/* 表格的样式 */
	protected STableStyle tableStyle;

	/* 放表体的容器 */
	protected Browser contentBrowser;

	/* 放表头的容器 */
	protected Browser titleBrowser;

	/* 滚动条的内容容器,也就是除滚动条之外的容器 */
	protected Composite bodyArea;

	protected Composite headArea;

	/* 隐藏容器，用于存放客户端数据 */
	protected Composite hideArea;

	/* 存放action事件监听器 */
	private List<SActionListener> actionlisteners;

	/* 存放select选中事件监听器 */
	private List<SelectionListener> selectionlisteners;

	/* 排序字段 */
	private STableStatus sTableStatus;

	/* 滚动条容器 */
	private ScrolledPanel scrolledPanel;

	/* 用于判断是否已经渲染过表格,不然不能调用操作事件 */
	private boolean rendered;

	private Object[] tableRows;
	
	private List<Object> rowsAfterRender = new ArrayList<Object>();

	private JSONObject actualTableColumn;

	/**
	 * 构造方法
	 * 
	 * @param parent
	 *            容器
	 * @param columns
	 *            表格列信息
	 * @param tableStyle
	 *            表格的样式
	 */
	public STable(Composite parent, STableColumn[] columns, STableStyle tableStyle) {
		super(parent);
		this.columns = columns;
		this.tableStyle = tableStyle;
		this.sTableStatus = new STableStatus();

		// 添加表格的控件极其样式
		this.clear();
		// 表格总控件的样式
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		this.setLayout(gridLayout);
		CBorder border = new CBorder();
		border.setColor(0x78a9bf);
		this.setBorder(border);
		this.setBackground(new Color(0xFFFFFF));
		this.addClientEventHandler(JWT.CLIENT_EVENT_RESIZE, "STable.handleClientResize");
		this.addClientEventHandler(JWT.CLIENT_EVENT_INIT, "STable.handleClientInit");

		// 表头样式和控件初始化,客户端方法
		headArea = new Composite(this);
		GridData gdHeader = new GridData(GridData.FILL_HORIZONTAL);
		headArea.setLayoutData(gdHeader);
		titleBrowser = new Browser(headArea);
		titleBrowser.setHeight(tableStyle.getHeaderHeight());
		titleBrowser.setHandlerName("handleHeaderEvent");
		titleBrowser.addClientEventHandler(JWT.CLIENT_EVENT_ACTION, "STableHeader.handleClientAction");

		// 表体样式和控件初始化,客户端方法
		if (tableStyle.isNoScroll()) {
			scrolledPanel = new ScrolledPanel(this, JWT.NONE);
		} else {
			scrolledPanel = new ScrolledPanel(this);
		}
		scrolledPanel.setID("scrolledPanel");
		scrolledPanel.setLayoutData(GridData.INS_FILL_BOTH);
		bodyArea = scrolledPanel.getComposite();
		this.scrolledPanel.addClientEventHandler(JWT.CLIENT_EVENT_SCROLL, "STableScrolledPanel.handlePanelScrolled");
		this.scrolledPanel.addClientNotifyListener(new ClientNotifyListener() {
			
			
			public void notified(ClientNotifyEvent e) {
				// 翻页
				if (!STable.this.tableStyle.isPageAble()) return;
				sTableStatus.setPageNo(sTableStatus.getPageNo() + 1);
				Object[] objects = contentProvider.getElements(getContext(), sTableStatus);
				if (null == objects || objects.length < 1) return ;
				for (Object object : objects) {
					addRow(object);
					rowsAfterRender.add(object);
				}
				STable.this.renderUpate();
			}
		});
		
		// 设置隐藏控件的位置必须在最后一个，客户端方法
		hideArea = new Composite(this);
		GridData gdHide = new GridData();
		gdHide.exclude = true;
		hideArea.setLayoutData(gdHide);
		hideArea.setVisible(false);
		hideArea.addClientEventHandler(JWT.CLIENT_EVENT_SERVER_NOTIFY, "STableHide.handleServerNotify");
		// 注册监听器
		hideArea.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent event) {

				JSONObject actualColumn = hideArea.getCustomObject("actualColumn");
				// 排序
				JSONObject sort = hideArea.getCustomObject("sort");
				// 点击超链接
				JSONObject link = hideArea.getCustomObject("link");
				// 选择事件
				JSONObject selected = hideArea.getCustomObject("selected");
				if (null != link) {
					String actionName = null;
					String actionValue = null;
					String rowId = null;
					try {
						actionName = link.getString("actionName");
						actionValue = link.getString("actionValue");
						rowId = link.getString("rowId");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					fireActionEvent(rowId, actionName, actionValue);
					hideArea.setCustomObject("link", null);
				} else if (null != sort) {
					String column = null;
					String direction = null;
					try {
						column = sort.getString("column");
						direction = sort.getString("direction");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					sTableStatus.setSortColumn(column);
					if (direction != null) {
						if (direction.equals("ASC")) {
							sTableStatus.setSortDirection(SSortDirection.ASC);
						} else if (direction.equals("DESC")) {
							sTableStatus.setSortDirection(SSortDirection.DESC);
						}
					}
					render();
					hideArea.setCustomObject("sort", null);
				} else if (null != selected) {
					fireSelectionEvent();
					hideArea.setCustomObject("selected", null);
				} else if (null != actualColumn) {
					actualTableColumn = actualColumn;
					try {
						renderStart(actualColumn, actualColumn.getBoolean("isResize"));
					} catch (JSONException e) {
						e.printStackTrace();
					} finally {
						hideArea.setCustomObject("actualColumn", null);
					}

				}
			}

		});

	}

	/**
	 * 开始构建表格
	 * 
	 * @param actualColumn
	 * @param isResize
	 *            是否是重新调整大小
	 */
	private void renderStart(JSONObject actualColumn, boolean isResize) {
		setTableContent(actualColumn);
		// 第一次创建才会去构建表头,之后排序或者刷新都不会去重新刷新表头
		if (isResize || !rendered) {
			// 处理多重表头
			int number = 1;
			Set<String> tablemorecolumnName = new HashSet<String>();
			Map<String, Integer> tablemorecolumnmap = new HashMap<String, Integer>();
			for (int i = 0; i < STable.this.columns.length; i++) {
				STableColumn tableColumn = STable.this.columns[i];
				if (number < tableColumn.getTitles().length) {
					number = tableColumn.getTitles().length;
				}
				if (tableColumn.getTitles() != null && tableColumn.getTitles().length > 1) {
					String name = tableColumn.getTitles()[1];
					tablemorecolumnName.add(name);
					if (tablemorecolumnmap.get(name) == null) {
						tablemorecolumnmap.put(name, 1);

					} else {
						int title_Number = tablemorecolumnmap.get(name);
						title_Number++;
						tablemorecolumnmap.remove(name);
						tablemorecolumnmap.put(name, title_Number);
					}
				}
			}
			// 生成表头html
			try {
				setTableHeader(number, tablemorecolumnName, tablemorecolumnmap, actualColumn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		rendered = true;
		STable.this.layout();
	}

	/**
	 * 新增一行表格数据
	 * 
	 * @param object
	 */
	public void addRow(Object object) {
		try {
			operationRow(object, "add");
		} catch (Exception e) {
			e.printStackTrace();
		}
		processAddRow(object);
	}

	/**
	 * 新增多行表格数据
	 * 
	 * @param object
	 */
	public void addRows(Object[] object) {
		if (!rendered) {
			throw new IllegalStateException();
		}
		try {
			JSONObject operation = getOperation();
			operation.put("type", "addrows");
			StringBuffer buffer = new StringBuffer();
			JSONArray jsonArray = new JSONArray();
			operation.put("ids", jsonArray);
			for (Object obj : object) {
				String id = this.contentProvider.getElementId(obj);
				jsonArray.put(id);
				// buffer.append(getRowHtml(obj, id, actualTableColumn));
				buffer.append(getContainsItemsRowHtml(obj, id, actualTableColumn));
			}
			if (buffer.length() > 0) {
				operation.put("rowDatas", buffer.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		processAddRows(object);
	}

	/**
	 * 修改一行表格数据
	 * 
	 * @param object
	 */
	public void updateRow(Object object) {
		try {
			operationRow(object, "update");
		} catch (Exception e) {
			e.printStackTrace();
		}
		processUpdateRow(object);
	}

	/**
	 * 修改一列数据
	 * 
	 * @param column
	 * @param row
	 * @param text
	 */
	public void updateCell(String rowId, int column, String text) {
		setUpdateCellValue(rowId, column, text, null);
	}

	/**
	 * 修改一列数据,并且设置提示ToolTip
	 * 
	 * @param column
	 * @param row
	 * @param text
	 */
	public void updateCellAndToolTip(String rowId, int column, String text, String toolTipText) {
		setUpdateCellValue(rowId, column, text, toolTipText);
	}

	/**
	 * 设置一列的数据，提示title为可选
	 * 
	 * @param rowId
	 * @param column
	 * @param text
	 * @param toolTipText
	 */
	private void setUpdateCellValue(String rowId, int column, String text, String toolTipText) {
		if (!rendered) {
			throw new IllegalStateException();
		}
		try {
			JSONObject operation = getOperation();
			operation.put("type", "updatecell");
			operation.put("id", rowId);
			operation.put("column", column); // 包括Tr的所有html
			operation.put("text", StringUtils.isEmpty(text) ? " " : text);
			operation.put("toolTipText", toolTipText);
		} catch (Exception t) {
			t.printStackTrace();
		}
		processUpdateCell(rowId, column, text);
	}

	/**
	 * 操作一行表格数据
	 * 
	 * @param object
	 * @param updateType
	 *            操作类型
	 */
	private void operationRow(Object object, String updateType) throws Exception {
		if (!rendered) {
			throw new IllegalStateException();
		}
		JSONObject operation = getOperation();
		String id = this.contentProvider.getElementId(object);
		operation.put("type", updateType);
		operation.put("id", id);
		// operation.put("rowData", getRowHtml(object, id, actualTableColumn));
		operation.put("rowData", getContainsItemsRowHtml(object, id, actualTableColumn));
	}

	/**
	 * 删除一行数据
	 * 
	 * @param id
	 */
	public void removeRow(String id) {
		if (!rendered) {
			throw new IllegalStateException();
		}
		try {
			JSONObject operation = getOperation();
			operation.put("type", "remove");
			operation.put("id", id);
			// TODO 
			// 这里要删除rowsAfterRender里相应的记录
		} catch (Exception e) {
			e.printStackTrace();
		}
		processRemoveRow(id);
	}

	/**
	 * 获取表格数据的json对象
	 * 
	 * @return
	 * @throws JSONException
	 */
	private JSONObject getOperation() throws JSONException {
		JSONObject rowOperations = hideArea.getCustomObject("rowOperations");
		JSONArray operationList = rowOperations.getJSONArray("operationList");
		JSONObject operation = new JSONObject();
		operationList.put(operation);
		hideArea.setCustomObject("rowOperations", rowOperations);
		return operation;
	}

	/**
	 * 构建表体的内容TR
	 * 
	 * @param object
	 * @param id
	 * @return
	 */
	private String getRowHtml(Object object, String id, JSONObject actualColumn) throws JSONException {
		StringBuffer buffer = new StringBuffer();
		JSONArray jsonArray = actualColumn.getJSONArray("widths");
		int arrayNumber = 0;
		buffer.append("<tr id='" + id + "' style='height:" + tableStyle.getRowHeight() + "'>");
		if (tableStyle.getSelectionMode() == SSelectionMode.Multi) {
			buffer.append("<td class='tdCenter' width='" + jsonArray.getInt(arrayNumber)
					+ "' ><input name='checkbox1' type='checkbox' ");
			if (!this.contentProvider.isSelectable(object)) {
				buffer.append(" disabled='false'");
			}
			if (this.contentProvider.isSelected(object)) {
				buffer.append(" checked='true'");
				// selectionsIds选中的行ID
				/*
				 * try { JSONObject jsonObject = hideArea
				 * .getCustomObject("selections"); JSONArray selectionsIds =
				 * jsonObject.getJSONArray("ids"); selectionsIds.put(id);
				 * hideArea.setCustomObject("selections", jsonObject); } catch
				 * (Exception e) { e.printStackTrace(); }
				 */
			}
			buffer.append("></input></td>");
			arrayNumber++;
		} else if (tableStyle.getSelectionMode() == SSelectionMode.Single) {
			// 单选
			buffer.append(" <td class='tdCenter' width='" + jsonArray.getInt(arrayNumber)
					+ "'><input name='radio1' type='radio' ");
			if (this.contentProvider.isSelected(object)) {
				buffer.append(" checked='true'");
				// selectionsIds选中的行ID
				/*
				 * try { JSONObject jsonObject = hideArea
				 * .getCustomObject("selections"); JSONArray selectionsIds =
				 * jsonObject.getJSONArray("ids"); selectionsIds.put(id);
				 * hideArea.setCustomObject("selections", jsonObject); } catch
				 * (Exception e) { e.printStackTrace(); }
				 */

			}
			buffer.append("></input></td>");
			arrayNumber++;
		}
		int colSpan = 1;
		// 数组保存行方向合并的那一列，以及合并多少行，第一个参数表示合并多少行，第二个参数表示是那一列
		int[] spanRowSpan = new int[2];
		for (int i = 0; i < columns.length; i++) {
			STableColumn sTableColumn = columns[i];
			if (colSpan > 1) {
				colSpan--;
				continue;
			}

			if (spanRowSpan[0] != 0 && spanRowSpan[1] != 0) {
				if (spanRowSpan[1] == i) {
					spanRowSpan[0]--;
					continue;
				}
			}

			buffer.append("<td ");
			int width = jsonArray.getInt(i + arrayNumber);
			if (this.spanProvider != null) {
				colSpan = this.spanProvider.getColSpan(object, i);
				if (colSpan != 0) {
					width = 0;
					for (int n = 0; n < colSpan; n++) {
						width += jsonArray.getInt(i + arrayNumber + n);
					}
					buffer.append(" colspan=\"" + colSpan + "\" ");
				}

				spanRowSpan[0] = this.spanProvider.getRowSpan(object, i);
				if (spanRowSpan[0] != 0) {
					buffer.append(" rowspan=\"" + spanRowSpan[0] + "\" ");
					spanRowSpan[0]--;
					spanRowSpan[1] = i;
				}
			}

			if (sTableColumn.getAlign() == JWT.LEFT) {
				buffer.append("style='text-align:left; ");
			} else if (sTableColumn.getAlign() == JWT.CENTER) {
				buffer.append("style='text-align:center; ");
			} else if (sTableColumn.getAlign() == JWT.RIGHT) {
				buffer.append("style='text-align:right; ");
			}
			buffer.append(getBorderRightWidth(actualColumn, i));
			buffer.append(" ");
			buffer.append(" width:" + width + "' ");

			Color background = labelProvider.getBackground(object, i);
			if (background != null) {
				buffer.append(" style='background-color:" + background.toString() + "' ");
			}
			if (sTableColumn.isSearch()) {
				buffer.append(" class='search' ");
			}
			buffer.append(" >");
			String text = labelProvider.getText(object, i);
			//
			if (labelProvider instanceof SNumberLabelProvider) {
				int decimal = ((SNumberLabelProvider) labelProvider).getDecimal(object, i);
				if (decimal >= 0 && text != null) {
					try {
						Double dv = DoubleUtil.strToDouble(text); // 如果不是数字，会出现异常
						text = DoubleUtil.getRoundStr(dv, decimal);
					} catch (Throwable t) {

					}
				}
			}
			buffer.append("<div style='width:" + (jsonArray.getInt(i + arrayNumber) - 10) + "' ><font ");

			if (text != null) {
				String tooltip = labelProvider.getToolTipText(object, i);
				if (tooltip != null && tooltip.length() > 0) {
					buffer.append(" title='" + tooltip + "' ");
				}
			}

			Color foreground = labelProvider.getForeground(object, i);
			if (foreground != null) {
				buffer.append(" style='color:" + foreground.toString() + "' ");
			}

			buffer.append(" >");
			buffer.append(StringUtils.isEmpty(text) ? "&nbsp;" : text);
			buffer.append("</font></div>");
			buffer.append("</td>");
		}
		buffer.append("</tr>");
		return buffer.toString();
	}

	/**
	 * 取得组装一行有多个条目的HTML
	 * 
	 * @param object
	 *            该行的显示对象
	 * @param id
	 *            id
	 * @param actualColumn
	 * 
	 * @return
	 * @throws JSONException
	 */
	private String getContainsItemsRowHtml(Object object, String id, JSONObject actualColumn) throws JSONException {
		StringBuffer buffer = new StringBuffer();
		JSONArray colWidthArray = actualColumn.getJSONArray("widths");
		int colWidthIndex = 0;
		int itemCount = 0;
		if (this.spanProvider != null) {
			itemCount = this.spanProvider.getMaxRowSpan(object);
		}
		buffer.append("<tr id='" + id + "' style='height:" + tableStyle.getRowHeight() + "'>");
		String selectionModeHtml = "";
		if (tableStyle.getSelectionMode() == SSelectionMode.Multi
				|| tableStyle.getSelectionMode() == SSelectionMode.Single) {
			// buffer.append(getSelectModelUnit(object,
			// tableStyle.getSelectionMode(),
			// colWidthArray.getInt(colWidthIndex), itemCount));
			selectionModeHtml = getSelectModelUnit(object, tableStyle.getSelectionMode(), colWidthArray
					.getInt(colWidthIndex), itemCount);
			colWidthIndex++;
		}

		StringBuffer columnContentHtml = new StringBuffer();
		boolean isNeedsShowSelection = true;
		for (int i = 0; i < columns.length; i++) {
			if (spanProvider != null && spanProvider.isSpanAlready(object, i)) {
				// 合并的行不需要显示选择列
				isNeedsShowSelection = false;
				continue;
			}
			STableColumn sTableColumn = columns[i];
			int width = colWidthArray.getInt(i + colWidthIndex);
			String borderRigthHtml = getBorderRightWidth(actualColumn, i);
			int rowSpan = spanProvider == null ? 0 : spanProvider.getRowSpan(object, i);
			columnContentHtml.append(getColumnUnitHtml(sTableColumn, i, object, width, borderRigthHtml, rowSpan));
		}
		if (isNeedsShowSelection) {
			buffer.append(selectionModeHtml);
		}
		buffer.append(columnContentHtml);
		buffer.append("</tr>");
		return buffer.toString();
	}

	private int getTotalRowItemCount() {
		return tableRows.length;
		// if (null == spanProvider) {
		// return tableRows.length;
		// }
		// int totalRowItemCount = 0;
		// for(Object object : tableRows) {
		// totalRowItemCount += spanProvider.getItems(object).length;
		// }
		// return totalRowItemCount;
	}

	/**
	 * 获取生成选择框单元格的HTML代码
	 * 
	 * @param object
	 *            该行显示的对象
	 * @param selectionMode
	 *            表格的选择模式
	 * @param selectionColWidth
	 *            选择单元格的宽度
	 * @param rowSpan
	 *            行合并数
	 * @return
	 * @throws JSONException
	 */
	private String getSelectModelUnit(Object object, SSelectionMode selectionMode, int selectionColWidth, int rowSpan)
			throws JSONException {
		StringBuffer buffer = new StringBuffer();
		if (selectionMode == SSelectionMode.Multi) {
			buffer.append("<td rowSpan='" + rowSpan + "' class='tdCenter' width='" + selectionColWidth
					+ "' ><input name='checkbox1' type='checkbox' ");
			if (!this.contentProvider.isSelectable(object)) {
				buffer.append(" disabled='false'");
			}
			if (this.contentProvider.isSelected(object)) {
				buffer.append(" checked='true'");
			}
			buffer.append("></input></td>");
		} else if (selectionMode == SSelectionMode.Single) {
			// 单选
			buffer.append(" <td rowSpan='" + rowSpan + "' class='tdCenter' width='" + selectionColWidth
					+ "'><input name='radio1' type='radio' ");
			if (this.contentProvider.isSelected(object)) {
				buffer.append(" checked='true'");
			}
			buffer.append("></input></td>");
		}
		return buffer.toString();
	}

	/**
	 * 获取生成单元格的HTML代码
	 * 
	 * @param sTableColumn
	 *            列对象
	 * @param columnIndex
	 *            列序号
	 * @param object
	 *            该行的显示对象
	 * @param columnWidth
	 *            列宽
	 * @param borderRigthHtml
	 *            单元格右边框
	 * @param rowSpan
	 *            行合并数(0表示不合并)
	 * @return
	 */
	private String getColumnUnitHtml(STableColumn sTableColumn, int columnIndex, Object object, int columnWidth,
			String borderRigthHtml, int rowSpan) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<td ");
		if (rowSpan > 0) {
			buffer.append(" rowspan=\"" + rowSpan + "\" ");
		}
		if (sTableColumn.getAlign() == JWT.LEFT) {
			buffer.append("style='text-align:left; ");
		} else if (sTableColumn.getAlign() == JWT.CENTER) {
			buffer.append("style='text-align:center; ");
		} else if (sTableColumn.getAlign() == JWT.RIGHT) {
			buffer.append("style='text-align:right; ");
		}
		buffer.append(borderRigthHtml);
		buffer.append(" ");
		buffer.append(" width:" + columnWidth + "' ");

		Color background = labelProvider.getBackground(object, columnIndex);
		if (background != null) {
			buffer.append(" style='background-color:" + background.toString() + "' ");
		}
		if (sTableColumn.isSearch()) {
			buffer.append(" class='search' ");
		}
		buffer.append(" >");
		String text = labelProvider.getText(object, columnIndex);
		//
		if (labelProvider instanceof SNumberLabelProvider) {
			int decimal = ((SNumberLabelProvider) labelProvider).getDecimal(object, columnIndex);
			if (decimal >= 0 && text != null) {
				try {
					Double dv = DoubleUtil.strToDouble(text); // 如果不是数字，会出现异常
					text = DoubleUtil.getRoundStr(dv, decimal);
				} catch (Throwable t) {

				}
			}
		}
		buffer.append("<div style='width:" + (columnWidth - 10) + "' ><font ");

		if (text != null) {
			String tooltip = labelProvider.getToolTipText(object, columnIndex);
			if (tooltip != null && tooltip.length() > 0) {
				buffer.append(" title='" + tooltip + "' ");
			}
		}

		Color foreground = labelProvider.getForeground(object, columnIndex);
		if (foreground != null) {
			buffer.append(" style='color:" + foreground.toString() + "' ");
		}

		buffer.append(" >");
		buffer.append(StringUtils.isEmpty(text) ? "&nbsp;" : text);
		buffer.append("</font></div>");
		buffer.append("</td>");
		return buffer.toString();
	}

	private String getBorderRightWidth(JSONObject actualColumn, int i) throws JSONException {
		// 判断是否有滚动条，如果没有，那边，最后一列的单元格没有右边框
		if (i == columns.length - 1) {
			if (actualColumn.getBoolean("isScrollbar")) {
				return " border-right-width:1px; ";
			} else {
				return " border-right-width:0px; ";
			}
		}
		return " ";
	}

	/**
	 * 对表格进行操作之后，调用该方法使得操作生效
	 */
	public void renderUpate() {
		if (!rendered) {
			throw new IllegalStateException();
		}
		try {
			// rowids 所有的行ID
			JSONObject rowids = hideArea.getCustomObject("rowIds");
			JSONArray jsonArray = rowids.getJSONArray("rowIdArray");
			// selectionsIds 选中的行ID
			JSONObject jsonObject = hideArea.getCustomObject("selections");
			JSONArray selectionsIds = jsonObject.getJSONArray("ids");
			// 本次操作的行队列
			JSONObject rowOperations = hideArea.getCustomObject("rowOperations");
			JSONArray operationList = rowOperations.getJSONArray("operationList");

			for (int i = 0; i < operationList.length(); i++) {
				JSONObject operation = operationList.getJSONObject(i);
				String type = operation.getString("type");

				// 更新rowIdArray
				if (type != null && type.length() > 0) {
					if (type.equalsIgnoreCase("add")) {
						String id = operation.getString("id");
						String rowData = operation.getString("rowData"); // 包括Tr的所有html
						jsonArray.put(id);
						// 得到对象是否选中，并在selection增加
						if (rowData.indexOf("checked='true'") != -1) {
							selectionsIds.put(id);
						}
					} else if (type.equalsIgnoreCase("remove")) {
						// 得到对象是否选中，并可能删除selection
						// 当前ID是否存在于jsonobject里面
						boolean isremove = false;
						String id = operation.getString("id");
						for (int number = 0; number < selectionsIds.length(); number++) {
							String arrayId = selectionsIds.getString(number);
							if (arrayId.equalsIgnoreCase(id)) {
								selectionsIds.remove(number);
								isremove = true;
							}

						}
						for (int number = 0; number < jsonArray.length(); number++) {
							String arrayId = jsonArray.getString(number);
							if (arrayId.equalsIgnoreCase(id)) {
								jsonArray.remove(number);
								isremove = true;
							}

						}
						// ID不存在，那么就不需要传到客户端去做删除操作
						if (!isremove) {
							operationList.remove(i);
						}
					} else if (type.equalsIgnoreCase("update")) {
						String id = operation.getString("id");
						String rowData = operation.getString("rowData");
						// 得到对象选中，并增加selection
						if (rowData.indexOf("checked='true'") != -1) {
							boolean isselected = false;
							for (int number = 0; number < selectionsIds.length(); number++) {
								String arrayId = selectionsIds.getString(number);
								if (arrayId.equalsIgnoreCase(id)) {
									isselected = true;
									break;
								}
							}
							if (isselected) {
								selectionsIds.put(id);
							}
						} else {
							// 得到对象没有选中，并删除selection
							for (int number = 0; number < selectionsIds.length(); number++) {
								String arrayId = selectionsIds.getString(number);
								if (arrayId.equalsIgnoreCase(id)) {
									selectionsIds.remove(number);
								}
							}
						}
					} else if (type.equalsIgnoreCase("addrows")) {
						String rowDatas = operation.getString("rowDatas"); // 包括Tr的所有html
						String[] rows = rowDatas.split("</tr>");
						JSONArray array = operation.getJSONArray("ids");
						for (int n = 0; n < array.length(); n++) {
							jsonArray.put(array.getString(n));

						}
						for (int n = 0; n < rows.length; n++) {
							String str = rows[n];
							// 得到对象是否选中，并在selection增加
							if (str.indexOf("checked='true'") != -1) {
								selectionsIds.put(array.getString(n));
							}
						}
					}
				}
			}
			hideArea.setCustomObject("rowIds", rowids);
			hideArea.setCustomObject("selections", jsonObject);
			hideArea.setCustomObject("rowOperations", rowOperations);
		} catch (JSONException e) {
			e.printStackTrace();
			return;
		}
		// 调用客户端方法
		hideArea.notifyClientAction();
	}

	/**
	 * 添加表格事件监听器
	 * 
	 * @param listener
	 */
	public void addActionListener(SActionListener listener) {
		hideArea.setCustomObject("actionregistered", new JSONObject());
		if (this.actionlisteners == null) {
			this.actionlisteners = new ArrayList<SActionListener>(3);
		}
		this.actionlisteners.add(listener);
	}

	/**
	 * 删除表格事件监听器
	 * 
	 * @param listener
	 */
	public void removeActionListener(SActionListener listener) {
		if (this.actionlisteners != null) {
			this.actionlisteners.remove(listener);
			if (actionlisteners.size() == 0) {
				hideArea.setCustomObject("actionregistered", null);
			}
		}
	}

	/**
	 * 添加选择监听器
	 * 
	 * @param listener
	 */
	public void addSelectionListener(SelectionListener listener) {
		hideArea.setCustomObject("selectregistered", new JSONObject());
		if (this.selectionlisteners == null) {
			this.selectionlisteners = new ArrayList<SelectionListener>(3);
		}
		this.selectionlisteners.add(listener);
	}

	/**
	 * 删除选择监听器
	 * 
	 * @param listener
	 */
	public void removeSelectionListener(SelectionListener listener) {
		if (this.selectionlisteners != null) {
			this.selectionlisteners.remove(listener);
			if (this.selectionlisteners.size() == 0) {
				hideArea.setCustomObject("selectregistered", null);
			}
		}
	}

	/**
	 * 表格事件发生的时候发出消息
	 * 
	 * @param rowId
	 * @param actionName
	 * @param actionValue
	 */
	protected final void fireActionEvent(String rowId, String actionName, String actionValue) {
		if (actionlisteners == null) {
			return;
		}
		for (SActionListener listener : actionlisteners) {
			listener.actionPerformed(rowId, actionName, actionValue);
		}
	}

	/**
	 * 选择事件发生的时候发出消息
	 */
	protected final void fireSelectionEvent() {
		if (selectionlisteners == null) {
			return;
		}
		for (SelectionListener listener : selectionlisteners) {
			listener.widgetSelected(new SelectionEvent(this));
		}
	}

	/**
	 * 返回所以表格的所有的行的唯一标识
	 * 
	 * @return
	 */
	public String[] getAllRowId() {
		JSONObject jsonObject = hideArea.getCustomObject("rowIds");
		if (jsonObject == null) {
			return null;
		}
		String[] rowids = null;
		try {
			JSONArray rowId = jsonObject.getJSONArray("rowIdArray");
			rowids = new String[rowId.length()];
			for (int i = 0; i < rowId.length(); i++) {
				rowids[i] = rowId.getString(i);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rowids;
	}
	
	/**
	 * 取得当前表格中所有的数据
	 * @return
	 */
	public List<Object> getAllRows() {
		List<Object> allRowsList = new ArrayList<Object>();
		for (Object object : tableRows) {
			allRowsList.add(object);
		}
		allRowsList.addAll(rowsAfterRender);
		return allRowsList;
	}

	/**
	 * 获取单选的选中项的唯一标识
	 * 
	 * @return
	 */
	public String getSelection() {
		String[] ids = this.getSelections();
		if (ids != null && ids.length > 0) {
			return ids[0];
		}
		return null;
	}

	/**
	 * 获取多选的选中项的唯一标识
	 * 
	 * @return
	 */
	public String[] getSelections() {
		JSONObject jsonObject = hideArea.getCustomObject("selections");
		if (jsonObject == null) {
			return null;
		}
		try {
			JSONArray values = jsonObject.getJSONArray("ids");
			if (values != null && values.length() > 0) {
				String[] ids = new String[values.length()];
				for (int i = 0; i < ids.length; i++) {
					ids[i] = values.getString(i);
				}
				return ids;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置表格内容的供应者
	 * 
	 * @param contentProvider
	 *            the contentProvider to set
	 */
	public void setContentProvider(SContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}

	/**
	 * 设置表格显示文本的供应者
	 * 
	 * @param labelProvider
	 *            the labelProvider to set
	 */
	public void setLabelProvider(SLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	/**
	 * 设置合并单元格的供应者
	 * 
	 * @param spanProvider
	 */
	public void setSpanProvider(SSpanProvider spanProvider) {
		this.spanProvider = spanProvider;
	}

	/**
	 * 渲染表格，渲染表格之前请确定已进行以下操作: 1.设置构造器需要的STableStyle，不然取不到表头信息，不能渲染出表头
	 * 2.实现getElements方法，不然取不到表体信息，不能渲染出表体
	 * 3. 将清空分页信息，从第一页开始显示
	 */
	public void render() {
		/*
		 * if (columns == null) { //
		 * this.contentBrowser.setHTML("<p>表头参数不能为空</p>"); return; } if
		 * (contentBrowser != null && !contentBrowser.isDisposed()) {
		 * contentBrowser.dispose(); }
		 * 
		 * // 表体控件添加客户端方法 contentBrowser = new Browser(bodyArea);
		 * contentBrowser.setHandlerName("handleEvent");
		 * contentBrowser.addClientEventHandler(JWT.CLIENT_EVENT_ACTION,
		 * "STableBody.handleClientAction");
		 * contentBrowser.addClientEventHandler(JWT.CLIENT_EVENT_INIT,
		 * "STableBody.handleInited");
		 */
		sTableStatus.resetPageInfo();
		// 初始化或者清空客户端需要处理的数据
		try {
			JSONObject rowOperations = new JSONObject();
			rowOperations.put("operationList", new JSONArray());
			hideArea.setCustomObject("rowOperations", rowOperations);
			//
			JSONObject jsonObject = new JSONObject();
			JSONArray selectionsIds = new JSONArray();
			jsonObject.put("ids", selectionsIds);
			hideArea.setCustomObject("selections", jsonObject);
			//
			JSONObject rowids = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			hideArea.setCustomObject("rowIds", rowids);
			rowids.put("rowIdArray", jsonArray);

		} catch (Throwable t) {
			t.printStackTrace();
		}

		// 添加给客户端使用的数据
		try {
			JSONObject tableInfo = new JSONObject();
			tableInfo.put("selctionMode", this.tableStyle.getSelectionMode());
			tableInfo.put("rowHeight", this.tableStyle.getRowHeight());
			JSONArray columnArray = new JSONArray();
			tableInfo.put("columns", columnArray);
			for (int i = 0; i < columns.length; i++) {
				JSONObject columnObject = new JSONObject();
				columnArray.put(columnObject);
				columnObject.put("width", columns[i].getWidth());
				columnObject.put("name", columns[i].getName());
				columnObject.put("grab", columns[i].isGrab());
				columnObject.put("align", columns[i].getAlign());
			}

			tableRows = this.contentProvider.getElements(getContext(), sTableStatus);
			if (tableRows != null) {
				tableInfo.put("rowLength", tableRows.length);
				// XXX：表格重构后，会对之前程序执行的逻辑有所影响，这里先简单的解决一下对getSelection的修正
				for (Object o : tableRows) {
					if (this.contentProvider.isSelected(o)) {
						try {
							JSONObject jsonObject = hideArea.getCustomObject("selections");
							JSONArray selectionsIds = jsonObject.getJSONArray("ids");
							selectionsIds.put(this.contentProvider.getElementId(o));
							hideArea.setCustomObject("selections", jsonObject);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				tableInfo.put("rowLength", 0);
			}

			hideArea.setCustomObject("tableInfo", tableInfo);
			rowsAfterRender.clear();
		} catch (JSONException t) {
			t.printStackTrace();
		}

		if (actualTableColumn != null) {
			renderStart(actualTableColumn, false);
		}
	}

	/**
	 * 设置表头
	 * 
	 * @param rowNumber
	 *            表头有多少行
	 * 
	 */
	private void setTableHeader(int rowNumber, Set<String> tablemorecolumnName,
			Map<String, Integer> tablemorecolumnmap, JSONObject actualColumn) throws Exception {
		if (rowNumber <= 0) {
			rowNumber = 1;
		}
		if (titleBrowser != null && !titleBrowser.isDisposed()) {
			titleBrowser.dispose();
		}
		titleBrowser = new Browser(this.headArea);
		titleBrowser.setHeight(tableStyle.getHeaderHeight());
		titleBrowser.setHandlerName("handleHeaderEvent");
		titleBrowser.addClientEventHandler(JWT.CLIENT_EVENT_ACTION, "STableHeader.handleClientAction");

		JSONArray jsonArray = actualColumn.getJSONArray("widths");
		int arrayNumber = 0;
		int height = tableStyle.getHeaderHeight() * rowNumber;
		StringBuffer buffer = new StringBuffer("");
		buffer.append("<script src=\"/saas_grid/STableHeader_Browser.js?v=" + VERSIONS + "\"></script>");
		buffer.append("<script src=\"/saas_grid/jquery.js\"></script>");
		buffer.append("<link rel='stylesheet' href='/saas_grid/STable.css'> ");

		buffer.append(" <html style='overflow:hidden;'><body unselectable=\"on\" " + "onselectstart=\"return false\""
				+ " style=\"-moz-user-select:none;\"" + " oncontextmenu=self.event.returnValue=false"
				+ " onclick='table_title_Click(event)'" + " bottommargin=\"0\"" + " topmargin=\"0\""
				+ " leftmargin=\"0\"" + " rightmargin=\"0\"" + " scroll='no'>");

		buffer.append("<table id='title_table' height='" + height + "px' cellspacing=0 cellpadding=0 > ");
		buffer.append(" <tr style='height:" + tableStyle.getHeaderHeight() + "px' > ");
		if (tableStyle.getSelectionMode() == SSelectionMode.Multi) {
			// 多选
			buffer.append(" <td  rowspan='" + rowNumber + "' width='" + jsonArray.getInt(arrayNumber)
					+ "' ><input name='checkbox1' type='checkbox'></input></td>");
			arrayNumber++;
		} else if (tableStyle.getSelectionMode() == SSelectionMode.Single) {
			// 单选
			buffer
					.append(" <td  rowspan='" + rowNumber + "' width='" + jsonArray.getInt(arrayNumber)
							+ "'>&nbsp;</td>");
			arrayNumber++;
		}
		List<Integer> columnWidthList = new ArrayList<Integer>();
		for (int i = 0; i < columns.length; i++) {
			STableColumn sTableColumn = columns[i];
			String[] titles = sTableColumn.getTitles();
			if (titles.length > 1) {
				// 复杂表头
				if (tablemorecolumnName != null && tablemorecolumnName.size() > 0) {
					for (String title : tablemorecolumnName) {
						if (titles[1].equalsIgnoreCase(title)) {
							tablemorecolumnName.remove(title);
							int colspan = tablemorecolumnmap.get(title);
							buffer.append("<td ");
							int width = 0;
							for (int j = 0; j < colspan; j++) {
								width += jsonArray.getInt(i + arrayNumber);
								columnWidthList.add(jsonArray.getInt(i + arrayNumber));
							}
							i += colspan - 1;
							buffer.append(" style = ' ");
							buffer.append(getBorderRightWidth(actualColumn, i));
							buffer.append(" ' ");
							buffer.append(" width='" + width + "' ");
							buffer.append(" colspan='" + colspan + "' > " + title);
							buffer.append("</td> ");

							break;
						}
					}
				}
			} else {
				// 简单表头
				buffer.append("<td width='" + jsonArray.getInt(i + arrayNumber) + "' id='" + sTableColumn.getName()
						+ "'  ");
				if (sTableColumn.isSortable() || this.tableStyle.isSortAll()) {
					buffer.append(" style = 'cursor: pointer;  ");
				} else {
					buffer.append(" style = ' ");
				}
				buffer.append(getBorderRightWidth(actualColumn, i));
				buffer.append(" ' ");
				buffer.append(" rowspan='" + rowNumber + "'>" + titles[0]);
				buffer.append("</td> ");
			}

		}
		if (actualColumn.getBoolean("isScrollbar")) {
			buffer.append("<td id='hidetd' style='width:17px;border-width:0px 0px 0px 0px;'>&nbsp;</td>");
		}
		buffer.append(" </tr> ");
		// 负责表头需要特别处理
		for (int m = 1; m < rowNumber; m++) {
			int columnWidthNumber = 0;
			buffer.append(" <tr height='" + tableStyle.getHeaderHeight() + "px'> ");
			for (int i = 0; i < columns.length; i++) {
				STableColumn tableColumn = columns[i];
				if (tableColumn.getTitles() != null && tableColumn.getTitles().length > 1) {
					buffer.append("<td width='" + columnWidthList.get(columnWidthNumber) + "'  id='"
							+ tableColumn.getName() + "'   ");
					if (tableColumn.isSortable() || tableStyle.isSortAll()) {
						buffer.append(" style='cursor: pointer; ");
					} else {
						buffer.append(" style = ' ");
					}
					buffer.append(getBorderRightWidth(actualColumn, i));
					buffer.append(" ' ");
					buffer.append(" >" + tableColumn.getTitles()[0]);
					buffer.append("</td> ");
					columnWidthNumber++;
				}
			}
			buffer.append(" </tr> ");
		}

		buffer.append("</table></body></html>");
		this.titleBrowser.applyHTML(buffer.toString());
		titleBrowser.setHeight(height);
		headArea.setHeight(height);

		try {
			int headerActualWidth = actualColumn.getInt("headerActualWidth");
			this.titleBrowser.setWidth(headerActualWidth);
			this.headArea.setWidth(headerActualWidth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置表体
	 */
	private void setTableContent(JSONObject actualColumn) {

		if (contentBrowser != null && !contentBrowser.isDisposed()) {
			contentBrowser.dispose();
		}

		// 表体控件添加客户端方法
		contentBrowser = new Browser(bodyArea);
		contentBrowser.setHandlerName("handleEvent");
		contentBrowser.addClientEventHandler(JWT.CLIENT_EVENT_ACTION, "STableBody.handleClientAction");
		contentBrowser.addClientEventHandler(JWT.CLIENT_EVENT_INIT, "STableBody.handleInited");
		StringBuffer buffer = new StringBuffer("");
		buffer.append("<script src=\"/saas_grid/STableBody_Browser.js?v=" + VERSIONS + "\"></script>");
		buffer.append("<script src=\"/saas_grid/jquery.js\"></script>");
		buffer.append("<link rel='stylesheet' href='/saas_grid/STable.css'> ");
		buffer
				.append("<html style='overflow:hidden;'><body unselectable=\"on\" onselectstart=\"return false\" style=\"-moz-user-select:none;\" oncontextmenu=self.event.returnValue=false "
						+ " onmouseover='table_content_Move(event)'"
						+ " onmouseout='table_content_Out(event)'"
						+ " onclick='table_content_Click(event)' "
						+ " ondblclick='table_content_dblClick(event)' "
						+ " bottommargin=\"0\" topmargin=\"0\" leftmargin=\"0\" rightmargin=\"0\" scroll='no'>");
		buffer.append("<table id='content_table' cellpadding=0 cellspacing=0 >");

		if (tableRows != null && tableRows.length > 0) {
			try {
				JSONObject rowIdsObj = hideArea.getCustomObject("rowIds");
				JSONArray rowIdArray = rowIdsObj.getJSONArray("rowIdArray");
				for (int j = 0; j < tableRows.length; j++) {
					Object object = tableRows[j];
					String id = this.contentProvider.getElementId(object);
					if (id == null) {
						id = "";
					}
					rowIdArray.put(id);
					processAddRow(object);
					// buffer.append(getRowHtml(object, id, actualColumn));
					buffer.append(getContainsItemsRowHtml(object, id, actualColumn));
				}
				hideArea.setCustomObject("rowIds", rowIdsObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			int contentHeight = tableStyle.getRowHeight() * getTotalRowItemCount() + 1;
			contentBrowser.setHeight(contentHeight);
		}
		buffer.append("</table></body></html>");
		// 判断是不是行选中的表格
		if (this.tableStyle.getSelectionMode() == SSelectionMode.Row) {
			buffer.append("<script>window.onload = function(){attchRowClickSelection();};</script>");
		}
		this.contentBrowser.applyHTML(buffer.toString());
		try {
			int bodyActualWidth = actualColumn.getInt("bodyActualWidth");
			this.contentBrowser.setWidth(bodyActualWidth);
			this.bodyArea.setWidth(bodyActualWidth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 行选中
	 * 
	 * @param rowId
	 */
	public void rowSelected(String rowId) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("rowId", rowId);
			hideArea.setCustomObject("selectRow", jsonObject);
			hideArea.notifyClientAction();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 设置单元格的背景颜色
	 * 
	 * @param rowId
	 *            行ID
	 * @param columnIndex
	 *            列
	 * @param color
	 *            颜色
	 */
	public void setTextBackgroundColor(List<STableTextColor> sTableTextColors) {
		try {
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			jsonObject.put("sTableTextColors", jsonArray);
			hideArea.setCustomObject("textBackgroundColor", jsonObject);
			for (STableTextColor tableTextColor : sTableTextColors) {
				JSONObject json = new JSONObject();
				json.put("rowId", tableTextColor.getRowId());
				json.put("columnIndex", tableTextColor.getColumnIndex());
				json.put("color", tableTextColor.getColor().toString());
				jsonArray.put(json);
			}
			hideArea.notifyClientAction();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增一行之后，子类需要处理下
	 * 
	 * @param object
	 */
	protected void processAddRow(Object object) {
	}

	/**
	 * 新增多行之后，子类需要处理下
	 * 
	 * @param object
	 */
	protected void processAddRows(Object[] object) {
	}

	/**
	 * 修改一行之后，子类需要处理下
	 * 
	 * @param object
	 */
	protected void processUpdateRow(Object object) {
	}

	/**
	 * 删除一行之后，子类需要处理下
	 * 
	 * @param id
	 */
	protected void processRemoveRow(String id) {
	}

	/**
	 * 修改一列数据之后，子类需要处理下
	 * 
	 * @param rowId
	 * @param column
	 * @param text
	 */
	protected void processUpdateCell(String rowId, int column, String text) {
	}

	/**
	 * 导出表格数据
	 * 
	 * @param fileName
	 * @param contentType
	 * @param contentLength
	 * @param contentTitle
	 */
	public void exportDatas(String fileName, String contentType, int contentLength, final String contentTitle) {
		if (!this.rendered || tableRows == null || columns == null) {
			return;
		}
		Display.getCurrent().exportFile(fileName, contentType, contentLength, new ExporterWithContext() {

			public void run(Context context, OutputStream outputStream) throws IOException {
				List<Object> dataList = new ArrayList<Object>();
				for (Object object : tableRows) {
					dataList.add(object);
				}
				dataList.addAll(rowsAfterRender);
				ExcelWriteHelperPoi writer = new ExcelWriteHelperPoi(dataList) {

					@Override
					protected String getText(Object element, int columnIndex) {
						String s = labelProvider.getText(element, columnIndex);
						if (StringUtils.isEmpty(s)) return "";
						if (s.endsWith("</a>")) {
							s = s.replace("</a>", "");
							s = s.substring(s.lastIndexOf(">")+1);
						}
						return s;
					}

					@Override
					protected String[] getHead() {
						String[] headerTitles = new String[columns.length];
						int index = 0;
						for (STableColumn column : columns) {
							String title = column.getTitle();
							if ("操作".equals(title)) {
								continue;
							}
							headerTitles[index] = title;
							index++;
						}
						return headerTitles;
					}
				};
				writer.writeNormal(outputStream, contentTitle);
			}
		});
	}
}
