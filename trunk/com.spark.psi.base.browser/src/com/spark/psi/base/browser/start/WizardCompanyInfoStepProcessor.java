package com.spark.psi.base.browser.start;

import java.io.IOException;
import java.io.InputStream;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.DataImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.MsgCompanyInfoChanged;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.base.config.entity.CompanyInfo;
import com.spark.psi.publish.base.config.task.UpdateCompanyInfoTask;

/**
 * <p>������ҵ��Ϣ���洦����</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-6
 */

public class WizardCompanyInfoStepProcessor extends WizardBaseStepProcessor{

	// Ĭ��logo
	public final static ImageDescriptor DEFAULF_LOGO = BaseImages.getImage("/images/config/logo.png");
	// ϵͳ����ɫ
	public final static ImageDescriptor DEFAULF_BACK = BaseImages.getImage("/images/config/top-bg01.gif");
	// �����ϴ�ͼƬ�ĸ�ʽ
	static final String[] suffixs = {"jpg", "png", "gif"};
	// LOGOͼƬ�������ֵ
	final static int MAX_LENGTH = 30000;
	final static int MAX_WIDTH = 150;
	final static int MAX_HEIGHT = 50;

	/**
	 * �ؼ�ID 
	 */
	public final static String ID_Label_Logo = "Label_Logo";
	public final static String ID_Label_Title = "Label_Title";
	public final static String ID_FileChooser_Img = "FileChooser_Img";
	public final static String ID_Button_UploadImg = "Button_UploadImg";
	public final static String ID_Text_Title = "Text_Title";
	public final static String ID_Button_ModifyTitle = "Button_ModifyTitle";
	public final static String ID_Text_ShortName = "Text_ShortName";
	public final static String ID_Text_FullName = "Text_FullName";
	public final static String ID_List_Province = "List_Province";
	public final static String ID_List_City = "List_City";
	public final static String ID_List_District = "List_District";
	public final static String ID_Text_Address = "Text_Address";
	public final static String ID_Text_PostCode = "Text_PostCode";
	public final static String ID_Text_Phone = "Text_Phone";
	public final static String ID_Text_Fax = "Text_Fax";

