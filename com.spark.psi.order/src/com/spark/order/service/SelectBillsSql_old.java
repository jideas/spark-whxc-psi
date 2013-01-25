package com.spark.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.order.intf.OrderConstant2;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.SelectOrderKey;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.key.SelectKey.ScopeEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.service.util.OrderUtil;
import com.spark.order.util.dnaSql.DnaSql_query;
import com.spark.psi.base.Department;

/**
 * <p>
 * ��ѯ����SQL
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * 
 * 
 * @author modi
 * @version 2012-4-6
 */
public class SelectBillsSql_old extends DnaSql_query {
	protected final SelectOrderKey key;
	private final StringBuilder sql = new StringBuilder();

	public SelectBillsSql_old(Context context, SelectOrderKey key) {
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
		boolean isfirest = true;
		for (OrderEnum orderEnum : key.getOrders()) {
			if (isfirest) {
				isfirest = false;
			} else {
				sql.append(" union all ");
			}
			addASelectTB(orderEnum);
		}
		sql.append(" ) as t ");
		// ======�ڲ�union����
		sql.append(" where t.isStoped = false ");
		// ����
		sql.append(" order by ");
		sql.append(key.getSortField());
		sql.append(" ");
		sql.append(key.getSortType());
		return sql.toString();
	}

