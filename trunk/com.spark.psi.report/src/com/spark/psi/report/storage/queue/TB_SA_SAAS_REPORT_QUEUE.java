package com.spark.psi.report.storage.queue;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_SAAS_REPORT_QUEUE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_SAAS_REPORT_QUEUE";

	public final TableFieldDefine f_tenantId;
	public final TableFieldDefine f_eventClass;
	public final TableFieldDefine f_attributeXml;
	public final TableFieldDefine f_currTime;
	public final TableFieldDefine f_userId;

	public static final String FN_tenantId ="tenantId";
	public static final String FN_eventClass ="eventClass";
	public static final String FN_attributeXml ="attributeXml";
	public static final String FN_currTime ="currTime";
	public static final String FN_userId ="userId";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_SAAS_REPORT_QUEUE() {
		super(TABLE_NAME);
		this.table.setTitle("�����¼��������");
		TableFieldDeclare field;
		this.f_tenantId = field = this.table.newField(FN_tenantId, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_eventClass = field = this.table.newField(FN_eventClass, TypeFactory.VARCHAR(100));
		field.setTitle("�¼�����");
		this.f_attributeXml = field = this.table.newField(FN_attributeXml, TypeFactory.VARCHAR(1000));
		field.setTitle("����xml");
		this.f_currTime = field = this.table.newField(FN_currTime, TypeFactory.LONG);
		field.setTitle("����ʱ���");
		this.f_userId = field = this.table.newField(FN_userId, TypeFactory.GUID);
		field.setTitle("�����¼��û�id");
	}

}
