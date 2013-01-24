package com.spark.common.components.pages;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.SashForm;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.StyledPanel;

/**
 * 页面视图渲染基类
 */
public abstract class PageRender {

	/**
	 * 
	 */
	protected Map<String, Control> controls = new HashMap<String, Control>();

	/**
	 * 
	 */
	protected PageController pageController;

	/**
	 * 父容器
	 */
	protected Composite contentArea;

	/**
	 * 
	 */
	protected PageProcessor processor;

	/**
	 * 
	 * @param contentArea
	 */
	public void setContentArea(Composite contentArea) {
		this.contentArea = contentArea;
	}

	/**
	 * 
	 * @param pageController
	 */
	public void setPageController(PageController pageController) {
		this.pageController = pageController;
	}

	/**
	 * 
	 * @param processor
	 */
	public void setProcessor(PageProcessor processor) {
		this.processor = processor;
	}

	/**
	 * 
	 * @return
	 */
	protected final Situation getContext() {
		return this.contentArea.getContext();
	}

	/**
	 * 
	 * @return
	 */
	protected final Object getArgument() {
		return processor.getArgument();
	}

	/**
	 * 
	 * @return
	 */
	protected final Object getArgument2() {
		return processor.getArgument2();
	}

	/**
	 * 
	 * @return
	 */
	protected final Object getArgument3() {
		return processor.getArgument3();
	}

	/**
	 * 
	 * @return
	 */
	protected final Object getArgument4() {
		return processor.getArgument4();
	}

	/**
	 * 页面初始化之前
	 * 
	 * @param situation
	 */
	public void init(Situation context) {

	}

	/**
	 * 
	 */
	protected abstract void beforeRender();

	/**
	 * 
	 */
	protected abstract void doRender();

	/**
	 * 
	 */
	protected void afterRender() {
		registerControls(contentArea);
	}

	/**
	 * 
	 * @param parent
	 */
	private final void registerControls(Composite parent) {
		Control[] children = parent.getChildren();
		for (Control control : children) {
			if (control.getID() != null) {
				controls.put(control.getID(), control);
			}
			if (control instanceof Composite) {
				registerControls((Composite) control);
			} else if (control instanceof ScrolledPanel) {
				registerControls(((ScrolledPanel) control).getComposite());
			} else if (control instanceof StyledPanel) {
				registerControls(((StyledPanel) control).getHeaderArea());
				registerControls(((StyledPanel) control).getContentArea());
			} else if (control instanceof SashForm) {
				registerControls(((SashForm) control).getFirstComposite());
				registerControls(((SashForm) control).getSecondComposite());
			}
		}
	}

	/**
	 * 创建控件
	 * 
	 * @param clazz
	 * @param controlId
	 * @param style
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T extends Control> T createControl(String controlId,
			Class<T> clazz, int style) {
		return (T) controls.get(controlId);
	}

}
