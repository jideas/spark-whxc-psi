package com.spark.psi.publish;

/**
 * <p>����״̬</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public enum NoticeStatus{

	NotRelease("01", "δ��������"),
	Released("02", "�ѷ�������"),
	Cancel("03", "����������"),
	Overdue("04", "���ڹ���");

	final String key;
	final String name;

	public String getKey(){
		return key;
	}

	public String getName(){
		return name;
	}

	private NoticeStatus(String key, String name){
		this.key = key;
		this.name = name;
	}
}
