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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceKind;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.util.StringUtils;
import com.spark.psi.base.Employee;
import com.spark.psi.base.internal.service.UserHeadService.UserHeadImpl;
import com.spark.psi.publish.base.config.entity.UserHeadInfo;
/**
 * <p>TODO 类描述</p>
 *


 *
 
 * @version 2012-4-19
 */

public class UserHeadService extends ResourceService<UserHeadInfo,UserHeadImpl,UserHeadImpl>{
	
	private static final String Suffix = ".png"; 
	
	private UserHeadInfo defaultHead;
	
	@StructClass
	class UserHeadImpl implements UserHeadInfo {

		GUID id;
		
		byte[] img;
		
		String imgClass;

		public GUID getId(){
        	return id;
        }

		public void setId(GUID id){
        	this.id = id;
        }

		public byte[] getImg(){
        	return img;
        }

		public void setImg(byte[] img){
        	this.img = img;
        }

		public String getImgClass(){
        	return imgClass;
        }

		public void setImgClass(String imgClass){
        	this.imgClass = imgClass;
        }

		
	}
	
	@Override
	protected void init(Context context){
		context.getList(UserHeadInfo.class);
	}
	
	protected UserHeadService(){
	    super("用户头像服务",ResourceKind.SINGLETON_IN_CLUSTER);
    }

	@Override
	protected void initResources(Context context,
			ResourceInserter<UserHeadInfo,UserHeadImpl,UserHeadImpl> init){
		InputStream is = getClass().getResourceAsStream("UserHead.xml");
		SXElementBuilder seb;
		try {
			seb = new SXElementBuilder();
			SXElement headNode = seb.build(is).firstChild();
			Iterator<SXElement> it = headNode.getChildren("head-type").iterator();
			while(it.hasNext()){
				SXElement element = it.next();
				String type = element.getAttribute("name");
				Iterator<SXElement> headIt = element.getChildren("head").iterator();
				while(headIt.hasNext()){
					SXElement headElement = headIt.next();
					String src = headElement.getAttribute("src");
					UserHeadImpl entity = new UserHeadImpl();
					entity.setId(GUID.MD5Of(src));
					entity.setImgClass(type);
					entity.setImg(getImg(src));
					if(StringUtils.isNotEmpty(headElement.getAttribute("isDefault"))){
						defaultHead = entity;
					}
					init.putResource(entity);
				}
			}
		}catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
	}

	private byte[] getImg(String src) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream in = null;
		byte[] buffer = new byte[1024];
		int bytesRead = 0;

		try {
			in =
		        getClass().getResourceAsStream("/res/head/" + src + Suffix);
			if (in == null) {
				throw new IllegalArgumentException("指定的资源不存在");
			}
			do {
				bytesRead = in.read(buffer);
				if (bytesRead > 0) {
					baos.write(buffer, 0, bytesRead);
				}
			} while (bytesRead > 0);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
				}
			}
		}
		return baos.toByteArray();
	}

	final class GetUserHeadInfoByIdProvider extends OneKeyResourceProvider<GUID>{

		@Override
        protected GUID getKey1(UserHeadImpl keysHolder){
	        return keysHolder.getId();
        }
		
	}
	
	@Publish
	protected final class GetUserHeadInfoProvider extends ResultProvider<UserHeadInfo>{

		@Override
        protected UserHeadInfo provide(
                ResourceContext<UserHeadInfo, UserHeadImpl, UserHeadImpl> context)
                throws Throwable
        {
			Employee emp = context.find(Employee.class);
			if(emp.getLogo()!=null){
				return context.find(UserHeadInfo.class,emp.getLogo());
			}else{
				return defaultHead;
			}
        }
		
	}
	
}
