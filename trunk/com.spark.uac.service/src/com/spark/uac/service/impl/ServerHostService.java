/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-16    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.uac.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-16    jiuqi
 * ============================================================*/

package com.spark.uac.service.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.AsyncInfo;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.RemoteServiceInvoker;
import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.entity.TenantInfo;
import com.spark.uac.publish.HostType;
import com.spark.uac.publish.ProductSerialsEnum;
import com.spark.uac.publish.entity.HostInfo;
import com.spark.uac.publish.task.UACServiceListener;
import com.spark.uac.publish.task.UACServiceListener.Item;
import com.spark.uac.service.impl.ServerHostService.HostInfoImpl;

/**
 * <p>TODO 类描述</p>
 *


 *
 
 * @version 2012-4-16
 */
public class ServerHostService extends
        ResourceService<HostInfo, HostInfoImpl, HostInfoImpl>
{
	private final static Map<HostType,HostInfo> map = new HashMap<HostType,HostInfo>();

	private final static long MONITOR_PERIOD = 10 * 1000; //监控间隔，多少秒  
	@StructClass
	class HostInfoImpl implements HostInfo{

		@StructField
		private String id;
		@StructField
		private String host;
		@StructField
		private int port;
		@StructField
		private String domain; //服务器域名
		@StructField
		private boolean domainEnabled; //登录时是否启用域名
		@StructField
		private HostType type;
		@StructField
		private String title;
		
		@StructField
		private String productSerials;

		public HostInfoImpl(){

		}

		public HostInfoImpl(String id, String host, int port, HostType type,
		        String title)
		{
			this.id = id;
			this.host = host;
			this.port = port;
			this.type = type;
			this.title = title;
		}

		public URL getDomainURL(){
			URL url = null;
			if(domain != null){
				try{
					url = new URL("http://".concat(domain));
				}
				catch(MalformedURLException e){
					e.printStackTrace();
				}
			}
			return url;
		}

		public URL getURL(){
			URL url = null;
			if(host != null){
				try{
					if(port == 0){
						//默认80端口
						url = new URL(String.format(URL_TEMPLATE, host, 80));
					}
					else{
						url = new URL(String.format(URL_TEMPLATE, host, port));
					}
				}
				catch(MalformedURLException e){
					e.printStackTrace();
				}
			}
			return url;
		}

		public String getProductSerials(){
        	return productSerials;
        }

		public void setProductSerials(String productSerials){
        	this.productSerials = productSerials;
        }

		public String getDomain(){
			return domain;
		}

		public void setDomain(String domain){
			this.domain = domain;
		}

		public boolean getDomainEnabled(){
			return domainEnabled;
		}

		public void setDomainEnabled(boolean domainEnabled){
			this.domainEnabled = domainEnabled;
		}

		public String getHost(){
			return host;
		}

		public void setHost(String host){
			this.host = host;
		}

		public int getPort(){
			return port;
		}

		public void setPort(int port){
			this.port = port;
		}

		public HostType getType(){
			return type;
		}

		public void setType(HostType type){
			this.type = type;
		}

		public String getTitle(){
			return title;
		}

		public void setTitle(String title){
			this.title = title;
		}

		@Override
		public int hashCode(){
			final int prime = 31;
			int result = 1;
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result + ((host == null) ? 0 : host.hashCode());
			result = prime * result + port;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj){
			if(this == obj) return true;
			if(obj == null) return false;
			if(getClass() != obj.getClass()) return false;
			HostInfoImpl other = (HostInfoImpl)obj;
			if(title == null){
				if(other.title != null) return false;
			}
			else if(!title.equals(other.title)) return false;
			if(host == null){
				if(other.host != null) return false;
			}
			else if(!host.equals(other.host)) return false;
			if(port != other.port) return false;
			if(id == null){
				if(other.id != null) return false;
			}
			else if(!id.equals(other.id)) return false;
			if(type == null){
				if(other.type != null) return false;
			}
			else if(!type.equals(other.type)) return false;
			return true;
		}

		@Override
		public String toString(){
			return "IndentifyHost [description=" + title + ", host=" + host
			        + ", port=" + port + ", recid=" + id + ", type=" + type
			        + "]";
		}

		public String getId(){
			return id;
		}

		public void setId(String id){
			this.id = id;
		}

	}

	class SendRadioTask extends SimpleTask{

	}

	protected ServerHostService(){
		super("服务器主机地址服务", ResourceKind.SINGLETON_IN_SITE);
	}

	@Override
	protected void init(Context context){
		context.asyncHandle(new SendRadioTask(), new AsyncInfo(new Date(),
		        MONITOR_PERIOD));
	}

	/**
	 * 给其他服务器广播地址
	 * 每间隔一段时间发送一次
	 *  void
	 */
	@Publish
	protected final class startSendRadioHandler extends
	        SimpleTaskMethodHandler<SendRadioTask>
	{

		@Override
		protected void handle(
		        ResourceContext<HostInfo, HostInfoImpl, HostInfoImpl> context,
		        SendRadioTask task) throws Throwable
		{
			List<HostInfo> list = context.getList(HostInfo.class);
			List<Item> items = new ArrayList<Item>();
			for(HostInfo hostInfo : list){
	            Item item = new Item();
				URL url;
				if(hostInfo.getDomainEnabled())
					url = hostInfo.getDomainURL();
				else
					url = hostInfo.getURL();
				item.setUrl(url.toString());
				item.setType(hostInfo.getType());
				item.setId(hostInfo.getId());
				item.setProductSerials(hostInfo.getProductSerials());
				items.add(item);
            }
			UACServiceListener listenerTask =
			        new UACServiceListener(items.toArray(new Item[0]));
			for(HostInfo hostInfo : list){ //给各个服务器广播认证中心的地址
				if(hostInfo.getType() == HostType.HOST_TYPE_IDENTIFY) continue;
				try{
	                RemoteServiceInvoker rsi =
	                        context.newEfficientRemoteServiceInvoker(hostInfo.getURL());
	                rsi.handle(listenerTask);
                }
                catch(Exception e){
                }
			}
		}
	}

	/**
	 * 
	 * <p>通过id获得服务器主机资源</p>
	 *
	
	
	 *
	 
	 * @version 2012-4-16
	 */
	protected final class GetHostInfoById extends
	        OneKeyResourceProvider<String>
	{

		@Override
		protected String getKey1(HostInfoImpl keysHolder){
			return keysHolder.getId();
		}

	}

	@Override
	protected void initResources(Context context,
	        ResourceInserter<HostInfo, HostInfoImpl, HostInfoImpl> initializer)
	{
		
		File hostFile =
		        new File(com.jiuqi.dna.core.spi.application.AppUtil
		                .getDefaultApp().getDNARoot().getAbsolutePath()
		                + File.separator + "work" + File.separator + "host.xml");
		
		if(!hostFile.exists()){
			for(HostType type : HostType.values()){	            
				initializer.putResource(getDefaultHostInfo(type));
            }
		}
		else{
			SXElementBuilder seb;
			try{
				seb = new SXElementBuilder();
				SXElement tenantsNode = seb.build(hostFile).firstChild();
				Iterator<SXElement> it =
				        tenantsNode.getChildren("host").iterator();
				while(it.hasNext()){
					SXElement element = it.next();
					HostInfoImpl hostinfo = new HostInfoImpl();
					hostinfo.setId(element.getAttribute("id"));
					hostinfo.setHost(element.getAttribute("host"));
					hostinfo.setPort(Integer.parseInt(element
					        .getAttribute("port")));
					hostinfo.setDomain(element.getAttribute("domain"));
					hostinfo.setDomainEnabled(element.getAttribute(
					        "domainEnabled").equals("true"));
					hostinfo.setType(HostType.getHostTypeByCode(element
					        .getAttribute("type")));
					hostinfo.setTitle(element.getAttribute("title"));
					hostinfo.setProductSerials(element.getAttribute("productSerials"));
					initializer.putResource(hostinfo);
					if(hostinfo.getType()!=HostType.HOST_TYPE_TENANTS)
						map.put(hostinfo.getType(), hostinfo);
				}
			}
			catch(Throwable t){
			}
		}

	}

	private HostInfoImpl getDefaultHostInfo(HostType type){
		String configFilePath =
		        com.jiuqi.dna.core.spi.application.AppUtil.getDefaultApp()
		                .getDNARoot().getAbsolutePath()
		                + File.separator
		                + "work"
		                + File.separator
		                + "dna-server.xml";
		SXElementBuilder seb;
		try{
			seb = new SXElementBuilder();
			SXElement dnaElement =
			        seb.build(new File(configFilePath)).firstChild("dna");
			SXElement httpElement = dnaElement.firstChild("http");
			SXElement listenElement = httpElement.firstChild("listen");
			int port = Integer.parseInt(listenElement.getAttribute("port"));
			HostInfoImpl h = new HostInfoImpl();
			h.setId(Integer.parseInt(type.getCode())+"");
			h.setHost("127.0.0.1");
			h.setPort(port);
			h.setType(type);
			if(type==HostType.HOST_TYPE_TENANTS){
				h.setProductSerials(ProductSerialsEnum.PSI.getCode());
			}
			for(HostType ht : HostType.values()){
				if(ht!=HostType.HOST_TYPE_TENANTS)
					map.put(ht, h);
            }
			return h;
		}
		catch(Throwable t){
			t.printStackTrace();
			return null;
		}

	}
	
	@Publish
	protected final class GetHostInfoByTypeProvider extends OneKeyResultProvider<HostInfo,HostType>{

		@Override
        protected HostInfo provide(
                ResourceContext<HostInfo, HostInfoImpl, HostInfoImpl> context,
                HostType key) throws Throwable
        {
			if(key==HostType.HOST_TYPE_TENANTS) throw new RuntimeException("不能调用此方法获得业务服务器host");
	        return map.get(key);
        }
		
	}
	
	@Publish
	protected final class GetHostInfoProvider extends TwoKeyResultProvider<HostInfo,GUID, ProductSerialsEnum>{

		@Override
        protected HostInfo provide(
                ResourceContext<HostInfo, HostInfoImpl, HostInfoImpl> context,
                GUID key1, ProductSerialsEnum key2) throws Throwable
        {
	        TenantInfo tenant = context.find(TenantInfo.class,key1);
	        if(tenant.getServcie(key2)==null)return null;
	        return tenant.getServcie(key2).getHostInfo();
        }
		
	}

}
