package com.spark.psi.base.phoneMessage.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.common.components.SendMessageType;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.dnasql.InsertSqlBuilder;
import com.spark.common.utils.encrypt.EncryptionUtil;
import com.spark.psi.base.phoneMessage.entity.ReturnMessage;
import com.spark.psi.publish.phonemessage.entity.MsgAccountBalance;
import com.spark.psi.publish.phonemessage.entity.PhoneMessageConfig;
import com.spark.psi.publish.phonemessage.key.GetMessageBalanceKey;
import com.spark.psi.publish.phonemessage.task.PhoneMessageLogTask;
import com.spark.psi.publish.phonemessage.task.PhoneMessageSendTask;
import com.spark.psi.publish.phonemessage.task.PhoneMessageSendTask.ReturnFlag;

public class PhoneMessageService extends Service {

	protected PhoneMessageService() {
		super("com.spark.psi.base.phoneMessage.service.PhoneMessageService");
	}

	@SuppressWarnings("deprecation")
	public boolean cannotSendMsg() {
		Date date = new Date();
		if (date.getHours() >= 0 && date.getHours() < 24) {
			return false;
		}
		return true;
	}

	/**
	 * 查看余额
	 * 
	 * @author Jideas
	 * 
	 */
	@Publish
	protected class MsgAccountBalanceProvider extends
			OneKeyResultProvider<MsgAccountBalance, GetMessageBalanceKey> {

		@Override
		protected MsgAccountBalance provide(Context context,
				GetMessageBalanceKey key) throws Throwable {
			if (cannotSendMsg()) {
				return new MsgAccountBalance("该时间段内禁止操作！");
			}
			String parameter = "";
			if (CheckIsNull.isNotEmpty(key.getCompAccountKey())) {
				parameter = key.getCompAccountKey()
						+ "="
						+ key.getCompAccount()
						+ "&"
						+ key.getUserNameKey()
						+ "="
						+ key.getUserName()
						+ "&"
						+ key.getPasswordKey()
						+ "="
						+ EncryptionUtil.encryptMD5(key.getPassword())
								.toLowerCase();
			} else {
				parameter = key.getUserNameKey() + "=" + key.getUserName()
						+ "&" + key.getPasswordKey() + "="
						+ EncryptionUtil.encryptMD5(key.getPassword());
			}
			StringBuilder sb = new StringBuilder();
			URL urls = new URL(key.getBalanceUrl());
			HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			uc.setRequestMethod("POST");
			uc.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 设置超时时间
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			uc.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(uc
					.getOutputStream());
			writer.write(parameter);
			writer.flush();
			writer.close();
			BufferedReader read;
			read = new BufferedReader(
					new InputStreamReader(uc.getInputStream()));
			String str;
			while ((str = read.readLine()) != null) {
				sb.append(str.trim());
			}
			read.close();// 关闭读取流

			// PhoneMessageSendTask pt = new PhoneMessageSendTask();
			// pt.setPhoneNo("18696532714");
			// pt.setMessage("发送短信时间：" + new Date().toLocaleString());
			// pt.setSendType(SendMessageType.UserActive);
			// context.handle(pt);

			if (CheckIsNull.isNotEmpty(key.getCompAccountKey())) {
				if (!sb.toString().startsWith("1")) {
					return new MsgAccountBalance("测试失败，请检查填写数据！");
				}
				return new MsgAccountBalance("该账号剩余短信条数："
						+ sb.toString().substring(
								sb.toString().indexOf("#") + 1) + "条。");
			} else {
				ReturnMessage msg = XmlParseUtil.getInstance().parseXml(
						sb.toString());
				if (msg.getResult().equals("0")) {
					return new MsgAccountBalance(msg.getDescription());
				}
			}
			return null;
		}
	}

