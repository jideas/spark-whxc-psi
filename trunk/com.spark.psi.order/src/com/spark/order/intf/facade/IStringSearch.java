package com.spark.order.intf.facade;

/**
 * <p>�ַ�������������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-24
 */
public interface IStringSearch {
	/**
	 * ���������ı�����Code����
	 * @param searchText
	 * @return String[]
	 */
	String[] searchValue(String searchText);
	/**
	 * ���������ı�����Code���ϣ�֧��Valueƴ��������������
	 * @param searchText
	 * @return String[]
	 */
	String[] searchValue2(String searchText);
	/**
	 * ������������
	 * @param code
	 * @param value
	 * @return IStringSearch
	 */
	IStringSearch put(String code, String value);
}
