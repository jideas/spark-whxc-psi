package com.spark.onlineorder.intf.publish.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.encrypt.MD5;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.onlineorder.intf.entity.OnlineOrderDetEntity;
import com.spark.onlineorder.intf.entity.OnlineOrderEntity;
import com.spark.onlineorder.intf.entity.OnlineOrderLogEntity;
import com.spark.onlineorder.intf.publish.entity.OnlineOrderListEntityImpl;
import com.spark.onlineorder.intf.publish.entity.TotalMaterialsItemImpl;
import com.spark.order.util.OnlineOrderUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.order.onlineorder.ORM_OnlineOrder;
import com.spark.psi.order.onlineorder.ORM_OnlineOrderDet;
import com.spark.psi.order.onlineorder.ORM_OnlineOrderDetByOrderId;
import com.spark.psi.order.onlineorder.ORM_OnlineOrderLog;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.VantagesType;
import com.spark.psi.publish.base.bom.entity.BomInfo;
import com.spark.psi.publish.base.bom.entity.BomInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfo;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderListEntity;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
import com.spark.psi.publish.onlineorder.key.GetOnlineOrderListKey;
import com.spark.psi.publish.onlineorder.key.GetTotalMaterialsKey;
import com.spark.psi.publish.onlineorder.task.CreateOnlineOrderTask;
import com.spark.psi.publish.onlineorder.task.DeleteOnlineOrderTask;
import com.spark.psi.publish.onlineorder.task.SetOnlineOrderReturnFlagTask;
import com.spark.psi.publish.onlineorder.task.SplitOnlineOrderTask;
import com.spark.psi.publish.onlineorder.task.UpdateOnlineOrderStatusTask;

/**
 * 网上订单service
 * 
 */
public class OnlineOrderPublishService extends Service {

	final ORM_OnlineOrderDetByOrderId orm_OnlineOrderDetByOrderId;
	final ORM_OnlineOrder orm_OnlineOrder;
	final ORM_OnlineOrderDet orm_OnlineOrderDet;
	final ORM_OnlineOrderLog orm_OnlineOrderLog;
	final static String onlineOrderTable = ERPTableNames.Sales.OnlineOrder.getTableName();

	protected OnlineOrderPublishService(ORM_OnlineOrderDetByOrderId orm_OnlineOrderDetByOrderId, ORM_OnlineOrder orm_OnlineOrder,
			ORM_OnlineOrderDet orm_OnlineOrderDet, ORM_OnlineOrderLog orm_OnlineOrderLog) {
		super("com.spark.onlineorder.intf.publish.service.OnlineOrderPublishService");
		this.orm_OnlineOrderDetByOrderId = orm_OnlineOrderDetByOrderId;
		this.orm_OnlineOrder = orm_OnlineOrder;
		this.orm_OnlineOrderDet = orm_OnlineOrderDet;
		this.orm_OnlineOrderLog = orm_OnlineOrderLog;
	}

	/**
	 * 
	 * 到货
	 */
	@Publish
	protected class Arrival extends TaskMethodHandler<UpdateOnlineOrderStatusTask, UpdateOnlineOrderStatusTask.Method> {

		protected Arrival() {
			super(UpdateOnlineOrderStatusTask.Method.Arrival);
		}

