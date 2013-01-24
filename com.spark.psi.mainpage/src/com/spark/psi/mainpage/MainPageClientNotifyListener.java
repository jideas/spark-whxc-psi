package com.spark.psi.mainpage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.AsyncListener;
import com.jiuqi.dna.ui.wt.events.ClientNotifyEvent;
import com.jiuqi.dna.ui.wt.events.ClientNotifyListener;
import com.spark.psi.base.browser.start.ConfigurationWizardWindow;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.SMessageType;
import com.spark.psi.publish.base.function.UpdateFunctionPositionTask;
import com.spark.psi.publish.base.index.task.IndexToolTask;
import com.spark.psi.publish.base.index.task.NoteTask;
import com.spark.psi.publish.base.organization.task.UpdateEmployeeStyleTask;
import com.spark.psi.publish.smessage.task.SMessageBubbledTask;
import com.spark.psi.publish.smessage.task.SMessageMonitorTask;

public class MainPageClientNotifyListener implements ClientNotifyListener,
		AsyncListener {
	
	private MainPage mainPage = null;
	private Context context = null;

	public MainPageClientNotifyListener(MainPage mainPage,Context context) {
		this.mainPage = mainPage;
		this.context = context;
	}
	
	public void notified(ClientNotifyEvent e) {
		
		LoginInfo loginInfo = context.find(LoginInfo.class);
		// ��������ͼ�������ͼ������ƶ���ʱ��,�����json
		JSONObject changeImgstatusjsonObject = mainPage
				.getCustomObject("changeImgstatus");
		// �򿪶��������ʱ��,�������������������ƶ���ͼ�������
		JSONObject openWindowsjsonObject = mainPage
				.getCustomObject("openWindowsjsonObject");
		
		
		// ���ּ�
		JSONObject noteJsonObject = mainPage
				.getCustomObject("note");
		
		// �������
		JSONObject monitorJsonObject = mainPage
				.getCustomObject("monitor");

		// ����������ݴ����ݿ�
		JSONObject monitorUpdateobject = mainPage
				.getCustomObject("monitorUpdate");
		//�޸ĵ�ǰ��ϢΪ������״̬
		JSONObject messageobject = mainPage
		.getCustomObject("message");
		//С���ݵ���Ϣ
		JSONObject bubbleMessage = mainPage.getCustomObject("getbubbleMessage");
		// ��ʽ
		JSONObject themeJosnObject = mainPage
		.getCustomObject("theme");
		//�Ƿ��Ѿ���������Ŀ
		JSONObject isStartJsonObject = mainPage.getCustomObject("isStart");
		//�򿪹ر�С����
		JSONObject indexToolJson = mainPage.getCustomObject("indexTool");
		
		if(indexToolJson != null) {
			try {
				String name = indexToolJson.getString("name");
				String opertion = indexToolJson.getString("opertion");
				IndexToolTask indexToolTask = new IndexToolTask(context.newRECID(), name, loginInfo.getEmployeeInfo().getId());
				if(opertion != null && opertion.equals("add")) {
					context.handle(indexToolTask, IndexToolTask.Method.ADD);
				}else if(opertion != null && opertion.equals("del")) {
					context.handle(indexToolTask, IndexToolTask.Method.DEL);
				}
				
			} catch (JSONException e1) {
				e1.printStackTrace();
			} finally {
				mainPage.setCustomObject("indexTool",null);
			}
		}else if(isStartJsonObject != null ) {
			try {
				if(!isStartJsonObject.getBoolean("start")) {
					new ConfigurationWizardWindow();
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			} finally {
				mainPage.setCustomObject("isStart",null);
			}
		}else if (changeImgstatusjsonObject != null) {
			updateImgstatus(loginInfo, changeImgstatusjsonObject);
			mainPage.setCustomObject("changeImgstatus", null);
		} else if (openWindowsjsonObject != null) {
			try {
				JSONArray jsonArray = openWindowsjsonObject
						.getJSONArray("toServerArray");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					jsonObject.put("status", "moveOut");
					updateImgstatus(loginInfo, jsonObject);
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			} finally {
				mainPage.setCustomObject("openWindowsjsonObject",
						null);
			}

		} else if (noteJsonObject != null) {
			try {
				if (noteJsonObject.getString("oper").equals("set")) {
					NoteTask task = new NoteTask(loginInfo
							.getEmployeeInfo().getId(), noteJsonObject
							.getString("text"));
					context.handle(task, NoteTask.Method.MODIFY);
				} else if (noteJsonObject.getString("oper").equals(
						"get")) {
					mainPage.setCustomObject("note", null);
					JSONObject jsonObject = mainPage.getNote(loginInfo);
					mainPage.setCustomObject("noteText",
							jsonObject);
					mainPage.notifyClientAction();
				}

			} catch (JSONException e1) {
				e1.printStackTrace();
			} finally {
				mainPage.setCustomObject("note", null);
			}
		} else if (monitorJsonObject != null) {
			mainPage.setCustomObject("monitor", null);
			mainPage.addMonitorOption();
			mainPage.notifyClientAction();
			// ��������װ��json���͵��ͻ���
		} else if (monitorUpdateobject != null) {
			// ���������Ƿ���ʾ
			try {
				SMessageMonitorTask messageMonitorTask = new SMessageMonitorTask(SMessageType.getType(monitorUpdateobject.getString("id")),monitorUpdateobject.getBoolean("isshow"));
				context.handle(messageMonitorTask);
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				mainPage.setCustomObject("monitorUpdate", null);
			}
		} else if(bubbleMessage != null) {
			mainPage.setCustomObject("getbubbleMessage", null);
			if(mainPage.addBubbleMessage() > 0) {
				mainPage.addMonitorOption();
				mainPage.notifyClientAction();
			}
		} else if(messageobject != null) {
			try {
				String id = messageobject.getString("id");
				if(id != null && id.length() > 0) {
					SMessageBubbledTask smessageTask = new SMessageBubbledTask();
					smessageTask.setId(GUID.valueOf(id));
					context.handle(smessageTask);
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			mainPage.setCustomObject("message", null);
		} else if (themeJosnObject != null) {
			UpdateEmployeeStyleTask styleTask;
			try {
				styleTask = new UpdateEmployeeStyleTask(loginInfo
						.getEmployeeInfo().getId(), themeJosnObject
						.getString("style"));
				context.handle(styleTask);
			} catch (JSONException e1) {
				//e1.printStackTrace();
			} finally {
				mainPage.setCustomObject("theme", null);
			}

		}
	}

	
	
	private void updateImgstatus(LoginInfo loginInfo,
			JSONObject jsonObject) {
		UpdateFunctionPositionTask updateFunctionPositionTask;
		try {
			updateFunctionPositionTask = new UpdateFunctionPositionTask(
					loginInfo.getEmployeeInfo().getId(), jsonObject
							.getString("code"));
			String status = jsonObject.getString("status");
			if (status.equals("moveOut")) {
				updateFunctionPositionTask.setPutMain(true);
				updateFunctionPositionTask.setXindex(jsonObject
						.getInt("x"));
				updateFunctionPositionTask.setYindex(jsonObject
						.getInt("y"));
			} else if (status.equals("moveIn")) {
				updateFunctionPositionTask.setPutMain(false);
			}
			context.handle(updateFunctionPositionTask);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
	}

}
