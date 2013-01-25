package com.spark.psi.publish.base.notice.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;

/**
 * <p>��ѯ�����б�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */

public class FindNoticeItemListKey extends LimitKey{

	/**��ѯ���ͳ���*/
	public static final String NOT_RELEASE = "notRelease"; //��ѯδ��������

	public static final String RELEASED = "released"; //��ѯ�ѷ�������

	public static final String HISTORY = "history"; //��ѯ��ʷ����
	
	/**�����ı�(�������ģ��ƥ��)*/
	private String searchText;

	/**��ѯ����*/
	private String type;

	/**������GUID*/
	private GUID createGuid;

	/**������������ID*/
	private GUID deptGuid;

	/**QueryTerm�б�*/
	private QueryTerm queryTerm;

	/**��������*/
	private String sortCloumName;

	/**����ʽ*/
	private SortType sortType;
	
	/** 
	 *���췽��
	 */
	public FindNoticeItemListKey(){
		this(0, 10000, true);
	}
	
	/** 
     *���췽��
     *@param offset ƫ����
     *@param count ����
     *@param queryTotal �Ƿ��ѯ����
     */
    public FindNoticeItemListKey(int offset, int count, boolean queryTotal){
	    super(offset, count, queryTotal);
    }

	public String getSearchText(){
		return searchText;
	}

	public void setSearchText(String searchText){
		this.searchText = searchText;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public GUID getCreateGuid(){
		return createGuid;
	}

	public void setCreateGuid(GUID createGuid){
		this.createGuid = createGuid;
	}

	public GUID getDeptGuid(){
		return deptGuid;
	}

	public void setDeptGuid(GUID deptGuid){
		this.deptGuid = deptGuid;
	}

	public QueryTerm getQueryTerm(){
		return queryTerm;
	}

	public void setQueryTerm(QueryTerm queryTerm){
		this.queryTerm = queryTerm;
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
