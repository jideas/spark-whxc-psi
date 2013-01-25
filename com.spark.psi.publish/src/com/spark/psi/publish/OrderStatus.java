package com.spark.psi.publish;


/**
 * 
 * <p>����״̬</p>
 *


 *
 
 
 */
public enum OrderStatus{
	
	/**
	 * ���ύ
	 */
	Submit("���ύ"),
	/**
	 * ����״̬Ϊ�����(����δ���)
	 * ��������ҳǩʹ�ô�״̬
	 */
	Approval_No("������"),
	
	/**
	 * ����״̬Ϊ����ˣ�����ͨ���������
	 * ���۸���ҳǩʹ�ô�״̬
	 */
	Approval_Yes("������"),

	/**
	 * δ���
	 */
	CheckingIn_NO("δ���"),
	/**
	 * δ����
	 */
	CheckingOut_No("δ����"),
	/**
	 * �������
	 */
	CheckingIn_Part("�������"),
	/**
	 * ���ų���
	 */
	CheckingOut_Part("���ֳ���"),
	/**
	 * ȫ�����
	 */
	CheckedIn("ȫ�����"),
	/**
	 * ȫ������
	 */
	CheckedOut("ȫ������"),
	/**
	 * δ����
	 */
	CONSIGNMENT_NO("δ����"),
	/**
	 * �ѷ���
	 */
	CONSIGNMENT_YES("�ѷ���"),
	/**
	 * �Ѳ���
	 */
	Denied("���˻�"),
	/**
	 * ����ֹ����������ǰ̨���̨Ҫ���ݣ�
	 */
	Stop("����ֹ"),
	/**
	 * �����
	 */
	finish("�����");
	
	
	final String name;

	public String getName(){
    	return name;
    }
	
	private OrderStatus(String name){
		this.name = name;
	}
	
	/**
	 * �жϵ�ǰö���Ƿ���ָ��ö�ټ����У�������Ļ�����true
	 * @param statuss
	 * @return boolean
	 */
	public boolean isIn(OrderStatus...statuss){
		for(OrderStatus os : statuss){
			if(this == os){
				return true;
			}
		}
		return false;
	}
	
}	
