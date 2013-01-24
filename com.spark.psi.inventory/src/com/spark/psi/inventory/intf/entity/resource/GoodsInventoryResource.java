/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.entity.resource
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-20       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.resource;

import com.jiuqi.dna.core.def.obja.StructClass;


/**
 * <p>
 * 商品库存资源对象
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author Wangtiancai
 * @version 2012-3-20
 */
@StructClass
public class GoodsInventoryResource {
//
//	private GUID id;
//	public Map<GUID, Map<GUID, GoodsInventory>> storeMap = new HashMap<GUID, Map<GUID, GoodsInventory>>();
//	public Map<GUID, Map<GUID, GoodsInventory>> goodsMap = new HashMap<GUID, Map<GUID, GoodsInventory>>();
//
//	/**
//	 * 通过商品ID获取库存列表
//	 * 
//	 * @param goodsItemId
//	 * @return List<GoodsInventory>
//	 */
//	public List<GoodsInventory> getListByGoodsId(GUID goodsItemId) {
//		List<GoodsInventory> list = new ArrayList<GoodsInventory>();
//		if(null!=goodsMap.get(goodsItemId))
//		{
//			list.addAll(goodsMap.get(goodsItemId).values());
//		}
//		return list;
//	}
//
//	/**
//	 * 通过仓库ID获取库存列表
//	 * 
//	 * @param storeId
//	 * @return List<GoodsInventory>
//	 */
//	public List<GoodsInventory> getListByStoreId(GUID storeId) {
//		List<GoodsInventory> list = new ArrayList<GoodsInventory>();
//		if(null!=storeMap.get(storeId))
//		{
//			list.addAll(storeMap.get(storeId).values());
//		}
//		return list;
//	}
//	
//	/**
//	 * 通过仓库ID和商品ID获取库存列表
//	 * 
//	 * @param storeId
//	 * @param goodsItemId
//	 * @return GoodsInventory
//	 */
//	public GoodsInventory getGoodsInventoryByStoreIdAndGoodsId(GUID storeId,GUID goodsItemId) {
//		GoodsInventory goodsInventory = null;
//		if(null!=storeMap.get(storeId))
//		{
//			goodsInventory = storeMap.get(storeId).get(goodsItemId);
//		}
//		return goodsInventory;
//	}
//	
//	/**
//	 * 通过仓库IdList和商品IdList获取库存列表
//	 * 
//	 * @param storeIdList
//	 * @param goodsIdList
//	 * @return List<GoodsInventory>
//	 */
//	public List<GoodsInventory> getListByIdList(List<GUID> storeIdList,List<GUID> goodsIdList) {
//		List<GoodsInventory> list = new ArrayList<GoodsInventory>();
//		if(null==goodsIdList&&null!=storeIdList)
//		{
//			for(GUID storeId:storeIdList)
//			{
//				list.addAll(getListByStoreId(storeId));
//			}
//		}
//		else if(null==storeIdList&&null!=goodsIdList)
//		{
//			for(GUID goodsId:goodsIdList)
//			{
//				list.addAll(getListByGoodsId(goodsId));
//			}
//		}
//		else if(null!=storeIdList&&null!=goodsIdList)
//		{
//			for(GUID storeId:storeIdList)
//			{
//				for(GUID goodsId:goodsIdList)
//				{
//					list.add(getGoodsInventoryByStoreIdAndGoodsId(storeId, goodsId));
//				}
//			}
//		}
//		return list;
//	}
//	/**
//	 * 获取所有库存列表
//	 * 
//	 * @return List<GoodsInventory>
//	 */
//	public List<GoodsInventory> getAllList() {
//		List<GoodsInventory> list = new ArrayList<GoodsInventory>();
//		List<Map> mapList = new ArrayList<Map>();
//		mapList.addAll(storeMap.values());
//		if(null!=mapList)
//		{
//			for(Map map:mapList)
//			{
//				list.addAll(map.values());
//			}
//		}
//		return list;
//	}
//
//	public void setId(GUID id) {
//		this.id = id;
//	}
//
//	public GUID getId() {
//		return id;
//	}

}
