package com.spark.psi.storage.inventory.checkout;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Checkouting extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Checkouting";

	public final TableFieldDefine f_sheetType;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerCode;
	public final TableFieldDefine f_partnerName;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_partnerShortName;
	public final TableFieldDefine f_relaBillsId;
	public final TableFieldDefine f_relaBillsNo;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_isStoped;
	public final TableFieldDefine f_stopReason;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_storeNamePY;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_billsAmount;
	public final TableFieldDefine f_billsCount;
	public final TableFieldDefine f_checkoutDate;
	public final TableFieldDefine f_checkoutString;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creatorDeptId;
	public final TableFieldDefine f_deptName;
	public final TableFieldDefine f_creator;

	public static final String FN_sheetType ="sheetType";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerCode ="partnerCode";
	public static final String FN_partnerName ="partnerName";
	public static final String FN_namePY ="namePY";
	public static final String FN_partnerShortName ="partnerShortName";
	public static final String FN_relaBillsId ="relaBillsId";
	public static final String FN_relaBillsNo ="relaBillsNo";
	public static final String FN_storeId ="storeId";
	public static final String FN_isStoped ="isStoped";
	public static final String FN_stopReason ="stopReason";
	public static final String FN_storeName ="storeName";
	public static final String FN_storeNamePY ="storeNamePY";
	public static final String FN_remark ="remark";
	public static final String FN_billsAmount ="billsAmount";
	public static final String FN_billsCount ="billsCount";
	public static final String FN_checkoutDate ="checkoutDate";
	public static final String FN_checkoutString ="checkoutString";
	public static final String FN_status ="status";
	public static final String FN_createDate ="createDate";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creatorDeptId ="creatorDeptId";
	public static final String FN_deptName ="deptName";
	public static final String FN_creator ="creator";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory_Checkouting() {
		super(TABLE_NAME);
		this.table.setTitle("出库单中间表");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_sheetType = field = this.table.newField(FN_sheetType, TypeFactory.CHAR(2));
		field.setTitle("出库类型");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("供应商/客户recid");
		this.f_partnerCode = this.table.newField(FN_partnerCode, TypeFactory.NVARCHAR(30));
		this.f_partnerName = field = this.table.newField(FN_partnerName, TypeFactory.NVARCHAR(100));
		field.setTitle("供应商/客户名称");
		this.f_namePY = field = this.table.newField(FN_namePY, TypeFactory.NVARCHAR(30));
		field.setTitle("拼音");
		this.f_partnerShortName = field = this.table.newField(FN_partnerShortName, TypeFactory.NVARCHAR(30));
		field.setTitle("简称");
		this.f_relaBillsId = field = this.table.newField(FN_relaBillsId, TypeFactory.GUID);
		field.setTitle("相关单据recid");
		this.f_relaBillsNo = field = this.table.newField(FN_relaBillsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("相关单据编号");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库recid");
		this.f_isStoped = field = this.table.newField(FN_isStoped, TypeFactory.BOOLEAN);
		field.setTitle("是否中止");
		this.f_stopReason = field = this.table.newField(FN_stopReason, TypeFactory.NVARCHAR(500));
		field.setTitle("中止原因");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(100));
		field.setTitle("仓库名称");
		this.f_storeNamePY = field = this.table.newField(FN_storeNamePY, TypeFactory.NVARCHAR(50));
		field.setTitle("拼音");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_billsAmount = field = this.table.newField(FN_billsAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("订单金额");
		this.f_billsCount = field = this.table.newField(FN_billsCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("订单总商品数");
		this.f_checkoutDate = field = this.table.newField(FN_checkoutDate, TypeFactory.DATE);
		field.setTitle("预计出库日期");
		this.f_checkoutString = field = this.table.newField(FN_checkoutString, TypeFactory.NVARCHAR(1000));
		field.setTitle("确认出库字符串");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("处理情况");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人");
		this.f_creatorDeptId = this.table.newField(FN_creatorDeptId, TypeFactory.GUID);
		this.f_deptName = this.table.newField(FN_deptName, TypeFactory.NVARCHAR(50));
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人");
	}

}
