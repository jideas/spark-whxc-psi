package com.spark.psi.storage.base.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_Bom extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_Bom";

	public final TableFieldDefine f_bomNo;
	public final TableFieldDefine f_goodsItemId;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_approvePerson;
	public final TableFieldDefine f_approvePersonName;
	public final TableFieldDefine f_approveDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_remark;

	public static final String FN_bomNo ="bomNo";
	public static final String FN_goodsItemId ="goodsItemId";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_createDate ="createDate";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_approvePerson ="approvePerson";
	public static final String FN_approvePersonName ="approvePersonName";
	public static final String FN_approveDate ="approveDate";
	public static final String FN_status ="status";
	public static final String FN_remark ="remark";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_Bom() {
		super(TABLE_NAME);
		this.table.setTitle("bom表");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_bomNo = field = this.table.newField(FN_bomNo, TypeFactory.NVARCHAR(30));
		field.setTitle("bom单号");
		this.f_goodsItemId = field = this.table.newField(FN_goodsItemId, TypeFactory.GUID);
		field.setTitle("商品条目id");
		this.f_goodsNo = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("定制日期");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人");
		this.f_approvePerson = field = this.table.newField(FN_approvePerson, TypeFactory.GUID);
		field.setTitle("审核人");
		this.f_approvePersonName = field = this.table.newField(FN_approvePersonName, TypeFactory.NVARCHAR(30));
		field.setTitle("审核人姓名");
		this.f_approveDate = field = this.table.newField(FN_approveDate, TypeFactory.DATE);
		field.setTitle("审核日期");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("状态");
		this.f_remark = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
	}

}
