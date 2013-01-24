/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-15    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.base.internal.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-15    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Goods;
import com.spark.psi.base.GoodsCategory;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.Login;
import com.spark.psi.base.Partner;
import com.spark.psi.base.SalesmanCredit;
import com.spark.psi.base.Store;
import com.spark.psi.base.Tenant;
import com.spark.psi.base.internal.entity.TenantResources;
import com.spark.psi.base.internal.entity.TenantSysParam;
import com.spark.psi.base.internal.entity.ormentity.TenantOrmEntity;
import com.spark.psi.base.internal.service.query.QD_GetAllTenant;
import com.spark.psi.base.internal.service.query.QD_GetTenantById;
import com.spark.psi.base.task.resource.UpdateApprovalConfigTask;
import com.spark.psi.base.task.resource.UpdateTenantResourceTask;
import com.spark.psi.base.task.resource.UpdateTenantSysParamTask;
import com.spark.psi.publish.SysParamKey;
import com.spark.psi.publish.base.config.entity.CompanyInfo;
import com.spark.psi.publish.base.config.task.UpdateCompanyInfoTask;
import com.spark.uac.publish.ProductSerialsEnum;
import com.spark.uac.publish.TenantConfigFormat;

/**
 * <p>
 * 租户相关资源根节点
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2012<br>
 * Company: 久其
 * </p>
 * 
 
 * @version 2012-3-15
 */

