package com.spark.b2c.storage.joint;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_JOINT_RECORD extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_JOINT_RECORD";

	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_sheetId;
	public final TableFieldDefine f_supplierId;
	public final TableFieldDefine f_supplierName;
	public final TableFieldDefine f_shortName;
	public final TableFieldDefine f_supplierNamePY;
	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsPrice;
	public final TableFieldDefine f_salesCount;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_percentage;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_alreadySettlement;

	public static final String FN_goodsId ="goodsId";
	public static final String FN_sheetId ="sheetId";
	public static final String FN_supplierId ="supplierId";
	public static final String FN_supplierName ="supplierName";
	public static final String FN_shortName ="shortName";
	public static final String FN_supplierNamePY ="supplierNamePY";
	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsPrice ="goodsPrice";
	public static final String FN_salesCount ="salesCount";
	public static final String FN_amount ="amount";
	public static final String FN_percentage ="percentage";
	public static final String FN_createDate ="createDate";
	public static final String FN_alreadySettlement ="alreadySettlement";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_JOINT_RECORD() {
		super(TABLE_NAME);
		this.table.setTitle("联营商品销售记录");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品id");
		this.f_sheetId = this.table.newField(FN_sheetId, TypeFactory.GUID);
		this.f_supplierId = this.table.newField(FN_supplierId, TypeFactory.GUID);
		this.f_supplierName = this.table.newField(FN_supplierName, TypeFactory.NVARCHAR(100));
		this.f_shortName = this.table.newField(FN_shortName, TypeFactory.NVARCHAR(20));
		this.f_supplierNamePY = this.table.newField(FN_supplierNamePY, TypeFactory.NVARCHAR(50));
		this.f_sheetNo = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(30));
		this.f_goodsCode = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		this.f_goodsNo = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		this.f_goodsSpec = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(100));
		this.f_goodsUnit = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(20));
		this.f_goodsName = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		this.f_goodsPrice = this.table.newField(FN_goodsPrice, TypeFactory.NUMERIC(10,2));
		this.f_salesCount = this.table.newField(FN_salesCount, TypeFactory.NUMERIC(15,2));
		this.f_amount = this.table.newField(FN_amount, TypeFactory.NUMERIC(15,2));
		this.f_percentage = this.table.newField(FN_percentage, TypeFactory.NUMERIC(5,4));
		this.f_createDate = this.table.newField(FN_createDate, TypeFactory.DATE);
		this.f_alreadySettlement = field = this.table.newField(FN_alreadySettlement, TypeFactory.BOOLEAN);
		field.setDefault(ConstExpression.builder.expOf(false));
	}

}
