package com.spark.portal.browser;

import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;

/**
 * 请求打开指定界面的消息
 * 
 */
public class MsgRequest {

	/**
	 * 界面标题
	 */
	private String title;

	/**
	 * 界面图标
	 */
	private ImageDescriptor icon;

	/**
	 * 　多个基础功能界面实例
	 */
	private BaseFunction[] functions;

	/**
	 * 默认在主窗口中打开，如果传入windowStyle，则在相应窗口中打开
	 */
	private WindowStyle windowStyle;

	/**
	 * 
	 */
	private ResponseHandler responseHandler;

	/**
	 * 
	 */
	private CancelHandler cancelHandler;
	
	/**
	 * 是否覆盖当前页面
	 */
	private boolean replace;

	/**
	 * 
	 * @param pageControllerInstance
	 */
	public MsgRequest(PageControllerInstance pageControllerInstance) {
		this(pageControllerInstance, null, null);
	}

	/**
	 * 
	 * @param pageControllerInstance
	 * @param title
	 */
	public MsgRequest(PageControllerInstance pageControllerInstance,
			String title) {
		this(pageControllerInstance, title, null);
	}

	/**
	 * 
	 * @param pageControllerInstance
	 * @param title
	 * @param windowStyle
	 */
	public MsgRequest(PageControllerInstance pageControllerInstance,
			String title, WindowStyle windowStyle) {
		this(new BaseFunction[] { new BaseFunction(pageControllerInstance,
				title) }, title, windowStyle);
	}

	/**
	 * 
	 * @param functions
	 * @param title
	 */
	public MsgRequest(BaseFunction[] functions, String title) {
		this(functions, title, null);
	}

	/**
	 * 
	 * @param functions
	 * @param title
	 * @param windowStyle
	 */
	public MsgRequest(BaseFunction[] functions, String title,
			WindowStyle windowStyle) {
		this.functions = functions;
		this.title = title;
		this.windowStyle = windowStyle;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the functions
	 */
	public BaseFunction[] getFunctions() {
		return functions;
	}

	/**
	 * 
	 * @return
	 */
	public WindowStyle getWindowStyle() {
		return this.windowStyle;
	}

	/**
	 * @return the responseHandler
	 */
	public ResponseHandler getResponseHandler() {
		return responseHandler;
	}

	/**
	 * @param responseHandler
	 *            the responseHandler to set
	 */
	public void setResponseHandler(ResponseHandler responseHandler) {
		this.responseHandler = responseHandler;
	}

	/**
	 * @param cancelHandler
	 *            the cancelHandler to set
	 */
	public void setCancelHandler(CancelHandler cancelHandler) {
		this.cancelHandler = cancelHandler;
	}

	/**
	 * @return the cancelHandler
	 */
	public CancelHandler getCancelHandler() {
		return cancelHandler;
	}

	/**
	 * @return the icon
	 */
	public ImageDescriptor getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(ImageDescriptor icon) {
		this.icon = icon;
	}

	public boolean isReplace() {
		return replace;
	}

	public void setReplace(boolean replace) {
		this.replace = replace;
	}

}
