package com.spark.psi.publish.base.contact.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.SortType;

/**
 * <p>查询通讯录(客户)列表Key</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-3
 */

public class FindCustomerSupplierContactListKey extends LimitKey{

	/**拼音过滤*/
	private String phonetics;

	/**搜索文本(模糊匹配)*/
	private String searchText;

	/**客户或供应商类型*/
	private PartnerType partnerType;

	/**排序列名*/
	private String sortCloumName;

	/**排序方式*/
	private SortType sortType;

	/** 
	 *构造方法
	 */
	public FindCustomerSupplierContactListKey(){
		this(0, 10000, true);
	}

	/** 
	 *构造方法
	 *@param offset 偏移量
	 *@param count 数量
	 *@param queryTotal 是否查询总数
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
