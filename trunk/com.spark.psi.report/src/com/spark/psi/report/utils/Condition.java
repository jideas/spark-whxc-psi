/**
 * 
 */
package com.spark.psi.report.utils;

import com.spark.psi.report.constant.ReportConstants.ConditionType;

/**
 * 过滤条件对象
 */
public class Condition{

	public boolean equals(Object obj){
		String s = obj.toString().toUpperCase();
		return this.conditionColumn.toUpperCase().equals(s);
	}

	/**
	 * @param target
	 *            指标
	 * @param type
	 *            连接类型（=,>,<,<>,In....）
	 * @param value
	 *            值（包括集合List）
	 */
	public Condition(String conditionColumn, ConditionType type, Object value){
		this.conditionColumn = conditionColumn;
		this.value = value;
		this.type = type;
	}

	/**
	 * @param target
	 *            指标
	 * @param type
	 *            连接类型（=,>,<,<>,In....）
	 * @param begginValue
	 *            起始值
	 * @param EndValue
	 *            结束值
	 */
	public Condition(String conditionColumn, ConditionType type, Object begginValue, Object EndValue){
		this.conditionColumn = conditionColumn;
		this.type = type;
		this.begginValue = begginValue;
		this.EndValue = EndValue;
	}

	/**
	 * 过滤条件关联的指标
	 */
	private String conditionColumn;

	/**
	 * 过滤条件的值
	 */
	private Object value;

	private Object begginValue;
	private Object EndValue;

	/**
	 * @return the begginValue
	 */
	public Object getBegginValue(){
		return begginValue;
	}

	/**
	 * @param begginValue
	 *            the begginValue to set
	 */
	public void setBegginValue(Object begginValue){
		this.begginValue = begginValue;
	}

	/**
	 * @return the endValue
	 */
	public Object getEndValue(){
		return EndValue;
	}

	/**
	 * @param endValue
	 *            the endValue to set
	 */
	public void setEndValue(Object endValue){
		EndValue = endValue;
	}

	/**
	 * 数据库表达式类型
	 */
	private ConditionType type;

	/**
	 * @return the conditionColumn
	 */
	public String getConditionColumn(){
		return conditionColumn;
	}

	/**
	 * @param conditionColumn the conditionColumn to set
	 */
	public void setConditionColumn(String conditionColumn){
		this.conditionColumn = conditionColumn;
	}

	/**
	 * @return the value
	 */
	public Object getValue(){
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value){
		this.value = value;
	}

	/**
	 * @return the type
	 */
	public ConditionType getType(){
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ConditionType type){
		this.type = type;
	}

	/**
	 * @return
	 */
	public Condition copy(){
		return new Condition(this.conditionColumn, this.type, this.value);
	}

}
