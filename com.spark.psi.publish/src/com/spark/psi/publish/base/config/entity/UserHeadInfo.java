package com.spark.psi.publish.base.config.entity;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.graphics.ImageData;

/**
 * 
 * <p>�û�ͷ��</p>
 * context.find(UserHeadInfo.class);  //��ѯ��ǰ�û���ͷ��
 * context.find(UserHeadInfo.class,GUID) //��ѯָ��ͷ��
 * context.getList(UserHeadInfo.class) //��ѯ����ͷ��
 * 	


 *
 
 * @version 2012-4-19
 */
public interface UserHeadInfo{
	
	public GUID getId();
	
	/**
	 * ���ͷ��ͼƬ
	 * 
	 * @return byte[]
	 */
	public byte[] getImg();
	
	/**
	 * ���ͼƬ���
	 * 
	 * @return String
	 */
	public String getImgClass();
	
}
