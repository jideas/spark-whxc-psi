package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Item;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.util.StringUtils;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.RetailPartner;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerShortItem;
import com.spark.psi.publish.base.partner.task.MarkPartnerUsedTask;

public abstract class PartnerSelectProcessor extends PSIListPageProcessor<PartnerShortItem> {

	public final static String ID_Label_PartnerInfo = "Label_PartnerInfo";
	public final static String ID_Button_New = "Button_New";
	public final static String ID_Button_Confirm = "Button_Confirm";
	public final static String ID_Button_Cancel = "Button_Cancel";

	public final static String ID_Text_Search = "Text_Search";

	private ComboList contactList;
	private ComboList deliveryList;

	private Text searchText;

	private Button confirmButton;

	// protected boolean contactSelect;
	// protected boolean delieverySelect;
	// protected boolean enableCreate;
	protected GUID partnerId;

	public void init(final Situation context) {
		super.init(context);
		// contactSelect = (Boolean) this.getArgument();
		// delieverySelect = (Boolean) this.getArgument2();
		// enableCreate = (Boolean) this.getArgument3();
		if (this.getArgument() != null) {
			partnerId = (GUID) this.getArgument4();
		}
		// XXX：由于目前客户选择中没有列出零售客户，为了保证选择一个客户，这里判断如果是零售客户，则设置为null
		if (partnerId != null && partnerId.equals(RetailPartner.Customer.getId())) {
			partnerId = null;
		}
	}

	public void process(final Situation context) {
		super.process(context);

		searchText = this.createControl(ID_Text_Search, Text.class);
		searchText.setText("");

		searchText.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				partnerId = null;
				table.render();
				handleSelection();
			}
		});
		confirmButton = this.createControl(ID_Button_Confirm, Button.class);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUID selectoin = GUID.tryValueOf(table.getSelection());
				if (selectoin == null) {
					return;
				}
				GUID contactId = null;
				GUID deliveryId = null;
				if (contactList != null && !StringUtils.isEmpty(contactList.getText())) {
					Item[] selection = contactList.getList().getSelection();
					if (selection != null && selection.length > 0) {
						contactId = ((ContactBookInfo) selection[0].getData()).getId();
					}
				}
				if (deliveryList != null && !StringUtils.isEmpty(deliveryList.getText())) {
					Item[] selection = deliveryList.getList().getSelection();
					if (selection != null && selection.length > 0) {
						deliveryId = ((ContactBookInfo) selection[0].getData()).getId();
					}
				}
				// 触发客户供应商选择事件
				getContext().handle(new MarkPartnerUsedTask(selectoin, null));

				getContext().bubbleMessage(new MsgResponse(true, GUID.valueOf(table.getSelection()), contactId, deliveryId));
			}
		});
		this.createControl(ID_Button_Cancel, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgCancel());
			}
		});

		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				handleSelection();
			}
		});

	}

	public void postProcess(Situation context) {
		super.postProcess(context);
		handleSelection();
	}

	private void handleSelection() {
		PartnerInfo partnerInfo = null;
		String selection = table.getSelection();
		if (!StringUtils.isEmpty(selection)) {
			partnerId = GUID.valueOf(selection);
			partnerInfo = getPartnerInfo(partnerId);
		}

		//
		confirmButton.setEnabled(partnerInfo != null);
	}

	public boolean isSelected(Object element) {
		PartnerShortItem item = (PartnerShortItem) element;
		if (partnerId == null) {
			partnerId = item.getId(); // 如果没有传入partnerId，则选中第一个
		}
		return item.getId().equals(partnerId);
	}

	@Override
	public final Object[] getElements(Context context, STableStatus tablestatus) {
		return this.getElements(context, searchText.getText(), tablestatus);
	}

	@Override
	public String getElementId(Object element) {
		return ((PartnerShortItem) element).getId().toString();
	}

	public abstract Object[] getElements(Context context, String searchText, STableStatus tablestatus);

	protected abstract PartnerInfo getPartnerInfo(GUID id);

	protected abstract String getPartnerType();

	protected abstract PageController getNewPartnerPageController();

}
