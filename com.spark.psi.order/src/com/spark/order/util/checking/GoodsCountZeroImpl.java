package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;

/**
 * <p>数量大于零校验</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-29
 */
class GoodsCountZeroImpl extends ServiceCheckImpl{


	public GoodsCountZeroImpl(Context context, CheckParam param) {
		super(context, param);
	}

	@Override
	protected boolean doCheck(CheckParam param) {
		return 0 >= param.getCount();
	}

}
