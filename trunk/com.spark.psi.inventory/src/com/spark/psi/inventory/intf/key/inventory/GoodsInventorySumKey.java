/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.key.inventory
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-29       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�ܿ���ѯKey</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-29
 */

public class GoodsInventorySumKey {

	private GUID goodsItemId;

//	private GUID tenantsId;
	
//	public GUID getTenantsId() {
//		return tenantsId;
//	}
//
//	public GoodsInventorySumKey(GUID tenantsId) {
//		super();
//		this.tenantsId = tenantsId;
//	}

	/**
	 * ��Ʒ��ĿID(����ֵ���ѯ������Ʒ���ܿ����Ϣ)
	 * 
	 * @param goodsItemId void
	 */
	public void setGoodsItemId(GUID goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

	/**
	 * ��Ʒ��ĿID(����ֵ���ѯ������Ʒ���ܿ����Ϣ)
	 * 
	 */
	public GUID getGoodsItemId() {
		return goodsItemId;
	}
}
