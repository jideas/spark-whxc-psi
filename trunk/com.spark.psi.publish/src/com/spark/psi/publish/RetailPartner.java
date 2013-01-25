package com.spark.psi.publish;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>���ǿͻ������ǲɹ���Ӧ��</p>
 * ��ѯ���� context.find(PartnerInfo.class,RetailPartner)


 *
 
 * @version 2012-5-8
 */
public enum RetailPartner{
	/**
	 * ���ۿͻ�
	 */
	Customer(GUID.valueOf("00001000000000000000000000000000"),"���ۿͻ�"),
	/**
	 * ���ǲɹ���Ӧ��
	 */
	Supplier(GUID.valueOf("00000100000000000000000000000000"),"���ǲɹ�");
	
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
