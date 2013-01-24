package com.spark.psi.base.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * 
 * <p> ��ѯ�������Ȳ��ţ��ӱ���һֱ׷�ݵ���˾��һˮ���Ĳ��ţ�ָ��ְ�ܵ�Ա���б�����ָ�����ŵ�Ա����</p>
 *


 *
 
 * @version 2012-3-15
 */
public class GetAncestorDeptEmployeeListByAuthKey extends
        GetEmployeeListByAuthKey
{

	/**
	 * ����id
	 */
	private GUID departmentId;
	
	/**
	 * ���ŵĲ㼶���
	 */
	private Level level = Level.Max;
	
	/**
	 * ��ѯָ�����ŵ����Ȳ���
	 * @param departmentId ����id
	 */
	public GetAncestorDeptEmployeeListByAuthKey(GUID departmentId){
		super(new Auth[0]);
		this.departmentId = departmentId;
	}
	
	/**
	 * ��ѯ�������Ȳ��ţ��ӱ���һֱ׷�ݵ���˾��һˮ���Ĳ��ţ�ָ��ְ�ܵ�Ա���б�����ָ�����ŵ�Ա����
	 * @param auths ְ���б�
	 * @param departmentId ����id
	 * ��ʱδʵ��
	 */
	public GetAncestorDeptEmployeeListByAuthKey(Auth[] auths,GUID departmentId){
	    super(auths);
	    this.departmentId = departmentId;
    }
	
	/**
	 * ��ѯ�������Ȳ��ţ��ӱ���һֱ׷�ݵ���˾��һˮ���Ĳ��ţ�ָ��ְ�ܵ�Ա���б�����ָ�����ŵ�Ա����
	 * @param auths ְ���б�
	 * @param departmentId ����id
	 * @param level ��ѯ���
	 * ��ʱδʵ��
	 */
	private GetAncestorDeptEmployeeListByAuthKey(Auth[] auths,GUID departmentId,Level level){
		this(auths,departmentId);
	}

	public GUID getDepartmentId(){
    	return departmentId;
    }
	
	public int getLevel(){
    	return level.getLevel();
    }
	
	

}
