package com.spark.psi.base.browser.start;

import org.json.JSONObject;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.SysParamKey;
import com.spark.psi.publish.base.start.key.FindSysParamValueKey;

/**
 * <p>�����򵼴�����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-25
 */

public class ConfigurationWizardProcessor extends BaseFormPageProcessor{

	/**����������״̬*/
	private final int ENABLE_status = 1; //����

	private final int DISENABLE_status = 2; //������

	private final int HOVER_status = 3; //�����ͣ

	/**
	 * �ؼ�ID
	 */
	public final static String ID_Button_QuickStart = "Button_QuickStart";
	//
	public final static String ID_Label_StepOneIcon = "Label_StepOneIcon";
	public final static String ID_Label_StepOneLabel = "Label_StepOneLabel";
	public final static String ID_Label_StepTwoIcon = "Label_StepTwoIcon";
	public final static String ID_Label_StepTwoLabel = "Label_StepTwoLabel";
	public final static String ID_Label_StepThreeIcon = "Label_StepThreeIcon";
	public final static String ID_Label_StepThreeLabel = "Label_StepThreeLabel";
	public final static String ID_Label_StepFourIcon = "Label_StepFourIcon";
	public final static String ID_Label_StepFourLabel = "Label_StepFourLabel";
	public final static String ID_Label_StepFiveIcon = "Label_StepFiveIcon";
	public final static String ID_Label_StepFiveLabel = "Label_StepFiveLabel";
	public final static String ID_Label_StepSixIcon = "Label_StepSixIcon";
	public final static String ID_Label_StepSixLabel = "Label_StepSixLabel";
	public final static String ID_Label_StepSevenIcon = "Label_StepSevenIcon";
	public final static String ID_Label_StepSevenLabel = "Label_StepSevenLabel";
	public final static String ID_Label_StepEightIcon = "Label_StepEightIcon";
	public final static String ID_Label_StepEightLabel = "Label_StepEightLabel";
	public final static String ID_Label_StepNineIcon = "Label_StepNineIcon";
	public final static String ID_Label_StepNineLabel = "Label_StepNineLabel";

