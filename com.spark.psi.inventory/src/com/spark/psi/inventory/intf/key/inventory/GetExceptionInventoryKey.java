/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-12-14       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

/**
 * ��ѯ����쳣����Ʒ�Ŀ����Ϣ
 * �쳣��Ʒ������������ڿ������ or ����������ڿ������ or �������ڿ�����޽��
 * @author zhongxin
 *
 */
public class GetExceptionInventoryKey {
	private String sortCloumName;//����
    
    private String sortType;//�������ͣ�����/����
    
	public String getSortCloumName() {
		return sortCloumName;
	}
	public void setSortCloumName(String sortCloumName) {
		this.sortCloumName = sortCloumName;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	
	
}
