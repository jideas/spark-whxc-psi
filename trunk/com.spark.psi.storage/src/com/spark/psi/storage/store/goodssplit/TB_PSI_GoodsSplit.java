package com.spark.psi.storage.store.goodssplit;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_GoodsSplit extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_GoodsSplit";

	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_approvalPersonId;
	public final TableFieldDefine f_approvalPersonName;
	public final TableFieldDefine f_approvalDate;
	public final TableFieldDefine f_rejectReason;
	public final TableFieldDefine f_distributPersonId;
	public final TableFieldDefine f_distributPersonName;
	public final TableFieldDefine f_distributDate;

	public static final String FN_storeId ="storeId";
	public static final String FN_remark ="remark";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_status ="status";
	public static final String FN_approvalPersonId ="approvalPersonId";
	public static final String FN_approvalPersonName ="approvalPersonName";
	public static final String FN_approvalDate ="approvalDate";
	public static final String FN_rejectReason ="rejectReason";
	public static final String FN_distributPersonId ="distributPersonId";
	public static final String FN_distributPersonName ="distributPersonName";
	public static final String FN_distributDate ="distributDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_GoodsSplit() {
		super(TABLE_NAME);
		this.table.setTitle("商品拆分主表");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("商品仓库GUID");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人Id");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人名称");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("状态");
		this.f_approvalPersonId = field = this.table.newField(FN_approvalPersonId, TypeFactory.GUID);
		field.setTitle("审批人标识");
		this.f_approvalPersonName = field = this.table.newField(FN_approvalPersonName, TypeFactory.NVARCHAR(50));
		field.setTitle("审批人名称");
		this.f_approvalDate = field = this.table.newField(FN_approvalDate, TypeFactory.DATE);
		field.setTitle("审批日期");
		this.f_rejectReason = field = this.table.newField(FN_rejectReason, TypeFactory.NVARCHAR(200));
		field.setTitle("驳回原因");
		this.f_distributPersonId = field = this.table.newField(FN_distributPersonId, TypeFactory.GUID);
		field.setTitle("分配人标识");
		this.f_distributPersonName = field = this.table.newField(FN_distributPersonName, TypeFactory.NVARCHAR(50));
		field.setTitle("分配人名称");
		this.f_distributDate = field = this.table.newField(FN_distributDate, TypeFactory.DATE);
		field.setTitle("分配日期");
	}

}
