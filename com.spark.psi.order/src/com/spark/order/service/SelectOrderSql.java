package com.spark.order.service;

import java.util.Arrays;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.OrderConstant2;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.SelectOrderKey;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.key.SelectKey.ScopeEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.service.util.OrderUtil;
import com.spark.order.util.dnaSql.DnaSql_query;
import com.spark.psi.base.Department;

/**
 * <p>��ѯ����SQL</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-6
 */
public class SelectOrderSql extends DnaSql_query {
	protected final SelectOrderKey key;
	private final StringBuilder sql = new StringBuilder();

	public SelectOrderSql(Context context, SelectOrderKey key) {
		super(context);
		this.key = key;
	}

	@Override
	protected String getSql() {
		sql.append(" select ");
		// ��ӷ�������
		addResultSetParam(sql);
		sql.append(" from ");
		// ======�ڲ�union��ʼ
		sql.append(" ( ");
		// ��һ�������һ����������
		for(OrderEnum orderEnum : key.getOrders()){
			addASelectTB(orderEnum);
		}
		sql.append(" ) as t ");
		// ======�ڲ�union����
		// ����
		sql.append(" order by ");
		sql.append(key.getSortField());
		sql.append(" ");
		sql.append(key.getSortType());
		return sql.toString();
	}
	
	private void addASelectTB(OrderEnum orderEnum){
		sql.append(" select ");
		// ��ӷ�������
		addResultSetParam(sql);
		sql.append(" from ");
		sql.append(orderEnum.getDb_table());
		sql.append(" as t ");
		sql.append(" where ");
		sql.append(" t.tenantsId = @tenantsId ");
		this.addParam("@tenantsId guid", BillsConstant
				.getTenantsGuid(context));
		// ���ύ�����˻ص���ֻ�д������Լ��ɼ�
		if (StatusEnum.Submit.isIn(key.getStatus())
				|| StatusEnum.Return.isIn(key.getStatus())) {
			// ���������Լ�
			addCreatorIsMe(sql);
			// ����״̬ɸѡ
			addOrderStatus(sql, key.getStatus());
		}
		// ����״̬��������ǰ�˱��������ϼ�
		else {
			StatusEnum[] StatusEnums = key.getStatus();
			boolean isHaveOr = false;// ���۶���ʱ�Ƿ������ӿ���
			if (OrderEnum.Sales == orderEnum) {
				List<StatusEnum> list = Arrays.asList(StatusEnums);
				if (StatusEnum.Approveing.isIn(StatusEnums)) {
					isHaveOr = true;
					// �õ�ǰ����˵Ĵ����
					addSalesApproveing(sql);
					list.remove(StatusEnum.Approveing);
				}
				if (StatusEnum.Approve.isIn(StatusEnums)) {
					if (isHaveOr) {
						sql.append(" or( 1=1  ");
					}
					// ��ǰ��Ȩ����˹��Ĵ����
					addSalesApprove(sql);
					if (isHaveOr) {
						sql.append(" )  ");
					}
					list.remove(StatusEnum.Approve);
				}
				StatusEnums = list.toArray(new StatusEnum[list.size()]);
			}
			if (isHaveOr && StatusEnums.length > 0) {
				sql.append(" or( 1=1  ");
			}
			// ����״̬ɸѡ
			addOrderStatus(sql, StatusEnums);
			switch (BillsConstant.getUserAuth(context, key.getOrders()[0])) {
			case BOSS:
				if (ScopeEnum.Main == key.getScopeEnum()) {
					// ���������Լ�
					addCreatorIsMe(sql);
				} else if (ScopeEnum.Dept == key.getScopeEnum()) {
					List<Department> depts = OrderUtil.getDescendantDepts(
							context, key.getSelectDeptId());
					// ����ɸѡ
					addDept(sql, depts);

				} else {

				}
				break;
			case MANGER:
				if (ScopeEnum.Main == key.getScopeEnum()) {
					// ���������Լ�
					addCreatorIsMe(sql);
				} else if (ScopeEnum.Dept == key.getScopeEnum()) {
					List<Department> depts = OrderUtil.getDescendantDepts(
							context, key.getSelectDeptId());
					// ����ɸѡ
					addDept(sql, depts);
				} else {
					List<Department> depts = OrderUtil
							.getDescendantDepts(context);
					// ����ɸѡ
					addDept(sql, depts);
				}
				break;
			case EMPLOYEE:
				// ���������Լ�
				addCreatorIsMe(sql);
				break;
			}
			if (isHaveOr && StatusEnums.length > 0) {
				sql.append(" ) ");
			}
		}
		// ʱЧ
		if (null != key.getTime()) {
			sql.append(" and ");
			sql.append(" t.createDate >= @beginTime ");
			this.addParam("@beginTime date", key.getTime().getBeginTime());
			sql.append(" t.ereateDate <= @endTime ");
			this.addParam("@endTime date", key.getTime().getEndTime());
		}
		// ����
		if(null != key.getSearchText()){
			String searchText = key.getSearchText();
			List<String> typeList = TypeEnum.searchType(orderEnum, searchText);
			sql.append(" and (t.billsNo like '%");
			sql.append(searchText);
			sql.append("%' ");
			sql.append(" or t.cuspName like '%");
			sql.append(searchText);
			sql.append("%' ");
			sql.append(" or t.cuspFullName like '%");
			sql.append(searchText);
			sql.append("%' ");

			sql.append(" or t.createPerson like '%");
			sql.append(searchText);
			sql.append("%' ");

			for (String type : typeList) {
				sql.append(" or t.type='");
				sql.append(type);
				sql.append("' ");
			}
			
			//״̬����
			List<String> statusList = StatusEnum.searchstatus(orderEnum, searchText);
			if(!statusList.isEmpty())
			{
				boolean isStoped = false;
				for(int i=0;i<statusList.size();i++)
				{
					String e = statusList.get(i);
					if(OrderConstant2.stopCode == e){
						isStoped = true;
					}
					else if(null!=e){
						sql.append(" or t.status= ");
						sql.append(e);
					}
				}
				if(isStoped)
				{
					sql.append(" or t.isStoped=true ");
				}
			}
			
			sql.append(") ");
		}
	
	}

