package com.spark.order.util.dnaSql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;

/**
 * <p>DNA SQL</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
abstract class DnaSqlImpl implements IDnaSql{
	enum DnaSqlType{
		Delete("define delete D_Order"),
		Update("define update U_Order"),
		Query("define query Q_Order");
		private String value;
		private DnaSqlType(String value){
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * ������
	 */
	protected Context context;
	/**
	 * ����ֵList
	 */
	private final List<Object> paramValue = new ArrayList<Object>();
	/**
	 * ������־List
	 */
	private final StringBuilder paramCode = new StringBuilder(); 
	private String init_sql;
	/**
	 * �������� 
	 */
	private final Map<String, Object> paramMap = new HashMap<String, Object>();
	
	/**
	 * @param context
	 */
	public DnaSqlImpl(Context context) {
		this.context = context;
//		initParam();
	}

	private final static String douhao = ",";
	/**
	 * ��Ӳ�����־
	 */
	private void addParamCode(String code){
		paramCode.append(code);
		paramCode.append(douhao);
	}
	

	/**
	 * ��Ӳ���ֵ
	 *  void
	 */
	private void addParamValue(Object obj) {
		paramValue.add(obj);
	}
	/**
	 * ������ʼ��
	 *  void
	 */
	private void initParam() {
		if(isInit){
			return;
		}
		this.init_sql = getSql();
		for(String key : paramMap.keySet()){
			addParamCode(key);
			addParamValue(paramMap.get(key));
		}
		paramCode.deleteCharAt(paramCode.length()-1);
		isInit = true;
	}
	
	private boolean isInit = false;

	public DBCommand getDBCommand() {
		initParam();
		return context.prepareStatement(getDnaSql(init_sql));
	}
	protected String getDnaSql(String init_sql) {
		StringBuilder dnaSql = new StringBuilder(getDnaSqlTitle());
		dnaSql.append(" ( ");
		dnaSql.append(paramCode);
		dnaSql.append(" ) \n");
		dnaSql.append(" begin \n");
		dnaSql.append(init_sql);
		dnaSql.append(" \n end ");
		return dnaSql.toString();
	}
	
	/**
	 * ���DNA SQĻͷ
	 * @return String
	 */
	protected abstract String getDnaSqlTitle();
	/**
	 * ���ԭ��Sql
	 * @return String
	 */
	protected abstract String getSql();
	
	/**
	 * ��Ӳ���
	 * @param code
	 * @param value void
	 */
	protected void addParam(String code, Object value){
		if(!paramMap.containsKey(code)){
			paramMap.put(code, value);
		}
	}
	
	public Object[] getParams() {
		return paramValue.toArray();
	}
	
	/**
	 * DBCommand �������
	 * @param db void
	 */
	private void setDBVaules(DBCommand db){
		for(int i=0; i<paramValue.size(); i++){
			db.setArgumentValue(i, paramValue.get(i));
		}
	}

	public RecordSet executeQuery() {
		DBCommand db = getDBCommand();
		setDBVaules(db);
		return db.executeQuery();
	}

	public RecordSet executeQuery(long offset, long rowCount) {
		DBCommand db = getDBCommand();
		setDBVaules(db);
		return db.executeQueryLimit(offset, rowCount);
	}

	public int rowCountOf() {
		DBCommand db = getDBCommand();
		setDBVaules(db);
		return db.rowCountOf();
	}

	public int executeUpdate() {
		DBCommand db = getDBCommand();
		setDBVaules(db);
		return db.executeUpdate();
	}
	
	public DBCommand getDBCommand_FinishedParams() {
		DBCommand db = getDBCommand();
		setDBVaules(db);
		return db;
	}

}
