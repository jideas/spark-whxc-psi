/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.entity.resource
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-20       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.resource;

import com.jiuqi.dna.core.def.obja.StructClass;


/**
 * <p>
 * ��Ʒ�����Դ����
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
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
//	 * ͨ����ƷID��ȡ����б�
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
//	 * ͨ���ֿ�ID��ȡ����б�
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
//	 * ͨ���ֿ�ID����ƷID��ȡ����б�
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
//	 * ͨ���ֿ�IdList����ƷIdList��ȡ����б�
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
//	 * ��ȡ���п���б�
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
