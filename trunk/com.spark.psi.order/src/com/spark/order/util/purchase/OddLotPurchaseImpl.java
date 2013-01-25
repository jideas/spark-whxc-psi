package com.spark.order.util.purchase;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.order.purchase.intf.task.PurchaseOrderTask;

/**
 * <p>
 * ���
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author modi
 * @version 2012-3-23
 */
class OddLotPurchaseImpl extends CreatePurchaseImpl {

	public OddLotPurchaseImpl(Context context) {
		super(context);
	}
	
	public boolean create(PurchaseOrderInfo info, List<PurchaseOrderItem> dets) throws Throwable {
		PurchaseOrderTask task = new PurchaseOrderTask();
		task.dets = dets;
		task.entity = info;
		context.handle(task, TaskEnum.ADD);
		return true;
	}

	public List<PurchaseOrderItem> getDirected() {
		return null;
	}

	public List<Integer> getDirectedIndexs() {
		return null;
	}

}
