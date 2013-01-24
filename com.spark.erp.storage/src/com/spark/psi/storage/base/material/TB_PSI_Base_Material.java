package com.spark.psi.storage.base.material;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_Material extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_Material";

	public final TableFieldDefine f_materialCode;
	public final TableFieldDefine f_materialName;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_categoryId;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_canDelete;
	public final TableFieldDefine f_refFlag;
	public final TableFieldDefine f_inventoryWarningType;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_isJointVenture;
	public final TableFieldDefine f_supplierId;
	public final TableFieldDefine f_percentage;
	public final TableFieldDefine f_lastModifyPerson;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_shelfLife;
	public final TableFieldDefine f_warningDay;

	public static final String FN_materialCode ="materialCode";
	public static final String FN_materialName ="materialName";
	public static final String FN_namePY ="namePY";
	public static final String FN_categoryId ="categoryId";
	public static final String FN_remark ="remark";
	public static final String FN_canDelete ="canDelete";
	public static final String FN_refFlag ="refFlag";
	public static final String FN_inventoryWarningType ="inventoryWarningType";
	public static final String FN_createDate ="createDate";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_isJointVenture ="isJointVenture";
	public static final String FN_supplierId ="supplierId";
	public static final String FN_percentage ="percentage";
	public static final String FN_lastModifyPerson ="lastModifyPerson";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_status ="status";
	public static final String FN_shelfLife ="shelfLife";
	public static final String FN_warningDay ="warningDay";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_Material() {
		super(TABLE_NAME);
		this.table.setTitle("材料主体");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_materialCode = field = this.table.newField(FN_materialCode, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编码");
		this.f_materialName = field = this.table.newField(FN_materialName, TypeFactory.NVARCHAR(100));
		field.setTitle("商品名称");
		this.f_namePY = field = this.table.newField(FN_namePY, TypeFactory.NVARCHAR(100));
		field.setTitle("拼音");
		this.f_categoryId = field = this.table.newField(FN_categoryId, TypeFactory.GUID);
		field.setTitle("商品分类GUID");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_canDelete = field = this.table.newField(FN_canDelete, TypeFactory.BOOLEAN);
		field.setTitle("是否可以删除");
		this.f_refFlag = field = this.table.newField(FN_refFlag, TypeFactory.BOOLEAN);
		field.setTitle("关联标识");
		this.f_inventoryWarningType = field = this.table.newField(FN_inventoryWarningType, TypeFactory.CHAR(2));
		field.setTitle("库存预警类型");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("数据插入时间");
		this.f_lastModifyDate = field = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		field.setTitle("数据最后修改时间");
		this.f_isJointVenture = field = this.table.newField(FN_isJointVenture, TypeFactory.BOOLEAN);
		field.setTitle("是否联营商品");
		this.f_supplierId = field = this.table.newField(FN_supplierId, TypeFactory.GUID);
		field.setTitle("联营供应商");
		this.f_percentage = field = this.table.newField(FN_percentage, TypeFactory.NUMERIC(17,4));
		field.setTitle("提成");
		this.f_lastModifyPerson = field = this.table.newField(FN_lastModifyPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("数据最后修改人");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("商品状态");
		this.f_shelfLife = field = this.table.newField(FN_shelfLife, TypeFactory.INT);
		field.setTitle("保质期");
		this.f_warningDay = field = this.table.newField(FN_warningDay, TypeFactory.INT);
		field.setTitle("预警天数");
	}

}