		@Override
		protected void handle(Context context, UpdateOnlineOrderStatusTask task) throws Throwable {
			if (null == task.getIds() || task.getIds().length < 1) {
				throw new Throwable("未知错误！");
			}
			Employee emp = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			StringBuffer sql = new StringBuffer();
			sql
					.append("define update updateToArrival(@id guid,@arrivedConfirmId guid,@arrivedConfirm string,@arrivedConfirmDate date,@verificationCode string)\n");
			sql.append("begin\n");
			sql.append("update \n");
			sql.append(onlineOrderTable);
			sql.append(" as t\n");
			sql.append("set status='");
			sql.append(OnlineOrderStatus.Arrivaled.getCode());
			sql.append("'\n");
			sql.append(",arrivedConfirmId=@arrivedConfirmId\n");
			sql.append(",arrivedConfirm=@arrivedConfirm\n");
			sql.append(",arrivedConfirmDate=@arrivedConfirmDate\n");
			sql.append(",verificationCode=@verificationCode\n");
			sql.append("where t.RECID=@id\n");
			sql.append(" and t.status='").append(OnlineOrderStatus.Delivering.getCode()).append("'\n");
			sql.append("end");
			DBCommand db = context.prepareStatement(sql);
			try {
				for (GUID id : task.getIds()) {
					String verificationCode = OnlineOrderUtil.getVerificationCode();
					db.setArgumentValues(id, emp.getId(), emp.getName(), new Date(System.currentTimeMillis()),new MD5().getMD5ofStr(verificationCode));
					if (db.executeUpdate() < 1) {
						throw new Throwable("有其他用户在做同样的操作，请检查！");
					}
					ORMAccessor<OnlineOrderEntity> orm = context.newORMAccessor(orm_OnlineOrder);
					OnlineOrderEntity od = orm.findByRECID(id);
					if(null!=od)
					{
						OnlineOrderUtil.sendVerificationCode(context, od, verificationCode);
						insertLog(context, id, task);
					}
					
				}
			} finally {
				db.unuse();
			}

		}

	}

	/**
	 * 
	 * 拣货中
	 */
	@Publish
	protected class Picking extends TaskMethodHandler<UpdateOnlineOrderStatusTask, UpdateOnlineOrderStatusTask.Method> {

		protected Picking() {
			super(UpdateOnlineOrderStatusTask.Method.Picking);
		}

		@Override
		protected void handle(Context context, UpdateOnlineOrderStatusTask task) throws Throwable {
			if (null == task.getIds() || task.getIds().length < 1) {
				throw new Throwable("未知错误！");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update updateToPicking(@id guid)\n");
			sql.append("begin\n");
			sql.append("update \n");
			sql.append(onlineOrderTable);
			sql.append(" as t\n");
			sql.append("set status='");
			sql.append(OnlineOrderStatus.Picking.getCode());
			sql.append("'\n");
			sql.append("where t.RECID=@id\n");
			sql.append(" and t.status='").append(OnlineOrderStatus.Effected.getCode()).append("'\n");
			sql.append("end");
			DBCommand db = context.prepareStatement(sql);
			try {
				for (GUID id : task.getIds()) {
					db.setArgumentValues(id);
					if (db.executeUpdate() < 1) {
						throw new Throwable("有其他用户在做同样的操作，请检查！");
					}
					insertLog(context, id, task);
				}
			} finally {
				db.unuse();
			}

		}

	}

	/**
	 * 
	 * 配送
	 */
	@Publish
	protected class Deliver extends TaskMethodHandler<UpdateOnlineOrderStatusTask, UpdateOnlineOrderStatusTask.Method> {

		protected Deliver() {
			super(UpdateOnlineOrderStatusTask.Method.Deliver);
		}

		@Override
		protected void handle(Context context, UpdateOnlineOrderStatusTask task) throws Throwable {
			if (null == task.getIds() || task.getIds().length < 1) {
				throw new Throwable("未知错误！");
			}
			Employee emp = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			StringBuffer sql = new StringBuffer();
			sql.append("define update updateToDeliver(@id guid,@consignorId guid,@consignor string,@consignedDate date)\n");
			sql.append("begin\n");
			sql.append("update \n");
			sql.append(onlineOrderTable);
			sql.append(" as t\n");
			sql.append("set status='");
			sql.append(OnlineOrderStatus.Delivering.getCode());
			sql.append("'\n");
			sql.append(" ,consignorId=@consignorId\n");
			sql.append(" ,consignor=@consignor\n");
			sql.append(" ,consignedDate=@consignedDate\n");
			sql.append("where t.RECID=@id\n");
			sql.append(" and t.status='").append(OnlineOrderStatus.Picking.getCode()).append("'\n");
			sql.append("end");
			DBCommand db = context.prepareStatement(sql);
			try {
				for (GUID id : task.getIds()) {
					db.setArgumentValues(id, emp.getId(), emp.getName(), System.currentTimeMillis());
					if (db.executeUpdate() < 1) {
						throw new Throwable("有其他用户在做同样的操作，请检查！");
					}
					insertLog(context, id, task);
				}
			} finally {
				db.unuse();
			}

		}

	}

