package com.spark.psi.storage.purchase;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Purchase_Order extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Purchase_Order";

	public final TableFieldDefine f_billsNo;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerName;
	public final TableFieldDefine f_partnerNamePY;
	public final TableFieldDefine f_shortName;
	public final TableFieldDefine f_billType;
	public final TableFieldDefine f_consignee;
	public final TableFieldDefine f_linkman;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_deliveryDate;
	public final TableFieldDefine f_rejectReason;
	public final TableFieldDefine f_stopReason;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_totalAmount;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_deptId;
	public final TableFieldDefine f_isStoped;
	public final TableFieldDefine f_approveStr;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_finishedDate;

	public static final String FN_billsNo ="billsNo";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerName ="partnerName";
	public static final String FN_partnerNamePY ="partnerNamePY";
	public static final String FN_shortName ="shortName";
	public static final String FN_billType ="billType";
	public static final String FN_consignee ="consignee";
	public static final String FN_linkman ="linkman";
	public static final String FN_address ="address";
	public static final String FN_deliveryDate ="deliveryDate";
	public static final String FN_rejectReason ="rejectReason";
	public static final String FN_stopReason ="stopReason";
	public static final String FN_remark ="remark";
	public static final String FN_totalAmount ="totalAmount";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_status ="status";
	public static final String FN_deptId ="deptId";
	public static final String FN_isStoped ="isStoped";
	public static final String FN_approveStr ="approveStr";
	public static final String FN_storeId ="storeId";
	public static final String FN_storeName ="storeName";
	public static final String FN_finishedDate ="finishedDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Purchase_Order() {
		super(TABLE_NAME);
		this.table.setTitle("采购订单");
		TableFieldDeclare field;
		this.f_billsNo = field = this.table.newField(FN_billsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("采购订单编号");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("供应商GUID");
		this.f_partnerName = field = this.table.newField(FN_partnerName, TypeFactory.NVARCHAR(30));
		field.setTitle("供应商名称");
		this.f_partnerNamePY = field = this.table.newField(FN_partnerNamePY, TypeFactory.NVARCHAR(30));
		field.setTitle("供应商名称拼音");
		this.f_shortName = field = this.table.newField(FN_shortName, TypeFactory.NVARCHAR(100));
		field.setTitle("供应商全称");
		this.f_billType = field = this.table.newField(FN_billType, TypeFactory.CHAR(2));
		field.setTitle("单据类型");
		this.f_consignee = field = this.table.newField(FN_consignee, TypeFactory.NVARCHAR(40));
		field.setTitle("收货人");
		this.f_linkman = field = this.table.newField(FN_linkman, TypeFactory.NVARCHAR(40));
		field.setTitle("联系人");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(200));
		field.setTitle("收货地址");
		this.f_deliveryDate = field = this.table.newField(FN_deliveryDate, TypeFactory.DATE);
		field.setTitle("交货日期");
		this.f_rejectReason = field = this.table.newField(FN_rejectReason, TypeFactory.NVARCHAR(200));
		field.setTitle("退回原因");
		this.f_stopReason = field = this.table.newField(FN_stopReason, TypeFactory.NVARCHAR(200));
		field.setTitle("中止原因");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_totalAmount = field = this.table.newField(FN_totalAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("总金额");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("制单人GUID");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(40));
		field.setTitle("制单人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("制单日期");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("处理情况");
		this.f_deptId = field = this.table.newField(FN_deptId, TypeFactory.GUID);
		field.setTitle("部门GUID");
		this.f_isStoped = field = this.table.newField(FN_isStoped, TypeFactory.BOOLEAN);
		field.setTitle("中止状态");
		this.f_approveStr = field = this.table.newField(FN_approveStr, TypeFactory.NVARCHAR(100));
		field.setTitle("审核人及日期");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库ID");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(50));
		field.setTitle("仓库名称");
		this.f_finishedDate = field = this.table.newField(FN_finishedDate, TypeFactory.DATE);
		field.setTitle("完成日期");
	}

}
