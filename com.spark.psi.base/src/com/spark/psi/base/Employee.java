package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * 
 * <p>Ա����Դ�ӿ�</p>
 *


 *
 
 * @version 2012-4-1
 */
public interface Employee{

	public GUID getId();
	
	public String getName();

	public String getMobileNo();
	
	public String getLandLineNumber();
	
	public GUID getLogo();

	public String getIdNumber();

	public long getBirthday();

	public String getEmail();

	public String getPosition();

	public int getRoles();

	public String getStatus();
	
	public long getCreateDate();
	
	public String getStyle();

	/**
	 * �⻧id
	 * 
	 * @return GUID
	 */
	public GUID getTenantId();

	public GUID getDepartmentId();

	

	/**
	 * �жϵ�ǰ�û��Ƿ�ӵ����ָ��������һ��Ȩ��
	 * @param auths
	 * @return
	 */
	public boolean hasAuth(Auth... auths);
	
	/**
	 * �жϵ�ǰ�û��Ƿ�ӵ����ָ��������Ȩ��
	 * 
	 * @param auths
	 * @return boolean
	 */
	public boolean hasAllAuth(Auth...auths);

	/**
	 * 
	 * @return
	 */
	public Auth[] getAcls();

}
