package com.spark.order.intf.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;

/**
 * <p>更新商品确认出入库数量</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-17
 */
public class ModifyGoodsOkCountTask extends SimpleTask implements ITaskResult{
	private BillsEnum billsEnum;//单据类型
	private GUID billsGuid;//单据GUID
	private double oldCount;//原始数量
	private double changeCount;//本次变化数量
	private Double changeAmount; //本次变化金额
	private StatusEnum billsstatus = null;//状态
	public int lenght;//返回条数
	/**
	 * 
	 * @return double
	 */
	public Double getChangeAmount() {
		return changeAmount;
	}
	/**
	 * 
	 * @param changeAmount void
	 */
	public void setChangeAmount(Double changeAmount) {
		this.changeAmount = changeAmount;
	}
	/**
	 * @return the billsEnum
	 */
	public BillsEnum getBillsEnum() {
		return billsEnum;
	}
	/**
	 * @return the billsGuid
	 */
	public GUID getBillsGuid() {
		return billsGuid;
	}
	/**
	 * @return the oldCount
	 */
	public double getOldCount() {
		return oldCount;
	}  
	/**
     * @return the billsstatus
     */
    public StatusEnum getBillsstatus(){
    	return billsstatus;
    }
	/**
     * @param billsstatus the billsstatus to set
     */
    public void setBillsstatus(StatusEnum billsstatus){
    	this.billsstatus = billsstatus;
    }
	/**
	 * @return the changeCount
	 */
	public double getChangeCount() {
		return changeCount;
	}
	/**
	 * @param billsEnum the billsEnum to set
	 */
	public void setBillsEnum(BillsEnum billsEnum) {
		this.billsEnum = billsEnum;
	}
	/**
	 * @param billsGuid the billsGuid to set
	 */
	public void setBillsGuid(GUID billsGuid) {
		this.billsGuid = billsGuid;
	}
	/**
	 * @param oldCount the oldCount to set
	 */
	public void setOldCount(double oldCount) {
		this.oldCount = oldCount;
	}
	/**
	 * @param changeCount the changeCount to set
	 */
	public void setChangeCount(double changeCount) {
		this.changeCount = changeCount;
	}
	public boolean isSucceed() {
		return 1 == lenght;
	}
	public int lenght() {
		return lenght;
	}
}
