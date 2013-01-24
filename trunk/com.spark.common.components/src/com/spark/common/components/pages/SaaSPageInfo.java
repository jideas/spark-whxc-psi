package com.spark.common.components.pages;

public class SaaSPageInfo {

	String space;

	PageController controller;

	String name;

	String title;

	public SaaSPageInfo(String space, String name, String title,
			PageController controller) {
		super();
		this.space = space;
		this.name = name;
		this.title = title;
		this.controller = controller;
	}

	/**
	 * @return the space
	 */
	public String getSpace() {
		return space;
	}

	/**
	 * @return the controller
	 */
	public PageController getController() {
		return controller;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

}
