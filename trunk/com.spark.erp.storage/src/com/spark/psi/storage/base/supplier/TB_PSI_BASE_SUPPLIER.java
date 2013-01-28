package com.spark.psi.storage.base.supplier;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_BASE_SUPPLIER extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_BASE_SUPPLIER";

	public final TableFieldDefine f_supplierName;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_shortName;
	public final TableFieldDefine f_supplierNo;
	public final TableFieldDefine f_supplierType;
	public final TableFieldDefine f_vatNo;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_taxRate;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_town;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_postcode;
	public final TableFieldDefine f_totalPurchaseAmount;
	public final TableFieldDefine f_lastPurchaseDate;
	public final TableFieldDefine f_totalPurchaseTimes;
	public final TableFieldDefine f_noticeAddress;
	public final TableFieldDefine f_noticePostcode;
	public final TableFieldDefine f_workTel;
	public final TableFieldDefine f_fax;
	public final TableFieldDefine f_accountPeriod;
	public final TableFieldDefine f_accountRemind;
	public final TableFieldDefine f_linkmanName;
	public final TableFieldDefine f_linkmanSuffix;
	public final TableFieldDefine f_linkmanNamePY;
	public final TableFieldDefine f_linkmanTel;
	public final TableFieldDefine f_linkmanMobile;
	public final TableFieldDefine f_linkmanEmail;
	public final TableFieldDefine f_canDelete;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_creditAmount;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_cooperation;

	public static final String FN_supplierName ="supplierName";
	public static final String FN_namePY ="namePY";
	public static final String FN_shortName ="shortName";
	public static final String FN_supplierNo ="supplierNo";
	public static final String FN_supplierType ="supplierType";
	public static final String FN_vatNo ="vatNo";
	public static final String FN_province ="province";
	public static final String FN_taxRate ="taxRate";
	public static final String FN_city ="city";
	public static final String FN_town ="town";
	public static final String FN_address ="address";
	public static final String FN_remark ="remark";
	public static final String FN_postcode ="postcode";
	public static final String FN_totalPurchaseAmount ="totalPurchaseAmount";
	public static final String FN_lastPurchaseDate ="lastPurchaseDate";
	public static final String FN_totalPurchaseTimes ="totalPurchaseTimes";
	public static final String FN_noticeAddress ="noticeAddress";
	public static final String FN_noticePostcode ="noticePostcode";
	public static final String FN_workTel ="workTel";
	public static final String FN_fax ="fax";
	public static final String FN_accountPeriod ="accountPeriod";
	public static final String FN_accountRemind ="accountRemind";
	public static final String FN_linkmanName ="linkmanName";
	public static final String FN_linkmanSuffix ="linkmanSuffix";
	public static final String FN_linkmanNamePY ="linkmanNamePY";
	public static final String FN_linkmanTel ="linkmanTel";
	public static final String FN_linkmanMobile ="linkmanMobile";
	public static final String FN_linkmanEmail ="linkmanEmail";
	public static final String FN_canDelete ="canDelete";
	public static final String FN_status ="status";
	public static final String FN_creditAmount ="creditAmount";
	public static final String FN_createDate ="createDate";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_cooperation ="cooperation";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_BASE_SUPPLIER() {
		super(TABLE_NAME);
		this.table.setTitle("��Ӧ����Ϣ");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_supplierName = field = this.table.newField(FN_supplierName, TypeFactory.NVARCHAR(50));
		field.setTitle("ȫ��");
		this.f_namePY = field = this.table.newField(FN_namePY, TypeFactory.NVARCHAR(50));
		field.setTitle("ȫ��ƴ��");
		this.f_shortName = field = this.table.newField(FN_shortName, TypeFactory.NVARCHAR(12));
		field.setTitle("���");
		this.f_supplierNo = field = this.table.newField(FN_supplierNo, TypeFactory.NVARCHAR(25));
		field.setTitle("���");
		this.f_supplierType = field = this.table.newField(FN_supplierType, TypeFactory.CHAR(2));
		field.setTitle("����");
		this.f_vatNo = field = this.table.newField(FN_vatNo, TypeFactory.NVARCHAR(25));
		field.setTitle("��ֵ˰��");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.NVARCHAR(20));
		field.setTitle("ʡ��");
		this.f_taxRate = field = this.table.newField(FN_taxRate, TypeFactory.NUMERIC(17,4));
		field.setTitle("˰��");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.NVARCHAR(20));
		field.setTitle("����");
		this.f_town = field = this.table.newField(FN_town, TypeFactory.NVARCHAR(100));
		field.setTitle("����");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(100));
		field.setTitle("��ַ");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_postcode = field = this.table.newField(FN_postcode, TypeFactory.NVARCHAR(10));
		field.setTitle("�ʱ�");
		this.f_totalPurchaseAmount = field = this.table.newField(FN_totalPurchaseAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ɹ��ܶ�");
		this.f_lastPurchaseDate = field = this.table.newField(FN_lastPurchaseDate, TypeFactory.DATE);
		field.setTitle("����ɹ�����");
		this.f_totalPurchaseTimes = field = this.table.newField(FN_totalPurchaseTimes, TypeFactory.INT);
		field.setTitle("�ɹ�����");
		this.f_noticeAddress = field = this.table.newField(FN_noticeAddress, TypeFactory.NVARCHAR(100));
		field.setTitle("֪ͨ��ַ");
		this.f_noticePostcode = field = this.table.newField(FN_noticePostcode, TypeFactory.NVARCHAR(10));
		field.setTitle("֪ͨ�ʱ�");
		this.f_workTel = field = this.table.newField(FN_workTel, TypeFactory.NVARCHAR(20));
		field.setTitle("�����绰");
		this.f_fax = field = this.table.newField(FN_fax, TypeFactory.NVARCHAR(20));
		field.setTitle("����");
		this.f_accountPeriod = field = this.table.newField(FN_accountPeriod, TypeFactory.INT);
		field.setTitle("����");
		this.f_accountRemind = field = this.table.newField(FN_accountRemind, TypeFactory.INT);
		field.setTitle("������������");
		this.f_linkmanName = field = this.table.newField(FN_linkmanName, TypeFactory.NVARCHAR(20));
		field.setTitle("��ϵ������");
		this.f_linkmanSuffix = field = this.table.newField(FN_linkmanSuffix, TypeFactory.NVARCHAR(10));
		field.setTitle("��ϵ�˺�׺");
		this.f_linkmanNamePY = field = this.table.newField(FN_linkmanNamePY, TypeFactory.VARCHAR(10));
		field.setTitle("��ϵ��ƴ��");
		this.f_linkmanTel = field = this.table.newField(FN_linkmanTel, TypeFactory.NVARCHAR(20));
		field.setTitle("��ϵ�˵绰");
		this.f_linkmanMobile = field = this.table.newField(FN_linkmanMobile, TypeFactory.NVARCHAR(20));
		field.setTitle("��ϵ���ֻ�");
		this.f_linkmanEmail = field = this.table.newField(FN_linkmanEmail, TypeFactory.NVARCHAR(50));
		field.setTitle("��ϵ������");
		this.f_canDelete = field = this.table.newField(FN_canDelete, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ����ɾ��");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
		this.f_creditAmount = field = this.table.newField(FN_creditAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("���ö��");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("������");
		this.f_cooperation = this.table.newField(FN_cooperation, TypeFactory.CHAR(2));
	}

}