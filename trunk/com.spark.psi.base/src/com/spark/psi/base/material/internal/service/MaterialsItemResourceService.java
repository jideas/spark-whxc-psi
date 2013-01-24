package com.spark.psi.base.material.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.exception.NullArgumentException;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.Materials;
import com.spark.psi.base.MaterialsCategory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.internal.entity.MaterialsItemImpl;
import com.spark.psi.base.task.resource.UpdateMaterialsItemResourceTask;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.base.materials.key.GetMaterialsItemByNoKey;

/**
 * <p>
 * 商品条目资源服务
 * </p>
 */

public class MaterialsItemResourceService extends ResourceService<MaterialsItem, MaterialsItemImpl, MaterialsItemImpl> {

	final String MaterialsItemTable = ERPTableNames.Base.MaterialsItem.getTableName();

	protected MaterialsItemResourceService() {
		super("com.spark.psi.base.internal.service.MaterialsItemResourceService", ResourceKind.SINGLETON_IN_CLUSTER);
	}

	@Override
	protected void init(Context context) {
		context.getList(MaterialsItem.class);
	}

	@Override
	protected void initResources(Context context,
			ResourceInserter<MaterialsItem, MaterialsItemImpl, MaterialsItemImpl> initializer) {
		List<MaterialsItemImpl> list = getAllMaterialsItems(context);
		for (MaterialsItemImpl MaterialsItem : list) {
			try {
				ResourceToken<MaterialsItem> token = initializer.putResource(MaterialsItem);
				ResourceToken<MaterialsCategory> categoryToken = context.findResourceToken(MaterialsCategory.class,
						MaterialsItem.getCategoryId());
				// ResourceToken<Tenant> tenantToken =
				// context.findResourceToken(Tenant.class,MaterialsItem.getTenantId());
				ResourceToken<Materials> MaterialsToken = context.findResourceToken(Materials.class, MaterialsItem
						.getMaterialId());
				initializer.putResourceReference(categoryToken, token);
				// initializer.putResourceReference(tenantToken, token);
				initializer.putResourceReference(MaterialsToken, token);
			} catch (NullArgumentException e) {
				System.out.println("初始化商品失败：" + MaterialsItem.getId());
				continue;
			}
		}
	}

	private List<MaterialsItemImpl> getAllMaterialsItems(Context context) {
		List<MaterialsItemImpl> list = new ArrayList<MaterialsItemImpl>();
		StringBuffer sql = new StringBuffer("define query getPropertyByMaterialsGuid()").append(" begin").append(
				" select ");
		sql.append("t.RECID as id,");
		sql.append("t.materialCode as materialCode,");
		sql.append("t.materialNo as materialNo,");
		sql.append("t.materialName as materialName,");
		sql.append("t.namePY as namePY,");
		sql.append("t.categoryId as categoryId,");
		sql.append("t.spec as spec,");
		sql.append("t.scale as scale,");
		sql.append("t.inventoryStrategy as inventoryStrategy,");
		sql.append("t.materialUnit as materialUnit,");
		sql.append("t.avgBuyPrice as avgBuyPrice,");
		sql.append("t.totalStoreUpper as totalStoreUpper,");
		sql.append("t.totalStoreFlore as totalStoreFlore,");
		sql.append("t.totalStoreAmount as totalStoreAmount,");
		sql.append("t.shelfLife as shelfLife,");
		sql.append("t.warningDay as warningDay,");
		sql.append("t.salePrice as salePrice,");
		sql.append("t.standardPrice as standardPrice,");
		sql.append("t.planPrice as planPrice,");
		sql.append("t.status as status,");
		sql.append("t.remark as remark,");
		sql.append("t.canDelete as canDelete,");
		sql.append("t.refFlag as refFlag,");
		sql.append("t.createDate as createDate,");
		sql.append("t.lastModifyDate as lastModifyDate,");
		sql.append("t.lastModifyPerson as lastModifyPerson,");
		sql.append("t.warningType as warningType,");
		sql.append("t.materialProperties as materialProperties,");
		sql.append("t.creatorId as creatorId,");
		sql.append("t.creator as creator,");
		sql.append("t.materialId as materialId,");
		sql.append("t.lossRate as lossRate");

		sql.append(" from ");
		sql.append(MaterialsItemTable);
		sql.append(" as t");
		sql.append(" order by t.recid asc");
		sql.append(" end");

		DBCommand dbc = context.prepareStatement(sql);
		RecordSet rs = dbc.executeQuery();
		while (rs.next()) {
			MaterialsItemImpl item = fullData(context, rs);
			if (item.getMaterial() != null && item.getCategory() != null)
				list.add(item);
		}

		return list;
	}

