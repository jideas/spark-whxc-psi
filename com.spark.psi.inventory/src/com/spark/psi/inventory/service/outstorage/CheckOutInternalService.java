package com.spark.psi.inventory.service.outstorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.internal.entity.InventoryLogEntity;
import com.spark.psi.inventory.intf.publish.entity.ProduceCheckoutInfoInfoImpl;
import com.spark.psi.inventory.intf.publish.entity.ProduceCheckoutInfoInfoItemImpl;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryDeliveringTask;
import com.spark.psi.inventory.intf.task.inventory.StoStreamTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryBusTask.DetItem;
import com.spark.psi.inventory.intf.task.outstorage.CreateCheckOutSheetTask;
import com.spark.psi.inventory.intf.task.outstorage.CreateCheckOutSheetTaskItem;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.InventoryLogType;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.inventory.checkout.entity.ProduceCheckoutInfoInfo;
import com.spark.psi.publish.inventory.checkout.entity.ProduceCheckoutInfoInfoItem;
import com.spark.psi.publish.inventory.checkout.key.GetProduceReturnCheckoutInfoKey;
import com.spark.psi.publish.inventory.checkout.key.GetProduceTakeCheckoutInfoKey;
import com.spark.psi.publish.inventory.checkout.task.RealGoodsCheckOutTask;
import com.spark.psi.publish.inventory.checkout.task.RealGoodsCheckOutTaskItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItemDet;

public class CheckOutInternalService extends Service {

	protected CheckOutInternalService() {
		super("com.spark.psi.inventory.service.outstorage.CheckOutInternalService");
	}

	@Publish
	protected class RealGoodsCheckOutHandle extends SimpleTaskMethodHandler<RealGoodsCheckOutTask> {

		@Override
		protected void handle(Context context, RealGoodsCheckOutTask task) throws Throwable {
			String sheetNo = context.get(String.class, SheetNumberType.Checkout);
			CreateCheckOutSheetTask sheet = new CreateCheckOutSheetTask();
			sheet.setRECID(context.newRECID());
			if(null!=task.getCheckingOutType())
			{
				sheet.setCheckoutType(task.getCheckingOutType().getCode());
			}
			else
			{
				sheet.setCheckoutType(CheckingOutType.RealGoods.getCode());
			}
			sheet.setRelaBillsId(task.getRelaBillsId());
			sheet.setRelaBillsNo(task.getRelaBillsNo());
			sheet.setRemark(task.getRemark());
			Store store = context.find(Store.class, task.getStoreId());
			sheet.setStoreId(task.getStoreId());
			sheet.setStoreName(store.getName());
			sheet.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			sheet.setSheetNo(sheetNo);
			double amount = 0;
			List<CreateCheckOutSheetTaskItem> items = new ArrayList<CreateCheckOutSheetTaskItem>();
			for (RealGoodsCheckOutTaskItem item : task.getItems()) {
				CreateCheckOutSheetTaskItem det = new CreateCheckOutSheetTaskItem();
				det.setRECID(context.newRECID());
				det.setGoodsCode(item.getGoodsCode());
				det.setGoodsId(item.getGoodsId());
				det.setGoodsNo(item.getGoodsNo());
				det.setGoodsName(item.getGoodsName());
				det.setGoodsSpec(item.getGoodsSpec());
				det.setUnit(item.getGoodsUnit());
				det.setScale(item.getGoodsScale());
				det.setSheetId(sheet.getRECID());
				det.setPrice(item.getPrice());
				// det.setAvgCost(item.getPrice());
				det.setRealCount(item.getCount());
				det.setAmount(item.getAmount());
				amount = DoubleUtil.sum(amount, det.getAmount());
				items.add(det);
			}
			sheet.setAmount(amount);
			sheet.setItems(items);
			context.handle(sheet);

			for (CreateCheckOutSheetTaskItem item : items) {
				// 更新库存和成本
				modfiyMaterialsStorage(context, sheet, item, false, null, InventoryType.Goods);
			}
			// 库存流水
			doWrightStream(context, sheet, InventoryType.Goods);
		}
	}

