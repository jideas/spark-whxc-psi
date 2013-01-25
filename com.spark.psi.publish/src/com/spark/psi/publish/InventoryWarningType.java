package com.spark.psi.publish;

/**
 * 
 * <p>商品库存预警类型</p>
 *	在商品管理，库存信息页签可以设置
 *		按金额预警
 *		按数量预警


 *
 
 * @version 2012-3-16
 */
public enum InventoryWarningType{
	/** 按所有仓库总和金额预警 */
	ALL_Amount("01","总库金额"),
	/** 按所有仓库总和数量预警 */
	ALL_Count("02","总库数量"),
	/** 按各个仓库的金额预警  */
	Store_Amount("03","分库金额"),
	/** 按各个仓库的数量预警 */
	Store_Count("04","分库数量");

	private String code;
	private String name;

	private InventoryWarningType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return code
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

	public static InventoryWarningType getGoodsWarnningType(String code) {
		for(InventoryWarningType entity : InventoryWarningType.values()){
			if(entity.code.equals(code)){
				return entity;
			}
        }
		return null;
	}

	
}
