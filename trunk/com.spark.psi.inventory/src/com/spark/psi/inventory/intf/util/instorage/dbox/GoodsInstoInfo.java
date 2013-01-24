/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.outstorage.util
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-17       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.util.instorage.dbox;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO 类描述</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-17
 */

public class GoodsInstoInfo{

	/**
	 * 
	 */
	public GoodsInstoInfo(){ 
	} 
	private GUID goodsGuid;
	private String goodsAttr;
	
	private String goodsName;
	
	private String goodsUnit;
	
	private int goodsScale;
	
	private List<GoodsInstoInfoDet> detList;
	
	/**
	 * 添加明细
	 * @param det void
	 */
	public void addDet(GoodsInstoInfoDet det){
		if(null==detList){
			detList = new ArrayList<GoodsInstoInfoDet>();
		}
		this.detList.add(det);
	}

	/**
     * @return the goodsGuid
     */
    public GUID getGoodsGuid(){
    	return goodsGuid;
    }

	/**
     * @param goodsGuid the goodsGuid to set
     */
    public void setGoodsGuid(GUID goodsGuid){
    	this.goodsGuid = goodsGuid;
    }

	/**
     * @return the goodsAttr
     */
    public String getGoodsAttr(){
    	return goodsAttr;
    }

	/**
     * @param goodsAttr the goodsAttr to set
     */
    public void setGoodsAttr(String goodsAttr){
    	this.goodsAttr = goodsAttr;
    }

	/**
     * @return the goodsName
     */
    public String getGoodsName(){
    	return goodsName;
    }

	/**
     * @param goodsName the goodsName to set
     */
    public void setGoodsName(String goodsName){
    	this.goodsName = goodsName;
    } 

	/**
     * @return the goodsUnit
     */
    public String getGoodsUnit(){
    	return goodsUnit;
    }

	/**
     * @param goodsUnit the goodsUnit to set
     */
    public void setGoodsUnit(String goodsUnit){
    	this.goodsUnit = goodsUnit;
    }

	/**
     * @return the goodsScale
     */
    public int getGoodsScale(){
    	return goodsScale;
    }

	/**
     * @param goodsScale the goodsScale to set
     */
    public void setGoodsScale(int goodsScale){
    	this.goodsScale = goodsScale;
    }

	/**
     * @return the detList
     */
    public List<GoodsInstoInfoDet> getDetList(){
    	return detList;
    }

	/**
     * @param detList the detList to set
     */
    public void setDetList(List<GoodsInstoInfoDet> detList){
    	this.detList = detList;
    }
	
}
