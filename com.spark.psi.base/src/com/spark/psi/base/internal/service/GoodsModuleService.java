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

package com.spark.psi.base.internal.service;

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
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.GoodsImpl;
import com.spark.psi.base.internal.entity.GoodsItemImpl;
import com.spark.psi.base.internal.entity.helper.LevelTreeFilter;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.internal.entity.ormentity.GoodsItemOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_GoodsItem;
import com.spark.psi.base.key.goods.GetGoodsCategoryLeafNodesKey;
import com.spark.psi.base.task.UpdateGoodsItemBomIdTask;
import com.spark.psi.base.task.goods.UpdateGoodsItemAveragePurchasePriceTask;
import com.spark.psi.base.task.goods.UpdateGoodsItemRecentPurchasePriceTask;
import com.spark.psi.base.task.resource.UpdateGoodsItemResourceTask;
import com.spark.psi.base.utils.GoodsConstant;
import com.spark.psi.base.utils.GoodsProperyUtil;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.base.goods.key.GetGoodsItemListKey;

/**
 * <p>
 * 商品 相关内部服务
 * </p>
 * 
 */

public class GoodsModuleService extends Service {

	final Orm_GoodsItem orm_GoodsItem;
	final String GoodsItemTable = ERPTableNames.Base.GoodsItem.getTableName();

