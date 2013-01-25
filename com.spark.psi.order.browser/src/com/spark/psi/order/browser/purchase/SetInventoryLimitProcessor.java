package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.publish.inventory.entity.InventoryInfo;

public class SetInventoryLimitProcessor extends PageProcessor{
	public static final String ID_SELECT_COUNT = "limit_selectCount"; 
	public static final String ID_SELECT_AMOUNT = "limit_selectAmount"; 
	public static final String ID_TEXT_UPPER = "limit_upper";
	public static final String ID_TEXT_LOWER = "limit_lower";
	public static final String ID_BUTTON_CONFIRM = "conofirm";
	public static final String ID_BUTTON_CANCEL = "cancel";
	
	private String countUpper, amountUpper, countLower;
	
	private static final String getStr(double d, int decimal){
		if(d <= 0){
			return "";
		}
		return DoubleUtil.getRoundStrWithOutQfw(d, decimal);
	}
	@Override
	public void process(Situation context) {
		//传入参数
		final InventoryInfo gid = (InventoryInfo) getArgument();
		//初始化显示值
		countUpper = getStr(gid.getUpperLimitCount(), gid.getScale());
		amountUpper = getStr(gid.getUpperLimitAmount(), 2);
		countLower = getStr(gid.getLowerLimitCount(), gid.getScale());
		
		final Button count = this.createControl(ID_SELECT_COUNT, Button.class);
		final Button amount = this.createControl(ID_SELECT_AMOUNT, Button.class);
		final Text upper = this.createControl(ID_TEXT_UPPER, Text.class);
		upper.setRegExp(SAAS.Reg.getReg(12, gid.getScale()));
		upper.setText(countUpper);
		final Text lower = this.createControl(ID_TEXT_LOWER, Text.class);
		lower.setRegExp(SAAS.Reg.getReg(12, gid.getScale()));
		lower.setText(countLower);
//		Button confirm = 
		this.createControl(ID_BUTTON_CONFIRM, Button.class).addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				double up = -1, lo = -1;
				if(isNull(upper.getText())){
					alert("库存上限不能为空！");
					return;
				}
				if(count.getSelection() && isNull(lower.getText())){
					alert("库存下限不能为空！");
					return;
				}
				try {
					up = Double.parseDouble(upper.getText());
					if(count.getSelection()){
						lo = Double.parseDouble(lower.getText());
						if(0 >= lo){
							alert("库存下限必须是大于0的数字！");
							return;
						}
						if(lo >= up){
							alert("库存上限应大于库存下限！");
							return;
						}
					}
				} catch (NumberFormatException e1) {
					alert("库存上下限应为整数部分不大于15位的有效数字！");
					return;
				}
				if(0 >= up){
					alert("库存上限必须是大于0的数字！");
					return;
				}
				SetInventoryLimitUtil.LimitType type = count
						.getSelection() ? SetInventoryLimitUtil.LimitType.Count
						: SetInventoryLimitUtil.LimitType.Amount;
				getContext().bubbleMessage(new MsgResponse(true, type, up, lo));
				
			}
		});
//		Button cancel = 
		this.createControl(ID_BUTTON_CANCEL, Button.class).addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});
		
		//
		//
		count.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(count.getSelection()){
					amountUpper = upper.getText();
					lower.getParent().setVisible(true);
					upper.setRegExp(SAAS.Reg.getReg(12, gid.getScale()));
					upper.setText(countUpper);
					lower.setText(countLower);
				}
			}
		});
		amount.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(amount.getSelection()){
					countUpper = upper.getText();
					countLower = lower.getText();
					lower.getParent().setVisible(false);
					upper.setRegExp(SAAS.Reg.getReg(2));
					upper.setText(amountUpper);
				}
			}
		});
	}
	
	private static final boolean isNull(String str){
		if(null == str || "".equals(str)){
			return true;
		}
		return false;
	}
}
