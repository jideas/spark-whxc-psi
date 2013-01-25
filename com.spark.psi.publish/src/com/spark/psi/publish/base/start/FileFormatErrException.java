package com.spark.psi.publish.base.start;

/**
 * 
 * <p>文件格式错误</p>
 *


 *
 
 * @version 2012-7-9
 */
public class FileFormatErrException extends IllegalArgumentException{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public FileFormatErrException(String msg){
	    super(msg);
    }

}
