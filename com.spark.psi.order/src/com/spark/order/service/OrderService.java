package com.spark.order.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.SelectOrderKey;
import com.spark.order.intf.entity.OrderFather;
import com.spark.order.util.dnaSql.DnaSql_update;
import com.spark.order.util.dnaSql.IDnaSql;

/**
 * 订单服务Service
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author modi
 * @version 2012-4-24
 */
public class OrderService extends Service {

	protected OrderService() {
		super("com.spark.order.service.OrderService");
	}

	/**
	 * 订单列表查询
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author modi
	 * @version 2012-4-24
	 */
	@Publish
	class SelectOrderListProvider extends
			OneKeyResultListProvider<OrderFather, SelectOrderKey> {

		@Override
		protected void provide(Context context, SelectOrderKey key,
				List<OrderFather> resultList) throws Throwable {
			SelectOrderSql sql = new SelectOrderSql(context, key);
			RecordSet rs = sql.executeQuery();
			resultList.addAll(getOrderImpls(rs));
		}

		private List<OrderFather> getOrderImpls(RecordSet rs) {
			List<OrderFather> list = new ArrayList<OrderFather>();
			OrderFather order = null;
			int index;
			while (rs.next()) {
				order = new OrderFather();
				index = 0;
				// t.'deliveryDate' as 'deliveryDate',
				// t.'contactId' as 'contactId',
				// t.'contactName' as 'contactName',
				// t.'contactPhone' as 'contactPhone',
				// t.'contactTel' as 'contactTel',
				order.setDeliveryDate(rs.getFields().get(index++).getDate());
				order.setContactId(rs.getFields().get(index++).getGUID());
				order.setContactName(rs.getFields().get(index++).getString());
				order.setContactPhone(rs.getFields().get(index++).getString());
				order.setContactTel(rs.getFields().get(index++).getString());
				// t.'defEight' as 'defEight',
				// t.'defFive' as 'defFive',
				// t.'defFour' as 'defFour',
				// t.'defNine' as 'defNine',
				// t.'defOne' as 'defOne',
				order.setDefEight(rs.getFields().get(index++).getDate());
				order.setDefFive(rs.getFields().get(index++).getDouble());
				order.setDefFour(rs.getFields().get(index++).getDouble());
				order.setDefNine(rs.getFields().get(index++).getString());
				order.setDefOne(rs.getFields().get(index++).getString());
				// t.'defSeven' as 'defSeven',
				// t.'defSix' as 'defSix',
				// t.'defTen' as 'defTen',
				// t.'defThree' as 'defThree',
				// t.'defTwo' as 'defTwo',
				order.setDefSeven(rs.getFields().get(index++).getDate());
				order.setDefSix(rs.getFields().get(index++).getDouble());
				order.setDefTen(rs.getFields().get(index++).getString());
				order.setDefThree(rs.getFields().get(index++).getString());
				order.setDefTwo(rs.getFields().get(index++).getString());
				// t.'deptId' as 'deptId',
				// t.'effectiveDate' as 'effectiveDate',
				// t.'examin' as 'examin',
				// t.'isStoped' as 'isStoped',
				// t.'orderNumber' as 'orderNumber',
				order.setDeptId(rs.getFields().get(index++).getGUID());
				order.setEffectiveDate(rs.getFields().get(index++).getDate());
				order.setExamin(rs.getFields().get(index++).getString());
				order.setStoped(rs.getFields().get(index++).getBoolean());
				order.setOrderNumber(rs.getFields().get(index++).getString());
				// t.'partnerFax' as 'partnerFax',
				// t.'partnerId' as 'partnerId',
				// t.'partnerName' as 'partnerName',
				// t.'partnerNamePY' as 'partnerNamePY',
				// t.'partnerShortName' as 'partnerShortName',
				order.setPartnerFax(rs.getFields().get(index++).getString());
				order.setPartnerId(rs.getFields().get(index++).getGUID());
				order.setPartnerName(rs.getFields().get(index++).getString());
				// order.setPartnerNamePY(null);
				index++;
				order.setPartnerShortName(rs.getFields().get(index++)
						.getString());
				// t.'remark' as 'remark',
				// t.'returnCause' as 'returnCause',
				// t.'status' as 'status',
				// t.'totalAmount' as 'totalAmount',
				// t.'type' as 'type',
				order.setRemark(rs.getFields().get(index++).getString());
				order.setReturnCause(rs.getFields().get(index++).getString());
				order.setStatus(rs.getFields().get(index++).getString());
				order.setTotalAmount(rs.getFields().get(index++).getDouble());
				order.setType(rs.getFields().get(index++).getString());
				// t.'createDate' as 'createDate',
				// t.'creator' as 'creator',
				// t.'creatorId' as 'creatorId',
				// t.'RECID' as 'recid',
				// t.'tenantsId' as 'tenantsId'
				order.setCreateDate(rs.getFields().get(index++).getDate());
				order.setCreator(rs.getFields().get(index++).getString());
				order.setCreatorId(rs.getFields().get(index++).getGUID());
				order.setRecid(rs.getFields().get(index++).getGUID());
				order.setTenantsId(rs.getFields().get(index++).getGUID());
				list.add(order);
			}
			return list;
		}
	}
	
