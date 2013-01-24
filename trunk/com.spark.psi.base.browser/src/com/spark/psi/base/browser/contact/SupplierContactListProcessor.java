package com.spark.psi.base.browser.contact;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.contact.entity.ContactItem;
import com.spark.psi.publish.base.contact.entity.ContactPersonItem;
import com.spark.psi.publish.base.contact.key.FindCustomerSupplierContactListKey;

/**
 * 
 * <p>ͨѸ¼(��Ӧ��)�б�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-4
 */
public class SupplierContactListProcessor extends ContactBaseListProcessor<ContactItem>{
	/**
	 * �ؼ�ID
	 */
	public final static String ID_BUTTON_SENDMSG = "Button_SendMsg";
	public final static String ID_BUTTON_SENDMAIL = "Button_SendMail";
	//�鿴����
	public final static String ID_ACTION_VIEW = "view";

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//���Ͷ���
		Button sendMsgButton = this.createControl(ID_BUTTON_SENDMSG, Button.class, JWT.NONE);
		sendMsgButton.addActionListener(new SendMsgButtonListener());
		//�����ʼ�
		Button sendEmailButton = this.createControl(ID_BUTTON_SENDMAIL, Button.class, JWT.NONE);
		sendEmailButton.addActionListener(new SendEmailButtonListener());
	}

	/**
	 * ���ͨѶ¼(��Ӧ��)�б�
	 */
	public Object[] getElements(Context context, STableStatus tablestatus){
		FindCustomerSupplierContactListKey key = new FindCustomerSupplierContactListKey();
		//��ѯ��Ӧ��
		key.setPartnerType(PartnerType.Supplier);
		//ƴ������
		key.setPhonetics(phoneticNavigatorBar.getText());
		//�����ַ���
		if(null != searchNoticeText.getText() && !searchNoticeText.getText().trim().equals("������������")){
			key.setSearchText(searchNoticeText.getText().trim());
		}
		//����
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<ContactPersonItem> contactPersonItemList = context.getList(ContactPersonItem.class, key);
		return contactPersonItemList != null ? contactPersonItemList.toArray() : new Object[0];
	}

	/**
	 * ��ȡָ�������ID
	 */
	public String getElementId(Object element){
		return ((ContactPersonItem)element).getId().toString();
	} 

	/**
	 * �ж���ָ����������ʱ���������¼�
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(actionName.equals(ID_ACTION_VIEW)){ //����
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(820, 500);
			ContactBookInfo contactBookInfo = getContext().get(ContactBookInfo.class, GUID.valueOf(rowId));
			PageControllerInstance controllerInstance = new PageControllerInstance("ContactEditPage", contactBookInfo);
			MsgRequest request = new MsgRequest(controllerInstance, "��ϵ����ϸ��Ϣ", style);
			//��Ӧ
			request.setResponseHandler(new ResponseHandler(){

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		} 
	}

	@Override
	protected String getExportFileTitle() {
		return "��ϵ��-��Ӧ��";
	}
}
