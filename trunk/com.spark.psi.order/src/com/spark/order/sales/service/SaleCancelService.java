/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

package com.spark.order.sales.service;

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
import com.spark.order.intf.task.ModifySaleCancelStatusTask;
import com.spark.order.intf.task.SaleCancelDetTask;
import com.spark.order.intf.task.SaleCancelTask;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleCancelItem;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.order.sales.ORM_SaleCancel;

/**
 * <p>
 * 销售退货Service
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

public class SaleCancelService extends Service {

	protected final ORM_SaleCancel orm_SaleCancel;

	protected SaleCancelService(ORM_SaleCancel orm_SaleCancel) {
		super("SaleCancelService");
		this.orm_SaleCancel = orm_SaleCancel;
	}

	/**
	 * 
	 * <p>
	 * 新增
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
	protected class AddSaleCancel extends TaskMethodHandler<SaleCancelTask, TaskEnum> {
		protected AddSaleCancel() {
			super(TaskEnum.ADD);
		}

		@Override
		protected void handle(Context context, SaleCancelTask task) throws Throwable {

			ORMAccessor<SaleCancel> orm = context.newORMAccessor(orm_SaleCancel);
			try {
				orm.insert(task.getEntity());
				insertDet(context, task);
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
	protected class ModifySaleCancel extends TaskMethodHandler<SaleCancelTask, TaskEnum> {
		protected ModifySaleCancel() {
			super(TaskEnum.MODIFY);
		}

		@Override
		protected void handle(Context context, SaleCancelTask task) throws Throwable {

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
	protected class DeleteSaleCancel extends TaskMethodHandler<SaleCancelTask, TaskEnum> {
		protected DeleteSaleCancel() {
			super(TaskEnum.DELETE);
		}

		@Override
		protected void handle(Context context, SaleCancelTask task) throws Throwable {
			if (null == task.getEntity()) {
				return;
			}
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define delete deleteCancel(@recid guid,@status string)\n");
			dnaSql.append(" begin delete from PSI_Sales_Return as t where t.recid=@recid \n");
			dnaSql.append(" end");
			DBCommand db = context.prepareStatement(dnaSql.toString());
			db.setArgumentValues(task.getEntity().getRECID(), task.getEntity().getStatus());
			try {
				int count = db.executeUpdate();
				if (1 == count) {
					deleteDet(context, task);
				}
			} finally {
				db.unuse();
			}

		}
	}

	/**
	 * 
	 * <p>
	 * 修改流程状态
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 王天才
	 * @version 2011-11-15
	 */
	@Publish
	protected class modifyCancelstatus extends TaskMethodHandler<ModifySaleCancelStatusTask, TaskEnum> {

		protected modifyCancelstatus() {
			super(TaskEnum.UPDATE);
		}

		@Override
		protected void handle(Context context, ModifySaleCancelStatusTask task) throws Throwable {
			SaleCancel cancel = task.getEntity();
			StringBuilder dnaSql = new StringBuilder();
			List<Object> paramValueList = new ArrayList<Object>();
			Employee user = context.find(Employee.class, context.getLogin().getUser().getID());

			dnaSql.append("define update modifystatus(\n");
			dnaSql.append(" @tenantsGuid guid");
			paramValueList.add(BillsConstant.getTenantsGuid(context));
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
			dnaSql.append(" update PSI_Sales_Return as t \n");
			dnaSql.append(" set status=@newstatus");

			if (StatusEnum.Submit.isThis(cancel.getOldStatus()) || StatusEnum.Return.isThis(cancel.getOldStatus())) {
				dnaSql.append(",deptGuid=@deptGuid");
			}

			if ((StatusEnum.Approve.isThis(cancel.getOldStatus()) || StatusEnum.Submit.isThis(cancel.getOldStatus()))
					&& StatusEnum.Store_N0.isThis(cancel.getNewStatus())) {
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
				// dnaSql.append(",examin='");
				// dnaSql.append(user.getTitle());
				// dnaSql.append("(");
				// dnaSql.append(DateUtil.dateFromat(new Date().getTime()));
				// dnaSql.append(")'");
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
					if ((StatusEnum.Approve.isThis(cancel.getOldStatus()) || StatusEnum.Submit.isThis(cancel.getOldStatus()) || StatusEnum.Return
							.isThis(cancel.getOldStatus()))
							&& StatusEnum.Store_N0.isThis(cancel.getNewStatus())) {
						// 生成入库单
						generateInStoBills(context, task);
					} 
					task.setDone(true);
				}
			} finally {
				db.unuse();
			}
		}
	}

	/**
	 * 生成入库单
	 * 
	 * @param context
	 * @param task
	 *            void
	 */
	public void generateInStoBills(Context context, ModifySaleCancelStatusTask task) {
		// SaleCancel cancel = context.find(SaleCancel.class, task
		// .getEntity().getRecid());
		// //写客户信用额度
		// writeCusAlreadyCreditLine(context,cancel,"new");
		// List<SaleCancelItem> detList = context.getList(SaleCancelItem.class,
		// cancel.getRecid());
		// Instorage entity = null;
		// List<InstorageItem> detailList = new ArrayList<InstorageItem>();
		// if (detList.isEmpty()) {
		// return;
		// }
		// for (int i=0;i<detList.size();i++) {
		// SaleCancelItem det = detList.get(i);
		//
		// if (null == entity
		// || !det.getStoreGuid().equals(entity.getStoreGuid())) {
		// if (null != entity) {
		// InstoAddTask outStoTask = new InstoAddTask(entity,
		// detailList);
		// context.handle(outStoTask, InstoAddTask.CheckInType.SALE_CANCEL);
		// }
		// entity = new Instorage();
		// entity.setRelaOrderGuid(cancel.getRecid());
		// entity.setRelaOrderNo(cancel.getBillsNo());
		// entity.setStoreGuid(det.getStoreGuid());
		// entity.setStoreName(det.getStoreName());
		// entity.setStoreNamePY(PinyinHelper.getLetter(det.getStoreName()));
		// entity.setCusproGuid(cancel.getCuspGuid());
		// entity.setCusproName(cancel.getCuspFullName());
		// // entity.setCusproNamePY(cancel.getCuspFullNamePY());
		// entity.setCusproShortName(cancel.getCuspName());
		// entity.setRemark(cancel.getRemark());
		// detailList.clear();
		// }
		//
		// InstorageItem odt = new InstorageItem();
		// odt.setGoodsGuid(det.getGoodsGuid());
		// odt.setGoodsName(det.getGoodsName());
		// odt.setGoodsNo(det.getGoodsNo());
		// odt.setGoodsAttr(det.getGoodsAttr());
		// odt.setGoodsUnit(det.getUnit());
		// odt.setGoodsPrice(det.getPrice());
		// odt.setPlanInstoCount(det.getNum());
		// odt.setGoodsScale(det.getGoodsCountDigit());
		// detailList.add(odt);
		//			
		// if(i==detList.size()-1)
		// {
		// InstoAddTask outStoTask = new InstoAddTask(entity,
		// detailList);
		// context.handle(outStoTask, InstoAddTask.CheckInType.SALE_CANCEL);
		// }
		//
		// }
		//
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
	protected class BuyCancelProvider extends OneKeyResultProvider<SaleCancel, GUID> {
		@Override
		protected SaleCancel provide(Context context, GUID guid) throws Throwable {
			ORMAccessor<SaleCancel> orm = context.newORMAccessor(orm_SaleCancel);
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
	protected class NewCancelListProvider extends OneKeyResultListProvider<SaleCancel, SelectMainKey> {

		@Override
		protected void provide(Context context, SelectMainKey key, List<SaleCancel> list) throws Throwable {
			StringBuilder dnaSql = new StringBuilder();
			dnaSql.append("define query QueryNewCancelList(@creatorId guid,@submitstatus string,@rebutstatus string)\n");
			dnaSql.append(" begin select \n");

			dnaSql.append(" t.RECID as recid, \n");
			dnaSql.append(" t.billsNo       as billsNo                 , \n");
			dnaSql.append(" t.billType      as 			billType     , \n");
			dnaSql.append(" t.partnerId     as 			partnerId    , \n");
			dnaSql.append(" t.shortName     as 			shortName    , \n");
			dnaSql.append(" t.partnerName   as 			partnerName  , \n");
			dnaSql.append(" t.partnerNamePY as 			partnerNamePY, \n");
			dnaSql.append(" t.fax           as 			fax          , \n");
			dnaSql.append(" t.linkman       as 			linkman      , \n");
			dnaSql.append(" t.rejectReason  as 			rejectReason , \n");
			dnaSql.append(" t.stopReason    as 			stopReason   , \n");
			dnaSql.append(" t.remark        as 			remark       , \n");
			dnaSql.append(" t.totalAmount   as 			totalAmount  , \n");
			dnaSql.append(" t.creatorId     as 			creatorId    , \n");
			dnaSql.append(" t.creator       as 			creator      , \n");
			dnaSql.append(" t.createDate    as 			createDate   , \n");
			dnaSql.append(" t.deptId        as 			deptId       , \n");
			dnaSql.append(" t.storeId       as 			storeId      , \n");
			dnaSql.append(" t.storeName     as 			storeName    , \n");
			dnaSql.append(" t.status        as 			status       , \n");
			dnaSql.append(" t.isStoped      as 			isStoped     , \n");
			dnaSql.append(" t.approveStr    as 			approveStr   , \n");
			dnaSql.append(" t.finishedDate  as 			finishedDate , \n");
			dnaSql.append(" t.address       as 			address     \n");
			dnaSql.append(" from PSI_Sales_Return as t\n");
			dnaSql.append(" where  t.creatorId=@creatorId \n");
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
				dnaSql.append(" or t.shortName like '%");
				dnaSql.append(searchText);
				dnaSql.append("%' ");
				dnaSql.append(" or t.partnerName like '%");
				dnaSql.append(searchText);
				dnaSql.append("%' ");

				dnaSql.append(" or t.creator like '%");
				dnaSql.append(searchText);
				dnaSql.append("%' ");

				for (String type : typeList) {
					dnaSql.append(" or t.type='");
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
			String rebutstatus = StatusEnum.Return.getKey();// OrderUtil.getSysFuntion(StatusEnum.REBUT.getKey())
			db.setArgumentValues(user.getId(), submitstatus, rebutstatus);
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
	 * @return FBuyCancel
	 */
	public SaleCancel setData(RecordSet rs) {
		SaleCancel c = new SaleCancel();
		try {
			int index = 0;
			c.setRECID(rs.getFields().get(index++).getGUID());
			c.setBillsNo(rs.getFields().get(index++).getString());
			c.setBillType(rs.getFields().get(index++).getString());
			c.setPartnerId(rs.getFields().get(index++).getGUID());
			c.setPartnerShortName(rs.getFields().get(index++).getString());
			c.setPartnerName(rs.getFields().get(index++).getString());
			c.setPartnerNamePY(rs.getFields().get(index++).getString());
			c.setFax(rs.getFields().get(index++).getString());
			c.setLinkman(rs.getFields().get(index++).getString());
			c.setRejectReason(rs.getFields().get(index++).getString());
			c.setStopReason(rs.getFields().get(index++).getString());
			c.setRemark(rs.getFields().get(index++).getString());
			c.setTotalAmount(rs.getFields().get(index++).getDouble());
			c.setCreatorId(rs.getFields().get(index++).getGUID());
			c.setCreator(rs.getFields().get(index++).getString());
			c.setCreateDate(rs.getFields().get(index++).getDate());
			c.setDeptId(rs.getFields().get(index++).getGUID());
			c.setStoreId(rs.getFields().get(index++).getGUID());
			c.setStoreName(rs.getFields().get(index++).getString());
			c.setStatus(rs.getFields().get(index++).getString());
			c.setStoped(rs.getFields().get(index++).getBoolean());
			c.setApproveStr(rs.getFields().get(index++).getString());
			c.setFinishedDate(rs.getFields().get(index++).getDate());
			c.setAddress(rs.getFields().get(index++).getString());

		} finally {

		}
		return c;
	}

	/**
	 * 新增明细
	 * 
	 * @param context
	 * @param task
	 *            void
	 */
	public void insertDet(Context context, SaleCancelTask task) {
		for (SaleCancelItem det : getDets(task.getList())) {
			SaleCancelDetTask dTask = new SaleCancelDetTask();
			dTask.setEntity(det);
			context.handle(dTask, TaskEnum.ADD);
		}

	}

	/**
	 * 明细按商品+仓库+单价合并
	 * 
	 * @param dets
	 * @return List<BuyOrdDet>
	 */
	private List<SaleCancelItem> getDets(List<SaleCancelItem> dets) {
		List<SaleCancelItem> list = new ArrayList<SaleCancelItem>();
		for (SaleCancelItem det : dets) {
			boolean b = true;
			for (SaleCancelItem d : list) {
				if (d.getGoodsId().equals(det.getGoodsId()) && d.getPrice() == det.getPrice()
						&& d.getStoreId().equals(det.getStoreId())) {
					
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

	/**
	 * 删除明细
	 * 
	 * @param context
	 * @param task
	 *            void
	 */
	public void deleteDet(Context context, SaleCancelTask task) {
		SaleCancelDetTask dTask = new SaleCancelDetTask();
		dTask.setBillsGuid(task.getEntity().getRECID());
		context.handle(dTask, TaskEnum.DELETE);
	}

}
