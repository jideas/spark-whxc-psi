package com.spark.oms.publish;

/**
 * ��ѯʱ�ڶ��󣨱��ܡ����ܡ����¡����¡����������ꡢ2011��2010...��<br>
 * ��ѯ������<br>
 * (1)ֱ�Ӳ�ѯQueryTerm�б�
 */
public class QueryTerm {

	/**
	 * ��ʼʱ��
	 */
	private long startTime;

	/**
	 * ����ʱ��
	 */
	private long endTime;

	/**
	 * ʱ������
	 */
	protected String name;

	/**
	 * ���캯��
	 * 
	 * @param startTime
	 * @param endTime
	 * @param name
	 */
	public QueryTerm(long startTime, long endTime, String name) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * �õ���ʱ�ڵĿ�ʼʱ��
	 * 
	 * @return
	 */
	public long getStartTime() {
		return this.startTime;
	}

	/**
	 * �õ���ʱ�ڵĽ���ʱ��
	 * 
	 * @return
	 */
	public long getEndTime() {
		return this.endTime;
	}

}
