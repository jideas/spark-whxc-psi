package com.spark.common.components.pages;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

/**
 * 用来标识一个是主功能页面
 * 
 * @author 李孜
 * 
 */
public interface MainFunction extends Functions, Function {

	/**
	 * 模块名称
	 * 
	 * @return String
	 */
	String getName();

	/**
	 * 模块类别
	 * 
	 * @return String
	 */
	String getGroup();
	
	/**
	 * 模块编号
	 * 
	 * @return String
	 */
	String getCode();

	/**
	 * 模块标题
	 */
	String getTitle();
	
	/**
	 * 是否默认放在桌面
	 * 以用户自定义为准，若用户第一次登录或没有拖放过此模块，则取这个值
	 * @return boolean
	 */
	boolean isPutMain();

	BaseFunction[] getBaseFunctions(Context context);

	ImageDescriptor getTitleIcon();

	ImageDescriptor getSmallNormalIcon();

	ImageDescriptor getSmallHoverIcon();

	ImageDescriptor getMiddleNormalIcon();

	ImageDescriptor getMiddleHoverIcon();

	ImageDescriptor getLargeNormalIcon();

	ImageDescriptor getLargeHoverIcon();

}
