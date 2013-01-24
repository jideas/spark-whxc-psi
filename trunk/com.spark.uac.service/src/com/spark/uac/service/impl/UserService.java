/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.service.impl
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-11    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.service.impl
 * 修改记录：
 * 日期                作者           内容
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
 * <p>认证中心用户服务</p>
 *


 *
 
 * @version 2012-4-11
 */

public class UserService extends Service{
	
	/**
	 * MD5 盐值
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
	    super("认证中心用户服务");
	    this.ormUserById = ormUserById;
//	    this.ormUserListByTenantName = ormUserListByTenantName;
//	    this.ormUserListByTenantCode = ormUserListByTenantCode;
	    this.ormUserByLogin = ormUserByLogin;
    }


	/**
	 * 
	 * <p>查询用户</p>
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
	 * <p>创建一个有效的未激活用户</p>
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
				throw new RuntimeException("没有这个租户:"+task.getTenantId());
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
	 * <p>修改用户密码</p>
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
					throw new IllegalArgumentException("旧密码错误");
				}
			}else if(user.getStatus()==UserStatus.Activated){
				if(!context.find(Boolean.class,new CheckUserPwdIsValidKey(user, task.getOldPwd()))){
					throw new IllegalArgumentException("旧密码错误");
				}
			}else {
				throw new IllegalArgumentException("用户未激活");
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
	 * <p>修改用户状态</p>
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
	 * <p>登录系统</p>
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
				task.setMsg("手机号码输入错误！");
				task.setErrType(ErrType.MobileNo);
				return ;
			}
			List<UserInfo> result = new ArrayList<UserInfo>();
			for(UserInfoImpl user : userList){
				if(user.getStatus()==UserStatus.Not_Activated){
					task.setMsg("用户未激活！请首先通过获取密码功能，激活用户。");
					task.setErrType(ErrType.Activition);
					continue ;
				}else if(user.getStatus()==UserStatus.Activation){
					if(!context.find(Boolean.class,new CheckActivationCodeKey(user.getUserId(), task.getPwd()))){
						task.setMsg("激活码错误或者已过期！");
						task.setErrType(ErrType.Password);
						continue ;
					}
				}else if(user.getStatus()==UserStatus.Activated){
					if(!context.find(Boolean.class,new CheckUserPwdIsValidKey(user, task.getPwd()))){
						task.setMsg("密码错误！");
						task.setErrType(ErrType.Password);
						continue ;
					}
				}		

				// TenantInfo tenant =
				// context.find(TenantInfo.class,user.getTenantId());
				// //此处一个租户可能会拥有几个系统，就会有多个host，这个时候就需要将所有的host都列出来让用户选择登录哪一个，目前暂时只假设只有一个系统。
				// if(tenant.getServices()==null||tenant.getServices().length==0){
				// task.setMsg("用户无效！租户未开通服务或者服务已过期！");
				// task.setErrType(ErrType.MobileNo);
				// continue;
				// }
				// List<HostInfo> phs = new ArrayList<HostInfo>();
				// for(com.spark.uac.service.impl.TenantInfoImpl.Service service
				// : tenant
				// .getServices())
				// {
				// if(service.isValid()){
				// if(task.getProductSerialsEnum() != null){ //如果指定了登录什么产品系列
				// if(task.getProductSerialsEnum() != service
				// .getProductSerials())
				// {
				// continue;
				// }
				// }
				// if(service.getHostInfo() == null)
				// throw new NullPointerException(
				// "租户配置错误，找不到对应的应用服务器地址");
				// // user.setUrl(service.getHostInfo().getURL());
				// phs.add(service.getHostInfo());
				// }
				// }
				// user.setProductSerialsHosts(phs.toArray(new HostInfo[0]));
				// if(user.getUrl()==null){
				// task.setMsg("用户无效！租户未开通服务或者服务已过期！");
				// task.setErrType(ErrType.MobileNo);
				// continue;
				// }
				result.add(user);
            }
			if(result.size()==0&&userList.size()>1){
				task.setMsg("激活码或密码错误！");
				task.setErrType(ErrType.Password);
			}
			if(!result.isEmpty()){
				task.setUser(result.toArray(new UserInfo[0]));
				task.setSucceed(true);	            
			}
        }
		
	}
	
	/**
	 * 密码明文加密
	 
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
	 * 检查用户密码是否正确
	 
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
	 * 修改用户有效性
	 
	 *
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class ChanageUserEnabledHandler extends SimpleTaskMethodHandler<ChangeUserEnabledTask>{

		@Override
		protected void handle(Context context, ChangeUserEnabledTask task)
				throws Throwable {
			//todo:批量修改用户有效性
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
	 * <p>修改手机号码</p>
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
			if(task.getUserId()==null||StringUtils.isEmpty(task.getMobileNo()))throw new IllegalArgumentException("用户id或手机号码不能为空");
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
	 * <p>查询是否有指定用户</p>
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
