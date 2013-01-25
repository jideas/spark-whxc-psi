package com.spark.uac.publish;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;

@StructClass
public enum HostType {
	/**��֤����*/
	@StructField
	HOST_TYPE_IDENTIFY("01"),
	/**ҵ�������*/
	@StructField
	HOST_TYPE_TENANTS("02"),
	/**��Ӫ������*/
	@StructField
	HOST_TYPE_OPERATION("03");
	@StructField
	final String code;
	
	public String getCode(){
    	return code;
    }

	HostType(String code){
		this.code = code;
	}
	
	public static HostType getHostTypeByCode(String code){
		for(HostType type : HostType.values()){
            if(type.getCode().equals(code)){
            	return type;
            }
        }
		throw new IllegalArgumentException("û���������ͱ��");
	}
}
