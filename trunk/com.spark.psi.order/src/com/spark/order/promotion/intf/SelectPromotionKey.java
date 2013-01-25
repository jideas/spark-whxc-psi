package com.spark.order.promotion.intf;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.key.SelectKey;
import com.spark.psi.publish.QueryScope.ScopeType;

/**
 * <p>促销单查询key</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
public class SelectPromotionKey extends SelectKey{
	private PromotionStatus2[] statuss;
	//查询对象
	private ScopeEnum scopeEnum;//当前查询对象。为null时自我判断
	private GUID selectDeptId;//选择部门是的部门Id;
	//排序字段
	private String sortField = "t.goodsName";//排序字段
	public PromotionStatus2[] getStatus() {
		return statuss;
	}
	public void setStatuss(PromotionStatus2[] statuss) {
		this.statuss = statuss;
	}
	public ScopeEnum getScopeEnum() {
		return scopeEnum;
	}
	public void setScopeEnum(ScopeEnum scopeEnum) {
		this.scopeEnum = scopeEnum;
	}
	public void setScopeEnum(ScopeType scopeType) {
		this.scopeEnum = ScopeType.Mine == scopeType?ScopeEnum.Main:ScopeEnum.Dept;
	}
	public GUID getSelectDeptId() {
		return selectDeptId;
	}
	public void setSelectDeptId(GUID selectDeptId) {
		this.selectDeptId = selectDeptId;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
	
}
