package com.spark.common.components.table;

public class STableStyle {

	/**
	 * 表头是否可见
	 */
	private boolean headerVisible;

	/**
	 * 行高
	 */
	private int rowHeight = 24;

	/**
	 * 表头高度
	 */
	private int headerHeight = 30;

	/**
	 * 选择模式
	 */
	private SSelectionMode selectionMode = SSelectionMode.Row;
	
	private boolean isPageAble = true;
	
	/**
	 * 全部列可以排序
	 */
	private boolean isSortAll = false;

	/**
	 * 禁止滚动条
	 */
	private boolean noScroll = false;
	
	/**
	 * 禁止横向滚动条
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
	 * 设置全部列都可以排序的表格
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