public class TenantResourceService extends
        ResourceService<Tenant, TenantResources, TenantResources>
{

	final QD_GetAllTenant qD_GetAllTenant;

	final QD_GetTenantById qD_GetTenantById;

	protected TenantResourceService(final QD_GetAllTenant qD_GetAllTenant,
	        final QD_GetTenantById qD_GetTenantById)
	{
		super(
		        "com.spark.psi.base.internal.service.EmployeeInternalService",
		        ResourceKind.SINGLETON_IN_CLUSTER);
		this.qD_GetAllTenant = qD_GetAllTenant;
		this.qD_GetTenantById = qD_GetTenantById;
	}

	protected class PartnerGroupResourceReference extends
	        ResourceReference<Partner>
	{

	}

	protected class EmployeeGroupResourceReference extends
	        ResourceReference<Employee>
	{

	}

	protected class StoreGroupResourceReference extends
	        ResourceReference<Store>
	{

	}

	protected class DepartmentGroupResourceReference extends
	        ResourceReference<Department>
	{

	}

	protected class GoodsCategoryGroupResourceReference extends
	        ResourceReference<GoodsCategory>
	{

	}

	protected class GoodsItemGroupResourceReference extends
	        ResourceReference<GoodsItem>
	{
	}

	protected class GoodsGroupResourceReference extends
	        ResourceReference<Goods>
	{
	}

	protected class SalesManCreditGroupResourceReference extends
	        ResourceReference<SalesmanCredit>
	{
	}

	protected void init(Context context){
		context.getList(Tenant.class);
	}

	@Override
	protected void initResources(
	        Context context,
	        ResourceInserter<Tenant, TenantResources, TenantResources> initializer)
	{
		initTenant(context);
		ORMAccessor<TenantOrmEntity> acc =
		        context.newORMAccessor(qD_GetAllTenant);
		for(TenantOrmEntity entity : acc.fetch()){
			initializer.putResource(initializer(context, entity));
		}
		acc.unuse();
	}

	private TenantResources initializer(
	        Context context, TenantOrmEntity entity)
	{
		SXElementBuilder seb;
		try{
			seb = new SXElementBuilder();
			SXElement element = seb.build(entity.getConfig()).firstChild();
			String title = element.getAttribute(TenantConfigFormat.A.TenantName);
			GUID tenantId = entity.getId();
			TenantResources tr = new TenantResources(tenantId, title);
			for(SysParamKey key : SysParamKey.values()){
				tr.addSysParam(key, context.find(TenantSysParam.class,
				        tenantId, key));
			}
			Iterator<SXElement> services = element.firstChild(TenantConfigFormat.Services.Name).getChildren(TenantConfigFormat.Services.Service.Name).iterator();
			while(services.hasNext()){		
				SXElement service = services.next();
				if(!service.getAttribute( 
				        TenantConfigFormat.Services.Service.A.ProductSerial)
				        .equals(ProductSerialsEnum.PSI.getCode())) continue;
				String sysName = service.getAttribute(TenantConfigFormat.Services.Service.A.SysName);
				int userCount =
				        Integer.parseInt(service.getAttribute(TenantConfigFormat.Services.Service.A.UserCount));
				tr.setUserCount(userCount);
				tr.setProduct(service.getAttribute(TenantConfigFormat.Services.Service.A.ProductCode));
				long startDate = 0,endDate = 0;
				try{
					startDate = Long.parseLong(service.getAttribute(TenantConfigFormat.Services.Service.A.ServerStartDate));
					endDate = Long.parseLong(service.getAttribute(TenantConfigFormat.Services.Service.A.ServerEndDate));
				}catch (java.lang.Exception e) {
					System.err.println("租户：【"+tr.getTitle()+"】的服务日期格式不正确，请输入正确的long型");
				}
//				if(StringUtils.isEmpty(startDate)){
//					startDate = "2012-1-1";
//				}
//				String endDate = ;
//				if(StringUtils.isEmpty(endDate)){
//					endDate = "2015-1-1";
//				}
				tr.setStartDate(startDate);
				tr.setEndDate(endDate);
				SXElement paramConfigElement = service.firstChild(TenantConfigFormat.Services.Service.ParamConfig.Name);
				boolean directSupply = false;
				try{
					directSupply =
						Boolean.parseBoolean(paramConfigElement.getAttribute(TenantConfigFormat.Services.Service.ParamConfig.A.DirectProviderFlag));
				}catch (Throwable e) {}
				tr.addSysParam(SysParamKey.STORE_DIRECT, new TenantSysParam(
						tenantId, SysParamKey.STORE_DIRECT, directSupply));
				//创建企业信息
				UpdateCompanyInfoTask task = new UpdateCompanyInfoTask();
				task.setId(tenantId);
				task.setCompanyName(title);
				task.setSystemName(sysName);
				if(context.find(CompanyInfo.class, tenantId) == null){
					context.handle(task, UpdateCompanyInfoTask.Method.Create);
				}
//				else{
//					context.handle(task,UpdateCompanyInfoTask.Method.Update);
//				}
			}
			return tr;
		}
		catch(Throwable t){
			t.printStackTrace();
			throw new RuntimeException("初始化租户时发生错误 id:"+entity.getId(),t);
		}
	}

	private void initTenant(Context context){
		File hostFile =
	        new File(com.jiuqi.dna.core.spi.application.AppUtil
	                .getDefaultApp().getDNARoot().getAbsolutePath()
	                + File.separator + "work" + File.separator + "Tenant.xml");
		if(!hostFile.exists())return;
		SXElementBuilder seb;
		ORMAccessor<TenantOrmEntity> acc =
		        context.newORMAccessor(qD_GetTenantById);
		try{
			seb = new SXElementBuilder();
			SXElement tenantsNode = seb.build(hostFile).firstChild();
			Iterator<SXElement> it =
			        tenantsNode.getChildren(TenantConfigFormat.Root).iterator();
			while(it.hasNext()){
				SXElement element = it.next();
				String title = element.getAttribute(TenantConfigFormat.A.TenantName);
				GUID tenantId = GUID.MD5Of(title);
				TenantOrmEntity e = acc.findByRECID(tenantId);
				if(e != null){
					acc.delete(e.getId());
				}
				TenantOrmEntity entity = new TenantOrmEntity();
				entity.setId(tenantId);
				entity.setConfig(element.toString());
				acc.insert(entity);
			}
		}
		catch(Throwable t){
			t.printStackTrace();
		}
//		finally{
//			try{
//				hostFile.;
//			}
//			catch(IOException e){
//			}
//		}
	}

	final class GetTenantResourceById extends OneKeyResourceProvider<GUID>{

		@Override
		protected GUID getKey1(TenantResources keysHolder){
			return keysHolder.getId();
		}
	}

	@Publish
	final protected class UpdateApprovalConfigHandler extends
	        SimpleTaskMethodHandler<UpdateApprovalConfigTask>
	{

		@Override
		protected void handle(
		        ResourceContext<Tenant, TenantResources, TenantResources> context,
		        UpdateApprovalConfigTask task) throws Throwable
		{
			GUID tenantId = task.getTenantId();
			if(tenantId == null)
			    tenantId = context.find(Login.class).getTenantId();
			TenantResources tr =
			        (TenantResources)context.modifyResource(tenantId);
			tr.setApprovalConfig(task.getConfig());
			context.postModifiedResource(tr);
		}

	}

	@Publish
	protected class UpdateTenantSysParamHandler extends
	        SimpleTaskMethodHandler<UpdateTenantSysParamTask>
	{

		@Override
		protected void handle(
		        ResourceContext<Tenant, TenantResources, TenantResources> context,
		        UpdateTenantSysParamTask task) throws Throwable
		{
			TenantSysParam sysParam = task.getSysParam();
			GUID tenantId = sysParam.getTenantId();
			if(tenantId == null)
			    tenantId = context.find(Login.class).getTenantId();
			TenantResources tr =
			        (TenantResources)context.modifyResource(tenantId);
			tr.addSysParam(SysParamKey.valueOf(sysParam.getKey()), sysParam);
			context.postModifiedResource(tr);
		}

	}

	@Publish
	protected final class CreateTenantHandler extends TaskMethodHandler<UpdateTenantResourceTask, UpdateTenantResourceTask.Method>{

		protected CreateTenantHandler(){
	        super(UpdateTenantResourceTask.Method.Put);
        }

		@Override
        protected void handle(
                ResourceContext<Tenant, TenantResources, TenantResources> context,
                UpdateTenantResourceTask task) throws Throwable
        {
	        ORMAccessor<TenantOrmEntity> acc = context.newORMAccessor(qD_GetTenantById);
	        TenantOrmEntity entity = acc.findByRECID(task.getId());
	        context.putResource(initializer(context, entity));
        }
		
	}

	@Publish
	protected final class ModifyTenantHandler extends TaskMethodHandler<UpdateTenantResourceTask, UpdateTenantResourceTask.Method>{

		protected ModifyTenantHandler(){
	        super(UpdateTenantResourceTask.Method.Modify);
        }

		@Override
        protected void handle(
                ResourceContext<Tenant, TenantResources, TenantResources> context,
                UpdateTenantResourceTask task) throws Throwable
        {
	        ORMAccessor<TenantOrmEntity> acc = context.newORMAccessor(qD_GetTenantById);
	        TenantOrmEntity entity = acc.findByRECID(task.getId());
	        TenantResources old = context.modifyResource(task.getId());
	        TenantResources _new = initializer(context, entity);
	        old.setApprovalConfig(_new.getApprovalConfig());
	        old.setEndDate(_new.getEndDate());
	        old.setProduct(_new.getProduct());
	        old.setStartDate(_new.getStartDate());
	        old.setUserCount(_new.getUserCount());
	        old.setSysParams(_new.getSysParams());
	        context.postModifiedResource(old);
//			throw new java.lang.Exception("此功能暂未实现");
        }
		
	}
	
}
