package com.spark.produceorder.intf.publish.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.produceorder.intf.entity.ProduceGoodsDetEntity;
import com.spark.produceorder.intf.entity.ProduceMaterialDetEntity;
import com.spark.produceorder.intf.entity.ProduceOrderEntity;
import com.spark.produceorder.intf.publish.entity.ProduceOrderInfoGoodsItemImpl;
import com.spark.produceorder.intf.publish.entity.ProduceOrderInfoImpl;
import com.spark.produceorder.intf.publish.entity.ProduceOrderInfoMaterialsItemImpl;
import com.spark.produceorder.intf.publish.entity.ProduceOrderItemImpl;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.intf.task.inventory.InventoryLockTask;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.inventory.checkout.entity.ProduceCheckoutInfoInfo;
import com.spark.psi.publish.inventory.checkout.entity.ProduceCheckoutInfoInfoItem;
import com.spark.psi.publish.inventory.checkout.key.GetProduceReturnCheckoutInfoKey;
import com.spark.psi.publish.inventory.checkout.key.GetProduceTakeCheckoutInfoKey;
import com.spark.psi.publish.inventory.checkout.task.MaterialCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.MaterialCheckOutTaskItem;
import com.spark.psi.publish.inventory.sheet.task.RealGoodsCheckinTask;
import com.spark.psi.publish.inventory.sheet.task.RealGoodsCheckinTaskItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoGoodsItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoMaterialsItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderItem;
import com.spark.psi.publish.produceorder.key.GetProduceOrderListKey;
import com.spark.psi.publish.produceorder.task.FinishTask;
import com.spark.psi.publish.produceorder.task.ReceiveTask;
import com.spark.psi.publish.produceorder.task.ReturnTask;

public final class ProduceOrderServiceUtil {

	final static String produceOrderTable = ERPTableNames.Sales.Produce_Order.getTableName();

