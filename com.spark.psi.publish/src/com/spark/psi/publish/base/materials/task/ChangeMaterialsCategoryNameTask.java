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

package com.spark.psi.publish.base.materials.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�޸Ĳ��Ϸ�������</p>
 *
 */

public class ChangeMaterialsCategoryNameTask extends SimpleTask{
	
	private GUID categoryId;
	
	private String name;
	
	public ChangeMaterialsCategoryNameTask(GUID categoryId,String name){
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
