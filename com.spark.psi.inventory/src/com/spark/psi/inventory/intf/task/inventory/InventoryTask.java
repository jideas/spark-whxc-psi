/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.service.resource.InventoryEntity;

/**
 * @author zhongxin
 *
 */
public class InventoryTask extends Task<InventoryTask.Method> {
	public enum Method {
		/** ���� */
		ADD,
		/** �޸Ŀ���ʼ����Ϣ */
		MODIFY_INIT_INFO, 
		/** ɾ������ʼ����Ϣ */
		DELETE_INIT_INFO,
		/** �޸Ŀ����Ϣ */
		MODIFY_STORAGE_INFO,
		/** �޸���Ʒ���ϡ�����*/
		MODIFY_STORE_UPPER_FLOOR,
	}
	private InventoryEntity storageEntity;
	public InventoryEntity getStorageEntity() {
		return storageEntity;
	}
	public void setStorageEntity(InventoryEntity storageEntity) {
		this.storageEntity = storageEntity;
	}
	
	
}
