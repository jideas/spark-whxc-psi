package com.spark.common.components.table;

public class STableStyle {

	/**
	 * ��ͷ�Ƿ�ɼ�
	 */
	private boolean headerVisible;

	/**
	 * �и�
	 */
	private int rowHeight = 24;

	/**
	 * ��ͷ�߶�
	 */
	private int headerHeight = 30;

	/**
	 * ѡ��ģʽ
	 */
	private SSelectionMode selectionMode = SSelectionMode.Row;
	
	private boolean isPageAble = true;
	
	/**
	 * ȫ���п�������
	 */
	private boolean isSortAll = false;

	/**
	 * ��ֹ������
	 */
	private boolean noScroll = false;
	
	/**
	 * ��ֹ���������
	 */
	private boolean noHScroll = false;
	public boolean isNoHScroll() {
		return noHScroll;
	}

	public void setNoHScroll(boolean noHScroll) {
		this.noHScroll = noHScroll;
	}

	/**
	 * @return the headerVisible
	 */
	public boolean isHeaderVisible() {
		return headerVisible;
	}

	/**
	 * @param headerVisible
	 *            the headerVisible to set
	 */
	public void setHeaderVisible(boolean headerVisible) {
		this.headerVisible = headerVisible;
	}

	/**
	 * @return the rowHeight
	 */
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * @param rowHeight
	 *            the rowHeight to set
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	/**
	 * @return the headerHeight
	 */
	public int getHeaderHeight() {
		return headerHeight;
	}

	/**
	 * @param headerHeight
	 *            the headerHeight to set
	 */
	public void setHeaderHeight(int headerHeight) {
		this.headerHeight = headerHeight;
	}

	/**
	 * @return the selectionMode
	 */
	public SSelectionMode getSelectionMode() {
		return selectionMode;
	}

	/**
	 * @param selectionMode
	 *            the selectionMode to set
	 */
	public void setSelectionMode(SSelectionMode selectionMode) {
		this.selectionMode = selectionMode;
	}
	
	/**
	 * ����ȫ���ж���������ı��
	 * @param isSortAll
	 */
	public void setSortAll(boolean isSortAll) {
		this.isSortAll = isSortAll;
	}

	public boolean isSortAll() {
		return isSortAll;
	}

	/**
	 * @return the noScroll
	 */
	public boolean isNoScroll() {
		return noScroll;
	}

	/**
	 * @param noscroll the noScroll to set
	 */
	public void setNoScroll(boolean noScroll) {
		this.noScroll = noScroll;
	}

	public boolean isPageAble() {
		return isPageAble;
	}

	public void setPageAble(boolean isPageAble) {
		this.isPageAble = isPageAble;
	}
	
}
