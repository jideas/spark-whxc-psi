package com.spark.uac.service.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.RemoteServiceInvoker;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.RandomCode;
import com.spark.uac.entity.TenantInfo;
import com.spark.uac.publish.ActivationType;
import com.spark.uac.publish.ProductSerialsEnum;
import com.spark.uac.publish.UserStatus;
import com.spark.uac.publish.entity.HostInfo;
import com.spark.uac.publish.key.CheckEmployeeNameIsValidKey;
import com.spark.uac.publish.task.ResetPwdTask;
import com.spark.uac.publish.task.ResetPwdTask.ErrType;
import com.spark.uac.service.db.ORMActivation;
import com.spark.uac.service.db.ORMUserById;
import com.spark.uac.service.db.ORMUserListByTenantName;
import com.spark.uac.service.db.QD_GetUserListByTenantId;
import com.spark.uac.task.ChangeUserStatusTask;
import com.spark.uac.task.CheckActivationCodeKey;
import com.spark.uac.task.SendMsgTask;

public class UACService extends Service {

//	private ORMUserById ormUserById;
//	private ORMActivation ormActivation;
	final private ORMUserListByTenantName ormUserListByTenantName;
	final QD_GetUserListByTenantId qd_GetUserListByTenantId;
	/**
	 * 激活池
	 */
	private static Map<GUID,ActivationCode> activations = new Hashtable<GUID,ActivationCode>();

	/**
	 * 
	 * <p>激活信息</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-12
	 */
	private class ActivationCode {
		
		/**
		 * 有效期 暂定10分钟
		 */
		public static final long life = 1000*60*10;
		
		/**
		 * 激活码长度
		 */
		public static final int CodeLen = 6;
		
		/**
		 * 激活码
		 */
		public String code;
		/**
		 * 激活类型
		 */
//		public ActivationType type;
		
		/**
		 * 创建后有效期结束后从激活池中移除
		 */
		public ActivationCode(final GUID userId,ActivationType type){
			this.code = RandomCode.newCode(CodeLen);
//			this.type = type;
			new Thread(){
				
				public void run(){
					try{
						Thread.sleep(life);
						activations.remove(userId);
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}					
				}
			}.start();
		}
		
		
		
		public String getCode(){
        	return code;
        }



//		public ActivationType getType(){
//        	return type;
//        }

	}
	
	
	
	protected UACService(
			final ORMUserById ormUserById,
			final ORMActivation ormActivation,
			final ORMUserListByTenantName ormUserListByTenantName,
			final QD_GetUserListByTenantId qd_GetUserListByTenantId) {
		super("认证中心用户服务");
//		this.ormUserById = ormUserById;
//		this.ormUserByLogin = ormUserByLogin;
//		this.ormActivation = ormActivation;
		this.ormUserListByTenantName = ormUserListByTenantName;
		this.qd_GetUserListByTenantId = qd_GetUserListByTenantId;
	}
//
//	@Publish
//	protected class GetActivationListByMobile extends
//			OneKeyResultListProvider<ActivationInfo, String> {
//
//		@Override
//		protected void provide(Context context, String mobile,
//				List<ActivationInfo> resultList) throws Throwable {
//			resultList.addAll(context.newORMAccessor(ormActivation).fetch(
//					mobile));
//		}
//
//	}

//	@Publish
//	protected class AddActivingUserTaskHandler extends
//			TaskMethodHandler<ActivingUserTask, ActivingUserTask.Method> {
//
//		protected AddActivingUserTaskHandler() {
//			super(ActivingUserTask.Method.Add);
//		}
//
//		@Override
//		protected void handle(Context context, ActivingUserTask task)
//				throws Throwable {
//			ActivationInfoImpl activationInfo = new ActivationInfoImpl();
//			activationInfo.setId(GUID.randomID());
//			activationInfo.setMobileNo(task.getMobileNo());
//			activationInfo.setSourceId(task.getTenantId());
//			activationInfo.setTargetId(task.getTenantId());
//			activationInfo.setTitle(task.getUserTitle());
//			activationInfo.setUserId(task.getUserId());
//			context.newORMAccessor(ormActivation).insert(activationInfo);
//
//			// XXX：这里简化测试环境过程，直接将总经理账户激活
//			if (task.getTenantId().equals(task.getUserId())) {
//				ActiveUserTask activeUserTask = new ActiveUserTask(
//						activationInfo.getId(), "");
//				context.handle(activeUserTask);
//			}
//		}
//	}

//	@Publish
//	protected class UpdateActivationPasswordTaskHandler extends
//			SimpleTaskMethodHandler<UpdateActivationPasswordTask> {
//
//		@Override
//		protected void handle(Context context, UpdateActivationPasswordTask task)
//				throws Throwable {
//			ActivationInfoImpl activation = context.newORMAccessor(
//					ormActivation).findByRECID(task.getActivationId());
//			activation.setActiveCode(task.getPassword());
//			activation.setActiveCodeCreateTime(System.currentTimeMillis());
//			context.newORMAccessor(ormActivation).update(activation);
//		}
//
//	}

//	@Publish
//	protected class ActiveUserTaskHandler extends
//			SimpleTaskMethodHandler<ActiveUserTask> {
//
//		@Override
//		protected void handle(Context context, ActiveUserTask task)
//				throws Throwable {
//			ActivationInfoImpl activation = context.newORMAccessor(
//					ormActivation).findByRECID(task.getActivationId());
//			UserInfoImpl userInfo = new UserInfoImpl();
//			userInfo.setUserId(activation.getUserId());
////			userInfo.setTitle(activation.getTitle());
//			userInfo.setMobileNo(activation.getMobileNo());
//			userInfo.setTenantId(activation.getTargetId());
////			userInfo.setPassword(task.getPassword());
//			userInfo.setEnabled(true);
//			//
//			context.newORMAccessor(ormUserById).insert(userInfo);
//			//
//			context.newORMAccessor(ormActivation)
//					.delete(task.getActivationId());
//		}
//	}

