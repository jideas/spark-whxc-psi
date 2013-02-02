package com.spark.psi.order.browser.online;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Browser;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.SLabelProvider;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.FormPrintEntity;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.base.browser.PSIPrinter;
import com.spark.psi.base.browser.PrintColumn;
import com.spark.psi.base.browser.PrintContentProvider;
import com.spark.psi.publish.BaseDictionaryEnum;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.base.key.BaseDictionaryKey;
import com.spark.psi.publish.base.station.entity.StationItem;
import com.spark.psi.publish.base.station.key.GetStationListKey;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderListEntity;
import com.spark.psi.publish.onlineorder.key.GetOnlineOrderListKey;

public class OnlineGoodsSummaryProcessor<Item> extends
		PSIListPageProcessor<Item> {
	
	public static final String ID_List_Station = "List_Station";
	public static final String ID_Date_Begin = "Date_Begin";
	public static final String ID_Date_End = "Date_End";
	public static final String ID_List_DelvierTime = "List_DeliverTime";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Print = "Button_Print";
	public static final String ID_Area_Hide = "Area_Hide";
	
	public static enum ColumnName {
		goodsCode, goodsNo, goodsName, spec, price, count
	}
	
	private static final String ID_List_Total = "list_toal";
	
	private LWComboList stationList = null;
	private LWComboList timeList = null;
	private SDatePicker dateBegin = null;
	private SDatePicker dateEnd = null;
	
	private List<SummaryGoods> summaryList = new ArrayList<SummaryGoods>();
	private StationProvider stationProvider;
	private TimeSource timeSource;

	@Override
	public void process(Situation context) {
		super.process(context);
		stationList = createControl(ID_List_Station, LWComboList.class);
		timeList = createControl(ID_List_DelvierTime, LWComboList.class);
		dateBegin = createControl(ID_Date_Begin, SDatePicker.class);
		dateEnd = createControl(ID_Date_End, SDatePicker.class);
		dateBegin.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});
		
		dateEnd.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});
		
		dateBegin.setDate(DateUtil.getThisMonth());
