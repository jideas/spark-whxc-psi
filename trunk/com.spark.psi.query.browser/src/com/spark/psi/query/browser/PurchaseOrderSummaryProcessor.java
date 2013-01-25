package com.spark.psi.query.browser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.ExcelWriteHelperPoi;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.query.intf.publish.entity.PurchaseItem;
import com.spark.psi.query.intf.publish.entity.PurchaseListEntity;
import com.spark.psi.query.intf.publish.key.GetPurchaseListKey;

public class PurchaseOrderSummaryProcessor<Item> extends AbstractSummaryProcessor<Item> {

	public static enum ColumnName {
		supplierNo("供应商编码"), supplierName("供应商名称"), billsNo("采购订单号"), materialCode("材料编码"), materialName("材料名称"), unit(
				"计量单位"), count("订单数量"), price("采购单价"), standardPrice("参考价格"), amount("订单金额"), checkedinCount("交货数量"), checkedinAmount(
				"交货金额"), checkinCount("未交货数量"), checkinAmount("未交货金额"), createDate("下单日期"), deliveryDate("到货日期"), status(
				"执行状态");

		private String title = null;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	private PurchaseSearchCondition advanceSearchCondition = null;

	@Override
	public void process(Situation context) {
		super.process(context);

		final Button resetButton = createButtonControl(ID_Button_Reset);
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	@Override
	public String getElementId(Object element) {
		PurchaseItem item = (PurchaseItem) element;
		return item.getBillsId().toString();
	}

	GetPurchaseListKey key = null;

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		key = new GetPurchaseListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		ListEntity<PurchaseItem> listEntity = getListEntity();
		setRecordCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE, listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new PurchaseItem[0]);
	}

	private ListEntity<PurchaseItem> getListEntity() {
		if (advanceSearchCondition != null) {
			key.setSupplierNo(advanceSearchCondition.getSupplierCode());
			key.setSupplierName(advanceSearchCondition.getSupplierName());
			key.setBillsNo(advanceSearchCondition.getBillsNo());
			key.setGoodsCode(advanceSearchCondition.getMaterialCode());
			key.setGoodsName(advanceSearchCondition.getMaterialName());
			key.setCreateDateBegin(advanceSearchCondition.getCreateDateBegin());
			key.setCreateDateEnd(advanceSearchCondition.getCreateDateEnd());
			key.setDeliveryDateBegin(advanceSearchCondition.getDeliverDateBegin());
			key.setDeliveryDateEnd(advanceSearchCondition.getDeliverDateEnd());
		}
		return getContext().find(PurchaseListEntity.class, key);
	}

	@Override
	protected void doAdvanceSearch() {
		// 高级搜索
		PageController pc = new PageController(PurhcaseOrderAdvanceSearchProcessor.class,
				PurhcaseOrderAdvanceSearchRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(390, 280);
		MsgRequest request = new MsgRequest(pci, "高级搜索", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			@Override
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null == returnValue)
					return;
				advanceSearchCondition = (PurchaseSearchCondition) returnValue;
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	protected void doExport() {
		final List<Object> datalist = table.getAllRows();
		Display.getCurrent().exportFile(getExportFileTitle() + QueryDataUtil.getDateNo() + ".xls",
				"application/vnd.ms-excel", 1000000, new ExporterWithContext() {
					public void run(Context context, OutputStream outputStream) throws IOException {
						ExcelWriteHelperPoi writer = new ExcelWriteHelperPoi(datalist) {
							@Override
							protected String getText(Object element, int columnIndex) {
								PurchaseItem item = (PurchaseItem) element;
								switch (columnIndex) {
								case 0:
									return item.getSupplierNo();
								case 1:
									return item.getSupplierName();
								case 2:
									return item.getBillsNo();
								case 3:
									return item.getGoodsCode();
								case 4:
									return item.getGoodsName();
								case 5:
									return item.getUnit();
								case 6:
									return DoubleUtil.getRoundStr(item.getCount());
								case 7:
									return DoubleUtil.getRoundStr(item.getPrice());
								case 8:
									return DoubleUtil.getRoundStr(item.getStandardPrice());
								case 9:
									return DoubleUtil.getRoundStr(item.getAmount());
								case 10:
									return DoubleUtil.getRoundStr(item.getCheckedinCount());
								case 11:
									return DoubleUtil.getRoundStr(item.getCheckedinAmount());
								case 12:
									return DoubleUtil.getRoundStr(item.getCheckinCount());
								case 13:
									return DoubleUtil.getRoundStr(item.getCheckinAmount());
								case 14:
									return DateUtil.dateFromat(item.getCreateDate());
								case 15:
									return DateUtil.dateFromat(item.getDeliveryDate());
								case 16:
									return item.getStatus().getName();
								default:
									return null;
								}
							}

							@Override
							protected String[] getHead() {
								String[] columns = new String[17];
								columns[0] = PurchaseOrderSummaryProcessor.ColumnName.supplierNo.getTitle();
								columns[1] = PurchaseOrderSummaryProcessor.ColumnName.supplierName.getTitle();
								columns[2] = PurchaseOrderSummaryProcessor.ColumnName.billsNo.getTitle();
								columns[3] = PurchaseOrderSummaryProcessor.ColumnName.materialCode.getTitle();
								columns[4] = PurchaseOrderSummaryProcessor.ColumnName.materialName.getTitle();
								columns[5] = PurchaseOrderSummaryProcessor.ColumnName.unit.getTitle();
								columns[6] = PurchaseOrderSummaryProcessor.ColumnName.count.getTitle();
								columns[7] = PurchaseOrderSummaryProcessor.ColumnName.price.getTitle();
								columns[8] = PurchaseOrderSummaryProcessor.ColumnName.standardPrice.getTitle();
								columns[9] = PurchaseOrderSummaryProcessor.ColumnName.amount.getTitle();
								columns[10] = PurchaseOrderSummaryProcessor.ColumnName.checkedinCount.getTitle();
								columns[11] = PurchaseOrderSummaryProcessor.ColumnName.checkedinAmount.getTitle();
								columns[12] = PurchaseOrderSummaryProcessor.ColumnName.checkinCount.getTitle();
								columns[13] = PurchaseOrderSummaryProcessor.ColumnName.checkinAmount.getTitle();
								columns[14] = PurchaseOrderSummaryProcessor.ColumnName.createDate.getTitle();
								columns[15] = PurchaseOrderSummaryProcessor.ColumnName.deliveryDate.getTitle();
								columns[16] = PurchaseOrderSummaryProcessor.ColumnName.status.getTitle();
								return columns;
							}
						};
						writer.writeNormal(outputStream, getExportFileTitle());
					}
				});

	}

	@Override
	protected void doReset() {
		advanceSearchCondition = null;
		table.render();
	}

	@Override
	protected String getExportFileTitle() {
		return "采购数据";
	}

}

class PurchaseSearchCondition {
	// 价格类型 决定参考价格
	// 字段选择
	private String supplierCode;
	private String supplierName;
	private String billsNo;
	private String materialCode;
	private String materialName;
	private long createDateBegin;
	private long createDateEnd;
	private long deliverDateBegin;
	private long deliverDateEnd;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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

	public long getDeliverDateBegin() {
		return deliverDateBegin;
	}

	public void setDeliverDateBegin(long deliverDateBegin) {
		this.deliverDateBegin = deliverDateBegin;
	}

	public long getDeliverDateEnd() {
		return deliverDateEnd;
	}

	public void setDeliverDateEnd(long deliverDateEnd) {
		this.deliverDateEnd = deliverDateEnd;
	}

}
