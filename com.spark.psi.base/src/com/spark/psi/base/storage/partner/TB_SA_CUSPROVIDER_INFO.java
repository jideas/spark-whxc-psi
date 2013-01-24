package com.spark.psi.base.storage.partner;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.table.IndexDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_CUSPROVIDER_INFO extends TableDeclarator {

	public static final String TABLE_NAME ="SA_CUSPROVIDER_INFO";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_cusproNo;
	public final TableFieldDefine f_cusproName;
	public final TableFieldDefine f_cusproShortName;
	public final TableFieldDefine f_cusproType;
	public final TableFieldDefine f_cusproSour;
	public final TableFieldDefine f_cusproGrd;
	public final TableFieldDefine f_industy;
	public final TableFieldDefine f_scale;
	public final TableFieldDefine f_account;
	public final TableFieldDefine f_cusproBank;
	public final TableFieldDefine f_accountHolder;
	public final TableFieldDefine f_creditline;
	public final TableFieldDefine f_creditLinePerson;
	public final TableFieldDefine f_workTel;
	public final TableFieldDefine f_fax;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_addressDetail;
	public final TableFieldDefine f_taxationNo;
	public final TableFieldDefine f_netAddress;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_postCode;
	public final TableFieldDefine f_takePayBalance;
	public final TableFieldDefine f_initPayBalance;
	public final TableFieldDefine f_busPerson;
	public final TableFieldDefine f_accountPeriod;
	public final TableFieldDefine f_accountRemind;
	public final TableFieldDefine f_isReflag;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_cusproNo ="cusproNo";
	public static final String FN_cusproName ="cusproName";
	public static final String FN_cusproShortName ="cusproShortName";
	public static final String FN_cusproType ="cusproType";
	public static final String FN_cusproSour ="cusproSour";
	public static final String FN_cusproGrd ="cusproGrd";
	public static final String FN_industy ="industy";
	public static final String FN_scale ="scale";
	public static final String FN_account ="account";
	public static final String FN_cusproBank ="cusproBank";
	public static final String FN_accountHolder ="accountHolder";
	public static final String FN_creditline ="creditline";
	public static final String FN_creditLinePerson ="creditLinePerson";
	public static final String FN_workTel ="workTel";
	public static final String FN_fax ="fax";
	public static final String FN_province ="province";
	public static final String FN_city ="city";
	public static final String FN_address ="address";
	public static final String FN_addressDetail ="addressDetail";
	public static final String FN_taxationNo ="taxationNo";
	public static final String FN_netAddress ="netAddress";
	public static final String FN_remark ="remark";
	public static final String FN_postCode ="postCode";
	public static final String FN_takePayBalance ="takePayBalance";
	public static final String FN_initPayBalance ="initPayBalance";
	public static final String FN_busPerson ="busPerson";
	public static final String FN_accountPeriod ="accountPeriod";
	public static final String FN_accountRemind ="accountRemind";
	public static final String FN_isReflag ="isReflag";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_CUSPROVIDER_INFO() {
		super(TABLE_NAME);
		this.table.setDescription("存放客户/供应商的相关信息");
		this.table.setTitle("客户/供应商管理");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		field.setKeepValid(true);
		this.f_cusproNo = field = this.table.newField(FN_cusproNo, TypeFactory.NVARCHAR(20));
		field.setTitle("客户供应商编号");
		this.f_cusproName = field = this.table.newField(FN_cusproName, TypeFactory.NVARCHAR(100));
		field.setTitle("客户供应商全称");
		field.setKeepValid(true);
		this.f_cusproShortName = field = this.table.newField(FN_cusproShortName, TypeFactory.VARCHAR(20));
		field.setTitle("客户供应商简称");
		field.setKeepValid(true);
		this.f_cusproType = field = this.table.newField(FN_cusproType, TypeFactory.CHAR(2));
		field.setTitle("客户供应商类型");
		field.setKeepValid(true);
		this.f_cusproSour = field = this.table.newField(FN_cusproSour, TypeFactory.CHAR(2));
		field.setTitle("客户供应商来源");
		this.f_cusproGrd = field = this.table.newField(FN_cusproGrd, TypeFactory.CHAR(2));
		field.setTitle("客户供应商分类");
		this.f_industy = field = this.table.newField(FN_industy, TypeFactory.CHAR(2));
		field.setTitle("所属行业");
		this.f_scale = field = this.table.newField(FN_scale, TypeFactory.CHAR(10));
		field.setTitle("企业规模");
		this.f_account = field = this.table.newField(FN_account, TypeFactory.NVARCHAR(30));
		field.setTitle("银行帐号");
		this.f_cusproBank = field = this.table.newField(FN_cusproBank, TypeFactory.NVARCHAR(100));
		field.setTitle("银行1");
		this.f_accountHolder = field = this.table.newField(FN_accountHolder, TypeFactory.NVARCHAR(50));
		field.setTitle("开户名称");
		this.f_creditline = field = this.table.newField(FN_creditline, TypeFactory.NUMERIC(17,2));
		field.setTitle("信用额度");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_creditLinePerson = field = this.table.newField(FN_creditLinePerson, TypeFactory.GUID);
		field.setTitle("信用额度调整人");
		this.f_workTel = field = this.table.newField(FN_workTel, TypeFactory.NVARCHAR(20));
		field.setTitle("工作电话");
		this.f_fax = field = this.table.newField(FN_fax, TypeFactory.NVARCHAR(20));
		field.setTitle("传真");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.NVARCHAR(20));
		field.setTitle("省份");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.NVARCHAR(20));
		field.setTitle("城市");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(100));
		field.setTitle("地址");
		this.f_addressDetail = field = this.table.newField(FN_addressDetail, TypeFactory.VARCHAR(100));
		field.setTitle("详细地址");
		this.f_taxationNo = field = this.table.newField(FN_taxationNo, TypeFactory.NVARCHAR(100));
		field.setTitle("增值税号");
		this.f_netAddress = field = this.table.newField(FN_netAddress, TypeFactory.NVARCHAR(100));
		field.setTitle("网址");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_postCode = field = this.table.newField(FN_postCode, TypeFactory.NVARCHAR(10));
		field.setTitle("邮编");
		this.f_takePayBalance = field = this.table.newField(FN_takePayBalance, TypeFactory.NUMERIC(17,2));
		field.setTitle("应收/应付余额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_initPayBalance = field = this.table.newField(FN_initPayBalance, TypeFactory.NUMERIC(17,2));
		field.setTitle("初始化应收/应付余额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_busPerson = field = this.table.newField(FN_busPerson, TypeFactory.GUID);
		field.setTitle("业务负责人");
		this.f_accountPeriod = field = this.table.newField(FN_accountPeriod, TypeFactory.INT);
		field.setTitle("帐期");
		this.f_accountRemind = field = this.table.newField(FN_accountRemind, TypeFactory.INT);
		field.setTitle("帐期提醒");
		this.f_isReflag = field = this.table.newField(FN_isReflag, TypeFactory.BOOLEAN);
		field.setTitle("关联标志");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(32));
		field.setTitle("创建人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		IndexDeclare index;
		index = this.table.newIndex("INDEX_PARTNER_NAME",f_tenantsGuid,f_cusproType,f_cusproName);
		index.setUnique(true);
		index = this.table.newIndex("INDEX_PARTNER_NAME2",f_tenantsGuid,f_cusproType,f_cusproShortName);
		index.setUnique(true);
	}

}
