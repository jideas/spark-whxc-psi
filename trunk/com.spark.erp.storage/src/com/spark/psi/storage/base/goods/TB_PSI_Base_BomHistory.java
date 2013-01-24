package com.spark.psi.storage.base.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_BomHistory extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_BomHistory";

	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_bomId;
	public final TableFieldDefine f_bomNo;
	public final TableFieldDefine f_bomCreator;
	public final TableFieldDefine f_bomApprovor;
	public final TableFieldDefine f_ineffector;
	public final TableFieldDefine f_outeffector;
	public final TableFieldDefine f_ineffectDate;
	public final TableFieldDefine f_outeffectDate;

	public static final String FN_goodsId ="goodsId";
	public static final String FN_bomId ="bomId";
	public static final String FN_bomNo ="bomNo";
	public static final String FN_bomCreator ="bomCreator";
	public static final String FN_bomApprovor ="bomApprovor";
	public static final String FN_ineffector ="ineffector";
	public static final String FN_outeffector ="outeffector";
	public static final String FN_ineffectDate ="ineffectDate";
	public static final String FN_outeffectDate ="outeffectDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_BomHistory() {
		super(TABLE_NAME);
		this.table.setTitle("Bom生效记录");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品条目id");
		this.f_bomId = field = this.table.newField(FN_bomId, TypeFactory.GUID);
		field.setTitle("bom表id");
		this.f_bomNo = field = this.table.newField(FN_bomNo, TypeFactory.NVARCHAR(30));
		field.setTitle("bom编号");
		this.f_bomCreator = field = this.table.newField(FN_bomCreator, TypeFactory.NVARCHAR(30));
		field.setTitle("bom创建人");
		this.f_bomApprovor = field = this.table.newField(FN_bomApprovor, TypeFactory.NVARCHAR(30));
		field.setTitle("bom审批人");
		this.f_ineffector = field = this.table.newField(FN_ineffector, TypeFactory.NVARCHAR(30));
		field.setTitle("bom生效操作人");
		this.f_outeffector = field = this.table.newField(FN_outeffector, TypeFactory.NVARCHAR(30));
		field.setTitle("bom失效操作人");
		this.f_ineffectDate = field = this.table.newField(FN_ineffectDate, TypeFactory.DATE);
		field.setTitle("bom生效日期");
		this.f_outeffectDate = field = this.table.newField(FN_outeffectDate, TypeFactory.DATE);
		field.setTitle("bom失效日期");
	}

}
