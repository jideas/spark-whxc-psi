package com.spark.order.promotion.intf;

import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.order.intf.entity.EntityFather;
import com.spark.psi.publish.PromotionStatus;

/**
 * <p>��Ʒ����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public class Promotion2 extends EntityFather{
	protected GUID goodsItemId;// ��ƷGUID guid
	protected String goodsName;// ��Ʒ���� varchar 50;
	protected String goodsNamePY;// ����ƴ�� varchar 50;
	protected String goodsProperties;//��Ʒ���� varchar 1000;
	protected int countDecimal;//��ƷС��λ�� int
	protected double promotionCount;// �������� numeric 17 5
	protected double saledCount;// ���������� numeric 17 5
	protected double price_goods;// ԭ�� numeric 17 2
	protected double price_promotion;//�����۸� numeric 17 2
	protected long beginDate;// ��ʼ���� date
	protected long endDate;// �������� date
	protected String status;// ״̬ char 2
	protected String promotionCause;// ����ԭ�� varchar 500
	protected String returnCause;// �˻�/����ֹԭ�� varchar 500
	protected long approvalDate;// ���ʱ�� date
	
	protected GUID deptId;//�����˲��� 

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
