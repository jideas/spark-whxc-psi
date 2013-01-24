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
 * <p>��˾�ֿ���洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-11
 */

public class WizardStoreInfoStepProcessor extends WizardBaseStepProcessor{

	/**
	 * �ؼ�ID 
	 */
	public final static String ID_Button_Add = "Button_Add";
	public final static String ID_Table_Store = "Table_Store";

	//
	private SEditTable table;

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation context){
		super.process(context);
		// �������
		createUIComponent();
	}

	/**
	 * �������
	 */
	private void createUIComponent(){
		//Table
		table = this.createControl(ID_Table_Store, SEditTable.class);
		//�����ֿ�
		Button addButton = this.createControl(ID_Button_Add, Button.class);
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				PageControllerInstance pci =
				        new PageControllerInstance(new PageController(StoreEditProcessor.class, StoreEditRender.class));
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(640, 300);
				MsgRequest request = new MsgRequest(pci, "�����ֿ�", windowStyle);
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
	 * ����ִ��
	 */
	@Override
	protected boolean operateExecute(){
		return true;
	}

	/**
	 * ��һ��ִ��
	 */
	@Override
	protected boolean nextExecute(){
		//��ֱ��ģʽʱ�����ٴ���һ�������õĲֿ�
		TenantInfo tenantInfo = getContext().get(TenantInfo.class);
		Integer enabledCount = getContext().get(Integer.class, new FindEnabledStorageCountKey());
		if(!tenantInfo.isDirectDelivery() && enabledCount < 1){
			alert("����Ҫ����һ������״̬�Ĳֿ⣡");
			return false;
		}
		return true;
	}

	/**
	 * �ֿ��б�
	 */
	public static class InnerClass extends StoreListProcessor{

		/**
		 * ָ����������ʱ���������¼�
		 */
		@Override
		public void actionPerformed(final String rowId, String actionName, String actionValue){
			if(actionName.equals(Action.Active.name())){
				BaseFunction[] functions =
				        new BaseFunction[] {
				                new BaseFunction(new PageControllerInstance("InitStoreGoodsListPage", rowId), "��Ʒ���"),
				                new BaseFunction(new PageControllerInstance("InitStoreKitListPage", rowId), "�������")};
				WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
				windowStyle.setSize(1100, 540);
				MsgRequest request = new MsgRequest(functions, "�ֿ���������", windowStyle);
				request.setResponseHandler(new ResponseHandler(){
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4)
					{
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
			else if(actionName.equals(Action.Delete.name())){
				confirm("ȷ��Ҫɾ����ѡ�ֿ���", new Runnable(){
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
				MsgRequest request = new MsgRequest(pci, "�༭�ֿ�", windowStyle);
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
