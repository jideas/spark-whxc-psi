package com.spark.psi.publish.base.organization.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>ͨ�����ź�ְ�ܻ��Ա���б�</p>
 *


 *
 
 * @version 2012-3-13
 */
public class GetEmployeeListByAuthKey extends LimitKey{
	
	/**
	 * ְ��
	 */
	private Auth[] auths;

	/**
	 * �Ƿ��ѯ������˾��Χ��Ա��
	 */
	private boolean queryAll = false;

	/**
	 * ����ID��queryAllΪfalseʱ��Ч��Ϊ��ʱ��ѯ��˾ֱ����
	 */
	private GUID departmentId;

	/**
	 * ��ѯ��ǰ�û�������(�ݹ鵽��ײ㲿��)ӵ��ָ��ְ�ܵ�Ա���б�
	 * @param auths ְ������
	 */
	public GetEmployeeListByAuthKey(Auth... auths){
		super(0,20,false);
		this.auths = auths;
	}
	
	/**
	 * ��ѯ������˾ӵ��ָ��ְ�ܵ�Ա���б�
	 * @param queryAll Ϊtrueʱ��ѯ������˾
	 * @param auths
	 */
	public GetEmployeeListByAuthKey(boolean queryAll,Auth... auths){
		super(0,20,false);
		this.auths = auths;
		this.queryAll = queryAll;
	}
	
	/**
	 * ��ѯָ��������(�ݹ鵽��ײ㲿��)ӵ��ָ��ְ�ܵ�Ա���б�
	 * @param departmentId
	 * @param auths
	 */
	public GetEmployeeListByAuthKey(GUID departmentId,Auth... auths){
		super(0,20,false);
		this.auths = auths;
		this.departmentId = departmentId;
	}

	public Auth[] getAuths(){
    	return auths;
    }

	public boolean isQueryAll(){
    	return queryAll;
    }

	public GUID getDepartmentId(){
    	return departmentId;
    }
	
	
}
