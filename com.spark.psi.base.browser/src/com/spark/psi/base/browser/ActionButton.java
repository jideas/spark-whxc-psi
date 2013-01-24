package com.spark.psi.base.browser;

import com.spark.psi.publish.Action;

public enum ActionButton{
	
	Add(Action.Add,"add"),

	/**
	 * 
	 */
	Delete(Action.Delete,"del"),

	/**
	 * 
	 */
	Clear(Action.Clear,"qingkong"),

	/**
	 * 
	 */
	Confirm(Action.Confirm,"confirm"),

	/**
	 * 
	 */
	Cancel(Action.Cancel,"cancel"),

	/**
	 * 
	 */
	Submit(Action.Submit,"submit"),

	/**
	 * 
	 */
	Approval(Action.Approval,"shenpi"),

	/**
	 * 
	 */
	Stop(Action.Stop,"stop"),

	/**
	 * 
	 */
	ReExecute(Action.ReExecute,"regain"),

	/**
	 * 
	 */
	Consignment(Action.Consignment,"qrfh"),

	/**
	 * 
	 */
	Deny(Action.Deny,"reject"),

	/**
	 * 
	 */
	DepartmentConfig(Action.DepartmentConfig,"shebumen"),

	/**
	 * 
	 */
	AuthConfig(Action.AuthConfig,"shequanxian"),

	/**
	 * 
	 */
	Resign(Action.Resign,"lizhi"),

	/**
	 * 
	 */
	Reinstatus(Action.Reinstatus,"fuzhi"),

	/**
	 * 
	 */
	OnSale(Action.OnSale,"zaishou"),

	/**
	 * 
	 */
	OffSale(Action.OffSale,"tingshou"),

	/**
	 * 
	 */
	Restore(Action.Restore,"regain"),

	/**
	 * 
	 */
	Associate(Action.Associate,"guanlian"),

	/**
	 * 
	 */
	Pay(Action.Pay,"pay"),

	/**
	 * 
	 */
	Receipt(Action.Receipt,"receipt"),

	/**
	 * 
	 */
	SubmitReceipt(Action.SubmitReceipt,"jiaokuan"),

	/**
	 *
	 */
	Invalidate(Action.Invalidate,"invalid"),

	/**
	 * 
	 */
	Detail(Action.Detail,"xiangqing"),

	/**
	 *
	 */
	Adjust(Action.Adjust,"tiaozheng"),

	/**
	 * 
	 */
	Active(Action.Active,"qiyong"),

	/**
	 * 
	 */
	DeActive(Action.DeActive,"tingyong"),

	/**
	 * 
	 */
	CheckIn(Action.CheckIn,"ruku"),

	/**
	 * 
	 */
	CheckOut(Action.CheckOut,"out"),

	/**
	 * 
	 */
	AllocateIn(Action.AllocateIn,"diaoru"),

	/**
	 * 
	 */
	AllocateOut(Action.AllocateOut,"diaochu"),

	/**
	 * 
	 */
	Allocate(Action.Allocate,"assign"),

	/**
	 * 
	 */
	CreditConfig(Action.CreditConfig,"xinyong"),

	/**
	 *
	 */
	SetSupplier(Action.SetSupplier,"szgys"),
	/**
	 * 
	 */
	LookInventory(Action.LookInventory,"chaku"),
	/**
	 *
	 */
	Cause(Action.Cause,"reason"),
	
	/**
	 *
	 */
	InventoryInfo(Action.InventoryInfo,"xgfz"),

	/**
	 *
	 */
	PurchaseInfo(Action.PurchaseInfo,"采购情况"),

	/**
	 *
	 */
	SalesInfo(Action.SalesInfo,"销售情况"),
	
	
	Select(Action.Select,"xuanze"),
	
	/**
	 *短信
	 */
//	SMS(Action.SMS,"sms"),
	/**
	 * 设阈值
	 */
	SetThreshold(Action.SetThreshold,"xgfz"),
	/**
	 *邮件
	 */
//	Email(Action.Email,"mail"),
	
	Default(null,"缺省");

	
	Action action;
	
	String name;
	
	String path = "images/action/saas_&.png";
	
	private ActionButton(Action action,String name){
	    this.name = name;
	    this.action = action;
    }
	
	public String getNormalImagePath(){
		return path.replace("&", name);
	}
	
	public String getHoverImagePaht(){
		return path.replace("&", name+"_h");
	}
	
	public static ActionButton getActionButton(Action action){
		for(ActionButton ab : ActionButton.values()){
			if(ab.action==null)continue;
	        if(ab.action.equals(action))return ab;
        } 
		return Default;
	}
}
