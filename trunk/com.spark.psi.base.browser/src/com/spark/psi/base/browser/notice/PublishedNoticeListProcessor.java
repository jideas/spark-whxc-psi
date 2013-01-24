package com.spark.psi.base.browser.notice;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SSortDirection;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.NoticeStatus;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.base.notice.entity.NoticeItem;
import com.spark.psi.publish.base.notice.key.FindNoticeItemListKey;
import com.spark.psi.publish.base.notice.task.CancelNoticeTask;

/**
 * 
 * <p>已发布公告列表处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-19
 */
public class PublishedNoticeListProcessor extends PSIListPageProcessor<NoticeItem>{
	/**
	 * 控件ID
	 */
	public final static String ID_LABEL_COUNT = "Label_Count";
	public final static String ID_TEXT_SEARCHTEXT = "Text_search";
	//查看公告详情
	public final static String ID_ACTION_VIEW = "view";

	/**
	 * 控件
	 */
	private Label noticeCountLabel;
	private Text searchNoticeText;

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
		//搜索按钮监听器
		searchNoticeButtonListenter();
	}

	/**
	 * 获得已发布公告列表
	 */
	public Object[] getElements(Context context, STableStatus tablestatus){
		FindNoticeItemListKey key = new FindNoticeItemListKey();
		//查询已发布公告
		key.setType(FindNoticeItemListKey.RELEASED);
		//搜索字符串
		if(null != searchNoticeText.getText() && !searchNoticeText.getText().trim().equals("输入搜索内容")){
			key.setSearchText(searchNoticeText.getText().trim());
		}
		LoginInfo loginInfo = context.get(LoginInfo.class);
		//非Boss
		if(!loginInfo.hasAuth(Auth.Boss)){
			//查询属于自已部门的公告，设置公告所属部门GUID
			key.setDeptGuid(loginInfo.getEmployeeInfo().getDepartmentId());
			//查询自已的公告，设置创建人GUID
			key.setCreateGuid(loginInfo.getEmployeeInfo().getId());
		}
		//排序
		key.setSortCloumName(tablestatus.getSortColumn());
		key.setSortType(SSortDirection.ASC == tablestatus.getSortDirection() ? SortType.Asc : SortType.Desc);
		List<NoticeItem> noticeItemList = context.getList(NoticeItem.class, key);
		//设置公告数量
		noticeCountLabel.setText((noticeItemList != null ? noticeItemList.size() : 0) + "");
		return noticeItemList != null ? noticeItemList.toArray() : new Object[0];
	}

	/**
	 * 获取指定对象的ID
	 */
	public String getElementId(Object element){
		return ((NoticeItem)element).getRECID().toString();
	}

	/**
	 * 获取可以对表格数据进行撤消操作
	 */
	@Override
	public String[] getTableActionIds(){
		//员工不存在操作列
		LoginInfo loginInfo = getContext().find(LoginInfo.class);
		boolean isManager =
		        loginInfo.hasAuth(Auth.Boss) || loginInfo.hasAuth(Auth.AccountManager)
		                || loginInfo.hasAuth(Auth.SalesManager) || loginInfo.hasAuth(Auth.PurchaseManager)
		                || loginInfo.hasAuth(Auth.StoreKeeperManager);
		if(isManager){
			return new String[] {Action.Cancel.name()};
		}
		else{
			return null;
		}
	}

	/**
	 * 获取可以对指定行对象进行撤消操作
	 */
	@Override
	protected String[] getElementActionIds(Object element){
		NoticeItem noticeItem = (NoticeItem)element;
		//只有总经理或经理具有撤消权限，总经理可撤消所有，经理只能撤消自已创建的公告
		LoginInfo loginInfo = getContext().find(LoginInfo.class);
		boolean hasRoot =
		        loginInfo.hasAuth(Auth.Boss)
		                || ((loginInfo.hasAuth(Auth.AccountManager) || loginInfo.hasAuth(Auth.SalesManager)
		                        || loginInfo.hasAuth(Auth.PurchaseManager) || loginInfo
		                        .hasAuth(Auth.StoreKeeperManager)) && loginInfo.getEmployeeInfo().getId().equals(
		                        noticeItem.getCreateGuid()));
		if(hasRoot){
			return new String[] {Action.Cancel.name()};
		}
		else{
			return null;
		}
	}

	/**
	 * 行对像指定操作发生时，触发的事件
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue){
		if(actionName.equals(Action.Cancel.name())){//撤消公告
			confirm("确认撤消当前公告？", new Runnable(){

				public void run(){
					CancelNoticeTask cancelNoticeTask = new CancelNoticeTask();
					cancelNoticeTask.setRECID(GUID.valueOf(rowId));
					cancelNoticeTask.setNoticeStatus(NoticeStatus.Cancel.getKey());
					getContext().handle(cancelNoticeTask);
					hint("撤消成功！");
					table.render();
				}
			});
		}
		else if(ID_ACTION_VIEW.equals(actionName)){//查询公告
			WindowStyle style = new WindowStyle(JWT.CLOSE | JWT.MODAL);
			style.setSize(750, 500);
			PageControllerInstance controllerInstance = new PageControllerInstance("noticeInfoPage", rowId);
			MsgRequest request = new MsgRequest(controllerInstance, "公告详情", style);
			//回应
			request.setResponseHandler(new ResponseHandler(){

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					hint("撤消成功！");
					table.render();
				}
			});
			getContext().bubbleMessage(request);
		}
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
