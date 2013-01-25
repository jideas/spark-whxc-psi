package com.spark.order.intf.publish.entity;

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
public class PromotionItemImpl implements com.spark.psi.publish.order.entity.PromotionItem{
	
	private GUID id;
	
	/**
	 * 商品id
	 */
	private GUID goodsItemId;  
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品属性
	 */
	private String goodsProperties;
	
	/**
	 * 促销商品数量
	 */
	private double promotionCount;
	
	/**
	 * 数量小数位数
	 */
	private int countDecimal;
	
	/**
	 * 原始单价
	 */
	private double originalPrice;
	/**
	 * 促销单价
	 */
	private double promotionPrice;
	
	/**
	 * 开始日期
	 */
	private long startDate;
	
	/**
	 * 结束日期
	 */
	private long endDate;
	
	/**
	 * 促销状态
	 */
	private PromotionStatus promotionstatus;
	
	/**
	 * 可操作
	 */
	private PromotionAction[] actions;
	
	/**
	 * 发起人
	 */
	private String creator; 
	
	/**
	 * 已售数量
	 */
	private double soldCount;

	public GUID getId(){
    	return id;
    }

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }

	public String getGoodsName(){
    	return goodsName;
    }

	public String getGoodsProperties(){
    	return goodsProperties;
    }

	public double getPromotionCount(){
    	return promotionCount;
    }

	public double getOriginalPrice(){
    	return originalPrice;
    }

	public double getPromotionPrice(){
    	return promotionPrice;
    }

	public long getStartDate(){
    	return startDate;
    }

	public long getEndDate(){
    	return endDate;
    }

	public PromotionStatus getPromotionStatus(){
    	return promotionstatus;
    }

	public String getCreator(){
    	return creator;
    }

	public double getSoldCount(){
    	return soldCount;
    }
	
	public int getScale(){
		return countDecimal;
	}
	
	public void setCountDecimal(int decimal) {
		this.countDecimal = decimal;
	}

//	public void addAction(PromotionAction... actions){
//		for(PromotionAction promotionAction : actions){
//	        this.promotionActions.add(promotionAction);
//        }
//	}
//	
//	public PromotionAction[] getActions(){
//		return promotionActions.toArray(new PromotionAction[promotionActions.size()]);
//	}
//
//	/**
//	 * @return the promotionActions
//	 */
//	public PromotionAction[] getPromotionActions() {
//		return promotionActions;
//	}
//
//	/**
//	 * @param promotionActions the promotionActions to set
//	 */
//	public void setPromotionActions(PromotionAction[] promotionActions) {
//		this.promotionActions = promotionActions;
//	}

	/**
	 * @return the actions
	 */
	public PromotionAction[] getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(PromotionAction... actions) {
		this.actions = actions;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @param goodsItemId the goodsItemId to set
	 */
	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @param goodsProperties the goodsProperties to set
	 */
	public void setGoodsProperties(String goodsProperties) {
		this.goodsProperties = goodsProperties;
	}

	/**
	 * @param promotionCount the promotionCount to set
	 */
	public void setPromotionCount(double promotionCount) {
		this.promotionCount = promotionCount;
	}

	/**
	 * @param originalPrice the originalPrice to set
	 */
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	/**
	 * @param promotionPrice the promotionPrice to set
	 */
	public void setPromotionPrice(double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param promotionstatus the promotionstatus to set
	 */
	public void setPromotionStatus(PromotionStatus promotionstatus) {
		this.promotionstatus = promotionstatus;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @param soldCount the soldCount to set
	 */
	public void setSoldCount(double soldCount) {
		this.soldCount = soldCount;
	}
		
}
