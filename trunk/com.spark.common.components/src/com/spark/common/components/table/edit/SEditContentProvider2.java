package com.spark.common.components.table.edit;


/**
 * 编辑内容数据提供器
 * 
 */
public interface SEditContentProvider2 extends SEditContentProvider {

	public SNameValue[] getOptions(Object element, String colummName);

}