	final class GetMaterialsCategoryResourceById extends OneKeyResourceProvider<GUID> {

		@Override
		protected GUID getKey1(MaterialsItemImpl keysHolder) {
			return keysHolder.getId();
		}
	}

	// @Publish
	// final protected class GetMaterialsItemImplById extends
	// OneKeyResultProvider<MaterialsItemImpl, GUID> {
	//
	// @Override
	// protected MaterialsItemImpl provide(
	// ResourceContext<MaterialsItem, MaterialsItemImpl, MaterialsItemImpl>
	// context,
	// GUID key) throws Throwable {
	// ResourceToken<MaterialsItemImpl> token = context.findResourceToken(
	// MaterialsItemImpl.class, key);
	// return token != null ? token.getFacade() : null;
	// }
	// }
	//
	// @Publish
	// final protected class GetMaterialsItemById extends
	// OneKeyResultProvider<MaterialsItem, GUID> {
	//
	// @Override
	// protected MaterialsItem provide(
	// ResourceContext<MaterialsItem, MaterialsItemImpl, MaterialsItemImpl>
	// context,
	// GUID key) throws Throwable {
	// return context.find(MaterialsItemImpl.class, key);
	// }
	//
	// }

	@Publish
	protected final class PutMaterialsItemResourceHandler extends
			TaskMethodHandler<UpdateMaterialsItemResourceTask, UpdateMaterialsItemResourceTask.Method> {

		protected PutMaterialsItemResourceHandler() {
			super(UpdateMaterialsItemResourceTask.Method.Put);
		}

		@Override
		protected void handle(ResourceContext<MaterialsItem, MaterialsItemImpl, MaterialsItemImpl> context,
				UpdateMaterialsItemResourceTask task) throws Throwable {
			MaterialsItemImpl MaterialsItem = GetMaterialsItemById(context, task.id);
			ResourceToken<MaterialsItem> token = context.putResource(MaterialsItem);
			;
			ResourceToken<MaterialsCategory> categoryToken = context.findResourceToken(MaterialsCategory.class,
					MaterialsItem.getCategoryId());
			// ResourceToken<Tenant> tenantToken =
			// context.findResourceToken(Tenant.class,MaterialsItem.getTenantId());
			ResourceToken<Materials> MaterialsToken = context.findResourceToken(Materials.class, MaterialsItem
					.getMaterialId());
			context.putResourceReference(categoryToken, token);
			// context.putResourceReference(tenantToken, token);
			context.putResourceReference(MaterialsToken, token);

		}

	}

	@Publish
	protected final class ModifyMaterialsItemResourceHandler extends
			TaskMethodHandler<UpdateMaterialsItemResourceTask, UpdateMaterialsItemResourceTask.Method> {

		protected ModifyMaterialsItemResourceHandler() {
			super(UpdateMaterialsItemResourceTask.Method.Modify);
		}

		@Override
		protected void handle(ResourceContext<MaterialsItem, MaterialsItemImpl, MaterialsItemImpl> context,
				UpdateMaterialsItemResourceTask task) throws Throwable {
			ResourceToken<MaterialsItem> token = context.findResourceToken(MaterialsItem.class, task.id);
			ResourceToken<MaterialsCategory> categoryToken = context.findResourceToken(MaterialsCategory.class, token
					.getFacade().getCategoryId());
			context.removeResourceReference(categoryToken, token);
			MaterialsItemImpl old = context.modifyResource(task.id);
			MaterialsItemImpl _new = GetMaterialsItemById(context, task.id);
			// old.setAveragePurchasePrice(_new.getAveragePurchasePrice());
			// old.setInventoryAmountUpperLimit(_new
			// .getInventoryAmountUpperLimit());
			// old.setRecentPurchasePrice(_new.getRecentPurchasePrice());
			old.setRefFlag(_new.isRefFlag());
			old.setSalePrice(_new.getSalePrice());
			old.setMaterialProperties(_new.getMaterialProperties());
			// old.setTotalStoreLowerLimit(_new.getTotalStoreLowerLimit());
			// old.setTotalStoreUpperLimit(_new.getTotalStoreUpperLimit());
			old.setTotalStoreAmount(_new.getTotalStoreAmount());
			old.setMaterialUnit(_new.getMaterialUnit());
			old.setStatus(_new.getStatus());
			old.setMaterial(_new.getMaterial());
			old.setAvgBuyPrice(_new.getAvgBuyPrice());
			// old.setRecentPurchasePrice(_new.getRecentPurchasePrice());
			context.postModifiedResource(old);

			categoryToken = context.findResourceToken(MaterialsCategory.class, _new.getCategoryId());
			context.putResourceReference(categoryToken, token);
		}
	}

