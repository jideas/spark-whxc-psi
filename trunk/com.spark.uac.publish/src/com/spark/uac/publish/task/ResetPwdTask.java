/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.uac.publish.ProductSerialsEnum;

/**
 * <p>��������</p>
 *


 *
 
 * @version 2012-4-11
 */
public class ResetPwdTask extends SimpleTask{
	
	public enum ErrType{
		MobileNo,
		CompanyName,
		Name
	}
	
	private ProductSerialsEnum productSerialsEnum;
	
	private ErrType errType;
	
	/**
	 * ��ҵ����
	 */
	private String tenantName;
	
	/**
	 * �û�����
	 */
	private String userName;
	
	/**
	 * �ֻ�����
	 */
	private String mobileNumber;
	
	/**
	 * ���ؼ�����(�ɲ�����)
	 */
	private String code;
	
	/**
	 * ������Ϣ
	 */
	private String msg;
	
	/**
	 * �����Ƿ�ɹ�
	 */
	private boolean succeed;
	
	public ResetPwdTask(String tenantName,String userName,String mobileNumber){
	    this.tenantName = tenantName;
	    this.userName = userName;
	    this.mobileNumber = mobileNumber;
    }
	
	public ResetPwdTask(String tenantName,String userName,String mobileNumber,ProductSerialsEnum productSerialsEnum){
	    this.tenantName = tenantName;
	    this.userName = userName;
	    this.mobileNumber = mobileNumber;
	    this.productSerialsEnum = productSerialsEnum;
    }

	public String getCode(){
    	return code;
    }

	public void setCode(String code){
    	this.code = code;
    }

	public String getMsg(){
    	return msg;
    }

	public void setMsg(String msg){
    	this.msg = msg;
    }

	public boolean isSucceed(){
    	return succeed;
    }

	public void setSucceed(boolean succeed){
    	this.succeed = succeed;
    }

	public String getTenantName(){
    	return tenantName;
    }

	public String getUserName(){
    	return userName;
    }

	public String getMobileNo(){
    	return mobileNumber;
    }

	public ErrType getErrType(){
    	return errType;
    }

	public void setErrType(ErrType errType){
    	this.errType = errType;
    }

	public ProductSerialsEnum getProductSerialsEnum(){
    	return productSerialsEnum;
    }

	
}
