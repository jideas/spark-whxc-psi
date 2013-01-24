package com.spark.common.components.pages;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

/**
 * ������ʶһ����������ҳ��
 * 
 * @author ����
 * 
 */
public interface MainFunction extends Functions, Function {

	/**
	 * ģ������
	 * 
	 * @return String
	 */
	String getName();

	/**
	 * ģ�����
	 * 
	 * @return String
	 */
	String getGroup();
	
	/**
	 * ģ����
	 * 
	 * @return String
	 */
	String getCode();

	/**
	 * ģ�����
	 */
	String getTitle();
	
	/**
	 * �Ƿ�Ĭ�Ϸ�������
	 * ���û��Զ���Ϊ׼�����û���һ�ε�¼��û���ϷŹ���ģ�飬��ȡ���ֵ
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
