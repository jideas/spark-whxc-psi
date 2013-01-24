package com.spark.psi.base.internal.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ApprovalConfig.Mode;

@StructClass
public class CfgExam {
	
	private GUID recid;
	
	private GUID tenantsGuid;
	
	private String modeid;
	
	private boolean isOpenExam = true;
	
	private double maxAmount = 0;
	
	private String updatePerson;
	
	private long updateDate;

	public CfgExam(GUID tenantsGuid2, Mode modeid){
	    this.tenantsGuid = tenantsGuid2;
	    this.modeid = modeid.getId();
	    this.setOpenExam(true);
	    this.setMaxAmount(0);
	    this.setRecid(GUID.randomID());
    }
	
	public CfgExam(){
		
	}

	public GUID getRecid(){
    	return recid;
    }

	public GUID getTenantsGuid(){
    	return tenantsGuid;
    }

	public String getModeid(){
    	return modeid;
    }

	public boolean isOpenExam(){
    	return isOpenExam;
    }

	public void setRecid(GUID recid){
    	this.recid = recid;
    }

	public void setTenantsGuid(GUID tenantsGuid){
    	this.tenantsGuid = tenantsGuid;
    }

	public void setModeid(String modeid){
    	this.modeid = modeid;
    }

	public void setOpenExam(boolean isOpenExam){
    	this.isOpenExam = isOpenExam;
    }

	public void setMaxAmount(double maxAmount){
    	this.maxAmount = maxAmount;
    }

	public void setUpdatePerson(String updatePerson){
    	this.updatePerson = updatePerson;
    }

	public void setUpdateDate(long updateDate){
    	this.updateDate = updateDate;
    }

	public double getMaxAmount(){
    	return maxAmount;
    }

	public String getUpdatePerson(){
    	return updatePerson;
    }

	public long getUpdateDate(){
    	return updateDate;
    }
	
}
