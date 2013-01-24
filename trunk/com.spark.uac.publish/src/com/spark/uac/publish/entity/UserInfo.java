package com.spark.uac.publish.entity;

import java.net.URL;

import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.publish.UserStatus;

/**
 * 
 * <p>��¼�û��ӿ�</p>
 *


 *
 
 * @version 2012-4-10
 */
public interface UserInfo {
	
	/**
	 * �û�id
	 * 
	 * @return GUID
	 */
	public GUID getUserId();

	/**
	 * �⻧id
	 * 
	 * @return GUID
	 */
	public GUID getTenantId();
	
	/**
	 * �⻧���ڷ������ĵ�ַ
	 * 
	 * @return URL
	 */
	public URL getUrl();
	
	/**
	 * ����û����Ե�¼�Ĳ�Ʒϵ��
	 * 
	 * @return HostInfo[]
	 */
	public HostInfo[] getProductSerialsHosts();
	
	/**
	 * �⻧����
	 * ����Ӫϵͳһ�£�
	 * @return String
	 */
	public String getTenantName();
	/**
	 * ������
	 * 
	 * @return String
	 */
	public String getTenantCode();

	/**
	 * �ֻ�����
	 * 
	 * @return String
	 */
	public String getMobileNo();
	
	/**
	 * ����
	 * 
	 * @return String
	 */
	public GUID getPassword();
	
	/**
	 * �Ƿ���Ч
	 * 
	 * @return boolean
	 */
	public boolean isEnabled();
	
	/**
	 * �û�״̬
	 * 
	 * @return UserStatus
	 */
	public UserStatus getStatus();
	
}
