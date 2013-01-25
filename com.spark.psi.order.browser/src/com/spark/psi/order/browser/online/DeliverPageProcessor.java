package com.spark.psi.order.browser.online;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.BaseDictionaryEnum;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.base.key.BaseDictionaryKey;
import com.spark.psi.publish.deliver.task.CreateDeliverTask;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderInfoItem;
import com.spark.psi.publish.onlineorder.entity.OnlineOrderItem;

public class DeliverPageProcessor extends BaseFormPageProcessor {

	public final static String ID_Button_Confirm = "Button_Confirm";
	public final static String ID_Form_Area = "Form_Area";
	
	
	private CreateDeliverItem[] items = null;
	private Map<String, ItemControl> stationControls = new HashMap<String, ItemControl>();
	
	private static GridData gdInput;
	
	static {
		gdInput = new GridData();
		gdInput.widthHint = 120;
	}
	
	@Override
	public void init(Situation context) {
		super.init(context);
		items = (CreateDeliverItem[])getArgument();
	}

	@Override
	public void process(final Situation context) {
		final Composite formArea = createControl(ID_Form_Area, Composite.class);
		final Button button = createControl(ID_Button_Confirm, Button.class);
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 确认生成配送单
				CreateDeliverTask task = null;
				for (CreateDeliverItem item : items) {
					ItemControl controls = stationControls.get(item.getStationId().toString());
					if (!validateInput(controls)) return ;
					task = new CreateDeliverTask();
					task.setAddress(item.getOrders()[0].getAddress());
					task.setRemark(controls.getRemarkText().getText());
					task.setStationId(item.getStationId());
					task.setStationName(item.getOrders()[0].getStationName());
					task.setDeliverPerson(controls.getDeliverPersonText().getText());
					long dateTime = controls.getDate().getDate().getTime();
					String dateTimeStr = DateUtil.dateFromat(dateTime);
					LWComboList timeList = controls.getTimeList();
					String timeStr = timeList.getList().getSeleted();
					String fullTimeStr = dateTimeStr + " " + timeStr;
					long fullTime = 0;
					try {
						fullTime = DateUtil.convertStringToDate(fullTimeStr);
					} catch (Exception ex) {
						alert("配送时间格式不正确，请与管理员联系。");
						return;
					}
					if (fullTime == 0) {
						alert("配送时间格式不正确，请与管理员联系。");
						return;
					}
					task.setPlanDate(fullTime);
					
					CreateDeliverTask.Item[] tItems = new CreateDeliverTask.Item[item.getOrders().length];
					CreateDeliverTask.Item tItem = null;
					int index = 0;
					for (OnlineOrderItem orderItem : item.getOrders()) {
						tItem = task.new Item();
						tItem.setMemberRealName(orderItem.getRealName());
						tItem.setOnlineOrderId(orderItem.getId());
						tItem.setOnlineOrderNo(orderItem.getBillsNo());
						tItem.setTotalAmount(orderItem.getTotalAmount());
						tItem.setMemberId(orderItem.getMemberId());
						tItem.setMobilePhone(orderItem.getConsigneeTel());
						tItem.setStationId(orderItem.getStationId());
						tItem.setStationName(orderItem.getStationName());
						tItem.setRemark(orderItem.getRemark());
						tItem.setAddress(orderItem.getAddress());
						tItem.setDisAmount(orderItem.getDisAmount());
						tItems[index] = tItem;
						index++;
					}
					task.setItems(tItems);
					try {
						context.handle(task);
					} catch(Throwable th) {
						th.printStackTrace();
						alert(th.getMessage());
						return;
					}
				}
				context.bubbleMessage(new MsgResponse(true));
			}
		});
		
		ScrolledPanel scrolledPanel = new ScrolledPanel(formArea, JWT.V_SCROLL);
		scrolledPanel.setLayoutData(GridData.INS_FILL_BOTH);
		CBorder border = new CBorder(1, JWT.BORDER_STYLE_SOLID, SSeparator.color.getColor());
		scrolledPanel.setBorder(border);
		Composite workArea = scrolledPanel.getComposite();
		GridLayout gl = new GridLayout();
