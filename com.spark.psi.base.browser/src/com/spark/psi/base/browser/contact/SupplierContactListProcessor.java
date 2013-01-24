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
 * <p>通迅录(供应商)列表处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-4
 */
public class SupplierContactListProcessor extends ContactBaseListProcessor<ContactItem>{
	/**
	 * 控件ID
	 */
	public final static String ID_BUTTON_SENDMSG = "Button_SendMsg";
	public final static String ID_BUTTON_SENDMAIL = "Button_SendMail";
	//查看详情
	public final static String ID_ACTION_VIEW = "view";

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//发送短信
		Button sendMsgButton = this.createControl(ID_BUTTON_SENDMSG, Button.class, JWT.NONE);
		sendMsgButton.addActionListener(new SendMsgButtonListener());
		//发送邮件
		Button sendEmailButton = this.createControl(ID_BUTTON_SENDMAIL, Button.class, JWT.NONE);
		sendEmailButton.addActionListener(new SendEmailButtonListener());
	}

	/**
	 * 获得通讯录(供应商)列表
	 */
	public Object[] getElements(Context context, STableStatus tablestatus){
		FindCustomerSupplierContactListKey key = new FindCustomerSupplierContactListKey();
		//查询供应商
		key.setPartnerType(PartnerType.Supplier);
		//拼音过滤
		key.setPhonetics(phoneticNavigatorBar.getText());
		//搜索字符串
		if(null != searchNoticeText.getText() && !searchNoticeText.getText().trim().equals("输入搜索内容")){
			key.setSearchText(searchNoticeText.getText().trim());
		}
		//排序
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<ContactPersonItem> contactPersonItemList = context.getList(ContactPersonItem.class, key);
		return contactPersonItemList != null ? contactPersonItemList.toArray() : new Object[0];
	}

	/**
	 * 获取指定对象的ID
	 */
	public String getElementId(Object element){
		return ((ContactPersonItem)element).getId().toString();
	} 

	/**
	 * 行对像指定操作发生时，触发的事件
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(actionName.equals(ID_ACTION_VIEW)){ //详情
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(820, 500);
			ContactBookInfo contactBookInfo = getContext().get(ContactBookInfo.class, GUID.valueOf(rowId));
			PageControllerInstance controllerInstance = new PageControllerInstance("ContactEditPage", contactBookInfo);
			MsgRequest request = new MsgRequest(controllerInstance, "联系人详细信息", style);
			//回应
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
		return "联系人-供应商";
	}
}
