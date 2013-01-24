/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-12    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-12    jiuqi
 * ============================================================*/

package com.spark.psi.base.material.internal.service;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.Filter;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.LimitUtil;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.Materials;
import com.spark.psi.base.MaterialsCategory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.internal.entity.MaterialsItemImpl;
import com.spark.psi.base.internal.entity.helper.LevelTreeFilter;
import com.spark.psi.base.internal.entity.ormentity.MaterialsItemOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_MaterialsItem;
import com.spark.psi.base.key.materials.GetMaterialsCategoryLeafNodesKey;
import com.spark.psi.base.task.Materials.UpdateMaterialsItemAveragePurchasePriceTask;
import com.spark.psi.base.task.resource.UpdateMaterialsItemResourceTask;
import com.spark.psi.base.utils.MaterialsConstant;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.base.materials.key.GetMaterialsItemListKey;

/**
 * <p>
 * 商品 相关内部服务
 * </p>
 * 
 */

public class MaterialsModuleService extends Service {

	final Orm_MaterialsItem orm_MaterialsItem;
	final String MaterialsItemTable = ERPTableNames.Base.MaterialsItem.getTableName();

	protected MaterialsModuleService(final Orm_MaterialsItem orm_MaterialsItem) {
		super("com.spark.psi.base.internal.service.MaterialsModuleService");
		this.orm_MaterialsItem = orm_MaterialsItem;
	}

	/**
	 * 
	 * <p>
	 * 通过商品分类和关键字获得商品条目列表
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-3-12
	 */
	@Publish
	protected class GetMaterialsItemListProvider extends
			OneKeyResultListProvider<MaterialsItem, GetMaterialsItemListKey> {

		@Override
		protected void provide(Context context, GetMaterialsItemListKey key,
				List<MaterialsItem> resultList) throws Throwable {
			if (CheckIsNull.isEmpty(key.getSearchText())) { // 如果没有关键字过滤，则直接通过分类资源获得条目列表
				MaterialsCategory category = context.find(MaterialsCategory.class, key
						.getGategoryId());
				List<MaterialsItem> list = context.getList(MaterialsItem.class,
						category.getId());
				resultList.addAll(LimitUtil.limit(list, key.getOffset(), key
						.getCount()));
			} else {
				StringBuffer sql = new StringBuffer(
						"define query getPropertyByMaterialsGuid(")
						.append(
								" @MaterialsTypeGuid guid,@yzFlag boolean,@MaterialsPropertyState string,@searchText string)")
						.append(" begin").append(" select ");
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
				sql.append("t.canDalete as canDalete,");
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
				sql.append("as t  join (");
				sql.append(MaterialsConstant.QUERY_TONGYONG);
				sql.append(MaterialsConstant.QUERY_CHILIDREN_AND_SELF);
				sql.append(") as type on t.categoryId=type.recid");
				sql.append(" and type.yzFlag=@yzFlag");
				if (null != key.getMaterialsStatus()
						&& MaterialsStatus.PART_SALE != key.getMaterialsStatus()) {
					sql.append(" and t.status=@MaterialsPropertyState");
				}
				if (null != key.getSearchText()
						&& !"".equals(key.getSearchText())) {
					// 搜索控件模糊匹配商品编号/条码
					sql.append(" and(t.MaterialsNo like '%' + @searchText +'%' or t.MaterialsName like '%' + @searchText + '%' )");
				}
				sql.append(" order by t.createDate desc").append(" end");

				DBCommand dbc = context.prepareStatement(sql);
				// dbc.setArgumentValue(0,
				// context.find(Login.class).getTenantId());
				dbc.setArgumentValue(0, key.getGategoryId());
				dbc.setArgumentValue(1, true);
				dbc.setArgumentValue(2, key.getMaterialsStatus().getCode());
				dbc.setArgumentValue(3, key.getSearchText());

				RecordSet rs = dbc.executeQuery();
				while (rs.next()) {
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
//					g.setJointVenture(g.getMaterials().isJointVenture());
					g.setCategory(context.find(MaterialsCategory.class, g.getCategoryId()));
					resultList.add(g);
				}
			}

		}
	}

