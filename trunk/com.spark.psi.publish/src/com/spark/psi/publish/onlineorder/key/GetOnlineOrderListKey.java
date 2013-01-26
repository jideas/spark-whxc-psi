package com.spark.psi.publish.onlineorder.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.OnlineOrderType;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.QueryTerm;

/**
 * 查询网上订单列表Key
 * 
 */
public class GetOnlineOrderListKey extends LimitKey{

	public GetOnlineOrderListKey(int offset, int count, boolean queryTotal) {
		super(offset, count, queryTotal);
	}
	private OnlineOrderStatus[] status;
	private QueryTerm queryTerm;
	private QueryScope queryScope;
	private SortField sortField;
	private GUID stationId;
	private Boolean returnFlag;
	private OnlineOrderType orderType;
	public OnlineOrderStatus[] getStatus() {
		return status;
	}
	public void setStatus(OnlineOrderStatus... status) {
		this.status = status;
	}
	public QueryTerm getQueryTerm() {
		return queryTerm;
	}
	public void setQueryTerm(QueryTerm queryTerm) {
		this.queryTerm = queryTerm;
	}
	public QueryScope getQueryScope() {
		return queryScope;
	}
	public void setQueryScope(QueryScope queryScope) {
		this.queryScope = queryScope;
	}
	public SortField getSortField() {
		return sortField;
	}
	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}
	
	public GUID getStationId() {
		return stationId;
	}
	public void setStationId(GUID stationId) {
		this.stationId = stationId;
	}

	/**
	 * 
	 * 高级搜索
	 */
	public void setAdvanceValues(AdvanceValues advanceValues) {
		this.advanceValues = advanceValues;
	}
	public AdvanceValues getAdvanceValues() {
		return advanceValues;
	}
	public void setReturnFlag(Boolean returnFlag) {
		this.returnFlag = returnFlag;
	}
	public Boolean isReturnFlag() {
		return returnFlag;
	}
	public void setOrderType(OnlineOrderType orderType) {
		this.orderType = orderType;
	}
	public OnlineOrderType getOrderType() {
		return orderType;
	}
	private AdvanceValues advanceValues;
	public class AdvanceValues
	{
		private String billsNo;
		private String realName;
		private String stationName;
		private long createDateBegin;
		private long createDateEnd;
		private long deliveryeDateBegin;
		private long deliveryeDateEnd;
		private String deliverTime;
		public String getBillsNo() {
			return billsNo;
		}
		public void setBillsNo(String billsNo) {
			this.billsNo = billsNo;
		}
		public String getRealName() {
			return realName;
		}
		public void setRealName(String realName) {
			this.realName = realName;
		}
		public String getStationName() {
			return stationName;
		}
		public void setStationName(String stationName) {
			this.stationName = stationName;
		}
		public long getCreateDateBegin() {
			return createDateBegin;
		}
		public void setCreateDateBegin(long createDateBegin) {
			this.createDateBegin = createDateBegin;
		}
		public long getCreateDateEnd() {
			return createDateEnd;
		}
		public void setCreateDateEnd(long createDateEnd) {
			this.createDateEnd = createDateEnd;
		}
		public long getDeliveryeDateBegin() {
			return deliveryeDateBegin;
		}
		public void setDeliveryeDateBegin(long deliveryeDateBegin) {
			this.deliveryeDateBegin = deliveryeDateBegin;
		}
		public long getDeliveryeDateEnd() {
			return deliveryeDateEnd;
		}
		public void setDeliveryeDateEnd(long deliveryeDateEnd) {
			this.deliveryeDateEnd = deliveryeDateEnd;
		}
		public String getDeliverTime() {
			return deliverTime;
		}
		public void setDeliverTime(String deliverTime) {
			this.deliverTime = deliverTime;
		}
		
	}
	public static enum SortField{
		/** 订单编号*/
		BillsNo("t.billsNo"),
		/** 客户 */
		RealName("t.realName"),
		/** 订单金额 */
		TotalAmount("t.totalAmount"),
		/** 下单日期 */
		CreateDate("t.createDate"),
		/** 交货日期 */
		DeliveryeDate("t.deliveryeDate"),
		/** 站点 */
		StationName("t.stationName"),
		/** 不排序 */
		None("t.deliveryeDate");
		

		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldName(){
        	return fieldName;
        }
		
		
	}
}
