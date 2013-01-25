package com.spark.order.util.checking;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.base.Store;
import com.spark.psi.base.Tenant;

/**
 * <p>只有一个仓库校验</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-29
 */
class StoreOneImpl extends ServiceCheckImpl{

	public StoreOneImpl(Context context, CheckParam param) {
		super(context, param);
	}

	@Override
	protected boolean doCheck(CheckParam param) {
		List<Store> list = OrderUtil.getStore(context);
		if(1 == list.size()){
			this.store = list.get(0);
		}
		Tenant tenant = context.find(Tenant.class);
		return 1 == list.size() && !tenant.isDirectSupply();
	}
}
