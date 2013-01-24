package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler2;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;

/**
 * <p>调拔详单控制器(本类负责所有的调拔详单显示)</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-5
 */

public class AllocateSheetDetailController{

	/**上下文*/
	private Situation context;

	/**调拔单ID*/
	private GUID id;

	/**调拔单详细信息*/
	private InventoryAllocateSheetInfo info;

	/**
	 * 表格控件对象
	 */
	protected SEditTable table;

	/**调拔单状态常量*/
	private final int ADD = 1; //新增

	private final int SUBMIT = 2; //待提交

	private final int APPROVAL = 3; //待审批

	private final int PROCCESSING = 4; //进行中

	private final int PROCCESSED = 5; //已完成

	/** 
	 *构造方法
	 *@param context
	 *@param info
	 */
	public AllocateSheetDetailController(Situation context, GUID id, SEditTable table){
		super();
		this.context = context;
		this.id = id;
		this.table = table;
	}

	/**
	 * 显示调拔单信息
	 */
	public void showAllocateSheetDetail(){
		if(id != null){
			info = context.find(InventoryAllocateSheetInfo.class, id);
		}
		//
		MsgRequest request = new MsgRequest(getPageControllerForDetail(), getPageDetailTitle());
		request.setResponseHandler(new ResponseHandler2(){
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
				if(table != null){
					table.render();
				}
			}

			public void afterHandle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
				if(returnValue != null){
					//显示列表页面
					//					PageControllerInstance pci = getPageControllerForList();
					//					MsgRequest request = new MsgRequest(pci);
					//					context.bubbleMessage(request);
					//
					//					AllocateSheetDetailController.this.context = pci.getInstancePage().getContext();
					AllocateSheetDetailController.this.id = (GUID)returnValue;
					showAllocateSheetDetail();
				}

			}
		});
		context.bubbleMessage(request);

	}

	/**
	 * 获得列表页签控制器
	 */
	private PageControllerInstance getPageControllerForList(){
		switch(getStatus()){
			case ADD:
			case SUBMIT:
				return new PageControllerInstance("SubmitingAllocateSheetListPage");
			case APPROVAL:
				return new PageControllerInstance("ApprovalingAllocateSheetListPage");
			case PROCCESSING:
				return new PageControllerInstance("ProcessingAllocateSheetListPage");
			default:
				return new PageControllerInstance("ProcessedAllocateSheetListPage");
		}
	}

	/**
	 * 获得调拔单详细控制器
	 */
	private PageControllerInstance getPageControllerForDetail(){
		switch(getStatus()){
			case ADD:
				return new PageControllerInstance("EditAllocateSheetPage");
			case SUBMIT:
				return new PageControllerInstance("EditAllocateSheetPage", info.getSheetId().toString());
			case APPROVAL:
				return new PageControllerInstance("ApprovalingSheetDetailPage", info.getSheetId().toString());
			case PROCCESSING:
				return new PageControllerInstance("ProcessingSheetDetailPage", info.getSheetId().toString());
			default:
				return new PageControllerInstance("AllocateSheetDetailBasePage", info.getSheetId().toString());
		}
	}

	/**
	 * 获得调拔单详细的标题
	 */
	private String getPageDetailTitle(){
		switch(getStatus()){
			case ADD:
				return "新增调拔单";
			case SUBMIT:
				return "编辑调拔单";
			case APPROVAL:
				return "调拨单详情";
			case PROCCESSING:
				return "调拨单详情";
			default:
				return "调拨单详情";
		}
	}

	/**
	 * 获得调拔单状态值
	 */
	private int getStatus(){
		LoginInfo login = context.find(LoginInfo.class);
		if(info == null){ //新增
			return ADD;
		}
		else if(InventoryAllocateStatus.Submitting.equals(info.getStatus())
		        || InventoryAllocateStatus.Rejected.equals(info.getStatus()))
		{ //待提交或已退回
			return SUBMIT;
		}
		else if(InventoryAllocateStatus.Submitted.equals(info.getStatus())
		        && login.hasAuth(Auth.Tag_InventoryAllocate_Approval))
		{ //待审批
			return APPROVAL;
		}
		else if(InventoryAllocateStatus.AllocateIn.equals(info.getStatus())){
			//已完成
			return PROCCESSED;
		}
		else{
			//进行中
			return PROCCESSING;
		}
	}
}
