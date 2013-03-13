package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.psi.base.browser.FormPrintEntity;
import com.spark.psi.base.browser.PrintColumn;
import com.spark.psi.base.browser.PrintContentProvider;

public class SalesOrderPrintContentProvider implements PrintContentProvider {

	/**
	 * 表单头的高度
	 */
	public static final int FORM_TITLE_HEIGHT = 22;
	/**
	 * 普通行的高度
	 */
	public static final int COMMON_ROW_HEIGHT = 19;
	/**
	 * 表格行的高度
	 */
	public static final int TABLE_ROW_HEIGHT  = 19;
	
	public static final int FONT_COMMON_SIZE  = 2;
	
	public static final int PAGE_WIDTH        = 700;
	
	private FormPrintEntity printEntity = null;
	
	public SalesOrderPrintContentProvider(FormPrintEntity printEntity) {
		this.printEntity = printEntity;
	}
	
	public String getContentHtml() {
		StringBuffer buffer = new StringBuffer();
		for (String commonRow : printEntity.getTableTitles()) {
			buffer.append("<span height='" + COMMON_ROW_HEIGHT + "'><font size='" + FONT_COMMON_SIZE + "'>").append(commonRow).append("</font></span> \n <br/>");
		}
		buffer.append("\n<hr/> \n");
		buffer.append("<table border='1' cellpadding='0' cellspacing='0'> \n");
		// 表头
		buffer.append("<tr> \n");
		for (PrintColumn column : printEntity.getColumns()) {
			buffer.append("<td align='center' width='" + column.getWidth() + "'><font size='" + FONT_COMMON_SIZE + "'>" + column.getTitle() + "</font></td> \n");
		}
		buffer.append("</tr> \n");
		// 表体
		for (Object object : printEntity.getDatas()) {
			buffer.append("<tr> \n");
			for (int columnIndex = 0; columnIndex < printEntity.getColumns().length; columnIndex++) {
				PrintColumn column = printEntity.getColumns()[columnIndex];
				buffer.append("<td align='" + getTableAlign(column.getAlign()) + "' width='" + column.getWidth() + "' >");
				buffer.append("<span><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>" + printEntity.getLabelProvider().getText(object, columnIndex) + "</font></span>");
				buffer.append("</td> \n");
			}
			buffer.append("</tr> \n");
		}
		buffer.append("</table> \n");
		buffer.append("\n<div style='height:1px;width: " + getWidth() + ";border-bottom: solid 0px;'></div> \n");
		for (String commonRow : printEntity.getTableFooters()) {
			buffer.append("<span width='" + 230 + "px' height='" + FormPrintEntity.COMMON_ROW_HEIGHT + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>" + commonRow + "</font></span> <br/>\n");
		}
		return buffer.toString();
	}

	private String getTableAlign(int align) {
		String alignStr = "left";
		if (JWT.RIGHT == align) {
			alignStr = "right";
		} else if (JWT.CENTER == align) {
			alignStr = "center";
		}
		return alignStr;
	}
	
	public int getHeight() {
		int height = FORM_TITLE_HEIGHT + 30;
		height += COMMON_ROW_HEIGHT * printEntity.getTableTitles().length;
		height += TABLE_ROW_HEIGHT * printEntity.getDatas().length + TABLE_ROW_HEIGHT; // 表格内容+表头
		height += COMMON_ROW_HEIGHT * printEntity.getTableFooters().length;
		height *= 2.85;
		return height;
	}

	public int getWidth() {
		return PAGE_WIDTH * 3;
	}

}