	/**
	 * 更新材料库存信息
	 * 
	 * @param b
	 * @param item
	 */
	protected static void modfiyMaterialsStorage(Context context, CreateCheckOutSheetTask sheet,
			CreateCheckOutSheetTaskItem item, boolean b, DistributeInventoryItem[] inventoryItems,
			InventoryType inventoryType) {
		// 更新库存
		InventoryBusTask task = new InventoryBusTask(sheet.getStoreId(), item.getGoodsId());
		task.setInventoryType(inventoryType);
		task.setChangeCountAndAmount(DoubleUtil.sub(0, item.getRealCount()), DoubleUtil.sub(0, item.getAmount()));
		if (null != inventoryItems) {
			setShelfItem(task, inventoryItems, sheet, item.getGoodsId());
		}
		if(InventoryType.Goods.equals(inventoryType))
		{
			task.setUpdateAvgPrice(true);
		}
		context.handle(task);
		
		// 更新交付需求
		if (b) {
			InventoryDeliveringTask onway = new InventoryDeliveringTask(sheet.getStoreId(), item.getGoodsId());
			onway.setInventoryType(InventoryType.Materials);
			onway.setToDeliverCount(DoubleUtil.sub(0, item.getRealCount()));
			context.handle(onway);
		}
	}

	/**
	 * 货位信息
	 */
	protected static void setShelfItem(InventoryBusTask task, DistributeInventoryItem[] inventoryItems,
			CreateCheckOutSheetTask sheet, GUID goodsId) {
		List<DetItem> dets = new ArrayList<DetItem>();
		for (DistributeInventoryItem iteminfo : inventoryItems) {
			if (!iteminfo.getStockId().equals(goodsId)) {
				continue;
			}
			if (null == iteminfo.getInventoryDetItems()) {
				continue;
			}
			for (DistributeInventoryItemDet item : iteminfo.getInventoryDetItems()) {
				double count = DoubleUtil.sub(0, item.getDistributeCount());
				if (CheckingOutType.Mateiral_Return.getCode().equals(sheet.getCheckoutType())) {
					count =  item.getCount();
				}
				DetItem det = task.new DetItem(item.getShelfId(), item.getShelfNo(), item.getTiersNo(), iteminfo
						.getStockId(), count, item.getProduceDate(), sheet.getStoreId());

				dets.add(det);
			}
		}
		task.setDets(dets.toArray(new DetItem[dets.size()]));
	}

