/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-16    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-16    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

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
import com.spark.psi.base.Goods;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.GoodsCategoryImpl;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.key.goods.GetChildrenGoodsCategoryListKey;
import com.spark.psi.base.task.resource.UpdateGoodsCategoryResourceTask;

/**
 * <p>
 * 商品分类资源服务
 * </p>
 *  相关引用资源
 *  	Goods
 *  	GoodsItem
 *  	GoodsCategory
 *  父类资源
 *  	Tenant
 *  	GoodsCategory
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-3-16
 */

public class GoodsCategoryResourceService extends
        ResourceService<GoodsCategory, GoodsCategoryImpl, GoodsCategoryImpl>
{

	protected GoodsCategoryResourceService(){
		super(
		        "com.spark.psi.base.internal.service.GoodsCategroyResourceService",
		        ResourceKind.SINGLETON_IN_CLUSTER);
	}

	protected class GoodsItemGroupResourceReference extends
	        ResourceReference<GoodsItem>
	{
	}

	protected class GoodsGroupResourceReference extends
	        ResourceReference<Goods>
	{
	}

	protected class GoodsCategoryGroupResourceReference extends
	        ResourceReference<GoodsCategory>
	{
	}

	@Override
	protected void init(Context context){
		context.getList(GoodsCategory.class);
	}

	@Override
	protected void initResources(
	        Context context,
	        ResourceInserter<GoodsCategory, GoodsCategoryImpl, GoodsCategoryImpl> initializer)
	{
		List<Tenant> tenants = context.getList(Tenant.class);
		for(Tenant tenant : tenants){
			List<GoodsCategoryImpl> list = getAllGoodsCategoryList(context,tenant.getId());
			Map<GUID, ResourceToken<GoodsCategory>> allTokens =
				new HashMap<GUID, ResourceToken<GoodsCategory>>();
			for(GoodsCategoryImpl goodsCategoryImpl : list){
				ResourceToken<GoodsCategory> token =
					initializer.putResource(goodsCategoryImpl);
				allTokens.put(goodsCategoryImpl.getId(), token);
//				ResourceToken<Tenant> parentToken =
//					context.findResourceToken(Tenant.class, goodsCategoryImpl
//							.getTenantId());
//				if(parentToken == null) continue;
//				initializer.putResourceReference(parentToken, token);
			}
			Iterator<GUID> it = allTokens.keySet().iterator();
			while(it.hasNext()){
				GUID id = it.next();
				ResourceToken<GoodsCategory> token = allTokens.get(id);
				if(token.getFacade().getParent() != null){
					ResourceToken<GoodsCategory> parentToken =
						allTokens.get(token.getFacade().getParent());
					if(parentToken != null){
						initializer.putResourceReference(parentToken, token);
					}
				}
			}	        
        }
	}

	@Publish
	final protected class GetChildrenGoodsCategoryListProvider
	        extends
	        OneKeyResultListProvider<GoodsCategory, GetChildrenGoodsCategoryListKey>
	{

		@Override
		protected void provide(
		        ResourceContext<GoodsCategory, GoodsCategoryImpl, GoodsCategoryImpl> context,
		        GetChildrenGoodsCategoryListKey key,
		        List<GoodsCategory> resultList) throws Throwable
		{
			resultList.addAll(context.getResourceReferences(
			        GoodsCategory.class, context.findResourceToken(
			                GoodsCategory.class, key.getParentId())));
		}

	}

	private List<GoodsCategoryImpl> getAllGoodsCategoryList(Context context, GUID guid){
		List<GoodsCategoryImpl> result = new ArrayList<GoodsCategoryImpl>();
		StringBuffer sql = new StringBuffer("define query getChildren()");
		sql.append(" begin");
//		sql
//		        .append(" select ")
//		        .append(" a.RECID as recid,")
//		        .append(" a.goodsTypeName as goodsTypeName,")
//		        .append(" c.yzFlag as yzFlag,")
//		        .append(" a.goodsCountDigit as goodsCountDigit,")
//		        .append(" a.reflag as reflag,")
//		        .append(" a.setPropertyFlag as setPropertyFlag, ")
//		        .append(" a.deleteFlag as deleteFlag, ")
//		        .append(" a.tenantsGuid as tenantsGuid,")
//		        .append(" a.parentGuid as parent,")
//		        .append(" a.path as path,")
//		        .append(" b.storeAmountUpper as storeAmountUpper, ")
//		        .append(" a.createDate as createDate ")
//		        .append(" from sa_goods_type as a left join sa_goods_type_tongji as b on a.recid = b.goodsTypeGuid LEFT JOIN (SELECT COUNT(1) as yzFlag,b.PARENTGUID FROM sa_goods_type as b where b.tenantsGuid = @tenantId GROUP BY b.PARENTGUID) as c ON a.recid = c.PARENTGUID where a.tenantsGuid = @tenantId order by a.path asc ")
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
				
			    .append(" from " + ERPTableNames.Base.GoodsCategory.getTableName() + " as a " +
			    		"LEFT JOIN (SELECT COUNT(1) as yzFlag,b.PARENTID FROM " + ERPTableNames.Base.GoodsCategory.getTableName() + " as b GROUP BY b.PARENTID) as c ON a.recid = c.PARENTID " +
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

	private GoodsCategoryImpl getGoodsCategoryById(Context context, GUID id){
		StringBuffer sql =
		        new StringBuffer("define query getChildren(@id guid)");
		sql.append(" begin");
//		sql
//		        .append(" select ")
//		        .append(" a.RECID as recid,")
//		        .append(" a.goodsTypeName as goodsTypeName,")
//		        .append(" c.yzFlag as yzFlag,")
//		        .append(" a.goodsCountDigit as goodsCountDigit,")
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
		                " from " + ERPTableNames.Base.GoodsCategory.getTableName() + " as a " +
		                "LEFT JOIN (SELECT COUNT(1) as yzFlag,b.PARENTID FROM " + ERPTableNames.Base.GoodsCategory.getTableName() + " as b " +
		                "GROUP BY b.PARENTID) as c ON a.recid = c.PARENTID  where a.recid = @id ").append("order by a.categoryNo asc ")
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

	GoodsCategoryImpl fullData(RecordSet rs){
		GoodsCategoryImpl goodsType = new GoodsCategoryImpl();
//		int i = 0;
		goodsType.setId(rs.getFields().get(0).getGUID());
		goodsType.setCategoryNo(rs.getFields().get(1).getString());
		goodsType.setName(rs.getFields().get(2).getString());
		Object isLeaf = rs.getFields().get(3).getObject();
		if(isLeaf==null){
			goodsType.setLeafNode(true);
		}else {
			goodsType.setLeafNode(false);
		}
		goodsType.setParentId(rs.getFields().get(4).getGUID());
		goodsType.setPath(new String(rs.getFields().get(5).getBytes()));
		goodsType.setCountDecimal(rs.getFields().get(6).getInt());
		goodsType.setValid(rs.getFields().get(7).getBoolean());
		goodsType.setReflag(rs.getFields().get(8).getBoolean());
		goodsType.setPropertyFlag(rs.getFields().get(9).getBoolean());
		goodsType.setCreateDate(rs.getFields().get(10).getDate());
		
		
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
		
		return goodsType;
	}

	final class GetGoodsCategoryResourceById extends
	        OneKeyResourceProvider<GUID>
	{

		@Override
		protected GUID getKey1(GoodsCategoryImpl keysHolder){
			return keysHolder.getId();
		}
	}


	@Publish
	protected final class PutGoodsCategoryResourceHandler
	        extends
	        TaskMethodHandler<UpdateGoodsCategoryResourceTask, UpdateGoodsCategoryResourceTask.Method>
	{

		protected PutGoodsCategoryResourceHandler(){
			super(UpdateGoodsCategoryResourceTask.Method.Put);
		}

		@Override
		protected void handle(
		        ResourceContext<GoodsCategory, GoodsCategoryImpl, GoodsCategoryImpl> context,
		        UpdateGoodsCategoryResourceTask task) throws Throwable
		{
			
			ResourceToken<Tenant> tenantToken = TenantHelper.getTenantToken(context);
			GoodsCategoryImpl category = getGoodsCategoryById(context, task.id);
			ResourceToken<GoodsCategory> parentToken =
			        context.findResourceToken(GoodsCategory.class, category
			                .getParent());
			ResourceToken<GoodsCategory> token = context.putResource(category);
			context.putResourceReference(tenantToken, token);
			if(parentToken!=null){
				context.putResourceReference(parentToken, token);
				context.handle(new UpdateGoodsCategoryResourceTask(category.getParent()),UpdateGoodsCategoryResourceTask.Method.Modify);
			}
		}

	}

	@Publish
	protected final class ModifyGoodsCategoryResourceHandler
	        extends
	        TaskMethodHandler<UpdateGoodsCategoryResourceTask, UpdateGoodsCategoryResourceTask.Method>
	{

		protected ModifyGoodsCategoryResourceHandler(){
			super(UpdateGoodsCategoryResourceTask.Method.Modify);
		}

		@Override
		protected void handle(
		        ResourceContext<GoodsCategory, GoodsCategoryImpl, GoodsCategoryImpl> context,
		        UpdateGoodsCategoryResourceTask task) throws Throwable
		{
			GoodsCategoryImpl goodsType = context.modifyResource(task.id);
			GoodsCategoryImpl _new = getGoodsCategoryById(context, task.id);
			goodsType.setId(_new.getId());
			goodsType.setName(_new.getName());
			goodsType.setLeafNode(_new.isLeafNode());
			goodsType.setCountDecimal(_new.getScale());
			goodsType.setReflag(_new.isReflag());
			goodsType.setPropertyFlag(_new.isPropertyFlag());
			goodsType.setValid(_new.isValid());
			goodsType.setTenantId(_new.getTenantId());
			goodsType.setPath(_new.getPath());
			goodsType.setCategoryNo(_new.getCategoryNo());
			context.postModifiedResource(goodsType);
		}
	}

	@Publish
	protected final class RemoveGoodsCategoryResourceHandler
	        extends
	        TaskMethodHandler<UpdateGoodsCategoryResourceTask, UpdateGoodsCategoryResourceTask.Method>
	{

		protected RemoveGoodsCategoryResourceHandler(){
			super(UpdateGoodsCategoryResourceTask.Method.Remove);
		}

		@Override
		protected void handle(
		        ResourceContext<GoodsCategory, GoodsCategoryImpl, GoodsCategoryImpl> context,
		        UpdateGoodsCategoryResourceTask task) throws Throwable
		{
			GoodsCategoryImpl category =
			        (GoodsCategoryImpl)context.find(GoodsCategory.class,
			                task.id);
			ResourceToken<GoodsCategory> token = context.findResourceToken(
			        GoodsCategory.class, category.getId());
//			ResourceToken<Tenant> tenantToken =
//			        context.findResourceToken(Tenant.class, category
//			                .getTenantId());

			if(!category.getParent().equals(GoodsCategory.ROOT.getId())){
				ResourceToken<GoodsCategory> parentToken =
				        context.findResourceToken(GoodsCategory.class, category
				                .getParent());
				context.removeResourceReference(parentToken,token);
				context.handle(new UpdateGoodsCategoryResourceTask(category.getParent()),UpdateGoodsCategoryResourceTask.Method.Modify);
			}
//			context.removeResourceReference(tenantToken, token);
			context.removeResource(task.id);
		}

	}

}