	/**
	 * 
	 * 完成
	 */
	@Publish
	protected class Finish extends TaskMethodHandler<UpdateOnlineOrderStatusTask, UpdateOnlineOrderStatusTask.Method> {

		protected Finish() {
			super(UpdateOnlineOrderStatusTask.Method.Finish);
		}

		@Override
		protected void handle(Context context, UpdateOnlineOrderStatusTask task) throws Throwable {
			if (null == task.getIds() || task.getIds().length < 1) {
				throw new Throwable("未知错误！");
			}
			Login login = context.find(Login.class);
			Employee emp = context.find(Employee.class, login.getEmployeeId());
			StringBuffer sql = new StringBuffer();
			sql.append("define update updateToFinish(@id guid)\n");
			sql.append("begin\n");
			sql.append("update \n");
			sql.append(onlineOrderTable);
			sql.append(" as t\n");
			sql.append("set status='");
			sql.append(OnlineOrderStatus.Finished.getCode());
			sql.append("'\n");
			if (CheckIsNull.isNotEmpty(task.getNoVerificationReason())) {
				sql.append(",noVerificationReason='").append(task.getNoVerificationReason().trim()).append("'\n");
			}
			sql.append("where t.RECID=@id\n");
			sql.append(" and t.status='").append(OnlineOrderStatus.Arrivaled.getCode()).append("'\n");
			sql.append("end");
			DBCommand db = context.prepareStatement(sql);
			try {
				for (GUID id : task.getIds()) {
					db.setArgumentValues(id);
					if (db.executeUpdate() < 1) {
						throw new Throwable("有其他用户在做同样的操作，请检查！");
					}
					insertLog(context, id, task);
					ORMAccessor<OnlineOrderEntity> orm = context.newORMAccessor(orm_OnlineOrder);
					OnlineOrderEntity od = orm.findByRECID(id);
					if (null != od && od.getVantages() > 0) {
						StringBuffer msql = new StringBuffer();
						msql.append("define update upCMS_MEMBER_ACCOUNT(@recid guid)\n");
						msql.append(" begin \n");
						msql.append("update CMS_MEMBER_ACCOUNT as cma \n");
						msql.append(" set vantages=(cma.vantages+").append(od.getVantages()).append(") \n");
						msql.append(" where cma.recid=@recid \n");
						msql.append("end");
						DBCommand dbc = context.prepareStatement(msql);
						dbc.setArgumentValues(od.getMemberId());
						int count = dbc.executeUpdate();
						if (count > 0) {
							StringBuffer lsql = new StringBuffer();
							lsql.append("define insert insertCMS_MEMBER_VANTAGES(@recid guid,@memberID guid");
							lsql.append(",@vType string");
							lsql.append(",@relaBillsId guid");
							lsql.append(",@relaBillsNo string");
							lsql.append(",@vantages double");
							lsql.append(",@occurDate date");
							lsql.append(",@modifyPerson string");
							lsql.append(",@modifyPersonId guid");
							lsql.append(")\n");
							lsql.append(" begin \n");
							lsql
									.append("insert into CMS_MEMBER_VANTAGES(RECID,memberID,vType,relaBillsId,relaBillsNo,vantages,occurDate,modifyPerson,modifyPersonId) \n");
							lsql
									.append(" values(@recid,@memberID,@vType,@relaBillsId,@relaBillsNo,@vantages,@occurDate,@modifyPerson,@modifyPersonId) \n");
							lsql.append("end");
							DBCommand ldbc = context.prepareStatement(lsql);
							ldbc.setArgumentValues(GUID.randomID(), od.getMemberId(), VantagesType.Handsel.getCode(), od.getId(), od.getBillsNo(), od
									.getVantages(), new Date(System.currentTimeMillis()), emp.getName(), emp.getId());
							ldbc.executeUpdate();
						}
					}
				}
			} finally {
				db.unuse();
			}

		}

	}

