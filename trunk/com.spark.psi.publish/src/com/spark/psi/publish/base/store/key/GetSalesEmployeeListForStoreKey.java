package com.spark.psi.publish.base.store.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.base.organization.key.GetEmployeeListByAuthKey;

/**
 * 
 * <p>��ѯ�ֿ��Ӧ������Ա���б�</p>
 *


 *
 
 * @version 2012-4-17
 */
public class GetSalesEmployeeListForStoreKey extends GetEmployeeListByAuthKey {
	
	private GUID storeId;
	
	public GetSalesEmployeeListForStoreKey(GUID storeId){
		super(Auth.Sales);
	    this.storeId = storeId;
    }

	public GUID getStoreId(){
    	return storeId;
    }
	
	
	
}