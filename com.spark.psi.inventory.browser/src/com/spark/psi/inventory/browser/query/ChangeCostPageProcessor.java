package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.DocumentEvent;
import com.jiuqi.dna.ui.wt.events.DocumentListener;
import com.jiuqi.dna.ui.wt.events.FocusEvent;
import com.jiuqi.dna.ui.wt.events.FocusListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.MsgResponse;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.inventory.intf.entity.inventory.GoodsInventorySum;
import com.spark.psi.inventory.intf.key.inventory.AverageInventoryCostKey;
import com.spark.psi.inventory.intf.key.inventory.GoodsInventorySumKey;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.base.goods.entity.GoodsItemInfo;
import com.spark.psi.publish.inventory.task.AdjustGoodsItemCostTask;

public class ChangeCostPageProcessor extends BaseFormPageProcessor{

	public final static String SURE = "sure";
	public final static String TEXT = "text";
	public final static String GOODSBUTTON = "GOODSBUTTON";
	public final static String STORELIST = "STORELIST";
	public final static String GoodsName = "GoodsName";
	public final static String GoodsAttr = "GoodsAttr";
	public final static String GoodsUnit = "GoodsUnit";
	public final static String GoodsCount = "GoodsCount";
	public final static String NowCost = "NowCost";
	public final static String NowInventoryAmount = "NowInventoryAmount";
	public final static String WillInventoryAmount = "WillInventoryAmount";

	private Text willCost;
	private ComboList storesList;
	private Text goodsButton;
	private Button sure;

	private GoodsItemInfo goods;
	private GUID storeId;
	private double count;
	private Label goodsName, goodsAttr, goodsUnit, goodsCount, nowCost, nowInventoryAmount, willInventoryAmount;