//		dateEnd.setDate(DateUtil.getThisMonth());
		
		stationProvider = new StationProvider();
		stationList.getList().setSource(stationProvider);
		stationList.getList().setInput(null);
		stationList.setSelection(ID_List_Total);
		stationList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
				
			}
		});
		//stationList.setSelection(stationProvider.getFirstStationId());
		
		timeSource = new TimeSource();
		timeList.getList().setSource(timeSource);
		timeList.getList().setInput(null);
		timeList.setSelection(ID_List_Total);
		timeList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
				
			}
		});
		
		Button button = createButtonControl(ID_Button_Print);
		button.addActionListener(new ActionListener() {
			 
			public void actionPerformed(ActionEvent e) {
				printAction();
			}
		});
	}

	@Override
	protected String getExportFileTitle() {
		return "拣货中-商品统计";
	}

	@Override
	public String getElementId(Object element) {
		SummaryGoods item = (SummaryGoods) element;
		return item.getId();
	}

	
	private void printAction() {
		PrintColumn[] columns = new PrintColumn[6];
		columns[0] = new PrintColumn("商品编号", 120, JWT.LEFT);
		columns[1] = new PrintColumn("商品条码", 120, JWT.LEFT);
		columns[2] = new PrintColumn("商品名称", 200, JWT.LEFT);
		columns[3] = new PrintColumn("规格", 100, JWT.LEFT);
		columns[4] = new PrintColumn("单价", 100, JWT.RIGHT);
		columns[5] = new PrintColumn("数量", 80, JWT.RIGHT);
		String station = stationProvider.getText(stationProvider.getElementId(stationList.getText()));
		String time = timeSource.getText(timeSource.getElementById(timeList.getText()));
		String tableTitle0 = "站点：" + station + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交货日期：" + DateUtil.dateFromat(dateBegin.getDate().getTime())
				+ "至" + DateUtil.dateFromat(dateEnd.getDate().getTime()) + "&nbsp;&nbsp;&nbsp;配送时间：" + time;
		FormPrintEntity fpe = new FormPrintEntity("网上订单", columns, summaryList.toArray(new SummaryGoods[0]), tableTitle0);
		fpe.setLabelProvider(new SLabelProvider() {
			
			
			public String getToolTipText(Object element, int columnIndex) {
				return null;
			}
			
			
			public String getText(Object element, int columnIndex) {
				SummaryGoods item = (SummaryGoods)element;
				switch(columnIndex) {
				case 0:
					return item.getGoodsCode();
				case 1:
					return item.getGoodsNo();
				case 2:
					return item.getGoodsName();
				case 3:
					return item.getSpec();
				case 4:
					return DoubleUtil.getRoundStr(item.getPrice());
				case 5:
					return DoubleUtil.getRoundStr(item.getCount(), 0);
				}
				return null;
			}
			
			
			public Color getForeground(Object element, int columnIndex) {
				return null;
			}
			
			
			public Color getBackground(Object element, int columnIndex) {
				return null;
			}
		});
		PrintProvider pProvider = new PrintProvider(fpe);
		PSIPrinter printer = new PSIPrinter(pProvider);
		printer.setNeedPreview(true);
		Composite hideArea = createControl(ID_Area_Hide, Composite.class);
		Browser browser = new Browser(hideArea);
		browser.setLayoutData(GridData.INS_FILL_BOTH);
		browser.applyHTML(printer.getPrinterContent());
		hideArea.layout();
	}
	
	
	public Object[] getElements(Context context, STableStatus tablestatus) {
//		if (StringUtils.isEmpty(stationList.getList().getSeleted())
//				|| StringUtils.isEmpty(timeList.getList().getSeleted())) {
//			return null;
//		}
		GetOnlineOrderListKey key = new GetOnlineOrderListKey(0, Integer.MAX_VALUE, true);
		key.setStatus(OnlineOrderStatus.Picking);
		GUID stationId = null;
		if (!StringUtils.isEmpty(stationList.getList().getSeleted())
				&& !ID_List_Total.equals(stationList.getList().getSeleted())) {
			stationId = GUID.tryValueOf(stationList.getList().getSeleted());
		}
		key.setStationId(stationId);
		GetOnlineOrderListKey.AdvanceValues advance = key.new AdvanceValues();
		advance.setDeliveryeDateBegin(dateBegin.getDate().getTime());
		advance.setDeliveryeDateEnd(DateUtil.getDayEndTime(dateEnd.getDate().getTime()));
		String time = null;
		if (StringHelper.isNotEmpty(timeList.getList().getSeleted())
				&& !ID_List_Total.equals(timeList.getList().getSeleted())) {
			time = timeList.getList().getSeleted();
		}
		advance.setDeliverTime(time);
		key.setAdvanceValues(advance);
		ListEntity<OnlineOrderItem> listEntity = getContext().find(OnlineOrderListEntity.class, key);
		List<OnlineOrderItem> orderList = new ArrayList<OnlineOrderItem>();
		if (null != listEntity) {
			orderList = listEntity.getItemList();
		}
		summaryList = getGoodsListByOrderList(orderList);
		return summaryList.toArray(new SummaryGoods[0]);
	}
	
	
	private List<SummaryGoods> getGoodsListByOrderList(List<OnlineOrderItem> orderList) {
		Map<String, SummaryGoods> goodsMap = new HashMap<String, SummaryGoods>();
		for (OnlineOrderItem item : orderList) {
			//SummaryGoods goods = goodsMap.get(item.get)
			for (OnlineOrderInfoItem goodsItem : item.getItems()) {
				SummaryGoods goods = goodsMap.get(goodsItem.getGoodsId().toString());
				if (null == goods) {
					goods = new SummaryGoods();
					goods.setId(goodsItem.getGoodsId().toString());
					goods.setGoodsCode(goodsItem.getGoodsCode());
					goods.setGoodsNo(goodsItem.getGoodsNo());
					goods.setGoodsName(goodsItem.getGoodsName());
					goods.setSpec(goodsItem.getGoodsSpec());
					goods.setPrice(goodsItem.getPrice());
					goods.setCount(goodsItem.getCount());
					goodsMap.put(goodsItem.getGoodsId().toString(), goods);
				} else {
					goods.setCount(DoubleUtil.sum(goods.getCount(), goodsItem.getCount()));
				}
			}
		}
		List<SummaryGoods> summaryList = new ArrayList<SummaryGoods>();
		Iterator<String> mapIt = goodsMap.keySet().iterator();
		while (mapIt.hasNext()) {
			summaryList.add(goodsMap.get(mapIt.next()));
		}
		return summaryList;
	}

	
	private class StationProvider extends ListSourceAdapter {

		private Map<String, StationItem> idMap = new HashMap<String, StationItem>();
		
		public String getElementId(Object element) {
			if (element instanceof String) {
				return ID_List_Total;
			}
			StationItem item = (StationItem) element;
			return item.getId().toString();
		}
		
		public Object getElementById(String id) {
			if (ID_List_Total.equals(id)) {
				return "全部";
			}
			return idMap.get(id);
		}
		
		public String getText(Object element) {
			if (element instanceof String) {
				return "全部";
			}
			StationItem item = (StationItem) element;
			return item.getName();
		}
		
		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement) {
			List<Object> elements = new ArrayList<Object>();
			GetStationListKey key = new GetStationListKey();
			ListEntity<StationItem> stationListEntity = getContext().find(ListEntity.class, key);
			if (null == stationListEntity) return null;
//			firstStationId = stationListEntity.getItemList().get(0).getId().toString();
			for (StationItem item : stationListEntity.getItemList()) {
				idMap.put(item.getId().toString(), item);
			}
			elements.add("全部");
			elements.addAll(stationListEntity.getItemList());
			return elements.toArray();
		}
		
	}
	
	private class TimeSource extends ListSourceAdapter {
		
//		private EnumEntity firstEntity = null;
		
		public String getElementId(Object element) {
			if (element instanceof String) {
				return ID_List_Total;
			}
			EnumEntity entity = (EnumEntity)element;
			return entity.getCode();
		}
		
		public Object getElementById(String id) {
			if (ID_List_Total.equals(id)) {
				return "全部";
			}
			BaseDictionaryKey key = new BaseDictionaryKey(BaseDictionaryEnum.DeliveryHour, id);
			EnumEntity entity = getContext().find(EnumEntity.class, key);
			return entity;
		}
		
		public String getText(Object element) {
			if (element instanceof String) {
				return "全部";
			}
			EnumEntity entity = (EnumEntity)element;
			return entity.getName();
		}
		
		public Object[] getElements(Object inputElement) {
			List<Object> elements = new ArrayList<Object>();
			List<EnumEntity> list = getContext().getList(EnumEntity.class,
					new BaseDictionaryKey(BaseDictionaryEnum.DeliveryHour));
//			if (list.size() > 0) {
//				firstEntity = list.get(0);
//			}
			elements.add("全部");
			elements.addAll(list);
			return elements.toArray();
		}
		
//		public EnumEntity getFirstEntity() {
//			return firstEntity;
//		}
		
	}
}
class SummaryGoods {
	private String id;
	private String goodsCode;
	private String goodsNo;
	private String goodsName;
	private String spec;
	private double price;
	private double count;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
}

