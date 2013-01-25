package com.spark.order.promotion.intf;

import java.util.ArrayList;
import java.util.List;

import com.spark.psi.publish.PromotionStatus;

/**
 * <p>促销状态</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public enum PromotionStatus2 {
	/**
	 * 未确认
	 */
	Submit(PromotionStatus.Submit, "01", "未确认"),
	/**
	 * 待发布
	 */
	Issue(PromotionStatus.Issue, "02", "待发布"),
	/**
	 * 待审批
	 */
	Approve(PromotionStatus.Approve, "03", "待审批"),
	/**
	 * 已退回
	 */
	Return(PromotionStatus.Return, "04", "已退回"),
	/**
	 * 促销中
	 */
	Promotioning(PromotionStatus.Promotioning, "05", "促销中"),
	/**
	 * 已中止
	 */
	Suspended(PromotionStatus.Suspended, "06", "已中止"),
	/**
	 * 已过期
	 */
	Out_of_date(PromotionStatus.Out_of_date, "07", "已过期"),
	/**
	 * 已停售
	 */
	Stoped_sales(PromotionStatus.Stoped_sales, "08", "已停售"),
	/**
	 * 已售完
	 */
	Finished(PromotionStatus.Finished, "09", "已售完");
	private PromotionStatus status;
	private String code;
	private String name;
	private PromotionStatus2(PromotionStatus status, String code, String name){
		PromotionConstant2.statusPubToMe.put(status, this);
		PromotionConstant2.statusMap.put(code, this);
		PromotionConstant2.StatusSearchUtil.put(code, name);
		this.status = status;
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
	 * 获得当前状态对应的外部状态，无返回null
	 * @return PromotionStatus
	 */
	public PromotionStatus getPubstatus() {
		return status;
	}
	
	/**
	 * 判断当前枚举是否是直供枚举中的一种，是返回true
	 * @param statuss
	 * @return boolean
	 */
	public boolean isInEnum(PromotionStatus2...statuss ){
		for(PromotionStatus2 ps : statuss){
			if(this == ps){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 将外部状态转换成内部状态数组
	 * @param pubstatuss
	 * @return PromotionStatus2[]
	 */
	public static PromotionStatus2[] getPubToMe(PromotionStatus... pubstatuss){
		List<PromotionStatus2> list = new ArrayList<PromotionStatus2>();
		for(PromotionStatus status : pubstatuss){
			list.add(getPubToMe(status));
		}
		return list.toArray(new PromotionStatus2[list.size()]);
	}
	
	/**
	 * 将外部状态转换成内部状态数组
	 * @param pubstatuss
	 * @return PromotionStatus2[]
	 */
	public static List<PromotionStatus2> getPubToMeList(PromotionStatus... pubstatuss){
		List<PromotionStatus2> list = new ArrayList<PromotionStatus2>();
		for(PromotionStatus status : pubstatuss){
			list.add(getPubToMe(status));
		}
		return list;
	}
	
	/**
	 * 根据外部状态获取内部状态
	 * @param pubstatus
	 * @return PromotionStatus2
	 */
	public static PromotionStatus2 getPubToMe(PromotionStatus pubstatus){
		return PromotionConstant2.statusPubToMe.get(pubstatus);
	}
	
	/**
	 * 搜索用，返回code集合
	 * @param searchText
	 * @return String[]
	 */
	public static List<String> searchstatus(String searchText){
		return PromotionConstant2.StatusSearchUtil.getKeys(searchText);
	}
	
	/**
	 * 根据key获得枚举项
	 * @param code
	 * @return PromotionStatus
	 */
	public static PromotionStatus2 getPromotionStatus2(String code){
		return PromotionConstant2.statusMap.get(code);
	}
}
