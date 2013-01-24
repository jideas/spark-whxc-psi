package com.spark.psi.base.material.internal.service;

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
import com.spark.b2c.publish.JointVenture.task.JointVentureLogTask;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.ComparatorUtils;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.Employee;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Materials;
import com.spark.psi.base.MaterialsCategory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.event.MaterialsItemThresholdChangeEvent;
import com.spark.psi.base.event.MaterialsStatusChangeEvent;
import com.spark.psi.base.internal.entity.MaterialsTraderLog;
import com.spark.psi.base.internal.entity.helper.MaterialsHelper;
import com.spark.psi.base.internal.entity.helper.LevelTreeFilter;
import com.spark.psi.base.internal.entity.helper.TenantHelper;
import com.spark.psi.base.internal.entity.ormentity.MaterialsItemOrmEntity;
import com.spark.psi.base.internal.entity.ormentity.MaterialsOrmEntity;
import com.spark.psi.base.internal.entity.ormentity.MaterialsTraderLogOrmEntity;
import com.spark.psi.base.internal.service.SqlUtil;
import com.spark.psi.base.internal.service.orm.Orm_Materials;
import com.spark.psi.base.internal.service.orm.Orm_MaterialsItem;
import com.spark.psi.base.internal.service.orm.Orm_MaterialsItemByMarerialId;
import com.spark.psi.base.internal.service.orm.Orm_MaterialsTraderLogByMaterialsId;
import com.spark.psi.base.internal.service.orm.Orm_MaterialsTraderLogByMaterialsItemIdAndPartnerId;
import com.spark.psi.base.key.materials.GetChildrenMaterialsCategoryListKey;
import com.spark.psi.base.key.materials.GetMaterialsCategoryLeafNodesKey;
import com.spark.psi.base.publicimpl.MaterialsCategoryInfoImpl;
import com.spark.psi.base.publicimpl.MaterialsCategoryTreeImpl;
import com.spark.psi.base.publicimpl.MaterialsInfoImpl;
import com.spark.psi.base.publicimpl.MaterialsInfoItemImpl;
import com.spark.psi.base.publicimpl.MaterialsItemDataImpl;
import com.spark.psi.base.publicimpl.MaterialsPropertyDefineImpl;
import com.spark.psi.base.publicimpl.MaterialsTraderLogItemImpl;
import com.spark.psi.base.task.Materials.MaterialsStoreLimeTask;
import com.spark.psi.base.task.Materials.UpdateMaterialsTraderLogTask;
import com.spark.psi.base.task.goods.InventoryLimitTask;
import com.spark.psi.base.task.resource.UpdateMaterialsCategoryResourceTask;
import com.spark.psi.base.task.resource.UpdateMaterialsItemResourceTask;
import com.spark.psi.base.task.resource.UpdateMaterialsResourceTask;
import com.spark.psi.base.utils.ExcelReader;
import com.spark.psi.base.utils.MaterialsProperyUtil;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.PropertyInputType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree;
import com.spark.psi.publish.base.materials.entity.MaterialsInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsInfoItem;
import com.spark.psi.publish.base.materials.entity.MaterialsItemData;
import com.spark.psi.publish.base.materials.entity.MaterialsItemInfo;
import com.spark.psi.publish.base.materials.entity.MaterialsPropertyDefine;
import com.spark.psi.publish.base.materials.entity.MaterialsTraderLogItem;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree.CategoryNode;
import com.spark.psi.publish.base.materials.entity.MaterialsTraderLogItem.TraderType;
import com.spark.psi.publish.base.materials.key.FindMaterialsCountKey;
import com.spark.psi.publish.base.materials.key.FindMaterialsTypeCountKey;
import com.spark.psi.publish.base.materials.key.FindMaterialsTypeHadSetPropertiesCountKey;
import com.spark.psi.publish.base.materials.key.GetMaterialsInfoByCodeKey;
import com.spark.psi.publish.base.materials.key.GetMaterialsItemByNoKey;
import com.spark.psi.publish.base.materials.key.GetMaterialsInfoItemListKey;
import com.spark.psi.publish.base.materials.key.GetMaterialsInfoListKey;
import com.spark.psi.publish.base.materials.key.GetMaterialsInfoListKey.SortField;
import com.spark.psi.publish.base.materials.task.ChangeMaterialsCategoryNameTask;
import com.spark.psi.publish.base.materials.task.ChangeMaterialsStatusTask;
import com.spark.psi.publish.base.materials.task.CreateMaterialsCategoryTask;
import com.spark.psi.publish.base.materials.task.DeleteMaterialsCategoryTask;
import com.spark.psi.publish.base.materials.task.DeleteMaterialsTask;
import com.spark.psi.publish.base.materials.task.MarkMaterialsItemUsedTask;
import com.spark.psi.publish.base.materials.task.MaterialsInfoTask;
import com.spark.psi.publish.base.materials.task.UpdateMaterialsCategoryTask;
import com.spark.psi.publish.base.materials.task.UpdateMaterialsItemThresholdTask;
import com.spark.psi.publish.base.materials.task.ValidationMaterialsIsExistTask;
import com.spark.psi.publish.base.materials.task.MaterialsInfoTask.ItemMethod;
import com.spark.psi.publish.base.materials.task.UpdateMaterialsItemThresholdTask.MaterialsItemThresholdItem;
import com.spark.psi.publish.base.materials.task.ValidationMaterialsIsExistTask.ErrType;
import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask;

/**
 * 材料相关 外部接口服务
 * 
 * 
 * 
 */
public class MaterialsPublishService extends Service {

	/**
	 * 材料分类表
	 */
	final static String Tab_MaterialsCategory = ERPTableNames.Base.MaterialsCategory.getTableName();
	/**
	 * 材料表
	 */
	final static String Tab_Materials = ERPTableNames.Base.Materials.getTableName();
	/**
	 * 材料条目表
	 */
	final static String Tab_MaterialsItem = ERPTableNames.Base.MaterialsItem.getTableName();

	final Orm_MaterialsItem orm_MaterialsItem;

	final Orm_MaterialsItemByMarerialId orm_MaterialsItemByMarerialId;

	final Orm_Materials orm_Materials;

	final Orm_MaterialsTraderLogByMaterialsId orm_MaterialsTraderLogByMaterialsId;

	final Orm_MaterialsTraderLogByMaterialsItemIdAndPartnerId orm_MaterialsTraderLogByMaterialsItemIdAndPartnerId;

	static final String lock = "0";

	static final int MAXIMUM = 1 << 10;

	protected MaterialsPublishService(final Orm_MaterialsItem orm_MaterialsItem, final Orm_Materials orm_Materials,
			Orm_MaterialsItemByMarerialId orm_MaterialsItemByMarerialId,
			final Orm_MaterialsTraderLogByMaterialsId orm_MaterialsTraderLogByMaterialsId,
			final Orm_MaterialsTraderLogByMaterialsItemIdAndPartnerId orm_MaterialsTraderLogByMaterialsIdAndPartnerId) {
		super("com.spark.psi.base.internal.service.MaterialsPublishService");
		this.orm_MaterialsItem = orm_MaterialsItem;
		this.orm_MaterialsItemByMarerialId = orm_MaterialsItemByMarerialId;
		this.orm_Materials = orm_Materials;
		this.orm_MaterialsTraderLogByMaterialsId = orm_MaterialsTraderLogByMaterialsId;
		this.orm_MaterialsTraderLogByMaterialsItemIdAndPartnerId = orm_MaterialsTraderLogByMaterialsIdAndPartnerId;
	}

	/*************************************
	 * 材料分类相关服务
	 ************************************/

	/**
	 * 获得材料分类维护对象
	 */
	@Publish
	protected class GetMaterialsCategoryInfoByIdProvider extends OneKeyResultProvider<MaterialsCategoryInfo, GUID> {

		@Override
		protected MaterialsCategoryInfo provide(Context context, GUID key) throws Throwable {
			MaterialsCategoryInfoImpl impl = new MaterialsCategoryInfoImpl();
			MaterialsCategory MaterialsCategory = context.find(MaterialsCategory.class, key);
			impl.setScale(MaterialsCategory.getScale());
			impl.setId(key);
			impl.setName(MaterialsCategory.getName());
			impl.setCategoryNo(MaterialsCategory.getCategoryNo());
			//
			StringBuffer sql = new StringBuffer("define query getProperties(@id guid)");
			sql.append(" begin");
			sql.append(" select t.properties as properties ");
			sql.append(" from " + Tab_MaterialsCategory + " as t");
			sql.append(" where t.RECID=@id");
			sql.append(" end");
			DBCommand dbc = context.prepareStatement(sql);
			dbc.setArgumentValue(0, key);
			RecordSet rs = dbc.executeQuery();

			List<MaterialsPropertyDefine> propertyDefineList = new ArrayList<MaterialsPropertyDefine>();
			;
			if (rs.next()) {
				String properties = rs.getFields().get(0).getString();
				try {
					JSONArray array = new JSONArray(properties);
					for (int i = 0; i < array.length(); i++) {
						JSONObject property = array.getJSONObject(i);
						String name = property.getString("name");
						PropertyInputType type = PropertyInputType.getPropertyInputType(property.getString("type"));
						JSONArray optionArray = property.getJSONArray("options");
						String[] options = new String[optionArray.length()];
						for (int j = 0; j < optionArray.length(); j++) {
							options[j] = optionArray.getString(j);
						}
						MaterialsPropertyDefineImpl propertyDefine = new MaterialsPropertyDefineImpl(GUID.randomID(), name, type, options);
						propertyDefineList.add(propertyDefine);
					}
				} catch (Throwable t) {
				}
			}

			//
			impl.setPropertyDefines(propertyDefineList.toArray(new MaterialsPropertyDefine[0]));

			return impl;
		}

	}