	/**
	 * 添加库存流水信息
	 */
	protected static void doWrightStream(Context context, CreateCheckOutSheetTask sheet, InventoryType inventoryType) {
		// 库存流水
		StoStreamTask stream = new StoStreamTask();
		List<InventoryLogEntity> list = new ArrayList<InventoryLogEntity>();
		Login login = context.find(Login.class);
		Employee user = context.find(Employee.class, login.getEmployeeId());
		for (CreateCheckOutSheetTaskItem item : sheet.getItems()) {
			InventoryLogEntity sto = new InventoryLogEntity();
			if (inventoryType.equals(InventoryType.Materials)) {
				MaterialsItem goods = context.find(MaterialsItem.class, item.getGoodsId());
				sto.setCategoryId(goods.getCategoryId());
				sto.setProperties(goods.getSpec());
				sto.setScale(goods.getScale()); 
				sto.setUnit(goods.getMaterialUnit());
				sto.setStockNo(goods.getMaterialNo());
				sto.setName(goods.getMaterialName());
				sto.setStockId(goods.getId());
				sto.setCode(goods.getMaterialCode());
				item.setAvgCost(goods.getAvgBuyPrice());
			} else {
				GoodsItem goods = context.find(GoodsItem.class, item.getGoodsId());
				sto.setCategoryId(goods.getCategoryId());
				sto.setProperties(goods.getSpec());
				sto.setScale(goods.getScale());
				sto.setUnit(goods.getGoodsUnit());
				sto.setStockNo(goods.getGoodsNo());
				sto.setName(goods.getGoodsName());
				sto.setStockId(goods.getId()); 
				sto.setCode(goods.getGoodsCode());
				item.setAvgCost(goods.getAvgCost());
			}
			sto.setStoreId(sheet.getStoreId());
			sto.setCreatedDate(System.currentTimeMillis());
			sto.setCreatePerson(user.getName());
			sto.setId(context.newRECID());
			sto.setOutstoAmount(DoubleUtil.mul(item.getRealCount(), item.getAvgCost()));
			sto.setOutstoCount(item.getRealCount());
			sto.setInventoryType(inventoryType.getCode());
			if (sheet.getCheckoutType().equals(CheckingOutType.RealGoods.getCode())) {
				sto.setLogType(InventoryLogType.OUTSTORAGE.getCode());
			} else if (sheet.getCheckoutType().equals(CheckingOutType.Return.getCode())) {
				sto.setLogType(InventoryLogType.BUYCANCEL.getCode());
			} else if (sheet.getCheckoutType().equals(CheckingOutType.Mateiral_Take.getCode())) {
				sto.setLogType(InventoryLogType.MaterialsCheckout.getCode());
			} else if (sheet.getCheckoutType().equals(CheckingOutType.Mateiral_Return.getCode())) {
				sto.setLogType(InventoryLogType.MaterialsCheckin.getCode());
			} else {
				sto.setLogType(InventoryLogType.OUTSTORAGE.getCode());
				if (CheckingOutType.RealGoods.getCode().equals(sheet.getCheckoutType())) {
					sto.setLogType(InventoryLogType.GoodsCheckout.getCode());
				}
				if(CheckingOutType.GoodsSplit.getCode().equals(sheet.getCheckoutType()))
				{
					sto.setLogType(InventoryLogType.GoodsSplitCheckout.getCode());
				}
			}
			sto.setOrderId(sheet.getRECID());
			sto.setOrderNo(sheet.getSheetNo());
			list.add(sto);
		}
		stream.setList(list);
		context.handle(stream, StoStreamTask.Task.add);
	}

	@Publish
	protected class GetProduceCheckoutInfoProvider extends
			OneKeyResultProvider<ProduceCheckoutInfoInfo[], GetProduceTakeCheckoutInfoKey> {

		@Override
		protected ProduceCheckoutInfoInfo[] provide(Context context, GetProduceTakeCheckoutInfoKey key)
				throws Throwable {
			return context.find(ProduceCheckoutInfoInfo[].class, key.getId(), CheckingOutType.Mateiral_Take);
		}
	}

	@Publish
	protected class GetProduceCheckoutReturnInfoProvider extends
			OneKeyResultProvider<ProduceCheckoutInfoInfo[], GetProduceReturnCheckoutInfoKey> {

		@Override
		protected ProduceCheckoutInfoInfo[] provide(Context context, GetProduceReturnCheckoutInfoKey key)
				throws Throwable {
			return context.find(ProduceCheckoutInfoInfo[].class, key.getId(), CheckingOutType.Mateiral_Return);
		}
	}

