/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.instorage.intf.facade
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-7-21       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.instorage;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;

/**
 * <p>update</p>
 *


 *
 * @author 王志坚
 * @version 2011-7-21
 */

public class UpdateBuilder{

	public UpdateBuilder(Context context){
		this.context = context;
	}

	private Context context;

	private String table;

	private List<String> columns = new ArrayList<String>();
	private List<String> args = new ArrayList<String>();
	private List<String> types = new ArrayList<String>();
	private List<Object> values = new ArrayList<Object>();
	private List<String> conditions = new ArrayList<String>();
	private List<String> expressions = new ArrayList<String>();

	public void addColumn(String column, String type, Object value){
		this.columns.add(column);
		this.args.add(column);
		this.types.add(type);
		this.values.add(value);
	}
	
	public void addExpression(String arg, String type, Object value,String expression){
		this.args.add(arg);
		this.types.add(type);
		this.values.add(value);
		this.expressions.add(expression);
	}

	public void setTable(String table){
		this.table = table;
	}

	public void addCondition(String condition){
		this.conditions.add(condition);
	}

	public void addCondition(String args, String type,Object value, String condition){
		this.args.add(args);
		this.types.add(type);
		this.values.add(value);
		this.conditions.add(condition);
	}

	private String getSql(){
		StringBuilder sf = new StringBuilder();
		sf.append("define update UpdateIt(");
		for(int i = 0; i < this.args.size(); i++){
			if(0 == i){
				sf.append("@" + args.get(i) + " " + this.types.get(i) + " not null");
			}
			else{
				sf.append(",@" + args.get(i) + " " + this.types.get(i) + " not null");
			}
		}
		sf.append(")");
		sf.append(" begin");
		sf.append(" update \"" + this.table + "\" as t");
		sf.append(" set "); 
		for(int i = 0; i < this.columns.size(); i++){
			String column = columns.get(i);
			if(0 == i){
				sf.append("" + column + "=@" + column);
			}
			else{
				sf.append("," + column + "=@" + column);
			}
		}
		for(int i = 0; i < this.expressions.size(); i++){
			if(0==i&&this.columns.size()==0){
				sf.append(this.expressions.get(i));
			}else{
				sf.append(","+this.expressions.get(i));
			}
		}
		sf.append(" where 1=1");
		for(int i = 0; i < this.conditions.size(); i++){
			sf.append(" and " + conditions.get(i));
		}
		sf.append(" end");

		return sf.toString();
	}

	private String getTestSql(){
		StringBuilder sf = new StringBuilder();
		sf.append("define update UpdateIt(");
		sf.append("\n");
		for(int i = 0; i < this.args.size(); i++){
			if(0 == i){
				sf.append("@" + args.get(i) + " " + this.types.get(i) + " not null");
				sf.append("\n");
			}
			else{
				sf.append(",@" + args.get(i) + " " + this.types.get(i) + " not null");
				sf.append("\n");
			}
		}
		sf.append(")");
		sf.append("\n");
		sf.append(" begin");
		sf.append("\n");
		sf.append(" update \"" + this.table + "\" as t");
		sf.append("\n");
		sf.append(" set ");
		sf.append("\n");
		for(int i = 0; i < this.columns.size(); i++){
			String column = columns.get(i);
			if(0 == i){
				sf.append("" + column + "=@" + column);
				sf.append("\n");
			}
			else{
				sf.append("," + column + "=@" + column);
				sf.append("\n");
			}
		}
		for(int i = 0; i < this.expressions.size(); i++){
			if(0==i&&this.columns.size()==0){
				sf.append(this.expressions.get(i));
				sf.append("\n");
			}else{
			sf.append(","+this.expressions.get(i));
			sf.append("\n");}
		}
		sf.append(" where 1=1");
		sf.append("\n");
		for(int i = 0; i < this.conditions.size(); i++){
			sf.append(" and " + conditions.get(i));
			sf.append("\n");
		}
		sf.append(" end");

		return sf.toString();
	}

	public String toString(){
		return this.getTestSql();
	}

	public int execute(){ 
		DBCommand db = context.prepareStatement(this.getSql());
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
