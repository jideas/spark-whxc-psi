/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.layouts.RowLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.portal.browser.MsgCancel;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.order.browser.util.IPurchaseGoodsGroup;
import com.spark.psi.order.browser.util.PurchaseUtil;
import com.spark.psi.order.browser.util.IPurchaseGoodsGroup.PurchaseGroupGoods;
import com.spark.psi.order.browser.util.IPurchaseGoodsGroup.PurchaseOrderGroup;
import com.spark.psi.order.browser.util.IPurchaseGoodsGroup.SupplierGroup;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderTask;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderTask.PurchaseOrderGoodsItem;

/**
 * 
 *
 */
public class GeneratePurchaseOrderProcessor extends BaseFormPageProcessor {

	private IPurchaseGoodsGroup purchaseGoodsGroup;
	private List<SupplierGroup> supplierList = new ArrayList<SupplierGroup>();

	public final static String ID_Button_Confirm = "Button_Confirm";
	public final static String ID_Form_Area = "Form_Area";

	private Composite formArea;

	private List<Text> datePickerList = new ArrayList<Text>();
	private List<Text> memoTextList = new ArrayList<Text>();

	@Override
	public void init(Situation context) {
		super.init(context);
		this.purchaseGoodsGroup = (IPurchaseGoodsGroup) this.getArgument();
		for (int i = 0; i < this.purchaseGoodsGroup.getSupplierGroupList().length; i++) {
			supplierList.add(this.purchaseGoodsGroup.getSupplierGroupList()[i]);
		}
	}

