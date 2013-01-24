package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * <p>����ϵͳ������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-11
 */

public class WizardStartSystemStepRender extends WizardBaseStepRender{
	/**
	 * ����
	 */
	@Override
	protected String getTitle(){
		return "����ϵͳ";
	}
	
	/**
	 * ������ť����
	 */
	protected String getButtonTitle(){
		return "����ϵͳ";
	}

	/**
	 * ������Ϣ��
	 */
	@Override
	protected void addInfoArea(Composite infoArea){
		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 10;
		layout.marginLeft = 20;
		infoArea.setLayout(layout);
		//��ҵ��Ϣ
		Composite compnayInfoArea = new Composite(infoArea);
		compnayInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createCompanyInfo(compnayInfoArea);
		//Ա�����û�
		Composite employeeInfoArea = new Composite(infoArea);
		employeeInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createEmpoyeeInfo(employeeInfoArea);
		//�ͻ���Ϣ
		Composite customerInfoArea = new Composite(infoArea);
		customerInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createCustomerInfo(customerInfoArea);
		//��Ӧ����Ϣ
		Composite providerInfoArea = new Composite(infoArea);
		providerInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createProviderInfo(providerInfoArea);
		//�ֿ���Ϣ
		Composite storeInfoArea = new Composite(infoArea);
		storeInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createStoreInfo(storeInfoArea);
		//��Ʒ����
		Composite goodsTypeArea = new Composite(infoArea);
		goodsTypeArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createGoodsTypeInfo(goodsTypeArea);
		//��Ʒ��Ϣ
		Composite goodsInfoArea = new Composite(infoArea);
		goodsInfoArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		createGoodsInfo(goodsInfoArea);
	}

	/**
     * ��ҵ��Ϣ
     *
     *@param compnayInfoArea
     */
    private void createCompanyInfo(Composite compnayInfoArea){
    	compnayInfoArea.setLayout(new GridLayout(4));
	    //
		createLabelIcon(compnayInfoArea);
		//
		new Label(compnayInfoArea).setText("��������ҵ��Ϣ��ϵͳ���ú󣬿�ͨ��");
		//
		Label companyConfiguration = new Label(compnayInfoArea);
		companyConfiguration.setText("��ҵ����");
		companyConfiguration.setFont(getFont());
		//
		new Label(compnayInfoArea).setText("����ά����");
    }
    
    /**
     * Ա�����û�
     *
     *@param EmployeeInfoArea
     */
    private void createEmpoyeeInfo(Composite employeeInfoArea){
    	employeeInfoArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(employeeInfoArea);
    	//
    	new Label(employeeInfoArea).setText("�����");
    	//
    	Label addEmployeeNum = new Label(employeeInfoArea);
    	addEmployeeNum.setID(WizardStartSystemStepProcessor.ID_Label_AddEmployeeNum);
    	//
    	new Label(employeeInfoArea).setText("��Ա��������");
    	//
    	Label hadUserRoleNum = new Label(employeeInfoArea);
    	hadUserRoleNum.setID(WizardStartSystemStepProcessor.ID_Label_HadUserRoleNum);
    	//
    	new Label(employeeInfoArea).setText("��Ա�����������û�Ȩ�ޣ�ϵͳ���ú󣬿�ͨ��");
    	//
    	Label companyConfiguration = new Label(employeeInfoArea);
    	companyConfiguration.setText("Ա������");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(employeeInfoArea).setText("����ά����");
    }
    
    /**
     * �ͻ���Ϣ
     *
     *@param customerInfoArea
     */
    private void createCustomerInfo(Composite customerInfoArea){
    	customerInfoArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(customerInfoArea);
    	//
    	new Label(customerInfoArea).setText("�����");
    	//
    	Label addCustomerNum = new Label(customerInfoArea);
    	addCustomerNum.setID(WizardStartSystemStepProcessor.ID_Label_AddCustomerNum);
    	//
    	new Label(customerInfoArea).setText("���ͻ�������");
    	//
    	Label hadInitCustomerNum = new Label(customerInfoArea);
    	hadInitCustomerNum.setID(WizardStartSystemStepProcessor.ID_Label_HadInitCustomerNum);
    	//
    	new Label(customerInfoArea).setText("���ͻ��������������ʼֵ��ϵͳ���ú󣬿�ͨ��");
    	//
    	Label companyConfiguration = new Label(customerInfoArea);
    	companyConfiguration.setText("���������");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(customerInfoArea).setText("�������г�ʼֵ���ã�");
    }
    
