/**
 * 
 */
package com.spark.psi.report.key;

import com.jiuqi.dna.core.type.GUID;

/**
 *
 */
public class MonitorTargetKey{

	private boolean isThisYear;

	private GUID id;

	private int maxSize;

	public MonitorTargetKey(boolean isThisYear, GUID id){
		this.isThisYear = isThisYear;
		this.id = id;
	}

	public MonitorTargetKey(GUID id, int maxSize){
		this.isThisYear = false;
		this.id = id;
		this.maxSize = maxSize;
	}

	/**
	 * @return the isThisYear
	 */
	public boolean isThisYear(){
		return isThisYear;
	}

	/**
	 * @return the id
	 */
	public GUID getId(){
		return id;
	}

	/**
	 * @return the maxSize
	 */
	public int getMaxSize(){
		return maxSize;
	}
}
