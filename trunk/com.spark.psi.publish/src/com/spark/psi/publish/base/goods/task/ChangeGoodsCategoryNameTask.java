/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.goods.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.goods.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�޸���Ʒ��������</p>
 *


 *
 
 * @version 2012-3-31
 */

public class ChangeGoodsCategoryNameTask extends SimpleTask{
	
	private GUID categoryId;
	
	private String name;
	
	public ChangeGoodsCategoryNameTask(GUID categoryId,String name){
		this.categoryId = categoryId;
		this.name = name;
	}

	public GUID getCategoryId(){
    	return categoryId;
    }

	public String getName(){
    	return name;
    }
	
	
}
