package com.spark.psi.base.internal.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.util.StringUtils;
import com.spark.psi.base.Tenant;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.base.organization.entity.RoleInfo;

public class ProductService extends Service {

	private final Map<String, List<RoleInfoImpl>> productRoles = new HashMap<String, List<RoleInfoImpl>>();

	protected ProductService() {
		super("com.spark.psi.base.internal.service.AuthService");
	}

	protected void init(Context context) {
		InputStream is = getClass().getResourceAsStream("Product.xml");
		SXElementBuilder seb;
		try {
			seb = new SXElementBuilder();
			SXElement tenantsNode = seb.build(is).firstChild();
			Iterator<SXElement> it = tenantsNode.getChildren("product")
					.iterator();
			while (it.hasNext()) {
				SXElement element = it.next();
				String productCode = element.getAttribute("id").toUpperCase();
				List<RoleInfoImpl> roleList = new ArrayList<RoleInfoImpl>();
				productRoles.put(productCode, roleList);
				SXElement authInfoElement = element.firstChild("auth-info");
				if (authInfoElement != null) {
					Iterator<SXElement> roleIt = authInfoElement.getChildren(
							"role").iterator();
					while (roleIt.hasNext()) {
						SXElement roleElement = roleIt.next();
						String code = roleElement.getAttribute("code");
						String[] maskCodes = StringUtils.split(
								roleElement.getAttribute("mask"), ",");
						String name = roleElement.getAttribute("name");
						String description = roleElement
								.getAttribute("description");
						List<Auth> acls = new ArrayList<Auth>();
						Iterator<SXElement> authIt = roleElement.getChildren(
								"auth").iterator();
						while (authIt.hasNext()) {
							try {
								Auth auth = Auth.getAuthByCode(authIt.next()
										.getText());
								if (auth != null) {
									acls.add(auth);
								}
							} catch (Throwable t) {
								t.printStackTrace();
							}
						}
						roleList.add(new RoleInfoImpl(code, name, description,
								acls.toArray(new Auth[0]), maskCodes));
					}
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * ��ѯ��ǰϵͳ��ɫ�б�
	 */
	@Publish
	protected class RoleInfoListQuery extends ResultListProvider<RoleInfo> {

		@Override
		protected void provide(Context context, List<RoleInfo> resultList)
				throws Throwable {
			String productCode = context.find(Tenant.class).getProduct().toUpperCase(); // XXX��Ӧ�ò�ѯ��ǰ��¼�û��⻧��������Ĳ�Ʒ�汾��Ϣ
			List<RoleInfoImpl> roleList = productRoles.get(productCode);
			if (roleList != null) {
				resultList.addAll(roleList);
			}
		}
	}

	/**
	 * ��ѯָ����Ʒ����ؽ�ɫ���ϵ�Ȩ���б�
	 */
	@Publish
	protected class AuthListQueryById extends
			TwoKeyResultListProvider<Auth, GUID, Integer> {

		@Override
		protected void provide(Context context, GUID tenantId, Integer roles,
				List<Auth> resultList) throws Throwable {
			Tenant tenant = context.find(Tenant.class, tenantId);
			String productCode = tenant.getProduct(); // XXX����Ҫ����employeeId��ѯ�⻧��Ϣ��ȷ����Ʒ�汾����
			resultList.addAll(getAclsByRoles(productCode, roles));
		}
	}
	
	@Publish
	protected class AuthListQueryByRole extends 
			TwoKeyResultListProvider<Auth, GUID, RoleInfoImpl>{

		@Override
        protected void provide(Context context, GUID tenantId, RoleInfoImpl role,
                List<Auth> resultList) throws Throwable
        {
			Tenant tenant = context.find(Tenant.class, tenantId);
			String procuctCode = tenant.getProduct(); // XXX����Ҫ����employeeId��ѯ�⻧��Ϣ��ȷ����Ʒ�汾����
			List<RoleInfoImpl> roleList = productRoles.get(procuctCode.toUpperCase());
			for(RoleInfoImpl roleInfoImpl : roleList){
				if(roleInfoImpl.getCode()==role.getCode())
					resultList.addAll(Arrays.asList(roleInfoImpl.getAcls()));
            }
        }
		
	}

	/**
	 * �õ�ָ����Ʒ��ָ����ɫ��Ȩ�޼���
	 * 
	 * @param procuctCode
	 * @param roles
	 * @return
	 */
	private List<Auth> getAclsByRoles(String procuctCode, int roles) {
		List<Auth> allAcls = new ArrayList<Auth>();
		List<RoleInfoImpl> roleList = productRoles.get(procuctCode.toUpperCase());
		if (roleList != null) {
			for (RoleInfo role : roleList) {
				if ((roles & (1 << role.getCode())) != 0) {
					RoleInfoImpl roleImpl = (RoleInfoImpl) role;
					Auth[] acls = roleImpl.getAcls();
					for (int i = 0; i < acls.length; i++) {
						allAcls.add(acls[i]);
					}
				}
			}
		}
		return allAcls;
	}

	/**
	 * 
	 */
	private static class RoleInfoImpl implements RoleInfo {

		/**
		 * ��ɫ����(0-31)
		 */
		private int code;

		/**
		 * ѡ��ý�ɫ�󣬽�ѡ��������ɫ����
		 */
		private int[] maskCodes;

		/**
		 * ��ɫ����
		 */
		private String name;

		/**
		 * ��ɫ����
		 */
		private String description;

		/**
		 * 
		 */
		private Auth[] acls;

		/**
		 * 
		 * @param code
		 * @param maskCodes
		 * @param name
		 * @param description
		 */
		public RoleInfoImpl(String code, String name, String description,
				Auth[] acls, String... maskCodes) {
			this.code = Integer.parseInt(code);
			this.name = name;
			this.description = description;
			this.maskCodes = new int[maskCodes.length];
			for (int i = 0; i < this.maskCodes.length; i++) {
				this.maskCodes[i] = Integer.parseInt(maskCodes[i]);
			}
			this.acls = acls;
			if (this.acls == null || this.acls.length == 0) {
				this.acls = Auth.values(); // �����ɫû������Ȩ�ޣ�����Ϊ��������Ȩ��
			}
		}

		public int getCode() {
			return this.code;
		}

		public int[] getMaskCodes() {
			return this.maskCodes;
		}

		public String getName() {
			return this.name;
		}

		public String getDescription() {
			return this.description;
		}

		public Auth[] getAcls() {
			return this.acls;
		}

	}
}
