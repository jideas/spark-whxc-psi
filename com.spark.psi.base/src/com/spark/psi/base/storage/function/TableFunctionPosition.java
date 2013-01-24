package com.spark.psi.base.storage.function;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.type.TypeFactory;

public class TableFunctionPosition extends TableDeclarator{

	public final static String NAME = "SA_FUNCTION_POSITION";

	/**
	 * id
	 */
	public final TableFieldDefine f_id;
	/**
	 * 模块id
	 */
	public final TableFieldDefine f_functionId;
	/**
	 * 用户id
	 */
	public final TableFieldDefine f_userId;
	/**
	 * 桌面图标X坐标
	 */
	public final TableFieldDefine f_xindex;
	/**
	 * 桌面图标Y坐标
	 */
	public final TableFieldDefine f_yindex;
	/**
	 * 是否放在桌面上
	 */
	public final TableFieldDefine f_isPutMain;

	public TableFunctionPosition(){
	    super(NAME);
	    f_id = this.table.f_RECID();
	    f_functionId = this.table.newPrimaryField("functionId", TypeFactory.VARCHAR16);
	    f_userId = this.table.newPrimaryField("userId", TypeFactory.GUID);
	    f_xindex = this.table.newField("xindex", TypeFactory.INT);
	    f_yindex = this.table.newField("yindex", TypeFactory.INT);
	    f_isPutMain = this.table.newField("isputmain", TypeFactory.BOOLEAN);
	    
    }
}
