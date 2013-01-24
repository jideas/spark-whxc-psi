package com.spark.psi.base.browser.config;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.psi.publish.base.config.entity.ApprovalConfigInfo;
import com.spark.psi.publish.base.config.task.SaveApprovalConfigTask;

/**
 * 审核配置界面处理器
 */
public class ApprovalConfigProcessor extends BaseFormPageProcessor implements IDataProcessPrompt {
	
	public static String ID_RADIO_SALEORDER_ON = "Radio_SaleOrderOn";//销售订货审核
	public static String ID_RADIO_SALEORDER_OFF = "Radio_SaleOrderOff";
	public static String ID_TEXT_SALE_APPROVALAMOUNT = "Text_SaleApprovalAmount";//金额条件
	public static String ID_COMPOSITE_SALEORDEROFF = "Composite_SaleOrderOff";//金额设置面板
	
	public static String ID_RADIO_SALERETURN_ON = "Radio_SaleReturnOn";//销售退货审核
	public static String ID_RADIO_SALERETURN_OFF = "Radio_SaleReturnOff";
	public static String ID_TEXT_SALERETURN_APPROVALAMOUNT = "Text_SaleReturnApprovalAmount";//金额条件
	public static String ID_COMPOSITE_SALERETURNOFF = "Composite_SaleReturnOff";//金额设置面板
	
	public static String ID_RADIO_PURCHASEORDER_ON = "Radio_PurchaseOrderOn";//采购订货审核
	public static String ID_RADIO_PURCHASEORDER_OFF = "Radio_PurchaseOrderOff";
	public static String ID_TEXT_PURCHASE_APPROVALAMOUNT = "Text_PurchaseApprovalAmount";//金额条件
	public static String ID_COMPOSITE_PURCHASEORDEROFF = "Composite_PurchaseOrderOff";//金额设置面板
	
	public static String ID_RADIO_PURCHASERETURN_ON = "Radio_PurchaseReturnOn";//采购退货审核
	public static String ID_RADIO_PURCHASERETURN_OFF = "Radio_PurchaseReturnOff";
	public static String ID_TEXT_PURCHASERETURN_APPROVALAMOUNT = "Text_PurchaseReturnApprovalAmount";//金额条件
	public static String ID_COMPOSITE_PURCHASERETURNOFF = "Composite_PurchaseReturnOff";//金额设置面板
	
	public static String ID_RADIO_INVENTORYALLOCATE_ON = "Radio_InventoryAllocateOn";//库存调拨
	public static String ID_RADIO_INVENTORYALLOCATE_OFF = "Radio_InventoryAllocateOff";
	
	public static String ID_BUTTON_SAVEBUTTON = "Button_SaveButton";
	
	Button btnSaleOrderOn;
	Button btnSaleOrderOff;
	SNumberText txtSaleApprovalAmount;		
	Button btnSaleReturnOn;
	Button btnSaleReturnOff;
	SNumberText txtSaleReturnApprovalAmount;	
	Button btnPurchaseOrderOn;
	SNumberText txtPurchaseApprovalAmount;
	Button btnPurchaseOrderOff;	
	Button btnPurchaseReturnOn;
	SNumberText txtPurchaseReturnApprovalAmount;
	Button btnPurchaseReturnOff;	
	Button btnInventoryAllocateOn;
	Button btnInventoryAllocateOff;
	
