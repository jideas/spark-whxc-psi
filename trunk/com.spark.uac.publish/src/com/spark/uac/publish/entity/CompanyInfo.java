package com.spark.uac.publish.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>企业信息对象</p>
 *


 *
 
 * @version 2012-7-2
 */
public interface CompanyInfo{

	public GUID getId();

	public String getName();


	public String getShortName();

	public byte[] getLogo();

	
	
	
}
