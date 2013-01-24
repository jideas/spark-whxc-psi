package com.spark.common.components.table;

/**
 * 表格列定义
 * 
 */
public class STableColumn {

	private String name;

	private String[] titles;

	private int width;

	private boolean grab = false;

	private boolean sortable;

	private int align;
	
	private boolean search;

	/**
	 * 
	 * @param name
	 * @param titles
	 * @param width
	 */
	public STableColumn(String name, int width,int align, String... titles) {
		super();
		this.name = name;
		this.titles = titles;
		this.width = width;
		this.align = align;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the titles
	 */
	public String[] getTitles() {
		return titles;
	}

	public String getTitle() {
		return titles[0];
	}

	/**
	 * @param titles
	 *            the titles to set
	 */
	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the grab
	 */
	public boolean isGrab() {
		return grab;
	}

	/**
	 * @param grab
	 *            the grab to set
	 */
	public void setGrab(boolean grab) {
		this.grab = grab;
	}

	/**
	 * @return the sortable
	 */
	public boolean isSortable() {
		return sortable;
	}

	/**
	 * @param sortable
	 *            the sortable to set
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public void setAlign(int align) {
		this.align = align;
	}

	public int getAlign() {
		return align;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public boolean isSearch() {
		return search;
	}

}
