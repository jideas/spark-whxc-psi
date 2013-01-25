package com.spark.psi.publish.base.materials.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.MaterialsStatus;
import com.spark.psi.publish.LimitKey;

/**
 * 
 * <p>������Ŀ�б��ѯ</p>
 *
 */
public class GetMaterialsInfoListKey extends LimitKey{

	/**
	 * ����Id
	 */
	private GUID cateoryId;
	
	/**
	 * �Ƿ�ֻ��ѯû�����ü۸�Ĳ���
	 */
	private boolean nopriceOnly = false;

	private SortField sortField;
	
	/**
	 * �Ƿ�ֻ��ѯ�����˼۸�Ĳ���
	 */
	private boolean setPriceOnley = false;
	
	/**
	 * 
	 */
	private MaterialsStatus status = MaterialsStatus.PART_SALE;	
	
	private Boolean jointVenture;
	
	public GetMaterialsInfoListKey(int offset, int count, boolean queryTotal){
	    super(offset, count, queryTotal);
    }
	
	
	public GetMaterialsInfoListKey(){
		super(0,10,false);
	}

	public GUID getCateoryId(){
    	return cateoryId;
    }

	public void setCateoryId(GUID cateoryId){
    	this.cateoryId = cateoryId;
    }

	public boolean isNopriceOnly(){
    	return nopriceOnly;
    }

	public void setNopriceOnly(boolean nopriceOnly){
    	this.nopriceOnly = nopriceOnly;
    }

	public MaterialsStatus getStatus(){
    	return status;
    }

	public void setStatus(MaterialsStatus status){
    	this.status = status;
    }
	
	
	
	public SortField getSortField() {
		return sortField;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}



	public boolean isSetPriceOnley(){
    	return setPriceOnley;
    }



	public void setSetPriceOnley(boolean setPriceOnley){
    	this.setPriceOnley = setPriceOnley;
    }



	public void setJointVenture(Boolean jointVenture) {
		this.jointVenture = jointVenture;
	}


	public Boolean getJointVenture() {
		return jointVenture;
	}



	public static enum SortField {
		/**
		 * ������
		 */
		None("createDate"), //
		
		code("code"),
		
		name("name");
		
		private final String fieldName;

		private SortField(String fieldName) {
			this.fieldName = fieldName;
		}

		public final String getFieldName() {
			return this.fieldName;
		}
	}
	
}
