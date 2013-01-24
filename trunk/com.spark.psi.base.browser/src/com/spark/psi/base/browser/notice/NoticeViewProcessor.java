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
 * <p>公告查看界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-23
 */
public class NoticeViewProcessor extends PageProcessor{

	/**
	 * 控件ID
	 */
	public final static String ID_Label_NoticeTile = "Label_NoticeTile";
	public final static String ID_Label_DeptNameStr = "Label_DeptNameStr";
	public final static String ID_Label_Content = "Label_Content";
	public final static String ID_Label_Publishing = "Label_Publishing";
	public final static String ID_Button_Cancel = "Button_Cancel";

	/**
	 * 控件
	 */
	private Label noticeTitleLabel;
	private Label deptNameStrLabel;
	private Label publishingDateLabel;
	private Browser noticeContentBrowser;
	
	/**
	 * 公告详情
	 */
	private NoticeInfo noticeInfo;
	
	/**
	 * 页面初始化之前
	 */
	public void init(Situation context) {
		//获得公告详情
		FindPublishedNoticeInfoKey findPublishedNoticeInfoKey = new FindPublishedNoticeInfoKey();
		findPublishedNoticeInfoKey.setRECID(GUID.valueOf(this.getArgument().toString()));
		noticeInfo = getContext().get(NoticeInfo.class, findPublishedNoticeInfoKey);
	}

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation situation){
		//公告标题
		noticeTitleLabel = this.createControl(ID_Label_NoticeTile, Label.class, JWT.NONE);
		//公告内容
		noticeContentBrowser = this.createControl(ID_Label_Content, Browser.class, JWT.NONE);
		//发布范围
		deptNameStrLabel = this.createControl(ID_Label_DeptNameStr, Label.class, JWT.NONE);
		//发布时间
		publishingDateLabel = this.createControl(ID_Label_Publishing, Label.class, JWT.NONE);
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context 上下文
	 */
	public void postProcess(Situation context) {
		//初始化公告详情
		initNoticeInfo();
		//创建取消按钮
		createCancelButton();
	}

	/**
	 * 创建撤消按钮
	 */
	private void createCancelButton(){
		//只有总经理或经理具有撤消权限，总经理可撤消所有，经理只能撤消自已创建的公告
		LoginInfo loginInfo = getContext().find(LoginInfo.class);
		boolean hasRoot =
		        loginInfo.hasAuth(Auth.Boss)
		                || ((loginInfo.hasAuth(Auth.AccountManager) || loginInfo.hasAuth(Auth.SalesManager)
		                        || loginInfo.hasAuth(Auth.PurchaseManager) || loginInfo
		                        .hasAuth(Auth.StoreKeeperManager)) && loginInfo.getEmployeeInfo().getId()
		                        .equals(noticeInfo.getCreateGuid()));
		Button cancelButton = this.createControl(ID_Button_Cancel, Button.class, JWT.NONE);
		//有权限撤消且为已发布状态
		if(hasRoot && noticeInfo.getNoticeStatus().equals(NoticeStatus.Released.getKey())){
			//撤消按钮
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
	 * 初始化公告详情
	 */
	private void initNoticeInfo(){
		if(null != noticeInfo){
			noticeTitleLabel.setText(noticeInfo.getNoticeTitle());
			deptNameStrLabel.setText("发布范围：" + noticeInfo.getDeptNameStr());
			publishingDateLabel.setText("发布时间：" + DateUtil.dateFromat(noticeInfo.getPublishTime()));
			noticeContentBrowser.setHTML("<body style='background-color:#D6E8F4;'>"+noticeInfo.getNoticeContent()+"</body>");
		}
	}
}
