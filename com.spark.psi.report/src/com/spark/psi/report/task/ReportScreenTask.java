/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.report.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-12-13       ���ɹ�        
 * ============================================================*/

package com.spark.psi.report.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.report.entity.ReportScreen;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ���ɹ�
 * @version 2011-12-13
 */

public class ReportScreenTask extends SimpleTask{

	public ReportScreenTask(ReportScreen reportScreen,long dateTime){
		this.reportScreen = reportScreen;
		this.dateTime = dateTime;
	}
	private long dateTime;
	private ReportScreen reportScreen; // ������ʵ��

	/**
	 * @return ������ʵ��
	 */
	public ReportScreen getReportScreen(){
		return reportScreen;
	}

	/**
	 * @param reportScreen ������ʵ��
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
