package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * <p>启用系统界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-11
 */

public class WizardStartSystemStepRender extends WizardBaseStepRender{
	/**
	 * 标题
	 */
	@Override
	protected String getTitle(){
		return "启用系统";
	}
	
	/**
	 * 操作按钮名称
	 */
	protected String getButtonTitle(){
		return "启用系统";
	}

	/**
	 * 内容信息区
	 */
	@Override
	protected void addInfoArea(Composite infoArea){
		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 10;
		layout.marginLeft = 20;
		infoArea.setLayout(layout);
		//企业信息
		Composite compnayInfoArea = new Composite(infoArea);
		compnayInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createCompanyInfo(compnayInfoArea);
		//员工和用户
		Composite employeeInfoArea = new Composite(infoArea);
		employeeInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createEmpoyeeInfo(employeeInfoArea);
		//客户信息
		Composite customerInfoArea = new Composite(infoArea);
		customerInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createCustomerInfo(customerInfoArea);
		//供应商信息
		Composite providerInfoArea = new Composite(infoArea);
		providerInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createProviderInfo(providerInfoArea);
		//仓库信息
		Composite storeInfoArea = new Composite(infoArea);
		storeInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createStoreInfo(storeInfoArea);
		//商品分类
		Composite goodsTypeArea = new Composite(infoArea);
		goodsTypeArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createGoodsTypeInfo(goodsTypeArea);
		//商品信息
		Composite goodsInfoArea = new Composite(infoArea);
		goodsInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createGoodsInfo(goodsInfoArea);
	}

	/**
     * 企业信息
     *
     *@param compnayInfoArea
     */
    private void createCompanyInfo(Composite compnayInfoArea){
    	compnayInfoArea.setLayout(new GridLayout(4));
	    //
		createLabelIcon(compnayInfoArea);
		//
		new Label(compnayInfoArea).setText("已设置企业信息！系统启用后，可通过");
		//
		Label companyConfiguration = new Label(compnayInfoArea);
		companyConfiguration.setText("企业设置");
		companyConfiguration.setFont(getFont());
		//
		new Label(compnayInfoArea).setText("进行维护！");
    }
    
    /**
     * 员工和用户
     *
     *@param EmployeeInfoArea
     */
    private void createEmpoyeeInfo(Composite employeeInfoArea){
    	employeeInfoArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(employeeInfoArea);
    	//
    	new Label(employeeInfoArea).setText("已添加");
    	//
    	Label addEmployeeNum = new Label(employeeInfoArea);
    	addEmployeeNum.setID(WizardStartSystemStepProcessor.ID_Label_AddEmployeeNum);
    	//
    	new Label(employeeInfoArea).setText("名员工，其中");
    	//
    	Label hadUserRoleNum = new Label(employeeInfoArea);
    	hadUserRoleNum.setID(WizardStartSystemStepProcessor.ID_Label_HadUserRoleNum);
    	//
    	new Label(employeeInfoArea).setText("名员工被赋予了用户权限！系统启用后，可通过");
    	//
    	Label companyConfiguration = new Label(employeeInfoArea);
    	companyConfiguration.setText("员工管理");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(employeeInfoArea).setText("进行维护！");
    }
    
    /**
     * 客户信息
     *
     *@param customerInfoArea
     */
    private void createCustomerInfo(Composite customerInfoArea){
    	customerInfoArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(customerInfoArea);
    	//
    	new Label(customerInfoArea).setText("已添加");
    	//
    	Label addCustomerNum = new Label(customerInfoArea);
    	addCustomerNum.setID(WizardStartSystemStepProcessor.ID_Label_AddCustomerNum);
    	//
    	new Label(customerInfoArea).setText("个客户，其中");
    	//
    	Label hadInitCustomerNum = new Label(customerInfoArea);
    	hadInitCustomerNum.setID(WizardStartSystemStepProcessor.ID_Label_HadInitCustomerNum);
    	//
    	new Label(customerInfoArea).setText("个客户已设置往来款初始值！系统启用后，可通过");
    	//
    	Label companyConfiguration = new Label(customerInfoArea);
    	companyConfiguration.setText("往来款管理");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(customerInfoArea).setText("继续进行初始值设置！");
    }
    
