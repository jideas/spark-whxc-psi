package com.spark.psi.publish.base.function;

import java.util.Map;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>����ģ�鰴ť��λ</p>
 *


 *
 
 * @version 2012-4-18
 */
public interface FunctionPositionInfo {
	
	public FunctionPosition[] getUserFunctionPositions();
	
	public FunctionPosition getFunctionPosition(String functionId);
	
	public interface FunctionPosition {
		
		/**
		 * ����ģ��id
		 * 
		 * @return String
		 */
		public String getFunctionId();
		
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
		 * �û��Ƿ��Ѿ���ʼ��ģ�鶨λ
		 * 
		 * @return boolean
		 */
		public boolean isInited();
		
		
		/**
		 * ��ý�ɫ���ȼ�
		 * 
		 * @return Map<String,Integer>
		 */
		public Map<String,Integer> getRolePriority();
		
	}
	

	
}