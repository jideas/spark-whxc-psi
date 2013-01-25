package com.spark.psi.order.browser.util;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.edit.SAsignFormula;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.common.components.table.edit.SFormula;
import com.spark.common.components.table.edit.SListEdit2Column;
import com.spark.common.components.table.edit.SListEditColumn;
import com.spark.common.components.table.edit.SNumberEditColumn;
import com.spark.common.components.table.edit.STextEditColumn;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.browser.SimpleSheetPageRender;
import com.spark.psi.base.browser.StoreSource;
import com.spark.psi.order.browser.util.OrderDetailProcessor.Cloumns;
import com.spark.psi.order.browser.util.OrderDetailProcessor.View;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.order.entity.OrderGoodsItem;
import com.spark.psi.publish.order.entity.OrderInfo;

public abstract class OrderDetailRender extends SimpleSheetPageRender {
	protected final Map<Integer, OrderDetailProcessor.Cloumns> enumMap = new HashMap<Integer, OrderDetailProcessor.Cloumns>();

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		sheetButtonArea.setID(OrderDetailProcessor.ID_COMPOSITE_BUTTON);
	}
	
	protected double totalAmount;
	protected View viewEnum;
	@Override
	public void init(Situation context) {
		super.init(context);
		OrderInfo orderInfo = ((OrderDetailProcessor<?>)this.processor).orderInfo;
		totalAmount = orderInfo.getAmount();
		if(null == viewEnum){
			if(null == orderInfo || null == orderInfo.getOrderNumber() || "".equals(orderInfo.getOrderNumber())){
				this.viewEnum = View.Create;
			}
			else if(orderInfo.getOrderStatus().isIn(OrderStatus.Submit, OrderStatus.Denied)){
				this.viewEnum = View.Edit;
			}
			else{
				this.viewEnum = View.Look;
			}
		}
	}
	@Override
	public STableColumn[] getColumns() {
		OrderDetailProcessor.Cloumns[] enums = getColumnsEnumList();
		STableColumn[] columns = new STableColumn[enums.length];
		int index = 0;
		//序列初始化
		for (OrderDetailProcessor.Cloumns c : enums) {
			enumMap.put(index, c);
			indexMap.put(c, index++);
		}
		//列标题初始化
		index = 0;
		for (OrderDetailProcessor.Cloumns c : enums) {
			columns[index++] = getSTableColumn(c);
		}
		return columns;
	}
	
	protected final Map<Cloumns, Integer> indexMap = new HashMap<Cloumns, Integer>();

	
	protected final static String $count = "["+Cloumns.Count.name()+"]";
	protected final static String $price = "["+Cloumns.Price.name()+"]";
	protected final static String $amount = "["+Cloumns.Amount.name()+"]";
	protected final static String $returnCount = "["+Cloumns.ReturnCount.name()+"]";
	protected final static String $returnPrice = "["+Cloumns.ReturnPrice.name()+"]";
	protected final static String $returnAmount = "["+Cloumns.ReturnAmount.name()+"]";
	protected final static String $disCount = "["+Cloumns.DisCount.name()+"]";
	protected final static String $disAmount = "["+Cloumns.DisAmount.name()+"]";
	/**
	 * 列初始化时，扩展列公式工具
	 *  void
	 * @param c 
	 * @param e 
	 */
	protected void addColumnsSAsignFormula(Cloumns e, STableColumn c) {
		if(!(c instanceof SNumberEditColumn)){
			return;
		}
		SNumberEditColumn column = (SNumberEditColumn) c;
		final String $amountExtra = "#"+Cloumns.Amount.name()+"";
		final SAsignFormula lastFormula = new SAsignFormula("["+$amountExtra+"]",Cloumns.Amount.name());
		final String $returnAmountExtra = "#"+Cloumns.ReturnAmount.name()+"";
		final SAsignFormula lastReturnFormula = new SAsignFormula("["+$returnAmountExtra+"]",Cloumns.ReturnAmount.name());
		switch (e) {
		case Count:
			column.setFormulas(new SFormula[]{new SAsignFormula($count +"*"+$price, $amountExtra), lastFormula});
			break;
		case Price:
			column.setFormulas(new SFormula[]{new SAsignFormula($count +"*"+$price, $amountExtra), lastFormula});
			break;
		case ReturnCount: 
//			column.setFormulas(new SFormula[]{ new SAsignFormula($returnAmount+" == 0?"+$returnPrice+":("+$returnAmount +"/"+$returnCount+")", Cloumns.ReturnPrice.name()), new SAsignFormula($returnAmount+" == 0?("+$returnCount +"*"+$returnPrice+"):"+$returnAmount, $returnAmountExtra), lastReturnFormula});
			column.setFormulas(new SFormula[]{new SAsignFormula($returnCount +"*"+$returnPrice, $returnAmountExtra), lastReturnFormula});
			break;
		case ReturnPrice:
			column.setFormulas(new SFormula[]{new SAsignFormula($returnCount +"*"+$returnPrice, $returnAmountExtra), lastReturnFormula});
			break;
		case ReturnAmount:
			column.setFormulas(new SFormula[]{new SAsignFormula($returnAmount +"/"+$returnCount, Cloumns.ReturnPrice.name())});
			break;
		default:
			break;
		}
	}

	/**
	 * 当前列表数组
	 * 
	 * @return OrderDetailProcessor.Cloumns[]
	 */
	protected abstract OrderDetailProcessor.Cloumns[] getColumnsEnumList();

	private STableColumn getSTableColumn(OrderDetailProcessor.Cloumns e) {
		STableColumn c = null;
		if(null == e.getCellType()){
			c = new STableColumn(e.name(), e.getWidth(), e.getAlign(), e
					.getTitle());
		}
		else{
			switch (e.getCellType()) {
			case Number:
				c = new SNumberEditColumn(e.name(), e.getWidth(), e.getAlign(), e
						.getTitle());
				break;
			case Text:
				c = new STextEditColumn(e.name(), e.getWidth(), e.getAlign(), e
						.getTitle());
				break;
			case List_Store:
				c = new SListEditColumn(e.name(), e.getWidth(), e.getAlign(), e
						.getTitle());
				((SListEditColumn)c).setListSource(new StoreSource(true));
				break;
			case List_Price:
				c = new SListEdit2Column(e.name(), e.getWidth(), e.getAlign(), 10, e
						.getTitle());
				break;
			default:
				c = new STableColumn(e.name(), e.getWidth(), e.getAlign(), e
						.getTitle());
				break;
			}
		}
		c.setGrab(e.isGrab());
		addColumnsSAsignFormula(e, c);
		return c;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		if(null == enumMap.get(columnIndex)){
			return "";
		}
		return getText(element, enumMap.get(columnIndex));
	}

	/**
	 * 文本信息
	 * 
	 * @param element
	 * @param columnEnum
	 * @return String
	 */
	protected String getText(Object element,
			OrderDetailProcessor.Cloumns columnEnum) {
		if(columnEnum == null) {
			return "";
		}
		OrderGoodsItem item = (OrderGoodsItem) element;
		switch (columnEnum) {
		case GoodsItemCode:
			return item.getGoodsCode();
		case GoodsNo:
			return item.getGoodsNo();
		case GoodsName:
			return item.getName();
		case GoodsProperties:
			return item.getSpec();
		case GoodsUnit:
			return item.getUnit();
		case Count:
			return DoubleUtil.getRoundStr(item.getCount(),item.getScale());
		case Price:
			return DoubleUtil.getRoundStr(item.getPrice());
		case Amount:
			return DoubleUtil.getRoundStr(item.getAmount());
		case ReturnCount:
			return DoubleUtil.getRoundStr(item.getCount(),item.getScale());
		case ReturnPrice:
			return DoubleUtil.getRoundStr(item.getPrice());
		case ReturnAmount:
			return DoubleUtil.getRoundStr(item.getAmount());
		default:
			return "";
		}
	}

	@Override
	protected void fillBaseInfoCellControl(Composite baseInfoArea, int row,
			int column) {
	}

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		GridLayout gl = new GridLayout(2);
		dataInfoArea.setLayout(gl);
		Label label = new Label(dataInfoArea);
		label.setText(getOrderTotalAmountTitle()+"：");
		
		SNumberText text = new SNumberText(dataInfoArea,2,false);
		text.setID(OrderDetailProcessor.Text_TotalAmount);
		text.setEditable(false);
		text.setValue(totalAmount);
		GridData gd = new GridData();
		gd.widthHint  = 120;
		text.setLayoutData(gd);
		
		table.addClientEventHandler(SEditTable.CLIENT_EVENT_VALUE_CHANGED, "SaaSOrder.handleTableDataChanged");
	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
	}

	@Override
	protected int getBaseInfoAreaRowCount() {
		return 0;
	}
	
	/**
	 * 订单总金额的标题：销售订单一般为“订单金额”，退货单为“退货总额”
	 * @return
	 */
	protected abstract String getOrderTotalAmountTitle();
	

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		OrderInfo orderInfo = ((OrderDetailProcessor<?>)this.processor).orderInfo;
		if(null == orderInfo || null == orderInfo.getOrderNumber() ||
				"".equals(orderInfo.getOrderNumber())||
				orderInfo.getOrderStatus().isIn(OrderStatus.Submit, OrderStatus.Denied)){
			Button button1 = new Button(tableButtonArea, JWT.APPEARANCE2);
			button1.setText(" 添加材料 ");
			button1.setID(OrderDetailProcessor.ID_BUTTON_ADDGOODS);
		}
	}
}
