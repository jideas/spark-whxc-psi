package com.spark.psi.base.storage.partner;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_CONTACKBOOK extends TableDeclarator {

	public static final String TABLE_NAME ="SA_CONTACKBOOK";

	public final TableFieldDefine f_tenantsGUID;
	public final TableFieldDefine f_name;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_main;
	public final TableFieldDefine f_sex;
	public final TableFieldDefine f_nickname;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_phone;
	public final TableFieldDefine f_email;
	public final TableFieldDefine f_company;
	public final TableFieldDefine f_companyPY;
	public final TableFieldDefine f_department;
	public final TableFieldDefine f_job;
	public final TableFieldDefine f_jobPY;
	public final TableFieldDefine f_qq;
	public final TableFieldDefine f_msn;
	public final TableFieldDefine f_aliim;
	public final TableFieldDefine f_birth;
	public final TableFieldDefine f_fav;
	public final TableFieldDefine f_comments;
	public final TableFieldDefine f_type;
	public final TableFieldDefine f_createPersonGUID;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_cusproGuid;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_district;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_postCode;
	public final TableFieldDefine f_lastDate;

	public static final String FN_tenantsGUID ="tenantsGUID";
	public static final String FN_name ="name";
	public static final String FN_namePY ="namePY";
	public static final String FN_main ="main";
	public static final String FN_sex ="sex";
	public static final String FN_nickname ="nickname";
	public static final String FN_mobile ="mobile";
	public static final String FN_phone ="phone";
	public static final String FN_email ="email";
	public static final String FN_company ="company";
	public static final String FN_companyPY ="companyPY";
	public static final String FN_department ="department";
	public static final String FN_job ="job";
	public static final String FN_jobPY ="jobPY";
	public static final String FN_qq ="qq";
	public static final String FN_msn ="msn";
	public static final String FN_aliim ="aliim";
	public static final String FN_birth ="birth";
	public static final String FN_fav ="fav";
	public static final String FN_comments ="comments";
	public static final String FN_type ="type";
	public static final String FN_createPersonGUID ="createPersonGUID";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_cusproGuid ="cusproGuid";
	public static final String FN_province ="province";
	public static final String FN_city ="city";
	public static final String FN_district ="district";
	public static final String FN_address ="address";
	public static final String FN_postCode ="postCode";
	public static final String FN_lastDate ="lastDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_CONTACKBOOK() {
		super(TABLE_NAME);
		this.table.setDescription("通讯录");
		TableFieldDeclare field;
		this.f_tenantsGUID = field = this.table.newField(FN_tenantsGUID, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_name = field = this.table.newField(FN_name, TypeFactory.NVARCHAR(40));
		field.setTitle("姓名");
		this.f_namePY = this.table.newField(FN_namePY, TypeFactory.VARCHAR(40));
		this.f_main = field = this.table.newField(FN_main, TypeFactory.BOOLEAN);
		field.setTitle("主联系人");
		this.f_sex = field = this.table.newField(FN_sex, TypeFactory.CHAR(2));
		field.setTitle("性别");
		this.f_nickname = field = this.table.newField(FN_nickname, TypeFactory.NVARCHAR(40));
		field.setTitle("尊称");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.NVARCHAR(20));
		field.setTitle("手机");
		this.f_phone = field = this.table.newField(FN_phone, TypeFactory.NVARCHAR(20));
		field.setTitle("固话");
		this.f_email = field = this.table.newField(FN_email, TypeFactory.NVARCHAR(50));
		field.setTitle("邮箱");
		this.f_company = field = this.table.newField(FN_company, TypeFactory.NVARCHAR(100));
		field.setTitle("公司");
		this.f_companyPY = this.table.newField(FN_companyPY, TypeFactory.NVARCHAR(100));
		this.f_department = field = this.table.newField(FN_department, TypeFactory.NVARCHAR(30));
		field.setTitle("部门");
		this.f_job = field = this.table.newField(FN_job, TypeFactory.NVARCHAR(30));
		field.setTitle("职位");
		this.f_jobPY = this.table.newField(FN_jobPY, TypeFactory.NVARCHAR(30));
		this.f_qq = field = this.table.newField(FN_qq, TypeFactory.NVARCHAR(15));
		field.setTitle("qq");
		this.f_msn = field = this.table.newField(FN_msn, TypeFactory.NVARCHAR(30));
		field.setTitle("msn");
		this.f_aliim = field = this.table.newField(FN_aliim, TypeFactory.NVARCHAR(50));
		field.setTitle("旺旺");
		this.f_birth = field = this.table.newField(FN_birth, TypeFactory.DATE);
		field.setTitle("生日");
		this.f_fav = field = this.table.newField(FN_fav, TypeFactory.NVARCHAR(100));
		field.setTitle("爱好");
		this.f_comments = field = this.table.newField(FN_comments, TypeFactory.NVARCHAR(200));
		field.setTitle("备注");
		this.f_type = field = this.table.newField(FN_type, TypeFactory.CHAR(2));
		field.setTitle("类型");
		this.f_createPersonGUID = field = this.table.newField(FN_createPersonGUID, TypeFactory.GUID);
		field.setTitle("创建人GUID");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人明文");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_cusproGuid = field = this.table.newField(FN_cusproGuid, TypeFactory.GUID);
		field.setTitle("客户供应商编号");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.NVARCHAR(20));
		field.setTitle("省份");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.NVARCHAR(20));
		field.setTitle("城市");
		this.f_district = field = this.table.newField(FN_district, TypeFactory.NVARCHAR(100));
		field.setTitle("地址");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(100));
		field.setTitle("详细地址");
		this.f_postCode = field = this.table.newField(FN_postCode, TypeFactory.NVARCHAR(10));
		field.setTitle("邮编");
		this.f_lastDate = field = this.table.newField(FN_lastDate, TypeFactory.DATE);
		field.setTitle("最近使用日期");
		this.table.newIndex("INDEX_CONTACKBOOK",f_cusproGuid,f_type);
		this.table.newIndex("INDEX_CONTACKBOOK2",f_tenantsGUID,f_type,f_name,f_namePY);
	}

}
