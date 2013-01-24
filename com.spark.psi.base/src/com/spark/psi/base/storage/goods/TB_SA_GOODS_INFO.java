package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_GOODS_INFO extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_INFO";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_salePrice;
	public final TableFieldDefine f_goodsStatus;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_deleteFlag;
	public final TableFieldDefine f_refFlag;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_lastModifyPerson;
	public final TableFieldDefine f_tipsType;
	public final TableFieldDefine f_setPriceFlag;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_salePrice ="salePrice";
	public static final String FN_goodsStatus ="goodsStatus";
	public static final String FN_remark ="remark";
	public static final String FN_deleteFlag ="deleteFlag";
	public static final String FN_refFlag ="refFlag";
	public static final String FN_createDate ="createDate";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_lastModifyPerson ="lastModifyPerson";
	public static final String FN_tipsType ="tipsType";
	public static final String FN_setPriceFlag ="setPriceFlag";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_GOODS_INFO() {
		super(TABLE_NAME);
		this.table.setDescription("存放商品管理信息");
		this.table.setTitle("商品信息表 ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setDescription("关联租户");
		field.setTitle("租户GUID");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(20));
		field.setTitle("商品编号");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("商品名称");
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setDescription("关联商品分类信息");
		field.setTitle("商品分类GUID");
		this.f_salePrice = field = this.table.newField(FN_salePrice, TypeFactory.NUMERIC(17,2));
		field.setDescription("默认为0，0表示没有设置该字段");
		field.setTitle("统一销售价格");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_goodsStatus = field = this.table.newField(FN_goodsStatus, TypeFactory.CHAR(2));
		field.setDescription("枚举(01在售、02停售、03既有在售又有停售)");
		field.setTitle("商品状态");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_deleteFlag = field = this.table.newField(FN_deleteFlag, TypeFactory.BOOLEAN);
		field.setDescription("1可删除、0不可删除，默认为1");
		field.setTitle("删除标志");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_refFlag = field = this.table.newField(FN_refFlag, TypeFactory.BOOLEAN);
		field.setDescription("1已关联、0未关联，默认为0");
		field.setTitle("关联标识");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("数据插入时间");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("数据创建人");
		this.f_lastModifyDate = field = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		field.setTitle("数据最后修改时间");
		this.f_lastModifyPerson = field = this.table.newField(FN_lastModifyPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("数据最后修改人");
		this.f_tipsType = field = this.table.newField(FN_tipsType, TypeFactory.CHAR(2));
		field.setTitle("商品预警类型");
		this.f_setPriceFlag = field = this.table.newField(FN_setPriceFlag, TypeFactory.BOOLEAN);
		field.setTitle("是否设置了销售价格");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.table.newIndex("SA_GOODS_INFO_ONE",f_tenantsGuid,f_goodsStatus,f_setPriceFlag);
	}

}
