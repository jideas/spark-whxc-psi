/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.table.STableStatus;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.StringHelper;
import com.spark.psi.base.browser.PSIProcessorUtils;
import com.spark.psi.base.browser.QueryScopeSource;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.QueryTerm;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.key.GetSalesOrderListKey;

/**
 * 所有处理完的销售订单列表处理器
 *
 */
public class ProcessedSalesOrderListProcessor extends SalesOrderListProcessor<OrderItem> {

	public final static String ID_COMBOLIST_TYPE = "ComboList_Type";
	public final static String ID_COMBOLIST_DATE = "ComboList_Date";
	//单据数量
	public final static String ID_LABEL_ORDER_COUNT = "Label_Order_Count";
	//销售金额
	public final static String ID_LABEL_SALES_AMOUNT = "Label_Sales_Amount";
	//退货金额
	public final static String ID_LABEL_REJECTED_AMOUNT = "Label_Rejected_Amount";

	private Label lblOrderCount, lblSalesAmount, lblRejectedAmount;
	private LWComboList colstDate, queryScopeList;
	private QueryScopeSource queryScopeSource;
	@Override
	public void process(Situation situation) {
		
		super.process(situation);
		
		//下拉
		if(!isEmployee()){
			queryScopeList = this.createControl(ID_COMBOLIST_TYPE, LWComboList.class);
			queryScopeSource = PSIProcessorUtils.initQueryScopeSource(
					queryScopeList, "我的单据", Auth.Sales);
			queryScopeList.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					table.render();
				}
			});
		}
		
		colstDate = this.createControl(ID_COMBOLIST_DATE,
				LWComboList.class, JWT.NO);
		PSIProcessorUtils.initQueryTermSource(colstDate);
		colstDate.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				table.render();
			}
		});
		
		lblOrderCount = this.createControl(ID_LABEL_ORDER_COUNT, Label.class, JWT.NO);
		lblSalesAmount = this.createControl(ID_LABEL_SALES_AMOUNT, Label.class, JWT.NO);
		lblRejectedAmount = this.createControl(ID_LABEL_REJECTED_AMOUNT, Label.class, JWT.NO);
		
		this.table.getSelection();	
		
	}
	

	@Override
	protected Object[] getOrderItem(STableStatus tablestatus) {
		GetSalesOrderListKey key = new GetSalesOrderListKey(tablestatus.getBeginIndex(), tablestatus.getPageSize(), false);
		key.setOrderStatus(OrderStatus.finish, OrderStatus.Stop);
		if(null != queryScopeSource){
			key.setQueryScope(queryScopeSource.getQueryScope(queryScopeList
					.getText()));
		}
		key.setQueryTerm(getContext().find(QueryTerm.class, colstDate.getText()));
		key.setSearchText(searchText.getText());
		if (null != tablestatus.getSortColumn()) {
			key.setSortField(Columns.valueOf(tablestatus.getSortColumn())
					.getSortField());
		}
		//排序
		setLimitKeySort(key, tablestatus);
		
		OrderListEntity entity = getContext().find(OrderListEntity.class, key);
		long size = entity.getTotalCount();
		double orderAmount = entity.getOrderAmount();
		double returnAmount = entity.getReturnAmount();
		if (tablestatus.getPageNo() != STableStatus.FIRSTPAGE) {
			String preSize = lblOrderCount.getText();
			if (StringHelper.isNotEmpty(preSize)) {
				size += Integer.parseInt(preSize);
			}
			String preOrderAmount = lblSalesAmount.getText();
			if (StringHelper.isNotEmpty(preOrderAmount)) {
				orderAmount = DoubleUtil.sub(orderAmount, DoubleUtil.strToDouble(preOrderAmount));
			}
			String preReturnAmount = lblRejectedAmount.getText();
			if (StringHelper.isNotEmpty(preReturnAmount)) {
				returnAmount = DoubleUtil.sub(returnAmount, DoubleUtil.strToDouble(preReturnAmount));
			}
		}
		lblOrderCount.setText(String.valueOf(size));
		lblSalesAmount.setText(DoubleUtil.getRoundStr(orderAmount));
		lblRejectedAmount.setText(DoubleUtil.getRoundStr(returnAmount));
		return entity.getItemList().toArray();
	}
	
	@Override
	public String[] getTableActionIds() {
		return new String[] {Action.ReExecute.name()};
	}


	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return "材料销售单";
	}
}