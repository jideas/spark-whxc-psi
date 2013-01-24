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
 * <p>配置企业信息界面处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-7-6
 */

public class WizardCompanyInfoStepProcessor extends WizardBaseStepProcessor{

	// 默认logo
	public final static ImageDescriptor DEFAULF_LOGO = BaseImages.getImage("/images/config/logo.png");
	// 系统背景色
	public final static ImageDescriptor DEFAULF_BACK = BaseImages.getImage("/images/config/top-bg01.gif");
	// 允许上传图片的格式
	static final String[] suffixs = {"jpg", "png", "gif"};
	// LOGO图片最大容量值
	final static int MAX_LENGTH = 30000;
	final static int MAX_WIDTH = 150;
	final static int MAX_HEIGHT = 50;

	/**
	 * 控件ID 
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
	 * 控件
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
	 * 页面初始化
	 */
	@Override
	public void process(Situation context){
		super.process(context);
		// 创建组件
		createUIComponent();
		//添加验证器
		addValidator();
	}

	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context
	 */
	public void postProcess(Situation context){
		super.postProcess(context);
		//初始化省市县
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);
		//初始数据
		initData();
	}
	
	/**
	 * 加载图标如果为空则显示默认图标
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
	 * 创建组件
	 */
	private void createUIComponent(){
		//Logo
		logoLabel = this.createControl(ID_Label_Logo, Label.class);
		// 文件上传
		fileImg = this.createControl(ID_FileChooser_Img, FileChooser.class, JWT.NONE);
		Button uploadButton = this.createControl(ID_Button_UploadImg, Button.class, JWT.NONE);
		uploadButton.addActionListener(new UploadButtonListener());
		fileImg.setRelativeTarget(uploadButton, ActionListener.class);
		//修改标题
		titleLabel = this.createControl(ID_Label_Title, Label.class);
		titleText = this.createControl(ID_Text_Title, Text.class);
		Button modifyTitleButton = this.createControl(ID_Button_ModifyTitle, Button.class);
		modifyTitleButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				titleLabel.setText(StringHelper.toTrimStr(titleText.getText()));
			}
		});
		//公司简称
		shortNameText = this.createControl(ID_Text_ShortName, Text.class);
		//全称
		fullNameText = this.createControl(ID_Text_FullName, Text.class);
		//地址
		addressText = this.createControl(ID_Text_Address, Text.class);
		//邮编
		postCodeText = this.createControl(ID_Text_PostCode, Text.class);
		//电话
		phoneText = this.createControl(ID_Text_Phone, Text.class);
		//传真
		faxText = this.createControl(ID_Text_Fax, Text.class);
		//省
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		//市
		cityList = this.createControl(ID_List_City, LWComboList.class);
		//县
		districtList = this.createControl(ID_List_District, LWComboList.class);
	}

	/**
	 * 界面添加验证器
	 */
	private void addValidator(){
		registerNotEmptyValidator(shortNameText, "企业简称");
		registerNotEmptyValidator(fullNameText, "企业全称");
	}

	/**
	 * 上传微标按钮侦听器
	 */
	private class UploadButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			try{
				String fileName = fileImg.getFileName();
				if(CheckIsNull.isEmpty(fileName)){
					alert("请选择要上传的图片。");
					return;
				}
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				if(!validateSuffix(suffix)){
					alert("图片格式不正确，请上传后缀名为：" + getSuffixs() + " 的文件。");
					return;
				}
				InputStream in = fileImg.getInputStream(fileImg.getFileName());
				final byte[] imgBytes = new byte[in.available()];
				in.read(imgBytes);
				if(imgBytes.length > MAX_LENGTH){
					alert("图片格式不正确，请传入30KB以下的图片。");
					in.close();
					return;
				}
				ImageDescriptor logoImg = DataImageDescriptor.createImageDescriptor(imgBytes, logoLabel);
				if(logoImg.getWidth() > MAX_WIDTH || logoImg.getHeight() > MAX_HEIGHT){
					alert("图片格式不正确，图片长度不能大于" + MAX_WIDTH + "像素，高度不能大于" + MAX_HEIGHT + "像素。");
					in.close();
					logoImg.dispose();
					return;
				}
				// 设置背景图片,预览功能
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
		 * 验证后缀，判断是否为图片
		 *
		 *@param suffix 后缀
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
		 * 获得支持图片格式的后缀串
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
	 * 操作执行
	 */
	@Override
	protected boolean operateExecute(){
		//验证企业信息
		if(!validateInput()){
			return false;
		}
		//保存企业信息
		saveCompanyInfo();
		hint("保存成功！");
		return true;
	}
	
	/**
	 * 下一步执行
	 */
	@Override
	protected boolean nextExecute(){
		//验证企业信息
		if(!validateInput()){
			return false;
		}
		return true;
	}
	
	/**
	 * 保存企业信息
	 */
	private void saveCompanyInfo(){
		// 调用任务处理器持久化数据
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
		// 通知上级页面，租户Logo和系统标题发生改变
		Display.getCurrent().getSituation().bubbleMessage(new MsgCompanyInfoChanged());
	}
}
