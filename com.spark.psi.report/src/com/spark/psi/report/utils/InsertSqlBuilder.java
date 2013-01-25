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
 * insert
 */
public class InsertSqlBuilder{

	private Context context;
	private List<String> columns = new ArrayList<String>();
	private List<String> args = new ArrayList<String>();
	private List<String> types = new ArrayList<String>();
	private List<Object> values = new ArrayList<Object>();

	public InsertSqlBuilder(Context context){
		this.context = context;
	}

	private String tableName;

	public void setTable(String tableName){
		this.tableName = tableName;
	}

	public void addColumn(String columnName, String type, Object value){
		if(CheckIsNull.isEmpty(columnName) && CheckIsNull.isEmpty(type) && CheckIsNull.isEmpty(value)){
			return;
		}
		this.args.add(columnName);
		this.columns.add(columnName);
		this.types.add(type);
		this.values.add(value);
	}

	private String builderSql(){
		StringBuilder ss = new StringBuilder("define insert Insert_" + new Date().getTime() + "(");
		for(int i = 0; i < args.size(); i++){
			String arg = args.get(i);
			if(i > 0){
				ss.append(",");
			}
			ss.append("@" + arg + " " + types.get(i));
		}
		ss.append(")begin insert into \"" + this.tableName + "\" (");
		for(int i = 0; i < columns.size(); i++){
			String column = columns.get(i);
			if(i > 0){
				ss.append(",");
			}
			ss.append(column);
		}
		ss.append(") values(");
		for(int i = 0; i < args.size(); i++){
			String arg = args.get(i);
			if(i > 0){
				ss.append(",");
			}
			ss.append("@" + arg);
		}
		ss.append(") end");
		return ss.toString();
	}

	public int execute(){
		DBCommand db = context.prepareStatement(this.builderSql());
		for(int i=0;i<this.values.size();i++){
			db.setArgumentValue(i, this.values.get(i));
		}
		int i = db.executeUpdate();
		db.unuse();
		return i;
	}

	public final String STRING = "string";
	public final String guid = "guid";
	public final String DOUBLE = "double";
	public final String INT = "int";
	public final String LONG = "long";
	public final String DATE = "date";
	public final String BOOLEAN = "boolean";
}
