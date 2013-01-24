package com.spark.psi.base.browser.notice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.text.HtmlEditor;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.portal.browser.WindowStyle.Location;
import com.spark.psi.publish.base.notice.entity.NoticeInfo;
import com.spark.psi.publish.base.notice.task.SaveOrUpdateNoticeTask;

/**
 * 
 * <p>新建或编辑公告界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-20
 */
public class NewNoticeProcessor extends BaseFormPageProcessor{

	/**
	 * 控件ID
	 */
	public final static String ID_Text_NoticeTile = "Text_NoticeTile";
	public final static String ID_CheckBox_IsTop = "CheckBox_IsTop";
	public final static String ID_Text_DeptNameStr = "Text_DeptNameStr";
	public final static String ID_Date_Publishing = "Date_Publishing";
	public final static String ID_Date_Cancel = "Date_Cancel";
	public final static String ID_Text_Content = "Text_Content";
	public final static String ID_Button_Save = "Button_Save";

	/**
	 * 控件
	 */
	private Text noticeTitleText;
	private Button isTopCheckBox;
	private Text deptNameStrText;
	private DatePicker publishingDate;
	private DatePicker cancelDate;
	private HtmlEditor noticeContentText;
	private Button saveButton;

	//部门GUID字符串，如多个用豆号分隔
	private String deptGuidStr;

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation situation){
		//初始化界面组件
		initUIComponent();
		//新增公告按钮监听器
		addSaveButtonListenter();
		//发布范围按钮侦听器
		deptNameStrTextListener();
		//添加验证器
		addValidator();
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context 上下文
	 */
	public void postProcess(Situation context){
		//编辑时初始化内容
		initNoticeInfo();
	}

	/**
	 * 编辑时初始化内容
	 */
	private void initNoticeInfo(){
		NoticeInfo noticeInfo = (NoticeInfo)this.getArgument();
		if(null != noticeInfo){
			deptGuidStr =
			        noticeInfo.getDeptGuidList() == null ? "" : StringUtils.join(noticeInfo.getDeptGuidList()
			                .iterator(), ",");
			noticeTitleText.setText(noticeInfo.getNoticeTitle());
			isTopCheckBox.setSelection(noticeInfo.getIsTop());
			deptNameStrText.setText(noticeInfo.getDeptNameStr());
			publishingDate.setDate(noticeInfo.getPublishTime() == 0 ? null : new Date(noticeInfo.getPublishTime()));
			cancelDate.setDate(noticeInfo.getCancelTime() == 0 ? null : new Date(noticeInfo.getCancelTime()));
			noticeContentText.setContent(noticeInfo.getNoticeContent());
		}
	}

	/**
	 * 初始化界面组件
	 */
	private void initUIComponent(){
		//公告标题
		noticeTitleText = this.createControl(ID_Text_NoticeTile, Text.class, JWT.NONE);
		//是否置顶
		isTopCheckBox = this.createControl(ID_CheckBox_IsTop, Button.class, JWT.NONE);
		//发布范围
		deptNameStrText = this.createControl(ID_Text_DeptNameStr, Text.class, JWT.NONE);
		//发布日期
		publishingDate = this.createControl(ID_Date_Publishing, DatePicker.class, JWT.NONE);
		publishingDate.setDate(new Date());
		//撤消日期 
		cancelDate = this.createControl(ID_Date_Cancel, DatePicker.class, JWT.NONE);
		//公告内容
		noticeContentText = this.createControl(ID_Text_Content, HtmlEditor.class, JWT.NONE);
		//保存按钮
		saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
	}

	/**
	 * 界面添加验证器
	 */
	private void addValidator(){
		//公告标题不为空
		registerNotEmptyValidator(noticeTitleText, "公告标题");
		//发布范围不为空
		registerNotEmptyValidator(deptNameStrText, "发布范围");
		//发布日期不为空
		registerNotEmptyValidator(publishingDate, "发布日期");
		//发布日期必须大于当前日期
		registerInputValidator(new TextInputValidator(publishingDate, "发布日期必须大于或等于当前日期"){

			@Override
			protected boolean validateText(String text){
				if(null == publishingDate.getDate()){
					return true;
				}
				return DateUtil.getDayStartTime(publishingDate.getDate().getTime()) >= DateUtil
				        .getDayStartTime(new Date().getTime());
			}

		});
		//撤消日期必须大于发布日期
		registerInputValidator(new TextInputValidator(cancelDate, "撤消日期必须大于或等于发布日期"){

			@Override
			protected boolean validateText(String text){
				if(null == publishingDate.getDate() || null == cancelDate.getDate()){
					return true; 
				}
				long curCancelDate = DateUtil.getDayEndTime(cancelDate.getDate().getTime());
				return curCancelDate >= DateUtil.getDayStartTime(publishingDate.getDate().getTime());
			}

		});
	}

	/**
	 * 发布范围按钮侦听器
	 */
	private void deptNameStrTextListener(){
		deptNameStrText.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				PageControllerInstance pageControllerInstance =
				        new PageControllerInstance(new PageController(DepartmentSelectProcessor.class,
				                DepartmentSelectRender.class), deptGuidStr);
				WindowStyle windowStyle = new WindowStyle(JWT.CLOSE, Location.Context);
				windowStyle.setSize(240, 360);
				MsgRequest request = new MsgRequest(pageControllerInstance, "部门选择", windowStyle);
				request.setResponseHandler(new ResponseHandler(){

					public void handle(Object type, Object deptNameStr, Object deptGuidStr, Object returnValue3){
						if(type.equals(DepartmentSelectProcessor.FINISH_SELECTED)){ //完成选择
							NewNoticeProcessor.this.deptNameStrText.setText(getOmitDeptNameStr(deptNameStr.toString()));
							NewNoticeProcessor.this.deptGuidStr = null != deptGuidStr ? deptGuidStr.toString() : "";
						}

					}
				});
				getContext().bubbleMessage(request);
			}
		});
	}

	/**
	 * 如果发布范围长度超过1000，则截掉多出的部分
	 */
	private String getOmitDeptNameStr(String deptNameStr){
		if(StringHelper.isEmpty(deptNameStr)){
			return null;
		}
		if(deptNameStr.length() <= 1000){
			return deptNameStr;
		}
		//长度超过997的部份被截掉，并加上省略号
		String omitDeptNameStr = "";
		String[] deptNameArr = deptNameStr.toString().split(",");
		String prevDeptNameStr = "";
		String newDeptNameStr = "";
		for(String name : deptNameArr){
			prevDeptNameStr = newDeptNameStr;
			newDeptNameStr += "," + name;
			if(newDeptNameStr.length() > 997){
				omitDeptNameStr = prevDeptNameStr.substring(1) + "...";
				break;
			}
		}
		return omitDeptNameStr;
	}

	/**
	 * 保存按钮侦听器
	 */
	private void addSaveButtonListenter(){
		saveButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				//验证数据
				if(!validateInput()){
					return;
				}
				//验证公告内容不能为空
				if(CheckIsNull.isEmpty(noticeContentText.getText())){
					alert("公告内容不能为空！");
					return;
				}
				SaveOrUpdateNoticeTask saveOrUpdateNoticeTask = buildSaveOrUpdateNoticeTask();
				if(null == getArgument()){ //新增
					getContext().handle(saveOrUpdateNoticeTask, SaveOrUpdateNoticeTask.Method.ADD);
				}
				else{//编辑
					getContext().handle(saveOrUpdateNoticeTask, SaveOrUpdateNoticeTask.Method.UPDATE);
				}
				MsgResponse response = new MsgResponse(true);
				getContext().bubbleMessage(response);
			}
		});
	}

	/**
	 * 组装公告对像
	 */
	private SaveOrUpdateNoticeTask buildSaveOrUpdateNoticeTask(){
		SaveOrUpdateNoticeTask saveOrUpdateNoticeTask = new SaveOrUpdateNoticeTask();
		if(null == getArgument()){
			//公告GUID
			saveOrUpdateNoticeTask.setRECID(getContext().newRECID());
			//创建日期
			saveOrUpdateNoticeTask.setCreateDate(new Date().getTime());
		}
		else{
			//公告GUID
			saveOrUpdateNoticeTask.setRECID(((NoticeInfo)this.getArgument()).getRECID());
			//创建日期
			saveOrUpdateNoticeTask.setCreateDate(((NoticeInfo)this.getArgument()).getCreateTime());
		}
		//公告标题
		saveOrUpdateNoticeTask
		        .setNoticeTitle(noticeTitleText.getText() != null ? noticeTitleText.getText().trim() : "");
		//是否置顶
		saveOrUpdateNoticeTask.setIsTop(isTopCheckBox.getSelection());
		//发布范围
		saveOrUpdateNoticeTask
		        .setDeptNameStr(deptNameStrText.getText() != null ? deptNameStrText.getText().trim() : "");
		//发布日期 
		saveOrUpdateNoticeTask
		        .setPublishTime(publishingDate.getDate() != null ? publishingDate.getDate().getTime() : 0);
		//撤消日期
		saveOrUpdateNoticeTask.setCancelTime(cancelDate.getDate() != null ? DateUtil.getDayEndTime(cancelDate.getDate()
		        .getTime()) : 0);
		//公告内容
		saveOrUpdateNoticeTask.setNoticeContent(noticeContentText.getContent() != null ? noticeContentText.getContent()
		        .trim() : "");
		//发布范围
		if(StringHelper.isNotEmpty(deptGuidStr)){
			String[] deptGuids = deptGuidStr.split(",");
			List<GUID> deptGuidList = new ArrayList<GUID>();
			if(null != deptGuids && deptGuids.length > 0){
				for(Object item : deptGuids){
					deptGuidList.add(GUID.valueOf(item.toString()));
				}
			}
			saveOrUpdateNoticeTask.setDeptGuidList(deptGuidList);
		}
		return saveOrUpdateNoticeTask;
	}

}
