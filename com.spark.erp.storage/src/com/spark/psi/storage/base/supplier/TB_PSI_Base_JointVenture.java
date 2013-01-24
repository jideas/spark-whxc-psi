package com.spark.psi.storage.base.supplier;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;

public final class TB_PSI_Base_JointVenture extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_JointVenture";

	public final TableFieldDefine f_supplierId;

	public static final String FN_supplierId ="supplierId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_JointVenture() {
		super(TABLE_NAME);
		this.table.setTitle("联营信息表");
		this.table.setCategory("基础数据主体");
		this.f_supplierId = this.table.newField(FN_supplierId, TypeFactory.GUID);
	}

}
