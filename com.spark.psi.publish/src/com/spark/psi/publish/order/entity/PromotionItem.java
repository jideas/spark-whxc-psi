package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PromotionAction;
import com.spark.psi.publish.PromotionStatus;

/**
 * 
 * <p>促销列表</p>
 * 查询未确认的促销列表 ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = PromotionStatus.Unrecognized
 * 查询待审批的促销列表  ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = PromotionStatus.Examine
 * 查询促销中的促销列表  ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = PromotionStatus.Promotion
 * 查询促销记录  ListEntity<PromotionItem> + GetPromotionListKey   promotionstatus = null


 *
 
 * @version 2012-3-6
 */
public interface PromotionItem{
	
//	private GUID id;
//	
//	/**
//	 * 商品id
//	 */
//	private GUID goodsItemId;  
//	
//	/**
//	 * 商品名称
//	 */
//	private String goodsName;
//	
//	/**
//	 * 商品属性
//	 */
//	private String goodsProperties;
//	
//	/**
//	 * 促销商品数量
//	 */
//	private double promotionCount;
//	
//	/**
//	 * 原始单价
//	 */
//	private double originalPrice;
//	/**
//	 * 促销单价
//	 */
//	private double promotionPrice;
//	
//	/**
//	 * 开始日期
//	 */
//	private long startDate;
//	
//	/**
//	 * 结束日期
//	 */
//	private long endDate;
//	
//	/**
//	 * 促销状态
//	 */
//	private PromotionStatus promotionstatus;
//	
//	/**
//	 * 发起人
//	 */
//	private String creator; 
//	
//	/**
//	 * 已售数量
//	 */
//	private double soldCount;

	public GUID getId();

	public GUID getGoodsItemId();

	public String getGoodsName();

	public String getGoodsProperties();

	public double getPromotionCount();
	
	public int getScale();

	public double getOriginalPrice();

	public double getPromotionPrice();

	public long getStartDate();

	public long getEndDate();

	public PromotionStatus getPromotionStatus();

	public String getCreator();

	public double getSoldCount();
	
	public PromotionAction[] getActions();

		
}
