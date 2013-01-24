package com.spark.psi.publish;


/**
 * 
 * <p>促销状态</p> 
 */
public enum PromotionStatus{
	/**
	 * 未确认
	 */
	Submit("01", "未确认"),
	/**
	 * 待发布
	 */
	Issue("02", "待发布"),
	/**
	 * 待审批
	 */
	Approve("03", "待审批"),
	/**
	 * 已退回
	 */
	Return("04", "已退回"),
	/**
	 * 促销中
	 */
	Promotioning("05", "促销中"),
	/**
	 * 已中止
	 */
	Suspended("06", "已中止"),
	/**
	 * 已过期
	 */
	Out_of_date("07", "已过期"),
	/**
	 * 已停售
	 */
	Stoped_sales("08", "已停售"),
	/**
	 * 已售完
	 */
	Finished("09", "已售完");
	private String code;
	private String name;
	private PromotionStatus(String code, String name){
		this.code = code;
		this.name = name;
	}
	/**
	 * 状态标识
	 * @return String
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 状态名称
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 判断当前枚举是否是直供枚举中的一种，是返回true
	 * @param statuss
	 * @return boolean
	 */
	public boolean isIn(PromotionStatus...statuss ){
		for(PromotionStatus ps : statuss){
			if(this == ps){
				return true;
			}
		}
		return false;
	}
}
