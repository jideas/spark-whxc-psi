package com.spark.psi.storage.store.check;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_STORE_CHECK extends TableDeclarator {

	public static final String TABLE_NAME ="SA_STORE_CHECK";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_startDate;
	public final TableFieldDefine f_endDate;
	public final TableFieldDefine f_checkOrdNo;
	public final TableFieldDefine f_checkOrdState;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_checkObj;
	public final TableFieldDefine f_checkPerson;
	public final TableFieldDefine f_markPerson;
	public final TableFieldDefine f_markName;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_storeGuid;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_storeState;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_storePY;
	public final TableFieldDefine f_checkProfit;
	public final TableFieldDefine f_checkDeficient;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_startDate ="startDate";
	public static final String FN_endDate ="endDate";
	public static final String FN_checkOrdNo ="checkOrdNo";
	public static final String FN_checkOrdState ="checkOrdState";
	public static final String FN_remark ="remark";
	public static final String FN_checkObj ="checkObj";
	public static final String FN_checkPerson ="checkPerson";
	public static final String FN_markPerson ="markPerson";
	public static final String FN_markName ="markName";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_storeGuid ="storeGuid";
	public static final String FN_storeName ="storeName";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_storeState ="storeState";
	public static final String FN_createDate ="createDate";
	public static final String FN_storePY ="storePY";
	public static final String FN_checkProfit ="checkProfit";
	public static final String FN_checkDeficient ="checkDeficient";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_STORE_CHECK() {
		super(TABLE_NAME);
		this.table.setDescription("库存盘点");
		this.table.setTitle("库存盘点");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户GUID");
		this.f_startDate = field = this.table.newField(FN_startDate, TypeFactory.DATE);
		field.setTitle("盘点开始时间");
		this.f_endDate = field = this.table.newField(FN_endDate, TypeFactory.DATE);
		field.setTitle("盘点结束时间");
		this.f_checkOrdNo = field = this.table.newField(FN_checkOrdNo, TypeFactory.VARCHAR(50));
		field.setTitle("盘点单编号（单号）");
		this.f_checkOrdState = field = this.table.newField(FN_checkOrdState, TypeFactory.CHAR(2));
		field.setTitle("盘点单据状态");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.VARCHAR(1000));
		field.setTitle("备注");
		this.f_checkObj = field = this.table.newField(FN_checkObj, TypeFactory.CHAR(2));
		field.setTitle("盘点对象");
		this.f_checkPerson = field = this.table.newField(FN_checkPerson, TypeFactory.VARCHAR(255));
		field.setTitle("盘点人");
		this.f_markPerson = field = this.table.newField(FN_markPerson, TypeFactory.GUID);
		field.setTitle("制单人");
		this.f_markName = field = this.table.newField(FN_markName, TypeFactory.VARCHAR(20));
		field.setTitle("制单人名称");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("部门");
		this.f_storeGuid = field = this.table.newField(FN_storeGuid, TypeFactory.GUID);
		field.setTitle("仓库");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.VARCHAR(20));
		field.setTitle("仓库名称");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.VARCHAR(20));
		field.setTitle("创建人");
		this.f_storeState = field = this.table.newField(FN_storeState, TypeFactory.CHAR(2));
		field.setTitle("仓库状态");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建时间");
		this.f_storePY = field = this.table.newField(FN_storePY, TypeFactory.VARCHAR(25));
		field.setTitle("仓库名称（拼音）");
		this.f_checkProfit = field = this.table.newField(FN_checkProfit, TypeFactory.NUMERIC(17,0));
		field.setTitle("盘盈数量");
		this.f_checkDeficient = field = this.table.newField(FN_checkDeficient, TypeFactory.NUMERIC(17,0));
		field.setTitle("盘亏数量");
		this.table.newIndex("check_checkOrdNo",f_checkOrdNo,f_storeName,f_storePY);
	}

}
