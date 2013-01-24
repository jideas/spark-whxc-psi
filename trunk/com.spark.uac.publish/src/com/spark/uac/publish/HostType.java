package com.spark.uac.publish;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;

@StructClass
public enum HostType {
	/**认证中心*/
	@StructField
	HOST_TYPE_IDENTIFY("01"),
	/**业务服务器*/
	@StructField
	HOST_TYPE_TENANTS("02"),
	/**运营服务器*/
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
		throw new IllegalArgumentException("没有这种类型编号");
	}
}
