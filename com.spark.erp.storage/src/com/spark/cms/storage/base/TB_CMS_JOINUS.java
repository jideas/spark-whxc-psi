package com.spark.cms.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_CMS_JOINUS extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_JOINUS";

	public final TableFieldDefine f_joinerName;
	public final TableFieldDefine f_joinerSex;
	public final TableFieldDefine f_joinerAge;
	public final TableFieldDefine f_maritalstatus;
	public final TableFieldDefine f_birthplace;
	public final TableFieldDefine f_culturalLevel;
	public final TableFieldDefine f_idCardNo;
	public final TableFieldDefine f_email;
	public final TableFieldDefine f_telephone;
	public final TableFieldDefine f_fax;
	public final TableFieldDefine f_qq;
	public final TableFieldDefine f_msn;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_livingAddress;
	public final TableFieldDefine f_storeAddress;
	public final TableFieldDefine f_storeLocation;
	public final TableFieldDefine f_rentOfYear;
	public final TableFieldDefine f_sumCost;
	public final TableFieldDefine f_salesEmployee;
	public final TableFieldDefine f_businessWay;
	public final TableFieldDefine f_fund;
	public final TableFieldDefine f_funding;
	public final TableFieldDefine f_askStartDate;
	public final TableFieldDefine f_competitionSituation;
	public final TableFieldDefine f_joinusReason;
	public final TableFieldDefine f_experience;
	public final TableFieldDefine f_dispositionAndFamily;
	public final TableFieldDefine f_riskAndHope;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_opered;

	public static final String FN_joinerName ="joinerName";
	public static final String FN_joinerSex ="joinerSex";
	public static final String FN_joinerAge ="joinerAge";
	public static final String FN_maritalstatus ="maritalstatus";
	public static final String FN_birthplace ="birthplace";
	public static final String FN_culturalLevel ="culturalLevel";
	public static final String FN_idCardNo ="idCardNo";
	public static final String FN_email ="email";
	public static final String FN_telephone ="telephone";
	public static final String FN_fax ="fax";
	public static final String FN_qq ="qq";
	public static final String FN_msn ="msn";
	public static final String FN_mobile ="mobile";
	public static final String FN_livingAddress ="livingAddress";
	public static final String FN_storeAddress ="storeAddress";
	public static final String FN_storeLocation ="storeLocation";
	public static final String FN_rentOfYear ="rentOfYear";
	public static final String FN_sumCost ="sumCost";
	public static final String FN_salesEmployee ="salesEmployee";
	public static final String FN_businessWay ="businessWay";
	public static final String FN_fund ="fund";
	public static final String FN_funding ="funding";
	public static final String FN_askStartDate ="askStartDate";
	public static final String FN_competitionSituation ="competitionSituation";
	public static final String FN_joinusReason ="joinusReason";
	public static final String FN_experience ="experience";
	public static final String FN_dispositionAndFamily ="dispositionAndFamily";
	public static final String FN_riskAndHope ="riskAndHope";
	public static final String FN_createDate ="createDate";
	public static final String FN_opered ="opered";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_JOINUS() {
		super(TABLE_NAME);
		this.table.setTitle("加盟申请表");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_joinerName = field = this.table.newField(FN_joinerName, TypeFactory.NVARCHAR(30));
		field.setTitle("姓名");
		this.f_joinerSex = field = this.table.newField(FN_joinerSex, TypeFactory.NVARCHAR(10));
		field.setTitle("性别");
		this.f_joinerAge = field = this.table.newField(FN_joinerAge, TypeFactory.NVARCHAR(10));
		field.setTitle("年龄");
		this.f_maritalstatus = field = this.table.newField(FN_maritalstatus, TypeFactory.NVARCHAR(10));
		field.setTitle("婚姻状况");
		this.f_birthplace = field = this.table.newField(FN_birthplace, TypeFactory.NVARCHAR(20));
		field.setTitle("籍贯");
		this.f_culturalLevel = field = this.table.newField(FN_culturalLevel, TypeFactory.NVARCHAR(20));
		field.setTitle("文化程度");
		this.f_idCardNo = field = this.table.newField(FN_idCardNo, TypeFactory.NVARCHAR(50));
		field.setTitle("身份证号");
		this.f_email = field = this.table.newField(FN_email, TypeFactory.NVARCHAR(50));
		field.setTitle("email");
		this.f_telephone = field = this.table.newField(FN_telephone, TypeFactory.NVARCHAR(20));
		field.setTitle("联系电话");
		this.f_fax = field = this.table.newField(FN_fax, TypeFactory.NVARCHAR(20));
		field.setTitle("传真");
		this.f_qq = field = this.table.newField(FN_qq, TypeFactory.NVARCHAR(20));
		field.setTitle("qq");
		this.f_msn = field = this.table.newField(FN_msn, TypeFactory.NVARCHAR(50));
		field.setTitle("msn");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.NVARCHAR(20));
		field.setTitle("手机号码");
		this.f_livingAddress = field = this.table.newField(FN_livingAddress, TypeFactory.NVARCHAR(500));
		field.setTitle("居住地址");
		this.f_storeAddress = field = this.table.newField(FN_storeAddress, TypeFactory.NVARCHAR(500));
		field.setTitle("详细店址");
		this.f_storeLocation = field = this.table.newField(FN_storeLocation, TypeFactory.NVARCHAR(20));
		field.setTitle("所处商业街地段");
		this.f_rentOfYear = field = this.table.newField(FN_rentOfYear, TypeFactory.NVARCHAR(20));
		field.setTitle("全年租金");
		this.f_sumCost = field = this.table.newField(FN_sumCost, TypeFactory.NVARCHAR(50));
		field.setTitle("费用合计");
		this.f_salesEmployee = field = this.table.newField(FN_salesEmployee, TypeFactory.NVARCHAR(50));
		field.setTitle("销售人员");
		this.f_businessWay = field = this.table.newField(FN_businessWay, TypeFactory.NVARCHAR(20));
		field.setTitle("经营方式");
		this.f_fund = field = this.table.newField(FN_fund, TypeFactory.NVARCHAR(50));
		field.setTitle("投融资金");
		this.f_funding = field = this.table.newField(FN_funding, TypeFactory.NVARCHAR(50));
		field.setTitle("运作资金");
		this.f_askStartDate = field = this.table.newField(FN_askStartDate, TypeFactory.NVARCHAR(50));
		field.setTitle("要求开业时间");
		this.f_competitionSituation = field = this.table.newField(FN_competitionSituation, TypeFactory.NVARCHAR(1000));
		field.setTitle("竞争情况");
		this.f_joinusReason = field = this.table.newField(FN_joinusReason, TypeFactory.NVARCHAR(1000));
		field.setTitle("加盟原因");
		this.f_experience = field = this.table.newField(FN_experience, TypeFactory.NVARCHAR(1000));
		field.setTitle("个人经验");
		this.f_dispositionAndFamily = field = this.table.newField(FN_dispositionAndFamily, TypeFactory.NVARCHAR(1000));
		field.setTitle("性格和家庭");
		this.f_riskAndHope = field = this.table.newField(FN_riskAndHope, TypeFactory.NVARCHAR(1000));
		field.setTitle("风险与期待");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.LONG);
		field.setTitle("申请时间");
		this.f_opered = field = this.table.newField(FN_opered, TypeFactory.BOOLEAN);
		field.setTitle("是否已处理");
		field.setDefault(ConstExpression.builder.expOf(false));
	}

}
