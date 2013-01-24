/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.goods
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-3    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.task.goods
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-5-3    jiuqi
 * ============================================================*/

package com.spark.psi.base.task.goods;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.goods.entity.GoodsTraderLogItem.TraderType;

/**
 * <p>������Ʒ���׼�¼</p>
 *


 *
 
 * @version 2012-5-3
 */

public class UpdateGoodsTraderLogTask extends SimpleTask{
	
	private GUID goodsItemId,pratnerId;
	
	private double price;
	
	private double count;
	
	private TraderType type;
	
	public UpdateGoodsTraderLogTask(TraderType type,GUID goodsItemId,GUID partnerId,double count,double price){
		this.count = count;
		this.pratnerId = partnerId;
		this.goodsItemId = goodsItemId;
		this.price = price;
		this.type =type;
    }

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }

	public GUID getPratnerId(){
    	return pratnerId;
    }

	public double getCount(){
    	return count;
    }

	public double getPrice(){
    	return price;
    }

	public TraderType getType(){
    	return type;
    }
	
	
	
}
