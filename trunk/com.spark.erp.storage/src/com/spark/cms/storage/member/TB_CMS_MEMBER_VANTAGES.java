package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_MEMBER_VANTAGES extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER_VANTAGES";

	public final TableFieldDefine f_memberID;
	public final TableFieldDefine f_vType;
	public final TableFieldDefine f_relaBillsId;
	public final TableFieldDefine f_relaBillsNo;
	public final TableFieldDefine f_vantages;
	public final TableFieldDefine f_occurDate;
	public final TableFieldDefine f_modifyPerson;
	public final TableFieldDefine f_modifyPersonId;

	public static final String FN_memberID ="memberID";
	public static final String FN_vType ="vType";
	public static final String FN_relaBillsId ="relaBillsId";
	public static final String FN_relaBillsNo ="relaBillsNo";
	public static final String FN_vantages ="vantages";
	public static final String FN_occurDate ="occurDate";
	public static final String FN_modifyPerson ="modifyPerson";
	public static final String FN_modifyPersonId ="modifyPersonId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_MEMBER_VANTAGES() {
		super(TABLE_NAME);
		this.table.setTitle("积分记录");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_memberID = field = this.table.newField(FN_memberID, TypeFactory.GUID);
		field.setTitle("会员id");
		this.f_vType = field = this.table.newField(FN_vType, TypeFactory.CHAR(2));
		field.setTitle("积分类型");
		this.f_relaBillsId = field = this.table.newField(FN_relaBillsId, TypeFactory.GUID);
		field.setTitle("面值卡、订单id");
		this.f_relaBillsNo = field = this.table.newField(FN_relaBillsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("相关单号");
		this.f_vantages = field = this.table.newField(FN_vantages, TypeFactory.NUMERIC(10,2));
		field.setTitle("积分");
		this.f_occurDate = field = this.table.newField(FN_occurDate, TypeFactory.DATE);
		field.setTitle("发生日期");
		this.f_modifyPerson = this.table.newField(FN_modifyPerson, TypeFactory.NVARCHAR(50));
		this.f_modifyPersonId = this.table.newField(FN_modifyPersonId, TypeFactory.GUID);
	}

}
