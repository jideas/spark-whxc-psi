/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.Publish;
import com.spark.psi.base.internal.entity.EnumEntityList;
import com.spark.psi.base.internal.entity.EnumEntityList.EnumEntityImpl;
import com.spark.psi.base.internal.service.query.QD_GetAllEnumEntityList;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;

/**
 * <p>数据字典服务</p>
 *


 *
 
 * @version 2012-3-27
 */

public class EnumResourceService2 extends ResourceService<EnumEntityList, EnumEntityList, EnumEntityList>{
	
	final QD_GetAllEnumEntityList qD_GetAllEnumEntityList;
	
	protected EnumResourceService2(final QD_GetAllEnumEntityList qD_GetAllEnumEntityList){
	    super("数据字典资源服务",ResourceKind.SINGLETON_IN_CLUSTER);
	    this.qD_GetAllEnumEntityList = qD_GetAllEnumEntityList;
    }
	
	
	@Override
	protected void init(Context context){
		context.getList(EnumEntityList.class);
	}
	
	@Override
	protected void initResources(Context context,
			ResourceInserter<EnumEntityList,EnumEntityList,EnumEntityList> initializer){
		List<EnumEntityList> list = getAllEnumEntityList(context);
		for(EnumEntityList enumEntityList : list){
	        initializer.putResource(enumEntityList);
        }
	}


	private List<EnumEntityList> getAllEnumEntityList(Context context){
		List<EnumEntityList> result = new ArrayList<EnumEntityList>();
	    Map<String,EnumEntityList> listMap = new HashMap<String,EnumEntityList>();
	    Map<String,EnumEntityImpl> entityMap = new HashMap<String,EnumEntityImpl>();
	    RecordSet rs = context.openQuery(qD_GetAllEnumEntityList);
	    while(rs.next()){
	    	String typeName = rs.getFields().get(0).getString();
	    	EnumEntityList listEntity;
	    	if(listMap.containsKey(typeName)){
	    		listEntity = listMap.get(typeName);
	    	}else{
	    		EnumType type = EnumType.getEnumTypeByCode(typeName);
	    		if(type==null)continue;
	    		listEntity = new EnumEntityList(EnumType.getEnumTypeByCode(typeName));
	    		listMap.put(typeName, listEntity);
	    	}
	    	EnumEntityImpl entity = new EnumEntityImpl();
	    	entity.setCode(rs.getFields().get(1).getString());
	    	entity.setName(rs.getFields().get(2).getString());
	    	EnumEntityImpl parent = entityMap.get(rs.getFields().get(3).getString());
	    	if(parent!=null){
	    		parent.addChild(entity);
	    	}
	    	listEntity.putEntity(entity.getCode(), entity);
	    	entityMap.put(entity.getCode(), entity);
	    	result.add(listEntity);
	    }
	    return result;
    }
	
	final class GetEnumEntityResourceByTypeCodeProvider extends OneKeyResourceProvider<EnumType>{

		@Override
        protected EnumType getKey1(EnumEntityList keysHolder){
	        return keysHolder.getType();
        }		
	}
	

	@Publish
	protected final class GetEnumEntitysByTypeProvider extends OneKeyResultProvider<EnumEntity[],EnumType>{

		@Override
        protected EnumEntity[] provide(
                ResourceContext<EnumEntityList, EnumEntityList, EnumEntityList> context,
                EnumType key) throws Throwable
        {
			EnumEntityList eel = context.find(EnumEntityList.class,key);
			if(eel!=null)
				return eel.getList();
	        return new EnumEntity[0];
        }
		
	}
//
//	/**
//	 * 查询枚举记录
//	 */
//	@Publish
//	protected class EnumEntityListQuery extends
//			OneKeyResultListProvider<EnumEntity, EnumType> {
//
//		@Override
//		protected void provide(ResourceContext<EnumEntityList, EnumEntityList, EnumEntityList> context, EnumType type,
//				List<EnumEntity> resultList) throws Throwable {
//			EnumEntityList listEntity = context.find(EnumEntityList.class,
//					type.getTypeName());
//			for (EnumEntity enumEntity : listEntity.getList()) {
//				resultList.add(enumEntity);
//			}
//		}
//	}

	/**
	 * 查询枚举记录：通过父节点Code查询
	 */
	@Publish
	protected class EnumEntityListQuery2 extends
			TwoKeyResultProvider<EnumEntity[], EnumType, String> {

		@Override
		protected EnumEntity[] provide(ResourceContext<EnumEntityList, EnumEntityList, EnumEntityList> context, EnumType type,
				String parentCode)
				throws Throwable {
			
			EnumEntityList listEntity = context.find(EnumEntityList.class,type);
			List<EnumEntityImpl> resultList = listEntity.getEnumEntity(parentCode).getChildren();
			return resultList.toArray(new EnumEntity[resultList.size()]);
		}
	}

	@Publish
	protected class EnumEneityQuery extends
			TwoKeyResultProvider<EnumEntity, String, EnumType> {

		@Override
		protected EnumEntity provide(ResourceContext<EnumEntityList, EnumEntityList, EnumEntityList> context, String code, EnumType type)
				throws Throwable {
			return context.find(EnumEntityList.class, type.getTypeName())
					.getEnumEntity(code);
		}

	}

}
