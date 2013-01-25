package com.spark.produceorder.intf.publish.service;

import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.produceorder.intf.entity.ProduceFinishedLogEntity;
import com.spark.produceorder.intf.entity.ProduceGoodsDetEntity;
import com.spark.produceorder.intf.entity.ProduceMaterialDetEntity;
import com.spark.produceorder.intf.entity.ProduceOrderEntity;
import com.spark.produceorder.intf.publish.entity.ProduceOrderListEntityImpl;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.order.produceorder.ORM_ProduceFinishedLog;
import com.spark.psi.order.produceorder.ORM_ProduceFinishedLogByOrderId;
import com.spark.psi.order.produceorder.ORM_ProduceGoodsDet;
import com.spark.psi.order.produceorder.ORM_ProduceGoodsDetByOrderId;
import com.spark.psi.order.produceorder.ORM_ProduceMaterialDet;
import com.spark.psi.order.produceorder.ORM_ProduceMaterialDetByOrderId;
import com.spark.psi.order.produceorder.ORM_ProduceOrder;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.onlineorder.task.UpdateOnlineOrderStatusTask;
import com.spark.psi.publish.produceorder.entity.ProduceOrderGoodsLogListEntity;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderListEntity;
import com.spark.psi.publish.produceorder.key.GetProduceOrderGoodsLogListKey;
import com.spark.psi.publish.produceorder.key.GetProduceOrderListKey;
import com.spark.psi.publish.produceorder.task.CreateProduceOrderTask;
import com.spark.psi.publish.produceorder.task.FinishTask;
import com.spark.psi.publish.produceorder.task.ReturnTask;
import com.spark.psi.publish.produceorder.task.UpdateProduceOrderStatusTask;
import com.spark.psi.publish.produceorder.task.ReceiveTask;
import com.spark.psi.publish.produceorder.task.FinishTask.Item;

/**
 * 
 * 生产订单publishService
 * 
 */
public class ProduceOrderPublishService extends Service {

	final ORM_ProduceOrder orm_ProduceOrder;
	final ORM_ProduceGoodsDet orm_ProduceGoodsDet;
	final ORM_ProduceGoodsDetByOrderId orm_ProduceGoodsDetByOrderId;
	final ORM_ProduceMaterialDet orm_ProduceMaterialDet;
	final ORM_ProduceMaterialDetByOrderId orm_ProduceMaterialDetByOrderId;
	final ORM_ProduceFinishedLog orm_ProduceFinishedLog;
	final ORM_ProduceFinishedLogByOrderId orm_ProduceFinishedLogByOrderId;
	final static String produceOrderTable = ERPTableNames.Sales.Produce_Order.getTableName();
	final static String produceGoodsTable = ERPTableNames.Sales.Produce_GoodsDet.getTableName();
	final static String produceMaterialTable = ERPTableNames.Sales.Produce_MaterialsDet.getTableName();

	public ProduceOrderPublishService(ORM_ProduceOrder orm_ProduceOrder, ORM_ProduceGoodsDet orm_ProduceGoodsDet,
			ORM_ProduceMaterialDet orm_ProduceMaterialDet, ORM_ProduceFinishedLog orm_ProduceFinishedLog,
			ORM_ProduceGoodsDetByOrderId orm_ProduceGoodsDetByOrderId,
			ORM_ProduceMaterialDetByOrderId orm_ProduceMaterialDetByOrderId,
			ORM_ProduceFinishedLogByOrderId orm_ProduceFinishedLogByOrderId
			) {
		super("com.spark.produceorder.intf.publish.service.ProduceOrderPublishService");
		this.orm_ProduceOrder = orm_ProduceOrder;
		this.orm_ProduceGoodsDet = orm_ProduceGoodsDet;
		this.orm_ProduceMaterialDet = orm_ProduceMaterialDet;
		this.orm_ProduceFinishedLog = orm_ProduceFinishedLog;
		this.orm_ProduceGoodsDetByOrderId = orm_ProduceGoodsDetByOrderId;
		this.orm_ProduceMaterialDetByOrderId = orm_ProduceMaterialDetByOrderId;
		this.orm_ProduceFinishedLogByOrderId = orm_ProduceFinishedLogByOrderId;
	}

