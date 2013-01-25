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
import com.spark.psi.publish.DeliverTicketType;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.query.intf.publish.entity.TicketItem;
import com.spark.psi.query.intf.publish.entity.TicketListEntity;
import com.spark.psi.query.intf.publish.key.GetTicketListKey;

public class GoodsDeliverSummaryProcessor<Item> extends AbstractSummaryProcessor<Item> {

	public static enum ColumnName {
		memberRealName("客户名称"), sheetNo("发货单号"), createDate("发货日期"), deliverTicketType("发货类型"), goodsCode("商品编码"), goodsNo(
				"商品条码"), goodsName("商品名称"), unit("计量单位"), count("发货数量"), price("单位成本"), amount("金额");
		private String title = null;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	private GoodsDeliverSearch searchCondition = null;

	@Override
	public void process(Situation context) {
		super.process(context);
	}

	@Override
	public String getElementId(Object element) {
		TicketItem item = (TicketItem) element;
		return item.getTicketId().toString();
	}
	GetTicketListKey key;
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		key = new GetTicketListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		ListEntity<TicketItem> listEntity = getListEntity();
		setRecordCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE, listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new TicketItem[0]);
	}

	private ListEntity<TicketItem> getListEntity() {
		if (null != searchCondition) {
			key.setCreateDateBegin(searchCondition.getCreateDateBegin());
			key.setCreateDateEnd(searchCondition.getCreateDateEnd());
			key.setDeliverTicketType(searchCondition.getDeliverTicketType());
			key.setGoodsCode(searchCondition.getGoodsCode());
			key.setGoodsName(searchCondition.getGoodsName());
			key.setGoodsNo(searchCondition.getGoodsNo());
			key.setMemberRealName(searchCondition.getMemberRealName());
			key.setSheetNo(searchCondition.getSheetNo());
		}
		return getContext().find(TicketListEntity.class, key);
	}

	@Override
	protected void doAdvanceSearch() {
		// 高级搜索
		PageController pc = new PageController(GoodsDeliverAdvanceSearchProcessor.class,
				GoodsDeliverAdvanceSearchRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(390, 280);
		MsgRequest request = new MsgRequest(pci, "高级搜索", windowStyle);
		request.setResponseHandler(new ResponseHandler() {

			@Override
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null == returnValue)
					return;
				searchCondition = (GoodsDeliverSearch) returnValue;
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
								TicketItem item = (TicketItem) element;
								switch (columnIndex) {
								case 0:
									return item.getMemberRealName();
								case 1:
									return item.getSheetNo();
								case 2:
									return DateUtil.dateFromat(item.getCreateDate());
								case 3:
									return item.getDeliverTicketType().getName();
								case 4:
									return item.getGoodsCode();
								case 5:
									return item.getGoodsNo();
								case 6:
									return item.getGoodsName();
								case 7:
									return item.getUnit();
								case 8:
									return DoubleUtil.getRoundStr(item.getCount());
								case 9:
									return DoubleUtil.getRoundStr(item.getPrice());
								case 10:
									return DoubleUtil.getRoundStr(item.getAmount());
								}
								return null;
							}

							@Override
							protected String[] getHead() {
								String[] columns = new String[11];
								columns[0] = GoodsDeliverSummaryProcessor.ColumnName.memberRealName.getTitle();
								columns[1] = GoodsDeliverSummaryProcessor.ColumnName.sheetNo.getTitle();
								columns[2] = GoodsDeliverSummaryProcessor.ColumnName.createDate.getTitle();
								columns[3] = GoodsDeliverSummaryProcessor.ColumnName.deliverTicketType.getTitle();
								columns[4] = GoodsDeliverSummaryProcessor.ColumnName.goodsCode.getTitle();
								columns[5] = GoodsDeliverSummaryProcessor.ColumnName.goodsNo.getTitle();
								columns[6] = GoodsDeliverSummaryProcessor.ColumnName.goodsName.getTitle();
								columns[7] = GoodsDeliverSummaryProcessor.ColumnName.unit.getTitle();
								columns[8] = GoodsDeliverSummaryProcessor.ColumnName.count.getTitle();
								columns[9] = GoodsDeliverSummaryProcessor.ColumnName.price.getTitle();
								columns[10] = GoodsDeliverSummaryProcessor.ColumnName.amount.getTitle();
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
		return "发货数据";
	}

}

class GoodsDeliverSearch {
	private String memberRealName;
	private String sheetNo;
	private long createDateBegin;
	private long createDateEnd;
	private DeliverTicketType deliverTicketType;
	private String goodsCode;
	private String goodsNo;
	private String goodsName;

	public String getMemberRealName() {
		return memberRealName;
	}

	public void setMemberRealName(String memberRealName) {
		this.memberRealName = memberRealName;
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

	public DeliverTicketType getDeliverTicketType() {
		return deliverTicketType;
	}

	public void setDeliverTicketType(String deliverTicketTypeCode) {
		if (StringUtils.isEmpty(deliverTicketTypeCode))
			return;
		this.deliverTicketType = DeliverTicketType.getDeliverTicketType(deliverTicketTypeCode);
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

}