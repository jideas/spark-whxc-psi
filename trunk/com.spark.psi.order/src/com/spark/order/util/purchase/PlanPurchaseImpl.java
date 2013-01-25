package com.spark.order.util.purchase;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.internal.service.MeToModuleUtil;
import com.spark.order.intf.event.ChangedType;
import com.spark.order.intf.event.PurchaseOrderChangedEvent;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.intf.type.UserAuthEnum;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.order.purchase.intf.task.PurchaseOrderTask;
import com.spark.order.util.checking.IServiceCheck;
import com.spark.order.util.checking.ServiceCheckFactory;
import com.spark.order.util.checking.IServiceCheck.CheckParam;
import com.spark.order.util.checking.ServiceCheckFactory.CheckEnum;
import com.spark.order.util.process.IProcessManage;
import com.spark.order.util.process.OrderFactory;

/**
 * <p>
 * 普通直供订单
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author modi
 * @version 2012-3-23
 */
class PlanPurchaseImpl extends CreatePurchaseImpl {

	public PlanPurchaseImpl(Context context) {
		super(context);
	}

	private List<PurchaseOrderItem> errorList = new ArrayList<PurchaseOrderItem>();
	private List<Integer> errorIndexs = new ArrayList<Integer>();

	public boolean create(PurchaseOrderInfo info, List<PurchaseOrderItem> dets) throws Throwable {
		// Partner partner = context.find(Partner.class, info.getCuspGuid());
		List<PurchaseOrderItem> planList = new ArrayList<PurchaseOrderItem>();
		// List<PurchaseOrderItem> onLineList = new
		// ArrayList<PurchaseOrderItem>();
		TypeEnum type = TypeEnum.getType(info.getBillType());
		double planAmount = 0;
		for (int i = 0; i < dets.size(); i++) {
			PurchaseOrderItem det = dets.get(i);
			// 直供

			planList.add(det);
			planAmount += det.getAmount();

		}
		boolean isOk = true;
		// 普通订单
		if (0 < planList.size()) {
			PurchaseOrderTask task = new PurchaseOrderTask();
			task.dets = planList;
			task.entity = info;
			 
			info.setStatus(StatusEnum.Submit.getKey());
			info.setTotalAmount(planAmount);
			IProcessManage ipm = OrderFactory.getProcessManage(context, BillsEnum.PURCHASE);
			ipm.setOrderInfo(info);
			info.setStatus(ipm.next(info.getRECID()).getKey());
			if (StatusEnum.Store_N0.getKey().equals(info.getStatus())) {
				new MeToModuleUtil(context).createInStore(task.entity, task.dets);
				context.dispatch(new PurchaseOrderChangedEvent(task.entity.getRECID(), ChangedType.Effectual));

			}
			context.handle(task, TaskEnum.ADD);
		} else {
			isOk = false;
		}
		return isOk;
	}

	public List<PurchaseOrderItem> getDirected() {
		return errorList;
	}

	public List<Integer> getDirectedIndexs() {
		return errorIndexs;
	}

}
