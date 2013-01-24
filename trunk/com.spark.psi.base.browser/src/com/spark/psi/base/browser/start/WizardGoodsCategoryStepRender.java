package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.base.browser.goods.GoodsCategoryDetailRender;
import com.spark.psi.base.browser.goods.GoodsCategoryEditRender;

/**
 * <p>��Ʒ���������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-11
 */

public class WizardGoodsCategoryStepRender extends WizardBaseStepRender{

	/**
	 * ����
	 */
	@Override
	protected String getTitle(){
		return "������Ʒ����";
	}

	/**
	 * ������Ϣ��
	 */
	@Override
	protected void addInfoArea(Composite infoArea){
		PageController pageController =
		        new PageController(WizardGoodsCategoryStepProcessor.InnerClass.class,
		                WizardGoodsCategoryStepRender.InnerClass.class);
		infoArea.showPage(ControllerPage.NAME, new PageControllerInstance(pageController));
		//
		getContext().regMessageListener(EnabledSaveButtonMessage.class, new MessageListener<EnabledSaveButtonMessage>(){

			public void onMessage(Situation context, EnabledSaveButtonMessage message,
                    MessageTransmitter<EnabledSaveButtonMessage> transmitter)
            {
				transmitter.terminate();
				operateButton.setEnabled(message.isEnabled());
            }
		});
	}

	/**
	 * �ײ���߰�ť��
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){
		// TODO Auto-generated method stub

	}
	
	/**
	 * ���Ʊ��水ť�Ƿ����
	 */
	protected static void enabledSaveButton(boolean enabled){
		
	}

	/**
	 * ��Ʒ����༭
	 */
	public static class InnerClass extends GoodsCategoryEditRender{

		/**
		 * ���صײ���
		 */
		protected boolean hideFooterArea(){
			return true;
		}

		@Override
		protected void doRender(){
			super.doRender();
			//
			rightArea.clear();
			rightArea.setLayout(new FillLayout());
			rightArea.setID(WizardGoodsCategoryStepProcessor.InnerClass.ID_Composite_RightArea);
//			//
//			this.getContext().regMessageListener(GoodsCategorySelectionMsg.class,
//			        new MessageListener<GoodsCategorySelectionMsg>(){
//				        public void onMessage(Situation context, GoodsCategorySelectionMsg message,
//				                MessageTransmitter<GoodsCategorySelectionMsg> transmitter)
//				        {
//				        	transmitter.terminate();
//					        rightArea.clear();
//					        GoodsCategoryTree treeData = getContext().find(GoodsCategoryTree.class);
//					        CategoryNode node = treeData.getNodeById(message.getCategoryId());
//					        if(node == null || hasSetPropertyFlag(node.getChildren())){ // ��ʾ�Ե�ǰ�ڵ�Ϊ���ڵ����
//						        new GoodsCategoryTreePage(rightArea, node, false);
//						        rightArea.layout();
//						        getContext().bubbleMessage(new EnabledSaveButtonMessage(Boolean.FALSE));
//					        }
//					        else{
//						        CategoryNode propertiedParentNode = getPropertiedParent(node);
//						        PageController pc =
//						                new PageController(WizardGoodsCategoryStepProcessor.InnerClass2.class,
//						                        WizardGoodsCategoryStepRender.InnerClass2.class);
//						        rightArea.showPage(ControllerPage.NAME, new PageControllerInstance(pc, new GUID[] {
//						                node.getId(),
//						                propertiedParentNode != null ? propertiedParentNode.getId() : null}));
//						        getContext().bubbleMessage(new EnabledSaveButtonMessage(Boolean.TRUE));
//					        }
//				        }
//			        });
		}
	}

	/**
	 * ��Ʒ������ϸ�༭
	 */
	public static class InnerClass2 extends GoodsCategoryDetailRender{
		/**
		 * ���صײ���
		 */
		protected boolean hideFooterArea(){
			return true;
		}
	}
	
	

}
