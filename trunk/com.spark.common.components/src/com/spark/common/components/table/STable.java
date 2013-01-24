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
 * ���������ؼ�
 */
public class STable extends Composite {

	/* ѡ���¼� */
	public final static String CLIENT_EVENT_SELECTION = "tableSelection";

	/* ���׼������¼� */
	public final static String CLIENT_EVENT_INITED = "tableInited";

	/* ����ƶ��������¼� */
	public final static String CLIENT_EVENT_MOUSEROVER = "tableMouseover";

	/* ����ƶ�������¼� */
	public final static String CLIENT_EVENT_MOUSEROUT = "tableMouseout";

	/* ������¼� */
	public final static String CLIENT_EVENT_ROWCLICK = "rowClick";

	/* �������¼� */
	public final static String CLIENT_EVENT_ROWADDED = "rowAdded";

	/* �޸����¼� */
	public final static String CLIENT_EVENT_ROWUPDATEED = "rowUpdated";

	/* ɾ�����¼� */
	public final static String CLIENT_EVENT_ROWREMOVEED = "rowRemoved";

	/* ˫�����¼� */
	public final static String CLIENT_EVENT_TABLEDBLCLICK = "tabledblClick";

	/* �����¼� */
	public final static String CLIENT_EVENT_ACTION = "tableAction";

	/* �汾�� */
	private final static String VERSIONS = "0.3";

	/* �����ʱ�������û��ĵ�Ԫ����ɫ���� */
	public final static Color WARNINGCOLOR = new Color(0xffebd6);
	/* ������ʱ�������û��ĵ�Ԫ����ɫ���� */
	public final static Color NORMALCOLOR = new Color(0xffffff);

	/* ÿһ�еı�ͷ��Ϣ */
	protected STableColumn[] columns;

	/* ������� */
	protected SContentProvider contentProvider;

	/* ���ľ����ı� */
	protected SLabelProvider labelProvider;

	/* �ϲ���Ԫ�� */
	protected SSpanProvider spanProvider;

	/* ������ʽ */
	protected STableStyle tableStyle;

	/* �ű�������� */
	protected Browser contentBrowser;

	/* �ű�ͷ������ */
	protected Browser titleBrowser;

	/* ����������������,Ҳ���ǳ�������֮������� */
	protected Composite bodyArea;

	protected Composite headArea;

	/* �������������ڴ�ſͻ������� */
	protected Composite hideArea;

	/* ���action�¼������� */
	private List<SActionListener> actionlisteners;

	/* ���selectѡ���¼������� */
	private List<SelectionListener> selectionlisteners;

	/* �����ֶ� */
	private STableStatus sTableStatus;

	/* ���������� */
	private ScrolledPanel scrolledPanel;

	/* �����ж��Ƿ��Ѿ���Ⱦ�����,��Ȼ���ܵ��ò����¼� */
	private boolean rendered;

	private Object[] tableRows;
	
	private List<Object> rowsAfterRender = new ArrayList<Object>();

	private JSONObject actualTableColumn;