	/**
	 * 
	 * 退货标识
	 * 
	 */
	@Publish
	protected class SetOnlineOrderReturnFlag extends SimpleTaskMethodHandler<SetOnlineOrderReturnFlagTask> {

		@Override
		protected void handle(Context context, SetOnlineOrderReturnFlagTask task) throws Throwable {
			if (null == task.getId()) {
				throw new Throwable("id不能为空！");
			}
			ORMAccessor<OnlineOrderEntity> orm = context.newORMAccessor(orm_OnlineOrder);
			OnlineOrderEntity e = orm.findByRECID(task.getId());
			if (null == e || e.isReturnFlag()) {
				throw new Throwable("您操作的数据已发生改变，请检查！");
			}
			e.setReturnFlag(true);
			orm.update(e);
			orm.unuse();
		}

	}

	/**
	 * 拆分
	 */
	@Publish
	protected class SplitOnlineOrder extends SimpleTaskMethodHandler<SplitOnlineOrderTask>

	{

		@Override
		protected void handle(Context context, SplitOnlineOrderTask task) throws Throwable {
			if (null == task.getId() || null == task.getItems() || task.getItems().length < 1 || task.getSplitingAmount() <= 0) {
				throw new Throwable("id||items不能为空，splitingAmount必须大于0！");
			}
			ORMAccessor<OnlineOrderDetEntity> ormd = context.newORMAccessor(orm_OnlineOrderDet);
			double splitingAmount = 0;
			for (SplitOnlineOrderTask.Item item : task.getItems()) {
				splitingAmount += item.getSplitingAmount();
			}
			if (splitingAmount != task.getSplitingAmount()) {
				throw new Throwable("明细拆分金额跟总查分金额不一致！");
			}
			ORMAccessor<OnlineOrderEntity> orm = context.newORMAccessor(orm_OnlineOrder);
			OnlineOrderEntity ooe = orm.findByRECID(task.getId());
			if (null == ooe || !OnlineOrderStatus.Picking.getCode().equals(ooe.getStatus()) || ooe.getTotalAmount() != task.getTotalAmount()
					|| ooe.getTotalAmount() < task.getSplitingAmount()) {
				throw new Throwable("您操作的数据已发生改变，请检查！");
			}
			ooe.setTotalAmount(DoubleUtil.sub(ooe.getTotalAmount(), task.getSplitingAmount()));
			orm.update(ooe);
			OnlineOrderEntity newooe = new OnlineOrderEntity();
			BeanUtils bu = new BeanUtils();
			bu.copyProperties(ooe, newooe);
			newooe.setTotalAmount(task.getSplitingAmount());
			newooe.setDisAmount(0);
			newooe.setStatus(OnlineOrderStatus.Effected.getCode());
			GUID recid = GUID.randomID();
			newooe.setId(recid);
			newooe.setBillsNo(context.find(String.class, SheetNumberType.OnlineOrder));
			orm.insert(newooe);
			for (SplitOnlineOrderTask.Item item : task.getItems()) {
				if (null == item.getId() || item.getSplitingCount() <= 0 || item.getSplitingAmount() <= 0) {
					throw new Throwable("item.splitingCount||item.splitingAmount必须大于0！");
				}
				OnlineOrderDetEntity oode = ormd.findByRECID(item.getId());
				if (null == oode || oode.getCount() != item.getCount() || oode.getAmount() != item.getAmount()
						|| oode.getAmount() < item.getSplitingAmount() || oode.getCount() < item.getSplitingCount()) {
					throw new Throwable("您操作的数据已发生改变，请检查！");
				}

				oode.setAmount(DoubleUtil.sub(oode.getAmount(), item.getSplitingAmount()));
				oode.setCount(DoubleUtil.sub(oode.getCount(), item.getSplitingCount()));
				ormd.update(oode);
				OnlineOrderDetEntity newoode = new OnlineOrderDetEntity();
				bu.copyProperties(oode, newoode);
				newoode.setBillsId(recid);
				newoode.setAmount(item.getSplitingAmount());
				newoode.setCount(item.getSplitingCount());
				newoode.setDisAmount(0);
				newoode.setDiscount(0);
				newoode.setId(GUID.randomID());
				ormd.insert(newoode);
			}
			orm.unuse();
			ormd.unuse();

		}

	}