	/**
	 * ��ǰ�����Ȩ�ޡ���� �����Ĵ���ˣ�����ר�ã� void
	 * 
	 * @param sql
	 */
	private void addSalesApprove(StringBuilder sql) {
		sql.append(" and ");
		sql.append(" t.status = ");
		sql.append(StatusEnum.Approveing.getKey());
		switch (BillsConstant.getUserAuth(context, key.getOrders()[0])) {
		case BOSS:
			if (ScopeEnum.Main == key.getScopeEnum()) {
				// ���������Լ�
				addCreatorIsMe(sql);
			} else if (ScopeEnum.Dept == key.getScopeEnum()) {
				List<Department> depts = OrderUtil.getDescendantDepts(context,
						key.getSelectDeptId());
				// ����ɸѡ
				addApproveDept(sql, depts);

			} else {

			}
			break;
		case MANGER:
			if (ScopeEnum.Main == key.getScopeEnum()) {
				// ���������Լ�
				addCreatorIsMe(sql);
			} else if (ScopeEnum.Dept == key.getScopeEnum()) {
				List<Department> depts = OrderUtil.getDescendantDepts(context,
						key.getSelectDeptId());
				// ����ɸѡ
				addApproveDept(sql, depts);
			} else {
				List<Department> depts = OrderUtil.getDescendantDepts(context);
				// ����ɸѡ
				addApproveDept(sql, depts);
			}
			break;
		case EMPLOYEE:
			// ���������Լ�
			addCreatorIsMe(sql);
			break;
		}
	}

	/**
	 * �õ�ǰ�����Ȩ����˵Ĵ���ˣ�����ר�ã� void
	 * 
	 * @param sql
	 */
	private void addSalesApproveing(StringBuilder sql) {
		sql.append(" and ");
		sql.append(" t.status = ");
		sql.append(StatusEnum.Approveing.getKey());
		switch (BillsConstant.getUserAuth(context, key.getOrders()[0])) {
		case BOSS:
			if (ScopeEnum.Main == key.getScopeEnum()) {
				// ���������Լ�
				addCreatorIsMe(sql);
			} else if (ScopeEnum.Dept == key.getScopeEnum()) {
				List<Department> depts = OrderUtil.getChildrenDepts(context,
						key.getSelectDeptId());
				// ����ɸѡ
				addApproveDept(sql, depts);

			} else {

			}
			break;
		case MANGER:
			if (ScopeEnum.Main == key.getScopeEnum()) {
				// ���������Լ�
				addCreatorIsMe(sql);
			} else if (ScopeEnum.Dept == key.getScopeEnum()) {
				List<Department> depts = OrderUtil.getChildrenDepts(context,
						key.getSelectDeptId());
				// ����ɸѡ
				addApproveDept(sql, depts);
			} else {
				List<Department> depts = OrderUtil.getDescendantDepts(context);
				// ����ɸѡ
				addApproveDept(sql, depts);
			}
			break;
		case EMPLOYEE:
			// ���������Լ�
			addCreatorIsMe(sql);
			break;
		}
	}

