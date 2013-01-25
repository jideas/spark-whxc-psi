package com.spark.psi.storage.report.store;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_sa_report_sto_stream extends TableDeclarator {

	public static final String TABLE_NAME ="sa_report_sto_stream";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_storeGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsAttr;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_currDate;
	public final TableFieldDefine f_orderGuid;
	public final TableFieldDefine f_orderNo;
	public final TableFieldDefine f_streamType;
	public final TableFieldDefine f_instoCount;
	public final TableFieldDefine f_instoAmount;
	public final TableFieldDefine f_goodsScale;
	public final TableFieldDefine f_outstoCount;
	public final TableFieldDefine f_outstoAmount;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createdDate;
	public final TableFieldDefine f_dayNo;
	public final TableFieldDefine f_monthNo;
	public final TableFieldDefine f_quarter;
	public final TableFieldDefine f_yearNo;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_storeGuid ="storeGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsAttr ="goodsAttr";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_currDate ="currDate";
	public static final String FN_orderGuid ="orderGuid";
	public static final String FN_orderNo ="orderNo";
	public static final String FN_streamType ="streamType";
	public static final String FN_instoCount ="instoCount";
	public static final String FN_instoAmount ="instoAmount";
	public static final String FN_goodsScale ="goodsScale";
	public static final String FN_outstoCount ="outstoCount";
	public static final String FN_outstoAmount ="outstoAmount";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createdDate ="createdDate";
	public static final String FN_dayNo ="dayNo";
	public static final String FN_monthNo ="monthNo";
	public static final String FN_quarter ="quarter";
	public static final String FN_yearNo ="yearNo";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_sa_report_sto_stream() {
		super(TABLE_NAME);
		this.table.setTitle("库存流水");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_storeGuid = field = this.table.newField(FN_storeGuid, TypeFactory.GUID);
		field.setTitle("仓库GUID");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("商品GUID");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.VARCHAR(50));
		field.setTitle("商品名称");
		this.f_goodsAttr = field = this.table.newField(FN_goodsAttr, TypeFactory.VARCHAR(100));
		field.setTitle("商品属性");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.VARCHAR(20));
		field.setTitle("商品单位");
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setTitle("商品分类GUID");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.VARCHAR(20));
		field.setTitle("商品编号");
		this.f_currDate = field = this.table.newField(FN_currDate, TypeFactory.DATE);
		field.setTitle("单据创建日期");
		this.f_orderGuid = field = this.table.newField(FN_orderGuid, TypeFactory.GUID);
		field.setTitle("单据编号");
		this.f_orderNo = field = this.table.newField(FN_orderNo, TypeFactory.NVARCHAR(20));
		field.setTitle("单据编号");
		this.f_streamType = field = this.table.newField(FN_streamType, TypeFactory.CHAR(2));
		field.setTitle("流水类型");
		this.f_instoCount = field = this.table.newField(FN_instoCount, TypeFactory.NUMERIC(17,4));
		field.setTitle("入库数量");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_instoAmount = field = this.table.newField(FN_instoAmount, TypeFactory.NUMERIC(17,4));
		field.setTitle("入库金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_goodsScale = field = this.table.newField(FN_goodsScale, TypeFactory.INT);
		field.setTitle("商品精度");
		this.f_outstoCount = field = this.table.newField(FN_outstoCount, TypeFactory.NUMERIC(17,4));
		field.setTitle("出库数量");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_outstoAmount = field = this.table.newField(FN_outstoAmount, TypeFactory.NUMERIC(17,4));
		field.setTitle("出库金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.VARCHAR(20));
		field.setTitle("创建人");
		this.f_createdDate = field = this.table.newField(FN_createdDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_dayNo = field = this.table.newField(FN_dayNo, TypeFactory.INT);
		field.setTitle("天号");
		this.f_monthNo = field = this.table.newField(FN_monthNo, TypeFactory.INT);
		field.setTitle("月号");
		this.f_quarter = field = this.table.newField(FN_quarter, TypeFactory.INT);
		field.setTitle("季号");
		this.f_yearNo = field = this.table.newField(FN_yearNo, TypeFactory.INT);
		field.setTitle("年号");
		this.table.newIndex("index_orderNo",f_orderNo,f_goodsName);
	}

}
