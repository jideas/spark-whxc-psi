package com.spark.psi.base.browser.contact;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;

public class DeliverAddressEditProcessor extends BaseFormPageProcessor {

	public final static String ID_List_Province = "List_Province";
	public final static String ID_List_City = "List_City";
	public final static String ID_List_District = "ID_List_District";
	public final static String ID_Text_Address = "Text_Address";
	public final static String ID_Text_PostCode = "Text_PostCode";
	public final static String ID_Text_Consignee = "Text_Consignee";
	public final static String ID_Text_LandLineNumber = "Text_LandLineNumber";
	public final static String ID_Text_MobileNo = "Text_MobileNo ";
	public final static String ID_Button_Confirm = "Button_Confirm ";
	public final static String ID_Button_Cancel = "Button_Cancel";

	protected LWComboList provinceList;
	protected LWComboList cityList;
	protected LWComboList districtList;
	protected Text addressText;
	protected Text postCodeText;
	protected Text consigneeText;
	protected Text landLineNumberText;
	protected Text mobileNumberText;

	private ContactBookInfo contactBookInfo;

	@Override
	public void init(Situation context) {
		super.init(context);
		if (this.getArgument() != null) {
			this.contactBookInfo = (ContactBookInfo) this.getArgument();
		}
	}

	@Override
	public void process(Situation context) {
		provinceList = this.createControl(ID_List_Province, LWComboList.class);
		cityList = this.createControl(ID_List_City, LWComboList.class);
		districtList = this.createControl(ID_List_District, LWComboList.class);
		addressText = this.createControl(ID_Text_Address, Text.class);
		postCodeText = this.createControl(ID_Text_PostCode, Text.class);
		consigneeText = this.createControl(ID_Text_Consignee, Text.class);
		landLineNumberText = this.createControl(ID_Text_LandLineNumber,
				Text.class);
		mobileNumberText = this.createControl(ID_Text_MobileNo, Text.class);
		this.createControl(ID_Button_Confirm, Button.class).addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!validateInput()) {
							return;
						}
						EditData editData = new EditData();
						editData.setAddress(addressText.getText());
						editData.setPostCode(postCodeText.getText());
						editData.setConsignee(consigneeText.getText());
						editData.setLandLineNumber(landLineNumberText.getText());
						editData.setMobileNo(mobileNumberText.getText());
						editData.setProvinceCode(provinceList.getText());
						editData.setCityCode(cityList.getText());
						editData.setDistrictCode(districtList.getText());
						getContext().bubbleMessage(
								new MsgResponse(true, editData));
					}
				});
		this.createControl(ID_Button_Cancel, Button.class).addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContext().bubbleMessage(new MsgCancel());
					}
				});

		//
		PSIProcessorUtils.initAreaSource(provinceList, cityList, districtList);

		if (contactBookInfo != null) {
			provinceList.setSelection(contactBookInfo.getProvince());
			cityList.setSelection(contactBookInfo.getCity());
			districtList.setSelection(contactBookInfo.getDistrict());
			addressText.setText(contactBookInfo.getAddress());
			postCodeText.setText(contactBookInfo.getPostCode());
			consigneeText.setText(contactBookInfo.getName());
			landLineNumberText.setText(contactBookInfo.getLandLineNumber());
			mobileNumberText.setText(contactBookInfo.getMobileNo());
		}
		//
		registerNotEmptyValidator(provinceList, "省（直辖市）");
		registerNotEmptyValidator(cityList, "市（区）");
		registerNotEmptyValidator(districtList, "区（县）");
		registerNotEmptyValidator(addressText, "详细地址");
	}

	/**
	 *
	 */
	public final static class EditData {
		private String consignee;
		private String address;
		private String postCode;
		private String landLineNumber;
		private String mobileNumber;
		private String provinceCode;
		private String cityCode;
		private String districtCode;

		/**
		 * @return the consignee
		 */
		public String getConsignee() {
			return consignee;
		}

		/**
		 * @param consignee
		 *            the consignee to set
		 */
		public void setConsignee(String consignee) {
			this.consignee = consignee;
		}

		/**
		 * @return the address
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * @param address
		 *            the address to set
		 */
		public void setAddress(String address) {
			this.address = address;
		}

		/**
		 * @return the postCode
		 */
		public String getPostCode() {
			return postCode;
		}

		/**
		 * @param postCode
		 *            the postCode to set
		 */
		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}

		/**
		 * @return the landLineNumber
		 */
		public String getLandLineNumber() {
			return landLineNumber;
		}

		/**
		 * @param landLineNumber
		 *            the landLineNumber to set
		 */
		public void setLandLineNumber(String landLineNumber) {
			this.landLineNumber = landLineNumber;
		}

		/**
		 * @return the mobileNumber
		 */
		public String getMobileNo() {
			return mobileNumber;
		}

		/**
		 * @param mobileNumber
		 *            the mobileNumber to set
		 */
		public void setMobileNo(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		/**
		 * @return the provinceCode
		 */
		public String getProvinceCode() {
			return provinceCode;
		}

		/**
		 * @param provinceCode
		 *            the provinceCode to set
		 */
		public void setProvinceCode(String provinceCode) {
			this.provinceCode = provinceCode;
		}

		/**
		 * @return the cityCode
		 */
		public String getCityCode() {
			return cityCode;
		}

		/**
		 * @param cityCode
		 *            the cityCode to set
		 */
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}

		/**
		 * @return the districtCode
		 */
		public String getDistrictCode() {
			return districtCode;
		}

		/**
		 * @param districtCode
		 *            the districtCode to set
		 */
		public void setDistrictCode(String districtCode) {
			this.districtCode = districtCode;
		}

	}
}
