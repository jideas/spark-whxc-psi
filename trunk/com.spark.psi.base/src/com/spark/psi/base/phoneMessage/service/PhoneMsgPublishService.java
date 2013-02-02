package com.spark.psi.base.phoneMessage.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.db.ERPTableNames;
import com.spark.common.utils.dnasql.InsertSqlBuilder;
import com.spark.common.utils.dnasql.QuerySqlBuilder;
import com.spark.common.utils.dnasql.UpdateSqlBuilder;
import com.spark.common.utils.encrypt.EncryptionUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.base.phoneMessage.entity.PhoneMsgConfigImpl;
import com.spark.psi.publish.phonemessage.entity.PhoneMessageConfig;
import com.spark.psi.publish.phonemessage.entity.PhoneMsgActiveConfig;
import com.spark.psi.publish.phonemessage.task.UpdatePhoneMsgConfigTask;

public class PhoneMsgPublishService extends Service {

	protected PhoneMsgPublishService() {
		super("com.spark.psi.base.phoneMessage.service.PhoneMsgPublishService");
	}

	/**
	 * 单例
	 */
	private static PhoneMessageConfig config;
	private static String realSecretKey = "spark4";

	@Override
	protected void init(Context context) throws Throwable {
		config = context.find(PhoneMessageConfig.class, true);
		if (null == config) {
			UpdatePhoneMsgConfigTask task = new UpdatePhoneMsgConfigTask();
			task.setActiving(false);
			task.setBalanceUrl("http://oa-sms.com/balance.action");
			task.setSubmitUrl("http://oa-sms.com/sendSms.action");
			task.setCompAccountKey("corpAccount");
			task.setCompAccount("qhshg");
			task.setUserNameKey("userAccount");
			task.setUserName("admin");
			task.setPasswordKey("pwd");
			task.setPassword("123456");
			task.setPhoneNumberKey("mobile");
			task.setMsgContentKey("content");
			task.setSecretkey(realSecretKey);
			// task.setBalanceUrl("http://api.100866.net:8966/balance");
			// task.setSubmitUrl("http://api.100866.net:8966/submit");
			// task.setUserNameKey("cpCode");
			// task.setUserName("wuhanxinchen2012");
			// task.setPasswordKey("cpPassword");
			// task.setPassword("123456");
			// task.setPhoneNumberKey("userNumber");
			// task.setMsgContentKey("msgContent");
			// task.setSecretkey(realSecretKey);
			context.handle(task);
		}
	}

	/**
	 * 新增或修改配置信息
	 * 
	 * @author Jideas
	 * 
	 */
	@Publish
	protected class UpdatePhoneMsgConfigHandler extends
			SimpleTaskMethodHandler<UpdatePhoneMsgConfigTask> {

		@Override
		protected void handle(Context context, UpdatePhoneMsgConfigTask task)
				throws Throwable {
			GUID userId = context.find(Login.class).getEmployeeId();
			String modfiyPerson = null;
			if (null != userId) {
				Employee user = context.find(Employee.class, userId);
				modfiyPerson = user.getName();
			} else {
				modfiyPerson = "系统初始化";
			}
			if (null == task.getId()) {
				InsertSqlBuilder ub = new InsertSqlBuilder(context);
				ub.setTable(ERPTableNames.Base.PhoneMsgConfig.getTableName());
				ub.addColumn("RECID", ub.guid, GUID.emptyID);
				ub.addColumn("activing", ub.BOOLEAN, task.isActiving());
				ub.addColumn("balanceUrl", ub.STRING, task.getBalanceUrl());
				ub.addColumn("submitUrl", ub.STRING, task.getSubmitUrl());
				ub.addColumn("userNameKey", ub.STRING, task.getUserNameKey());
				ub.addColumn("passwordKey", ub.STRING, task.getPasswordKey());
				ub.addColumn("companyAccountKey", ub.STRING, task
						.getCompAccountKey());
				ub
						.addColumn("companyAccount", ub.STRING, task
								.getCompAccount());
				ub.addColumn("phoneNumberKey", ub.STRING, task
						.getPhoneNumberKey());
				ub.addColumn("msgContentKey", ub.STRING, task
						.getMsgContentKey());
				ub.addColumn("userName", ub.STRING, EncryptionUtil.encryptAES(
						task.getUserName(), task.getSecretkey()));
				ub.addColumn("password", ub.STRING, EncryptionUtil.encryptAES(
						EncryptionUtil.encryptAES(task.getPassword(), task
								.getSecretkey()), realSecretKey));
				ub.addColumn("secretKey", ub.STRING, EncryptionUtil.encryptAES(
						task.getSecretkey(), realSecretKey));
				ub.addColumn("modfiyPerson", ub.STRING, modfiyPerson);
				ub.addColumn("modfiyDate", ub.DATE, System.currentTimeMillis());
				ub.execute();
				PhoneMsgPublishService.this.init(context);
				return;
			}
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable(ERPTableNames.Base.PhoneMsgConfig.getTableName());
			// ub.addColumn("RECID", ub.guid, task.getId());
			ub.addColumn("activing", ub.BOOLEAN, task.isActiving());
			ub.addColumn("balanceUrl", ub.STRING, task.getBalanceUrl());
			ub.addColumn("submitUrl", ub.STRING, task.getSubmitUrl());
			ub.addColumn("userNameKey", ub.STRING, task.getUserNameKey());
			ub.addColumn("passwordKey", ub.STRING, task.getPasswordKey());
			ub.addColumn("companyAccountKey", ub.STRING, task
					.getCompAccountKey());
			ub.addColumn("companyAccount", ub.STRING, task.getCompAccount());
			ub.addColumn("phoneNumberKey", ub.STRING, task.getPhoneNumberKey());
			ub.addColumn("msgContentKey", ub.STRING, task.getMsgContentKey());
			ub.addColumn("userName", ub.STRING, EncryptionUtil.encryptAES(task
					.getUserName(), task.getSecretkey()));
			ub.addColumn("password", ub.STRING, EncryptionUtil.encryptAES(
					EncryptionUtil.encryptAES(task.getPassword(), task
							.getSecretkey()), realSecretKey));
			ub.addColumn("secretKey", ub.STRING, EncryptionUtil.encryptAES(task
					.getSecretkey(), realSecretKey));
			ub.addColumn("modfiyPerson", ub.STRING, modfiyPerson);
			ub.addColumn("modfiyDate", ub.DATE, System.currentTimeMillis());
			ub.addCondition("id", ub.guid, task.getId(), "t.RECID=@id");
			ub.execute();
			PhoneMsgPublishService.this.init(context);
		}
	}

