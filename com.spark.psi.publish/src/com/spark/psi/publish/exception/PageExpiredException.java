package com.spark.psi.publish.exception;

/**
 * 
 * <p>页面已过期</p>
 *


 *
 
 * @version 2012-5-10
 */
public class PageExpiredException extends RuntimeException{

	private static final String msg = "页面已过期";
	
    private static final long serialVersionUID = 4876266253328566423L;

    public PageExpiredException(){
	    super(msg);
    }
}
