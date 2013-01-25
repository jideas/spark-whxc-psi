package com.spark.psi.storage.messagetip;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.table.IndexDeclare;

public final class TB_SA_COMM_MESSAGETIPSTAT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_COMM_MESSAGETIPSTAT";

	public final TableFieldDefine f_receivePerson;
	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_lastViewTime;
	public final TableFieldDefine f_settings;

	public static final String FN_receivePerson ="receivePerson";
	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_lastViewTime ="lastViewTime";
	public static final String FN_settings ="settings";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_COMM_MESSAGETIPSTAT() {
		super(TABLE_NAME);
		this.table.setDescription("�����Ϣ����״̬");
		this.table.setTitle("��Ϣ����״̬");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_receivePerson = field = this.table.newField(FN_receivePerson, TypeFactory.GUID);
		field.setTitle("��Ϣ������");
		field.setKeepValid(true);
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_lastViewTime = field = this.table.newField(FN_lastViewTime, TypeFactory.DATE);
		field.setTitle("���鿴ʱ��");
		this.f_settings = field = this.table.newField(FN_settings, TypeFactory.VARCHAR(50));
		field.setTitle("������ʾ����");
		IndexDeclare index;
		index = this.table.newIndex("INDEX_MESSAGETIP_STAT",f_tenantsGuid,f_receivePerson);
		index.setUnique(true);
	}

}