	/**
	 * 创建
	 * 
	 */
	@Publish
	protected class CreateProduceOrder extends SimpleTaskMethodHandler<CreateProduceOrderTask> {

		@Override
		protected void handle(Context context, CreateProduceOrderTask task) throws Throwable {
			if (checkValues(task)) {
				throw new Throwable("参数：onlineOrderIds||goods，不能为空！");
			}
			ORMAccessor<ProduceOrderEntity> orm = context.newORMAccessor(orm_ProduceOrder);
						
			GUID id = GUID.randomID();
			ProduceOrderEntity order = new ProduceOrderEntity();
			order.setBillsNo(context.find(String.class, SheetNumberType.ProduceOrder));
			order.setCreateDate(System.currentTimeMillis());
			order.setCreator(context.find(Employee.class,context.find(Login.class).getEmployeeId()).getName());
			order.setGoodsCount(task.getGoodsCount());
			order.setId(id);
			order.setPlanDate(task.getPlanDate());
			order.setRemark(task.getRemark());
			order.setStatus(task.getStatus().getCode());
			
			task.setId(id);
			try
			{
				double count = 0;
				double lockCount = 0;
				for(CreateProduceOrderTask.GoodsItem g:task.getGoods())
				{
					count = DoubleUtil.sum(count, g.getCount());
					lockCount = DoubleUtil.sum(lockCount, g.getLockCount());
				}
				if(count!=lockCount)
				{
					orm.insert(order);
					insertGoodsDet(context,task);
					insertMaterialsDet(context,task);
				}
				
				if(ProduceOrderStatus.Producing.equals(task.getStatus())&&null!=task.getOnlineOrderIds()&&task.getOnlineOrderIds().length>0)
				updateOnlineOrderStatus(context,task);
			}
			finally
			{
				orm.unuse();
			}

		}

	}

	/**
	 * 领料
	 * 
	 */
	@Publish
	protected class Receive extends SimpleTaskMethodHandler<ReceiveTask> {

		@Override
		protected void handle(Context context, ReceiveTask task) throws Throwable {
			if(null==task.getId()||null==task.getItems()||task.getItems().length<1)
			{
				throw new Throwable("参数：id||items不能为空");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update receive(@id guid,@count double)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(produceMaterialTable);
			sql.append(" as t\n");
			sql.append(" set \"receivedCount\"=t.\"receivedCount\"+@count\n");
			sql.append(" where t.recid=@id\n");
//			sql.append(" and ((t.\"count\"-t.receivedCount)>@count ");
//			sql.append(" or (t.\"count\"-t.receivedCount)=@count)\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			for(ReceiveTask.Item item:task.getItems())
			{
				db.setArgumentValues(item.getId(), item.getCount());
				if(db.executeUpdate()<1)
				{
					throw new Throwable("有其他人在做同样的操作，请检查！");
				}
			}
			ProduceOrderServiceUtil.createCheckOutSheet(context,task);
			db.unuse();

		}

	}

	/**
	 * 退料
	 * 
	 */
	@Publish
	protected class Return extends SimpleTaskMethodHandler<ReturnTask> {

		@Override
		protected void handle(Context context, ReturnTask task) throws Throwable {
			if(null==task.getId()||null==task.getItems()||task.getItems().length<1)
			{
				throw new Throwable("参数：id||items不能为空");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update receive(@id guid,@count double)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(produceMaterialTable);
			sql.append(" as t\n");
			sql.append(" set returnedCount=t.returnedCount+@count\n");
			sql.append(" where t.recid=@id\n");
//			sql.append(" and ((t.receivedCount-t.returnedCount)>@count ");
//			sql.append(" or (t.receivedCount-t.returnedCount)=@count)\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			for(ReturnTask.Item item:task.getItems())
			{
				db.setArgumentValues(item.getId(), item.getCount());
				if(db.executeUpdate()<1)
				{
					throw new Throwable("有其他人在做同样的操作，请检查！");
				}
			}
			ProduceOrderServiceUtil.createCheckOutSheet(context,task);
			db.unuse();

		}

	}