    /**
     * 供应商信息
     *
     *@param providerInfoArea
     */
    private void createProviderInfo(Composite providerInfoArea){
    	providerInfoArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(providerInfoArea);
    	//
    	new Label(providerInfoArea).setText("已添加");
    	//
    	Label addProviderNum = new Label(providerInfoArea);
    	addProviderNum.setID(WizardStartSystemStepProcessor.ID_Label_AddProviderNum);
    	//
    	new Label(providerInfoArea).setText("个供应商，其中");
    	//
    	Label hadInitProviderNum = new Label(providerInfoArea);
    	hadInitProviderNum.setID(WizardStartSystemStepProcessor.ID_Label_HadInitProviderNum);
    	//
    	new Label(providerInfoArea).setText("个供应商已设置往来款初始值！系统启用后，可通过");
    	//
    	Label companyConfiguration = new Label(providerInfoArea);
    	companyConfiguration.setText("往来款管理");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(providerInfoArea).setText("进行维护！");
    }
    
    /**
     * 仓库信息
     *
     *@param storeInfoArea
     */
    private void createStoreInfo(Composite storeInfoArea){
    	storeInfoArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(storeInfoArea);
    	//
    	new Label(storeInfoArea).setText("已添加");
    	//
    	Label addStoreNum = new Label(storeInfoArea);
    	addStoreNum.setID(WizardStartSystemStepProcessor.ID_Label_AddStoreNum);
    	//
    	new Label(storeInfoArea).setText("个仓库，其中");
    	//
    	Label hadInitStoreNum = new Label(storeInfoArea);
    	hadInitStoreNum.setID(WizardStartSystemStepProcessor.ID_Label_HadInitStoreNum);
    	//
    	new Label(storeInfoArea).setText("个仓库完成商品库存初始化操作！系统启用后，可通过");
    	//
    	Label companyConfiguration = new Label(storeInfoArea);
    	companyConfiguration.setText("仓库设置");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(storeInfoArea).setText("进行维护！");
    }
    
    /**
     * 商品分类
     *
     *@param goodsTypeArea
     */
    private void createGoodsTypeInfo(Composite goodsTypeArea){
    	goodsTypeArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(goodsTypeArea);
    	//
    	new Label(goodsTypeArea).setText("已添加");
    	//
    	Label addGoodsTypeNum = new Label(goodsTypeArea);
    	addGoodsTypeNum.setID(WizardStartSystemStepProcessor.ID_Label_AddGoodsTypeNum);
    	//
    	new Label(goodsTypeArea).setText("个商品分类，其中");
    	//
    	Label hadSetGoodsTypeNum = new Label(goodsTypeArea);
    	hadSetGoodsTypeNum.setID(WizardStartSystemStepProcessor.ID_Label_HadSetGoodsTypeNum);
    	//
    	new Label(goodsTypeArea).setText("个商品分类被设置了属性！系统启用后，可通过");
    	//
    	Label companyConfiguration = new Label(goodsTypeArea);
    	companyConfiguration.setText("商品管理");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(goodsTypeArea).setText("进行维护！");
    }
    
    /**
     * 商品信息
     *
     *@param goodsInfoArea
     */
    private void createGoodsInfo(Composite goodsInfoArea){
    	goodsInfoArea.setLayout(new GridLayout(6));
    	//
    	createLabelIcon(goodsInfoArea);
    	//
    	new Label(goodsInfoArea).setText("已添加");
    	//
    	Label addGoodsNum = new Label(goodsInfoArea);
    	addGoodsNum.setID(WizardStartSystemStepProcessor.ID_Label_AddGoodsNum);
    	//
    	new Label(goodsInfoArea).setText("个商品！系统启用后，可通过");
    	//
    	Label companyConfiguration = new Label(goodsInfoArea);
    	companyConfiguration.setText("商品管理");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(goodsInfoArea).setText("进行维护！");
    }
	
	/**
	 * 图标
	 */
	private Label createLabelIcon(Composite container){
		Label label = new Label(container);
		label.setImage(BaseImages.getImage("images/wizard/wizard_start_row_icon.png"));
		return label;
	}
	
	/**
	 * 获得字体
	 */
	private Font getFont(){
		return new Font(9, "宋体", JWT.FONT_STYLE_BOLD);
	}

	/**
	 * 底部左边按钮区
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){
		GridLayout layout = new GridLayout();
		layout.marginLeft = 20;
		leftButtonArea.setLayout(layout);
		//说明
		Label remarkLabel = new Label(leftButtonArea, JWT.MIDDLE);
		remarkLabel.setText("系统启用之后，您可通过相关功能模块继续对以上信息进行管理。");
		remarkLabel.setLayoutData(GridData.INS_FILL_VERTICAL);
	}
}
