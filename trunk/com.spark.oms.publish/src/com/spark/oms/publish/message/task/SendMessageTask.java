package com.spark.oms.publish.message.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 短信发送接口：接收外围发送的短信列表
 */
public class SendMessageTask extends SimpleTask{
	/**
	 * 短信Id号 
	 * 短信Id号由发送短信方自动生成,用于用户发送短信的回执ID,目前暂未实现
	 * SMSRECID
	 */
	private GUID id;
	/**
	 * 租户id
	 */
	private GUID tenantsId;
	/**
	 * 租户名称
	 * 可以不赋值
	 */
	private String tenantsName;
	/**
	 * 手机号码字符串，格式为 移动电话：电话号码;电话号码，号码之间以";"为连接符
	 */
	private String mobile;
	/**
	 * 发送内容
	 */
	private String content;
	
	public SendMessageTask(String mobile,String content){
	    this.mobile = mobile;
	    this.content = content;
    }
	
	public SendMessageTask(String mobile,String content,GUID tenantId){
	    this.mobile = mobile;
	    this.content = content;
	    this.tenantsId = tenantId;
    }
	
	public SendMessageTask(){
    }
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getTenantsId() {
		return tenantsId;
	}
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	public String getTenantsName() {
		return tenantsName;
	}
	public void setTenantsName(String tenantsName) {
		this.tenantsName = tenantsName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
