package com.spark.order.intf.facade;

/**
 * <p>�����쳣</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-24
 */
public class OrderException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1<<9L;
	public OrderException(){
		super();
	}
	public OrderException(String arg0){
		super(arg0);
	}
	public OrderException(OrderException arg0){
		super(arg0);
	}
	public OrderException(String arg0, OrderException arg1){
		super(arg0, arg1);
	}
}
