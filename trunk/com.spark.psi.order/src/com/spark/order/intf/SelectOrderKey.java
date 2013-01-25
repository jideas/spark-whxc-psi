package com.spark.order.intf;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.type.StatusEnum;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.QueryScope.ScopeType;
import com.spark.psi.publish.order.key.GetSalesOrderListKey;

/**
 * <p>��ѯ����Key</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-6
 */
public class SelectOrderKey extends SelectKey{
	private StatusEnum[] statuss;//��ѯ״̬
	private boolean isHaveStop = false;//�Ƿ�������ֹ���ݡ�Ĭ��û��
	private OrderEnum[] orders;//��ѯ�ĵ���
	//��ѯ����
	private ScopeEnum scopeEnum;//��ǰ��ѯ����Ϊnullʱ�����ж�
	private GUID selectDeptId;//ѡ�����ǵĲ���Id;
	//�����ֶ�
	private String sortField = "t.createDate";//�����ֶ�
	
	/**
	 * ��ʱֻ�������۸���ҳǩ
	 */
	public SelectOrderKey(GetSalesOrderListKey key) {
		super();
		setLimitKey(key);
		statuss = new StatusEnum[]{StatusEnum.Approve, StatusEnum.Store_All, StatusEnum.Store_N0, StatusEnum.Store_Part};
		orders = new OrderEnum[]{OrderEnum.Sales, OrderEnum.Sales_Return};
		if(null != key.getQueryScope()){
			setScopeEnum(key.getQueryScope().getType());
			selectDeptId = key.getQueryScope().getDepartmentId();
		}
		if(null != key.getSortField()){
			sortField = key.getSortField().getFieldName();
		}
		else{
			sortField = "t.createDate";
		}
	}
	/**
	 * 
	 */
	public SelectOrderKey() {
		super();
	}
	public StatusEnum[] getStatus() {
		return statuss;
	}
	public void setStatuss(OrderStatus[] pubstatuss) throws Throwable {
		if(OrderStatus.Stop.isIn(pubstatuss)){
			isHaveStop = true;
		}
		this.statuss = StatusEnum.getPubToMe(pubstatuss);
	}
	public boolean isHaveStop() {
		return isHaveStop;
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
	public OrderEnum[] getOrders() {
		return orders;
	}
	public void setOrders(OrderEnum... orders) {
		this.orders = orders;
	}
}
