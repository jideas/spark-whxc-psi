package com.spark.psi.storage.contactbook;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_IMPORTANTDATE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_IMPORTANTDATE";

	public final TableFieldDefine f_tenantsGUID;
	public final TableFieldDefine f_personGUID;
	public final TableFieldDefine f_month;
	public final TableFieldDefine f_day;
	public final TableFieldDefine f_cycle;
	public final TableFieldDefine f_note;
	public final TableFieldDefine f_msgToMe;
	public final TableFieldDefine f_msgToObj;
	public final TableFieldDefine f_alertToMe;
	public final TableFieldDefine f_alertToObj;
	public final TableFieldDefine f_dateToMe;
	public final TableFieldDefine f_dateToObj;
	public final TableFieldDefine f_meBeforeDay;
	public final TableFieldDefine f_meBeforeHour;
	public final TableFieldDefine f_meBeforeMinute;
	public final TableFieldDefine f_objBeforeDay;
	public final TableFieldDefine f_objBeforeHour;
	public final TableFieldDefine f_objBeforeMinute;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createPersonGUID;
	public final TableFieldDefine f_createDate;

	public static final String FN_tenantsGUID ="tenantsGUID";
	public static final String FN_personGUID ="personGUID";
	public static final String FN_month ="month";
	public static final String FN_day ="day";
	public static final String FN_cycle ="cycle";
	public static final String FN_note ="note";
	public static final String FN_msgToMe ="msgToMe";
	public static final String FN_msgToObj ="msgToObj";
	public static final String FN_alertToMe ="alertToMe";
	public static final String FN_alertToObj ="alertToObj";
	public static final String FN_dateToMe ="dateToMe";
	public static final String FN_dateToObj ="dateToObj";
	public static final String FN_meBeforeDay ="meBeforeDay";
	public static final String FN_meBeforeHour ="meBeforeHour";
	public static final String FN_meBeforeMinute ="meBeforeMinute";
	public static final String FN_objBeforeDay ="objBeforeDay";
	public static final String FN_objBeforeHour ="objBeforeHour";
	public static final String FN_objBeforeMinute ="objBeforeMinute";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createPersonGUID ="createPersonGUID";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_IMPORTANTDATE() {
		super(TABLE_NAME);
		this.table.setDescription("重要日期");
		TableFieldDeclare field;
		this.f_tenantsGUID = field = this.table.newField(FN_tenantsGUID, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_personGUID = field = this.table.newField(FN_personGUID, TypeFactory.GUID);
		field.setTitle("所属联系人");
		this.f_month = field = this.table.newField(FN_month, TypeFactory.INT);
		field.setTitle("提醒月份");
		this.f_day = field = this.table.newField(FN_day, TypeFactory.INT);
		field.setTitle("提醒日期");
		this.f_cycle = field = this.table.newField(FN_cycle, TypeFactory.CHAR(2));
		field.setTitle("提醒周期");
		this.f_note = field = this.table.newField(FN_note, TypeFactory.NVARCHAR(100));
		field.setTitle("日期说明");
		this.f_msgToMe = field = this.table.newField(FN_msgToMe, TypeFactory.NVARCHAR(200));
		field.setTitle("消息内容给用户");
		this.f_msgToObj = field = this.table.newField(FN_msgToObj, TypeFactory.NVARCHAR(200));
		field.setTitle("消息内容给联系人");
		this.f_alertToMe = field = this.table.newField(FN_alertToMe, TypeFactory.NVARCHAR(5));
		field.setTitle("提醒方式给用户");
		this.f_alertToObj = field = this.table.newField(FN_alertToObj, TypeFactory.NVARCHAR(5));
		field.setTitle("提醒方式给联系人");
		this.f_dateToMe = field = this.table.newField(FN_dateToMe, TypeFactory.DATE);
		field.setTitle("部门");
		this.f_dateToObj = field = this.table.newField(FN_dateToObj, TypeFactory.DATE);
		field.setTitle("职位");
		this.f_meBeforeDay = field = this.table.newField(FN_meBeforeDay, TypeFactory.INT);
		field.setTitle("给用户提前提醒天数");
		this.f_meBeforeHour = field = this.table.newField(FN_meBeforeHour, TypeFactory.INT);
		field.setTitle("给用户提前提醒小时");
		this.f_meBeforeMinute = field = this.table.newField(FN_meBeforeMinute, TypeFactory.INT);
		field.setTitle("给用户提前提醒分");
		this.f_objBeforeDay = field = this.table.newField(FN_objBeforeDay, TypeFactory.INT);
		field.setTitle("给联系人提前提醒天数");
		this.f_objBeforeHour = field = this.table.newField(FN_objBeforeHour, TypeFactory.INT);
		field.setTitle("给联系人提前提醒小时");
		this.f_objBeforeMinute = field = this.table.newField(FN_objBeforeMinute, TypeFactory.INT);
		field.setTitle("给联系人提前提醒分");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人明文");
		this.f_createPersonGUID = this.table.newField(FN_createPersonGUID, TypeFactory.GUID);
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
	}

}
