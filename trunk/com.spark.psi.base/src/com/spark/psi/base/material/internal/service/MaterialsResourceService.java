package com.spark.psi.base.material.internal.service;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.Filter;
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
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.MaterialsImpl;
import com.spark.psi.base.internal.entity.helper.MaterialsHelper;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.internal.service.orm.Orm_Materials;
import com.spark.psi.base.publicimpl.MaterialsInfoImpl;
import com.spark.psi.base.publicimpl.MaterialsItemDataImpl;
import com.spark.psi.base.task.resource.UpdateMaterialsItemResourceTask;
import com.spark.psi.base.task.resource.UpdateMaterialsResourceTask;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.key.GetMaterialsItemByNoKey;

/**
 * <p>
 * 商品资源服务
 * </p>
 * 
 */

public class MaterialsResourceService extends ResourceService<Materials, MaterialsImpl, MaterialsImpl> {

	final Orm_Materials orm_Materials;

	protected MaterialsResourceService(final Orm_Materials orm_Materials) {
		super("com.spark.psi.base.internal.service.MaterialsResourceService", ResourceKind.SINGLETON_IN_CLUSTER);
		this.orm_Materials = orm_Materials;
	}

	protected class MaterialsItemGroupResourceReference extends ResourceReference<MaterialsItem> {
	}

	@Override
	protected void init(Context context) {
		context.getList(Materials.class);
	}

	@Override
	protected void initResources(Context context, ResourceInserter<Materials, MaterialsImpl, MaterialsImpl> initializer) {
		List<MaterialsImpl> list = getAllMaterialsList(context);
		for (MaterialsImpl MaterialsImpl : list) {
			ResourceToken<MaterialsCategory> parentToken = context.findResourceToken(MaterialsCategory.class, MaterialsImpl.getCategoryId());
			// ResourceToken<Tenant> tenantToken =
			// context.findResourceToken(Tenant.class,MaterialsImpl.getTenantId());
			ResourceToken<Materials> token = initializer.putResource(MaterialsImpl);
			initializer.putResourceReference(parentToken, token);
			// initializer.putResourceReference(tenantToken,token);
		}
	}

	private List<MaterialsImpl> getAllMaterialsList(Context context) {
		List<MaterialsImpl> list = new ArrayList<MaterialsImpl>();
		StringBuffer sql = new StringBuffer("define query getPropertyByMaterialsGuid()");
		sql.append(" begin");
		sql.append(" select ");
		sql.append(" t.RECID as id,");
		sql.append(" t.materialCode as materialCode,");
		sql.append(" t.materialName as materialName,");
		sql.append(" t.namePY as namePY,");
		sql.append(" t.categoryId as categoryId,");
		sql.append(" t.remark as remark,");
		sql.append(" t.canDelete as canDelete,");
		sql.append(" t.refFlag as refFlag,");
		sql.append(" t.inventoryWarningType as inventoryWarningType,");
		sql.append(" t.createDate as createDate,");
		sql.append(" t.lastModifyDate as lastModifyDate,");
		sql.append(" t.lastModifyPerson as lastModifyPerson,");
		sql.append(" t.creatorId as creatorId,");
		sql.append(" t.creator as creator,");
		sql.append(" t.status as status,");
		sql.append(" t.shelfLife as shelfLife,");
		sql.append(" t.warningDay as warningDay");
		sql.append(" ,t.isJointVenture  as isJointVenture ");
		sql.append(" ,t.supplierId as supplierId");
		sql.append(" ,t.percentage as percentage");

		sql.append(" from ");
		sql.append(ERPTableNames.Base.Materials.getTableName());
		sql.append(" as t ");
		sql.append(" order by t.recid asc ");
		sql.append(" end");

		DBCommand dbc = context.prepareStatement(sql);
		RecordSet rs = dbc.executeQuery();
		while (rs.next()) {
			list.add(fullData(context, rs));
		}
		return list;
	}