	/**
	 * 
	 * <p>重置密码</p>
	 *	生成激活码
	
	
	 *
	 
	 * @version 2012-4-12
	 */
	@Publish
	protected class ResetPwdHandler extends SimpleTaskMethodHandler<ResetPwdTask>{

		@Override
        protected void handle(Context context, ResetPwdTask task)
                throws Throwable
        {
			//验证企业名称是否正确
			if(task.getTenantName()==null)throw new IllegalArgumentException("激活用户时企业名称不能为空");
			TenantInfo tenant = context.find(TenantInfo.class,task.getTenantName());			
			if(tenant==null){
				task.setMsg("企业名称录入错误！");
				task.setErrType(ErrType.CompanyName);
				return ;
			}
			List<UserInfoImpl> userList = context.newORMAccessor(qd_GetUserListByTenantId).fetch(tenant.getId());
			//验证用户姓名是否正确
//			TenantInfo t = context.find(TenantInfo.class,userList.get(0).getTenantId());
			com.spark.uac.service.impl.TenantInfoImpl.Service 
				service = tenant.getServcie(task.getProductSerialsEnum());
			if(service==null||!service.isValid()){
				task.setMsg("企业没有开通指定服务或服务已过期");
				task.setErrType(ErrType.CompanyName);
				return ;
			}
			HostInfo host = service.getHostInfo();
//			RemoteServiceInvoker remote = context.newEfficientRemoteServiceInvoker(host.getURL());
			CheckEmployeeNameIsValidKey key = new CheckEmployeeNameIsValidKey(userList.get(0).getTenantId(),task.getUserName(),task.getMobileNo());
			try{
	            if(!context.find(Boolean.class,key)){
	            	task.setMsg("用户姓名不正确！");
	            	task.setErrType(ErrType.Name);
	            	return ;
	            }
            }
            catch(NullPointerException e){
	            throw new NullPointerException(host.getURL()+"上的"+task.getProductSerialsEnum().getName()+"业务子系统必须实现：OneKeyResultProvider<Boolean, CheckEmployeeNameIsValidKey>接口");
            }
			UserInfoImpl user = null;
			for(UserInfoImpl u : userList){
	            if(u.getMobileNo().equals(task.getMobileNo())&&u.isEnabled()){
	            	user = u;
	            	break;
	            }
            }
			if(user == null){
				task.setErrType(ErrType.Name);
				task.setMsg("企业中没有此手机号码的用户");
				return ;
			}
			//如果用户未激活，则激活方式为 获取密码，如果用户已激活则为找回密码
			ActivationType type = user.getStatus()==UserStatus.Not_Activated ? ActivationType.Get_Password : ActivationType.Retrieve_Password;
			ActivationCode code = new ActivationCode(user.getUserId(),type);
			activations.put(user.getUserId(), code);
			//修改用户状态为激活中
			context.handle(new ChangeUserStatusTask(user.getUserId(), UserStatus.Activation)); 
			task.setCode(code.getCode());
//			StringBuilder msg = new StringBuilder();
//			if(type==ActivationType.Get_Password){
//				msg.append("尊敬的用户，您好！您的鱼儿多系统登录密码为").append(code.getCode()).append("，欢迎使用！");
//			}else if(type==ActivationType.Retrieve_Password){
//				msg.append("登录系统的密码为:").append(code.getCode());
//			}
//			context.handle(new SendMsgTask(task.getMobileNo(),msg.toString(),user.getTenantId()));
			task.setSucceed(true);
        }
		
	}
	
	/**
	 * 
	 * <p>检查激活码是否正确</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-12
	 */
	@Publish
	protected final class CheckActivationCodeProvider extends OneKeyResultProvider<Boolean, CheckActivationCodeKey>{

		@Override
        protected Boolean provide(Context context, CheckActivationCodeKey key)
                throws Throwable
        {
	        ActivationCode code = activations.get(key.getUserId());
	        if(code==null)return false;
	        return code.getCode().equals(key.getPwd().toUpperCase());
        }
		
	}
	
}