	/**
	 * 查询当前租户的材料分类树
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetMaterialsCategoryTreeProvider extends ResultProvider<MaterialsCategoryTree> {

		@Override
		protected MaterialsCategoryTree provide(Context context) throws Throwable {

			List<MaterialsCategory> categorys = context.getList(MaterialsCategory.class);
			ComparatorUtils.sort(categorys, "categoryNo", false);
			List<MaterialsCategoryTree.CategoryNode> result = new ArrayList<MaterialsCategoryTree.CategoryNode>();
			int i = 0; // 设置了属性的材料分类总数
			for (MaterialsCategory MaterialsCategory : categorys) {
				if (MaterialsCategory.ROOT.getId().equals(MaterialsCategory.getParent())) {
					result.add(toNode(context, MaterialsCategory));
				}
				if (MaterialsCategory.isPropertyFlag()) {
					i++;
				}
			}
			MaterialsCategoryTreeImpl tree = new MaterialsCategoryTreeImpl(result.toArray(new MaterialsCategoryTree.CategoryNode[result.size()]));
			tree.setCount(categorys.size());
			tree.setPropertiedCount(i);
			return tree;
		}

		private MaterialsCategoryTree.CategoryNode toNode(Context context, MaterialsCategory impl) {
			CategoryNodeImpl node = new CategoryNodeImpl();
			node.setId(impl.getId());
			node.setName(impl.getName());
			node.setCategoryNo(impl.getCategoryNo());
			node.setPropertyFlag(impl.isPropertyFlag());
			List<MaterialsCategory> childList = context.getList(MaterialsCategory.class, new GetChildrenMaterialsCategoryListKey(impl.getId()));
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
	 * 新增一个材料分类
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class CreateMaterialsCategoryHandler extends SimpleTaskMethodHandler<CreateMaterialsCategoryTask> {

		@Override
		protected void handle(Context context, CreateMaterialsCategoryTask task) throws Throwable {
			// Tenant tr = TenantHelper.getTenant(context);
			task.setId(context.newRECID());
			GUID parentId = task.getParentId() == null ? MaterialsCategory.ROOT.getId() : task.getParentId();
			byte[] path = null;
			if (task.getParentId() == null) {
				path = ("00" + task.getId()).getBytes();
			} else {
				MaterialsCategory parentType = context.find(MaterialsCategory.class, task.getParentId());
				path = (new String(parentType.getPath()) + "00" + task.getId()).getBytes();
			}
			// MaterialsType.setPath(path);

			// 1、将本节点添加到其父节点下

			StringBuffer sql = new StringBuffer(
					"define insert addChildMaterialsType(@recid guid,"
							+ "@MaterialsTypeName string, @categoryNo string, @parentGuid guid,@path bytes,@createDate date,@createPerson string,@creatorId guid)");

			sql.append(" begin");
			sql.append(" insert into ").append(Tab_MaterialsCategory).append(" ");
			sql.append(" (RECID,categoryName,categoryNo,parentId,path,createDate,creator,creatorId)");
			sql.append(" values(@recid,@MaterialsTypeName, @categoryNo, @parentGuid,@path,@createDate,@createPerson,@creatorId)");
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
				throw new IllegalArgumentException("增加材料分类失败，分类名称是否冲突？");
			}
			// 2、将父节点的是否是叶子节点改为非叶子节点
			StringBuffer sql1 = new StringBuffer("define update modifyParentMaterialsType(@leafFlag boolean,@parentGuid guid)");
			sql1.append(" begin");
			sql1.append(" update ").append(Tab_MaterialsCategory).append(" as a");
			sql1.append(" set leafFlag=@leafFlag");
			sql1.append(" where a.RECID=@parentGuid");
			sql1.append(" end");

			DBCommand dbc1 = context.prepareStatement(sql1);

			dbc1.setArgumentValue(0, false);
			dbc1.setArgumentValue(1, task.getParentId());

			dbc1.executeUpdate();
			if (task.getParentId() != null) {
				context.handle(new UpdateMaterialsCategoryResourceTask(task.getParentId()), UpdateMaterialsCategoryResourceTask.Method.Modify);
			}
			//
			// StringBuffer sql2 = new StringBuffer(
			// "define insert addMaterialsTypeTongJi(@RECID guid,"
			// +
			// "@MaterialsTypeGuid guid,@parentGuid guid,@storeAmountUpper double)");
			// sql2.append(" begin");
			// sql2.append(" insert into SA_Materials_TYPE_TONGJI(RECID,tenantsGuid,MaterialsTypeGuid,parentGuid,storeAmountUpper)");
			// sql2.append(" values(@RECID,@tenantsGuid,@MaterialsTypeGuid,@parentGuid,@storeAmountUpper)");
			// sql2.append(" end");
			// dbc1 = context.prepareStatement(sql2);
			// dbc1.setArgumentValue(0, context.newRECID());
			// dbc1.setArgumentValue(1, task.getId());
			// dbc1.setArgumentValue(2, task.getParentId());
			// dbc1.setArgumentValue(3, 0);
			// dbc1.executeUpdate();
			context.handle(new UpdateMaterialsCategoryResourceTask(task.getId()), UpdateMaterialsCategoryResourceTask.Method.Put);

			//
			task.setId(task.getId());
		}

	}

	/**
	 * 修改材料分类信息
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class UpdateMaterialsCategoryHandler extends SimpleTaskMethodHandler<UpdateMaterialsCategoryTask> {

		@Override
		protected void handle(Context context, UpdateMaterialsCategoryTask task) throws Throwable {
			// 序列化属性列表
			MaterialsPropertyDefine[] propertyDefines = task.getPropertyDefines();
			String properties = null;
			if (propertyDefines != null && propertyDefines.length > 0) {
				JSONArray propertyArray = new JSONArray();
				if (propertyDefines != null) {
					for (MaterialsPropertyDefine propertyDefine : propertyDefines) {
						if (!StringHelper.isEmpty(propertyDefine.getName())) {
							String[] options = propertyDefine.getOptions();
							JSONObject propertyObj = new JSONObject();
							propertyObj.put("name", propertyDefine.getName());
							propertyObj.put("type", propertyDefine.getValueInputMode().getCode());
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
			modifyMaterialsCategory(context, task.getId(), task.getName(), task.getScale(), task.getCategoryNo(), properties); // 修改材料分类的基础信息
			//
			context.handle(new UpdateMaterialsCategoryResourceTask(task.getId()), UpdateMaterialsCategoryResourceTask.Method.Modify);

			// deleteProperty(context, task.getId());// 清空所有属性及属性项目
			// saveProperty(context, task); // 重新保存属性项目
		}

		// private void saveProperty(Context context,
		// UpdateMaterialsCategoryTask
		// task) {
		// StringBuffer sql = new StringBuffer(
		// "define insert addMaterialsTypeProerty(@recid guid,@MaterialsTypeGuid guid,"
		// +
		// "@propertyName string,@propertyInputType string,@propertyOrder int)");
		// sql.append(" begin");
		// sql.append(" insert into sa_Materials_type_property");
		// sql.append(" (RECID,MaterialsTypeGuid,propertyName,propertyInputType,propertyOrder)");
		// sql.append(" values(@recid,@MaterialsTypeGuid,@propertyName,@propertyInputType,@propertyOrder)");
		// sql.append(" end");
		//
		// DBCommand dbc = context.prepareStatement(sql);
		// StringBuffer optionSql = new StringBuffer(
		// "define insert addMaterialsTypeProject(@recid guid,@propertyGuid guid,"
		// + "@projectValue string,@projectKey string,@projectOrder int)");
		// optionSql.append(" begin");
		// optionSql.append(" insert into sa_Materials_type_project");
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

		private void modifyMaterialsCategory(Context context, GUID categoryId, String categoryName, int scale, String categoryNo, String properties) {
			MaterialsCategory category = context.find(MaterialsCategory.class, categoryId);
			StringBuffer sql = new StringBuffer(
					"define update modifyMaterialsTypeName(@recid guid,@categoryName string,@categoryNo string, @scale int,@setPropertyFlag boolean, @properties string)");
			sql.append(" begin");
			sql.append(" update ").append(Tab_MaterialsCategory).append("  as a");
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
				throw new IllegalArgumentException("编辑材料分类失败，分类名称是否冲突？");
			}
			// StringBuffer sql1 = new StringBuffer(
			// "define update modifyMaterialsTypeTongJi(@MaterialsTypeGuid guid,@categoryNo string)");
			// sql1.append(" begin");
			// sql1.append(" update SA_Materials_TYPE_TONGJI as a");
			// sql1.append(" set categoryNo=@categoryNo");
			// sql1.append(" where 1=1");
			// sql1.append(" and a.MaterialsTypeGuid=@MaterialsTypeGuid");
			// sql1.append(" end");
			//
			// dbc = context.prepareStatement(sql1);
			// dbc.setArgumentValue(0, categoryId);
			// dbc.setArgumentValue(1, categoryNo);
			// dbc.executeUpdate();

		}

		// /**
		// * <p>
		// * 删除某一个材料分类属性下的所有属性项目
		// * </p>
		// *
		// */
		// protected void deleteProperty(Context context, GUID categoryId)
		// throws Throwable {
		// // 删除该属性下的所有属性项目
		//
		// GUID[] propertys = getMaterialsCategoryPropertyList(context,
		// categoryId);
		// for (GUID guid : propertys) { // 删除所有分类属性的项目值
		// deleteOptions(context, guid);
		// }
		// StringBuffer sql1 = new StringBuffer(
		// "define delete deleteMaterialsTypeProperty(@MaterialsTypeGuid guid)");
		// sql1.append(" begin");
		// sql1.append(" delete from sa_Materials_type_property as a");
		// sql1.append(" where a.MaterialsTypeGuid=@MaterialsTypeGuid");
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
		// "define delete deleteMaterialsTypeProjectByRecid(@propertyGuid guid)");
		// sql1.append(" begin");
		// sql1.append(" delete from sa_Materials_type_project as a");
		// sql1.append(" where a.propertyGuid =@propertyGuid");
		// sql1.append(" end");
		// DBCommand dbc1 = context.prepareStatement(sql1);
		// dbc1.setArgumentValue(0, PropertyId);
		// dbc1.executeUpdate();
		// }

