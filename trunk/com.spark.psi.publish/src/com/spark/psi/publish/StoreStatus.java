/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.store.store.intf.common
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       zhongxin        
 * ============================================================*/

package com.spark.psi.publish;

/**
 * 仓库状态
 */
public enum StoreStatus{
	ALL("-1", "全部状态 "), //
	/**
	 * 未启用
	 */
	DISABLED("0", "未启用"), //
	/**
	 * 启用中
	 */
	ENABLE("1", "启用 "), //
	/**
	 * 盘点中
	 */
	ONCOUNTING("2", "盘点中 "), //
	/**
	 * 已停用
	 */
	STOP("3", "停用"),
	/**
	 * 停用盘点
	 */
	STOPANDONCOUNTING("4", "盘点中");;

	private String code;
	private String name;

	private StoreStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public static StoreStatus getStatusByCode(String code) {
		if (DISABLED.code.equals(code)) {
			return DISABLED;
		} else if (ENABLE.code.equals(code)) {
			return ENABLE;
		} else if (ONCOUNTING.code.equals(code)) {
			return ONCOUNTING;
		} else if (STOP.code.equals(code)) {
			return STOP;
		} 
		else if (STOPANDONCOUNTING.code.equals(code)) {
			return STOPANDONCOUNTING;
		}else {
			return null;
		}
	}
}
