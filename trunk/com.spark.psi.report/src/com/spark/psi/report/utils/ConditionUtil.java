/**
 * 
 */
package com.spark.psi.report.utils;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.report.constant.ReportConstants.ConditionType;

/**
 *将条件拼装入sql语句工具
 */
public abstract class ConditionUtil{

	/**
	 * 将条件拼装入sql语句中
	 * 
	 * argparam qb
	 * argparam con
	 * argparam column
	 */
	@SuppressWarnings("unchecked")
	public static void fillConditionToSql(QuerySqlBuilder qb, Condition con, String column){
		ConditionType type = con.getType();
		switch(type){
			case Equals:
				qb.addArgs("arg" + con.getConditionColumn().toString(), getType(con.getValue()), con.getValue());
				qb.addEquals(column, "@arg" + con.getConditionColumn().toString());
				break;
			case In:
				List<String> alist = new ArrayList<String>();
				int i = 0;
				for(Object obj : (List)con.getValue()){
					qb.addArgs("arg" + con.getConditionColumn().toString() + i, getType(obj), obj);
					alist.add("@arg" + con.getConditionColumn().toString() + i++);
				}
				qb.addIn(column, alist);
				break;
			case Less_Than:
				qb.addArgs("arg" + con.getConditionColumn().toString(), getType(con.getValue()), con.getValue());
				qb.addLessThan(column, "@arg" + con.getConditionColumn().toString());
				break;
			case NotEquals:
				qb.addArgs("arg" + con.getConditionColumn().toString(), getType(con.getValue()), con.getValue());
				qb.addNotEquals(column, "@arg" + con.getConditionColumn().toString());
				break;
			case Greater_Than:
				qb.addArgs("arg" + con.getConditionColumn().toString(), getType(con.getValue()), con.getValue());
				qb.addGreaterThan(column, "@arg" + con.getConditionColumn().toString());
				break;
			case Greater_ThanOrEquals:
				qb.addArgs("arg" + con.getConditionColumn().toString(), getType(con.getValue()), con.getValue());
				qb.addGreaterThanOrEquals(column, "@arg" + con.getConditionColumn().toString());
				break;
			case Less_ThanOrEquals:
				qb.addArgs("arg" + con.getConditionColumn().toString(), getType(con.getValue()), con.getValue());
				qb.addLessThanOrEquals(column, "@arg" + con.getConditionColumn().toString());
				break;
			case Between:
				qb.addArgs("arg" + con.getConditionColumn().toString() + 1, getType(con.getBegginValue()), con
				        .getBegginValue());
				qb
				        .addArgs("arg" + con.getConditionColumn().toString() + 2, getType(con.getEndValue()), con
				                .getEndValue());
				qb.addBetween(column, "@arg" + con.getConditionColumn().toString() + 1, "@arg"
				        + con.getConditionColumn().toString() + 2);
				break;
			case NotIn:
				List<String> alist2 = new ArrayList<String>();
				int i2 = 0;
				for(Object obj : (List)con.getValue()){
					qb.addArgs("arg" + con.getConditionColumn().toString() + i2, getType(obj), obj);
					alist2.add("@arg" + con.getConditionColumn().toString() + i2++);
				}
				qb.addIn(column, alist2);
				break;
			default:
				break;
		}
	}

	/**
	 * 获得类型
	 * 
	 * argparam obj
	 * argreturn
	 */
	private static String getType(Object obj){
		String type = null;
		if(obj instanceof Integer){
			return "int";
		}
		else if(obj instanceof Long){
			return "date";
		}
		else if(obj instanceof GUID){
			return "guid";
		}
		else if(obj instanceof String){
			return "string";
		}
		else if(obj instanceof Boolean){
			return "boolean";
		}
		else if(obj instanceof Double){
			return "double";
		}
		return type;
	}
}
