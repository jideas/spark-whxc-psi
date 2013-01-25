package com.spark.psi.publish.base.contact.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.SortType;

/**
 * <p>��ѯͨѶ¼(�ͻ�)�б�Key</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-3
 */

public class FindCustomerSupplierContactListKey extends LimitKey{

	/**ƴ������*/
	private String phonetics;

	/**�����ı�(ģ��ƥ��)*/
	private String searchText;

	/**�ͻ���Ӧ������*/
	private PartnerType partnerType;

	/**��������*/
	private String sortCloumName;

	/**����ʽ*/
	private SortType sortType;

	/** 
	 *���췽��
	 */
	public FindCustomerSupplierContactListKey(){
		this(0, 10000, true);
	}

	/** 
	 *���췽��
	 *@param offset ƫ����
	 *@param count ����
	 *@param queryTotal �Ƿ��ѯ����
	 */
	public FindCustomerSupplierContactListKey(int offset, int count, boolean queryTotal){
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

	public PartnerType getPartnerType(){
		return partnerType;
	}

	public void setPartnerType(PartnerType partnerType){
		this.partnerType = partnerType;
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