	/**
	 * 添加事件
	 * @param txt 
	 */
	private void initEvent(Button btnOn, Button btnOff,String cmpId, final SNumberText txt) {
		
		final Composite composite = this.createControl(cmpId, Composite.class);
		
		btnOn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Button btn = (Button)e.widget;
				if(btn.getSelection()){
					composite.setEnabled(false);
				} else {
					composite.setEnabled(true);
				}
			}
		});
		
		btnOff.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				Button btn = (Button)e.widget;
				if(btn.getSelection()){
					composite.setEnabled(true);
					txt.clearValue();
				} else {
					composite.setEnabled(false);
				}
			}
		});
	}
	
	/**
	 * 初始化控件相关数据与事件
	 */
	private void initControl() {
		
		btnSaleOrderOn = this.createControl(ID_RADIO_SALEORDER_ON, Button.class);
		btnSaleOrderOff = this.createControl(ID_RADIO_SALEORDER_OFF, Button.class);
		txtSaleApprovalAmount = this.createControl(ID_TEXT_SALE_APPROVALAMOUNT, SNumberText.class);	
		
		btnSaleReturnOn = this.createControl(ID_RADIO_SALERETURN_ON, Button.class);
		btnSaleReturnOff = this.createControl(ID_RADIO_SALERETURN_OFF, Button.class);
		txtSaleReturnApprovalAmount = this.createControl(ID_TEXT_SALERETURN_APPROVALAMOUNT, SNumberText.class);
		
		btnPurchaseOrderOn = this.createControl(ID_RADIO_PURCHASEORDER_ON, Button.class);
		txtPurchaseApprovalAmount = this.createControl(ID_TEXT_PURCHASE_APPROVALAMOUNT, SNumberText.class);
		btnPurchaseOrderOff = this.createControl(ID_RADIO_PURCHASEORDER_OFF, Button.class);
		
		btnPurchaseReturnOn = this.createControl(ID_RADIO_PURCHASERETURN_ON, Button.class);
		txtPurchaseReturnApprovalAmount = this.createControl(ID_TEXT_PURCHASERETURN_APPROVALAMOUNT, SNumberText.class);
		btnPurchaseReturnOff = this.createControl(ID_RADIO_PURCHASERETURN_OFF, Button.class);
		
		btnInventoryAllocateOn = this.createControl(ID_RADIO_INVENTORYALLOCATE_ON, Button.class);
		btnInventoryAllocateOff = this.createControl(ID_RADIO_INVENTORYALLOCATE_OFF, Button.class);
		
		initEvent(btnSaleOrderOn,btnSaleOrderOff,ID_COMPOSITE_SALEORDEROFF,txtSaleApprovalAmount);
		initEvent(btnSaleReturnOn,btnSaleReturnOff,ID_COMPOSITE_SALERETURNOFF,txtSaleReturnApprovalAmount);
		initEvent(btnPurchaseOrderOn,btnPurchaseOrderOff,ID_COMPOSITE_PURCHASEORDEROFF,txtPurchaseApprovalAmount);		
		initEvent(btnPurchaseReturnOn,btnPurchaseReturnOff,ID_COMPOSITE_PURCHASERETURNOFF,txtPurchaseReturnApprovalAmount);
	}
	
	/**
	 * 初始化控件数据与状态
	 */
	private void initData() {
		
		ApprovalConfigInfo approvalConfigInfo = getContext().get(ApprovalConfigInfo.class);		
		
		if(approvalConfigInfo.isSalesOrderNeedApproval()){//获取是否开始销售订单审批流程			
			btnSaleOrderOn.setSelection(true);	
		} else {			
			btnSaleOrderOff.setSelection(true);
			if(approvalConfigInfo.getSalesApprovalLimit()<=0)
				txtSaleApprovalAmount.clearValue();
			else
				txtSaleApprovalAmount.setValue(approvalConfigInfo.getSalesApprovalLimit());
		}
		
		if(approvalConfigInfo.isSalesReturnNeedApproval()){//获取销售退货是否开启审批流程			
			btnSaleReturnOn.setSelection(true);
		} else {
			btnSaleReturnOff.setSelection(true);
			if(approvalConfigInfo.getSalesReturnApprovalLimit()<=0)
				txtSaleReturnApprovalAmount.clearValue();
			else
				txtSaleReturnApprovalAmount.setValue(approvalConfigInfo.getSalesReturnApprovalLimit());
		}		
		
		if(approvalConfigInfo.isPurchaseOrderNeedApproval()){//获取是否开启采购订单审批流程
			btnPurchaseOrderOn.setSelection(true);
		} else {
			btnPurchaseOrderOff.setSelection(true);
			if(approvalConfigInfo.getPurchaseApprovalLimit()<=0)
				txtPurchaseApprovalAmount.clearValue();
			else
				txtPurchaseApprovalAmount.setValue(approvalConfigInfo.getPurchaseApprovalLimit());
		}		
		
		if(approvalConfigInfo.isPurchaseReturnNeedApproval()){//获取采购退货订单审核开启金额
			btnPurchaseReturnOn.setSelection(true);
		} else {
			btnPurchaseReturnOff.setSelection(true);
			if(approvalConfigInfo.getPurchaseReturnApprovalLimit()<=0)
				txtPurchaseReturnApprovalAmount.clearValue();
			else
				txtPurchaseReturnApprovalAmount.setValue(approvalConfigInfo.getPurchaseReturnApprovalLimit());
		}		
						
		if(approvalConfigInfo.isAllocateNeedApproval()){//获取是否开启库存调拨审批
			btnInventoryAllocateOn.setSelection(true);
		} else {
			btnInventoryAllocateOff.setSelection(true);
		}
	}

	@Override
	public void process(Situation situation) {
		
		initControl();
		initData();
				
		//保存审核设置
		Button saveButton = this.createControl(ID_BUTTON_SAVEBUTTON, Button.class, JWT.NONE);
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				processData();
			}
		});
	}

	public String getPromptMessage(){
	    // TODO Auto-generated method stub
	    return null;
    }
	
	public boolean processData(){
		
		
		SaveApprovalConfigTask ApprovalConfig = new SaveApprovalConfigTask();
		
		if(btnSaleOrderOn.getSelection()){
			ApprovalConfig.setSalesOrderNeedApproval(true);
		} else {
			ApprovalConfig.setSalesOrderNeedApproval(false);
			if(txtSaleApprovalAmount.getDoubleValue(-1)==0){
				alert("销售订货审核,关闭审核时,订单金额必须大于0或者为空");
				txtSaleApprovalAmount.clearValue();
				return false;
			}
			ApprovalConfig.setSalesApprovalLimit(txtSaleApprovalAmount.getDoubleValue(-1));
		}
		
		if(btnSaleReturnOn.getSelection()){
			ApprovalConfig.setSalesReturnNeedApproval(true);
		} else {
			ApprovalConfig.setSalesReturnNeedApproval(false);
			if(txtSaleReturnApprovalAmount.getDoubleValue(-1)==0){
				alert("销售退货审核,关闭审核时,订单金额必须大于0或者为空");
				txtSaleReturnApprovalAmount.clearValue();
				return false;
			}
			ApprovalConfig.setSalesReturnApprovalLimit(txtSaleReturnApprovalAmount.getDoubleValue(-1));
		}
		
		if(btnPurchaseOrderOn.getSelection()){
			ApprovalConfig.setPurchaseOrderNeedApproval(true);
		} else {
			ApprovalConfig.setPurchaseOrderNeedApproval(false);
			if(txtPurchaseApprovalAmount.getDoubleValue(-1)==0){
				alert("采购订货审核,关闭审核时,订单金额必须大于0或者为空");
				txtPurchaseApprovalAmount.clearValue();
				return false;
			}
			ApprovalConfig.setPurchaseApprovalLimit(txtPurchaseApprovalAmount.getDoubleValue(-1));
		}
		
		if(btnPurchaseReturnOn.getSelection()){
			ApprovalConfig.setPurchaseReturnNeedApproval(true);
		} else {
			ApprovalConfig.setPurchaseReturnNeedApproval(false);
			if(txtPurchaseReturnApprovalAmount.getDoubleValue(-1)==0){
				alert("采购退货审核,关闭审核时,订单金额必须大于0或者为空");
				txtPurchaseReturnApprovalAmount.clearValue();
				return false;
			}
			ApprovalConfig.setPurchaseReturnApprovalLimit(txtPurchaseReturnApprovalAmount.getDoubleValue(-1));
		}
		
		ApprovalConfig.setAllocateNeedApproval((btnInventoryAllocateOn.getSelection())?true:false);

		getContext().handle(ApprovalConfig);
		hint("保存成功");
		resetDataChangedstatus();
	    return true;
    }
}