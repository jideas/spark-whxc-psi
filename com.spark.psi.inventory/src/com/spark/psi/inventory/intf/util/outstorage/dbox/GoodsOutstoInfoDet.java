/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.outstorage.util
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-17       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.outstorage.dbox;

/**
 * <p>TODO 类描述</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-17
 */

public class GoodsOutstoInfoDet{

	/**
	 * 
	 */
	public GoodsOutstoInfoDet(double goodsPrice,long plandate,String storeName,

	double planCount,

	double realCount,

	String instoNo)
	{
		this.storeName = storeName;
		this.planCount = planCount;
		this.realCount = realCount;
		this.instoNo = instoNo;
		this.planOutstoDate = plandate;
		this.goodsPrice = goodsPrice;
	}
	
	private long planOutstoDate;
	
	private double goodsPrice;

	/**
     * @return the planOutstoDate
     */
    public long getPlanOutstoDate(){
    	return planOutstoDate;
    }

	/**
     * @param planOutstoDate the planOutstoDate to set
     */
    public void setPlanOutstoDate(long planOutstoDate){
    	this.planOutstoDate = planOutstoDate;
    }

	private String storeName;

	private double planCount;

	private double realCount;

	private String instoNo;

	/**
	 * @return the storeName
	 */
	public String getStoreName(){
		return storeName;
	}

	/**
     * @return the goodsPrice
     */
    public double getGoodsPrice(){
    	return goodsPrice;
    }

	/**
     * @param goodsPrice the goodsPrice to set
     */
    public void setGoodsPrice(double goodsPrice){
    	this.goodsPrice = goodsPrice;
    }

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	/**
	 * @return the planCount
	 */
	public double getPlanCount(){
		return planCount;
	}

	/**
	 * @param planCount the planCount to set
	 */
	public void setPlanCount(double planCount){
		this.planCount = planCount;
	}

	/**
	 * @return the realCount
	 */
	public double getRealCount(){
		return realCount;
	}

	/**
	 * @param realCount the realCount to set
	 */
	public void setRealCount(double realCount){
		this.realCount = realCount;
	}

	/**
	 * @return the instoNo
	 */
	public String getInstoNo(){
		return instoNo;
	}

	/**
	 * @param instoNo the instoNo to set
	 */
	public void setInstoNo(String instoNo){
		this.instoNo = instoNo;
	}
}
