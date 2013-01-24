package com.spark.common.components.table.edit;

import com.spark.common.components.table.SContentProvider;

/**
 * 编辑内容数据提供器
 * 
 */
public interface SEditContentProvider extends SContentProvider {

	public String getValue(Object element, String columnName);

	public String[] getActionIds(Object element);

	public Object getNewElement();

	public SNameValue[] getExtraData(Object element);

}