	private void addASelectTB(OrderEnum orderEnum) {
		sql.append(" select ");
		// ��ӷ�������
		addResultSetParam(sql);
		sql.append(" from ");
		sql.append(orderEnum.getDb_table());
		sql.append(" as t ");
		sql.append(" where ");
		sql.append(" 1=1 and (1=1 ");
		// ���ύ�����˻ص���ֻ�д������Լ��ɼ�
		if (StatusEnum.Submit.isIn(key.getStatus()) || StatusEnum.Return.isIn(key.getStatus())) {
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
				List<StatusEnum> list = new ArrayList<StatusEnum>();
				list.addAll(Arrays.asList(StatusEnums));
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
					isHaveOr = true;
				}
				StatusEnums = list.toArray(new StatusEnum[list.size()]);
			}
			if (isHaveOr && StatusEnums.length > 0) {
				sql.append(" or( 1=1  ");
			}
			// ����״̬ɸѡ
			// sql.append(" or(1=1 ");
			addOrderStatus(sql, StatusEnums);
			switch (BillsConstant.getUserAuth(context, key.getOrders()[0])) {
			case BOSS:
				if (ScopeEnum.Main == key.getScopeEnum()) {
					// ���������Լ�
					addCreatorIsMe(sql);
				} else if (ScopeEnum.Dept == key.getScopeEnum()) {
					List<Department> depts = OrderUtil.getDescendantDepts(context, key.getSelectDeptId());
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
					List<Department> depts = OrderUtil.getDescendantDepts(context, key.getSelectDeptId());
					// ����ɸѡ
					addDept(sql, depts);
				} else {
					List<Department> depts = OrderUtil.getDescendantDepts(context);
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
		sql.append(" ) ");
		// ʱЧ
		if (null != key.getTime()) {
			sql.append(" and ");
			sql.append(" t.createDate >= @beginTime ");
			this.addParam("@beginTime date", key.getTime().getBeginTime());
			sql.append(" t.createDate <= @endTime ");
			this.addParam("@endTime date", key.getTime().getEndTime());
		}
		// ����
		if (null != key.getSearchText()) {
			String searchText = key.getSearchText();
			List<String> typeList = TypeEnum.searchType(orderEnum, searchText);
			sql.append(" and (t.billsNo like '%");
			sql.append(searchText);
			sql.append("%' ");
			sql.append(" or t.partnerName like '%");
			sql.append(searchText);
			sql.append("%' ");
			sql.append(" or t.shortName like '%");
			sql.append(searchText);
			sql.append("%' ");

			sql.append(" or t.creator like '%");
			sql.append(searchText);
			sql.append("%' ");

			for (String type : typeList) {
				sql.append(" or t.billType='");
				sql.append(type);
				sql.append("' ");
			}

			// ״̬����
			List<String> statusList = StatusEnum.searchstatus(orderEnum, searchText);
			if (!statusList.isEmpty()) {
				boolean isStoped = false;
				for (int i = 0; i < statusList.size(); i++) {
					String e = statusList.get(i);
					if (OrderConstant2.stopCode == e) {
						isStoped = true;
					} else if (null != e) {
						sql.append(" or t.status= ");
						sql.append(e);
					}
				}
				if (isStoped) {
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
		sql.append(" t.status = @approved ");
		this.addParam("@approved string", StatusEnum.Approveing.getKey());
		switch (BillsConstant.getUserAuth(context, key.getOrders()[0])) {
		case BOSS:
			if (ScopeEnum.Main == key.getScopeEnum()) {
				// ���������Լ�
				addCreatorIsMe(sql);
			} else if (ScopeEnum.Dept == key.getScopeEnum()) {
				List<Department> depts = OrderUtil.getDescendantDepts(context, key.getSelectDeptId()); 

			} else {
				List<Department> depts = OrderUtil.getDescendantDepts(context); 
			}
			break;
		case MANGER:
			if (ScopeEnum.Main == key.getScopeEnum()) {
				// ���������Լ�
				addCreatorIsMe(sql);
			} else if (ScopeEnum.Dept == key.getScopeEnum()) {
				List<Department> depts = OrderUtil.getDescendantDepts(context, key.getSelectDeptId()); 
			} else {
				// ���������Լ�
				addCreatorIsMe(sql);
				List<Department> depts = OrderUtil.getDescendantDepts(context); 
			}
			break;
		case EMPLOYEE:
			// ���������Լ�
			addCreatorIsMe(sql);
			break;
		}
	}

	/**
	 * �õ�ǰ�����Ȩ����˵Ĵ���ˣ�����ר�ã� void ��ǰ�˷�������
	 * 
	 * @param sql
	 */
	@Deprecated
	private void addSalesApproveing(StringBuilder sql) {
		sql.append(" and ");
		sql.append(" t.status = ");
		sql.append(StatusEnum.Approveing.getKey());
		switch (BillsConstant.getUserAuth(context, key.getOrders()[0])) {
		case BOSS:
			if (ScopeEnum.Main == key.getScopeEnum()) {
				// ���������Լ�
				addCreatorIsMe(sql);
			}  
			break;
		case MANGER:
			if (ScopeEnum.Main == key.getScopeEnum()) {
				// ���������Լ�
				addCreatorIsMe(sql);
			} 
			break;
		case EMPLOYEE:
			// ���������Լ�
			addCreatorIsMe(sql);
			break;
		}
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
		sql.append(" t.RECID as RECID, \n");
		sql.append(" t.billsNo as billsNo, \n");
		sql.append(" t.partnerId      as       partnerId     ,\n");
		sql.append(" t.partnerName    as       partnerName   ,\n");
		sql.append(" t.partnerNamePY  as       partnerNamePY ,\n");
		sql.append(" t.shortName       as       shortName      ,\n");
		sql.append(" t.billType        as       billType       ,\n");
		sql.append(" t.linkman         as       linkman        ,\n");
		sql.append(" t.address         as       address        ,\n");
		sql.append(" t.rejectReason    as       rejectReason   ,\n");
		sql.append(" t.stopReason      as       stopReason     ,\n");
		sql.append(" t.remark          as       remark         ,\n");
		sql.append(" t.totalAmount     as       totalAmount    ,\n");
		sql.append(" t.creatorId       as       creatorId      ,\n");
		sql.append(" t.creator         as       creator        ,\n");
		sql.append(" t.createDate      as       createDate     ,\n");
		sql.append(" t.status          as       status         ,\n");
		sql.append(" t.deptId          as       deptId         ,\n");
		sql.append(" t.isStoped        as       isStoped       ,\n");
		sql.append(" t.approveStr      as       approveStr     ,\n");
		sql.append(" t.storeId         as       storeId        ,\n");
		sql.append(" t.storeName       as       storeName      ,\n");
		sql.append(" t.finishedDate    as       finishedDate ,\n");
		sql.append(" t.deliveryDate    as       deliveryDate \n");
	}

	public List<OrderInfo> getList(RecordSet rs) {
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		while (rs.next()) {
			list.add(setData(rs));
		}
		return list;
	}

	/**
	 * �������
	 * 
	 * @param rs
	 * @return OrderInfo
	 */
	private OrderInfo setData(RecordSet rs) {
		OrderInfo billsInfo = new OrderInfo();
		int index = 0;
		billsInfo.setRECID(rs.getFields().get(index++).getGUID());
		billsInfo.setBillsNo(rs.getFields().get(index++).getString());
		billsInfo.setPartnerId(rs.getFields().get(index++).getGUID());
		billsInfo.setPartnerName(rs.getFields().get(index++).getString());
		billsInfo.setPartnerNamePY(rs.getFields().get(index++).getString());
		billsInfo.setPartnerShortName(rs.getFields().get(index++).getString());
		billsInfo.setBillType(rs.getFields().get(index++).getString());
		billsInfo.setLinkman(rs.getFields().get(index++).getString());
		billsInfo.setAddress(rs.getFields().get(index++).getString());
		// index++;
		billsInfo.setRejectReason(rs.getFields().get(index++).getString());
		// dnaSql.append(" t.stopReason      as       stopReason     ,\n");
		billsInfo.setStopReason(rs.getFields().get(index++).getString());
		// dnaSql.append(" t.remark          as       remark         ,\n");
		billsInfo.setRemark(rs.getFields().get(index++).getString());
		// dnaSql.append(" t.totalAmount     as       totalAmount    ,\n");
		billsInfo.setTotalAmount(rs.getFields().get(index++).getDouble());
		// dnaSql.append(" t.creatorId       as       creatorId      ,\n");
		billsInfo.setCreatorId(rs.getFields().get(index++).getGUID());
		// dnaSql.append(" t.creator         as       creator        ,\n");
		billsInfo.setCreator(rs.getFields().get(index++).getString());
		// dnaSql.append(" t.createDate      as       createDate     ,\n");
		billsInfo.setCreateDate(rs.getFields().get(index++).getDate());
		// dnaSql.append(" t.status          as       status         ,\n");
		billsInfo.setStatus(rs.getFields().get(index++).getString());
		// dnaSql.append(" t.deptId          as       deptId         ,\n");
		billsInfo.setDeptId(rs.getFields().get(index++).getGUID());
		// dnaSql.append(" t.isStoped        as       isStoped       ,\n");
		billsInfo.setStoped(rs.getFields().get(index++).getBoolean());

		// dnaSql.append(" t.approveStr      as       approveStr     ,\n");
		billsInfo.setApproveStr(rs.getFields().get(index++).getString());
		// dnaSql.append(" t.storeId         as       storeId        ,\n");
		index++;
		// dnaSql.append(" t.storeName       as       storeName      ,\n");
		index++;
		// dnaSql.append(" t.finishedDate    as       finishedDate \n");
		billsInfo.setFinishedDate(rs.getFields().get(index++).getDate());
		return billsInfo;
	}
}