class PrintProvider implements PrintContentProvider {
	public static final int PAGE_WIDTH        = 700;
	
	private FormPrintEntity printEntity = null;
	
	public PrintProvider(FormPrintEntity printEntity) {
		this.printEntity = printEntity;
	}
	
	public String getContentHtml() {
		StringBuffer buffer = new StringBuffer();
		for (String commonRow : printEntity.getTableTitles()) {
			buffer.append("<span height='" + FormPrintEntity.COMMON_ROW_HEIGHT + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>").append(commonRow).append("</font></span> \n <br/>");
		}
		buffer.append("\n<div style='height:1px;width: " + getWidth() + ";border-bottom: solid red 1px;'></div> \n");
		buffer.append("<table> \n");
		// 表头
		buffer.append("<tr> \n");
		for (PrintColumn column : printEntity.getColumns()) {
			buffer.append("<td align='" +  getTableAlign(column.getAlign()) + "' width='" + column.getWidth() + "'><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>" + column.getTitle() + "</font></td> \n");
		}
		buffer.append("</tr> \n");
		// 表体
		for (Object object : printEntity.getDatas()) {
			buffer.append("<tr> \n");
			for (int columnIndex = 0; columnIndex < printEntity.getColumns().length; columnIndex++) {
				PrintColumn column = printEntity.getColumns()[columnIndex];
				buffer.append("<td align='" + getTableAlign(column.getAlign()) + "' width='" + column.getWidth() + "'>");
				buffer.append("<span><font size='" + FormPrintEntity.FONT_COMMON_SIZE + "'>" + printEntity.getLabelProvider().getText(object, columnIndex) + "</font></span>");
				buffer.append("</td> \n");
			}
			buffer.append("</tr> \n");
		}
		buffer.append("</table> \n");
		return buffer.toString();
	}
	private String getTableAlign(int align) {
		String alignStr = "left";
		if (JWT.RIGHT == align) {
			alignStr = "right";
		} else if (JWT.CENTER == align) {
			alignStr = "center";
		}
		return alignStr;
	}
	public int getHeight() {
		int height = 0;
		height += 25 * printEntity.getTableTitles().length;
		height += 25 * printEntity.getDatas().length + 25; // 表格内容+表头
		height *= 2.85;
		return height;
	}

	public int getWidth() {
		return PAGE_WIDTH * 3;
	}
	
}