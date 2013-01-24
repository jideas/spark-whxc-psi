/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.util
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.instorage.dbox;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>流程改变时的数据实体</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-10
 */

public class InstoProcess{

	private GUID recid;
	private String prestatus;
	private String instoState;
	private String sureInsto; 
	private long allInstoDate;
	/**
     * @return the recid
     */
    public GUID getRecid(){
    	return recid;
    }
	/**
     * @param recid the recid to set
     */
    public void setRecid(GUID recid){
    	this.recid = recid;
    }
	/**
     * @return the prestatus
     */
    public String getPrestatus(){
    	return prestatus;
    }
	/**
     * @param prestatus the prestatus to set
     */
    public void setPrestatus(String prestatus){
    	this.prestatus = prestatus;
    }
	/**
     * @return the instoState
     */
    public String getInstoState(){
    	return instoState;
    }
	/**
     * @param instoState the instoState to set
     */
    public void setInstoState(String instoState){
    	this.instoState = instoState;
    }
	/**
     * @return the sureInsto
     */
    public String getSureInsto(){
    	return sureInsto;
    }
	/**
     * @param sureInsto the sureInsto to set
     */
    public void setSureInsto(String sureInsto){
    	this.sureInsto = sureInsto;
    } 
	/**
     * @return the allInstoDate
     */
    public long getAllInstoDate(){
    	return allInstoDate;
    }
	/**
     * @param allInstoDate the allInstoDate to set
     */
    public void setAllInstoDate(long allInstoDate){
    	this.allInstoDate = allInstoDate;
    }
}
