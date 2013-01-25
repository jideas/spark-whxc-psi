/**
 * 
 */
package com.spark.psi.report.dao.task;

import com.jiuqi.dna.core.type.GUID;

/**
 *
 */
public class ReportInventoryTask extends BaseTask{
	
	public ReportInventoryTask(String inventoryType ){
		this.inventoryType = inventoryType;
	}
	
	private String inventoryType ;  

	private GUID storeGuid;//	仓库GUID
	private GUID goodsGuid;//	商品GUID 
//	private double beginStoreCount;//	期初库存数量
//	private double beginStoreMoney;//	期初库存金额
	private double instoCount;//	入库数量
	private double instoAmount;//	入库金额
	private double outstoCount;//出库数量
	private double outstoAmount;//出库金额
//	private double endStoreCount;//	期末库存数量
//	private double endStoreMoney;//期末库存金额
	private long createdDate;//创建日期
	private String goodsName;//商品名称
	private String goodsAttr;//商品属性
	private String goodsUnit;//商品单位
	private GUID goodsTypeGuid;//商品分类GUID
	private String goodsNo;//商品编号
	private int goodsScale;//商品精度

	public String getInventoryType() {
		return inventoryType;
	} 

	/**
	 * @return the storeGuid
	 */
	public GUID getStoreGuid(){
		return storeGuid;
	}

	/**
	 * @param storeGuid the storeGuid to set
	 */
	public void setStoreGuid(GUID storeGuid){
		this.storeGuid = storeGuid;
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
	 * @return the instoAmount
	 */
	public double getInstoAmount(){
		return instoAmount;
	}

	/**
	 * @param instoAmount the instoAmount to set
	 */
	public void setInstoAmount(double instoAmount){
		this.instoAmount = instoAmount;
	}

	/**
	 * @return the outstoCount
	 */
	public double getOutstoCount(){
		return outstoCount;
	}

	/**
	 * @param outstoCount the outstoCount to set
	 */
	public void setOutstoCount(double outstoCount){
		this.outstoCount = outstoCount;
	}

	/**
	 * @return the outstoAmount
	 */
	public double getOutstoAmount(){
		return outstoAmount;
	}

	/**
	 * @param outstoAmount the outstoAmount to set
	 */
	public void setOutstoAmount(double outstoAmount){
		this.outstoAmount = outstoAmount;
	} 

	/**
	 * @return the createdDate
	 */
	public long getCreatedDate(){
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(long createdDate){
		this.createdDate = createdDate;
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
	 * @return the goodsUnit
	 */
	public String getGoodsUnit(){
		return goodsUnit;
	}

	/**
	 * @param goodsUnit the goodsUnit to set
	 */
	public void setGoodsUnit(String goodsUnit){
		this.goodsUnit = goodsUnit;
	}

	/**
	 * @return the goodsTypeGuid
	 */
	public GUID getGoodsTypeGuid(){
		return goodsTypeGuid;
	}

	/**
	 * @param goodsTypeGuid the goodsTypeGuid to set
	 */
	public void setGoodsTypeGuid(GUID goodsTypeGuid){
		this.goodsTypeGuid = goodsTypeGuid;
	}

	/**
	 * @return the goodsNo
	 */
	public String getGoodsNo(){
		return goodsNo;
	}

	/**
	 * @param goodsNo the goodsNo to set
	 */
	public void setGoodsNo(String goodsNo){
		this.goodsNo = goodsNo;
	}

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

}
