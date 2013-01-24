package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.table.SActionListener;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.TradingRecordListEntity;
import com.spark.psi.publish.order.key.GetPurchaseOrderBySupplierKey;
import com.spark.psi.publish.order.key.GetSalesOrderByCustomerKey;

/**
 * 客户供应商订单列表处理器
 * 
 */
public abstract class PartnerOrderListProcessor extends
		PSIListPageProcessor<OrderItem> {

	final static String ID_TEXT_SEARCHTEXT = "Text_SearchText";

	public static enum Columns {
		/**
		 * 
		 */
		CreateDate(GetSalesOrderByCustomerKey.SortField.CreateDate,
				GetPurchaseOrderBySupplierKey.SortField.CreateDate),
		/**
		 * 
		 */
		SheetNumber(GetSalesOrderByCustomerKey.SortField.OrderNumber,
				GetPurchaseOrderBySupplierKey.SortField.OrderNumber),
		/**
		 * 
		 */
		SheetType(GetSalesOrderByCustomerKey.SortField.OrderType,
				GetPurchaseOrderBySupplierKey.SortField.OrderType),
		/**
		 * 
		 */
		Amount(GetSalesOrderByCustomerKey.SortField.Amount,
				GetPurchaseOrderBySupplierKey.SortField.Amount),
		/**
		 * 
		 */
		CreatePerson(GetSalesOrderByCustomerKey.SortField.CreatePerson,
				GetPurchaseOrderBySupplierKey.SortField.CreatePerson),
		/**
		 * 
		 */
		Processstatus(GetSalesOrderByCustomerKey.SortField.OrderStatus,
				GetPurchaseOrderBySupplierKey.SortField.OrderStatus);
		private GetSalesOrderByCustomerKey.SortField salesField;
		private GetPurchaseOrderBySupplierKey.SortField purchaseField;
		/**
		 * @param salesField
		 * @param purchaseField
		 */
		private Columns(
				GetSalesOrderByCustomerKey.SortField salesField,
				GetPurchaseOrderBySupplierKey.SortField purchaseField) {
			this.salesField = salesField;
			this.purchaseField = purchaseField;
		}
		public GetSalesOrderByCustomerKey.SortField getSalesField() {
			return salesField;
		}
		public GetPurchaseOrderBySupplierKey.SortField getPurchaseField() {
			return purchaseField;
		}
	}

	private Text searchText;

	protected PartnerInfo partnerInfo;
	
	protected TradingRecordListEntity entity;

	@Override
	public final void init(Situation situation) {
		super.init(situation);
		partnerInfo = this.getPartnerInfo((GUID) this.getArgument());
	}

	@Override
	public void process(Situation situation) {
		super.process(situation);
		searchText = this.createControl(ID_TEXT_SEARCHTEXT, Text.class);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		table.addActionListener(new SActionListener() {
			
			public void actionPerformed(String rowId, String actionName,
					String actionValue) {
				if(Columns.SheetNumber.name().equals(actionName)){
					inOrderDetail(rowId);
				}
			}
		});
	}
	
	/**
	 * 进入订单详情界面
	 * @param orderId void
	 */
	protected void inOrderDetail(String rowId){
		
	}

	public final Object[] getElements(Context context, STableStatus tablestatus) {
		return getElements(context, tablestatus, searchText.getText());
	}

	public abstract Object[] getElements(Context context,
			STableStatus tablestatus, String searchText);

	protected abstract PartnerInfo getPartnerInfo(GUID partnerId);
}
