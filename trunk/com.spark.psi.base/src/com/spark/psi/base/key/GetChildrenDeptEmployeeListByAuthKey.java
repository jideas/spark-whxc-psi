package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * 
 * <p>��ѯ��ǰ���ż��Ӳ��ŵ�ӵ��ָ��ְ�ܵ�Ա���б�</p>
 *


 *
 
 * @version 2012-3-14
 */
public class GetChildrenDeptEmployeeListByAuthKey extends GetEmployeeListByAuthKey {
	
	/**
	 * ����id
	 */
	private GUID departmentId;
	
	/**
	 * ���ŵĲ㼶���
	 */
	private Level level = Level.Max;
	
	/**
	 * ��ѯ�Ӳ���ָ��ְ�ܵ�Ա���б�����ָ�����ŵ�Ա����
	 * @param departmentId ����id
	 */
	public GetChildrenDeptEmployeeListByAuthKey(GUID departmentId){
	    this.departmentId = departmentId;
    }
	
	/**
	 * ��ѯ�Ӳ���ָ��ְ�ܵ�Ա���б�����ָ�����ŵ�Ա����
	 * @param auths ְ���б�
	 * @param departmentId ����id
	 */
	public GetChildrenDeptEmployeeListByAuthKey(GUID departmentId,Auth... auths){
	    super(auths);
	    this.departmentId = departmentId;
    }
	
	/**
	 * ��ѯ�Ӳ���ָ��ְ�ܵ�Ա���б�����ָ�����ŵ�Ա����
	 * @param auths ְ���б�
	 * @param departmentId ����id
	 * @param level ��ѯ���
	 */
	public GetChildrenDeptEmployeeListByAuthKey(GUID departmentId,Level level,Auth... auths){
		this(departmentId,auths);
	}

	public GUID getDepartmentId(){
    	return departmentId;
    }

	public Level getLevel(){
    	return level;
    }
	
}