//		gl.marginLeft = 10;
//		gl.marginRight = 10;
		gl.verticalSpacing = 0;
		workArea.setLayout(gl);
		
		for (CreateDeliverItem item : items) {
			showItem(workArea, item);
		}
		
		formArea.layout();
	}
	
	private boolean validateInput(ItemControl controls) {
//		String pacageCountStr = controls.getPackageText().getText();
//		if (StringUtils.isEmpty(pacageCountStr)) {
//			alert("箱数不能为空。");
//			return false;
//		} 
		String deliverPerson = controls.getDeliverPersonText().getText();
		if (StringUtils.isEmpty(deliverPerson)) {
			alert("配送员不能为空。");
			return false;
		}
		LWComboList timeList = controls.getTimeList();
		if (StringUtils.isEmpty(timeList.getList().getSeleted())) {
			alert("配送时间不能为空。");
			return false;
		}
		return true;
	}
	private void showItem(Composite parentArea, CreateDeliverItem item) {
		Composite titleArea = new Composite(parentArea);
		GridData gdTitle = new GridData(GridData.FILL_HORIZONTAL);
		gdTitle.heightHint = 23;
		titleArea.setLayoutData(gdTitle);
		GridLayout glTitle = new GridLayout();
		glTitle.numColumns = 13;
		titleArea.setLayout(glTitle);
		titleArea.setBackground(new Color(0xC3DFE9));
		
		String tempStr = "      ";
		new Label(titleArea).setText("站点：" + item.getOrders()[0].getStationName());
		new Label(titleArea).setText(tempStr);
		new Label(titleArea).setText("地址：" + item.getOrders()[0].getAddress());
		new Label(titleArea).setText(tempStr);
//		new Label(titleArea).setText("箱数：");
//		Text packageText = new Text(titleArea, JWT.APPEARANCE3);
//		packageText.setLayoutData(gdInput);
//		packageText.setRegExp(TextRegexp.NUMBER);
//		new Label(titleArea).setText(tempStr);
		new Label(titleArea).setText("预计到货日期：");
		SDatePicker date = new SDatePicker(titleArea);
		date.setLayoutData(gdInput);
		LWComboList timeList = new LWComboList(titleArea, JWT.APPEARANCE3);
		TimeSource source = new TimeSource();
		timeList.getList().setSource(source);
		timeList.getList().setInput(null);
		timeList.setSelection(source.getFirstEntity().getCode());
		new Label(titleArea).setText(tempStr);
		new Label(titleArea).setText("配送员：");
		Text deliverPersonText = new Text(titleArea, JWT.APPEARANCE3);
		deliverPersonText.setLayoutData(gdInput);
		
		Composite remarkArea = new Composite(parentArea);
		GridLayout glMemo = new GridLayout();
		glMemo.horizontalSpacing = 10;
		glMemo.numColumns = 2;
		glMemo.marginTop = 5;
		remarkArea.setLayout(glMemo);
		GridData gdMemoMain = new GridData(GridData.FILL_HORIZONTAL);
		gdMemoMain.heightHint = 60;
		remarkArea.setLayoutData(gdMemoMain);
		
		Label titleLabel = new Label(remarkArea);
		titleLabel.setText("备注：");
		GridData gdMemo = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_END);
		gdMemo.verticalIndent = 3;
		titleLabel.setLayoutData(gdMemo);
		
		Text remarkText = new Text(remarkArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP | JWT.V_SCROLL);
		GridData gdMemoText = new GridData(GridData.FILL_BOTH);
		remarkText.setLayoutData(gdMemoText);
		
		Composite orderArea = new Composite(parentArea);
		GridLayout glOrder = new GridLayout();
		glOrder.marginTop = 5;
		orderArea.setLayout(glOrder);
		GridData gdOrder = new GridData(GridData.FILL_HORIZONTAL);
		orderArea.setLayoutData(gdOrder);
		
		for (OnlineOrderItem order : item.getOrders()) {
			new Label(orderArea).setText("订单：" + order.getBillsNo() + "（" + getOrderCountInfo(order) + "）");
		}
		
		
		ItemControl itemControl = new ItemControl();
		itemControl.setDate(date);
//		itemControl.setPackageText(packageText);
		itemControl.setRemarkText(remarkText);
		itemControl.setDeliverPersonText(deliverPersonText);
		itemControl.setTimeList(timeList);
		stationControls.put(item.getStationId().toString(), itemControl);
	}
	
	
	private class TimeSource extends ListSourceAdapter {
		
		private EnumEntity firstEntity = null;
		
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
			if (list.size() > 0) {
				firstEntity = list.get(0);
			}
			return list.toArray();
		}
		
		public EnumEntity getFirstEntity() {
			return firstEntity;
		}
		
	}
	
	private String getOrderCountInfo(OnlineOrderItem order) {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for (OnlineOrderInfoItem item : order.getItems()) {
			sb.append(item.getGoodsName() + "  " + item.getCount() + item.getUnit() + ";");
			if (index != order.getItems().length - 1) {
				sb.append("   ");
			}
			index++;
		}
		return sb.toString();
	}
}

class CreateDeliverItem {
	private GUID stationId;
	private String stationName;
	private String address;
	private OnlineOrderItem[] orders;
	
	public CreateDeliverItem(GUID stationId) {
		this.stationId = stationId;
	}
	public void setOrders(OnlineOrderItem[] orders) {
		this.orders = orders;
	}
	public GUID getStationId() {
		return stationId;
	}

	public OnlineOrderItem[] getOrders() {
		return orders;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

class ItemControl {
//	private Text packageText;
	private SDatePicker date;
	private Text deliverPersonText;
	private Text remarkText;
	private LWComboList timeList;
	
//	public Text getPackageText() {
//		return packageText;
//	}
//	public void setPackageText(Text packageText) {
//		this.packageText = packageText;
//	}
	public SDatePicker getDate() {
		return date;
	}
	public void setDate(SDatePicker date) {
		this.date = date;
	}
	public Text getRemarkText() {
		return remarkText;
	}
	public void setRemarkText(Text remarkText) {
		this.remarkText = remarkText;
	}
	public Text getDeliverPersonText() {
		return deliverPersonText;
	}
	public void setDeliverPersonText(Text deliverPersonText) {
		this.deliverPersonText = deliverPersonText;
	}
	public LWComboList getTimeList() {
		return timeList;
	}
	public void setTimeList(LWComboList timeList) {
		this.timeList = timeList;
	}
	
}