	/**
	 * �ؼ�
	 */
	private Text shortNameText;
	private Text fullNameText;
	private Text addressText;
	private Text postCodeText;
	private Text phoneText;
	private Text faxText;
	private LWComboList provinceList;
	private LWComboList cityList;
	private LWComboList districtList;
	private Label logoLabel;
	private FileChooser fileImg;
	private Text titleText;
	private Label titleLabel;
	//
	private GUID tenantId;
	private byte[] logoData;

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation context){
		super.process(context);
		// �������
		createUIComponent();
		//�����֤��
		addValidator();
	}

	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		//��ʼ��ʡ����
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);
		//��ʼ����
		initData();
	}
	
	/**
	 * ����ͼ�����Ϊ������ʾĬ��ͼ��
	 */
	private void initData(){
		CompanyInfo company = getContext().get(CompanyInfo.class);
		if(company != null){
			tenantId = company.getId();
			if(company.getLogo() != null && company.getLogo().length > 0){
				ImageDescriptor img = DataImageDescriptor.createImageDescriptor(company.getLogo(), logoLabel);
				logoLabel.setImage(img);
			}
			if(company.getSystemName() != null && company.getSystemName().length() > 0){
				titleLabel.setText(company.getSystemName());
			}
			titleText.setText(company.getSystemName());
			shortNameText.setText(company.getCompanyShortName());
			fullNameText.setText(company.getCompanyName());
			if(company.getProvince() != null){
				provinceList.setSelection(company.getProvince());
			}
			if(company.getCity() != null){
				cityList.setSelection(company.getCity());
			}
			if(company.getDistrict() != null){
				districtList.setSelection(company.getDistrict());
			}
			addressText.setText(company.getAddress());
			phoneText.setText(company.getLandLineNumber());
			faxText.setText(company.getFaxNumber());
			postCodeText.setText(company.getPostcode());
		}
	}

	/**
	 * �������
	 */
	private void createUIComponent(){
		//Logo
		logoLabel = this.createControl(ID_Label_Logo, Label.class);
		// �ļ��ϴ�
		fileImg = this.createControl(ID_FileChooser_Img, FileChooser.class, JWT.NONE);
		Button uploadButton = this.createControl(ID_Button_UploadImg, Button.class, JWT.NONE);
		uploadButton.addActionListener(new UploadButtonListener());
		fileImg.setRelativeTarget(uploadButton, ActionListener.class);
		//�޸ı���
		titleLabel = this.createControl(ID_Label_Title, Label.class);
		titleText = this.createControl(ID_Text_Title, Text.class);
		Button modifyTitleButton = this.createControl(ID_Button_ModifyTitle, Button.class);
		modifyTitleButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				titleLabel.setText(StringHelper.toTrimStr(titleText.getText()));
			}
		});
		//��˾���
		shortNameText = this.createControl(ID_Text_ShortName, Text.class);
		//ȫ��
		fullNameText = this.createControl(ID_Text_FullName, Text.class);
		//��ַ
		addressText = this.createControl(ID_Text_Address, Text.class);
		//�ʱ�
		postCodeText = this.createControl(ID_Text_PostCode, Text.class);
		//�绰
		phoneText = this.createControl(ID_Text_Phone, Text.class);
		//����
		faxText = this.createControl(ID_Text_Fax, Text.class);
		//ʡ
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		//��
		cityList = this.createControl(ID_List_City, LWComboList.class);
		//��
		districtList = this.createControl(ID_List_District, LWComboList.class);
	}

	/**
	 * ���������֤��
	 */
	private void addValidator(){
		registerNotEmptyValidator(shortNameText, "��ҵ���");
		registerNotEmptyValidator(fullNameText, "��ҵȫ��");
	}

	/**
	 * �ϴ�΢�갴ť������
	 */
	private class UploadButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			try{
				String fileName = fileImg.getFileName();
				if(CheckIsNull.isEmpty(fileName)){
					alert("��ѡ��Ҫ�ϴ���ͼƬ��");
					return;
				}
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				if(!validateSuffix(suffix)){
					alert("ͼƬ��ʽ����ȷ�����ϴ���׺��Ϊ��" + getSuffixs() + " ���ļ���");
					return;
				}
				InputStream in = fileImg.getInputStream(fileImg.getFileName());
				final byte[] imgBytes = new byte[in.available()];
				in.read(imgBytes);
				if(imgBytes.length > MAX_LENGTH){
					alert("ͼƬ��ʽ����ȷ���봫��30KB���µ�ͼƬ��");
					in.close();
					return;
				}
				ImageDescriptor logoImg = DataImageDescriptor.createImageDescriptor(imgBytes, logoLabel);
				if(logoImg.getWidth() > MAX_WIDTH || logoImg.getHeight() > MAX_HEIGHT){
					alert("ͼƬ��ʽ����ȷ��ͼƬ���Ȳ��ܴ���" + MAX_WIDTH + "���أ��߶Ȳ��ܴ���" + MAX_HEIGHT + "���ء�");
					in.close();
					logoImg.dispose();
					return;
				}
				// ���ñ���ͼƬ,Ԥ������
				logoLabel.setImage(logoImg);
				logoLabel.getParent().layout();
				logoData = imgBytes;
				in.close();
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}

		/**
		 * ��֤��׺���ж��Ƿ�ΪͼƬ
		 *
		 *@param suffix ��׺
		 *@return
		 */
		private boolean validateSuffix(String suffix){
			for(String string : suffixs){
				if(string.equals(suffix)){
					return true;
				}
			}
			return false;
		}

		/**
		 * ���֧��ͼƬ��ʽ�ĺ�׺��
		 *
		 *@return
		 */
		private String getSuffixs(){
			StringBuilder buffer = new StringBuilder();
			for(String string : suffixs){
				buffer.append(",");
				buffer.append(string);
			}
			return buffer.toString().substring(1);
		}

	}

	/**
	 * ����ִ��
	 */
	@Override
	protected boolean operateExecute(){
		//��֤��ҵ��Ϣ
		if(!validateInput()){
			return false;
		}
		//������ҵ��Ϣ
		saveCompanyInfo();
		hint("����ɹ���");
		return true;
	}
	
	/**
	 * ��һ��ִ��
	 */
	@Override
	protected boolean nextExecute(){
		//��֤��ҵ��Ϣ
		if(!validateInput()){
			return false;
		}
		return true;
	}
	
	/**
	 * ������ҵ��Ϣ
	 */
	private void saveCompanyInfo(){
		// �������������־û�����
		UpdateCompanyInfoTask task = new UpdateCompanyInfoTask();
		task.setId(tenantId);
		task.setCompanyName(StringHelper.toTrimStr(fullNameText.getText()));
		task.setCompanyShortName(shortNameText.getText());
		task.setAddress(addressText.getText());
		task.setProvince(provinceList.getText());
		task.setCity(cityList.getText());
		task.setDistrict(districtList.getText());
		task.setPhone(phoneText.getText());
		task.setPostcode(postCodeText.getText());
		task.setFax(faxText.getText());
		if(logoData != null && logoData.length > 0){
			task.setLogo(logoData);
		}
		else{
			CompanyInfo company = getContext().get(CompanyInfo.class);
			if(company != null){
				task.setLogo(company.getLogo());
			}
		}
		task.setSystemName(titleText.getText());
		getContext().handle(task, UpdateCompanyInfoTask.Method.Update);
		// ֪ͨ�ϼ�ҳ�棬�⻧Logo��ϵͳ���ⷢ���ı�
		Display.getCurrent().getSituation().bubbleMessage(new MsgCompanyInfoChanged());
	}
}
