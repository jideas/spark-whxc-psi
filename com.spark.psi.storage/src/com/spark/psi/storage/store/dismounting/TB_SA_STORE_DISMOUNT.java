package com.spark.psi.storage.store.dismounting;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_STORE_DISMOUNT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_STORE_DISMOUNT";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_dismDate;
	public final TableFieldDefine f_dismOrdNo;
	public final TableFieldDefine f_markPerson;
	public final TableFieldDefine f_markName;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_storeGuid;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_storePY;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_dismDate ="dismDate";
	public static final String FN_dismOrdNo ="dismOrdNo";
	public static final String FN_markPerson ="markPerson";
	public static final String FN_markName ="markName";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_storeGuid ="storeGuid";
	public static final String FN_storeName ="storeName";
	public static final String FN_storePY ="storePY";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_STORE_DISMOUNT() {
		super(TABLE_NAME);
		this.table.setDescription("库存拆装");
		this.table.setTitle("库存拆装");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户ID");
		this.f_dismDate = field = this.table.newField(FN_dismDate, TypeFactory.DATE);
		field.setTitle("拆装日期");
		this.f_dismOrdNo = field = this.table.newField(FN_dismOrdNo, TypeFactory.VARCHAR(20));
		field.setTitle("拆装单编号（单号）");
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
		this.f_storePY = field = this.table.newField(FN_storePY, TypeFactory.VARCHAR(25));
		field.setTitle("仓库名称（拼音）");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.VARCHAR(20));
		field.setTitle("创建人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建时间");
		this.table.newIndex("dismount_markName",f_markName,f_storePY,f_storeName,f_dismOrdNo);
	}

}
