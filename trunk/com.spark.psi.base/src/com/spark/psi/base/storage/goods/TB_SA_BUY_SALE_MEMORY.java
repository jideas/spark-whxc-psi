package com.spark.psi.base.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_BUY_SALE_MEMORY extends TableDeclarator {

	public static final String TABLE_NAME ="SA_BUY_SALE_MEMORY";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_cusproGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_goodsPropertyGuid;
	public final TableFieldDefine f_buySaleTime;
	public final TableFieldDefine f_buySaleCount;
	public final TableFieldDefine f_recentBuySalePrice;
	public final TableFieldDefine f_recentBuySaleCount;
	public final TableFieldDefine f_recentBuySaleDate;
	public final TableFieldDefine f_cusproType;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_cusproGuid ="cusproGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_goodsPropertyGuid ="goodsPropertyGuid";
	public static final String FN_buySaleTime ="buySaleTime";
	public static final String FN_buySaleCount ="buySaleCount";
	public static final String FN_recentBuySalePrice ="recentBuySalePrice";
	public static final String FN_recentBuySaleCount ="recentBuySaleCount";
	public static final String FN_recentBuySaleDate ="recentBuySaleDate";
	public static final String FN_cusproType ="cusproType";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_BUY_SALE_MEMORY() {
		super(TABLE_NAME);
		this.table.setDescription("������ĳһ��Ʒ�Ĳɹ����������");
		this.table.setTitle("�ɹ�/����������м�� ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_cusproGuid = field = this.table.newField(FN_cusproGuid, TypeFactory.GUID);
		field.setTitle("��Ӧ��/�ͻ�GUID");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("��ƷGUID");
		this.f_goodsPropertyGuid = field = this.table.newField(FN_goodsPropertyGuid, TypeFactory.GUID);
		field.setTitle("��Ʒ����GUID");
		this.f_buySaleTime = field = this.table.newField(FN_buySaleTime, TypeFactory.INT);
		field.setTitle("�ɹ�/���۴���");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_buySaleCount = field = this.table.newField(FN_buySaleCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("�ۼƲɹ�/��������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_recentBuySalePrice = field = this.table.newField(FN_recentBuySalePrice, TypeFactory.NUMERIC(17,2));
		field.setTitle("����ɹ�/���ۼ۸�");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_recentBuySaleCount = field = this.table.newField(FN_recentBuySaleCount, TypeFactory.NUMERIC(17,2));
		field.setTitle("����ɹ�/��������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_recentBuySaleDate = field = this.table.newField(FN_recentBuySaleDate, TypeFactory.DATE);
		field.setTitle("����ɹ�/��������");
		this.f_cusproType = field = this.table.newField(FN_cusproType, TypeFactory.CHAR(2));
		field.setDescription("ö�٣�01��Ӧ�̡�02�ͻ���");
		field.setTitle("��Ӧ��/�ͻ����ͱ�ʶ");
		this.table.newIndex("SA_BUY_SALE_MEMORY_ONE",f_tenantsGuid,f_cusproType);
		this.table.newIndex("SA_BUY_SALE_MEMORY_TWO",f_tenantsGuid,f_goodsPropertyGuid,f_cusproType,f_cusproGuid);
	}

}
