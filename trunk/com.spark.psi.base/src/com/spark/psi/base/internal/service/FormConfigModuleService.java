/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-12    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-12    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.CodeBuilder;

/**
 * <p>�������� ����ڲ�����</p>
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
	 * <p>�����µĵ��ݱ��</p>
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