		// protected GUID[] getMaterialsCategoryPropertyList(Context context,
		// GUID MaterialsTypeGuid) throws Throwable {
		// StringBuffer sql = new StringBuffer(
		// "define query getOnePropertyProject(@MaterialsTypeGuid guid)");
		// sql.append(" begin");
		// sql.append(" select a.recid as recid ");
		// sql.append(" from SA_Materials_TYPE_PROPERTY as a");
		// sql.append(" where a.recid=@MaterialsTypeGuid");
		// sql.append(" end");
		//
		// DBCommand dbc = context.prepareStatement(sql);
		// dbc.setArgumentValue(0, MaterialsTypeGuid);
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
	 * 修改材料分类名称
	 * </p>
	 * 
	 */
	@Publish
	protected final class ChangeMaterialsCategoryNameHandler extends SimpleTaskMethodHandler<ChangeMaterialsCategoryNameTask> {

		@Override
		protected void handle(Context context, ChangeMaterialsCategoryNameTask task) throws Throwable {
			StringBuffer sql = new StringBuffer("define update modifyMaterialsCategoryName(").append("@id guid,@name string) ").append(" begin")
					.append("    update ").append(Tab_MaterialsCategory).append("  as a").append("    set categoryName=@name")
					.append("    where 1=1").append("    and a.recid=@id").append(" end");

			DBCommand dbc = context.prepareStatement(sql);
			dbc.setArgumentValue(0, task.getCategoryId());
			dbc.setArgumentValue(1, task.getName());
			dbc.executeUpdate();
			context.handle(new UpdateMaterialsCategoryResourceTask(task.getCategoryId()), UpdateMaterialsCategoryResourceTask.Method.Modify);
		}
	}

	@Publish
	protected final class DeleteMaterialsCategoryHandler extends SimpleTaskMethodHandler<DeleteMaterialsCategoryTask> {

		@Override
		protected void handle(Context context, DeleteMaterialsCategoryTask task) throws Throwable {

			// StringBuffer sql =
			// new StringBuffer(
			// "define delete modifyMaterialsPropertystatus(@id guid)");
			// sql.append(" begin");
			// sql.append(" delete from SA_Materials_TYPE as a");
			// sql.append(" where a.RECID=@id ");
			// sql.append(" end");
			//
			// DBCommand dbc = context.prepareStatement(sql);
			// dbc.setArgumentValue(0, task.getId());
			// dbc.executeUpdate(); // 删除材料条目
			// MaterialsCategory category =
			// context.find(MaterialsCategory.class,task.getId());
			// if(category.is)
			MaterialsCategory category = context.find(MaterialsCategory.class, task.getId());
			// ResourceToken<Tenant> token =
			// TenantHelper.getTenantToken(context, category.getTenantId());
			// List<MaterialsCategory> list =
			// context.getResourceReferences(MaterialsCategory.class, new
			// LevelTreeFilter<MaterialsCategory>(category
			// .getPath()));
			List<MaterialsCategory> sList = context.getList(MaterialsCategory.class);
			List<MaterialsCategory> list = new ArrayList<MaterialsCategory>();
			LevelTreeFilter ltf = new LevelTreeFilter(category.getPath());
			for (MaterialsCategory c : sList) {
				if (ltf.accept(c)) {
					list.add(c);
				}
			}
			for (MaterialsCategory MaterialsCategory : list) {
				int result = SqlUtil.DeleteById(context, Tab_MaterialsCategory, MaterialsCategory.getId());
				if (result > 0)
					context.handle(new UpdateMaterialsCategoryResourceTask(MaterialsCategory.getId()),
							UpdateMaterialsCategoryResourceTask.Method.Remove);
			}
			int result = SqlUtil.DeleteById(context, Tab_MaterialsCategory, task.getId());
			if (result > 0)
				context.handle(new UpdateMaterialsCategoryResourceTask(task.getId()), UpdateMaterialsCategoryResourceTask.Method.Remove);
		}
	}

	/*************************************
	 * 材料相关服务
	 ************************************/

	/**
	 * 验证材料名称和编号是否重复
	 */
	@Publish
	protected final class ValidationMaterialsNameIsExistProvider extends SimpleTaskMethodHandler<ValidationMaterialsIsExistTask> {

