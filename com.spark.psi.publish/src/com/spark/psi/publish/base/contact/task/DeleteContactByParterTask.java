package com.spark.psi.publish.base.contact.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.PartnerType;

/**
 * <p>根据客户/供应商删除联系人</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-16
 */

public class DeleteContactByParterTask extends SimpleTask{

	/**客户/供应商类型*/
	private PartnerType partnerType;

	/** 
	 *构造方法
	 *@param partnerType
	 */
	public DeleteContactByParterTask(PartnerType partnerType){
		super();
		this.partnerType = partnerType;
	}

	public PartnerType getPartnerType(){
		return partnerType;
	}

	public void setPartnerType(PartnerType partnerType){
		this.partnerType = partnerType;
	}

}
