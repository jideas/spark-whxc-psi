/**
 * 
 */
package com.spark.psi.account.browser;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.BrowserActionEvent;
import com.jiuqi.dna.ui.wt.events.BrowserActionListener;
import com.jiuqi.dna.ui.wt.graphics.ImageBorder;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.widgets.Browser;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.ImageBorderComposite;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.spark.psi.account.browser.internal.AccountImages;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.partner.entity.CustomerShortItem;
import com.spark.psi.publish.base.partner.entity.SupplierItem;
import com.spark.psi.publish.base.partner.key.GetShortCustomerListKey;
import com.spark.psi.publish.base.partner.key.GetSupplierListKey;
import com.spark.psi.publish.base.partner.key.GetShortCustomerListKey.SearchType;

/**
 * 
 *
 */
public class PartnerSelectPage extends Page {
	public enum ContentStyle {
		CustomerOnly, CustomerFirst, SupplierFirst
	}

	final static ImageBorder imageBorder = new ImageBorder(
			new ImageDescriptor[] {
					AccountImages.getImage("images/window2_Lists_lt.png"),
					AccountImages.getImage("images/window2_Lists_t.png"),
					AccountImages.getImage("images/window2_Lists_rt.png"),
					AccountImages.getImage("images/window2_Lists_r.png"),
					AccountImages.getImage("images/window2_Lists_rb.png"),
					AccountImages.getImage("images/window2_Lists_b.png"),
					AccountImages.getImage("images/window2_Lists_lb.png"),
					AccountImages.getImage("images/window2_Lists_l.png") });

	//
	private ImageBorderComposite imageBorderComposite;

	private Browser browser;
	private int columnCount = 2;
	private ContentStyle style;

	private PartnerSelectionMsg.PartnerType defaultType;
	private GUID defaultSelectId;

	private String searchText;

	public PartnerSelectPage(Composite parent, ContentStyle style) {
		super(parent);
		this.setLayout(new FillLayout());
		this.style = style;

		imageBorderComposite = new ImageBorderComposite(this);
		imageBorderComposite.setImageBorder(imageBorder);
		imageBorderComposite.setLayout(new FillLayout());

		loadContent();

		getContext().regMessageListener(PartnerSearchMsg.class,
				new MessageListener<PartnerSearchMsg>() {

					public void onMessage(Situation context,
							PartnerSearchMsg message,
							MessageTransmitter<PartnerSearchMsg> transmitter) {
						searchText = message.getSearchText();
						loadContent();
						PartnerSelectPage.this.layout();
					}
				});

		this.layout();
	}

	// public void setColumnCount(int columnCount) {
	// this.columnCount = columnCount;
	// loadContent();
	// }

	private void loadContent() {
		if (null != browser) {
			browser.dispose();
		}
		browser = new Browser(imageBorderComposite);
		browser.setHandlerName("nodeSelecting");
		StringBuffer buffer = new StringBuffer();
		buffer
				.append("<html><head></head><body topmargin='6' rightmargin='0' unselectable=\"on\" onselectstart=\"return false\" style=\"-moz-user-select:none;\" oncontextmenu=self.event.returnValue=false>");
		if (this.style.equals(ContentStyle.CustomerFirst)) {
//			defaultType = PartnerSelectionMsg.PartnerType.Customer;
			buffer.append(getLoadCustomerHTML());
			buffer.append("<br/>");
			buffer.append(getLoadSupplierHTML(false));
		} else if (this.style.equals(ContentStyle.SupplierFirst)) {
			buffer.append(getLoadSupplierHTML(true));
			buffer.append("<br/>");
			buffer.append(getLoadCustomerHTML());
		} else if (this.style.equals(ContentStyle.CustomerOnly)) {
			defaultType = PartnerSelectionMsg.PartnerType.Customer;
			this.columnCount = 1;
			buffer.append(getLoadCustomerHTML());
		}
//		buffer
//				.append("<script>window.onload= function test(){var inputs=document.getElementsByTagName('INPUT');if(inputs.length > 0 && inputs[0].type=='radio'){ inputs[0].click(); } }</script>");
		buffer.append("</body></html>");
		browser.applyHTML(buffer.toString());
		browser.addActionListener(new BrowserActionListener() {

			public void actionPerformed(BrowserActionEvent e) {
				String[] values = e.actionValue.split("_");
				PartnerSelectionMsg.PartnerType partnerType;
				if (values[0].equals("0")) {
					partnerType = PartnerSelectionMsg.PartnerType.Customer;
				} else {
					partnerType = PartnerSelectionMsg.PartnerType.Supplier;
				}
				getContext().broadcastMessage(
						new PartnerSelectionMsg(GUID.tryValueOf(values[1]),
								partnerType));
			}
		});
	}

