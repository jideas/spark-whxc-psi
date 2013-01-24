/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-19    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-19    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.service.Publish.Mode;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Login;
import com.spark.psi.base.internal.entity.ormentity.CompanyOrmEntity;
import com.spark.psi.base.internal.service.orm.Orm_Company;
import com.spark.psi.base.publicimpl.CompanyInfoImpl;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.base.config.entity.CompanyInfo;
import com.spark.psi.publish.base.config.task.UpdateCompanyInfoTask;
import com.spark.uac.publish.key.GetCompanyInfoKey;
import com.spark.uac.publish.task.GetCompanyLogoForTenantsRecidTask;

/**
 * <p>企业基础信息服务</p>
 *


 *
 
 * @version 2012-4-19
 */

public class CompanyPublishService extends Service{

	final Orm_Company orm_Company;
	
	protected CompanyPublishService(final Orm_Company orm_Company){
	    super("企业基础信息服务");
	    this.orm_Company = orm_Company;
    }

	/**
	 * 
	 * <p>创建企业信息</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-19
	 */
	@Publish
	protected final class CreateCompanyInfoHandler extends TaskMethodHandler<UpdateCompanyInfoTask, UpdateCompanyInfoTask.Method>{

		protected CreateCompanyInfoHandler()
        {
	        super(UpdateCompanyInfoTask.Method.Create);
        }

		@Override
        protected void handle(Context context, UpdateCompanyInfoTask task)
                throws Throwable
        {
			CompanyOrmEntity entity = new CompanyOrmEntity();
			fullEntity(entity, task);
			context.newORMAccessor(orm_Company).insert(entity);
        }
		
	}

	/**
	 * 
	 * <p>修改企业信息</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-19
	 */
	@Publish
	protected final class UpdateCompanyInfoHandler extends TaskMethodHandler<UpdateCompanyInfoTask, UpdateCompanyInfoTask.Method>{

		protected UpdateCompanyInfoHandler()
        {
	        super(UpdateCompanyInfoTask.Method.Update);
        }

		@Override
        protected void handle(Context context, UpdateCompanyInfoTask task)
                throws Throwable
        {
			ORMAccessor<CompanyOrmEntity> acc = context.newORMAccessor(orm_Company);
	        CompanyOrmEntity entity = acc.findByRECID(task.getId());
	        fullEntity(entity, task);
	        acc.update(entity);
        }
		
	}
	
	private void fullEntity(CompanyOrmEntity entity,UpdateCompanyInfoTask task){
		entity.setAddress(task.getAddress());
		entity.setCity(task.getCity());
		entity.setCompanyName(task.getCompanyName());
		entity.setCompanyShortName(task.getCompanyShortName());
		entity.setDistrict(task.getDistrict());
		entity.setFaxNumber(task.getFax());
		entity.setId(task.getId());
		entity.setLandLineNumber(task.getPhone());
		entity.setPostcode(task.getPostcode());
		entity.setProvince(task.getProvince());
		entity.setSystemName(task.getSystemName());
		entity.setLogo(task.getLogo());		
	}
	
	/**
	 * 
	 * <p>查询当前租户信息</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-19
	 */
	@Publish
	protected final class GetCompanyInfoProvider extends ResultProvider<CompanyInfo>{

		@Override
        protected CompanyInfo provide(Context context) throws Throwable{
	        return context.find(CompanyInfo.class,context.find(Login.class).getTenantId());
        }
		
	}
	
	/**
	 * 
	 * <p>查询当前租户信息</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-19
	 */
	@Publish
	protected final class GetCompanyInfoByIdProvider extends OneKeyResultProvider<CompanyInfo,GUID>{

		@Override
        protected CompanyInfo provide(Context context,GUID id) throws Throwable{
			CompanyOrmEntity company = context.newORMAccessor(orm_Company).findByRECID(id);
			if(company == null){
				return null;
			}
			CompanyInfoImpl entity = new CompanyInfoImpl();
			entity.setId(company.getId());
			entity.setAddress(company.getAddress());
			entity.setCity(getAare(context,company.getCity()));
			entity.setCompanyName(company.getCompanyName());
			entity.setCompanyShortName(company.getCompanyShortName());
			entity.setDistrict(getAare(context, company.getDistrict()));
			entity.setFaxNumber(company.getFaxNumber());
			entity.setLandLineNumber(company.getLandLineNumber());
			entity.setLogo(company.getLogo());
			entity.setPostcode(company.getPostcode());
			entity.setProvince(getAare(context,company.getProvince()));
			entity.setSystemName(company.getSystemName());
	        return entity;
        }
		
		private EnumEntity getAare(Context context,String code){
			if(code==null)return null;
			return context.find(EnumEntity.class,EnumType.Area,code);
		}
		
	}
	
	/**
	 * 
	 * <p>获得企业Logo</p>
	 *
	
	
	 *
	 
	 * @version 2012-6-7
	 */
	@Publish(Mode.SITE_PUBLIC)
	protected final class GetCompanyLogoByTenantIdProvider extends SimpleTaskMethodHandler<GetCompanyLogoForTenantsRecidTask>{

		@Override
        protected void handle(Context context,
                GetCompanyLogoForTenantsRecidTask task) throws Throwable
        {
	        CompanyInfo info = context.find(CompanyInfo.class,task.getREICD());
	        task.setLogo(info.getLogo());
        }
	}
	
	@Publish(Mode.SITE_PUBLIC)
	protected final class GetCompanyInfoProvider2 extends OneKeyResultProvider<com.spark.uac.publish.entity.CompanyInfo,GetCompanyInfoKey>{

		@Override
        protected com.spark.uac.publish.entity.CompanyInfo provide(
                Context context, GetCompanyInfoKey key) throws Throwable
        {
	        final CompanyInfo info = context.find(CompanyInfo.class,key.getTenantId());
	        if(info==null)return null;
	        return new com.spark.uac.publish.entity.CompanyInfo(){
				
				public String getShortName(){
					return info.getCompanyShortName();
				}
				
				public String getName(){
					return info.getCompanyName();
				}
				
				public byte[] getLogo(){
					return info.getLogo();
				}
				
				public GUID getId(){
					return info.getId();
				}
			};
        }
		
	}
	
	
}
