package com.spark.common.utils.exceptions;

/**
 * ���ݹ����쳣
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
