/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-12    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-12    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.CodeBuilder;

/**
 * <p>单据配置 相关内部服务</p>
 *


 *
 
 * @version 2012-3-12
 */

public class FormConfigModuleService extends Service{

	protected FormConfigModuleService(){
	    super("com.spark.psi.base.internal.service.FormConfigModuleService");
    }

	/**
	 * 
	 * <p>生成新的单据编号</p>
	 *
	
	
	 *
	 
	 * @version 2012-3-12
	 */
	protected class BuildNewCodeProvider extends OneKeyResultProvider<String, CodeBuilder>{

		@Override
        protected String provide(Context context, CodeBuilder key)
                throws Throwable
        {
	        // TODO Auto-generated method stub
	        return null;
        }
		
	}
	
}
