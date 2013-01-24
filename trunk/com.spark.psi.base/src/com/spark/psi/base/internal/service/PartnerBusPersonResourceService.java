/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-20    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-20    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.entity.PartnerBusPersons;

/**
 * <p>TODO 类描述</p>
 *


 *
 
 * @version 2012-3-20
 */
@Deprecated
public class PartnerBusPersonResourceService extends ResourceService<PartnerBusPersons, PartnerBusPersons, PartnerBusPersons>{

	protected PartnerBusPersonResourceService(){
	    super("com.spark.psi.base.internal.service.PartnerBusPersonResourceService",ResourceKind.SINGLETON_IN_CLUSTER);
    }

	@Override
	protected void initResources(Context context,
			ResourceInserter<PartnerBusPersons, PartnerBusPersons, PartnerBusPersons> initializer){
//		List<TenantResources> trList = context.getList(TenantResources.class);
//		Map<GUID,PartnerBusPersons> map = new HashMap<GUID,PartnerBusPersons>();
//		for(TenantResources tr : trList){
//	        for(PartnerImpl partner : tr.getCustomersList()){
//	            if(!map.containsKey(partner.getBusinessPerson())){
//	            	PartnerBusPersons pbp = new PartnerBusPersons(partner.getBusinessPerson());
//	            	map.put(partner.getBusinessPerson(), pbp);
//	            	initializer.putResource(pbp);
//	            }
//	            map.get(partner.getBusinessPerson()).addPartner(partner);
//            }
//        }
//		map.clear();
//		map = null;
	}
	
	final class GetPartnerBusPersonResourceByEmployeeId extends OneKeyResourceProvider<GUID>{

		@Override
        protected GUID getKey1(PartnerBusPersons keysHolder){
	        return keysHolder.getEmployeeId();
        }		
	}
}
