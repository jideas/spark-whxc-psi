package com.spark.psi.publish.base.contact.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>查询过滤指定联系人的主联系人的数量Key</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-8
 */

public class FindMainPersonCountKey{

	/**客户供应商Guid*/
	private GUID cusproGuid;

	/**过滤联系人Guid*/
	private GUID contactPersonGuid;

	/** 
	 *构造方法
	 */
	public FindMainPersonCountKey(){
		super();
	}

	/** 
	 *构造方法
	 *@param contactPersonGuid
	 */
	public FindMainPersonCountKey(GUID cusproGuid, GUID contactPersonGuid){
		super();
		this.cusproGuid = cusproGuid;
		this.contactPersonGuid = contactPersonGuid;
	}

	public GUID getCusproGuid(){
		return cusproGuid;
	}

	public void setCusproGuid(GUID cusproGuid){
		this.cusproGuid = cusproGuid;
	}

	public GUID getContactPersonGuid(){
		return contactPersonGuid;
	}

	public void setContactPersonGuid(GUID contactPersonGuid){
		this.contactPersonGuid = contactPersonGuid;
	}

}
