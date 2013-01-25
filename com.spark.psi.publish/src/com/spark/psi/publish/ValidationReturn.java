package com.spark.psi.publish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * <p>Task��������У������װ</p>
 *


 *
 
 * @version 2012-3-22
 */
public class ValidationReturn<T>{
	
	/**
	 * 
	 * <p>�쳣����</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-22
	 */
	public enum ErrorLevel{
		/** ���󼶱� */
		Error,
		/**
		 * ���漶��
		 */
		Warning,
		/**
		 * ��ʾ����
		 */
		Tooltip
	}
	
	
	private int index = 0;  //Ĭ��Ϊ0
	
	
	public ValidationReturn(int index){
		this.index = index;
	}
	private List<T> errors = new ArrayList<T>();
	
	private Map<Integer,ValidationReturn<T>> itemErrors = new HashMap<Integer,ValidationReturn<T>>();
	
	private Map<ErrorLevel,List<T>> errorsByLevelMap = new HashMap<ErrorLevel, List<T>>();
	
	public ValidationReturn(int index,T e){
		this(index,e,ErrorLevel.Error);
	}
	
	public ValidationReturn(int index,T e,ErrorLevel level){
		this();
		this.addError(e,level);
	}
	
	public ValidationReturn(){
		for(ErrorLevel el : ErrorLevel.values()){
			errorsByLevelMap.put(el, new ArrayList<T>());
		}		
	}
	
	public List<T> getErrors1(){
		return errors;
	}
	
	/**
	 * �жϴ����Ƿ���ָ������
	 * @param error
	 * @return boolean
	 */
	public boolean isHaveError(T error){
		for(T t : errors){
			if(t.equals(error)){
				return true;
			}
		}
		return false;
	}
	
	public ValidationReturn<T>[] getItemErrors(){
		return itemErrors.values().toArray(new ValidationReturn[itemErrors.size()]);
	}

	public int getIndex(){
    	return index;
    }

	public void setIndex(int index){
    	this.index = index;
    }
	
	public void addError(T e){
		addError(e,ErrorLevel.Error);
	}
	
	public void addError(T e,ErrorLevel level){
		this.errors.add(e);
		errorsByLevelMap.get(level).add(e);
	}
	
	public void addItemError(int index,T e){
		addItemError(index, e, ErrorLevel.Error);
	}
	
	public void addItemError(int index,T e,ErrorLevel level){
		if(itemErrors.containsKey(index)){
			itemErrors.get(index).addError(e,level);
		}else{
			itemErrors.put(index, new ValidationReturn<T>(index, e,level));
		}
	}
	
	/**
	 * ���ָ���쳣�����ö��
	 * 
	 * @param level
	 * @return T[]
	 */
	public T[] getErrors(ErrorLevel level){
		List<T> list = errorsByLevelMap.get(level);
		return (T[])list.toArray();
	}
	
}