	@Publish
	protected final class RemoveMaterialsItemResourceHandler extends
			TaskMethodHandler<UpdateMaterialsItemResourceTask, UpdateMaterialsItemResourceTask.Method> {

		protected RemoveMaterialsItemResourceHandler() {
			super(UpdateMaterialsItemResourceTask.Method.Remove);
		}

		@Override
		protected void handle(ResourceContext<MaterialsItem, MaterialsItemImpl, MaterialsItemImpl> context,
				UpdateMaterialsItemResourceTask task) throws Throwable {
			ResourceToken<MaterialsItem> token = context.findResourceToken(MaterialsItem.class, task.id);
			MaterialsItemImpl MaterialsItem = (MaterialsItemImpl) token.getFacade();
			ResourceToken<MaterialsCategory> categoryToken = context.findResourceToken(MaterialsCategory.class,
					MaterialsItem.getCategoryId());
			// ResourceToken<Tenant> tenantToken =
			// context.findResourceToken(Tenant.class,MaterialsItem.getTenantId());
			ResourceToken<Materials> MaterialsToken = context.findResourceToken(Materials.class, MaterialsItem
					.getMaterialId());
			context.removeResourceReference(categoryToken, token);
			// context.removeResourceReference(tenantToken, token);
			context.removeResourceReference(MaterialsToken, token);
			context.removeResource(task.id);
		}

	}

	private MaterialsItemImpl GetMaterialsItemById(Context context, GUID id) {

		StringBuffer sql = new StringBuffer("define query getPropertyByMaterialsGuid(@id guid)").append(" begin")
				.append(" select ");
		sql.append("t.RECID as id,");
		sql.append("t.materialCode as materialCode,");
		sql.append("t.materialNo as materialNo,");
		sql.append("t.materialName as materialName,");
		sql.append("t.namePY as namePY,");
		sql.append("t.categoryId as categoryId,");
		sql.append("t.spec as spec,");
		sql.append("t.scale as scale,");
		sql.append("t.inventoryStrategy as inventoryStrategy,");
		sql.append("t.materialUnit as materialUnit,");
		sql.append("t.avgBuyPrice as avgBuyPrice,");
		sql.append("t.totalStoreUpper as totalStoreUpper,");
		sql.append("t.totalStoreFlore as totalStoreFlore,");
		sql.append("t.totalStoreAmount as totalStoreAmount,");
		sql.append("t.shelfLife as shelfLife,");
		sql.append("t.warningDay as warningDay,");
		sql.append("t.salePrice as salePrice,");
		sql.append("t.standardPrice as standardPrice,");
		sql.append("t.planPrice as planPrice,");
		sql.append("t.status as status,");
		sql.append("t.remark as remark,");
		sql.append("t.canDelete as canDelete,");
		sql.append("t.refFlag as refFlag,");
		sql.append("t.createDate as createDate,");
		sql.append("t.lastModifyDate as lastModifyDate,");
		sql.append("t.lastModifyPerson as lastModifyPerson,");
		sql.append("t.warningType as warningType,");
		sql.append("t.materialProperties as materialProperties,");
		sql.append("t.creatorId as creatorId,");
		sql.append("t.creator as creator,");
		sql.append("t.materialId as materialId,");
		sql.append("t.lossRate as lossRate");
		sql.append(" from ");
		sql.append(MaterialsItemTable);
		sql.append(" as t");
		sql.append("  where t.recid = @id ").append(" end");

		DBCommand dbc = context.prepareStatement(sql);
		dbc.setArgumentValue(0, id);
		RecordSet rs = dbc.executeQuery();
		if (rs.next()) {
			return fullData(context, rs);
		} else {
			return null;
		}
	}

