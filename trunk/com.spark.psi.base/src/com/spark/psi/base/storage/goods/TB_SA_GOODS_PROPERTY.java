package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_GOODS_PROPERTY extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_PROPERTY";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_goodsCountDigit;
	public final TableFieldDefine f_salePrice;
	public final TableFieldDefine f_goodsPropertyState;
	public final TableFieldDefine f_deleteFlag;
	public final TableFieldDefine f_reflag;
	public final TableFieldDefine f_propertyValue;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_avgBuyPrice;
	public final TableFieldDefine f_totalStoreUpper;
	public final TableFieldDefine f_totalStoreFlore;
	public final TableFieldDefine f_totalStoreAmount;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_lastModifyPerson;
	public final TableFieldDefine f_tipsType;
	public final TableFieldDefine f_recentPurchasePrice;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_goodsCountDigit ="goodsCountDigit";
	public static final String FN_salePrice ="salePrice";
	public static final String FN_goodsPropertyState ="goodsPropertyState";
	public static final String FN_deleteFlag ="deleteFlag";
	public static final String FN_reflag ="reflag";
	public static final String FN_propertyValue ="propertyValue";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_avgBuyPrice ="avgBuyPrice";
	public static final String FN_totalStoreUpper ="totalStoreUpper";
	public static final String FN_totalStoreFlore ="totalStoreFlore";
	public static final String FN_totalStoreAmount ="totalStoreAmount";
	public static final String FN_createDate ="createDate";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_lastModifyPerson ="lastModifyPerson";
	public static final String FN_tipsType ="tipsType";
	public static final String FN_recentPurchasePrice ="recentPurchasePrice";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_GOODS_PROPERTY() {
		super(TABLE_NAME);
		this.table.setDescription("存放商品属性信息");
		this.table.setTitle("商品属性信息表 ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setDescription("关联租户表");
		field.setTitle("租户GUID");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setDescription("关联商品表");
		field.setTitle("商品GUID");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(20));
		field.setTitle("商品编号");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("商品名称");
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setDescription("关联商品分类信息");
		field.setTitle("商品分类GUID");
		this.f_goodsCountDigit = field = this.table.newField(FN_goodsCountDigit, TypeFactory.INT);
		field.setTitle("商品显示位数");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_salePrice = field = this.table.newField(FN_salePrice, TypeFactory.NUMERIC(17,2));
		field.setDescription("默认为0，表示没有设置");
		field.setTitle("销售价格");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_goodsPropertyState = field = this.table.newField(FN_goodsPropertyState, TypeFactory.CHAR(2));
		field.setDescription("枚举(01在售、02停售)，默认为01");
		field.setTitle("商品属性状态");
		this.f_deleteFlag = field = this.table.newField(FN_deleteFlag, TypeFactory.BOOLEAN);
		field.setDescription("1可删除、0不可删除，默认为1");
		field.setTitle("删除标志");
		field.setDefault(ConstExpression.builder.expOf(true));
		this.f_reflag = field = this.table.newField(FN_reflag, TypeFactory.BOOLEAN);
		field.setDescription("1已关联、0未关联，默认为0");
		field.setTitle("关联标识");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_propertyValue = field = this.table.newField(FN_propertyValue, TypeFactory.TEXT);
		field.setDescription("用JSON的方式来记录这些属性的键值对，其中键为属性名称、值为录入的内容，例如：{“单位”:”个”,{“颜色”:{“01”:”红”}}}");
		field.setTitle("属性值");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(40));
		field.setTitle("商品单位");
		this.f_avgBuyPrice = field = this.table.newField(FN_avgBuyPrice, TypeFactory.NUMERIC(17,2));
		field.setTitle("平均采购价格");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_totalStoreUpper = field = this.table.newField(FN_totalStoreUpper, TypeFactory.NUMERIC(17,5));
		field.setTitle("总库存上限数量");
		this.f_totalStoreFlore = field = this.table.newField(FN_totalStoreFlore, TypeFactory.NUMERIC(17,5));
		field.setTitle("总库存下限数量");
		this.f_totalStoreAmount = field = this.table.newField(FN_totalStoreAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("总库存上限金额");
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
		this.f_recentPurchasePrice = field = this.table.newField(FN_recentPurchasePrice, TypeFactory.VARCHAR(10));
		field.setDescription("订单生效为准");
		field.setTitle("最近采购价格");
		this.table.newIndex("SA_GOODS_PROPERTY_ONE",f_tenantsGuid,f_goodsPropertyState,f_goodsGuid);
	}

}
