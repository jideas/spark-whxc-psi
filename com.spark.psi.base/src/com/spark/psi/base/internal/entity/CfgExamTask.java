/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-22    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.entity
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-22    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ApprovalConfig.Mode;

/**
 * <p>�⻧���������޸�</p>
 *


 *
 
 * @version 2012-3-22
 */

public class CfgExamTask extends SimpleTask{
	
	private GUID tenantsGuid;
	
	private final Mode modeid;
	
	private boolean isOpenExam;
	
	private double maxAmount;
	
	public CfgExamTask(Mode modeid){
		this.modeid = modeid;
	}

	public GUID getTenantsGuid(){
    	return tenantsGuid;
    }

	public void setTenantsGuid(GUID tenantsGuid){
    	this.tenantsGuid = tenantsGuid;
    }

	public Mode getModeid(){
    	return modeid;
    }

	public boolean isOpenExam(){
    	return isOpenExam;
    }

	public void setOpenExam(boolean isOpenExam){
    	this.isOpenExam = isOpenExam;
    }

	public double getMaxAmount(){
    	return maxAmount;
    }

	public void setMaxAmount(double maxAmount){
    	this.maxAmount = maxAmount;
    }

	
	

	
}
