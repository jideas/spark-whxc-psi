package com.spark.psi.order.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_GOODS_Promotion extends TableDeclarator {

	public static final String TABLE_NAME ="SA_GOODS_Promotion";

	public final TableFieldDefine f_tenantsId;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_goodsItemId;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsNamePY;
	public final TableFieldDefine f_goodsProperties;
	public final TableFieldDefine f_countDecimal;
	public final TableFieldDefine f_promotionCount;
	public final TableFieldDefine f_saledCount;
	public final TableFieldDefine f_price_goods;
	public final TableFieldDefine f_price_promotion;
	public final TableFieldDefine f_beginDate;
	public final TableFieldDefine f_endDate;
	public final TableFieldDefine f_state;
	public final TableFieldDefine f_promotionCause;
	public final TableFieldDefine f_returnCause;
	public final TableFieldDefine f_approvalDate;
	public final TableFieldDefine f_deptId;

	public static final String FN_tenantsId ="tenantsId";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_goodsItemId ="goodsItemId";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsNamePY ="goodsNamePY";
	public static final String FN_goodsProperties ="goodsProperties";
	public static final String FN_countDecimal ="countDecimal";
	public static final String FN_promotionCount ="promotionCount";
	public static final String FN_saledCount ="saledCount";
	public static final String FN_price_goods ="price_goods";
	public static final String FN_price_promotion ="price_promotion";
	public static final String FN_beginDate ="beginDate";
	public static final String FN_endDate ="endDate";
	public static final String FN_state ="state";
	public static final String FN_promotionCause ="promotionCause";
	public static final String FN_returnCause ="returnCause";
	public static final String FN_approvalDate ="approvalDate";
	public static final String FN_deptId ="deptId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_GOODS_Promotion() {
		super(TABLE_NAME);
		this.table.setTitle("商品促销管理2");
		TableFieldDeclare field;
		this.f_tenantsId = field = this.table.newField(FN_tenantsId, TypeFactory.GUID);
		field.setTitle("租户GUID");
		field.setKeepValid(true);
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人GUID");
		field.setKeepValid(true);
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.VARCHAR(50));
		field.setTitle("创建人姓名");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		field.setKeepValid(true);
		this.f_goodsItemId = field = this.table.newField(FN_goodsItemId, TypeFactory.GUID);
		field.setTitle("商品GUID");
		field.setKeepValid(true);
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.VARCHAR(100));
		field.setTitle("商品名称");
		this.f_goodsNamePY = field = this.table.newField(FN_goodsNamePY, TypeFactory.VARCHAR(50));
		field.setTitle("名称拼音");
		this.f_goodsProperties = field = this.table.newField(FN_goodsProperties, TypeFactory.VARCHAR(1000));
		field.setTitle("商品属性");
		this.f_countDecimal = field = this.table.newField(FN_countDecimal, TypeFactory.INT);
		field.setTitle("商品小数位数");
		this.f_promotionCount = field = this.table.newField(FN_promotionCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("促销数量");
		field.setKeepValid(true);
		this.f_saledCount = field = this.table.newField(FN_saledCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("销售数量");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_price_goods = field = this.table.newField(FN_price_goods, TypeFactory.NUMERIC(17,2));
		field.setTitle("原价");
		field.setKeepValid(true);
		this.f_price_promotion = field = this.table.newField(FN_price_promotion, TypeFactory.NUMERIC(17,2));
		field.setTitle("促销单价");
		field.setKeepValid(true);
		this.f_beginDate = field = this.table.newField(FN_beginDate, TypeFactory.DATE);
		field.setTitle("开始日期");
		field.setKeepValid(true);
		this.f_endDate = field = this.table.newField(FN_endDate, TypeFactory.DATE);
		field.setTitle("结束日期");
		field.setKeepValid(true);
		this.f_state = field = this.table.newField(FN_state, TypeFactory.CHAR(2));
		field.setTitle("状态");
		field.setKeepValid(true);
		this.f_promotionCause = field = this.table.newField(FN_promotionCause, TypeFactory.VARCHAR(500));
		field.setTitle("促销原因");
		this.f_returnCause = field = this.table.newField(FN_returnCause, TypeFactory.VARCHAR(500));
		field.setTitle("退回原因");
		this.f_approvalDate = field = this.table.newField(FN_approvalDate, TypeFactory.DATE);
		field.setTitle("审核时间");
		this.f_deptId = field = this.table.newField(FN_deptId, TypeFactory.GUID);
		field.setTitle("部门GUID");
		field.setKeepValid(true);
		this.table.newIndex("SA_GOODS_PROMOTION_1",f_tenantsId,f_state,f_deptId);
		this.table.newIndex("SA_GOODS_PROMOTION_2",f_tenantsId,f_goodsItemId);
	}

}