	public static List<ProduceOrderItem> getProduceOrderList(Context context, GetProduceOrderListKey key) {
		List<ProduceOrderItem> list = new ArrayList<ProduceOrderItem>();

		StringBuffer sql = new StringBuffer();
		sql.append("define query getProduceOrderItem()\n");
		sql.append("begin\n");
		sql.append(" select \n");
		sql.append(getColumns());
		sql.append(" from ");
		sql.append(produceOrderTable);
		sql.append(" as t\n");
		sql.append(" where ");
		sql.append(getStatusSql(key));
		sql.append(getSearchSql(key));
		sql.append(getOrderBySql(key));
		sql.append("end");

		DBCommand db = context.prepareStatement(sql);
		RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getCount());
		while (rs.next()) {
			list.add(fillOrder(rs));
		}
		return list;

	}

	private static ProduceOrderItem fillOrder(RecordSet rs) {
		ProduceOrderItemImpl impl = new ProduceOrderItemImpl();
		int index = 0;
		impl.setId(rs.getFields().get(index++).getGUID());
		impl.setBillsNo(rs.getFields().get(index++).getString());
		impl.setPlanDate(rs.getFields().get(index++).getDate());
		impl.setGoodsCount(rs.getFields().get(index++).getDouble());
		impl.setRemark(rs.getFields().get(index++).getString());
		impl.setCreator(rs.getFields().get(index++).getString());
		impl.setCreateDate(rs.getFields().get(index++).getDate());
		impl.setFinishDate(rs.getFields().get(index++).getDate());
		impl.setStatus(ProduceOrderStatus.getStatus(rs.getFields().get(index++).getString()));

		return impl;
	}

	private static StringBuffer getOrderBySql(GetProduceOrderListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getSortField()) {
			sql.append(" order by ").append(key.getSortField().getFieldName()).append(" ");
			if (null != key.getSortType()) {
				sql.append(key.getSortType()).append("\n");
			} else {
				sql.append(" asc \n");
			}
		} else {
			sql.append(" order by t.planDate asc \n");
		}
		return sql;
	}

	private static StringBuffer getSearchSql(GetProduceOrderListKey key) {
		StringBuffer sql = new StringBuffer();

		if (null != key.getSearchText()) {
			String searchText = key.getSearchText().trim();
			sql.append(" and (");

			sql.append(" t.billsNo like '%").append(searchText).append("%' ");
			sql.append(")\n");
		}

		return sql;
	}

	private static StringBuffer getStatusSql(GetProduceOrderListKey key) {
		StringBuffer sql = new StringBuffer();
		if (null != key.getStatus() && key.getStatus().length > 0) {
			int index = 0;
			sql.append(" (");
			for (ProduceOrderStatus status : key.getStatus()) {
				if (index != 0) {
					sql.append(" or ");
				}
				sql.append(" t.status='").append(status.getCode()).append("' ");
				index++;
			}
			sql.append(") \n");
		}
		return sql;
	}

	private static StringBuffer getColumns() {
		StringBuffer sql = new StringBuffer();
		sql.append("t.RECID as id,");
		sql.append("t.billsNo as billsNo,");
		sql.append("t.planDate as planDate,");
		sql.append("t.goodsCount as goodsCount,");
		sql.append("t.remark as remark,");
		sql.append("t.creator as creator,");
		sql.append("t.createDate as createDate,");
		sql.append("t.finishDate as finishDate,");
		sql.append("t.status as status,");
		sql.append("t.approvePerson as approvePerson,");
		sql.append("t.approveDate as approveDate\n");

		return sql;
	}

	public static ProduceOrderInfo getProduceOrderInfo(Context context, ProduceOrderEntity o, List<ProduceGoodsDetEntity> gl,
			List<ProduceMaterialDetEntity> ml) {
		ProduceOrderInfoImpl impl = new ProduceOrderInfoImpl();
		impl.setBillsNo(o.getBillsNo());
		impl.setCreateDate(o.getCreateDate());
		impl.setCreator(o.getCreator());
		impl.setFinishDate(o.getFinishDate());
		impl.setGoodsCount(o.getGoodsCount());
		impl.setId(o.getId());
		impl.setPlanDate(o.getPlanDate());
		impl.setRemark(o.getRemark());
		impl.setStatus(ProduceOrderStatus.getStatus(o.getStatus()));
		impl.setApproveDate(o.getApproveDate());
		impl.setApprovePerson(o.getApprovePerson());
		impl.setRejectReason(o.getRejectReason());

		ProduceOrderInfoGoodsItem[] gs = new ProduceOrderInfoGoodsItem[gl.size()];
		ProduceOrderInfoMaterialsItem[] ms = new ProduceOrderInfoMaterialsItem[ml.size()];
		for (int i = 0; i < gl.size(); i++) {
			gs[i] = getProduceOrderInfoGoodsItem(gl.get(i));
		}
		for (int i = 0; i < ml.size(); i++) {
			ms[i] = getProduceOrderInfoMaterialsItem(ml.get(i));
		}
		impl.setGoods(gs);
		impl.setMaterials(ms);
		ProduceCheckoutInfoInfo[] receives = context.find(ProduceCheckoutInfoInfo[].class, new GetProduceTakeCheckoutInfoKey(o.getId()));
		ProduceCheckoutInfoInfo[] returns = context.find(ProduceCheckoutInfoInfo[].class, new GetProduceReturnCheckoutInfoKey(o.getId()));
		ProduceOrderInfoImpl.ReceivedLogImpl[] receivedLogs = new ProduceOrderInfoImpl.ReceivedLogImpl[receives.length];
		ProduceOrderInfoImpl.ReturnedLogImpl[] returnedLogs = new ProduceOrderInfoImpl.ReturnedLogImpl[returns.length];
		for(int i=0;i<receives.length;i++)
		{
			ProduceCheckoutInfoInfo r = receives[i];
			ProduceOrderInfoImpl.ReceivedLogImpl pr =impl.new ReceivedLogImpl();
			pr.setCreator(r.getCreator());
			pr.setStoreId(r.getStoreId());
			pr.setStoreName(r.getStoreName());
			ProduceOrderInfoImpl.ReceivedLogImpl.ItemImpl[] pris = new ProduceOrderInfoImpl.ReceivedLogImpl.ItemImpl[r.getItems().length];
			for(int j=0;j<r.getItems().length;j++)
			{
				ProduceCheckoutInfoInfoItem ci = r.getItems()[j];
				ProduceOrderInfoImpl.ReceivedLogImpl.ItemImpl pri = pr.new ItemImpl();
				pri.setPlanCount(ci.getPlanCount());
				pri.setRealCount(ci.getRealCount());
				pri.setMaterialName(ci.getMaterialName());
				pri.setMaterialSpec(ci.getMaterialSpec());
				pri.setMaterialUnit(ci.getMaterialUnit());
				pri.setScale(ci.getScale());
				pris[j] =pri;
			}
			pr.setItems(pris);
			receivedLogs[i] = pr;
		}
		for(int i=0;i<returns.length;i++)
		{
			ProduceCheckoutInfoInfo r = returns[i];
			ProduceOrderInfoImpl.ReturnedLogImpl pr =impl.new ReturnedLogImpl();
			pr.setCreator(r.getCreator());
			pr.setStoreId(r.getStoreId());
			pr.setStoreName(r.getStoreName());
			ProduceOrderInfoImpl.ReturnedLogImpl.ItemImpl[] pris = new ProduceOrderInfoImpl.ReturnedLogImpl.ItemImpl[r.getItems().length];
			for(int j=0;j<r.getItems().length;j++)
			{
				ProduceCheckoutInfoInfoItem ci = r.getItems()[j];
				ProduceOrderInfoImpl.ReturnedLogImpl.ItemImpl pri = pr.new ItemImpl();
				pri.setPlanCount(ci.getPlanCount());
				pri.setRealCount(ci.getRealCount());
				pri.setMaterialName(ci.getMaterialName());
				pri.setMaterialSpec(ci.getMaterialSpec());
				pri.setMaterialUnit(ci.getMaterialUnit());
				pri.setScale(ci.getScale());
				pris[j] =pri;
			}
			pr.setItems(pris);
			returnedLogs[i] = pr;
		}
		impl.setReceivedLogs(receivedLogs);
		impl.setReturnedLogs(returnedLogs);
		return impl;
	}

	private static ProduceOrderInfoMaterialsItem getProduceOrderInfoMaterialsItem(ProduceMaterialDetEntity produceMaterialDetEntity) {
		ProduceOrderInfoMaterialsItemImpl impl = new ProduceOrderInfoMaterialsItemImpl();

		try {
			new BeanUtils().copyProperties(produceMaterialDetEntity, impl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return impl;
	}

	private static ProduceOrderInfoGoodsItem getProduceOrderInfoGoodsItem(ProduceGoodsDetEntity g) {
		ProduceOrderInfoGoodsItemImpl i = new ProduceOrderInfoGoodsItemImpl();
		i.setBillsId(g.getBillsId());
		i.setBomId(g.getBomId());
		i.setCount(g.getCount());
		i.setFinishedCount(g.getFinishedCount());
		i.setGoodsCode(g.getGoodsCode());
		i.setGoodsId(g.getGoodsId());
		i.setGoodsName(g.getGoodsName());
		i.setGoodsNo(g.getGoodsNo());
		i.setGoodsScale(g.getGoodsScale());
		i.setGoodsSpec(g.getGoodsSpec());
		i.setId(g.getId());
		i.setUnit(g.getUnit());
		return i;
	}

	public static void createCheckOutSheet(Context context, Object task) {
		if(task instanceof ReceiveTask)
		{
			
			Map<GUID, List<ReceiveTask.Item>> map = new HashMap<GUID, List<ReceiveTask.Item>>();

			for (ReceiveTask.Item item : ((ReceiveTask)task).getItems()) {
				if (map.containsKey(item.getStoreId())) {
					List<ReceiveTask.Item> list = map.get(item.getStoreId());
					list.add(item);
				} else {
					List<ReceiveTask.Item> list = new ArrayList<ReceiveTask.Item>();
					list.add(item);
					map.put(item.getStoreId(), list);
				}
			}
			for (java.util.Map.Entry<GUID, List<ReceiveTask.Item>> entry : map.entrySet()) {
				MaterialCheckOutTask ot = new MaterialCheckOutTask(false);
				ot.setRelaBillsId(((ReceiveTask)task).getId());
				ot.setRelaBillsNo(((ReceiveTask)task).getSheetNo());
				ot.setStoreId(entry.getKey());
				List<MaterialCheckOutTaskItem> items = new ArrayList<MaterialCheckOutTaskItem>();
				for (ReceiveTask.Item ri : entry.getValue()) {
					MaterialCheckOutTaskItem oi = new MaterialCheckOutTaskItem();
					oi.setMateiralId(ri.getMaterialId());
					oi.setPlanCount(ri.getCount());
					items.add(oi);
				}
				ot.setItems(items);
				context.handle(ot);
			}
			
		}
		else if(task instanceof ReturnTask)
		{

			Map<GUID, List<ReturnTask.Item>> map = new HashMap<GUID, List<ReturnTask.Item>>();

			for (ReturnTask.Item item : ((ReturnTask)task).getItems()) {
				if (map.containsKey(item.getStoreId())) {
					List<ReturnTask.Item> list = map.get(item.getStoreId());
					list.add(item);
				} else {
					List<ReturnTask.Item> list = new ArrayList<ReturnTask.Item>();
					list.add(item);
					map.put(item.getStoreId(), list);
				}
			}
			for (java.util.Map.Entry<GUID, List<ReturnTask.Item>> entry : map.entrySet()) {
				MaterialCheckOutTask ot = new MaterialCheckOutTask(true);
				ot.setRelaBillsId(((ReturnTask)task).getId());
				ot.setRelaBillsNo(((ReturnTask)task).getSheetNo());
				ot.setStoreId(entry.getKey());
				List<MaterialCheckOutTaskItem> items = new ArrayList<MaterialCheckOutTaskItem>();
				for (ReturnTask.Item ri : entry.getValue()) {
					MaterialCheckOutTaskItem oi = new MaterialCheckOutTaskItem();
					oi.setMateiralId(ri.getMaterialId());
					oi.setPlanCount(ri.getCount());
					items.add(oi);
				}
				ot.setItems(items);
				context.handle(ot);
			}

		
		}
	}

	public static void createCheckInSheet(Context context, FinishTask task) {
		
		RealGoodsCheckinTask t = new RealGoodsCheckinTask();
		t.setRelaBillsId(task.getId());
		t.setRelaBillsNo(task.getSheetNo());
		t.setStoreId(Store.GoodsStoreId);
		t.setStoreName(Store.GoodsStoreName);
		
		List<RealGoodsCheckinTaskItem> items = new ArrayList<RealGoodsCheckinTaskItem>();
		for(FinishTask.Item item:task.getItems())
		{
			RealGoodsCheckinTaskItem ri = new RealGoodsCheckinTaskItem();
			ri.setCount(item.getCount());
			ri.setGoodsId(item.getGoodsId());
			GoodsItem gi = context.find(GoodsItem.class, item.getGoodsId());
			ri.setGoodsName(gi.getGoodsName());
			ri.setGoodsCode(gi.getGoodsCode());
			ri.setGoodsNo(gi.getGoodsNo());
			ri.setGoodsScale(gi.getScale());
			ri.setGoodsSpec(gi.getSpec());
			ri.setGoodsUnit(gi.getGoodsUnit());
			ri.setPrice(gi.getStandardCost());
			ri.setAmount(DoubleUtil.mul(item.getCount(),gi.getStandardCost())
				);
			items.add(ri);
		}
		t.setItems(items);
		
		context.handle(t);
	}
//TODO
//	public static void modifyInventoryLockCount(Context context, com.spark.psi.publish.produceorder.task.CreateProduceOrderTask.GoodsItem g) {
//		InventoryLockTask task = new InventoryLockTask(Store.GoodsStoreId, g.getGoodsId());
//		task.setInventoryType(InventoryType.Goods);
//		task.setLockedCount(g.getLockCount());
//		context.handle(task);
//		
//	}
}
