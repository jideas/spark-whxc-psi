package com.spark.order.util.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.internal.service.MeToModuleUtil;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.task.FlowTask;
import com.spark.order.intf.task.RebutTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.order.util.checking.IServiceCheck;
import com.spark.order.util.checking.ServiceCheckFactory;
import com.spark.order.util.checking.IServiceCheck.CheckParam;
import com.spark.order.util.checking.ServiceCheckFactory.CheckEnum;

public class PurchaseOrderActionImpl extends OrderActionImpl {

	public PurchaseOrderActionImpl(Context context) {
		super(context, BillsEnum.PURCHASE);
	}

	@Override
	protected boolean approval() throws Throwable {
		// 执行
		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
		pm.setOrderInfo(this.orderInfo);
		next = pm.next(this.id);
		RebutTask task = new RebutTask(billsEnum);
		task.billsRECID = id;
		task.oldstatus = this.orderInfo.getStatus();
		task.newstatus = next.getKey();
		task.info = this.orderInfo;
		context.handle(task);
		if (StatusEnum.Store_N0 == next && task.isSucceed()) {
			new MeToModuleUtil(context).createInStore((PurchaseOrderInfo) this.orderInfo, context.getList(
					PurchaseOrderItem.class, this.id));
		}
		return task.isSucceed();
	}

	@Override
	protected boolean consignment() {
		// 执行
		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
		pm.setOrderInfo(this.orderInfo);
		next = pm.next(this.id);
		FlowTask task = new FlowTask(billsEnum);
		task.billsRECID = id;
		task.oldstatus = StatusEnum.getstatus(this.orderInfo.getStatus()).getKey();
		task.newstatus = next.getKey();
		context.handle(task);
		return task.isSucceed();
	}
 
	@Override
	protected void initOrderInfo() {
		if (!(CheckIsNull.isNotEmpty(this.orderInfo) && this.orderInfo instanceof PurchaseOrderInfo)) {
			orderInfo = context.find(PurchaseOrderInfo.class, id);
		}
	}

	@Override
	protected boolean submit() throws Throwable {
		boolean isError = false;
		// 校验商品是否超过库存数量上限（如无分库存，检查总库存）
		List<PurchaseOrderItem> items = context.getList(PurchaseOrderItem.class, this.id);
		Map<GUID, PurchaseOrderItem> map = new HashMap<GUID, PurchaseOrderItem>();
		Map<GUID, Integer> indexMap = new HashMap<GUID, Integer>();
		for (int i = 0; i < items.size(); i++) {
			PurchaseOrderItem item = items.get(i);
			if (map.containsKey(item.getGoodsId())) {
				map.get(item.getGoodsId()).setCount(map.get(item.getGoodsId()).getCount() + item.getCount());
				map.get(item.getGoodsId()).setAmount(map.get(item.getGoodsId()).getAmount() + item.getAmount());
			} else {
				map.put(item.getGoodsId(), item);
				indexMap.put(item.getRECID(), i);
			}
		}
		for (PurchaseOrderItem item : map.values()) {
			IServiceCheck sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(item.getGoodsId(),
					((PurchaseOrderInfo) this.orderInfo).getStoreId(), item.getCount(), CheckEnum.inventory_count_upper));
			if (sc.doError()) {
				isError = true;
				this.addCheck(sc);
			}
		}
		// 校验商品是否超过库存金额上限（如无分库存，检查总库存）
		for (PurchaseOrderItem item : map.values()) {
			IServiceCheck sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(item.getGoodsId(),
					((PurchaseOrderInfo) this.orderInfo).getStoreId(), item.getAmount(), CheckEnum.inventory_count_upper));
			if (sc.doError()) {
				isError = true;
				sc.setIndex(indexMap.get(item.getRECID()));
				this.addCheck(sc);
			}
		}
		// 校验结果
		if (isError && !this.isIgnoredWarning) {
			return false;
		}
		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
		pm.setOrderInfo(this.orderInfo);
		next = pm.next(this.id);
		FlowTask task = new FlowTask(billsEnum);
		task.billsRECID = id;
		task.oldstatus = StatusEnum.getstatus(this.orderInfo.getStatus()).getKey();
		task.newstatus = next.getKey();
		task.setDeptGuid(BillsConstant.getUser(context).getDepartmentId());
		context.handle(task);
		if (StatusEnum.Store_N0 == next && task.isSucceed()) {
			new MeToModuleUtil(context).createInStore((PurchaseOrderInfo) this.orderInfo, context.getList(
					PurchaseOrderItem.class, this.id));
		}
		return task.isSucceed();
	}
}
