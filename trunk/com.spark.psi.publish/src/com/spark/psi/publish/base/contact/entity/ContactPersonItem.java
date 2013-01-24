package com.spark.psi.publish.base.contact.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ϵ���б���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-3
 */

public interface ContactPersonItem{

	/**����[1����ϵ�ˣ�2��ͬ��]*/
	public static final String CONTACT_PERSON = "1"; //��ϵ��
	
	public static final String COLLEAGUE = "2"; //ͬ��
	
	/**
	 * ID
	 */
	public GUID getId();

	/**
	 * ��ϵ������
	 */
	public String getName();

	/**
	 * (��λ)����
	 */
	public String getDepartment();
	
	/**
	 * ְλ
	 */
	public String getJob();

	/**
	 * �̶��绰
	 */
	public String getPhone();

	/**
	 * �ֻ�
	 */
	public String getMobile();

	/**
	 * ����
	 */
	public String getEmail();
	
	/**
	 * ����
	 */
	public String getType();
}