	/**
	 * �ؼ�
	 */
	private Label quickStartButton;
	//
	private Label stepOneIcon;
	private Label stepOneLabel;
	private Label stepTwoIcon;
	private Label stepTwoLabel;
	private Label stepThreeIcon;
	private Label stepThreeLabel;
	private Label stepFourIcon;
	private Label stepFourLabel;
	private Label stepFiveIcon;
	private Label stepFiveLabel;
	private Label stepSixIcon;
	private Label stepSixLabel;
	private Label stepSevenIcon;
	private Label stepSevenLabel;
	private Label stepEightIcon;
	private Label stepEightLabel;
	private Label stepNineIcon;
	private Label stepNineLabel;

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation context){
		//�������
		createUIComponent();
	}

	/**
	 * �������
	 */
	private void createUIComponent(){
		//����������ť
		quickStartButton = this.createControl(ID_Button_QuickStart, Label.class);
		//
		stepOneIcon = this.createControl(ID_Label_StepOneIcon, Label.class);
		stepOneLabel = this.createControl(ID_Label_StepOneLabel, Label.class);
		//
		stepTwoIcon = this.createControl(ID_Label_StepTwoIcon, Label.class);
		stepTwoLabel = this.createControl(ID_Label_StepTwoLabel, Label.class);
		//
		stepThreeIcon = this.createControl(ID_Label_StepThreeIcon, Label.class);
		stepThreeLabel = this.createControl(ID_Label_StepThreeLabel, Label.class);
		//
		stepFourIcon = this.createControl(ID_Label_StepFourIcon, Label.class);
		stepFourLabel = this.createControl(ID_Label_StepFourLabel, Label.class);
		//
		stepFiveIcon = this.createControl(ID_Label_StepFiveIcon, Label.class);
		stepFiveLabel = this.createControl(ID_Label_StepFiveLabel, Label.class);
		//
		stepSixIcon = this.createControl(ID_Label_StepSixIcon, Label.class);
		stepSixLabel = this.createControl(ID_Label_StepSixLabel, Label.class);
		//
		stepSevenIcon = this.createControl(ID_Label_StepSevenIcon, Label.class);
		stepSevenLabel = this.createControl(ID_Label_StepSevenLabel, Label.class);
		//
		stepEightIcon = this.createControl(ID_Label_StepEightIcon, Label.class);
		stepEightLabel = this.createControl(ID_Label_StepEightLabel, Label.class);
		//
		stepNineIcon = this.createControl(ID_Label_StepNineIcon, Label.class);
		stepNineLabel = this.createControl(ID_Label_StepNineLabel, Label.class);
	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		//�Ƿ��ʼ����ӭ����
		isInitWelcome(context);
		//��ʼ����������ϵͳ��ť״̬
		initQuickStartButtonstatus();
		//��ʼ���������ø���ť״̬
		initStepButtonstatus(context);
		//����������ϵͳ�ĸ���ť���������
		addStepListener();
	}

	/**
	 * �Ƿ񵯳���ӭ����
	 */
	private void isInitWelcome(Situation context){
		Boolean hasInitWelcome =
		        getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_WELCOME));
		if(!hasInitWelcome && this.getArgument() != null){
			new WelcomeWindow(new ResponseHandler(){

				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
					//������Ϣ���ر�������
					if(returnValue != null && (Boolean)returnValue){
						getContext().bubbleMessage(new ReLoginMessage());
					}

				}
			});
		}
	}

	/**
	 * ��ʼ����������ϵͳ��ť״̬
	 */
	private void initQuickStartButtonstatus(){
		//�Ѿ���ʼ����ҵ��Ϣ����
		Boolean hasInitCompany =
		        getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_COMPANY));
		if(hasInitCompany){ //�Ѵ���������������
			quickStartButton.setCursor(null);
			quickStartButton.setBackimage(BaseImages.getImage("images/wizard/quick_start_button_unenable.png"));
			quickStartButton.setEnabled(false);
		}
		else{ //������������������
			quickStartButton.setCursor(Cursor.HAND);
			quickStartButton.setBackimage(BaseImages.getImage("images/wizard/quick_start_button_enable.png"));
			quickStartButton.addMouseClickListener(new QuickStartButtonListener());
		}
	}

	/**
	 * ��ʼ���������ø���ť״̬
	 */
	private void initStepButtonstatus(Situation context){
		//��һ��״̬
		initStepOnestatus(context);
		//�ڶ���״̬
		initStepTwostatus(context);
		//������״״̬
		initStepThreestatus(context);
		//���Ĳ�״״̬
		initStepFourstatus(context);
		//���岽״״̬
		initStepFivestatus(context);
		//������״״̬
		initStepSixstatus(context);
		//���߲�״״̬
		initStepSevenstatus(context);
		//�ڰ˲�״״̬
		initStepEightstatus(context);
		//�ھŲ�״״̬
		initStepNinestatus(context);
	}

	/**
	 * ��һ��״̬
	 */
	private void initStepOnestatus(Situation context){
		//
		ImageDescriptor enableImage = BaseImages.getImage("images/wizard/wizard_step1_enable.png");
		ImageDescriptor hoverImage = BaseImages.getImage("images/wizard/wizard_step1_hover.png");
		//
		stepOneIcon.setBackimage(enableImage);
		stepOneIcon.setCursor(Cursor.HAND);
		stepOneLabel.setForeground(getColor(ENABLE_status));
		//
		JSONObject images = new JSONObject();
		try{
			images.put("enableImage", enableImage.getDNAURI());
			images.put("hoverImage", hoverImage.getDNAURI());
		}
		catch(Throwable t){
			t.printStackTrace();
		}
		//
		addClientEventListener(stepOneIcon, images);
	}

	/**
	 * �ڶ���״̬
	 */
	private void initStepTwostatus(Situation context){
		//�Ѿ���ʼ����ҵ��Ϣ����
		Boolean hasInitCompany =
		        getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_COMPANY));
		//
		if(hasInitCompany){
			ImageDescriptor enableImage = BaseImages.getImage("images/wizard/wizard_step2_enable.png");
			ImageDescriptor hoverImage = BaseImages.getImage("images/wizard/wizard_step2_hover.png");
			stepTwoIcon.setBackimage(enableImage);
			stepTwoIcon.setCursor(Cursor.HAND);
			stepTwoLabel.setForeground(getColor(ENABLE_status));
			//
			JSONObject images = new JSONObject();
			try{
				images.put("enableImage", enableImage.getDNAURI());
				images.put("hoverImage", hoverImage.getDNAURI());
			}
			catch(Throwable t){
				t.printStackTrace();
			}
			//
			addClientEventListener(stepTwoIcon, images);
		}
		else{
			stepTwoIcon.setBackimage(BaseImages.getImage("images/wizard/wizard_step2_disable.png"));
			stepTwoLabel.setForeground(getColor(DISENABLE_status));
			stepTwoIcon.setEnabled(false);
			stepTwoLabel.setEnabled(false);
		}

	}

	/**
	 * ������״̬
	 */
	private void initStepThreestatus(Situation context){
		//�Ѿ���ʼ������
		Boolean hasInitDept = getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_DEPT));
		//
		if(hasInitDept){
			//
			ImageDescriptor enableImage = BaseImages.getImage("images/wizard/wizard_step3_enable.png");
			ImageDescriptor hoverImage = BaseImages.getImage("images/wizard/wizard_step3_hover.png");
			stepThreeIcon.setBackimage(enableImage);
			stepThreeIcon.setCursor(Cursor.HAND);
			stepThreeLabel.setForeground(getColor(ENABLE_status));
			//
			JSONObject images = new JSONObject();
			try{
				images.put("enableImage", enableImage.getDNAURI());
				images.put("hoverImage", hoverImage.getDNAURI());
			}
			catch(Throwable t){
				t.printStackTrace();
			}
			//
			addClientEventListener(stepThreeIcon, images);
		}
		else{
			stepThreeIcon.setBackimage(BaseImages.getImage("images/wizard/wizard_step3_disable.png"));
			stepThreeLabel.setForeground(getColor(DISENABLE_status));
			stepThreeIcon.setEnabled(false);
			stepThreeLabel.setEnabled(false);
		}
	}

	/**
	 * ���Ĳ�״̬
	 */
	private void initStepFourstatus(Situation context){
		//�Ѿ���ʼ��Ա��
		Boolean hasInitEmp = getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_EMP));
		//
		if(hasInitEmp){
			//
			ImageDescriptor enableImage = BaseImages.getImage("images/wizard/wizard_step4_enable.png");
			ImageDescriptor hoverImage = BaseImages.getImage("images/wizard/wizard_step4_hover.png");
			stepFourIcon.setBackimage(enableImage);
			stepFourIcon.setCursor(Cursor.HAND);
			stepFourLabel.setForeground(getColor(ENABLE_status));
			//
			JSONObject images = new JSONObject();
			try{
				images.put("enableImage", enableImage.getDNAURI());
				images.put("hoverImage", hoverImage.getDNAURI());
			}
			catch(Throwable t){
				t.printStackTrace();
			}
			//
			addClientEventListener(stepFourIcon, images);
		}
		else{
			stepFourIcon.setBackimage(BaseImages.getImage("images/wizard/wizard_step4_disable.png"));
			stepFourLabel.setForeground(getColor(DISENABLE_status));
			stepFourIcon.setEnabled(false);
			stepFourLabel.setEnabled(false);
		}
	}

	/**
	 * ���岽״̬
	 */
	private void initStepFivestatus(Situation context){
		Boolean hasInitCuspro = getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_CUSPRO));
		//
		if(hasInitCuspro){
			//
			ImageDescriptor enableImage = BaseImages.getImage("images/wizard/wizard_step5_enable.png");
			ImageDescriptor hoverImage = BaseImages.getImage("images/wizard/wizard_step5_hover.png");
			stepFiveIcon.setBackimage(enableImage);
			stepFiveIcon.setCursor(Cursor.HAND);
			stepFiveLabel.setForeground(getColor(ENABLE_status));
			//
			JSONObject images = new JSONObject();
			try{
				images.put("enableImage", enableImage.getDNAURI());
				images.put("hoverImage", hoverImage.getDNAURI());
			}
			catch(Throwable t){
				t.printStackTrace();
			}
			//
			addClientEventListener(stepFiveIcon, images);
		}
		else{
			stepFiveIcon.setBackimage(BaseImages.getImage("images/wizard/wizard_step5_disable.png"));
			stepFiveLabel.setForeground(getColor(DISENABLE_status));
			stepFiveIcon.setEnabled(false);
			stepFiveLabel.setEnabled(false);
		}
	}

	/**
	 * ������״̬
	 */
	private void initStepSixstatus(Situation context){
		//�Ѿ���ʼ����Ӧ��
		Boolean hasInitProvider =
		        getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_PROVIDER));
		//
		if(hasInitProvider){
			//
			ImageDescriptor enableImage = BaseImages.getImage("images/wizard/wizard_step6_enable.png");
			ImageDescriptor hoverImage = BaseImages.getImage("images/wizard/wizard_step6_hover.png");
			stepSixIcon.setBackimage(enableImage);
			stepSixIcon.setCursor(Cursor.HAND);
			stepSixLabel.setForeground(getColor(ENABLE_status));
			//
			JSONObject images = new JSONObject();
			try{
				images.put("enableImage", enableImage.getDNAURI());
				images.put("hoverImage", hoverImage.getDNAURI());
			}
			catch(Throwable t){
				t.printStackTrace();
			}
			//
			addClientEventListener(stepSixIcon, images);
		}
		else{
			stepSixIcon.setBackimage(BaseImages.getImage("images/wizard/wizard_step6_disable.png"));
			stepSixLabel.setForeground(getColor(DISENABLE_status));
			stepSixIcon.setEnabled(false);
			stepSixLabel.setEnabled(false);
		}
	}

	/**
	 * ���߲�״̬
	 */
	private void initStepSevenstatus(Situation context){
		//�Ѿ���ʼ����Ʒ����
		Boolean hasInitGoodsType =
		        getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_GOODSTYPE));
		//
		if(hasInitGoodsType){
			//
			ImageDescriptor enableImage = BaseImages.getImage("images/wizard/wizard_step7_enable.png");
			ImageDescriptor hoverImage = BaseImages.getImage("images/wizard/wizard_step7_hover.png");
			stepSevenIcon.setBackimage(enableImage);
			stepSevenIcon.setCursor(Cursor.HAND);
			stepSevenLabel.setForeground(getColor(ENABLE_status));
			//
			JSONObject images = new JSONObject();
			try{
				images.put("enableImage", enableImage.getDNAURI());
				images.put("hoverImage", hoverImage.getDNAURI());
			}
			catch(Throwable t){
				t.printStackTrace();
			}
			//
			addClientEventListener(stepSevenIcon, images);
		}
		else{
			stepSevenIcon.setBackimage(BaseImages.getImage("images/wizard/wizard_step7_disable.png"));
			stepSevenLabel.setForeground(getColor(DISENABLE_status));
			stepSevenIcon.setEnabled(false);
			stepSevenLabel.setEnabled(false);
		}
	}

	/**
	 * �ڰ˲�״̬
	 */
	private void initStepEightstatus(Situation context){
		// �Ѿ���ʼ����Ʒ
		Boolean hasInitGoods = getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_GOODS));
		//
		if(hasInitGoods){
			//
			ImageDescriptor enableImage = BaseImages.getImage("images/wizard/wizard_step8_enable.png");
			ImageDescriptor hoverImage = BaseImages.getImage("images/wizard/wizard_step8_hover.png");
			stepEightIcon.setBackimage(enableImage);
			stepEightIcon.setCursor(Cursor.HAND);
			stepEightLabel.setForeground(getColor(ENABLE_status));
			//
			JSONObject images = new JSONObject();
			try{
				images.put("enableImage", enableImage.getDNAURI());
				images.put("hoverImage", hoverImage.getDNAURI());
			}
			catch(Throwable t){
				t.printStackTrace();
			}
			//
			addClientEventListener(stepEightIcon, images);
		}
		else{
			stepEightIcon.setBackimage(BaseImages.getImage("images/wizard/wizard_step8_disable.png"));
			stepEightLabel.setForeground(getColor(DISENABLE_status));
			stepEightIcon.setEnabled(false);
			stepEightLabel.setEnabled(false);
		}
	}

	/**
	 * �ھŲ�״̬
	 */
	private void initStepNinestatus(Situation context){
		// �Ѿ���ʼ���ֿ� 
		Boolean hasInitStore = getContext().get(Boolean.class, new FindSysParamValueKey(SysParamKey.HAS_INIT_STORE));
		//
		if(hasInitStore){
			//
			ImageDescriptor enableImage = BaseImages.getImage("images/wizard/wizard_step9_enable.png");
			ImageDescriptor hoverImage = BaseImages.getImage("images/wizard/wizard_step9_hover.png");
			stepNineIcon.setBackimage(enableImage);
			stepNineIcon.setCursor(Cursor.HAND);
			stepNineLabel.setForeground(getColor(ENABLE_status));
			//
			JSONObject images = new JSONObject();
			try{
				images.put("enableImage", enableImage.getDNAURI());
				images.put("hoverImage", hoverImage.getDNAURI());
			}
			catch(Throwable t){
				t.printStackTrace();
			}
			//
			addClientEventListener(stepNineIcon, images);
		}
		else{
			stepNineIcon.setBackimage(BaseImages.getImage("images/wizard/wizard_step9_disable.png"));
			stepNineLabel.setForeground(getColor(DISENABLE_status));
			stepNineIcon.setEnabled(false);
			stepNineLabel.setEnabled(false);
		}
	}

	/**
	 * ��ӿͻ����¼����������ȥ��ɫ�仯
	 */
	private void addClientEventListener(Label target, JSONObject jsonObject){
		target.setCustomObject("images", jsonObject);
		target.addClientEventHandler(JWT.CLIENT_EVENT_MOUSE_MOVE, "PSIWizard.StepMouseMoveIn");
		target.addClientEventHandler(JWT.CLIENT_EVENT_MOUSE_EXIT, "PSIWizard.StepMouseMoveOut");
	}

	/**
	 * ����������������
	 */
	private class QuickStartButtonListener implements MouseClickListener{

		public void click(MouseEvent e){
			//���Ϸ�����Ϣ�򿪿�������ϵͳ����
			PageControllerInstance controllerInstance = new PageControllerInstance("QuickConfigurationPage");
			getContext().bubbleMessage(new StepPageMessage("��������ϵͳ", controllerInstance));
		}

	}

	/**
	 * �����ɫ
	 * 
	 * @param status ״̬(��ΪENABLE_status��DISENABLE_status��HOVER_status����) 
	 */
	private Color getColor(int status){
		switch(status){
			case ENABLE_status:
				return new Color(0x008015);
			case DISENABLE_status:
				return new Color(0xA2A9AE);
			case HOVER_status:
				return new Color(0xDF8A18);
			default:
				return Color.COLOR_BLACK;
		}
	}

	/**
	 * ����������ϵͳ�ĸ���ť���������
	 */
	private void addStepListener(){
		//�����������ҵ��Ϣ
		if(stepOneIcon.isEnabled()){
			stepOneIcon.addMouseClickListener(new MouseClickListener(){

				public void click(MouseEvent e){
					openPage(WizardUtil.COMPANEY_PAGENAME);
				}
			});
		}
		//��������ò�����Ϣ
		if(stepTwoIcon.isEnabled()){
			stepTwoIcon.addMouseClickListener(new MouseClickListener(){

				public void click(MouseEvent e){
					openPage(WizardUtil.DEPARTMENT_PAGENAME);
				}
			});
		}
		//��������Ա�����û�
		if(stepThreeIcon.isEnabled()){
			stepThreeIcon.addMouseClickListener(new MouseClickListener(){

				public void click(MouseEvent e){
					openPage(WizardUtil.EMPLOYEE_PAGENAME);
				}
			});
		}
		//�������ӿͻ���Ϣ
		if(stepFourIcon.isEnabled()){
			stepFourIcon.addMouseClickListener(new MouseClickListener(){

				public void click(MouseEvent e){
					openPage(WizardUtil.CUSTOMER_PAGENAME);

				}
			});
		}
		//�������ӹ�Ӧ����Ϣ
		if(stepFiveIcon.isEnabled()){
			stepFiveIcon.addMouseClickListener(new MouseClickListener(){

				public void click(MouseEvent e){
					openPage(WizardUtil.PROVIDER_PAGENAME);
				}
			});
		}
		//����������Ʒ����
		if(stepSixIcon.isEnabled()){
			stepSixIcon.addMouseClickListener(new MouseClickListener(){

				public void click(MouseEvent e){
					openPage(WizardUtil.GOODSTYPE_PAGENAME);
				}
			});
		}
		//����������Ʒ��Ϣ
		if(stepSevenIcon.isEnabled()){
			stepSevenIcon.addMouseClickListener(new MouseClickListener(){

				public void click(MouseEvent e){
					openPage(WizardUtil.GOODS_PAGENAME);
				}
			});
		}
		//��������ù�˾�ֿ�
		if(stepEightIcon.isEnabled()){
			stepEightIcon.addMouseClickListener(new MouseClickListener(){

				public void click(MouseEvent e){
					openPage(WizardUtil.STORAGE_PAGENAME);
				}
			});
		}
		//���������ϵͳ
		if(stepNineIcon.isEnabled()){
			stepNineIcon.addMouseClickListener(new MouseClickListener(){

				public void click(MouseEvent e){
					openPage(WizardUtil.STARTSYSTEM_PAGENAME);
				}
			});
		}
	}

	/**
	 * ��ҳ��
	 */
	private void openPage(String pageName){
		PageControllerInstance controllerInstance =
		        new PageControllerInstance(pageName, WizardUtil.getPageNameMap().get(pageName));
		getContext().bubbleMessage(new StepPageMessage("������", controllerInstance));
	}

}
