package com.spark.psi.publish;

/**
 * 仓库类型
 */
public enum StoreType {

	/**
	 * 材料仓库
	 */
	MerterialsStore("01", "材料仓库"), //
	/**
	 * 商品仓库
	 */
	GoodsStore("02", "商品仓库");

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private StoreType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 根据代码获取枚举对象
	 * 
	 * @param code
	 * @return
	 */
	public final static StoreType getCheckingInType(String code) {
		if (MerterialsStore.code.equals(code)) {
			return MerterialsStore;
		} else if (GoodsStore.code.equals(code)) {
			return GoodsStore;
		}
		return null;
	}

}
