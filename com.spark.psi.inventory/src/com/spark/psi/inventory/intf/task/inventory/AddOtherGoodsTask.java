/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
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
	 * @param goodsName ��Ʒ����
	 * @param goodsDescription ��Ʒ����
	 * @param goodsNum ��Ʒ����
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
	 * �Ƿ��Ǹ��ĳ�ʼ����ϢĬ��Ϊ��
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
