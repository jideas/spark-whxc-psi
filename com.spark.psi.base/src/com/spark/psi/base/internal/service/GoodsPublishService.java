/**
 * 
 */
/**
 * 
 */
package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.Filter;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.da.RecordSetField;
import com.jiuqi.dna.core.da.RecordSetFieldContainer;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.util.StringUtils;
import com.jiuqi.util.json.JSONArray;
import com.jiuqi.util.json.JSONObject;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.reflection.BeanUtils;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Goods;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.SheetNumberType;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.event.GoodsStatusChangeEvent;
import com.spark.psi.base.internal.entity.GoodsTraderLog;
import com.spark.psi.base.internal.entity.helper.GoodsHelper;
import com.spark.psi.base.internal.entity.helper.LevelTreeFilter;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.internal.entity.ormentity.GoodsItemOrmEntity;
import com.spark.psi.base.internal.entity.ormentity.GoodsOrmEntity;
import com.spark.psi.base.internal.entity.ormentity.GoodsTraderLogOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_Goods;
import com.spark.psi.base.internal.service.orm.Orm_GoodsItem;
import com.spark.psi.base.internal.service.orm.Orm_GoodsTraderLogByGoodsId;
import com.spark.psi.base.internal.service.orm.Orm_GoodsTraderLogByGoodsItemIdAndPartnerId;
import com.spark.psi.base.key.goods.GetChildrenGoodsCategoryListKey;
import com.spark.psi.base.key.goods.GetGoodsCategoryLeafNodesKey;
import com.spark.psi.base.publicimpl.GoodsCategoryInfoImpl;
import com.spark.psi.base.publicimpl.GoodsCategoryTreeImpl;
import com.spark.psi.base.publicimpl.GoodsInfoImpl;
import com.spark.psi.base.publicimpl.GoodsInfoItemImpl;
import com.spark.psi.base.publicimpl.GoodsItemDataImpl;
import com.spark.psi.base.publicimpl.GoodsTraderLogItemImpl;
import com.spark.psi.base.publicimpl.PropertyDefineImpl;
import com.spark.psi.base.task.goods.UpdateGoodsTraderLogTask;
import com.spark.psi.base.task.resource.UpdateGoodsCategoryResourceTask;
import com.spark.psi.base.task.resource.UpdateGoodsItemResourceTask;
import com.spark.psi.base.task.resource.UpdateGoodsResourceTask;
import com.spark.psi.base.utils.ExcelReader;
import com.spark.psi.base.utils.GoodsProperyUtil;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.PropertyInputType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryInfo;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;
import com.spark.psi.publish.base.goods.entity.GoodsInfo;
import com.spark.psi.publish.base.goods.entity.GoodsInfoItem;
import com.spark.psi.publish.base.goods.entity.GoodsItemData;
import com.spark.psi.publish.base.goods.entity.GoodsItemDetail;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.base.goods.entity.GoodsTraderLogItem;
import com.spark.psi.publish.base.goods.entity.PropertyDefine;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree.CategoryNode;
import com.spark.psi.publish.base.goods.entity.GoodsTraderLogItem.TraderType;
import com.spark.psi.publish.base.goods.key.FindGoodsCountKey;
import com.spark.psi.publish.base.goods.key.FindGoodsTypeCountKey;
import com.spark.psi.publish.base.goods.key.FindGoodsTypeHadSetPropertiesCountKey;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoByCodeKey;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoItemListKey;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey;
import com.spark.psi.publish.base.goods.key.GetGoodsItemDetailListKey;
import com.spark.psi.publish.base.goods.key.GetGoodsItemListKey;
import com.spark.psi.publish.base.goods.key.GetGoodsInfoListKey.SortField;
import com.spark.psi.publish.base.goods.task.ChangeGoodsCategoryNameTask;
import com.spark.psi.publish.base.goods.task.ChangeGoodsStatusTask;
import com.spark.psi.publish.base.goods.task.CreateGoodsCategoryTask;
import com.spark.psi.publish.base.goods.task.DeleteGoodsCategoryTask;
import com.spark.psi.publish.base.goods.task.DeleteGoodsTask;
import com.spark.psi.publish.base.goods.task.GoodsInfoTask;
import com.spark.psi.publish.base.goods.task.MarkGoodsItemUsedTask;
import com.spark.psi.publish.base.goods.task.UpdateGoodsCategoryTask;
import com.spark.psi.publish.base.goods.task.UpdateGoodsItemThresholdTask;
import com.spark.psi.publish.base.goods.task.ValidationGoodsIsExistTask;
import com.spark.psi.publish.base.goods.task.GoodsInfoTask.ItemMethod;
import com.spark.psi.publish.base.goods.task.UpdateGoodsItemThresholdTask.GoodsItemThresholdItem;
import com.spark.psi.publish.base.goods.task.ValidationGoodsIsExistTask.ErrType;
import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask;

/**
 * 商品相关 外部接口服务
 * 
 * 
 * 
 */
public class GoodsPublishService extends Service {

	/**
	 * 商品分类表
	 */
	final static String Tab_GoodsCategory = ERPTableNames.Base.GoodsCategory
			.getTableName();
	/**
	 * 商品表
	 */
	final static String Tab_Goods = ERPTableNames.Base.Goods.getTableName();
	/**
	 * 商品条目表
	 */
	final static String Tab_GoodsItem = ERPTableNames.Base.GoodsItem
			.getTableName();

	final Orm_GoodsItem orm_GoodsItem;

	final Orm_Goods orm_Goods;

	final Orm_GoodsTraderLogByGoodsId orm_GoodsTraderLogByGoodsId;

	final Orm_GoodsTraderLogByGoodsItemIdAndPartnerId orm_GoodsTraderLogByGoodsItemIdAndPartnerId;

	static final String lock = "0";

	static final int MAXIMUM = 1 << 10;

	protected GoodsPublishService(
			final Orm_GoodsItem orm_GoodsItem,
			final Orm_Goods orm_Goods,
			final Orm_GoodsTraderLogByGoodsId orm_GoodsTraderLogByGoodsId,
			final Orm_GoodsTraderLogByGoodsItemIdAndPartnerId orm_GoodsTraderLogByGoodsIdAndPartnerId) {
		super("com.spark.psi.base.internal.service.GoodsPublishService");
		this.orm_GoodsItem = orm_GoodsItem;
		this.orm_Goods = orm_Goods;
		this.orm_GoodsTraderLogByGoodsId = orm_GoodsTraderLogByGoodsId;
		this.orm_GoodsTraderLogByGoodsItemIdAndPartnerId = orm_GoodsTraderLogByGoodsIdAndPartnerId;
	}

	/*************************************
	 * 商品分类相关服务
	 ************************************/

	/**
	 * 获得商品分类维护对象
	 */
	@Publish
	protected class GetGoodsCategoryInfoByIdProvider extends
			OneKeyResultProvider<GoodsCategoryInfo, GUID> {

		@Override
		protected GoodsCategoryInfo provide(Context context, GUID key)
				throws Throwable {
			GoodsCategoryInfoImpl impl = new GoodsCategoryInfoImpl();
			GoodsCategory goodsCategory = context
					.find(GoodsCategory.class, key);
			impl.setScale(goodsCategory.getScale());
			impl.setId(key);
			impl.setName(goodsCategory.getName());
			impl.setCategoryNo(goodsCategory.getCategoryNo());
			//
			StringBuffer sql = new StringBuffer(
					"define query getProperties(@id guid)");
			sql.append(" begin");
			sql.append(" select t.properties as properties ");
			sql.append(" from " + Tab_GoodsCategory + " as t");
			sql.append(" where t.RECID=@id");
			sql.append(" end");
			DBCommand dbc = context.prepareStatement(sql);
			dbc.setArgumentValue(0, key);
			RecordSet rs = dbc.executeQuery();

			List<PropertyDefine> propertyDefineList = new ArrayList<PropertyDefine>();
			;
			if (rs.next()) {
				String properties = rs.getFields().get(0).getString();
				try {
					JSONArray array = new JSONArray(properties);
					for (int i = 0; i < array.length(); i++) {
						JSONObject property = array.getJSONObject(i);
						String name = property.getString("name");
						PropertyInputType type = PropertyInputType
								.getPropertyInputType(property
										.getString("type"));
						JSONArray optionArray = property
								.getJSONArray("options");
						String[] options = new String[optionArray.length()];
						for (int j = 0; j < optionArray.length(); j++) {
							options[j] = optionArray.getString(j);
						}
						PropertyDefineImpl propertyDefine = new PropertyDefineImpl(
								GUID.randomID(), name, type, options);
						propertyDefineList.add(propertyDefine);
					}
				} catch (Throwable t) {
				}
			}

			//
			impl.setPropertyDefines(propertyDefineList
					.toArray(new PropertyDefine[0]));

			return impl;
		}

	}

