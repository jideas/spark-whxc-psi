/**
 * 
 */
/**
 * 
 */
package com.spark.psi.report.basedate;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.order.intf.event.ChangedType;
import com.spark.order.intf.event.PromotionOrderChangedEvent;
import com.spark.order.intf.event.PurchaseOrderChangedEvent;
import com.spark.order.intf.event.PurchaseReturnChangedEvent;
import com.spark.order.intf.event.SalesOrderChangedEvent;
import com.spark.order.intf.event.SalesReturnChangedEvent;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseOrder;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleOrder;
import com.spark.psi.base.ApprovalConfig.Mode;
import com.spark.psi.base.task.CreateApprovalRecordTask;
import com.spark.psi.inventory.internal.entity.AllocateInventory;
import com.spark.psi.inventory.intf.event.InventoryAllocateApprovalEvent;

/**
 * 审批记录服务
 */
public class ApprovalLogListener extends Service{

	/**
     * @param title
     */
    protected ApprovalLogListener(){
	    super("审批记录服务");
    }
    
    /**
     * 销售订单审批通过监听器
     */
    @Publish
    protected final class SalesOrderChangedEventListener extends EventListener<SalesOrderChangedEvent>{

		/* (non-Javadoc)
         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
         */
        @Override
        protected void occur(Context context, SalesOrderChangedEvent event)
                throws Throwable
        {
        	if(event.getType()!=ChangedType.Approval)return;
        	SaleOrder order = context.find(SaleOrder.class,event.getId());
        	CreateApprovalRecordTask task = new CreateApprovalRecordTask();
        	task.setAmount(order.getInfo().getTotalAmount());
        	task.setBillNumber(order.getInfo().getBillsNo());
        	task.setBillstatus(order.getInfo().getStatus());
        	task.setBusMode(Mode.SALES_BILLS);
        	task.setCreateDate(order.getInfo().getCreateDate());
        	task.setCreatePerson(order.getInfo().getCreator());
        	context.handle(task);
        }   	
    }
    
    /**
     * 销售退货审批通过监听器
     */
    @Publish
    protected final class SalesReturnChangedEventListener extends EventListener<SalesReturnChangedEvent>{

		/* (non-Javadoc)
         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
         */
        @Override
        protected void occur(Context context, SalesReturnChangedEvent event)
                throws Throwable
        {
        	if(event.getType()!=ChangedType.Approval)return;
//        	SaleOrder order = context.find(SaleOrder.class,event.getId());
        	SaleCancel cancel = context.find(SaleCancel.class, event.getId());
        	CreateApprovalRecordTask task = new CreateApprovalRecordTask();
        	task.setAmount(cancel.getTotalAmount());
        	task.setBillNumber(cancel.getBillsNo());
        	task.setBillstatus(cancel.getStatus());
        	task.setBusMode(Mode.SALES_RETURN);
        	task.setCreateDate(cancel.getCreateDate());
        	task.setCreatePerson(cancel.getCreator());
        	context.handle(task);
        }   	
    }
    
    /**
     * 采购订单审批通过监听器
     */
    @Publish
    protected final class PurchaseOrderChangedEventListener extends EventListener<PurchaseOrderChangedEvent>{

		/* (non-Javadoc)
         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
         */
        @Override
        protected void occur(Context context, PurchaseOrderChangedEvent event)
                throws Throwable
        {
        	if(event.getType()!=ChangedType.Approval)return;
        	PurchaseOrder order = context.find(PurchaseOrder.class,event.getId());
        	CreateApprovalRecordTask task = new CreateApprovalRecordTask();
        	task.setAmount(order.getInfo().getTotalAmount());
        	task.setBillNumber(order.getInfo().getBillsNo());
        	task.setBillstatus(order.getInfo().getStatus());
        	task.setBusMode(Mode.BUY_ORDER);
        	task.setCreateDate(order.getInfo().getCreateDate());
        	task.setCreatePerson(order.getInfo().getCreator());
        	context.handle(task);
        }   	
    }
    
    /**
     * 采购退货审批通过监听器
     */
    @Publish
    protected final class PurchaseReturnChangedEventListener extends EventListener<PurchaseReturnChangedEvent>{

		/* (non-Javadoc)
         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
         */
        @Override
        protected void occur(Context context, PurchaseReturnChangedEvent event)
                throws Throwable
        {
        	if(event.getType()!=ChangedType.Approval)return;
//        	PurchaseOrder order = context.find(PurchaseOrder.class,event.getId());
        	PurchaseCancel cancel = context.find(PurchaseCancel.class, event.getId());
        	CreateApprovalRecordTask task = new CreateApprovalRecordTask();
        	task.setAmount(cancel.getTotalAmount());
        	task.setBillNumber(cancel.getBillsNo());
        	task.setBillstatus(cancel.getStatus());
        	task.setBusMode(Mode.BUY_RETURN);
        	task.setCreateDate(cancel.getCreateDate());
        	task.setCreatePerson(cancel.getCreator());
        	context.handle(task);
        }   	
    }
    
    /**
     * 商品促销审批通过监听器
     */
    @Publish
    protected final class PromotionOrderChangedEventListener extends EventListener<PromotionOrderChangedEvent>{

		/* (non-Javadoc)
         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
         */
        @Override
        protected void occur(Context context, PromotionOrderChangedEvent event)
                throws Throwable
        {
        	if(event.getType()!=ChangedType.Approval)return;
        	Promotion2 order = context.find(Promotion2.class,event.getId());
        	CreateApprovalRecordTask task = new CreateApprovalRecordTask();
        	task.setAmount(order.getPrice_promotion());
        	task.setBillNumber("--");
        	task.setBillstatus(order.getStatus());
        	task.setBusMode(Mode.PROMOTION);
        	task.setCreateDate(order.getCreateDate());
        	task.setCreatePerson(order.getCreator());
        	context.handle(task);
        }   	
    }
    
    /**
     * 库存调拨审批通过监听器
     */
    @Publish
    protected final class InventoryApprovalEventListener extends EventListener<InventoryAllocateApprovalEvent>{

		/* (non-Javadoc)
         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
         */
        @Override
        protected void occur(Context context, InventoryAllocateApprovalEvent event)
                throws Throwable
        {
        	AllocateInventory order = context.find(AllocateInventory.class,event.getId());
        	CreateApprovalRecordTask task = new CreateApprovalRecordTask();
        	task.setAmount(0);
        	task.setBillNumber(order.getAllocSheetNo());
        	task.setBillstatus(order.getStatus());
        	task.setBusMode(Mode.BLENDING);
        	task.setCreateDate(order.getCreateDate());
        	task.setCreatePerson(order.getCreator());
        	context.handle(task);
        }   	
    }

    
    
}
