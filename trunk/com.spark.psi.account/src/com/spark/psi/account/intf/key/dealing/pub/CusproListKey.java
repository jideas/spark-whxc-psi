package com.spark.psi.account.intf.key.dealing.pub;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.account.intf.constant.dealing.CusdealConstant;

/**
 * <p>��ȡ�����б�</p>
 * <p>�ṩ���ո���Ľӿ�</p>
 * service:com.spark.bus.finance.cusdeal.service.FinanceCusdealDao.QueryCusproListDaoProvider
 * 


 *
 * @author yanglin
 * @version 2011-11-16
 */

public class CusproListKey{
	/**
	 * �տ�/����
	 */
	private String cusproType;
	/**
	 * ����GUID
	 */
	private GUID deptGuid;
	/**
	 * SAAS.CUSPRO_DATA_TYPE����,������Աش�
	 * ALL = "01";	//ȡ���е�����
	 * SALE = "02";	//���۲鿴�ͻ����
	 * BUY = "03";	//�ɹ��鿴��Ӧ�����
	 */
	private CusdealConstant.DATA_TYPE dataType;
	/**
	 * �����ֶ�
	 */
	private CusdealConstant.ORDER_COLUMNS orderColumn;
	/**
	 * ��������
	 */
	private CusdealConstant.ORDER_TYPE orderType;
	/**
	 * ҵ�����ˣ�����Ա����user ��GUID,������ɫ����
	 */
	private GUID busPerson;
	
	/**
	 * ��ѯƫ�ƣ���0��ʼ��
	 */
	private int offset;

	/**
	 * ��ѯ����
	 */
	private int count;

	/**
	 * �Ƿ��ѯ����
	 */
	private boolean queryTotal;

	/**
	 * ����ʽ
	 */
	private String sortType;

	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * @param cusproType
	 */
	public CusproListKey(String cusproType){
		this.cusproType = cusproType;
	}

	/**
	 * @param cusproType
	 * @param dataType
	 */
	public CusproListKey(String cusproType, CusdealConstant.DATA_TYPE dataType)
	{
		this.cusproType = cusproType;
		this.dataType = dataType;
	}

	/**
	 * @param cusproType
	 * @param deptGuid
	 * @param dataType
	 */
	public CusproListKey(String cusproType, GUID deptGuid,
	        CusdealConstant.DATA_TYPE dataType)
	{
		this.cusproType = cusproType;
		this.deptGuid = deptGuid;
		this.dataType = dataType;
	}

	public CusdealConstant.ORDER_COLUMNS getOrderColumn(){
		return orderColumn;
	}

	public void setOrderColumn(CusdealConstant.ORDER_COLUMNS orderColumn){
		this.orderColumn = orderColumn;
	}

	public CusdealConstant.ORDER_TYPE getOrderType(){
		return orderType;
	}

	public void setOrderType(CusdealConstant.ORDER_TYPE orderType){
		this.orderType = orderType;
	}

	public GUID getBusinessPerson(){
		return busPerson;
	}

	public void setBusPerson(GUID busPerson){
		this.busPerson = busPerson;
	}

	public GUID getDeptGuid(){
		return deptGuid;
	}

	public void setDeptGuid(GUID deptGuid){
		this.deptGuid = deptGuid;
	}

	public CusdealConstant.DATA_TYPE getDataType(){
		return dataType;
	}

	public void setDataType(CusdealConstant.DATA_TYPE dataType){
		this.dataType = dataType;
	}

	public String getCusproType(){
		return cusproType;
	}

	public void setCusproType(String cusproType){
		this.cusproType = cusproType;
	}
}
