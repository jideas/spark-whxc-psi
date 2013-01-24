package com.spark.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>��ҳ������</p>
 *


 *
 
 * @version 2012-3-19
 */
public final class LimitUtil{

	/**
	 * ��ָ��list���з�ҳ��������һ���µ�list<BR>
	 * �µ�List����ΪpageCount
	 * @param <T>
	 * @param list ��Ҫ��ҳ�����Ķ���
	 * @param offset ƫ��ֵ
	 * @param pageCount ÿҳ����
	 * @return List<T> ���й���ҳ�������List
	 */
	
	public static <T> List<T> limit(List<T> list,int offset,int pageCount){
		if(true)return list; //��ʱ�������ҳ edit������
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
