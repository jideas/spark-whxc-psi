package com.spark.order.intf.facade;

/**
 * <p>订单异常</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

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
