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
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckOutItem;
import com.spark.psi.query.intf.publish.entity.MaterialsCheckOutListEntity;
import com.spark.psi.query.intf.publish.key.GetMaterialsCheckOutListKey;

public class ReceiptMaterialsCheckinSummaryProcessor<Item> extends AbstractSummaryProcessor<Item> {
	private ReceiptMaterialSearchCondition searchCondition = null;

	public static enum ColumnName {
		department("领用部门"), produceSheetNo("生产订单号"), sheetNo("出库单号"), createDate("出库日期"), checkingOutType("出库类型"), materialNo(
				"材料编码"), materialName("材料名称"), unit("计量单位"), count("出库数量"), cost("单位成本"), amount("出库金额");
		private String title = null;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	@Override
	public void process(Situation context) {
		super.process(context);
	}

	@Override
	public String getElementId(Object element) {
		MaterialsCheckOutItem item = (MaterialsCheckOutItem) element;
		return item.getSheetId().toString();
	}
	GetMaterialsCheckOutListKey key;
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		key = new GetMaterialsCheckOutListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		ListEntity<MaterialsCheckOutItem> listEntity = getListEntity();
		setRecordCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE, listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new MaterialsCheckOutItem[0]);
	}

	private ListEntity<MaterialsCheckOutItem> getListEntity() {
		key
				.setCheckingOutType(new CheckingOutType[] { CheckingOutType.Mateiral_Take,
						CheckingOutType.Mateiral_Return });
		if (null != searchCondition) {
			key.setSheetNo(searchCondition.getSheetNo());
			key.setGoodsCode(searchCondition.getMaterialNo());
			key.setGoodsName(searchCondition.getMaterialName());
			if (null != searchCondition.getCheckingOutType()) {
				key.setCheckingOutType(new CheckingOutType[] { searchCondition.getCheckingOutType() });
			}
			key.setCreateDateBegin(searchCondition.getCreateDateBegin());
			key.setCreateDateEnd(searchCondition.getCreateDateEnd());
			key.setDepartment(searchCondition.getDepartment());
			key.setProduceSheetNo(searchCondition.getProduceSheetNo());
		}
		return getContext().find(MaterialsCheckOutListEntity.class, key);
	}

	@Override
	protected void doAdvanceSearch() {
		// 高级搜索
		PageController pc = new PageController(ReceiptMaterialAdvanceSearchProcessor.class,
				ReceiptMaterialAdvanceSearchRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(390, 280);
		MsgRequest request = new MsgRequest(pci, "高级搜索", windowStyle);
		request.setResponseHandler(new ResponseHandler() {

			@Override
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null == returnValue)
					return;
				searchCondition = (ReceiptMaterialSearchCondition) returnValue;
				table.render();
			}
		});
		getContext().bubbleMessage(request);
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
								MaterialsCheckOutItem item = (MaterialsCheckOutItem) element;
								switch (columnIndex) {
								case 0:
									return item.getDepartment();
								case 1:
									return item.getProduceSheetNo();
								case 2:
									return item.getSheetNo();
								case 3:
									return DateUtil.dateFromat(item.getCreateDate());
								case 4:
									return item.getCheckingOutType().getName();
								case 5:
									return item.getGoodsCode();
								case 6:
									return item.getGoodsName();
								case 7:
									return item.getUnit();
								case 8:
									return DoubleUtil.getRoundStr(item.getCount());
								case 9:
									return DoubleUtil.getRoundStr(item.getCost());
								case 10:
									return DoubleUtil.getRoundStr(item.getAmount());
								}
								return null;
							}

							@Override
							protected String[] getHead() {
								String[] columns = new String[11];
								columns[0] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.department.getTitle();
								columns[1] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.produceSheetNo
										.getTitle();
								columns[2] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.sheetNo.getTitle();
								columns[3] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.createDate.getTitle();
								columns[4] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.checkingOutType
										.getTitle();
								columns[5] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.materialNo.getTitle();
								columns[6] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.materialName.getTitle();
								columns[7] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.unit.getTitle();
								columns[8] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.count.getTitle();
								columns[9] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.cost.getTitle();
								columns[10] = ReceiptMaterialsCheckinSummaryProcessor.ColumnName.amount.getTitle();
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
		return "领料数据";
	}

}

class ReceiptMaterialSearchCondition {
	private String department;
	private String produceSheetNo;
	private String sheetNo;
	private long createDateBegin;
	private long createDateEnd;
	private CheckingOutType checkingOutType;
	private String materialNo;
	private String materialName;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProduceSheetNo() {
		return produceSheetNo;
	}

	public void setProduceSheetNo(String produceSheetNo) {
		this.produceSheetNo = produceSheetNo;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
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

	public CheckingOutType getCheckingOutType() {
		return checkingOutType;
	}

	public void setCheckingOutType(String checkingOutTypeCode) {
		if (StringUtils.isEmpty(checkingOutTypeCode))
			return;
		this.checkingOutType = CheckingOutType.getCheckingOutType(checkingOutTypeCode);
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
