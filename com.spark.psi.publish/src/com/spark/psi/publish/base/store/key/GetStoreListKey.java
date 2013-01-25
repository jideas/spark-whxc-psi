package com.spark.psi.publish.base.store.key;

import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.StoreStatus;

/**
 * ²éÑ¯²Ö¿âÁÐ±íKey
 * 
 */
public class GetStoreListKey extends LimitKey {

	/**
	 * ²Ö¿â×´Ì¬
	 */
	private StoreStatus[] status;
	
	private boolean allStore;

	private SortField sortField;
	 


	public boolean isAllStore(){
    	return allStore;
    }
	public GetStoreListKey(boolean allStore,StoreStatus... status){
		super(0, 20, false);
		this.status = status;
		this.allStore = allStore;
		this.sortType = SortType.Asc;
    }
	public GetStoreListKey(StoreStatus... status){
		this(false,status);
    } 
	
	public StoreStatus[] getStatus() {
		return status;
	}
	public void setStatus(StoreStatus[] status) {
		this.status = status;
	}
	public SortField getSortField(){
    	return sortField;
    }


	public void setSortField(SortField sortField){
    	this.sortField = sortField;
    }

	

	public void setAllStore(boolean allStore){
    	this.allStore = allStore;
    }



	/**
	 * ÅÅÐò×Ö¶Î
	 */
	public static enum SortField {
		Name(""), Address(""), Keepers(""), status(""),None("");


		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}


	}
}
