package com.spark.psi.publish.deliverticket.key;

import com.spark.psi.publish.LimitKey;

public class GetDeliverTicketListKey extends LimitKey {

	public GetDeliverTicketListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}
	public SortField getSortField() {
		return sortField;
	}

	public void setDateEnd(long dateEnd) {
		this.dateEnd = dateEnd;
	}

	public long getDateEnd() {
		return dateEnd;
	}

	public void setDateBegin(long dateBegin) {
		this.dateBegin = dateBegin;
	}

	public long getDateBegin() {
		return dateBegin;
	}

	private long dateBegin,dateEnd;
	private SortField sortField;
	
	public static enum SortField{
		/** 编号*/
		SheetNo("t.sheetNo"),
		/** 网上订单编号*/
		OnlineOrderNo("t.onlineOrderNo"),
		/** 客户 */
		MemberRealName("t.memberRealName"),
		/** 站点 */
		StationName("t.stationName"),
		/** 发货日期 */
		CreateDate("t.createDate"),
		/** 不排序 */
		None("t.createDate");
		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }
		
		
	}
}
