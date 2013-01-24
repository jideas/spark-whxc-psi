package com.spark.psi.storage.base.station;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_Base_Station extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_Station";

	public final TableFieldDefine f_stationNo;
	public final TableFieldDefine f_stationName;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_shortName;
	public final TableFieldDefine f_telephone;
	public final TableFieldDefine f_fax;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_town;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_managerId;
	public final TableFieldDefine f_deptId;
	public final TableFieldDefine f_manager;
	public final TableFieldDefine f_managerPersonId;
	public final TableFieldDefine f_managerPhone;
	public final TableFieldDefine f_managerEmail;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_isStop;

	public static final String FN_stationNo ="stationNo";
	public static final String FN_stationName ="stationName";
	public static final String FN_namePY ="namePY";
	public static final String FN_shortName ="shortName";
	public static final String FN_telephone ="telephone";
	public static final String FN_fax ="fax";
	public static final String FN_remark ="remark";
	public static final String FN_town ="town";
	public static final String FN_province ="province";
	public static final String FN_city ="city";
	public static final String FN_address ="address";
	public static final String FN_managerId ="managerId";
	public static final String FN_deptId ="deptId";
	public static final String FN_manager ="manager";
	public static final String FN_managerPersonId ="managerPersonId";
	public static final String FN_managerPhone ="managerPhone";
	public static final String FN_managerEmail ="managerEmail";
	public static final String FN_createDate ="createDate";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_isStop ="isStop";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_Station() {
		super(TABLE_NAME);
		this.table.setTitle("站点表");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_stationNo = field = this.table.newField(FN_stationNo, TypeFactory.NVARCHAR(30));
		field.setTitle("站点编号");
		this.f_stationName = field = this.table.newField(FN_stationName, TypeFactory.NVARCHAR(50));
		field.setTitle("站点名称");
		this.f_namePY = field = this.table.newField(FN_namePY, TypeFactory.NVARCHAR(50));
		field.setTitle("名称拼音");
		this.f_shortName = field = this.table.newField(FN_shortName, TypeFactory.NVARCHAR(20));
		field.setTitle("站点简称");
		this.f_telephone = field = this.table.newField(FN_telephone, TypeFactory.NVARCHAR(20));
		field.setTitle("电话");
		this.f_fax = field = this.table.newField(FN_fax, TypeFactory.NVARCHAR(20));
		field.setTitle("传真");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_town = field = this.table.newField(FN_town, TypeFactory.CHAR(20));
		field.setTitle("地区");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.CHAR(20));
		field.setTitle("省");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.CHAR(20));
		field.setTitle("市");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(200));
		field.setTitle("详细地址");
		this.f_managerId = field = this.table.newField(FN_managerId, TypeFactory.GUID);
		field.setTitle("负责人id");
		this.f_deptId = this.table.newField(FN_deptId, TypeFactory.GUID);
		this.f_manager = field = this.table.newField(FN_manager, TypeFactory.NVARCHAR(20));
		field.setTitle("负责人");
		this.f_managerPersonId = field = this.table.newField(FN_managerPersonId, TypeFactory.NVARCHAR(30));
		field.setTitle("负责人身份证号");
		this.f_managerPhone = field = this.table.newField(FN_managerPhone, TypeFactory.NVARCHAR(20));
		field.setTitle("负责人电话");
		this.f_managerEmail = field = this.table.newField(FN_managerEmail, TypeFactory.NVARCHAR(50));
		field.setTitle("负责人邮箱");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人");
		this.f_isStop = field = this.table.newField(FN_isStop, TypeFactory.BOOLEAN);
		field.setKeepValid(true);
		field.setDefault(ConstExpression.builder.expOf(false));
	}

}
