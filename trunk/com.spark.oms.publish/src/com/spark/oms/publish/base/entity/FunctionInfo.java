package com.spark.oms.publish.base.entity;

import com.jiuqi.dna.core.type.GUID;

public interface FunctionInfo {
	
	public GUID getRECID();

	/**
	 * @return ����ģ����
	 */
	public String getCode();

	/**
	 * @return ����ģ������
	 */
	public String getName();
	
	/**
	 * @return �ϼ�����ģ��
	 */
	public String getParent();
	
	/**
	 * @return ����ģ�����
	 */
	public String getSort();
}
