package com.spark.psi.storage.base.notice;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_Notice_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_Notice_Det";

	public final TableFieldDefine f_noticeId;
	public final TableFieldDefine f_deptId;
	public final TableFieldDefine f_deptName;

	public static final String FN_noticeId ="noticeId";
	public static final String FN_deptId ="deptId";
	public static final String FN_deptName ="deptName";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_Notice_Det() {
		super(TABLE_NAME);
		this.table.setTitle("公告部门关联");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_noticeId = field = this.table.newField(FN_noticeId, TypeFactory.GUID);
		field.setTitle("公告GUID");
		this.f_deptId = field = this.table.newField(FN_deptId, TypeFactory.GUID);
		field.setTitle("部门GUID");
		this.f_deptName = field = this.table.newField(FN_deptName, TypeFactory.NVARCHAR(50));
		field.setTitle("部门名称");
	}

}