	@Override
	public void process(Situation context){
		sure = this.createControl(SURE, Button.class);
		sure.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				confirm("确定调整当前商品的成本和库存金额？", new Runnable(){
					public void run(){
						if(null == goods){
							alert("请先选择商品！");
							willCost.clear();
							return;
						}
						if(null == storeId){
							alert("请先选择仓库！");
							willCost.clear();
							return;
						}
						if(0 == count){
							alert("选择的仓库没有该商品！");
							willCost.clear();
							return;
						}
						String value = willCost.getText();
						if(CheckIsNull.isEmpty(value)){
							alert("请填写调整后成本！");
							return;
						}
						double v = Double.parseDouble(value);
						AdjustGoodsItemCostTask task =
						        new AdjustGoodsItemCostTask(goods.getItemData().getId(), storeId, v);

						getContext().handle(task);
						hint("添加成功！");
						getContext().bubbleMessage(new MsgResponse(true));
					}
				});
			}
		});
		willCost = this.createControl(TEXT, Text.class, JWT.NO);
		willCost.addDocumentListener(new DocumentListener(){
			public void documentUpdate(DocumentEvent e){
				if(null == goods){
					alert("请先选择商品！");
					willCost.clear();
					willInventoryAmount.setText("--");
					willInventoryAmount.getParent().layout();
					return;
				}
				if(null == storeId){
					alert("请先选择仓库！");
					willCost.clear();
					willInventoryAmount.setText("--");
					willInventoryAmount.getParent().layout();
					return;
				}
				if(0 == count){
					alert("选择的仓库没有该商品！");
					willCost.clear();
					willInventoryAmount.setText("--");
					willInventoryAmount.getParent().layout();
					return;
				}
				String value = willCost.getText();
				if(CheckIsNull.isEmpty(value)){
					willInventoryAmount.setText("--");
					willInventoryAmount.getParent().layout();
					return;
				}
				double v = Double.parseDouble(value);
				double amount = DoubleUtil.mul(v, count);
				willInventoryAmount.setText(DoubleUtil.getRoundStr(amount));
				willInventoryAmount.getParent().getParent().layout();
			}
		});
		willCost.addFocusListener(new FocusListener(){

			public void focusLost(FocusEvent e){
				String text = willCost.getText();
				if(CheckIsNull.isEmpty(text)){
					return;
				}
				double v = Double.parseDouble(text);
				if(0==v){
					alert("调整后成本不能为0！");
					willCost.setText(null);
				}
			}

			public void focusGained(FocusEvent e){
			}
		});
		goodsButton = this.createControl(GOODSBUTTON, Text.class);
		goodsButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				MsgRequest request = CommonSelectRequest.createSelectGoodsRequest(null, false, true, true);
				request.setResponseHandler(new ResponseHandler(){

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4)
					{
						GoodsItemInfo[] itemList = (GoodsItemInfo[])returnValue;
						for(GoodsItemInfo item : itemList){
							goods = item;
						}
						selectedGoods();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		storesList = this.createControl(STORELIST, ComboList.class);
		storesList.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				storeId = (GUID)storesList.getList().getSelection()[0].getData();
				selectedGoods();
			}
		});

		goodsName = this.createControl(GoodsName, Label.class);
		goodsAttr = this.createControl(GoodsAttr, Label.class);
		goodsUnit = this.createControl(GoodsUnit, Label.class);
		goodsCount = this.createControl(GoodsCount, Label.class);
		nowCost = this.createControl(NowCost, Label.class);
		nowInventoryAmount = this.createControl(NowInventoryAmount, Label.class);
		willInventoryAmount = this.createControl(WillInventoryAmount, Label.class);
	}

	private void selectedGoods(){
		this.willCost.clear();
		if(null == goods){
			return;
		}

		this.goodsButton.setText(goods.getBaseInfo().getCode());
		goodsButton.getParent().layout();
		this.goodsName.setText(goods.getBaseInfo().getName());
		this.goodsAttr.setText(goods.getItemData().getPropertiesWithoutUnit());

		this.goodsUnit.setText("个");
		this.goodsUnit.getParent().layout();
		if(null == storeId){
//			Tenant t = getContext().find(Tenant.class);
			GoodsInventorySumKey sumKey = new GoodsInventorySumKey();
			sumKey.setGoodsItemId(goods.getItemData().getId());
			GoodsInventorySum sum = getContext().find(GoodsInventorySum.class, sumKey);
			count = sum.getTotalCount();
			this.goodsCount.setText(DoubleUtil.getRoundStr(sum.getTotalCount(), goods.getItemData().getScale()));
			//TODO
			Double value = getContext().find(Double.class, new AverageInventoryCostKey(goods.getItemData().getId(),InventoryType.Materials));
			this.nowCost.setText(DoubleUtil.getRoundStr(value));
			this.nowInventoryAmount.setText(DoubleUtil.getRoundStr(sum.getTotalAmount()));
			nowCost.getParent().getParent().layout();
			return;
		}
		ResourceToken<Inventory> token =
		        getContext().findResourceToken(Inventory.class, this.storeId, this.goods.getItemData().getId());
		if(null != token && null != token.getFacade()){
			Inventory inventory = token.getFacade();
			count = inventory.getCount();
			this.goodsCount
			        .setText(DoubleUtil.getRoundStr(inventory.getCount(), goods.getItemData().getScale()));
			//TODO
			Double value = getContext().find(Double.class, new AverageInventoryCostKey(goods.getItemData().getId(),InventoryType.Materials));
			this.nowCost.setText(DoubleUtil.getRoundStr(value));
			this.nowInventoryAmount.setText(DoubleUtil.getRoundStr(inventory.getAmount()));
			nowCost.getParent().getParent().layout();
		}
		else{
			count = 0;
			this.goodsCount.setText(DoubleUtil.getRoundStr(0d, goods.getItemData().getScale()));
			this.nowCost.setText(DoubleUtil.getRoundStr(goods.getItemData().getAverageCost()));
			this.nowInventoryAmount.setText(DoubleUtil.getRoundStr(0d));
			nowCost.getParent().getParent().layout();
		}

	}
}
