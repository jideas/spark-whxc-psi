package com.spark.psi.base.browser.notice;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.notice.entity.NoticeItem;
import com.spark.psi.publish.base.notice.key.FindNoticeItemListKey;
import com.spark.psi.publish.base.notice.task.DeleteNoticeTask;

/**
 * 
 * <p>��ʷ�����б�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-4
 */
public class HistoryNoticeListProcessor extends PSIListPageProcessor<NoticeItem>{
	/**
	 * �ؼ�ID
	 */
	public final static String ID_COMBO_TIME = "Combo_Time";
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	//�鿴��������
	public final static String ID_ACTION_VIEW = "view";

	/**
	 * �ؼ�
	 */
	private LWComboList publishTimeComboList;
	private Label noticeCountLabel;
	private Text searchNoticeText;

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//����ʱ��
		publishTimeComboList = this.createControl(ID_COMBO_TIME, LWComboList.class);
		PSIProcessorUtils.initQueryTermSource(publishTimeComboList);
		//��������ѡ���¼�������
		timeSelectListenter();
		//��������
		noticeCountLabel = this.createControl(ID_LABEL_COUNT, Label.class, JWT.NONE);
		//�����ı�
		searchNoticeText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class, JWT.NONE);
		//������ť������
		searchNoticeButtonListenter();
	}
	
	/**
	 * �����ʷ�������б�
	 */
	public Object[] getElements(Context context, STableStatus tablestatus){
		FindNoticeItemListKey key = new FindNoticeItemListKey();
		//��ѯδ��������
		key.setType(FindNoticeItemListKey.HISTORY);
		//�����ַ���
		if(null != searchNoticeText.getText() && !searchNoticeText.getText().trim().equals("������������")){
			key.setSearchText(searchNoticeText.getText().trim());
		}
		LoginInfo loginInfo = context.get(LoginInfo.class);
		//��Boss
		if(!loginInfo.hasAuth(Auth.Boss)){
			//��ѯ�������Ѳ��ŵĹ��棬���ù�����������GUID
			key.setDeptGuid(loginInfo.getEmployeeInfo().getDepartmentId());
			//��ѯ���ѵĹ��棬���ô�����GUID
			key.setCreateGuid(loginInfo.getEmployeeInfo().getId());
		}
		//����������ʼʱ��
		key.setQueryTerm(getContext().find(QueryTerm.class, publishTimeComboList.getText()));
		//����
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<NoticeItem> noticeItemList = context.getList(NoticeItem.class, key);
		//���ù�������
		noticeCountLabel.setText((noticeItemList != null ? noticeItemList.size() : 0)+"");
		return noticeItemList != null ? noticeItemList.toArray() : new Object[0];
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
		NoticeItem noticeItem = (NoticeItem)element;
		//ֻ���ܾ��������г���Ȩ�ޣ��ܾ���ɳ������У�����ֻ�ܳ������Ѵ����Ĺ���
		LoginInfo loginInfo = getContext().find(LoginInfo.class);
		boolean hasRoot =
		        loginInfo.hasAuth(Auth.Boss)
		                || ((loginInfo.hasAuth(Auth.AccountManager) || loginInfo.hasAuth(Auth.SalesManager)
		                        || loginInfo.hasAuth(Auth.PurchaseManager) || loginInfo
		                        .hasAuth(Auth.StoreKeeperManager)) && loginInfo.getEmployeeInfo().getId().equals(
		                        noticeItem.getCreateGuid()));
		if(hasRoot){
			return new String[] {Action.Delete.name()};
		}
		else{
			return null;
		}
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
		}else if(ID_ACTION_VIEW.equals(actionName)){ //�鿴����
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(750, 500);
			PageControllerInstance controllerInstance = new PageControllerInstance("noticeInfoPage", rowId);
			MsgRequest request = new MsgRequest(controllerInstance, "��������", style);
			getContext().bubbleMessage(request);
		}
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
	
	/**
	 * ��������ѡ���¼�������
	 */
	protected void timeSelectListenter(){
		publishTimeComboList.addSelectionListener(new SelectionListener(){
			
			public void widgetSelected(SelectionEvent e){
				table.render();
			}
		});
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	}
}
