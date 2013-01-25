/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.service.impl
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.uac.service.impl
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

package com.spark.uac.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.service.Publish.Mode;
import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.entity.TenantInfo;
import com.spark.uac.publish.UserStatus;
import com.spark.uac.publish.entity.UserInfo;
import com.spark.uac.publish.key.CheckUserPwdIsValidKey;
import com.spark.uac.publish.key.HasUserKey;
import com.spark.uac.publish.task.ChangeUserEnabledTask;
import com.spark.uac.publish.task.ChangeUserMobileNoTask;
import com.spark.uac.publish.task.CreateUserTask;
import com.spark.uac.publish.task.LoginUserTask;
import com.spark.uac.publish.task.RemoveUserTask;
import com.spark.uac.publish.task.UpdateUserPasswordTask;
import com.spark.uac.publish.task.LoginUserTask.ErrType;
import com.spark.uac.service.db.ORMUserById;
import com.spark.uac.service.db.ORMUserByLogin;
import com.spark.uac.service.db.ORMUserListByTenantCode;
import com.spark.uac.service.db.ORMUserListByTenantName;
import com.spark.uac.task.ChangeUserStatusTask;
import com.spark.uac.task.CheckActivationCodeKey;
import com.spark.uac.task.PwdEncryptionTask;

/**
 * <p>��֤�����û�����</p>
 *


 *
 
 * @version 2012-4-11
 */

public class UserService extends Service{
	
	/**
	 * MD5 ��ֵ
	 */
	private static final String Salt = "!@#-jiuqisaas<>?";
	
	final private ORMUserById ormUserById;
//	final private ORMUserListByTenantName ormUserListByTenantName;
//	final private ORMUserListByTenantCode ormUserListByTenantCode;
	final private ORMUserByLogin ormUserByLogin;
	
	protected UserService(
			final ORMUserById ormUserById,
			final ORMUserListByTenantName ormUserListByTenantName,
			final ORMUserListByTenantCode ormUserListByTenantCode,
			final ORMUserByLogin ormUserByLogin){
	    super("��֤�����û�����");
	    this.ormUserById = ormUserById;
//	    this.ormUserListByTenantName = ormUserListByTenantName;
//	    this.ormUserListByTenantCode = ormUserListByTenantCode;
	    this.ormUserByLogin = ormUserByLogin;
    }


	/**
	 * 
	 * <p>��ѯ�û�</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-11
	 */
	@Publish
	protected class GetUserById extends OneKeyResultProvider<UserInfo, GUID> {

		@Override
		protected UserInfo provide(Context context, GUID id) throws Throwable {
			return context.newORMAccessor(ormUserById).findByRECID(id);
		}

	}

	
	/**
	 * 
	 * <p>����һ����Ч��δ�����û�</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-11
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class CreateUserHandler extends SimpleTaskMethodHandler<CreateUserTask>{

		@Override
        protected void handle(Context context, CreateUserTask task)
                throws Throwable
        {
			TenantInfo tenant = context.find(TenantInfo.class,task.getTenantId());
			if(tenant==null){
				throw new RuntimeException("û������⻧:"+task.getTenantId());
			}
	        UserInfoImpl entity = new UserInfoImpl();
	        entity.setUserId(task.getUserId());
	        entity.setEnabled(task.isValid());
	        entity.setMobileNo(task.getMobile());
	        entity.setStatus(UserStatus.Not_Activated);
	        entity.setTenantCode(tenant.getFishNumber());
	        entity.setTenantId(task.getTenantId());
	        entity.setTenantName(tenant.getTenantTitle());
	        context.newORMAccessor(ormUserById).insert(entity);
        }		
	}
	
	/**
	 * 
	 * <p>�޸��û�����</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-11
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class UpdateUserPasswordHandler extends SimpleTaskMethodHandler<UpdateUserPasswordTask>{

		@Override
        protected void handle(Context context, UpdateUserPasswordTask task)
                throws Throwable
        {
			UserInfoImpl user = context.newORMAccessor(ormUserById).findByRECID(task.getUserId());
			if(user.getStatus()==UserStatus.Activation){
				if(!context.find(Boolean.class,new CheckActivationCodeKey(user.getUserId(), task.getOldPwd()))){
					throw new IllegalArgumentException("���������");
				}
			}else if(user.getStatus()==UserStatus.Activated){
				if(!context.find(Boolean.class,new CheckUserPwdIsValidKey(user, task.getOldPwd()))){
					throw new IllegalArgumentException("���������");
				}
			}else {
				throw new IllegalArgumentException("�û�δ����");
			}
			PwdEncryptionTask pwdTask = new PwdEncryptionTask(task.getNewPwd());
			context.handle(pwdTask);
			user.setPassword(pwdTask.getCiphertext());
			user.setStatus(UserStatus.Activated);
			context.newORMAccessor(ormUserById).update(user);
        }
		
	}
	
	/**
	 * 
	 * <p>�޸��û�״̬</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-12
	 */
	@Publish
	protected final class ChangeUserStatusHandler extends SimpleTaskMethodHandler<ChangeUserStatusTask>{

