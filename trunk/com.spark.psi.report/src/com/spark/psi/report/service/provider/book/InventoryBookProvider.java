/**
 * 
 */
package com.spark.psi.report.service.provider.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.MaterialsCategory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.key.GetGoodsInventoryByStoreIdKey;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.inventory.key.ReportInventoryBookKey;
import com.spark.psi.report.utils.QuerySqlBuilder;

/**
 *
 */
public class InventoryBookProvider extends Service {

	/**
	 * @param title
	 */
	protected InventoryBookProvider() {
		super("InventoryBookProvider");
	}

	@Publish
	protected class BookProvider extends OneKeyResultListProvider<InventoryBook, ReportInventoryBookKey> {

		@Override
		protected void provide(Context context, ReportInventoryBookKey key, List<InventoryBook> list) throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable("sa_report_sto_stdbook", "t");
			qb.addColumn("t.goodsGuid", "goodsGuid");
			qb.addColumn("t.goodsName", "goodsName");
			qb.addColumn("t.goodsAttr", "goodsAttr");
			qb.addColumn("t.goodsUnit", "goodsUnit");
			qb.addColumn("t.goodsNo", "goodsNo");
			qb.addColumn("t.goodsScale", "goodsScale");
			qb.addColumn("t.beginStoreCount", "beginStoreCount");
			qb.addColumn("t.beginStoreMoney", "beginStoreMoney");
			qb.addColumn("t.instoCount", "instoCount");
			qb.addColumn("t.instoAmount", "instoAmount");
			qb.addColumn("t.outstoCount", "outstoCount");
			qb.addColumn("t.outstoAmount", "outstoAmount");
			qb.addColumn("t.endStoreCount", "endStoreCount");
			qb.addColumn("t.endStoreMoney", "endStoreMoney");
			qb.addColumn("t.storeGuid", "storeGuid");
			qb.addColumn("t.inventoryType", "inventoryType");
			qb.addOrderBy(" t.goodsGuid");
			qb.addOrderBy("t.dateNo");

			if (null != key.getStoreId()) {
				qb.addArgs("storeGuid", qb.guid, key.getStoreId());
				qb.addEquals("t.storeGuid", "@storeGuid");
			}

			qb.addArgs("invenType", qb.STRING, key.getInventoryType().getCode());
			qb.addEquals("t.inventoryType", "@invenType");
			List<GUID> cateGoryList = new ArrayList<GUID>();
			if (null != key.getGoodsTypeId()) {
				if (InventoryType.Goods.equals(key.getInventoryType())) {
					GoodsCategory gc = context.find(GoodsCategory.class, key.getGoodsTypeId());
					if (null != gc) {
						GoodsCategory[] gclist = gc.getLeafNodes(context);
						List<String> args = new ArrayList<String>();
						int index = 0;
						for (GoodsCategory g : gclist) {
							cateGoryList.add(g.getId());
							qb.addArgs("goodsTypeId" + index, qb.guid, g.getId());
							args.add("@goodsTypeId" + index);
							index++;
						}
						qb.addArgs("goodsTypeId" + index, qb.guid, gc.getId());
						args.add("@goodsTypeId" + index);
						qb.addIn("t.goodsTypeGuid", args);
					}
				} else if (InventoryType.Materials.equals(key.getInventoryType())) {
					MaterialsCategory gc = context.find(MaterialsCategory.class, key.getGoodsTypeId());
					if (null != gc) {
						MaterialsCategory[] gclist = gc.getLeafNodes(context);
						List<String> args = new ArrayList<String>();
						int index = 0;
						for (MaterialsCategory g : gclist) {
							cateGoryList.add(g.getId());
							qb.addArgs("goodsTypeId" + index, qb.guid, g.getId());
							args.add("@goodsTypeId" + index);
							index++;
						}
						qb.addArgs("goodsTypeId" + index, qb.guid, gc.getId());
						args.add("@goodsTypeId" + index);
						qb.addIn("t.goodsTypeGuid", args);
					}
				}
			}

