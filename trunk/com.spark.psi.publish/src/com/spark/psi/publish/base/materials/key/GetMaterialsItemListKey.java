package com.spark.psi.publish.base.materials.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.MaterialsStatus;

/**
 * 
 * <p>���ݲ��Ϸ���id��ѯ������Ŀ�б�</p>
 *  �ݹ������ӷ������Ʒ��Ŀ
 * 
 */
public class GetMaterialsItemListKey extends LimitKey{
	
	/**
	 * ���Ϸ���id
	 */
	private GUID categoryId;
	
	/**
	 * ������Ŀ״̬
	 * 
	 */
	private MaterialsStatus materialsStatus = MaterialsStatus.PART_SALE;

	
	/**
	 * ���ؼ��������Ĳ�����Ŀ��ѯ
	 * @param gategoryId ���Ϸ���ID
	 * @param searchKey �����ؼ���
	 */
	public GetMaterialsItemListKey(GUID categoryId,String searchText){
		super(0,0,false);
		this.categoryId = categoryId;
		this.searchText = searchText;
	}

	
	/**
	 * ���ؼ��������Ĳ�����Ŀ��ѯ
	 * @param gategoryId ��Ʒ����ID
	 * @param searchKey �����ؼ���
	 * @param goodsstatus ��Ʒ״̬ ���� or ͣ��
	 */
	public GetMaterialsItemListKey(GUID categoryId,String searchText,MaterialsStatus materialsStatus){
		this(categoryId,searchText);
		this.materialsStatus = materialsStatus;
	}
	
	/**
	 * ��ѯָ����Ʒ�����µ����в�����Ŀ
	 * @param gategoryId
	 */
	public GetMaterialsItemListKey(GUID categoryId){
		super(0,0,false);
		this.categoryId = categoryId;
	}
	
	/**
	 * ��ѯָ�����Ϸ����µ����в�����Ŀ
	 * @param gategoryId
	 * @param goodsstatus ����״̬ ���� or ͣ��
	 */
	public GetMaterialsItemListKey(GUID categoryId,MaterialsStatus materialsStatus){
		super(0,0,false);
		this.categoryId = categoryId;
		this.materialsStatus = materialsStatus;
	}

	public GUID getGategoryId(){
    	return categoryId;
    }

	public MaterialsStatus getMaterialsStatus(){
	    return materialsStatus;
    }


	
}