	/**
	 * ��˲���ɸѡ������ר�ã�
	 * 
	 * @param sql2
	 * @param depts
	 *            void
	 */
	private void addApproveDept(StringBuilder sql, List<Department> depts) {
		sql.append(" and ");
		sql.append(" ( ");
		for (int i = 0; i < depts.size(); i++) {
			Department dept = depts.get(i);
			sql.append(" t.examDeptId = @examDeptId" + i);
			this.addParam("@examDeptId" + i + " guid", dept.getId());
			sql.append(" or ");
		}
		sql.append(" 1=0) ");
	}

	/**
	 * ����ɸѡ
	 * 
	 * @param sql2
	 * @param depts
	 *            void
	 */
	private void addDept(StringBuilder sql, List<Department> depts) {
		sql.append(" and ");
		sql.append(" ( ");
		for (int i = 0; i < depts.size(); i++) {
			Department dept = depts.get(i);
			sql.append(" t.deptId = @deptId" + i);
			this.addParam("@deptId" + i + " guid", dept.getId());
			sql.append(" or ");
		}
		sql.append(" 1=0) ");
	}

	/**
	 * ���״̬ɸѡ
	 * 
	 * @param sql
	 *            void
	 */
	private void addOrderStatus(StringBuilder sql, StatusEnum... StatusEnums) {
		sql.append(" and ");
		sql.append(" ( ");
		for (int i = 0; i < StatusEnums.length; i++) {
			StatusEnum status = StatusEnums[i];
			sql.append(" t.status = @status" + i);
			this.addParam("@status" + i + " string", status.getKey());
			sql.append(" or ");
		}
		sql.append(" 1=0) ");
	}

	/**
	 * ���������Լ�
	 * 
	 * @param sql
	 *            void
	 */
	private void addCreatorIsMe(StringBuilder sql) {
		sql.append(" and ");
		sql.append(" t.creatorId = @creatorId ");
		this.addParam("@creatorId guid", BillsConstant.getUserGuid(context));
	}

	/**
	 * �������Զ�Sql void
	 */
	private void addResultSetParam(StringBuilder sql) {
		sql.append(" t.'deliveryDate' as 'deliveryDate',         ");
		sql.append(" t.'contactId' as 'contactId',               ");
		sql.append(" t.'contactName' as 'contactName',           ");
		sql.append(" t.'contactPhone' as 'contactPhone',         ");
		sql.append(" t.'contactTel' as 'contactTel',             ");
		sql.append(" t.'defEight' as 'defEight',                 ");
		sql.append(" t.'defFive' as 'defFive',                   ");
		sql.append(" t.'defFour' as 'defFour',                   ");
		sql.append(" t.'defNine' as 'defNine',                   ");
		sql.append(" t.'defOne' as 'defOne',                     ");
		sql.append(" t.'defSeven' as 'defSeven',                 ");
		sql.append(" t.'defSix' as 'defSix',                     ");
		sql.append(" t.'defTen' as 'defTen',                     ");
		sql.append(" t.'defThree' as 'defThree',                 ");
		sql.append(" t.'defTwo' as 'defTwo',                     ");
		sql.append(" t.'deptId' as 'deptId',                     ");
		sql.append(" t.'effectiveDate' as 'effectiveDate',       ");
		sql.append(" t.'examin' as 'examin',                     ");
		sql.append(" t.'isStoped' as 'isStoped',                 ");
		sql.append(" t.'orderNumber' as 'orderNumber',           ");
		sql.append(" t.'partnerFax' as 'partnerFax',             ");
		sql.append(" t.'partnerId' as 'partnerId',               ");
		sql.append(" t.'partnerName' as 'partnerName',           ");
		sql.append(" t.'partnerNamePY' as 'partnerNamePY',       ");
		sql.append(" t.'partnerShortName' as 'partnerShortName', ");
		sql.append(" t.'remark' as 'remark',                     ");
		sql.append(" t.'returnCause' as 'returnCause',           ");
		sql.append(" t.'status' as 'status',                       ");
		sql.append(" t.'totalAmount' as 'totalAmount',           ");
		sql.append(" t.'type' as 'type',                         ");
		sql.append(" t.'createDate' as 'createDate',             ");
		sql.append(" t.'creator' as 'creator',                   ");
		sql.append(" t.'creatorId' as 'creatorId',               ");
		sql.append(" t.'RECID' as 'recid',                       ");
		sql.append(" t.'tenantsId' as 'tenantsId'                ");
	}
}
