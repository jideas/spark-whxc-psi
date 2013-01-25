package com.spark.psi.query.browser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STable;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.excel.ExcelWriteHelperPoi;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.query.intf.publish.entity.ProduceItem;
import com.spark.psi.query.intf.publish.entity.ProduceListEntity;
import com.spark.psi.query.intf.publish.key.GetProduceListKey;

public class ProduceOrderSummaryProcessor<Item> extends AbstractSummaryProcessor<Item> {

	public final static String ID_ViewDetail = "ViewDetail";

	public static enum ColumnName {
		billsNo("生产订单编号"), goodsCode("商品编号"), goodsName("商品名称"), unit("商品单位"), count("订单数量"), createDate("制单日期"), planDate(
				"预计完成日期"), finishedCount("已完成数量");
		private String title = null;

		private ColumnName(String title) {
			this.title = title;
		}

		public String getTitle() {
			return this.title;
		}
	}

	private ProduceOrderSearchCondition searchCondition = null;

	private Map<String, ProduceItem> itemMap = new HashMap<String, ProduceItem>();

	private ProduceDetailWindow detailWindow;

	@Override
	public void process(Situation context) {
		super.process(context);

		table.addClientEventHandler(STable.CLIENT_EVENT_ACTION, "QueryClient.handleActionPerformed");

//		detailWindow = new ProduceDetailWindow(table);
	}

	@Override
	public String getElementId(Object element) {
		ProduceItem item = (ProduceItem) element;
		return item.getBillsId().toString();
	}
	GetProduceListKey key;
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		key = new GetProduceListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		ListEntity<ProduceItem> listEntity = getListEntity();
		for (ProduceItem item : listEntity.getItemList()) {
			itemMap.put(item.getBillsId().toString(), item);
		}
		setRecordCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE, listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new ProduceItem[0]);
	}

	private ListEntity<ProduceItem> getListEntity() {
		if (searchCondition != null) {
			key.setBillsNo(searchCondition.getBillsNo());
			key.setCreateDateBegin(searchCondition.getCreateDateBegin());
			key.setCreateDateEnd(searchCondition.getCreateDateEnd());
			key.setGoodsCode(searchCondition.getGoodsCode());
			key.setGoodsName(searchCondition.getGoodsName());
			key.setPlanDateBegin(searchCondition.getDeliveryDateBegin());
			key.setPlanDateEnd(searchCondition.getDeliveryDateEnd());
		}
		return getContext().find(ProduceListEntity.class, key);
	}

	@Override
	public void actionPerformed(String rowId, String actionName, String actionValue) {
		if (ID_ViewDetail.equals(actionName)) {
			String[] locationInfo = actionValue.split(":");
			ProduceItem item = itemMap.get(rowId);
			if (null == item)
				return;
			detailWindow = new ProduceDetailWindow(table);
			detailWindow.refresh(item.getLogs(), new Point(Integer.parseInt(locationInfo[0]), Integer
					.parseInt(locationInfo[1])));
		}
	}

	@Override
	protected void doAdvanceSearch() {
		// 高级搜索
		PageController pc = new PageController(ProduceOrderAdvaceSearchProcessor.class,
				ProduceOrderAdvaceSearchRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc);
		WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		windowStyle.setSize(390, 210);
		MsgRequest request = new MsgRequest(pci, "高级搜索", windowStyle);
		request.setResponseHandler(new ResponseHandler() {

			@Override
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				if (null == returnValue)
					return;
				searchCondition = (ProduceOrderSearchCondition) returnValue;
				table.render();
			}
		});
		getContext().bubbleMessage(request);
	}

	@Override
	protected void doExport() {
		List<Object> datalist = table.getAllRows();
		final List<String[]> list = new ArrayList<String[]>();
		Set<GUID> map = new HashSet<GUID>();
		for (Object pio : datalist) {
			ProduceItem pi = (ProduceItem)pio;
			for (ProduceItem.Item item : pi.getItems()) {
				String[] s = new String[16];
				if (map.add(pi.getBillsId())) {
					s[0] = pi.getBillsNo();
					s[1] = pi.getGoodsCode();
					s[2] = pi.getGoodsName();
					s[3] = pi.getUnit();
					s[4] = DoubleUtil.getRoundStr(pi.getCount());
					s[5] = DateUtil.dateFromat(pi.getCreateDate());
					s[6] = DateUtil.dateFromat(pi.getPlanDate());
					s[7] = DoubleUtil.getRoundStr(pi.getFinishedCount());
				}
				s[8] = item.getMaterialCode();
				s[9] = item.getMaterialNo();
				s[10] = item.getMaterialName();
				s[11] = item.getSpec();
				s[12] = item.getUnit();
				s[13] = DoubleUtil.getRoundStr(item.getCount());
				s[14] = DoubleUtil.getRoundStr(item.getCost());
				s[15] = DoubleUtil.getRoundStr(item.getTotalCost());
				list.add(s);
			}
		}
		Display.getCurrent().exportFile("加工数据" + QueryDataUtil.getDateNo() + ".xls", "application/vnd.ms-excel",
				1000000, new ExporterWithContext() {
					public void run(Context context, OutputStream outputStream) throws IOException {
						ExcelWriteHelperPoi writer = new ExcelWriteHelperPoi(list) {
							@Override
							protected String getText(Object element, int columnIndex) {
								String[] item = (String[]) element;
								return item[columnIndex];
							}

							@Override
							protected String[] getHead() {
								String[] columns = new String[16];
								columns[0] = ProduceOrderSummaryProcessor.ColumnName.billsNo.getTitle();
								columns[1] = ProduceOrderSummaryProcessor.ColumnName.goodsCode.getTitle();
								columns[2] = ProduceOrderSummaryProcessor.ColumnName.goodsName.getTitle();
								columns[3] = ProduceOrderSummaryProcessor.ColumnName.unit.getTitle();
								columns[4] = ProduceOrderSummaryProcessor.ColumnName.count.getTitle();
								columns[5] = ProduceOrderSummaryProcessor.ColumnName.createDate.getTitle();
								columns[6] = ProduceOrderSummaryProcessor.ColumnName.planDate.getTitle();
								columns[7] = ProduceOrderSummaryProcessor.ColumnName.finishedCount.getTitle();
								columns[8] = "材料编号";
								columns[9] = "材料条码";
								columns[10] = "材料名称";
								columns[11] = "材料规格";
								columns[12] = "材料单位";
								columns[13] = "需求数量";
								columns[14] = "标准成本";
								columns[15] = "金额";
								return columns;
							}
						};
						writer.writeNormal(outputStream, "加工数据");
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
		return "加工数据";
	}

}

class ProduceOrderSearchCondition {
	private String billsNo;
	private String goodsCode;
	private String goodsName;
	private long createDateBegin;
	private long createDateEnd;
	private long deliveryDateBegin;
	private long deliveryDateEnd;

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
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