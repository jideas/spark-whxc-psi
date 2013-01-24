/**
 * 
 */
package com.spark.psi.inventory.intf.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 *
 */
public class CheckingOut {
	private GUID RECID;//标识	GUID
	private GUID tenantsGuid;//	租户编号	GUID
	private String outstoNo;//出库单号	V(20)
	private String outstoType;//;//出库类型	C（2）
	private GUID cusproGuid;//供应商/客户recid	GUID
	private String cusproName;//供应商/客户名称	V(20)
	private String cusproShortName;
	private String cusproNamePY;

	private GUID relaOrderGuid;//相关单据recid	GUID
	private String relaOrderNo;//相关单据编号	V(20)
	private GUID storeGuid;//仓库recid	GUID
	private String storeName;//仓库名称	V(100)
	private String storeNamePY;
	private String outstoState;//处理状态	C(2)
	private String goodsUse;//;//商品用途	V(200)
	private String goodsFrom;//商品来源	V(200)
	private String remark;//备注	text 
	private long planOutstoDate;//预计出库日期	D
	private long allOutstoDate;//全部出库日期	D
	private String sureOutsto;//确认出库	V(200)
	private String takePerson;//提货人	V(20)
	private String takeUnit;//提货单位	V(200)
	private String vouchersNo;//凭证号	V(100)
	private String stopReaseon;//驳回原因	V(100)
	private boolean isStoped;
	private GUID deptGuid;//部门recid	GUID 
	private GUID createPerson;//制单人recid	GUID
	private String createPersonName;//制单人名称	V(20)
	private long createDate;//制单时间	D

	private String defOne;//用户自定义字段1	V(100)
	private String defTwo;//用户自定义字段2	V(100)
	private String defThree;//用户自定义字段3	V(100)
	private double defFour;//用户自定义字段4	NUM(17,2)
	private double defFive;//用户自定义字段5	NUM(17,2)
	private double defSix;//用户自定义字段6	NUM(17,2)
	private long defSeven;//用户自定义字段7	D
	private long defEight;//用户自定义字段8	D
	private String defNine;//用户自定义字段9	C(2)
	private String defTen;//用户自定义字段10	C(2)
	
	private double orderGoodsTotalCount;
	private double orderTotalAmount;

	public double getOrderGoodsTotalCount() {
		return orderGoodsTotalCount;
	}

	public void setOrderGoodsTotalCount(double orderGoodsTotalCount) {
		this.orderGoodsTotalCount = orderGoodsTotalCount;
	}

	public double getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
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
	 * @return the outstoNo
	 */
	public String getOutstoNo(){
		return outstoNo;
	}

	/**
	 * @param outstoNo the outstoNo to set
	 */
	public void setOutstoNo(String outstoNo){
		this.outstoNo = outstoNo;
	}

	/**
	 * @return the outstoType
	 */
	public String getOutstoType(){
		return outstoType;
	}

