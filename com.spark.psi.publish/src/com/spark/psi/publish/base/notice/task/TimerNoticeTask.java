package com.spark.psi.publish.base.notice.task;

import com.jiuqi.dna.core.invoke.Task;

/**
 * <p>���涨ʱ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-27
 */

public class TimerNoticeTask  extends Task<TimerNoticeTask.Method>{

	public enum Method{
		AUTOPUBLISH,
		AUTOOVERDUE
	}
}
