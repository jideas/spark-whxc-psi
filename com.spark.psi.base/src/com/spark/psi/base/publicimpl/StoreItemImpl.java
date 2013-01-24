package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;

public class StoreItemImpl implements StoreItem{
	
	private String address;
	
	private GUID id;
	
	private GUID[] keeperIds;
	
	private String name;
	
	private StoreStatus status;
	
	private String keeperInfo;

	private int recver;
	
	private GUID createPerson;	
	
	private Action[] action;	
	
	public GUID getCreatePerson(){
    	return createPerson;
    }

	public void setCreatePerson(GUID createPerson){
    	this.createPerson = createPerson;
    }

	public Action[] getAction(){
    	return action;
    }

	public void setAction(Action[] action){
    	this.action = action;
    }

	public int getRecver(){
    	return recver;
    }

	public void setRecver(int recver){
    	this.recver = recver;
    }

	public String getAddress(){
    	return address;
    }

	public void setAddress(String address){
    	this.address = address;
    }

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public GUID[] getKeeperIds(){
		if(keeperIds==null)return new GUID[0];
    	return keeperIds;
    }

	public void setKeeperIds(GUID[] keeperIds){
    	this.keeperIds = keeperIds;
    }

	public String getName(){
    	return name;
    }

	public void setName(String name){
    	this.name = name;
    } 

	public StoreStatus getStatus() {
		return status;
	}

	public void setStatus(StoreStatus status) {
		this.status = status;
	}

	public String getKeeperInfo(){
    	return keeperInfo;
    }

	public void setKeeperInfo(String keeperInfo){
    	this.keeperInfo = keeperInfo;
    }

	public String getKepperInfo(){
	    return keeperInfo;
    }

	
}