	/**
	 * @param outstoType the outstoType to set
	 */
	public void setOutstoType(String outstoType){
		this.outstoType = outstoType;
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
	 * @return the relaOrderGuid
	 */
	public GUID getRelaOrderGuid(){
		return relaOrderGuid;
	}

	/**
	 * @param relaOrderGuid the relaOrderGuid to set
	 */
	public void setRelaOrderGuid(GUID relaOrderGuid){
		this.relaOrderGuid = relaOrderGuid;
	}

	/**
	 * @return the relaOrderNo
	 */
	public String getRelaOrderNo(){
		return relaOrderNo;
	}

	/**
	 * @param relaOrderNo the relaOrderNo to set
	 */
	public void setRelaOrderNo(String relaOrderNo){
		this.relaOrderNo = relaOrderNo;
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
	 * @return the storeName
	 */
	public String getStoreName(){
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	/**
	 * @return the outstoState
	 */
	public String getOutstoState(){
		return outstoState;
	}

	/**
	 * @param outstoState the outstoState to set
	 */
	public void setOutstoState(String outstoState){
		this.outstoState = outstoState;
	}

	/**
	 * @return the goodsUse
	 */
	public String getGoodsUse(){
		return goodsUse;
	}

	/**
	 * @param goodsUse the goodsUse to set
	 */
	public void setGoodsUse(String goodsUse){
		this.goodsUse = goodsUse;
	}

	/**
	 * @return the goodsFrom
	 */
	public String getGoodsFrom(){
		return goodsFrom;
	}

	/**
	 * @param goodsFrom the goodsFrom to set
	 */
	public void setGoodsFrom(String goodsFrom){
		this.goodsFrom = goodsFrom;
	}

	/**
	 * @return the remark
	 */
	public String getRemark(){
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

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

	/**
	 * @return the allOutstoDate
	 */
	public long getAllOutstoDate(){
		return allOutstoDate;
	}

	/**
	 * @param allOutstoDate the allOutstoDate to set
	 */
	public void setAllOutstoDate(long allOutstoDate){
		this.allOutstoDate = allOutstoDate;
	}

	/**
	 * @return the sureOutsto
	 */
	public String getSureOutsto(){
		return sureOutsto;
	}

	/**
	 * @param sureOutsto the sureOutsto to set
	 */
	public void setSureOutsto(String sureOutsto){
		this.sureOutsto = sureOutsto;
	}

	/**
	 * @return the takePerson
	 */
	public String getTakePerson(){
		return takePerson;
	}

	/**
	 * @param takePerson the takePerson to set
	 */
	public void setTakePerson(String takePerson){
		this.takePerson = takePerson;
	}

	/**
	 * @return the takeUnit
	 */
	public String getTakeUnit(){
		return takeUnit;
	}

	/**
	 * @param takeUnit the takeUnit to set
	 */
	public void setTakeUnit(String takeUnit){
		this.takeUnit = takeUnit;
	}

	/**
	 * @return the vouchersNo
	 */
	public String getVouchersNo(){
		return vouchersNo;
	}

	/**
	 * @param vouchersNo the vouchersNo to set
	 */
	public void setVouchersNo(String vouchersNo){
		this.vouchersNo = vouchersNo;
	}
 
	/**
     * @return the deptGuid
     */
    public GUID getDeptGuid(){
    	return deptGuid;
    }

	/**
     * @param deptGuid the deptGuid to set
     */
    public void setDeptGuid(GUID deptGuid){
    	this.deptGuid = deptGuid;
    }

	/**
	 * @return the createPerson
	 */
	public GUID getCreatePerson(){
		return createPerson;
	}

	/**
	 * @param createPerson the createPerson to set
	 */
	public void setCreatePerson(GUID createPerson){
		this.createPerson = createPerson;
	}

	/**
	 * @return the createPersonName
	 */
	public String getCreatePersonName(){
		return createPersonName;
	}

	/**
	 * @param createPersonName the createPersonName to set
	 */
	public void setCreatePersonName(String createPersonName){
		this.createPersonName = createPersonName;
	}

	/**
	 * @return the createDate
	 */
	public long getCreateDate(){
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(long createDate){
		this.createDate = createDate;
	}

	/**
	 * @return the defOne
	 */
	public String getDefOne(){
		return defOne;
	}

	/**
	 * @param defOne the defOne to set
	 */
	public void setDefOne(String defOne){
		this.defOne = defOne;
	}

	/**
	 * @return the defTwo
	 */
	public String getDefTwo(){
		return defTwo;
	}

	/**
	 * @param defTwo the defTwo to set
	 */
	public void setDefTwo(String defTwo){
		this.defTwo = defTwo;
	}

	/**
	 * @return the defThree
	 */
	public String getDefThree(){
		return defThree;
	}

	/**
	 * @param defThree the defThree to set
	 */
	public void setDefThree(String defThree){
		this.defThree = defThree;
	}

	/**
	 * @return the defFour
	 */
	public double getDefFour(){
		return defFour;
	}

	/**
	 * @param defFour the defFour to set
	 */
	public void setDefFour(double defFour){
		this.defFour = defFour;
	}

	/**
	 * @return the defFive
	 */
	public double getDefFive(){
		return defFive;
	}

	/**
	 * @param defFive the defFive to set
	 */
	public void setDefFive(double defFive){
		this.defFive = defFive;
	}

	/**
	 * @return the defSix
	 */
	public double getDefSix(){
		return defSix;
	}

	/**
	 * @param defSix the defSix to set
	 */
	public void setDefSix(double defSix){
		this.defSix = defSix;
	}

	/**
	 * @return the defSeven
	 */
	public long getDefSeven(){
		return defSeven;
	}

	/**
	 * @param defSeven the defSeven to set
	 */
	public void setDefSeven(long defSeven){
		this.defSeven = defSeven;
	}

	/**
	 * @return the defEight
	 */
	public long getDefEight(){
		return defEight;
	}

	/**
	 * @param defEight the defEight to set
	 */
	public void setDefEight(long defEight){
		this.defEight = defEight;
	}

	/**
	 * @return the defNine
	 */
	public String getDefNine(){
		return defNine;
	}

	/**
	 * @param defNine the defNine to set
	 */
	public void setDefNine(String defNine){
		this.defNine = defNine;
	}

	/**
	 * @return the defTen
	 */
	public String getDefTen(){
		return defTen;
	}

	/**
	 * @param defTen the defTen to set
	 */
	public void setDefTen(String defTen){
		this.defTen = defTen;
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

	/**
	 * @return the storeNamePY
	 */
	public String getStoreNamePY(){
		return storeNamePY;
	}

	/**
	 * @param storeNamePY the storeNamePY to set
	 */
	public void setStoreNamePY(String storeNamePY){
		this.storeNamePY = storeNamePY;
	}

	/**
	 * @return the stopReaseon
	 */
	public String getStopReaseon(){
		return stopReaseon;
	}

	/**
	 * @param stopReaseon the stopReaseon to set
	 */
	public void setStopReaseon(String stopReaseon){
		this.stopReaseon = stopReaseon;
	}

	/**
	 * @return the isStoped
	 */
	public boolean isStoped(){
		return isStoped;
	}

	/**
	 * @param isStoped the isStoped to set
	 */
	public void setStoped(boolean isStoped){
		this.isStoped = isStoped;
	}
}
