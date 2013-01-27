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
		buffer.append("<img border='0' src='/psi_print/print_logo.jpg' width='220' height='65' /><br/>");
		for (String commonRow : entity.getTableTitles()) {
			buffer.append("<span height='" + FormPrintEntity.COMMON_ROW_HEIGHT + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>").append(commonRow).append("</font></span> \n <br/>");
		}
		buffer.append("\n<div style='height:1px;width: " + getWidth() + ";border-bottom: solid red 1px;'></div> \n");
		buffer.append("<table> \n");
		// 表头
		buffer.append("<tr> \n");
		for (PrintColumn column : entity.getColumns()) {
			buffer.append("<td align='" +  getTableAlign(column.getAlign()) + "' width='" + column.getWidth() + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>" + column.getTitle() + "</font></td> \n");
		}
		buffer.append("</tr> \n");
		// 表体
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
		for (String commonRow : entity.getTableFooters()) {
			buffer.append("<span width='" + 230 + "px' height='" + FormPrintEntity.COMMON_ROW_HEIGHT + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>" + commonRow + "</font></span> <br/>\n");
		}
		
		buffer.append("<span height='" + FormPrintEntity.COMMON_ROW_HEIGHT + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>7号生活馆电子商务有限公司</font></span> \n <br/>");
		buffer.append("<span height='" + FormPrintEntity.COMMON_ROW_HEIGHT + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>电话：4001-027-577</font></span> \n <br/>");
		buffer.append("<span height='" + FormPrintEntity.COMMON_ROW_HEIGHT + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>地址：武汉市黄陂区武湖农场工业园</font></span> \n <br/>");
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
		int height = printEntities.length * 90;
		for (FormPrintEntity entity : printEntities) {
			height += FormPrintEntity.FORM_TITLE_HEIGHT + 30;
			height += FormPrintEntity.COMMON_ROW_HEIGHT * entity.getTableTitles().length;
			height += FormPrintEntity.COMMON_ROW_HEIGHT * entity.getTableFooters().length;
			height += FormPrintEntity.TABLE_ROW_HEIGHT * entity.getDatas().length + FormPrintEntity.TABLE_ROW_HEIGHT; // 表格内容+表头
		}
		height *= 2.85;
		//height += 100;
		return height;
	}

	public int getWidth() {
		return FormPrintEntity.FORM_WIDTH;
	}

}
