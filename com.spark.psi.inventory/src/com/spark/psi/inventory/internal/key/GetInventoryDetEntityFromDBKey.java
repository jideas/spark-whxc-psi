package com.spark.psi.inventory.internal.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�����ݿ��ѯһ�������ϸ��¼</p>
 *
 */

public class GetInventoryDetEntityFromDBKey {

	private GUID shelfId;//	��λ��ʶ
	private int tiersNo;//	������ڲ���
	private GUID stockId;//	�����ʶ
	private long produceDate;//	��������
	private GUID storeId;//	�ֿ�id
	
	public GUID getShelfId() {
		return shelfId;
	}

	public int getTiersNo() {
		return tiersNo;
	}

	public GUID getStockId() {
		return stockId;
	}

	public long getProduceDate() {
		return produceDate;
	}

	public GUID getStoreId() {
		return storeId;
	}

	/**
	 * 
	 * @param shelfId       ��λ��ʶ      
	 * @param tiersNo       ������ڲ���    
	 * @param stockId       �����ʶ      
	 * @param produceDate   ��������  
	 * @param storeId       �ֿ�id      
	 */
	public GetInventoryDetEntityFromDBKey(GUID shelfId, int tiersNo, GUID stockId, long produceDate, GUID storeId) {
		super();
		this.shelfId = shelfId;
		this.tiersNo = tiersNo;
		this.stockId = stockId;
		this.produceDate = produceDate;
		this.storeId = storeId;
	}
	
	

}