    /**
     * ��Ӧ����Ϣ
     *
     *@param providerInfoArea
     */
    private void createProviderInfo(Composite providerInfoArea){
    	providerInfoArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(providerInfoArea);
    	//
    	new Label(providerInfoArea).setText("�����");
    	//
    	Label addProviderNum = new Label(providerInfoArea);
    	addProviderNum.setID(WizardStartSystemStepProcessor.ID_Label_AddProviderNum);
    	//
    	new Label(providerInfoArea).setText("����Ӧ�̣�����");
    	//
    	Label hadInitProviderNum = new Label(providerInfoArea);
    	hadInitProviderNum.setID(WizardStartSystemStepProcessor.ID_Label_HadInitProviderNum);
    	//
    	new Label(providerInfoArea).setText("����Ӧ���������������ʼֵ��ϵͳ���ú󣬿�ͨ��");
    	//
    	Label companyConfiguration = new Label(providerInfoArea);
    	companyConfiguration.setText("���������");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(providerInfoArea).setText("����ά����");
    }
    
    /**
     * �ֿ���Ϣ
     *
     *@param storeInfoArea
     */
    private void createStoreInfo(Composite storeInfoArea){
    	storeInfoArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(storeInfoArea);
    	//
    	new Label(storeInfoArea).setText("�����");
    	//
    	Label addStoreNum = new Label(storeInfoArea);
    	addStoreNum.setID(WizardStartSystemStepProcessor.ID_Label_AddStoreNum);
    	//
    	new Label(storeInfoArea).setText("���ֿ⣬����");
    	//
    	Label hadInitStoreNum = new Label(storeInfoArea);
    	hadInitStoreNum.setID(WizardStartSystemStepProcessor.ID_Label_HadInitStoreNum);
    	//
    	new Label(storeInfoArea).setText("���ֿ������Ʒ����ʼ��������ϵͳ���ú󣬿�ͨ��");
    	//
    	Label companyConfiguration = new Label(storeInfoArea);
    	companyConfiguration.setText("�ֿ�����");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(storeInfoArea).setText("����ά����");
    }
    
    /**
     * ��Ʒ����
     *
     *@param goodsTypeArea
     */
    private void createGoodsTypeInfo(Composite goodsTypeArea){
    	goodsTypeArea.setLayout(new GridLayout(8));
    	//
    	createLabelIcon(goodsTypeArea);
    	//
    	new Label(goodsTypeArea).setText("�����");
    	//
    	Label addGoodsTypeNum = new Label(goodsTypeArea);
    	addGoodsTypeNum.setID(WizardStartSystemStepProcessor.ID_Label_AddGoodsTypeNum);
    	//
    	new Label(goodsTypeArea).setText("����Ʒ���࣬����");
    	//
    	Label hadSetGoodsTypeNum = new Label(goodsTypeArea);
    	hadSetGoodsTypeNum.setID(WizardStartSystemStepProcessor.ID_Label_HadSetGoodsTypeNum);
    	//
    	new Label(goodsTypeArea).setText("����Ʒ���౻���������ԣ�ϵͳ���ú󣬿�ͨ��");
    	//
    	Label companyConfiguration = new Label(goodsTypeArea);
    	companyConfiguration.setText("��Ʒ����");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(goodsTypeArea).setText("����ά����");
    }
    
    /**
     * ��Ʒ��Ϣ
     *
     *@param goodsInfoArea
     */
    private void createGoodsInfo(Composite goodsInfoArea){
    	goodsInfoArea.setLayout(new GridLayout(6));
    	//
    	createLabelIcon(goodsInfoArea);
    	//
    	new Label(goodsInfoArea).setText("�����");
    	//
    	Label addGoodsNum = new Label(goodsInfoArea);
    	addGoodsNum.setID(WizardStartSystemStepProcessor.ID_Label_AddGoodsNum);
    	//
    	new Label(goodsInfoArea).setText("����Ʒ��ϵͳ���ú󣬿�ͨ��");
    	//
    	Label companyConfiguration = new Label(goodsInfoArea);
    	companyConfiguration.setText("��Ʒ����");
    	companyConfiguration.setFont(getFont());
    	//
    	new Label(goodsInfoArea).setText("����ά����");
    }
	
	/**
	 * ͼ��
	 */
	private Label createLabelIcon(Composite container){
		Label label = new Label(container);
		label.setImage(BaseImages.getImage("images/wizard/wizard_start_row_icon.png"));
		return label;
	}
	
	/**
	 * �������
	 */
	private Font getFont(){
		return new Font(9, "����", JWT.FONT_STYLE_BOLD);
	}

	/**
	 * �ײ���߰�ť��
	 */
	@Override
	protected void renderLeftButtonArea(Composite leftButtonArea){
		GridLayout layout = new GridLayout();
		layout.marginLeft = 20;
		leftButtonArea.setLayout(layout);
		//˵��
		Label remarkLabel = new Label(leftButtonArea, JWT.MIDDLE);
		remarkLabel.setText("ϵͳ����֮������ͨ����ع���ģ�������������Ϣ���й���");
		remarkLabel.setLayoutData(GridData.INS_FILL_VERTICAL);
	}
}
