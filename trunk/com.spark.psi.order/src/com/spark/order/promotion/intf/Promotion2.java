package com.spark.order.promotion.intf;

import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.order.intf.entity.EntityFather;
import com.spark.psi.publish.PromotionStatus;

/**
 * <p>商品促销</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public class Promotion2 extends EntityFather{
	protected GUID goodsItemId;// 商品GUID guid
	protected String goodsName;// 商品名称 varchar 50;
	protected String goodsNamePY;// 名称拼音 varchar 50;
	protected String goodsProperties;//商品属性 varchar 1000;
	protected int countDecimal;//商品小数位数 int
	protected double promotionCount;// 促销数量 numeric 17 5
	protected double saledCount;// 已销售数量 numeric 17 5
	protected double price_goods;// 原价 numeric 17 2
	protected double price_promotion;//促销价格 numeric 17 2
	protected long beginDate;// 开始日期 date
	protected long endDate;// 结束日期 date
	protected String status;// 状态 char 2
	protected String promotionCause;// 促销原因 varchar 500
	protected String returnCause;// 退回/或中止原因 varchar 500
	protected long approvalDate;// 审核时间 date
	
	protected GUID deptId;//发起人部门 

	/**
	 * @return the goodsItemId
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}

	/**
	 * @param goodsItemId the goodsItemId to set
	 */
	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
		this.goodsNamePY = PinyinHelper.getLetter(goodsName);
	}

	/**
	 * @return the goodsNamePY
	 */
	public String getGoodsNamePY() {
		return goodsNamePY;
	}

//	/**
//	 * @param goodsNamePY the goodsNamePY to set
//	 */
//	public void setGoodsNamePY(String goodsNamePY) {
//		this.goodsNamePY = goodsNamePY;
//	}

	public int getScale() {
		return countDecimal;
	}

	public void setCountDecimal(int countDecimal) {
		this.countDecimal = countDecimal;
	}

	public String getStatus() {
		return status;
	}
	
	public PromotionStatus getPubstatus(){
		return PromotionStatus2.getPromotionStatus2(status).getPubstatus();
	}

	/**
	 * @return the goodsProperties
	 */
	public String getGoodsProperties() {
		return goodsProperties;
	}

	/**
	 * @param goodsProperties the goodsProperties to set
	 */
	public void setGoodsProperties(String goodsProperties) {
		this.goodsProperties = goodsProperties;
	}

	/**
	 * @return the promotionCount
	 */
	public double getPromotionCount() {
		return promotionCount;
	}

	/**
	 * @param promotionCount the promotionCount to set
	 */
	public void setPromotionCount(double promotionCount) {
		this.promotionCount = promotionCount;
	}

	/**
	 * @return the saledCount
	 */
	public double getSaledCount() {
		return saledCount;
	}

	/**
	 * @param saledCount the saledCount to set
	 */
	public void setSaledCount(double saledCount) {
		this.saledCount = saledCount;
	}

	/**
	 * @return the price_goods
	 */
	public double getPrice_goods() {
		return price_goods;
	}

	/**
	 * @param priceGoods the price_goods to set
	 */
	public void setPrice_goods(double priceGoods) {
		price_goods = priceGoods;
	}

	/**
	 * @return the price_promotion
	 */
	public double getPrice_promotion() {
		return price_promotion;
	}

	/**
	 * @param pricePromotion the price_promotion to set
	 */
	public void setPrice_promotion(double pricePromotion) {
		price_promotion = pricePromotion;
	}

	/**
	 * @return the beginDate
	 */
	public long getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(long beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public long getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the promotionCause
	 */
	public String getPromotionCause() {
		return promotionCause;
	}

	/**
	 * @param promotionCause the promotionCause to set
	 */
	public void setPromotionCause(String promotionCause) {
		this.promotionCause = promotionCause;
	}

	/**
	 * @return the returnCause
	 */
	public String getReturnCause() {
		return returnCause;
	}

	/**
	 * @param returnCause the returnCause to set
	 */
	public void setReturnCause(String returnCause) {
		this.returnCause = returnCause;
	}

	/**
	 * @return the approvalDate
	 */
	public long getApprovalDate() {
		return approvalDate;
	}

	/**
	 * @param approvalDate the approvalDate to set
	 */
	public void setApprovalDate(long approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**
	 * @return the deptId
	 */
	public GUID getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(GUID deptId) {
		this.deptId = deptId;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
