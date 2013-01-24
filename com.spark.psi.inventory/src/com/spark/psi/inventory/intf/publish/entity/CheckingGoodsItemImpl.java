/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.publish.entity
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-13       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.constant.GoodsScale;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-13
 */

public class CheckingGoodsItemImpl implements CheckingGoodsItem{

	/**
	 * 此次出入库数量
	 */
	private double checkCount;
	/**
	 * 已出入库数量
	 */
	private double checkedCount;
	/**
	 * 需出入库的数量
	 */
	private double checkingCount;
	/**
	 * 单价
	 */
	private double price;
	/**
	 * 商品条目ID
	 */
	private GUID goodsItemId;
	/**
	 * 数量小数位
	 */
	private int countDecimal;
	
	private double inspectCount;

	/**
	 * RECID
	 */
	private GUID id;

	public double getCheckCount(){
		return checkCount;
	}

	public void setCheckCount(double checkCount){
		this.checkCount = checkCount;
	}

	public double getCheckedCount(){
		return checkedCount;
	}

	public void setCheckedCount(double checkedCount){
		this.checkedCount = checkedCount;
	}

	public double getCheckingCount(){
		return checkingCount;
	}

	public double getInspectCount() {
		return inspectCount;
	}

	public void setInspectCount(double inspectCount) {
		this.inspectCount = inspectCount;
	}

	public void setCheckingCount(double checkingCount){
		this.checkingCount = checkingCount;
	}

	public double getPrice(){
		return price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public GUID getGoodsItemId(){
		return goodsItemId;
	}

	public void setGoodsItemId(GUID goodsItemId){
		this.goodsItemId = goodsItemId;
	}

	public void setId(GUID id){
		this.id = id;
	}

	public GUID getId(){
		return id;
	}

	public int getScale(){
		return GoodsScale.PSI_SCALE;
	}

	public void setCountDecimal(int countDecimal){
		this.countDecimal = countDecimal;
	}

}
