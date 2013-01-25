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
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.event.ChangedType;
import com.spark.order.intf.event.RetailOrderChangedEvent;
import com.spark.order.intf.event.RetailReturnChangedEvent;
import com.spark.order.intf.event.SalesOrderChangedEvent;
import com.spark.order.intf.event.SalesReturnChangedEvent; 
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.psi.account.intf.event.DealingAmountChanageEvent;
import com.spark.psi.base.Partner;
import com.spark.psi.base.task.UpdatePartnerStatusTask;
import com.spark.psi.publish.PartnerStatus;

/**
 * ���Ƹ����������ݵ�״̬
 * �磺��ʹ��
 */
public class StatusControlListener extends Service{

	/**
     * @param title
     */
    protected StatusControlListener(){
	    super("״̬���Ʒ���");
    }
    
//    @Publish
//    protected final class UsedListener extends EventListener<UsedEvent>{
//
//		/* (non-Javadoc)
//         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
//         */
//        @Override
//        protected void occur(Context context, UsedEvent event) throws Throwable
//        {
//        	MarkUsedTask task = getMarkTask(event);
//        	context.handle(task);
//        }
//    	
//    }
//    
//    private MarkUsedTask getMarkTask(UsedEvent event){
//    	if(event.getClazs() == Clazs.Partner){
//    		return new MarkPartnerUsedTask(event.getId());
//        }else if(event.getClazs() == Clazs.Goods){
//        	return new MarkGoodsUsedTask(event.getId());
//        }
//    	throw new IllegalArgumentException("û��ʵ���������͵Ľӿ�"+event.getClazs());
//    }

    
    @Publish
    protected final class SalesOrderChangedListener extends EventListener<SalesOrderChangedEvent>{

		/* (non-Javadoc)
         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
         */
        @Override
        protected void occur(Context context, SalesOrderChangedEvent event)
                throws Throwable
        {
	        if(event.getType()==ChangedType.Effectual){
	        	SaleOrderInfo order = context.find(SaleOrderInfo.class,event.getId());
	        	Partner partner = context.find(Partner.class,order.getPartnerId());
	        	if(partner.getStatus()==PartnerStatus.Potential)
	        		updatePartnerStatus(context, order.getPartnerId());
	        }
        }
    	
    }
    
    @Publish
    protected final class SalesReturnChangedListener extends EventListener<SalesReturnChangedEvent>{

		/* (non-Javadoc)
         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
         */
        @Override
        protected void occur(Context context, SalesReturnChangedEvent event)
                throws Throwable
        {
	        if(event.getType()==ChangedType.Effectual){
	        	SaleCancel entity = context.find(SaleCancel.class,event.getId());
	        	Partner partner = context.find(Partner.class,entity.getPartnerId());
	        	if(partner.getStatus()==PartnerStatus.Potential)
	        	updatePartnerStatus(context, entity.getPartnerId());
	        }
        }
    	
    }
    
    private void updatePartnerStatus(Context context,GUID id){
    	context.handle(new UpdatePartnerStatusTask(id));
    }
    
    @Publish
    protected final class DealingAmountChanageListener extends EventListener<DealingAmountChanageEvent>{

		/* (non-Javadoc)
         * @see com.jiuqi.dna.core.impl.ServiceBase.EventListener#occur(com.jiuqi.dna.core.Context, com.jiuqi.dna.core.invoke.Event)
         */
        @Override
        protected void occur(Context context, DealingAmountChanageEvent event)
                throws Throwable
        {
	        Partner partner = context.find(Partner.class,event.getPartnerId());
	        if(partner.getStatus()==PartnerStatus.Potential){
	        	updatePartnerStatus(context, event.getPartnerId());
	        }
        }
    	
    }

}
