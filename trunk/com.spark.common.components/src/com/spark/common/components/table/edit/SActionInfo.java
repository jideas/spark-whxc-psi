package com.spark.common.components.table.edit;

import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

public class SActionInfo {

	private String id;

	/**
	 * 操作名称
	 */
	private String title;

	/**
	 * 普通图片
	 */
	private ImageDescriptor normalImage;

	/**
	 * 光标悬浮图片
	 */
	private ImageDescriptor hoverImage;

	/**
	 * 构造函数
	 * 
	 * @param title
	 * @param normalImage
	 * @param hoverImage
	 */
	public SActionInfo(String id, String title, ImageDescriptor normalImage,
			ImageDescriptor hoverImage) {
		super();
		this.id = id;
		this.title = title;
		this.normalImage = normalImage;
		this.hoverImage = hoverImage;
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @return
	 */
	public ImageDescriptor getNormalImage() {
		return normalImage;
	}

	/**
	 * 
	 * @return
	 */
	public ImageDescriptor getHoverImage() {
		return hoverImage;
	}
}
