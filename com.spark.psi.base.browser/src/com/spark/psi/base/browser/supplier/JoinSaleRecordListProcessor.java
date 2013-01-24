package com.spark.psi.base.browser.supplier;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.b2c.publish.JointVenture.entity.JointVentureRecordItem;
import com.spark.b2c.publish.JointVenture.entity.JointVentureRecordListEntity;
import com.spark.b2c.publish.JointVenture.key.GetJointVentureRecordListKey;
import com.spark.common.components.controls.text.SSearchText2;
import com.spark.common.components.table.STableStatus;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;

public class JoinSaleRecordListProcessor<Item> extends
		PSIListPageProcessor<Item> {

	public static final String ID_Label_SupplierName = "Label_SupplierName";
	public static final String ID_Label_Count = "Label_Count";
	public static final String ID_Search = "Search";
	
	public static enum ColumnName {
		sheetNo("订单编号"), 
		materialName("材料名称"), 
		materialCode("材料编号"), 
		materialNo("材料条码"), 
		price("单价"), 
		count("数量"), 
		amount("金额"), 
		isAlreadySettlement("结算状态");
		
		private String title;
		private ColumnName(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return this.title;
		}
	}
	
	private Label countLabel   = null;
	private SSearchText2 search = null;
	
	private SupplierInfo supplierInfo = null;
	
	
	@Override
	public void init(Situation context) {
		super.init(context);
		supplierInfo = (SupplierInfo)getArgument();
	}
	@Override
	public void process(Situation context) {
		super.process(context);
		final Label nameLabel = createLabelControl(ID_Label_SupplierName);
		nameLabel.setText(supplierInfo.getShortName());
		countLabel = createLabelControl(ID_Label_Count);
		search = createControl(ID_Search, SSearchText2.class);
		search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				table.render();
			}
		});
	}
	
	@Override
	public String getElementId(Object element) {
		JointVentureRecordItem item = (JointVentureRecordItem)element;
		return item.getRECID().toString();
	}

	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		GetJointVentureRecordListKey key = new GetJointVentureRecordListKey(0, Integer.MAX_VALUE, true, supplierInfo.getId());
		key.setSearchText(search.getText());
		ListEntity<JointVentureRecordItem> listEntity = context.find(JointVentureRecordListEntity.class, key);
		countLabel.setText("" + listEntity.getItemList().size());
		return listEntity.getItemList().toArray(new JointVentureRecordItem[0]);
	}
	@Override
	protected String getExportFileTitle() {
		return "联营销售记录";
	}

}
