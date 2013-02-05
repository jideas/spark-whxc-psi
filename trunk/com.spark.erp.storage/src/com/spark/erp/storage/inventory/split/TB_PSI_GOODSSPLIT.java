package com.spark.erp.storage.inventory.split;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;

public final class TB_PSI_GOODSSPLIT extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_GOODSSPLIT";

	public final TableFieldDefine f_billNo;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_approvalPerson;
	public final TableFieldDefine f_approvalPersonId;
	public final TableFieldDefine f_approvalDate;
	public final TableFieldDefine f_distributPerson;
	public final TableFieldDefine f_distributPersonId;
	public final TableFieldDefine f_distributDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_rejectReason;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_storeId;

	public static final String FN_billNo ="billNo";
	public static final String FN_creator ="creator";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_createDate ="createDate";
	public static final String FN_approvalPerson ="approvalPerson";
	public static final String FN_approvalPersonId ="approvalPersonId";
	public static final String FN_approvalDate ="approvalDate";
	public static final String FN_distributPerson ="distributPerson";
	public static final String FN_distributPersonId ="distributPersonId";
	public static final String FN_distributDate ="distributDate";
	public static final String FN_status ="status";
	public static final String FN_rejectReason ="rejectReason";
	public static final String FN_remark ="remark";
	public static final String FN_storeId ="storeId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_GOODSSPLIT() {
		super(TABLE_NAME);
		this.table.setTitle("商品拆分单");
		this.table.setCategory("基础数据主体");
		this.f_billNo = this.table.newField(FN_billNo, TypeFactory.NVARCHAR(30));
		this.f_creator = this.table.newField(FN_creator, TypeFactory.NVARCHAR(50));
		this.f_creatorId = this.table.newField(FN_creatorId, TypeFactory.GUID);
		this.f_createDate = this.table.newField(FN_createDate, TypeFactory.DATE);
		this.f_approvalPerson = this.table.newField(FN_approvalPerson, TypeFactory.NVARCHAR(50));
		this.f_approvalPersonId = this.table.newField(FN_approvalPersonId, TypeFactory.GUID);
		this.f_approvalDate = this.table.newField(FN_approvalDate, TypeFactory.DATE);
		this.f_distributPerson = this.table.newField(FN_distributPerson, TypeFactory.NVARCHAR(50));
		this.f_distributPersonId = this.table.newField(FN_distributPersonId, TypeFactory.GUID);
		this.f_distributDate = this.table.newField(FN_distributDate, TypeFactory.DATE);
		this.f_status = this.table.newField(FN_status, TypeFactory.CHAR(2));
		this.f_rejectReason = this.table.newField(FN_rejectReason, TypeFactory.NVARCHAR(1000));
		this.f_remark = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		this.f_storeId = this.table.newField(FN_storeId, TypeFactory.GUID);
	}

}
