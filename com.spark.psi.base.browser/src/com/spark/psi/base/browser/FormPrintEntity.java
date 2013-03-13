package com.spark.psi.base.browser;

import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.SSpanProvider;

/**
 * СƱ��ӡʵ��
 * @author Administrator
 *
 */
public class FormPrintEntity {
	/**
	 * ��ͷ�ĸ߶�
	 */
	public static final int FORM_TITLE_HEIGHT = 22;
	/**
	 * ��ͨ�еĸ߶�
	 */
	public static final int COMMON_ROW_HEIGHT = 19;
	/**
	 * ����еĸ߶�
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
	 * @param title ��ͷ
	 * @param columns ����ͷ
	 * @param datas �������
	 * @param tableTitles ���ǰ����ʾ���ݣ�һ������Ϊһ��
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
	 * ȡ�ñ�Title
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
	 * ������ʾ�ṩ��
	 * @return
	 */
	public SLabelProvider getLabelProvider() {
		return labelProvider;
	}
	
	/**
	 * ���ǰ����ʾ���ݣ�һ������Ϊһ��
	 * @return
	 */
	public String[] getTableTitles() {
		return this.tableTitles;
	}

	public SSpanProvider getSpanProvider() {
		return spanProvider;
	}
	
	/**
	 * ��/�кϲ��ṩ��
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
