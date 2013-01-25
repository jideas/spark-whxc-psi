package com.spark.psi.publish.base.notice.task;

import com.jiuqi.dna.core.invoke.Task;

/**
 * <p>公告定时器处理</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-27
 */

public class TimerNoticeTask  extends Task<TimerNoticeTask.Method>{

	public enum Method{
		AUTOPUBLISH,
		AUTOOVERDUE
	}
}
