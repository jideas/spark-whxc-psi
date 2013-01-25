package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.spark.order.service.util.OrderUtil;

/**
 * <p>�ɹ�Ԥ����Ʒ���(�����㽻������)</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-29
 */
class PurchaseWarningImpl extends ServiceCheckImpl{

	public PurchaseWarningImpl(Context context, CheckParam param) {
		super(context, param);
	}

	@Override
	protected boolean doCheck(CheckParam param) {
		//���βɹ�<����������-�ɹ�������-���������
		this.inventory = OrderUtil.getInventory(context, param.getId2(), param.getId());
		return param.getCount()<0 || 
			param.getCount()<this.inventory.getAmount()
			-this.inventory.getOnWayCount()
			-OrderUtil.getGoodsBuyingCount(context, param.getId(), param.getId2())
			-this.inventory.getCount();
	}

}
