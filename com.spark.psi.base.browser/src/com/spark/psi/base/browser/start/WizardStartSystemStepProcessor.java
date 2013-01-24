package com.spark.psi.base.browser.start;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.account.key.GetInitedPartnerSummaryKey;
import com.spark.psi.publish.base.config.entity.CompanyInfo;
import com.spark.psi.publish.base.config.entity.TenantInfo;
import com.spark.psi.publish.base.goods.key.FindGoodsCountKey;
import com.spark.psi.publish.base.goods.key.FindGoodsTypeCountKey;
import com.spark.psi.publish.base.goods.key.FindGoodsTypeHadSetPropertiesCountKey;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.organization.key.FindEmployeeCountKey;
import com.spark.psi.publish.base.organization.key.FindEmployeeHadRolesCountKey;
import com.spark.psi.publish.base.organization.task.EnabledAllUserTask;
import com.spark.psi.publish.base.partner.key.FindCustomerCountKey;
import com.spark.psi.publish.base.partner.key.FindProviderCountKey;
import com.spark.psi.publish.base.sms.task.SendMsgTask;
import com.spark.psi.publish.base.store.key.FindEnabledStorageCountKey;
import com.spark.psi.publish.base.store.key.FindStorageCountKey;

/**
 * <p>启用系统界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-11
 */

public class WizardStartSystemStepProcessor extends WizardBaseStepProcessor{

	/**
	 * 控件ID
	 */
	public final static String ID_Label_AddEmployeeNum = "Label_AddEmployeeNum";
	public final static String ID_Label_HadUserRoleNum = "Label_HadUserRoleNum";
	public final static String ID_Label_AddCustomerNum = "Label_AddCustomerNum";
	public final static String ID_Label_HadInitCustomerNum = "Label_HadInitCustomerNum";
	public final static String ID_Label_AddProviderNum = "Label_AddProviderNum";
	public final static String ID_Label_HadInitProviderNum = "Label_HadInitProviderNum";
	public final static String ID_Label_AddStoreNum = "Label_AddStoreNum";
	public final static String ID_Label_HadInitStoreNum = "Label_HadInitStoreNum";
	public final static String ID_Label_AddGoodsTypeNum = "Label_AddGoodsTypeNum";
	public final static String ID_Label_HadSetGoodsTypeNum = "Label_HadSetGoodsTypeNum";
	public final static String ID_Label_AddGoodsNum = "Label_AddGoodsNum";

	/**
	 * 组件
	 */
	private Label addEmployeeNum;
	private Label HadUserRoleNum;
	private Label addCustomerNum;
	private Label hadInitCustomerNum;
	private Label addProviderNum;
	private Label hadInitProviderNum;
	private Label addStoreNum;
	private Label hadInitStoreNum;
	private Label addGoodsTypeNum;
	private Label hadSetGoodsTypeNum;
	private Label addGoodsNum;

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation context){
		super.process(context);
		// 创建组件
		createUIComponent();
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		addEmployeeNum.setText(context.get(Integer.class, new FindEmployeeCountKey()) + "");
		HadUserRoleNum.setText(context.get(Integer.class, new FindEmployeeHadRolesCountKey()) + "");
		addCustomerNum.setText(context.get(Integer.class, new FindCustomerCountKey()) + "");
		hadInitCustomerNum.setText(context.get(Integer.class, new GetInitedPartnerSummaryKey(PartnerType.Customer))
		        + "");
		addProviderNum.setText(context.get(Integer.class, new FindProviderCountKey()) + "");
		hadInitProviderNum.setText(context.get(Integer.class, new GetInitedPartnerSummaryKey(PartnerType.Supplier))
		        + "");
		addStoreNum.setText(context.get(Integer.class, new FindStorageCountKey()) + "");
		hadInitStoreNum.setText(context.get(Integer.class, new FindEnabledStorageCountKey()) + "");
		addGoodsTypeNum.setText(context.get(Integer.class, new FindGoodsTypeCountKey()) + "");
		hadSetGoodsTypeNum.setText(context.get(Integer.class, new FindGoodsTypeHadSetPropertiesCountKey()) + "");
		addGoodsNum.setText(context.get(Integer.class, new FindGoodsCountKey()) + "");
	}

	/**
	 * 创建组件
	 */
	private void createUIComponent(){
		addEmployeeNum = this.createControl(ID_Label_AddEmployeeNum, Label.class);
		HadUserRoleNum = this.createControl(ID_Label_HadUserRoleNum, Label.class);
		addCustomerNum = this.createControl(ID_Label_AddCustomerNum, Label.class);
		hadInitCustomerNum = this.createControl(ID_Label_HadInitCustomerNum, Label.class);
		addProviderNum = this.createControl(ID_Label_AddProviderNum, Label.class);
		hadInitProviderNum = this.createControl(ID_Label_HadInitProviderNum, Label.class);
		addStoreNum = this.createControl(ID_Label_AddStoreNum, Label.class);
		hadInitStoreNum = this.createControl(ID_Label_HadInitStoreNum, Label.class);
		addGoodsTypeNum = this.createControl(ID_Label_AddGoodsTypeNum, Label.class);
		hadSetGoodsTypeNum = this.createControl(ID_Label_HadSetGoodsTypeNum, Label.class);
		addGoodsNum = this.createControl(ID_Label_AddGoodsNum, Label.class);
	}

	/**
	 * 操作执行
	 */
	@Override
	protected boolean operateExecute(){
		//将所有用户置为可用
		getContext().handle(new EnabledAllUserTask());
		//发送短信
		EmployeeInfo employeeInfo = getContext().get(EmployeeInfo.class, getContext().get(TenantInfo.class).getId());
		CompanyInfo companyInfo = getContext().get(CompanyInfo.class);
		getContext().handle(
		        new SendMsgTask(employeeInfo.getMobileNo(), companyInfo.getCompanyShortName() + "企业管理系统已经启用！",
		                getContext().find(LoginInfo.class).getTenantId()));
		hint("系统启用成功！");
		return true;
	}

}
