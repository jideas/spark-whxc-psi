package com.spark.psi.base.browser;

import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SSpanProvider;

/**
 * 小票打印实体
 * @author Administrator
 *
 */
public class FormPrintEntity {
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
	public static final int TABLE_ROW_HEIGHT  = 38;
	
	public static final int FONT_COMMON_SIZE  = 2;
	
	public static final int FORM_WIDTH        = 900;
	
	private String title           = null;
	private PrintColumn[] columns  = null;
	private Object[] datas         = null;
	private String[] tableTitles   = null;
	//private String summaryInfo     = null;
	private String[] tableFooters  = null;
	
	private SLabelProvider labelProvider = null;
	
	private SSpanProvider spanProvider = null;
	
	/**
	 * 
	 * @param title 表单头
	 * @param columns 表格表头
	 * @param datas 表格数据
	 * @param tableTitles 表格前的显示内容，一条内容为一行
	 */
	public FormPrintEntity(String title, PrintColumn[] columns, Object[] datas, String... tableTitles) {
		this.title   = title;
		this.columns = columns;
		this.datas   = datas;
		this.tableTitles = tableTitles;
	}
	
	public void setLabelProvider(SLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}
	
	/**
	 * 取得表单Title
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	public PrintColumn[] getColumns() {
		return columns;
	}
	
	public Object[] getDatas() {
		return datas;
	}

	/**
	 * 内容显示提供器
	 * @return
	 */
	public SLabelProvider getLabelProvider() {
		return labelProvider;
	}
	
	/**
	 * 表格前的显示内容，一条内容为一行
	 * @return
	 */
	public String[] getTableTitles() {
		return this.tableTitles;
	}

	public SSpanProvider getSpanProvider() {
		return spanProvider;
	}
	
	/**
	 * 行/列合并提供器
	 * @param spanProvider
	 */
	public void setSpanProvider(SSpanProvider spanProvider) {
		this.spanProvider = spanProvider;
	}

//	public String getSummaryInfo() {
//		return summaryInfo;
//	}
//
//	public void setSummaryInfo(String summaryInfo) {
//		this.summaryInfo = summaryInfo;
//	}

	public String[] getTableFooters() {
		return tableFooters;
	}

	public void setTableFooters(String... tableFooters) {
		this.tableFooters = tableFooters;
	}
	
}