		@Override
        protected void handle(Context context, ChangeUserStatusTask task)
                throws Throwable
        {
			UserInfoImpl user = context.newORMAccessor(ormUserById).findByRECID(task.getUserId());
			user.setStatus(task.getUserStatus());
			context.newORMAccessor(ormUserById).update(user);	        
        }
		
	}
	
	/**
	 * 
	 * <p>��¼ϵͳ</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-11
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class LoginUserHandler extends SimpleTaskMethodHandler<LoginUserTask>{

		@Override
        protected void handle(Context context, LoginUserTask task)
                throws Throwable
        {
			List<UserInfoImpl> userList;
//			if(task.getLoginType()==LoginType.TenantCode){
//				userList = context.newORMAccessor(ormUserListByTenantCode).fetch(task.getTenant());
//			}else{
//				userList = context.newORMAccessor(ormUserListByTenantName).fetch(task.getTenant());
//			}
			userList = context.newORMAccessor(ormUserByLogin).fetch(task.getMobile());
			if(userList.isEmpty()){
				task.setMsg("�ֻ������������");
				task.setErrType(ErrType.MobileNo);
				return ;
			}
			List<UserInfo> result = new ArrayList<UserInfo>();
			for(UserInfoImpl user : userList){
				if(user.getStatus()==UserStatus.Not_Activated){
					task.setMsg("�û�δ���������ͨ����ȡ���빦�ܣ������û���");
					task.setErrType(ErrType.Activition);
					continue ;
				}else if(user.getStatus()==UserStatus.Activation){
					if(!context.find(Boolean.class,new CheckActivationCodeKey(user.getUserId(), task.getPwd()))){
						task.setMsg("�������������ѹ��ڣ�");
						task.setErrType(ErrType.Password);
						continue ;
					}
				}else if(user.getStatus()==UserStatus.Activated){
					if(!context.find(Boolean.class,new CheckUserPwdIsValidKey(user, task.getPwd()))){
						task.setMsg("�������");
						task.setErrType(ErrType.Password);
						continue ;
					}
				}		

				// TenantInfo tenant =
				// context.find(TenantInfo.class,user.getTenantId());
				// //�˴�һ���⻧���ܻ�ӵ�м���ϵͳ���ͻ��ж��host�����ʱ�����Ҫ�����е�host���г������û�ѡ���¼��һ����Ŀǰ��ʱֻ����ֻ��һ��ϵͳ��
				// if(tenant.getServices()==null||tenant.getServices().length==0){
				// task.setMsg("�û���Ч���⻧δ��ͨ������߷����ѹ��ڣ�");
				// task.setErrType(ErrType.MobileNo);
				// continue;
				// }
				// List<HostInfo> phs = new ArrayList<HostInfo>();
				// for(com.spark.uac.service.impl.TenantInfoImpl.Service service
				// : tenant
				// .getServices())
				// {
				// if(service.isValid()){
				// if(task.getProductSerialsEnum() != null){ //���ָ���˵�¼ʲô��Ʒϵ��
				// if(task.getProductSerialsEnum() != service
				// .getProductSerials())
				// {
				// continue;
				// }
				// }
				// if(service.getHostInfo() == null)
				// throw new NullPointerException(
				// "�⻧���ô����Ҳ�����Ӧ��Ӧ�÷�������ַ");
				// // user.setUrl(service.getHostInfo().getURL());
				// phs.add(service.getHostInfo());
				// }
				// }
				// user.setProductSerialsHosts(phs.toArray(new HostInfo[0]));
				// if(user.getUrl()==null){
				// task.setMsg("�û���Ч���⻧δ��ͨ������߷����ѹ��ڣ�");
				// task.setErrType(ErrType.MobileNo);
				// continue;
				// }
				result.add(user);
            }
			if(result.size()==0&&userList.size()>1){
				task.setMsg("��������������");
				task.setErrType(ErrType.Password);
			}
			if(!result.isEmpty()){
				task.setUser(result.toArray(new UserInfo[0]));
				task.setSucceed(true);	            
			}
        }
		
	}
	
	/**
	 * �������ļ���
	 
	 *
	 */
	@Publish
	protected final class PwdEncryptionHandler extends SimpleTaskMethodHandler<PwdEncryptionTask>{

		
		@Override
		protected void handle(Context context, PwdEncryptionTask task)
				throws Throwable {
			task.setCiphertext(GUID.MD5Of(task.getPwd()+Salt));
		}		
	}
	
