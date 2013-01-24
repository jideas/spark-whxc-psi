package com.spark.common.components.controls.text;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.utils.character.DoubleUtil;

/**
 * 
 * @author leezizi
 * 
 */
public class SNumberText extends Text {

	private final static String REGEXP_2 = getReg(2, false);
	private final static String REGEXP_P2 = getReg(2, true);

	private final static String getReg(int decimal, boolean positive) {
		return "^([+" + (positive ? "" : "-") + "]?\\d{0,15}(\\.\\d{0,"
				+ decimal + "})?)$";
	}

	/**
	 * 小数位数
	 */
	private int decimal;

	/**
	 * 
	 * @param parent
	 * @param decimal
	 */
	public SNumberText(Composite parent, int decimal) {
		this(parent, decimal, true);
	}

	public SNumberText(Composite parent, int decimal, boolean positive) {
		super(parent, JWT.APPEARANCE3 | JWT.RIGHT);
		this.decimal = decimal;
		//
		if (decimal == 2) {
			super.setRegExp(positive ? REGEXP_P2 : REGEXP_2);
		} else {
			super.setRegExp(getReg(decimal, positive));
		}
		//
		this.addClientEventHandler(JWT.CLIENT_EVENT_FOCUS_LOST,
				"SComponent.hanleNumberTextFocusLost");
		JSONObject data = new JSONObject();
		try {
			data.put("decimal", decimal);
		} catch (JSONException e) {
		}
		this.setCustomObject("data", data);
	}

	public void setRegExp(String regexp) {
		throw new UnsupportedOperationException("已经内置正则表达式");
	}

	/**
	 * 
	 */
	public void setText(String text) {
		try {
			Double dv = DoubleUtil.strToDouble(text);
			super.setText(DoubleUtil.getRoundStrWithOutQfw(dv, decimal));
			super.setDescription(DoubleUtil.getRoundStr(dv, decimal));
		} catch (Throwable t) {
			super.setText("");
			super.setDescription("");
		}
	}

	/**
	 * 
	 */
	public String getText() {
		return super.getText();
		// throw new UnsupportedOperationException("直接使用getValue方法");
	}

	/**
	 * 
	 */
	public void setDescription(String text) {
		throw new UnsupportedOperationException("不支持");
	}

	/**
	 * 
	 */
	public String getDescription() {
		return super.getDescription();
		// throw new UnsupportedOperationException("不支持");
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.setValue((double) value);
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(long value) {
		this.setValue((double) value);
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(float value) {
		this.setValue((double) value);
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(double value) {
		super.setText(DoubleUtil.getRoundStrWithOutQfw(value, decimal));
		super.setDescription(DoubleUtil.getRoundStr(value, decimal));
	}

	/**
	 * 
	 * @param defaultValue
	 * @return
	 */
	public int getIntValue(int defaultValue) {
		try {
			return Integer.parseInt(super.getText());
		} catch (Throwable t) {
			return defaultValue;
		}
	}

	/**
	 * 
	 */
	public void clearValue() {
		super.setText("");
		super.setDescription("");
	}

	/**
	 * 
	 * @param defaultValue
	 * @return
	 */
	public long getLongValue(long defaultValue) {
		try {
			return Long.parseLong(super.getText());
		} catch (Throwable t) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param defaultValue
	 * @return
	 */
	public float getFloatValue(float defaultValue) {
		try {
			return Float.parseFloat(super.getText());
		} catch (Throwable t) {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @param defaultValue
	 * @return
	 */
	public double getDoubleValue(double defaultValue) {
		try {
			return Double.parseDouble(super.getText());
		} catch (Throwable t) {
			return defaultValue;
		}
	}

}
