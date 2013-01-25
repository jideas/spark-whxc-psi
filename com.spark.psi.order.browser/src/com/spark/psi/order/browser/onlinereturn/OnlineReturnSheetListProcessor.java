/**
 * 
 */
package com.spark.psi.order.browser.onlinereturn;

import com.jiuqi.dna.core.situation.Situation;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.onlinereturn.entity.OnlineReturnItem;

/**
 * ���ύ�����˻����б�����
 * 
 * �����˻�
 * 
 */
public abstract class OnlineReturnSheetListProcessor extends PSIListPageProcessor<OnlineReturnItem> {


	public enum OlReturnColumns {
		CreateDate("�˻�����"), Customer("��Ա"), billsNo("�˻�����"), OnlineBillsNo("��ص���"), Creator("������"), Status(
				"����״̬"), Amount("���")// , Station("�˻�վ��")
		;
		private String title;

		private OlReturnColumns(String title) {
			this.title = title;
		}

		public String title() {
			return title;
		}
	}
	
	protected LoginInfo loginInfo  = null;

	@Override
	public void init(Situation context) {
		super.init(context);
		loginInfo = context.find(LoginInfo.class);
	}

	@Override
	public void process(Situation context) {
		super.process(context);
	}

	@Override
	protected String getExportFileTitle() {
		return "�����˻�";
	}

//	protected void setBillsCount(int count) {
//		labelCount.setText(DoubleUtil.getRoundStr(DoubleUtil.strToDouble(count + ""), 0));
//	}
//
//	protected String getSearchText() {
//		return this.textSearch.getText();
//	}

}