	@Override
	public void process(Situation context) {
		this.createControl(ID_Button_Confirm, Button.class).addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				UpdatePurchaseOrderTask task = null;
				if (supplierList.size() > 0) {
					SupplierGroup supplierGroup = supplierList.get(0);
					PurchaseOrderGroup[] purchaseOrderGroups = purchaseGoodsGroup
							.getPurchaseOrderGroupList(supplierGroup.getId());
					for (int i = 0; i < purchaseOrderGroups.length; i++) {
						PurchaseOrderGroup pog = purchaseOrderGroups[i];
						task = new UpdatePurchaseOrderTask();
						String dateText = datePickerList.get(i).getText();
						if (StringUtils.isEmpty(dateText)) {
							alert("交货日期不能为空");
							datePickerList.get(i).forceFocus();
							datePickerList.get(i).applyMode(JWT.MODE_ERROR);
							return;
						}
						task.setDeliveryDate(DateUtil.getDayEndTime(DateUtil.convertStringToDate(dateText)));
						task.setMemo(memoTextList.get(i).getText());
						//
						task.setDirectSupply(pog.isDirect());
						// task.setId(id);
						task.setPartnerId(supplierGroup.getId());
						task.setRelatedId(pog.getSourceId());
						task.setRelateName(pog.getSourceName());
						List<PurchaseOrderGoodsItem> list = new ArrayList<PurchaseOrderGoodsItem>();
						for (PurchaseGroupGoods goods : pog.getGoodsItems()) {
							list.add(PurchaseUtil.getGoodsItem(getContext(), goods));
						}
						task.setPurchaseOrderGoodsItems(list.toArray(new PurchaseOrderGoodsItem[list.size()]));
						getContext().handle(task, UpdatePurchaseOrderTask.Method.Create);
						//
					}
					//
					MsgResponse response = new MsgResponse(false, purchaseOrderGroups);
					getContext().bubbleMessage(response);

					supplierList.remove(0);
					if (supplierList.size() > 0) {
						createOrderDetailView();
					} else {
						getContext().bubbleMessage(new MsgCancel());
					}
				}
			}
		});
		formArea = createControl(ID_Form_Area, Composite.class);
		formArea.setLayout(new GridLayout());

		//
		createOrderDetailView();
	}

	private void createOrderDetailView() {
		formArea.clear();
		datePickerList.clear();
		memoTextList.clear();
		if (supplierList.size() > 0) {

			//
			SupplierGroup supplierGroup = supplierList.get(0);

			//
			Label supplierNameLabel = new Label(formArea);
			supplierNameLabel.setLayoutData(GridData.INS_FILL_HORIZONTAL);
			supplierNameLabel.setText("供应商：" + supplierGroup.getName());

			ScrolledPanel scrolledPanel = new ScrolledPanel(formArea, JWT.V_SCROLL);
			scrolledPanel.setLayoutData(GridData.INS_FILL_BOTH);
			CBorder border = new CBorder(1, JWT.BORDER_STYLE_SOLID, SSeparator.color.getColor());
			scrolledPanel.setBorder(border);
			Composite workArea = scrolledPanel.getComposite();
			GridLayout gl = new GridLayout();
			gl.verticalSpacing = 10;
			workArea.setLayout(gl);

			Label infoLabel = new Label(formArea);
			infoLabel.setLayoutData(GridData.INS_FILL_HORIZONTAL);
			infoLabel.setText("还有" + (supplierList.size() - 1) + "个供应商的订单需要生成");
			//
			PurchaseOrderGroup[] purchaseOrderGroups = this.purchaseGoodsGroup.getPurchaseOrderGroupList(supplierGroup
					.getId());
			for (int i = 0; i < purchaseOrderGroups.length; i++) {
				PurchaseOrderGroup purchaseOrderGroup = purchaseOrderGroups[i];
				Composite groupArea = new Composite(workArea);
				groupArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
				gl = new GridLayout();
				groupArea.setLayout(gl);

				Composite area1 = new Composite(groupArea);
				area1.setLayoutData(GridData.INS_FILL_HORIZONTAL);
				gl = new GridLayout();
				gl.numColumns = 3;
				gl.marginLeft = 10;
				gl.marginTop = 2;
				area1.setLayout(gl);
				area1.setBackground(new Color(0xC3DFE9));

				Label sourceNameLabel = new Label(area1);

				sourceNameLabel.setText("仓库：" + purchaseOrderGroup.getSourceName());

				GridData gd = new GridData();
				gd.widthHint = 300;
				sourceNameLabel.setLayoutData(gd);

				new SAsteriskLabel(area1, "交货日期：");
				SDatePicker dp = new SDatePicker(area1);
				datePickerList.add(dp);
				if (purchaseOrderGroup.isDirect()) {
					dp.setDate(new Date(purchaseOrderGroup.getDeliveryDate()));
					dp.setButtonVisible(false);
				}

				Composite area2 = new Composite(groupArea);
				area2.setLayoutData(GridData.INS_FILL_HORIZONTAL);
				gl = new GridLayout();
				gl.marginLeft = 10;
				gl.numColumns = 2;
				area2.setLayout(gl);
				Label label = new Label(area2);
				label.setText("备注：");
				gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
				gd.verticalIndent = 3;
				label.setLayoutData(gd);

				Text text = new Text(area2, JWT.MULTI | JWT.APPEARANCE3 | JWT.WRAP | JWT.V_SCROLL);
				gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.heightHint = 60;
				text.setLayoutData(gd);
				memoTextList.add(text);
				if (purchaseOrderGroup.isDirect()) {
					text.setText(purchaseOrderGroup.getSalesMemo());
					text.setEditable(false);
				}

				Composite area3 = new Composite(groupArea);
				area3.setLayoutData(GridData.INS_FILL_HORIZONTAL);
				gl = new GridLayout();
				gl.numColumns = 2;
				gl.marginLeft = 10;
				area3.setLayout(gl);

				//
				label = new Label(area3);
				label.setText("材料：");
				gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
				label.setLayoutData(gd);

				Composite goodsInfoArea = new Composite(area3);
				goodsInfoArea.setLayoutData(GridData.INS_FILL_BOTH);
				RowLayout rl = new RowLayout();
				rl.wrap = true;
				goodsInfoArea.setLayout(rl);
				// label = new Label(goodsInfoArea, JWT.WRAP);
				// label.setLayoutData(GridData.INS_FILL_BOTH);

				PurchaseGroupGoods[] goodsList = purchaseOrderGroup.getGoodsItems();
				for (PurchaseGroupGoods goods : goodsList) {
					StringBuffer buffer = new StringBuffer();
					buffer.append(goods.getGoodsName());
					buffer.append('(');
					buffer.append(DoubleUtil.getRoundStr(goods.getPrice()));
					buffer.append('×');
					buffer.append(DoubleUtil.getRoundStr(goods.getCount()));
					buffer.append(')');
					buffer.append("   ");
					new Label(goodsInfoArea).setText(buffer.toString());
				}

			}
		}
		formArea.layout();
	}
}
