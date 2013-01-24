package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.OtherGoods;
import com.spark.psi.inventory.internal.entity.OthersInventory;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;

/**
 * ������Ʒ�������
 *
 */
public class UpdateOtherGoodsTask extends Task<Method> {
	
	private OthersInventory otherStorage;
	private GUID storeId;
	private OtherGoods otherGoods;
	
	private boolean isInit = false;
	/**
	 * 
	 * @param goodsName ��Ʒ����
	 * @param goodsDescription ��Ʒ����
	 * @param goodsNum ��Ʒ����
	 */
	public UpdateOtherGoodsTask(GUID storeId, OtherGoods otherGoods) {
		this.storeId = storeId;
		this.otherGoods = otherGoods;
	}
	/**
	 * ����ɾ��ʱ���� 
	 */
	public UpdateOtherGoodsTask () {
		
	}
	
	public GUID getStoreId() {
		return storeId;
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
	public void setOtherStorage(OthersInventory otherStorage) {
		this.otherStorage = otherStorage;
	}
	public OthersInventory getOtherStorage() {
		return otherStorage;
	}
	
	
}
