package com.spark.psi.base.browser.material;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.mdcommon.CategoryListChangeMsg;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;

/**
 * 在售商品列表处理器
 */
public class OnSaleMaterialListProcessor extends MaterialCategoryFramePageProcessor {
	public final static String ID_Button_EditCategory = "Button_EditCategory";

	@Override
	public void process(final Situation context) {
		super.process(context);
		final LoginInfo loginInfo = context.find(LoginInfo.class);
		final Button editCategoryButton = this.createControl(ID_Button_EditCategory,
				Button.class, JWT.NONE);
		if(loginInfo.hasAuth(Auth.SubFunction_MaterialManage_Category)) {
			editCategoryButton.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent e) {
					// 打开管理分类页面
					MsgRequest request = new MsgRequest(new PageControllerInstance(
							"MaterialListEditPage"), "分类管理");
					request.setResponseHandler(new ResponseHandler() {
						
						public void handle(Object returnValue, Object returnValue2,
								Object returnValue3, Object returnValue4) {
							if(returnValue == null) {
								context.broadcastMessage(new CategoryListChangeMsg(null));
							} else {
								context.broadcastMessage(new CategoryListChangeMsg((GUID)returnValue));
							}
						}
					});
					context.bubbleMessage(request);
					
				}
			});
		} else {
			editCategoryButton.dispose();
		}
	}
}
