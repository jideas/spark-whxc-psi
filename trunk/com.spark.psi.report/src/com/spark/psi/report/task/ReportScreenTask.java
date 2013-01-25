/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.report.intf.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-12-13       汤成国        
 * ============================================================*/

package com.spark.psi.report.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.report.entity.ReportScreen;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 汤成国
 * @version 2011-12-13
 */

public class ReportScreenTask extends SimpleTask{

	public ReportScreenTask(ReportScreen reportScreen,long dateTime){
		this.reportScreen = reportScreen;
		this.dateTime = dateTime;
	}
	private long dateTime;
	private ReportScreen reportScreen; // 滚动屏实体

	/**
	 * @return 滚动屏实体
	 */
	public ReportScreen getReportScreen(){
		return reportScreen;
	}

	/**
	 * @param reportScreen 滚动屏实体
	 */
	public void setReportScreen(ReportScreen reportScreen){
		this.reportScreen = reportScreen;
	}

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}
}
