/**
 * 
 */
package com.spark.psi.report.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.spark.common.utils.character.CheckIsNull;

/**
 *
 */
public class DeleteSqlBuilder{

	private Context context;
	private String table;
	/**
	 * 过滤的列
	 */
	private List<String> columns = new ArrayList<String>();

	/**
	 * 参数的值
	 */
	private List<Object> values = new ArrayList<Object>();

	/**
	 * 所有参数类型
	 */
	private List<String> argsType = new ArrayList<String>();

	public DeleteSqlBuilder(Context context){
		this.context = context;
	}

	public void setTable(String table){
		this.table = table;
	}

	public void addEquals(String column, String type, Object value){
		if(CheckIsNull.isEmpty(column) || CheckIsNull.isEmpty(type) || CheckIsNull.isEmpty(value)){
			return;
		}
		this.columns.add(column);
		this.argsType.add(type);
		this.values.add(value);
	}

	public int execute(){
		DBCommand db = context.prepareStatement(this.builderSql());
		for(int i = 0; i < this.values.size(); i++){
			db.setArgumentValue(i, this.values.get(i));
		}
		int i = db.executeUpdate();
		db.unuse();
		return i;
	}

	private String builderSql(){
		int size = columns.size();
		StringBuilder ss = new StringBuilder("define delete Delete_" + new Date().getTime() + "(");
		for(int i = 0; i < size; i++){
			String arg = "arg" + i;
			if(i > 0){
				ss.append(",");
			}
			ss.append("@" + arg + " " + argsType.get(i));
		}
		ss.append(")begin delete from ");
		ss.append(this.table + " as t");
		ss.append(" where 1=1 ");
		for(int i = 0; i < size; i++){
			ss.append(" and t.");
			ss.append(columns.get(i) + " = @arg" + i);
		}
		ss.append(" end");
		return ss.toString();
	}

	public final String STRING = "string";
	public final String guid = "guid";
	public final String DOUBLE = "double";
	public final String INT = "int";
	public final String LONG = "long";
	public final String DATE = "date";
	public final String BOOLEAN = "boolean";
}
