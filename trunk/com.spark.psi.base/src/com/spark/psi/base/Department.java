package com.spark.psi.base;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.entity.ILevelTree;
import com.spark.psi.publish.Auth;

/**
 * ������Ϣ<br>
 * 
 * ��ѯ������<br>
 * (1)����id��ѯDepartment
 * 
 */
public interface Department extends ILevelTree {


	/**
	 * ����ID
	 * @return
	 */
	public GUID getId();
	public String getCode();
	/**
	 * ��������
	 * @return
	 */
	public String getName();
	
	/**
	 * ����Ӳ����б�
	 * 
	 * @return Department[]
	 */
	public Department[] getChildren(final Context context,Auth... auth);
	
	/**
	 * ������ﲿ���б�
	 * 
	 * @return Department[]
	 */
	public Department[] getDescendants(final Context context,Auth... auth);
	
	/**
	 * ������Ȳ����б�
	 * 
	 * @return Department[]
	 */
	public Department[] getAncestors(final Context context);
	
	/**
	 * ����ϼ�����
	 * 
	 * @return Department
	 */
	public Department getParent(final Context context);
	
	/**
	 * �ϼ�����id
	 */
	public GUID getParent();
//	
//	/**
//	 * ���Ա�����������������ﲿ�ŵ�Ա����
//	 * 
//	 * @return int
//	 */
//	public int getEmployeeCount();
	
	/**
	 * �Ƿ�ӵ��ָ��ְ��
	 * @param auths
	 * @return
	 */
	public boolean hasAuth(Auth...auths);
	
	/**
	 * ְ���б�
	 * 
	 * @return Auth[]
	 */
	public Auth[] getAuths();
	
	/**
	 * ��������
	 * 
	 * @return int
	 */
	public int getSortIndex();
	
	/**
	 * �⻧id
	 * 
	 * @return GUID
	 */
	public GUID getTenantId();
	
	/**
	 * �Ƿ���Ч
	 * 
	 * @return boolean
	 */
	public boolean isValid();
	
}
