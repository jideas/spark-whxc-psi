package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SEditContentProvider;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.base.partner.entity.BankAccountItem;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.base.partner.task.ItemOfSupplier;
import com.spark.psi.publish.base.partner.task.UpdatePartnerTask;
import com.spark.psi.publish.base.partner.task.UpdateSupplierTask;

/**
 * 新增供应商处理器
 */
public final class EditSupplierProcessor extends SupplierInfoProcessor {
	protected SupplierInfo partnerInfo;

	public void init(Situation context) {
		super.init(context);
		this.partnerInfo = getPartnerInfo((GUID) this.getArgument());
		this.id = partnerInfo.getId();

	}

	public void process(final Situation context) {
		super.process(context);
		//
		shortNameText.setText(partnerInfo.getShortName());
		fullNameText.setText(partnerInfo.getName());
		provinceList.setSelection(partnerInfo.getProvince());
		cityList.setSelection(partnerInfo.getCity());
		districtList.setSelection(partnerInfo.getTown());
		addressText.setText(partnerInfo.getAddress());
		postCodeText.setText(partnerInfo.getPostcode());
		landLineNumberText.setText(partnerInfo.getWorkTel());
		noticeAddress.setText(partnerInfo.getNoticeAddress());
		noticePost.setText(partnerInfo.getNoticePostcode());
		numberText.setText(partnerInfo.getNumber());
		this.contactNameText.setText(partnerInfo.getLinkmanName());
		contactSexList.setSelection(partnerInfo.getLinkmanSuffix());
		contactMobileNumberText.setText(partnerInfo.getLinkmanMobile());
		contactLandLineNumberText.setText(partnerInfo.getLinkmanTel());
		contactEmailText.setText(partnerInfo.getLinkmanEmail());
		taxRate.setText(DoubleUtil.getRoundStr(partnerInfo.getTaxRate()));
		taxNumberText.setText(partnerInfo.getVatNo());
		faxText.setText(partnerInfo.getFax());
		((SNumberText) creditAmountText).setValue(partnerInfo.getCreditAmount());
		creditDayText.setText(String.valueOf(partnerInfo.getAccountPeriod()));
		remindDayText.setText(String.valueOf(partnerInfo.getAccountRemind()));
		memoText.setText(partnerInfo.getRemark());
		typeList.setSelection(partnerInfo.getSupplierType());
		wayList.setSelection(partnerInfo.getSupplierCooperation());
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
				if (null == element || element instanceof String) {
					return "";
				}
				BankAccountItem item = (BankAccountItem) element;
				if (AcountTableName.bank.name().equals(columnName)) {
					return item.getBankName();
				}
				if (AcountTableName.acountName.name().equals(columnName)) {
					return item.getAccountHolder();
				}
				if (AcountTableName.acountNumber.name().equals(columnName)) {
					return item.getAccount();
				}
				if (AcountTableName.remark.name().equals(columnName)) {
					return item.getRemark();
				}
				return "";
			}

			public String getElementId(Object element) {
				if (element instanceof String) {
					return (String) element;
				} else if (element instanceof BankAccountItem) {
					return ((BankAccountItem) element).getId().toString();
				}
				return null;
			}

			public Object[] getElements(Context context, STableStatus tableState) {
				return partnerInfo.getBanks().toArray();
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
				if (null == element || element instanceof String) {
					return "";
				}
				BankAccountItem item = (BankAccountItem) element;
				switch (columnIndex) {
				case 0:
					return item.getBankName();
				case 1:
					return item.getAccountHolder();
				case 2:
					return item.getAccount();
				case 3:
					return item.getRemark();
				default:
					break;
				}
				return "";
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

	protected final void fillTaskData(UpdateSupplierTask task) {
		task.setId(partnerInfo.getId());
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
			item.setId(GUID.valueOf(id));
			try {
				item.setAccount(accountTable.getEditValue(id, AcountTableName.acountNumber.name())[0]);
				item.setAccountHolder(accountTable.getEditValue(id, AcountTableName.acountName.name())[0]);
				item.setBankName(accountTable.getEditValue(id, AcountTableName.bank.name())[0]);
				item.setRemark(accountTable.getEditValue(id, AcountTableName.remark.name())[0]);
			} catch (NullPointerException e) {
				continue;
			}
			items[i++] = item;
		}
		task.setItems(items);

	}

	protected final void postDataSaved() {
		hint("保存成功");
	}

	@Override
	protected boolean isControlCreditRange() {
		return false;
	}

	protected void handleSave() {
		UpdateSupplierTask task = new UpdateSupplierTask();
		fillTaskData(task);
		getContext().handle(task, UpdatePartnerTask.Method.UPDATE);
		getContext().bubbleMessage(new MsgResponse(false));
	}

	protected SupplierInfo getPartnerInfo(GUID id) {
		return getContext().find(SupplierInfo.class, id);
	}

	@Override
	protected String getPartnerType() {
		return "供应商";
	}
}
