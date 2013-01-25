package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.promotion.intf.Promotion2;

/**
 * <p>��Ʒ�������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-29
 */
class GoodsPromotionImpl extends ServiceCheckImpl{

	public GoodsPromotionImpl(Context context, CheckParam param) {
		super(context, param);
	}

	@Override
	protected boolean doCheck(CheckParam param) {
		GUID id = param.getId();
		this.promotion = context.find(Promotion2.class, id);
		return param.getMoney() != this.promotion.getPrice_promotion() || 
			param.getCount()>(this.promotion.getPromotionCount()-this.promotion.getSaledCount());
	}

}
