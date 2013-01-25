package com.spark.uac.publish.entity;

import java.net.URL;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.spark.uac.publish.HostType;
@StructClass
public interface HostInfo{
	
//	/**
//	 * 认证中心主机id
//	 */
//	public static final String Uac_Server_Id = "0";
	@StructField
	String URL_TEMPLATE = "http://%s:%d";


	public String getId();
	
	/**
	 * 得到组合后的ip地址
	 */
	public URL getURL();
	
	/**
	 * 得到组合后的域名地址
	 */
	public URL getDomainURL();
	
	/**
	 * 服务地址
	 * @return
	 */
	public String getHost();

	/**
	 * 服务器端口
	 * @return
	 */
	public int getPort();

	/**
	 * 服务器域名
	 * @return
	 */
	public String getDomain();
	
	/**
	 * 登录时是否启用域名登录
	 * @return
	 */
	public boolean getDomainEnabled();
	
	/**
	 * 服务器类型
	 * @return
	 */
	public HostType getType();

	/**
	 * 服务器描述
	 */
	public String getTitle();
	
	/**
	 * 产品系列
	 * 
	 * @return String
	 */
	public String getProductSerials();
}
