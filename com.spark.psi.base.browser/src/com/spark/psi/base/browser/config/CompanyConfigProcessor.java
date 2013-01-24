package com.spark.psi.base.browser.config;

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
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.browser.MsgCompanyInfoChanged;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.base.config.entity.CompanyInfo;
import com.spark.psi.publish.base.config.task.UpdateCompanyInfoTask;

/**
 * 企业信息配置界面处理器
 */
public class CompanyConfigProcessor extends BaseFormPageProcessor implements
		IDataProcessPrompt {

	// 默认logo
	public final static ImageDescriptor DEFAULF_LOGO = BaseImages
			.getImage("/images/config/logo7.png");
	// 系统背景色
	public final static ImageDescriptor DEFAULF_BACK = BaseImages
			.getImage("/images/config/top-bg01.gif");
	// 允许上传图片的格式
	static final String[] suffixs = { "jpg", "png", "gif" };
	// LOGO图片最大容量值
	final static int MAX_LENGTH = 30000;
	final static int MAX_WIDTH = 150;
	final static int MAX_HEIGHT = 50;

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
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Label_Logo = "Label_Logo";// 预览企业图标
	public final static String ID_Label_Title = "Label_Title";// 预览企业系统名称
	public final static String ID_Label_SaasNo = "Label_SaasNo";// 鱼儿多号

	Label lblTitle;
	Text txtTitle;
	Label lblLogo;
	LWComboList provinceList;
	LWComboList cityList;
	LWComboList districtList;
	FileChooser fileImg;
	Button uploadButton;
	Text txt_shortName;
	Text txt_fullName;
	Text txt_address;
	Text txt_postCode;
	Text txt_phone;
	Text txt_fax;
	Label lbl_saas_No;

	byte[] logoData;

	boolean isCreate = true;

	GUID tenantId;

	@Override
	public void process(Situation situation) {

		lbl_saas_No = this.createControl(ID_Label_SaasNo, Label.class);

		txt_shortName = this.createControl(ID_Text_ShortName, Text.class);
		txt_fullName = this.createControl(ID_Text_FullName, Text.class);
		txt_address = this.createControl(ID_Text_Address, Text.class);
		txt_postCode = this.createControl(ID_Text_PostCode, Text.class);
		txt_phone = this.createControl(ID_Text_Phone, Text.class);
		txt_fax = this.createControl(ID_Text_Fax, Text.class);

		lblTitle = this.createControl(ID_Label_Title, Label.class);
		txtTitle = this.createControl(ID_Text_Title, Text.class);
		lblLogo = this.createControl(ID_Label_Logo, Label.class);

		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		cityList = this.createControl(ID_List_City, LWComboList.class);
		districtList = this.createControl(ID_List_District, LWComboList.class);

		//
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);

		// 修改标题
		Button changeTitleButton = this.createControl(ID_Button_ModifyTitle,
				Button.class, JWT.NONE);
		changeTitleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTitle.setText(txtTitle.getText());
				markDataChanged();
			}
		});
		// 文件上传
		fileImg = this.createControl(ID_FileChooser_Img, FileChooser.class,
				JWT.NONE);
		uploadButton = this.createControl(ID_Button_UploadImg, Button.class,
				JWT.NONE);
		fileImg.setRelativeTarget(uploadButton, ActionListener.class);
		uploadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					if (CheckIsNull.isEmpty(fileImg.getFileName())) {
						alert("请选择要上传的图片。");
						return;
					}

					String fileName = fileImg.getFileName();
					String suffix = fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length());
					if (!changeSuffixIsPicture(suffix)) {
						alert("图片格式不正确，请上传后缀名为：" + getSuffixs() + " 的文件。");
						return;
					}

					InputStream in = fileImg.getInputStream(fileImg
							.getFileName());
					final byte[] b = new byte[in.available()];
					in.read(b);
					if (b.length > MAX_LENGTH) {
						alert("图片格式不正确，请传入30KB以下的图片。");
						in.close();
						return;
					}

					ImageDescriptor logoImg = DataImageDescriptor
							.createImageDescriptor(b, lblLogo);
					if (logoImg.getWidth() > MAX_WIDTH
							|| logoImg.getHeight() > MAX_HEIGHT) {
						alert("图片格式不正确，图片长度不能大于" + MAX_WIDTH + "像素，高度不能大于"
								+ MAX_HEIGHT + "像素。");
						in.close();
						logoImg.dispose();
						return;
					}

					lblLogo.setImage(logoImg);// 设置背景图片,预览功能
					lblLogo.getParent().layout();
					logoData = b;
					in.close();
					markDataChanged();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			private boolean changeSuffixIsPicture(String suffix) {
				for (String string : suffixs) {
					if (string.equals(suffix))
						return true;
				}
				return false;
			}

			private String getSuffixs() {
				StringBuilder buf = new StringBuilder();
				int i = 0;
				for (String string : suffixs) {
					if (i > 0)
						buf.append(",");
					i++;
					buf.append(string);
				}
				return buf.toString();
			}
		});

		Button btnSave = this.createControl(ID_Button_Save, Button.class);
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (processData()) {
					hint("保存成功");
				}
			}
		});

		// 初始化数据
		initData();

		//
		registerNotEmptyValidator(txt_shortName, "企业简称");
		registerNotEmptyValidator(txt_fullName, "企业全称");
	}

	// 加载图标如果为空则显示默认图标
	private void initData() {

		CompanyInfo company = getContext().get(CompanyInfo.class);
		tenantId = company.getId();
		if (company.getId() != null) {
			isCreate = false;
			if (company.getLogo() != null && company.getLogo().length > 0) {
				ImageDescriptor img = DataImageDescriptor
						.createImageDescriptor(company.getLogo(), lblLogo);
				lblLogo.setImage(img);
			}
			if (company.getSystemName() != null
					&& company.getSystemName().length() > 0) {
				lblTitle.setText(company.getSystemName());
			}
			txtTitle.setText(company.getSystemName());
			txt_shortName.setText(company.getCompanyShortName());
			txt_fullName.setText(company.getCompanyName());
			if (company.getProvince() != null)
				provinceList.setSelection(company.getProvince());
			if (company.getCity() != null)
				cityList.setSelection(company.getCity());
			if (company.getDistrict() != null)
				districtList.setSelection(company.getDistrict());
			txt_address.setText(company.getAddress());
			txt_phone.setText(company.getLandLineNumber());
			txt_fax.setText(company.getFaxNumber());
			txt_postCode.setText(company.getPostcode());
		}
		// else {
		// isCreate = true;
		// lblLogo.setImage(CompanyConfigProcessor.DEFAULF_LOGO);//设置默认图标
		// lblTitle.setText("企业信息化管理系统");
		// txtTitle.setText("企业信息化管理系统");
		// }
		// 鱼儿多号，从运营平台中读取，怎么获取?
		// lbl_saas_No.setText("京A00088");// 从运营平台中读取，不可编辑
	}

	public String getPromptMessage() {
		return null;
	}

	public boolean processData() {
		if (!validateInput()) {
			return false;
		}
		// 调用任务处理器持久化数据
		UpdateCompanyInfoTask task = new UpdateCompanyInfoTask();
		task.setId(tenantId);
		task.setCompanyName(txt_fullName.getText());
		task.setCompanyShortName(txt_shortName.getText());
		task.setAddress(txt_address.getText());
		task.setProvince(provinceList.getText());
		task.setCity(cityList.getText());
		task.setDistrict(districtList.getText());
		task.setPhone(txt_phone.getText());
		task.setPostcode(txt_postCode.getText());
		task.setFax(txt_fax.getText());
		if (logoData != null && logoData.length > 0) {
			task.setLogo(logoData);
		} else {
			CompanyInfo company = getContext().get(CompanyInfo.class);
			if (company != null) {
				task.setLogo(company.getLogo());
			}
		}
		task.setSystemName(txtTitle.getText());
		getContext().handle(task, UpdateCompanyInfoTask.Method.Update);
		// 通知上级页面，租户Logo和系统标题发生改变
		Display.getCurrent().getSituation()
				.bubbleMessage(new MsgCompanyInfoChanged());
		resetDataChangedstatus();
		return true;
	}
}