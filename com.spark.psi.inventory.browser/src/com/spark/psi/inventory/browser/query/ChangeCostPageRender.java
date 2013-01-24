package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.ListItem;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.portal.browser.MsgCancel;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.StoreStatus;
import com.spark.psi.publish.base.store.entity.StoreItem;
import com.spark.psi.publish.base.store.key.GetStoreListKey;

public class ChangeCostPageRender extends BaseFormPageRender{

	@Override
	protected void renderButton(Composite buttonArea){
		Button buttonSure = new Button(buttonArea, JWT.APPEARANCE3);
		buttonSure.setID(ChangeCostPageProcessor.SURE);
		buttonSure.setText("确定添加");
		Button buttonCancel = new Button(buttonArea, JWT.APPEARANCE3);
		buttonCancel.setText("放弃添加");
		buttonCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getContext().bubbleMessage(new MsgCancel());
			}
		});
	}

	@Override
	protected void renderFormArea(Composite form){
		form.setLayout(new GridLayout());
		initRow1(form);
		initRow2(form);
		initRow3(form);
		initRow4(form);

	}

	private void initRow1(Composite form){
		Composite row = new Composite(form);
		GridLayout gl = new GridLayout();
		gl.horizontalSpacing = 8;
		gl.numColumns = 2;
		row.setLayout(gl);

		GridData formData = new GridData();
		formData.grabExcessHorizontalSpace = true;
		formData.grabExcessVerticalSpace = true;
		formData.horizontalAlignment = JWT.FILL;
		formData.heightHint = 22;
		row.setLayoutData(formData);

		Composite left = new Composite(row);
		left.setLayout(new GridLayout(2));
		GridData gd1 = new GridData();
		gd1.grabExcessVerticalSpace = true;
		gd1.verticalAlignment = JWT.CENTER;
		gd1.widthHint = 280;
		left.setLayoutData(gd1);
		Label l = new Label(left);
		l.setText("编号/条码：");

		Text goodsButton = new Text(left, JWT.BUTTON | JWT.APPEARANCE3);
		goodsButton.setEditable(false);
		goodsButton.setLayoutData(formData);
		goodsButton.setID(ChangeCostPageProcessor.GOODSBUTTON);
		left.layout();

		Composite right = new Composite(row);
		right.setLayoutData(gd1);
		right.setLayout(new GridLayout(2));

		Label goodsNameTitle = new Label(right);
		goodsNameTitle.setText("商品名称：");
		Label goodsName = new Label(right);
		goodsName.setID(ChangeCostPageProcessor.GoodsName);
		goodsName.setLayoutData(gd1);
		goodsName.setText("--");
	}

	private void initRow2(Composite form){
		Composite row = new Composite(form);

		GridLayout gl = new GridLayout();
		gl.horizontalSpacing = 8;
		gl.numColumns = 4;
		row.setLayout(gl);

		GridData formData = new GridData();
		formData.grabExcessHorizontalSpace = true;
		formData.grabExcessVerticalSpace = true;
		formData.heightHint = 22;
		row.setLayoutData(formData);
		Label goodsAttrTitle = new Label(row);
		goodsAttrTitle.setText(" 商品规格：");

		Label goodsAttr = new Label(row);
		goodsAttr.setText("--");
		goodsAttr.setID(ChangeCostPageProcessor.GoodsAttr);

		Label goodsUnitTitle = new Label(row);
		goodsUnitTitle.setText("     单位：");

		Label goodsUnit = new Label(row);
		goodsUnit.setText("--");
		goodsUnit.setID(ChangeCostPageProcessor.GoodsUnit);
	}

	@SuppressWarnings("unchecked")
	private void initRow3(Composite form){
		Composite row = new Composite(form);
		GridLayout gl = new GridLayout();
		gl.horizontalSpacing = 8;
		gl.numColumns = 2;
		row.setLayout(gl);

		GridData formData = new GridData();
		formData.grabExcessHorizontalSpace = true;
		formData.grabExcessVerticalSpace = true;
		formData.horizontalAlignment = JWT.FILL;
		formData.heightHint = 22;
		row.setLayoutData(formData);

		Composite left = new Composite(row);
		left.setLayout(new GridLayout(2));
		GridData gd1 = new GridData();
		gd1.grabExcessVerticalSpace = true;
		gd1.verticalAlignment = JWT.CENTER;
		gd1.widthHint = 280;
		left.setLayoutData(gd1);
		Label l = new Label(left);
		l.setText(" 调整仓库：");

		ComboList storesList = new ComboList(left);
		storesList.setID(ChangeCostPageProcessor.STORELIST);
		storesList.setEditable(false);
		storesList.setLayoutData(formData);
		ListEntity<StoreItem> result =
		        getContext().find(ListEntity.class, new GetStoreListKey(StoreStatus.ENABLE, StoreStatus.ONCOUNTING));
		ListItem li0 = new ListItem(storesList.getList());
		li0.setData(null);
		li0.setText("请选择仓库");
		storesList.setSelection(0);
		if(null != result) for(StoreItem s : result.getItemList()){
			ListItem li = new ListItem(storesList.getList());
			li.setData(s.getId());
			li.setText(s.getName());
		}
		left.layout();

		Composite right = new Composite(row);
		right.setLayoutData(gd1);
		right.setLayout(new GridLayout(2));

		Label goodsNameTitle = new Label(right);
		goodsNameTitle.setText("当前库存数量：");
		Label goodsCount = new Label(right);
		goodsCount.setID(ChangeCostPageProcessor.GoodsCount);
		goodsCount.setLayoutData(gd1);
		goodsCount.setText("--");
	}

	private void initRow4(Composite form){
		Composite row = new Composite(form);

		GridLayout gl = new GridLayout();
		gl.horizontalSpacing = 8;
		gl.numColumns = 2;
		row.setLayout(gl);

		GridData formData = new GridData();
		formData.grabExcessHorizontalSpace = true;
		formData.grabExcessVerticalSpace = true;
		formData.horizontalAlignment = JWT.FILL;
		formData.heightHint = 45;

		row.setLayoutData(formData);

		GridData formData1 = new GridData();
		formData1.heightHint = 45;
		formData1.widthHint = 280;

		Composite left = new Composite(row);
		left.setBorder(new CBorder(1, 0, 0x8FA3AE));
		left.setBackground(Color.COLOR_WHITE);
		left.setLayoutData(formData1);
		left.setLayout(new GridLayout());
		left.getParent().layout();

		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;

		Composite l1 = new Composite(left);
		l1.setLayoutData(gd);
		l1.setLayout(new GridLayout(2));
		Label nowCostTitle = new Label(l1);
		nowCostTitle.setText("    调整前成本：");
		Label nowCost = new Label(l1);
		nowCost.setText("--");
		nowCost.setID(ChangeCostPageProcessor.NowCost);

		Composite l2 = new Composite(left);
		l2.setLayoutData(gd);
		l2.setLayout(new GridLayout(2));
		Label nowInventoryAmountTitle = new Label(l2);
		nowInventoryAmountTitle.setText("调整前库存金额：");
		Label nowInventoryAmount = new Label(l2);
		nowInventoryAmount.setText("--");
		nowInventoryAmount.setID(ChangeCostPageProcessor.NowInventoryAmount);

		Composite right = new Composite(row);
		right.setBorder(new CBorder(1, 0, 0x8FA3AE));
		right.setLayoutData(formData1);
		right.setLayout(new GridLayout());
		right.setBackground(Color.COLOR_WHITE);

		Composite r1 = new Composite(right);
		r1.setLayoutData(gd);
		r1.setLayout(new GridLayout(2));
		Label willCostTitle = new Label(r1);
		willCostTitle.setText("    调整后成本：");
		GridData gd2 = new GridData();
		gd2.widthHint = 100;
		final Text willCost = new Text(r1, JWT.APPEARANCE3);
		willCost.setRegExp(SAAS.Reg.REGEXP_POSITIVE_DOUBLE);
		willCost.setID(ChangeCostPageProcessor.TEXT);
		willCost.setLayoutData(gd2);
		willCost.getParent().layout();

		Composite r2 = new Composite(right);
		r2.setLayoutData(gd);
		r2.setLayout(new GridLayout(2));
		Label willInventoryAmountTitle = new Label(r2);
		willInventoryAmountTitle.setText("调整后库存金额：");

		Label willInventoryAmount = new Label(r2);
		willInventoryAmount.setText("--");
		willInventoryAmount.setID(ChangeCostPageProcessor.WillInventoryAmount);

	}
}
