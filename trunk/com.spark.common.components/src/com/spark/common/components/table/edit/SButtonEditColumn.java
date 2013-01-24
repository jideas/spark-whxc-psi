package com.spark.common.components.table.edit;

import com.jiuqi.dna.core.situation.Situation;

/**
 * 带按钮的文本输入框编辑列
 */
public class SButtonEditColumn extends SEditColumn {

	/**
	 * 
	 */
	private ClickHandler handler;

	/**
	 * 
	 * @param name
	 * @param width
	 * @param align
	 * @param titles
	 */
	public SButtonEditColumn(String name, int width, int align,
			String... titles) {
		super(name, width, align, titles);
	}

	/**
	 * 
	 * @return
	 */
	public ClickHandler getHandler() {
		return handler;
	}

	/**
	 * 
	 * @param handler
	 */
	public void setHandler(ClickHandler handler) {
		this.handler = handler;
	}

	/**
	 * 
	 */
	public static interface ClickHandler {
		public void processButontClick(Situation context, String value,
				String text, CellValueSetter valueSetter);
	}

	/**
	 * 
	 * @author leezizi
	 * 
	 */
	public static interface CellValueSetter {
		public void setValue(String value, String text);
	}

}
