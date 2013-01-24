package com.spark.psi.inventory.browser.checkin;

import java.text.SimpleDateFormat;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.BaseFormPageProcessor;
import com.spark.psi.publish.inventory.sheet.entity.CheckedInItem;
import com.spark.psi.publish.inventory.sheet.entity.CheckedOutItem;

public class InfoPageProcessor extends BaseFormPageProcessor {

	private String nvl(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	public final static String ID_Composite_ResultSet = "Composite_ResultSet";
	Composite cmp;

	@Override
	public void process(Situation context) {
		cmp = this.createControl(ID_Composite_ResultSet, Composite.class);
		ShowType type = (ShowType) this.getArgument2();
		if (type.equals(ShowType.CheckIngIn)) {
			createItem((CheckedInItem[]) this.getArgument());
		} else if (type.equals(ShowType.CheckIngOut)) {
			createOutItem((CheckedOutItem[]) this.getArgument());
		}
	}

	private void createOutItem(CheckedOutItem[] items) {
		cmp.setBackground(Color.COLOR_WHITE);
		cmp.setBorder(new CBorder(1, 0, 0x78a9bf));
		int len = items.length;
		int i = 0;
		for (CheckedOutItem item : items) {

			Composite cmposite = new Composite(cmp);
			GridData gridData2 = new GridData(GridData.GRAB_HORIZONTAL
					| GridData.HORIZONTAL_ALIGN_FILL);
			gridData2.heightHint = 40;
			gridData2.widthHint = 353;
			GridLayout layout = new GridLayout(1);
			layout.marginLeft = 5;
			layout.marginTop = 5;
			layout.verticalSpacing = 0;
			cmposite.setLayout(layout);
			cmposite.setLayoutData(gridData2);
			if (0 == i % 2) {
				cmposite.setBackground(new Color(0xd6f3fc));
			}

			Composite top = new Composite(cmposite);
			GridData gd = new GridData(GridData.GRAB_HORIZONTAL
					| GridData.HORIZONTAL_ALIGN_FILL);
			gd.heightHint = 20;
			top.setLayout(new GridLayout(2));
			top.setLayoutData(gd);
			Composite leftCmp = new Composite(top,JWT.FILL);
			leftCmp.setLayout(new GridLayout(1));
			GridData leftGd = new GridData(GridData.FILL_VERTICAL);
			leftGd.widthHint = 107;
			leftCmp.setLayoutData(new GridData(GridData.FILL_BOTH));
			leftCmp.setLayoutData(leftGd);
			Label lbl = new Label(leftCmp, JWT.RIGHT);
			StringBuffer takerText = new StringBuffer();
			takerText.append("提货：");
			takerText.append(nvl(item.getTaker()));
			int takerMaxLength = getMaxLength(takerText,20);
			lbl.setText(takerText.length() > takerMaxLength ? takerText.substring(0,
					takerMaxLength) : takerText.toString());
			Composite rightCmp = new Composite(top,JWT.FILL);
			rightCmp.setLayout(new GridLayout(1));
			GridData rightGd = new GridData(GridData.FILL_BOTH);
			rightCmp.setLayoutData(rightGd);
			StringBuffer unitText = new StringBuffer();
			if (null != item.getTakerUnit() && !"".equals(item.getTakerUnit())) {
				unitText.append("　提货单位：").append(nvl(item.getTakerUnit()));
			}
			Label rLbl =  new Label(rightCmp,JWT.LEFT);
			int unitMaxLength = getMaxLength(unitText,42);
			rLbl.setText(unitText.length() > unitMaxLength ? unitText.substring(0,
					unitMaxLength) : unitText.toString());
			Composite buttom = new Composite(cmposite);
			buttom.setLayout(new GridLayout(2));
			buttom.setLayoutData(gd);
			Composite bleftCmp = new Composite(buttom,JWT.FILL);
			bleftCmp.setLayout(new GridLayout(1));
			GridData bleftGd = new GridData(GridData.FILL_VERTICAL);
			bleftGd.widthHint = 190;
			bleftCmp.setLayoutData(new GridData(GridData.FILL_BOTH));
			bleftCmp.setLayoutData(bleftGd);
			Label bleftLbl = new Label(bleftCmp, JWT.RIGHT);
			StringBuffer checkedOutText = new StringBuffer();
			checkedOutText.append("确认出库：").append(
					nvl(item.getCheckedOutPersonName()));
			checkedOutText.append("(").append(
					new SimpleDateFormat("yyyy-MM-dd").format(item
							.getCheckOutDate()));
			checkedOutText.append(")");
			int checkedOutMaxLength = getMaxLength(checkedOutText,34);
			bleftLbl.setText(checkedOutText.length() > checkedOutMaxLength ? checkedOutText.substring(0,
					checkedOutMaxLength) : checkedOutText.toString());
			Composite brightCmp = new Composite(buttom,JWT.FILL);
			brightCmp.setLayout(new GridLayout(1));
			GridData brightGd = new GridData(GridData.FILL_BOTH);
			brightCmp.setLayoutData(brightGd);
			Label brightLbl = new Label(brightCmp,JWT.LEFT);
			StringBuffer numberText = new StringBuffer();
			if (null != item.getVouchersNumber()
					&& !"".equals(item.getVouchersNumber())) {
				numberText.append("　凭证号：").append(
						nvl(item.getVouchersNumber()));
			}
			brightLbl.setText(numberText.toString());
			int numberMaxLength = getMaxLength(numberText,28);
			brightLbl.setText(numberText.length() > numberMaxLength ? numberText
					.substring(0, numberMaxLength) : numberText.toString());
			if (i < len - 1) {
				Composite line = new Composite(cmp);
				GridData linegd = new GridData(GridData.GRAB_HORIZONTAL
						| GridData.HORIZONTAL_ALIGN_FILL);
				linegd.heightHint = 1;
				line.setBackground(new Color(0xb5d7e3));
				line.setLayout(new GridLayout(1));
				line.setLayoutData(linegd);
			}
			i++;
		}
	}

	private int getMaxLength(StringBuffer takerText,int maxLength) {

		int len = 0;
		int endIndex = 1;
		char[] chars = takerText.toString().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			len += 1;
			if (isGBK(bytes) || Character.isUpperCase(chars[i])) {
				len += 1;
			}
			if (len > maxLength) {
				break;
			}
			endIndex = i;
		}
		if (len < maxLength) {
			endIndex = chars.length;
		}
		return endIndex;
	}

