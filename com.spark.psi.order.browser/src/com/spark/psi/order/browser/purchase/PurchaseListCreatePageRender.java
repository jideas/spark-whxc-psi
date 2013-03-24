package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.PageRender;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.constant.PSICommonConstant;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.key.GetPurchaseOrderListKey;

public class PurchaseListCreatePageRender extends PageRender {

	@Override
	protected void beforeRender() {
		
	}

	@Override
	protected void doRender() {
		GetPurchaseOrderListKey key = new GetPurchaseOrderListKey(0, PSICommonConstant.MAXIMUM, false);
		key.setOrderStatus(OrderStatus.Submit, OrderStatus.Denied); 
		OrderListEntity listEntity = getContext().find(OrderListEntity.class, key);
		String pageName;
		try{
			if(listEntity.getItemList().size() > 0){
				pageName = "SubmitingPurchaseOrderListPage";
			}
			else{
				pageName = "PurchasingGoodsListPage";
			}
		}catch(Exception e){
			pageName = "PurchasingGoodsListPage";
		}
		
		PageControllerInstance pci = new PageControllerInstance(pageName);
		contentArea.showPage(ControllerPage.NAME, pci);
	}
	

}
