package com.spark.psi.base.browser.config;

import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.PageProcessor;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.organization.entity.RoleInfo;

public class AuthConfigProcessor extends PageProcessor {

	public final static String ID_Label_NAME = "Label_Name";// 姓名
	public final static String ID_COMPOSITE_ROLE_OPTIONS = "Composite_Role_Options";// 权限选项
	public final static String ID_BUTTON_CONFIRM = "Button_Confirm";// 确定按钮
	public final static String RoleButtonID_Prefix = "Role_";// 角色check按钮的ID前缀

	private List<RoleInfo> roleList;

	private String employeeNames;

	private int rolesValue;

	GUID tenantId;

	public void init(Situation context) {
		employeeNames = (String) this.getArgument();
		rolesValue = (Integer) this.getArgument2();
		tenantId = context.find(LoginInfo.class).getTenantId();
	}

	@Override
	public void process(Situation context) {
		Label lblName = this.createControl(ID_Label_NAME, Label.class, JWT.NONE);
		lblName.setText(employeeNames);

		// int rolesValue = this.getArgument();
		// int rolesValue = 1714;//
		// 1706;//0x7FE;//0x7FE;//二进制表示为：00000000000000000000011111111111
		//
		roleList = getContext().getList(RoleInfo.class);
		for (int i = 0; i < roleList.size(); i++) {
			RoleInfo roleInfo = roleList.get(i);
			Button roleButton = findRoleButton(roleInfo.getCode());
			roleButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exclude();
				}
			});
			if (isCompany() && isManager(roleList.get(i))) {
				roleButton.setEnabled(false);
			}
		}

		initData(rolesValue);
		//

		//
		this.createControl(ID_BUTTON_CONFIRM, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuffer sb = new StringBuffer();
				int roles = 0;
				for (RoleInfo roleInfo : roleList) {// 根据选择，禁用对应的复选
					Button roleButton = createControl(RoleButtonID_Prefix + roleInfo.getCode(), Button.class);
					if (roleButton.getSelection()) {// 判断控件是否选中
						sb.append(roleInfo.getName());
						sb.append(',');
						roles |= (1 << roleInfo.getCode());
					}
				}
				if (sb.length() > 0) {
					sb.deleteCharAt(sb.length() - 1);
				}
				// System.out.println(roles + ":" + sb.toString());
				getContext().bubbleMessage(new MsgResponse(true, roles, sb.toString()));// 返回值，是否关闭窗口
			}
		});
	}

	private Button findRoleButton(int roleCode) {
		return this.createControl(RoleButtonID_Prefix + roleCode, Button.class);
	}

	private boolean isCompany() {
		String[] depts = (String[]) getArgument3();
		for (String string : depts) {
			if (string.equals(tenantId.toString()))
				return true;
		}
		return false;
	}

	/**
	 * 初始化按钮选择情况
	 */
	private void initData(int rolesValue) {
		// code,0,1,2,3,4,5,6,7,8,9,10
		if (rolesValue < 1)
			return; // 周利均 edit , rolesValue 存在-1的情况，-1为重来没有设置过角色
		for (RoleInfo role : roleList) {
			if (((1 << role.getCode()) & rolesValue) != 0) {
				Button roleButton = findRoleButton(role.getCode());
				roleButton.setSelection(true);// 设置为选中
			}
		}
	}

	/*
	 * 控制按钮的选择关联
	 */
	private void exclude() {
		//
		for (RoleInfo role : roleList) {
			Button roleButton = findRoleButton(role.getCode());
			roleButton.setEnabled(true);
		}
		//
		for (RoleInfo role : roleList) {// 根据选择，禁用对应的复选
			Button roleButton = findRoleButton(role.getCode());
			if (roleButton.getSelection()) {// 判断控件是否选中
				// 选择该角色后，禁选的其他角色代码
				int[] maskCodes = role.getMaskCodes();
				for (int maskCode : maskCodes) {
					Button maskButton = findRoleButton(maskCode);
					if (null == maskButton) {
						continue;
					}
					maskButton.setEnabled(false);
				}
			}
			if (isCompany() && isManager(role)) {
				roleButton.setEnabled(false);
			}
		}
	}

	private boolean isManager(RoleInfo roles) {
		List<Auth> list = getContext().getList(Auth.class, tenantId, roles);
		if (list.contains(Auth.Boss) || list.contains(Auth.Assistant)) { // 如果是总经理或这助理
																			// 返回false
			return false;
		}
		if (list.contains(Auth.AccountManager) || list.contains(Auth.SalesManager)
				|| list.contains(Auth.PurchaseManager) || list.contains(Auth.StoreKeeperManager)) {
			return true;
		}
		return false;
	}

}
