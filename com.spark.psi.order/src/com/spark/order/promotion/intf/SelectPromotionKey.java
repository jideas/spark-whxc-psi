package com.spark.order.promotion.intf;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.key.SelectKey;
import com.spark.psi.publish.QueryScope.ScopeType;

/**
 * <p>��������ѯkey</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
public class SelectPromotionKey extends SelectKey{
	private PromotionStatus2[] statuss;
	//��ѯ����
	private ScopeEnum scopeEnum;//��ǰ��ѯ����Ϊnullʱ�����ж�
	private GUID selectDeptId;//ѡ�����ǵĲ���Id;
	//�����ֶ�
	private String sortField = "t.goodsName";//�����ֶ�
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
