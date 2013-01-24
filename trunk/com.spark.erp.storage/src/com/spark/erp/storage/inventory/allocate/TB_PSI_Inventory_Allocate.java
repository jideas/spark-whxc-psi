package com.spark.erp.storage.inventory.allocate;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Allocate extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Allocate";

	public final TableFieldDefine f_allocSheetNo;
	public final TableFieldDefine f_applyDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_allocateOutStoreId;
	public final TableFieldDefine f_allocateOutStoreName;
	public final TableFieldDefine f_allocateInStoreId;
	public final TableFieldDefine f_allocateInStoreName;
	public final TableFieldDefine f_approvePersonId;
	public final TableFieldDefine f_approvePerson;
	public final TableFieldDefine f_approveDate;
	public final TableFieldDefine f_allocateOutPerson;
	public final TableFieldDefine f_allocateOutPersonName;
	public final TableFieldDefine f_allocateOutDate;
	public final TableFieldDefine f_allocateInPerson;
	public final TableFieldDefine f_allocateInPersonName;
	public final TableFieldDefine f_allocateInDate;
	public final TableFieldDefine f_allocateReason;
	public final TableFieldDefine f_rejectReason;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_deptId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_creatorPY;
	public final TableFieldDefine f_createDate;

	public static final String FN_allocSheetNo ="allocSheetNo";
	public static final String FN_applyDate ="applyDate";
	public static final String FN_status ="status";
	public static final String FN_allocateOutStoreId ="allocateOutStoreId";
	public static final String FN_allocateOutStoreName ="allocateOutStoreName";
	public static final String FN_allocateInStoreId ="allocateInStoreId";
	public static final String FN_allocateInStoreName ="allocateInStoreName";
	public static final String FN_approvePersonId ="approvePersonId";
	public static final String FN_approvePerson ="approvePerson";
	public static final String FN_approveDate ="approveDate";
	public static final String FN_allocateOutPerson ="allocateOutPerson";
	public static final String FN_allocateOutPersonName ="allocateOutPersonName";
	public static final String FN_allocateOutDate ="allocateOutDate";
	public static final String FN_allocateInPerson ="allocateInPerson";
	public static final String FN_allocateInPersonName ="allocateInPersonName";
	public static final String FN_allocateInDate ="allocateInDate";
	public static final String FN_allocateReason ="allocateReason";
	public static final String FN_rejectReason ="rejectReason";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_deptId ="deptId";
	public static final String FN_creator ="creator";
	public static final String FN_creatorPY ="creatorPY";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory_Allocate() {
		super(TABLE_NAME);
		this.table.setDescription("库存调拔");
		TableFieldDeclare field;
		this.f_allocSheetNo = field = this.table.newField(FN_allocSheetNo, TypeFactory.NVARCHAR(30));
		field.setTitle("调拨单号");
		this.f_applyDate = field = this.table.newField(FN_applyDate, TypeFactory.DATE);
		field.setTitle("申请日期");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("处理状态");
		this.f_allocateOutStoreId = field = this.table.newField(FN_allocateOutStoreId, TypeFactory.GUID);
		field.setTitle("调出库GUID");
		this.f_allocateOutStoreName = field = this.table.newField(FN_allocateOutStoreName, TypeFactory.NVARCHAR(50));
		field.setTitle("调出仓库名称");
		this.f_allocateInStoreId = field = this.table.newField(FN_allocateInStoreId, TypeFactory.GUID);
		field.setTitle("调入库GUID");
		this.f_allocateInStoreName = field = this.table.newField(FN_allocateInStoreName, TypeFactory.NVARCHAR(50));
		field.setTitle("调入仓库名称");
		this.f_approvePersonId = field = this.table.newField(FN_approvePersonId, TypeFactory.GUID);
		field.setTitle("审核人id");
		this.f_approvePerson = field = this.table.newField(FN_approvePerson, TypeFactory.NVARCHAR(30));
		field.setTitle("审核人名称");
		this.f_approveDate = field = this.table.newField(FN_approveDate, TypeFactory.DATE);
		field.setTitle("审核日期");
		this.f_allocateOutPerson = field = this.table.newField(FN_allocateOutPerson, TypeFactory.GUID);
		field.setTitle("确认调出人GUID");
		this.f_allocateOutPersonName = field = this.table.newField(FN_allocateOutPersonName, TypeFactory.NVARCHAR(30));
		field.setTitle("确认调出人名称");
		this.f_allocateOutDate = field = this.table.newField(FN_allocateOutDate, TypeFactory.DATE);
		field.setTitle("确认调出日期");
		this.f_allocateInPerson = field = this.table.newField(FN_allocateInPerson, TypeFactory.GUID);
		field.setTitle("确认调入人GUID");
		this.f_allocateInPersonName = field = this.table.newField(FN_allocateInPersonName, TypeFactory.NVARCHAR(30));
		field.setTitle("确认调入人名称");
		this.f_allocateInDate = field = this.table.newField(FN_allocateInDate, TypeFactory.DATE);
		field.setTitle("确认调入日期");
		this.f_allocateReason = field = this.table.newField(FN_allocateReason, TypeFactory.NVARCHAR(1000));
		field.setTitle("调拨原因");
		this.f_rejectReason = field = this.table.newField(FN_rejectReason, TypeFactory.NVARCHAR(1000));
		field.setTitle("退回原因");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人GUID");
		this.f_deptId = field = this.table.newField(FN_deptId, TypeFactory.GUID);
		field.setTitle("所属部门");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人姓名");
		this.f_creatorPY = field = this.table.newField(FN_creatorPY, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人拼音");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
	}

}