	/**
	 * 查询当前租户的商品分类树
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetGoodsCategoryTreeProvider extends
			ResultProvider<GoodsCategoryTree> {

		@Override
		protected GoodsCategoryTree provide(Context context) throws Throwable {

			List<GoodsCategory> categorys = context
					.getList(GoodsCategory.class);
			ComparatorUtils.sort(categorys, "categoryNo", false);
			List<GoodsCategoryTree.CategoryNode> result = new ArrayList<GoodsCategoryTree.CategoryNode>();
			int i = 0; // 设置了属性的商品分类总数
			for (GoodsCategory goodsCategory : categorys) {
				if (GoodsCategory.ROOT.getId()
						.equals(goodsCategory.getParent())) {
					result.add(toNode(context, goodsCategory));
				}
				if (goodsCategory.isPropertyFlag()) {
					i++;
				}
			}
			GoodsCategoryTreeImpl tree = new GoodsCategoryTreeImpl(result
					.toArray(new GoodsCategoryTree.CategoryNode[result.size()]));
			tree.setCount(categorys.size());
			tree.setPropertiedCount(i);
			return tree;
		}

		private GoodsCategoryTree.CategoryNode toNode(Context context,
				GoodsCategory impl) {
			CategoryNodeImpl node = new CategoryNodeImpl();
			node.setId(impl.getId());
			node.setName(impl.getName());
			node.setCategoryNo(impl.getCategoryNo());
			node.setPropertyFlag(impl.isPropertyFlag());
			List<GoodsCategory> childList = context.getList(
					GoodsCategory.class, new GetChildrenGoodsCategoryListKey(
							impl.getId()));
			ComparatorUtils.sort(childList, "categoryNo", false);
			CategoryNode[] children = new CategoryNodeImpl[childList.size()];
			for (int i = 0; i < childList.size(); i++) {
				children[i] = toNode(context, childList.get(i));
			}
			node.setChildren(children);
			return node;
		}

		public class CategoryNodeImpl implements CategoryNode {
			/**
			 * 分类ID
			 */
			private GUID id;

			/**
			 * 分类名称
			 */
			private String name;

			private String categoryNo;

			/**
			 * 子分类节点
			 */
			private CategoryNode[] children;

			/**
			 * 负分类节点
			 */
			private CategoryNode parent;
			/**
			 * 是否已设置属性
			 */
			protected boolean propertyFlag;

			public boolean isSetPropertyFlag() {
				return propertyFlag;
			}

			public void setPropertyFlag(boolean propertyFlag) {
				this.propertyFlag = propertyFlag;
			}

			/**
			 * 获取分类ID
			 * 
			 * @return
			 */
			public GUID getId() {
				return this.id;
			}

			/**
			 * 获取分类名称
			 * 
			 * @return
			 */
			public String getName() {
				return this.name;
			}

			/**
			 * @return the parent
			 */
			public CategoryNode getParent() {
				return parent;
			}

			/**
			 * @param parent
			 *            the parent to set
			 */
			public void setParent(CategoryNode parent) {
				this.parent = parent;
			}

			/**
			 * 获取子分类节点
			 * 
			 * @return
			 */
			public CategoryNode[] getChildren() {
				return this.children;
			}

