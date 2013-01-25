package com.spark.psi.query.browser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
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
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckInItem;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckInListEntity;
import com.spark.psi.query.intf.publish.key.GetMaterialsCheckInListKey;

public class MaterialsCheckinSummaryProcessor<Item> extends AbstractSummaryProcessor<Item> {

	public static enum ColumnName {
		partnerNo("供应商编码"), partnerName("供应商名称"), purchaseSheetNo("采购订单号"), sheetNo("入库单号"), checkinDate("入库时间"), checkingInType(
				"入库类型"), goodsCode("材料编码"), goodsName("材料名称"), unit("单位"), count("入库数量"), price("单位成本"), amount("商品金额"), standardCost(
				"参考成本"), standardAmount("参考金额");

		private String title = null;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	private MaterialCheckInSearchCondition searchCondition = null;

	@Override
	public void process(Situation context) {
		super.process(context);
	}

	@Override
	public String getElementId(Object element) {
		MaterialsCheckInItem item = (MaterialsCheckInItem) element;
		return item.getSheetId().toString();
	}
	GetMaterialsCheckInListKey key;
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		key = new GetMaterialsCheckInListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		ListEntity<MaterialsCheckInItem> listEntity = getListEntity();
		setRecordCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE,listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new MaterialsCheckInItem[0]);
	}

	@Override
	protected void doAdvanceSearch() {
		// 高级搜索
		PageController pc = new PageController(MaterialCheckinAdvanceSearchProcessor.class,
				MaterialCheckinAdvanceSearchRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(390, 280);
		MsgRequest request = new MsgRequest(pci, "高级搜索", windowStyle);
		request.setResponseHandler(new ResponseHandler() {

			@Override
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null == returnValue)
					return;
				searchCondition = (MaterialCheckInSearchCondition) returnValue;
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	private ListEntity<MaterialsCheckInItem> getListEntity() {
		if (null != searchCondition) {
			key.setSheetNo(searchCondition.getSheetNo());
			key.setGoodsCode(searchCondition.getMaterialNo());
			key.setGoodsName(searchCondition.getMaterialName());
			key.setPurchaseSheetNo(searchCondition.getPurchaseSheetNo());
			key.setCheckinDateBegin(searchCondition.getCheckinDateBegin());
			key.setCheckinDateEnd(searchCondition.getCheckinDateEnd());
			key.setCheckingInType(searchCondition.getCheckingInType());
			key.setPartnerName(searchCondition.getPartnerName());
			key.setPartnerNo(searchCondition.getPartnerNo());
		}
		return getContext().find(MaterialsCheckInListEntity.class, key);
	}

	@Override
	protected void doExport() {
		final List<Object> datalist = table.getAllRows();
		Display.getCurrent().exportFile(getExportFileTitle() + QueryDataUtil.getDateNo() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {
					public void run(Context context, OutputStream outputStream) throws IOException {
						ExcelWriteHelperPoi writer = new ExcelWriteHelperPoi(datalist) {
							@Override
							protected String getText(Object element, int columnIndex) {
								MaterialsCheckInItem item = (MaterialsCheckInItem) element;
								switch (columnIndex) {
								case 0:
									return item.getPartnerNo();
								case 1:
									return item.getPartnerName();
								case 2:
									return item.getPurchaseSheetNo();
								case 3:
									return item.getSheetNo();
								case 4:
									return DateUtil.dateFromat(item.getCheckinDate());
								case 5:
									return item.getCheckingInType().getName();
								case 6:
									return item.getGoodsCode();
								case 7:
									return item.getGoodsName();
								case 8:
									return item.getUnit();
								case 9:
									return DoubleUtil.getRoundStr(item.getCount());
								case 10:
									return DoubleUtil.getRoundStr(item.getPrice());
								case 11:
									return DoubleUtil.getRoundStr(item.getAmount());
								case 12:
									return DoubleUtil.getRoundStr(item.getStandardCost());
								case 13:
									return DoubleUtil.getRoundStr(item.getStandardAmount());
								}
								return null;
							}

							@Override
							protected String[] getHead() {
								String[] columns = new String[14];
								columns[0] = MaterialsCheckinSummaryProcessor.ColumnName.partnerNo.getTitle();
								columns[1] = MaterialsCheckinSummaryProcessor.ColumnName.partnerName.getTitle();
								columns[2] = MaterialsCheckinSummaryProcessor.ColumnName.purchaseSheetNo.getTitle();
								columns[3] = MaterialsCheckinSummaryProcessor.ColumnName.sheetNo.getTitle();
								columns[4] = MaterialsCheckinSummaryProcessor.ColumnName.checkinDate.getTitle();
								columns[5] = MaterialsCheckinSummaryProcessor.ColumnName.checkingInType.getTitle();
								columns[6] = MaterialsCheckinSummaryProcessor.ColumnName.goodsCode.getTitle();
								columns[7] = MaterialsCheckinSummaryProcessor.ColumnName.goodsName.getTitle();
								columns[8] = MaterialsCheckinSummaryProcessor.ColumnName.unit.getTitle();
								columns[9] = MaterialsCheckinSummaryProcessor.ColumnName.count.getTitle();
								columns[10] = MaterialsCheckinSummaryProcessor.ColumnName.price.getTitle();
								columns[11] = MaterialsCheckinSummaryProcessor.ColumnName.amount.getTitle();
								columns[12] = MaterialsCheckinSummaryProcessor.ColumnName.standardCost.getTitle();
								columns[13] = MaterialsCheckinSummaryProcessor.ColumnName.standardAmount.getTitle();
								return columns;
							}
						};
						writer.writeNormal(outputStream, getExportFileTitle());
					}
				});

	}

	@Override
	protected void doReset() {
		searchCondition = null;
		table.render();
	}

	@Override
	protected String getExportFileTitle() {
		return "材料入库数据";
	}

}

class MaterialCheckInSearchCondition {
	private String partnerNo;
	private String partnerName;
	private String purchaseSheetNo;
	private String sheetNo;
	private long checkinDateBegin;
	private long checkinDateEnd;
	private CheckingInType checkingInType;
	private String materialNo;
	private String materialName;

	public String getPartnerNo() {
		return partnerNo;
	}

	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPurchaseSheetNo() {
		return purchaseSheetNo;
	}

	public void setPurchaseSheetNo(String purchaseSheetNo) {
		this.purchaseSheetNo = purchaseSheetNo;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public long getCheckinDateBegin() {
		return checkinDateBegin;
	}

	public void setCheckinDateBegin(long checkinDateBegin) {
		this.checkinDateBegin = checkinDateBegin;
	}

	public long getCheckinDateEnd() {
		return checkinDateEnd;
	}

	public void setCheckinDateEnd(long checkinDateEnd) {
		this.checkinDateEnd = checkinDateEnd;
	}

	public CheckingInType getCheckingInType() {
		return checkingInType;
	}

	public void setCheckingInType(String checkingInTypeCode) {
		if (StringUtils.isEmpty(checkingInTypeCode))
			return;
		this.checkingInType = CheckingInType.getCheckingInType(checkingInTypeCode);
	}

	public String getMaterialNo() {
		return materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

}
