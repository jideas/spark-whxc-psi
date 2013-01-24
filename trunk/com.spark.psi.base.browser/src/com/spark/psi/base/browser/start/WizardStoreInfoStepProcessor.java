package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.store.StoreEditProcessor;
import com.spark.psi.base.browser.store.StoreEditRender;
import com.spark.psi.base.browser.store.StoreListProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.store.key.FindEnabledStorageCountKey;
import com.spark.psi.publish.base.store.task.ChangeStoreStatusTask;
import com.spark.psi.publish.base.store.task.DeleteStoreTask;

/**
 * <p>公司仓库界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-11
 */

public class WizardStoreInfoStepProcessor extends WizardBaseStepProcessor{

	/**
	 * 控件ID 
	 */
	public final static String ID_Button_Add = "Button_Add";
	public final static String ID_Table_Store = "Table_Store";

	//
	private SEditTable table;

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation context){
		super.process(context);
		// 创建组件
		createUIComponent();
	}

	/**
	 * 创建组件
	 */
	private void createUIComponent(){
		//Table
		table = this.createControl(ID_Table_Store, SEditTable.class);
		//新增仓库
		Button addButton = this.createControl(ID_Button_Add, Button.class);
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				PageControllerInstance pci =
				        new PageControllerInstance(new PageController(StoreEditProcessor.class, StoreEditRender.class));
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(640, 300);
				MsgRequest request = new MsgRequest(pci, "新增仓库", windowStyle);
				request.setResponseHandler(new ResponseHandler(){
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4)
					{
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	/**
	 * 操作执行
	 */
	@Override
	protected boolean operateExecute(){
		return true;
	}

	/**
	 * 下一步执行
	 */
	@Override
	protected boolean nextExecute(){
		//非直供模式时，至少存在一个已启用的仓库
		TenantInfo tenantInfo = getContext().get(TenantInfo.class);
		Integer enabledCount = getContext().get(Integer.class, new FindEnabledStorageCountKey());
		if(!tenantInfo.isDirectDelivery() && enabledCount < 1){
			alert("至少要包含一个启用状态的仓库！");
			return false;
		}
		return true;
	}

	/**
	 * 仓库列表
	 */
	public static class InnerClass extends StoreListProcessor{

		/**
		 * 指定操作发生时，触发的事件
		 */
		@Override
		public void actionPerformed(final String rowId, String actionName, String actionValue){
			if(actionName.equals(Action.Active.name())){
				BaseFunction[] functions =
				        new BaseFunction[] {
				                new BaseFunction(new PageControllerInstance("InitStoreGoodsListPage", rowId), "商品库存"),
				                new BaseFunction(new PageControllerInstance("InitStoreKitListPage", rowId), "其他库存")};
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(1100, 540);
				MsgRequest request = new MsgRequest(functions, "仓库启用设置", windowStyle);
				request.setResponseHandler(new ResponseHandler(){
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4)
					{
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
			else if(actionName.equals(Action.Delete.name())){
				confirm("确定要删除所选仓库吗？", new Runnable(){
					public void run(){
						getContext().handle(new DeleteStoreTask(GUID.valueOf(rowId)));
						table.render();
					}
				});
			}
			else if(actionName.equals(Action.DeActive.name())){
				ChangeStoreStatusTask task = new ChangeStoreStatusTask(GUID.valueOf(rowId), StoreStatus.STOP);
				getContext().handle(task);
				table.render();
			}
			else if(actionName.equals("edit")){
				PageControllerInstance pci =
				        new PageControllerInstance(new PageController(StoreEditProcessor.class, StoreEditRender.class),
				                GUID.valueOf(rowId));
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(640, 300);
				MsgRequest request = new MsgRequest(pci, "编辑仓库", windowStyle);
				request.setResponseHandler(new ResponseHandler(){
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4)
					{
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
		}
	}

}
