/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.publish.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-6-8    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.publish.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-6-8    jiuqi
 * ============================================================*/

package com.spark.uac.publish.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�༭ҵ��������⻧��Ϣ</p>
 *


 *
 
 * @version 2012-6-8
 */

public class UpdateSubServerTenantTask extends SimpleTask{
	
	private GUID id;
	private String config;
	
	public UpdateSubServerTenantTask(GUID id,String config){
		this.id = id;
		this.config = config;
    }

	public GUID getId(){
    	return id;
    }

	public String getConfig(){
    	return config;
    }
	
	
}
