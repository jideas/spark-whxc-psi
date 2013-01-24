package com.spark.psi.base.browser.notice;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.notice.entity.NoticeInfo;
import com.spark.psi.publish.base.notice.entity.NoticeItem;
import com.spark.psi.publish.base.notice.key.FindNoticeInfoKey;
import com.spark.psi.publish.base.notice.key.FindNoticeItemListKey;
import com.spark.psi.publish.base.notice.task.DeleteNoticeTask;

/**
 * 
 * <p>δ���������б�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-19
 */
public class PublishingNoticeListProcessor extends PSIListPageProcessor<NoticeItem>{

	/**
	 * �ؼ�ID
	 */
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_ADDNOTICE = "Button_AddNotice";
	//�༭����
	public final static String ID_ACTION_EDIT = "edit";

	/**
	 * �ؼ�
	 */
	private Label noticeCountLabel;
	private Text searchNoticeText;
	private Button addNoticeButton;

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//��������
		noticeCountLabel = this.createControl(ID_LABEL_COUNT, Label.class, JWT.NONE);
		//�����ı�
		searchNoticeText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class, JWT.NONE);
		//�½�����
		addNoticeButton = this.createControl(ID_BUTTON_ADDNOTICE, Button.class, JWT.NONE);
		//�������水ť������
		addNoticeButtonListenter();
		//������ť������
		searchNoticeButtonListenter();
	}

	/**
	 * ��ѯδ��������
	 */
	private List<NoticeItem> findNoticeItemList(Context context, STableStatus tablestatus){
		FindNoticeItemListKey key = new FindNoticeItemListKey();
		//��ѯδ��������
		key.setType(FindNoticeItemListKey.NOT_RELEASE);
		//�����ַ���
		if(StringHelper.isNotEmpty(searchNoticeText.getText()) && !searchNoticeText.getText().trim().equals("������������")){
			key.setSearchText(searchNoticeText.getText().trim());
		}
		LoginInfo loginInfo = context.get(LoginInfo.class);
		//��Bossֻ��ѯ���ѵĹ��棬���ô�����GUID
		if(!loginInfo.hasAuth(Auth.Boss)){
			key.setCreateGuid(loginInfo.getEmployeeInfo().getId());
		}
		//����
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<NoticeItem> noticeItemList = context.getList(NoticeItem.class, key);
		return noticeItemList;
	}

	/**
	 * ���δ���������б�
	 */
	public Object[] getElements(Context context, STableStatus tablestatus){
		List<NoticeItem> noticeItemList = findNoticeItemList(context, tablestatus);
		//���ù�������
		noticeCountLabel.setText((null != noticeItemList ? noticeItemList.size() : 0) + "");
		return null != noticeItemList ? noticeItemList.toArray() : null;
	}

	/**
	 * ��ȡָ�������ID
	 */
	public String getElementId(Object element){
		return ((NoticeItem)element).getRECID().toString();
	}

	/**
	 * ��ȡ���ԶԱ�����ݽ���ɾ������
	 */
	@Override
	public String[] getTableActionIds(){
		return new String[] {Action.Delete.name()};
	}

	/**
	 * ��ȡ���Զ�ָ���ж������ɾ������
	 */
	@Override
	protected String[] getElementActionIds(Object element){
		return new String[] {Action.Delete.name()};
	}

	/**
	 * �ж���ָ����������ʱ���������¼�
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(actionName.equals(Action.Delete.name())){ //ɾ������
			confirm("ȷ��ɾ����ǰ���棿", new Runnable(){

				public void run(){
					DeleteNoticeTask deleteNoticeTask = new DeleteNoticeTask();
					deleteNoticeTask.setRECID(GUID.valueOf(rowId));
					getContext().handle(deleteNoticeTask);
					hint("ɾ���ɹ���");
					table.render();
				}
			});
		}
		else if(ID_ACTION_EDIT.equals(actionName)){ //�༭����
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(750, 500);
			FindNoticeInfoKey findNoticeInfoKey = new FindNoticeInfoKey();
			findNoticeInfoKey.setRECID(GUID.valueOf(rowId));
			NoticeInfo noticeInfo = getContext().get(NoticeInfo.class, findNoticeInfoKey);
			PageControllerInstance controllerInstance = new PageControllerInstance("addNoticePage", noticeInfo);
			MsgRequest request = new MsgRequest(controllerInstance, "�༭����", style);
			//��Ӧ
			request.setResponseHandler(new ResponseHandler(){

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					hint("����ɹ����ڷ����գ�ϵͳ���Զ������ù��档");
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	/**
	 * �������水ť�����¼�������
	 */
	protected void addNoticeButtonListenter(){
		addNoticeButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
				style.setSize(750, 500);
				PageControllerInstance controllerInstance = new PageControllerInstance("addNoticePage");
				MsgRequest request = new MsgRequest(controllerInstance, "�½�����", style);
				//��Ӧ
				request.setResponseHandler(new ResponseHandler(){

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4)
					{
						hint("����ɹ����ڷ����գ�ϵͳ���Զ������ù��档");
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	/**
	 * ������ť�����¼�������
	 */
	protected void searchNoticeButtonListenter(){
		searchNoticeText.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				table.render();
			}
		});
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}
