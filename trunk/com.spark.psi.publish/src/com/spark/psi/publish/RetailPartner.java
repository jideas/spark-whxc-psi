package com.spark.psi.publish;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>零星客户及零星采购供应商</p>
 * 查询方法 context.find(PartnerInfo.class,RetailPartner)


 *
 
 * @version 2012-5-8
 */
public enum RetailPartner{
	/**
	 * 零售客户
	 */
	Customer(GUID.valueOf("00001000000000000000000000000000"),"零售客户"),
	/**
	 * 零星采购供应商
	 */
	Supplier(GUID.valueOf("00000100000000000000000000000000"),"零星采购");
	
	final GUID id;
	
	final String name;
	
	private RetailPartner(GUID id,String name){
	    this.id = id;
	    this.name = name;
    }
	
	public boolean is(GUID id){
		return this.id.equals(id);	
	}

	public GUID getId(){
    	return id;
    }

	public String getName(){
    	return name;
    }
	
	
	
}