	/**
	 * 列表查询
	 */
	@Publish
	protected class GetOnlineOrderList extends OneKeyResultProvider<OnlineOrderListEntity, GetOnlineOrderListKey> {

		@Override
		protected OnlineOrderListEntity provide(Context context, GetOnlineOrderListKey key) throws Throwable {
			List<OnlineOrderItem> list = OnlineOrderUtil.getOrderItemList(context, key);
			int totalCount = list.size();
			OnlineOrderListEntityImpl listEntity = new OnlineOrderListEntityImpl(list, totalCount);
			return listEntity;
		}

	}

	/**
	 * 详情查询
	 */
	@Publish
	protected class GetOnlineOrderInfo extends OneKeyResultProvider<OnlineOrderInfo, GUID> {
		@Override
		protected OnlineOrderInfo provide(Context context, GUID id) throws Throwable {
			ORMAccessor<OnlineOrderEntity> orm = context.newORMAccessor(orm_OnlineOrder);
			OnlineOrderEntity entity = orm.findByRECID(id);
			if (null != entity) {
				return OnlineOrderUtil.getOrderInfo(context, entity);
			}
			return null;
		}

	}

	/**
	 * 详情商品明细查询
	 */
	@Publish
	protected class GetOnlineOrderInfoItems extends OneKeyResultListProvider<OnlineOrderInfoItem, GUID> {
		@Override
		protected void provide(Context context, GUID id, List<OnlineOrderInfoItem> resultList) throws Throwable {
			if (null != id) {
				ORMAccessor<OnlineOrderDetEntity> orm = context.newORMAccessor(orm_OnlineOrderDetByOrderId);
				List<OnlineOrderDetEntity> list = orm.fetch(id);
				if (null != list && list.size() > 0) {
					for (OnlineOrderDetEntity det : list) {
						resultList.add(OnlineOrderUtil.getOrderInfoItem(context, det));
					}
				}
				orm.unuse();
			}
		}

	}

	public void insertLog(Context context, GUID id, Object task) {
		OnlineOrderLogEntity entity = OnlineOrderUtil.fillLogEntity(context, id, task);
		ORMAccessor<OnlineOrderLogEntity> orm = context.newORMAccessor(orm_OnlineOrderLog);
		try {
			orm.insert(entity);
		} finally {
			orm.unuse();
		}
	}

	/**
	 * 创建
	 */
	@Publish
	protected class Create extends SimpleTaskMethodHandler<CreateOnlineOrderTask> {

		@Override
		protected void handle(Context context, CreateOnlineOrderTask task) throws Throwable {
			GUID id = GUID.randomID();
			ORMAccessor<OnlineOrderEntity> orm = context.newORMAccessor(orm_OnlineOrder);
			ORMAccessor<OnlineOrderDetEntity> ormd = context.newORMAccessor(orm_OnlineOrderDet);

			OnlineOrderEntity o = new OnlineOrderEntity();
			o.setAddress(task.getAddress());
			o.setBillsNo(context.find(String.class, SheetNumberType.OnlineOrder));
			o.setConsignee(task.getConsignee());
			o.setConsigneeTel(task.getConsigneeTel());
			o.setCreateDate(System.currentTimeMillis());
			o.setDeliveryeDate(task.getDeliveryeDate());
			o.setDisAmount(task.getDisAmount());
			o.setId(id);
			o.setMemberId(task.getMemberId());
			o.setRealName(task.getRealName());
			o.setRemark(task.getRemark());
			o.setStationId(task.getStationId());
			o.setStationName(task.getStationName());
			o.setStatus(OnlineOrderStatus.Effected.getCode());
			o.setTotalAmount(task.getTotalAmount());
			o.setType(task.getType());
			o.setToDoor(task.isToDoor());

			try {
				orm.insert(o);
				if (null != task.getItems() && task.getItems().length > 0) {
					for (CreateOnlineOrderTask.Item i : task.getItems()) {
						OnlineOrderDetEntity d = new OnlineOrderDetEntity();
						d.setAmount(i.getAmount());
						d.setBillsId(id);
						d.setCount(i.getCount());
						d.setDisAmount(i.getDisAmount());
						d.setDiscount(i.getDiscount());
						d.setGoodsCode(i.getGoodsCode());
						d.setGoodsId(i.getGoodsId());
						d.setGoodsName(i.getGoodsName());
						d.setGoodsNo(i.getGoodsNo());
						d.setGoodsScale(i.getGoodsScale());
						d.setGoodsSpec(i.getGoodsSpec());
						d.setId(GUID.randomID());
						d.setPrice(i.getPrice());
						d.setUnit(i.getUnit());

						ormd.insert(d);
					}
				}

			} finally {
				orm.unuse();
				ormd.unuse();
			}

			insertLog(context, id, task);

		}
	}

