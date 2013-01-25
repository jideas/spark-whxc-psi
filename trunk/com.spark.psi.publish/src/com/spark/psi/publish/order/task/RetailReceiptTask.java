/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.PsiSimpleTask;
import com.spark.psi.publish.order.task.CreateRetailOrderTask.RetailOrderGoodsItem;

/**
 * <p>���۵��տ�</p>
 *


 *
 
 * @version 2012-3-6
 */

public class RetailReceiptTask extends PsiSimpleTask<RetailReceiptTask.Error>{
	
	private GUID id;
	public static enum Error{
		NotHaveStore;
	}
	
	/**
	 * ���ʽ
	 */
	private DealingsWay dealingsWay;
	private double discountAmount;//�����ۿ�
	private double amount;

	private String memo;
	private RetailOrderGoodsItem[] retailOrderGoodsItems;  //������Ʒ��ϸ	

//	public RetailReceiptTask(final GUID id,final DealingsWay dealingsWay){
//		this.id = id;
//		this.dealingsWay = dealingsWay;
//	}

	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public void setDealingsWay(DealingsWay dealingsWay) {
		this.dealingsWay = dealingsWay;
	}

	public GUID getId(){
    	return id;
    }

	public DealingsWay getDealingsWay(){
    	return dealingsWay;
    }

	public RetailOrderGoodsItem[] getRetailOrderGoodsItems(){
    	return retailOrderGoodsItems;
    }

	public void setRetailOrderGoodsItems(
            RetailOrderGoodsItem[] retailOrderGoodsItems)
    {
    	this.retailOrderGoodsItems = retailOrderGoodsItems;
    }

	
		
	
}
