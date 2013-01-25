package com.spark.psi.publish.base.notice.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;

/**
 * <p>查询公告列表</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */

public class FindNoticeItemListKey extends LimitKey{

	/**查询类型常量*/
	public static final String NOT_RELEASE = "notRelease"; //查询未发布公告

	public static final String RELEASED = "released"; //查询已发布公告

	public static final String HISTORY = "history"; //查询历史公告
	
	/**搜索文本(公告标题模糊匹配)*/
	private String searchText;

	/**查询类型*/
	private String type;

	/**创建人GUID*/
	private GUID createGuid;

	/**公告所属部门ID*/
	private GUID deptGuid;

	/**QueryTerm列表*/
	private QueryTerm queryTerm;

	/**排序列名*/
	private String sortCloumName;

	/**排序方式*/
	private SortType sortType;
	
	/** 
	 *构造方法
	 */
	public FindNoticeItemListKey(){
		this(0, 10000, true);
	}
	
	/** 
     *构造方法
     *@param offset 偏移量
     *@param count 数量
     *@param queryTotal 是否查询总数
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
