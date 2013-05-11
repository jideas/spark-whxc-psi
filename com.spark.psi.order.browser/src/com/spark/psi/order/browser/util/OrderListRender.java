package com.spark.psi.order.browser.util;

import java.util.HashMap;
import java.util.Map;

import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableColumn;
import com.spark.common.components.table.StableUtil;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.browser.PSIListPageRender;
import com.spark.psi.order.browser.util.OrderListProcessor.Columns;
import com.spark.psi.publish.order.entity.OrderItem;

/**
 * <p>订单试图父类</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-20
 */
public abstract class OrderListRender<T extends OrderItem> extends PSIListPageRender{
	private final Map<Integer, Columns> enumMap = new HashMap<Integer, Columns>();

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		new SSearchText2(headerRightArea).setID(OrderListProcessor.ID_TEXT_SEARCH);
	}
	
	
	@Override
	public STableColumn[] getColumns() {
		Columns[] enums = getColumnsEnumList();
		STableColumn[] columns = new STableColumn[enums.length];
		int index = 0;
		for (Columns c : enums) {
			enumMap.put(index, c);
			columns[index++] = getSTableColumn(c);
		}
		return columns;
	}

	/**
	 * 当前列表数组
	 * 
	 * @return OrderDetailProcessor.Cloumns[]
	 */
	protected abstract Columns[] getColumnsEnumList();
	/**
	 * 获得列标题
	 * @param e
	 * @return STableColumn
	 */
	protected STableColumn getSTableColumn(Columns e) {
		STableColumn c = new STableColumn(e.name(), e.getWidth(), e.getAlign(),
				e.getTitle());
		c.setGrab(e.isGrab());
		c.setSortable(e.isSort());
		return c;
	}

	@Override
	public String getText(Object element, int columnIndex) {
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
			Columns columnEnum) {
		if(null == columnEnum){
			return null;
		}
		OrderItem item = (OrderItem) element;
		switch (columnEnum) {
		case DeliveryDate:
			return DateUtil.dateFromat(item.getDeliveryDate());
		case OrderNumber:
			return StableUtil.toLink(Columns.OrderNumber.name(), null, item.getOrderNumber());
		case CreateDate:
			return DateUtil.dateFromat(item.getCreateDate());
		case Creator:
			return item.getCreator();
		case PartnerName:
			return item.getPartnerShortName();
		case status:
			return item.getOrderStatus().getName();
		case Amount:
			return DoubleUtil.getRoundStr(item.getAmount());
		case Type:
			return item.getOrderType().getName();
		default:
			return "";
		}
	}
	@Override
	public String getToolTipText(Object element, int columnIndex) {
		if(enumMap.get(columnIndex) == Columns.PartnerName){
			OrderItem item = (OrderItem) element;
			return item.getPartnerName(); 
		}
		return super.getToolTipText(element, columnIndex);
	}
	
	/**
	 * 当前用户是员工
	 * @return boolean
	 */
	protected abstract boolean isEmployee();
}
