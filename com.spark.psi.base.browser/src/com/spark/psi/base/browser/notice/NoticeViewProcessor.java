package com.spark.psi.base.browser.notice;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Browser;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.NoticeStatus;
import com.spark.psi.publish.base.notice.entity.NoticeInfo;
import com.spark.psi.publish.base.notice.key.FindPublishedNoticeInfoKey;
import com.spark.psi.publish.base.notice.task.CancelNoticeTask;

/**
 * 
 * <p>����鿴���洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-23
 */
public class NoticeViewProcessor extends PageProcessor{

	/**
	 * �ؼ�ID
	 */
	public final static String ID_Label_NoticeTile = "Label_NoticeTile";
	public final static String ID_Label_DeptNameStr = "Label_DeptNameStr";
	public final static String ID_Label_Content = "Label_Content";
	public final static String ID_Label_Publishing = "Label_Publishing";
	public final static String ID_Button_Cancel = "Button_Cancel";

	/**
	 * �ؼ�
	 */
	private Label noticeTitleLabel;
	private Label deptNameStrLabel;
	private Label publishingDateLabel;
	private Browser noticeContentBrowser;
	
	/**
	 * ��������
	 */
	private NoticeInfo noticeInfo;
	
	/**
	 * ҳ���ʼ��֮ǰ
	 */
	public void init(Situation context) {
		//��ù�������
		FindPublishedNoticeInfoKey findPublishedNoticeInfoKey = new FindPublishedNoticeInfoKey();
		findPublishedNoticeInfoKey.setRECID(GUID.valueOf(this.getArgument().toString()));
		noticeInfo = getContext().get(NoticeInfo.class, findPublishedNoticeInfoKey);
	}

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation){
		//�������
		noticeTitleLabel = this.createControl(ID_Label_NoticeTile, Label.class, JWT.NONE);
		//��������
		noticeContentBrowser = this.createControl(ID_Label_Content, Browser.class, JWT.NONE);
		//������Χ
		deptNameStrLabel = this.createControl(ID_Label_DeptNameStr, Label.class, JWT.NONE);
		//����ʱ��
		publishingDateLabel = this.createControl(ID_Label_Publishing, Label.class, JWT.NONE);
	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context ������
	 */
	public void postProcess(Situation context) {
		//��ʼ����������
		initNoticeInfo();
		//����ȡ����ť
		createCancelButton();
	}

	/**
	 * ����������ť
	 */
	private void createCancelButton(){
		//ֻ���ܾ��������г���Ȩ�ޣ��ܾ���ɳ������У�����ֻ�ܳ������Ѵ����Ĺ���
		LoginInfo loginInfo = getContext().find(LoginInfo.class);
		boolean hasRoot =
		        loginInfo.hasAuth(Auth.Boss)
		                || ((loginInfo.hasAuth(Auth.AccountManager) || loginInfo.hasAuth(Auth.SalesManager)
		                        || loginInfo.hasAuth(Auth.PurchaseManager) || loginInfo
		                        .hasAuth(Auth.StoreKeeperManager)) && loginInfo.getEmployeeInfo().getId()
		                        .equals(noticeInfo.getCreateGuid()));
		Button cancelButton = this.createControl(ID_Button_Cancel, Button.class, JWT.NONE);
		//��Ȩ�޳�����Ϊ�ѷ���״̬
		if(hasRoot && noticeInfo.getNoticeStatus().equals(NoticeStatus.Released.getKey())){
			//������ť
			cancelButton.addActionListener(new ActionListener(){
	
				public void actionPerformed(ActionEvent e){
					CancelNoticeTask cancelNoticeTask = new CancelNoticeTask();
					cancelNoticeTask.setRECID(noticeInfo.getRECID());
					cancelNoticeTask.setNoticeStatus(NoticeStatus.Cancel.getKey());
					getContext().handle(cancelNoticeTask);
					MsgResponse response = new MsgResponse(true);
					getContext().bubbleMessage(response);
				}
			});
		}else{
			cancelButton.setVisible(false);
		}
	}

	/**
	 * ��ʼ����������
	 */
	private void initNoticeInfo(){
		if(null != noticeInfo){
			noticeTitleLabel.setText(noticeInfo.getNoticeTitle());
			deptNameStrLabel.setText("������Χ��" + noticeInfo.getDeptNameStr());
			publishingDateLabel.setText("����ʱ�䣺" + DateUtil.dateFromat(noticeInfo.getPublishTime()));
			noticeContentBrowser.setHTML("<body style='background-color:#D6E8F4;'>"+noticeInfo.getNoticeContent()+"</body>");
		}
	}
}
