/**
 * 
 */
package com.spark.psi.report.queue;

import java.util.Date;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.spark.common.utils.date.DateUtil;
import com.spark.order.intf.event.ChangedType;
import com.spark.order.intf.event.PromotionOrderChangedEvent;
import com.spark.order.intf.event.PurchaseOrderChangedEvent;
import com.spark.order.intf.event.PurchaseReturnChangedEvent;
import com.spark.order.intf.event.SalesOrderChangedEvent;
import com.spark.order.intf.event.SalesReturnChangedEvent;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.psi.base.Employee;
import com.spark.psi.message.entity.SMessageInfo;
import com.spark.psi.message.entity.SMessageMapping;
import com.spark.psi.message.task.SMessageDelTask;
import com.spark.psi.message.task.SMessageInfoTask;
import com.spark.psi.message.task.SMessageMappingTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.SMessageTemplateEnum;
import com.spark.psi.publish.SMessageType;
import com.spark.psi.publish.deliver.Event.DeliverExceptionCreateEvent;
import com.spark.psi.publish.deliver.Event.DeliverExceptionOverEvent;
import com.spark.psi.report.entity.ReportQueue;
import com.spark.psi.report.utils.SMessageUtils;

/**
 * 订单模块数据提取
 */
public class OrderTaskHandle {
	// 促销
	protected static void handle(Context context, PromotionOrderChangedEvent event, ReportQueue rq) {
	}

	// 配送
	protected static void handle(Context context, DeliverExceptionCreateEvent event, ReportQueue rq) {
		context.handle(new SMessageDelTask(SMessageType.DeliveryException, event.getId()));
		SMessageInfo info = new SMessageInfo();
		info.setMessageType(SMessageType.DeliveryException.getCode());
		info.setRECID(context.newRECID());
		info.setRelaObjId(event.getId());
		info.setTemplateCode(SMessageTemplateEnum.DeliveryException01.getCode());
		info.setStringValue1(event.getBillNo());
		context.handle(new SMessageInfoTask(info));
		Set<Employee> set = SMessageUtils.getEmployeeByAuths(context, null, Auth.Boss, Auth.Distribute);
		for (Employee e : set) {
			SMessageMapping mapping = new SMessageMapping();
			mapping.setMessageId(info.getRECID());
			mapping.setMessageType(info.getMessageType());
			mapping.setRECID(context.newRECID());
			mapping.setUserId(e.getId());
			context.handle(new SMessageMappingTask(mapping));
		}
	}

	// 配送
	protected static void handle(Context context, DeliverExceptionOverEvent event, ReportQueue rq) {
		context.handle(new SMessageDelTask(SMessageType.DeliveryException, event.getId()));
	}