	@Publish
	class UpdateOrderStatusHandler extends SimpleTaskMethodHandler<OrderStatusTask2>{
		@Override
		protected void handle(Context context, OrderStatusTask2 task)
				throws Throwable {
			IDnaSql sql = new UpdateOrderStatusSql(context, task);
			task.setLenght(sql.executeUpdate());
			task.setSucceed(task.lenght() == 1);
		}
		class UpdateOrderStatusSql extends DnaSql_update{
			private final OrderStatusTask2 task;

			public UpdateOrderStatusSql(Context context, OrderStatusTask2 task) {
				super(context);
				this.task = task; 
			}

			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(task.getOrderEnum().getDb_table());
				sql.append(" as t ");
				sql.append(" set ");
				sql.append(" status = @status ");
				this.addParam("@status string", task.getNewstatus());
				if(null != task.getCause()){
					sql.append(" , ");
					sql.append(" returnCause = @cause ");
					this.addParam("@cause string", task.getCause());
				}
				if(null != task.getDeptId()){
					sql.append(" , ");
					sql.append(" deptId = @deptId ");
					this.addParam("@deptId guid", task.getDeptId());
				}
				if(null != task.getCause() && OrderEnum.Sales == task.getOrderEnum()){
					sql.append(" , ");
					sql.append(" examDeptId = @examDeptId ");
					this.addParam("@examDeptId guid", task.getExamineDeptId());
				}
				sql.append(" where ");
				sql.append(" t.recid = @id ");
				this.addParam("@id guid", task.getId());
				sql.append(" and ");
				sql.append(" t.state = @oldstatus ");
				this.addParam("@oldstatus string", task.getOldstatus());
				return sql.toString();
			}
		}
	}
	
	@Publish
	class StopOrderHandler extends TaskMethodHandler<StopOrderTask2, StopOrderTask2.Method>{
		protected StopOrderHandler() {
			super(StopOrderTask2.Method.Stop);
		}
		@Override
		protected void handle(Context context, StopOrderTask2 task)
		throws Throwable {
			IDnaSql sql = new StopOrderStatusSql(context, task);
			task.setLenght(sql.executeUpdate());
			task.setSucceed(task.lenght() == 1);
		}
		private class StopOrderStatusSql extends DnaSql_update{
			private final StopOrderTask2 task;
			
			public StopOrderStatusSql(Context context, StopOrderTask2 task) {
				super(context);
				this.task = task; 
			}
			
			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(task.getOrderEnum().getDb_table());
				sql.append(" as t ");
				sql.append(" set ");
				sql.append(" isStoped = @isStop, ");
				this.addParam("@isStop boolean", true);
				sql.append(" returnCause = @cause ");
				this.addParam("@cause string", task.getCause());
				sql.append(" where ");
				sql.append(" t.recid = @id ");
				this.addParam("@id guid", task.getId());
				sql.append(" and ");
				sql.append(" t.isStoped = @oldStop ");
				this.addParam("@oldStop boolean", false);
				return sql.toString();
			}
		}
	}
	@Publish
	class ExecutOrderHandler extends TaskMethodHandler<StopOrderTask2, StopOrderTask2.Method>{
		protected ExecutOrderHandler() {
			super(StopOrderTask2.Method.Execut);
		}
		@Override
		protected void handle(Context context, StopOrderTask2 task)
		throws Throwable {
			IDnaSql sql = new StopOrderStatusSql(context, task);
			task.setLenght(sql.executeUpdate());
			task.setSucceed(task.lenght() == 1);
		}
		private class StopOrderStatusSql extends DnaSql_update{
			private final StopOrderTask2 task;
			
			public StopOrderStatusSql(Context context, StopOrderTask2 task) {
				super(context);
				this.task = task; 
			}
			
			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(task.getOrderEnum().getDb_table());
				sql.append(" as t ");
				sql.append(" set ");
				sql.append(" isStoped = @isStop ");
				this.addParam("@isStop boolean", false);
				sql.append(" where ");
				sql.append(" t.recid = @id ");
				this.addParam("@id guid", task.getId());
				sql.append(" and ");
				sql.append(" t.isStoped = @oldStop ");
				this.addParam("@oldStop boolean", true);
				return sql.toString();
			}
		}
	}
}
