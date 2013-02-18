package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.SetPSICaseUtil;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.inventory.task.InventoryAllocateTask;
import com.spark.psi.publish.inventory.task.UpdateInventoryAllocateSheetTask;

/**
 * <p>待审批详细调拔单处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-24
 */

public class ApprovalingSheetDetailProcesser extends AllocateSheetDetailBaseProcessor{

	/**
	 * 组件ID
	 */
	public final static String ID_Button_Approval = "Button_Approval";
	public final static String ID_Button_Deny = "Button_Deny";
	
	/**
	 * 页面初始化
	 * 
	 * @param context 上下文
	 */
	public void process(Situation context){
		super.process(context);
		//批准申请
		Button approvalButton = this.createControl(ID_Button_Approval, Button.class, JWT.NONE);
		//退回申请
		Button denyButton = this.createControl(ID_Button_Deny, Button.class, JWT.NONE);
		if (InventoryAllocateStatus.Submitted.equals(info.getStatus())) {
			approvalButton.addActionListener(new ApprovalButtonListener());
			denyButton.addActionListener(new DenyButtonListener());
		} else {
			approvalButton.dispose();
			denyButton.dispose();
		}
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context 上下文
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		//
	}
	
	/**
	 * 批准申请监听器
	 */
	private class ApprovalButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			InventoryAllocateTask task = new InventoryAllocateTask(GUID.valueOf(sheetId));
			getContext().handle(task, InventoryAllocateTask.Method.Approval);
			if(!task.isSuccess())
			{
				if(null!=task.getErrors()&&task.getErrors().length>0)
				{
					StringBuffer message = new StringBuffer();
					for (InventoryAllocateTask.Error error : task.getErrors()) {
						message.append(error.getMessage());
					}
					alert(message.toString());
					//是否出现并行操作错误
					if(task.isCurrentError()){
						getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
					}
					return;
				}
			}
			getContext().bubbleMessage(new MsgResponse(true));
        }
		
	}
	
	/**
	 * 退回申请监听器
	 */
	private class DenyButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			new SetPSICaseUtil(getContext(), SetPSICaseUtil.ReturnCause){

				@Override
				protected void action(String cause){
					InventoryAllocateTask task = new InventoryAllocateTask(GUID.valueOf(sheetId));
					task.setDenyReason(cause);
					getContext().handle(task, InventoryAllocateTask.Method.Deny);
					if(!task.isSuccess())
					{
						if(null!=task.getErrors()&&task.getErrors().length>0)
						{
							StringBuffer message = new StringBuffer();
							for (InventoryAllocateTask.Error error : task.getErrors()) {
								message.append(error.getMessage());
							}
							alert(message.toString());
							//是否出现并行操作错误
							if(task.isCurrentError()){
								getContext().bubbleMessage(new MsgResponse(true, task.getSheetId()));
							}
							return;
						}
					}
					getContext().bubbleMessage(new MsgResponse(true));
				}
			};
		}
		
	}
}
