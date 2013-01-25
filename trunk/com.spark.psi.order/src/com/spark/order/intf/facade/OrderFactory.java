package com.spark.order.intf.facade;

import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;


/**
 * <p>��������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-24
 */
public final class OrderFactory {
	private OrderFactory(){
	}
	
	private final static IStringSearch salesType, purchaseType, salesstatus, purchasestatus;
	static{
		//��������
		salesType = new StringSearchImpl();
		for(TypeEnum type : TypeEnum.values()){
			salesType.put(type.getKey(), type.getName(OrderEnum.Sales));
		}
		//�ɹ�����
		purchaseType = new StringSearchImpl();
		for(TypeEnum type : TypeEnum.values()){
			purchaseType.put(type.getKey(), type.getName(OrderEnum.Purchase));
		}
		//����״̬
		salesstatus = new StringSearchImpl();
		for(StatusEnum status : StatusEnum.values()){
			salesstatus.put(status.getKey(), status.getName(OrderEnum.Sales));
		}
		//�ɹ�״̬
		purchasestatus = new StringSearchImpl();
		for(StatusEnum status : StatusEnum.values()){
			purchasestatus.put(status.getKey(), status.getName(OrderEnum.Purchase));
		}
	}
	/**
	 * �����������������
	 * @param orderEnum
	 * @return IStringSearch
	 * @throws OrderException 
	 */
	public final static IStringSearch searchSalesType(OrderEnum orderEnum) throws OrderException {
		switch (orderEnum) {
		case Sales:
			return salesType;
		case Sales_Return:
			return salesType;
		case Purchase:
			return purchaseType;
		case Purchase_Return:
			return purchaseType;
		default:
			throw new OrderException("��ǰ����������������֧�֡�");
		}
	}
	/**
	 * ���״̬����������
	 * @param orderEnum
	 * @return IStringSearch
	 * @throws OrderException 
	 */
	public final static IStringSearch searchSalesstatus(OrderEnum orderEnum) throws OrderException {
		switch (orderEnum) {
		case Sales:
			return salesstatus;
		case Sales_Return:
			return salesstatus;
		case Purchase:
			return purchasestatus;
		case Purchase_Return:
			return purchasestatus;
		default:
			throw new OrderException("��ǰ����������������֧�֡�");
		}
	}
}