			public void setId(GUID id) {
				this.id = id;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getCategoryNo() {
				return categoryNo;
			}

			public void setCategoryNo(String categoryNo) {
				this.categoryNo = categoryNo;
			}

			public void setChildren(CategoryNode[] children) {
				for (int childIndex = 0; childIndex < children.length; childIndex++) {
					CategoryNodeImpl childImp = (CategoryNodeImpl) children[childIndex];
					childImp.setParent(this);
				}
				this.children = children;
			}

		}
	}

	/**
	 * 新增一个商品分类
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class CreateGoodsCategoryHandler extends
			SimpleTaskMethodHandler<CreateGoodsCategoryTask> {

		@Override
		protected void handle(Context context, CreateGoodsCategoryTask task)
				throws Throwable {
			// Tenant tr = TenantHelper.getTenant(context);
			task.setId(context.newRECID());
			GUID parentId = task.getParentId() == null ? GoodsCategory.ROOT
					.getId() : task.getParentId();
			byte[] path = null;
			if (task.getParentId() == null) {
				path = ("00" + task.getId()).getBytes();
			} else {
				GoodsCategory parentType = context.find(GoodsCategory.class,
						task.getParentId());
				path = (new String(parentType.getPath()) + "00" + task.getId())
						.getBytes();
			}
			// goodsType.setPath(path);

			// 1、将本节点添加到其父节点下

			StringBuffer sql = new StringBuffer(
					"define insert addChildGoodsType(@recid guid,"
							+ "@goodsTypeName string, @categoryNo string, @parentGuid guid,@path bytes,@createDate date,@createPerson string,@creatorId guid)");

			sql.append(" begin");
			sql.append(" insert into ").append(Tab_GoodsCategory).append(" ");
			sql
					.append(" (RECID,categoryName,categoryNo,parentId,path,createDate,creator,creatorId)");
			sql
					.append(" values(@recid,@goodsTypeName, @categoryNo, @parentGuid,@path,@createDate,@createPerson,@creatorId)");
			sql.append(" end");

			DBCommand dbc = context.prepareStatement(sql);

			dbc.setArgumentValue(0, task.getId());
			dbc.setArgumentValue(1, task.getName());
			dbc.setArgumentValue(2, task.getCategoryNo());
			dbc.setArgumentValue(3, parentId);
			dbc.setArgumentValue(4, path);
			dbc.setArgumentValue(5, System.currentTimeMillis());
			Employee employee = context.find(Employee.class);
			dbc.setArgumentValue(6, employee.getName());
			dbc.setArgumentValue(7, employee.getId());

			try {
				dbc.executeUpdate();
			} catch (Exception e) {
				throw new IllegalArgumentException("增加商品分类失败，分类名称是否冲突？");
			}
			// 2、将父节点的是否是叶子节点改为非叶子节点
			StringBuffer sql1 = new StringBuffer(
					"define update modifyParentGoodsType(@leafFlag boolean,@parentGuid guid)");
			sql1.append(" begin");
			sql1.append(" update ").append(Tab_GoodsCategory).append(" as a");
			sql1.append(" set leafFlag=@leafFlag");
			sql1.append(" where a.RECID=@parentGuid");
			sql1.append(" end");

			DBCommand dbc1 = context.prepareStatement(sql1);

			dbc1.setArgumentValue(0, false);
			dbc1.setArgumentValue(1, task.getParentId());

			dbc1.executeUpdate();
			if (task.getParentId() != null) {
				context.handle(new UpdateGoodsCategoryResourceTask(task
						.getParentId()),
						UpdateGoodsCategoryResourceTask.Method.Modify);
			}
			//
			// StringBuffer sql2 = new StringBuffer(
			// "define insert addGoodsTypeTongJi(@RECID guid,"
			// +
			// "@goodsTypeGuid guid,@parentGuid guid,@storeAmountUpper double)");
			// sql2.append(" begin");
			// sql2.append(" insert into SA_GOODS_TYPE_TONGJI(RECID,tenantsGuid,goodsTypeGuid,parentGuid,storeAmountUpper)");
			// sql2.append(" values(@RECID,@tenantsGuid,@goodsTypeGuid,@parentGuid,@storeAmountUpper)");
			// sql2.append(" end");
			// dbc1 = context.prepareStatement(sql2);
			// dbc1.setArgumentValue(0, context.newRECID());
			// dbc1.setArgumentValue(1, task.getId());
			// dbc1.setArgumentValue(2, task.getParentId());
			// dbc1.setArgumentValue(3, 0);
			// dbc1.executeUpdate();
			context.handle(new UpdateGoodsCategoryResourceTask(task.getId()),
					UpdateGoodsCategoryResourceTask.Method.Put);

			//
			task.setId(task.getId());
		}

	}

	/**
	 * 修改商品分类信息
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class UpdateGoodsCategoryHandler extends
			SimpleTaskMethodHandler<UpdateGoodsCategoryTask> {

		@Override
		protected void handle(Context context, UpdateGoodsCategoryTask task)
				throws Throwable {
			// 序列化属性列表
			PropertyDefine[] propertyDefines = task.getPropertyDefines();
			String properties = null;
			if (propertyDefines != null && propertyDefines.length > 0) {
				JSONArray propertyArray = new JSONArray();
				if (propertyDefines != null) {
					for (PropertyDefine propertyDefine : propertyDefines) {
						if (!StringHelper.isEmpty(propertyDefine.getName())) {
							String[] options = propertyDefine.getOptions();
							JSONObject propertyObj = new JSONObject();
							propertyObj.put("name", propertyDefine.getName());
							propertyObj.put("type", propertyDefine
									.getValueInputMode().getCode());
							JSONArray optionArray = new JSONArray();
							propertyObj.put("options", optionArray);
							if (options != null) {
								for (String option : options) {
									optionArray.put(option);
								}
							}
							propertyArray.put(propertyObj);
						}
					}
				}
				properties = propertyArray.toString();
			}
			//
			modifyGoodsCategory(context, task.getId(), task.getName(), task
					.getScale(), task.getCategoryNo(), properties); // 修改商品分类的基础信息
			//
			context.handle(new UpdateGoodsCategoryResourceTask(task.getId()),
					UpdateGoodsCategoryResourceTask.Method.Modify);

			// deleteProperty(context, task.getId());// 清空所有属性及属性项目
			// saveProperty(context, task); // 重新保存属性项目
		}

		// private void saveProperty(Context context, UpdateGoodsCategoryTask
		// task) {
		// StringBuffer sql = new StringBuffer(
		// "define insert addGoodsTypeProerty(@recid guid,@goodsTypeGuid guid,"
		// +
		// "@propertyName string,@propertyInputType string,@propertyOrder int)");
		// sql.append(" begin");
		// sql.append(" insert into sa_goods_type_property");
		// sql.append(" (RECID,goodsTypeGuid,propertyName,propertyInputType,propertyOrder)");
		// sql.append(" values(@recid,@goodsTypeGuid,@propertyName,@propertyInputType,@propertyOrder)");
		// sql.append(" end");
		//
		// DBCommand dbc = context.prepareStatement(sql);
		// StringBuffer optionSql = new StringBuffer(
		// "define insert addGoodsTypeProject(@recid guid,@propertyGuid guid,"
		// + "@projectValue string,@projectKey string,@projectOrder int)");
		// optionSql.append(" begin");
		// optionSql.append(" insert into sa_goods_type_project");
		// optionSql
		// .append(" (RECID,propertyGuid,projectValue,projectKey,projectOrder)");
		// optionSql
		// .append(" values(@recid,@propertyGuid,@projectValue,@projectKey,@projectOrder)");
		// optionSql.append(" end");
		// DBCommand dbc2 = context.prepareStatement(optionSql);
		// for (int i = 0; i < task.getPropertyDefines().length; i++) {
		// PropertyDefine property = task.getPropertyDefines()[i];
		// dbc.setArgumentValue(0, context.newRECID());
		// dbc.setArgumentValue(1, task.getId());
		// dbc.setArgumentValue(2, property.getName());
		// dbc.setArgumentValue(3, property.getValueInputMode().getCode());
		// dbc.setArgumentValue(4, i);
		// dbc.executeUpdate(); // 保存属性对象
		// for (int n = 0; n < property.getOptions().length; n++) {
		// dbc2.setArgumentValue(0, context.newRECID());
		// dbc2.setArgumentValue(1, property.getId());
		// dbc2.setArgumentValue(2, property.getOptions()[n]);
		// dbc2.setArgumentValue(3, n);
		// dbc2.setArgumentValue(4, n);
		// dbc2.executeUpdate(); // 保存属性条目对象
		// }
		// }
		// }

		private void modifyGoodsCategory(Context context, GUID categoryId,
				String categoryName, int scale, String categoryNo,
				String properties) {
			GoodsCategory category = context.find(GoodsCategory.class,
					categoryId);
			StringBuffer sql = new StringBuffer(
					"define update modifyGoodsTypeName(@recid guid,@categoryName string,@categoryNo string, @scale int,@setPropertyFlag boolean, @properties string)");
			sql.append(" begin");
			sql.append(" update ").append(Tab_GoodsCategory).append("  as a");
			sql
					.append(" set categoryName=@categoryName, scale = @scale, categoryNo=@categoryNo, setPropertyFlag = @setPropertyFlag, properties = @properties");
			sql.append(" where a.RECID=@recid");
			sql.append(" end");

			DBCommand dbc = context.prepareStatement(sql);

			dbc.setArgumentValue(0, categoryId);
			dbc.setArgumentValue(1, categoryName);
			dbc.setArgumentValue(2, categoryNo);
			dbc.setArgumentValue(3, scale);
			// if (task.getPropertyDefines().length > 1) { XXX:必须多与一个属性才设置属性标志？
			if (properties != null) {
				dbc.setArgumentValue(4, true); // 已设置属性标志
				dbc.setArgumentValue(5, properties);
			} else {
				dbc.setArgumentValue(4, category.isPropertyFlag());
				dbc.setArgumentValue(5, "{}");
			}

			try {
				dbc.executeUpdate();
			} catch (Exception e) {
				throw new IllegalArgumentException("编辑商品分类失败，分类名称是否冲突？");
			}
			// StringBuffer sql1 = new StringBuffer(
			// "define update modifyGoodsTypeTongJi(@goodsTypeGuid guid,@categoryNo string)");
			// sql1.append(" begin");
			// sql1.append(" update SA_GOODS_TYPE_TONGJI as a");
			// sql1.append(" set categoryNo=@categoryNo");
			// sql1.append(" where 1=1");
			// sql1.append(" and a.goodsTypeGuid=@goodsTypeGuid");
			// sql1.append(" end");
			//
			// dbc = context.prepareStatement(sql1);
			// dbc.setArgumentValue(0, categoryId);
			// dbc.setArgumentValue(1, categoryNo);
			// dbc.executeUpdate();

		}

		// /**
		// * <p>
		// * 删除某一个商品分类属性下的所有属性项目
		// * </p>
		// *
		// */
		// protected void deleteProperty(Context context, GUID categoryId)
		// throws Throwable {
		// // 删除该属性下的所有属性项目
		//
		// GUID[] propertys = getGoodsCategoryPropertyList(context, categoryId);
		// for (GUID guid : propertys) { // 删除所有分类属性的项目值
		// deleteOptions(context, guid);
		// }
		// StringBuffer sql1 = new StringBuffer(
		// "define delete deleteGoodsTypeProperty(@goodsTypeGuid guid)");
		// sql1.append(" begin");
		// sql1.append(" delete from sa_goods_type_property as a");
		// sql1.append(" where a.goodsTypeGuid=@goodsTypeGuid");
		// sql1.append(" end");
		// DBCommand dbc1 = context.prepareStatement(sql1);
		// dbc1.setArgumentValue(0, categoryId);
		// dbc1.executeUpdate();
		// }
		//
		// protected void deleteOptions(Context context, GUID PropertyId)
		// throws Throwable {
		// // 删除该属性项目
		// StringBuffer sql1 = new StringBuffer(
		// "define delete deleteGoodsTypeProjectByRecid(@propertyGuid guid)");
		// sql1.append(" begin");
		// sql1.append(" delete from sa_goods_type_project as a");
		// sql1.append(" where a.propertyGuid =@propertyGuid");
		// sql1.append(" end");
		// DBCommand dbc1 = context.prepareStatement(sql1);
		// dbc1.setArgumentValue(0, PropertyId);
		// dbc1.executeUpdate();
		// }

		// protected GUID[] getGoodsCategoryPropertyList(Context context,
		// GUID goodsTypeGuid) throws Throwable {
		// StringBuffer sql = new StringBuffer(
		// "define query getOnePropertyProject(@goodsTypeGuid guid)");
		// sql.append(" begin");
		// sql.append(" select a.recid as recid ");
		// sql.append(" from SA_GOODS_TYPE_PROPERTY as a");
		// sql.append(" where a.recid=@goodsTypeGuid");
		// sql.append(" end");
		//
		// DBCommand dbc = context.prepareStatement(sql);
		// dbc.setArgumentValue(0, goodsTypeGuid);
		// RecordSet rs = dbc.executeQuery();
		// List<GUID> list = new ArrayList<GUID>();
		// while (rs.next()) {
		// list.add(rs.getFields().get(0).getGUID());
		// }
		// return list.toArray(new GUID[list.size()]);
		// }

	}

	/**
	 * 
	 * <p>
	 * 修改商品分类名称
	 * </p>
	 * 
	 */
	@Publish
	protected final class ChangeGoodsCategoryNameHandler extends
			SimpleTaskMethodHandler<ChangeGoodsCategoryNameTask> {

		@Override
		protected void handle(Context context, ChangeGoodsCategoryNameTask task)
				throws Throwable {
			StringBuffer sql = new StringBuffer(
					"define update modifyGoodsCategoryName(").append(
					"@id guid,@name string) ").append(" begin").append(
					"    update ").append(Tab_GoodsCategory).append("  as a")
					.append("    set categoryName=@name").append(
							"    where 1=1").append("    and a.recid=@id")
					.append(" end");

			DBCommand dbc = context.prepareStatement(sql);
			dbc.setArgumentValue(0, task.getCategoryId());
			dbc.setArgumentValue(1, task.getName());
			dbc.executeUpdate();
			context.handle(new UpdateGoodsCategoryResourceTask(task
					.getCategoryId()),
					UpdateGoodsCategoryResourceTask.Method.Modify);
		}
	}

	@Publish
	protected final class DeleteGoodsCategoryHandler extends
			SimpleTaskMethodHandler<DeleteGoodsCategoryTask> {

		@Override
		protected void handle(Context context, DeleteGoodsCategoryTask task)
				throws Throwable {

			// StringBuffer sql =
			// new StringBuffer(
			// "define delete modifyGoodsPropertystatus(@id guid)");
			// sql.append(" begin");
			// sql.append(" delete from SA_GOODS_TYPE as a");
			// sql.append(" where a.RECID=@id ");
			// sql.append(" end");
			//
			// DBCommand dbc = context.prepareStatement(sql);
			// dbc.setArgumentValue(0, task.getId());
			// dbc.executeUpdate(); // 删除商品条目
			// GoodsCategory category =
			// context.find(GoodsCategory.class,task.getId());
			// if(category.is)
			GoodsCategory category = context.find(GoodsCategory.class, task
					.getId());
			ResourceToken<Tenant> token = TenantHelper.getTenantToken(context,
					category.getTenantId());
			List<GoodsCategory> list = context.getResourceReferences(
					GoodsCategory.class, token,
					new LevelTreeFilter<GoodsCategory>(category.getPath()));
			for (GoodsCategory goodsCategory : list) {
				int result = SqlUtil.DeleteById(context, Tab_GoodsCategory,
						goodsCategory.getId());
				if (result > 0)
					context.handle(new UpdateGoodsCategoryResourceTask(
							goodsCategory.getId()),
							UpdateGoodsCategoryResourceTask.Method.Remove);
			}
			int result = SqlUtil.DeleteById(context, Tab_GoodsCategory, task
					.getId());
			if (result > 0)
				context.handle(
						new UpdateGoodsCategoryResourceTask(task.getId()),
						UpdateGoodsCategoryResourceTask.Method.Remove);
		}
	}

	/*************************************
	 * 商品相关服务
	 ************************************/

	/**
	 * 验证商品名称和编号是否重复
	 */
	@Publish
	protected final class ValidationGoodsNameIsExistProvider extends
			SimpleTaskMethodHandler<ValidationGoodsIsExistTask> {

		@Override
		protected void handle(Context context, ValidationGoodsIsExistTask task)
				throws Throwable {
			// ResourceToken<Tenant> token =
			// TenantHelper.getTenantToken(context);
			List<Goods> list = context.getList(Goods.class);
			if (!StringUtils.isEmpty(task.getName())) {
				for (Goods goods : list) { // 校验商品名称在对应商品分类中是否已经存在
					if (goods.getId().equals(task.getId()))
						continue;
					if (goods.getGoodsName().equals(task.getName())) {
						task.setErrType(ErrType.Name);
						task.setExist(true);
						break;
					}
				}
			}
			if (StringHelper.isEmpty(task.getCode()))
				return;
			List<GoodsItem> itemList = context.getList(GoodsItem.class);
			for (GoodsItem item : itemList) {
				if (task.getItemId() != null
						&& item.getId().equals(task.getItemId()))
					continue; // 排除本身
				if (item.getGoodsNo() == null || item.getSpec() == null)
					continue; // 编号为空的商品不参与比较
				if (!item.getGoodsNo().equals(task.getCode())
						|| !item.getSpec().equals(task.getSpec()))
					continue;
				if (task.getErrType() == null) { // 如果名称没重复
					task.setErrType(ErrType.SPECANDNUMBER);
				} else if (task.getErrType().equals(ErrType.Name)) { // 如果名称也重复了
					task.setErrType(ErrType.All);
				}
				task.setExist(true);
			}
		}

	}

	@Publish
	protected class GetGoodsInfoItemListProvider
			extends
			OneKeyResultProvider<ListEntity<GoodsInfoItem>, GetGoodsInfoItemListKey> {

		@Override
		protected ListEntity<GoodsInfoItem> provide(Context context,
				GetGoodsInfoItemListKey key) throws Throwable {
			StringBuffer sql = new StringBuffer(
					"define query getGoodsInfoItem(");
			sql.append("@tenantsGuid guid");
			sql.append(",@goodsstatus string");
			if (key.getCategoryId() != null) {
				sql.append(",@category guid");
			}
			if (!StringUtils.isEmpty(key.getSearchText())) {
				sql.append(",@searchText string");
			}
			sql.append(")");
			sql.append(" begin").append(" select ")
					.append(" a.RECID as recid,").append(
							" a.goodsNo as goodsNo,").append(
							" a.goodsName as goodsName,").append(
							" a.salePrice as salePrice,").append(
							" a.refflag as refflag ").append(" from ").append(
							Tab_Goods).append("  AS a").append(
							" where a.tenantsGuid=@tenantsGuid").append(
							" and a.goodsstatus=@goodsstatus");
			if (key.getCategoryId() != null) {
				sql.append(" and  a.goodsTypeGuid=@category");
			}
			if (!StringUtils.isEmpty(key.getSearchText())) {
				sql
						.append(" and(a.goodsNo like '%' + @searchText +'%' or a.goodsName like '%' + @searchText + '%' )");
			}
			if (key.isNopriceOnly()) {
				sql.append(" and a.salePrice = 0");
			}
			sql.append(" order by a.createDate desc").append(" end");
			//
			DBCommand dbc = context.prepareStatement(sql);
			int index = 0;
			dbc.setArgumentValue(index++, context.find(Login.class)
					.getTenantId());
			dbc.setArgumentValue(index++, key.getStatus().getCode());
			if (key.getCategoryId() != null) {
				dbc.setArgumentValue(index++, key.getCategoryId());
			}
			if (!StringUtils.isEmpty(key.getSearchText())) {
				dbc.setArgumentValue(index++, key.getSearchText());
			}
			List<GoodsInfoItem> dataList = new ArrayList<GoodsInfoItem>();
			int count = 0;
			RecordSet rs = dbc.executeQuery();
			while (rs.next()) {
				count++;
				RecordSetFieldContainer<? extends RecordSetField> fields = rs
						.getFields();
				GoodsInfoItemImpl item = new GoodsInfoItemImpl();
				item.setId(fields.get(0).getGUID());
				item.setCode(fields.get(1).getString());
				item.setName(fields.get(2).getString());
				item.setPrice(fields.get(3).getDouble());
				item.setRef(fields.get(4).getBoolean());
				dataList.add(item);
			}
			return new ListEntity<GoodsInfoItem>(dataList, count);
		}
	}

	/**
	 * 获得商品维护对象
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetGoodsInfoByIdProvider extends
			OneKeyResultProvider<GoodsInfo, GUID> {

		@Override
		protected GoodsInfo provide(Context context, GUID guid)
				throws Throwable {
			Goods goods = context.find(Goods.class, guid);
			if (goods == null)
				return null;
			GoodsInfoImpl entity = GoodsHelper.goodsToGoodsInfo(context, goods);
			entity.setItems(getGoodsItem(context, goods));
			entity.setCategory(context.find(GoodsCategoryInfo.class, goods
					.getCategoryId()));
			return entity;
		}

		private List<GoodsItemDataImpl> getGoodsItem(final Context context,
				Goods goods) {
			List<GoodsItem> list = context.getResourceReferences(
					GoodsItem.class, context.findResourceToken(Goods.class,
							goods.getId()));
			List<GoodsItemDataImpl> result = new ArrayList<GoodsItemDataImpl>();
			for (GoodsItem impl : list) {
				if (impl.getGoodsId().equals(goods.getId())) {
					result.add(GoodsHelper.goodsItemToItemData(impl));
				}
			}
			return result;
		}

	}

	/**
	 * 根据商品编号获得商品信息
	 * 
	 * 
	 */
	@Publish
	protected class GetGoodsInfoByCodeProvider extends
			OneKeyResultProvider<GoodsInfo, GetGoodsInfoByCodeKey> {

		@Override
		protected GoodsInfo provide(Context context,
				final GetGoodsInfoByCodeKey key) throws Throwable {
			ResourceToken<Tenant> token = TenantHelper.getTenantToken(context);
			List<Goods> list = context.getResourceReferences(Goods.class,
					token, new Filter<Goods>() {

						public boolean accept(Goods item) {
							if (null == item.getGoodsCode()
									|| null == key.getGoodsCode()) {
								return false;
							}
							return item.getGoodsCode().equals(
									key.getGoodsCode());
						}
					});
			if (list.size() < 1)
				return null;
			GoodsInfoImpl goods = GoodsHelper.goodsToGoodsInfo(context, list
					.get(0));
			goods.setItems(getGoodsItem(context, list.get(0)));
			goods.setCategory(context.find(GoodsCategoryInfo.class, list.get(0)
					.getCategoryId()));
			return goods;
		}

		private List<GoodsItemDataImpl> getGoodsItem(final Context context,
				Goods goods) {
			List<GoodsItem> list = context.getResourceReferences(
					GoodsItem.class, context.findResourceToken(Goods.class,
							goods.getId()));
			List<GoodsItemDataImpl> result = new ArrayList<GoodsItemDataImpl>();
			for (GoodsItem impl : list) {
				if (impl.getGoodsId().equals(goods.getId())) {
					result.add(GoodsHelper.goodsItemToItemData(impl));
				}
			}
			return result;
		}

	}

	// protected class GetGoodsListProvider extends
	// OneKeyResultListProvider<GoodsItem, TKey>

	/**
	 * 修改商品状态
	 */
	@Publish
	protected class ChanageGoodsStatusHandler extends
			SimpleTaskMethodHandler<ChangeGoodsStatusTask> {

		@Override
		protected void handle(Context context, ChangeGoodsStatusTask task)
				throws Throwable {
			GoodsStatus status = task.isTurnOnOrOff() ? GoodsStatus.ON_SALE
					: GoodsStatus.STOP_SALE;
			StringBuffer sql1 = new StringBuffer(
					"define update modifyBatchGoodsStatus(@RECID guid,@goodsstatus string)");
			sql1.append(" begin");
			sql1.append(" update ").append(Tab_Goods).append("  as a");
			sql1.append(" set status=@goodsstatus");
			sql1.append(" where 1=1");
			sql1.append(" and a.RECID=@RECID");
			sql1.append(" end");

			DBCommand dbc1 = context.prepareStatement(sql1);
			// 首先向属性表中写入数据
			for (GUID guid : task.getIds()) {
				updateGoodsItemstatus(context, status, guid);
				dbc1.setArgumentValue(0, guid);
				dbc1.setArgumentValue(1, status.getCode());
				dbc1.executeUpdate();
				context.handle(new UpdateGoodsResourceTask(guid),
						UpdateGoodsResourceTask.Method.Modify);
			}

		}

		protected void updateGoodsItemstatus(Context context,
				GoodsStatus status, GUID goodsId) throws Throwable {

			StringBuffer sql = new StringBuffer(
					"define update modifyGoodsPropertystatus("
							+ "@status string,@goodsId guid)");
			sql.append(" begin");
			sql.append(" update ");
			sql.append(Tab_GoodsItem);
			sql.append(" as a");
			sql.append(" set status=@status");
			sql.append(" where 1=1");
			sql.append(" and a.goodsId=@goodsId");
			sql.append(" end");

			DBCommand dbc = context.prepareStatement(sql);
			dbc.setArgumentValue(0, status.getCode());
			dbc.setArgumentValue(1, goodsId);
			dbc.executeUpdate();
			ResourceToken<Goods> token = context.findResourceToken(Goods.class,
					goodsId);
			for (GoodsItem item : context.getResourceReferences(
					GoodsItem.class, token)) {
				context.handle(new UpdateGoodsItemResourceTask(item.getId()),
						UpdateGoodsItemResourceTask.Method.Modify);
				context.dispatch(new GoodsStatusChangeEvent(item.getId()));
			}
		}

	}

	/**
	 * 删除商品信息
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class DeleteGoodsHandler extends
			SimpleTaskMethodHandler<DeleteGoodsTask> {

		@Override
		protected void handle(Context context, DeleteGoodsTask task)
				throws Throwable {
			// 更新商品的状态
			StringBuffer sql1 = new StringBuffer(
					"define delete deleteBatchGoodsInfo(@RECID guid)");
			sql1.append(" begin");
			sql1.append(" delete from ").append(Tab_Goods).append("  as a");
			sql1.append(" where 1=1");
			sql1.append(" and a.RECID=@RECID");
			sql1.append(" and a.refFlag=false");
			sql1.append(" end");

			StringBuffer sql = new StringBuffer(
					"define delete modifyGoodsPropertystatus(@goodsId guid)");
			sql.append(" begin");
			sql.append(" delete from ");
			sql.append(Tab_GoodsItem);
			sql.append(" as a");
			sql.append(" where 1=1");
			sql.append(" and a.goodsId=@goodsId");
			sql.append(" and a.refFlag=false");
			sql.append(" end");

			DBCommand dbc = context.prepareStatement(sql);

			DBCommand dbc1 = context.prepareStatement(sql1);
			for (GUID guid : task.getIds()) {
				ResourceToken<Goods> token = context.findResourceToken(
						Goods.class, guid);
				if (token.getFacade().isRefFlag())
					throw new IllegalArgumentException(guid + "商品已经使用过了，不能删除");
				for (GoodsItem item : context.getResourceReferences(
						GoodsItem.class, token)) {
					context.handle(
							new UpdateGoodsItemResourceTask(item.getId()),
							UpdateGoodsItemResourceTask.Method.Remove);
				}
				dbc.setArgumentValue(0, guid);
				dbc.executeUpdate(); // 删除商品条目
				dbc1.setArgumentValue(0, guid);
				dbc1.executeUpdate(); // 删除商品
				context.handle(new UpdateGoodsResourceTask(guid),
						UpdateGoodsResourceTask.Method.Remove);
			}
		}
	}

	/**
	 * 新增商品基础信息
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class CreateGoodsInfoHandler extends
			TaskMethodHandler<GoodsInfoTask, GoodsInfoTask.Method> {

		protected CreateGoodsInfoHandler() {
			super(GoodsInfoTask.Method.Create);
		}

		@Override
		protected void handle(Context context, GoodsInfoTask goodsInfo)
				throws Throwable {
			// Tenant tr = TenantHelper.getTenant(context);
			Employee emp = context.find(Employee.class);
			GoodsStatus status = GoodsStatus.ON_SALE;
			boolean isOnSale = false, isStopSale = false;
			if (goodsInfo.getItems() != null) {
				for (GoodsInfoTask.Item item : goodsInfo.getItems()) {
					if (item.isOnsale()) {
						isOnSale = true;
					} else {
						isStopSale = true;
					}
				}
			}
			if (!(isOnSale && isStopSale)) {
				if (isStopSale) {
					status = GoodsStatus.STOP_SALE;
				}
			}
			// 然后向商品信息表中写入数据
			// 然后将新数据插入表中
			// StringBuffer sql1 = new StringBuffer(
			// "define insert addGoodsInfo(@RECID guid,@tenantsGuid guid,@goodsNo string,"
			// +
			// "@goodsName string,@goodsTypeGuid guid,@salePrice double,@remark string,@createDate date,@createPerson string,@goodsstatus string,@setPriceFlag boolean)");
			// sql1.append(" begin");
			// sql1.append(" insert into ").append(Tab_Goods).append(" ");
			// sql1.append(" (RECID,tenantsGuid,goodsNo,goodsName,goodsTypeGuid,salePrice,remark,createDate,createPerson,goodsstatus,setPriceFlag)");
			// sql1.append(" values(@RECID,@tenantsGuid,@goodsNo,@goodsName,@goodsTypeGuid,@salePrice,@remark,@createDate,@createPerson,@goodsstatus,@setPriceFlag)");
			// sql1.append(" end");
			//
			// DBCommand dbc1 = context.prepareStatement(sql1);
			// goodsInfo.setId(context.newRECID());
			// dbc1.setArgumentValue(0, goodsInfo.getId());
			// dbc1.setArgumentValue(1, tr.getId());
			// dbc1.setArgumentValue(2, goodsInfo.getCode());
			// dbc1.setArgumentValue(3, goodsInfo.getName());
			// dbc1.setArgumentValue(4, goodsInfo.getCategoryId());
			// dbc1.setArgumentValue(5, goodsInfo.getSalePrice());
			// dbc1.setArgumentValue(6, goodsInfo.getRemark());
			// dbc1.setArgumentValue(7, System.currentTimeMillis());
			// dbc1.setArgumentValue(8, emp.getName());
			// // 向属性表中写入数据
			// dbc1.setArgumentValue(9, status.getCode());
			// dbc1.setArgumentValue(10, true);
			// dbc1.executeUpdate();

			synchronized (lock) {
				goodsInfo.setId(context.newRECID());
				GoodsOrmEntity entity = new GoodsOrmEntity();
				entity.setId(goodsInfo.getId());

				GoodsCategory goodsCategory = context.find(GoodsCategory.class,
						goodsInfo.getCategoryId());
				String maxCode = goodsCategory.getCategoryNo() + "0000";
				GoodsItem[] gis = goodsCategory.getGoodsItems(context);
				if (gis.length > 0) {
					for (GoodsItem gi : gis) {
						if (Integer.parseInt(gi.getGoodsCode()) > Integer
								.parseInt(maxCode)) {
							maxCode = gi.getGoodsCode();
						}
					}
				}
				goodsInfo.setCode(StringHelper.addOneInt(maxCode));
				// String code = context.get(String.class,
				// SheetNumberType.OnlineReturn);
				// code = code.substring(5, code.length());
				// entity.setGoodsCode(code);
				entity.setGoodsCode(goodsInfo.getCode());
				entity.setGoodsName(goodsInfo.getName());
				entity.setNamePY(PinyinHelper.getLetter(goodsInfo.getName()));
				entity.setCategoryId(goodsInfo.getCategoryId());
				entity.setSalePrice(goodsInfo.getSalePrice());
				entity.setRemark(goodsInfo.getRemark());
				entity.setCreatorId(emp.getId());
				entity.setCreator(emp.getName());
				entity.setCreateDate(System.currentTimeMillis());
				entity.setStatus(status.getCode());
				entity.setJointVenture(goodsInfo.isJointVenture());
				entity.setSupplierId(goodsInfo.getSupplierId());
				entity.setShelfLife(goodsInfo.getShelfLife());
				entity.setWarningDay(goodsInfo.getWarningDay());

				ORMAccessor<GoodsOrmEntity> orm = context
						.newORMAccessor(orm_Goods);
				try {
					orm.insert(entity);
				} finally {
					orm.unuse();
				}

				context.handle(new UpdateGoodsResourceTask(goodsInfo.getId()),
						UpdateGoodsResourceTask.Method.Put);
				if (goodsInfo.getItems() != null) {
					for (GoodsInfoTask.Item item : goodsInfo.getItems()) {
						saveGoodsProperty(context, goodsInfo, item);
					}
				}
			}
		}
	}

	private void saveGoodsProperty(Context context, GoodsInfoTask goodsInfo,
			GoodsInfoTask.Item goodsItem) {
		// Tenant tr = TenantHelper.getTenant(context);
		ORMAccessor<GoodsItemOrmEntity> acc = context
				.newORMAccessor(orm_GoodsItem);
		if (goodsItem.getMethod() == ItemMethod.Delete) {
			acc.delete(goodsItem.getId());
			context.handle(new UpdateGoodsItemResourceTask(goodsItem.getId()),
					UpdateGoodsItemResourceTask.Method.Remove);
		} else {
			GoodsItemOrmEntity entity;
			GoodsItem resource = context.find(GoodsItem.class, goodsItem
					.getId());
			boolean isGoodsStatusChange = false;
			if (resource == null) {
				entity = new GoodsItemOrmEntity();
				entity.setId(goodsItem.getId());
				String serialNumber = context.get(String.class,
						SheetNumberType.GoodsSerial);
				serialNumber = serialNumber.substring(0, serialNumber.length());
				entity.setSerialNumber(serialNumber);
			} else {
				entity = acc.findByRECID(goodsItem.getId());
				isGoodsStatusChange = !entity.getStatus().equals(
						goodsItem.isOnsale() ? GoodsStatus.ON_SALE.getCode()
								: GoodsStatus.STOP_SALE.getCode());
			}
			// entity.setTenantId(tr.getId());
			entity.setGoodsId(goodsInfo.getId());
			entity.setGoodsName(goodsInfo.getName());
			entity.setNamePY(PinyinHelper.getLetter(goodsInfo.getName()));
			entity.setGoodsCode(goodsInfo.getCode());
			entity.setCategoryId(goodsInfo.getCategoryId());
			entity.setStatus(goodsItem.isOnsale() ? GoodsStatus.ON_SALE
					.getCode() : GoodsStatus.STOP_SALE.getCode());
			entity.setGoodsProperties(GoodsProperyUtil
					.subGoodsPropertyToString(goodsItem.getPropertyValues()));
			entity.setGoodsNo(goodsItem.getGoodsNo());
			entity.setSalePrice(goodsItem.getSalePrice());
			entity.setLossRate(goodsItem.getLossRate());
			entity.setOriginalPrice(goodsItem.getOriginalPrice());
			entity.setSpec(goodsItem.getGoodsSpec());
			entity.setShelfLife(goodsInfo.getShelfLife());
			entity.setWarningDay(goodsInfo.getWarningDay());
			entity.setGoodsUnit(goodsItem.getUnit());
			entity.setStandardCost(goodsItem.getStandardCost());

			if (resource == null) {
				entity.setCreateDate(System.currentTimeMillis());
				Employee emp = context.find(Employee.class, context.find(
						Login.class).getEmployeeId());
				entity.setCreatorId(emp.getId());
				entity.setCreator(emp.getName());
				entity.setCreateDate(System.currentTimeMillis());
				acc.insert(entity);
				context.handle(new UpdateGoodsItemResourceTask(entity.getId()),
						UpdateGoodsItemResourceTask.Method.Put);
			} else {
				acc.update(entity);
				context.handle(new UpdateGoodsItemResourceTask(entity.getId()),
						UpdateGoodsItemResourceTask.Method.Modify);
				if (isGoodsStatusChange) {
					context.dispatch(new GoodsStatusChangeEvent(goodsItem
							.getId()));
				}
			}
			// 停售时更新网站商品发布状态
			if (!goodsItem.isOnsale()) {

			}
			// update cms_goods同步更新网站对应商品的销售价格
			StringBuffer updateCmsSql = new StringBuffer(
					"define update modifyCmsGoods(");
			updateCmsSql
					.append("@RECID guid,@realPrice double, @goodsname string,@goodsNo string,@goodsSpec string,@goodsUnit string,@originalPrice double)");
			updateCmsSql.append(" begin");
			updateCmsSql.append(" update cms_goods ").append("  as a");
			updateCmsSql.append(" set realPrice=@realPrice");
			updateCmsSql.append(", goodsName=@goodsname");
			updateCmsSql.append(", goodsNo=@goodsNo");
			updateCmsSql.append(", goodsSpec=@goodsSpec");
			updateCmsSql.append(", goodsUnit=@goodsUnit");
			updateCmsSql.append(", originalPrice=@originalPrice");
			if (GoodsStatus.STOP_SALE.getCode().equals(entity.getStatus())) {
				updateCmsSql.append(", isPublished=false ");
			}
			updateCmsSql.append(" where a.recid=@RECID");
			updateCmsSql.append(" end");
			DBCommand dbc3 = context.prepareStatement(updateCmsSql);
			dbc3.setArgumentValues(entity.getId(), entity.getSalePrice(),
					entity.getGoodsName(), entity.getGoodsNo(), entity
							.getSpec(), entity.getGoodsUnit(), entity
							.getOriginalPrice());
			int result = dbc3.executeUpdate();
			if (result < 1) {
				System.out.println("商品同步出错。。。。。");
			}
		}
	}

	/**
	 * 修改商品基础信息
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class UpdateGoodsInfoHandler extends
			TaskMethodHandler<GoodsInfoTask, GoodsInfoTask.Method> {

		protected UpdateGoodsInfoHandler() {
			super(GoodsInfoTask.Method.Update);
		}

		@Override
		protected void handle(Context context, GoodsInfoTask goodsInfo)
				throws Throwable {
			Employee emp = context.find(Employee.class);
			boolean isOnSale = false, isStopSale = false;
			GoodsStatus goodsstatus = GoodsStatus.ON_SALE;
			// deleteGoodsProperty(context, goodsInfo.getId()); //
			// XXX：就服务层逻辑而言，不够严谨。应该是对于已ref的条目进行更新，非ref的，可以先删除再增加
			if (goodsInfo.getItems() != null) {
				for (GoodsInfoTask.Item item : goodsInfo.getItems()) {
					if (item.isOnsale()) {
						isOnSale = true;
					} else {
						isStopSale = true;
					}
					// item.setId(context.newRECID());
					saveGoodsProperty(context, goodsInfo, item);
					// context.handle(
					// new UpdateGoodsItemResourceTask(item.getId()),
					// UpdateGoodsItemResourceTask.Method.Put);
				}
			}
			if (!(isOnSale && isStopSale)) {
				if (isStopSale) {
					goodsstatus = GoodsStatus.STOP_SALE;
				}
			}
			StringBuffer sql2 = new StringBuffer(
					"define update modifyGoodsInfo(");
			sql2.append("@RECID guid,@goodsName string,");
			sql2
					.append("@salePrice double,@remark string,@lastModifyDate date,");
			sql2
					.append("@lastModifyPerson string,@setPriceFlag boolean,@goodsstatus string,");
			sql2.append("@shelfLife int");
			sql2.append(",@warningDay int");
			sql2.append(",@isJointVenture boolean");
			sql2.append(",@supplierId guid");
			sql2.append(")");
			sql2.append(" begin");
			sql2.append(" update ").append(Tab_Goods).append("  as a");
			sql2.append(" set goodsName=@goodsName,");
			sql2.append(" salePrice=@salePrice,remark=@remark,");
			sql2.append(" lastModifyDate=@lastModifyDate,");
			// sql2.append(" lastModifyPerson=@lastModifyPerson,setPriceFlag=@setPriceFlag,goodsstatus = @goodsstatus");
			sql2
					.append(" lastModifyPerson=@lastModifyPerson,status = @goodsstatus");
			sql2.append(",shelfLife=@shelfLife");
			sql2.append(",warningDay=@warningDay");
			sql2.append(",isJointVenture=@isJointVenture");
			sql2.append(",supplierId=@supplierId");
			sql2.append(" where 1=1");
			sql2.append(" and a.RECID=@RECID");
			sql2.append(" end");

			DBCommand dbc2 = context.prepareStatement(sql2);
			int i = 0;
			dbc2.setArgumentValue(i++, goodsInfo.getId());
			// dbc2.setArgumentValue(i++, goodsInfo.getCode());
			dbc2.setArgumentValue(i++, goodsInfo.getName());
			dbc2.setArgumentValue(i++, goodsInfo.getSalePrice());
			dbc2.setArgumentValue(i++, goodsInfo.getRemark());
			dbc2.setArgumentValue(i++, System.currentTimeMillis());
			dbc2.setArgumentValue(i++, emp.getName());
			dbc2.setArgumentValue(i++, true);
			dbc2.setArgumentValue(i++, goodsstatus.getCode());
			dbc2.setArgumentValue(i++, goodsInfo.getShelfLife());
			dbc2.setArgumentValue(i++, goodsInfo.getWarningDay());
			dbc2.setArgumentValue(i++, goodsInfo.isJointVenture());
			dbc2.setArgumentValue(i++, goodsInfo.getSupplierId());
			dbc2.executeUpdate();
			context.handle(new UpdateGoodsResourceTask(goodsInfo.getId()),
					UpdateGoodsResourceTask.Method.Modify);

			// for (GoodsInfoTask.Item item : goodsInfo.getItems()) {
			// saveGoodsProperty(context, goodsInfo, item);
			// context.handle(new UpdateGoodsItemResourceTask(item.getId()),
			// UpdateGoodsItemResourceTask.Method.Put);
			// }
		}

		// /**
		// * 删除商品的所有条目
		// *
		// * @param context
		// * @param id
		// * void
		// */
		// private void deleteGoodsProperty(Context context, GUID id) {
		// StringBuffer sql = new StringBuffer(
		// "define delete deleteGoodsItem(@goodsId guid)");
		// sql.append(" begin");
		// sql.append(" delete from ");
		// sql.append(Tab_GoodsItem);
		// sql.append(" as a");
		// sql.append(" where 1=1");
		// sql.append(" and a.goodsGuid=@goodsId ");
		// sql.append(" end");
		// DBCommand dbc = context.prepareStatement(sql);
		// dbc.setArgumentValue(0, id);
		// dbc.executeUpdate();
		// ResourceToken<Goods> token =
		// context.findResourceToken(Goods.class,id);
		// for(GoodsItem item : context.getResourceReferences(GoodsItem.class,
		// token)){
		// context.handle(new
		// UpdateGoodsItemResourceTask(item.getId()),UpdateGoodsItemResourceTask.Method.Remove);
		// }
		// }

	}

	/*************************************
	 * 商品条目相关服务
	 ************************************/

	/**
	 * 获得指定商品的交易记录
	 */
	@Publish
	protected final class GetGoodsTraderLogListProvider extends
			TwoKeyResultListProvider<GoodsTraderLogItem, GUID, TraderType> {

		@Override
		protected void provide(Context context, GUID key1, TraderType key2,
				List<GoodsTraderLogItem> resultList) throws Throwable {
			ORMAccessor<GoodsTraderLogOrmEntity> acc = context
					.newORMAccessor(orm_GoodsTraderLogByGoodsId);
			List<GoodsTraderLogOrmEntity> list = acc.fetch(key1, key2.name());
			for (GoodsTraderLogOrmEntity goodsTraderLogOrmEntity : list) {
				GoodsTraderLog log = new GoodsTraderLog(goodsTraderLogOrmEntity);
				GoodsTraderLogItemImpl g = new GoodsTraderLogItemImpl();
				GoodsItem gi = context.find(GoodsItem.class, log
						.getGoodsItemId());
				Partner p = context.find(Partner.class, log.getPartnerId());
				g.setProperty(GoodsProperyUtil.subGoodsPropertyToString(gi
						.getGoodsProperties()));
				g.setUnit(gi.getGoodsUnit());
				g.setPartnerName(p.getShortName());
				g.setStatus(gi.getStatus().getName());
				g.setCount(log.getCount());
				g.setTotalTraderAmount(log.getTotalTraderAmount());
				g.setTotalTraderCount(DoubleUtil.getRoundStr(log
						.getTotalTraderCount(), gi.getScale()));
				g.setRecentCount(DoubleUtil.getRoundStr(log.getRecentCount(),
						gi.getScale()));
				g.setRecentDate(log.getRecentDate());
				g.setRecentPrice(log.getRecentPrice());
				g.setPriceList(log.getPriceList());
				resultList.add(g);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 更新商品条目交易记录
	 * </p>
	 * 
	 */
	@Publish
	protected final class UpdateGoodsTraderLogHandler extends
			SimpleTaskMethodHandler<UpdateGoodsTraderLogTask> {

		@Override
		protected void handle(Context context, UpdateGoodsTraderLogTask task)
				throws Throwable {
			ORMAccessor<GoodsTraderLogOrmEntity> acc = context
					.newORMAccessor(orm_GoodsTraderLogByGoodsItemIdAndPartnerId);
			GoodsTraderLogOrmEntity entity = acc.findByPKey(task
					.getGoodsItemId(), task.getPratnerId());
			GoodsTraderLog log;
			boolean isCreate = false;
			if (entity == null) {
				log = new GoodsTraderLog();
				entity = new GoodsTraderLogOrmEntity();
				entity.setId(context.newRECID());
				entity.setType(task.getType().name());
				GoodsItem goodsItem = context.find(GoodsItem.class, task
						.getGoodsItemId());
				entity.setGoodsId(goodsItem.getGoodsId());
				entity.setGoodsItemId(task.getGoodsItemId());
				entity.setPartnerId(task.getPratnerId());
				isCreate = true;
			} else {
				log = new GoodsTraderLog(entity);
			}
			log.setRecentCount(task.getCount());
			log.setRecentPrice(task.getPrice());
			entity.setData(log.getData());
			if (isCreate) {
				acc.insert(entity);
			} else {
				acc.update(entity);
			}
			// 取消（2012-10-14）
			// if(task.getType()==TraderType.Purchase){ //写入商品最近采购单价
			// UpdateGoodsItemRecentPurchasePriceTask
			// updateGoodsItemRecentPurchasePriceTask = new
			// UpdateGoodsItemRecentPurchasePriceTask(task.getGoodsItemId(),
			// task.getPrice());
			// context.handle(updateGoodsItemRecentPurchasePriceTask);
			// }
		}

	}

	/**
	 * 获得商品条目维护对象
	 */
	@Publish
	protected class GetGoodsItemInfoByIdProvider extends
			OneKeyResultProvider<GoodsItemInfo, GUID> {

		@Override
		protected GoodsItemInfo provide(Context context, GUID guid)
				throws Throwable {
			GoodsItem goodsItem = context.find(GoodsItem.class, guid);
			GoodsItemInfoImpl result = new GoodsItemInfoImpl();
			result.setBaseInfo(context.find(GoodsInfo.class, goodsItem
					.getGoodsId()));
			result.setItemData(GoodsHelper.goodsItemToItemData(goodsItem));
			return result;
		}

		public class GoodsItemInfoImpl extends GoodsItemInfo {

			public void setBaseInfo(GoodsInfo baseInfo) {
				this.baseInfo = baseInfo;
			}

			public void setItemData(GoodsItemData itemData) {
				this.itemData = itemData;
			}

		}

	}

	@Publish
	protected class GetGoodsItemListProvider
			extends
			OneKeyResultListProvider<GoodsItemDetail, GetGoodsItemDetailListKey> {

		@Override
		protected void provide(Context context, GetGoodsItemDetailListKey key,
				List<GoodsItemDetail> resultList) throws Throwable {
			GetGoodsItemListKey iKey = new GetGoodsItemListKey(
					GoodsCategory.ROOT.getId(), key.getOffset(),
					key.getCount(), key.isQueryTotal());

			List<GoodsItem> itemList = context.getList(GoodsItem.class);

			// BeanCopy.copys(itemList, resultList);
			// BeanCopy.copys(resultList, itemList);
			GoodsItemDetail itemDetail = null;
			for (GoodsItem item : itemList) {
				itemDetail = new GoodsItemDetail();
				BeanUtils.copyObject(item, itemDetail);
				resultList.add(itemDetail);
			}
		}

	}

	/**
	 * 修改商品库存上限<BR>
	 * 
	 * 
	 */
	@Publish
	@Deprecated
	// 取消（2012-10-14）
	protected class UpdateGoodsItemUpperLimitHandler extends
			SimpleTaskMethodHandler<UpdateGoodsItemThresholdTask> {

		@Override
		protected void handle(Context context, UpdateGoodsItemThresholdTask task)
				throws Throwable {
			// GoodsItemThresholdItem[] items = task.items;
			// // ORMAccessor<GoodsOrmEntity> goodsAcc =
			// // context.newORMAccessor(orm_Goods);
			// boolean isFirst = true;
			// for (GoodsItemThresholdItem item : items) {
			// if (isFirst) {
			// changeGoodsWarnningType(context, task);
			// }
			// if (item.getInventoryWarningType() ==
			// InventoryWarningType.Store_Amount
			// || InventoryWarningType.Store_Count ==
			// item.getInventoryWarningType()) {
			// // 如果是分仓库预警
			// InventoryLimitTask goodsStoreLimeTask = new
			// InventoryLimitTask(item.getStoreId(), item.getGoodsItemId(), item
			// .getStoreUpper(), item.getStoreFloor(),
			// item.getStoreAmountUpper());
			// context.handle(goodsStoreLimeTask);
			// } else {
			// ORMAccessor<GoodsItemOrmEntity> acc =
			// context.newORMAccessor(orm_GoodsItem);
			// GoodsItemOrmEntity entity =
			// acc.findByRECID(item.getGoodsItemId());
			// //
			// entity.setInventoryAmountUpperLimit(item.getStoreAmountUpper());
			// // entity.setTotalStoreLowerLimit(item.getStoreFloor());
			// // entity.setTotalStoreUpperLimit(item.getStoreUpper());
			// acc.update(entity);
			// context.handle(new UpdateGoodsItemResourceTask(entity.getId()),
			// UpdateGoodsItemResourceTask.Method.Modify);
			// }
			// context.dispatch(new
			// GoodsItemThresholdChangeEvent(item.getGoodsItemId()));
			// }
		}

		private void changeGoodsWarnningType(Context context,
				UpdateGoodsItemThresholdTask task) {
			GoodsItemThresholdItem item = task.items[0];
			GoodsItem goodsItem = context.find(GoodsItem.class, item
					.getGoodsItemId());
			ORMAccessor<GoodsOrmEntity> acc = context.newORMAccessor(orm_Goods);
			GoodsOrmEntity entity = acc.findByRECID(goodsItem.getGoodsId());
			acc.update(entity);
			context.handle(new UpdateGoodsResourceTask(goodsItem.getGoodsId()),
					UpdateGoodsResourceTask.Method.Modify);
		}
	}

	/**
	 * 
	 * <p>
	 * 商品条目选择界面查询商品条目列表
	 * </p>
	 * 
	 */
	@Publish
	protected final class GetGoodsInfoListProvider extends
			OneKeyResultProvider<ListEntity<GoodsInfo>, GetGoodsInfoListKey> {

		private class GFilter implements Filter<GoodsItem> {

			private String searchkey;

			private GetGoodsInfoListKey key;

			public GFilter(GetGoodsInfoListKey key) {
				this.searchkey = key.getSearchText() == null ? "" : key
						.getSearchText();
				this.key = key;
			}

			public boolean accept(GoodsItem item) {
				if (key.isQueryAll()) {
					if (item.getGoodsName().indexOf(searchkey) > -1
							|| item.getGoodsCode().indexOf(searchkey) > -1) {
						return true;
					}
					return false;
				}
				if (key.isJointVenture()) {
					if (!(GoodsStatus.ON_SALE == item.getStatus() && item
							.getGoods().isJointVenture())) {
						return false;
					}
					if (item.getGoodsName().indexOf(searchkey) > -1
							|| item.getGoodsCode().indexOf(searchkey) > -1) {
						return true;
					}
					return false;
				}
				if (key.getStatus() != GoodsStatus.PART_SALE) {
					if (!item.getStatus().equals(key.getStatus())
							|| item.getGoods().isJointVenture())
						return false;
				}
				if (key.isNopriceOnly()) {
					if (item.getSalePrice() != 0)
						return false;
				}
				if (key.isSetPriceOnley()) {
					if (item.getSalePrice() < 0)
						return false;
				}
				return item.getGoodsName().indexOf(searchkey) > -1
						|| item.getGoodsCode().indexOf(searchkey) > -1;
			}

		}

		@Override
		protected ListEntity<GoodsInfo> provide(final Context context,
				final GetGoodsInfoListKey key) throws Throwable {
			LinkedHashMap<GUID, GoodsInfoImpl> goodsMap = new LinkedHashMap<GUID, GoodsInfoImpl>();
			List<GoodsItem> items = new ArrayList<GoodsItem>();
			if (key.getCateoryId() != null) {
				List<GoodsCategory> list = context.getList(GoodsCategory.class,
						new GetGoodsCategoryLeafNodesKey(key.getCateoryId()));
				for (GoodsCategory goodsCategory : list) {
					ResourceToken<GoodsCategory> category = context
							.findResourceToken(GoodsCategory.class,
									goodsCategory.getId());
					items.addAll(context.getResourceReferences(GoodsItem.class,
							category, new GFilter(key)));
				}
			} else {
				// ResourceToken<Tenant> token =
				// TenantHelper.getTenantToken(context);
				List<GoodsItem> list = context.getList(GoodsItem.class);
				items.addAll(getFilteredList(list, key));
				// items = context.getResourceReferences(GoodsItem.class, new
				// GFilter(key));
			}
			for (GoodsItem goodsItem : items) {
				if (!goodsMap.containsKey(goodsItem.getGoodsId())) {
					GoodsInfoImpl info = GoodsHelper.goodsToGoodsInfo(context,
							goodsItem.getGoods());
					info.setCategory(GoodsHelper
							.goodsCategoryToGoodsCategoryInfoImpl(context.find(
									GoodsCategory.class, goodsItem
											.getCategoryId())));
					goodsMap.put(goodsItem.getGoodsId(), info);
				}
				goodsMap.get(goodsItem.getGoodsId()).addItem(
						GoodsHelper.goodsItemToItemData(goodsItem));
			}
			List<GoodsInfo> result = new ArrayList<GoodsInfo>();
			int i = 0;
			for (GoodsInfo goods : goodsMap.values()) {
				// if(goods.gets)
				if (i >= key.getOffset()
						&& i < (key.getOffset() + key.getCount())) {
					result.add(goods);
				}
				// if(i==key.getOffset()+key.getCount())break;
				i++;
			}

			if (key.getSortField() != null
					&& !key.getSortField().equals(SortField.None)) {
				String sortField = key.getSortField().getFieldName();
				boolean isSortByDesc = key.getSortType().equals(SortType.Desc) ? true
						: false;
				ComparatorUtils.sort(result, sortField, isSortByDesc);
			}

			return new ListEntity<GoodsInfo>(result, goodsMap.size());
		}

		private Collection<? extends GoodsItem> getFilteredList(
				List<GoodsItem> list, GetGoodsInfoListKey key) {
			List<GoodsItem> newList = new ArrayList<GoodsItem>();
			String searchkey = key.getSearchText() == null ? "" : key
					.getSearchText();

			for (GoodsItem item : list) {
				if (key.isQueryAll()) {
					if (item.getGoodsName().indexOf(searchkey) > -1
							|| item.getGoodsCode().indexOf(searchkey) > -1) {
						newList.add(item);
					}
					continue;
				}
				if (key.isJointVenture()) {
					if (!(GoodsStatus.ON_SALE == item.getStatus() && item
							.getGoods().isJointVenture())) {
						continue;
					}
					if (item.getGoodsName().indexOf(searchkey) > -1
							|| item.getGoodsCode().indexOf(searchkey) > -1) {
						newList.add(item);
					}
					continue;
				}
				if (((key.getStatus() != GoodsStatus.PART_SALE && !item
						.getStatus().equals(key.getStatus()))
						|| (key.isNopriceOnly() && !item.getStatus().equals(
								key.getStatus())) || (key.isSetPriceOnley() && item
						.getSalePrice() < 0))
						|| item.getGoods().isJointVenture()) {
					continue;
				}
				if (item.getGoodsName().indexOf(searchkey) > -1
						|| item.getGoodsCode().indexOf(searchkey) > -1) {
					newList.add(item);
				}
			}

			return newList;
		}

	}

	/**
	 * 
	 * <p>
	 * 标识商品已使用过了
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-22
	 */
	@Publish
	protected final class MarkGoodsItemUsedHandler extends
			SimpleTaskMethodHandler<MarkGoodsItemUsedTask> {

		@Override
		protected void handle(Context context, MarkGoodsItemUsedTask task)
				throws Throwable {
			GoodsItem goodsItem = context.find(GoodsItem.class, task.getId());
			if (goodsItem.isRefFlag())
				return;
			ORMAccessor<GoodsItemOrmEntity> acc = context
					.newORMAccessor(orm_GoodsItem);
			GoodsItemOrmEntity entity = acc.findByRECID(task.getId());
			entity.setRefFlag(true);
			acc.update(entity);
			context.handle(new UpdateGoodsItemResourceTask(entity.getId()),
					UpdateGoodsItemResourceTask.Method.Modify);
			ORMAccessor<GoodsOrmEntity> goodsAcc = context
					.newORMAccessor(orm_Goods);
			GoodsOrmEntity goodsEntity = goodsAcc.findByRECID(entity
					.getGoodsId());
			goodsEntity.setRefFlag(true);
			goodsAcc.update(goodsEntity);
			context.handle(new UpdateGoodsResourceTask(goodsEntity.getId()),
					UpdateGoodsResourceTask.Method.Modify);
		}

	}

	/**
	 * 
	 * <p>
	 * 批量添加商品
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-7-11
	 */
	@Publish
	protected final class BatchSaveGoodsHandler
			extends
			TaskMethodHandler<BatchSaveForExcelTask, BatchSaveForExcelTask.Model> {

		protected BatchSaveGoodsHandler() {
			super(BatchSaveForExcelTask.Model.Goods);
		}

		@Override
		protected void handle(Context context, BatchSaveForExcelTask task)
				throws Throwable {
			ExcelReader reader = new ExcelReader(task.getExcel());
			// TODO 此处添加批量添加商品的代码

		}

	}

	/**
	 * 查询所有商品数量
	 */
	@Publish
	protected final class FindGoodsCountProvider extends
			OneKeyResultProvider<Integer, FindGoodsCountKey> {

		/**
		 * 查询商品数量的SQL
		 */
		private String getGoodsCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_GoodsCount(@tenantGuid guid) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from sa_goods_info as t ");
			buffer.append(" where t.tenantsGuid=@tenantGuid");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindGoodsCountKey key)
				throws Throwable {
			DBCommand dbCommand = context.prepareStatement(getGoodsCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class)
						.getTenantId());
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	/**
	 * 查询所有商品分类数量
	 */
	@Publish
	protected final class FindGoodsTypeCountProvider extends
			OneKeyResultProvider<Integer, FindGoodsTypeCountKey> {

		/**
		 * 查询商品分类数量的SQL
		 */
		private String getGoodsTypeCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_GoodsTypeCount(@tenantGuid guid) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from sa_goods_type as t ");
			buffer.append(" where t.tenantsGuid=@tenantGuid");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindGoodsTypeCountKey key)
				throws Throwable {
			DBCommand dbCommand = context
					.prepareStatement(getGoodsTypeCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class)
						.getTenantId());
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	/**
	 * 查询已设置属性商品分类的数量
	 */
	@Publish
	protected final class FindGoodsTypeHadSetPropertiesCountProvider
			extends
			OneKeyResultProvider<Integer, FindGoodsTypeHadSetPropertiesCountKey> {

		/**
		 * 查询已设置属性商品分类的数量的SQL
		 */
		private String getGoodsTypeHadSetPropertiesCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer
					.append("define query Qu_GoodsTypeCount(@tenantGuid guid, @setPropertyFlag boolean) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from sa_goods_type as t ");
			buffer
					.append(" where t.tenantsGuid=@tenantGuid and t.setPropertyFlag=@setPropertyFlag");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context,
				FindGoodsTypeHadSetPropertiesCountKey key) throws Throwable {
			DBCommand dbCommand = context
					.prepareStatement(getGoodsTypeHadSetPropertiesCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class)
						.getTenantId());
				dbCommand.setArgumentValue(1, Boolean.TRUE);
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

}
