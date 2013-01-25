package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>根据条件查询订单信息(业务服务用)</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-3-8
 */
public class GetOrderListBusKey {
	private GUID partnerGuid;
	//生效时间
	private Long beginTime;
	private Long endTime;
	/**
	 * 客户供应商id
	 * @return GUID
	 */
	public GUID getPartnerGuid() {
		return partnerGuid;
	}
	public void setPartnerGuid(GUID partnerGuid) {
		this.partnerGuid = partnerGuid;
	}
	/**
	 * 开始时间
	 * @return Long
	 */
	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 结束时间
	 * @return Long
	 */
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
}