	private MaterialsImpl getMaterialsById(Context context, GUID id) {
		// ORMAccessor<MaterialsOrmEntity> orm =
		// context.newORMAccessor(orm_Materials);
		// MaterialsOrmEntity e = null;
		// try
		// {
		// e = orm.findByRECID(id);
		// if(null!=e)
		// {
		// MaterialsImpl i = new MaterialsImpl();
		// i.setCanDalete(e.isCanDalete());
		// i.setCategoryId(e.getCategoryId());
		// i.setCreateDate(e.getCreateDate());
		// i.setCreator(e.getCreator());
		// i.setCreatorId(e.getCreatorId());
		// i.setMaterialsCode(e.getMaterialsCode());
		// i.setMaterialsName(e.getMaterialsName());
		// i.setId(e.getId());
		// i.setInventoryWarningType(InventoryWarningType.getMaterialsWarnningType(e.getInventoryWarningType()));
		// i.setJointVenture(e.isJointVenture());
		// i.setLastModifyDate(e.getLastModifyDate());
		// i.setLastModifyPerson(e.getLastModifyPerson());
		// i.setNamePY(e.getNamePY());
		// i.setRefFlag(e.isRefFlag());
		// i.setRemark(e.getRemark());
		// i.setSalePrice(e.getSalePrice());
		// i.setShelfLife(e.getShelfLife());
		// i.setStatus(MaterialsStatus.getMaterialsStatus(e.getStatus()));
		// i.setSupplierId(e.getSupplierId());
		// i.setWarningDay(e.getWarningDay());
		// return i;
		// }
		// return null;
		// }
		// finally
		// {
		// orm.unuse();
		// }

		StringBuffer sql = new StringBuffer("define query getPropertyByMaterialsGuid(@id guid)");
		sql.append(" begin");
		sql.append(" select ");
		sql.append(" t.RECID as id,");
		sql.append(" t.materialCode as materialCode,");
		sql.append(" t.materialName as materialName,");
		sql.append(" t.namePY as namePY,");
		sql.append(" t.categoryId as categoryId,");
		sql.append(" t.remark as remark,");
		sql.append(" t.canDelete as canDelete,");
		sql.append(" t.refFlag as refFlag,");
		sql.append(" t.inventoryWarningType as inventoryWarningType,");
		sql.append(" t.createDate as createDate,");
		sql.append(" t.lastModifyDate as lastModifyDate,");
		sql.append(" t.lastModifyPerson as lastModifyPerson,");
		sql.append(" t.creatorId as creatorId,");
		sql.append(" t.creator as creator,");
		sql.append(" t.status as status,");
		sql.append(" t.shelfLife as shelfLife,");
		sql.append(" t.warningDay as warningDay,");
		sql.append(" t.isJointVenture as isJointVenture,");
		sql.append(" t.supplierId as supplierId,");
		sql.append(" t.percentage as percentage");
		sql.append(" from ");
		sql.append(ERPTableNames.Base.Materials.getTableName());
		sql.append(" as t ");
		sql.append(" where t.recid=@id ");
		sql.append(" end");

		DBCommand dbc = context.prepareStatement(sql);
		dbc.setArgumentValue(0, id);
		RecordSet rs = dbc.executeQuery();
		if (rs.next()) {
			return fullData(context, rs);
		} else {
			return null;
		}
	}

	private MaterialsImpl fullData(Context context, RecordSet rs) {
		int i = 0;
		MaterialsImpl info = new MaterialsImpl();
		info.setId(rs.getFields().get(i++).getGUID());
		info.setMaterialCode(rs.getFields().get(i++).getString());
		info.setMaterialName(rs.getFields().get(i++).getString());
		info.setNamePY(rs.getFields().get(i++).getString());
		info.setCategoryId(rs.getFields().get(i++).getGUID());
		info.setRemark(rs.getFields().get(i++).getString());
		info.setCanDalete(rs.getFields().get(i++).getBoolean());
		info.setRefFlag(rs.getFields().get(i++).getBoolean());
		info.setInventoryWarningType(InventoryWarningType.getGoodsWarnningType(rs.getFields().get(i++).getString()));
		info.setCreateDate(rs.getFields().get(i++).getDate());
		info.setLastModifyDate(rs.getFields().get(i++).getDate());
		info.setLastModifyPerson(rs.getFields().get(i++).getString());
		info.setCreatorId(rs.getFields().get(i++).getGUID());
		info.setCreator(rs.getFields().get(i++).getString());
		info.setStatus(MaterialsStatus.getMaterialsStatus(rs.getFields().get(i++).getString()));
		info.setShelfLife(rs.getFields().get(i++).getInt());
		info.setWarningDay(rs.getFields().get(i++).getInt());
		info.setJointVenture(rs.getFields().get(i++).getBoolean());
		info.setSupplierId(rs.getFields().get(i++).getGUID());
		info.setPercentage(rs.getFields().get(i++).getDouble());
		return info;
	}

	// final class GetMaterialsResourceByCode extends
	// TwoKeyResourceProvider<GUID,String>{
	//
	// @Override
	// protected GUID getKey1(MaterialsImpl keysHolder){
	// return keysHolder.getTenantId();
	// }
	//
	// @Override
	// protected String getKey2(MaterialsImpl keysHolder){
	// // TODO Auto-generated method stub
	// return keysHolder.getCode();
	// }
	//		
	// }

	final class GetMaterialsResourceById extends OneKeyResourceProvider<GUID> {

		@Override
		protected GUID getKey1(MaterialsImpl keysHolder) {
			return keysHolder.getId();
		}
	}

