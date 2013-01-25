package com.spark.psi.publish.base.function;

import java.util.Map;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>功能模块按钮定位</p>
 *


 *
 
 * @version 2012-4-18
 */
public interface FunctionPositionInfo {
	
	public FunctionPosition[] getUserFunctionPositions();
	
	public FunctionPosition getFunctionPosition(String functionId);
	
	public interface FunctionPosition {
		
		/**
		 * 功能模块id
		 * 
		 * @return String
		 */
		public String getFunctionId();
		
		/**
		 * 图标在桌面上的X坐标
		 * 
		 * @return int
		 */
		public int getXindex();
		
		/**
		 * 图标在桌面上的Y坐标
		 * 
		 * @return int
		 */
		public int getYindex();
		
		/**
		 * 是否放在桌面上
		 * 
		 * @return boolean
		 */
		public boolean isPutMain();
		
		/**
		 * 用户是否已经初始化模块定位
		 * 
		 * @return boolean
		 */
		public boolean isInited();
		
		
		/**
		 * 获得角色优先级
		 * 
		 * @return Map<String,Integer>
		 */
		public Map<String,Integer> getRolePriority();
		
	}
	

	
}