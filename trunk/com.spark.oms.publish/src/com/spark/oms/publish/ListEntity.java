package com.spark.oms.publish;

import java.util.List;

/**
 * �洢����б�����������
 * @param <T>
 */

public class ListEntity <T>{
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	//��Ŀ
	private int count = 0;
	//�ܽ��
	private double totalAmount = 0;
	
	//list �����
	private List<T> list;

}