	/**
	 * 查询配置信息维护对象
	 * 
	 * @author Jideas
	 * 
	 */
	@Publish
	protected class LoadPhoneMsgConfigProvider extends
			ResultProvider<PhoneMessageConfig> {

		@Override
		protected PhoneMessageConfig provide(Context context) throws Throwable {
			if (config == null) {
				PhoneMsgPublishService.this.init(context);
			}
			return config;
		}
	}

	/**
	 * 查询配置信息维护对象
	 * 
	 * @author Jideas
	 * 
	 */
	@Publish
	protected class LoadConfigProvider extends
			OneKeyResultProvider<PhoneMessageConfig, Boolean> {
		@Override
		protected PhoneMessageConfig provide(Context context, Boolean key)
				throws Throwable {
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addTable(ERPTableNames.Base.PhoneMsgConfig.getTableName(), "t");
			qb.addColumn("t.RECID", "RECID");
			qb.addColumn("t.activing", "activing");
			qb.addColumn("t.balanceUrl", "balanceUrl");
			qb.addColumn("t.submitUrl", "submitUrl");
			qb.addColumn("t.companyAccountKey", "companyAccountKey");
			qb.addColumn("t.companyAccount", "companyAccount");
			qb.addColumn("t.userNameKey", "userNameKey");
			qb.addColumn("t.passwordKey", "passwordKey");
			qb.addColumn("t.phoneNumberKey", "phoneNumberKey");
			qb.addColumn("t.msgContentKey", "msgContentKey");
			qb.addColumn("t.secretKey", "secretKey");
			qb.addColumn("t.userName", "userName");
			qb.addColumn("t.password", "password");
			qb.addArgs("id", qb.guid, GUID.emptyID);
			qb.addEquals("t.RECID", "@id");
			RecordSet rs = qb.getRecord();
			while (rs.next()) {
				PhoneMsgConfigImpl impl = new PhoneMsgConfigImpl();
				int index = 0;
				impl.setId(rs.getFields().get(index++).getGUID());
				impl.setActiving(rs.getFields().get(index++).getBoolean());
				impl.setBalanceUrl(rs.getFields().get(index++).getString());
				impl.setSubmitUrl(rs.getFields().get(index++).getString());
				impl.setCompAccountKey(rs.getFields().get(index++).getString());
				impl.setCompAccount(rs.getFields().get(index++).getString());
				impl.setUserNameKey(rs.getFields().get(index++).getString());
				impl.setPasswordKey(rs.getFields().get(index++).getString());
				impl.setPhoneNumberKey(rs.getFields().get(index++).getString());
				impl.setMsgContentKey(rs.getFields().get(index++).getString());
				String secretKey = rs.getFields().get(index++).getString();
				String username = rs.getFields().get(index++).getString();
				String password = rs.getFields().get(index++).getString();

				impl.setSecretkey(EncryptionUtil.decryptAES(secretKey,
						realSecretKey));

				impl.setUserName(EncryptionUtil.decryptAES(username, impl
						.getSecretkey()));
				password = EncryptionUtil.decryptAES(password, realSecretKey);
				impl.setPassword(EncryptionUtil.decryptAES(password, impl
						.getSecretkey()));
				return impl;
			}
			return null;
		}
	}

	/**
	 * 查询是否开启短信发送功能
	 * 
	 * @author Jideas
	 * 
	 */
	@Publish
	protected class LoadPhoneMsgActiveConfigProvider extends
			ResultProvider<PhoneMsgActiveConfig> {

		@Override
		protected PhoneMsgActiveConfig provide(Context context)
				throws Throwable {
			if (null == config) {
				PhoneMsgPublishService.this.init(context);
			}
			return new PhoneMsgActiveConfig(config.isActiving());
		}

	}

}