	@SuppressWarnings("unchecked")
	private String getLoadCustomerHTML() {
		StringBuffer buffer = new StringBuffer();
		GetShortCustomerListKey key = new GetShortCustomerListKey();
		if(getContext().find(LoginInfo.class).hasAuth(Auth.Account)){
			key.setSearchType(SearchType.All);
		}
		if (searchText != null) {
			key.setSearchText(searchText);
		}
		ListEntity<CustomerShortItem> customerList = getContext().find(
				ListEntity.class, key);
		if (customerList.getItemList().size() > 0) {
			defaultType = defaultType == null ? PartnerSelectionMsg.PartnerType.Customer : defaultType;
			defaultSelectId = defaultSelectId == null ? customerList.getItemList().get(0).getId() : defaultSelectId;
		}
		if (!ContentStyle.CustomerOnly.equals(style)) {
			buffer.append("<font size='2'><strong>客户：</strong></font><br/>");
		}

		buffer.append("<table border='0' width='100%'>");
		for (int rowIndex = 0; rowIndex < getRowCount(customerList
				.getItemList().size()); rowIndex++) {
			buffer.append("<tr>");
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				int itemIndex = rowIndex * columnCount + columnIndex;
				if (itemIndex < customerList.getItemList().size()) {
					CustomerShortItem customer = customerList.getItemList().get(
							itemIndex);
					if(defaultSelectId != null && defaultSelectId.equals(customer.getId())) {
						buffer
						.append("<td width='180px' align='left'><input type='radio' id='c_" + customer.getId().toString() + "' checked='checked' name='partner' onclick=\"nodeSelecting('0_"
								+ customer.getId().toString()
								+ "')\" value='0_"
								+ customer.getId().toString()
								+ "' /><label for='c_" + customer.getId().toString() + "'><font size='2'>"
								+ customer.getShortName() + "</font></label></td>");
					} else {
						buffer
						.append("<td width='180px' align='left'><input type='radio' id='c_" + customer.getId().toString() + "' name='partner' onclick=\"nodeSelecting('0_"
								+ customer.getId().toString()
								+ "')\" value='0_"
								+ customer.getId().toString()
								+ "' /><label for='c_" + customer.getId().toString() + "'><font size='2'>"
								+ customer.getShortName() + "</font></label></td>");
					}
					
				}
			}
			buffer.append("</tr>");
		}
		buffer.append("</table>");
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	private String getLoadSupplierHTML(boolean isFirst) {
		StringBuffer buffer = new StringBuffer();
		GetSupplierListKey key = new GetSupplierListKey();
		key.setQueryAll(true);
		if (searchText != null) {
			key.setSearchText(searchText);
		}
		ListEntity<SupplierItem> supplierList = getContext().find(
				ListEntity.class, key);
		if(supplierList==null)return "";
		if (supplierList.getItemList().size() > 0) {
			defaultType = defaultType == null ? PartnerSelectionMsg.PartnerType.Supplier : defaultType;
			defaultSelectId = defaultSelectId == null ? supplierList.getItemList().get(0).getId() : defaultSelectId;
		}
		buffer.append("<font size='2'><strong>供应商：</strong></font><br/>");
		buffer.append("<table border='0' width='100%'>");
		for (int rowIndex = 0; rowIndex < getRowCount(supplierList
				.getItemList().size()); rowIndex++) {
			buffer.append("<tr>");
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
				int itemIndex = rowIndex * columnCount + columnIndex;
				if (itemIndex < supplierList.getItemList().size()) {
					SupplierItem supplier = supplierList.getItemList().get(
							itemIndex);
					if(defaultSelectId != null && defaultSelectId.equals(supplier.getId())) {
						buffer
							.append("<td width='180px' align='left'><input type='radio' id='s_" + supplier.getId().toString() + "' checked='checked' name='partner' onclick=\"nodeSelecting('1_"
									+ supplier.getId().toString()
									+ "')\" value='1_"
									+ supplier.getId().toString()
									+ "' /><label for='s_" + supplier.getId().toString() + "'><font size='2'>"
									+ supplier.getShortName() + "</font></label></td>");
					} else { 
						buffer
							.append("<td width='180px' align='left'><input type='radio' id='s_" + supplier.getId().toString() + "' name='partner' onclick=\"nodeSelecting('1_"
									+ supplier.getId().toString()
									+ "')\" value='1_"
									+ supplier.getId().toString()
									+ "' /><label for='s_" + supplier.getId().toString() + "'><font size='2'>"
									+ supplier.getShortName() + "</font></label></td>");
					}
				}
			}
			buffer.append("</tr>");
		}
		buffer.append("</table>");
		return buffer.toString();
	}

	/**
	 * 计算总行数
	 * 
	 * @param nodeCount
	 * @return
	 */
	private int getRowCount(int nodeCount) {
		if (0 == nodeCount)
			return 0;
		if (nodeCount % columnCount == 0) {
			return nodeCount / columnCount;
		} else {
			return nodeCount / columnCount + 1;
		}
	}

	public PartnerSelectionMsg.PartnerType getDefaultType() {
		return defaultType;
	}

	public GUID getDefaultSelectId() {
		return defaultSelectId;
	}

}
