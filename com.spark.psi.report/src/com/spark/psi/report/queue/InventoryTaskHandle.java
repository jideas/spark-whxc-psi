/**
 * 
 */
package com.spark.psi.report.queue;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Store;
import com.spark.psi.base.key.GetInventoryByStockIdKey;
import com.spark.psi.inventory.internal.entity.AllocateInventory;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.event.CheckInEvent;
import com.spark.psi.inventory.intf.event.CheckInSheetStatusChanageEvent;
import com.spark.psi.inventory.intf.event.CheckOutEvent;
import com.spark.psi.inventory.intf.event.CheckOutSheetStatusChanageEvent;
import com.spark.psi.inventory.intf.event.CheckingInEvent;
import com.spark.psi.inventory.intf.event.CheckingOutEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateApprovalEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateDenyEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateSubmitted;
import com.spark.psi.inventory.intf.event.InventoryLogEvent;
import com.spark.psi.message.entity.SMessageInfo;
import com.spark.psi.message.entity.SMessageMapping;
import com.spark.psi.message.task.SMessageDelTask;
import com.spark.psi.message.task.SMessageInfoTask;
import com.spark.psi.message.task.SMessageMappingTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.SMessageTemplateEnum;
import com.spark.psi.publish.SMessageType;
import com.spark.psi.publish.inventory.entity.CheckingInInfo;
import com.spark.psi.publish.inventory.entity.CheckingOutInfo;
import com.spark.psi.report.dao.task.ReportInventoryTask;
import com.spark.psi.report.entity.ReportQueue;
import com.spark.psi.report.utils.SMessageUtils;

/**
 * 库存模块数据提取
 */
public class InventoryTaskHandle {
	public static void handle(Context context, InventoryAllocateDenyEvent event, ReportQueue rq) {
		context.handle(new SMessageDelTask(SMessageType.UnapproveOrders, event.getId()));
	}

