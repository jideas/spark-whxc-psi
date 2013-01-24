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
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
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
import com.spark.psi.base.Login;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.GoodsImpl;
import com.spark.psi.base.internal.entity.ormentity.GoodsOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_Goods;
import com.spark.psi.base.task.resource.UpdateGoodsItemResourceTask;
import com.spark.psi.base.task.resource.UpdateGoodsResourceTask;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.InventoryWarningType;

/**
 * <p>
 * 商品资源服务
 * </p>
 * 
 */

public class GoodsResourceService extends
		ResourceService<Goods, GoodsImpl, GoodsImpl> {

	final Orm_Goods orm_Goods;

	protected GoodsResourceService(final Orm_Goods orm_Goods) {
		super("com.spark.psi.base.internal.service.GoodsResourceService",
				ResourceKind.SINGLETON_IN_CLUSTER);
		this.orm_Goods = orm_Goods;
	}

	protected class GoodsItemGroupResourceReference extends
			ResourceReference<GoodsItem> {
	}

	@Override
	protected void init(Context context) {
		context.getList(Goods.class);
	}

	@Override
	protected void initResources(Context context,
			ResourceInserter<Goods, GoodsImpl, GoodsImpl> initializer) {
		List<GoodsImpl> list = getAllGoodsList(context);
		for (GoodsImpl goodsImpl : list) {
			ResourceToken<GoodsCategory> parentToken = context
					.findResourceToken(GoodsCategory.class, goodsImpl
							.getCategoryId());
			// ResourceToken<Tenant> tenantToken =
			// context.findResourceToken(Tenant.class,goodsImpl.getTenantId());
			ResourceToken<Goods> token = initializer.putResource(goodsImpl);
			initializer.putResourceReference(parentToken, token);
			// initializer.putResourceReference(tenantToken,token);
		}
	}

	private List<GoodsImpl> getAllGoodsList(Context context) {
		List<GoodsImpl> list = new ArrayList<GoodsImpl>();
		StringBuffer sql = new StringBuffer(
				"define query getPropertyByGoodsGuid()");
		sql.append(" begin");
		sql.append(" select ");
		sql.append(" t.RECID as id, ");
		sql.append(" t.goodsCode as goodsCode, ");
		sql.append(" t.goodsName as goodsName, ");
		sql.append(" t.namePY as namePY, ");
		sql.append(" t.categoryId as categoryId, ");
		sql.append(" t.salePrice as salePrice, ");
		sql.append(" t.isJointVenture as isJointVenture, ");
		sql.append(" t.supplierId as supplierId, ");
		sql.append(" t.remark as remark, ");
		sql.append(" t.shelfLife as shelfLife, ");
		sql.append(" t.warningDay as warningDay, ");
		sql.append(" t.canDelete as canDelete, ");
		sql.append(" t.refFlag as refFlag, ");
		sql.append(" t.inventoryWarningType as inventoryWarningType, ");
		sql.append(" t.createDate as createDate, ");
		sql.append(" t.lastModifyDate as lastModifyDate, ");
		sql.append(" t.lastModifyPerson as lastModifyPerson, ");
		sql.append(" t.creatorId as creatorId, ");
		sql.append(" t.creator as creator, ");
		sql.append(" t.status as status ");
		sql.append(" from ");
		sql.append(ERPTableNames.Base.Goods.getTableName());
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

	private GoodsImpl getGoodsById(Context context, GUID id) {
		// ORMAccessor<GoodsOrmEntity> orm = context.newORMAccessor(orm_Goods);
		// GoodsOrmEntity e = null;
		// try
		// {
		// e = orm.findByRECID(id);
		// if(null!=e)
		// {
		// GoodsImpl i = new GoodsImpl();
		// i.setCanDalete(e.isCanDalete());
		// i.setCategoryId(e.getCategoryId());
		// i.setCreateDate(e.getCreateDate());
		// i.setCreator(e.getCreator());
		// i.setCreatorId(e.getCreatorId());
		// i.setGoodsCode(e.getGoodsCode());
		// i.setGoodsName(e.getGoodsName());
		// i.setId(e.getId());
		// i.setInventoryWarningType(InventoryWarningType.getGoodsWarnningType(e.getInventoryWarningType()));
		// i.setJointVenture(e.isJointVenture());
		// i.setLastModifyDate(e.getLastModifyDate());
		// i.setLastModifyPerson(e.getLastModifyPerson());
		// i.setNamePY(e.getNamePY());
		// i.setRefFlag(e.isRefFlag());
		// i.setRemark(e.getRemark());
		// i.setSalePrice(e.getSalePrice());
		// i.setShelfLife(e.getShelfLife());
		// i.setStatus(GoodsStatus.getGoodsStatus(e.getStatus()));
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

		StringBuffer sql = new StringBuffer(
				"define query getPropertyByGoodsGuid(@id guid)");
		sql.append(" begin");
		sql.append(" select ");
		sql.append(" t.RECID as id, ");
		sql.append(" t.goodsCode as goodsCode, ");
		sql.append(" t.goodsName as goodsName, ");
		sql.append(" t.namePY as namePY, ");
		sql.append(" t.categoryId as categoryId, ");
		sql.append(" t.salePrice as salePrice, ");
		sql.append(" t.isJointVenture as isJointVenture, ");
		sql.append(" t.supplierId as supplierId, ");
		sql.append(" t.remark as remark, ");
		sql.append(" t.shelfLife as shelfLife, ");
		sql.append(" t.warningDay as warningDay, ");
		sql.append(" t.canDelete as canDelete, ");
		sql.append(" t.refFlag as refFlag, ");
		sql.append(" t.inventoryWarningType as inventoryWarningType, ");
		sql.append(" t.createDate as createDate, ");
		sql.append(" t.lastModifyDate as lastModifyDate, ");
		sql.append(" t.lastModifyPerson as lastModifyPerson, ");
		sql.append(" t.creatorId as creatorId, ");
		sql.append(" t.creator as creator, ");
		sql.append(" t.status as status ");
		sql.append(" from ");
		sql.append(ERPTableNames.Base.Goods.getTableName());
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

	private GoodsImpl fullData(Context context, RecordSet rs) {
		int i = 0;
		GoodsImpl info = new GoodsImpl();
		info.setId(rs.getFields().get(i++).getGUID());
		info.setGoodsCode(rs.getFields().get(i++).getString());
		info.setGoodsName(rs.getFields().get(i++).getString());
		info.setNamePY(rs.getFields().get(i++).getString());
		info.setCategoryId(rs.getFields().get(i++).getGUID());
		info.setSalePrice(rs.getFields().get(i++).getDouble());
		info.setJointVenture(rs.getFields().get(i++).getBoolean());
		info.setSupplierId(rs.getFields().get(i++).getGUID());
		info.setRemark(rs.getFields().get(i++).getString());
		info.setShelfLife(rs.getFields().get(i++).getInt());
		info.setWarningDay(rs.getFields().get(i++).getInt());
		info.setCanDelete(rs.getFields().get(i++).getBoolean());
		info.setRefFlag(rs.getFields().get(i++).getBoolean());
		info.setInventoryWarningType(InventoryWarningType.getGoodsWarnningType(rs.getFields().get(i++).getString()));
		info.setCreateDate(rs.getFields().get(i++).getDate());
		info.setLastModifyDate(rs.getFields().get(i++).getDate());
		info.setLastModifyPerson(rs.getFields().get(i++).getString());
		info.setCreatorId(rs.getFields().get(i++).getGUID());
		info.setCreator(rs.getFields().get(i++).getString());
		info.setStatus(GoodsStatus.getGoodsStatus(rs.getFields().get(i++).getString()));
		
		return info;
	}

	// final class GetGoodsResourceByCode extends
	// TwoKeyResourceProvider<GUID,String>{
	//
	// @Override
	// protected GUID getKey1(GoodsImpl keysHolder){
	// return keysHolder.getTenantId();
	// }
	//
	// @Override
	// protected String getKey2(GoodsImpl keysHolder){
	// // TODO Auto-generated method stub
	// return keysHolder.getCode();
	// }
	//		
	// }

	final class GetGoodsResourceById extends OneKeyResourceProvider<GUID> {

		@Override
		protected GUID getKey1(GoodsImpl keysHolder) {
			return keysHolder.getId();
		}
	}

	// @Publish
	// final protected class GetGoodsImplById extends
	// OneKeyResultProvider<GoodsImpl,GUID>{
	//
	// @Override
	// protected GoodsImpl provide(
	// ResourceContext<Goods, GoodsImpl, GoodsImpl> context, GUID key)
	// throws Throwable
	// {
	// ResourceToken<GoodsImpl> token =
	// context.findResourceToken(Goods.class,key);
	// return token != null ? token.getFacade() : null;
	// }
	// }

	// @Publish
	// final protected class GetGoodsById extends
	// OneKeyResultProvider<Goods,GUID>{
	//
	// @Override
	// protected Goods provide(
	// ResourceContext<GoodsImpl, GoodsImpl, GoodsImpl> context, GUID key)
	// throws Throwable
	// {
	// return context.find(GoodsImpl.class,key);
	// }
	// }
	//

	@Publish
	protected final class PutGoodsResourceHandler
			extends
			TaskMethodHandler<UpdateGoodsResourceTask, UpdateGoodsResourceTask.Method> {

		protected PutGoodsResourceHandler() {
			super(UpdateGoodsResourceTask.Method.Put);
		}

		@Override
		protected void handle(
				ResourceContext<Goods, GoodsImpl, GoodsImpl> context,
				UpdateGoodsResourceTask task) throws Throwable {
			GoodsImpl goodsImpl = getGoodsById(context, task.id);
			ResourceToken<GoodsCategory> parentToken = context
					.findResourceToken(GoodsCategory.class, goodsImpl
							.getCategoryId());
			// ResourceToken<Tenant> tenantToken =
			// context.findResourceToken(Tenant.class,goodsImpl.getTenantId());
			ResourceToken<Goods> token = context.putResource(getGoodsById(
					context, task.id));
			context.putResourceReference(parentToken, token);
			// context.putResourceReference(tenantToken,token);
		}

	}

	@Publish
	protected final class ModifyGoodsResourceHandler
			extends
			TaskMethodHandler<UpdateGoodsResourceTask, UpdateGoodsResourceTask.Method> {

		protected ModifyGoodsResourceHandler() {
			super(UpdateGoodsResourceTask.Method.Modify);
		}

		@Override
		protected void handle(
				ResourceContext<Goods, GoodsImpl, GoodsImpl> context,
				UpdateGoodsResourceTask task) throws Throwable {
			GoodsImpl old = context.modifyResource(task.id);
			GoodsImpl _new = getGoodsById(context, task.id);
			old.setGoodsCode(_new.getGoodsCode());
			old.setInventoryWarningType(_new.getInventoryWarningType());
			old.setRemark(_new.getRemark());
			old.setGoodsName(_new.getGoodsName());
			old.setRefFlag(_new.isRefFlag());
			old.setSalePrice(_new.getSalePrice());
			old.setStatus(_new.getStatus());
			old.setJointVenture(_new.isJointVenture());
			old.setSupplierId(_new.getSupplierId());
			old.setShelfLife(_new.getShelfLife());
			old.setWarningDay(_new.getWarningDay());
			old.setCanDelete(_new.isCanDelete());
			context.postModifiedResource(old);
			ResourceToken<Goods> token = context.findResourceToken(Goods.class,
					task.id);
			List<GoodsItem> goodsItems = context.getResourceReferences(
					GoodsItem.class, token);
			for (GoodsItem goodsItem : goodsItems) {
				context.handle(new UpdateGoodsItemResourceTask(goodsItem
						.getId()), UpdateGoodsItemResourceTask.Method.Modify);
			}
		}
	}

	@Publish
	protected final class RemoveGoodsResourceHandler
			extends
			TaskMethodHandler<UpdateGoodsResourceTask, UpdateGoodsResourceTask.Method> {

		protected RemoveGoodsResourceHandler() {
			super(UpdateGoodsResourceTask.Method.Remove);
		}

		@Override
		protected void handle(
				ResourceContext<Goods, GoodsImpl, GoodsImpl> context,
				UpdateGoodsResourceTask task) throws Throwable {
			ResourceToken<Goods> token = context.findResourceToken(Goods.class,
					task.id);
			ResourceToken<GoodsCategory> parentToken = context
					.findResourceToken(GoodsCategory.class, token.getFacade()
							.getCategoryId());
//			ResourceToken<Tenant> tenantToken = context.findResourceToken(
//					Tenant.class, context.find(Login.class).getTenantId());
			context.removeResourceReference(parentToken, token);
//			context.removeResourceReference(tenantToken, token);
			context.removeResource(task.id);
		}

	}

}
