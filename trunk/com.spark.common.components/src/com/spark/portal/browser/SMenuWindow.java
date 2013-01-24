package com.spark.portal.browser;

import com.jiuqi.dna.ui.custom.popup.PopupWindow;
import com.jiuqi.dna.ui.wt.graphics.ImageBorder;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.spark.common.components.CommonImages;

/**
 * 类似弹出菜单的窗口控件（当鼠标悬浮在指定区域时，显示）
 * 
 */
public class SMenuWindow extends PopupWindow {

	public static ImageBorder imageBorder = new ImageBorder(
			new ImageDescriptor[] {
					CommonImages.getImage("img/border1/notes-lt.png"),
					CommonImages.getImage("img/border1/notes-t.png"),
					CommonImages.getImage("img/border1/notes-rt.png"),
					CommonImages.getImage("img/border1/notes-r.png"),
					CommonImages.getImage("img/border1/notes-rb.png"),
					CommonImages.getImage("img/border1/notes-b.png"),
					CommonImages.getImage("img/border1/notes-lb.png"),
					CommonImages.getImage("img/border1/notes-l.png") });
	public static ImageDescriptor image1 = CommonImages
			.getImage("img/border1/notes-arrow-up.png");
	public static ImageDescriptor image2 = CommonImages
			.getImage("img/border1/notes-arrow-down.png");

	public static ImageDescriptor backImage = CommonImages
			.getImage("img/border1/notes-bg.png");

	/**
	 * 
	 * @param parentWindow
	 * @param owner
	 * @param defaultDirection
	 * @param imageBorder
	 * @param image1
	 * @param image2
	 * @param delayTime
	 */
	public SMenuWindow(PopupWindow parentWindow, Control owner,
			Direction defaultDirection) {
		this(parentWindow, owner, defaultDirection, ActiveMode.Hover);
	}

	public SMenuWindow(PopupWindow parentWindow, Control owner,
			Direction defaultDirection, ActiveMode activeMode) {
		this(parentWindow, owner, Style.InfoWindow, defaultDirection,
				activeMode);
	}

	public SMenuWindow(PopupWindow parentWindow, Control owner, Style style,
			Direction defaultDirection, ActiveMode activeMode) {
		super(parentWindow, owner, style, defaultDirection, activeMode,
				imageBorder, image1, image2, 500);
		this.contentArea.setBackimage(backImage);
	}

	/**
	 * 绑定到目标控件
	 */
	public void bindTargetControl(Control target) {
		super.bindTargetControl(target);
	}

	/**
	 * 取消绑定
	 */
	public void unbindTargetControl(Control target) {
		super.unbindTargetControl(target);
	}

	/**
	 * 得到内容区域
	 * 
	 * @return
	 */
	public final Composite getContentArea() {
		return this.contentArea;
	}
}
