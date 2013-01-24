/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.outstorage.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-17       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.intf.key.outstorage.OutstorageKey;
import com.spark.psi.inventory.intf.util.outstorage.dbox.GoodsOutstoInfo;

/**
 * <p>TODO 类描述</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-17
 */

public class OutInfoService extends Service{

	public OutInfoService(){
		super("OutInfoService");
	}

	@Publish
	protected class OutInfoProvider extends OneKeyResultListProvider<GoodsOutstoInfo, GUID>{

		@Override
		protected void provide(Context context, GUID recid, List<GoodsOutstoInfo> list) throws Throwable{
			OutstorageKey key = new OutstorageKey();
			key.setRelaOrdGuid(recid);
			list.addAll(context.getList(GoodsOutstoInfo.class, key));
		}

	}
}
