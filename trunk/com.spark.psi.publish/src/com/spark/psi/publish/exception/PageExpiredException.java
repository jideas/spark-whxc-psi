package com.spark.psi.publish.exception;

/**
 * 
 * <p>ҳ���ѹ���</p>
 *


 *
 
 * @version 2012-5-10
 */
public class PageExpiredException extends RuntimeException{

	private static final String msg = "ҳ���ѹ���";
	
    private static final long serialVersionUID = 4876266253328566423L;

    public PageExpiredException(){
	    super(msg);
    }
}
