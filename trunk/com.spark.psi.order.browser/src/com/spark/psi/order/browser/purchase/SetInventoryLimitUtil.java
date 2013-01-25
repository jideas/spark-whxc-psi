package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.publish.inventory.entity.InventoryInfo;

/**
 * <p>设置库存预警工具类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-5-8
 */
abstract class SetInventoryLimitUtil {
	private final Situation context;
	protected enum LimitType{
		Count,
		Amount
	}

	/**
	 * @param context
	 */
	public SetInventoryLimitUtil(Situation context, InventoryInfo gid) {
		super();
		this.context = context;
		initPage(gid);
	}
	
	private void initPage(InventoryInfo gid) {
		PageControllerInstance pci = new PageControllerInstance(
				new PageController(SetInventoryLimitProcessor.class, SetInventoryLimitRender.class), gid);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(295, 160);
		MsgRequest request = new MsgRequest(pci, "修改阈值", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			
			public void handle(Object returnValue, Object returnValue2,
					Object returnValue3, Object returnValue4) {
				if(null != returnValue){
					setLimit((LimitType)returnValue, (Double)returnValue2, (Double)returnValue3);
				}
			}
		});
		context.bubbleMessage(request);
	}

	/**
	 * 修改库存
	 * @param type
	 * @param upper
	 * @param lower void
	 */
	protected abstract void setLimit(LimitType type, double upper, double lower);
}
