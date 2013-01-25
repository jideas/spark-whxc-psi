/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.jiuqi.test.main
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-5-26       ��־��      
 * ============================================================*/

package com.spark.psi.report.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.common.utils.character.CheckIsNull;

/**
 * 
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2012<br>
 * </p>
 * 
 * @author ��־��
 * @version 2011-5-26
 */

public class QuerySqlBuilder{

	/**
	 * 
	 */
	public QuerySqlBuilder(Context context){
		this.context = context;
	}

	public Context getContext(){
		return this.context;
	}

	private Context context;
	private DBCommand db;

	private String from;

	public void setFrom(String from){
		this.from = from;
	}

	/**
	 * ��ѯ����
	 */
	private List<String> columns = new ArrayList<String>();

	/**
	 * ������ֵ
	 */
	private List<Object> values = new ArrayList<Object>();

	/**
	 * ��ѯ�ı�
	 */
	private List<String> tables = new ArrayList<String>();

	/**
	 * ���в���
	 */
	private List<String> args = new ArrayList<String>();

	/**
	 * ȥ�ظ�����
	 */
	private Set<String> names = new HashSet<String>();

	/**
	 * ��ѯ��������
	 */
	private List<String> Conditions = new ArrayList<String>();
	private List<String> groupbys = new ArrayList<String>();
	private List<String> orderbys = new ArrayList<String>();
	private List<String> havings = new ArrayList<String>();

	/**
	 * ���һ�������ֶ�
	 */
	public void addGroupBy(String column){
		this.groupbys.add(column);
	}

	/**
	 * ���һ����������
	 */
	public void addHaving(String condition){
		this.havings.add(condition);
	}

	/**
	 * ���һ�������ֶ�
	 */
	public void addOrderBy(String column){
		this.orderbys.add(column);
	}

	/**
	 * ���һ�����ڵĲ�ѯ����
	 * 
	 * @param column
	 * @param value
	 *            ֻ�����ֶλ��߲���������@�� void
	 */
	public void addEquals(String column, String value){
		this.Conditions.add(column + "=" + value);
	}

	/**
	 * ֱ�����һ����ѯ����
	 * 
	 * @param condition
	 *            void
	 */
	public void addCondition(String condition){
		this.Conditions.add(condition);
	}

	/**
	 * ��string�ֶμ�һ��like����
	 * 
	 * @param column
	 * @param value
	 *            void
	 */
	public void addLike(String column, String value){
		this.Conditions.add(column + " like '%'+" + value + "+'%'");
	}

	/**
	 * ��һ���ֶ����in����
	 * 
	 * @param column
	 * @param params
	 *            void
	 */
	public void addIn(String column, List<String> params){
		if(params.size() > 0){
			StringBuilder sb = new StringBuilder();
			for(String s : params){
				sb.append("," + s);
			}
			this.Conditions.add(column + " in (" + sb.toString().substring(1) + ")");
		}
	}

	/**
	 * ��һ���ֶ����not in����
	 * 
	 * @param column
	 * @param params
	 *            void
	 */
	public void addNotIn(String column, List<String> params){
		if(params.size() > 0){
			StringBuilder sb = new StringBuilder();
			for(String s : params){
				sb.append("," + s);
			}
			this.Conditions.add(column + " not in (" + sb.toString().substring(1) + ")");
		}
	}

	/**
	 * ���һ��between����
	 * 
	 * @param column
	 * @param begin
	 * @param end
	 *            void
	 */
	public void addBetween(String column, String begin, String end){
		this.Conditions.add(column + " between " + begin + " and " + end);
	}

	/**
	 * ���һ������
	 * 
	 * @param name
	 *            ������
	 * @param type
	 *            ��������
	 * @param value
	 *            ������ֵ
	 * @return boolean
	 */
	public boolean addArgs(String name, String type, Object value){
		if(null != name && name.length() > 0 && null != type && type.length() > 0){
			if(names.add(name)){
				args.add("@" + name + " " + type + " not null ");
				values.add(value);
				return true;
			}
		}
		return false;
	}

