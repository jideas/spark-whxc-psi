package com.spark.psi.base.browser;

public class PrintColumn {
	
	public static int NAME_COLUMN_WIDTH   = 80;
	public static int PRICE_COLUMN_WIDTH  = 50;
	public static int COUNT_COLUMN_WIDTH  = 27;
	public static int AMOUNT_COLUMN_WIDTH = 45;
	public static int NUMBER_COLUMN_WIDTH = 150;
	public static int SPEC_COLUMN_WIDTH   = 57;
	
	private String title;
	private int width;
	private int align;
	
	public PrintColumn(String title, int width, int align) {
		this.title = title;
		this.width = width;
		this.align = align;
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getAlign() {
		return align;
	}
}
