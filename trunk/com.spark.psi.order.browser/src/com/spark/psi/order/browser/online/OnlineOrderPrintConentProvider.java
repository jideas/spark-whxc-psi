package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.psi.base.browser.FormPrintEntity;
import com.spark.psi.base.browser.PrintColumn;
import com.spark.psi.base.browser.PrintContentProvider;

public class OnlineOrderPrintConentProvider implements PrintContentProvider {

	private FormPrintEntity[] printEntities = null;
	
	public OnlineOrderPrintConentProvider(FormPrintEntity... printEntities) {
		this.printEntities = printEntities;
	}
	
	public String getContentHtml() {
		StringBuffer sb = new StringBuffer();
		for (FormPrintEntity entity : printEntities) {
			sb.append("<br/><br/>");
			sb.append(getContentHtmlByEntity(entity));
		}
		return sb.toString();
	}

	private String getContentHtmlByEntity(FormPrintEntity entity) {
		StringBuffer buffer = new StringBuffer();
		for (String commonRow : entity.getTableTitles()) {
			buffer.append("<span height='" + FormPrintEntity.COMMON_ROW_HEIGHT + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>").append(commonRow).append("</font></span> \n <br/>");
		}
		buffer.append("\n<div style='height:1px;width: " + getWidth() + ";border-bottom: solid red 1px;'></div> \n");
		buffer.append("<table> \n");
		// ��ͷ
		buffer.append("<tr> \n");
		for (PrintColumn column : entity.getColumns()) {
			buffer.append("<td align='" +  getTableAlign(column.getAlign()) + "' width='" + column.getWidth() + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>" + column.getTitle() + "</font></td> \n");
		}
		buffer.append("</tr> \n");
		// ����
		for (Object object : entity.getDatas()) {
			buffer.append("<tr> \n");
			for (int columnIndex = 0; columnIndex < entity.getColumns().length; columnIndex++) {
				PrintColumn column = entity.getColumns()[columnIndex];
				buffer.append("<td align='" + getTableAlign(column.getAlign()) + "' width='" + column.getWidth() + "'>");
				buffer.append("<span><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>" + entity.getLabelProvider().getText(object, columnIndex) + "</font></span>");
				buffer.append("</td> \n");
			}
			buffer.append("</tr> \n");
		}
		buffer.append("</table> \n");
		//buffer.append("\n<hr/> \n");
		buffer.append("\n<div style='height:1px;width: " + getWidth() + ";border-bottom: solid red 1px;'></div> \n");
		buffer.append("<span height='" + FormPrintEntity.COMMON_ROW_HEIGHT + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>" + entity.getSummaryInfo() + "</font></span>\n");
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
		int height = printEntities.length * 30;
		for (FormPrintEntity entity : printEntities) {
			height += FormPrintEntity.FORM_TITLE_HEIGHT + 30;
			height += FormPrintEntity.COMMON_ROW_HEIGHT * entity.getTableTitles().length;
			height += FormPrintEntity.TABLE_ROW_HEIGHT * entity.getDatas().length + FormPrintEntity.TABLE_ROW_HEIGHT; // ��������+��ͷ
		}
		height *= 2.85;
		//height += 100;
		return height;
	}

	public int getWidth() {
		return FormPrintEntity.FORM_WIDTH;
	}

}