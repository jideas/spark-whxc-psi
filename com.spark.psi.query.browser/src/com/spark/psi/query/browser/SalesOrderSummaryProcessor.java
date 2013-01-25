package com.spark.psi.query.browser;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.query.intf.publish.entity.SalesItem;
import com.spark.psi.query.intf.publish.entity.SalesListEntity;
import com.spark.psi.query.intf.publish.key.GetSalesListKey;

public class SalesOrderSummaryProcessor<Item> extends AbstractSummaryProcessor<Item> {

	public static enum ColumnName {
		customerNo("客户编码"), customerName("客户名称"), sheetNo("销售订单号"), createDate("下单日期"), deliveryDate("订单交货日期"), goodsCode(
				"材料编码"), goodsNo("材料条码"), goodsName("材料名称"), price("单价"), count("订单数量"), amount("订单金额"), checkedoutCount(
				"交货数量"), checkedoutAmount("交货金额"), checkoutCount("未交货数量"), checkoutAmount("未交货金额");

		private String title = null;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	private SaleOrderSearchCondition searchCondition = null;

	@Override
	public void process(Situation context) {
		super.process(context);
	}

	@Override
	public String getElementId(Object element) {
		SalesItem item = (SalesItem) element;
		return item.getBillsId().toString();
	}
	GetSalesListKey key;
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		key = new GetSalesListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		ListEntity<SalesItem> listEntity = getListEntity();
		setRecordCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE, listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new SalesItem[0]);
	}

	private ListEntity<SalesItem> getListEntity() {
		if (null != searchCondition) {
			key.setBillsNo(searchCondition.getSheetNo());
			key.setCreateDateBegin(searchCondition.getCreateDateBegin());
			key.setCreateDateEnd(searchCondition.getCreateDateEnd());
			key.setCustomerName(searchCondition.getCustomerName());
			key.setDeliveryDateBegin(searchCondition.getDeliveryDateBegin());
			key.setDeliveryDateEnd(searchCondition.getDeliveryDateEnd());
			key.setGoodsCode(searchCondition.getGoodsCode());
			key.setGoodsNo(searchCondition.getGoodsNo());
			key.setGoodsName(searchCondition.getGoodsName());
		}
		return getContext().find(SalesListEntity.class, key);
	}

	@Override
	protected void doAdvanceSearch() {
		// 高级搜索
		PageController pc = new PageController(SaleOrderAdvaceSearchProcessor.class, SaleOrderAdvaceSearchRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(390, 280);
		MsgRequest request = new MsgRequest(pci, "高级搜索", windowStyle);
		request.setResponseHandler(new ResponseHandler() {

			@Override
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null == returnValue)
					return;
				searchCondition = (SaleOrderSearchCondition) returnValue;
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	protected void doExport() {
		table.exportDatas("销售数据" + QueryDataUtil.getDateNo() + ".xls", "application/vnd.ms-excel", 102400000, "销售数据");
//		final List<SalesItem> datalist = getListEntity().getItemList();
//		Display.getCurrent().exportFile("销售数据" + QueryDataUtil.getDateNo() + ".xls", "application/vnd.ms-excel",
//				1000000, new ExporterWithContext() {
//					public void run(Context context, OutputStream outputStream) throws IOException {
//						ExcelWriteHelper writer = new ExcelWriteHelper(datalist) {
//							@Override
//							protected String getText(Object element, int columnIndex) {
//								SalesItem item = (SalesItem) element;
//								switch (columnIndex) {
//								case 0:
//									return item.getCustomerNo();
//								case 1:
//									return item.getCustomerName();
//								case 2:
//									return item.getBillsNo();
//								case 3:
//									return DateUtil.dateFromat(item.getCreateDate());
//								case 4:
//									return DateUtil.dateFromat(item.getDeliveryDate());
//								case 5:
//									return item.getGoodsCode();
//								case 6:
//									return item.getGoodsNo();
//								case 7:
//									return item.getGoodsName();
//								case 8:
//									return DoubleUtil.getRoundStr(item.getPrice());
//								case 9:
//									return DoubleUtil.getRoundStr(item.getCount());
//								case 10:
//									return DoubleUtil.getRoundStr(item.getAmount());
//								case 11:
//									return DoubleUtil.getRoundStr(item.getCheckedoutCount());
//								case 12:
//									return DoubleUtil.getRoundStr(item.getCheckedoutAmount());
//								case 13:
//									return DoubleUtil.getRoundStr(item.getCheckoutCount());
//								case 14:
//									return DoubleUtil.getRoundStr(item.getCheckoutAmount());
//								}
//								return null;
//							}
//
//							@Override
//							protected String[] getHead() {
//								String[] columns = new String[15];
//								columns[0] = SalesOrderSummaryProcessor.ColumnName.customerNo.getTitle();
//								columns[1] = SalesOrderSummaryProcessor.ColumnName.customerName.getTitle();
//								columns[2] = SalesOrderSummaryProcessor.ColumnName.sheetNo.getTitle();
//								columns[3] = SalesOrderSummaryProcessor.ColumnName.createDate.getTitle();
//								columns[4] = SalesOrderSummaryProcessor.ColumnName.deliveryDate.getTitle();
//								columns[5] = SalesOrderSummaryProcessor.ColumnName.goodsCode.getTitle();
//								columns[6] = SalesOrderSummaryProcessor.ColumnName.goodsNo.getTitle();
//								columns[7] = SalesOrderSummaryProcessor.ColumnName.goodsName.getTitle();
//								columns[8] = SalesOrderSummaryProcessor.ColumnName.price.getTitle();
//								columns[9] = SalesOrderSummaryProcessor.ColumnName.count.getTitle();
//								columns[10] = SalesOrderSummaryProcessor.ColumnName.amount.getTitle();
//								columns[11] = SalesOrderSummaryProcessor.ColumnName.checkedoutCount.getTitle();
//								columns[12] = SalesOrderSummaryProcessor.ColumnName.checkedoutAmount.getTitle();
//								columns[13] = SalesOrderSummaryProcessor.ColumnName.checkoutCount.getTitle();
//								columns[14] = SalesOrderSummaryProcessor.ColumnName.checkoutAmount.getTitle();
//								return columns;
//							}
//						};
//						writer.writeNormal(outputStream, "采购数据");
//					}
//				});

	}

	@Override
	protected void doReset() {
		// 清空搜索条件
		searchCondition = null;
		table.render();
	}

	@Override
	protected String getExportFileTitle() {
		return "销售数据";
	}

}

class SaleOrderSearchCondition {
	private String customerName;
	private String sheetNo;
	private String goodsCode;
	private String goodsNo;
	private String goodsName;
	private long createDateBegin;
	private long createDateEnd;
	private long deliveryDateBegin;
	private long deliveryDateEnd;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public long getCreateDateBegin() {
		return createDateBegin;
	}

	public void setCreateDateBegin(long createDateBegin) {
		this.createDateBegin = createDateBegin;
	}

	public long getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(long createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public long getDeliveryDateBegin() {
		return deliveryDateBegin;
	}

	public void setDeliveryDateBegin(long deliveryDateBegin) {
		this.deliveryDateBegin = deliveryDateBegin;
	}

	public long getDeliveryDateEnd() {
		return deliveryDateEnd;
	}

	public void setDeliveryDateEnd(long deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
	}

}
