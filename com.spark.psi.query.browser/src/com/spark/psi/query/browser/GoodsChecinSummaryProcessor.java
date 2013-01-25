package com.spark.psi.query.browser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
import com.spark.psi.publish.ListEntity;
import com.spark.psi.query.intf.publish.entity.GoodsCheckInItem;
import com.spark.psi.query.intf.publish.entity.GoodsCheckInListEntity;
import com.spark.psi.query.intf.publish.key.GetGoodsCheckInListKey;

public class GoodsChecinSummaryProcessor<Item> extends AbstractSummaryProcessor<Item> {

	public static enum ColumnName {
		department("生产部门"), produceSheetNo("生产订单号"), sheetNo("入库单号"), goodsCode("商品编号"), goodsName("商品名称"), isNeedProduce(
				"是否需要生产"), unit("计量单位"), cost("单位成本"), count("入库数量"), amount("入库金额"), createDate("入库日期"), standardCost(
				"参考成本"), standardAmount("参考金额");

		private String title = null;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	private GoodsCheckinAdvanceSearch searchCondition = null;

	@Override
	public void process(Situation context) {
		super.process(context);
	}

	@Override
	public String getElementId(Object element) {
		GoodsCheckInItem item = (GoodsCheckInItem) element;
		return item.getSheetId().toString();
	}
	GetGoodsCheckInListKey key;
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		key = new GetGoodsCheckInListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		ListEntity<GoodsCheckInItem> listEntity = getListEntity();
		setRecordCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE, listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new GoodsCheckInItem[0]);
	}
	private ListEntity<GoodsCheckInItem> getListEntity() {
		if (null != searchCondition) {
			key.setCreateDateBegin(searchCondition.getCreateDateBegin());
			key.setCreateDateEnd(searchCondition.getCreateDateEnd());
			key.setDepartment(searchCondition.getDepartment());
			key.setGoodsCode(searchCondition.getGoodsCode());
			key.setGoodsName(searchCondition.getGoodsName());
			key.setProduceSheetNo(searchCondition.getProduceSheetNo());
		}
		ListEntity<GoodsCheckInItem> listEntity = getContext().find(GoodsCheckInListEntity.class, key);
		return listEntity;
	}

	@Override
	protected void doAdvanceSearch() {
		// 高级搜索
		PageController pc = new PageController(GoodsCheckinAdvaceSearchProcessor.class,
				GoodsCheckinAdvaceSearchRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(390, 280);
		MsgRequest request = new MsgRequest(pci, "高级搜索", windowStyle);
		request.setResponseHandler(new ResponseHandler() {

			@Override
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null == returnValue)
					return;
				searchCondition = (GoodsCheckinAdvanceSearch) returnValue;
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	protected void doExport() {
		final List<Object> datalist = table.getAllRows();
		Display.getCurrent().exportFile(getExportFileTitle() + QueryDataUtil.getDateNo() + ".xls", "application/vnd.ms-excel", 1000000,
				new ExporterWithContext() {
					public void run(Context context, OutputStream outputStream) throws IOException {
						ExcelWriteHelperPoi writer = new ExcelWriteHelperPoi(datalist) {
							@Override
							protected String getText(Object element, int columnIndex) {
								GoodsCheckInItem item = (GoodsCheckInItem) element;
								switch (columnIndex) {
								case 0:
									return item.getDepartment();
								case 1:
									return item.getProduceSheetNo();
								case 2:
									return item.getSheetNo();
								case 3:
									return item.getGoodsCode();
								case 4:
									return item.getGoodsName();
								case 5:
									return item.isNeedProduce() ? "是" : "否";
								case 6:
									return item.getUnit();
								case 7:
									return DoubleUtil.getRoundStr(item.getCost());
								case 8:
									return DoubleUtil.getRoundStr(item.getCount());
								case 9:
									return DoubleUtil.getRoundStr(item.getAmount());
								case 10:
									return DateUtil.dateFromat(item.getCreateDate());
								case 11:
									return DoubleUtil.getRoundStr(item.getStandardCost());
								case 12:
									return DoubleUtil.getRoundStr(item.getStandardAmount());
								}
								return null;
							}

							@Override
							protected String[] getHead() {
								String[] columns = new String[13];
								columns[0] = GoodsChecinSummaryProcessor.ColumnName.department.getTitle();
								columns[1] = GoodsChecinSummaryProcessor.ColumnName.produceSheetNo.getTitle();
								columns[2] = GoodsChecinSummaryProcessor.ColumnName.sheetNo.getTitle();
								columns[3] = GoodsChecinSummaryProcessor.ColumnName.goodsCode.getTitle();
								columns[4] = GoodsChecinSummaryProcessor.ColumnName.goodsName.getTitle();
								columns[5] = GoodsChecinSummaryProcessor.ColumnName.isNeedProduce.getTitle();
								columns[6] = GoodsChecinSummaryProcessor.ColumnName.unit.getTitle();
								columns[7] = GoodsChecinSummaryProcessor.ColumnName.cost.getTitle();
								columns[8] = GoodsChecinSummaryProcessor.ColumnName.count.getTitle();
								columns[9] = GoodsChecinSummaryProcessor.ColumnName.amount.getTitle();
								columns[10] = GoodsChecinSummaryProcessor.ColumnName.createDate.getTitle();
								columns[11] = GoodsChecinSummaryProcessor.ColumnName.standardCost.getTitle();
								columns[12] = GoodsChecinSummaryProcessor.ColumnName.standardAmount.getTitle();
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
		return "成品入库";
	}

}

class GoodsCheckinAdvanceSearch {
	private String department;
	private String produceSheetNo;
	private String goodsCode;
	private String goodsName;
	private boolean isNeedProduce;
	private long createDateBegin;
	private long createDateEnd;

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

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public boolean isNeedProduce() {
		return isNeedProduce;
	}

	public void setNeedProduce(boolean isNeedProduce) {
		this.isNeedProduce = isNeedProduce;
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

}
