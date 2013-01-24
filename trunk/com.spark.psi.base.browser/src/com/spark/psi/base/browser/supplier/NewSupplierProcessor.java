package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.base.partner.entity.BankAccountItem;
import com.spark.psi.publish.base.partner.task.ItemOfSupplier;
import com.spark.psi.publish.base.partner.task.UpdatePartnerTask;
import com.spark.psi.publish.base.partner.task.UpdateSupplierTask;

/**
 * 新增供应商处理器
 */
public final class NewSupplierProcessor extends SupplierInfoProcessor {

	public final static String ID_Button_SaveAndNew = "Button_SaveAndNew";

	@Override
	public void process(Situation situation) {
		super.process(situation);

		this.createControl(ID_Button_SaveAndNew, Button.class, JWT.NONE).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!validateInput()) {
					return;
				}
				handleSaveAndNew();
			}
		});

		registerInputValidator(new TextInputValidator(contactNameText, "联系人姓名不能为空！") {

			@Override
			protected boolean validateText(String text) {
				if (!isValidationContact())
					return true;
				return !StringUtils.isEmpty(text);
			}
		});
		accountTable.setContentProvider(new SEditContentProvider() {

			public String[] getActionIds(Object element) {
				return new String[] { Action.Delete.name() };
			}

			public SNameValue[] getExtraData(Object element) {
				return null;
			}

			public Object getNewElement() {
				return GUID.randomID().toString();
			}

			public String getValue(Object element, String columnName) {
				return "";
			}

			public String getElementId(Object element) {
				if (element instanceof String) {
					return (String) element;
				}
				if (element instanceof BankAccountItem) {
					return ((BankAccountItem) element).getId().toString();
				}
				return null;
			}

			public Object[] getElements(Context context, STableStatus tableState) {
				return null;
			}

			public boolean isSelectable(Object element) {
				return false;
			}

			public boolean isSelected(Object element) {
				return false;
			}

		});
		accountTable.setLabelProvider(new SLabelProvider() {

			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}

			public String getText(Object element, int columnIndex) {
				return null;
			}

			public Color getForeground(Object element, int columnIndex) {
				return null;
			}

			public Color getBackground(Object element, int columnIndex) {
				return null;
			}
		});
		accountTable.render();
	}

	/**
	 * 是否验证联系人姓名 如果联系人手机号码，固话，邮箱任意一个不为空就需要验证联系人姓名
	 * 
	 * @return boolean
	 */
	private boolean isValidationContact() {
		return !StringUtils.isEmpty(contactEmailText.getText())
				|| !StringUtils.isEmpty(contactMobileNumberText.getText())
				|| !StringUtils.isEmpty(contactLandLineNumberText.getText());
	}

	protected final void fillTaskData(UpdatePartnerTask<?> stask) {
		UpdateSupplierTask task = (UpdateSupplierTask) stask;
		task.setShortName(shortNameText.getText());
		task.setName(fullNameText.getText());
		task.setType(this.typeList.getText());
		task.setAddress(addressText.getText());
		task.setPostcode(postCodeText.getText());
		task.setProvince(provinceList.getText());
		task.setCity(cityList.getText());
		task.setTown(districtList.getText());
		task.setVatNo(taxNumberText.getText());
		task.setNoticeAddress(this.noticeAddress.getText());
		task.setNoticePostcode(this.noticePost.getText());
		task.setLinkmanEmail(contactEmailText.getText());
		task.setLinkmanTel(contactLandLineNumberText.getText());
		task.setLinkmanMobile(contactMobileNumberText.getText());
		task.setLinkmanName(contactNameText.getText());
		task.setLinkmanSuffix(contactSexList.getText()); //
		task.setCreditAmount(Double.parseDouble(creditAmountText.getText() == null ? "0" : creditAmountText.getText()));
		task.setCreditAmount(creditAmountText.getDoubleValue(0));
		task.setAccountPeriod(Integer.parseInt(creditDayText.getText() == null ? "0" : creditDayText.getText()));
		task.setAccountRemind(Integer.parseInt(remindDayText.getText() == null ? "0" : remindDayText.getText()));
		task.setWorkTel(landLineNumberText.getText());
		task.setSupplierCooperation(wayList.getText());
		task.setFax(faxText.getText());
		if (CheckIsNull.isNotEmpty(taxRate.getText())) {
			task.setTaxRate(DoubleUtil.strToDouble(taxRate.getText()));
		}
		task.setRemark(memoText.getText());
		String[] rowIds = this.accountTable.getAllRowId();
		ItemOfSupplier[] items = new ItemOfSupplier[rowIds.length];
		int i = 0;
		for (String id : rowIds) {
			ItemOfSupplier item = new ItemOfSupplier();
			item.setAccount(accountTable.getEditValue(id, AcountTableName.acountNumber.name())[0]);
			item.setAccountHolder(accountTable.getEditValue(id, AcountTableName.acountName.name())[0]);
			item.setBankName(accountTable.getEditValue(id, AcountTableName.bank.name())[0]);
			item.setRemark(accountTable.getEditValue(id, AcountTableName.remark.name())[0]);
			item.setId(GUID.valueOf(id));
			items[i++] = item;
		}
		task.setItems(items);
	}

	@Override
	protected boolean isControlCreditRange() {
		return false;
	}

	protected void handleSave() {
		UpdateSupplierTask task = new UpdateSupplierTask();
		fillTaskData(task);
		getContext().handle(task, UpdatePartnerTask.Method.CREATE);
		// 回到列表页
		MsgRequest request = new MsgRequest(new PageControllerInstance(new PageController(SupplierListProcessor.class,
				SupplierListRender.class)));
		getContext().bubbleMessage(request);
	}

	protected void handleSaveAndNew() {
		UpdateSupplierTask task = new UpdateSupplierTask();
		fillTaskData(task);
		getContext().handle(task, UpdatePartnerTask.Method.CREATE);
		// 刷新新建页
		MsgRequest request = new MsgRequest(new PageControllerInstance(new PageController(NewSupplierProcessor.class,
				NewSupplierRender.class)));
		getContext().bubbleMessage(request);
	}

	@Override
	protected String getPartnerType() {
		return "供应商";
	}

	@Override
	protected void postDataSaved() {

	}

}
