/**
 * 
 */
package com.spark.psi.report.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spark.common.utils.character.CheckIsNull;

/**
 *
 */
public class ReportChartDatas{

	@SuppressWarnings("unchecked")
	public ReportChartDatas(List<ReportResult> list, Enum target){
		this.list = list;
		this.map = new HashMap<Object, ReportResult>();
		for(ReportResult rr : list){
			map.put(rr.getTargetValue(target), rr);
		}
	}

	private Map<Object, ReportResult> map;
	private List<ReportResult> list;

	public ReportResult get(Object key){
		return this.map.get(key);
	}

	public boolean isEmpty(){
		return CheckIsNull.isEmpty(this.map);
	}

	/**
     * @return the list
     */
    public List<ReportResult> getList(){
    	return list;
    }
}
