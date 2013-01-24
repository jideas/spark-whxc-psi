package com.spark.common.components.pages;

import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

/**
 * 子功能
 */
public final class BaseFunction implements Function {

	/**
	 * 
	 */
	private PageControllerInstance controllerInstance;

	/**
	 * 是否默认
	 */
	private boolean isDefault;

	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 */
	private ImageDescriptor icon;

	/**
	 * 
	 * @param pageController
	 * @param title
	 */
	public BaseFunction(PageControllerInstance controllerInstance, String title) {
		this(controllerInstance, title, false);
	}

	/**
	 * 
	 * @param controllerInstance
	 * @param isDefault
	 */
	public BaseFunction(PageControllerInstance controllerInstance,
			String title, boolean isDefault) {
		this.controllerInstance = controllerInstance;
		this.title = title;
		this.isDefault = isDefault;
	}

	/**
	 * @return the controllerInstance
	 */
	public PageControllerInstance getPageControllerInstance() {
		return controllerInstance;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDefault() {
		return this.isDefault;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the icon
	 */
	public ImageDescriptor getTitleIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setTitleIcon(ImageDescriptor icon) {
		this.icon = icon;
	}

}
