package com.spark.psi.base.storage.tenant;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_CLIENT_COMPANYINFO extends TableDeclarator {

	public static final String TABLE_NAME ="SA_CLIENT_COMPANYINFO";

	public final TableFieldDefine f_companyName;
	public final TableFieldDefine f_companyShortName;
	public final TableFieldDefine f_systemName;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_area;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_postcode;
	public final TableFieldDefine f_phone;
	public final TableFieldDefine f_fax;
	public final TableFieldDefine f_logo;

	public static final String FN_companyName ="companyName";
	public static final String FN_companyShortName ="companyShortName";
	public static final String FN_systemName ="systemName";
	public static final String FN_province ="province";
	public static final String FN_city ="city";
	public static final String FN_area ="area";
	public static final String FN_address ="address";
	public static final String FN_postcode ="postcode";
	public static final String FN_phone ="phone";
	public static final String FN_fax ="fax";
	public static final String FN_logo ="logo";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_CLIENT_COMPANYINFO() {
		super(TABLE_NAME);
		this.table.setTitle("企业信息维护表 ");
		TableFieldDeclare field;
		this.f_companyName = field = this.table.newField(FN_companyName, TypeFactory.NVARCHAR(50));
		field.setTitle("企业名称");
		this.f_companyShortName = field = this.table.newField(FN_companyShortName, TypeFactory.NVARCHAR(25));
		field.setTitle("企业简称");
		this.f_systemName = field = this.table.newField(FN_systemName, TypeFactory.NVARCHAR(20));
		field.setTitle("系统名称");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.NVARCHAR(12));
		field.setTitle("省");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.NVARCHAR(12));
		field.setTitle("市");
		this.f_area = field = this.table.newField(FN_area, TypeFactory.NVARCHAR(12));
		field.setTitle("区县");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(100));
		field.setTitle("详细地址");
		this.f_postcode = field = this.table.newField(FN_postcode, TypeFactory.NVARCHAR(6));
		field.setTitle("邮编");
		this.f_phone = field = this.table.newField(FN_phone, TypeFactory.NVARCHAR(20));
		field.setTitle("电话");
		this.f_fax = field = this.table.newField(FN_fax, TypeFactory.NVARCHAR(20));
		field.setTitle("传真");
		this.f_logo = field = this.table.newField(FN_logo, TypeFactory.BLOB);
		field.setTitle("logo");
	}

}
