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
 * <p>未发布公告列表处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */
public class PublishingNoticeListProcessor extends PSIListPageProcessor<NoticeItem>{

	/**
	 * 控件ID
	 */
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	public final static String ID_BUTTON_ADDNOTICE = "Button_AddNotice";
	//编辑公告
	public final static String ID_ACTION_EDIT = "edit";

	/**
	 * 控件
	 */
	private Label noticeCountLabel;
	private Text searchNoticeText;
	private Button addNoticeButton;

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation situation){
		super.process(situation);
		//公告数量
		noticeCountLabel = this.createControl(ID_LABEL_COUNT, Label.class, JWT.NONE);
		//搜索文本
		searchNoticeText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class, JWT.NONE);
		//新建公告
		addNoticeButton = this.createControl(ID_BUTTON_ADDNOTICE, Button.class, JWT.NONE);
		//新增公告按钮监听器
		addNoticeButtonListenter();
		//搜索按钮监听器
		searchNoticeButtonListenter();
	}

	/**
	 * 查询未布布公告
	 */
	private List<NoticeItem> findNoticeItemList(Context context, STableStatus tablestatus){
		FindNoticeItemListKey key = new FindNoticeItemListKey();
		//查询未发布公告
		key.setType(FindNoticeItemListKey.NOT_RELEASE);
		//搜索字符串
		if(StringHelper.isNotEmpty(searchNoticeText.getText()) && !searchNoticeText.getText().trim().equals("输入搜索内容")){
			key.setSearchText(searchNoticeText.getText().trim());
		}
		LoginInfo loginInfo = context.get(LoginInfo.class);
		//非Boss只查询自已的公告，设置创建人GUID
		if(!loginInfo.hasAuth(Auth.Boss)){
			key.setCreateGuid(loginInfo.getEmployeeInfo().getId());
		}
		//排序
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<NoticeItem> noticeItemList = context.getList(NoticeItem.class, key);
		return noticeItemList;
	}

	/**
	 * 获得未发布公告列表
	 */
	public Object[] getElements(Context context, STableStatus tablestatus){
		List<NoticeItem> noticeItemList = findNoticeItemList(context, tablestatus);
		//设置公告数量
		noticeCountLabel.setText((null != noticeItemList ? noticeItemList.size() : 0) + "");
		return null != noticeItemList ? noticeItemList.toArray() : null;
	}

	/**
	 * 获取指定对象的ID
	 */
	public String getElementId(Object element){
		return ((NoticeItem)element).getRECID().toString();
	}

	/**
	 * 获取可以对表格数据进行删除操作
	 */
	@Override
	public String[] getTableActionIds(){
		return new String[] {Action.Delete.name()};
	}

	/**
	 * 获取可以对指定行对象进行删除操作
	 */
	@Override
	protected String[] getElementActionIds(Object element){
		return new String[] {Action.Delete.name()};
	}

	/**
	 * 行对像指定操作发生时，触发的事件
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(actionName.equals(Action.Delete.name())){ //删除公告
			confirm("确认删除当前公告？", new Runnable(){

				public void run(){
					DeleteNoticeTask deleteNoticeTask = new DeleteNoticeTask();
					deleteNoticeTask.setRECID(GUID.valueOf(rowId));
					getContext().handle(deleteNoticeTask);
					hint("删除成功！");
					table.render();
				}
			});
		}
		else if(ID_ACTION_EDIT.equals(actionName)){ //编辑公告
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(750, 500);
			FindNoticeInfoKey findNoticeInfoKey = new FindNoticeInfoKey();
			findNoticeInfoKey.setRECID(GUID.valueOf(rowId));
			NoticeInfo noticeInfo = getContext().get(NoticeInfo.class, findNoticeInfoKey);
			PageControllerInstance controllerInstance = new PageControllerInstance("addNoticePage", noticeInfo);
			MsgRequest request = new MsgRequest(controllerInstance, "编辑公告", style);
			//回应
			request.setResponseHandler(new ResponseHandler(){

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					hint("保存成功！在发布日，系统将自动发布该公告。");
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	/**
	 * 新增公告按钮增加事件监听器
	 */
	protected void addNoticeButtonListenter(){
		addNoticeButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
				style.setSize(750, 500);
				PageControllerInstance controllerInstance = new PageControllerInstance("addNoticePage");
				MsgRequest request = new MsgRequest(controllerInstance, "新建公告", style);
				//回应
				request.setResponseHandler(new ResponseHandler(){

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4)
					{
						hint("保存成功！在发布日，系统将自动发布该公告。");
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	/**
	 * 搜索按钮增加事件监听器
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
