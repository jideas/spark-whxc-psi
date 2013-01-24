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
 * <p>�½���༭������洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-20
 */
public class NewNoticeProcessor extends BaseFormPageProcessor{

	/**
	 * �ؼ�ID
	 */
	public final static String ID_Text_NoticeTile = "Text_NoticeTile";
	public final static String ID_CheckBox_IsTop = "CheckBox_IsTop";
	public final static String ID_Text_DeptNameStr = "Text_DeptNameStr";
	public final static String ID_Date_Publishing = "Date_Publishing";
	public final static String ID_Date_Cancel = "Date_Cancel";
	public final static String ID_Text_Content = "Text_Content";
	public final static String ID_Button_Save = "Button_Save";

	/**
	 * �ؼ�
	 */
	private Text noticeTitleText;
	private Button isTopCheckBox;
	private Text deptNameStrText;
	private DatePicker publishingDate;
	private DatePicker cancelDate;
	private HtmlEditor noticeContentText;
	private Button saveButton;

	//����GUID�ַ����������ö��ŷָ�
	private String deptGuidStr;

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation situation){
		//��ʼ���������
		initUIComponent();
		//�������水ť������
		addSaveButtonListenter();
		//������Χ��ť������
		deptNameStrTextListener();
		//�����֤��
		addValidator();
	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context ������
	 */
	public void postProcess(Situation context){
		//�༭ʱ��ʼ������
		initNoticeInfo();
	}

	/**
	 * �༭ʱ��ʼ������
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
	 * ��ʼ���������
	 */
	private void initUIComponent(){
		//�������
		noticeTitleText = this.createControl(ID_Text_NoticeTile, Text.class, JWT.NONE);
		//�Ƿ��ö�
		isTopCheckBox = this.createControl(ID_CheckBox_IsTop, Button.class, JWT.NONE);
		//������Χ
		deptNameStrText = this.createControl(ID_Text_DeptNameStr, Text.class, JWT.NONE);
		//��������
		publishingDate = this.createControl(ID_Date_Publishing, DatePicker.class, JWT.NONE);
		publishingDate.setDate(new Date());
		//�������� 
		cancelDate = this.createControl(ID_Date_Cancel, DatePicker.class, JWT.NONE);
		//��������
		noticeContentText = this.createControl(ID_Text_Content, HtmlEditor.class, JWT.NONE);
		//���水ť
		saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
	}

	/**
	 * ���������֤��
	 */
	private void addValidator(){
		//������ⲻΪ��
		registerNotEmptyValidator(noticeTitleText, "�������");
		//������Χ��Ϊ��
		registerNotEmptyValidator(deptNameStrText, "������Χ");
		//�������ڲ�Ϊ��
		registerNotEmptyValidator(publishingDate, "��������");
		//�������ڱ�����ڵ�ǰ����
		registerInputValidator(new TextInputValidator(publishingDate, "�������ڱ�����ڻ���ڵ�ǰ����"){

			@Override
			protected boolean validateText(String text){
				if(null == publishingDate.getDate()){
					return true;
				}
				return DateUtil.getDayStartTime(publishingDate.getDate().getTime()) >= DateUtil
				        .getDayStartTime(new Date().getTime());
			}

		});
		//�������ڱ�����ڷ�������
		registerInputValidator(new TextInputValidator(cancelDate, "�������ڱ�����ڻ���ڷ�������"){

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
	 * ������Χ��ť������
	 */
	private void deptNameStrTextListener(){
		deptNameStrText.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				PageControllerInstance pageControllerInstance =
				        new PageControllerInstance(new PageController(DepartmentSelectProcessor.class,
				                DepartmentSelectRender.class), deptGuidStr);
				WindowStyle windowStyle = new WindowStyle(JWT.CLOSE, Location.Context);
				windowStyle.setSize(240, 360);
				MsgRequest request = new MsgRequest(pageControllerInstance, "����ѡ��", windowStyle);
				request.setResponseHandler(new ResponseHandler(){

					public void handle(Object type, Object deptNameStr, Object deptGuidStr, Object returnValue3){
						if(type.equals(DepartmentSelectProcessor.FINISH_SELECTED)){ //���ѡ��
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
	 * ���������Χ���ȳ���1000����ص�����Ĳ���
	 */
	private String getOmitDeptNameStr(String deptNameStr){
		if(StringHelper.isEmpty(deptNameStr)){
			return null;
		}
		if(deptNameStr.length() <= 1000){
			return deptNameStr;
		}
		//���ȳ���997�Ĳ��ݱ��ص���������ʡ�Ժ�
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
	 * ���水ť������
	 */
	private void addSaveButtonListenter(){
		saveButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				//��֤����
				if(!validateInput()){
					return;
				}
				//��֤�������ݲ���Ϊ��
				if(CheckIsNull.isEmpty(noticeContentText.getText())){
					alert("�������ݲ���Ϊ�գ�");
					return;
				}
				SaveOrUpdateNoticeTask saveOrUpdateNoticeTask = buildSaveOrUpdateNoticeTask();
				if(null == getArgument()){ //����
					getContext().handle(saveOrUpdateNoticeTask, SaveOrUpdateNoticeTask.Method.ADD);
				}
				else{//�༭
					getContext().handle(saveOrUpdateNoticeTask, SaveOrUpdateNoticeTask.Method.UPDATE);
				}
				MsgResponse response = new MsgResponse(true);
				getContext().bubbleMessage(response);
			}
		});
	}

	/**
	 * ��װ�������
	 */
	private SaveOrUpdateNoticeTask buildSaveOrUpdateNoticeTask(){
		SaveOrUpdateNoticeTask saveOrUpdateNoticeTask = new SaveOrUpdateNoticeTask();
		if(null == getArgument()){
			//����GUID
			saveOrUpdateNoticeTask.setRECID(getContext().newRECID());
			//��������
			saveOrUpdateNoticeTask.setCreateDate(new Date().getTime());
		}
		else{
			//����GUID
			saveOrUpdateNoticeTask.setRECID(((NoticeInfo)this.getArgument()).getRECID());
			//��������
			saveOrUpdateNoticeTask.setCreateDate(((NoticeInfo)this.getArgument()).getCreateTime());
		}
		//�������
		saveOrUpdateNoticeTask
		        .setNoticeTitle(noticeTitleText.getText() != null ? noticeTitleText.getText().trim() : "");
		//�Ƿ��ö�
		saveOrUpdateNoticeTask.setIsTop(isTopCheckBox.getSelection());
		//������Χ
		saveOrUpdateNoticeTask
		        .setDeptNameStr(deptNameStrText.getText() != null ? deptNameStrText.getText().trim() : "");
		//�������� 
		saveOrUpdateNoticeTask
		        .setPublishTime(publishingDate.getDate() != null ? publishingDate.getDate().getTime() : 0);
		//��������
		saveOrUpdateNoticeTask.setCancelTime(cancelDate.getDate() != null ? DateUtil.getDayEndTime(cancelDate.getDate()
		        .getTime()) : 0);
		//��������
		saveOrUpdateNoticeTask.setNoticeContent(noticeContentText.getContent() != null ? noticeContentText.getContent()
		        .trim() : "");
		//������Χ
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