	/**
	 * 删除
	 */
	@Publish
	protected class Delete extends SimpleTaskMethodHandler<DeleteOnlineOrderTask> {

		@Override
		protected void handle(Context context, DeleteOnlineOrderTask task) throws Throwable {
			if (OnlineOrderUtil.deleteOnlineOrder(context, task.getId()) > 0) {
				OnlineOrderUtil.deleteOnlineOrderDet(context, task.getId());
				OnlineOrderUtil.deleteOnlineOrderLog(context, task.getId());
			}

		}

	}

	/**
	 * 根据商品汇总信息，查询所需材料汇总信息
	 */
	@Publish
	protected class GetTotalMaterials extends OneKeyResultProvider<TotalMaterialsItem, GetTotalMaterialsKey> {

		@Override
		protected TotalMaterialsItem provide(Context context, GetTotalMaterialsKey key) throws Throwable {
			if (null == key.getOnlineOrderIds() || key.getOnlineOrderIds().length < 1) {
				if (null == key.getGoodsItems() || key.getGoodsItems().length < 1) {
					throw new Throwable("goodsItems不能为空！");
				}
				TotalMaterialsItemImpl tmi = new TotalMaterialsItemImpl();

				// TotalMaterialsItemImpl.GoodsItemImpl[] gs = new
				// TotalMaterialsItemImpl.GoodsItemImpl[key.getGoodsItems().length];
				Map<GUID, TotalMaterialsItemImpl.MaterialsItemImpl> mmap = new HashMap<GUID, TotalMaterialsItemImpl.MaterialsItemImpl>();
				for (GetTotalMaterialsKey.GoodsItem item : key.getGoodsItems()) {

					GoodsItem goods = context.find(GoodsItem.class, item.getGoodsId());

					String message = "商品：" + item.getGoodsName() + "[" + item.getGoodsSpec() + "]" + "，尚未设置BOM，请先设置！";
					if (null == goods.getBomId()) {
						throw new Throwable(message);
					}
					BomInfo bom = context.find(BomInfo.class, goods.getBomId());
					if (null == bom) {
						throw new Throwable(message);
					}
					List<BomInfoItem> bomItems = bom.getBomInfoItems();
					if (null == bomItems || bomItems.size() < 1) {
						throw new Throwable(message);
					}
					for (BomInfoItem i : bomItems) {
						if (mmap.containsKey(i.getMaterialId())) {
							TotalMaterialsItemImpl.MaterialsItemImpl t = (TotalMaterialsItemImpl.MaterialsItemImpl) mmap.get(i.getMaterialId());
							double count = t.getCount() + (DoubleUtil.mul(item.getCount(), i.getRealCount(), 2));
							t.setCount(count);
						} else {
							TotalMaterialsItemImpl.MaterialsItemImpl impl = tmi.new MaterialsItemImpl();
							impl.setCount(DoubleUtil.mul(item.getCount(), i.getRealCount(), 2));
							impl.setMaterialCode(i.getMaterialCode());
							impl.setMaterialId(i.getMaterialId());
							impl.setMaterialName(i.getMaterialName());
							impl.setMaterialNo(i.getMaterialNo());
							impl.setMaterialSpec(i.getMaterialSpec());
							impl.setUnit(i.getMaterialUnit());
							mmap.put(impl.getMaterialId(), impl);
						}
					}
				}
				TotalMaterialsItemImpl.MaterialsItemImpl[] ms = new TotalMaterialsItemImpl.MaterialsItemImpl[mmap.size()];
				int i = 0;
				for (TotalMaterialsItemImpl.MaterialsItemImpl mi : mmap.values()) {
					ms[i] = mi;
					i++;
				}
				// int j = 0;
				// for(TotalMaterialsItemImpl.GoodsItemImpl gi:gl)
				// {
				// gs[j] = gi;
				// j++;
				// }
				// tmi.setGoods(gs);
				tmi.setMaterials(ms);
				return tmi;
			} else {
				TotalMaterialsItemImpl tmi = new TotalMaterialsItemImpl();
				List<TotalMaterialsItemImpl.GoodsItemImpl> gl = OnlineOrderUtil.getGoods(context, key);
				List<TotalMaterialsItemImpl.GoodsItemImpl> rgl = new ArrayList<TotalMaterialsItemImpl.GoodsItemImpl>();
				
				Map<GUID, TotalMaterialsItemImpl.MaterialsItemImpl> mmap = new HashMap<GUID, TotalMaterialsItemImpl.MaterialsItemImpl>();
				for (TotalMaterialsItemImpl.GoodsItemImpl item : gl) {
					//TODO
//					if (item.getCount() == item.getLockCount()) {
//						continue;
//					}
					GoodsItem goods = context.find(GoodsItem.class, item.getGoodsId());
					if (null != goods) {
						item.setBomId(goods.getBomId());
					}
					String message = "商品：" + item.getGoodsName() + "[" + item.getGoodsSpec() + "]" + "，尚未设置BOM，请先设置！";
					if (null == item.getBomId()) {
						throw new Throwable(message);
					}
					BomInfo bom = context.find(BomInfo.class, item.getBomId());
					if (null == bom) {
						throw new Throwable(message);
					}
					List<BomInfoItem> bomItems = bom.getBomInfoItems();
					if (null == bomItems || bomItems.size() < 1) {
						throw new Throwable(message);
					}
					for (BomInfoItem i : bomItems) {
						if (mmap.containsKey(i.getMaterialId())) {
							TotalMaterialsItemImpl.MaterialsItemImpl t = (TotalMaterialsItemImpl.MaterialsItemImpl) mmap.get(i.getMaterialId());
							double count = t.getCount() + (DoubleUtil.mul(item.getCount(), i.getRealCount(), 2));
							//TODO
//							double count = t.getCount() + (DoubleUtil.mul(DoubleUtil.sub(item.getCount(), item.getLockCount()), i.getRealCount(), 2));
							t.setCount(count);
						} else {
							TotalMaterialsItemImpl.MaterialsItemImpl impl = tmi.new MaterialsItemImpl();
							impl.setCount(DoubleUtil.mul(item.getCount(), i.getRealCount(), 2));
							//TODO
//							impl.setCount(DoubleUtil.mul(DoubleUtil.sub(item.getCount(), item.getLockCount()), i.getRealCount(), 2));
							impl.setMaterialCode(i.getMaterialCode());
							impl.setMaterialId(i.getMaterialId());
							impl.setMaterialName(i.getMaterialName());
							impl.setMaterialNo(i.getMaterialNo());
							impl.setMaterialSpec(i.getMaterialSpec());
							impl.setUnit(i.getMaterialUnit());
							mmap.put(impl.getMaterialId(), impl);
						}
					}
					rgl.add(item);
				}
				TotalMaterialsItemImpl.MaterialsItemImpl[] ms = new TotalMaterialsItemImpl.MaterialsItemImpl[mmap.size()];
				int i = 0;
				for (TotalMaterialsItemImpl.MaterialsItemImpl mi : mmap.values()) {
					ms[i] = mi;
					i++;
				}
				TotalMaterialsItemImpl.GoodsItemImpl[] gs = new TotalMaterialsItemImpl.GoodsItemImpl[rgl.size()];
				int j = 0;
				for (TotalMaterialsItemImpl.GoodsItemImpl gi : rgl) {
					gs[j] = gi;
					j++;
				}
				tmi.setGoods(gs);
				tmi.setMaterials(ms);
				return tmi;
			}

		}

	}

}
