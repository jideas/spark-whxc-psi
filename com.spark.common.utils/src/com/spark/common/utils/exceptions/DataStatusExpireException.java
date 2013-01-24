package com.spark.common.utils.exceptions;

/**
 * 数据过期异常
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-23
 */
@SuppressWarnings("serial")
public class DataStatusExpireException extends RuntimeException{
	public DataStatusExpireException(){
		super();
	}
	public DataStatusExpireException(String msg){
		super(msg);
	}
}
