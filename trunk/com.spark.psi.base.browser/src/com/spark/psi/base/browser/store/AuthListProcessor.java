package com.spark.psi.base.browser.store;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.base.organization.entity.RoleInfo;

public class AuthListProcessor<Item> extends PSIListPageProcessor<Item> {
	private final Map<String, List<RoleInfoImpl>> productRoles = new HashMap<String, List<RoleInfoImpl>>();
	private List<AuthInfo> authList = new ArrayList<AuthInfo>();
	@Override
	public String getElementId(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		init(context);
		List<AuthItem> itemList = new ArrayList<AuthItem>();
		for (AuthInfo a : authList) {
			AuthItem item = null;
			for (Auth auth : a.getAuthList()) {
				item = new AuthItem();
				item.setRoleName(a.getName());
				item.setAuthName(auth.getName());
				itemList.add(item);
			}
		}
		return itemList.toArray();
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
					AuthInfo aInfo = null;
//					List<RoleInfo> aRoleList = new ArrayList<RoleInfo>();
					while (roleIt.hasNext()) {
						aInfo = new AuthInfo();
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
						aInfo.setName(name);
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
						aInfo.setAuthList(acls);
						roleList.add(new RoleInfoImpl(code, name, description,
								acls.toArray(new Auth[0]), maskCodes));
						authList.add(aInfo);
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
	
	public class AuthInfo {
		private String name;
		private List<Auth> authList = new ArrayList<Auth>();
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Auth> getAuthList() {
			return authList;
		}
		public void setAuthList(List<Auth> authList) {
			this.authList = authList;
		}
	}
	
	
	private static class RoleInfoImpl implements RoleInfo {

		/**
		 * 角色代码(0-31)
		 */
		private int code;

		/**
		 * 选择该角色后，禁选的其他角色代码
		 */
		private int[] maskCodes;

		/**
		 * 角色名称
		 */
		private String name;

		/**
		 * 角色描述
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
				this.acls = Auth.values(); // 如果角色没有设置权限，则认为具有所有权限
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


	@Override
	protected String getExportFileTitle() {
		return null;
	}
}

class AuthItem {
	private String authName;
	private String roleName;
	
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
