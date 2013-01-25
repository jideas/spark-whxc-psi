package com.spark.deliverticket.intf.publish.service;

import java.util.List;

import com.spark.psi.publish.deliverticket.entity.DeliverTicketItem;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.deliverticket.intf.entity.DeliverTicketEntity;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.order.deliverticket.ORM_DeliverTicket;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketInfo;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketListEntity;
import com.spark.psi.publish.deliverticket.key.GetDeliverTicketListKey;
import com.spark.psi.publish.deliverticket.task.CreateDeliverTicketTask;
/**
 * 
 * 发货单publish service
 *
 */
public class DeliverTicketPublishService extends Service {

	final ORM_DeliverTicket orm_DeliverTicket;
	public DeliverTicketPublishService(ORM_DeliverTicket orm_DeliverTicket) {
		super("com.spark.deliverticket.intf.publish.service.DeliverTicketPublishService");
		this.orm_DeliverTicket = orm_DeliverTicket;
	}
	
	/**
	 * 创建
	 */
	@Publish
	protected class Create extends SimpleTaskMethodHandler<CreateDeliverTicketTask>
	{

		@Override
		protected void handle(Context context, CreateDeliverTicketTask t)
				throws Throwable {
			Employee emp = context.find(Employee.class, context.find(Login.class).getEmployeeId());
			GUID id = GUID.randomID();
			DeliverTicketEntity e = new DeliverTicketEntity();
			new BeanUtils().copyObject(t, e);
			e.setId(id);
			e.setCreateDate(System.currentTimeMillis());
			e.setCreator(emp.getName());
			e.setCreatorId(emp.getId());
			e.setSheetNo(context.find(String.class, SheetNumberType.DeliverTicket));
			ORMAccessor<DeliverTicketEntity> orm = context.newORMAccessor(orm_DeliverTicket);
			orm.insert(e);
			orm.unuse();
		}
		
	}
	
		
	/**
	 * 列表查询
	 */
	@Publish
	protected class GetDeliverTicketList extends OneKeyResultProvider<DeliverTicketListEntity, GetDeliverTicketListKey>
	{

		@Override
		protected DeliverTicketListEntity provide(Context context,
				GetDeliverTicketListKey key) throws Throwable {
			List<DeliverTicketItem> list = DeliverTickeServicetUtil.getDeliverTicketItemList(context,key);
			return new DeliverTicketListEntity(list,list.size());
		}
		
	}
	
	/**
	 * 详情查询
	 */
	@Publish
	protected class GetDeliverTicket extends OneKeyResultProvider<DeliverTicketInfo, GUID>
	{

		@Override
		protected DeliverTicketInfo provide(Context context,
				GUID id) throws Throwable {
			if(null==id)
			{
				throw new Throwable("id不能为空！");
			}
			ORMAccessor<DeliverTicketEntity> orm = context.newORMAccessor(orm_DeliverTicket);
			DeliverTicketEntity e = orm.findByRECID(id);
			if(e==null)
			{
				throw new Throwable("未知错误！");
			}
			return DeliverTickeServicetUtil.getDeliverInfo(context,e);
		}
		
	}

}
