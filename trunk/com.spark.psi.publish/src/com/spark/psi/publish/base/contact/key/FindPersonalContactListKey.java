package com.spark.psi.publish.base.contact.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.SortType;

/**
 * <p>��ѯͨѶ¼(����)�б�Key</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-3
 */

public class FindPersonalContactListKey extends LimitKey{

	/**ƴ������*/
	private String phonetics;

	/**�����ı�(ģ��ƥ��)*/
	private String searchText;

	/**��������*/
	private String sortCloumName;

	/**����ʽ*/
	private SortType sortType;

	/** 
	 *���췽��
	 */
	public FindPersonalContactListKey(){
		this(0, 10000, true);
	}

	/** 
	 *���췽��
	 *@param offset ƫ����
	 *@param count ����
	 *@param queryTotal �Ƿ��ѯ����
	 */
	public FindPersonalContactListKey(int offset, int count, boolean queryTotal){
		super(offset, count, queryTotal);
	}

	public String getPhonetics(){
		return phonetics;
	}

	public void setPhonetics(String phonetics){
		this.phonetics = phonetics;
	}

	public String getSearchText(){
		return searchText;
	}

	public void setSearchText(String searchText){
		this.searchText = searchText;
	}

	public String getSortCloumName(){
		return sortCloumName;
	}

	public void setSortCloumName(String sortCloumName){
		this.sortCloumName = sortCloumName;
	}

	public SortType getSortType(){
		return sortType;
	}

	public void setSortType(SortType sortType){
		this.sortType = sortType;
	}
}
