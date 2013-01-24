package com.spark.psi.base.internal.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;


public class EnumEntityList {
	

	protected EnumType type;

	protected Map<String,EnumEntityImpl> enumMap = new LinkedHashMap<String,EnumEntityImpl>();
	
	public EnumEntityList(EnumType type){
		this.type = type;
	}
	
	
	public EnumEntity[] getList(){
		return enumMap.values().toArray(new EnumEntity[enumMap.size()]);
	}
	
	public void putEntity(String code,EnumEntityImpl entity){
		enumMap.put(code, entity);
	}
	
	public EnumEntityImpl getEnumEntity(String code){
		return enumMap.get(code);
	}	

	public EnumType getType(){
    	return type;
    }


	@StructClass
	public final static class EnumEntityImpl implements EnumEntity{
		
		protected String code;
		
		protected String name;
		
		protected List<EnumEntityImpl> children = new ArrayList<EnumEntityImpl>();

		public String getCode(){
        	return code;
        }

		public void setCode(String code){
        	this.code = code;
        }

		public String getName(){
        	return name;
        }

		public void setName(String name){
        	this.name = name;
        }

		public List<EnumEntityImpl> getChildren(){
        	return children;
        }

		public void addChild(EnumEntityImpl entity){
			this.children.add(entity);
		}
		
	}
	
}	
