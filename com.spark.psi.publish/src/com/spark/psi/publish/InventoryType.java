package com.spark.psi.publish;

/**
 * 库存类型
 */

public enum InventoryType {
	/**材料库存 */
	Materials("01","材料库存"),
	/** 商品库存 */
	Goods("02","商品库存"),
	/** 其他库存 */
	Others("03","其他库存");
	
	private String code,name;
	private InventoryType(String code,String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static InventoryType getEnum(String code) {
		if(InventoryType.Goods.getCode().equals(code)) {
			return Goods;
		} else if(InventoryType.Others.getCode().equals(code)){
			return Others;
		}
		else if(InventoryType.Materials.getCode().equals(code)){
			return Materials;
		}else {
			return null;
		}
	}
	
}