	private boolean isGBK(byte[] bytes) {
		if (bytes.length == 2) {
			int[] ints = new int[2];
			ints[0] = bytes[0] & 0xff;
			ints[1] = bytes[1] & 0xff;
			if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
					&& ints[1] <= 0xFE) {
				return true;
			}
		}
		return false;
	}

	private void createItem(CheckedInItem[] items) {
		cmp.setBackground(Color.COLOR_WHITE);
		cmp.setBorder(new CBorder(1, 0, 0x78a9bf));
		int i = 0;
		int len = items.length;
		for (CheckedInItem item : items) {
			Composite cmposite = new Composite(cmp);
			GridData gridData2 = new GridData(GridData.GRAB_HORIZONTAL
					| GridData.HORIZONTAL_ALIGN_FILL);
			gridData2.heightHint = 25;
			GridLayout layout = new GridLayout(1);
			layout.marginLeft = 5;
			layout.marginTop = 5;
			cmposite.setLayout(layout);
			cmposite.setLayoutData(gridData2);
			if (0 == i % 2) {
				cmposite.setBackground(new Color(0xd6f3fc));
			}
			Label lbl = new Label(cmposite, JWT.RIGHT);
			StringBuffer text = new StringBuffer();
			text.append("确认入库：");
			text.append(nvl(item.getCheckedInPersonName()));
			text.append("(");
			text.append(new SimpleDateFormat("yyyy-MM-dd").format(item
					.getCheckinDate()));
			text.append(")");
			int endIndex = getMaxLength(text,36);
			lbl.setText(text.length()>endIndex?text.substring(0, endIndex):text.toString());
			if (i < len - 1) {
				Composite line = new Composite(cmp);
				GridData linegd = new GridData(GridData.GRAB_HORIZONTAL
						| GridData.HORIZONTAL_ALIGN_FILL);
				linegd.heightHint = 1;
				line.setBackground(new Color(0xb5d7e3));
				line.setLayout(new GridLayout(1));
				line.setLayoutData(linegd);
			}
			i++;
		}
	}
}