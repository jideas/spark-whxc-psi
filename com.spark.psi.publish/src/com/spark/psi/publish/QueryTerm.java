package com.spark.psi.publish;

/**
 * 查询时期对象（本周、上周、本月、上月、本季、本年、2011、2010...）<br>
 * 查询方法：<br>
 * (1)直接查询QueryTerm列表
 */
public class QueryTerm {
	
	/**
	 * 本月
	 */
	public static final String ID_MONTH = "本月";
	
	/**
	 * 上月
	 */
	public static final String ID_LAST_MONTH = "上月";
	
	/**
	 * 本周
	 */
	public static final String ID_WEEK = "本周";
	
	/**
	 * 上周
	 */
	public static final String ID_LAST_WEEK = "上周";
	
	/**
	 * 本季
	 */
	public static final String ID_QUARTER = "本季";
	
	/**
	 * 本年
	 */
	public static final String ID_YEAR = "本年";
	
	

	/**
	 * 开始时间
	 */
	private long startTime;

	/**
	 * 结束时间
	 */
	private long endTime;

	/**
	 * 时期名称
	 */
	protected String name;

	/**
	 * 构造函数
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
	 * 得到本时期的开始时间
	 * 
	 * @return
	 */
	public long getStartTime() {
		return this.startTime;
	}

	/**
	 * 得到本时期的结束时间
	 * 
	 * @return
	 */
	public long getEndTime() {
		return this.endTime;
	}

}
