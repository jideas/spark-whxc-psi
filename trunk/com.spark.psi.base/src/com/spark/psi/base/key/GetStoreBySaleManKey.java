package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.StoreStatus;

/**
 * 
 * <p>����Ա��id��ѯ�����Ĳֿ��б�</p>
 *


 *
 
 * @version 2012-3-12
 */
public class GetStoreBySaleManKey extends Key{

	/**
	 * �ֿ�״̬ Ĭ��Ϊ��ѯ�����õĲֿ�
	 */
	private StoreStatus[] StoreStatuss = {StoreStatus.ENABLE};

	/**
	 * ��ѯ���۾����Ա�������������õĲֿ��б� 
	 * @param salesManId
	 */
	public GetStoreBySaleManKey(GUID salesManId){
	    super(salesManId);
    }
	
	/**
	 * ��ѯ���۾����Ա��������ָ��״̬�Ĳֿ��б�
	 * @param salesManId
	 * @param StoreStatus �ֿ�״̬
	 */
	public GetStoreBySaleManKey(GUID salesManId,StoreStatus... StoreStatuss){
	    super(salesManId);
	    this.StoreStatuss = StoreStatuss;
    }

	public StoreStatus[] getStoreStatus(){
    	return StoreStatuss;
    }	

}
