package com.spark.psi.mainpage;

import java.util.Map;

import com.spark.common.components.pages.FunctionGroup;
import com.spark.common.components.pages.MainFunction;

/**
 * 
 * <p>�û�����ģ�鰴ť��λ</p>
 *


 *
 
 * @version 2012-4-18
 */
public interface UserFunction extends MainFunction{
	
	
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
	
	/**
	 * ����ģ�����ö��
	 * 
	 * @return FunctionGroup
	 */
	public FunctionGroup getFunctionGroup();

	
	/**
	 * ��ý�ɫ���ȼ�
	 * 
	 * @return Map<String,Integer>
	 */
	public Map<String,Integer> getRolePriority();	
}
