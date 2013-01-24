package com.spark.cms.storage.card;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_CARD_DISTRIBUTE_LOG extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_CARD_DISTRIBUTE_LOG";

	public final TableFieldDefine f_cardId;
	public final TableFieldDefine f_distributePersonId;
	public final TableFieldDefine f_distributePerson;
	public final TableFieldDefine f_responsiblePersonId;
	public final TableFieldDefine f_responsiblePerson;
	public final TableFieldDefine f_distributeDate;
	public final TableFieldDefine f_lastPersonId;
	public final TableFieldDefine f_lastPerson;

	public static final String FN_cardId ="cardId";
	public static final String FN_distributePersonId ="distributePersonId";
	public static final String FN_distributePerson ="distributePerson";
	public static final String FN_responsiblePersonId ="responsiblePersonId";
	public static final String FN_responsiblePerson ="responsiblePerson";
	public static final String FN_distributeDate ="distributeDate";
	public static final String FN_lastPersonId ="lastPersonId";
	public static final String FN_lastPerson ="lastPerson";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_CARD_DISTRIBUTE_LOG() {
		super(TABLE_NAME);
		this.table.setTitle("面值卡派发记录");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_cardId = field = this.table.newField(FN_cardId, TypeFactory.GUID);
		field.setTitle("卡id");
		this.f_distributePersonId = field = this.table.newField(FN_distributePersonId, TypeFactory.GUID);
		field.setTitle("派发人");
		this.f_distributePerson = field = this.table.newField(FN_distributePerson, TypeFactory.NVARCHAR(30));
		field.setTitle("派发人姓名");
		this.f_responsiblePersonId = field = this.table.newField(FN_responsiblePersonId, TypeFactory.GUID);
		field.setTitle("新责任人");
		this.f_responsiblePerson = field = this.table.newField(FN_responsiblePerson, TypeFactory.NVARCHAR(30));
		field.setTitle("新责任人姓名");
		this.f_distributeDate = field = this.table.newField(FN_distributeDate, TypeFactory.DATE);
		field.setTitle("变更日期");
		this.f_lastPersonId = field = this.table.newField(FN_lastPersonId, TypeFactory.GUID);
		field.setTitle("前责任人");
		this.f_lastPerson = field = this.table.newField(FN_lastPerson, TypeFactory.NVARCHAR(30));
		field.setTitle("前责任人姓名");
	}

}
