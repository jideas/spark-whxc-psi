/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

package com.spark.order.purchase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.date.DateUtil;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.key.SelectMainKey;
import com.spark.order.intf.task.ModifyCancelStatusTask;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;
import com.spark.order.purchase.intf.task.PurchaseCancelDetTask;
import com.spark.order.purchase.intf.task.PurchaseCancelTask;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Tenant;
import com.spark.psi.order.purchase.ORM_BuyCancel;

/**
 * <p>
 * 采购退货Service
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author 王天才
 * @version 2011-11-10
 */

public class BuyCancelService extends Service {

	protected final ORM_BuyCancel orm_BuyCancel;

	protected BuyCancelService(ORM_BuyCancel orm_BuyCancel) {
		super("BuyCancelService");
		this.orm_BuyCancel = orm_BuyCancel;
	}

	/**
	 * 新增
	 */
	@Publish
	protected class AddBuyCancel extends TaskMethodHandler<PurchaseCancelTask, TaskEnum> {
		protected AddBuyCancel() {
			super(TaskEnum.ADD);
		}

		@Override
		protected void handle(Context context, PurchaseCancelTask task) throws Throwable {
			ORMAccessor<PurchaseCancel> orm = context.newORMAccessor(orm_BuyCancel);
			try {
				orm.insert(task.getEntity());
				insertDet(context, task);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				orm.unuse();
			}

		}
	}

	/**
	 * 
	 * <p>
	 * 修改
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 王天才
	 * @version 2011-11-10
	 */
	@Publish
	protected class ModifyBuyCancel extends TaskMethodHandler<PurchaseCancelTask, TaskEnum> {
		protected ModifyBuyCancel() {
			super(TaskEnum.MODIFY);
		}

		@Override
		protected void handle(Context context, PurchaseCancelTask task) throws Throwable {
			context.handle(task, TaskEnum.DELETE);
			context.handle(task, TaskEnum.ADD);
		}
	}

	/**
	 * 
	 * <p>
	 * 删除
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 王天才
	 * @version 2011-11-10
	 */
	@Publish
	protected class DeleteBuyCancel extends TaskMethodHandler<PurchaseCancelTask, TaskEnum> {
		protected DeleteBuyCancel() {
			super(TaskEnum.DELETE);
		}

		@Override
		protected void handle(Context context, PurchaseCancelTask task) throws Throwable {

			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define delete deleteCancel(@recid guid)\n");
			dnaSql.append(" begin \n");
			dnaSql.append(" delete from PSI_Purchase_Return as t where t.recid=@recid \n");
			dnaSql.append(" end ");
			DBCommand db = context.prepareStatement(dnaSql);
			db.setArgumentValues(task.getEntity().getRECID(), task.getEntity().getStatus());
			try {
				int count = db.executeUpdate();
				if (count == 1) {
					deleteDet(context, task);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.unuse();
			}

		}
	}

	/**
	 * 修改流程状态
	 */
	@Publish
	protected class modifyCancelstatus extends TaskMethodHandler<ModifyCancelStatusTask, TaskEnum> {

		protected modifyCancelstatus() {
			super(TaskEnum.UPDATE);
		}