	/**
	 * 
	 * <p>
	 * 查询所有叶子节点
	 * </p>
	 * 如果自己是叶子节点则返回本身
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-3-28
	 */
	@Publish
	final protected class GetAllMaterialsCategoryLeafNodesProvider
			extends
			OneKeyResultListProvider<MaterialsCategory, GetMaterialsCategoryLeafNodesKey> {

		@Override
		protected void provide(final Context context,
				final GetMaterialsCategoryLeafNodesKey key,
				List<MaterialsCategory> resultList) throws Throwable {
//			ResourceToken<Tenant> token = TenantHelper.getTenantToken(context,
//					key.getTenantId());
			List<MaterialsCategory> list = new ArrayList<MaterialsCategory>();
			List<MaterialsCategory> sList = context.getList(MaterialsCategory.class);
			if (key.getCategoryId() == null) {
				for(MaterialsCategory c:sList)
				{
					if(c.isLeafNode())
					{
						list.add(c);
					}
				}
			} else {
				MaterialsCategory category = context.find(MaterialsCategory.class, key
						.getCategoryId());
				if (category.isLeafNode()) {
					resultList.add(category);
					return;
				}
				MaterialsCategory parent = context.find(MaterialsCategory.class, key
						.getCategoryId());
				LevelTreeFilter ltf = new LevelTreeFilter<MaterialsCategory>(parent
						.getPath());
				for(MaterialsCategory c:sList)
				{
					if(c.isLeafNode()&&ltf.accept(c))
					{
						list.add(c);
					}
				}
			}
			resultList.addAll(list);
		}

	}

	/**
	 * 
	 * <p>
	 * 通过分类id查询商品条目列表
	 * </p>
	 * 包含所有子分类的商品条目
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-9
	 */
	@Publish
	final protected class GetMaterialsItemsByCategoryIdProvider extends
			OneKeyResultListProvider<MaterialsItem, GUID> {

		@Override
		protected void provide(Context context, GUID key,
				List<MaterialsItem> resultList) throws Throwable {
			List<MaterialsCategory> list = context.getList(MaterialsCategory.class,
					new GetMaterialsCategoryLeafNodesKey(key));
			for (MaterialsCategory MaterialsCategory : list) {
				ResourceToken<MaterialsCategory> category = context
						.findResourceToken(MaterialsCategory.class, MaterialsCategory
								.getId());
				resultList.addAll(context.getResourceReferences(
						MaterialsItem.class, category, new Filter<MaterialsItem>() {

							public boolean accept(MaterialsItem item) {
								return true;
							}
						}));
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 通过分类id查询商品条目列表
	 * </p>
	 * 包含所有子分类的商品条目
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-9
	 */
	@Publish
	final protected class GetMaterialsItemsByCategoryIdProvider2 extends
			TwoKeyResultListProvider<MaterialsItem, GUID, GUID> {

		@Override
		protected void provide(Context context, GUID key, GUID tenantId,
				List<MaterialsItem> resultList) throws Throwable {
			List<MaterialsCategory> list = context.getList(MaterialsCategory.class,
					new GetMaterialsCategoryLeafNodesKey(key, tenantId));
			for (MaterialsCategory MaterialsCategory : list) {
				ResourceToken<MaterialsCategory> category = context
						.findResourceToken(MaterialsCategory.class, MaterialsCategory
								.getId());
				resultList.addAll(context.getResourceReferences(
						MaterialsItem.class, category, new Filter<MaterialsItem>() {

							public boolean accept(MaterialsItem item) {
								return true;
							}
						}));
			}
		}

	}

	/**
	 * 
	 * <p>
	 * 修改商品条目平均库存成本
	 * </p>
	 * 
	 */
	@Publish
	protected final class UpdateMaterialsItemAveragePurchasePriceTaskHandler extends
			SimpleTaskMethodHandler<UpdateMaterialsItemAveragePurchasePriceTask> {

		@Override
		protected void handle(Context context,
				UpdateMaterialsItemAveragePurchasePriceTask task) throws Throwable {
			ORMAccessor<MaterialsItemOrmEntity> acc = context
					.newORMAccessor(orm_MaterialsItem);
			MaterialsItemOrmEntity entity = acc.findByRECID(task.getMaterialsItemId());
			entity.setAvgBuyPrice(task.getPrice());
			acc.update(entity);
			acc.unuse();
			context.handle(new UpdateMaterialsItemResourceTask(task
					.getMaterialsItemId()),
					UpdateMaterialsItemResourceTask.Method.Modify);

		}
	}

	/**
	 * 
	 * <p>
	 * 修改商品条目最近采购单价
	 * </p>
	 * 
	 */
	// 取消（2012-10-14）
	// @Publish
	// protected final class UpdateMaterialsItemRecentPurchasePriceHandler extends
	// SimpleTaskMethodHandler<UpdateMaterialsItemRecentPurchasePriceTask> {
	//
	// @Override
	// protected void handle(Context context,
	// UpdateMaterialsItemRecentPurchasePriceTask task) throws Throwable
	// {
	// ORMAccessor<MaterialsItemOrmEntity> acc =
	// context.newORMAccessor(orm_MaterialsItem);
	// MaterialsItemOrmEntity entity = acc.findByRECID(task.getId());
	// entity.setRecentPurchasePrice(task.getPrice());
	// acc.update(entity);
	// acc.unuse();
	// context.handle(new
	// UpdateMaterialsItemResourceTask(task.getId()),UpdateMaterialsItemResourceTask.Method.Modify);
	// }
	//		
	// }

}