		@Override
		protected void handle(Context context, ValidationMaterialsIsExistTask task) throws Throwable {
			// ResourceToken<Tenant> token =
			// TenantHelper.getTenantToken(context);
			List<Materials> list = context.getList(Materials.class);
			if (!StringUtils.isEmpty(task.getName())) {
				for (Materials Materials : list) { // 校验材料名称在对应材料分类中是否已经存在
					if (Materials.getId().equals(task.getId()))
						continue;
					if (Materials.getMaterialName().equals(task.getName())) {
						task.setErrType(ErrType.Name);
						task.setExist(true);
						break;
					}
				}
			}
			if (StringHelper.isEmpty(task.getCode()))
				return;

			List<MaterialsItem> itemList = context.getList(MaterialsItem.class);
			for (MaterialsItem item : itemList) {
				if (null != task.getMaterialItemId() && item.getId().equals(task.getMaterialItemId())) {
					continue;
				}
				if (item.getMaterialNo() == null || item.getSpec() == null) {
					continue;
				}
				if (!item.getMaterialNo().equals(task.getCode()) || !item.getSpec().equals(task.getSpec())) {
					continue;
				}
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
	protected class GetMaterialsInfoItemListProvider extends OneKeyResultProvider<ListEntity<MaterialsInfoItem>, GetMaterialsInfoItemListKey> {

		@Override
		protected ListEntity<MaterialsInfoItem> provide(Context context, GetMaterialsInfoItemListKey key) throws Throwable {
			StringBuffer sql = new StringBuffer("define query getMaterialsInfoItem(");
			sql.append("@tenantsGuid guid");
			sql.append(",@Materialsstatus string");
			if (key.getCategoryId() != null) {
				sql.append(",@category guid");
			}
			if (!StringUtils.isEmpty(key.getSearchText())) {
				sql.append(",@searchText string");
			}
			sql.append(")");
			sql.append(" begin").append(" select ").append(" a.RECID as recid,").append(" a.MaterialsNo as MaterialsNo,").append(
					" a.MaterialsName as MaterialsName,").append(" a.salePrice as salePrice,").append(" a.refflag as refflag ").append(" from ")
					.append(Tab_Materials).append("  AS a").append(" where a.tenantsGuid=@tenantsGuid").append(
							" and a.Materialsstatus=@Materialsstatus");
			if (key.getCategoryId() != null) {
				sql.append(" and  a.MaterialsTypeGuid=@category");
			}
			if (!StringUtils.isEmpty(key.getSearchText())) {
				sql.append(" and(a.MaterialsNo like '%' + @searchText +'%' or a.MaterialsName like '%' + @searchText + '%' )");
			}
			if (key.isNopriceOnly()) {
				sql.append(" and a.salePrice = 0");
			}
			sql.append(" order by a.createDate desc").append(" end");
			//
			DBCommand dbc = context.prepareStatement(sql);
			int index = 0;
			dbc.setArgumentValue(index++, context.find(Login.class).getTenantId());
			dbc.setArgumentValue(index++, key.getStatus().getCode());
			if (key.getCategoryId() != null) {
				dbc.setArgumentValue(index++, key.getCategoryId());
			}
			if (!StringUtils.isEmpty(key.getSearchText())) {
				dbc.setArgumentValue(index++, key.getSearchText());
			}
			List<MaterialsInfoItem> dataList = new ArrayList<MaterialsInfoItem>();
			int count = 0;
			RecordSet rs = dbc.executeQuery();
			while (rs.next()) {
				count++;
				RecordSetFieldContainer<? extends RecordSetField> fields = rs.getFields();
				MaterialsInfoItemImpl item = new MaterialsInfoItemImpl();
				item.setId(fields.get(0).getGUID());
				item.setCode(fields.get(1).getString());
				item.setName(fields.get(2).getString());
				item.setPrice(fields.get(3).getDouble());
				item.setRef(fields.get(4).getBoolean());
				dataList.add(item);
			}
			return new ListEntity<MaterialsInfoItem>(dataList, count);
		}
	}

	/**
	 * 获得材料维护对象
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class GetMaterialsInfoByIdProvider extends OneKeyResultProvider<MaterialsInfo, GUID> {

		@Override
		protected MaterialsInfo provide(Context context, GUID guid) throws Throwable {
			Materials Materials = context.find(Materials.class, guid);
			if (Materials == null)
				return null;
			MaterialsInfoImpl entity = MaterialsHelper.materialsToMaterialsInfo(context, Materials);
			entity.setItems(getMaterialsItem(context, Materials));
			entity.setCategory(context.find(MaterialsCategoryInfo.class, Materials.getCategoryId()));
			return entity;
		}

		private List<MaterialsItemDataImpl> getMaterialsItem(final Context context, Materials Materials) {
			List<MaterialsItem> list = context.getResourceReferences(MaterialsItem.class, context.findResourceToken(Materials.class, Materials
					.getId()));
			List<MaterialsItemDataImpl> result = new ArrayList<MaterialsItemDataImpl>();
			for (MaterialsItem impl : list) {
				if (impl.getMaterialId().equals(Materials.getId())) {
					result.add(MaterialsHelper.materialsItemToItemData(impl));
				}
			}
			return result;
		}

	}

	/**
	 * 根据材料编号获得材料信息
	 * 
	 * 
	 */
	@Publish
	protected class GetMaterialsInfoByCodeProvider extends OneKeyResultProvider<MaterialsInfo, GetMaterialsInfoByCodeKey> {

		@Override
		protected MaterialsInfo provide(Context context, final GetMaterialsInfoByCodeKey key) throws Throwable {
//			ResourceToken<Tenant> token = TenantHelper.getTenantToken(context);
			List<Materials> list = context.getList(Materials.class);
			List<Materials> rlist = new ArrayList<Materials>();
			for(Materials item:list)
			{
				if(item.getMaterialCode().equals(key.getMaterialsCode()))
				{
					rlist.add(item);
				}
			}
			if (rlist.size() < 1)
				return null;
			MaterialsInfoImpl Materials = MaterialsHelper.materialsToMaterialsInfo(context, rlist.get(0));
			Materials.setItems(getMaterialsItem(context, rlist.get(0)));
			Materials.setCategory(context.find(MaterialsCategoryInfo.class, rlist.get(0).getCategoryId()));
			return Materials;
		}

		private List<MaterialsItemDataImpl> getMaterialsItem(final Context context, Materials Materials) {
			List<MaterialsItem> list = context.getResourceReferences(MaterialsItem.class, context.findResourceToken(Materials.class, Materials
					.getId()));
			List<MaterialsItemDataImpl> result = new ArrayList<MaterialsItemDataImpl>();
			for (MaterialsItem impl : list) {
				if (impl.getMaterialId().equals(Materials.getId())) {
					result.add(MaterialsHelper.materialsItemToItemData(impl));
				}
			}
			return result;
		}

	}
	
	

	// protected class GetMaterialsListProvider extends
	// OneKeyResultListProvider<MaterialsItem, TKey>

	/**
	 * 修改材料状态
	 */
	@Publish
	protected class ChanageMaterialsStatusHandler extends SimpleTaskMethodHandler<ChangeMaterialsStatusTask> {

		@Override
		protected void handle(Context context, ChangeMaterialsStatusTask task) throws Throwable {
			MaterialsStatus status = task.isTurnOnOrOff() ? MaterialsStatus.ON_SALE : MaterialsStatus.STOP_SALE;
			StringBuffer sql1 = new StringBuffer("define update modifyBatchMaterialsStatus(@RECID guid,@Materialsstatus string)");
			sql1.append(" begin");
			sql1.append(" update ").append(Tab_Materials).append("  as a");
			sql1.append(" set status=@Materialsstatus");
			sql1.append(" where 1=1");
			sql1.append(" and a.RECID=@RECID");
			sql1.append(" end");

			DBCommand dbc1 = context.prepareStatement(sql1);
			// 首先向属性表中写入数据
			for (GUID guid : task.getIds()) {
				updateMaterialsItemstatus(context, status, guid);
				dbc1.setArgumentValue(0, guid);
				dbc1.setArgumentValue(1, status.getCode());
				dbc1.executeUpdate();
				context.handle(new UpdateMaterialsResourceTask(guid), UpdateMaterialsResourceTask.Method.Modify);
			}

		}

		protected void updateMaterialsItemstatus(Context context, MaterialsStatus status, GUID MaterialsId) throws Throwable {

			StringBuffer sql = new StringBuffer("define update modifyMaterialsPropertystatus(" + "@status string,@MaterialId guid)");
			sql.append(" begin");
			sql.append(" update ");
			sql.append(Tab_MaterialsItem);
			sql.append(" as a");
			sql.append(" set status=@status");
			sql.append(" where 1=1");
			sql.append(" and a.MaterialId=@MaterialId");
			sql.append(" end");

			DBCommand dbc = context.prepareStatement(sql);
			dbc.setArgumentValue(0, status.getCode());
			dbc.setArgumentValue(1, MaterialsId);
			dbc.executeUpdate();
			ResourceToken<Materials> token = context.findResourceToken(Materials.class, MaterialsId);
			for (MaterialsItem item : context.getResourceReferences(MaterialsItem.class, token)) {
				context.handle(new UpdateMaterialsItemResourceTask(item.getId()), UpdateMaterialsItemResourceTask.Method.Modify);
				context.dispatch(new MaterialsStatusChangeEvent(item.getId()));
			}
		}

	}

	/**
	 * 删除材料信息
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class DeleteMaterialsHandler extends SimpleTaskMethodHandler<DeleteMaterialsTask> {

		@Override
		protected void handle(Context context, DeleteMaterialsTask task) throws Throwable {
			// 更新材料的状态
			StringBuffer sql1 = new StringBuffer("define delete deleteBatchMaterialsInfo(@RECID guid)");
			sql1.append(" begin");
			sql1.append(" delete from ").append(Tab_Materials).append("  as a");
			sql1.append(" where 1=1");
			sql1.append(" and a.RECID=@RECID");
			sql1.append(" and a.refFlag=false");
			sql1.append(" end");

			StringBuffer sql = new StringBuffer("define delete modifyMaterialsPropertystatus(@MaterialId guid)");
			sql.append(" begin");
			sql.append(" delete from ");
			sql.append(Tab_MaterialsItem);
			sql.append(" as a");
			sql.append(" where 1=1");
			sql.append(" and a.MaterialId=@MaterialId");
			sql.append(" and a.refFlag=false");
			sql.append(" end");

			DBCommand dbc = context.prepareStatement(sql);

			DBCommand dbc1 = context.prepareStatement(sql1);
			for (GUID guid : task.getIds()) {
				ResourceToken<Materials> token = context.findResourceToken(Materials.class, guid);
				if (token.getFacade().isRefFlag())
					throw new IllegalArgumentException(guid + "材料已经使用过了，不能删除");
				for (MaterialsItem item : context.getResourceReferences(MaterialsItem.class, token)) {
					context.handle(new UpdateMaterialsItemResourceTask(item.getId()), UpdateMaterialsItemResourceTask.Method.Remove);
				}
				dbc.setArgumentValue(0, guid);
				dbc.executeUpdate(); // 删除材料条目
				dbc1.setArgumentValue(0, guid);
				dbc1.executeUpdate(); // 删除材料
				context.handle(new UpdateMaterialsResourceTask(guid), UpdateMaterialsResourceTask.Method.Remove);
			}
		}
	}

	/**
	 * 新增材料基础信息
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class CreateMaterialsInfoHandler extends TaskMethodHandler<MaterialsInfoTask, MaterialsInfoTask.Method> {

		protected CreateMaterialsInfoHandler() {
			super(MaterialsInfoTask.Method.Create);
		}

		@Override
		protected void handle(Context context, MaterialsInfoTask materialsInfo) throws Throwable {
			// Tenant tr = TenantHelper.getTenant(context);
			Employee emp = context.find(Employee.class);
			MaterialsStatus status = MaterialsStatus.ON_SALE;
			boolean isOnSale = false, isStopSale = false;
			if (materialsInfo.isJointVenture() && null == materialsInfo.getSupplierId()) {
				throw new Throwable("联营材料必须设置供应商！");
			}
			if (materialsInfo.getItems() != null) {
				for (MaterialsInfoTask.Item item : materialsInfo.getItems()) {
					if (item.isOnsale()) {
						isOnSale = true;
					} else {
						isStopSale = true;
					}
				}
			}
			if (!(isOnSale && isStopSale)) {
				if (isStopSale) {
					status = MaterialsStatus.STOP_SALE;
				}
			}
			// 然后向材料信息表中写入数据
			// 然后将新数据插入表中
			// StringBuffer sql1 = new StringBuffer(
			// "define insert addMaterialsInfo(@RECID guid,@tenantsGuid guid,@MaterialsNo string,"
			// +
			// "@MaterialsName string,@MaterialsTypeGuid guid,@salePrice double,@remark string,@createDate date,@createPerson string,@Materialsstatus string,@setPriceFlag boolean)");
			// sql1.append(" begin");
			// sql1.append(" insert into ").append(Tab_Materials).append(" ");
			// sql1.append(" (RECID,tenantsGuid,MaterialsNo,MaterialsName,MaterialsTypeGuid,salePrice,remark,createDate,createPerson,Materialsstatus,setPriceFlag)");
			// sql1.append(" values(@RECID,@tenantsGuid,@MaterialsNo,@MaterialsName,@MaterialsTypeGuid,@salePrice,@remark,@createDate,@createPerson,@Materialsstatus,@setPriceFlag)");
			// sql1.append(" end");
			//
			// DBCommand dbc1 = context.prepareStatement(sql1);
			// MaterialsInfo.setId(context.newRECID());
			// dbc1.setArgumentValue(0, MaterialsInfo.getId());
			// dbc1.setArgumentValue(1, tr.getId());
			// dbc1.setArgumentValue(2, MaterialsInfo.getCode());
			// dbc1.setArgumentValue(3, MaterialsInfo.getName());
			// dbc1.setArgumentValue(4, MaterialsInfo.getCategoryId());
			// dbc1.setArgumentValue(5, MaterialsInfo.getSalePrice());
			// dbc1.setArgumentValue(6, MaterialsInfo.getRemark());
			// dbc1.setArgumentValue(7, System.currentTimeMillis());
			// dbc1.setArgumentValue(8, emp.getName());
			// // 向属性表中写入数据
			// dbc1.setArgumentValue(9, status.getCode());
			// dbc1.setArgumentValue(10, true);
			// dbc1.executeUpdate();

			synchronized (lock) {
				materialsInfo.setId(context.newRECID());
				MaterialsOrmEntity entity = new MaterialsOrmEntity();
				entity.setId(materialsInfo.getId());

				MaterialsCategory MaterialsCategory = context.find(MaterialsCategory.class, materialsInfo.getCategoryId());
				String maxCode = MaterialsCategory.getCategoryNo() + "0000";
				MaterialsItem[] gis = MaterialsCategory.getMaterialsItems(context);
				if (gis.length > 0) {
					for (MaterialsItem gi : gis) {
						if (Integer.parseInt(gi.getMaterialCode()) > Integer.parseInt(maxCode)) {
							maxCode = gi.getMaterialCode();
						}
					}
				}
				materialsInfo.setCode(StringHelper.addOneInt(maxCode));
				entity.setMaterialCode(materialsInfo.getCode());
				entity.setMaterialName(materialsInfo.getName());
				entity.setNamePY(PinyinHelper.getLetter(materialsInfo.getName()));
				entity.setCategoryId(materialsInfo.getCategoryId());
				// entity.setSalePrice(materialsInfo.getSalePrice());
				entity.setRemark(materialsInfo.getRemark());
				entity.setCreatorId(emp.getId());
				entity.setCreator(emp.getName());
				entity.setCreateDate(System.currentTimeMillis());
				entity.setStatus(status.getCode());
				entity.setJointVenture(materialsInfo.isJointVenture());
				entity.setSupplierId(materialsInfo.getSupplierId());
				entity.setPercentage(materialsInfo.getPercentage());
				entity.setShelfLife(materialsInfo.getShelfLife());
				entity.setWarningDay(materialsInfo.getWarningDay());
				if (CheckIsNull.isNotEmpty(materialsInfo.getInventoryWarningType()))
					entity.setInventoryWarningType(materialsInfo.getInventoryWarningType().getCode());

				ORMAccessor<MaterialsOrmEntity> orm = context.newORMAccessor(orm_Materials);
				try {
					orm.insert(entity);
					if (entity.isJointVenture()) {
						insertJointLog(context, entity,materialsInfo);
					}
				} finally {
					orm.unuse();
				}

				context.handle(new UpdateMaterialsResourceTask(materialsInfo.getId()), UpdateMaterialsResourceTask.Method.Put);
				if (materialsInfo.getItems() != null) {
					for (MaterialsInfoTask.Item item : materialsInfo.getItems()) {
						saveMaterialsProperty(context, materialsInfo, item);
					}
				}
			}
		}
	}

	private void saveMaterialsProperty(Context context, MaterialsInfoTask materialsInfo, MaterialsInfoTask.Item materialsItem) {
		// Tenant tr = TenantHelper.getTenant(context);
		ORMAccessor<MaterialsItemOrmEntity> acc = context.newORMAccessor(orm_MaterialsItem);
		if (materialsItem.getMethod() == ItemMethod.Delete) {
			acc.delete(materialsItem.getId());
			context.handle(new UpdateMaterialsItemResourceTask(materialsItem.getId()), UpdateMaterialsItemResourceTask.Method.Remove);
		} else {
			MaterialsItemOrmEntity entity;
			MaterialsItem resource = context.find(MaterialsItem.class, materialsItem.getId());
			boolean isMaterialsStatusChange = false;
			if (resource == null) {
				entity = new MaterialsItemOrmEntity();
				entity.setId(materialsItem.getId());
			} else {
				entity = acc.findByRECID(materialsItem.getId());
				isMaterialsStatusChange = !entity.getStatus().equals(
						materialsItem.isOnsale() ? MaterialsStatus.ON_SALE.getCode() : MaterialsStatus.STOP_SALE.getCode());
			}
			// entity.setTenantId(tr.getId());
			entity.setMaterialId(materialsInfo.getId());
			entity.setMaterialName(materialsInfo.getName());
			entity.setNamePY(PinyinHelper.getLetter(materialsInfo.getName()));
			entity.setMaterialCode(materialsInfo.getCode());
			entity.setCategoryId(materialsInfo.getCategoryId());
			entity.setStatus(materialsItem.isOnsale() ? MaterialsStatus.ON_SALE.getCode() : MaterialsStatus.STOP_SALE.getCode());
			entity.setMaterialProperties(MaterialsProperyUtil.subMaterialsPropertyToString(materialsItem.getPropertyValues()));
			entity.setMaterialNo(materialsItem.getMaterialsNo());
			entity.setSalePrice(materialsItem.getSalePrice());
			entity.setLossRate(materialsItem.getLossRate());
			entity.setPlanPrice(materialsItem.getPlanPrice());
			entity.setStandardPrice(materialsItem.getStandardPrice());
			entity.setMaterialUnit(materialsItem.getUnit());
			// entity.setAvgBuyPrice(materialsItem.get)
			// entity.setOriginalPrice(MaterialsItem.getOriginalPrice());
			entity.setSpec(materialsItem.getMaterialsSpec());
			entity.setShelfLife(materialsInfo.getShelfLife());
			entity.setWarningDay(materialsInfo.getWarningDay());
			entity.setInventoryStrategy(materialsItem.getInventoryStrategy());
			if (resource == null) {
				entity.setCreateDate(System.currentTimeMillis());
				Employee emp = context.find(Employee.class, context.find(Login.class).getEmployeeId());
				entity.setCreatorId(emp.getId());
				entity.setCreator(emp.getName());
				entity.setCreateDate(System.currentTimeMillis());
				acc.insert(entity);
				context.handle(new UpdateMaterialsItemResourceTask(entity.getId()), UpdateMaterialsItemResourceTask.Method.Put);
			} else {
				acc.update(entity);
				context.handle(new UpdateMaterialsItemResourceTask(entity.getId()), UpdateMaterialsItemResourceTask.Method.Modify);
				if (isMaterialsStatusChange) {
					context.dispatch(new MaterialsStatusChangeEvent(materialsItem.getId()));
				}
			}
		}
	}

	public void insertJointLog(Context context, MaterialsOrmEntity entity, MaterialsInfoTask materialsInfo) {

		JointVentureLogTask task = new JointVentureLogTask();
		task.setMaterialCode(entity.getMaterialCode());
		task.setMaterialId(entity.getId());
		task.setMaterialName(entity.getMaterialName());
		task.setPercentage(entity.getPercentage());
		task.setSupplierId(entity.getSupplierId());
		task.setMaterialNo(materialsInfo.getItems()[0].getMaterialsNo());
		task.setMaterialUnit(materialsInfo.getItems()[0].getUnit());

		context.handle(task, JointVentureLogTask.Method.Create);
	}

	public void saveJointLog(Context context, MaterialsOrmEntity entity, MaterialsInfoTask materialsInfo) {
		if (materialsInfo.isJointVenture()&&null != materialsInfo.getSupplierId() && materialsInfo.getPercentage() != 0) {
			JointVentureLogTask task = new JointVentureLogTask();
			task.setMaterialCode(entity.getMaterialCode());
			task.setMaterialId(entity.getId());
			task.setMaterialName(entity.getMaterialName());
			task.setPercentage(materialsInfo.getPercentage());
			task.setSupplierId(materialsInfo.getSupplierId());
			task.setMaterialNo(materialsInfo.getItems()[0].getMaterialsNo());
			task.setMaterialUnit(materialsInfo.getItems()[0].getUnit());

			context.handle(task, JointVentureLogTask.Method.Create);
		}

		if (null != entity.getSupplierId() && entity.getPercentage() != 0) {
			JointVentureLogTask u = new JointVentureLogTask();
			u.setMaterialCode(entity.getMaterialCode());
			u.setMaterialId(entity.getId());
			u.setMaterialName(entity.getMaterialName());
			u.setPercentage(entity.getPercentage());
			u.setSupplierId(entity.getSupplierId());

			context.handle(u, JointVentureLogTask.Method.Update);
		}

	}

	/**
	 * 修改材料基础信息
	 * 
	 * 
	 * 
	 */
	@Publish
	protected class UpdateMaterialsInfoHandler extends TaskMethodHandler<MaterialsInfoTask, MaterialsInfoTask.Method> {

		protected UpdateMaterialsInfoHandler() {
			super(MaterialsInfoTask.Method.Update);
		}

		@Override
		protected void handle(Context context, MaterialsInfoTask materialsInfo) throws Throwable {
			Employee emp = context.find(Employee.class);
			boolean isOnSale = false, isStopSale = false;
			MaterialsStatus Materialsstatus = MaterialsStatus.ON_SALE;
			ORMAccessor<MaterialsOrmEntity> orm = context.newORMAccessor(orm_Materials);
			MaterialsOrmEntity materialsOrmEntity = orm.findByRECID(materialsInfo.getId());
			// deleteMaterialsProperty(context, MaterialsInfo.getId()); //
			// XXX：就服务层逻辑而言，不够严谨。应该是对于已ref的条目进行更新，非ref的，可以先删除再增加
			if (materialsInfo.getItems() != null) {
				for (MaterialsInfoTask.Item item : materialsInfo.getItems()) {
					if (item.isOnsale()) {
						isOnSale = true;
					} else {
						isStopSale = true;
					}
					// item.setId(context.newRECID());
					saveMaterialsProperty(context, materialsInfo, item);
					// context.handle(
					// new UpdateMaterialsItemResourceTask(item.getId()),
					// UpdateMaterialsItemResourceTask.Method.Put);
				}
			}
			if (!(isOnSale && isStopSale)) {
				if (isStopSale) {
					Materialsstatus = MaterialsStatus.STOP_SALE;
				}
			}
			StringBuffer sql2 = new StringBuffer("define update modifyMaterialsInfo(");
			sql2.append("@RECID guid,@MaterialsName string,");
			sql2.append("@salePrice double,@remark string,@lastModifyDate date,");
			sql2.append("@lastModifyPerson string,@setPriceFlag boolean,@Materialsstatus string,");
			sql2.append("@shelfLife int");
			sql2.append(",@warningDay int");
			sql2.append(",@isJointVenture boolean");
			sql2.append(",@supplierId guid");
			sql2.append(",@percentage double");
			sql2.append(")");
			sql2.append(" begin");
			sql2.append(" update ").append(Tab_Materials).append("  as a");
			sql2.append(" set MaterialName=@MaterialsName,");
			// sql2.append(" salePrice=@salePrice,remark=@remark,");
			sql2.append(" remark=@remark,");
			sql2.append(" lastModifyDate=@lastModifyDate,");
			// sql2.append(" lastModifyPerson=@lastModifyPerson,setPriceFlag=@setPriceFlag,Materialsstatus = @Materialsstatus");
			sql2.append(" lastModifyPerson=@lastModifyPerson,status = @Materialsstatus");
			sql2.append(",shelfLife=@shelfLife");
			sql2.append(",warningDay=@warningDay");
			sql2.append(",isJointVenture=@isJointVenture");
			sql2.append(",supplierId=@supplierId");
			sql2.append(",percentage=@percentage");
			sql2.append(" where 1=1");
			sql2.append(" and a.RECID=@RECID");
			sql2.append(" end");

			DBCommand dbc2 = context.prepareStatement(sql2);
			int i = 0;
			dbc2.setArgumentValue(i++, materialsInfo.getId());
			dbc2.setArgumentValue(i++, materialsInfo.getName());
			dbc2.setArgumentValue(i++, materialsInfo.getSalePrice());
			dbc2.setArgumentValue(i++, materialsInfo.getRemark());
			dbc2.setArgumentValue(i++, System.currentTimeMillis());
			dbc2.setArgumentValue(i++, emp.getName());
			dbc2.setArgumentValue(i++, true);
			dbc2.setArgumentValue(i++, Materialsstatus.getCode());
			dbc2.setArgumentValue(i++, materialsInfo.getShelfLife());
			dbc2.setArgumentValue(i++, materialsInfo.getWarningDay());
			dbc2.setArgumentValue(i++, materialsInfo.isJointVenture());
			dbc2.setArgumentValue(i++, materialsInfo.getSupplierId());
			dbc2.setArgumentValue(i++, materialsInfo.getPercentage());
			dbc2.executeUpdate();
			if (checkJointVenture(context, materialsOrmEntity, materialsInfo)) {
				saveJointLog(context, materialsOrmEntity, materialsInfo);
			}
			if (!materialsOrmEntity.isJointVenture() && materialsInfo.isJointVenture()) {
				updateMaterialsItemThreshold(context, materialsOrmEntity);

			}
			context.handle(new UpdateMaterialsResourceTask(materialsInfo.getId()), UpdateMaterialsResourceTask.Method.Modify);

			for (MaterialsInfoTask.Item item : materialsInfo.getItems()) {
				saveMaterialsProperty(context, materialsInfo, item);
				context.handle(new UpdateMaterialsItemResourceTask(item.getId()), UpdateMaterialsItemResourceTask.Method.Put);
			}
		}

		// /**
		// * 删除材料的所有条目
		// *
		// * @param context
		// * @param id
		// * void
		// */
		// private void deleteMaterialsProperty(Context context, GUID id) {
		// StringBuffer sql = new StringBuffer(
		// "define delete deleteMaterialsItem(@MaterialsId guid)");
		// sql.append(" begin");
		// sql.append(" delete from ");
		// sql.append(Tab_MaterialsItem);
		// sql.append(" as a");
		// sql.append(" where 1=1");
		// sql.append(" and a.MaterialsGuid=@MaterialsId ");
		// sql.append(" end");
		// DBCommand dbc = context.prepareStatement(sql);
		// dbc.setArgumentValue(0, id);
		// dbc.executeUpdate();
		// ResourceToken<Materials> token =
		// context.findResourceToken(Materials.class,id);
		// for(MaterialsItem item :
		// context.getResourceReferences(MaterialsItem.class,
		// token)){
		// context.handle(new
		// UpdateMaterialsItemResourceTask(item.getId()),UpdateMaterialsItemResourceTask.Method.Remove);
		// }
		// }

	}

	/*************************************
	 * 材料条目相关服务
	 ************************************/

	/**
	 * 获得指定材料的交易记录
	 */
	@Publish
	protected final class GetMaterialsTraderLogListProvider extends TwoKeyResultListProvider<MaterialsTraderLogItem, GUID, TraderType> {

		@Override
		protected void provide(Context context, GUID key1, TraderType key2, List<MaterialsTraderLogItem> resultList) throws Throwable {
			ORMAccessor<MaterialsTraderLogOrmEntity> acc = context.newORMAccessor(orm_MaterialsTraderLogByMaterialsId);
			List<MaterialsTraderLogOrmEntity> list = acc.fetch(key1, key2.name());
			for (MaterialsTraderLogOrmEntity MaterialsTraderLogOrmEntity : list) {
				MaterialsTraderLog log = new MaterialsTraderLog(MaterialsTraderLogOrmEntity);
				MaterialsTraderLogItemImpl g = new MaterialsTraderLogItemImpl();
				MaterialsItem gi = context.find(MaterialsItem.class, log.getMaterialsItemId());
				Partner p = context.find(Partner.class, log.getPartnerId());
				g.setProperty(MaterialsProperyUtil.subMaterialsPropertyToString(gi.getMaterialProperties()));
				g.setUnit(gi.getMaterialUnit());
				g.setPartnerName(p.getShortName());
				g.setStatus(gi.getStatus().getName());
				g.setCount(log.getCount());
				g.setTotalTraderAmount(log.getTotalTraderAmount());
				g.setTotalTraderCount(DoubleUtil.getRoundStr(log.getTotalTraderCount(), gi.getScale()));
				g.setRecentCount(DoubleUtil.getRoundStr(log.getRecentCount(), gi.getScale()));
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
	 * 更新材料条目交易记录
	 * </p>
	 * 
	 */
	@Publish
	protected final class UpdateMaterialsTraderLogHandler extends SimpleTaskMethodHandler<UpdateMaterialsTraderLogTask> {

		@Override
		protected void handle(Context context, UpdateMaterialsTraderLogTask task) throws Throwable {
			ORMAccessor<MaterialsTraderLogOrmEntity> acc = context.newORMAccessor(orm_MaterialsTraderLogByMaterialsItemIdAndPartnerId);
			MaterialsTraderLogOrmEntity entity = acc.findByPKey(task.getMaterialsItemId(), task.getPratnerId());
			MaterialsTraderLog log;
			boolean isCreate = false;
			if (entity == null) {
				log = new MaterialsTraderLog();
				entity = new MaterialsTraderLogOrmEntity();
				entity.setId(context.newRECID());
				entity.setType(task.getType().name());
				MaterialsItem MaterialsItem = context.find(MaterialsItem.class, task.getMaterialsItemId());
				entity.setMaterialsId(MaterialsItem.getMaterialId());
				entity.setMaterialsItemId(task.getMaterialsItemId());
				entity.setPartnerId(task.getPratnerId());
				isCreate = true;
			} else {
				log = new MaterialsTraderLog(entity);
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
			// if(task.getType()==TraderType.Purchase){ //写入材料最近采购单价
			// UpdateMaterialsItemRecentPurchasePriceTask
			// updateMaterialsItemRecentPurchasePriceTask = new
			// UpdateMaterialsItemRecentPurchasePriceTask(task.getMaterialsItemId(),
			// task.getPrice());
			// context.handle(updateMaterialsItemRecentPurchasePriceTask);
			// }
		}

	}

	/**
	 * 获得材料条目维护对象
	 */
	@Publish
	protected class GetMaterialsItemInfoByIdProvider extends OneKeyResultProvider<MaterialsItemInfo, GUID> {

		@Override
		protected MaterialsItemInfo provide(Context context, GUID guid) throws Throwable {
			MaterialsItem MaterialsItem = context.find(MaterialsItem.class, guid);
			MaterialsItemInfoImpl result = new MaterialsItemInfoImpl();
			result.setBaseInfo(context.find(MaterialsInfo.class, MaterialsItem.getMaterialId()));
			result.setItemData(MaterialsHelper.materialsItemToItemData(MaterialsItem));
			return result;
		}

		public class MaterialsItemInfoImpl extends MaterialsItemInfo {

			public void setBaseInfo(MaterialsInfo baseInfo) {
				this.baseInfo = baseInfo;
			}

			public void setItemData(MaterialsItemData itemData) {
				this.itemData = itemData;
			}

		}

	}

	/**
	 * 修改材料库存上限<BR>
	 * 
	 * 
	 */
	@Publish
	protected class UpdateMaterialsItemUpperLimitHandler extends SimpleTaskMethodHandler<UpdateMaterialsItemThresholdTask> {

		@Override
		protected void handle(Context context, UpdateMaterialsItemThresholdTask task) throws Throwable {
			MaterialsItemThresholdItem[] items = task.items;
			// ORMAccessor<MaterialsOrmEntity> MaterialsAcc =
			// context.newORMAccessor(orm_Materials);
			boolean isFirst = true;
			for (MaterialsItemThresholdItem item : items) {
				if (isFirst) {
					changeMaterialsWarnningType(context, task);
				}
				InventoryLimitTask iTask = null;
				switch (item.getInventoryWarningType()) {
				case Store_Amount:
					iTask = new InventoryLimitTask(item.getStoreId(), item.getMaterialsItemId(), item.getStoreAmountUpper());
					context.handle(iTask);
					break;
				case Store_Count:
					iTask = new InventoryLimitTask(item.getStoreId(), item.getMaterialsItemId(), item.getStoreUpper(), item.getStoreFloor());
					context.handle(iTask);
					break;
				case ALL_Amount:
					ORMAccessor<MaterialsItemOrmEntity> acc = context.newORMAccessor(orm_MaterialsItem);
					MaterialsItemOrmEntity entity = acc.findByRECID(item.getMaterialsItemId());
					entity.setTotalStoreAmount(item.getStoreAmountUpper());
					acc.update(entity);
					context.handle(new UpdateMaterialsItemResourceTask(entity.getId()), UpdateMaterialsItemResourceTask.Method.Modify);
					break;
				}
				context.dispatch(new MaterialsItemThresholdChangeEvent(item.getMaterialsItemId()));
			}
		}

		private void changeMaterialsWarnningType(Context context, UpdateMaterialsItemThresholdTask task) {
			MaterialsItemThresholdItem item = task.items[0];
			MaterialsItem MaterialsItem = context.find(MaterialsItem.class, item.getMaterialsItemId());
			ORMAccessor<MaterialsOrmEntity> acc = context.newORMAccessor(orm_Materials);
			MaterialsOrmEntity entity = acc.findByRECID(MaterialsItem.getMaterialId());
			entity.setInventoryWarningType(item.getInventoryWarningType().getCode());
			acc.update(entity);
			context.handle(new UpdateMaterialsResourceTask(MaterialsItem.getMaterialId()), UpdateMaterialsResourceTask.Method.Modify);
		}
	}

	/**
	 * 
	 * <p>
	 * 材料条目选择界面查询材料条目列表
	 * </p>
	 * 
	 */
	@Publish
	protected final class GetMaterialsInfoListProvider extends OneKeyResultProvider<ListEntity<MaterialsInfo>, GetMaterialsInfoListKey> {

		private class GFilter implements Filter<MaterialsItem> {

			private String searchkey;

			private GetMaterialsInfoListKey key;

			public GFilter(GetMaterialsInfoListKey key) {
				this.searchkey = key.getSearchText() == null ? "" : key.getSearchText();
				this.key = key;
			}

			public boolean accept(MaterialsItem item) {

				if (null != key.getJointVenture() && key.getJointVenture()) {
					if (!(MaterialsStatus.ON_SALE == item.getStatus() && item.getMaterial().isJointVenture())) {
						return false;
					}
					if (item.getMaterialName().indexOf(searchkey) > -1 || item.getMaterialCode().indexOf(searchkey) > -1) {
						return true;
					}
					return false;
				}

				if(null!=key.getJointVenture()&&item.getMaterial().isJointVenture()&&(item.getMaterial().isJointVenture()&&!key.getStatus().equals(MaterialsStatus.STOP_SALE)))
				{
					return false;
				}
				if (key.getStatus() != MaterialsStatus.PART_SALE) {
					if (!item.getStatus().equals(key.getStatus()))
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
				return item.getMaterialName().indexOf(searchkey) > -1 || item.getMaterialCode().indexOf(searchkey) > -1;
			}

		}

		@Override
		protected ListEntity<MaterialsInfo> provide(final Context context, final GetMaterialsInfoListKey key) throws Throwable {
			LinkedHashMap<GUID, MaterialsInfoImpl> MaterialsMap = new LinkedHashMap<GUID, MaterialsInfoImpl>();
			List<MaterialsItem> items = new ArrayList<MaterialsItem>();
			if (key.getCateoryId() != null) {
				List<MaterialsCategory> list = context.getList(MaterialsCategory.class, new GetMaterialsCategoryLeafNodesKey(key.getCateoryId()));
				for (MaterialsCategory MaterialsCategory : list) {
					ResourceToken<MaterialsCategory> category = context.findResourceToken(MaterialsCategory.class, MaterialsCategory.getId());
					items.addAll(context.getResourceReferences(MaterialsItem.class, category, new GFilter(key)));
				}
			} else {
				// ResourceToken<Tenant> token =
				// TenantHelper.getTenantToken(context);
				List<MaterialsItem> list = context.getList(MaterialsItem.class);
				items.addAll(getFilteredList(list, key));
				// items = context.getResourceReferences(MaterialsItem.class,
				// new GFilter(key));
			}
			for (MaterialsItem MaterialsItem : items) {
				if (!MaterialsMap.containsKey(MaterialsItem.getMaterialId())) {
					MaterialsInfoImpl info = MaterialsHelper.materialsToMaterialsInfo(context, MaterialsItem.getMaterial());
					info.setCategory(MaterialsHelper.materialsCategoryTomaterialsCategoryInfoImpl(context.find(MaterialsCategory.class, MaterialsItem
							.getCategoryId())));
					MaterialsMap.put(MaterialsItem.getMaterialId(), info);
				}
				MaterialsMap.get(MaterialsItem.getMaterialId()).addItem(MaterialsHelper.materialsItemToItemData(MaterialsItem));
			}
			List<MaterialsInfo> result = new ArrayList<MaterialsInfo>();
			int i = 0;
			for (MaterialsInfo Materials : MaterialsMap.values()) {
				// if(Materials.gets)
				if(i >= key.getOffset() && i <(key.getOffset()+key.getCount())){
					result.add(Materials);
				}
				// if(i==key.getOffset()+key.getCount())break;
				i++;
			}

			if (key.getSortField() != null && !key.getSortField().equals(SortField.None)) {
				String sortField = key.getSortField().getFieldName();
				boolean isSortByDesc = key.getSortType().equals(SortType.Desc) ? true : false;
				ComparatorUtils.sort(result, sortField, isSortByDesc);
			}

			return new ListEntity<MaterialsInfo>(result, MaterialsMap.size());
		}

		private Collection<? extends MaterialsItem> getFilteredList(List<MaterialsItem> list, GetMaterialsInfoListKey key) {
			List<MaterialsItem> newList = new ArrayList<MaterialsItem>();
			String searchkey = key.getSearchText() == null ? "" : key.getSearchText();

			for (MaterialsItem item : list) {
				if (null != key.getJointVenture() && key.getJointVenture()) {
					if (!(MaterialsStatus.ON_SALE == item.getStatus() && item.getMaterial().isJointVenture())) {
						continue;
					}
					if (item.getMaterialName().indexOf(searchkey) > -1 || item.getMaterialCode().indexOf(searchkey) > -1
							|| item.getMaterialNo().indexOf(searchkey) > -1) {
						newList.add(item);
					}
					continue;
				}

				if ((key.getStatus() != MaterialsStatus.PART_SALE && !item.getStatus().equals(key.getStatus()))
						|| (key.isNopriceOnly() && !item.getStatus().equals(key.getStatus())) || (key.isSetPriceOnley() && item.getSalePrice() < 0)
						|| (null!=key.getJointVenture()&&item.getMaterial().isJointVenture()&&!key.getStatus().equals(MaterialsStatus.STOP_SALE))) {
					continue;
				}
				if (item.getMaterialName().indexOf(searchkey) > -1 || item.getMaterialCode().indexOf(searchkey) > -1
						|| item.getMaterialNo().indexOf(searchkey) > -1) {
					newList.add(item);
				}
			}

			return newList;
		}

	}

	/**
	 * 
	 * <p>
	 * 标识材料已使用过了
	 * </p>
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @version 2012-4-22
	 */
	@Publish
	protected final class MarkMaterialsItemUsedHandler extends SimpleTaskMethodHandler<MarkMaterialsItemUsedTask> {

		@Override
		protected void handle(Context context, MarkMaterialsItemUsedTask task) throws Throwable {
			MaterialsItem MaterialsItem = context.find(MaterialsItem.class, task.getId());
			if (MaterialsItem.isRefFlag())
				return;
			ORMAccessor<MaterialsItemOrmEntity> acc = context.newORMAccessor(orm_MaterialsItem);
			MaterialsItemOrmEntity entity = acc.findByRECID(task.getId());
			entity.setRefFlag(true);
			acc.update(entity);
			context.handle(new UpdateMaterialsItemResourceTask(entity.getId()), UpdateMaterialsItemResourceTask.Method.Modify);
			ORMAccessor<MaterialsOrmEntity> MaterialsAcc = context.newORMAccessor(orm_Materials);
			MaterialsOrmEntity MaterialsEntity = MaterialsAcc.findByRECID(entity.getMaterialId());
			MaterialsEntity.setRefFlag(true);
			MaterialsAcc.update(MaterialsEntity);
			context.handle(new UpdateMaterialsResourceTask(MaterialsEntity.getId()), UpdateMaterialsResourceTask.Method.Modify);
		}

	}

	/**
	 * 
	 * <p>
	 * 批量添加
	 * </p>
	 * 
	 * 
	 */
	@Publish
	protected final class BatchSaveMaterialsHandler extends TaskMethodHandler<BatchSaveForExcelTask, BatchSaveForExcelTask.Model> {

		protected BatchSaveMaterialsHandler() {
			super(BatchSaveForExcelTask.Model.Materials);
		}

		@Override
		protected void handle(Context context, BatchSaveForExcelTask task) throws Throwable {
			ExcelReader reader = new ExcelReader(task.getExcel());
			// TODO 此处添加批量添加材料的代码

		}

	}

	/**
	 * 查询所有材料数量
	 */
	@Publish
	protected final class FindMaterialsCountProvider extends OneKeyResultProvider<Integer, FindMaterialsCountKey> {

		/**
		 * 查询材料数量的SQL
		 */
		private String getMaterialsCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_MaterialsCount(@tenantGuid guid) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from sa_Materials_info as t ");
			buffer.append(" where t.tenantsGuid=@tenantGuid");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindMaterialsCountKey key) throws Throwable {
			DBCommand dbCommand = context.prepareStatement(getMaterialsCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class).getTenantId());
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	/**
	 * 查询所有材料分类数量
	 */
	@Publish
	protected final class FindMaterialsTypeCountProvider extends OneKeyResultProvider<Integer, FindMaterialsTypeCountKey> {

		/**
		 * 查询材料分类数量的SQL
		 */
		private String getMaterialsTypeCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_MaterialsTypeCount(@tenantGuid guid) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from sa_Materials_type as t ");
			buffer.append(" where t.tenantsGuid=@tenantGuid");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindMaterialsTypeCountKey key) throws Throwable {
			DBCommand dbCommand = context.prepareStatement(getMaterialsTypeCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class).getTenantId());
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	/**
	 * 查询已设置属性材料分类的数量
	 */
	@Publish
	protected final class FindMaterialsTypeHadSetPropertiesCountProvider extends
			OneKeyResultProvider<Integer, FindMaterialsTypeHadSetPropertiesCountKey> {

		/**
		 * 查询已设置属性材料分类的数量的SQL
		 */
		private String getMaterialsTypeHadSetPropertiesCountSql() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("define query Qu_MaterialsTypeCount(@tenantGuid guid, @setPropertyFlag boolean) ");
			buffer.append(" begin ");
			buffer.append(" select count(*) ");
			buffer.append(" from sa_Materials_type as t ");
			buffer.append(" where t.tenantsGuid=@tenantGuid and t.setPropertyFlag=@setPropertyFlag");
			buffer.append(" end ");
			return buffer.toString();
		}

		@Override
		protected Integer provide(Context context, FindMaterialsTypeHadSetPropertiesCountKey key) throws Throwable {
			DBCommand dbCommand = context.prepareStatement(getMaterialsTypeHadSetPropertiesCountSql());
			try {
				dbCommand.setArgumentValue(0, context.get(Login.class).getTenantId());
				dbCommand.setArgumentValue(1, Boolean.TRUE);
				return (Integer) dbCommand.executeScalar();
			} finally {
				dbCommand.unuse();
			}
		}

	}

	public boolean checkJointVenture(Context context, MaterialsOrmEntity materialsOrmEntity, MaterialsInfoTask materialsInfo) {

		return materialsOrmEntity.isJointVenture() != materialsInfo.isJointVenture()
				|| (null == materialsOrmEntity.getSupplierId() && null != materialsInfo.getSupplierId())
				|| (null!=materialsOrmEntity.getSupplierId()&&null== materialsInfo.getSupplierId())
				|| (null != materialsOrmEntity.getSupplierId() && null != materialsInfo.getSupplierId() && !materialsInfo.getSupplierId().equals(
						materialsOrmEntity.getSupplierId())) || materialsOrmEntity.getPercentage() != materialsInfo.getPercentage();
	}

	public void updateMaterialsItemThreshold(Context context, MaterialsOrmEntity materialsOrmEntity) {

		ORMAccessor<MaterialsItemOrmEntity> acc = context.newORMAccessor(orm_MaterialsItemByMarerialId);
		List<MaterialsItemOrmEntity> list = acc.fetch(materialsOrmEntity.getId());
		for (MaterialsItemOrmEntity item : list) {
			/**
			 * 取消总库存阈值
			 */
			item.setTotalStoreAmount(-1d);
			acc.update(item);
			context.handle(new UpdateMaterialsItemResourceTask(item.getId()), UpdateMaterialsItemResourceTask.Method.Modify);
			/**
			 * 取消分库存阈值
			 */
			StringBuffer scriptBuffer = new StringBuffer();// 存放SQL脚本
			scriptBuffer.append("define update Up_goodsCount(@stockId guid,"
					+ " @upperLimitCount double, @lowerLimitCount double, @upperLimitAmount double,@inventoryType string) \n");

			StringBuffer setColumns = new StringBuffer();
			setColumns.append(" upperLimitCount = @upperLimitCount");
			setColumns.append(",lowerLimitCount = @lowerLimitCount");
			setColumns.append(",upperLimitAmount = @upperLimitAmount");

			scriptBuffer.append(" begin \n");
			scriptBuffer.append(" update \n");
			scriptBuffer.append(ERPTableNames.Inventory.Inventory.getTableName());
			scriptBuffer.append(" as t \n");
			scriptBuffer.append(" set \n");
			scriptBuffer.append(setColumns.toString());
			scriptBuffer.append("\n where t.stockId = @stockId");
			scriptBuffer.append(" and t.inventoryType=@inventoryType\n");
			scriptBuffer.append(" end");
			DBCommand db = context.prepareStatement(scriptBuffer.toString());

			int index = 0;
			db.setArgumentValue(index++, item.getId());
			db.setArgumentValue(index++, -1d);
			db.setArgumentValue(index++, -1d);
			db.setArgumentValue(index++, -1d);
			db.setArgumentValue(index++, InventoryType.Materials.getCode());
			db.executeUpdate();
			context.dispatch(new MaterialsItemThresholdChangeEvent(item.getId()));

			db.unuse();
			acc.unuse();

		}
	}

}