	/**
	 * ����û������Ƿ���ȷ
	 
	 *
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class CheckUserPwdProvider extends OneKeyResultProvider<Boolean, CheckUserPwdIsValidKey>{

		@Override
		protected Boolean provide(Context context, CheckUserPwdIsValidKey key)
				throws Throwable {
			PwdEncryptionTask pwdTask = new PwdEncryptionTask(key.getPwd());
			context.handle(pwdTask);
			if(key.getUser()!=null){
				return key.getUser().getPassword().equals(pwdTask.getCiphertext());
			}else{
				UserInfo user = context.newORMAccessor(ormUserById).findByRECID(key.getUid());
				return user.getPassword().equals(pwdTask.getCiphertext());
			}
		}
	}
	
	/**
	 * �޸��û���Ч��
	 
	 *
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class ChanageUserEnabledHandler extends SimpleTaskMethodHandler<ChangeUserEnabledTask>{

		@Override
		protected void handle(Context context, ChangeUserEnabledTask task)
				throws Throwable {
			//todo:�����޸��û���Ч��
			for (GUID uid : task.getUserId()) {
				UserInfoImpl user = context.newORMAccessor(ormUserById).findByRECID(uid);
				if(user.isEnabled()==task.isEnabled())continue;
				user.setEnabled(task.isEnabled());
				
				context.newORMAccessor(ormUserById).update(user);	        
			}
		}
		
	}
	
	/**
	 * 
	 * <p>�޸��ֻ�����</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-19
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class ChangeUserMobileNoHandler extends SimpleTaskMethodHandler<ChangeUserMobileNoTask>{

		@Override
        protected void handle(Context context, ChangeUserMobileNoTask task)
                throws Throwable
        {
			if(task.getUserId()==null||StringUtils.isEmpty(task.getMobileNo()))throw new IllegalArgumentException("�û�id���ֻ����벻��Ϊ��");
			UserInfoImpl user = context.newORMAccessor(ormUserById).findByRECID(task.getUserId());
			if(user.getMobileNo().equals(task.getMobileNo()))return ;
			user.setMobileNo(task.getMobileNo());
			context.newORMAccessor(ormUserById).update(user);	        			
        }
		
	}
	
	
	@Publish(Mode.SITE_PUBLIC)
	protected final class RemoveUserHandler extends SimpleTaskMethodHandler<RemoveUserTask>{

		@Override
        protected void handle(Context context, RemoveUserTask task)
                throws Throwable
        {
			ORMAccessor<UserInfoImpl> acc = context.newORMAccessor(ormUserById);
			UserInfoImpl user = acc.findByRECID(task.getUserId());
			if(user!=null){
				acc.delete(task.getUserId());
			}
        }
		
	}
	
	/**
	 * 
	 * <p>��ѯ�Ƿ���ָ���û�</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-22
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class HasUserProvider extends OneKeyResultProvider<Boolean, HasUserKey>{

		@Override
        protected Boolean provide(Context context, HasUserKey key)
                throws Throwable
        {
	        UserInfoImpl user = context.newORMAccessor(ormUserById).findByRECID(key.getId());
	        return user!=null;
        }
		
	}
}