	/**
	 * 发送短信
	 * 
	 * @author Jideas
	 */
	@Publish
	protected class PhoneMessageSendHandle extends
			SimpleTaskMethodHandler<PhoneMessageSendTask> {

		private boolean cannotGoOn(PhoneMessageSendTask task) {
			if (cannotSendMsg()) {
				task.setFlag(ReturnFlag.TimeOut);
				return true;
			}
			if (CheckIsNull.isEmpty(task.getPhoneNo())) {
				task.setFlag(ReturnFlag.NullPhoneNumber);
				return true;
			}
			if (CheckIsNull.isEmpty(task.getMessage())) {
				task.setFlag(ReturnFlag.NullMessage);
				return true;
			}
			if (task.getMessage().length() > 60) {
				task.setFlag(ReturnFlag.MessageTooLong);
				return true;
			}
			return false;
		}

		@Override
		protected void handle(Context context, PhoneMessageSendTask task)
				throws Throwable {
			System.out.println("连接短信服务器。。。。。。");
			PhoneMessageConfig config = context.find(PhoneMessageConfig.class);
			if (!config.isActiving()) {
				task.setFlag(ReturnFlag.NotActiving);
				return;
			}
			if (cannotGoOn(task)) {
				return;
			}
			StringBuilder ss = new StringBuilder();
			if (CheckIsNull.isNotEmpty(config.getCompAccountKey())) {
				ss.append(config.getCompAccountKey() + "="
						+ config.getCompAccount());
				ss.append("&" + config.getUserNameKey() + "="
						+ config.getUserName());
				ss.append("&"
						+ config.getPasswordKey()
						+ "="
						+ EncryptionUtil.encryptMD5(config.getPassword())
								.toLowerCase());
				ss.append("&" + config.getPhoneNumberKey() + "="
						+ task.getPhoneNo());
				ss.append("&"
						+ config.getMsgContentKey()
						+ "="
						+ URLEncoder(task.getMessage().replace(" ", "")
								+ "【7号生活馆】", "UTF-8"));// 【7号生活馆】杰驰
			} else {
				ss.append(config.getUserNameKey() + "=" + config.getUserName());
				ss.append("&" + config.getPasswordKey() + "="
						+ EncryptionUtil.encryptMD5(config.getPassword()));
				ss.append("&" + config.getPhoneNumberKey() + "="
						+ task.getPhoneNo());
				ss.append("&" + config.getMsgContentKey() + "="
						+ task.getMessage().replace(" ", "") + "【7号生活馆】");
			}
			System.out.println("传送参数：" + ss.toString());
			URL urls = new URL(config.getSubmitUrl());
			HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			uc.setRequestMethod("POST");
			uc.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 设置超时时间
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			uc.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(uc
					.getOutputStream());
			writer.write(ss.toString());
			writer.flush();
			writer.close();
			BufferedReader read;
			read = new BufferedReader(
					new InputStreamReader(uc.getInputStream()));
			String str;
			StringBuilder sb = new StringBuilder();
			while ((str = read.readLine()) != null) {
				sb.append(str.trim());
			}
			read.close();// 关闭读取流
			System.out.println("返回结果：" + sb.toString());
			if (CheckIsNull.isEmpty(config.getCompAccountKey())) {
				ReturnMessage msg = XmlParseUtil.getInstance().parseXml(
						sb.toString());
				task.setFlag(ReturnFlag.getFlag(msg.getResult()));
			} else {
				task.setFlag(ReturnFlag.Failure);
				if (sb.toString().startsWith("1")) {
					task.setFlag(ReturnFlag.Success);
					context.asyncHandle(new PhoneMessageLogTask(task
							.getPhoneNo(), task.getSendType()));
				}
			}
			if (task.getFlag() == ReturnFlag.Success) {
				context.asyncHandle(new PhoneMessageLogTask(task.getPhoneNo(),
						task.getSendType()));
			}
		}
	}

	/**
	 * 记录短信日志
	 * 
	 * @author Jideas
	 */
	@Publish
	protected class WrightMessageLogHandle extends
			SimpleTaskMethodHandler<PhoneMessageLogTask> {
		@Override
		protected void handle(Context context, PhoneMessageLogTask task)
				throws Throwable {
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable(ERPTableNames.Base.SendMessageLog.getTableName());
			ib.addColumn("recid", ib.guid, context.newRECID());
			ib.addColumn("phoneNo", ib.STRING, task.getPhoneNo());
			ib.addColumn("sendType", ib.STRING, task.getSendType().getCode());
			ib.addColumn("remoteAddress", ib.STRING, context.getLogin()
					.getRemoteInfo().getRemoteAddr());
			ib.addColumn("remoteHost", ib.STRING, context.getLogin()
					.getRemoteInfo().getRemoteHost());
			ib.addColumn("createDate", ib.LONG, getDateNo(new Date()));
			ib.execute();
		}
	}

	/**
	 * 获得日编号
	 * 
	 * @param date
	 * @return
	 */
	private int getDateNo(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String s = sdf.format(date);
		return Integer.valueOf(s);
	}

	/**
	 * 
	 * @param str
	 *            内容
	 * @param type
	 *            编码格式 utf-8 ISO8859_1，gbk,gb2312
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws UnsupportedEncodingException
	 */
	public static String URLEncoder(String str, String type)
			throws UnsupportedEncodingException {
		if (str == null) {
			return str;
		}
		str = java.net.URLEncoder.encode(str, type);

		return str;
	}
}
