/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.demo.store.common
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-10-22       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.inventoryenum;

/**
 * <p>�ֿ�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author zhongxin
 * @version 2011-10-22
 */

public enum StoreDetailItem {
	/**  ����Ա */
	STOREMANAGER("0"),
	/**  ���� */
	SALER("1"),
	/**  �ɹ� */
	PURCHASE("2");
	
	private String value;
	private StoreDetailItem(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
