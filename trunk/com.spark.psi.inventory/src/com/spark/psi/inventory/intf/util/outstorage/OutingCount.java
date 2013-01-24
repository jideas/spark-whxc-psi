/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.outstorage.dbox
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-30       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.outstorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��δ�������Ϣ</p>
 *


 *
 * @author ��־��
 * @version 2011-11-30
 */

public class OutingCount{
	public OutingCount(GUID goodsGuid, GUID storeGuid, double count){
		this.goodsGuid = goodsGuid;
		this.storeGuid = storeGuid;
		this.count = count;
	}

	private GUID goodsGuid;
	private GUID storeGuid;
	private double count;

	/**
	 * @return the goodsGuid
	 */
	public GUID getGoodsGuid(){
		return goodsGuid;
	}

	/**
	 * @param goodsGuid the goodsGuid to set
	 */
	public void setGoodsGuid(GUID goodsGuid){
		this.goodsGuid = goodsGuid;
	}

	/**
	 * @return the storeGuid
	 */
	public GUID getStoreGuid(){
		return storeGuid;
	}

	/**
	 * @param storeGuid the storeGuid to set
	 */
	public void setStoreGuid(GUID storeGuid){
		this.storeGuid = storeGuid;
	}

	/**
	 * @return the count
	 */
	public double getCount(){
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(double count){
		this.count = count;
	}
}
