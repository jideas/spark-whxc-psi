package com.spark.cms.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_CMS_GOODS extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_GOODS";

	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_originalPrice;
	public final TableFieldDefine f_realPrice;
	public final TableFieldDefine f_vantagesType;
	public final TableFieldDefine f_freeDelivery;
	public final TableFieldDefine f_categoryId1;
	public final TableFieldDefine f_categoryId2;
	public final TableFieldDefine f_categoryId3;
	public final TableFieldDefine f_propertiy1;
	public final TableFieldDefine f_propertiy2;
	public final TableFieldDefine f_propertiy3;
	public final TableFieldDefine f_propertiy4;
	public final TableFieldDefine f_propertiy5;
	public final TableFieldDefine f_picturePath1;
	public final TableFieldDefine f_picturePath2;
	public final TableFieldDefine f_picturePath3;
	public final TableFieldDefine f_saledCount;
	public final TableFieldDefine f_inventoryCount;
	public final TableFieldDefine f_isMostSales;
	public final TableFieldDefine f_isPopular;
	public final TableFieldDefine f_isPublished;
	public final TableFieldDefine f_isVantagesGoods;
	public final TableFieldDefine f_vantagesCost;
	public final TableFieldDefine f_goodsType;
	public final TableFieldDefine f_publishPersonId;
	public final TableFieldDefine f_publishPersonName;
	public final TableFieldDefine f_publishDate;
	public final TableFieldDefine f_isPromotion;
	public final TableFieldDefine f_halfkgPrice;

	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_originalPrice ="originalPrice";
	public static final String FN_realPrice ="realPrice";
	public static final String FN_vantagesType ="vantagesType";
	public static final String FN_freeDelivery ="freeDelivery";
	public static final String FN_categoryId1 ="categoryId1";
	public static final String FN_categoryId2 ="categoryId2";
	public static final String FN_categoryId3 ="categoryId3";
	public static final String FN_propertiy1 ="propertiy1";
	public static final String FN_propertiy2 ="propertiy2";
	public static final String FN_propertiy3 ="propertiy3";
	public static final String FN_propertiy4 ="propertiy4";
	public static final String FN_propertiy5 ="propertiy5";
	public static final String FN_picturePath1 ="picturePath1";
	public static final String FN_picturePath2 ="picturePath2";
	public static final String FN_picturePath3 ="picturePath3";
	public static final String FN_saledCount ="saledCount";
	public static final String FN_inventoryCount ="inventoryCount";
	public static final String FN_isMostSales ="isMostSales";
	public static final String FN_isPopular ="isPopular";
	public static final String FN_isPublished ="isPublished";
	public static final String FN_isVantagesGoods ="isVantagesGoods";
	public static final String FN_vantagesCost ="vantagesCost";
	public static final String FN_goodsType ="goodsType";
	public static final String FN_publishPersonId ="publishPersonId";
	public static final String FN_publishPersonName ="publishPersonName";
	public static final String FN_publishDate ="publishDate";
	public static final String FN_isPromotion ="isPromotion";
	public static final String FN_halfkgPrice ="halfkgPrice";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_GOODS() {
		super(TABLE_NAME);
		this.table.setTitle("网上商品");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("编号");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("条码");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("名称");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(50));
		field.setTitle("规格");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(20));
		field.setTitle("单位");
		this.f_originalPrice = field = this.table.newField(FN_originalPrice, TypeFactory.NUMERIC(10,2));
		field.setTitle("原价");
		this.f_realPrice = field = this.table.newField(FN_realPrice, TypeFactory.NUMERIC(10,2));
		field.setTitle("现价");
		this.f_vantagesType = field = this.table.newField(FN_vantagesType, TypeFactory.CHAR(2));
		field.setTitle("积分类别");
		this.f_freeDelivery = field = this.table.newField(FN_freeDelivery, TypeFactory.BOOLEAN);
		field.setTitle("是否免费送货");
		this.f_categoryId1 = field = this.table.newField(FN_categoryId1, TypeFactory.GUID);
		field.setTitle("一级分类id");
		this.f_categoryId2 = field = this.table.newField(FN_categoryId2, TypeFactory.GUID);
		field.setTitle("二级分类id");
		this.f_categoryId3 = field = this.table.newField(FN_categoryId3, TypeFactory.GUID);
		field.setTitle("三级分类id");
		this.f_propertiy1 = field = this.table.newField(FN_propertiy1, TypeFactory.NVARCHAR(30));
		field.setTitle("属性一");
		this.f_propertiy2 = field = this.table.newField(FN_propertiy2, TypeFactory.NVARCHAR(30));
		field.setTitle("属性二");
		this.f_propertiy3 = field = this.table.newField(FN_propertiy3, TypeFactory.NVARCHAR(30));
		field.setTitle("属性三");
		this.f_propertiy4 = field = this.table.newField(FN_propertiy4, TypeFactory.NVARCHAR(30));
		field.setTitle("属性四");
		this.f_propertiy5 = field = this.table.newField(FN_propertiy5, TypeFactory.NVARCHAR(30));
		field.setTitle("属性五");
		this.f_picturePath1 = field = this.table.newField(FN_picturePath1, TypeFactory.NVARCHAR(300));
		field.setTitle("图片地址1");
		this.f_picturePath2 = field = this.table.newField(FN_picturePath2, TypeFactory.NVARCHAR(300));
		field.setTitle("图片地址2");
		this.f_picturePath3 = field = this.table.newField(FN_picturePath3, TypeFactory.NVARCHAR(300));
		field.setTitle("图片地址3");
		this.f_saledCount = field = this.table.newField(FN_saledCount, TypeFactory.NUMERIC(10,2));
		field.setTitle("已销数量");
		this.f_inventoryCount = field = this.table.newField(FN_inventoryCount, TypeFactory.NUMERIC(10,2));
		field.setTitle("库存数量");
		this.f_isMostSales = field = this.table.newField(FN_isMostSales, TypeFactory.BOOLEAN);
		field.setTitle("是否热卖");
		this.f_isPopular = field = this.table.newField(FN_isPopular, TypeFactory.BOOLEAN);
		field.setTitle("是否人气");
		this.f_isPublished = field = this.table.newField(FN_isPublished, TypeFactory.BOOLEAN);
		field.setTitle("是否已发布");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_isVantagesGoods = field = this.table.newField(FN_isVantagesGoods, TypeFactory.BOOLEAN);
		field.setTitle("是否积分商城商品");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_vantagesCost = field = this.table.newField(FN_vantagesCost, TypeFactory.NUMERIC(17,0));
		field.setTitle("积分价格");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_goodsType = field = this.table.newField(FN_goodsType, TypeFactory.CHAR(2));
		field.setTitle("普通，预订");
		field.setDefault(ConstExpression.builder.expOf("0"));
		this.f_publishPersonId = field = this.table.newField(FN_publishPersonId, TypeFactory.GUID);
		field.setTitle("发布人ID");
		this.f_publishPersonName = field = this.table.newField(FN_publishPersonName, TypeFactory.VARCHAR(30));
		field.setTitle("发布人名称");
		this.f_publishDate = field = this.table.newField(FN_publishDate, TypeFactory.DATE);
		field.setTitle("发布日期");
		this.f_isPromotion = field = this.table.newField(FN_isPromotion, TypeFactory.BOOLEAN);
		field.setTitle("是否有促销");
		field.setDefault(ConstExpression.builder.expOf(false));
		this.f_halfkgPrice = field = this.table.newField(FN_halfkgPrice, TypeFactory.NUMERIC(17,2));
		field.setTitle("元/斤");
		field.setDefault(ConstExpression.builder.expOf(0.0));
	}

}
