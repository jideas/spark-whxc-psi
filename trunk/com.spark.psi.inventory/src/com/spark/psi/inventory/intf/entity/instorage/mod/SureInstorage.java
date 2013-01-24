

package com.spark.psi.inventory.intf.entity.instorage.mod;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>确认入库</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */

public class SureInstorage{

	private GUID RECID;// 标识
	private GUID tenantsGuid;//租户编号	guid
	private String operType;//操作类型	char
	private GUID instoGuid;//入库单recid	guid
	private GUID goodsGuid;//商品recid	guid
	private String goodsName;//商品名称	nvarchar
	private String goodsNamePY;//拼音	nvarchar
	private String goodsAttr;//商品属性	nvarchar
	private int goodsScale;
	private GUID sureInsto;//确认入库人	guid
	private String sureInstoName;//确认入库人姓名	nvarchar
	private long instoDate;//入库时间	date
	private double instoCount;//本次入库数量	numeric
	private double planAmount;//应付款金额	numeric
	private double payAmount;//;//已付金额	numeric
	private boolean isFinished;//是否已完成付款	boolean
	private GUID cusproGuid;
	private String cusproName;
	private String cusproShortName;
	private String cusproNamePY;
	private GUID checkInLogId;//确认入库记录ID


	/**
     * @return the goodsScale
     */
    public int getGoodsScale(){
    	return goodsScale;
    }

	/**
     * @param goodsScale the goodsScale to set
     */
    public void setGoodsScale(int goodsScale){
    	this.goodsScale = goodsScale;
    }

	/**
	 * 本次抵减金额
	 */
	private double thisTimeAmount;

	/**
	 * @return the thisTimeAmount
	 */
	public double getThisTimeAmount(){
		return thisTimeAmount;
	}

	/**
	 * @param thisTimeAmount the thisTimeAmount to set
	 */
	public void setThisTimeAmount(double thisTimeAmount){
		this.thisTimeAmount = thisTimeAmount;
	}

	/**
	 * @return the rECID
	 */
	public GUID getRECID(){
		return RECID;
	}

	/**
	 * @param rECID the rECID to set
	 */
	public void setRECID(GUID rECID){
		RECID = rECID;
	}

	/**
	 * @return the tenantsGuid
	 */
	public GUID getTenantsGuid(){
		return tenantsGuid;
	}

	/**
	 * @param tenantsGuid the tenantsGuid to set
	 */
	public void setTenantsGuid(GUID tenantsGuid){
		this.tenantsGuid = tenantsGuid;
	}

	/**
	 * @return the operType
	 */
	public String getOperType(){
		return operType;
	}

	/**
	 * @param operType the operType to set
	 */
	public void setOperType(String operType){
		this.operType = operType;
	}

	/**
	 * @return the instoGuid
	 */
	public GUID getInstoGuid(){
		return instoGuid;
	}

	/**
	 * @param instoGuid the instoGuid to set
	 */
	public void setInstoGuid(GUID instoGuid){
		this.instoGuid = instoGuid;
	}

	/**
	 * @return the goodsGuid
	 */
	public GUID getGoodsGuid(){
		return goodsGuid;
	}

	/**
	 * @param goodsGuid the goodsGuid to set
	 */
	public void setGoodsGuid(GUID goodsGuid){
		this.goodsGuid = goodsGuid;
	}

	/**
	 * @return the goodsName
	 */
	public String getGoodsName(){
		return goodsName;
	}

	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName){
		this.goodsName = goodsName;
	}

	/**
	 * @return the goodsNamePY
	 */
	public String getGoodsNamePY(){
		return goodsNamePY;
	}

	/**
	 * @param goodsNamePY the goodsNamePY to set
	 */
	public void setGoodsNamePY(String goodsNamePY){
		this.goodsNamePY = goodsNamePY;
	}

	/**
	 * @return the goodsAttr
	 */
	public String getGoodsAttr(){
		return goodsAttr;
	}

	/**
	 * @param goodsAttr the goodsAttr to set
	 */
	public void setGoodsAttr(String goodsAttr){
		this.goodsAttr = goodsAttr;
	}

	/**
	 * @return the sureInsto
	 */
	public GUID getSureInsto(){
		return sureInsto;
	}

	/**
	 * @param sureInsto the sureInsto to set
	 */
	public void setSureInsto(GUID sureInsto){
		this.sureInsto = sureInsto;
	}

	/**
	 * @return the sureInstoName
	 */
	public String getSureInstoName(){
		return sureInstoName;
	}

	/**
	 * @param sureInstoName the sureInstoName to set
	 */
	public void setSureInstoName(String sureInstoName){
		this.sureInstoName = sureInstoName;
	}

	/**
	 * @return the instoDate
	 */
	public long getInstoDate(){
		return instoDate;
	}

	/**
	 * @param instoDate the instoDate to set
	 */
	public void setInstoDate(long instoDate){
		this.instoDate = instoDate;
	}

	/**
	 * @return the instoCount
	 */
	public double getInstoCount(){
		return instoCount;
	}

	/**
	 * @param instoCount the instoCount to set
	 */
	public void setInstoCount(double instoCount){
		this.instoCount = instoCount;
	}

	/**
	 * @return the planAmount
	 */
	public double getPlanAmount(){
		return planAmount;
	}

	/**
	 * @param planAmount the planAmount to set
	 */
	public void setPlanAmount(double planAmount){
		this.planAmount = planAmount;
	}

	/**
	 * @return the payAmount
	 */
	public double getPayAmount(){
		return payAmount;
	}

	/**
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(double payAmount){
		this.payAmount = payAmount;
	}

	/**
	 * @return the isFinished
	 */
	public boolean isFinished(){
		return isFinished;
	}

	/**
	 * @param isFinished the isFinished to set
	 */
	public void setFinished(boolean isFinished){
		this.isFinished = isFinished;
	}

	/**
     * @return the cusproGuid
     */
    public GUID getCusproGuid(){
    	return cusproGuid;
    }

	/**
     * @param cusproGuid the cusproGuid to set
     */
    public void setCusproGuid(GUID cusproGuid){
    	this.cusproGuid = cusproGuid;
    }

	/**
     * @return the cusproName
     */
    public String getCusproName(){
    	return cusproName;
    }

	/**
     * @param cusproName the cusproName to set
     */
    public void setCusproName(String cusproName){
    	this.cusproName = cusproName;
    }

	/**
     * @return the cusproShortName
     */
    public String getCusproShortName(){
    	return cusproShortName;
    }

	/**
     * @param cusproShortName the cusproShortName to set
     */
    public void setCusproShortName(String cusproShortName){
    	this.cusproShortName = cusproShortName;
    }

	/**
     * @return the cusproNamePY
     */
    public String getCusproNamePY(){
    	return cusproNamePY;
    }

	/**
     * @param cusproNamePY the cusproNamePY to set
     */
    public void setCusproNamePY(String cusproNamePY){
    	this.cusproNamePY = cusproNamePY;
    }

	public void setCheckInLogId(GUID checkInLogId) {
		this.checkInLogId = checkInLogId;
	}

	public GUID getCheckInLogId() {
		return checkInLogId;
	}

}
