package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�����Ȩ��Ӧ�̵�Զ���⻧ID</p>
 * ���ô˷���ǰ��Ҫ�жϵ�ǰ��Ӧ���Ƿ�����Ȩ��Ӧ��<Br>
 * ���������Ȩ��Ӧ�̽��׳�NotAuthorizedSupplierException �쳣 <BR>


 *
 
 * @version 2012-3-12
 */
public class GetAuthorizedSupplierTenantIdKey extends Key{

	public GetAuthorizedSupplierTenantIdKey(GUID id){
	    super(id);
    }
	
	/**
	 * 
	 * <p>������Ȩ��Ӧ���쳣</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-12
	 */
	public class NotAuthorizedSupplierException extends RuntimeException {
		
		public NotAuthorizedSupplierException(String guid){
			super(guid+"������Ȩ��Ӧ��");
		}
		
	}

}
