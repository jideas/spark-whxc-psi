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
 * <p>商品分类界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-11
 */

public class WizardGoodsCategoryStepRender extends WizardBaseStepRender{

	/**
	 * 标题
	 */
	@Override
	protected String getTitle(){
		return "配置商品分类";
	}

	/**
	 * 内容信息区
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
	 * 底部左边按钮区
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){
		// TODO Auto-generated method stub

	}
	
	/**
	 * 控制保存按钮是否可用
	 */
	protected static void enabledSaveButton(boolean enabled){
		
	}

	/**
	 * 商品分类编辑
	 */
	public static class InnerClass extends GoodsCategoryEditRender{

		/**
		 * 隐藏底部区
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
//					        if(node == null || hasSetPropertyFlag(node.getChildren())){ // 显示以当前节点为根节点的树
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
	 * 商品分类详细编辑
	 */
	public static class InnerClass2 extends GoodsCategoryDetailRender{
		/**
		 * 隐藏底部区
		 */
		protected boolean hideFooterArea(){
			return true;
		}
	}
	
	

}
