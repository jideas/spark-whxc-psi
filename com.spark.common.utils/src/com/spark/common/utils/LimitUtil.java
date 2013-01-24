package com.spark.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>分页工具类</p>
 *


 *
 
 * @version 2012-3-19
 */
public final class LimitUtil{

	/**
	 * 对指定list进行分页处理，返回一个新的list<BR>
	 * 新的List长度为pageCount
	 * @param <T>
	 * @param list 需要分页操作的对象
	 * @param offset 偏移值
	 * @param pageCount 每页长度
	 * @return List<T> 进行过分页处理的新List
	 */
	
	public static <T> List<T> limit(List<T> list,int offset,int pageCount){
		if(true)return list; //暂时不处理分页 edit周利均
		List<T> result = new ArrayList<T>();
		for(int i = 0;i<list.size();i++){
	        if(i >= offset){
	        	result.add(list.get(i));
	        }
	        if(result.size()==pageCount){
	        	break;
	        }
        }
		return result;
	}
	
}