	// @Publish
	// final protected class GetMaterialsImplById extends
	// OneKeyResultProvider<MaterialsImpl,GUID>{
	//
	// @Override
	// protected MaterialsImpl provide(
	// ResourceContext<Materials, MaterialsImpl, MaterialsImpl> context, GUID
	// key)
	// throws Throwable
	// {
	// ResourceToken<MaterialsImpl> token =
	// context.findResourceToken(Materials.class,key);
	// return token != null ? token.getFacade() : null;
	// }
	// }

	// @Publish
	// final protected class GetMaterialsById extends
	// OneKeyResultProvider<Materials,GUID>{
	//
	// @Override
	// protected Materials provide(
	// ResourceContext<MaterialsImpl, MaterialsImpl, MaterialsImpl> context,
	// GUID key)
	// throws Throwable
	// {
	// return context.find(MaterialsImpl.class,key);
	// }
	// }
	//

	@Publish
	protected final class PutMaterialsResourceHandler extends TaskMethodHandler<UpdateMaterialsResourceTask, UpdateMaterialsResourceTask.Method> {

		protected PutMaterialsResourceHandler() {
			super(UpdateMaterialsResourceTask.Method.Put);
		}

		@Override
		protected void handle(ResourceContext<Materials, MaterialsImpl, MaterialsImpl> context, UpdateMaterialsResourceTask task) throws Throwable {
			MaterialsImpl MaterialsImpl = getMaterialsById(context, task.id);
			ResourceToken<MaterialsCategory> parentToken = context.findResourceToken(MaterialsCategory.class, MaterialsImpl.getCategoryId());
			// ResourceToken<Tenant> tenantToken =
			// context.findResourceToken(Tenant.class,MaterialsImpl.getTenantId());
			ResourceToken<Materials> token = context.putResource(MaterialsImpl);
			context.putResourceReference(parentToken, token);
			// context.putResourceReference(tenantToken,token);
		}

	}

	@Publish
	protected final class ModifyMaterialsResourceHandler extends TaskMethodHandler<UpdateMaterialsResourceTask, UpdateMaterialsResourceTask.Method> {

		protected ModifyMaterialsResourceHandler() {
			super(UpdateMaterialsResourceTask.Method.Modify);
		}

		@Override
		protected void handle(ResourceContext<Materials, MaterialsImpl, MaterialsImpl> context, UpdateMaterialsResourceTask task) throws Throwable {
			MaterialsImpl old = context.modifyResource(task.id);
			MaterialsImpl _new = getMaterialsById(context, task.id);
			old.setMaterialCode(_new.getMaterialCode());
			old.setInventoryWarningType(_new.getInventoryWarningType());
			old.setRemark(_new.getRemark());
			old.setMaterialName(_new.getMaterialName());
			old.setRefFlag(_new.isRefFlag());
//			old.setSalePrice(_new.getSalePrice());
			old.setStatus(_new.getStatus());
			old.setShelfLife(_new.getShelfLife());
			old.setWarningDay(_new.getWarningDay());
			old.setRemark(_new.getRemark());
			old.setJointVenture(_new.isJointVenture());
			old.setSupplierId(_new.getSupplierId());
			old.setPercentage(_new.getPercentage());
			old.setInventoryWarningType(_new.getInventoryWarningType());
			context.postModifiedResource(old);
			ResourceToken<Materials> token = context.findResourceToken(Materials.class, task.id);
			List<MaterialsItem> MaterialsItems = context.getResourceReferences(MaterialsItem.class, token);
			for (MaterialsItem MaterialsItem : MaterialsItems) {
				context.handle(new UpdateMaterialsItemResourceTask(MaterialsItem.getId()), UpdateMaterialsItemResourceTask.Method.Modify);
			}
		}
	}

	@Publish
	protected final class RemoveMaterialsResourceHandler extends TaskMethodHandler<UpdateMaterialsResourceTask, UpdateMaterialsResourceTask.Method> {

		protected RemoveMaterialsResourceHandler() {
			super(UpdateMaterialsResourceTask.Method.Remove);
		}

		@Override
		protected void handle(ResourceContext<Materials, MaterialsImpl, MaterialsImpl> context, UpdateMaterialsResourceTask task) throws Throwable {
			ResourceToken<Materials> token = context.findResourceToken(Materials.class, task.id);
			ResourceToken<MaterialsCategory> parentToken = context.findResourceToken(MaterialsCategory.class, token.getFacade().getCategoryId());
			ResourceToken<Tenant> tenantToken = context.findResourceToken(Tenant.class, context.find(Login.class).getTenantId());
			context.removeResourceReference(parentToken, token);
			context.removeResourceReference(tenantToken, token);
			context.removeResource(task.id);
		}

	}

}
