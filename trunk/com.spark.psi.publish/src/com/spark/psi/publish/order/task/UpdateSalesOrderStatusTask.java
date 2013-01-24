/**
 * 
 */
/**
 * 
 */
package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.PsiSimpleTask;

/**
 * 修改销售订单状态
 * @author zhoulijun
 *
 */
public class UpdateSalesOrderStatusTask extends PsiSimpleTask<UpdateSalesOrderStatusTask.Error> {
	public enum Error{
		/**
		 * 设置预警出库日期
		 */
		SetPlanDate(),
		/**
		 * 可用促销数量不够
		 *  void
		 */
		PromotionCountError();
	}

	private GUID id;
	
	private OrderAction orderAction;
	
	private String cause;
	
	private Long planOutDate;//预计出库日期
	
	/**
	 * 修改订单的状态
	 * @param id
	 * @param orderstatus
	 */
	public UpdateSalesOrderStatusTask(final GUID id,final OrderAction orderAction){
		this.id = id;
		this.orderAction = orderAction;
	}
	
	/**
	 * @param id
	 * @param orderAction
	 * @param planOutDate
	 */
	public UpdateSalesOrderStatusTask(final GUID id, final OrderAction orderAction,
			final Long planOutDate) {
		super();
		this.id = id;
		this.orderAction = orderAction;
		this.planOutDate = planOutDate;
	}

	/**
	 * 修改订单状态，带原因
	 * @param id
	 * @param orderstatus
	 * @param cause
	 */
	public UpdateSalesOrderStatusTask(final GUID id,final OrderAction orderAction,final String cause){
		this(id,orderAction);
		this.cause = cause;
	}
	
	public GUID getId() {
		return id;
	}

	public OrderAction getActions() {
		return orderAction;
	}

	public String getCause(){
    	return cause;
    }
	
	public Long getPlanOutDate() {
		return planOutDate;
	}
	
}
