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
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageProcessor;
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
	
	public static enum ColumnName {
		goodsName, spec, price, count
	}
	
	private LWComboList stationList = null;
	private LWComboList timeList = null;
	private SDatePicker dateBegin = null;
	private SDatePicker dateEnd = null;
//	private Button button = null;

	@Override
	public void process(Situation context) {
		super.process(context);
		stationList = createControl(ID_List_Station, LWComboList.class);
		timeList = createControl(ID_List_DelvierTime, LWComboList.class);
		dateBegin = createControl(ID_Date_Begin, SDatePicker.class);
		dateEnd = createControl(ID_Date_Begin, SDatePicker.class);
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
		
		StationProvider stationProvider = new StationProvider();
		stationList.getList().setSource(stationProvider);
		stationList.getList().setInput(null);
		stationList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
				
			}
		});
		//stationList.setSelection(stationProvider.getFirstStationId());
		
		TimeSource timeSource = new TimeSource();
		timeList.getList().setSource(timeSource);
		timeList.getList().setInput(null);
		
		timeList.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
				
			}
		});
		
//		button = createButtonControl(ID_Button_Confirm);
//		button.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				table.render();
//			}
//		});
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

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
//		if (StringUtils.isEmpty(stationList.getList().getSeleted())
//				|| StringUtils.isEmpty(timeList.getList().getSeleted())) {
//			return null;
//		}
		GetOnlineOrderListKey key = new GetOnlineOrderListKey(0, Integer.MAX_VALUE, true);
		key.setStatus(OnlineOrderStatus.Picking);
		key.setStationId(StringUtils.isEmpty(stationList.getList().getSeleted()) ? null : GUID.tryValueOf(stationList.getList().getSeleted()));
		GetOnlineOrderListKey.AdvanceValues advance = key.new AdvanceValues();
		advance.setDeliveryeDateBegin(dateBegin.getDate().getTime());
		advance.setDeliveryeDateEnd(DateUtil.getDayEndTime(dateEnd.getDate().getTime()));
		advance.setDeliverTime(StringUtils.isEmpty(timeList.getList().getSeleted()) ? null : timeList.getList().getSeleted());
		key.setAdvanceValues(advance);
		ListEntity<OnlineOrderItem> listEntity = getContext().find(OnlineOrderListEntity.class, key);
		List<OnlineOrderItem> orderList = new ArrayList<OnlineOrderItem>();
		if (null != listEntity) {
			orderList = listEntity.getItemList();
		}
		List<SummaryGoods> summaryList = getGoodsListByOrderList(orderList);
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
			StationItem item = (StationItem) element;
			return item.getId().toString();
		}
		
		public Object getElementById(String id) {
			return idMap.get(id);
		}
		
		public String getText(Object element) {
			StationItem item = (StationItem) element;
			return item.getName();
		}
		
		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement) {
			GetStationListKey key = new GetStationListKey();
			ListEntity<StationItem> stationListEntity = getContext().find(ListEntity.class, key);
			if (null == stationListEntity) return null;
//			firstStationId = stationListEntity.getItemList().get(0).getId().toString();
			for (StationItem item : stationListEntity.getItemList()) {
				idMap.put(item.getId().toString(), item);
			}
			return stationListEntity.getItemList().toArray(new StationItem[0]);
		}
		
	}
	
	private class TimeSource extends ListSourceAdapter {
		
//		private EnumEntity firstEntity = null;
		
		public String getElementId(Object element) {
			EnumEntity entity = (EnumEntity)element;
			return entity.getCode();
		}
		
		public Object getElementById(String id) {
			BaseDictionaryKey key = new BaseDictionaryKey(BaseDictionaryEnum.DeliveryHour, id);
			EnumEntity entity = getContext().find(EnumEntity.class, key);
			return entity;
		}
		
		public String getText(Object element) {
			EnumEntity entity = (EnumEntity)element;
			return entity.getName();
		}
		
		public Object[] getElements(Object inputElement) {
			List<EnumEntity> list = getContext().getList(EnumEntity.class,
					new BaseDictionaryKey(BaseDictionaryEnum.DeliveryHour));
//			if (list.size() > 0) {
//				firstEntity = list.get(0);
//			}
			return list.toArray();
		}
		
//		public EnumEntity getFirstEntity() {
//			return firstEntity;
//		}
		
	}
}

class SummaryGoods {
	private String id;
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
