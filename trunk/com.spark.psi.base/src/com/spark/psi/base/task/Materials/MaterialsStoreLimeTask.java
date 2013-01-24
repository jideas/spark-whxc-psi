/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.storage.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-11       zhongxin        
 * ============================================================*/

package com.spark.psi.base.task.Materials;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * 库存上、下限task
 * 处理库存上、下限， 库存金额上限的修改操作
 * @author zhongxin
 *
 */
public class MaterialsStoreLimeTask extends SimpleTask {
	
		private GUID storeGuid, materialsGuid;
	
		private double storeUpper = -1d;
		private double storeFloor = -1d;
		private double storeAmountUpper = -1d;
		
		
		/**
		 * 修改库存上限，库存下限，库存金额
		 * @param tenantsGuid 租户GUID
		 * @param storeGuid 仓库GUID
		 * @param MaterialsGuid 商品GUID
		 */
		public MaterialsStoreLimeTask(GUID storeGuid, GUID MaterialsGuid,double storeAmountUpper) {
			this.storeGuid = storeGuid;
			this.materialsGuid = MaterialsGuid;
			this.storeAmountUpper = storeAmountUpper;
		}
		
		/**
		 * 修改库存上限，库存下限，库存金额
		 * @param tenantsGuid 租户GUID
		 * @param storeGuid 仓库GUID
		 * @param MaterialsGuid 商品GUID
		 */
		public MaterialsStoreLimeTask(GUID storeGuid, GUID MaterialsGuid,double storeUpper,double storeFloor) {
			this.storeGuid = storeGuid;
			this.materialsGuid = MaterialsGuid;
			this.storeFloor = storeFloor;
			this.storeUpper = storeUpper;
		}
		
		/**
		 * 修改库存上限，库存下限，库存金额
		 * @param tenantsGuid 租户GUID
		 * @param storeGuid 仓库GUID
		 * @param materialsGuid 商品GUID
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
