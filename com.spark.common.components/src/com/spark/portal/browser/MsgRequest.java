package com.spark.portal.browser;

import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageControllerInstance;

/**
 * �����ָ���������Ϣ
 * 
 */
public class MsgRequest {

	/**
	 * �������
	 */
	private String title;

	/**
	 * ����ͼ��
	 */
	private ImageDescriptor icon;

	/**
	 * ������������ܽ���ʵ��
	 */
	private BaseFunction[] functions;

	/**
	 * Ĭ�����������д򿪣��������windowStyle��������Ӧ�����д�
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
	 * �Ƿ񸲸ǵ�ǰҳ��
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
