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
	 * ģ��id
	 */
	public final TableFieldDefine f_functionId;
	/**
	 * �û�id
	 */
	public final TableFieldDefine f_userId;
	/**
	 * ����ͼ��X����
	 */
	public final TableFieldDefine f_xindex;
	/**
	 * ����ͼ��Y����
	 */
	public final TableFieldDefine f_yindex;
	/**
	 * �Ƿ����������
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
