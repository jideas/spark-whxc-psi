package com.spark.common.components.pages;

/**
 * 页面控制器，用于向相关控制页面提供页面的处理器和渲染器类型
 * 
 * @author leezizi
 * 
 */
public final class PageController {

	private Class<? extends PageProcessor> processorClazz;

	private Class<? extends PageRender> renderClazz;

	public PageController(Class<? extends PageProcessor> processorClazz,
			Class<? extends PageRender> renderClazz) {
		super();
		this.processorClazz = processorClazz;
		this.renderClazz = renderClazz;
	}

	/**
	 * @return the processorClazz
	 */
	public Class<? extends PageProcessor> getProcessorClazz() {
		return processorClazz;
	}

	/**
	 * @return the renderClazz
	 */
	public Class<? extends PageRender> getRenderClazz() {
		return renderClazz;
	}

}