	/**
	 * ���췽��
	 * 
	 * @param parent
	 *            ����
	 * @param columns
	 *            �������Ϣ
	 * @param tableStyle
	 *            ������ʽ
	 */
	public STable(Composite parent, STableColumn[] columns, STableStyle tableStyle) {
		super(parent);
		this.columns = columns;
		this.tableStyle = tableStyle;
		this.sTableStatus = new STableStatus();

		// ��ӱ��Ŀؼ�������ʽ
		this.clear();
		// ����ܿؼ�����ʽ
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 0;
		this.setLayout(gridLayout);
		CBorder border = new CBorder();
		border.setColor(0x78a9bf);
		this.setBorder(border);
		this.setBackground(new Color(0xFFFFFF));
		this.addClientEventHandler(JWT.CLIENT_EVENT_RESIZE, "STable.handleClientResize");
		this.addClientEventHandler(JWT.CLIENT_EVENT_INIT, "STable.handleClientInit");

		// ��ͷ��ʽ�Ϳؼ���ʼ��,�ͻ��˷���
		headArea = new Composite(this);
		GridData gdHeader = new GridData(GridData.FILL_HORIZONTAL);
		headArea.setLayoutData(gdHeader);
		titleBrowser = new Browser(headArea);
		titleBrowser.setHeight(tableStyle.getHeaderHeight());
		titleBrowser.setHandlerName("handleHeaderEvent");
		titleBrowser.addClientEventHandler(JWT.CLIENT_EVENT_ACTION, "STableHeader.handleClientAction");

		// ������ʽ�Ϳؼ���ʼ��,�ͻ��˷���
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
				// ��ҳ
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
		
		// �������ؿؼ���λ�ñ��������һ�����ͻ��˷���
		hideArea = new Composite(this);
		GridData gdHide = new GridData();
		gdHide.exclude = true;
		hideArea.setLayoutData(gdHide);
		hideArea.setVisible(false);
		hideArea.addClientEventHandler(JWT.CLIENT_EVENT_SERVER_NOTIFY, "STableHide.handleServerNotify");
		// ע�������
		hideArea.addClientNotifyListener(new ClientNotifyListener() {
			public void notified(ClientNotifyEvent event) {

				JSONObject actualColumn = hideArea.getCustomObject("actualColumn");
				// ����
				JSONObject sort = hideArea.getCustomObject("sort");
				// ���������
				JSONObject link = hideArea.getCustomObject("link");
				// ѡ���¼�
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
	 * ��ʼ�������
	 * 
	 * @param actualColumn
	 * @param isResize
	 *            �Ƿ������µ�����С
	 */
	private void renderStart(JSONObject actualColumn, boolean isResize) {
		setTableContent(actualColumn);
		// ��һ�δ����Ż�ȥ������ͷ,֮���������ˢ�¶�����ȥ����ˢ�±�ͷ
		if (isResize || !rendered) {
			// ������ر�ͷ
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
			// ���ɱ�ͷhtml
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
	 * ����һ�б������
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
	 * �������б������
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
	 * �޸�һ�б������
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
	 * �޸�һ������
	 * 
	 * @param column
	 * @param row
	 * @param text
	 */
	public void updateCell(String rowId, int column, String text) {
		setUpdateCellValue(rowId, column, text, null);
	}

	/**
	 * �޸�һ������,����������ʾToolTip
	 * 
	 * @param column
	 * @param row
	 * @param text
	 */
	public void updateCellAndToolTip(String rowId, int column, String text, String toolTipText) {
		setUpdateCellValue(rowId, column, text, toolTipText);
	}

	/**
	 * ����һ�е����ݣ���ʾtitleΪ��ѡ
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
			operation.put("column", column); // ����Tr������html
			operation.put("text", StringUtils.isEmpty(text) ? " " : text);
			operation.put("toolTipText", toolTipText);
		} catch (Exception t) {
			t.printStackTrace();
		}
		processUpdateCell(rowId, column, text);
	}

	/**
	 * ����һ�б������
	 * 
	 * @param object
	 * @param updateType
	 *            ��������
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
	 * ɾ��һ������
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
			// ����Ҫɾ��rowsAfterRender����Ӧ�ļ�¼
		} catch (Exception e) {
			e.printStackTrace();
		}
		processRemoveRow(id);
	}

	/**
	 * ��ȡ������ݵ�json����
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
	 * �������������TR
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
				// selectionsIdsѡ�е���ID
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
			// ��ѡ
			buffer.append(" <td class='tdCenter' width='" + jsonArray.getInt(arrayNumber)
					+ "'><input name='radio1' type='radio' ");
			if (this.contentProvider.isSelected(object)) {
				buffer.append(" checked='true'");
				// selectionsIdsѡ�е���ID
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
		// ���鱣���з���ϲ�����һ�У��Լ��ϲ������У���һ��������ʾ�ϲ������У��ڶ���������ʾ����һ��
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
						Double dv = DoubleUtil.strToDouble(text); // ����������֣�������쳣
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
	 * ȡ����װһ���ж����Ŀ��HTML
	 * 
	 * @param object
	 *            ���е���ʾ����
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
				// �ϲ����в���Ҫ��ʾѡ����
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
	 * ��ȡ����ѡ���Ԫ���HTML����
	 * 
	 * @param object
	 *            ������ʾ�Ķ���
	 * @param selectionMode
	 *            ����ѡ��ģʽ
	 * @param selectionColWidth
	 *            ѡ��Ԫ��Ŀ��
	 * @param rowSpan
	 *            �кϲ���
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
			// ��ѡ
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
	 * ��ȡ���ɵ�Ԫ���HTML����
	 * 
	 * @param sTableColumn
	 *            �ж���
	 * @param columnIndex
	 *            �����
	 * @param object
	 *            ���е���ʾ����
	 * @param columnWidth
	 *            �п�
	 * @param borderRigthHtml
	 *            ��Ԫ���ұ߿�
	 * @param rowSpan
	 *            �кϲ���(0��ʾ���ϲ�)
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
					Double dv = DoubleUtil.strToDouble(text); // ����������֣�������쳣
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
		// �ж��Ƿ��й����������û�У��Ǳߣ����һ�еĵ�Ԫ��û���ұ߿�
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
	 * �Ա����в���֮�󣬵��ø÷���ʹ�ò�����Ч
	 */
	public void renderUpate() {
		if (!rendered) {
			throw new IllegalStateException();
		}
		try {
			// rowids ���е���ID
			JSONObject rowids = hideArea.getCustomObject("rowIds");
			JSONArray jsonArray = rowids.getJSONArray("rowIdArray");
			// selectionsIds ѡ�е���ID
			JSONObject jsonObject = hideArea.getCustomObject("selections");
			JSONArray selectionsIds = jsonObject.getJSONArray("ids");
			// ���β������ж���
			JSONObject rowOperations = hideArea.getCustomObject("rowOperations");
			JSONArray operationList = rowOperations.getJSONArray("operationList");

			for (int i = 0; i < operationList.length(); i++) {
				JSONObject operation = operationList.getJSONObject(i);
				String type = operation.getString("type");

				// ����rowIdArray
				if (type != null && type.length() > 0) {
					if (type.equalsIgnoreCase("add")) {
						String id = operation.getString("id");
						String rowData = operation.getString("rowData"); // ����Tr������html
						jsonArray.put(id);
						// �õ������Ƿ�ѡ�У�����selection����
						if (rowData.indexOf("checked='true'") != -1) {
							selectionsIds.put(id);
						}
					} else if (type.equalsIgnoreCase("remove")) {
						// �õ������Ƿ�ѡ�У�������ɾ��selection
						// ��ǰID�Ƿ������jsonobject����
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
						// ID�����ڣ���ô�Ͳ���Ҫ�����ͻ���ȥ��ɾ������
						if (!isremove) {
							operationList.remove(i);
						}
					} else if (type.equalsIgnoreCase("update")) {
						String id = operation.getString("id");
						String rowData = operation.getString("rowData");
						// �õ�����ѡ�У�������selection
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
							// �õ�����û��ѡ�У���ɾ��selection
							for (int number = 0; number < selectionsIds.length(); number++) {
								String arrayId = selectionsIds.getString(number);
								if (arrayId.equalsIgnoreCase(id)) {
									selectionsIds.remove(number);
								}
							}
						}
					} else if (type.equalsIgnoreCase("addrows")) {
						String rowDatas = operation.getString("rowDatas"); // ����Tr������html
						String[] rows = rowDatas.split("</tr>");
						JSONArray array = operation.getJSONArray("ids");
						for (int n = 0; n < array.length(); n++) {
							jsonArray.put(array.getString(n));

						}
						for (int n = 0; n < rows.length; n++) {
							String str = rows[n];
							// �õ������Ƿ�ѡ�У�����selection����
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
		// ���ÿͻ��˷���
		hideArea.notifyClientAction();
	}

	/**
	 * ��ӱ���¼�������
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
	 * ɾ������¼�������
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
	 * ���ѡ�������
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
	 * ɾ��ѡ�������
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
	 * ����¼�������ʱ�򷢳���Ϣ
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
	 * ѡ���¼�������ʱ�򷢳���Ϣ
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
	 * �������Ա������е��е�Ψһ��ʶ
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
	 * ȡ�õ�ǰ��������е�����
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
	 * ��ȡ��ѡ��ѡ�����Ψһ��ʶ
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
	 * ��ȡ��ѡ��ѡ�����Ψһ��ʶ
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
	 * ���ñ�����ݵĹ�Ӧ��
	 * 
	 * @param contentProvider
	 *            the contentProvider to set
	 */
	public void setContentProvider(SContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}

	/**
	 * ���ñ����ʾ�ı��Ĺ�Ӧ��
	 * 
	 * @param labelProvider
	 *            the labelProvider to set
	 */
	public void setLabelProvider(SLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	/**
	 * ���úϲ���Ԫ��Ĺ�Ӧ��
	 * 
	 * @param spanProvider
	 */
	public void setSpanProvider(SSpanProvider spanProvider) {
		this.spanProvider = spanProvider;
	}

	/**
	 * ��Ⱦ�����Ⱦ���֮ǰ��ȷ���ѽ������²���: 1.���ù�������Ҫ��STableStyle����Ȼȡ������ͷ��Ϣ��������Ⱦ����ͷ
	 * 2.ʵ��getElements��������Ȼȡ����������Ϣ��������Ⱦ������
	 * 3. ����շ�ҳ��Ϣ���ӵ�һҳ��ʼ��ʾ
	 */
	public void render() {
		/*
		 * if (columns == null) { //
		 * this.contentBrowser.setHTML("<p>��ͷ��������Ϊ��</p>"); return; } if
		 * (contentBrowser != null && !contentBrowser.isDisposed()) {
		 * contentBrowser.dispose(); }
		 * 
		 * // ����ؼ���ӿͻ��˷��� contentBrowser = new Browser(bodyArea);
		 * contentBrowser.setHandlerName("handleEvent");
		 * contentBrowser.addClientEventHandler(JWT.CLIENT_EVENT_ACTION,
		 * "STableBody.handleClientAction");
		 * contentBrowser.addClientEventHandler(JWT.CLIENT_EVENT_INIT,
		 * "STableBody.handleInited");
		 */
		sTableStatus.resetPageInfo();
		// ��ʼ��������տͻ�����Ҫ���������
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

		// ��Ӹ��ͻ���ʹ�õ�����
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
				// XXX������ع��󣬻��֮ǰ����ִ�е��߼�����Ӱ�죬�����ȼ򵥵Ľ��һ�¶�getSelection������
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
	 * ���ñ�ͷ
	 * 
	 * @param rowNumber
	 *            ��ͷ�ж�����
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
			// ��ѡ
			buffer.append(" <td  rowspan='" + rowNumber + "' width='" + jsonArray.getInt(arrayNumber)
					+ "' ><input name='checkbox1' type='checkbox'></input></td>");
			arrayNumber++;
		} else if (tableStyle.getSelectionMode() == SSelectionMode.Single) {
			// ��ѡ
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
				// ���ӱ�ͷ
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
				// �򵥱�ͷ
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
		// �����ͷ��Ҫ�ر���
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
	 * ���ñ���
	 */
	private void setTableContent(JSONObject actualColumn) {

		if (contentBrowser != null && !contentBrowser.isDisposed()) {
			contentBrowser.dispose();
		}

		// ����ؼ���ӿͻ��˷���
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
		// �ж��ǲ�����ѡ�еı��
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
	 * ��ѡ��
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
	 * ���õ�Ԫ��ı�����ɫ
	 * 
	 * @param rowId
	 *            ��ID
	 * @param columnIndex
	 *            ��
	 * @param color
	 *            ��ɫ
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
	 * ����һ��֮��������Ҫ������
	 * 
	 * @param object
	 */
	protected void processAddRow(Object object) {
	}

	/**
	 * ��������֮��������Ҫ������
	 * 
	 * @param object
	 */
	protected void processAddRows(Object[] object) {
	}

	/**
	 * �޸�һ��֮��������Ҫ������
	 * 
	 * @param object
	 */
	protected void processUpdateRow(Object object) {
	}

	/**
	 * ɾ��һ��֮��������Ҫ������
	 * 
	 * @param id
	 */
	protected void processRemoveRow(String id) {
	}

	/**
	 * �޸�һ������֮��������Ҫ������
	 * 
	 * @param rowId
	 * @param column
	 * @param text
	 */
	protected void processUpdateCell(String rowId, int column, String text) {
	}

	/**
	 * �����������
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
							if ("����".equals(title)) {
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
