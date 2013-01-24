/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-11       zhongxin        
 * ============================================================*/

package com.spark.psi.base.task.Materials;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * ����ϡ�����task
 * �������ϡ����ޣ� ��������޵��޸Ĳ���
 * @author zhongxin
 *
 */
public class MaterialsStoreLimeTask extends SimpleTask {
	
		private GUID storeGuid, materialsGuid;
	
		private double storeUpper = -1d;
		private double storeFloor = -1d;
		private double storeAmountUpper = -1d;
		
		
		/**
		 * �޸Ŀ�����ޣ�������ޣ������
		 * @param tenantsGuid �⻧GUID
		 * @param storeGuid �ֿ�GUID
		 * @param MaterialsGuid ��ƷGUID
		 */
		public MaterialsStoreLimeTask(GUID storeGuid, GUID MaterialsGuid,double storeAmountUpper) {
			this.storeGuid = storeGuid;
			this.materialsGuid = MaterialsGuid;
			this.storeAmountUpper = storeAmountUpper;
		}
		
		/**
		 * �޸Ŀ�����ޣ�������ޣ������
		 * @param tenantsGuid �⻧GUID
		 * @param storeGuid �ֿ�GUID
		 * @param MaterialsGuid ��ƷGUID
		 */
		public MaterialsStoreLimeTask(GUID storeGuid, GUID MaterialsGuid,double storeUpper,double storeFloor) {
			this.storeGuid = storeGuid;
			this.materialsGuid = MaterialsGuid;
			this.storeFloor = storeFloor;
			this.storeUpper = storeUpper;
		}
		
		/**
		 * �޸Ŀ�����ޣ�������ޣ������
		 * @param tenantsGuid �⻧GUID
		 * @param storeGuid �ֿ�GUID
		 * @param materialsGuid ��ƷGUID
		 */
		public MaterialsStoreLimeTask(GUID storeGuid, GUID materialsGuid,double storeUpper,double storeFloor,double storeAmountUpper) {
			this.storeGuid = storeGuid;
			this.materialsGuid = materialsGuid;
			this.storeFloor = storeFloor;
			this.storeUpper = storeUpper;
			this.storeAmountUpper = storeAmountUpper;
		}


		public GUID getStoreGuid(){
        	return storeGuid;
        }


		public GUID getMaterialsGuid(){
        	return materialsGuid;
        }


		public double getStoreUpper(){
        	return storeUpper;
        }


		public double getStoreFloor(){
        	return storeFloor;
        }


		public double getStoreAmountUpper(){
        	return storeAmountUpper;
        }

		
}
