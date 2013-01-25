package com.spark.psi.order.browser.onlinereturn;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.OnlineOrderStatus;
import com.spark.psi.publish.base.station.entity.StationItem;
import com.spark.psi.publish.base.station.key.GetStationListKey;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderListEntity;
import com.spark.psi.publish.onlineorder.key.GetOnlineOrderListKey;

public class OnlineOrderForSelectListProcessor<Item> extends
		PSIListPageProcessor<Item> {
	
	public static final String ID_List_Station = "List_Station";
	public static final String ID_Search = "Search";
	public static final String ID_Date_Begin = "Date_Begin";
	public static final String ID_Date_End = "Date_End";
	public static final String ID_Button_DateConfirm = "Button_DateConfirm";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";

	public static enum ColumnName {
		sheetNo("订单编号"), 
		memberName("会员"), 
		amount("订单金额"), 
		createDate("下单日期"),
		arrivedConfirmDate("交货日期");
		
		private String title;
		private ColumnName(String title) {
			this.title = title;
		}
		public String getTitle() {
			return this.title;
		}
	}

	private SSearchText2 search = null;
	private SDatePicker dateBegin, dateEnd;
	
	private GUID stationId = null;
	
	@Override
	public void process(Situation context) {
		super.process(context);
		search = createControl(ID_Search, SSearchText2.class);
		dateBegin = createControl(ID_Date_Begin, SDatePicker.class);
		dateEnd = createControl(ID_Date_End, SDatePicker.class);
		final LWComboList list = createControl(ID_List_Station, LWComboList.class);
		final Button dateConfirmButton = createButtonControl(ID_Button_DateConfirm);
		final Button confirmButton = createButtonControl(ID_Button_Confirm);
		final Button cancelButton = createButtonControl(ID_Button_Cancel);
		
		dateBegin.setDate(DateUtil.getThisMonth());
		dateEnd.setDate(new Date());
		
		StationProvider stationProvider = new StationProvider();
		list.getList().setSource(stationProvider);
		list.getList().setInput(null);
		list.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String selectedStationId = list.getList().getSeleted();
				stationId = StringUtils.isEmpty(selectedStationId) ? null : GUID.tryValueOf(selectedStationId);
				table.render();
			}
		});
		if (null == stationProvider.getFirstStationId()) {
			alert("暂不能退货，没有可用站点。", new Runnable() {
				
				public void run() {
					getContext().bubbleMessage(new MsgResponse(true));
				}
			});
		}
		list.setSelection(stationProvider.getFirstStationId());
		
		dateConfirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
				
			}
		});
		
		confirmButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确定选择
				// validate
				String selectRowId = table.getSelection();
				if (StringUtils.isEmpty(selectRowId)) {
					alert("请选择要退货的订单。");
					return;
				}
				
				// action
				getContext().bubbleMessage(new MsgResponse(true, GUID.tryValueOf(selectRowId)));
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
	}

	@Override
	public String getElementId(Object element) {
		OnlineOrderItem item = (OnlineOrderItem)element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
//		if (stationId == null) {
//			alert("请先选择站点。");
//			return null;
//		}
		GetOnlineOrderListKey key = new GetOnlineOrderListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		GetOnlineOrderListKey.AdvanceValues adValue = key.new AdvanceValues();
		adValue.setCreateDateBegin(dateBegin.getDate().getTime());
		adValue.setCreateDateEnd(DateUtil.getDayEndTime(dateEnd.getDate().getTime()));
		key.setAdvanceValues(adValue);
		key.setStatus(OnlineOrderStatus.Finished);
		key.setSearchText(search.getText());
		key.setStationId(stationId);
		key.setReturnFlag(false);
		ListEntity<OnlineOrderItem> listEntity = getContext().find(OnlineOrderListEntity.class, key);
		if (null != listEntity) {
			return listEntity.getItemList().toArray(new OnlineOrderItem[0]);
		} else {
			return null;
		}
	}
	
	private class StationProvider extends ListSourceAdapter {

		private String firstStationId = null;
		
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
			firstStationId = stationListEntity.getItemList().get(0).getId().toString();
			for (StationItem item : stationListEntity.getItemList()) {
				idMap.put(item.getId().toString(), item);
			}
			return stationListEntity.getItemList().toArray(new StationItem[0]);
		}
		
		public String getFirstStationId() {
			return this.firstStationId;
		}
		
	}

	@Override
	protected String getExportFileTitle() {
		return null;
	} 

}
