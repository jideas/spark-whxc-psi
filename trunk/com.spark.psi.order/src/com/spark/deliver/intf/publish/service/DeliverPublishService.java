package com.spark.deliver.intf.publish.service;

import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.deliver.intf.entity.DeliverDetEntity;
import com.spark.deliver.intf.entity.DeliverEntity;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.order.deliver.ORM_Deliver;
import com.spark.psi.order.deliver.ORM_DeliverDet;
import com.spark.psi.order.deliver.ORM_DeliverDetByDeliverId;
import com.spark.psi.publish.DeliverStatus;
import com.spark.psi.publish.deliver.entity.DeliverInfo;
import com.spark.psi.publish.deliver.entity.DeliverItem;
import com.spark.psi.publish.deliver.entity.DeliverListEntity;
import com.spark.psi.publish.deliver.key.GetDeliverListKey;
import com.spark.psi.publish.deliver.task.CreateDeliverTask;
import com.spark.psi.publish.deliver.task.UpdateDeliverStatausTask;
import com.spark.psi.publish.onlineorder.task.UpdateOnlineOrderStatusTask;
/**
 * 配送单publishService
 *
 */
public class DeliverPublishService extends Service {

	final ORM_Deliver orm_Deliver;
	final ORM_DeliverDet orm_DeliverDet;
	final ORM_DeliverDetByDeliverId orm_DeliverDetByDeliverId;
	final static String deliverTable = ERPTableNames.Sales.Deliver_Sheet.getTableName();
	public DeliverPublishService(
			ORM_Deliver orm_Deliver,                          
			ORM_DeliverDet orm_DeliverDet,                     
			ORM_DeliverDetByDeliverId orm_DeliverDetByDeliverId) {
		super("com.spark.deliver.intf.publish.service.DeliverPublishService");
		this.orm_Deliver = orm_Deliver;
		this.orm_DeliverDet = orm_DeliverDet;
		this.orm_DeliverDetByDeliverId = orm_DeliverDetByDeliverId;
	}
	
	/**
	 * 创建
	 */
	@Publish
	protected class CreateDeliver extends SimpleTaskMethodHandler<CreateDeliverTask>
	{

		@Override
		protected void handle(Context context, CreateDeliverTask t)
				throws Throwable {
			if(null==t.getItems()||t.getItems().length<1)
			{
				throw new Throwable("items不能为空!");
			}
			Login login = context.find(Login.class);
			DeliverEntity d = new DeliverEntity();
			GUID id = GUID.randomID();
			d.setAddress(t.getAddress());
			d.setCreateDate(System.currentTimeMillis());
			d.setCreator(context.find(Employee.class, login.getEmployeeId()).getName());
			d.setCreatorId(login.getEmployeeId());
			d.setDeliveredPackageCount(t.getDeliveredPackageCount());
			d.setSheetNo(context.find(String.class, SheetNumberType.Deliver));
			d.setStationId(t.getStationId());
			d.setStationName(t.getStationName());
			d.setStatus(DeliverStatus.Deliver.getCode());
			d.setRemark(t.getRemark());
			d.setId(id);
			d.setPlanDate(t.getPlanDate());
			d.setDeliverPerson(t.getDeliverPerson());
			t.setId(id);
			
			ORMAccessor<DeliverEntity> orm = context.newORMAccessor(orm_Deliver);
			ORMAccessor<DeliverDetEntity> ormd = context.newORMAccessor(orm_DeliverDet);
			DeliverDetEntity[] dets = new DeliverDetEntity[t.getItems().length];
			GUID[] onlineOrderIds = new GUID[t.getItems().length];
			try
			{
				for(int i=0;i<t.getItems().length;i++)
				{
					CreateDeliverTask.Item item = t.getItems()[i];
					if(null==item.getOnlineOrderId())
					{
						throw new Throwable("明细onlineOrderId不能为空！");
					}
					DeliverDetEntity det = new DeliverDetEntity();
					det.setId(GUID.randomID());
					det.setDeliverSheetId(id);
					det.setMemberRealName(item.getMemberRealName());
					det.setOnlineOrderId(item.getOnlineOrderId());
					onlineOrderIds[i] = item.getOnlineOrderId();
					det.setOnlineOrderNo(item.getOnlineOrderNo());
					det.setOrderAmount(item.getTotalAmount());
					dets[i] = det;
					
				}
				orm.insert(d);
				ormd.insert(dets);
				DeliverServiceUtil.insertDeliverTicket(context,t);
				
				UpdateOnlineOrderStatusTask u = new UpdateOnlineOrderStatusTask(onlineOrderIds);
				context.handle(u, UpdateOnlineOrderStatusTask.Method.Deliver);
				
			}
			finally
			{
				orm.unuse();
				ormd.unuse();
			}
			
		}
		
	}
	
	
	/**
	 * 配送
	 */
	@Publish
	protected class Deliver extends TaskMethodHandler<UpdateDeliverStatausTask, UpdateDeliverStatausTask.Method>
	{

