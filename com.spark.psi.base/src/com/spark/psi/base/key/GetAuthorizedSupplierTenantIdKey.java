package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获得授权供应商的远程租户ID</p>
 * 调用此方法前需要判断当前供应商是否是授权供应商<Br>
 * 如果不是授权供应商将抛出NotAuthorizedSupplierException 异常 <BR>


 *
 
 * @version 2012-3-12
 */
public class GetAuthorizedSupplierTenantIdKey extends Key{

	public GetAuthorizedSupplierTenantIdKey(GUID id){
	    super(id);
    }
	
	/**
	 * 
	 * <p>不是授权供应商异常</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-12
	 */
	public class NotAuthorizedSupplierException extends RuntimeException {
		
		public NotAuthorizedSupplierException(String guid){
			super(guid+"不是授权供应商");
		}
		
	}

}
