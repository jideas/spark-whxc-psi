/**
 * 
 */
package com.spark.psi.report.utils;

import com.spark.psi.report.constant.ReportConstants.ConditionType;

/**
 * ������������
 */
public class Condition{

	public boolean equals(Object obj){
		String s = obj.toString().toUpperCase();
		return this.conditionColumn.toUpperCase().equals(s);
	}

	/**
	 * @param target
	 *            ָ��
	 * @param type
	 *            �������ͣ�=,>,<,<>,In....��
	 * @param value
	 *            ֵ����������List��
	 */
	public Condition(String conditionColumn, ConditionType type, Object value){
		this.conditionColumn = conditionColumn;
		this.value = value;
		this.type = type;
	}

	/**
	 * @param target
	 *            ָ��
	 * @param type
	 *            �������ͣ�=,>,<,<>,In....��
	 * @param begginValue
	 *            ��ʼֵ
	 * @param EndValue
	 *            ����ֵ
	 */
	public Condition(String conditionColumn, ConditionType type, Object begginValue, Object EndValue){
		this.conditionColumn = conditionColumn;
		this.type = type;
		this.begginValue = begginValue;
		this.EndValue = EndValue;
	}

	/**
	 * ��������������ָ��
	 */
	private String conditionColumn;

	/**
	 * ����������ֵ
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
	 * ���ݿ���ʽ����
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
