/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-12-7       Ī��        
 * ============================================================*/

package com.spark.order.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.order.intf.entity.CuspBillsEntity;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.facade.BillsConstant.BillsWithout;
import com.spark.order.intf.key.SelectCuspMainKey;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.account.intf.entity.dealing.PartnerDealingRealAmount;
import com.spark.psi.base.Employee;

/**
 * <p>
 * �ͻ���Ӧ�����service
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author �����
 * @version 2011-12-7
 */

public class BillsForCusProService extends Service {

	protected BillsForCusProService() {
		super("BillsForCusPro");
	}

	/**
	 * ���б����ݼ���
	 */
	@Publish
	protected class BillsForCusProProvide extends OneKeyResultListProvider<OrderInfo, SelectCuspMainKey> {

		@Override
		protected void provide(Context context, SelectCuspMainKey key, List<OrderInfo> list) throws Throwable {

			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramValueList = new ArrayList<Object>();
			dnaSql.append("define query QueryMainList(\n");
			addParamAndCondtion(context, key, dnaSql, conditionSql, paramValueList);
			dnaSql.append(") begin \n");

			addSelect(dnaSql);
			dnaSql.append(" from( ");
			addSelect(dnaSql);
			if (BillsEnum.PURCHASE == key.getBillsEnum()) {
				addFrom(dnaSql, BillsEnum.PURCHASE_CANCEL.getDb_table());
			} else {
				addFrom(dnaSql, BillsEnum.SALE_CANCEL.getDb_table());
			}

			dnaSql.append(conditionSql);
			// ����
			// ��ҳ
			dnaSql.append(" union all ");
			addSelect(dnaSql);
			addFrom(dnaSql, key.getBillsEnum().getDb_table());

			dnaSql.append(conditionSql);
			// ����
			// ��ҳ
			dnaSql.append(" ) as t ");
			// ����
			dnaSql.append(" order by ");
			if (null != key.getSortField()) {
				dnaSql.append(key.getSortField() + " " + key.getSortType());
			} else {
				dnaSql.append("t.createDate desc ");
			}
			// ��ҳ

			dnaSql.append(" end");
			DBCommand db = context.prepareStatement(dnaSql);

			for (int i = 0; i < paramValueList.size(); i++) {
				db.setArgumentValue(i, paramValueList.get(i));
			}
			try {
				RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getPageSize());
				while (rs.next()) {
					list.add(setData(rs));
				}
			} finally {
				db.unuse();
			}

		}
	}

	/**
	 * <p>
	 * �����������
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	 * Company: ����
	 * </p>
	 * 
	 * @author �����
	 * @version 2011-11-11
	 */
	@Publish
	protected class LastCreateDateProvide extends OneKeyResultProvider<Long, SelectCuspMainKey> {

		@Override
		protected Long provide(Context context, SelectCuspMainKey key) throws Throwable {

			StringBuilder dnaSql = new StringBuilder();
			StringBuilder conditionSql = new StringBuilder();
			List<Object> paramValueList = new ArrayList<Object>();
			dnaSql.append("define query QueryMainList(\n");
			addParamAndCondtion(context, key, dnaSql, conditionSql, paramValueList);
			dnaSql.append(") begin \n");

			addSelectLastCreateDate(dnaSql);
			dnaSql.append(" from( ");
			addSelectLastCreateDate(dnaSql);
			if (BillsEnum.PURCHASE == key.getBillsEnum()) {
				addFrom(dnaSql, BillsEnum.PURCHASE_CANCEL.getDb_table());
			} else {
				addFrom(dnaSql, BillsEnum.SALE_CANCEL.getDb_table());
			}

			dnaSql.append(conditionSql);

			dnaSql.append(" union all ");
			addSelectLastCreateDate(dnaSql);
			addFrom(dnaSql, key.getBillsEnum().getDb_table());

			dnaSql.append(conditionSql);
			// ����
			// ��ҳ
			dnaSql.append(" ) as t ");
			// ����
			dnaSql.append(" order by t.createDate desc ");
			// ��ҳ

			dnaSql.append(" end");
			DBCommand db = context.prepareStatement(dnaSql);

			for (int i = 0; i < paramValueList.size(); i++) {
				db.setArgumentValue(i, paramValueList.get(i));
			}

			try {
				RecordSet rs = db.executeQueryLimit(0, 1);
				while (rs.next()) {
					return rs.getFields().get(0).getDate();
				}
			} finally {
				db.unuse();
			}
			return null;
		}
	}

	/**
	 * <p>
	 * ͳ��ֵ��ѯ
	 * </p>
	 * 
	 * <p>
	 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	 * Company: ����
	 * </p>
	 * 
	 * @author �����
	 * @version 2011-11-11
	 */
	@Publish
	protected class CuspBillsEntityProvide extends OneKeyResultProvider<CuspBillsEntity, SelectCuspMainKey> {

		@Override
		protected CuspBillsEntity provide(Context context, SelectCuspMainKey key) throws Throwable {

			CuspBillsEntity cbe = new CuspBillsEntity();
			double orderTotalAmount = 0;// ����/�ɹ��ܶ�
			// double orderReceipt = 0;//����/�ɹ��տ�
			// double cancelAmount = 0;//����/�ɹ��˿�
			int orderTime = 0;// ����/�ɹ�����
			int cancelTime = 0;// ����/�ɹ��˻�����

			// ����/�ɹ��ܶ����
			List<Object> paramValueList = new ArrayList<Object>();
			StringBuilder orderTotalAmountSql = new StringBuilder();
			StringBuilder orderConditionSql = new StringBuilder();
			orderTotalAmountSql.append("define query QueryorderTotalAmount(\n");
			addParamAndCondtionForTotalAmount(context, key, orderTotalAmountSql, orderConditionSql, paramValueList);
			orderTotalAmountSql.append(") begin \n");

			orderTotalAmountSql.append("select sum(t.totalAmount) as totalAmount,count(t.recid) as c");

			addFrom(orderTotalAmountSql, key.getBillsEnum().getDb_table());

			orderTotalAmountSql.append(orderConditionSql);
			orderTotalAmountSql.append(" end");

			// ����/�ɹ��˻�����
			StringBuilder cancelTotalAmountSql = new StringBuilder();
			StringBuilder cancelConditionSql = new StringBuilder();
			cancelTotalAmountSql.append("define query QueryorderTotalAmount(\n");
			paramValueList.clear();
			addParamAndCondtionForTotalAmount(context, key, cancelTotalAmountSql, cancelConditionSql, paramValueList);
			cancelTotalAmountSql.append(") begin \n");

			cancelTotalAmountSql.append("select sum(t.totalAmount) as totalAmount,count(t.recid) as c");

			if (BillsEnum.PURCHASE.equals(key.getBillsEnum())) {
				addFrom(cancelTotalAmountSql, BillsEnum.PURCHASE_CANCEL.getDb_table());
			} else {
				addFrom(cancelTotalAmountSql, BillsEnum.SALE_CANCEL.getDb_table());
			}

			cancelTotalAmountSql.append(cancelConditionSql);
			cancelTotalAmountSql.append(" end");

			DBCommand orderDB = context.prepareStatement(orderTotalAmountSql);
			DBCommand cancelDB = context.prepareStatement(cancelTotalAmountSql);

			for (int i = 0; i < paramValueList.size(); i++) {
				orderDB.setArgumentValue(i, paramValueList.get(i));
				cancelDB.setArgumentValue(i, paramValueList.get(i));
			}

			try {
				RecordSet orderRs = orderDB.executeQuery();
				RecordSet cancelRs = cancelDB.executeQuery();
				while (orderRs.next()) {
					orderTotalAmount = orderRs.getFields().get(0).getDouble();
					orderTime = orderRs.getFields().get(1).getInt();
				}
				while (cancelRs.next()) {
					cancelTime = cancelRs.getFields().get(1).getInt();
				}
				cbe.setCancelTime(cancelTime);
				cbe.setOrderTime(orderTime);
				cbe.setOrderTotalAmount(orderTotalAmount);
				PartnerDealingRealAmount real = context.find(PartnerDealingRealAmount.class, key.getCusProGuid());
				if (null != real) {
					cbe.setCancelAmount(real.getReturnAmount());
					cbe.setOrderReceipt(real.getOrderAmount());
				}
			} finally {
				orderDB.unuse();
				cancelDB.unuse();
			}
			return cbe;
		}
	}

	public OrderInfo setData(RecordSet rs) {
		OrderInfo billsInfo = new OrderInfo();
		int index = 0;
		billsInfo.setRECID(rs.getFields().get(index++).getGUID());
		billsInfo.setBillsNo(rs.getFields().get(index++).getString());
		billsInfo.setPartnerId(rs.getFields().get(index++).getGUID());
		billsInfo.setPartnerShortName(rs.getFields().get(index++).getString());
		billsInfo.setPartnerName(rs.getFields().get(index++).getString());
		billsInfo.setPartnerNamePY(rs.getFields().get(index++).getString());
		billsInfo.setLinkman(rs.getFields().get(index++).getString());
		billsInfo.setAddress(rs.getFields().get(index++).getString());
		billsInfo.setRejectReason(rs.getFields().get(index++).getString());
		billsInfo.setStopReason(rs.getFields().get(index++).getString());
		billsInfo.setRemark(rs.getFields().get(index++).getString());
		billsInfo.setTotalAmount(rs.getFields().get(index++).getDouble());
		billsInfo.setCreatorId(rs.getFields().get(index++).getGUID());
		billsInfo.setCreator(rs.getFields().get(index++).getString());
		billsInfo.setCreateDate(rs.getFields().get(index++).getDate());
		billsInfo.setDeptId(rs.getFields().get(index++).getGUID());
		billsInfo.setStatus(rs.getFields().get(index++).getString());
		billsInfo.setStoped(rs.getFields().get(index++).getBoolean());
		billsInfo.setApproveStr(rs.getFields().get(index++).getString());
		billsInfo.setFinishedDate(rs.getFields().get(index++).getDate());
		billsInfo.setStoreId(rs.getFields().get(index++).getGUID());
		billsInfo.setStoreName(rs.getFields().get(index++).getString());
		billsInfo.setBillType(rs.getFields().get(index++).getString());
		return billsInfo;
	}

	/**
	 * �ɹ�����ͳ������SQL
	 * 
	 * @param context
	 * @param key
	 * @param dnaSql
	 * @param conditionSql
	 * @param paramValueList
	 *            void
	 */
	@SuppressWarnings("unused")
	public void addParamAndCondtionForTotalAmount(Context context, SelectCuspMainKey key, StringBuilder dnaSql, StringBuilder conditionSql,
			List<Object> paramValueList) {

		Employee user = BillsConstant.getUser(context);

		String submitstatus = StatusEnum.Submit.getKey();
		String rebutstatus = StatusEnum.Return.getKey();
		String examstatus = StatusEnum.Approve.getKey();
		String noStoreStatus = StatusEnum.Store_N0.getKey();
		String partStoreStatus = StatusEnum.Store_Part.getKey();
		String allStoreStatus = StatusEnum.Store_All.getKey();
		String finishstatus = StatusEnum.Finished.getKey();
		dnaSql.append("@tenantsGuid guid\n");
		conditionSql.append(" where ");
		conditionSql.append(" 1=1\n");
		paramValueList.add(BillsConstant.getTenantsGuid(context));
		dnaSql.append(",@cuspGuid guid\n");
		conditionSql.append(" and t.partnerId=@cuspGuid\n");
		paramValueList.add(key.getCusProGuid());

		/**
		 * ����ˡ�����ֹ������ɵĵ���ͳ��
		 */
		dnaSql.append(",@submitstatus string\n");
		conditionSql.append(" and (t.status<>@submitstatus\n");
		paramValueList.add(submitstatus);
		dnaSql.append(",@rebutstatus string\n");
		conditionSql.append(" and t.status<>@rebutstatus\n");
		paramValueList.add(rebutstatus);
		dnaSql.append(",@examstatus string\n");
		conditionSql.append("  and t.status<>@examstatus)\n");
		paramValueList.add(examstatus);
	}

	public void addSelectLastCreateDate(StringBuilder dnaSql) {
		dnaSql.append(" select t.createDate as createDate \n");

	}

	public void addFrom(StringBuilder dnaSql, String dbTable) {
		dnaSql.append(" from \n");
		dnaSql.append(dbTable);
		dnaSql.append(" as t \n");

	}

	public void addSelect1(StringBuilder dnaSql) {
		dnaSql.append(" select \n");
		dnaSql.append(" t.RECID as recid, \n");
		dnaSql.append(" ,t.billsNo       as billsNo       \n");
		dnaSql.append(" ,t.partnerId     as partnerId     \n");
		dnaSql.append(" ,t.partnerName   as partnerName   \n");
		dnaSql.append(" ,t.partnerNamePY as partnerNamePY \n");
		dnaSql.append(" ,t.shortName     as shortName     \n");
		dnaSql.append(" ,t.billType      as billType      \n");
		dnaSql.append(" ,t.linkman       as linkman       \n");
		dnaSql.append(" ,t.address       as address       \n");
		dnaSql.append(" ,t.deliveryDate  as deliveryDate  \n");
		dnaSql.append(" ,t.rejectReason  as rejectReason  \n");
		dnaSql.append(" ,t.stopReason    as stopReason    \n");
		dnaSql.append(" ,t.remark        as remark        \n");
		dnaSql.append(" ,t.totalAmount   as totalAmount   \n");
		dnaSql.append(" ,t.creatorId     as creatorId     \n");
		dnaSql.append(" ,t.creator       as creator       \n");
		dnaSql.append(" ,t.createDate    as createDate    \n");
		dnaSql.append(" ,t.status        as status        \n");
		dnaSql.append(" ,t.deptId        as deptId        \n");
		dnaSql.append(" ,t.isStoped      as isStoped      \n");
		dnaSql.append(" ,t.approveStr    as approveStr    \n");
		dnaSql.append(" ,t.storeId       as storeId       \n");
		dnaSql.append(" ,t.storeName     as storeName     \n");
		dnaSql.append(" ,t.finishedDate  as finishedDate  \n");

	}

	public void addSelect(StringBuilder dnaSql) {
		dnaSql.append(" select \n");
		dnaSql.append(" t.RECID         as RECID         \n");
		dnaSql.append(" ,t.billsNo       as billsNo       \n");
		dnaSql.append(" ,t.partnerId     as partnerId     \n");
		dnaSql.append(" ,t.shortName     as shortName     \n");
		dnaSql.append(" ,t.partnerName   as partnerName   \n");
		dnaSql.append(" ,t.partnerNamePY as partnerNamePY \n"); 
		dnaSql.append(" ,t.linkman       as linkman       \n");
		dnaSql.append(" ,t.address       as address       \n");
		dnaSql.append(" ,t.rejectReason  as rejectReason  \n");
		dnaSql.append(" ,t.stopReason    as stopReason    \n");
		dnaSql.append(" ,t.remark        as remark        \n");
		dnaSql.append(" ,t.totalAmount   as totalAmount   \n");
		dnaSql.append(" ,t.creatorId     as creatorId     \n");
		dnaSql.append(" ,t.creator       as creator       \n");
		dnaSql.append(" ,t.createDate    as createDate    \n");
		dnaSql.append(" ,t.deptId        as deptId        \n");
		dnaSql.append(" ,t.status        as status        \n");
		dnaSql.append(" ,t.isStoped      as isStoped      \n");
		dnaSql.append(" ,t.approveStr    as approveStr    \n");
		dnaSql.append(" ,t.finishedDate  as finishedDate  \n");
		dnaSql.append(" ,t.storeId       as storeId       \n");
		dnaSql.append(" ,t.storeName     as storeName     \n");
		dnaSql.append(" ,t.billType      as billType      \n");
	}

	public void addParamAndCondtion(Context context, SelectCuspMainKey key, StringBuilder dnaSql, StringBuilder conditionSql,
			List<Object> paramValueList) {

		Employee user = BillsConstant.getUser(context);

		String submitstatus = StatusEnum.Submit.getKey();
		String rebutstatus = StatusEnum.Return.getKey();
		String examstatus = StatusEnum.Approve.getKey();
		String noStoreStatus = StatusEnum.Store_N0.getKey();
		String partStoreStatus = StatusEnum.Store_Part.getKey();
		String allStoreStatus = StatusEnum.Store_All.getKey();
		String finishstatus = StatusEnum.Finished.getKey();
		dnaSql.append("@tenantsGuid guid\n");
		conditionSql.append(" where ");
		conditionSql.append(" 1=1\n");
		paramValueList.add(BillsConstant.getTenantsGuid(context));
		dnaSql.append(",@cuspGuid guid\n");
		conditionSql.append(" and t.partnerId=@cuspGuid\n");
		paramValueList.add(key.getCusProGuid());

		/**
		 * δ�꽻��
		 */
		if (BillsWithout.FINISH_NO.equals(key.getType())) {
			/**
			 * �Լ������Ĵ��ύ�����˻ص���
			 */
			dnaSql.append(",@createGuid guid");
			conditionSql.append(" and ((t.creatorId=@createGuid ");
			paramValueList.add(user.getId());
			dnaSql.append(",@submitstatus string");
			conditionSql.append(" and (t.status=@submitstatus or \n");
			paramValueList.add(submitstatus);
			dnaSql.append(",@rebutstatus string");
			conditionSql.append(" t.status=@rebutstatus)) \n");
			paramValueList.add(rebutstatus);
			/**
			 * ����ֹ��������ɵ���
			 */
			dnaSql.append(",@examstatus string");
			conditionSql.append(" or (t.status=@examstatus and t.isStoped=false) \n");
			paramValueList.add(examstatus);
			dnaSql.append(",@noStoreStatus string");
			conditionSql.append(" or (t.status=@noStoreStatus and t.isStoped=false) \n");
			paramValueList.add(noStoreStatus);
			dnaSql.append(",@partStoreStatus string");
			conditionSql.append(" or (t.status=@partStoreStatus and t.isStoped=false) \n");
			paramValueList.add(partStoreStatus);
			dnaSql.append(",@allStoreStatus string");
			conditionSql.append(" or (t.status=@allStoreStatus and t.isStoped=false) \n");
			paramValueList.add(allStoreStatus);

			// ֱ������
			dnaSql.append(",@noConsignment string");
			conditionSql.append(" or t.status=@noConsignment\n");
			paramValueList.add(StatusEnum.Consignment_No.getKey());
			dnaSql.append(",@yesConsignment string");
			conditionSql.append(" or t.status=@yesConsignment\n");
			paramValueList.add(StatusEnum.Consignment_Yes.getKey());

			conditionSql.append(" ) \n");

		}
		/**
		 * ���׼�¼
		 */
		if (BillsWithout.FINISH_YES.equals(key.getType())) {
			conditionSql.append(" and ");
			dnaSql.append(",@finishstatus string");
			conditionSql.append(" (t.status=@finishstatus\n");
			paramValueList.add(finishstatus);
			conditionSql.append(" or t.isStoped=true)\n");

		}

		/**
		 * ��������
		 */
		if (key.getStartDate() != 0) {
			conditionSql.append(" and t.createDate>= @startDate");
			dnaSql.append(",@startDate date ");
			paramValueList.add(key.getStartDate());
			conditionSql.append(" \n");
		}
		if (null != key.getEndDate()) {
			conditionSql.append(" and t.createDate<= @endDate");
			dnaSql.append(", @endDate date ");
			paramValueList.add(key.getEndDate());
			conditionSql.append(" \n");
		}

		/**
		 * ����
		 */
		if (null != key.getSearch()) {
			String searchText = key.getSearch();
			List<String> typeList = OrderUtil.getBillsType(key.getBillsEnum(), searchText);
			conditionSql.append(" and (t.billsNo like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");
			conditionSql.append(" or partnerName like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");
			conditionSql.append(" or t.shortName like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");

			conditionSql.append(" or t.creator like '%");
			conditionSql.append(searchText);
			conditionSql.append("%' ");

			for (String type : typeList) {
				conditionSql.append(" or t.billType='");
				conditionSql.append(type);
				conditionSql.append("' ");
			}
			conditionSql.append(") ");
		}

	}

}
