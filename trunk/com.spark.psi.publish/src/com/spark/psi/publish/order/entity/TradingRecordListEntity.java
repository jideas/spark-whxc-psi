package com.spark.psi.publish.order.entity;

import java.util.List;

import com.spark.psi.publish.ListEntity;


/**
 * 
 * <p>���׼�¼�б�</p>
 * ��ѯ��Ӧ�̽��׼�¼
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ������
 * @version 2012-2-22
 */
public class TradingRecordListEntity extends ListEntity<OrderItem>{

	protected double totalAmount; //�����ܽ��
	
	protected double orderAmount;  //�������
	
	protected double returnAmount;  //�˻����
	
	protected int orderCount;	//��������
	
	protected int returnCount;  //�˻�����
	
	public TradingRecordListEntity(List<OrderItem> dataList, int totalCount) {
		super(dataList, totalCount);
	}

	public double getTotalAmount(){
    	return totalAmount;
    }

	public double getOrderAmount(){
    	return orderAmount;
    }

	public double getReturnAmount(){
    	return returnAmount;
    }

	public int getReturnCount(){
    	return returnCount;
    }

	public int getOrderCount() {
		return orderCount;
	}

}