	@Publish
	protected class RealProduceCheckoutInfoProvider extends
			TwoKeyResultProvider<ProduceCheckoutInfoInfo[], GUID, CheckingOutType> {

		@Override
		protected ProduceCheckoutInfoInfo[] provide(Context context, GUID key1, CheckingOutType key2) throws Throwable {
			List<ProduceCheckoutInfoInfo> infos = new ArrayList<ProduceCheckoutInfoInfo>();
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.Checkingout.getTableName(), "t");
			qb.addColumn("t.storeId", "storeId");
			qb.addColumn("t.storeName", "storeName");
			qb.addColumn("t.creator", "creator");
			qb.addColumn("t.creatorId", "creatorId");

			qb.addArgs("relaId", qb.guid, key1);
			qb.addEquals("t.relaBillsId", "@relaId");

			qb.addArgs("stype", qb.STRING, key2.getCode());
			qb.addEquals("t.sheetType", "@stype");

			qb.addGroupBy("t.storeId");
			qb.addGroupBy("t.storeName");
			qb.addGroupBy("t.creator");
			qb.addOrderBy("t.checkoutDate ");
			RecordSet rs = qb.getRecord();
			Map<String, List<ProduceCheckoutInfoInfoItem>> map = queryDetails(context, key1, key2);
			while (rs.next()) {
				ProduceCheckoutInfoInfoImpl info = new ProduceCheckoutInfoInfoImpl();
				info.setStoreId(rs.getFields().get(0).getGUID());
				info.setStoreName(rs.getFields().get(1).getString());
				info.setCreator(rs.getFields().get(2).getString());
				String key = info.getStoreId() + "" + rs.getFields().get(3).getGUID();
				info.setItems(map.get(key).toArray(new ProduceCheckoutInfoInfoItem[map.get(key).size()]));
				infos.add(info);
			}
			return infos.toArray(new ProduceCheckoutInfoInfo[infos.size()]);
		}

		private Map<String, List<ProduceCheckoutInfoInfoItem>> queryDetails(Context context, GUID key1,
				CheckingOutType key2) {
			Map<String, List<ProduceCheckoutInfoInfoItem>> map = new HashMap<String, List<ProduceCheckoutInfoInfoItem>>();
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Inventory.Checkingout_Det.getTableName(), "t");
			qb.addTable(ERPTableNames.Inventory.Checkingout.getTableName(), "s");
			qb.addColumn("s.storeId", "storeId");
			qb.addColumn("s.creatorId", "creatorId");
			qb.addColumn("t.goodsId", "goodsId");
			qb.addColumn("t.goodsCode", "goodsCode");
			qb.addColumn("t.goodsNo", "goodsNo");
			qb.addColumn("t.goodsName", "goodsName");
			qb.addColumn("t.unit", "unit");
			qb.addColumn("t.scale", "scale");
			qb.addColumn("t.goodsSpec", "goodsSpec");
			qb.addColumn("sum(t.planCount)", "planCount");
			qb.addColumn("sum(t.checkoutCount)", "checkoutCount");

			qb.addEquals("t.sheetId", "s.RECID");

			qb.addArgs("relaId", qb.guid, key1);
			qb.addEquals("s.relaBillsId", "@relaId");

			qb.addArgs("stype", qb.STRING, key2.getCode());
			qb.addEquals("s.sheetType", "@stype");

			qb.addOrderBy("t.sheetId");

			qb.addGroupBy("s.storeId");
			qb.addGroupBy("s.creatorId");
			qb.addGroupBy("t.goodsId");
			qb.addGroupBy("t.goodsCode");
			qb.addGroupBy("t.goodsNo");
			qb.addGroupBy("t.goodsName");
			qb.addGroupBy("t.unit");
			qb.addGroupBy("t.scale");
			qb.addGroupBy("t.goodsSpec");

			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				ProduceCheckoutInfoInfoItemImpl item = new ProduceCheckoutInfoInfoItemImpl();
				String key = rs.getFields().get(0).getGUID() + "" + rs.getFields().get(1).getGUID();
				item.setMaterialId(rs.getFields().get(2).getGUID());
				item.setMaterialCode(rs.getFields().get(3).getString());
				item.setMaterialNo(rs.getFields().get(4).getString());
				item.setMaterialName(rs.getFields().get(5).getString());
				item.setMaterialUnit(rs.getFields().get(6).getString());
				item.setScale(rs.getFields().get(7).getInt());
				item.setMaterialSpec(rs.getFields().get(8).getString());
				item.setPlanCount(rs.getFields().get(9).getDouble());
				item.setRealCount(rs.getFields().get(10).getDouble());
				List<ProduceCheckoutInfoInfoItem> items = map.get(key);
				if (null == items) {
					items = new ArrayList<ProduceCheckoutInfoInfoItem>();
				}
				items.add(item);
				map.put(key, items);
			}
			return map;
		}
	}

}
