/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.outstorage.dbox
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-12-15       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.outstorage.dbox;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO 类描述</p>
 *


 *
 * @author 王志坚
 * @version 2011-12-15
 */

public class CreateDataBox{

	private GUID createGuid;
	
	private GUID deptGuid;
	
	private long createTime;

	/**
     * @return the createGuid
     */
    public GUID getCreateGuid(){
    	return createGuid;
    }

	/**
     * @param createGuid the createGuid to set
     */
    public void setCreateGuid(GUID createGuid){
    	this.createGuid = createGuid;
    }

	/**
     * @return the deptGuid
     */
    public GUID getDeptGuid(){
    	return deptGuid;
    }

	/**
     * @param deptGuid the deptGuid to set
     */
    public void setDeptGuid(GUID deptGuid){
    	this.deptGuid = deptGuid;
    }

	/**
     * @return the createTime
     */
    public long getCreateTime(){
    	return createTime;
    }

	/**
     * @param createTime the createTime to set
     */
    public void setCreateTime(long createTime){
    	this.createTime = createTime;
    }
}
