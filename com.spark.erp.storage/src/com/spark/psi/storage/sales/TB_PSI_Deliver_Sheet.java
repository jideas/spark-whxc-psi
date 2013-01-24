package com.spark.psi.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Deliver_Sheet extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Deliver_Sheet";

	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_finishDate;
	public final TableFieldDefine f_stationId;
	public final TableFieldDefine f_stationName;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_deliverPersonId;
	public final TableFieldDefine f_deliverPerson;
	public final TableFieldDefine f_planDate;
	public final TableFieldDefine f_deliverDate;
	public final TableFieldDefine f_consigneeId;
	public final TableFieldDefine f_consignee;
	public final TableFieldDefine f_consigneeDate;
	public final TableFieldDefine f_deliveredPackageCount;
	public final TableFieldDefine f_receivedPackageCount;
	public final TableFieldDefine f_description;
	public final TableFieldDefine f_formula;
	public final TableFieldDefine f_handlerId;
	public final TableFieldDefine f_handler;
	public final TableFieldDefine f_handleDate;

	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_remark ="remark";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_finishDate ="finishDate";
	public static final String FN_stationId ="stationId";
	public static final String FN_stationName ="stationName";
	public static final String FN_address ="address";
	public static final String FN_status ="status";
	public static final String FN_deliverPersonId ="deliverPersonId";
	public static final String FN_deliverPerson ="deliverPerson";
	public static final String FN_planDate ="planDate";
	public static final String FN_deliverDate ="deliverDate";
	public static final String FN_consigneeId ="consigneeId";
	public static final String FN_consignee ="consignee";
	public static final String FN_consigneeDate ="consigneeDate";
	public static final String FN_deliveredPackageCount ="deliveredPackageCount";
	public static final String FN_receivedPackageCount ="receivedPackageCount";
	public static final String FN_description ="description";
	public static final String FN_formula ="formula";
	public static final String FN_handlerId ="handlerId";
	public static final String FN_handler ="handler";
	public static final String FN_handleDate ="handleDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Deliver_Sheet() {
		super(TABLE_NAME);
		this.table.setTitle("配送单");
		TableFieldDeclare field;
		this.f_sheetNo = field = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(30));
		field.setTitle("单据编号");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("发货人Id");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("发货人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("发货日期");
		this.f_finishDate = field = this.table.newField(FN_finishDate, TypeFactory.DATE);
		field.setTitle("完成日期");
		this.f_stationId = field = this.table.newField(FN_stationId, TypeFactory.GUID);
		field.setTitle("站点ID");
		this.f_stationName = field = this.table.newField(FN_stationName, TypeFactory.NVARCHAR(50));
		field.setTitle("站点名称");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(200));
		field.setTitle("地址");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("状态");
		this.f_deliverPersonId = field = this.table.newField(FN_deliverPersonId, TypeFactory.GUID);
		field.setTitle("配送人Id");
		this.f_deliverPerson = field = this.table.newField(FN_deliverPerson, TypeFactory.NVARCHAR(30));
		field.setTitle("配送人");
		this.f_planDate = field = this.table.newField(FN_planDate, TypeFactory.DATE);
		field.setTitle("预计到货日期");
		this.f_deliverDate = field = this.table.newField(FN_deliverDate, TypeFactory.DATE);
		field.setTitle("配送日期");
		this.f_consigneeId = field = this.table.newField(FN_consigneeId, TypeFactory.GUID);
		field.setTitle("收货人Id");
		this.f_consignee = field = this.table.newField(FN_consignee, TypeFactory.NVARCHAR(40));
		field.setTitle("收货人");
		this.f_consigneeDate = field = this.table.newField(FN_consigneeDate, TypeFactory.DATE);
		field.setTitle("收货日期");
		this.f_deliveredPackageCount = field = this.table.newField(FN_deliveredPackageCount, TypeFactory.INT);
		field.setTitle("发货箱数");
		this.f_receivedPackageCount = field = this.table.newField(FN_receivedPackageCount, TypeFactory.INT);
		field.setTitle("收货箱数");
		this.f_description = field = this.table.newField(FN_description, TypeFactory.NVARCHAR(1000));
		field.setTitle("异常情况");
		this.f_formula = field = this.table.newField(FN_formula, TypeFactory.NVARCHAR(1000));
		field.setTitle("处理方案");
		this.f_handlerId = field = this.table.newField(FN_handlerId, TypeFactory.GUID);
		field.setTitle("处理人ID");
		this.f_handler = field = this.table.newField(FN_handler, TypeFactory.NVARCHAR(30));
		field.setTitle("处理人");
		this.f_handleDate = field = this.table.newField(FN_handleDate, TypeFactory.DATE);
		field.setTitle("处理时间");
	}

}
