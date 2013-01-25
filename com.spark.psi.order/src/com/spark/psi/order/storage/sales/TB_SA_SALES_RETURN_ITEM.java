package com.spark.psi.order.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_SALES_RETURN_ITEM extends TableDeclarator {

	public static final String TABLE_NAME ="SA_SALES_RETURN_ITEM";

	public final TableFieldDefine f_tenantsId;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_price;
	public final TableFieldDefine f_num;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_orderId;
	public final TableFieldDefine f_goodsItemId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsProperties;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_countDecimal;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_storeName;

	public static final String FN_tenantsId ="tenantsId";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_price ="price";
	public static final String FN_num ="num";
	public static final String FN_amount ="amount";
	public static final String FN_orderId ="orderId";
	public static final String FN_goodsItemId ="goodsItemId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsProperties ="goodsProperties";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_countDecimal ="countDecimal";
	public static final String FN_storeId ="storeId";
	public static final String FN_storeName ="storeName";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_SALES_RETURN_ITEM() {
		super(TABLE_NAME);
		this.table.setTitle("存放退货单明细信息");
		TableFieldDeclare field;
		this.f_tenantsId = field = this.table.newField(FN_tenantsId, TypeFactory.GUID);
		field.setTitle("租户GUID");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人GUID");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.VARCHAR(50));
		field.setTitle("创建人姓名");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_price = field = this.table.newField(FN_price, TypeFactory.NUMERIC(17,2));
		field.setTitle("单价");
		this.f_num = field = this.table.newField(FN_num, TypeFactory.NUMERIC(17,5));
		field.setTitle("数量");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("金额");
		this.f_orderId = field = this.table.newField(FN_orderId, TypeFactory.GUID);
		field.setTitle("退货单号");
		this.f_goodsItemId = field = this.table.newField(FN_goodsItemId, TypeFactory.GUID);
		field.setTitle("商品Guid");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(20));
		field.setTitle("商品条码");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_goodsProperties = field = this.table.newField(FN_goodsProperties, TypeFactory.NVARCHAR(1000));
		field.setTitle("商品属性");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(12));
		field.setTitle("单位");
		this.f_countDecimal = field = this.table.newField(FN_countDecimal, TypeFactory.INT);
		field.setTitle("商品小数位数");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库GUID");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(50));
		field.setTitle("仓库名称");
	}

}
