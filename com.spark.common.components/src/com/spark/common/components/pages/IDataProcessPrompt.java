package com.spark.common.components.pages;

/**
 * 数据处理提示接口<BR>
 * 一般而言，在Processor中实现该接口，即可具有获取数据保存提示的功能
 */
public interface IDataProcessPrompt {

	public String getPromptMessage();

	public boolean processData();

}
