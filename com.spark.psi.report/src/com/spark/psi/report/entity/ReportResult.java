/**
 * 
 */
package com.spark.psi.report.entity;

import java.util.HashMap;
import java.util.Map;

import com.spark.common.utils.character.CheckIsNull;

/**
 * 分析数据明细
 */
public class ReportResult{

	/**
	 * 数据存储位置，私有
	 */
	@SuppressWarnings("unchecked")
	private Map<Enum, Object> map;

	private Object orderObj;

	@SuppressWarnings("unchecked")
	public ReportResult(){
		map = new HashMap<Enum, Object>();
	}

	@SuppressWarnings("unchecked")
	public void setTargetValue(Enum target, Object value){
		map.put(target, value);
	}

	/**
	 * 获得具体的指标值
	 * 
	 * @param <T>
	 * @param t
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getTargetValue(Class<T> t, Enum target){
		if(null == map){
			return null;
		}
		return (T)map.get(target);
	}

	@SuppressWarnings("unchecked")
	public Object getTargetValue(Enum target){
		if(null == map){
			return null;
		}
		return map.get(target);
	}

	/**
	 * 得到数字类型的值
	 * 
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Double getDoubleValue(Enum target){
		if(null == map || CheckIsNull.isEmpty(map.get(target))){
			return null;
		}
		return Double.parseDouble(map.get(target).toString());
	}

	/**
	 * 得到字符串类型的值
	 * 
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getStringValue(Enum target){
		if(null == map){
			return null;
		}
		if(CheckIsNull.isEmpty(map.get(target))){
			return null;
		}
		return map.get(target).toString();
	}

	/**
	 * 得到长整形类型的值
	 * 
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Long getLongValue(Enum target){
		if(null == map || CheckIsNull.isEmpty(map.get(target))){
			return null;
		}
		return Long.parseLong(map.get(target).toString());
	}

	/**
	 * 得到整形类型的值
	 * 
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getIntegerValue(Enum target){
		if(null == map || CheckIsNull.isEmpty(map.get(target))){
			return null;
		}
		return Integer.parseInt(map.get(target).toString());
	}

	@SuppressWarnings("unchecked")
	public void setOrderObj(Enum target){
		this.orderObj = map.get(target);
	}

	/**
	 * @return the orderObj
	 */
	public Object getOrderObj(){
		return orderObj;
	}
	@Override
	public boolean equals(Object obj){
		if(obj instanceof ReportResult){
			ReportResult rr = (ReportResult)obj;
			if(null == this.orderObj){
				return super.equals(rr);
			}
			else{
				return this.orderObj.equals(rr.getOrderObj());
			}
		}
		return false;
	}
}
