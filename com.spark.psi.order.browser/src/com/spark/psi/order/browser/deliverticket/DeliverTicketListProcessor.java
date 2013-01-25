package com.spark.psi.order.browser.deliverticket;

import java.util.Date;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.StringHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketItem;
import com.spark.psi.publish.deliverticket.entity.DeliverTicketListEntity;
import com.spark.psi.publish.deliverticket.key.GetDeliverTicketListKey;

public class DeliverTicketListProcessor<Item> extends
		PSIListPageProcessor<Item> {

	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "Search";
	public static final String ID_Button_AdvanceSearch = "Button_AdvanceSearch";
	
	// 过滤日期下拉列表
	public final static String ID_DATE_BEGIN = "DATE_BEGIN";
	public final static String ID_DATE_End = "DATE_End";
	public final static String ID_BUTTON_QueryButton = "BUTTON_QueryButton";
	private DatePicker dateBegin, dateEnd;
	private Button queryButton;
	
	public static enum ColumnName {
		sheetNo, onlineOrderNo, customerName, stationName, deliverDate
	}
	
	
	private SSearchText2 search = null;
	@Override
	public void process(final Situation context) {
		super.process(context);
		search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		this.dateBegin = this.createSDatePickerControl(ID_DATE_BEGIN);
		if(null!=this.getArgument3()){
			this.dateBegin.setDate((Date) this.getArgument3());
		}else{
			this.dateBegin.setDate(DateUtil.getThisMonth());
		}
		this.dateEnd = this.createSDatePickerControl(ID_DATE_End);
		if(null!=this.getArgument4()){
			this.dateEnd.setDate((Date) this.getArgument4());
		}else{
			this.dateEnd.setDate(new Date());
		}
		this.queryButton = this.createButtonControl(ID_BUTTON_QueryButton);
		this.queryButton.setText("确定");
		this.queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
		final Button button = createButtonControl(ID_Button_AdvanceSearch);
//		button.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				//  高级搜索
//				
//			}
//		});
		button.dispose();
	}

	@Override
	public String getElementId(Object element) {
		DeliverTicketItem item = (DeliverTicketItem)element;
		return item.getId().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetDeliverTicketListKey key = new GetDeliverTicketListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), true);
		key.setSearchText(search.getText());
		key.setDateBegin(this.dateBegin.getDate().getTime());
		key.setDateEnd(dateEnd.getDate().getTime());
		ListEntity<DeliverTicketItem> listEntity = context.find(DeliverTicketListEntity.class, key);
		if (null == listEntity) return null;
		setCount(tablestatus.getPageNo() == STableStatus.FIRSTPAGE, listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new DeliverTicketItem[0]);
	}
	
	private void setCount(boolean isFirstPage, int count) {
		final Label countLabel = createLabelControl(ID_Label_Count);
		int size = count;
		if (!isFirstPage) {
			String preSize = countLabel.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
		}
		countLabel.setText(String.valueOf(size));
	}

	@Override
	public void actionPerformed(String rowId, String actionName,
			String actionValue) {
		if (Action.Detail.name().equals(actionName)) {
			PageController pc = new PageController(DeliverTicketSheetProcessor.class, DeliverTicketSheetRender.class);
			PageControllerInstance pci = new PageControllerInstance(pc, GUID.tryValueOf(rowId));
			MsgRequest request = new MsgRequest(pci, "发货单详情");
			getContext().bubbleMessage(request);
		}
	}

	@Override
	protected String getExportFileTitle() {
		return "发货单记录";
	}
}
