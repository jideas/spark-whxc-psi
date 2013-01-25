package com.spark.psi.publish;

/**
 * 
 * <p>��������</p>
 *


 *
 
 
 */
public enum OrderType{
	
	PLAIN("��ͨ����"), 
	ON_LINE("���϶���"), 
	PLAIN_DIRECT("��ͨ����(ֱ��)"), 
	ON_LINE_DIRECT( "���϶���(ֱ��)"), 
	SALES_RETURN("�����˻�"),
	Purchase_Return("�ɹ��˻�"),
	Purchase_SPORADIC("���ǲɹ�"),
	Retail_Order("��Ʒ����"),
	Retail_Return("�����˻�");
	
	final String name;
	
	public String getName(){
    	return name;
    }

	private OrderType(String name){
		this.name = name;
	}
	
	public boolean isIn(OrderType...types){
		for(OrderType type : types){
			if(this == type){
				return true;
			}
		}
		return false;
	}
	
}
