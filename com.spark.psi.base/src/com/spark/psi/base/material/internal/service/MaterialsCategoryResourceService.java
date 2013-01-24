package com.spark.psi.base.material.internal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.psi.base.Materials;
import com.spark.psi.base.MaterialsCategory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.MaterialsCategoryImpl;
import com.spark.psi.base.key.materials.GetChildrenMaterialsCategoryListKey;
import com.spark.psi.base.task.resource.UpdateMaterialsCategoryResourceTask;

/**
 * <p>
 * 商品分类资源服务
 * </p>
 *  相关引用资源
 *  	Materials
 *  	MaterialsItem
 *  	MaterialsCategory
 *  父类资源
 *  	MaterialsCategory
 * 
 */

public class MaterialsCategoryResourceService extends
        ResourceService<MaterialsCategory, MaterialsCategoryImpl, MaterialsCategoryImpl>
{

	protected MaterialsCategoryResourceService(){
		super(
		        "com.spark.psi.base.internal.service.MaterialsCategroyResourceService",
		        ResourceKind.SINGLETON_IN_CLUSTER);
	}

	protected class MaterialsItemGroupResourceReference extends
	        ResourceReference<MaterialsItem>
	{
	}

	protected class MaterialsGroupResourceReference extends
	        ResourceReference<Materials>
	{
	}

	protected class MaterialsCategoryGroupResourceReference extends
	        ResourceReference<MaterialsCategory>
	{
	}

	@Override
	protected void init(Context context){
		context.getList(MaterialsCategory.class);
	}

	@Override
	protected void initResources(
	        Context context,
	        ResourceInserter<MaterialsCategory, MaterialsCategoryImpl, MaterialsCategoryImpl> initializer)
	{
		List<Tenant> tenants = context.getList(Tenant.class);
		for(Tenant tenant : tenants){
			List<MaterialsCategoryImpl> list = getAllMaterialsCategoryList(context,tenant.getId());
			Map<GUID, ResourceToken<MaterialsCategory>> allTokens =
				new HashMap<GUID, ResourceToken<MaterialsCategory>>();
			for(MaterialsCategoryImpl MaterialsCategoryImpl : list){
				ResourceToken<MaterialsCategory> token =
					initializer.putResource(MaterialsCategoryImpl);
				allTokens.put(MaterialsCategoryImpl.getId(), token);
//				ResourceToken<Tenant> parentToken =
//					context.findResourceToken(Tenant.class, MaterialsCategoryImpl
//							.getTenantId());
//				if(parentToken == null) continue;
//				initializer.putResourceReference(parentToken, token);
			}
			Iterator<GUID> it = allTokens.keySet().iterator();
			while(it.hasNext()){
				GUID id = it.next();
				ResourceToken<MaterialsCategory> token = allTokens.get(id);
				if(token.getFacade().getParent() != null){
					ResourceToken<MaterialsCategory> parentToken =
						allTokens.get(token.getFacade().getParent());
					if(parentToken != null){
						initializer.putResourceReference(parentToken, token);
					}
				}
			}	        
        }
	}

	@Publish
	final protected class GetChildrenMaterialsCategoryListProvider
	        extends
	        OneKeyResultListProvider<MaterialsCategory, GetChildrenMaterialsCategoryListKey>
	{

		@Override
		protected void provide(
		        ResourceContext<MaterialsCategory, MaterialsCategoryImpl, MaterialsCategoryImpl> context,
		        GetChildrenMaterialsCategoryListKey key,
		        List<MaterialsCategory> resultList) throws Throwable
		{
			resultList.addAll(context.getResourceReferences(
			        MaterialsCategory.class, context.findResourceToken(
			                MaterialsCategory.class, key.getParentId())));
		}

	}

	private List<MaterialsCategoryImpl> getAllMaterialsCategoryList(Context context, GUID guid){
		List<MaterialsCategoryImpl> result = new ArrayList<MaterialsCategoryImpl>();
		StringBuffer sql = new StringBuffer("define query getChildren()");
		sql.append(" begin");
//		sql
//		        .append(" select ")
//		        .append(" a.RECID as recid,")
//		        .append(" a.MaterialsTypeName as MaterialsTypeName,")
//		        .append(" c.yzFlag as yzFlag,")
//		        .append(" a.MaterialsCountDigit as MaterialsCountDigit,")
//		        .append(" a.reflag as reflag,")
//		        .append(" a.setPropertyFlag as setPropertyFlag, ")
//		        .append(" a.deleteFlag as deleteFlag, ")
//		        .append(" a.tenantsGuid as tenantsGuid,")
//		        .append(" a.parentGuid as parent,")
//		        .append(" a.path as path,")
//		        .append(" b.storeAmountUpper as storeAmountUpper, ")
//		        .append(" a.createDate as createDate ")
//		        .append(" from sa_Materials_type as a left join sa_Materials_type_tongji as b on a.recid = b.MaterialsTypeGuid LEFT JOIN (SELECT COUNT(1) as yzFlag,b.PARENTGUID FROM sa_Materials_type as b where b.tenantsGuid = @tenantId GROUP BY b.PARENTGUID) as c ON a.recid = c.PARENTGUID where a.tenantsGuid = @tenantId order by a.path asc ")
//		        .append(" end");
		sql
				.append(" select ")
				.append(" a.RECID as recid, ")
				.append(" a.categoryNo as categoryNo,")
				.append(" a.categoryName as categoryName,")
				.append(" c.yzFlag as yzFlag,")
				.append(" a.parentId as parentId,")
				.append(" a.path as path,")
//				.append(" a.leafFlag as leafFlag,")
				.append(" a.scale as scale,")
				.append(" a.canDelete as canDelete,")
				.append(" a.reflag as reflag,")
				.append(" a.setPropertyFlag as setPropertyFlag,")
//				.append(" a.properties as properties,")
				.append(" a.createDate as createDate,")
				.append(" a.lastModifyDate as lastModifyDate,")
				.append(" a.lastModifyPerson as lastModifyPerson,")
				.append(" a.creatorId as creatorId,")
				.append(" a.creator as creator")
				
			    .append(" from " + ERPTableNames.Base.MaterialsCategory.getTableName() + " as a " +
			    		"LEFT JOIN (SELECT COUNT(1) as yzFlag,b.PARENTID FROM " + ERPTableNames.Base.MaterialsCategory.getTableName() + " as b GROUP BY b.PARENTID) as c ON a.recid = c.PARENTID " +
			    		"order by a.categoryNo asc ")
			    .append(" end");

		DBCommand dbc = context.prepareStatement(sql);
		dbc.setArgumentValues(guid);
		RecordSet rs = dbc.executeQuery();
		while(rs.next()){
			result.add(fullData(rs));
		}
		return result;
	}

	private MaterialsCategoryImpl getMaterialsCategoryById(Context context, GUID id){
		StringBuffer sql =
		        new StringBuffer("define query getChildren(@id guid)");
		sql.append(" begin");
//		sql
//		        .append(" select ")
//		        .append(" a.RECID as recid,")
//		        .append(" a.MaterialsTypeName as MaterialsTypeName,")
//		        .append(" c.yzFlag as yzFlag,")
//		        .append(" a.MaterialsCountDigit as MaterialsCountDigit,")
//		        .append(" a.reflag as reflag,")
//		        .append(" a.setPropertyFlag as setPropertyFlag, ")
//		        .append(" a.deleteFlag as deleteFlag, ")
//		        .append(" a.tenantsGuid as tenantsGuid,")
//		        .append(" a.parentGuid as parent,")
//		        .append(" a.path as path,")
//		        .append(" b.storeAmountUpper as storeAmountUpper, ")
//		        .append(" a.createDate as createDate ")
		        
        sql
				.append(" select ")
				.append(" a.RECID as recid, ")
				.append(" a.categoryNo as categoryNo,")
				.append(" a.categoryName as categoryName,")
				.append(" c.yzFlag as yzFlag,")
				.append(" a.parentId as parentId,")
				.append(" a.path as path,")
//				.append(" a.leafFlag as leafFlag,")
				.append(" a.scale as scale,")
				.append(" a.canDelete as canDelete,")
				.append(" a.reflag as reflag,")
				.append(" a.setPropertyFlag as setPropertyFlag,")
//				.append(" a.properties as properties,")
				.append(" a.createDate as createDate,")
				.append(" a.lastModifyDate as lastModifyDate,")
				.append(" a.lastModifyPerson as lastModifyPerson,")
				.append(" a.creatorId as creatorId,")
				.append(" a.creator as creator")
		        .append(
		                " from " + ERPTableNames.Base.MaterialsCategory.getTableName() + " as a " +
		                "LEFT JOIN (SELECT COUNT(1) as yzFlag,b.PARENTID FROM " + ERPTableNames.Base.MaterialsCategory.getTableName() + " as b " +
		                "GROUP BY b.PARENTID) as c ON a.recid = c.PARENTID  where a.recid = @id ").append(" order by a.categoryNo asc ")
		        .append(" end");

		DBCommand dbc = context.prepareStatement(sql);
		dbc.setArgumentValue(0, id);
		RecordSet rs = dbc.executeQuery();
		if(rs.next()){
			return fullData(rs);
		}
		else{
			return null;
		}
	}

	MaterialsCategoryImpl fullData(RecordSet rs){
		MaterialsCategoryImpl MaterialsType = new MaterialsCategoryImpl();
//		int i = 0;
		MaterialsType.setId(rs.getFields().get(0).getGUID());
		MaterialsType.setCategoryNo(rs.getFields().get(1).getString());
		MaterialsType.setName(rs.getFields().get(2).getString());
		Object isLeaf = rs.getFields().get(3).getObject();
		if(isLeaf==null){
			MaterialsType.setLeafNode(true);
		}else {
			MaterialsType.setLeafNode(false);
		}
		MaterialsType.setParentId(rs.getFields().get(4).getGUID());
		MaterialsType.setPath(new String(rs.getFields().get(5).getBytes()));
		MaterialsType.setCountDecimal(rs.getFields().get(6).getInt());
		MaterialsType.setValid(rs.getFields().get(7).getBoolean());
		MaterialsType.setReflag(rs.getFields().get(8).getBoolean());
		MaterialsType.setPropertyFlag(rs.getFields().get(9).getBoolean());
		MaterialsType.setCreateDate(rs.getFields().get(10).getDate());
		
		
//		.append(" a.RECID as recid, ")
//		.append(" a.categoryNo as categoryNo,")
//		.append(" a.categoryName as categoryName,")
//		.append(" c.yzFlag as yzFlag,")
//		.append(" a.parentId as parentId,")
//		.append(" a.path as path,")
////		.append(" a.leafFlag as leafFlag,")
//		.append(" a.scale as scale,")
//		.append(" a.canDalete as canDalete,")
//		.append(" a.reflag as reflag,")
//		.append(" a.setPropertyFlag as setPropertyFlag,")
////		.append(" a.properties as properties,")
//		.append(" a.createDate as createDate,")
//		.append(" a.lastModifyDate as lastModifyDate,")
//		.append(" a.lastModifyPerson as lastModifyPerson,")
//		.append(" a.creatorId as creatorId,")
//		.append(" a.creator as creator")
		
		return MaterialsType;
	}

	final class GetMaterialsCategoryResourceById extends
	        OneKeyResourceProvider<GUID>
	{

		@Override
		protected GUID getKey1(MaterialsCategoryImpl keysHolder){
			return keysHolder.getId();
		}
	}


	@Publish
	protected final class PutMaterialsCategoryResourceHandler
	        extends
	        TaskMethodHandler<UpdateMaterialsCategoryResourceTask, UpdateMaterialsCategoryResourceTask.Method>
	{

		protected PutMaterialsCategoryResourceHandler(){
			super(UpdateMaterialsCategoryResourceTask.Method.Put);
		}

		@Override
		protected void handle(
		        ResourceContext<MaterialsCategory, MaterialsCategoryImpl, MaterialsCategoryImpl> context,
		        UpdateMaterialsCategoryResourceTask task) throws Throwable
		{
			
//			ResourceToken<Tenant> tenantToken = TenantHelper.getTenantToken(context);
			MaterialsCategoryImpl category = getMaterialsCategoryById(context, task.id);
			ResourceToken<MaterialsCategory> parentToken =
			        context.findResourceToken(MaterialsCategory.class, category
			                .getParent());
			ResourceToken<MaterialsCategory> token = context.putResource(category);
//			context.putResourceReference(tenantToken, token);
			if(parentToken!=null){
				context.putResourceReference(parentToken, token);
				context.handle(new UpdateMaterialsCategoryResourceTask(category.getParent()),UpdateMaterialsCategoryResourceTask.Method.Modify);
			}
		}

	}

	@Publish
	protected final class ModifyMaterialsCategoryResourceHandler
	        extends
	        TaskMethodHandler<UpdateMaterialsCategoryResourceTask, UpdateMaterialsCategoryResourceTask.Method>
	{

		protected ModifyMaterialsCategoryResourceHandler(){
			super(UpdateMaterialsCategoryResourceTask.Method.Modify);
		}

		@Override
		protected void handle(
		        ResourceContext<MaterialsCategory, MaterialsCategoryImpl, MaterialsCategoryImpl> context,
		        UpdateMaterialsCategoryResourceTask task) throws Throwable
		{
			MaterialsCategoryImpl MaterialsType = context.modifyResource(task.id);
			MaterialsCategoryImpl _new = getMaterialsCategoryById(context, task.id);
			MaterialsType.setId(_new.getId());
			MaterialsType.setName(_new.getName());
			MaterialsType.setLeafNode(_new.isLeafNode());
			MaterialsType.setCountDecimal(_new.getScale());
			MaterialsType.setReflag(_new.isReflag());
			MaterialsType.setPropertyFlag(_new.isPropertyFlag());
			MaterialsType.setValid(_new.isValid());
			MaterialsType.setTenantId(_new.getTenantId());
			MaterialsType.setPath(_new.getPath());
			MaterialsType.setCategoryNo(_new.getCategoryNo());
			context.postModifiedResource(MaterialsType);
		}
	}

	@Publish
	protected final class RemoveMaterialsCategoryResourceHandler
	        extends
	        TaskMethodHandler<UpdateMaterialsCategoryResourceTask, UpdateMaterialsCategoryResourceTask.Method>
	{

		protected RemoveMaterialsCategoryResourceHandler(){
			super(UpdateMaterialsCategoryResourceTask.Method.Remove);
		}

		@Override
		protected void handle(
		        ResourceContext<MaterialsCategory, MaterialsCategoryImpl, MaterialsCategoryImpl> context,
		        UpdateMaterialsCategoryResourceTask task) throws Throwable
		{
			MaterialsCategoryImpl category =
			        (MaterialsCategoryImpl)context.find(MaterialsCategory.class,
			                task.id);
			ResourceToken<MaterialsCategory> token = context.findResourceToken(
			        MaterialsCategory.class, category.getId());
//			ResourceToken<Tenant> tenantToken =
//			        context.findResourceToken(Tenant.class, category
//			                .getTenantId());

			if(!category.getParent().equals(MaterialsCategory.ROOT.getId())){
				ResourceToken<MaterialsCategory> parentToken =
				        context.findResourceToken(MaterialsCategory.class, category
				                .getParent());
				context.removeResourceReference(parentToken,token);
				context.handle(new UpdateMaterialsCategoryResourceTask(category.getParent()),UpdateMaterialsCategoryResourceTask.Method.Modify);
			}
//			context.removeResourceReference(tenantToken, token);
			context.removeResource(task.id);
		}

	}

}
