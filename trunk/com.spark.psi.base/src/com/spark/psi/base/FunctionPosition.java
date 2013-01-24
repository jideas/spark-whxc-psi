package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�û�����ģ�鶨λ��Ϣ</p>
 *


 *
 
 * @version 2012-4-18
 */
public interface FunctionPosition{
	
	public GUID getId();
	
	/**
	 * ����ģ��id
	 * 
	 * @return String
	 */
	public String getFunctionId();
	
	/**
	 * �û�id
	 * 
	 * @return GUID
	 */
	public GUID getUserId();
	
	/**
	 * ͼ���������ϵ�X����
	 * 
	 * @return int
	 */
	public int getXindex();
	
	/**
	 * ͼ���������ϵ�Y����
	 * 
	 * @return int
	 */
	public int getYindex();
	
	/**
	 * �Ƿ����������
	 * 
	 * @return boolean
	 */
	public boolean isPutMain();
	
}
