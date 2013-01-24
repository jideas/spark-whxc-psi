package com.spark.psi.account.intf.key.dealing.pub;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.account.intf.constant.dealing.CusdealConstant;

/**
 * <p>获取往来列表</p>
 * <p>提供给收付款的接口</p>
 * service:com.spark.bus.finance.cusdeal.service.FinanceCusdealDao.QueryCusproListDaoProvider
 * 


 *
 * @author yanglin
 * @version 2011-11-16
 */

public class CusproListKey{
	/**
	 * 收款/付款
	 */
	private String cusproType;
	/**
	 * 部门GUID
	 */
	private GUID deptGuid;
	/**
	 * SAAS.CUSPRO_DATA_TYPE类里,这个属性必传
	 * ALL = "01";	//取所有的数据
	 * SALE = "02";	//销售查看客户情况
	 * BUY = "03";	//采购查看供应商情况
	 */
	private CusdealConstant.DATA_TYPE dataType;
	/**
	 * 排序字段
	 */
	private CusdealConstant.ORDER_COLUMNS orderColumn;
	/**
	 * 排序类型
	 */
	private CusdealConstant.ORDER_TYPE orderType;
	/**
	 * 业务负责人，销售员工传user 的GUID,其它角色不传
	 */
	private GUID busPerson;
	
	/**
	 * 查询偏移（从0开始）
	 */
	private int offset;

	/**
	 * 查询数量
	 */
	private int count;

	/**
	 * 是否查询总数
	 */
	private boolean queryTotal;

	/**
	 * 排序方式
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
