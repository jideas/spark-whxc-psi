package com.spark.portal.browser;

import com.jiuqi.dna.core.exception.AbortException;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.ExceptionHandler;
import com.jiuqi.dna.ui.wt.InfomationException;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.pages.SMessageAlertWindow;
import com.spark.common.utils.exceptions.DataStatusExpireException;

public class SExceptionHandler implements ExceptionHandler {

	public final static SExceptionHandler INSTANCE = new SExceptionHandler();

	public void processException(Throwable t) {
		if (t instanceof AbortException) {
			return;
		}
		if (t instanceof InfomationException) {
			new SMessageAlertWindow(false, t.getMessage(), null);
		} else if (t instanceof DataStatusExpireException) {
			if(null == t.getMessage() || "".equals(t.getMessage())){
				new SMessageAlertWindow(true, "所操作的数据已经过期，请刷新界面！", null);
			}
			else{
				new SMessageAlertWindow(true, t.getMessage(), null);
			}
		} else {
			WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
			windowStyle.setSize(960, 500);
			PageController pc = new PageController(ErrorPageProcessor.class,
					ErrorPageRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, t);
			new FrameWindow(null, null, new BaseFunction[] { new BaseFunction(
					pci, "") }, "系统崩溃啦", windowStyle, null, null);
		}
	}

}
