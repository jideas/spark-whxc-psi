package com.spark.psi.order.browser.online;

import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.BaseDictionaryEnum;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.base.key.BaseDictionaryKey;

public class AdvanceSearchPageProcessor extends BaseFormPageProcessor {

	public static final String ID_Text_BillsNo = "Text_BillsNo";
	public static final String ID_Text_RealName = "Text_RealName";
	public static final String ID_Text_StationName = "Text_StationName";
	public static final String ID_Date_CreateDateBegin = "Date_CreateDateBegin";
	public static final String ID_Date_CreateDateEnd = "Date_CreateDateEnd";
	public static final String ID_Date_DeliveryeDateBegin = "Date_DeliveryeDateBegin";
	public static final String ID_Date_DeliveryeDateEnd = "Date_DeliveryeDateEnd";
	public static final String ID_List_DeliveryeTime = "List_DeliveryeTime";
	public static final String ID_Button_Confirm = "Button_Confirm";
	public static final String ID_Button_Cancel = "Button_Cancel";
	
	@Override
	public void process(final Situation context) {
		final Text sheetNoText = createTextControl(ID_Text_BillsNo);
		final Text realNameText = createTextControl(ID_Text_RealName);
		final Text stationNameText = createTextControl(ID_Text_StationName);
		final SDatePicker createDateBegin = createControl(ID_Date_CreateDateBegin, SDatePicker.class);
		final SDatePicker createDateEnd = createControl(ID_Date_CreateDateEnd, SDatePicker.class);
		final SDatePicker deliveryeDateBegin = createControl(ID_Date_DeliveryeDateBegin, SDatePicker.class);
		final SDatePicker deliveryeDateEnd = createControl(ID_Date_DeliveryeDateEnd, SDatePicker.class);
		final LWComboList deliverTimeList = createControl(ID_List_DeliveryeTime, LWComboList.class);
		final Button saveButton = createButtonControl(ID_Button_Confirm);
		
		createDateBegin.setDate(DateUtil.getThisMonth());
		deliveryeDateBegin.setDate(DateUtil.getThisMonth());
		TimeSource timeSource = new TimeSource();
		deliverTimeList.getList().setSource(timeSource);
		deliverTimeList.getList().setInput(null);
		//deliverTimeList.setSelection(timeSource.getFirstEntity().getCode());
		
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!validateInput()) return;
				AdvanceSearchCondition condition = new AdvanceSearchCondition();
				condition.setBillsNo(sheetNoText.getText());
				condition.setCreateDateBegin(createDateBegin.getDate().getTime());
				condition.setCreateDateEnd(createDateEnd.getDate().getTime());
				condition.setDeliveryeDateBegin(deliveryeDateBegin.getDate().getTime());
				condition.setDeliveryeDateEnd(deliveryeDateEnd.getDate().getTime());
				condition.setRealName(realNameText.getText());
				condition.setStationName(stationNameText.getText());
//				String time = deliverTimeList.getList().getSeleted();
//				if (StringHelper.isNotEmpty(time)) {
////					String dateBegin = DateUtil.convertLongToDate(condition.getDeliveryeDateBegin());
//					String dateBegin = DateUtil.dateFromat(condition.getDeliveryeDateBegin());
//					String dateEnd = DateUtil.dateFromat(condition.getDeliveryeDateEnd());
//					String beginStr = dateBegin + " " + time;
//					String endStr = dateEnd + " " + time;
//					condition.setDeliveryeDateBegin(DateUtil.convertStringToDate(beginStr));
//					condition.setDeliveryeDateEnd(DateUtil.convertStringToDate(endStr));
//				}
				condition.setDeliverTime(deliverTimeList.getList().getSeleted());
				context.bubbleMessage(new MsgResponse(true, condition));
			}
			
			private boolean validateInput() {
				if (createDateBegin.getDate().getTime() > createDateEnd.getDate().getTime()) {
					alert("下单日期：开始日期不能晚于结束日期。");
					return false;
				}
				if (deliveryeDateBegin.getDate().getTime() > deliveryeDateEnd.getDate().getTime()) {
					alert("交货日期：开始日期不能晚于结束日期。");
					return false;
				}
				return true;
			}
		});
		
		final Button cancelButton = createButtonControl(ID_Button_Cancel);
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
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

class AdvanceSearchCondition {
	private String billsNo;
	private String realName;
	private String stationName;
	private long createDateBegin;
	private long createDateEnd;
	private long deliveryeDateBegin;
	private long deliveryeDateEnd;
	private String deliverTime;
	
	public String getBillsNo() {
		return billsNo;
	}
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
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
	public long getDeliveryeDateBegin() {
		return deliveryeDateBegin;
	}
	public void setDeliveryeDateBegin(long deliveryeDateBegin) {
		this.deliveryeDateBegin = deliveryeDateBegin;
	}
	public long getDeliveryeDateEnd() {
		return deliveryeDateEnd;
	}
	public void setDeliveryeDateEnd(long deliveryeDateEnd) {
		this.deliveryeDateEnd = deliveryeDateEnd;
	}
	public String getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}
	
}