			if (key.getBeginTime() > 0) {
				qb.addArgs("beginTime", qb.DATE, key.getBeginTime());
				qb.addGreaterThanOrEquals("t.createdDate", "@beginTime");
			}
			if (key.getEndTime() > 0) {
				qb.addArgs("endTime", qb.DATE, key.getEndTime());
				qb.addLessThanOrEquals("t.createdDate", "@endTime");
			}
			if (CheckIsNull.isNotEmpty(key.getSearchKey())) {
				qb.addArgs("goodsSearch", qb.STRING, key.getSearchKey());
				StringBuilder ss = new StringBuilder();
				ss.append("(");
				ss.append("t.goodsNo like '%'+@goodsSearch+'%' or ");
				ss.append("t.goodsName like '%'+@goodsSearch+'%' ");
				ss.append("or t.goodsAttr like '%'+@goodsSearch+'%' ");
				ss.append(")");
				qb.addCondition(ss.toString());
			}
			RecordSet rs = qb.getRecord();
			Map<GUID, InventoryBook> map = new HashMap<GUID, InventoryBook>();
			Map<GUID, Set<GUID>> dmap = null;
			Map<GUID, Map<GUID, Double>> ecmap = null;
			Map<GUID, Map<GUID, Double>> eamap = null;
			if (key.getStoreId() == null) {
				dmap = new HashMap<GUID, Set<GUID>>();
				ecmap = new HashMap<GUID, Map<GUID, Double>>();
				eamap = new HashMap<GUID, Map<GUID, Double>>();
			}
			while (rs.next()) {
				InventoryBook book = null;
				GUID goodsId = rs.getFields().get(0).getGUID();
				book = map.get(goodsId);
				if (book == null) {
					book = new InventoryBook();
					book.setGoodsId(goodsId);
					book.setGoodsName(rs.getFields().get(1).getString());
					book.setGoodsAttr(rs.getFields().get(2).getString());
					book.setGoodsUnit(rs.getFields().get(3).getString());
					book.setGoodsNo(rs.getFields().get(4).getString());
					book.setGoodsScale(rs.getFields().get(5).getInt());
					book.setCount_begin(rs.getFields().get(6).getDouble());
					book.setAmount_begin(rs.getFields().get(7).getDouble());
				}
				if (null != dmap && dmap.get(goodsId) != null) {
					Set<GUID> set = dmap.get(goodsId);
					if (set.add(rs.getFields().get(14).getGUID())) {
						book.setCount_begin(book.getCount_begin() + rs.getFields().get(6).getDouble());
						book.setAmount_begin(book.getAmount_begin() + rs.getFields().get(7).getDouble());
					}
				} else if (null != dmap) {
					Set<GUID> set = new HashSet<GUID>();
					set.add(rs.getFields().get(14).getGUID());
					dmap.put(goodsId, set);
				}
				book.setInstoCount(rs.getFields().get(8).getDouble());
				book.setInstoAmount(rs.getFields().get(9).getDouble());
				book.setOutstoCount(rs.getFields().get(10).getDouble());
				book.setOutstoAmount(rs.getFields().get(11).getDouble());
				if (key.getStoreId() == null) {
					Map<GUID, Double> c = ecmap.get(goodsId);
					if (null == c) {
						Map<GUID, Double> mm = new HashMap<GUID, Double>();
						mm.put(rs.getFields().get(14).getGUID(), rs.getFields().get(12).getDouble());
						ecmap.put(goodsId, mm);
					} else {
						c.put(rs.getFields().get(14).getGUID(), rs.getFields().get(12).getDouble());
						ecmap.put(goodsId, c);
					}
					Map<GUID, Double> a = eamap.get(goodsId);
					if (null == a) {
						Map<GUID, Double> mm = new HashMap<GUID, Double>();
						mm.put(rs.getFields().get(14).getGUID(), rs.getFields().get(13).getDouble());
						eamap.put(goodsId, mm);
					} else {
						a.put(rs.getFields().get(14).getGUID(), rs.getFields().get(13).getDouble());
						eamap.put(goodsId, a);
					}
				} else {
					book.setCount_end(rs.getFields().get(12).getDouble());
					book.setAmount_end(rs.getFields().get(13).getDouble());
				}
				book.setInventoryType(rs.getFields().get(15).getString());
				
				if (CheckIsNull.isEmpty(book.getGoodsCode())) {
					if (InventoryType.Materials.getCode().equals(book.getInventoryType())) {
						MaterialsItem gi = context.find(MaterialsItem.class, book.getGoodsId());
						if (null != gi) {
							book.setGoodsCode(gi.getMaterialCode());
						}
					} else if(InventoryType.Goods.getCode().equals(book.getInventoryType())){
						GoodsItem gi = context.find(GoodsItem.class, book.getGoodsId());
						if (null != gi) {
							book.setGoodsCode(gi.getGoodsCode());
						}
					}
				}
				map.put(goodsId, book);
			}
			List<GUID> glist = new ArrayList<GUID>();
			for (InventoryBook book : map.values()) {
				glist.add(book.getGoodsId());
				if (key.getStoreId() == null) {
					Map<GUID, Double> c = ecmap.get(book.getGoodsId());
					for (Double d : c.values()) {
						book.setCount_end(DoubleUtil.sum(d, book.getCount_end()));
					}
					Map<GUID, Double> a = eamap.get(book.getGoodsId());
					for (Double d : a.values()) {
						book.setAmount_end(DoubleUtil.sum(d, book.getAmount_end()));
					}
				}
				list.add(book);
			}
			List<Inventory> allList = null;
			if (key.getStoreId() == null) {
				allList = context.getList(Inventory.class);
			} else {
				allList = context.getList(Inventory.class, new GetGoodsInventoryByStoreIdKey(key.getStoreId()));
			}
			Map<GUID, InventoryBook> allList1 = new HashMap<GUID, InventoryBook>();
			for (Inventory gi : allList) {
				InventoryBook book = allList1.get(gi.getStockId());
				if (book == null) {
					book = new InventoryBook();
				}
				MaterialsItem good1 = context.find(MaterialsItem.class, gi.getStockId());
				if (null != good1) {
					if (cateGoryList.indexOf(good1.getCategoryId()) < 0) {
						continue;
					}
					if (CheckIsNull.isNotEmpty(key.getSearchKey())) {
						if (good1.getMaterialName().indexOf(key.getSearchKey()) < 0
								&& good1.getSpec().indexOf(key.getSearchKey()) < 0
								&& good1.getMaterialCode().indexOf(key.getSearchKey()) < 0) {
							continue;
						}
					}
					if (key.getBeginTime() < good1.getCreateDate()) {
						continue;
					}
					book.setGoodsId(gi.getStockId());
					book.setGoodsName(good1.getMaterialName());
					book.setGoodsAttr(good1.getSpec());
					book.setGoodsUnit(good1.getMaterialUnit());
					book.setGoodsNo(good1.getMaterialCode());
					book.setGoodsScale(good1.getScale());
					book.setCount_begin(gi.getCount() + book.getCount_begin());
					book.setAmount_begin(gi.getAmount() + book.getAmount_begin());
					book.setAmount_end(gi.getAmount() + book.getAmount_end());
					book.setCount_end(gi.getCount() + book.getCount_end());
				} else {
					GoodsItem good = context.find(GoodsItem.class, gi.getStockId());
					if (cateGoryList.indexOf(good.getCategoryId()) < 0) {
						continue;
					}
					if (CheckIsNull.isNotEmpty(key.getSearchKey())) {
						if (good.getGoodsName().indexOf(key.getSearchKey()) < 0
								&& good.getPropertiesWithoutUnit().indexOf(key.getSearchKey()) < 0
								&& good.getGoodsCode().indexOf(key.getSearchKey()) < 0) {
							continue;
						}
					}
					if (key.getBeginTime() < good.getCreateDate()) {
						continue;
					}
					book.setGoodsId(gi.getStockId());
					book.setGoodsName(good.getGoodsName());
					book.setGoodsAttr(good.getPropertiesWithoutUnit());
					book.setGoodsUnit(good.getGoodsUnit());
					book.setGoodsNo(good.getGoodsCode());
					book.setGoodsScale(good.getScale());
					book.setCount_begin(gi.getCount() + book.getCount_begin());
					book.setAmount_begin(gi.getAmount() + book.getAmount_begin());
					book.setAmount_end(gi.getAmount() + book.getAmount_end());
					book.setCount_end(gi.getCount() + book.getCount_end());
				}
				allList1.put(gi.getStockId(), book);
			}
			for (InventoryBook gi : allList1.values()) {
				if (glist.contains(gi.getGoodsId())) {
					continue;
				}
				list.add(gi);
				glist.add(gi.getGoodsId());
			}

			if (key.getSortField() != null) {
				ComparatorUtils.sort(list, key.getSortField().getColumn(), key.getSortType() == SortType.Asc);
			}
		}
	}
}
