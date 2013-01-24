package com.spark.portal.browser;

/**
 * 
 */
public final class WindowStyle {

	public enum Location {
		Center, Context
	}

	/**
	 * 
	 */
	private int style;

	/**
	 * 
	 */
	private Location location;

	/**
	 * 
	 */
	private int width;

	/**
	 * 
	 */
	private int height;

	/**
	 * 
	 * @param style
	 */
	public WindowStyle(int style) {
		this.style = style;
	}

	/**
	 * 
	 * @param style
	 * @param location
	 */
	public WindowStyle(int style, Location location) {
		super();
		this.style = style;
		this.location = location;
	}

	/**
	 * @return the style
	 */
	public int getStyle() {
		return style;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(int style) {
		this.style = style;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * 
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

}
