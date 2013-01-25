package com.spark.psi.order.browser.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.psi.order.browser.purchase.PurchasingGoodsListProcessor;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.order.entity.PurchaseGoodsItem;
import com.spark.psi.publish.order.key.GetPurchasingGoodsItemListKey;

class PurchaseGoodsGroupImpl implements IPurchaseGoodsGroup {
	protected final Map<GUID, PurchaseGoodsItem> goodsMap;
	protected final SEditTable table;
	protected final Context context;
	private final List<SupplierGroup> supplierList = new ArrayList<SupplierGroup>(); 
	private final Map<GUID, List<PurchaseOrderGroup>> orderMap = new HashMap<GUID, List<PurchaseOrderGroup>>(); 
	private final Map<GUID, Double> doubleMap = new HashMap<GUID, Double>();
	/**
	 * @param table
	 */
	@SuppressWarnings("unchecked")
	PurchaseGoodsGroupImpl(Context context, SEditTable table) {
		this.context = context;
		ListEntity<PurchaseGoodsItem> listEntity = context.find(
				ListEntity.class, new GetPurchasingGoodsItemListKey());
		this.goodsMap = new HashMap<GUID, PurchaseGoodsItem>();
		for(PurchaseGoodsItem item : listEntity.getItemList()){
			this.goodsMap.put(item.getId(), item);
		}
		this.table = table;
		init();
	}

	private void init() {
		Map<GUID, Map<GUID, Map<GUID, List<PurchaseGoodsItem>>>> map = new HashMap<GUID, Map<GUID,Map<GUID,List<PurchaseGoodsItem>>>>();
		for(PurchaseGoodsItem item : goodsMap.values()){
			if(null == item.getSupplierId()){
				continue;
			}
			else{
				String[] price = table.getEditValue(item.getId().toString(), PurchasingGoodsListProcessor.Columns.PurchasePrice.name());
				if(null == price || price.length == 0){
					continue;
				}
				else{
					try{
						double priceDouble = Double.valueOf(price[0]);
						if(priceDouble < 0 ){
							continue;
						}
						doubleMap.put(item.getId(), priceDouble);
					}catch (NumberFormatException ex) {
						continue;
					}
				}
			}
			//供应商
			if(!map.containsKey(item.getSupplierId())){
				map.put(item.getSupplierId(), new HashMap<GUID, Map<GUID,List<PurchaseGoodsItem>>>());
				supplierList.add(new SupplierGroup(item.getSupplierId(), item.getSupplierShorName()));
			}
			//仓库或销售订单
			if(!map.get(item.getSupplierId()).containsKey(item.getStoreId())){
				map.get(item.getSupplierId()).put(item.getStoreId(), new HashMap<GUID, List<PurchaseGoodsItem>>());
			}
			//联系人
			if(!map.get(item.getSupplierId()).get(item.getStoreId()).containsKey(item.getContactId())){
				map.get(item.getSupplierId()).get(item.getStoreId()).put(item.getContactId(), new ArrayList<PurchaseGoodsItem>());
			}
			map.get(item.getSupplierId()).get(item.getStoreId()).get(item.getContactId()).add(item);
		}
		for(Map<GUID, Map<GUID, List<PurchaseGoodsItem>>> i : map.values()){
			for(Map<GUID, List<PurchaseGoodsItem>> j : i.values()){
				for(List<PurchaseGoodsItem> k : j.values()){
					PurchaseOrderGroup order = new PurchaseOrderGroup();
					order.contactId = k.get(0).getContactId();
					order.isDirect = k.get(0).isDirectDelivery();
					order.sourceId = k.get(0).getStoreId();
					order.sourceName = k.get(0).getStoreName();
					order.deliveryDate = k.get(0).getDirectDeliveryDate();
					order.salesMemo = k.get(0).getSalesMemo();
					List<PurchaseGroupGoods> list = new ArrayList<PurchaseGroupGoods>(); 
					PurchaseGroupGoods pgg = null;
					for(PurchaseGoodsItem pgi : k){
						pgg = new PurchaseGroupGoods();
						pgg.purchaseGoodsId = pgi.getId();
						pgg.goodsName = pgi.getGoodsName();
						pgg.count = pgi.getCount();
						pgg.price = doubleMap.get(pgi.getId()); 
						pgg.goodsItemId = pgi.getGoodsItemId();
						list.add(pgg);
					}
					order.goodsItems = list.toArray(new PurchaseGroupGoods[list.size()]);
					if(!orderMap.containsKey(k.get(0).getSupplierId())){
						orderMap.put(k.get(0).getSupplierId(), new ArrayList<PurchaseOrderGroup>());
					}
					orderMap.get(k.get(0).getSupplierId()).add(order);
				}
			}
		}
	}

	public PurchaseOrderGroup[] getPurchaseOrderGroupList(
			GUID supplierGroupId) {
		return orderMap.get(supplierGroupId).toArray(new PurchaseOrderGroup[0]);
	}

	public SupplierGroup[] getSupplierGroupList() {
		return supplierList.toArray(new SupplierGroup[supplierList.size()]);
	}

}