	/**
	 * ���һ��Ҫ��ѯ�ı�
	 * 
	 * @param tableName
	 * @param alias
	 *            void
	 */
	public void addTable(String tableName, String alias){
		tables.add(tableName + " as " + alias);
	}

	/**
	 * ���һ��Ҫ��ѯ���ֶμ�����
	 * 
	 * @param column
	 * @param alias
	 * @return boolean
	 */
	public boolean addColumn(String column, String alias){
		if(names.add(column)){
			columns.add(column + " as " + alias);
			return true;
		}
		return false;
	}

	/**
	 * ���һ��Ҫ��ѯ���ֶμ�����
	 * 
	 * @param column
	 * @param alias
	 * @return boolean
	 */
	public boolean addDistinctColumn(String column, String alias){
		if(names.add(column)){
			columns.add(" distinct " + column + " as " + alias);
			return true;
		}
		return false;
	}

	public String getRealDnaSql(){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		for(int i = 0; i < columns.size(); i++){
			String s = columns.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" ," + s);
			}
		}
		sb.append(" from ");
		if(null == this.from){
			for(int i = 0; i < tables.size(); i++){
				String s = tables.get(i);
				if(i == 0){
					sb.append(" " + s);
				}
				else{
					sb.append(" ," + s);
				}
			}
		}
		else{
			sb.append(this.from);
		}
		sb.append(" where 1=1 ");
		for(int i = 0; i < this.Conditions.size(); i++){
			String s = Conditions.get(i);
			sb.append(" and " + s);
		}
		if(this.groupbys.size() > 0){
			sb.append(" group by ");
		}
		for(int i = 0; i < this.groupbys.size(); i++){
			String s = groupbys.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" ," + s);
			}
		}
		if(this.havings.size() > 0){
			sb.append(" having ");
		}
		for(int i = 0; i < this.havings.size(); i++){
			String s = havings.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" and " + s);
			}
		}
		if(this.orderbys.size() > 0){
			sb.append(" order by ");
		}
		for(int i = 0; i < this.orderbys.size(); i++){
			String s = orderbys.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" , " + s);
			}
		}
		return sb.toString();
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("define query " + getTitle() + "(");
		for(int i = 0; i < args.size(); i++){
			String s = args.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" ," + s);
			}
		}
		sb.append(") begin SELECT ");
		for(int i = 0; i < columns.size(); i++){
			String s = columns.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" ," + s);
			}
		}
		if(null == this.from&&CheckIsNull.isNotEmpty(tables)){
			sb.append(" from ");
			for(int i = 0; i < tables.size(); i++){
				String s = tables.get(i);
				if(i == 0){
					sb.append(" " + s);
				}
				else{
					sb.append(" ," + s);
				}
			}
		}
		else if(CheckIsNull.isNotEmpty(this.from)){
			sb.append(" from ");
			sb.append(this.from);
		}
		if(CheckIsNull.isNotEmpty(this.Conditions)){
			sb.append(" where 1=1 ");
		}
		for(int i = 0; i < this.Conditions.size(); i++){
			String s = Conditions.get(i);
			sb.append(" and " + s);
		}
		if(this.groupbys.size() > 0){
			sb.append(" group by ");
		}
		for(int i = 0; i < this.groupbys.size(); i++){
			String s = groupbys.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" ," + s);
			}
		}
		if(this.havings.size() > 0){
			sb.append(" having ");
		}
		for(int i = 0; i < this.havings.size(); i++){
			String s = havings.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" and " + s);
			}
		}
		if(this.orderbys.size() > 0){
			sb.append(" order by ");
		}
		for(int i = 0; i < this.orderbys.size(); i++){
			String s = orderbys.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" , " + s);
			}
		}
		sb.append(" end ");
		return sb.toString();
	}

	private String getTitle(){
		return "T" + new Date().getTime();
	}

	public String getTestStr(){
		StringBuilder sb = new StringBuilder();
		sb.append("define query " + getTitle() + "(");
		for(int i = 0; i < args.size(); i++){
			sb.append("\n");
			String s = args.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" ," + s);
			}
		}
		sb.append(") begin SELECT ");
		for(int i = 0; i < columns.size(); i++){
			sb.append("\n");
			String s = columns.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" ," + s);
			}
		}
		sb.append(" from ");
		if(null == this.from){
			for(int i = 0; i < tables.size(); i++){
				sb.append("\n");
				String s = tables.get(i);
				if(i == 0){
					sb.append(" " + s);
				}
				else{
					sb.append(" ," + s);
				}
			}
		}
		else{
			sb.append(this.from);
		}
		sb.append(" where 1=1 ");
		for(int i = 0; i < this.Conditions.size(); i++){
			sb.append("\n");
			String s = Conditions.get(i);
			sb.append(" and " + s);
		}
		if(this.groupbys.size() > 0){
			sb.append(" group by ");
		}
		for(int i = 0; i < this.groupbys.size(); i++){
			sb.append("\n");
			String s = groupbys.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" ," + s);
			}
		}
		if(this.havings.size() > 0){
			sb.append("\n");
			sb.append(" having ");
		}
		for(int i = 0; i < this.havings.size(); i++){
			sb.append("\n");
			String s = havings.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" and " + s);
			}
		}
		if(this.orderbys.size() > 0){
			sb.append("\n");
			sb.append(" order by ");
		}
		for(int i = 0; i < this.orderbys.size(); i++){
			sb.append("\n");
			String s = orderbys.get(i);
			if(i == 0){
				sb.append(" " + s);
			}
			else{
				sb.append(" and " + s);
			}
		}
		sb.append("\n");
		sb.append(" end ");
		return sb.toString();
	}

	/**
	 * ������ֵ void
	 */
	private void fillArgsValue(){
		if(this.values.size() > 0){
			for(int i = 0; i < this.values.size(); i++){
				db.setArgumentValue(i, this.values.get(i));
			}
		}
	}

	/**
	 * �õ����ս��
	 * 
	 * @return RecordSet
	 */
	public RecordSet getRecord(){
		this.db = context.prepareStatement(this.toString());
		this.fillArgsValue();
		RecordSet rs = db.executeQuery();
		db.unuse();
		return rs;
	}

	/**
	 * �õ����ս��
	 * 
	 * @return RecordSet
	 */
	public RecordSet getRecordLimit(int offset, int pageSize){
		this.db = context.prepareStatement(this.toString());
		this.fillArgsValue();
		RecordSet rs = db.executeQueryLimit(offset, pageSize);
		db.unuse();
		return rs;
	}

	/**
	 * ���һ�������ڵĲ�ѯ����
	 * 
	 * @param column
	 * @param value
	 *            ֻ�����ֶλ��߲���������@�� void
	 */
	public void addNotEquals(String column, String value){
		this.Conditions.add(column + "<>" + value);
	}

	/**
	 * ���һ�����ڵĲ�ѯ����
	 * 
	 * @param column
	 * @param value
	 *            ֻ�����ֶλ��߲���������@�� void
	 */
	public void addGreaterThan(String column, String value){
		this.Conditions.add(column + ">" + value);
	}

	/**
	 * ���һ�����ڵ��ڵĲ�ѯ����
	 * 
	 * @param column
	 * @param value
	 *            ֻ�����ֶλ��߲���������@�� void
	 */
	public void addGreaterThanOrEquals(String column, String value){
		this.Conditions.add(column + ">=" + value);
	}

	/**
	 * ���һ��С�ڵĲ�ѯ����
	 * 
	 * @param column
	 * @param value
	 *            ֻ�����ֶλ��߲���������@�� void
	 */
	public void addLessThan(String column, String value){
		this.Conditions.add(column + "<" + value);
	}

	/**
	 * ���һ��С�ڵ��ڵĲ�ѯ����
	 * 
	 * @param column
	 * @param value
	 *            ֻ�����ֶλ��߲���������@�� void
	 */
	public void addLessThanOrEquals(String column, String value){
		this.Conditions.add(column + "<=" + value);
	}

	public final String STRING = "string";
	public final String guid = "guid";
	public final String DOUBLE = "double";
	public final String INT = "int";
	public final String LONG = "long";
	public final String DATE = "date";
	public final String BOOLEAN = "boolean";

}
