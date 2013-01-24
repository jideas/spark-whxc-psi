package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.goods.GoodsCategoryDetailProcessor;
import com.spark.psi.base.browser.goods.GoodsCategoryEditProcessor;
import com.spark.psi.base.browser.goods.GoodsCategorySelectionMsg;
import com.spark.psi.base.browser.goods.GoodsCategoryTreePage;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree.CategoryNode;

/**
 * <p>��Ʒ������洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-11
 */

public class WizardGoodsCategoryStepProcessor extends WizardBaseStepProcessor{

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context
	 */
	public void postProcess(Situation context) {
		super.postProcess(context);
		//Ĭ�ϲ�����
		operateButton.setEnabled(Boolean.FALSE);
	}
	
	/**
	 * ����ִ��
	 */
	@Override
    protected boolean operateExecute(){
	    SaveGoodsCategoryInfoMessage message = new SaveGoodsCategoryInfoMessage();
	    message.setResponseHandler(new ResponseHandler(){
			
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
				if(returnValue != null && (Boolean)returnValue){
					//
					hint("����ɹ���");
				}
			}
		});
	    getContext().broadcastMessage(message);
	    return true;
    }
	
	/**
	 * ��Ʒ����༭
	 */
	public static class InnerClass extends GoodsCategoryEditProcessor{
		/**
		 * ���ID
		 */
		public final static String ID_Composite_RightArea = "Composite_RightArea";
		
		/**
		 * ҳ���ʼ��
		 * 
		 * @param situation
		 */
		@Override
		public void process(Situation context){
			super.process(context);
			//
			final Composite rightArea = this.createControl(ID_Composite_RightArea, Composite.class);
			//
			this.getContext().regMessageListener(GoodsCategorySelectionMsg.class,
			        new MessageListener<GoodsCategorySelectionMsg>(){
				        public void onMessage(Situation context, GoodsCategorySelectionMsg message,
				                MessageTransmitter<GoodsCategorySelectionMsg> transmitter)
				        {
				        	transmitter.terminate();
					        rightArea.clear();
					        GoodsCategoryTree treeData = getContext().find(GoodsCategoryTree.class);
					        CategoryNode node = treeData.getNodeById(message.getCategoryId());
					        if(node == null || hasSetPropertyFlag(node.getChildren())){ // ��ʾ�Ե�ǰ�ڵ�Ϊ���ڵ����
						        new GoodsCategoryTreePage(rightArea, node, false);
						        rightArea.layout();
						        getContext().bubbleMessage(new EnabledSaveButtonMessage(Boolean.FALSE));
						        resetDataChangedstatus();
					        }
					        else{
						        CategoryNode propertiedParentNode = getPropertiedParent(node);
						        PageController pc =
						                new PageController(WizardGoodsCategoryStepProcessor.InnerClass2.class,
						                        WizardGoodsCategoryStepRender.InnerClass2.class);
						        rightArea.showPage(ControllerPage.NAME, new PageControllerInstance(pc, new GUID[] {
						                node.getId(),
						                propertiedParentNode != null ? propertiedParentNode.getId() : null}));
						        getContext().bubbleMessage(new EnabledSaveButtonMessage(Boolean.TRUE));
					        }
				        }
			        });
		}
		
		protected CategoryNode getPropertiedParent(CategoryNode node) {
			CategoryNode parentNode = node.getParent();
			while (parentNode != null) {
				if (parentNode.isSetPropertyFlag()) {
					break;
				}
				parentNode = parentNode.getParent();
			}
			return parentNode;
		}

		protected boolean hasSetPropertyFlag(CategoryNode[] nodes) {
			for (CategoryNode node : nodes) {
				if (node.isSetPropertyFlag() || hasSetPropertyFlag(node.getChildren())) {
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * ��Ʒ������ϸ�༭
	 */
	public static class InnerClass2 extends GoodsCategoryDetailProcessor{
		
	}

}
