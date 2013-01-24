/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.Outstorage.util
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.outstorage.dbox;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>流程改变时的数据实体</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-10
 */

public class OutstoProcess{

	private GUID recid;
	private String prestatus;
	private String outstoState;
	private String sureOutsto;
	private long allOutstoDate;

	private String takePerson;//提货人	V(20)
	private String takeUnit;//提货单位	V(200)
	private String vouchersNo;//凭证号	V(100)

	/**
     * @return the takePerson
     */
    public String getTakePerson(){
    	return takePerson;
    }

	/**
     * @param takePerson the takePerson to set
     */
    public void setTakePerson(String takePerson){
    	this.takePerson = takePerson;
    }

	/**
     * @return the takeUnit
     */
    public String getTakeUnit(){
    	return takeUnit;
    }

	/**
     * @param takeUnit the takeUnit to set
     */
    public void setTakeUnit(String takeUnit){
    	this.takeUnit = takeUnit;
    }

	/**
     * @return the vouchersNo
     */
    public String getVouchersNo(){
    	return vouchersNo;
    }

	/**
     * @param vouchersNo the vouchersNo to set
     */
    public void setVouchersNo(String vouchersNo){
    	this.vouchersNo = vouchersNo;
    }

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
	 * @return the outstoState
	 */
	public String getOutstoState(){
		return outstoState;
	}

	/**
	 * @param outstoState the outstoState to set
	 */
	public void setOutstoState(String outstoState){
		this.outstoState = outstoState;
	}

	/**
	 * @return the sureOutsto
	 */
	public String getSureOutsto(){
		return sureOutsto;
	}

	/**
	 * @param sureOutsto the sureOutsto to set
	 */
	public void setSureOutsto(String sureOutsto){
		this.sureOutsto = sureOutsto;
	}

	/**
	 * @return the allOutstoDate
	 */
	public long getAllOutstoDate(){
		return allOutstoDate;
	}

	/**
	 * @param allOutstoDate the allOutstoDate to set
	 */
	public void setAllOutstoDate(long allOutstoDate){
		this.allOutstoDate = allOutstoDate;
	}
}
