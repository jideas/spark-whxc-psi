package com.spark.psi.base.browser.contact;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.contact.entity.ContactPersonItem;
import com.spark.psi.publish.base.contact.key.FindPersonalContactListKey;
import com.spark.psi.publish.base.contact.task.DeleteContactInfoTask;

/**
 * 
 * <p>
 * ͨѸ¼(����)�б�����
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 20012 - 20018<br>
 * 
 * 
 * @author ����
 * @version 2012-5-4
 */
public class PersonalContactListProcessor extends ContactBaseListProcessor<ContactPersonItem> {
	/**
	 * �ؼ�ID
	 */
	public final static String ID_BUTTON_ADDCONTACT = "Button_AddContact";
	public final static String ID_BUTTON_DELCONTACT = "Button_DelContact";
	// �鿴����
	public final static String ID_ACTION_VIEW = "view";

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation) {
		super.process(situation);
		// ������ϵ��
		Button addButton = this.createControl(ID_BUTTON_ADDCONTACT, Button.class, JWT.NONE);
		addButton.addActionListener(new AddButtonListener());
		// ɾ����ϵ��
		Button delButton = this.createControl(ID_BUTTON_DELCONTACT, Button.class, JWT.NONE);
		delButton.addActionListener(new DeleteButtonListener());
	}

	/**
	 * ���ͨѶ¼(����)�б�
	 */
	public Object[] getElements(Context context, STableStatus tablestatus) {
		FindPersonalContactListKey key = new FindPersonalContactListKey();
		// ƴ������
		key.setPhonetics(phoneticNavigatorBar.getText());
		// �����ַ���
		if (null != searchNoticeText.getText() && !searchNoticeText.getText().trim().equals("������������")) {
			key.setSearchText(searchNoticeText.getText().trim());
		}
		// ����
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<ContactPersonItem> contactPersonItemList = context.getList(ContactPersonItem.class, key);
		return contactPersonItemList != null ? contactPersonItemList.toArray() : new Object[0];
	}

	/**
	 * ��ȡָ�������ID
	 */
	public String getElementId(Object element) {
		return ((ContactPersonItem) element).getId().toString();
	}

	/**
	 * ��ȡ���ԶԱ�����ݽ���ɾ������
	 */
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}

	/**
	 * ��ȡ���Զ�ָ���ж������ɾ������
	 */
	@Override
	protected String[] getElementActionIds(Object element) {
		return new String[] { Action.Delete.name() };
	}

	/**
	 * �ж���ָ����������ʱ���������¼�
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (actionName.equals(ID_ACTION_VIEW)) { // ����
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(820, 500);
			ContactBookInfo contactBookInfo = getContext().get(ContactBookInfo.class, GUID.valueOf(rowId));
			PageControllerInstance controllerInstance = new PageControllerInstance("ContactEditPage", contactBookInfo);
			MsgRequest request = new MsgRequest(controllerInstance, "��ϵ����ϸ��Ϣ", style);
			// ��Ӧ
			request.setResponseHandler(new ResponseHandler() {

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		} else if (actionName.equals(Action.Delete.name())) { // ɾ��
			confirm("ȷ��ɾ����ǰ��ϵ�ˣ�", new Runnable() {

				public void run() {
					getContext().handle(new DeleteContactInfoTask(GUID.valueOf(rowId)));
					hint("ɾ���ɹ���");
					table.render();
				}
			});
		}  
	}

	/**
	 * ������ϵ�˰�ť������
	 */
	private class AddButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(820, 500);
			PageControllerInstance controllerInstance = new PageControllerInstance("ContactEditPage");
			MsgRequest request = new MsgRequest(controllerInstance, "������ϵ��", style);
			// ��Ӧ
			request.setResponseHandler(new ResponseHandler() {

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);

		}

	}

	/**
	 * ɾ����ϵ�˰�ť������
	 */
	private class DeleteButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (!isSelectedPerson()) {
				return;
			}
			confirm("ȷ��ɾ��ѡ�����ϵ�ˣ�", new Runnable() {

				public void run() {
					for (String rowId : table.getSelections()) {
						getContext().handle(new DeleteContactInfoTask(GUID.valueOf(rowId)));
						table.removeRow(rowId);
					}
					hint("ɾ���ɹ���");
					table.render();
				}
			});

		}

	}

	@Override
	protected String getExportFileTitle() {
		return "��ϵ��-����";
	}
}
