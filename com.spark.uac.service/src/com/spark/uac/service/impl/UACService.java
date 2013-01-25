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
	 * �����
	 */
	private static Map<GUID,ActivationCode> activations = new Hashtable<GUID,ActivationCode>();

	/**
	 * 
	 * <p>������Ϣ</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-12
	 */
	private class ActivationCode {
		
		/**
		 * ��Ч�� �ݶ�10����
		 */
		public static final long life = 1000*60*10;
		
		/**
		 * �����볤��
		 */
		public static final int CodeLen = 6;
		
		/**
		 * ������
		 */
		public String code;
		/**
		 * ��������
		 */
//		public ActivationType type;
		
		/**
		 * ��������Ч�ڽ�����Ӽ�������Ƴ�
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
		super("��֤�����û�����");
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
//			// XXX������򻯲��Ի������̣�ֱ�ӽ��ܾ����˻�����
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
	 * <p>��������</p>
	 *	���ɼ�����
	
	
	 *
	 
	 * @version 2012-4-12
	 */
	@Publish
	protected class ResetPwdHandler extends SimpleTaskMethodHandler<ResetPwdTask>{

		@Override
        protected void handle(Context context, ResetPwdTask task)
                throws Throwable
        {
			//��֤��ҵ�����Ƿ���ȷ
			if(task.getTenantName()==null)throw new IllegalArgumentException("�����û�ʱ��ҵ���Ʋ���Ϊ��");
			TenantInfo tenant = context.find(TenantInfo.class,task.getTenantName());			
			if(tenant==null){
				task.setMsg("��ҵ����¼�����");
				task.setErrType(ErrType.CompanyName);
				return ;
			}
			List<UserInfoImpl> userList = context.newORMAccessor(qd_GetUserListByTenantId).fetch(tenant.getId());
			//��֤�û������Ƿ���ȷ
//			TenantInfo t = context.find(TenantInfo.class,userList.get(0).getTenantId());
			com.spark.uac.service.impl.TenantInfoImpl.Service 
				service = tenant.getServcie(task.getProductSerialsEnum());
			if(service==null||!service.isValid()){
				task.setMsg("��ҵû�п�ָͨ�����������ѹ���");
				task.setErrType(ErrType.CompanyName);
				return ;
			}
			HostInfo host = service.getHostInfo();
//			RemoteServiceInvoker remote = context.newEfficientRemoteServiceInvoker(host.getURL());
			CheckEmployeeNameIsValidKey key = new CheckEmployeeNameIsValidKey(userList.get(0).getTenantId(),task.getUserName(),task.getMobileNo());
			try{
	            if(!context.find(Boolean.class,key)){
	            	task.setMsg("�û���������ȷ��");
	            	task.setErrType(ErrType.Name);
	            	return ;
	            }
            }
            catch(NullPointerException e){
	            throw new NullPointerException(host.getURL()+"�ϵ�"+task.getProductSerialsEnum().getName()+"ҵ����ϵͳ����ʵ�֣�OneKeyResultProvider<Boolean, CheckEmployeeNameIsValidKey>�ӿ�");
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
				task.setMsg("��ҵ��û�д��ֻ�������û�");
				return ;
			}
			//����û�δ����򼤻ʽΪ ��ȡ���룬����û��Ѽ�����Ϊ�һ�����
			ActivationType type = user.getStatus()==UserStatus.Not_Activated ? ActivationType.Get_Password : ActivationType.Retrieve_Password;
			ActivationCode code = new ActivationCode(user.getUserId(),type);
			activations.put(user.getUserId(), code);
			//�޸��û�״̬Ϊ������
			context.handle(new ChangeUserStatusTask(user.getUserId(), UserStatus.Activation)); 
			task.setCode(code.getCode());
//			StringBuilder msg = new StringBuilder();
//			if(type==ActivationType.Get_Password){
//				msg.append("�𾴵��û������ã����������ϵͳ��¼����Ϊ").append(code.getCode()).append("����ӭʹ�ã�");
//			}else if(type==ActivationType.Retrieve_Password){
//				msg.append("��¼ϵͳ������Ϊ:").append(code.getCode());
//			}
//			context.handle(new SendMsgTask(task.getMobileNo(),msg.toString(),user.getTenantId()));
			task.setSucceed(true);
        }
		
	}
	
	/**
	 * 
	 * <p>��鼤�����Ƿ���ȷ</p>
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