	private MaterialsItemImpl fullData(Context context, RecordSet rs) {
		int i = 0;
		MaterialsItemImpl g = new MaterialsItemImpl();

		g.setId(rs.getFields().get(i++).getGUID());
		g.setMaterialCode(rs.getFields().get(i++).getString());
		g.setMaterialNo(rs.getFields().get(i++).getString());
		g.setMaterialName(rs.getFields().get(i++).getString());
		g.setNamePY(rs.getFields().get(i++).getString());
		g.setCategoryId(rs.getFields().get(i++).getGUID());
		g.setSpec(rs.getFields().get(i++).getString());
		g.setScale(rs.getFields().get(i++).getInt());
		g.setInventoryStrategy(rs.getFields().get(i++).getString());
		g.setMaterialUnit(rs.getFields().get(i++).getString());
		g.setAvgBuyPrice(rs.getFields().get(i++).getDouble());
		g.setTotalStoreUpper(rs.getFields().get(i++).getDouble());
		g.setTotalStoreFlore(rs.getFields().get(i++).getDouble());
		g.setTotalStoreAmount(rs.getFields().get(i++).getDouble());
		g.setShelfLife(rs.getFields().get(i++).getInt());
		g.setWarningDay(rs.getFields().get(i++).getInt());
		g.setSalePrice(rs.getFields().get(i++).getDouble());
		g.setStandardPrice(rs.getFields().get(i++).getDouble());
		g.setPlanPrice(rs.getFields().get(i++).getDouble());
		g.setStatus(MaterialsStatus.getMaterialsStatus(rs.getFields().get(i++).getString()));
		g.setRemark(rs.getFields().get(i++).getString());
		g.setCanDelete(rs.getFields().get(i++).getBoolean());
		g.setRefFlag(rs.getFields().get(i++).getBoolean());
		g.setCreateDate(rs.getFields().get(i++).getDate());
		g.setLastModifyDate(rs.getFields().get(i++).getDate());
		g.setLastModifyPerson(rs.getFields().get(i++).getString());
		g.setWarningType(InventoryWarningType.getGoodsWarnningType(rs.getFields().get(i++).getString()));
		g.setMaterialProperties(MaterialsProperyUtil.subMaterialsPropertyToArray(rs.getFields().get(i++).getString()));
		g.setCreatorId(rs.getFields().get(i++).getGUID());
		g.setCreator(rs.getFields().get(i++).getString());
		g.setMaterialId(rs.getFields().get(i++).getGUID());
		g.setLossRate(rs.getFields().get(i++).getDouble());

		g.setMaterial(context.find(Materials.class, g.getMaterialId()));
		// g.setJointVenture(g.getMaterials().isJointVenture());
		g.setCategory(context.find(MaterialsCategory.class, g.getCategoryId()));
		return g;
	}

	/**
	 * 根据材料条码获得材料信息
	 * 
	 * 
	 */
	@Publish
	protected class GetMaterialsItemByNoProvider extends OneKeyResultProvider<MaterialsItem, GetMaterialsItemByNoKey> {

		@Override
		protected MaterialsItem provide(ResourceContext<MaterialsItem, MaterialsItemImpl, MaterialsItemImpl> context,
				GetMaterialsItemByNoKey key) throws Throwable {
			if (CheckIsNull.isEmpty(key.getMaterialsNo())) {
				throw new Throwable("条码不能为空！");
			}
			List<MaterialsItem> list = context.getList(MaterialsItem.class);
			for (MaterialsItem item : list) {
				System.out.println(item.getMaterialNo()+"==========="+(key.getMaterialsNo()));
				if (item.getMaterialNo().equals(key.getMaterialsNo())) {
					return item;
				}
			}
			return null;
		}

	}
}
