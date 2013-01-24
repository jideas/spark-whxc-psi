package com.spark.oms.publish.product.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.ProductStatus;

public class SetProductSaleStatusTask extends SimpleTask {
	
  /**
   * 产品Id或者产品条目Id
   */
	private GUID[]  id;
	
	/**
	 * 产品状态枚举：在售，停售
	 */
	private ProductStatus productstatus;
	
	/**
	 * 获取存放产品及产品条目的标识
	 * @return
	 */
	public GUID[] getId() {
		return id;
	}
	
	/**
	 * 存放产品及产品条目的标识
	 * @return
	 */
	public void setId(GUID...id) {
		this.id = id;
	}
	
	/**
	 * 获取产品在售状态
	 * @return
	 */
	public ProductStatus getProductStatus() {
		return productstatus;
	}
	
	/**
	 * 设置产品在售状态
	 * @param productstatus
	 */
	public void setProductStatus(ProductStatus productstatus){
		  this.productstatus = productstatus;
	}
  
}
