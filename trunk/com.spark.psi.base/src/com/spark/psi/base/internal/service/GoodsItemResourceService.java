package com.spark.psi.base.internal.service;

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
import com.spark.psi.base.Goods;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.internal.entity.GoodsItemImpl;
import com.spark.psi.base.task.resource.UpdateGoodsItemResourceTask;
import com.spark.psi.base.utils.GoodsProperyUtil;
import com.spark.psi.publish.GoodsStatus;

/**
 * <p>
 * 商品条目资源服务
 * </p>
 */

public class GoodsItemResourceService extends
		ResourceService<GoodsItem, GoodsItemImpl, GoodsItemImpl> {

	final String GoodsItemTable = ERPTableNames.Base.GoodsItem.getTableName();
	protected GoodsItemResourceService() {
		super("com.spark.psi.base.internal.service.GoodsItemResourceService",
				ResourceKind.SINGLETON_IN_CLUSTER);
	}

	@Override
	protected void init(Context context) {
		context.getList(GoodsItem.class);
	}

	@Override
	protected void initResources(
			Context context,
			ResourceInserter<GoodsItem, GoodsItemImpl, GoodsItemImpl> initializer) {
		List<GoodsItemImpl> list = getAllGoodsItems(context);
		for (GoodsItemImpl goodsItem : list) {
			try{
				ResourceToken<GoodsItem> token = initializer.putResource(goodsItem);
				ResourceToken<GoodsCategory> categoryToken = context.findResourceToken(GoodsCategory.class,goodsItem.getCategoryId());
//				ResourceToken<Tenant> tenantToken = context.findResourceToken(Tenant.class,goodsItem.getTenantId());
				ResourceToken<Goods> goodsToken = context.findResourceToken(Goods.class,goodsItem.getGoodsId());
	            initializer.putResourceReference(categoryToken, token);
//	            initializer.putResourceReference(tenantToken, token);
	            initializer.putResourceReference(goodsToken, token);
            }
            catch(NullArgumentException e){
	            System.out.println("初始化商品失败："+goodsItem.getId());
	            continue;
            }
		}
	}

	private List<GoodsItemImpl> getAllGoodsItems(Context context) {
		List<GoodsItemImpl> list = new ArrayList<GoodsItemImpl>();
		StringBuffer sql = new StringBuffer(
				"define query getPropertyByGoodsGuid()").append(" begin")
				.append(" select ");
				sql.append("t.RECID as id,");
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
		sql.append(" as t");
		sql.append(" order by t.recid asc");
		sql.append(" end");

		DBCommand dbc = context.prepareStatement(sql);
		RecordSet rs = dbc.executeQuery();
		while (rs.next()) {
			GoodsItemImpl item  = fullData(context, rs);
			if(item.getGoods()!=null&&item.getCategory()!=null)
				list.add(item);	
		}

		return list;
	}

	final class GetGoodsCategoryResourceById extends
			OneKeyResourceProvider<GUID> {

		@Override
		protected GUID getKey1(GoodsItemImpl keysHolder) {
			return keysHolder.getId();
		}
	}

//	@Publish
//	final protected class GetGoodsItemImplById extends
//			OneKeyResultProvider<GoodsItemImpl, GUID> {
//
//		@Override
//		protected GoodsItemImpl provide(
//				ResourceContext<GoodsItem, GoodsItemImpl, GoodsItemImpl> context,
//				GUID key) throws Throwable {
//			ResourceToken<GoodsItemImpl> token = context.findResourceToken(
//					GoodsItemImpl.class, key);
//			return token != null ? token.getFacade() : null;
//		}
//	}
//
//	@Publish
//	final protected class GetGoodsItemById extends
//			OneKeyResultProvider<GoodsItem, GUID> {
//
//		@Override
//		protected GoodsItem provide(
//				ResourceContext<GoodsItem, GoodsItemImpl, GoodsItemImpl> context,
//				GUID key) throws Throwable {
//			return context.find(GoodsItemImpl.class, key);
//		}
//
//	}

	@Publish
	protected final class PutGoodsItemResourceHandler
			extends
			TaskMethodHandler<UpdateGoodsItemResourceTask, UpdateGoodsItemResourceTask.Method> {

		protected PutGoodsItemResourceHandler() {
			super(UpdateGoodsItemResourceTask.Method.Put);
		}

		@Override
		protected void handle(
				ResourceContext<GoodsItem, GoodsItemImpl, GoodsItemImpl> context,
				UpdateGoodsItemResourceTask task) throws Throwable {
			GoodsItemImpl goodsItem = GetGoodsItemById(context, task.id);			
			ResourceToken<GoodsItem> token = context.putResource(goodsItem);;
			ResourceToken<GoodsCategory> categoryToken = context.findResourceToken(GoodsCategory.class,goodsItem.getCategoryId());
//			ResourceToken<Tenant> tenantToken = context.findResourceToken(Tenant.class,goodsItem.getTenantId());
			ResourceToken<Goods> goodsToken = context.findResourceToken(Goods.class,goodsItem.getGoodsId());
			context.putResourceReference(categoryToken, token);
//			context.putResourceReference(tenantToken, token);
			context.putResourceReference(goodsToken, token);

		}

	}

	@Publish
	protected final class ModifyGoodsItemResourceHandler
			extends
			TaskMethodHandler<UpdateGoodsItemResourceTask, UpdateGoodsItemResourceTask.Method> {

		protected ModifyGoodsItemResourceHandler() {
			super(UpdateGoodsItemResourceTask.Method.Modify);
		}

		@Override
		protected void handle(
				ResourceContext<GoodsItem, GoodsItemImpl, GoodsItemImpl> context,
				UpdateGoodsItemResourceTask task) throws Throwable {
			ResourceToken<GoodsItem> token = context.findResourceToken(GoodsItem.class,task.id);
			ResourceToken<GoodsCategory> categoryToken = context.findResourceToken(GoodsCategory.class,token.getFacade().getCategoryId());
			context.removeResourceReference(categoryToken, token);
			GoodsItemImpl old = context.modifyResource(task.id);
			GoodsItemImpl _new = GetGoodsItemById(context, task.id);
//			old.setAveragePurchasePrice(_new.getAveragePurchasePrice());
//			old.setInventoryAmountUpperLimit(_new
//					.getInventoryAmountUpperLimit());
//			old.setRecentPurchasePrice(_new.getRecentPurchasePrice());
			old.setRefFlag(_new.isRefFlag());
			old.setSalePrice(_new.getSalePrice());
			old.setGoodsProperties(_new.getGoodsProperties());
			old.setBomId(_new.getBomId());
//			old.setTotalStoreLowerLimit(_new.getTotalStoreLowerLimit());
//			old.setTotalStoreUpperLimit(_new.getTotalStoreUpperLimit());
			old.setGoodsUnit(_new.getGoodsUnit());
			old.setStatus(_new.getStatus());
			old.setGoods(_new.getGoods());
			old.setSpec(_new.getSpec());
			old.setLossRate(_new.getLossRate());
			old.setStandardCost(_new.getStandardCost());
			old.setAvgCost(_new.getAvgCost());
			old.setAssessCost(_new.getAssessCost());
			old.setGoodsNo(_new.getGoodsNo());
			old.setSerialNumber(_new.getSerialNumber());
//			old.setRecentPurchasePrice(_new.getRecentPurchasePrice());
			context.postModifiedResource(old);
			
			categoryToken = context.findResourceToken(GoodsCategory.class,_new.getCategoryId());
			context.putResourceReference(categoryToken, token);
		}
	}

	@Publish
	protected final class RemoveGoodsItemResourceHandler
			extends
			TaskMethodHandler<UpdateGoodsItemResourceTask, UpdateGoodsItemResourceTask.Method> {

		protected RemoveGoodsItemResourceHandler() {
			super(UpdateGoodsItemResourceTask.Method.Remove);
		}

		@Override
		protected void handle(
				ResourceContext<GoodsItem, GoodsItemImpl, GoodsItemImpl> context,
				UpdateGoodsItemResourceTask task) throws Throwable {
			ResourceToken<GoodsItem> token = context.findResourceToken(GoodsItem.class, task.id);
			GoodsItemImpl goodsItem = (GoodsItemImpl)token.getFacade();
			ResourceToken<GoodsCategory> categoryToken = context.findResourceToken(GoodsCategory.class,goodsItem.getCategoryId());
//			ResourceToken<Tenant> tenantToken = context.findResourceToken(Tenant.class,goodsItem.getTenantId());
			ResourceToken<Goods> goodsToken = context.findResourceToken(Goods.class,goodsItem.getGoodsId());
			context.removeResourceReference(categoryToken, token);
//			context.removeResourceReference(tenantToken, token);
			context.removeResourceReference(goodsToken, token);
			context.removeResource(task.id);
		}

	}

	private GoodsItemImpl GetGoodsItemById(Context context, GUID id) {
//		StringBuffer sql = new StringBuffer(
//				"define query getPropertyByGoodsGuid(@id guid)")
//				.append(" begin").append(" select ")
//				.append(" a.RECID as recid,")
//				.append(" a.salePrice as salePrice,")
//				.append(" a.goodsPropertyState as status,")
//				.append(" a.reflag as reflag,")
//				.append(" a.goodsUnit as goodsUnit,")
//				.append(" a.propertyValue as propertyValue,")
//				.append(" a.totalStoreAmount as totalStoreAmount,")
//				.append(" a.totalStoreUpper as totalStoreUpper,")
//				.append(" a.totalStoreFlore as totalStoreFlore,")
//				.append(" a.avgBuyPrice as avgBuyPrice,")
//				.append(" a.goodsGuid as goodsGuid,")
//				.append(" a.tenantsGuid as tenantsGuid,")
//				.append(" a.recentPurchasePrice as recentPurchasePrice, ")
//				.append(" a.createDate as createDate,")
//				.append(" a.createPerson as createPerson,")
//				.append(" a.goodsTypeGuid as goodsTypeGuid ")
//				.append(" from SA_GOODS_PROPERTY as a")
//				.append("  where a.recid = @id ").append(" end");
		
		StringBuffer sql = new StringBuffer(
		"define query getPropertyByGoodsGuid(@id guid)")
		.append(" begin").append(" select ");
		sql.append("t.RECID as id,");
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

	private GoodsItemImpl fullData(Context context, RecordSet rs) {
		int i = 0;
		GoodsItemImpl g = new GoodsItemImpl();

//		goodsItem.setId(rs.getFields().get(i++).getGUID());
//		goodsItem.setSalePrice(rs.getFields().get(i++).getDouble());
//		goodsItem.setStatus(GoodsStatus.getGoodsStatus(rs.getFields().get(i++)
//				.getString()));
//		goodsItem.setRefFlag(rs.getFields().get(i++).getBoolean());
//		goodsItem.setUnit(rs.getFields().get(i++).getString());
//		goodsItem.setPropertyValues(GoodsProperyUtil.subGoodsPropertyToArray(rs
//				.getFields().get(i++).getString()));
//		if (rs.getFields().get(i).isNull()) {
//			goodsItem.setInventoryAmountUpperLimit(-1d);
//		} else {
//			goodsItem.setInventoryAmountUpperLimit(rs.getFields().get(i)
//					.getDouble());
//		}
//		i++;
//		if (rs.getFields().get(i).isNull()) {
//			goodsItem.setTotalStoreUpperLimit(-1d);
//		} else {
//			goodsItem
//					.setTotalStoreUpperLimit(rs.getFields().get(i).getDouble());
//		}
//		i++;
//		if (rs.getFields().get(i).isNull()) {
//			goodsItem.setTotalStoreLowerLimit(-1d);
//		} else {
//			goodsItem
//					.setTotalStoreLowerLimit(rs.getFields().get(i).getDouble());
//		}
//		i++;
//		goodsItem.setAveragePurchasePrice(rs.getFields().get(i++).getDouble());
//		goodsItem.setGoodsId(rs.getFields().get(i++).getGUID());		
//		goodsItem.setGoods(context.find(Goods.class,goodsItem.getGoodsId()));
//		goodsItem.setTenantId(rs.getFields().get(i++).getGUID());
//		goodsItem.setRecentPurchasePrice(rs.getFields().get(i++).getDouble());
//		goodsItem.setCreateDate(rs.getFields().get(i++).getDate());
//		goodsItem.setCreatePerson(rs.getFields().get(i++).getString());
//		GUID guid = rs.getFields().get(i++).getGUID();
//		goodsItem.setGoodsCategroy(context.find(GoodsCategory.class, guid));
		
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
		
		g.setGoods(context.find(Goods.class, g.getGoodsId()));
//		g.setJointVenture(g.getGoods().isJointVenture());
		g.setCategory(context.find(GoodsCategory.class, g.getCategoryId()));
		return g;
	}
}