	/**
	 * 调拨单审批通过
	 */
	public static void handle(Context context, InventoryAllocateApprovalEvent event, ReportQueue rq) {
		context.handle(new SMessageDelTask(SMessageType.UnapproveOrders, event.getId()));
		AllocateInventory entity = context.find(AllocateInventory.class, event.getId());
		Set<Employee> set = new HashSet<Employee>();
		if (entity.getApprovePersonId() == null) {
			Store instore = context.find(Store.class, entity.getAllocateInStoreId());
			set.addAll(SMessageUtils.getStoreManagers(context, instore));
			Store outstore = context.find(Store.class, entity.getAllocateOutStoreId());
			set.addAll(SMessageUtils.getStoreManagers(context, outstore));
		}
		if (!set.isEmpty()) {
			SMessageInfo info = new SMessageInfo();
			info.setRECID(context.newRECID());
			info.setMessageType(SMessageType.UnapproveOrders.getCode());
			info.setRelaObjId(entity.getId());
			info.setTemplateCode(SMessageTemplateEnum.UnapproveOrders06.getCode());
			info.setStringValue1(entity.getAllocSheetNo());
			context.handle(new SMessageInfoTask(info));
			long startTime = new Date().getTime();
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setRECID(context.newRECID());
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setStartTime(startTime);
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	// 调拨单待审批事件
	protected static void handle(Context context, InventoryAllocateSubmitted event, ReportQueue rq) {
		/**
		 * 新增调拨单待审批提醒
		 */
		AllocateInventory entity = context.find(AllocateInventory.class, event.getInventoryAllocateSheetId());
		Set<Employee> set = new HashSet<Employee>();
		if (entity.getApprovePersonId() == null) {
			Store instore = context.find(Store.class, entity.getAllocateInStoreId());
			set.addAll(SMessageUtils.getStoreManagers(context, instore));
			Store outstore = context.find(Store.class, entity.getAllocateOutStoreId());
			set.addAll(SMessageUtils.getStoreManagers(context, outstore));
		}
		if (!set.isEmpty()) {
			SMessageInfo info = new SMessageInfo();
			info.setRECID(context.newRECID());
			info.setMessageType(SMessageType.UnapproveOrders.getCode());
			info.setRelaObjId(entity.getId());
			info.setTemplateCode(SMessageTemplateEnum.UnapproveOrders06.getCode());
			info.setStringValue1(entity.getAllocSheetNo());
			context.handle(new SMessageInfoTask(info));
			long startTime = new Date().getTime();
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setRECID(context.newRECID());
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setStartTime(startTime);
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	// 入库单事件：@@@@
	// 中止、重新执行（入库单ID）
	protected static void handle(Context context, CheckInSheetStatusChanageEvent event, ReportQueue rq) {
		/**
		 * 更新待确认入库预警
		 */
		context.handle(new SMessageDelTask(SMessageType.UncheckinOrders, event.getCheckInSheetId()));
		CheckingInInfo sheet = context.find(CheckingInInfo.class, event.getCheckInSheetId());
		if (sheet.getStatus() == CheckingInStatus.Part || sheet.getStatus() == CheckingInStatus.None) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.UncheckinOrders.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getCheckInSheetId());
			if (CheckingInType.Return == sheet.getType()) {
				info.setTemplateCode(SMessageTemplateEnum.UncheckinOrders02.getCode());
			} else {
				info.setTemplateCode(SMessageTemplateEnum.UncheckinOrders01.getCode());
			}
			context.handle(new SMessageInfoTask(info));
			Set<Employee> set = SMessageUtils.getStoreManagers(context, sheet.getStoreId());
			long startTime = new Date().getTime();
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setRECID(context.newRECID());
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setStartTime(startTime);
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	// 出库单事件： @@@@
	// 中止、重新执行（出库单ID）
	protected static void handle(Context context, CheckOutSheetStatusChanageEvent event, ReportQueue rq) {
		/**
		 * 更新待确认出库预警
		 */
		context.handle(new SMessageDelTask(SMessageType.UncheckoutOrders, event.getCheckOutSheetId()));
		CheckingOutInfo sheet = context.find(CheckingOutInfo.class, event.getCheckOutSheetId());
		if (sheet.getStatus() == CheckingOutStatus.Part || sheet.getStatus() == CheckingOutStatus.None) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.UncheckoutOrders.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getCheckOutSheetId());
			if (CheckingOutType.Return == sheet.getType()) {
				info.setTemplateCode(SMessageTemplateEnum.UncheckoutOrders02.getCode());
			} else {
				info.setTemplateCode(SMessageTemplateEnum.UncheckoutOrders01.getCode());
			}
			context.handle(new SMessageInfoTask(info));
			Set<Employee> set = SMessageUtils.getStoreManagers(context, sheet.getStoreId());
			long startTime = new Date().getTime();
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setRECID(context.newRECID());
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setStartTime(startTime);
				mapping.setUserId(e.getId());
			}
		}
	}

	// 库存流水事件@@@@
	protected static void handle(Context context, InventoryLogEvent event, ReportQueue rq) {
		Date date = new Date(rq.getCurrTime());
		InventoryLogEntity log = context.find(InventoryLogEntity.class, event.getInventoryLogId());
		if (log.getInventoryType() == null) {
			log.setInventoryType(InventoryType.Materials.getCode());
		}
		if (null != log && log.getInventoryType().equals(InventoryType.Materials.getCode())) {
			/**
			 * 更新商品库存预警
			 */
			// 删除商品库存预警
			// 查询商品库存信息
			// 根据查询结果重新输入商品库存预警信息
			context.handle(new SMessageDelTask(SMessageType.GoodsInventory, log.getStockId()));
			List<Inventory> list = context.getList(Inventory.class, new GetInventoryByStockIdKey(log.getStockId(),
					InventoryType.Materials));
			double sumAmount = 0;
			double sumCount = 0;
			Set<Employee> set = SMessageUtils.getEmployeeByAuths(context, rq.getTenantsGuid(), Auth.Boss,
					Auth.Purchase, Auth.PurchaseManager);
			MaterialsItem item = context.find(MaterialsItem.class, log.getStockId());
			boolean amounted = false;
			boolean countuped = false;
			boolean countloed = false;
			for (Inventory gi : list) {
				sumAmount += gi.getAmount();
				sumCount += gi.getCount();
				if (item.getWarningType() == InventoryWarningType.Store_Count && gi.getUpperLimitCount() >= 0
						&& gi.getCount() > gi.getUpperLimitCount()) {
					Store store = context.find(Store.class, gi.getStoreId());
					sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory04, set, store
							.getName(), DoubleUtil.sub(gi.getCount(), gi.getUpperLimitCount()));
					countuped = true;
				} else if (item.getWarningType() == InventoryWarningType.Store_Count && gi.getLowerLimitCount() >= 0
						&& gi.getCount() < gi.getLowerLimitCount()) {

					Store store = context.find(Store.class, gi.getStoreId());
					sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory03, set, store
							.getName(), DoubleUtil.sub(gi.getLowerLimitCount(), gi.getCount()));
					countloed = true;
				}
				if (item.getWarningType() == InventoryWarningType.Store_Amount && gi.getUpperLimitAmount() >= 0
						&& gi.getAmount() > gi.getUpperLimitAmount()) {
					amounted = true;
					Store store = context.find(Store.class, gi.getStoreId());
					sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory02, set, store
							.getName(), DoubleUtil.sub(gi.getAmount(), gi.getUpperLimitAmount()));
				}
			}
			if (item.getWarningType() == InventoryWarningType.ALL_Count && item.getTotalStoreFlore() >= 0
					&& sumCount > item.getTotalStoreUpper() && !countuped) {
				sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory07, set, null,
						DoubleUtil.sub(sumCount, item.getTotalStoreUpper()));
			} else if (item.getWarningType() == InventoryWarningType.ALL_Count && item.getTotalStoreFlore() >= 0
					&& sumCount < item.getTotalStoreFlore() && !countloed) {
				sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory06, set, null,
						DoubleUtil.sub(item.getTotalStoreFlore(), sumCount));
			}
			if (item.getWarningType() == InventoryWarningType.ALL_Amount && item.getTotalStoreAmount() >= 0
					&& sumAmount > item.getTotalStoreAmount() && !amounted) {
				sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory05, set, null,
						DoubleUtil.sub(sumAmount, item.getTotalStoreAmount()));
			}
		}

		/**
		 * 报表部分： 更新库存台账
		 */
		if (null == log) {
			return;
		}
		ReportInventoryTask task = new ReportInventoryTask(log.getInventoryType());
		task.setDateNo(date);
		task.setCreatedDate(date.getTime());
		task.setTenantId(rq.getTenantsGuid());
		task.setGoodsGuid(log.getStockId());
		task.setGoodsTypeGuid(log.getCategoryId());
		task.setGoodsAttr(log.getProperties());
		task.setGoodsName(log.getName());
		task.setGoodsNo(log.getStockNo());
		task.setGoodsScale(log.getScale());
		task.setGoodsUnit(log.getUnit());
		task.setStoreGuid(log.getStoreId());
		task.setInstoAmount(log.getInstoAmount());
		task.setInstoCount(log.getInstoCount());
		task.setOutstoAmount(log.getOutstoAmount());
		task.setOutstoCount(log.getOutstoCount());
		context.handle(task);
	}

	public static void sendMessage(Context context, MaterialsItem item, GUID tenantId, SMessageTemplateEnum tempType,
			Set<Employee> set, String value2, double value3) {
		SMessageInfo info = new SMessageInfo();
		info.setRECID(context.newRECID());
		info.setMessageType(SMessageType.GoodsInventory.getCode());
		info.setRelaObjId(item.getId());
		info.setTemplateCode(tempType.getCode());
		info.setStringValue1(item.getMaterialName() + "["+item.getSpec()+"]");
		info.setStringValue2(value2);
		info.setStringValue3(DoubleUtil.getRoundStr(value3));
		context.handle(new SMessageInfoTask(info));
		for (Employee e : set) {
			SMessageMapping mapping = new SMessageMapping();
			mapping.setRECID(context.newRECID());
			mapping.setMessageId(info.getRECID());
			mapping.setMessageType(SMessageType.GoodsInventory.getCode());
			mapping.setUserId(e.getId());
			context.handle(new SMessageMappingTask(mapping));
		}
	}

	// 入库单事件：创建@@@
	protected static void handle(Context context, CheckingInEvent event, ReportQueue rq) {
		/**
		 * 更新待确认入库预警
		 */
		context.handle(new SMessageDelTask(SMessageType.UncheckinOrders, event.getCheckInSheetId()));
		CheckingInInfo sheet = context.find(CheckingInInfo.class, event.getCheckInSheetId());
		if (sheet.getStatus() == CheckingInStatus.Part || sheet.getStatus() == CheckingInStatus.None) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.UncheckinOrders.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getCheckInSheetId());
			if (CheckingInType.Return == sheet.getType()) {
				info.setTemplateCode(SMessageTemplateEnum.UncheckinOrders02.getCode());
			} else {
				info.setTemplateCode(SMessageTemplateEnum.UncheckinOrders01.getCode());
			}
			info.setStringValue1(sheet.getRelaBillsNo());
			context.handle(new SMessageInfoTask(info));
			Set<Employee> set = SMessageUtils.getStoreManagers(context, sheet.getStoreId());
			long startTime = new Date().getTime();
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setRECID(context.newRECID());
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setStartTime(startTime);
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	// 出库单事件：创建@@@
	protected static void handle(Context context, CheckingOutEvent event, ReportQueue rq) {
		/**
		 * 更新待确认出库预警
		 */
		context.handle(new SMessageDelTask(SMessageType.UncheckoutOrders, event.getCheckOutSheetId()));
		CheckingOutInfo sheet = context.find(CheckingOutInfo.class, event.getCheckOutSheetId());
		if (sheet.getStatus() == CheckingOutStatus.Part || sheet.getStatus() == CheckingOutStatus.None) {
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.UncheckoutOrders.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(event.getCheckOutSheetId());
			if (CheckingOutType.Return == sheet.getType()) {
				info.setTemplateCode(SMessageTemplateEnum.UncheckoutOrders02.getCode());
			} else {
				info.setTemplateCode(SMessageTemplateEnum.UncheckoutOrders01.getCode());
			}
			info.setStringValue1(sheet.getRelaBillsNo());
			context.handle(new SMessageInfoTask(info));
			Set<Employee> set = SMessageUtils.getStoreManagers(context, sheet.getStoreId());
			long startTime = new Date().getTime();
			for (Employee e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setRECID(context.newRECID());
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(info.getMessageType());
				mapping.setStartTime(startTime);
				mapping.setUserId(e.getId());
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	// 确认入库@@@@@@@@@@@
	protected static void handle(Context context, CheckInEvent event, ReportQueue rq) {
		if (event.getCheckingInId() == null) {
			return;
		}
		Instorage sheet = context.find(Instorage.class, event.getCheckingInId());
		/**
		 * 更新待确认入库预警
		 */
		if (null != sheet) {
			context.handle(new SMessageDelTask(SMessageType.UncheckinOrders, sheet.getRECID()));
			if (sheet.getStatus().equals(CheckingInStatus.Part.getCode())
					|| sheet.getStatus().equals(CheckingInStatus.None.getCode())) {
				SMessageInfo info = new SMessageInfo();
				info.setMessageType(SMessageType.UncheckinOrders.getCode());
				info.setRECID(context.newRECID());
				info.setRelaObjId(sheet.getRECID());
				if (CheckingInType.Return.getCode().equals(sheet.getSheetType())) {
					info.setTemplateCode(SMessageTemplateEnum.UncheckinOrders02.getCode());
				} else {
					info.setTemplateCode(SMessageTemplateEnum.UncheckinOrders01.getCode());
				}
				info.setStringValue1(sheet.getRelaBillsNo());
				context.handle(new SMessageInfoTask(info));
				Set<Employee> set = SMessageUtils.getStoreManagers(context, sheet.getStoreId());
				long startTime = new Date().getTime();
				for (Employee e : set) {
					SMessageMapping mapping = new SMessageMapping();
					mapping.setRECID(context.newRECID());
					mapping.setMessageId(info.getRECID());
					mapping.setMessageType(info.getMessageType());
					mapping.setStartTime(startTime);
					mapping.setUserId(e.getId());
					context.handle(new SMessageMappingTask(mapping));
				}
			}
		}
	}

	// 确认出库@@@@@@@@@@@
	protected static void handle(Context context, CheckOutEvent event, ReportQueue rq) {
		if (event.getCheckingOutId() == null) {
			return;
		}
		Outstorage sheet = context.find(Outstorage.class, event.getCheckingOutId());
		/**
		 * 更新待确认出库预警
		 */
		if (null != sheet) {
			context.handle(new SMessageDelTask(SMessageType.UncheckoutOrders, sheet.getRECID()));
			if (sheet.getStatus().equals(CheckingOutStatus.Part.getCode())
					|| sheet.getStatus().equals(CheckingOutStatus.None.getCode())) {
				SMessageInfo info = new SMessageInfo();
				info.setMessageType(SMessageType.UncheckoutOrders.getCode());
				info.setRECID(context.newRECID());
				info.setRelaObjId(sheet.getRECID());
				info.setStringValue1(sheet.getRelaBillsNo());
				if (CheckingOutType.Return.getCode().equals(sheet.getSheetType())) {
					info.setTemplateCode(SMessageTemplateEnum.UncheckoutOrders02.getCode());
				} else {
					info.setTemplateCode(SMessageTemplateEnum.UncheckoutOrders01.getCode());
				}
				context.handle(new SMessageInfoTask(info));
				Set<Employee> set = SMessageUtils.getStoreManagers(context, sheet.getStoreId());
				long startTime = new Date().getTime();
				for (Employee e : set) {
					SMessageMapping mapping = new SMessageMapping();
					mapping.setRECID(context.newRECID());
					mapping.setMessageId(info.getRECID());
					mapping.setMessageType(info.getMessageType());
					mapping.setStartTime(startTime);
					mapping.setUserId(e.getId());
					context.handle(new SMessageMappingTask(mapping));
				}
			}
		}
	}
}
