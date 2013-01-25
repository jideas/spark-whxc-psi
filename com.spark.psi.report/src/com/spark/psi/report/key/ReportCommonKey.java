package com.spark.psi.report.key;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.report.constant.SubjectEnum;
import com.spark.psi.report.utils.Condition;

/**
 * 通用数据查询Key
 */
public class ReportCommonKey{

	/**
	 * 
	 */
	public ReportCommonKey(SubjectEnum subject){
		this.subject = subject;
	}

	/**
	 * 查询主体
	 */
	private SubjectEnum subject;
	/**
	 * 指标组
	 */
	@SuppressWarnings("unchecked")
	private Set<Enum> targets;
	/**
	 * 过滤条件组
	 */
	private List<Condition> conditions;

	// 按照区间查询
	private long beginDate;
	private long endDate;
	// 最多条数
	private int maxCount;
	// 排序指标
	private String orderTarget;
	private boolean isOrderDesc, isDateColumn;

	/**
	 * @return the orderTarget
	 */
	public String getOrderTarget(){
		return orderTarget;
	}

	/**
	 * @param orderTarget the orderTarget to set
	 */
	public void setOrderTarget(String orderTarget){
		this.orderTarget = orderTarget;
	}

	/**
	 * @return the isOrderDesc
	 */
	public boolean isOrderDesc(){
		return isOrderDesc;
	}

	/**
	 * @param isOrderDesc the isOrderDesc to set
	 */
	public void setOrderDesc(boolean isOrderDesc){
		this.isOrderDesc = isOrderDesc;
	}

	/**
	 * @return the isDateColumn
	 */
	public boolean isDateColumn(){
		return isDateColumn;
	}

	/**
	 * @param isDateColumn the isDateColumn to set
	 */
	public void setDateColumn(boolean isDateColumn){
		this.isDateColumn = isDateColumn;
	}

	/**
	 * @return the maxCount
	 */
	public int getMaxCount(){
		return maxCount;
	}

	/**
	 * @param maxCount
	 *            the maxCount to set
	 */
	public void setMaxCount(int maxCount){
		this.maxCount = maxCount;
	}

	/**
	 * @return the conditions
	 */
	public List<Condition> getConditions(){
		return conditions;
	}

	/**
	 * @param conditions
	 *            the conditions to set
	 */
	public void setConditions(Condition... conditions){
		if(null == this.conditions){
			this.conditions = new ArrayList<Condition>();
		} 
		for(Condition c : conditions){
			if(null==c){
				continue;
			}
			this.conditions.add(c);
		}
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(List<Condition> conditions){
		this.conditions = conditions;
	}

	/**
	 * @return the beginDate
	 */
	public long getBeginDate(){
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(long beginDate){
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public long getEndDate(){
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(long endDate){
		this.endDate = endDate;
	}

	/**
	 * 添加一个指标
	 */
	@SuppressWarnings("unchecked")
	public void addTarget(Enum target){
		if(CheckIsNull.isEmpty(target)){
			return;
		}
		if(null == targets){
			targets = new HashSet<Enum>();
		}
		targets.add(target);
	}

	/**
	 * @return the subject
	 */
	public SubjectEnum getSubject(){
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(SubjectEnum subject){
		this.subject = subject;
	}

	/**
	 * @return the targets
	 */
	@SuppressWarnings("unchecked")
	public List<Enum> getTargets(){
		if(CheckIsNull.isEmpty(targets)){
			return null;
		}
		return new ArrayList<Enum>(targets);
	}

}
