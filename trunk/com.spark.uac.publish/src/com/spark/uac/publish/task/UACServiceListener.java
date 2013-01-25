package com.spark.uac.publish.task;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.uac.publish.HostType;
import com.spark.uac.publish.ProductSerialsEnum;

/**
 * 给子服务器发送消息，保持子服务器持有主服务器的访问地址
 
 *
 */
@StructClass
public class UACServiceListener extends SimpleTask {

	@StructField
	private Item[] hostInfos;
	
	public UACServiceListener(Item[] hostInfos){
		this.hostInfos = hostInfos;
	}

	public Item[] getHostInfos() {
		return hostInfos;
	}	
	@StructClass
	public static class Item {
		
		@StructField
		private String id;
		@StructField
		private String productSerials;
		
		@StructField
		private String url;
		@StructField
		private HostType type;
		public String getUrl(){
        	return url;
        }
		public void setUrl(String url){
        	this.url = url;
        }
		public HostType getType(){
        	return type;
        }
		public void setType(HostType type){
        	this.type = type;
        }
		public String getId(){
        	return id;
        }
		public void setId(String id){
        	this.id = id;
        }
		public String getProductSerials(){
        	return productSerials;
        }
		public void setProductSerials(String productSerials){
        	this.productSerials = productSerials;
        }
		
	}
	
}
