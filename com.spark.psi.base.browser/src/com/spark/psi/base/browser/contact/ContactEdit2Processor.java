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
import com.spark.psi.base.browser.EnumEntitySource;
import com.spark.psi.publish.EnumType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;

/**
 * 联系人编辑界面处理器
 */
public class ContactEdit2Processor extends BaseFormPageProcessor {

	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_List_Sex = "List_Sex";
	public final static String ID_Text_Department = "Text_Department";
	public final static String ID_Text_Position = "Text_Position";
	public final static String ID_Text_LandLineNumber = "Text_LandLineNumber";
	public final static String ID_Text_MobileNo = "Text_MobileNo ";
	public final static String ID_Text_Email = "Text_Email";
	public final static String ID_Button_Confirm = "Button_Confirm ";
	public final static String ID_Button_Cancel = "Button_Cancel";

	private LWComboList sexList;
	private Text nameText;
	private Text departmentText;
	private Text positionText;
	private Text landLineNumberText;
	private Text mobileNumberText;
	private Text emailText;

	private ContactBookInfo contactBookInfo;

	@Override
	public void init(Situation context) {
		super.init(context);
		if (this.getArgument() != null) {
			this.contactBookInfo = (ContactBookInfo) this.getArgument();
		}
	}

	@Override
	public void process(Situation situation) {
		nameText = this.createControl(ID_Text_Name, Text.class);
		sexList = this.createControl(ID_List_Sex, LWComboList.class);
		departmentText = this.createControl(ID_Text_Department, Text.class);
		positionText = this.createControl(ID_Text_Position, Text.class);
		landLineNumberText = this.createControl(ID_Text_LandLineNumber,
				Text.class);
		mobileNumberText = this.createControl(ID_Text_MobileNo, Text.class);
		emailText = this.createControl(ID_Text_Email, Text.class);

		this.createControl(ID_Button_Confirm, Button.class).addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!validateInput()) {
							return;
						}
						EditData editData = new EditData();
						editData.setName(nameText.getText());
						editData.setSexCode(sexList.getText());
						editData.setDepartment(departmentText.getText());
						editData.setPosition(positionText.getText());
						editData.setLandLineNumber(landLineNumberText.getText());
						editData.setMobileNo(mobileNumberText.getText());
						editData.setEmail(emailText.getText());
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
		sexList.getList().setSource(new EnumEntitySource(EnumType.Sex));
		sexList.getList().setInput("");

		if (contactBookInfo != null) {
			nameText.setText(contactBookInfo.getName());
			sexList.setSelection(contactBookInfo.getSex());
			departmentText.setText(contactBookInfo.getDepartment());
			positionText.setText(contactBookInfo.getPosition());
			landLineNumberText.setText(contactBookInfo.getLandLineNumber());
			mobileNumberText.setText(contactBookInfo.getMobileNo());
			emailText.setText(contactBookInfo.getEmail());
		}
		registerNotEmptyValidator(nameText, "联系人姓名");
	}

	/**
	 *
	 */
	public final static class EditData {
		private String name;
		private String sexCode;
		private String department;
		private String position;
		private String landLineNumber;
		private String mobileNumber;
		private String email;

		/**
		 * @return the sexCode
		 */
		public String getSexCode() {
			return sexCode;
		}

		/**
		 * @param sexCode
		 *            the sexCode to set
		 */
		public void setSexCode(String sexCode) {
			this.sexCode = sexCode;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the department
		 */
		public String getDepartment() {
			return department;
		}

		/**
		 * @param department
		 *            the department to set
		 */
		public void setDepartment(String department) {
			this.department = department;
		}

		/**
		 * @return the position
		 */
		public String getPosition() {
			return position;
		}

		/**
		 * @param postion
		 *            the poistion to set
		 */
		public void setPosition(String position) {
			this.position = position;
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
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email
		 *            the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

	}

}
