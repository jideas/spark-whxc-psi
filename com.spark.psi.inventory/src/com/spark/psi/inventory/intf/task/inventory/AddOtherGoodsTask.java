/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-15       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.inventory;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.OtherGoods;

/**
 * @author zhongxin
 *
 */
public class AddOtherGoodsTask extends SimpleTask {
//	private GUID tenantsGuid;
	private GUID storeGuid;
	private OtherGoods otherGoods;
	private List<OtherGoods> otherGoodsList;
	
	private boolean isInit = false;
	
	/**
	 * 
	 * @param goodsName 物品名称
	 * @param goodsDescription 物品描述
	 * @param goodsNum 物品数量
	 */
	public AddOtherGoodsTask( GUID storeGuid, OtherGoods otherGoods) {
//		this.tenantsGuid = tenantsGuid;
		this.storeGuid = storeGuid;
		this.otherGoods = otherGoods;
	}
	
	public AddOtherGoodsTask(GUID storeGuid, List<OtherGoods> otherGoodsList)
	{
		this.storeGuid = storeGuid;
		this.otherGoodsList = otherGoodsList;
	}

	public GUID getStoreGuid() {
		return storeGuid;
	}
	public OtherGoods getOtherGoods() {
		return otherGoods;
	}
	public boolean isInit() {
		return isInit;
	}
	/**
	 * 是否是更改初始化信息默认为否
	 * @param isInit
	 */
	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}

	public void setOtherGoodsList(List<OtherGoods> otherGoodsList) {
		this.otherGoodsList = otherGoodsList;
	}

	public List<OtherGoods> getOtherGoodsList() {
		return otherGoodsList;
	}
	
}