		@Override
		protected void handle(Context context, ModifyCancelStatusTask task) throws Throwable {
			PurchaseCancel cancel = task.getEntity();
			StringBuilder dnaSql = new StringBuilder();
			List<Object> paramValueList = new ArrayList<Object>();
			Employee user = context.find(Employee.class, context.getLogin().getUser().getID());

			dnaSql.append("define update modifystatus(\n");
			dnaSql.append(" @tenantsGuid guid");
			paramValueList.add(context.find(Tenant.class).getId());
			dnaSql.append(",@deptGuid guid");
			paramValueList.add(user.getDepartmentId());
			dnaSql.append(",@recid guid");
			paramValueList.add(cancel.getRECID());
			dnaSql.append(",@oldstatus guid");
			paramValueList.add(cancel.getOldStatus());
			dnaSql.append(",@newstatus guid\n");
			paramValueList.add(cancel.getNewStatus());
			dnaSql.append(")");

			dnaSql.append(" begin \n");
			dnaSql.append(" update PSI_Purchase_Return as t \n");
			dnaSql.append(" set status=@newstatus");

			if (StatusEnum.Submit.isThis(cancel.getOldStatus()) || StatusEnum.Return.isThis(cancel.getOldStatus())) {
				dnaSql.append(",deptGuid=@deptGuid");
			}
			if ((StatusEnum.Approve.isThis(cancel.getOldStatus()) || StatusEnum.Submit.isThis(cancel.getOldStatus()))
					&& StatusEnum.Store_N0.isThis(cancel.getOldStatus())) {
				dnaSql.append(",examin='");
				dnaSql.append(user.getName());
				dnaSql.append("(");
				dnaSql.append(DateUtil.dateFromat(new Date().getTime()));
				dnaSql.append(")'");
			}
			if (null != cancel.getRejectReason()) {
				dnaSql.append(",examin='");
				dnaSql.append(user.getName());
				dnaSql.append("(");
				dnaSql.append(DateUtil.dateFromat(new Date().getTime()));
				dnaSql.append(")'");
				dnaSql.append(",rebutCause='");
				dnaSql.append(cancel.getRejectReason().trim());
				dnaSql.append("'");
			}
			if (null != cancel.getStopReason()) {
				dnaSql.append(",stopCause='");
				dnaSql.append(cancel.getStopReason().trim());
				dnaSql.append("'");
			}
			if (cancel.isStoped()) {
				dnaSql.append(",isStoped=true");
			}

			if (!cancel.isStoped()) {
				dnaSql.append(",isStoped=false");
			}

			dnaSql.append("\n");

			dnaSql.append(" where t.tenantsGuid=@tenantsGuid \n");
			dnaSql.append(" and t.recid=@recid \n");
			dnaSql.append(" and t.status=@oldstatus");

			dnaSql.append(" end");
			DBCommand db = context.prepareStatement(dnaSql);
			for (int i = 0; i < paramValueList.size(); i++) {
				db.setArgumentValue(i, paramValueList.get(i));
			}
			try {
				int count = db.executeUpdate();
				if (1 == count) {
					task.setDone(true);
				}
			} finally {
				db.unuse();
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 根据GUID查询1条记录
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 王天才
	 * @version 2011-11-10
	 */
	@Publish
	protected class BuyCancelProvider extends OneKeyResultProvider<PurchaseCancel, GUID> {

		@Override
		protected PurchaseCancel provide(Context context, GUID guid) throws Throwable {
			ORMAccessor<PurchaseCancel> orm = context.newORMAccessor(orm_BuyCancel);
			return orm.findByRECID(guid);
		}

	}

	/**
	 * 
	 * <p>
	 * 待提交或已退货的数据
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 王天才
	 * @version 2011-11-14
	 */
	@Publish
	protected class NewCancelListProvider extends OneKeyResultListProvider<PurchaseCancel, SelectMainKey> {

		@Override
		protected void provide(Context context, SelectMainKey key, List<PurchaseCancel> list) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			dnaSql
					.append("define query QueryNewCancelList(@tenantsGuid guid,@createGuid guid,@submitstatus string,@rebutstatus string)\n");
			dnaSql.append(" begin select \n");

			dnaSql.append(" t.RECID as recid, \n");
			dnaSql.append(" t.billsNo        as billsNo       ,\n");
			dnaSql.append(" t.partnerId     as supplierId    ,\n");
			dnaSql.append(" t.ShortName      as ShortName     ,\n");
			dnaSql.append(" t.partnerName   as supplierName  ,\n");
			dnaSql.append(" t.partnerNamePY as supplierNamePY,\n");
			dnaSql.append(" t.consignee      as consignee     ,\n");
			dnaSql.append(" t.linkman        as linkman       ,\n");
			dnaSql.append(" t.address        as address       ,\n");
			dnaSql.append(" t.rejectReason   as rejectReason  ,\n");
			dnaSql.append(" t.stopReason     as stopReason    ,\n");
			dnaSql.append(" t.remark         as remark        ,\n");
			dnaSql.append(" t.totalAmount    as totalAmount   ,\n");
			dnaSql.append(" t.creatorId      as creatorId     ,\n");
			dnaSql.append(" t.creator        as creator       ,\n");
			dnaSql.append(" t.createDate     as createDate    ,\n");
			dnaSql.append(" t.deptId         as deptId        ,\n");
			dnaSql.append(" t.status         as status        ,\n");
			dnaSql.append(" t.isStoped       as isStoped      ,\n");
			dnaSql.append(" t.approveStr     as approveStr    ,\n");
			dnaSql.append(" t.finishedDate   as finishedDate  ,\n");
			dnaSql.append(" t.storeId        as storeId       ,\n");
			dnaSql.append(" t.storeName      as storeName     ,\n");
			dnaSql.append(" t.billType       as billType   \n");
			dnaSql.append(" from PSI_Purchase_Return as t\n");

			dnaSql.append(" where t.creatorId=@createGuid \n");
			dnaSql.append(" and (t.status=@submitstatus or t.status=@rebutstatus)");
			/**
			 * 搜索
			 */
			if (null != key.getSearch()) {
				String searchText = key.getSearch();
				List<String> typeList = OrderUtil.getBillsType(key.getBillsEnum(), searchText);
				dnaSql.append(" and (t.billsNo like '%");
				dnaSql.append(searchText);
				dnaSql.append("%' ");
				dnaSql.append(" or t.partnerName like '%");
				dnaSql.append(searchText);
				dnaSql.append("%' ");
				dnaSql.append(" or t.ShortName like '%");
				dnaSql.append(searchText);
				dnaSql.append("%' ");

				dnaSql.append(" or t.creator like '%");
				dnaSql.append(searchText);
				dnaSql.append("%' ");

				for (String type : typeList) {
					dnaSql.append(" or t.billType='");
					dnaSql.append(type);
					dnaSql.append("' ");
				}
				dnaSql.append(") ");
			}

			if (null != key.getSortField() && !"".equals(key.getSortField())) {
				dnaSql.append(" order by ");
				dnaSql.append(key.getSortField());
				dnaSql.append(" ");
				dnaSql.append(key.getSortType());
			} else {
				dnaSql.append(" order by t.createDate desc ");
			}

			dnaSql.append(" end");
			DBCommand db = context.prepareStatement(dnaSql);

			Employee user = BillsConstant.getUser(context);
			String submitstatus = StatusEnum.Submit.getKey();// OrderUtil.getSysFuntion(StatusEnum.SUBMIT.getKey()).getRecid();
			String rebutstatus = StatusEnum.Return.getKey();// OrderUtil.getSysFuntion(StatusEnum.REBUT.getKey()).getRecid();
			db.setArgumentValues(BillsConstant.getTenantsGuid(context), user.getId(), submitstatus, rebutstatus);
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
	 * 填充数据
	 * 
	 * @param rs
	 * @return BuyCancel
	 */
	public PurchaseCancel setData(RecordSet rs) {
		PurchaseCancel c = new PurchaseCancel();
		try {
			int index = 0;
			c.setRECID(rs.getFields().get(index++).getGUID());
			c.setBillsNo(rs.getFields().get(index++).getString());
			c.setPartnerId(rs.getFields().get(index++).getGUID());
			c.setPartnerShortName(rs.getFields().get(index++).getString());
			c.setPartnerName(rs.getFields().get(index++).getString());
			c.setPartnerNamePY(rs.getFields().get(index++).getString());
			c.setConsignee(rs.getFields().get(index++).getString());
			c.setLinkman(rs.getFields().get(index++).getString());
			c.setAddress(rs.getFields().get(index++).getString());
			c.setRejectReason(rs.getFields().get(index++).getString());
			c.setStopReason(rs.getFields().get(index++).getString());
			c.setRemark(rs.getFields().get(index++).getString());
			c.setTotalAmount(rs.getFields().get(index++).getDouble());
			c.setCreatorId(rs.getFields().get(index++).getGUID());
			c.setCreator(rs.getFields().get(index++).getString());
			c.setCreateDate(rs.getFields().get(index++).getDate());
			c.setDeptId(rs.getFields().get(index++).getGUID());
			c.setStatus(rs.getFields().get(index++).getString());
			c.setStoped(rs.getFields().get(index++).getBoolean());
			c.setApproveStr(rs.getFields().get(index++).getString());
			c.setFinishedDate(rs.getFields().get(index++).getDate());
			c.setStoreId(rs.getFields().get(index++).getGUID());
			c.setStoreName(rs.getFields().get(index++).getString());
			c.setBillType(rs.getFields().get(index++).getString());
		} finally {

		}
		return c;
	}

	/**
	 * 删除明细
	 * 
	 * @param context
	 * @param task
	 *            void
	 */
	public void deleteDet(Context context, PurchaseCancelTask task) {
		PurchaseCancelDetTask dTask = new PurchaseCancelDetTask();
		dTask.setBillsGuid(task.getEntity().getRECID());
		context.handle(dTask, TaskEnum.DELETE);

	}

	/**
	 * 
	 * 新增明细
	 * 
	 * @param context
	 * @param task
	 *            void
	 */
	public void insertDet(Context context, PurchaseCancelTask task) {
		for (PurchaseCancelItem det : getDets(task.getList())) {
			PurchaseCancelDetTask detTask = new PurchaseCancelDetTask();
			detTask.setEntity(det);
			context.handle(detTask, TaskEnum.ADD);
		}

	}

	/**
	 * 明细按商品+仓库+单价合并
	 * 
	 * @param dets
	 * @return List<BuyOrdDet>
	 */
	private List<PurchaseCancelItem> getDets(List<PurchaseCancelItem> dets) {
		List<PurchaseCancelItem> list = new ArrayList<PurchaseCancelItem>();
		for (PurchaseCancelItem det : dets) {
			boolean b = true;
			for (PurchaseCancelItem d : list) {
				if (d.getGoodsId().equals(det.getGoodsId()) && d.getPrice() == det.getPrice()) {
					d.setCount(d.getCount() + det.getCount());
					b = false;
					break;
				}
			}
			if (b) {
				list.add(det);
			}
		}
		return list;
	}
}
