package com.spark.order.util.checking;

import com.jiuqi.dna.core.Context;
import com.spark.psi.base.Store;
import com.spark.psi.publish.StoreStatus;

/**
 * <p>仓库停用校验</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-29
 */
class StoreStopImpl extends ServiceCheckImpl{

	public StoreStopImpl(Context context, CheckParam param) {
		super(context, param);
	}

	@Override
	protected boolean doCheck(CheckParam param) {
		Store store = context.find(Store.class, param.getId());
		return StoreStatus.STOP == store.getStatus();
	}

}
