package com.spark.psi.account.service.jointSettlement;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementInfo;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementItem;
import com.spark.b2c.publish.JointVenture.entity.JointSettlementLog;
import com.spark.b2c.publish.JointVenture.key.GetJointSettlementListKey;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.psi.account.intf.entity.jointSettlement.JointSettlementDetEntity;
import com.spark.psi.account.intf.entity.jointSettlement.JointSettlementEntity;
import com.spark.psi.account.intf.publish.entity.JointSettlementInfoImpl;
import com.spark.psi.account.intf.publish.entity.JointSettlementInfoItemImpl;
import com.spark.psi.account.intf.publish.entity.JointSettlementItemImpl;
import com.spark.psi.account.intf.publish.entity.JointSettlementLogImpl;
import com.spark.psi.publish.JointSettlementStatus;

public class JointSettlementServiceUtil {

	final static String jointSettlementTable = ERPTableNames.Joint.Joint_Settlement.getTableName();
	final static String jointSettlementDetTable = ERPTableNames.Joint.Joint_Settlement_Det.getTableName();

	public static List<JointSettlementItem> getJointSettlementList(Context context, GetJointSettlementListKey key) {
		List<JointSettlementItem> list = new ArrayList<JointSettlementItem>();
		StringBuffer sql = new StringBuffer();
		sql.append("define query getOrderItemList()\n");
		sql.append("begin\n");
		sql.append("select \n");
		sql.append(getColumns());
		sql.append(" from \n");
		sql.append(jointSettlementTable);
		sql.append(" as t\n");
		sql.append(" where 1=1 \n");
		sql.append(getStatusSql(key));
		sql.append(getSearchSql(key));
		sql.append(getOrderSql(key));
		sql.append(" end");

		DBCommand db = context.prepareStatement(sql);
		RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getCount());
		while (rs.next()) {
			list.add(fillJointSettlement(context, rs));
		}
		return list;
	}

	private static JointSettlementItem fillJointSettlement(Context context, RecordSet rs) {
		JointSettlementItemImpl impl = new JointSettlementItemImpl();
		int index = 0;
		impl.setId(rs.getFields().get(index++).getGUID());
		impl.setSupplierName(rs.getFields().get(index++).getString());
		impl.setNamePY(rs.getFields().get(index++).getString());
		impl.setShortName(rs.getFields().get(index++).getString());
		impl.setSupplierNo(rs.getFields().get(index++).getString());
		impl.setSupplierId(rs.getFields().get(index++).getGUID());
		impl.setBeginDate(rs.getFields().get(index++).getDate());
		impl.setEndDate(rs.getFields().get(index++).getDate());
		impl.setSalesAmount(rs.getFields().get(index++).getDouble());
		impl.setPercentageAmount(rs.getFields().get(index++).getDouble());
		impl.setAdjustAmount(rs.getFields().get(index++).getDouble());
		impl.setMolingAmount(rs.getFields().get(index++).getDouble());
		impl.setAmount(rs.getFields().get(index++).getDouble());
		impl.setPaidAmount(rs.getFields().get(index++).getDouble());
		impl.setCreatorId(rs.getFields().get(index++).getGUID());
		impl.setCreator(rs.getFields().get(index++).getString());
		impl.setCreateDate(rs.getFields().get(index++).getDate());
		impl.setStatus(JointSettlementStatus.getStatus(rs.getFields().get(index++).getString()));
		impl.setSheetNo(rs.getFields().get(index++).getString());

		return impl;
	}

	private static StringBuffer getColumns() {
		StringBuffer sql = new StringBuffer();
		sql.append("t.recid as id,");
		sql.append("t.supplierName as supplierName,");
		sql.append("t.namePY as namePY,");
		sql.append("t.shortName as shortName,");
		sql.append("t.supplierNo as supplierNo,");
		sql.append("t.supplierId as upplierId,");
		sql.append("t.beginDate as beginDate,");
		sql.append("t.endDate as endDate,");
		sql.append("t.salesAmount as salesAmount,");
		sql.append("t.percentageAmount as percentageAmount,");
		sql.append("t.adjustAmount as adjustAmount,");
		sql.append("t.molingAmount as molingAmount,");
		sql.append("t.amount as amount,");
		sql.append("t.paidAmount as paidAmount,");
		sql.append("t.creatorId as creatorId,");
		sql.append("t.creator as creator,");
		sql.append("t.createDate as createDate,");
		sql.append("t.status as status,");
		sql.append("t.sheetNo as sheetNo");

		return sql;
	}

	private static StringBuffer getOrderSql(GetJointSettlementListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSortField()) {
			sql.append(" order by ").append(key.getSortField().getFieldName());
			if (null != key.getSortType()) {
				sql.append(" ").append(key.getSortType()).append("\n");
			}
		} else {
			sql.append(" order by t.createDate asc \n");
		}
		return sql;
	}

	private static StringBuffer getSearchSql(GetJointSettlementListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSearchText()) {
			String searchText = key.getSearchText().trim();
			sql.append(" and (");

			sql.append(" t.sheetNo like '%").append(searchText).append("%' ");
			sql.append(" or t.namePY like '%").append(searchText).append("%' ");
			sql.append(" or t.shortName like '%").append(searchText).append("%' ");
			sql.append(" or t.supplierNo like '%").append(searchText).append("%' ");

			sql.append(")\n");
		}
		return sql;
	}

	private static StringBuffer getStatusSql(GetJointSettlementListKey key) {
		StringBuffer sql = new StringBuffer();
		if (key.getStatus().length > 0) {
			sql.append(" and ");
			if (key.getStatus().length > 1) {
				sql.append("(");
			}
			for (int i = 0; i < key.getStatus().length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" t.status='").append(key.getStatus()[i].getCode()).append("' ");
			}
			if (key.getStatus().length > 1) {
				sql.append(")\n");
			}
		}
		return sql;
	}

	public static List<JointSettlementLog> getJointSettlementLogList(Context context, GUID id) {
		List<JointSettlementLog> list = new ArrayList<JointSettlementLog>();
		StringBuffer sql = new StringBuffer();
		sql.append("define query queryJointSettlementLogs(@id guid)\n");
		sql.append("begin\n");
		sql.append("select\n");
		sql.append(getLogCloumns());
		sql.append("from ").append(ERPTableNames.Account.Payment_Det.getTableName()).append(" as d\n");
		sql.append(" join ").append(ERPTableNames.Account.Payment.getTableName()).append(" as t\n");
		sql.append(" on  t.recid=d.paymentId\n");
		sql.append(" and d.relevantBillId=@id\n");
		sql.append("end");
		DBCommand db = context.prepareStatement(sql);
		db.setArgumentValues(id);
		RecordSet rs = db.executeQuery();
		while (rs.next()) {
			list.add(fillJointSettlementLog(context, rs, id));
		}
		return list;
	}

	private static JointSettlementLog fillJointSettlementLog(Context context, RecordSet rs, GUID id) {
		JointSettlementLogImpl impl = new JointSettlementLogImpl();
		int index = 0;
		impl.setId(rs.getFields().get(index++).getGUID());// 标识
		impl.setSheetId(id);
		impl.setPaymentId(rs.getFields().get(index++).getGUID());// 付款单id
		impl.setPaymentNo(rs.getFields().get(index++).getString());
		impl.setAmount(rs.getFields().get(index++).getDouble());// 金额
		impl.setPaidAmount(rs.getFields().get(index++).getDouble());// 已付金额
		impl.setMolingAmount(rs.getFields().get(index++).getDouble());// 抹零金额
		impl.setCreatorId(rs.getFields().get(index++).getGUID());
		impl.setCreator(rs.getFields().get(index++).getString());
		impl.setCreateDate(rs.getFields().get(index++).getDate());
		return impl;
	}

	private static StringBuffer getLogCloumns() {
		StringBuffer sql = new StringBuffer();
		sql.append("d.recid as id,");
		sql.append("d.paymentId as paymentId,");// 付款单id
		sql.append("t.paymentNo as paymentNo,");
		sql.append("d.askAmount as askAmount,");// 金额
		sql.append("d.paidAmount as paidAmount,");// 已付金额
		sql.append("d.molingAmount as molingAmount,");// 抹零金额
		sql.append("t.creatorId as creatorId,");
		sql.append("t.creator as creator,");
		sql.append("t.createDate as createDate\n");
		return sql;
	}

	public static JointSettlementInfo getJointSettlementInfo(Context context, JointSettlementEntity entity, List<JointSettlementDetEntity> detList,
			List<JointSettlementLog> logList) throws Throwable {
		JointSettlementInfoImpl impl = new JointSettlementInfoImpl();

		new BeanUtils().copyObject(entity, impl);

		impl.setStatus(JointSettlementStatus.getStatus(entity.getStatus()));
		JointSettlementInfoItemImpl[] items = new JointSettlementInfoItemImpl[detList.size()];
		for (int i = 0; i < detList.size(); i++) {
			JointSettlementDetEntity det = detList.get(i);
			JointSettlementInfoItemImpl item = new JointSettlementInfoItemImpl();

			new BeanUtils().copyObject(det, item);

			items[i] = item;
		}
		JointSettlementLogImpl[] logs = new JointSettlementLogImpl[logList.size()];
		for (int i = 0; i < logList.size(); i++) {
			JointSettlementLogImpl log = new JointSettlementLogImpl();
			JointSettlementLog l = logList.get(i);

			new BeanUtils().copyObject(l, log);

			logs[i] = log;
		}
		impl.setItems(items);
		impl.setLogs(logs);
		return impl;
	}
}
