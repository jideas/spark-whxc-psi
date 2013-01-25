package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.publish.GoodsStatus;

/**
 * <p>商品停售检查</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-29
 */
class GoodsStopImpl extends ServiceCheckImpl{


	public GoodsStopImpl(Context context, CheckParam param) {
		super(context, param);
	}

	@Override
	protected boolean doCheck(CheckParam param) {
		this.goodsItem = context.find(GoodsItem.class, param.getId());
		return GoodsStatus.STOP_SALE == this.goodsItem.getStatus();
	}

}