		protected Deliver() {
			super(UpdateDeliverStatausTask.Method.Deliver);
			
		}

		@Override
		protected void handle(Context context, UpdateDeliverStatausTask task)
				throws Throwable {
			if(null==task.getId()||task.getPackageCount()<=0)
			{
				throw new Throwable("id||包装箱数不能为空！");
			}
			DeliverInfo info = context.find(DeliverInfo.class, task.getId());
			StringBuffer sql = new StringBuffer();
			sql.append("define update deliver(@id guid,@deliverDate date)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(deliverTable);
			sql.append(" as t\n");
			sql.append("set status='").append(DeliverStatus.Delivering.getCode()).append("'\n");
			sql.append(" ,deliveredPackageCount=").append(task.getPackageCount()).append("\n");
			sql.append(" ,deliverDate=@deliverDate\n");
			
			sql.append(" where t.recid=@id\n");
			sql.append(" and t.status='").append(DeliverStatus.Deliver.getCode()).append("'\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			try
			{
				db.setArgumentValues(task.getId(),System.currentTimeMillis());
				if(db.executeUpdate()<1)
				{
					throw new Throwable("有其他用户在做同样的操作，请检查！");
				}
				DeliverServiceUtil.insertCheckOutSheet(context,info);
			}
			finally
			{
				db.unuse();
			}
		}
		
	}
	
	/**
	 * 到货
	 */
	@Publish
	protected class Arrive extends TaskMethodHandler<UpdateDeliverStatausTask, UpdateDeliverStatausTask.Method>
	{

		protected Arrive() {
			super(UpdateDeliverStatausTask.Method.Arrive);
			
		}

		@Override
		protected void handle(Context context, UpdateDeliverStatausTask task)
				throws Throwable {
			if(null==task.getId())
			{
				throw new Throwable("id不能为空！");
			}
			Employee emp = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			StringBuffer sql = new StringBuffer();
			sql.append("define update deliver(@id guid,@consigneeId guid,@consignee string,@consigneeDate date)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(deliverTable);
			sql.append(" as t\n");
			sql.append("set status='").append(DeliverStatus.Arrived.getCode()).append("'\n");
			sql.append(",consigneeId=@consigneeId,consignee=@consignee,consigneeDate=@consigneeDate\n");
			
			sql.append(" where t.recid=@id\n");
			sql.append(" and t.status='").append(DeliverStatus.Delivering.getCode()).append("'\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			try
			{
				db.setArgumentValues(task.getId(),emp.getId(),emp.getName(),System.currentTimeMillis());
				if(db.executeUpdate()<1)
				{
					throw new Throwable("有其他用户在做同样的操作，请检查！");
				}
				ORMAccessor<DeliverDetEntity> orm = context.newORMAccessor(orm_DeliverDetByDeliverId);
				List<DeliverDetEntity> dets = orm.fetch(task.getId());
				if(null==dets||dets.size()<1)
				{
					throw new Throwable("未知错误！");
				}
				GUID[] ids = new GUID[dets.size()];
				for(int i=0;i<dets.size();i++)
				{
					DeliverDetEntity det = dets.get(i);
					if(null==det.getOnlineOrderId())
					{
						throw new Throwable("未知错误！");
					}
					ids[i] = det.getOnlineOrderId();
				}
				UpdateOnlineOrderStatusTask u = new UpdateOnlineOrderStatusTask(ids);
				context.handle(u, UpdateOnlineOrderStatusTask.Method.Arrival);
			}
			finally
			{
				db.unuse();
			}
			
		}
		
	}
	
	/**
	 * 异常
	 */
	@Publish
	protected class Exception extends TaskMethodHandler<UpdateDeliverStatausTask, UpdateDeliverStatausTask.Method>
	{

		protected Exception() {
			super(UpdateDeliverStatausTask.Method.Exception);
		}

		@Override
		protected void handle(Context context, UpdateDeliverStatausTask task)
				throws Throwable {
			if(null==task.getId())
			{
				throw new Throwable("id，不能为空！");
			}
			if(CheckIsNull.isEmpty(task.getDescription())||CheckIsNull.isEmpty(task.getReceivedPackageCount()))
			{
				throw new Throwable("异常情况||到货箱数，不能为空！");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update deliver(@id guid,@receivedPackageCount int,@description string)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(deliverTable);
			sql.append(" as t\n");
			sql.append("set status='").append(DeliverStatus.Exception.getCode()).append("'\n");
			sql.append(",receivedPackageCount=@receivedPackageCount\n");
			sql.append(",description=@description\n");
			sql.append(" where t.recid=@id\n");
			sql.append(" and t.status='").append(DeliverStatus.Delivering.getCode()).append("'\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			try
			{
				db.setArgumentValues(task.getId(),task.getReceivedPackageCount(),task.getDescription());
				if(db.executeUpdate()<1)
				{
					throw new Throwable("有其他用户在做同样的操作，请检查！");
				}
			}
			finally
			{
				db.unuse();
			}
			
		}
		
	}
	
	/**
	 * 处理
	 */
	@Publish
	protected class Handle extends TaskMethodHandler<UpdateDeliverStatausTask, UpdateDeliverStatausTask.Method>
	{

		protected Handle() {
			super(UpdateDeliverStatausTask.Method.Handle);
		}

		@Override
		protected void handle(Context context, UpdateDeliverStatausTask task)
				throws Throwable {
			if(null==task.getId())
			{
				throw new Throwable("id，不能为空！");
			}
			if(CheckIsNull.isEmpty(task.getFormula()))
			{
				throw new Throwable("处理方案，不能为空！");
			}
			StringBuffer sql = new StringBuffer();
			sql.append("define update deliver(@id guid,@formula string)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(deliverTable);
			sql.append(" as t\n");
			sql.append("set status='").append(DeliverStatus.Handled.getCode()).append("'\n");
			sql.append(",formula=@formula\n");
			sql.append(" where t.recid=@id\n");
			sql.append(" and t.status='").append(DeliverStatus.Exception.getCode()).append("'\n");
			sql.append("end");
			
			DBCommand db = context.prepareStatement(sql);
			try
			{
				db.setArgumentValues(task.getId(),task.getFormula());
				if(db.executeUpdate()<1)
				{
					throw new Throwable("有其他用户在做同样的操作，请检查！");
				}
			}
			finally
			{
				db.unuse();
			}
			
		}
		
	}
	
	/**
	 * 列表查询
	 */
	@Publish
	protected class GetDeliverList extends OneKeyResultProvider<DeliverListEntity, GetDeliverListKey>
	{

		@Override
		protected DeliverListEntity provide(Context context, GetDeliverListKey key) throws Throwable {
			if(null==key.getStatus()||key.getStatus().length<1)
			{
				throw new Throwable("status不能为空！");
			}
			List<DeliverItem> list = DeliverServiceUtil.getDeliverItemList(context,key);
			return new DeliverListEntity(list,list.size());
		}
		
	}
	
	/**
	 * 详情查询
	 */
	@Publish
	protected class GetDeliverInfo extends OneKeyResultProvider<DeliverInfo, GUID>
	{

		@Override
		protected DeliverInfo provide(Context context, GUID id)
				throws Throwable {
			if(null==id)
			{
				throw new Throwable("id不能为空！");
			}
			ORMAccessor<DeliverEntity> orm = context.newORMAccessor(orm_Deliver);
			ORMAccessor<DeliverDetEntity> ormd = context.newORMAccessor(orm_DeliverDetByDeliverId);
			DeliverEntity e = orm.findByRECID(id);
			if(null==e)
			{
				throw new Throwable("未知错误！");
			}
			List<DeliverDetEntity> dets = ormd.fetch(id);
			return DeliverServiceUtil.getDeliverInfo(context,e,dets);
		}
		
	}
}
