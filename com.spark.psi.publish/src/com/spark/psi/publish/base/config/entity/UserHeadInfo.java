package com.spark.psi.publish.base.config.entity;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.graphics.ImageData;

/**
 * 
 * <p>用户头像</p>
 * context.find(UserHeadInfo.class);  //查询当前用户的头像
 * context.find(UserHeadInfo.class,GUID) //查询指定头像
 * context.getList(UserHeadInfo.class) //查询所有头像
 * 	


 *
 
 * @version 2012-4-19
 */
public interface UserHeadInfo{
	
	public GUID getId();
	
	/**
	 * 获得头像图片
	 * 
	 * @return byte[]
	 */
	public byte[] getImg();
	
	/**
	 * 获得图片类别
	 * 
	 * @return String
	 */
	public String getImgClass();
	
}
