/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.goods
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-14    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.goods
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-14    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.goods;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�޸���Ʒ����ɹ�����</p>
 * 
 */
//Deprecated 2012-10-14
@Deprecated
public class UpdateGoodsItemRecentPurchasePriceTask extends SimpleTask{
	
	private GUID id;
	
	private double price;
	
	public UpdateGoodsItemRecentPurchasePriceTask(GUID id,double price){
	    this.id = id;
	    this.price = price;
    }

	public GUID getId(){
    	return id;
    }

	public double getPrice(){
    	return price;
    }
	
	
	
}
