/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.account.service.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-1       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.service.pub;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.account.intf.entity.dealing.Dealing;
import com.spark.psi.base.BalanceAmount;
import com.spark.psi.base.key.GetBalanceAmountByPartnerKey;

/**
 * <p>模块间查询专用</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-4-1
 */

public class AccountModuleQueryService extends Service{

	protected AccountModuleQueryService(){
		super("com.spark.psi.account.service.pub.AccountModuleQueryService");
	}

	// 查询应收/付金额
	@Publish
	protected class GetBalanceAmount extends OneKeyResultProvider<BalanceAmount, GetBalanceAmountByPartnerKey>{

		@Override
		protected BalanceAmount provide(Context context, GetBalanceAmountByPartnerKey key) throws Throwable{
			Dealing dealing = context.find(Dealing.class, key.getId());
			if(CheckIsNull.isEmpty(dealing)){
				return null;
			}
			BalanceAmount ba = new BalanceAmount();
			ba.setDueAmount(dealing.getAmount());
			return ba;
		}
	}
}