	protected GoodsModuleService(final Orm_GoodsItem orm_GoodsItem) {
		super("com.spark.psi.base.internal.service.GoodsModuleService");
		this.orm_GoodsItem = orm_GoodsItem;
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
	protected class GetGoodsItemListProvider extends
			OneKeyResultListProvider<GoodsItem, GetGoodsItemListKey> {

		@Override
		protected void provide(Context context, GetGoodsItemListKey key,
				List<GoodsItem> resultList) throws Throwable {
			if (CheckIsNull.isEmpty(key.getSearchText())) { // 如果没有关键字过滤，则直接通过分类资源获得条目列表
				GoodsCategory category = context.find(GoodsCategory.class, key
						.getGategoryId());
				List<GoodsItem> list = context.getList(GoodsItem.class,
						category.getId());
				resultList.addAll(LimitUtil.limit(list, key.getOffset(), key
						.getCount()));
			} else {
				StringBuffer sql = new StringBuffer(
						"define query getPropertyByGoodsGuid(")
						.append(
								" @goodsTypeGuid guid,@yzFlag boolean,@goodsPropertyState string,@searchText string)")
						.append(" begin").append(" select ");
				sql.append("t.goodsCode as goodsCode, ");
				sql.append("t.goodsNo as goodsNo, ");
				sql.append("t.goodsName as goodsName, ");
				sql.append("t.namePY as namePY, ");
				sql.append("t.categoryId as categoryId, ");
				sql.append("t.spec as spec, ");
				sql.append("t.scale as scale, ");
				sql.append("t.needProduce as needProduce, ");
				sql.append("t.inventoryStrategy as inventoryStrategy, ");
				sql.append("t.goodsUnit as goodsUnit, ");
				sql.append("t.avgCost as avgCost, ");
				sql.append("t.standardCost as standardCost, ");
				sql.append("t.assessCost as assessCost, ");
				sql.append("t.shelfLife as shelfLife, ");
				sql.append("t.warningDay as warningDay, ");
				sql.append("t.salePrice as salePrice, ");
				sql.append("t.originalPrice as originalPrice, ");
				sql.append("t.lossRate as lossRate, ");
				sql.append("t.status as status, ");
				sql.append("t.canDelete as canDelete, ");
				sql.append("t.refFlag as refFlag, ");
				sql.append("t.createDate as createDate, ");
				sql.append("t.lastModifyDate as lastModifyDate, ");
				sql.append("t.lastModifyPerson as lastModifyPerson, ");
				sql.append("t.goodsProperties as goodsProperties, ");
				sql.append("t.bomId as bomId, ");
				sql.append("t.creatorId as creatorId, ");
				sql.append("t.creator as creator, ");
				sql.append("t.goodsId as goodsId, ");
				sql.append("t.serialNumber as serialNumber ");
				sql.append(" from ");
				sql.append(GoodsItemTable);
				sql.append("as t  join (");
				sql.append(GoodsConstant.QUERY_TONGYONG);
				sql.append(GoodsConstant.QUERY_CHILIDREN_AND_SELF);
				sql.append(") as type on t.goodsTypeGuid=type.recid");
				sql.append(" and type.yzFlag=@yzFlag");
				if (null != key.getGoodsStatus()
						&& GoodsStatus.PART_SALE != key.getGoodsStatus()) {
					sql.append(" and t.goodsPropertyState=@goodsPropertyState");
				}
				if (null != key.getSearchText()
						&& !"".equals(key.getSearchText())) {
					// 搜索控件模糊匹配商品编号/条码
					sql.append(" and(t.goodsNo like '%' + @searchText +'%' or t.goodsName like '%' + @searchText + '%' )");
				}
				sql.append(" order by t.createDate desc").append(" end");

				DBCommand dbc = context.prepareStatement(sql);
				// dbc.setArgumentValue(0,
				// context.find(Login.class).getTenantId());
				dbc.setArgumentValue(0, key.getGategoryId());
				dbc.setArgumentValue(1, true);
				dbc.setArgumentValue(2, key.getGoodsStatus().getCode());
				dbc.setArgumentValue(3, key.getSearchText());

				RecordSet rs = dbc.executeQuery();
				while (rs.next()) {
					int i = 0;
					GoodsItemImpl g = new GoodsItemImpl();
					g.setId(rs.getFields().get(i++).getGUID());
					g.setGoodsCode(rs.getFields().get(i++).getString());
					g.setGoodsNo(rs.getFields().get(i++).getString());
					g.setGoodsName(rs.getFields().get(i++).getString());
					g.setNamePY(rs.getFields().get(i++).getString());
					g.setCategoryId(rs.getFields().get(i++).getGUID());
					g.setSpec(rs.getFields().get(i++).getString());
					g.setScale(rs.getFields().get(i++).getInt());
					g.setNeedProduce(rs.getFields().get(i++).getBoolean());
					g.setInventoryStrategy(rs.getFields().get(i++).getString());
					g.setGoodsUnit(rs.getFields().get(i++).getString());
					g.setAvgCost(rs.getFields().get(i++).getDouble());
					g.setStandardCost(rs.getFields().get(i++).getDouble());
					g.setAssessCost(rs.getFields().get(i++).getDouble());
					g.setShelfLife(rs.getFields().get(i++).getInt());
					g.setWarningDay(rs.getFields().get(i++).getInt());
					g.setSalePrice(rs.getFields().get(i++).getDouble());
					g.setOriginalPrice(rs.getFields().get(i++).getDouble());
					g.setLossRate(rs.getFields().get(i++).getDouble());
					g.setStatus(GoodsStatus.getGoodsStatus(rs.getFields().get(i++).getString()));
					g.setCanDelete(rs.getFields().get(i++).getBoolean());
					g.setRefFlag(rs.getFields().get(i++).getBoolean());
					g.setCreateDate(rs.getFields().get(i++).getDate());
					g.setLastModifyDate(rs.getFields().get(i++).getDate());
					g.setLastModifyPerson(rs.getFields().get(i++).getString());
					g.setGoodsProperties(GoodsProperyUtil.subGoodsPropertyToArray(rs.getFields().get(i++).getString()));
					g.setBomId(rs.getFields().get(i++).getGUID());
					g.setCreatorId(rs.getFields().get(i++).getGUID());
					g.setCreator(rs.getFields().get(i++).getString());
					g.setGoodsId(rs.getFields().get(i++).getGUID());
					g.setSerialNumber(rs.getFields().get(i++).getString());
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
	final protected class GetAllGoodsCategoryLeafNodesProvider
			extends
			OneKeyResultListProvider<GoodsCategory, GetGoodsCategoryLeafNodesKey> {

		@Override
		protected void provide(final Context context,
				final GetGoodsCategoryLeafNodesKey key,
				List<GoodsCategory> resultList) throws Throwable {
//			ResourceToken<Tenant> token = TenantHelper.getTenantToken(context,
//					key.getTenantId());
			List<GoodsCategory> list = new ArrayList<GoodsCategory>();
			List<GoodsCategory> sList = context.getList(GoodsCategory.class);
			if (key.getCategoryId() == null) {
				for(GoodsCategory c:sList)
				{
					if(c.isLeafNode())
					{
						list.add(c);
					}
				}
			} else {
				GoodsCategory category = context.find(GoodsCategory.class, key
						.getCategoryId());
				if (category.isLeafNode()) {
					resultList.add(category);
					return;
				}
				GoodsCategory parent = context.find(GoodsCategory.class, key
						.getCategoryId());
				LevelTreeFilter ltf = new LevelTreeFilter<GoodsCategory>(parent
						.getPath());
				for(GoodsCategory c:sList)
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
	final protected class GetGoodsItemsByCategoryIdProvider extends
			OneKeyResultListProvider<GoodsItem, GUID> {

		@Override
		protected void provide(Context context, GUID key,
				List<GoodsItem> resultList) throws Throwable {
			List<GoodsCategory> list = context.getList(GoodsCategory.class,
					new GetGoodsCategoryLeafNodesKey(key));
			for (GoodsCategory goodsCategory : list) {
				ResourceToken<GoodsCategory> category = context
						.findResourceToken(GoodsCategory.class, goodsCategory
								.getId());
				resultList.addAll(context.getResourceReferences(
						GoodsItem.class, category, new Filter<GoodsItem>() {

							public boolean accept(GoodsItem item) {
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
	final protected class GetGoodsItemsByCategoryIdProvider2 extends
			TwoKeyResultListProvider<GoodsItem, GUID, GUID> {

		@Override
		protected void provide(Context context, GUID key, GUID tenantId,
				List<GoodsItem> resultList) throws Throwable {
			List<GoodsCategory> list = context.getList(GoodsCategory.class,
					new GetGoodsCategoryLeafNodesKey(key, tenantId));
			for (GoodsCategory goodsCategory : list) {
				ResourceToken<GoodsCategory> category = context
						.findResourceToken(GoodsCategory.class, goodsCategory
								.getId());
				resultList.addAll(context.getResourceReferences(
						GoodsItem.class, category, new Filter<GoodsItem>() {

							public boolean accept(GoodsItem item) {
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
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-17
	 */
	@Publish
	protected final class UpdateGoodsItemAveragePurchasePriceTaskHandler extends
			SimpleTaskMethodHandler<UpdateGoodsItemAveragePurchasePriceTask> {

		@Override
		protected void handle(Context context,
				UpdateGoodsItemAveragePurchasePriceTask task) throws Throwable {
			ORMAccessor<GoodsItemOrmEntity> acc = context
					.newORMAccessor(orm_GoodsItem);
			GoodsItemOrmEntity entity = acc.findByRECID(task.getGoodsItemId());
			entity.setAvgCost(task.getPrice());
			acc.update(entity);
			acc.unuse();
			context.handle(new UpdateGoodsItemResourceTask(task
					.getGoodsItemId()),
					UpdateGoodsItemResourceTask.Method.Modify);

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
	// protected final class UpdateGoodsItemRecentPurchasePriceHandler extends
	// SimpleTaskMethodHandler<UpdateGoodsItemRecentPurchasePriceTask> {
	//
	// @Override
	// protected void handle(Context context,
	// UpdateGoodsItemRecentPurchasePriceTask task) throws Throwable
	// {
	// ORMAccessor<GoodsItemOrmEntity> acc =
	// context.newORMAccessor(orm_GoodsItem);
	// GoodsItemOrmEntity entity = acc.findByRECID(task.getId());
	// entity.setRecentPurchasePrice(task.getPrice());
	// acc.update(entity);
	// acc.unuse();
	// context.handle(new
	// UpdateGoodsItemResourceTask(task.getId()),UpdateGoodsItemResourceTask.Method.Modify);
	// }
	//		
	// }
	
	/**
	 * 设置BOMID
	 */
	@Publish
	protected class UpdateGoodsItemBom extends SimpleTaskMethodHandler<UpdateGoodsItemBomIdTask>
	{

		@Override
		protected void handle(Context context, UpdateGoodsItemBomIdTask task) throws Throwable {
			StringBuffer sql = new StringBuffer();
			sql.append("define update updateGoodsItemBom(@goodsItemId guid,@bomId guid)\n");
			sql.append("begin\n");
			sql.append("update ");
			sql.append(ERPTableNames.Base.GoodsItem.getTableName());
			sql.append(" as t\n");
			sql.append(" set bomId=@bomId\n");
			sql.append("where t.recid=@goodsItemId\n");
			sql.append("end");
			
			DBCommand dbc = context.prepareStatement(sql);
			dbc.setArgumentValues(task.getGoodsItemId(),task.getBomId());
			try
			{
				dbc.executeUpdate();
				context.handle(new UpdateGoodsItemResourceTask(task
						.getGoodsItemId()),
						UpdateGoodsItemResourceTask.Method.Modify);
			}
			finally
			{
				dbc.unuse();
			}
		}
		
	}

}
