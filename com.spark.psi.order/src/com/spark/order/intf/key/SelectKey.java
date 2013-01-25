package com.spark.order.intf.key;

import java.util.HashMap;
import java.util.Map;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;
/**
 * <p>��ѯkey�ӿ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-10-13
 */
public class SelectKey{
	private static final Map<SortType, SortEnum> sortMap = new HashMap<SortType, SortEnum>();
	/**
	 * <p>����ʽ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-19
	 */
	public enum SortEnum{
		/**
		 * ����
		 */
		Asc(SortType.Asc, ""), 
		/**
		 * ����
		 */
		Desc(SortType.Desc, "desc");
		private SortType sort;
		private String value;
		private SortEnum(SortType sort, String value){
			this.sort = sort;
			this.value = value;
			sortMap.put(sort, this);
		}
		public SortType getSort() {
			return sort;
		}
		public String getValue() {
			return value;
		}
		public static SortEnum getSortEnum(SortType sort){
			return sortMap.get(sort);
		}
	}
	/**
	 * <p>��ѯʱЧ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-31
	 */
	public class SelectTerm{
		private long beginTime;
		private Long endTime;
		private String name;
		public SelectTerm(QueryTerm term){
			this.beginTime = term.getStartTime();
			this.endTime = term.getEndTime();
			this.name = term.getName();
		}
		public String getName() {
			return name;
		}
		public long getBeginTime() {
			return beginTime;
		}
		public Long getEndTime() {
			return endTime;
		}
	}
	public enum ScopeEnum{
		Main,
		Dept,
	}
	
	protected long offset;
	protected long pageSize;
	/**
	 * �������
	 */
	private String sortType = SortEnum.Asc.value;
	/**
	 * �����ı�
	 */
	protected String searchText;
	/**
	 * ʱЧ
	 */
	protected SelectTerm time;
	
	/**
	 * �ⲿ����ֵ
	 * @param key void
	 */
	public void setLimitKey(LimitKey key){
		this.offset = key.getOffset();
		this.pageSize = key.getCount();
		this.searchText = key.getSearchText();
		if(null != key.getSortType()){
			this.sortType = sortMap.get(key.getSortType()).getValue();
		}
	}
	
	/**
	 * ����ʱЧ����
	 * @param term void
	 */
	public void setQueryTerm(QueryTerm term){
		if(null != term){
			time = new SelectTerm(term);
		}
	}
	
	public SelectTerm getTime() {
		return time;
	}
	
	public String getSearchText() {
		return searchText;
	}
	
	public String getSortType() {
		return sortType;
	}
	@Deprecated
	public void setSortType(SortEnum sortType) {
		this.sortType = sortType.value;
	}
	@Deprecated
	public void setSortType(SortType sortType) {
		this.sortType = SortEnum.getSortEnum(sortType).value;
	}
	public long getOffset() {
		return offset;
	}
	public long getPageSize() {
		return pageSize;
	}
	@Deprecated
	public void setOffset(long offset) {
		this.offset = offset;
	}
	@Deprecated
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	@Deprecated
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
