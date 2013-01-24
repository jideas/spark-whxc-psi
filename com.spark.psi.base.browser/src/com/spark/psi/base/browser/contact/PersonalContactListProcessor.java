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
 * 通迅录(个人)列表处理器
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 20012 - 20018<br>
 * 
 * 
 * @author 刘青
 * @version 2012-5-4
 */
public class PersonalContactListProcessor extends ContactBaseListProcessor<ContactPersonItem> {
	/**
	 * 控件ID
	 */
	public final static String ID_BUTTON_ADDCONTACT = "Button_AddContact";
	public final static String ID_BUTTON_DELCONTACT = "Button_DelContact";
	// 查看详情
	public final static String ID_ACTION_VIEW = "view";

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation situation) {
		super.process(situation);
		// 新增联系人
		Button addButton = this.createControl(ID_BUTTON_ADDCONTACT, Button.class, JWT.NONE);
		addButton.addActionListener(new AddButtonListener());
		// 删除联系人
		Button delButton = this.createControl(ID_BUTTON_DELCONTACT, Button.class, JWT.NONE);
		delButton.addActionListener(new DeleteButtonListener());
	}

	/**
	 * 获得通讯录(个人)列表
	 */
	public Object[] getElements(Context context, STableStatus tablestatus) {
		FindPersonalContactListKey key = new FindPersonalContactListKey();
		// 拼音过滤
		key.setPhonetics(phoneticNavigatorBar.getText());
		// 搜索字符串
		if (null != searchNoticeText.getText() && !searchNoticeText.getText().trim().equals("输入搜索内容")) {
			key.setSearchText(searchNoticeText.getText().trim());
		}
		// 排序
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<ContactPersonItem> contactPersonItemList = context.getList(ContactPersonItem.class, key);
		return contactPersonItemList != null ? contactPersonItemList.toArray() : new Object[0];
	}

	/**
	 * 获取指定对象的ID
	 */
	public String getElementId(Object element) {
		return ((ContactPersonItem) element).getId().toString();
	}

	/**
	 * 获取可以对表格数据进行删除操作
	 */
	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.Delete.name() };
	}

	/**
	 * 获取可以对指定行对象进行删除操作
	 */
	@Override
	protected String[] getElementActionIds(Object element) {
		return new String[] { Action.Delete.name() };
	}

	/**
	 * 行对像指定操作发生时，触发的事件
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (actionName.equals(ID_ACTION_VIEW)) { // 详情
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(820, 500);
			ContactBookInfo contactBookInfo = getContext().get(ContactBookInfo.class, GUID.valueOf(rowId));
			PageControllerInstance controllerInstance = new PageControllerInstance("ContactEditPage", contactBookInfo);
			MsgRequest request = new MsgRequest(controllerInstance, "联系人详细信息", style);
			// 回应
			request.setResponseHandler(new ResponseHandler() {

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		} else if (actionName.equals(Action.Delete.name())) { // 删除
			confirm("确认删除当前联系人？", new Runnable() {

				public void run() {
					getContext().handle(new DeleteContactInfoTask(GUID.valueOf(rowId)));
					hint("删除成功！");
					table.render();
				}
			});
		}  
	}

	/**
	 * 新增联系人按钮侦听器
	 */
	private class AddButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(820, 500);
			PageControllerInstance controllerInstance = new PageControllerInstance("ContactEditPage");
			MsgRequest request = new MsgRequest(controllerInstance, "新增联系人", style);
			// 回应
			request.setResponseHandler(new ResponseHandler() {

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					table.render();
				}
			});
			getContext().bubbleMessage(request);

		}

	}

	/**
	 * 删除联系人按钮侦听器
	 */
	private class DeleteButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (!isSelectedPerson()) {
				return;
			}
			confirm("确认删除选择的联系人？", new Runnable() {

				public void run() {
					for (String rowId : table.getSelections()) {
						getContext().handle(new DeleteContactInfoTask(GUID.valueOf(rowId)));
						table.removeRow(rowId);
					}
					hint("删除成功！");
					table.render();
				}
			});

		}

	}

	@Override
	protected String getExportFileTitle() {
		return "联系人-个人";
	}
}
