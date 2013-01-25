package com.spark.uac.service.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.RemoteServiceInvoker;
import com.jiuqi.dna.core.service.Publish.Mode;
import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.entity.TenantInfo;
import com.spark.uac.publish.ProductSerialsEnum;
import com.spark.uac.publish.TenantConfigFormat;
import com.spark.uac.publish.entity.HostInfo;
import com.spark.uac.publish.entity.UserInfo;
import com.spark.uac.publish.task.CreateUserTask;
import com.spark.uac.publish.task.UpdateSubServerTenantTask;
import com.spark.uac.publish.task.UpdateTenantTask;
import com.spark.uac.service.db.QD_GetAllTenant;
import com.spark.uac.service.db.QD_GetTenantById;
import com.spark.uac.service.impl.TenantInfoImpl.Service;

public class TenantService extends
        ResourceService<TenantInfo, TenantInfoImpl, TenantInfoImpl>
{

	final QD_GetAllTenant qD_GetAllTenant;

	final QD_GetTenantById qD_GetTenantById;

	protected TenantService(final QD_GetAllTenant qD_GetAllTenant,
	        final QD_GetTenantById qD_GetTenantById)
	{
		super("认证中心租户信息服务");
		this.qD_GetAllTenant = qD_GetAllTenant;
		this.qD_GetTenantById = qD_GetTenantById;
	}

	@Override
	protected void initResources(
	        Context context,
	        ResourceInserter<TenantInfo, TenantInfoImpl, TenantInfoImpl> initializer)
	        throws Throwable
	{
		initTenant(context);
		ORMAccessor<TenantInfoImpl> acc =
		        context.newORMAccessor(qD_GetAllTenant);
		for(TenantInfoImpl entity : acc.fetch()){
		        entity.setServices(getServices(context,entity));
	            initializer.putResource(entity);
        }
 	}
	
	private Service[] getServices(Context context,TenantInfoImpl entity){
		SXElementBuilder seb;
		try{
			List<Service> list = new ArrayList<Service>();
            String servicesStr = entity.getServicesStr();
            seb = new SXElementBuilder();
            SXElement e = seb.build(servicesStr).firstChild();
            Iterator<SXElement> services = e.getChildren(TenantConfigFormat.Services.Service.Name).iterator();
            while(services.hasNext()){
            	SXElement s = services.next();
            	String hid = s.getAttribute(TenantConfigFormat.Services.Service.A.ServiceHostId);
            	HostInfo hostInfo = context.find(HostInfo.class,hid);
            	ProductSerialsEnum pse = ProductSerialsEnum.getProductSeriesSeries(s.getAttribute(TenantConfigFormat.Services.Service.A.ProductSerial));
            	long endDate = 0;
            	try{
            		endDate = Long.parseLong(s.getAttribute(TenantConfigFormat.Services.Service.A.ServerEndDate));
            	}catch (Exception e1) {}
            	Service h = new Service(pse, hostInfo,endDate);
            	list.add(h);
            }
            return list.toArray(new Service[0]);
		}
            catch(Exception e){
	            System.err.println("认证中心初始化租户出错："+entity.getTenantTitle());
	            e.printStackTrace();
	            return new Service[0];
            }
	}

	private void initTenant(Context context){
		File hostFile =
	        new File(com.jiuqi.dna.core.spi.application.AppUtil
	                .getDefaultApp().getDNARoot().getAbsolutePath()
	                + File.separator + "work" + File.separator + "Tenant.xml");
		if(!hostFile.exists())return;
		SXElementBuilder seb;
		ORMAccessor<TenantInfoImpl> acc =
		        context.newORMAccessor(qD_GetTenantById);
		try{
			seb = new SXElementBuilder();
			SXElement tenantsNode = seb.build(hostFile).firstChild();
			Iterator<SXElement> it =
			        tenantsNode.getChildren(TenantConfigFormat.Root).iterator();
			while(it.hasNext()){
				SXElement element = it.next();
				String title = element.getAttribute(TenantConfigFormat.A.TenantName);
				String boss = element.getAttribute(TenantConfigFormat.A.BossName);
				String mobile = element.getAttribute(TenantConfigFormat.A.BossMobile);
				TenantInfoImpl tenant =
				        new TenantInfoImpl(GUID.MD5Of(title), title, boss,
				                mobile, "123456");
				Iterator<SXElement> services = element.firstChild(TenantConfigFormat.Services.Name).getChildren(TenantConfigFormat.Services.Service.Name).iterator();
				tenant.setServicesStr(buildServiceXml(services));
				if(acc.findByRECID(tenant.getId()) == null){
					acc.insert(tenant);
				}else{
					acc.update(tenant);
				}
			}
		}
		catch(Throwable t){
			System.err.println("初始化配置租户出错");
			t.printStackTrace();
		}
	}

	private String buildServiceXml(Iterator<SXElement> services){
		DocumentFactory factory = DocumentFactory.getInstance();
		Document doc = factory.createDocument(TenantConfigFormat.Encoding);
		Element root = doc.addElement(TenantConfigFormat.Services.Name);
		while(services.hasNext()){
			SXElement node = services.next();
			Element service = root.addElement(TenantConfigFormat.Services.Service.Name);
			String hid = node.getAttribute(TenantConfigFormat.Services.Service.A.ServiceHostId);
			service.addAttribute(TenantConfigFormat.Services.Service.A.ServiceHostId, hid);
			service.addAttribute(TenantConfigFormat.Services.Service.A.ServerEndDate, node.getAttribute(TenantConfigFormat.Services.Service.A.ServerEndDate));
			service.addAttribute(TenantConfigFormat.Services.Service.A.ProductSerial, node.getAttribute(TenantConfigFormat.Services.Service.A.ProductSerial));
		}
		return doc.asXML();
	}
	
	@Override
	protected void init(Context context) throws Throwable{
		List<TenantInfo> tenantList = context.getList(TenantInfo.class);
		for(int i = 0; i < tenantList.size(); i++){
			TenantInfo tenant = tenantList.get(i);
			GUID tenantId = tenant.getId(); // 暂时用租户名称生成ID
			GUID userId = tenantId; // 暂时用租户ID作为总经理用户ID
			UserInfo userInfo = context.find(UserInfo.class, userId);

			if(userInfo == null){
				CreateUserTask task =
				        new CreateUserTask(tenantId, userId, tenant
				                .getBossMoible(), true);
				context.handle(task);
			}
		}
	}

	@Publish 
	protected class GetTenantByTitle extends OneKeyResourceProvider<String>{

		@Override
		protected String getKey1(TenantInfoImpl keys){
			return keys.getTenantTitle();
		}

	}

	@Publish
	protected class GetTenantById extends OneKeyResourceProvider<GUID>{

		@Override
		protected GUID getKey1(TenantInfoImpl keys){
			return keys.getId();
		}

	}

	@Publish(Mode.SITE_PUBLIC)
	protected final class CreateTenantHandler extends
	        TaskMethodHandler<UpdateTenantTask, UpdateTenantTask.Method>
	{

		protected CreateTenantHandler(){
			super(UpdateTenantTask.Method.Create);
		}

		@Override
		protected void handle(
		        ResourceContext<TenantInfo, TenantInfoImpl, TenantInfoImpl> context,
		        UpdateTenantTask task) throws Throwable
		{
			ORMAccessor<TenantInfoImpl> acc =
			        context.newORMAccessor(qD_GetTenantById);
			SXElementBuilder seb;

			seb = new SXElementBuilder();
			SXElement element = seb.build(task.getConfig()).firstChild();
			String title = element.getAttribute(TenantConfigFormat.A.TenantName);
			String boss = element.getAttribute(TenantConfigFormat.A.BossName);
			String mobile = element.getAttribute(TenantConfigFormat.A.BossMobile);
			TenantInfoImpl tenant = new TenantInfoImpl(task.getId(), title, boss, mobile, "123456");
			Iterator<SXElement> services = element.firstChild(TenantConfigFormat.Services.Name).getChildren(TenantConfigFormat.Services.Service.Name).iterator();
			tenant.setServicesStr(buildServiceXml(services));
			tenant.setServices(getServices(context, tenant));
			if(acc.findByRECID(tenant.getId()) == null){
				acc.insert(tenant);
				context.putResource(tenant);
				CreateUserTask createBossTask =
			        new CreateUserTask(tenant.getId(), tenant.getId(), mobile, true);
				context.handle(createBossTask);
//				for(Service host : tenant.getServices()){
//					HostInfo hostInfo = host.getHostInfo();
//					URL url;
//					if(hostInfo.getDomainEnabled()){
//						url = hostInfo.getDomainURL();
//					}else{
//						url = hostInfo.getURL();
//					}
//					RemoteServiceInvoker remote = context.newEfficientRemoteServiceInvoker(url);
//					try{
//	                    remote.handle(new UpdateSubServerTenantTask(task.getId(), task.getConfig()));
//                    }
//                    catch(NullPointerException e){
//        	            throw new NullPointerException(url+"上的"+host.getProductSerials().getName()+"业务子系统必须实现：TaskMethodHandler<UpdateSubServerTenantTask, UpdateSubServerTenantTask.Method>接口");
//                    }	                
//                }
			}else{
				acc.update(tenant);
				TenantInfoImpl impl = context.modifyResource(tenant.getId());
				impl.setTenantTitle(tenant.getTenantTitle());
				impl.setServices(tenant.getServices());
				impl.setServicesStr(tenant.getServicesStr());
				context.postModifiedResource(impl);
			}
			for(Service host : tenant.getServices()){
				HostInfo hostInfo = host.getHostInfo();
				URL url;
				if(hostInfo.getDomainEnabled()){
					url = hostInfo.getDomainURL();
				}else{
					url = hostInfo.getURL();
				}
				RemoteServiceInvoker remote = context.newEfficientRemoteServiceInvoker(url);
				try{
					remote.handle(new UpdateSubServerTenantTask(task.getId(), task.getConfig()));
				}
				catch(NullPointerException e){
					throw new NullPointerException(url+"上的"+host.getProductSerials().getName()+"业务子系统必须实现：TaskMethodHandler<UpdateSubServerTenantTask, UpdateSubServerTenantTask.Method>接口");
				}	                
			}
		}

	}
	
//	@Publish
//	protected final class GetTenantByName extends OneKeyResourceProvider<String>{
//
//		@Override
//        protected String getKey1(TenantInfoImpl keysHolder){
//	        return keysHolder.getTenantTitle();
//        }
//		
//	}
//	
//	@Publish(Mode.SITE_PUBLIC)
//	protected final class StopTenantHandler extends SimpleTaskMethodHandler<StopTenantTask>{
//
//		@Override
//        protected void handle(
//                ResourceContext<TenantInfo, TenantInfoImpl, TenantInfoImpl> context,
//                StopTenantTask task) throws Throwable
//        {
//	        
//        }
//		
//	}
	

}