	/**
	 * 确认完成
	 * 
	 */
	@Publish
	protected class Finish extends SimpleTaskMethodHandler<FinishTask> {

		@Override
		protected void handle(Context context, FinishTask task) throws Throwable {
			if(null==task.getId()||null==task.getItems()||task.getItems().length<1)
			{
				throw new Throwable("参数：id||items不能为空");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update receive(@id guid,@count double)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(produceGoodsTable);
			sql.append(" as t\n");
			sql.append(" set finishedCount=t.finishedCount+@count\n");
			sql.append(" where t.recid=@id\n");
			sql.append(" and ((t.\"count\"-t.finishedCount)>@count ");
			sql.append(" or (t.\"count\"-t.finishedCount)=@count)\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			for(FinishTask.Item item:task.getItems())
			{
				db.setArgumentValues(item.getId(), item.getCount());
				if(db.executeUpdate()<1)
				{
					throw new Throwable("有其他人在做同样的操作，请检查！");
				}
				insertLog(context,item,task);
			}
			ProduceOrderServiceUtil.createCheckInSheet(context,task);
			ORMAccessor<ProduceGoodsDetEntity> orm = context.newORMAccessor(orm_ProduceGoodsDetByOrderId);
			List<ProduceGoodsDetEntity> list = orm.fetch(task.getId());
			double count = 0;
			double finishedCount =  0;
			for(ProduceGoodsDetEntity det:list)
			{
				count = DoubleUtil.sum(count, det.getCount());
				finishedCount = DoubleUtil.sum(finishedCount, det.getFinishedCount());
			}
			if(0!=count&&count==finishedCount)
			{
				UpdateProduceOrderStatusTask t = new UpdateProduceOrderStatusTask(task.getId());
				context.handle(t, UpdateProduceOrderStatusTask.Method.Finish);
			}
			db.unuse();

		}

	}

	/**
	 * 提交
	 * 
	 */
	@Publish
	protected class Submit extends TaskMethodHandler<UpdateProduceOrderStatusTask, UpdateProduceOrderStatusTask.Method> {

		protected Submit() {
			super(UpdateProduceOrderStatusTask.Method.Submit);
		}

		@Override
		protected void handle(Context context, UpdateProduceOrderStatusTask task) throws Throwable {
			StringBuffer sql = new StringBuffer();
			if(null==task.getId())
			{
				throw new Throwable("参数：id不能为空！");
			}
			sql.append("define update submit(@id guid)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(produceOrderTable);
			sql.append(" as t\n");
			sql.append(" set status='").append(ProduceOrderStatus.Submited.getCode()).append("'\n");
			sql.append(" where \n");
			sql.append(" t.RECID=@id\n");
			sql.append(" and (t.status='").append(ProduceOrderStatus.Submiting.getCode()).append("'\n");
			sql.append(" or t.status='").append(ProduceOrderStatus.Reject.getCode()).append("')\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			db.setArgumentValues(task.getId());
			if(db.executeUpdate()<1)
			{
				throw new Throwable("有其他用户在做同样的操作，请检查！");
			}
			db.unuse();

		}

	}

	/**
	 * 批准
	 * 
	 */
	@Publish
	protected class Approve extends TaskMethodHandler<UpdateProduceOrderStatusTask, UpdateProduceOrderStatusTask.Method> {

		protected Approve() {
			super(UpdateProduceOrderStatusTask.Method.Approve);
		}

		@Override
		protected void handle(Context context, UpdateProduceOrderStatusTask task) throws Throwable {
			
			if(null==task.getId())
			{
				throw new Throwable("参数：id不能为空！");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update approve(@id guid)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(produceOrderTable);
			sql.append(" as t\n");
			sql.append(" set status='").append(ProduceOrderStatus.Producing.getCode()).append("'\n");
			sql.append(" where \n");
			sql.append(" t.RECID=@id\n");
			sql.append(" and t.status='").append(ProduceOrderStatus.Submited.getCode()).append("'\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			db.setArgumentValues(task.getId());
			if(db.executeUpdate()<1)
			{
				throw new Throwable("有其他用户在做同样的操作，请检查！");
			}
			db.unuse();
		}

	}

	/**
	 * 退回
	 * 
	 */
	@Publish
	protected class Deny extends TaskMethodHandler<UpdateProduceOrderStatusTask, UpdateProduceOrderStatusTask.Method> {

		protected Deny() {
			super(UpdateProduceOrderStatusTask.Method.Deny);
		}

		@Override
		protected void handle(Context context, UpdateProduceOrderStatusTask task) throws Throwable {
			if(null==task.getId())
			{
				throw new Throwable("参数：id不能为空！");
			}
			if (StringUtils.isEmpty(task.getRejectReason())) {
				throw new Throwable("退回原因不能为空！");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update approve(@id guid)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(produceOrderTable);
			sql.append(" as t\n");
			sql.append(" set status='").append(ProduceOrderStatus.Reject.getCode()).append("', ");
			sql.append(" rejectReason='" + task.getRejectReason() + "' \n");
			sql.append(" where \n");
			sql.append(" t.RECID=@id\n");
			sql.append(" and t.status='").append(ProduceOrderStatus.Submited.getCode()).append("'\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			db.setArgumentValues(task.getId());
			if(db.executeUpdate()<1)
			{
				throw new Throwable("有其他用户在做同样的操作，请检查！");
			}
			db.unuse();

		}

	}
	
	/**
	 * 完成
	 * 
	 */
	@Publish
	protected class Finished extends TaskMethodHandler<UpdateProduceOrderStatusTask, UpdateProduceOrderStatusTask.Method> {

		protected Finished() {
			super(UpdateProduceOrderStatusTask.Method.Finish);
		}

		@Override
		protected void handle(Context context, UpdateProduceOrderStatusTask task) throws Throwable {
			StringBuffer sql = new StringBuffer();
			if(null==task.getId())
			{
				throw new Throwable("参数：id不能为空！");
			}
			sql.append("define update submit(@id guid,@finishDate date)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(produceOrderTable);
			sql.append(" as t\n");
			sql.append(" set status='").append(ProduceOrderStatus.Finished.getCode()).append("'\n");
			sql.append(",finishDate=@finishDate\n");
			sql.append(" where \n");
			sql.append(" t.RECID=@id\n");
			sql.append(" and t.status='").append(ProduceOrderStatus.Producing.getCode()).append("'\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			db.setArgumentValues(task.getId(),System.currentTimeMillis());
			if(db.executeUpdate()<1)
			{
				throw new Throwable("有其他用户在做同样的操作，请检查！");
			}
			db.unuse();

		}

	}

	/**
	 * 列表查询
	 */
	@Publish
	protected class GetProduceOrderList extends OneKeyResultProvider<ProduceOrderListEntity, GetProduceOrderListKey> {

		@Override
		protected ProduceOrderListEntity provide(Context context, GetProduceOrderListKey key) throws Throwable {
			if(null==key.getStatus()||key.getStatus().length<1)
			return null;
			List<ProduceOrderItem> dataList = ProduceOrderServiceUtil.getProduceOrderList(context,key);
			
			return new ProduceOrderListEntityImpl(dataList, dataList.size());
		}

	}

	/**
	 * 详情查询
	 */
	@Publish
	protected class GetProduceOrderInfo extends OneKeyResultProvider<ProduceOrderInfo, GUID> {

		@Override
		protected ProduceOrderInfo provide(Context context, GUID id) throws Throwable {
			if(null==id)
			{
				throw new Throwable("参数：ID不能为空！");
			}
			ORMAccessor<ProduceOrderEntity> orm = context.newORMAccessor(orm_ProduceOrder);
			ORMAccessor<ProduceGoodsDetEntity> ormg = context.newORMAccessor(orm_ProduceGoodsDetByOrderId);
			ORMAccessor<ProduceMaterialDetEntity> ormm = context.newORMAccessor(orm_ProduceMaterialDetByOrderId);
			ProduceOrderEntity order = orm.findByRECID(id);
			List<ProduceGoodsDetEntity> gList = ormg.fetch(id);
			List<ProduceMaterialDetEntity> mList = ormm.fetch(id);
			orm.unuse();
			ormg.unuse();
			ormm.unuse();
			return ProduceOrderServiceUtil.getProduceOrderInfo(context,order,gList,mList);
		}

	}

	/**
	 * 完成商品记录查询
	 */
	@Publish
	protected class GetProduceOrderLogList extends OneKeyResultProvider<ProduceOrderGoodsLogListEntity, GetProduceOrderGoodsLogListKey> {

		@Override
		protected ProduceOrderGoodsLogListEntity provide(Context context, GetProduceOrderGoodsLogListKey key) throws Throwable {
			//TODO 
			if(null==key.getId())
			{
				throw new Throwable("参数：id不能为空！");
			}
			ORMAccessor<ProduceFinishedLogEntity> orm = context.newORMAccessor(orm_ProduceFinishedLogByOrderId);
			for(ProduceFinishedLogEntity e:orm.fetch(key.getId()))
			{
				
			}
			return null;
		}

	}

	public boolean checkValues(CreateProduceOrderTask task) {

		if(ProduceOrderStatus.Producing.equals(task.getStatus()))
		{
			if(null == task.getOnlineOrderIds() || task.getOnlineOrderIds().length < 1)
			{
				return false;
			}
//			double count = 0;
//			double lockCount = 0;
//			for(CreateProduceOrderTask.GoodsItem g:task.getGoods())
//			{
//				count = DoubleUtil.sum(count, g.getCount());
//				lockCount = DoubleUtil.sum(lockCount, g.getLockCount());
//			}
//			if(count == lockCount)
//			{
//				return true;
//			}
			return null == task.getOnlineOrderIds() || task.getOnlineOrderIds().length < 1 || null == task.getGoods()
			|| task.getGoods().length < 1;
		}
		else
		{
			if(null == task.getOnlineOrderIds() || task.getOnlineOrderIds().length < 1)
			{
				return false;
			}
			double count = 0;
			double lockCount = 0;
			for(CreateProduceOrderTask.GoodsItem g:task.getGoods())
			{
				count = DoubleUtil.sum(count, g.getCount());
				lockCount = DoubleUtil.sum(lockCount, g.getLockCount());
			}
			if(count == lockCount)
			{
				return true;
			}
			return null == task.getGoods()|| task.getGoods().length < 1 || null == task.getMaterials() || task.getMaterials().length < 1;
		}
	}

	public void insertLog(Context context, Item item, FinishTask task) {
		Employee emp = context.find(Employee.class, context.find(Login.class).getEmployeeId()); 
		ORMAccessor<ProduceFinishedLogEntity> orm = context.newORMAccessor(orm_ProduceFinishedLog);
		ORMAccessor<ProduceGoodsDetEntity> ormg = context.newORMAccessor(orm_ProduceGoodsDet);
		ProduceGoodsDetEntity g = ormg.findByRECID(item.getId());
		ProduceFinishedLogEntity e = new ProduceFinishedLogEntity();
		e.setBillsId(g.getBillsId());
		e.setBomId(g.getBomId());
		e.setCount(g.getCount());
		e.setCreateDate(System.currentTimeMillis());
		e.setCreator(emp.getName());
		e.setCreatorId(emp.getId());
		e.setGoodsCode(g.getGoodsCode());
		e.setGoodsNo(g.getGoodsNo());
		e.setGoodsSpec(g.getGoodsSpec());
		e.setGoodsId(g.getGoodsId());
		e.setGoodsName(g.getGoodsName());
		e.setId(context.newRECID());
		e.setUnit(g.getUnit());
		e.setThistimeCount(item.getCount());
		
		orm.insert(e);
		
		orm.unuse();
		ormg.unuse();
		
	}

	public void updateOnlineOrderStatus(Context context, CreateProduceOrderTask task) {
		UpdateOnlineOrderStatusTask t = new UpdateOnlineOrderStatusTask(task.getOnlineOrderIds());
		context.handle(t, UpdateOnlineOrderStatusTask.Method.Picking);
		
	}

	public void insertMaterialsDet(Context context, CreateProduceOrderTask task) throws Throwable {
		if(null==task.getMaterials()||task.getMaterials().length<1)
		{
			throw new Throwable("Materials不能为空！");
		}
		ORMAccessor<ProduceMaterialDetEntity> orm = context.newORMAccessor(orm_ProduceMaterialDet);
		ProduceMaterialDetEntity[] entities = new ProduceMaterialDetEntity[task.getMaterials().length];
		for(int i=0;i<task.getMaterials().length;i++)
		{
			CreateProduceOrderTask.MaterialsItem m = task.getMaterials()[i];
			ProduceMaterialDetEntity e = new ProduceMaterialDetEntity();
			e.setBillsId(task.getId());
			e.setCount(m.getCount());
			e.setId(GUID.randomID());
			e.setMaterialCode(m.getMaterialCode());
			e.setMaterialId(m.getMaterialId());
			e.setMaterialName(m.getMaterialName());
			e.setMaterialNo(m.getMaterialNo());
			e.setMaterialScale(m.getMaterialScale());
			e.setMaterialSpec(m.getMaterialSpec());
			e.setStoreId(m.getStoreId());
			e.setStoreName(m.getStoreName());
			e.setUnit(m.getUnit());
			
			entities[i]=e;
		}
		try
		{
			orm.insert(entities);
		}
		finally
		{
			orm.unuse();
		}
	}

	public void insertGoodsDet(Context context, CreateProduceOrderTask task) {
		ORMAccessor<ProduceGoodsDetEntity> orm = context.newORMAccessor(orm_ProduceGoodsDet);
		ProduceGoodsDetEntity[] entities = new ProduceGoodsDetEntity[task.getGoods().length];
		for(int i=0;i<task.getGoods().length;i++)
		{
			CreateProduceOrderTask.GoodsItem g = task.getGoods()[i];
			//TODO
//			if(g.getLockCount()>0)
//			{
//				ProduceOrderServiceUtil.modifyInventoryLockCount(context,g);
//			}
//			if(g.getCount()==g.getLockCount())
//			{
//				continue;
//			}
			ProduceGoodsDetEntity e = new ProduceGoodsDetEntity();
			e.setBillsId(task.getId());
			e.setBomId(g.getBomId());
			e.setCount(DoubleUtil.sub(g.getCount(),g.getLockCount()));
			e.setGoodsCode(g.getGoodsCode());
			e.setGoodsId(g.getGoodsId());
			e.setGoodsName(g.getGoodsName());
			e.setGoodsNo(g.getGoodsNo());
			e.setGoodsScale(g.getGoodsScale());
			e.setGoodsSpec(g.getGoodsSpec());
			e.setId(GUID.randomID());
			e.setUnit(g.getUnit());
			
			entities[i]=e;
		}
		try
		{
			orm.insert(entities);
		}
		finally
		{
			orm.unuse();
		}
		
	}

}