	// 销售退货@@@@@@@@@
	protected static void handle(Context context, SalesReturnChangedEvent event, ReportQueue rq) {
		SaleCancel retail = context.find(SaleCancel.class, event.getId());
		if (ChangedType.SAVE.equals(event.getType()) || ChangedType.Deny.equals(event.getType())
				|| ChangedType.Delete.equals(event.getType())) {
			if (retail == null) {
				return;
			}
		}
		/**
		 * 消息提醒数据
		 */
		// 删除待审批单据提醒
		context.handle(new SMessageDelTask(SMessageType.UnapproveOrders, retail.getRECID()));
		if (StatusEnum.Approve.getKey().equals(retail.getStatus())) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.UnapproveOrders.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getId());
			info.setTemplateCode(SMessageTemplateEnum.UnapproveOrders04.getCode());
			info.setStringValue1(retail.getBillsNo());
			context.handle(new SMessageInfoTask(info));
			Employee emp = context.find(Employee.class, retail.getCreatorId());
			Set<Employee> set = SMessageUtils.getMyManager(context, emp, false, Auth.Boss, Auth.SalesManager);
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setRECID(context.newRECID());
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	// 采购退货@@@@@@@@@
	protected static void handle(Context context, PurchaseReturnChangedEvent event, ReportQueue rq) {
		PurchaseCancel retail = context.find(PurchaseCancel.class, event.getId());
		if (ChangedType.SAVE.equals(event.getType()) || ChangedType.Deny.equals(event.getType())
				|| ChangedType.Delete.equals(event.getType())) {
			if (retail == null) {
				return;
			}
		}
		/**
		 * 消息提醒数据
		 */
		// 删除待审批单据提醒
		context.handle(new SMessageDelTask(SMessageType.UnapproveOrders, retail.getRECID()));
		if (StatusEnum.Approve.getKey().equals(retail.getStatus())) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.UnapproveOrders.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getId());
			info.setTemplateCode(SMessageTemplateEnum.UnapproveOrders03.getCode());
			info.setStringValue1(retail.getBillsNo());
			context.handle(new SMessageInfoTask(info));
			Employee emp = context.find(Employee.class, retail.getCreatorId());
			Set<Employee> set = SMessageUtils.getMyManager(context, emp, false, Auth.Boss, Auth.PurchaseManager);
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setRECID(context.newRECID());
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	// 采购订单@@@@@@@@@@
	protected static void handle(Context context, PurchaseOrderChangedEvent event, ReportQueue rq) {
		Date date = new Date(rq.getCurrTime());
		PurchaseOrderInfo retail = context.find(PurchaseOrderInfo.class, event.getId());
		if (ChangedType.SAVE.equals(event.getType()) || ChangedType.Deny.equals(event.getType())
				|| ChangedType.Delete.equals(event.getType())) {
			if (retail == null) {
				return;
			}
		}
		Employee creator = context.find(Employee.class, retail.getCreatorId());
		/**
		 * 消息提醒
		 */
		// 交货日期（销售采购订货）临近预警
		context.handle(new SMessageDelTask(SMessageType.NearOrderDate, event.getId()));
		long between = DateUtil.getDaysBetween(date, new Date(retail.getDeliveryDate()));
		if (!retail.getStatus().equals(StatusEnum.Finished.getKey())
				&& !retail.getStatus().equals(StatusEnum.Store_All.getKey()) && !retail.isStoped() && between <= 3) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.NearOrderDate.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getId());
			info.setStringValue1(retail.getBillsNo());
			if (between >= 0 && between <= 3) {
				info.setTemplateCode(SMessageTemplateEnum.NearOrderDate01.getCode());
			} else if (between < 0) {
				info.setTemplateCode(SMessageTemplateEnum.NearOrderDate02.getCode());
			}
			context.handle(new SMessageInfoTask(info));
			SMessageMapping mapping = new SMessageMapping();
			mapping.setMessageId(info.getRECID());
			mapping.setMessageType(info.getMessageType());
			mapping.setRECID(context.newRECID());
			mapping.setUserId(creator.getId());
			context.handle(new SMessageMappingTask(mapping));
		}
		context.handle(new SMessageDelTask(SMessageType.UnapproveOrders, event.getId()));
		if (retail.getStatus().equals(StatusEnum.Approve.getKey())) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.UnapproveOrders.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getId());
			info.setStringValue1(retail.getBillsNo());
			info.setTemplateCode(SMessageTemplateEnum.UnapproveOrders01.getCode());
			context.handle(new SMessageInfoTask(info));
			Set<Employee> set = SMessageUtils.getMyManager(context, creator, false, Auth.Boss, Auth.PurchaseManager);
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setRECID(context.newRECID());
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	// 销售订单@@@@@@@@@
	protected static void handle(Context context, SalesOrderChangedEvent event, ReportQueue rq) {
		Date date = new Date(rq.getCurrTime());
		SaleOrderInfo retail = context.find(SaleOrderInfo.class, event.getId());
		if (ChangedType.SAVE.equals(event.getType()) || ChangedType.Deny.equals(event.getType())
				|| ChangedType.Delete.equals(event.getType())) {
			if (retail == null) {
				return;
			}
		}
		Employee creator = context.find(Employee.class, retail.getCreatorId());
		/**
		 * 消息提醒
		 */
		context.handle(new SMessageDelTask(SMessageType.NearOrderDate, event.getId()));
		long between = DateUtil.getDaysBetween(date, new Date(retail.getDeliveryDate()));
		if (!retail.getStatus().equals(StatusEnum.Finished.getKey())
				&& !retail.getStatus().equals(StatusEnum.Store_All.getKey()) && !retail.isStoped() && between <= 3) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.NearOrderDate.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getId());
			info.setStringValue1(retail.getBillsNo());
			if (between < 0) {
				info.setTemplateCode(SMessageTemplateEnum.NearOrderDate04.getCode());
			} else {
				info.setTemplateCode(SMessageTemplateEnum.NearOrderDate03.getCode());
			}
			context.handle(new SMessageInfoTask(info));
			SMessageMapping mapping = new SMessageMapping();
			mapping.setMessageId(info.getRECID());
			mapping.setMessageType(info.getMessageType());
			mapping.setRECID(context.newRECID());
			mapping.setUserId(creator.getId());
			context.handle(new SMessageMappingTask(mapping));

		}
		context.handle(new SMessageDelTask(SMessageType.UnapproveOrders, event.getId()));
		if (retail.getStatus().equals(StatusEnum.Approve.getKey())) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.UnapproveOrders.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getId());
			info.setStringValue1(retail.getBillsNo());
			info.setTemplateCode(SMessageTemplateEnum.UnapproveOrders02.getCode());
			context.handle(new SMessageInfoTask(info));
			Set<Employee> set = SMessageUtils.getSalesExamor(context, creator);
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setRECID(context.newRECID());
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}

		// 完成配货
		// 销售配货提醒
		context.handle(new SMessageDelTask(SMessageType.UndistributeOrders, event.getId()));
		if (!retail.isAllot() && retail.getStatus().equals(StatusEnum.Store_N0.getKey())) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.UndistributeOrders.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getId());
			info.setStringValue1(retail.getBillsNo());
			info.setTemplateCode(SMessageTemplateEnum.UndistributeOrders01.getCode());
			context.handle(new SMessageInfoTask(info));
			Set<Employee> set = SMessageUtils.getEmployeeByAuths(context, rq.getTenantsGuid(), Auth.Distribute);
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setRECID(context.newRECID());
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

}
