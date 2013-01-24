package com.spark.oms.publish.product.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.ProductStatus;

public class SetProductSaleStatusTask extends SimpleTask {
	
  /**
   * ��ƷId���߲�Ʒ��ĿId
   */
	private GUID[]  id;
	
	/**
	 * ��Ʒ״̬ö�٣����ۣ�ͣ��
	 */
	private ProductStatus productstatus;
	
	/**
	 * ��ȡ��Ų�Ʒ����Ʒ��Ŀ�ı�ʶ
	 * @return
	 */
	public GUID[] getId() {
		return id;
	}
	
	/**
	 * ��Ų�Ʒ����Ʒ��Ŀ�ı�ʶ
	 * @return
	 */
	public void setId(GUID...id) {
		this.id = id;
	}
	
	/**
	 * ��ȡ��Ʒ����״̬
	 * @return
	 */
	public ProductStatus getProductStatus() {
		return productstatus;
	}
	
	/**
	 * ���ò�Ʒ����״̬
	 * @param productstatus
	 */
	public void setProductStatus(ProductStatus productstatus){
		  this.productstatus = productstatus;
	}
  
}
