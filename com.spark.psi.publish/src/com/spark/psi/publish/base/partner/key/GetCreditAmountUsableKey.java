package com.spark.psi.publish.base.partner.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ѯ�ͻ��������ö��</p>
 * context.find(Double.class,GetCreditAmountUaableKey)


 *
 
 * @version 2012-6-27
 */
public class GetCreditAmountUsableKey{
	
	private GUID id;
	
	public GetCreditAmountUsableKey(GUID id){
	    this.id = id;
    }

	public GUID getId(){
    	return id;
    }
	
	
	
}
