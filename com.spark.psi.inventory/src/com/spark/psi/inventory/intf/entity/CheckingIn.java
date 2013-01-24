/**
 * 
 */
package com.spark.psi.inventory.intf.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 *
 */
public class CheckingIn {
	private GUID RECID;//��ʶ
	private GUID tenantsGuid;// �⻧���
	private String instoNo;//��ⵥ��
	private String instoType;//	�������
	private GUID cusproGuid;//��Ӧ��/�ͻ�recid  111
	private String cusproName;//��Ӧ��/�ͻ����� 1111
	private String cusproShortName; //111 

	private GUID relaOrderGuid;//	��ص���recid//111
	private String relaOrderNo;//	��ص��ݱ��//111
	private GUID storeGuid;//�ֿ�recid//111
	private String storeName;//�ֿ�����//111//111
	private String storeNamePY;//�ֿ�����//111
	private String goodsFrom;//��Ʒ��Դ
	private String remark;//��ע//111
	private String buyPerson;//�ɹ���
	private long buyDate;//�ɹ�����
	private double instoAmount;//�����  //111
	private long planInstoDate;//	Ԥ���������//111
	private long allInstoDate;//	ȫ���������
	private String instoState;//	����״̬
	private String sureInsto;//	ȷ�����
	private String stopReaseon;//	����ԭ��
	private boolean isStoped = false;
	private GUID deptGuid;//����recid 
	private GUID createPerson;//�Ƶ���recid
	private String createPersonName;//	�Ƶ�������
	private long createDate;//	�Ƶ�ʱ��
	private String defOne; //	�û��Զ����ֶ�1	
	private String defTwo; //	�û��Զ����ֶ�2	
	private String defThree; //	�û��Զ����ֶ�3	
	private double defFour; //	�û��Զ����ֶ�4	
	private double defFive; //	�û��Զ����ֶ�5	
	private double defSix; //	�û��Զ����ֶ�6	
	private long defSeven; //	�û��Զ����ֶ�7	
	private long defEight; //	�û��Զ����ֶ�8	
	private String defNine; //	�û��Զ����ֶ�9	
	private String defTen; //	�û��Զ����ֶ�10	
	
	private double orderGoodsTotalCount;
	private double orderTotalAmount;

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

	public GUID getRECID(){
		return RECID;
	}

	public void setRECID(GUID rECID){
		RECID = rECID;
	}

	public String getInstoNo(){
		return instoNo;
	}

	public void setInstoNo(String instoNo){
		this.instoNo = instoNo;
	}

	public String getInstoType(){
		return instoType;
	}

	public void setInstoType(String instoType){
		this.instoType = instoType;
	}

	public GUID getCusproGuid(){
		return cusproGuid;
	}

	public void setCusproGuid(GUID cusproGuid){
		this.cusproGuid = cusproGuid;
	}

	public String getCusproName(){
		return cusproName;
	}

	public void setCusproName(String cusproName){
		this.cusproName = cusproName;
	}

	public GUID getRelaOrderGuid(){
		return relaOrderGuid;
	}

	public void setRelaOrderGuid(GUID relaOrderGuid){
		this.relaOrderGuid = relaOrderGuid;
	}

	public String getRelaOrderNo(){
		return relaOrderNo;
	}

	public void setRelaOrderNo(String relaOrderNo){
		this.relaOrderNo = relaOrderNo;
	}

	public GUID getStoreGuid(){
		return storeGuid;
	}

	public void setStoreGuid(GUID storeGuid){
		this.storeGuid = storeGuid;
	}

	public String getStoreName(){
		return storeName;
	}

	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	public String getGoodsFrom(){
		return goodsFrom;
	}

	public void setGoodsFrom(String goodsFrom){
		this.goodsFrom = goodsFrom;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getBuyPerson(){
		return buyPerson;
	}

	public void setBuyPerson(String buyPerson){
		this.buyPerson = buyPerson;
	}

	public long getBuyDate(){
		return buyDate;
	}

	public void setBuyDate(long buyDate){
		this.buyDate = buyDate;
	}

	public double getInstoAmount(){
		return instoAmount;
	}

	public void setInstoAmount(double instoAmount){
		this.instoAmount = instoAmount;
	}

	public long getPlanInstoDate(){
		return planInstoDate;
	}

	public void setPlanInstoDate(long planInstoDate){
		this.planInstoDate = planInstoDate;
	}

	public long getAllInstoDate(){
		return allInstoDate;
	}

	public void setAllInstoDate(long allInstoDate){
		this.allInstoDate = allInstoDate;
	}

	public String getInstoState(){
		return instoState;
	}

	public void setInstoState(String instoState){
		this.instoState = instoState;
	}

	public String getSureInsto(){
		return sureInsto;
	}

	public void setSureInsto(String sureInsto){
		this.sureInsto = sureInsto;
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

	public GUID getCreatePerson(){
		return createPerson;
	}

	public void setCreatePerson(GUID createPerson){
		this.createPerson = createPerson;
	}

	public String getCreatePersonName(){
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName){
		this.createPersonName = createPersonName;
	}

	public long getCreateDate(){
		return createDate;
	}

	public void setCreateDate(long createDate){
		this.createDate = createDate;
	}

	public String getDefOne(){
		return defOne;
	}

	public void setDefOne(String defOne){
		this.defOne = defOne;
	}

	public String getDefTwo(){
		return defTwo;
	}

	public void setDefTwo(String defTwo){
		this.defTwo = defTwo;
	}

	public String getDefThree(){
		return defThree;
	}

	public void setDefThree(String defThree){
		this.defThree = defThree;
	}

	public double getDefFour(){
		return defFour;
	}

	public void setDefFour(double defFour){
		this.defFour = defFour;
	}

	public double getDefFive(){
		return defFive;
	}

	public void setDefFive(double defFive){
		this.defFive = defFive;
	}

	public double getDefSix(){
		return defSix;
	}

	public void setDefSix(double defSix){
		this.defSix = defSix;
	}

	public long getDefSeven(){
		return defSeven;
	}

	public void setDefSeven(long defSeven){
		this.defSeven = defSeven;
	}

	public long getDefEight(){
		return defEight;
	}

	public void setDefEight(long defEight){
		this.defEight = defEight;
	}

	public String getDefNine(){
		return defNine;
	}

	public void setDefNine(String defNine){
		this.defNine = defNine;
	}

	public String getDefTen(){
		return defTen;
	}

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

	public void setOrderGoodsTotalCount(double orderGoodsTotalCount) {
		this.orderGoodsTotalCount = orderGoodsTotalCount;
	}

	public double getOrderGoodsTotalCount() {
		return orderGoodsTotalCount;
	}

	public void setOrderTotalAmount(double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public double getOrderTotalAmount() {
		return orderTotalAmount;
	} 
}
